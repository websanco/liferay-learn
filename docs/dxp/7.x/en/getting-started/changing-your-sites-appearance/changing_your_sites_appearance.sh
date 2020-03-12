#!/bin/bash

if test -f liferay-5b2v-theme.war
then
	exit 1
fi

if test -d liferay-5b2v-theme
then
	rm -r liferay-5b2v-theme
fi

./changing_your_sites_appearance_resource.sh

cd liferay-5b2v-theme

echo -e "\n#wrapper {" >> src/css/_custom.scss
echo -e "\tbackground-color: #0000ff !important;" >> src/css/_custom.scss
echo "}" >> src/css/_custom.scss

npm run build

cp dist/liferay-5b2v-theme.war ../liferay-5b2v-theme.war