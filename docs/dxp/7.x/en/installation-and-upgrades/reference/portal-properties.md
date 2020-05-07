# Portal Properties

All the configurations that DXP requires to run out-of-the-box are specified using *Portal Properties*. The properties are a set of name/value pairs that DXP reads from properties files and Env variables on server startup. The default properties are specified in the `portal-impl.jar/portal.properties` file. The latest release's Portal Properties (including their descriptions, corresponding Env variable names, and examples) are published [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) on <https://docs.liferay.com/dxp/portal/>.

While some properties can be changed through the user interface (UI), other properties can only be changed using properties a properties file. Some examples of configurations that _must_ be done through a properties file include but are not limited to: connecting to a database, declaring the location of the [`[Liferay Home]`](./liferay-home.md) folder, changing how users authenticate (by screen name instead of by email address), and increasing the size limit for file uploads.

The typical properties file to create is called `portal-ext.properties`. It belongs in your `[Liferay Home](./liferay-home.md)` folder or `[USER_HOME]` folder. You must restart DXP to apply a new or modified properties file.

```warning::
   Never directly modify the ``portal-impl.jar/portal.properties`` file; rather, use another properties file (an extension file) to override properties you want to change. The typical extension file is called ``portal-ext.properties``.
```

A Portal Properties extension file is the most common and recommended way to configure Liferay DXP. It provides the following benefits:

* The file can be easily distributed to other Liferay DXP environments and server nodes.
* The ability to store configurations in a version control system to simplify configuration management.
* Setting properties in the file before initial startup is the quickest way to configure DXP.

**Contents:**

