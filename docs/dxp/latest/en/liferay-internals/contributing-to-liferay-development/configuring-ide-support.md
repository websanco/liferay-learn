# Configuring IDE Support

The Liferay Source is comprised of many modules. Attempting to import every module into an IDE results in the IDE becoming unresponsive. Using the following workaround it becomes possible to import portions of the source into an IDE and work with them like any other gradle enabled project. For example, if a bug exists in *portal-workflow-web*, it becomes possible to import this project along with all of its dependent projects into an IDE and work with them.

## Custom Marker File

The process involves a manual work around but there are plans to automate this as much as possible. The workaround adds a custom marker file to each project that get’s imported into the IDE.

Modify: `liferay-portal/modules/build.gradle` and insert the following after: apply plugin: *`com.liferay.defaults.plugin`* (around line 104):

```
if (tasks.findByName("jar")) {
           task createCustomMarkerfile{
               File customMarkerFile = new File(project.projectDir, "/.lfrbuild-custom")
               customMarkerFile.createNewFile()
           }
           jar.finalizedBy createCustomMarkerfile
       }
```

* Create custom marker files for core modules:

```
ant compile install-portal-snapshots
```

* Build a module such as portal-workflow-web or blogs-web, for example:

```
cd /Users/jamie/Repos/liferay-portal/modules/apps/blogs/blogs-web
blade gw deploy
```

* The difference with the workaround is now the custom task (added to the build.gradle file above) is seen in the output:

```
> Task :apps:blogs:blogs-web:createCustomMarkerfile
```

## Liferay Developer Studio

From Liferay Developer Studio, do the following:

```{tip}
(OPTIONAL) Import liferay-portal dir as a Gradle Project into Eclipse.
```

* Import > Import > Gradle > Existing Gradle Project
* Project root directory: liferay-portal root (For example: /Users/jamie/Repos/liferay-portal)
* Import Options: Click Configure Workspace Settings…
    * Gradle Distribution: Specfic Gradle Version: 4.10.2
    * JVM argument: -Dbuild.profile=custom

Import modules: <!--should be a h3 here?-->

* Import > Import > Gradle > Existing Gradle Project
* Project root directory: liferay-portal/modules

## IntelliJ

For IntelliJ do the following:

```{tip}
OPTIONAL: Import liferay-portal dir as a Gradle Project into IntelliJ.
```

* Import Project > Gradle
* Root directory: *liferay-portal* root (For example: /Users/jamie/Repos/liferay-portal)
* Use auto-import: *true*
* Create separate modules per sources set: *false*
* Use default Gradle wrapper: *true*
* Gradle VM options: *-Dbuild.profile=custom*
* Replace existing .idea file: *yes*

Import modules: <!--should be a h3 here?-->

* File > Project Structure > Modules
* Import Module: *liferay-portal/modules*
* Import type: *Gradle*
* Use auto-import: *true*
* Create separate modules per sources set: *false*