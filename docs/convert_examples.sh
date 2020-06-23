#!/bin/bash

for zip_dir_name in `find . -name "liferay-*.zip" -type d`
do
        cp -fr tools/_template/* ${zip_dir_name}

        #pushd ${dir_name}

        #./gradlew classes formatSource

        #popd
done

for dir in `find . -type d -name "*.zip"`
do
    rm $dir/build.gradle
done

for file_name in `find commerce/2.x/en/developer-guide/tutorials -name gradle.properties -type f`
do
        input=$file_name
        echo "$input"
        filepath=${input%%gradle.properties}
        while IFS= read -r line
        do
                echo ${line//portal-7.3-ga3/commerce-2.0.7-7.2} >> $filepath/gradle.properties.temp
        done < "$input"
        mv $filepath/gradle.properties.temp $filepath/gradle.properties
done
