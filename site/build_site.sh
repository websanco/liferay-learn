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

function generate_sphinx_input {
	rm -fr build

	cd ../docs

	git clean -dfx .

	./update_examples.sh && ./update_permissions.sh

	cd ../site

	for product_name in `find ../docs -maxdepth 1 -mindepth 1 -printf "%f\n" -type d`
	do
		if [[ ${product_name} == _* ]]
		then
			continue
		fi
		for version_name in `find ../docs/${product_name} -maxdepth 1 -mindepth 1 -printf "%f\n" -type d`
		do
            for language in `find ../docs/${product_name}/${version_name} -maxdepth 1 -mindepth 1 -type d -printf "%f\n"`
            do
                if [ ! -f "../docs/${product_name}/${version_name}/${language}/contents.rst" ]; then
                    echo "docs/${product_name}/${version_name}/${language}/contents.rst does not exist, skipping..."
                else
                    mkdir -p build/input/${product_name}/${version_name}/${language}/docs

                    cp -R docs/* build/input/${product_name}/${version_name}/$language

                    cp -R ../docs/${product_name}/${version_name}/${language}/* build/input/${product_name}/${version_name}/${language}
                fi
            done
        done
    done

	rsync -a homepage/* build/input/homepage --exclude={'*.json','node_modules'}
}

function generate_static_html {
    for product_name in `find build/input/ -maxdepth 1 -mindepth 1 -type d -printf "%f\n"`; do
		if [[ ${product_name} == homepage ]]
		then
			continue
		fi

        for version_name in `find build/input/${product_name} -maxdepth 1 -mindepth 1 -type d -printf "%f\n"`; do
            for language in `find build/input/${product_name}/${version_name} -maxdepth 1 -mindepth 1 -type d -printf "%f\n"`; do
                echo "Generating static html for $product_name $version_name $language"

                input_path="build/input/${product_name}/${version_name}/${language}"
                output_path="build/output/${product_name}/${version_name}/${language}"

                #
                # Use Sphinx to generate static HTML for each
                #   product/version/language. The Homepage content is built
                #   separately at the end of the function.
                #
                sphinx-build -M html ${input_path} ${output_path}

                mv ${output_path}/html/* ${output_path}

		#
		# Fix broken links.
		#

		for html_file_name in `find build/output/${dir_name} -name *.html -type f`
		do
			sed -i 's/.md"/.html"/g' ${html_file_name}
			sed -i 's/.md#/.html#/g' ${html_file_name}
			sed -i 's/README.html"/index.html"/g' ${html_file_name}
			sed -i 's/README.html#/index.html#/g' ${html_file_name}
		done

		#
		# Rename README.html to index.html.
		#

		for readme_file_name in `find build/output/${dir_name} -name *README.html -type f`
		do
			mv ${readme_file_name} $(dirname ${readme_file_name})/index.html
		done

		#
		# Update search references for README.html to index.html.
		#

		sed -i 's/README"/index"/g' build/output/${dir_name}/searchindex.js

		#
		# Make ZIP files.
		#

		for zip_dir_name in `find build/input/${dir_name} -name *.zip -type d`
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

	mv build/output/homepage/* build/output

	rmdir build/output/homepage
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
