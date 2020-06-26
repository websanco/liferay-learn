#!/bin/bash

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

		#pushd ${dir_name}

		#./gradlew classes formatSource

		#popd
	done
}

function main {
	copy_template
}

main