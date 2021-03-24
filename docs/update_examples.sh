#!/bin/bash

readonly CURRENT_DIR_NAME=$(dirname "${0}")

source ../_common.sh

function copy_template {
	local zip_dir_name_pattern="liferay-*.zip"

	if [ -n "${1}" ] && [ "${1}" != "prod" ]
	then
		zip_dir_name_pattern="liferay-${1}.zip"
	fi

	for zip_dir_name in `find . -name ${zip_dir_name_pattern} -type d`
	do
		local gradle_build_file_name="$(echo $(find ${zip_dir_name} -name build.gradle -print) | head -n1)"

		if [ -n "${gradle_build_file_name}" ]
		then
			cp -fr _template/java/* ${zip_dir_name}

			if [ -n "$(grep release.dxp.api $(echo ${gradle_build_file_name}))" ]
			then
				echo -ne "liferay.workspace.product=${LIFERAY_LEARN_DXP_WORKSPACE_TOKEN}" > ${zip_dir_name}/gradle.properties
			else
				echo -ne "liferay.workspace.product=${LIFERAY_LEARN_PORTAL_WORKSPACE_TOKEN}" > ${zip_dir_name}/gradle.properties
			fi

			pushd ${zip_dir_name}

			./gradlew classes formatSource

			if [ "${1}" == "prod" ]
			then
				git clean -d -e "gradle.properties" -fx .

				cp -fr $(git rev-parse --show-toplevel)/docs/_template/java/* .
			fi

			popd
		fi

		local package_json_file_name="$(echo $(find ${zip_dir_name} -name package.json -print) | head -n1)"

		if [ -n "${package_json_file_name}" ]
		then
			cp -fr _template/js/* ${zip_dir_name}
		fi
	done
}

function update_examples {
	if [ -n "${1}" ] && [ "${1}" != "prod" ]
	then
		local zip_dir_name=`find . -name "liferay-${1}.zip" -type d`

		if [ -n "${zip_dir_name}" ]
		then
			pushd "${zip_dir_name}/.."

			./update_example.sh

			popd
		fi
	else
		for update_example_script_name in `find . -name "update_example.sh" -type f`
		do
			pushd $(dirname "${update_example_script_name}")

			./$(basename "${update_example_script_name}")

			popd
		done
	fi
}

function main {
	pushd "${CURRENT_DIR_NAME}" || exit 1

	copy_template ${1}

	update_examples ${1}
}

main "${@}"