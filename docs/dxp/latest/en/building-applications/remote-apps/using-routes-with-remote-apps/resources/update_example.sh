#!/bin/bash

readonly CURRENT_DIR_NAME=$(dirname "$0")

function generate_remote_app {
	rm -fr liferay-j1v3.zip

	mkdir liferay-j1v3.zip

	cd liferay-j1v3.zip

	curl -Ls https://github.com/liferay/liferay-portal/raw/master/tools/create_remote_app.sh | bash -s j1v3-remote-app react

	cd j1v3-remote-app

	rm -r node_modules
}

function main {
	pushd "${CURRENT_DIR_NAME}" || exit 1

	generate_remote_app
}

main "${@}"