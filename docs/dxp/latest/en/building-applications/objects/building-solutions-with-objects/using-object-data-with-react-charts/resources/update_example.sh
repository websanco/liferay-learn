#!/bin/bash

readonly CURRENT_DIR_NAME=$(dirname "$0")

function generate_app {
	cd liferay-x3j8.zip

	curl -Ls https://github.com/liferay/liferay-portal/raw/master/tools/create_remote_app.sh | bash -s x3j8-remote-app react

	rm -R x3j8-remote-app/src

	cd x3j8-overlay

	cp -R src ../x3j8-remote-app

	cd ../x3j8-remote-app
	
	rm -R ../x3j8-overlay

	sed -i '5 a \
	  "fusioncharts": "^3.18.0", \
	  "react-fusioncharts": "^3.1.2",' \
	  package.json

	yarn install
}

function main {
	pushd "${CURRENT_DIR_NAME}" || exit 1

	generate_app
}

main "${@}"