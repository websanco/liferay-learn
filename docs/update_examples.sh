#!/bin/bash

readonly CURRENT_DIR_NAME=$(dirname "$0")

function copy_template {
	local zip_dir_name_pattern="liferay-*.zip"

	if [ ! -z ${1} ]
	then
		zip_dir_name_pattern="liferay-${1}.zip"
	fi

	for zip_dir_name in `find . -name ${zip_dir_name_pattern} -type d`
	do
		cp -fr _template/* ${zip_dir_name}

		local liferay_workspace_product=portal-7.3-ga6

		echo -ne "liferay.workspace.product=${liferay_workspace_product}" > ${zip_dir_name}/gradle.properties

		pushd ${zip_dir_name}

		./gradlew classes formatSource

		popd
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