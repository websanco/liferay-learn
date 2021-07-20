#!/bin/bash

# Putting the logic here so I can test it all by itself without worrying about the whole 
# build_site.sh script. 

curl https://docs.liferay.com/portal/7.4-latest/liferay-ce-portal-doc-7.4.1-ga2-20210609223456272.zip -O

7z x liferay-ce-portal-doc-7.4.1-ga2-20210609223456272.zip 

mv liferay-ce-portal-doc-7.4.1-ga2/* ./build/input/reference/latest/en/dxp
rmdir liferay-ce-portal-doc-7.4.1-ga2
rm -f liferay-ce-portal-doc-7.4.1-ga2-20210609223456272.zip

curl https://repo1.maven.org/maven2/javax/portlet/portlet-api/3.0.1/portlet-api-3.0.1-javadoc.jar -O

mkdir ../site/build/input/reference/latest/en/dxp/portlet-api

7z x -o../site/build/input/reference/latest/en/portlet-api portlet-api-3.0.1-javadoc.jar
rm -f portlet-api-3.0.1-javadoc.jar
