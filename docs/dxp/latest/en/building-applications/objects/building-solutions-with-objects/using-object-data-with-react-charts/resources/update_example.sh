#!/bin/bash

readonly CURRENT_DIR_NAME=$(dirname "$0")

function generate_app {
	cd liferay-x3j8.zip

	curl -Ls https://github.com/liferay/liferay-portal/raw/master/tools/create_remote_app.sh | bash -s x3j8-remote-app react

	cd x3j8-overlay

	cp package.json -R ../x3j8-remote-app

	cp src -R ../x3j8-remote-app

	cp public -R ../x3j8-remote-app

	cd ../x3j8-remote-app

	yarn install
}

function main {
	pushd "${CURRENT_DIR_NAME}" || exit 1

	generate_app
}

main "${@}"