#!/bin/bash

readonly CURRENT_DIR_NAME=$(dirname "$0")

function copy_template {
	for zip_dir_name in `find . -name "liferay-*.zip" -type d`
	do
		cp -fr _template/* ${zip_dir_name}

		local liferay_workspace_product=portal-7.3-ga3

		if [[ ${zip_dir_name} == ./commerce/** ]]
		then
			liferay_workspace_product=commerce-2.0.7-7.2
		fi

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

	copy_template

	update_examples
}

main "${@}"