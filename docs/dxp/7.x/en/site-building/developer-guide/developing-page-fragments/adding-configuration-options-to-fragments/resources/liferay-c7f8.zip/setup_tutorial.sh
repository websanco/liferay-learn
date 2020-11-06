#!/bin/sh

if [ -z "$NPM_CONFIG_PREFIX" ]
then
    echo "NPM_CONFIG_PREFIX not set. Please set environment variable NPM_CONFIG_PREFIX to a folder for storing Node packages.
For example,

    export NPM_CONFIG_PREFIX=~/.npm-global"
    exit
fi

if [[ "$PATH" != *"$NPM_CONFIG_PREFIX/bin"* ]]
then
    echo "$NPM_CONFIG_PREFIX/bin not in PATH. Please add $NPM_CONFIG_PREFIX/bin to your PATH.
For example,

    export PATH=\$PATH:\$NPM_CONFIG_PREFIX/bin"
    exit
fi

if  [ ! -d "$NPM_CONFIG_PREFIX/lib/node_modules/yo" ] ||
    [ ! -d "$NPM_CONFIG_PREFIX/lib/node_modules/generator-liferay-fragments" ] ||
    [ ! -d "$NPM_CONFIG_PREFIX/lib/node_modules/generator-liferay-js" ] ||
    [ ! -d "$NPM_CONFIG_PREFIX/lib/node_modules/generator-liferay-theme" ]
then
    echo "Missing a dependency. Please install the tutorial dependencies by running the following command:

    npm install -g generator-liferay-fragments generator-liferay-js generator-liferay-theme yo"
fi