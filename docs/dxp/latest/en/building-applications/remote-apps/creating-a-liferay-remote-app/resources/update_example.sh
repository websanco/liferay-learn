#!/bin/bash

readonly CURRENT_DIR_NAME=$(dirname "$0")

function generate_remote_app {
	mkdir liferay-h5v7.zip

	cd liferay-h5v7.zip

	curl -Ls https://github.com/liferay/liferay-portal/raw/master/tools/create_remote_app.sh | bash -s liferay-hello-world react

	cd liferay-hello-world

	rm -R node_modules
}

function main {
	pushd "${CURRENT_DIR_NAME}" || exit 1

	generate_remote_app
}

main "${@}"