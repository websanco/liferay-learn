#!/bin/bash

set -eou pipefail

readonly CURRENT_DIR_NAME=$(dirname "$0")

function check_utils {

	#
	# https://stackoverflow.com/a/677212
	#

	for util in "${@}"
	do
		command -v ${util} >/dev/null 2>&1 || { echo >&2 "The utility ${util} is not installed."; exit 1; }
	done
}

#
# Extract the product, version, and language from paths.
#
function get_site_tokens {
	product=$(echo "${path}" | cut -f3 -d'/')
	version=$(echo "${path}" | cut -f4 -d'/')
	language=$(echo "${path}" | cut -f5 -d'/')
}

function generate_sphinx_input {
	rm -fr build

	cd ../docs

	git clean -dfx .

	./update_examples.sh && ./update_permissions.sh

	cd ../site

	for path in `find ../docs -maxdepth 4 -mindepth 4 -type f -name "contents.rst" -printf "%h\n"`
	do
		get_site_tokens

		mkdir -p build/input/${product}/${version}/${language}/docs

		cp -R docs/* build/input/${product}/${version}/${language}

		cp -R ../docs/${product}/${version}/${language}/* build/input/${product}/${version}/${language}
	done

	rsync -a homepage/* build/input/homepage --exclude={'*.json','node_modules'}
}

function generate_static_html {
	for path in `find build/input -maxdepth 4 -mindepth 4 -type f -name "contents.rst" -printf "%h\n" `
	do
		get_site_tokens

		echo "Generating static html for ${product} ${version} ${language}"

		#
		# Use Sphinx to generate static HTML for each product/version/language.
		#

		sphinx-build -M html "build/input/${product}/${version}/${language}" "build/output/${product}/${version}/${language}"

		mv build/output/${product}/${version}/${language}/html/* build/output/${product}/${version}/${language}

		#
		# Fix broken links.
		#

		for html_file_name in `find build/output/${product}/${version}/${language} -name *.html -type f`
		do
			sed -i 's/.md"/.html"/g' ${html_file_name}
			sed -i 's/.md#/.html#/g' ${html_file_name}
			sed -i 's/README.html"/index.html"/g' ${html_file_name}
			sed -i 's/README.html#/index.html#/g' ${html_file_name}
		done

		#
		# Rename README.html to index.html.
		#

		for readme_file_name in `find build/output/${product}/${version}/${language} -name *README.html -type f`
		do
			mv ${readme_file_name} $(dirname ${readme_file_name})/index.html
		done

		#
		# Update search references for README.html to index.html.
		#

		sed -i 's/README"/index"/g' build/output/${product}/${version}/${language}/searchindex.js

		#
		# Make ZIP files.
		#

		for zip_dir_name in `find build/input/${product}/${version}/${language} -name *.zip -type d`
		do
			pushd ${zip_dir_name}

			local zip_file_name=$(basename ${zip_dir_name})

			zip -r ${zip_file_name} .

			local output_dir_name=$(dirname ${zip_dir_name})

			output_dir_name=$(dirname ${output_dir_name})
			output_dir_name=${output_dir_name/input/output}

			popd

			mv ${zip_dir_name}/${zip_file_name} ${output_dir_name}
		done
	done

	#
	# Use Sphinx to build the Homepage content separately.
	#

	sphinx-build -M html build/input/homepage build/output/homepage

	mv build/output/homepage/html/* build/output/

	rm -r build/output/homepage
}

function main {
	pushd "${CURRENT_DIR_NAME}" || exit 1

	#
	# sudo dnf install python3-sphinx
	#

	python3 -m venv venv

	source venv/bin/activate

	check_utils pip3 zip

	pip_install recommonmark sphinx sphinx-copybutton sphinx-intl sphinx-markdown-tables sphinx-notfound-page

	generate_sphinx_input

	generate_static_html

	upload_to_server
}

function pip_install {
	for package_name in "$@"
	do
		if [[ -z `pip3 list --disable-pip-version-check --format=columns | grep ${package_name}` ]]
		then
			pip3 install --disable-pip-version-check ${package_name}
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