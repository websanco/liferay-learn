#!/bin/bash

# Putting the logic here so I can test it all by itself without worrying about the whole 
# build_site.sh script. 

curl https://docs.liferay.com/portal/7.4-latest/liferay-ce-portal-doc-7.4.1-ga2-20210609223456272.zip -O

7z x liferay-ce-portal-doc-7.4.1-ga2-20210609223456272.zip 

mv liferay-ce-portal-doc-7.4.1-ga2/* ./build/output/reference/latest/en
rmdir liferay-ce-portal-doc-7.4.1-ga2

#curl https://search.maven.org/remotecontent?filepath=javax/portlet/portlet-api/3.0.1/portlet-api-3.0.1-javadoc.jar -O

mkdir ../site/build/output/reference/latest/en/portlet-api

7z x -o../site/build/output/reference/latest/en/portlet-api portlet-api-3.0.1-javadoc.jar
