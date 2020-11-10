#!/bin/bash

readonly CURRENT_DIR_NAME=$(dirname "$0")

source ../site/tokens.sh

function copy_template {
	local zip_dir_name_pattern="liferay-*.zip"

	if [ ! -z ${1} ]
	then
		zip_dir_name_pattern="liferay-${1}.zip"
	fi

	for zip_dir_name in `find . -name ${zip_dir_name_pattern} -type d`
	do
		if [ "$(find ${zip_dir_name} -name build.gradle | wc -l)" -gt 0 ]
		then
			cp -fr _template/java/* ${zip_dir_name}

			echo -ne "liferay.workspace.product=${LIFERAY_LEARN_PORTAL_WORKSPACE_TOKEN}" > ${zip_dir_name}/gradle.properties

			pushd ${zip_dir_name}

			./gradlew classes formatSource

			popd
		else
			cp -fr _template/js/* ${zip_dir_name}
		fi
	done
}

function update_examples {
	for update_example_script_name in `find . -name "update_example.sh" -type f`
	do
		echo ${update_example_script_name}
	done
}

function main {
	pushd "${CURRENT_DIR_NAME}" || exit 1

	copy_template ${1}

	update_examples
}

main "${@}"