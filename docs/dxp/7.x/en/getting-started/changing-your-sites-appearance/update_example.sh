#!/bin/bash

rm -fr liferay-5b2v-theme

yo liferay-theme:classic --config config.json

cd liferay-5b2v-theme

echo -e "\n#wrapper {" >> src/css/_custom.scss
echo -e "\tbackground-color: #0000ff !important;" >> src/css/_custom.scss
echo "}" >> src/css/_custom.scss

npm run build

cd ..

mv liferay-5b2v-theme/dist/liferay-5b2v-theme.war .

rm -fr liferay-5b2v-theme