#!/bin/sh

function main {
	if [ -z "${NPM_CONFIG_PREFIX}" ]
	then
		echo "The environment variable NPM_CONFIG_PREFIX is not set and needs to point to a folder for storing Node packages. Run this command:

		export NPM_CONFIG_PREFIX=~/.npm-global"

		exit
	fi

    local npm_bin_dir="${NPM_CONFIG_PREFIX}"/bin
    local npm_modules_dir="${NPM_CONFIG_PREFIX}"/lib/node_modules
    local export_path_command="export PATH=\${PATH}:\${NPM_CONFIG_PREFIX}/bin"

    if [[ "${OSTYPE}" == "cygwin" ]] &&
       [[ "${OSTYPE}" == "msys" ]]
    then
        npm_bin_dir="${NPM_CONFIG_PREFIX}"
        npm_modules_dir="${NPM_CONFIG_PREFIX}"/node_modules
        export_path_command="export PATH=\${PATH}:\${NPM_CONFIG_PREFIX}"
    fi

	if [[ "${PATH}" != *"${npm_bin_dir}"* ]]
	then
		echo "The environment variable PATH does not include ${npm_bin_dir}. Add it to your PATH. Run this command:

		${export_path_command}"

		exit
	fi

	if  [ ! -d "${npm_modules_dir}/generator-liferay-fragments" ] ||
		[ ! -d "${npm_modules_dir}/generator-liferay-js" ] ||
		[ ! -d "${npm_modules_dir}/generator-liferay-theme" ] ||
		[ ! -d "${npm_modules_dir}/yo" ]
	then
		echo "A tutorial dependency is missing. Run this command:

		npm install -g generator-liferay-fragments generator-liferay-js generator-liferay-theme yo"

		exit
	fi

	echo "Your environment is ready for the tutorial."
}

main