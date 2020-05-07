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
* [Understanding Configuration Priority](#understanding-configuration-priority)
* [Configuration via System Settings and Configuration Files](#property-configuration-via-system-settings)

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

## Understanding Configuration Priority

Some use cases involve multiple extension files.

* The Setup Wizard creates a `portal-setup-wizard.properties` file and you want to use a `portal-ext.properties` file to add/override properties.
* You want to add/override properties without modifying your `portal-ext.properties` file. For example, you want to temporarily use developer property via an additional extension file.

If you use multiple extension files that that specify the same property, it's important to understand which property setting is prioritized. An overview of the configuration process and some examples are the best way to learn how properties are prioritized.

The configuration process has two phases.

1. [Create the List of Property Sources](#phase-1-create-the-list-of-property-sources)
2. [Process the Properties](#phase-2-process-the-properties)

These phases are executed in the context of DXP's `portal-impl.jar/portal.properties` file, which specifies the initial list of other properties files to include and specifies the default property values.

### Phase 1: Create the List of Property Sources

First DXP creates a list of all the Portal Properties sources. The `portal-impl.jar/portal.properties` file is the first source added. The properties files that `portal-impl.jar/portal.properties` specifies via [`include-and-override`](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/portal.properties.html#Properties%20Override) properties are added next. Here are those `include-and-override` definitions:

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

DXP checks each of the above properties files for additional `include-and-override` properties. The process adds the properties files as sources if it encounters any.

In light of this recursive process, it's comforting to know that the only `include-and-override` definitions you have to worry about are the ones in `portal-impl.jar/portal.properties` and any that you (or your teammates) add to the extension files.

![The list of included properties files used by your DXP server is available in the Server Administration page of the Control Panel's Configuration section](./portal-properties/images/01.png)

DXP scans [Liferay Home](./liferay-home.md) for the `include-and-override` files whose path starts with `${liferay.home}/` and scans your home folder (for example, `/home/$USER`) for files specified by a relative path or file name. `${external-properties}` represents any properties file assigned to DXP's Java property `external-properties` (for example, `java -Dexternal-properties=some.properties`).

If you're using a Liferay Docker container, any Liferay Env variables you specify are aggregated into a Portal Properties source that's added to the list.<!--TODO Link to docker docs when they're published. jhinkey -->

Here's the potential list of Portal Property sources:

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

**Important:** DXP reverses the order of the sources and in essence processes the sources listed above from **bottom to top**.

#### Properties Source Order

DXP processes the sources in this order:

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

### Phase 2: Processing the Properties

DXP parses the Portal Properties sources based on the [Properties Source Order](#properties-source-order) and assigns properties to the DXP configuration.

**Important:** A Portal Property is set only once---the first time it is read.

The [Properties Source Order](#properties-source-order) indicates that properties in the `portal-impl.jar/portal.properties` file have the lowest priority, and are overridden by properties of the same name defined in any of the other properties sources.

![All of your DXP server's Portal Properties are available to view in the Server Administration page in the Control Panel's Configuration section.](./portal-properties/images/02.png)

The following examples demonstrate how properties sources and specific properties are used to configure DXP.

### Example 1: Using `portal-ext.properties` to Override a Property

If you've configured a mail session on your app server and it's named differently than the default in `portal-impl.jar/portal.properties` (it sets `mail.session.jndi.name=mail/MailSession`), specify your mail session name in a `portal-ext.properties` file.

New value in `portal-ext.properties`:

```properties
mail.session.jndi.name=mail/SomeMailSession
```

Phase 1 - resulting properties source order:

1. `[Liferay Home]/portal-ext.properties`
2. `portal-impl.jar/portal.properties`

Phase 2 - resulting configuration:

```properties
mail.session.jndi.name=mail/SomeMailSession
```

### Example 2: Adding a Properties File

You can add a properties file for a specific environment, such as a development environment. This allows you to keep your `portal-ext.properties` as is, while adding or overriding properties to create an environment-specific configuration. Here are the steps:

1. Create an arbitrary extension file (e.g., `portal-development.properties`) for your environment and add environment-specific properties to it:

    ```properties
    mail.session.jndi.name=mail/DevMailSession
    ```

1. Include the new extension file in your properties file list by adding the following entry to the top of your `portal-ext.properties` file:

    ```properties
    include-and-override=portal-development.properties
    ```

Phase 1 - Resulting properties source order:

1. `[Liferay Home]/portal-development.properties`
1. `[Liferay Home]/portal-ext.properties`
1. `portal-impl.jar/portal.properties`

Phase 2 - Resulting configuration:

```properties
mail.session.jndi.name=mail/DevMailSession
```

```note::
   We recommend using as few properties files as necessary to simplify configuration management for a DXP installation.
```

## Configuration Via System Settings and Configuration Files

Some Portal Properties are available to change as [System Settings](../../system-administration/system-settings/system-settings.md), at *Control Panel* &rarr; *Configuration* &rarr; *System Settings* or in [`.config` files](../../system-administration/system-settings/understanding-configuration-files.md). These properties are stored in the DXP database. The SAML authentication properties, for example, are Portal Properties available in System Settings.

```important::
   Properties stored in the DXP database are prioritized over properties set in Portal Properties files.
```

## Additional Information

* [Portal Properties](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/portal.properties.html)

* [System Settings](../../system-administration/system-settings/system-settings.md)

* [Understanding Configuration Files](../../system-administration/system-settings/understanding-configuration-files.md)