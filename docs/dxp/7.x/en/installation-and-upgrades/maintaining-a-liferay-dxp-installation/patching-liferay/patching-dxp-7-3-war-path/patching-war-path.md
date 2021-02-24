# Patching war path with Patching Tool 3

The war path for the following web servers are zipped into a .war file
* Webpshere
* Jboss Wildfly
* Weblogic

In order to be able to patch your system, you have to unzip the .war file and
set the WAR_PATH to the folder path in the property file of Patching Tool.
After the command finished, zip the folder back to a .war file and copy it into
your bundle.
