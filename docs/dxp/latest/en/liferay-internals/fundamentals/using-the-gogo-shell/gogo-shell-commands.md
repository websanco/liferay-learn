# Gogo Shell Commands

The Gogo shell executes [Felix Gogo basic commands](https://felix.apache.org/documentation/subprojects/apache-felix-gogo.html#basic-commands) and Liferay commands. The Gogo shell is accessible in the [Control Panel](../using-the-gogo-shell.md) (recommended) and from the [command line](./command-line-gogo-shell.md).

Here are some commonly used commands:

| Command | Description |
| :------ | :---------- |
| `b [BUNDLE_ID]` | Lists information about a specific bundle including the bundle's symbolic name, bundle ID, data root, registered (provided) and used services, imported and exported packages, and more |
| `diag [BUNDLE_ID]` | Lists information about why the specified bundle is not working (e.g., unresolved dependencies, etc.) |
| `dm na` | Lists OSGi components that are "not available" (unresolved). Please see [Leveraging the Shell](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager/tutorials/leveraging-the-shell.html) for more Dependency Manager information. |
| `ds:unsatisfied` | Lists all unsatisfied Declarative Services (DS) components |
| `ds:unsatisfied [BUNDLE_ID]` | Lists the bundle's unsatisfied DS components |
| `headers [BUNDLE_ID]` | Lists metadata about the bundle from the bundle's `MANIFEST.MF` file |
| `equinox:refresh [BUNDLE_ID]` | Detects available optional dependencies and reactivates the bundle. |
| `help` | Lists all the available Gogo shell commands. Notice that each command has two parts to its name, separated by a colon. For example, the full name of the `help` command is `felix:help`. The first part is the command scope while the second part is the command function. The scope allows commands with the same name to be disambiguated. E.g., scope allows the `felix:refresh` command to be distinguished from the `equinox:refresh` command. |
| `help [COMMAND_NAME]` | Lists information about a specific command including a description of the command, the scope of the command, and information about any flags or parameters that can be supplied when invoking the command. |
| `inspect capability service [BUNDLE_ID]` | Lists services exposed by a bundle |
| `install [PATH_TO_JAR_FILE]` | Installs the specified bundle into Liferay's module framework |
| `lb` | Lists all of the bundles installed in Liferay's module framework. Use the `-s` flag to list the bundles using the bundles' symbolic names. |
| `packages [PACKAGE_NAME]` | Lists all of the named package's dependencies |
| `scr:list` | Lists all of the components registered in the module framework (*scr* stands for service component runtime) |
| `scr:info [COMPONENT_NAME]` | Lists information about a specific component including the component's description, services, properties, configuration, references, and more. |
| `services` | Lists all of the services that have been registered in the module framework |
| `start [BUNDLE_ID]` | Starts the specified bundle |
| `stop [BUNDLE_ID]` | Stops the specified bundle |
| `system:check` | Scans the system for anomalies, executing the `ds:unsatisfied` and `dm na` commands. |
| `uninstall [BUNDLE_ID]` | uninstalls the specified bundle from the module framework. This does not remove the specified bundle from Liferay's module framework; it's hidden from Gogo's `lb` command, but is still present. Adding a new version of the uninstalled bundle, therefore, will not reinstall it; it will update the currently hidden uninstalled version. To remove a bundle from Liferay's module framework permanently, manually delete it from the `[Liferay Home]/osgi` folder. For more information on the `uninstall` command, see OSGi's [uninstall](https://osgi.org/javadoc/r6/core/org/osgi/framework/Bundle.html#uninstall\(\)) documentation. |

## Additional Information

* [Using the Gogo Shell](../using-the-gogo-shell.md)
* [Command Line Gogo Shell](./command-line-gogo-shell.md)
* [Apache Felix Gogo official documentation](http://felix.apache.org/documentation/subprojects/apache-felix-gogo.html).