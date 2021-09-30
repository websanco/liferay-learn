#!/bin/bash

set -eo pipefail

readonly CURRENT_DIR_NAME=$(dirname "$0")

source ../_common.sh

function activate_venv {
	if [ "$(uname)" == "Darwin" ] || [ "$(uname)" == "Linux" ]
	then
		python3 -m venv venv

		source venv/bin/activate
	else
		if [ -z $(find "${PWD}" -maxdepth 1 -mindepth 1 -name venv -type d) ]
		then
			python -m venv ${PWD}/venv
		fi

		source ${PWD}/venv/scripts/activate
	fi
}

function check_usage {
	if [ "${#}" -eq 0 ]
	then
		return
	fi

	if [ "${#}" -ge 1 ]
	then
		if [ "${1}" != "prod" ]  &&  [[ ${1} != *".md" ]]
		then
			echo "Usage: Pass no arguments to build for development. Pass \"prod\" to build for production. Pass the file name of a markdown article to build a single article preview."

			exit 1
		fi

		if [[ ${1} == *".md" ]]
		then
			local article_file_names=$(find ../docs -name "${1}")

			if [[ "${article_file_names}" -eq 0 ]]
			then
				echo "File ${1} does not exist."

				exit 1
			fi
		fi
	fi
}

function check_utils {

	#
	# https://stackoverflow.com/a/677212
	#

	for util in "${@}"
	do
		command -v ${util} >/dev/null 2>&1 || { echo >&2 "The utility ${util} is not installed."; exit 1; }
	done
}

function configure_env {
	if [ "${1}" == "prod" ]
	then
		rm -fr venv
	fi

	activate_venv

	check_utils 7z pip3

	pip install pipenv

	pipenv install

	if [ "${1}" == "prod" ]
	then
		nodeenv -p --node=15.14.0

		activate_venv

		npm_install generator-liferay-fragments generator-liferay-theme yo
	fi
}

function echo_path {
	if [[ "${1}" == *".md" ]]
	then
		echo "$(find build/output -name ${1%.*}.html)"
	else
		echo "$(find build/output -maxdepth 4 -mindepth 2 -name index.html)"
	fi
}

