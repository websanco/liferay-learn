# Installing on JBoss EAP

Installing on JBoss EAP requires installing the DXP WAR, installing dependencies, configuring JBoss, and deploying DXP on JBoss. You must also configure your database and mail server connections.

## Prerequisites

Liferay DXP requires Java JDK 8 or 11. See [the compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151) for further information.

Download these files from the [Help Center](https://customer.liferay.com/downloads) (subscription) or from [Liferay Community Downloads](https://www.liferay.com/downloads-community):

* DXP WAR file
* OSGi Dependencies ZIP file
* Dependencies ZIP file (DXP 7.3 and earlier)

The installation steps use these terms:

[`[Liferay Home]`](../../reference/liferay-home.md): The folder containing the JBoss server folder (referred to as `$JBOSS_HOME`). After installing and deploying DXP, it generates `data`, `deploy`, and `logs` folders.

`$JBOSS_HOME`: The JBoss server folder. It is usually named `jboss-eap-[version]`.

## Installing the DXP WAR

1. If you're starting with a clean JBoss installation and a `$JBOSS_HOME/standalone/deployments/ROOT.war` folder exists, delete all of its subfolders and files.
1. Unzip the DXP WAR file into the `$JBOSS_HOME/standalone/deployments/ROOT.war` folder (create this folder if it doesn't exist).

```{important}
The default Liferay Portal web context can be changed (e.g. `localhost:8080/` to `localhost:8080/myportal`) by changing the WAR filename, but this is not recommended.
```

## Installing Dependencies

1. Unzip the OSGi Dependencies ZIP file into the `[Liferay Home]/osgi` folder (create this folder if it doesn't exist). Liferay's OSGi runtime depends on these modules.
1. The DXP 7.4+ WAR file includes drivers for MariaDB and PostgreSQL. Earlier WARs don't have them. If the 7.4+ WAR doesn't have the driver for the supported database you're using, download your database vendor's JDBC JAR file and place it in the `$JBOSS_HOME/standalone/deployments/ROOT.war/WEB-INF/shielded-container-lib` folder.

    Please see the [compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151) for a list of supported databases.

```{note}
DXP includes a Hypersonic database that is useful for testing purposes. **Do not** use HSQL for production instances.
```

### Install Dependencies for Earlier Versions

For DXP 7.3 and earlier, follow these additional steps:

1. Unzip the Dependencies ZIP file to a folder called `$JBOSS_HOME/modules/com/liferay/portal/main` (create this folder if it doesn't exist).
1. Create a file called `module.xml` in the `$JBOSS_HOME/modules/com/liferay/portal/main` folder. In the file, declare the portal module and all of its required resources and dependencies:

    ```xml
    <?xml version="1.0"?>

    <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
        <resources>
            <resource-root path="[place your database vendor's JAR file name here]" />
            <resource-root path="[place a Liferay dependencies ZIP JAR file name here]" />
            <!-- Add a resource-root element for each Liferay dependencies ZIP JAR -->
        </resources>
        <dependencies>
            <module name="javax.api" />
            <module name="javax.mail.api" />
            <module name="javax.servlet.api" />
            <module name="javax.servlet.jsp.api" />
            <module name="javax.transaction.api" />
        </dependencies>
    </module>
    ```

    Replace `[place your database vendor's JAR file name here]` with the driver JAR for your database.

    For each JAR in the Liferay dependencies ZIP, add a `resource-root` element with its `path` attribute set to the JAR name. For example, add a `resource-root` element like this for the `com.liferay.petra.concurrent.jar` file:

    ```xml
    <resource-root path="com.liferay.petra.concurrent.jar" />
    ```

## Running DXP on JBoss EAP in Standalone Mode vs. Domain Mode

JBoss EAP can be launched in either *standalone* mode or *domain* mode. Domain mode allows multiple application server instances to be managed from a single control point. A collection of such application servers is known as a *domain*. For more information on standalone mode vs. domain mode, please refer to the section on this topic in the [JBoss EAP Product Documentation](https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.1/html/introduction_to_jboss_eap/overview_of_jboss_eap#operating_modes).

DXP supports JBoss EAP when it runs in standalone mode but not when it runs in domain mode. DXP's auto-deploy does not work with a managed deployment, since JBoss manages the content of a managed deployment by copying files (exploded or non-exploded). This prevents JSP hooks and Ext plugins from working as intended. For example, JSP hooks don't work on JBoss EAP running in managed domain mode, since DXP's JSP override mechanism relies on the application server. Since JSP hooks and Ext plugins are deprecated, however, you may not be using them.

If you use domain mode deployment, use the command line interface.

```{note}
This does not prevent DXP from running in a clustered environment on multiple JBoss servers. You can set up a cluster of DXP instances running on JBoss EAP servers running in standalone mode. Please refer to the [clustering articles](../../setting-up-liferay/clustering-for-high-availability.md) for more information.
```

## Configuring JBoss

Here are the JBoss configuration steps:

* Set environment variables
* Specify properties and descriptors
* Remove unnecessary configurations

Make the following modifications to `$JBOSS_HOME/standalone/configuration/standalone.xml`:

1. Configure the servlet container to use Java 8 VM compatibility with JSPs. Locate the default servlet container `<servlet-container name="default">` in the `<subsystem xmlns="urn:jboss:domain:undertow:12.0" ...` element. In the servlet container's `<jsp-config>` element, set `development`, `source-vm`, and `target-vm` attributes like this:

    ```xml
    <jsp-config development="true" source-vm="1.8" target-vm="1.8" />
    ```

1. Locate the closing `</extensions>` tag. Directly beneath that closing tag, insert the following system properties, if they don't already exist:

    ```xml
    <system-properties>
        <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
        <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
    </system-properties>
    ```

1. Filter out `WFLYSRV0059` and `WFLYEE0007` messages from the log. In the `<subsystem xmlns="urn:jboss:domain:logging:8.0">` element's `<console-handler>` tag, add the following `<filter-spec>` tag directly below the `<level name="INFO"/>` tag.

    ```xml
    <filter-spec value="not(any(match(&quot;WFLYSRV0059&quot;),match(&quot;WFLYEE0007&quot;)))" />
    ```

1. Add a deployment scanner timeout by adding a `deployment-timeout="600"` setting to the `<deployment-scanner>` tag in the `<subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">` element. For example,

    ```xml
    <deployment-scanner deployment-timeout="600" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000" runtime-failure-causes-rollback="${jboss.deployment.scanner.rollback.on.failure:false}"/>
    ```

1. Add Liferay's JAAS security domain to the `<subsystem xmlns="urn:jboss:domain:security:2.0">`'s `<security-domains>` element. Here is the domain code to add:

    ```xml
    <security-domain name="PortalRealm">
        <authentication>
            <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
        </authentication>
    </security-domain>
    ```

1. Comment out the welcome content elements from the `<subsystem xmlns="urn:jboss:domain:undertow:12.0" ...>` element. For example,

    ```xml
    <!--<location name="/" handler="welcome-content"/>-->
    ```

    and

    ```xml
    <handlers>
        <!--<file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>-->
    </handlers>
    ```

**Checkpoint:**

Before continuing, verify the following properties have been set in the `standalone.xml` file:

1. The new `<system-property>` is added.
1. The new `<filter-spec>` is added.
1. The `<deployment-timeout>` is set to `600`.
1. The new `<security-domain>` is created.
1. Welcome content is disabled.

Next, configure the JVM and startup scripts:

In the `$JBOSS_HOME/bin/` folder, modify the standalone domain's configuration script file `standalone.conf`:

* Set the file encoding to `UTF-8`
* Set the user time zone to `GMT`
* Set the preferred protocol stack
* Increase the default amount of memory available.

```{important}
DXP requires the application server JVM to use the `GMT` time zone and `UTF-8` file encoding.
```

Make the following edits to your `standalone.conf` script.

1. Below the `if [ "x$JAVA_OPTS" = "x" ];` statement, remove the JVM sizing options from the `JAVA_OPTS` assignment. For example, replace this

    ```bash
    JAVA_OPTS="-Xms1303m -Xmx1303m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2560m -Djava.net.preferIPv4Stack=true"
    ```

    with this:

    ```bash
    JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
    ```

1. Add this Java options setting at the end of the file:

    ```bash
    JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
    ```

The Java options and memory arguments are explained below.

**JVM Options Explained**

| Option | Explanation |
| :----- | :---------- |
| `-Dfile.encoding=UTF-8` | DXP requires UTF-8 file encoding. |
| `-Djava.locale.providers=JRE,COMPAT,CLDR` | This is required for displaying four-digit dates on JDK 11. |
| `-Djava.net.preferIPv4Stack=true` | Prefers an IPv4 stack over IPv6. |
| `-Dlog4j2.formatMsgNoLookups=true` | Resolves a remote code execution (RCE) vulnerability. See [LPS-143663](https://issues.liferay.com/browse/LPS-143663) for details. |
| `-Duser.timezone=GMT` | DXP requires the application server JVM to use the GMT time zone. |

**Memory Arguments Explained**

| Memory Arguments | Explanation |
| :--------------- | :---------- |
| `-Xms` | Initial space for the heap. |
| `-Xmx` | Maximum space for the heap. |
| `-XX:NewSize`| Initial new space. Setting the new size to half of the total heap typically provides better performance than using a smaller new size. |
| `-XX:MaxNewSize` | Maximum new space. |
| `-XX:MetaspaceSize` | Initial space for static content. |
| `-XX:MaxMetaspaceSize` | Maximum space for static content. |
| `-XX:SurvivorRatio` | Ratio of the new space to the survivor space. The survivor space holds young generation objects before being promoted to old generation space. |

```{note}
After installing DXP, these configurations (including these JVM options) can be further tuned for improved performance. Please see [Tuning Liferay](../../setting-up-liferay/tuning-liferay.md) and [Tuning Your JVM](../../setting-up-liferay/tuning-your-jvm.md) for more information.
```

### Using the IBM JDK

If you're using the IBM JDK with the JBoss server, complete these additional steps:

1. For DXP 7.3 and earlier, navigate to the `$JBOSS_HOME/modules/com/liferay/portal/main/module.xml` file and insert this dependency within the `<dependencies>` element:

    `<module name="ibm.jdk" />`

1. Navigate to the `$JBOSS_HOME/modules/system/layers/base/sun/jdk/main/module.xml` file and insert these paths inside the `<paths>...</paths>` element:

    ```xml
    <path name="com/sun/crypto" />
    <path name="com/sun/crypto/provider" />
    <path name="com/sun/image/codec/jpeg" />
    <path name="com/sun/org/apache/xml/internal/resolver" />
    <path name="com/sun/org/apache/xml/internal/resolver/tools" />
    ```

The added paths resolve issues with portal deployment exceptions and image uploading problems.

## Data Source Configuration in Liferay

DXP contains a built-in Hypersonic database which is great for demonstration purposes but **should not be used in production**. For production, use a full-featured, supported RDBMS. See [Configure a Database](../configuring-a-database.md) to set up your database.

Liferay DXP can connect with your database using DXP's built-in data source (recommended) or using a data source you create on your app server.

You can configure DXP's built-in data source with your database the first time you run DXP by using the [Setup Wizard](../running-liferay-for-the-first-time.md). Or you can configure the data source in a [`portal-ext.properties` file](../../reference/portal-properties.md) based on the [Database Template](../../reference/database-templates.md) for your database.

## Data Source Configuration in JBoss EAP

If you're using JBoss to manage the data source, follow these steps:

1. Get the JDBC JAR from your DXP WAR (7.4+) or from the database vendor, and copy it to the `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

1. For DXP 7.4, create a file called `module.xml` in the `$JBOSS_HOME/modules/com/liferay/portal/main` folder. In the file, declare the portal module and the JDBC JAR. This step is not necessary for DXP 7.3 and earlier because it was already created in the [Install Dependencies](#installing-dependencies) section.

    ```xml
    <?xml version="1.0"?>

    <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
        <resources>
            <resource-root path="[place your database vendor's JAR file name here]" />
        </resources>
        <dependencies>
            <module name="javax.api" />
            <module name="javax.mail.api" />
            <module name="javax.servlet.api" />
            <module name="javax.servlet.jsp.api" />
            <module name="javax.transaction.api" />
        </dependencies>
    </module>
    ```

1. Add the data source inside the `$JBOSS_HOME/standalone/configuration/standalone.xml` file's the `<datasources>` element.

    ```xml
    <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
        <connection-url>[place the URL to your database here]</connection-url>
        <driver>[place the driver name here]</driver>
        <security>
            <user-name>[place your user name here]</user-name>
            <password>[place your password here]</password>
        </security>
    </datasource>
    ```

    Make sure to replace the database URL, user name, and password with the appropriate values.

    ```{note}
    If the data source `jndi-name` must be changed, edit the `datasource` element in the `<default-bindings>` tag.
    ```

1. Add the driver to the `standalone.xml` file's `<drivers>` element also found within the `<datasources>` element.

    ```xml
    <drivers>
        <driver name="[name of driver must match name above]" module="com.liferay.portal">
            <driver-class>[place your JDBC driver class here]</driver-class>
        </driver>
    </drivers>
    ```

    A final data source subsystem that uses MySQL should look like this:

    ```xml
    <subsystem xmlns="urn:jboss:domain:datasources:5.0">
        <datasources>
            <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
                <connection-url>jdbc:mysql://localhost/lportal</connection-url>
                <driver>mysql</driver>
                <security>
                    <user-name>root</user-name>
                    <password>root</password>
                </security>
            </datasource>
            <drivers>
                <driver name="mysql" module="com.liferay.portal">
                    <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
                </driver>
            </drivers>
        </datasources>
    </subsystem>
    ```

1. In a [`portal-ext.properties`](../../reference/portal-properties.md) file in the Liferay Home, specify your data source. For example,

    ```properties
    jdbc.default.jndi.name=java:jboss/datasources/ExampleDS
    ```

The data source is now configured and ready to connect to the database.

## Connect to a Mail Server

As with database configuration, the easiest mail session to configure is DXP's. If you want to use DXP's built-in mail session, skip this section and [connect to a mail server](../../setting-up-liferay/configuring-mail.md) in the Control Panel.

If you want to configure the mail session in JBoss, follow these steps:

1. Specify the mail subsystem in the
    `$JBOSS_HOME/standalone/configuration/standalone.xml` file like this:

    ```xml
    <subsystem xmlns="urn:jboss:domain:mail:3.0">
        <mail-session jndi-name="java:jboss/mail/MailSession" >
            <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp">
                <login username="[place user name here]" password="[place password here]"/>
            </smtp-server>
       </mail-session>
    </subsystem>
    ...
    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    ...
    <outbound-socket-binding name="mail-smtp">
            <remote-destination host="[place SMTP mail host here]" port="[place mail port here]"/>
        </outbound-socket-binding>
    </socket-binding-group>
    ```

1. In the [`portal-ext.properties`](../../reference/portal-properties.md) file in Liferay Home, reference the mail session. For example,

    ```properties
    mail.session.jndi.name=java:jboss/mail/MailSession
    ```

## Deploying DXP

1. To trigger deployment `ROOT.war`, create an empty file named `ROOT.war.dodeploy` in the `$JBOSS_HOME/standalone/deployments/` folder.
1. Start the JBoss application server by navigating to `$JBOSS_HOME/bin` and running `standalone.sh`. JBoss detects the `ROOT.war.dodeploy` file and deploys the web application matching the file prefix (i.e., `ROOT.war`).

After deploying DXP, you may see excessive warnings and log messages such as the ones below, involving `PhaseOptimizer`. These are benign and can be ignored. You can turn off these messages by adjusting the app server's logging level or log filters.

```
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
WARNING: Skipping pass gatherExternProperties
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
WARNING: Skipping pass checkControlFlow
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
```

If you have a Liferay DXP Enterprise subscription, DXP requests your activation key. See [Activating Liferay DXP](../../setting-up-liferay/activating-liferay-dxp.md) for more information.

Congratulations! You're running DXP on JBoss EAP.

## Next Steps

You can [sign in as your administrator user](../../../getting-started/introduction-to-the-admin-account.md) and start [building a solution on DXP](../../../building_solutions_on_dxp.html. Or you can explore [additional Liferay DXP setup](../../setting-up-liferay.md) topics:

* [Installing the Marketplace Plugin](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
* [Accessing Plugins During a Trial Period](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
* [Installing a Search Engine](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md)
* [Securing Liferay DXP](../../securing-liferay.md)
* [Clustering for High Availability](../../setting-up-liferay/clustering-for-high-availability.md)