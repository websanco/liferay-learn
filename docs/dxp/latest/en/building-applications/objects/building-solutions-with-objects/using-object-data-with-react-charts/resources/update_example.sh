#!/bin/bash

readonly CURRENT_DIR_NAME=$(dirname "$0")

function generate_remote_app {
	cd liferay-x3j8.zip

	curl -Ls https://github.com/liferay/liferay-portal/raw/master/tools/create_remote_app.sh | bash -s x3j8-remote-app react

	jq -s '.[0] * .[1]' ./x3j8-overlay/package.json ./x3j8-remote-app/package.json > package.json

	mv package.json ./x3j8-remote-app/package.json

	rm -R x3j8-remote-app/src

	cd x3j8-overlay

	cp -R src ../x3j8-remote-app

	cd ../x3j8-remote-app
	
	rm -R ../x3j8-overlay

	yarn install
}

function main {
	pushd "${CURRENT_DIR_NAME}" || exit 1

	generate_remote_app
}

main "${@}"