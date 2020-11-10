#!/bin/sh

if [ -z "${NPM_CONFIG_PREFIX}" ]
then
	echo "The environment variable NPM_CONFIG_PREFIX is not set and needs to point to a folder for storing Node packages. Run this command:

	export NPM_CONFIG_PREFIX=~/.npm-global"

	exit
fi

if [[ "${PATH}" != *"${NPM_CONFIG_PREFIX}/bin"* ]]
then
	echo "The environment variable PATH does not include ${NPM_CONFIG_PREFIX}/bin. Add ${NPM_CONFIG_PREFIX}/bin to your PATH. Run this command:

	export PATH=\${PATH}:\${NPM_CONFIG_PREFIX}/bin"

	exit
fi

if  [ ! -d "${NPM_CONFIG_PREFIX}/lib/node_modules/generator-liferay-fragments" ] ||
	[ ! -d "${NPM_CONFIG_PREFIX}/lib/node_modules/generator-liferay-js" ] ||
	[ ! -d "${NPM_CONFIG_PREFIX}/lib/node_modules/generator-liferay-theme" ] ||
	[ ! -d "${NPM_CONFIG_PREFIX}/lib/node_modules/yo" ]
then
	echo "A tutorial dependency is missing. Run this command:

	npm install -g generator-liferay-fragments generator-liferay-js generator-liferay-theme yo"

	exit
fi

echo "Your environment is ready for the tutorial."