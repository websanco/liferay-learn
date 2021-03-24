#!/bin/bash

function main {
	local group_name="com.liferay"
	local artifact_name="com.liferay.headless.delivery.client"

	curl -L "https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=${group_name}&a=${artifact_name}&v=LATEST" -o $(ls -d liferay*.zip | head -n1)/java/${artifact_name}.jar
}

main "${@}"