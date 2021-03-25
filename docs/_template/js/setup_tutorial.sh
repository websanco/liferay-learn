#!/bin/sh

function main {
	if [ -z "${NPM_CONFIG_PREFIX}" ]
	then
		echo "The environment variable NPM_CONFIG_PREFIX is not set and needs to point to a folder for storing Node packages. Run this command:

		export NPM_CONFIG_PREFIX=~/.npm-global"

		exit
	fi

    local export_path_command="export PATH=\${PATH}:\${NPM_CONFIG_PREFIX}/bin"
    local npm_bin_dir_name="${NPM_CONFIG_PREFIX}"/bin
    local npm_modules_dir_name="${NPM_CONFIG_PREFIX}"/lib/node_modules

    if [[ "${OSTYPE}" == "cygwin" ]] &&
       [[ "${OSTYPE}" == "msys" ]]
    then
        export_path_command="export PATH=\${PATH}:\${NPM_CONFIG_PREFIX}"
        npm_bin_dir_name="${NPM_CONFIG_PREFIX}"
        npm_modules_dir_name="${NPM_CONFIG_PREFIX}"/node_modules
    fi

	if [[ "${PATH}" != *"${npm_bin_dir_name}"* ]]
	then
		echo "The environment variable PATH does not include ${npm_bin_dir_name}. Add it to your PATH. Run this command:

		${export_path_command}"

		exit
	fi

	if  [ ! -d "${npm_modules_dir_name}/generator-liferay-fragments" ] ||
		[ ! -d "${npm_modules_dir_name}/generator-liferay-js" ] ||
		[ ! -d "${npm_modules_dir_name}/generator-liferay-theme" ] ||
		[ ! -d "${npm_modules_dir_name}/yarn" ] ||
		[ ! -d "${npm_modules_dir_name}/yo" ]
	then
		echo "A tutorial dependency is missing. Run this command:

		npm install -g generator-liferay-fragments generator-liferay-js generator-liferay-theme yarn yo"

		exit
	fi

	echo "Your environment is ready for the tutorial."
}

main