#!/bin/bash

for zip_dir_name in `find . -name "liferay-*.zip" -type d`
do
	cp -fr _template/* ${zip_dir_name}

	#pushd ${dir_name}

	#./gradlew classes formatSource

	#popd
done