function generate_sphinx_input {
	rm -fr build

	if [ "${1}" == "prod" ]
	then
		pushd ../docs

		git clean -dfx .

		./update_examples.sh "${1}" && ./update_permissions.sh

		pushd ../site
	fi

	for docs_dir_name in $(find ../docs -maxdepth 4 -mindepth 4 -type f -name "contents.rst" -printf "%h\n")
	do
		local product_version_language_dir_name=$(get_product_version_language_dir_name)

		mkdir -p build/input/"${product_version_language_dir_name}"

		local product_version_english_dir_name=$(get_product_version_language_dir_name | sed 's@/[^/]*$@/en@')

		cp -R docs/* build/input/"${product_version_language_dir_name}"

		if [[ "${1}" == *".md" ]]
		then
			pushd ../docs

			for article_file_name in $(find "${product_version_language_dir_name}" -name "${1}")
			do
				rsync -a ${product_version_language_dir_name}/*.* ${product_version_english_dir_name}/images ../site/build/input/${product_version_language_dir_name}

				local article_dir_name=$(dirname ${article_file_name} | sed "s,${product_version_language_dir_name},,g")

				mkdir -p ../site/build/input/${product_version_language_dir_name}/${article_dir_name}

				cp ${article_file_name} ../site/build/input/${product_version_language_dir_name}/${article_dir_name}

				if [[ -n $(find ${product_version_english_dir_name}/${article_dir_name} -name $(basename -s .md ${article_file_name}) -type d) ]]
				then
					rsync -a ${product_version_english_dir_name}${article_dir_name}/$(basename -s .md ${article_file_name})/* ../site/build/input/${product_version_language_dir_name}/${article_dir_name}/$(basename -s .md ${article_file_name})
				fi
			done

			pushd ../site
		else
			rsync -a --exclude "*.md" --exclude "*.rst" --ignore-existing ../docs/"${product_version_english_dir_name}"/* build/input/"${product_version_language_dir_name}"/

			cp -R ../docs/"${product_version_language_dir_name}"/* build/input/"${product_version_language_dir_name}"
		fi
	done

	rsync -a homepage/* build/input/homepage --exclude={'*.json','node_modules'}

	#
	# Replace tokens.
	#

	for md_file_name in $(find build/input -name "*.md" -type f)
	do
		sed -i "s/${LIFERAY_LEARN_COMMERCE_DOCKER_IMAGE_TOKEN}/${LIFERAY_LEARN_COMMERCE_DOCKER_IMAGE_VALUE}/g" "${md_file_name}"
		sed -i "s/${LIFERAY_LEARN_COMMERCE_GIT_TAG_TOKEN}/${LIFERAY_LEARN_COMMERCE_GIT_TAG_VALUE}/g" "${md_file_name}"
		sed -i "s/${LIFERAY_LEARN_DXP_DOCKER_IMAGE_TOKEN}/${LIFERAY_LEARN_DXP_DOCKER_IMAGE_VALUE}/g" "${md_file_name}"
		sed -i "s/${LIFERAY_LEARN_PORTAL_DOCKER_IMAGE_TOKEN}/${LIFERAY_LEARN_PORTAL_DOCKER_IMAGE_VALUE}/g" "${md_file_name}"
		sed -i "s/${LIFERAY_LEARN_PORTAL_GIT_TAG_TOKEN}/${LIFERAY_LEARN_PORTAL_GIT_TAG_VALUE}/g" "${md_file_name}"
	done
}

function generate_static_html {
	for docs_dir_name in $(find build/input -maxdepth 4 -mindepth 4 -type f -name "contents.rst" -printf "%h\n" )
	do
		local product_version_language_dir_name=$(get_product_version_language_dir_name)

		echo "Generating static HTML for $(get_product_version_language_dir_name)."

		#
		# Use Sphinx to generate static HTML for each product, version, and language.
		#

		sphinx-build -M html "build/input/${product_version_language_dir_name}" "build/output/${product_version_language_dir_name}"

		mv build/output/"${product_version_language_dir_name}"/html/* build/output/"${product_version_language_dir_name}"

		#
		# Fix broken links.
		#

		for html_file_name in $(find build/output/"${product_version_language_dir_name}" -name *.html -type f)
		do
			sed -i '/github\.com\/liferay\/liferay\-learn/ ! s/.md"/.html"/g' ${html_file_name}
			sed -i 's/.md#/.html#/g' ${html_file_name}
			sed -i 's/README.html"/index.html"/g' ${html_file_name}
			sed -i 's/README.html#/index.html#/g' ${html_file_name}
		done

		#
		# Include MP4 files in the output.
		#

		for images_dir in $(find build/input/"${product_version_language_dir_name}" -name images -prune -type d)
		do
			if [[ -n $(find "${images_dir}" -name "*.mp4" -type f) ]]
			then
				mkdir -p "${images_dir/input/output}"

				find "${images_dir}" -name "*.mp4" -type f -exec cp {} "${images_dir/input/output}" \;
			fi
		done

		#
		# Rename README.html to index.html.
		#

		for readme_file_name in $(find build/output/"${product_version_language_dir_name}" -name *README.html -type f)
		do
			mv "${readme_file_name}" "$(dirname "${readme_file_name}")"/index.html
		done

		#
		# Update search references for README.html to index.html.
		#

		sed -i 's/README"/index"/g' build/output/"${product_version_language_dir_name}"/searchindex.js

		#
		# Make ZIP files.
		#

		for zip_dir_name in $(find build/input/"${product_version_language_dir_name}" -name *.zip -type d)
		do
			pushd "${zip_dir_name}"

			local zip_file_name=$(basename "${zip_dir_name}")

			7z a ${zip_file_name} ../${zip_file_name}\

			7z rn ${zip_file_name} ${zip_file_name} ${zip_file_name%.*}

			local output_dir_name=$(dirname "${zip_dir_name}")

			output_dir_name=$(dirname "${output_dir_name}")
			output_dir_name=$(dirname "${output_dir_name}")
			output_dir_name=${output_dir_name/input/output}

			popd

			mkdir -p "${output_dir_name}"

			mv "${zip_dir_name}"/"${zip_file_name}" "${output_dir_name}"
		done
	done

	#
	# Build the Homepage separately.
	#

	sphinx-build -M html build/input/homepage build/output/homepage

	mv build/output/homepage/html/* build/output/

	rm -fr build/output/homepage
}

function get_product_version_language_dir_name {
	local language=$(echo "${docs_dir_name}" | cut -f5 -d'/')
	local product=$(echo "${docs_dir_name}" | cut -f3 -d'/')
	local version=$(echo "${docs_dir_name}" | cut -f4 -d'/')

	echo "${product}"/"${version}"/"${language}"
}

function unzip_reference_docs {

	#
	# liferay-ce-portal-doc-*.zip
	#

	if [[ ${1} != *".md" ]]
	then
		curl -L https://github.com/liferay/liferay-portal/releases/download/"${LIFERAY_LEARN_PORTAL_GIT_TAG_VALUE}"/"${LIFERAY_LEARN_PORTAL_DOC_FILE_NAME}" > liferay-ce-portal-doc.zip

		7z x liferay-ce-portal-doc.zip

		mv liferay-ce-portal-doc-${LIFERAY_LEARN_PORTAL_GIT_TAG_VALUE}/* ./build/output/reference/latest/en/dxp

		rmdir liferay-ce-portal-doc-${LIFERAY_LEARN_PORTAL_GIT_TAG_VALUE}

		rm -f liferay-ce-portal-doc.zip

		#
		# portlet-api-3.0.1-javadoc.jar
		#

		curl https://repo1.maven.org/maven2/javax/portlet/portlet-api/3.0.1/portlet-api-3.0.1-javadoc.jar -O

		mkdir ../site/build/output/reference/latest/en/dxp/portlet-api

		7z x -o../site/build/output/reference/latest/en/portlet-api portlet-api-3.0.1-javadoc.jar

		rm -f portlet-api-3.0.1-javadoc.jar
	fi
}

function main {
	pushd "${CURRENT_DIR_NAME}" || exit 1

	check_usage ${@}

	configure_env ${1}

	generate_sphinx_input ${1}

	generate_static_html

	unzip_reference_docs ${1}

	upload_to_server

	echo_path ${1}
}

function npm_install {
	for package_name in "${@}"
	do
		if [ -z $(npm list --depth=0 --global --loglevel=silent --no-versions --parseable | grep "${package_name}") ]
		then
			npm install -g ${package_name}
		fi
	done
}

function upload_to_server {

	#
	# TODO
	#

	echo upload_to_server
}

main "${@}"