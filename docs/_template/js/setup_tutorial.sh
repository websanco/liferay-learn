#!/bin/sh

function main {
	if [ -z "${NPM_CONFIG_PREFIX}" ]
	then
		echo "The environment variable NPM_CONFIG_PREFIX is not set and needs to point to a folder for storing Node packages. Run this command:

		export NPM_CONFIG_PREFIX=~/.npm-global"

		exit
	fi

    local npm_executables_dir_name="${NPM_CONFIG_PREFIX}"/bin
    local npm_node_modules_dir_name="${NPM_CONFIG_PREFIX}"/lib/node_modules
    local path_export_command="export PATH=\${PATH}:\${NPM_CONFIG_PREFIX}/bin"
    
    if  [[ "$OSTYPE" == "cygwin" ]] &&
        [[ "$OSTYPE" == "msys" ]]
    then
        npm_executables_dir_name="${NPM_CONFIG_PREFIX}"
        npm_node_modules_dir_name="${NPM_CONFIG_PREFIX}"/node_modules
        path_export_command="export PATH=\${PATH}:\${NPM_CONFIG_PREFIX}"
    fi

	if [[ "${PATH}" != *"${npm_executables_dir_name}"* ]]
	then
		echo "The environment variable PATH does not include ${npm_executables_dir_name}. Add it to your PATH. Run this command:

		${path_export_command}"

		exit
	fi

	if  [ ! -d "${npm_node_modules_dir_name}/generator-liferay-fragments" ] ||
		[ ! -d "${npm_node_modules_dir_name}/generator-liferay-js" ] ||
		[ ! -d "${npm_node_modules_dir_name}/generator-liferay-theme" ] ||
		[ ! -d "${npm_node_modules_dir_name}/yo" ]
	then
		echo "A tutorial dependency is missing. Run this command:

		npm install -g generator-liferay-fragments generator-liferay-js generator-liferay-theme yo"

		exit
	fi

	echo "Your environment is ready for the tutorial."
}

main