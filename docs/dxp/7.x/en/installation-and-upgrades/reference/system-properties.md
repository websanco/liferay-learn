# System Properties

DXP runs in the application server's JVM. System properties can be passed directly to the JVM as arguments to `java` in the format `-D[name1]=[value1]` or can be loaded using DXP System Properties files. System properties are different from [Portal Properties](./portal-properties.md) in that they're available to the application server and all of its applications.

Your application server's prescribed script is the safest place to add/modify system properties. It can be used to centralize the system properties. Passing all of the properties in as JVM arguments eliminates timing issues by setting all of properties at application server startup. The application server, DXP, and all other web applications can use the properties immediately.

DXP uses the `portal-impl.jar/system.properties` file, however, as a convenience to set required properties. The properties are described at [`system.properties`](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/system.properties.html). Also any system properties, including the `system.properties` can be extended or overridden using a `system-ext.properties` file. System properties files are loaded by the [Portal application class loader](https://help.liferay.com/hc/en-us/articles/360035828131-Liferay-Portal-Classloader-Hierarchy) after application server startup. Here are some functionalities that DXP configures using system properties:

* File encoding
* Logging
* Default XML parser configuration
* JAXB context factory
* Enabling JRuby native extensions

DXP uses System Properties files in these ways:

* DXP _extends_ properties using new properties in `system.properties` (and in `system-ext.properties`), unless the system property `system.properties.set` is `false`
* DXP _overrides_ properties using new values in `system.properties` (and in `system-ext.properties`), unless the system property `system.properties.set.override` is `false`.

```warning::
   Setting or reseting system properties after application server startup has risks:

   * If permissions are enabled on the application server, they may forbid changing system values.
   * System properties are intended to be immutable. Another process on the application server can conceivably cache an initial property value before DXP resets the value. In such a case, the system attempts to operate with different values for the same property.
```

Here both ways of specifying system properties are demonstrated:

* [Setting System Properties Directly](#setting-system-properties-directly)
* [Using a `system-ext.properties` File](#using-a-system-ext-properties-file)

## Setting System Properties Directly

An application server's prescribed script for setting system properties is the recommended place to add and modify properties:

1. Disable using `system.properties` and `system-ext.properties` files by setting the following system properties as JVM arguments in the application server script:

    ```
    -Dsystem.properties.set=false -Dsystem.properties.set.override=false
    ```

1. Add each new property as JVM argument using the following format:

    ```
    -D[name]=[value]
    ```

1. Modify any existing properties.

1. Restart the application server.

    Stop the server:

    ```bash
    [Liferay Home]/[App Server]/bin/shutdown.sh
    ```

    Start the server:

    ```bash
    [Liferay Home]/[App Server]/bin/startup.sh
    ```

The application server and DXP are using the new system properties.

## Using a `system-ext.properties` File

If you want to use DXP's `system.properties` file and you want to extend/override system properties, use a `system-ext.properties` file. Here are the steps:

1. Stop the application server.

    ```bash
    [Liferay Home]/[App Server]/bin/shutdown.sh
    ```

1. Create a file called `system-ext.properties`.

    ```bash
    touch [Liferay Home]/[App Server]/[Path to Portal Application]/WEB-INF/classes/system-ext.properties
    ```

1. In `system-ext.properties`, add any new properties or new values for existing properties.

    ```bash
    echo "name=value" >> [Liferay Home]/[App Server]/[Path to Portal Application]/WEB-INF/classes/system-ext.properties
    ```

1. Start the application server.

    ```bash
    [Liferay Home]/[App Server]/bin/startup.sh
    ```

Now you understand the purpose of system properties, you are familiar with DXP's default system properties, and you know how to extend and override system properties.

## Additional Information

* [7.2 System Properties](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/system.properties.html)
* [Portal Properties](./portal-properties.md)