* [Using Portal Properties](#using-portal-properties)
* [Portal Property Priority](#portal-property-priority)
* [Using System Settings and Configuration Files](#using-system-settings-and-configuration-files)

## Using Portal Properties

`[Liferay Home]/portal-ext.properties` is the most common extension file to use. If there is no `portal-ext.properties` file and you apply changes using the [Setup Wizard](../installing-liferay/running-liferay-dxp-for-the-first-time.md), DXP sets those properties in a file called `portal-setup-wizard.properties`.

Here are a few examples of configurations that can be set in an extension file.

### Setting a Database Connection

Database connection properties are most commonly set in a `portal-ext.properties` file. If you want to change the database connection, for example, create a `portal-ext.properties` file and set the [database connection properties](./database-templates.md) to the values you want:

```properties
jdbc.default.driverClassName=org.mariadb.jdbc.Driver
jdbc.default.url=jdbc:mariadb://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
jdbc.default.username=joe.bloggs
jdbc.default.password=123456
```

For more database configuration details, see [Database Configurations](./database-configurations.md) and [Database Templates](./database-templates.md).

### Setting the Liferay Home Location

Some application servers (e.g., WebLogic) require [customizing the Liferay Home location](../installing-liferay-on-an-application-server/installing-dxp-on-weblogic#declare-the-liferay-home-folder) before deploying the DXP WAR file. The [`liferay.home`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Liferay%20Home) property sets the location.

```properties
liferay.home=/home/jbloggs/liferay
```

### Changing How Users Authenticate

To change how users authenticate to your Liferay DXP server, add one of the following `company.security.auth.type` property values in your `portal-ext.properties` file.

```properties
company.security.auth.type=emailAddress
```

```properties
company.security.auth.type=screenName
```

```properties
company.security.auth.type=userId
```

## Portal Property Priority

Some use cases involve multiple properties sources.

* Using the Setup Wizard (which creates a `portal-setup-wizard.properties` file) and using a `portal-ext.properties` file to add/override properties.
* Creating an environment-specific configuration by using an extension file in addition to a `portal-ext.properties` file.

If you use multiple extension files that specify the same property, it's important to understand which property setting is prioritized. The following topics summarize, explain, and demonstrate how portal property settings are prioritized.

1. [Key Points](#key-points)
1. [Understanding Configuration Processing](#understanding-configuration-processing)
1. [Portal Property Priority Examples](#portal-property-priority-examples)

### Key Points

These key points summarize how portal properties are defined and prioritized:

1. Portal properties sources include:

    * The `portal-impl.jar/portal.properties` file
    * Extension properties files
    * Liferay Docker Env variables

1. The first value read for a *shared property* (a property defined multiple times) takes priority.

1. Properties sources are read in a deterministic order. See [Properties Source Order](#properties-source-order) for details.

[Understanding Configuration Processing](#understanding-configuration-processing) (next) describes the the initial property source list, explains how property sources are added, and demonstrates how to determine property source order and property priority.

### Configuration Processing

The configuration process has two phases.

1. [Create the List of Property Sources](#phase-1-create-the-list-of-property-sources)
1. [Process the Properties](#phase-2-process-the-properties)

These phases are executed in the context of DXP's `portal-impl.jar/portal.properties` file, which  specifies the default property settings and the initial list of extension files to include.

#### Phase 1: Create the List of Property Sources

First DXP creates a list of all the Portal Properties sources. The `portal-impl.jar/portal.properties` file is the first source added. The extension files that `portal-impl.jar/portal.properties` specifies via [`include-and-override`](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/portal.properties.html#Properties%20Override) properties are added next. Here are those `include-and-override` definitions:

```properties
include-and-override=portal-bundle.properties
include-and-override=${liferay.home}/portal-bundle.properties
include-and-override=portal-ext.properties
include-and-override=${liferay.home}/portal-ext.properties
include-and-override=portal-setup-wizard.properties
include-and-override=${liferay.home}/portal-setup-wizard.properties
include-and-override=${external-properties}
include-and-override=${liferay.home}/${external-properties}
```

DXP checks each of the above extension files for additional `include-and-override` definitions. The process adds any extension files found as portal properties sources.

In light of this recursive process, it's comforting to know that the only `include-and-override` definitions you have to worry about are the ones that are in `portal-impl.jar/portal.properties` and any that you (or your teammates) add to the extension files it includes.

![The list of included extension files your DXP server is using is available in the Server Administration page of the Control Panel's Configuration section](./portal-properties/images/01.png)

DXP scans [Liferay Home](./liferay-home.md) for the `include-and-override` files whose path starts with `${liferay.home}/` and scans your home folder (for example, `/home/$USER`) for files specified by file name or a relative path. `${external-properties}` represents any properties file assigned to DXP's Java property `external-properties` (for example, `-Dexternal-properties=some.properties`).

If you're using a Liferay Docker container, any Liferay Env variables you specify are aggregated into a Portal Properties source that's added to the list.<!--TODO Link to docker docs when they're published. jhinkey -->

Here's the potential list of Portal Properties sources:

```properties
portal-impl.jar/portal.properties
include-and-override=portal-bundle.properties
include-and-override=${liferay.home}/portal-bundle.properties
include-and-override=portal-ext.properties
include-and-override=${liferay.home}/portal-ext.properties
include-and-override=portal-setup-wizard.properties
include-and-override=${liferay.home}/portal-setup-wizard.properties
include-and-override=${external-properties}
include-and-override=${liferay.home}/${external-properties}
[Added `include-and-override` files]
[Liferay Docker Env variables]
```

**Important:** DXP **reverses** the initial properties source order (shown above) and in essence processes the sources listed above from bottom to top.

#### Properties Source Order

DXP reverses the initial properties source order and reads the sources in this order:

1. [Liferay Docker Env variables]
1. [Added `include-and-override` files]
1. `[Liferay Home]/${external-properties}`
1. `[User Home]/${external-properties}`
1. `[Liferay Home]/portal-setup-wizard.properties`
1. `[User Home]/portal-setup-wizard.properties`
1. `[Liferay Home]/portal-ext.properties`
1. `[User Home]/portal-ext.properties`
1. `[Liferay Home]/portal-bundle.properties`
1. `[User Home]/portal-bundle.properties`
1. `portal-impl.jar/portal.properties`

The properties source order dictates the priority of shared property settings.

#### Phase 2: Processing the Properties

DXP reads the Portal Properties sources based on the [Properties Source Order](#properties-source-order) and assigns properties to the DXP configuration.

```important::
   Remember, a Portal Property is set only once---the first time it is read.
```

The [Properties Source Order](#properties-source-order) indicates that properties in the `portal-impl.jar/portal.properties` file have the lowest priority and they are overridden by properties of the same name defined in any of the other properties sources.

![All of your DXP server's Portal Properties are available to view in the Server Administration page in the Control Panel's Configuration section.](./portal-properties/images/02.png)

The following examples demonstrate how properties sources and specific properties configure DXP.

### Example 1: Using `portal-ext.properties` to Override a Property

If you've configured a mail session on your application server and it's named differently than the default in `portal-impl.jar/portal.properties` (it sets `mail.session.jndi.name=mail/MailSession`), specify your mail session name in a `portal-ext.properties` file.

New value in `portal-ext.properties`:

```properties
mail.session.jndi.name=mail/SomeMailSession
```

Resulting properties source order:

1. `[Liferay Home]/portal-ext.properties`
2. `portal-impl.jar/portal.properties`

Resulting configuration:

```properties
mail.session.jndi.name=mail/SomeMailSession
```

### Example 2: Adding a Properties File

You can add a properties file for a specific environment, such as a development environment. This allows you to preserve your `portal-ext.properties` while adding or overriding properties for an environment-specific configuration. Here are the steps:

1. Create an arbitrary extension file (e.g., `portal-development.properties`) for your environment and add environment-specific properties to it:

    ```properties
    mail.session.jndi.name=mail/DevMailSession
    ```

1. Include the new extension file as a properties source by adding this `include-and-override` property to the top of your `portal-ext.properties` file:

    ```properties
    include-and-override=portal-development.properties
    ```

Resulting properties source order:

1. `[Liferay Home]/portal-development.properties`
1. `[Liferay Home]/portal-ext.properties`
1. `portal-impl.jar/portal.properties`

Resulting configuration:

```properties
mail.session.jndi.name=mail/DevMailSession
```

```tip::
   Using as few properties files as necessary simplifies managing DXP configuration.
```

## Using System Settings and Configuration Files

Some Portal Properties can be set using [System Settings](../../system-administration/system-settings/system-settings.md) and [Configuration Files](../../system-administration/system-settings/understanding-configuration-files.md). The SAML authentication properties, for example, are Portal Properties available in System Settings.

System Settings is available in the *Control Panel* at *Configuration* &rarr; *System Settings*. The System Settings can be exported as Configuration Files (`.config` files), for example, to save in source control and use in distributed DXP installations. Portal Properties set via System Settings and Configuration Files are stored in the DXP database. Some of the properties are applied immediately while others require restarting the server.

```important::
   Properties stored in the DXP database are prioritized over properties set in Portal Properties files.
```

## Additional Information

* [Portal Properties](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/portal.properties.html)

* [System Settings](../../system-administration/system-settings/system-settings.md)

* [Understanding Configuration Files](../../system-administration/system-settings/understanding-configuration-files.md)