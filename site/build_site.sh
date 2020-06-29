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

    for path in `find ../docs -maxdepth 4 -mindepth 4 -type f -name "contents.rst" -printf "%h\n" `
    do
        # removes the ../docs/ from the found paths, returning "product/version/language"
        site_path=$(echo "${path}" | cut -f3- -d'/')

        mkdir -p build/input/${site_path}/docs

        cp -R docs/* build/input/${site_path}

        cp -R ../docs/${site_path}/* build/input/${site_path}
    done

	rsync -a homepage/* build/input/homepage --exclude={'*.json','node_modules'}

}

function generate_static_html {
    for path in `find build/input -maxdepth 4 -mindepth 4 -type f -name "contents.rst" -printf "%h\n" `
    do
        # removes the build/input/ from the found paths, returning "product/version/language"
        site_path=$(echo "${path}" | cut -f3- -d'/')

        echo "Generating static html for $site_path"

        #
        # Use Sphinx to generate static HTML for each
        #   product/version/language. The Homepage content is built
        #   separately at the end of the function.
        #
        sphinx-build -M html "build/input/${site_path}" "build/output/${site_path}"

        mv build/output/${site_path}/html/* build/output/${site_path}

        #
        # Fix broken links.
        #

        for html_file_name in `find build/output/${site_path} -name *.html -type f`
        do
            sed -i 's/.md"/.html"/g' ${html_file_name}
            sed -i 's/.md#/.html#/g' ${html_file_name}
            sed -i 's/README.html"/index.html"/g' ${html_file_name}
            sed -i 's/README.html#/index.html#/g' ${html_file_name}
        done

        #
        # Rename README.html to index.html.
        #

        for readme_file_name in `find build/output/${site_path} -name *README.html -type f`
        do
            mv ${readme_file_name} $(dirname ${readme_file_name})/index.html
        done

        #
        # Update search references for README.html to index.html.
        #

        sed -i 's/README"/index"/g' build/output/${site_path}/searchindex.js

        #
        # Make ZIP files.
        #

        for zip_dir_name in `find build/input/${site_path} -name *.zip -type d`
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
