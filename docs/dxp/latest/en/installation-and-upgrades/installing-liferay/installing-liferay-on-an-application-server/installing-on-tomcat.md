# Installing on Tomcat

```{important}
[Using a Liferay-Tomcat bundle](../installing-a-liferay-tomcat-bundle.md) or [Docker image](../../../getting-started/starting-with-a-docker-image.md) is the fastest way to get started using Liferay DXP. This article is for users who want to have full control over their Tomcat application server's configuration.

Review the [Installing a Liferay-Tomcat Bundle](../installing-a-liferay-tomcat-bundle.md) and [Configuring a Database](../configuring-a-database.md) articles before continuing.
```

Installing on Tomcat requires installing the DXP WAR, installing dependencies, configuring Tomcat, and deploying DXP. You must also configure your database and mail server connections.

The simplest and easiest way to accomplish this is by [downloading the Liferay Liferay-Tomcat](../installing-a-liferay-tomcat-bundle.md) bundle and copying the dependencies, scripts, and `ROOT.xml` from it to the locations described below. You can otherwise download the dependencies and manually configure Tomcat.

## Prerequisites

No matter how you configure Tomcat, you must also download and install these files from the [Help Center](https://customer.liferay.com/downloads) (subscription) or from [Liferay Community Downloads](https://www.liferay.com/downloads-community):

* DXP WAR file
* OSGi Dependencies ZIP file
* Dependencies ZIP file (DXP 7.3 and earlier)

Java JDK 8 or 11 is required.

```{note}
Please see [the compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151) for information on supported JDKs, databases, and environments.  See [JVM Configuration](../../reference/jvm-configuration.md) for recommended JVM settings.
```

The Tomcat server parent folder is [*Liferay Home*](../../reference/liferay-home.md). `$TOMCAT_HOME` refers to Tomcat server folder. It is usually named `tomcat-[version]` or `apache-tomcat-[version]`.

## Installing the DXP WAR

1. If you're starting with a clean Tomcat installation, delete the contents of the `$CATALINA_BASE/webapps/ROOT` folder. This removes the default Tomcat home page.
1. Extract the DXP `.war` file contents to `$CATALINA_BASE/webapps/ROOT`.

## Installing Dependencies

DXP depends on many JARs included in Liferay-Tomcat bundle. Some of the bundle's JARs are not strictly required but can still be useful. If you're not using a Tomcat bundle, you'll use the *OSGi Dependencies* archive you downloaded and any third-party JAR dependencies as described below.

1. Unzip the OSGi Dependencies ZIP file contents in the `[Liferay Home]/osgi` folder (create this folder if it doesn't exist). Liferay's OSGi runtime depends on these modules.
1. The DXP 7.4+ WAR file includes drivers for MariaDB and PostgreSQL. Earlier WARs don't have them. If the 7.4+ WAR doesn't have the driver for the supported database you're using, download your database vendor's JDBC JAR file and place it in the `$CATALINA_BASE/webapps/ROOT/WEB-INF/shielded-container-lib` folder.

    Please see the [compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151) for a list of supported databases.

```{note}
A Hypersonic database is bundled with DXP and is useful for testing purposes. **Do not** use HSQL for production instances.
```

```{note}
For DXP 7.3 and earlier, unzip the Dependencies ZIP file to the `$TOMCAT_HOME/lib/ext` folder (create this folder if it doesn't exist). Place your database vendor's JDBC JAR file in that folder too.
```

## Configuring Tomcat

Configuring Tomcat to run DXP includes these tasks:

* Setting the JVM options
* Specifying a web application context for DXP
* Setting properties and descriptors

1. Copy the `setenv.bat`, `setenv.sh`,  `startup.bat`, `startup.sh`, `shutdown.bat`, and `shutdown.sh` files from a DXP bundle to the `$CATALINA_BASE/bin` folder. Otherwise, create the  `setenv.bat` and `setenv.sh` scripts.

1. The `setenv.sh` script sets JVM options for Catalina, which is Tomcat's servlet container. Among these options is the location of the Java runtime environment. If this environment is not available on the server globally, set its location in the `setenv.sh` script so Tomcat can run. Do this by pointing the `JAVA_HOME` environment variable to a DXP-supported JRE:

    ```bash
    export JAVA_HOME=/usr/lib/jvm/java-8-jdk
    export PATH=$JAVA_HOME/bin:$PATH
    ```

1. Then configure Catalina's JVM options to support DXP.

    ```bash
    CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
    ```

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
| `-XX:SurvivorRatio` | Ratio of the new space to the survivor space. The survivor space holds young generation objects before being promoted to old generation space. |

```{note}
After installing DXP, these configurations (including these JVM options) can be further tuned for improved performance. Please see [Tuning Liferay](../../setting-up-liferay/tuning-liferay.md) and [Tuning Your JVM](../../setting-up-liferay/tuning-your-jvm.md) for more information.
```

Continue configuring Tomcat.

1. If you have a Liferay-Tomcat bundle, copy its `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` file to the corresponding location in the application server. Create the file path if it doesn't exist and create the `ROOT.xml` file.

    The `ROOT.xml` file specifies a web application context for DXP. For example,

    ```xml
    <Context crossContext="true">
        <JarScanner className="com.liferay.support.tomcat.util.scan.NOPJarScanner" />

        <!-- JAAS -->

        <!--<Realm
            className="org.apache.catalina.realm.JAASRealm"
            appName="PortalRealm"
            userClassNames="com.liferay.portal.kernel.security.jaas.PortalPrincipal"
            roleClassNames="com.liferay.portal.kernel.security.jaas.PortalRole"
        />-->
    </Context>
    ```

     Setting `crossContext="true"` lets multiple web applications use the same class loader. This configuration includes commented instructions and tags for configuring a JAAS realm.

```{important}
The default Liferay Portal web context can be changed (e.g. `localhost:8080/` to `localhost:8080/myportal`) by changing the XML filename, but this is not recommended.
```

2. Make sure to use UTF-8 URI encoding consistently. Copy the `$CATALINA_BASE/conf/server.xml` file from a Tomcat bundle to the server. Otherwise, open the `$CATALINA_BASE/conf/server.xml` file and add the attribute `URIEncoding="UTF-8"` to HTTP and AJP connectors that use `redirectPort=8443`. Here are examples:

    Old:

    ```xml
    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />
    ```

    New:

    ```xml
    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />
    ```

    Old:

    ```xml
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
    ```

    New:

    ```xml
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" URIEncoding="UTF-8" />
    ```

3. Refrain from writing access logs (optional) by commenting out the access log `Valve` element in `$CATALINA_BASE/conf/server.xml`. It's commented out here:

    ```xml
    <!-- <Valve className="org.apache.catalina.valves.AccessLogValve"
           directory="logs"
           prefix="localhost_access_log" suffix=".txt"
           pattern="%h %l %u %t &quot;%r&quot; %s %b" /> -->
    ```

4. Optionally, set the following log levels in the `$CATALINA_HOME/conf/logging.properties` file:

    ```properties
    org.apache.catalina.startup.Catalina.level=INFO
    org.apache.catalina.startup.ClassLoaderFactory.level=SEVERE
    org.apache.catalina.startup.VersionLoggerListener.level=WARNING
    org.apache.level=WARNING
    ```

5. For DXP 7.3 and earlier, open the `$CATALINA_HOME/conf/web.xml` file and set the JSP compiler to Java 8 and set DXP's `TagHandlerPool` class to manage the JSP tag pool by adding the following elements above the `jsp` servlet element's `<load-on-startup>` element.

    ```xml
    <init-param>
        <param-name>compilerSourceVM</param-name>
        <param-value>1.8</param-value>
    </init-param>
    <init-param>
        <param-name>compilerTargetVM</param-name>
        <param-value>1.8</param-value>
    </init-param>
    <init-param>
        <param-name>tagpoolClassName</param-name>
        <param-value>com.liferay.support.tomcat.jasper.runtime.TagHandlerPool</param-value>
    </init-param>
    ```

6. In `$CATALINA_HOME/conf/web.xml`, specify whether the application server should look for extra metadata, such as annotations in the application's JARs and classes. Setting `web-app` element's attribute `metadata-complete="true"` tells the application server there's no extra metadata. The application server starts faster with this setting. The default is to check for extra metadata.

7. If you're using Unix, Linux, or Mac OS, make the shell scripts in your `$CATALINA_HOME/bin` and `$CATALINA_BASE/bin` folders executable by running this command in each folder:

    ```bash
    chmod a+x *.sh
    ```

Liferay's Tomcat support JAR is part of the DXP web application. DXP uses the JAR's file scanner. The JAR needs to be in the common class loader for DXP to use it. Provide Catalina access to the file by opening your `$CATALINA_BASE/conf/catalina.properties` file and adding this value to the beginning of the `common.loader` property's comma-separated value list:

```properties
"${catalina.home}/webapps/ROOT/WEB-INF/lib/support-tomcat.jar",
```

For DXP 7.3 and earlier, provide Catalina access to the JARs in `$CATALINA_BASE/lib/ext` by adding these values to the beginning of the `common.loader` property's value list:

```
"${catalina.home}/lib/ext","${catalina.home}/lib/ext/*.jar",
```

**Checkpoint:**

1. The file encoding, user time-zone, and preferred protocol stack are set in the `setenv.sh` script.
1. The default memory available and Metaspace limit are set.
1. `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` declares the web application context.
1. `$CATALINA_BASE/conf/server.xml` sets UTF-8 encoding.
1. `$CATALINA_BASE/conf/server.xml` does not declare any value for writing host access logs. *(optional)*
1. `$CATALINA_HOME/conf/logging.properties` sets the desired log levels.
1. `$CATALINA_HOME/conf/web.xml` sets the tag handler pool and sets Java 8 as the JSP compiler.
1. `$CATALINA_HOME/conf/web.xml` specifies for the application server to refrain from looking for extra metadata. *(optional)*
1. The scripts in Tomcat's `bin` folders are executable.
1. The `common.loader` property in `$CATALINA_BASE/conf/catalina.properties`grants Catalina access to required JARs.

The application server is configured to run DXP.

## Database Configuration

DXP contains a built-in Hypersonic database which is great for demonstration purposes but **should not be used in production**. For production, use a full-featured, supported RDBMS. See [Configure a Database](../configuring-a-database.md) to set up your database.

Liferay DXP can connect with your database using DXP's built-in data source (recommended) or using a data source you create on your app server.

You can configure DXP's built-in data source with your database the first time you run DXP by using the [Setup Wizard](../running-liferay-for-the-first-time.md). Or you can configure the data source in a [`portal-ext.properties` file](../../reference/portal-properties.md) based on the [Database Template](../../reference/database-templates.md) for your database.

Otherwise, you can configure the data source in Tomcat.

### Configuring the Tomcat Data Source

1. Make sure the database server is installed and working. If it's installed on a different machine, verify that the DXP machine can access it.

1. Get the JDBC JAR from your DXP WAR (7.4+) or from the database vendor, and copy it to the `$TOMCAT_HOME/lib/ext` folder.

1. Open `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` and add data source as a `Resource` in the web application `Context`:

    ```xml
    <Context...>
        ...
        <Resource
            name="jdbc/LiferayPool"
            auth="Container"
            factory="com.zaxxer.hikari.HikariJNDIFactory"
            type="javax.sql.DataSource"
            minimumIdle="10"
            maxLifetime="0"
            maximumPoolSize="85"
            driverClassName="com.mysql.jdbc.Driver"
            dataSource.user="[place your user name here]"
            dataSource.password="[place your password here]"
            jdbcUrl="jdbc:mysql://localhost/lportal?characterEncoding=UTF8&amp;dontTrackOpenResources=true&amp;holdResultsOpenOverStatementClose=true&amp;useFastDateParsing=false&amp;useUnicode=true"
        />
    </Context>
    ```

    Make sure to replace the database URL, user name, and password with the appropriate values. Note that Liferay uses [Hikari CP](https://liferay.dev/blogs/-/blogs/tomcat-hikaricp) by default for the database connection pool.

1. In a `portal-ext.properties` file in **[Liferay_Home]**, specify the data source. For example,

    ```properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    ```

The data source is configured.

If you are using JNDI connections, see [Setting Up JNDI on Tomcat](./setting-up-jndi-on-tomcat.md)

## Mail Configuration

The easiest way to configure mail is to use the DXP [built-in mail session](../../setting-up-liferay/configuring-mail.md). If you use the built-in mail session, you may skip this section.

If you want to use Tomcat to manage the mail session, follow these steps:

1. Open `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` and define your mail session as a `Resource` in the web application `Context`. Replace the example mail session values with your own.

    ```xml
    <Context...>
        ...
        <Resource
            name="mail/MailSession"
            auth="Container"
            type="javax.mail.Session"
            mail.pop3.host="[place POP mail host here]"
            mail.pop3.port="110"
            mail.smtp.host="[place SMTP mail host here]"
            mail.smtp.port="465"
            mail.smtp.user="[place user name here]"
            mail.smtp.password="[place password here]"
            mail.smtp.auth="true"
            mail.smtp.starttls.enable="true"
            mail.smtp.socketFactory.class="javax.net.ssl.SSLSocketFactory"
            mail.imap.host="[place IMAP mail host here]"
            mail.imap.port="993"
            mail.transport.protocol="smtp"
            mail.store.protocol="imap"
        />
    </Context>
    ```

1. In the `portal-ext.properties` file in Liferay Home, specify the mail session. For example,

    ```properties
    mail.session.jndi.name=mail/MailSession
    ```

The mail session is configured on Tomcat.

## Deploying DXP

Start Tomcat by navigating to `$CATALINA_HOME/bin` and executing `./startup.sh`. Alternatively, execute `./catalina.sh run` to tail DXP's log file. The log audits startup activities and is useful for debugging deployment.

If you have a Liferay DXP Enterprise subscription, DXP requests your activation key. See [Activating Liferay DXP](../../setting-up-liferay/activating-liferay-dxp.md) for more information.

Congratulations! You're running DXP on Tomcat.

## Next Steps

You can [sign in as your administrator user](../../../getting-started/introduction-to-the-admin-account.md) and start building a solution on DXP. Or you can explore [additional Liferay DXP setup](../../setting-up-liferay.md) topics:

* [Installing the Marketplace Plugin](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
* [Accessing Plugins During a Trial Period](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
* [Installing a Search Engine](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md)
* [Securing Liferay DXP](../../securing-liferay.md)
* [Clustering for High Availability](../../setting-up-liferay/clustering-for-high-availability.md)