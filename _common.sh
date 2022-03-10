#!/bin/bash

readonly LIFERAY_LEARN_COMMERCE_DOCKER_IMAGE_TOKEN=\\[\$LIFERAY_LEARN_COMMERCE_DOCKER_IMAGE\$\\]
readonly LIFERAY_LEARN_COMMERCE_DOCKER_IMAGE_VALUE=liferay\\/commerce\\:2.0.5
readonly LIFERAY_LEARN_COMMERCE_GIT_TAG_TOKEN=\\[\$LIFERAY_LEARN_COMMERCE_GIT_TAG\$\\]
readonly LIFERAY_LEARN_COMMERCE_GIT_TAG_VALUE=2.0.5
readonly LIFERAY_LEARN_COMMERCE_WORKSPACE_TOKEN=
readonly LIFERAY_LEARN_DXP_DOCKER_IMAGE_TOKEN=\\[\$LIFERAY_LEARN_DXP_DOCKER_IMAGE\$\\]
readonly LIFERAY_LEARN_DXP_DOCKER_IMAGE_VALUE=liferay\\/dxp\\:7.4.13-u4
readonly LIFERAY_LEARN_DXP_WORKSPACE_TOKEN=dxp-7.4-u4
readonly LIFERAY_LEARN_PORTAL_DOC_FILE_NAME=liferay-ce-portal-doc-7.4.3.8-ga8-20220117195955276.zip
readonly LIFERAY_LEARN_PORTAL_DOCKER_IMAGE_TOKEN=\\[\$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE\$\\]
readonly LIFERAY_LEARN_PORTAL_DOCKER_IMAGE_VALUE=liferay\\/portal\\:7.4.3.8-ga8
readonly LIFERAY_LEARN_PORTAL_GIT_TAG_TOKEN=\\[\$LIFERAY_LEARN_PORTAL_GIT_TAG\$\\]
readonly LIFERAY_LEARN_PORTAL_GIT_TAG_VALUE=7.4.3.8-ga8
readonly LIFERAY_LEARN_PORTAL_WORKSPACE_TOKEN=\\[\$LIFERAY_LEARN_PORTAL_WORKSPACE\$\\]
readonly LIFERAY_LEARN_PORTAL_WORKSPACE_TOKEN_VALUE=portal-7.4-ga8
readonly LIFERAY_LEARN_YOUTUBE_BEGIN_HTML='\<iframe width\=\"560\" height\=\"315\" src\=\"'
readonly LIFERAY_LEARN_YOUTUBE_END_HTML='\" title\=\"YouTube video player\" frameborder\=\"0\" allow\=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen\>\<\/iframe\>'
readonly LIFERAY_LEARN_YOUTUBE_URL_TOKEN=\\[\$LIFERAY_LEARN_YOUTUBE_URL\$\\]

function download_nexus_jar {
	curl -L "https://repository-cdn.liferay.com/nexus/service/local/artifact/maven/redirect?a=${1}&g=com.liferay&r=liferay-public-releases&v=LATEST" -o $(ls -d liferay*.zip | head -n1)/java/${1}.jar
}

function generate_remote_app {
	git clean -dfx .

	mkdir liferay-${1}.zip

	cd liferay-${1}.zip

	curl -Ls https://github.com/liferay/liferay-portal/raw/master/tools/create_remote_app.sh | bash -s ${1}-remote-app react

	if [ -e ../liferay-${1}-overlay ]
	then

		# TODO Prettify package.json (overlay and merged)

		if [ -e ../liferay-${1}-overlay/package.json ]
		then
			jq -s '.[0] * .[1]' ../liferay-${1}-overlay/package.json ./${1}-remote-app/package.json > package.json

			echo mv package.json ./${1}-remote-app/package.json
		fi

		rm -fr ./${1}-remote-app/src

		cp -r ../liferay-${1}-overlay/src ${1}-remote-app

		cd ${1}-remote-app

		yarn install

		cd ..
	fi

	cd ..
}