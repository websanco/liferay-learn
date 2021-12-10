# Installing on WebLogic

If you are using WebLogic as your application server, it is *highly recommended* to install DXP to a WebLogic Managed server. A managed server can start and stop DXP quickly and can be converted to a cluster configuration. Here you'll install DXP to a Managed Server.

## Prerequisites

Configure an Admin Server and a Managed Server following [WebLogic's documentation](http://www.oracle.com/technetwork/middleware/weblogic/documentation/index.html).

Liferay DXP requires a Java JDK 8 or 11. See [the compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151) to choose a JDK. See [JVM Configuration](../../reference/jvm-configuration.md) for recommended settings.

Download these files from the [Help Center](https://customer.liferay.com/downloads) (subscription) or from [Liferay Community Downloads](https://www.liferay.com/downloads-community):

* DXP WAR file
* OSGi Dependencies ZIP file
* Dependencies ZIP file (DXP 7.3 and earlier)

## Preparing the DXP WAR

1. Unzip the DXP WAR file to an arbitrary location.

1. Create a file called [`portal-ext.properties`](../../reference/portal-properties.md) in the expanded WAR's `WEB-INF/classes` folder.

1. In the `portal-ext.properties` file, set the `liferay.home` property to your [*Liferay Home*](../../reference/liferay-home.md) folder path. In WebLogic, [`[Liferay Home]`](../../reference/liferay-home.md) is typically set to the domain's folder, but you can use any local folder. For example,

    ```properties
    liferay.home=/full/path/to/your/liferay/home/folder
    ```

1. Package the `portal-ext.properties` in your DXP WAR file by expanding the DXP WAR file and copying the `portal-ext.properties` file into the `WEB-INF/classes` folder.

1. Optionally, you can re-WAR the expanded DXP WAR. When you're ready to deploy DXP, you can deploy it as an expanded archive or WAR file. In both cases, DXP reads the property settings once it starts up.

```{note}
If you need to update `portal-ext.properties` after DXP deploys, it is in the user domain's `autodeploy/ROOT/WEB-INF/classes` folder. Note that the `autodeploy/ROOT` folder contains the DXP deployment.
```

## Configuring WebLogic

### Configuring WebLogic's Node Manager

WebLogic's Node Manager starts and stops managed servers.

If you're running WebLogic on a UNIX system other than Solaris or Linux, use the Java Node Manager, instead of the native version of the Node Manager, by configuring these Node Manager properties in the `domains/your_domain_name/nodemanager/nodemanager.properties` file:

```properties
NativeVersionEnabled=false

StartScriptEnabled=true
```

```{note}
By default, the Node Manager uses SSL. If you want to disable SSL during development, set `SecureListener=false` in your `nodemanager.properties` file.
```

See Oracle's [Configuring Java Node Manager](https://docs.oracle.com/middleware/1212/wls/NODEM/java_nodemgr.htm#NODEM173) documentation for details.

### Configuring WebLogic's JVM

Configure the JVM and other options in a `setUserOverridesLate` WebLogic startup script and in your Managed Server UI.

1. Create a `setUserOverridesLate.sh` script in `[Your Domain]/bin`.

1. Add the following settings.

    ```bash
    export DERBY_FLAG="false"
    export JAVA_OPTIONS="${JAVA_OPTIONS} -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -Duser.timezone=GMT -da:org.apache.lucene... -da:org.aspectj..."
    export JAVA_PROPERTIES="-Dfile.encoding=UTF-8 ${JAVA_PROPERTIES} ${CLUSTER_PROPERTIES}"
    export MW_HOME="[place your WebLogic Server folder path here]"
    export USER_MEM_ARGS="-Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
    ```

    The `DERBY_FLAG` setting disables the WebLogic's built-in Derby server---DXP does not require this server.

    `JAVA_OPTIONS` sets DXP's UTF-8 requirement, Lucene usage, and Aspect Oriented Programming via AspectJ.

    `JAVA_PROPERTIES` also sets DXP's UTF-8 requirement. TODO use the lowercase one per liferay-portal?

    ```{important}
    DXP requires the application server JVM to use the `GMT` time zone and `UTF-8` file encoding.
    ```

    Set `MW_HOME` to the folder containing the WebLogic server. For example,

    ```bash
    export MW_HOME="/Users/ray/Oracle/wls12210"
    ```

    The `*_MEM_ARGS` variables set DXP's starting and maximum heap memory capacity.

1. Make sure the Node Manager sets DXP's memory requirements when starting the Managed Server. In the Admin Server's console UI, navigate to the Managed Server where DXP is to be deployed and select the *Server Start* tab. Enter the following parameters into the *Arguments* field:

    ```bash
    -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7
    ```

1. Click *Save*.

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

## Installing Dependencies

DXP depends on OSGi modules (OSGi Dependencies ZIP) and a database driver.

1. Unzip the OSGi Dependencies ZIP file into the `[Liferay Home]/osgi` folder (create this folder if it doesn't exist). Liferay's OSGi runtime depends on these modules.
1. The DXP 7.4+ WAR file includes drivers for MariaDB and PostgreSQL. Earlier DXP WARs don't have them. If the 7.4+ WAR doesn't have the driver for the supported database you're using, download your database vendor's JDBC JAR file and place it in the exploded DXP WAR's `WEB-INF/shielded-container-lib` folder.

    Please see the [compatibility matrix](https://help.liferay.com/hc/en-us/articles/360049238151) for a list of supported databases.

```{note}
A Hypersonic database is bundled with DXP and is useful for testing purposes. **Do not** use HSQL for production instances.
```

```{note}
For DXP 7.3 and earlier, unzip the Dependencies ZIP file to the WebLogic domain's `lib` folder. Place your database vendor's JDBC JAR file in that folder too.
```

## Installing Elasticsearch

When DXP starts, it installs and starts a default [sidecar](../../../using-search/installing-and-upgrading-a-search-engine/elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md) Elasticsearch server. For the installation to succeed, you must provide some archives:

Download the following archives to your `[Liferay Home]` folder.

* [Elasticsearch OSS No JDK 7.9](https://www.elastic.co/guide/en/elasticsearch/reference/7.9/release-notes-7.9.0.html) ([available here--7.9.0](https://www.elastic.co/downloads/past-releases/elasticsearch-oss-no-jdk-7-9-0))
* [ICU Analysis Plugin](https://www.elastic.co/guide/en/elasticsearch/plugins/7.9/analysis-icu.html) ([download](https://artifacts.elastic.co/downloads/elasticsearch-plugins/analysis-icu/analysis-icu-7.9.0.zip))
* [Japanese (kuromoji) Analysis Plugin](https://www.elastic.co/guide/en/elasticsearch/plugins/7.9/analysis-kuromoji.html) ([download](https://artifacts.elastic.co/downloads/elasticsearch-plugins/analysis-kuromoji/analysis-kuromoji-7.9.0.zip))
* [Smart Chinese Analysis Plugin](https://www.elastic.co/guide/en/elasticsearch/plugins/7.9/analysis-smartcn.html) ([download](https://artifacts.elastic.co/downloads/elasticsearch-plugins/analysis-smartcn/analysis-smartcn-7.9.0.zip))
* [Stempel Polish Analysis Plugin](https://www.elastic.co/guide/en/elasticsearch/plugins/7.9/analysis-stempel.html) ([download](https://artifacts.elastic.co/downloads/elasticsearch-plugins/analysis-stempel/analysis-stempel-7.9.0.zip))

On DXP startup, DXP unpackages the archives, installs them, and starts the sidecar Elasticsearch server.

## Connect to Database

DXP contains a built-in Hypersonic database for demonstration purposes, but _it should not be used in production_. Use a full-featured, supported database. See [Configure a Database](../configuring-a-database.md) to set up your database.

Liferay DXP can connect to your database using DXP's built-in data source (recommended) or a JNDI data source on your app server.

You can configure DXP's built-in data source with your database the first time you run DXP by using the [Setup Wizard](../running-liferay-for-the-first-time.md). Or you can configure the data source in a [`portal-ext.properties` file](../../reference/portal-properties.md) based on the [Database Template](../../reference/database-templates.md) for your database.

Otherwise, you can configure the data source in WebLogic.

1. Get the JDBC JAR from your DXP WAR (7.4+) or from the database vendor, and copy it to the domain's `lib` folder.
1. Log in to the AdminServer console.
1. In the *Domain Structure* tree, find the domain and navigate to *Services* &rarr; *JDBC* &rarr; *Data Sources*.
1. To create a new data source, click *New*.
1. Enter the *Name* field with `Liferay Data Source` and the *JNDI Name* field with `jdbc/LiferayPool`.
1. Select the database type and driver. For example, MySQL is *MySQL's Driver (Type 4) Versions:using com.mysql.cj.jdbc.Driver*.
1. Click *Next* to continue.
1. Accept the default settings on this page and click *Next* to move on.
1. Fill in the database information for the MySQL database.
1. If using MySQL, add the text `?useUnicode=true&characterEncoding=UTF-8&\useFastDateParsing=false` to the URL line and test the connection. If it works, click *Next*.
1. Select the target for the data source and click *Finish*.
1. Connect DXP to the JDBC data source. In the `portal-ext.properties` file (see above), enter the data source JNDI name. For example,

    ```properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    ```

## Connect to Mail Server

You can connect Liferay DXP [to a mail server](../../setting-up-liferay/configuring-mail.md) using its built-in mail session. Otherwise, you can use WebLogic's mail session:

1. Start WebLogic and log in to the Admin Server's console.
1. Select *Services* &rarr; *Mail Sessions* from the *Domain Structure* box on the left hand side of the Admin Server's console UI.
1. Click *New* to begin creating a new mail session.
1. Name the session *LiferayMail* and give it the JNDI name `mail/MailSession`.
1. Enter the *Session Username*, *Session Password*, *Confirm Session Password*, and *JavaMail Properties* fields as necessary for the mail server. See the [WebLogic documentation](http://docs.oracle.com/middleware/1221/wls/FMWCH/pagehelp/Mailcreatemailsessiontitle.html) for more information on these fields.
1. Click *Next*.
1. Choose the Managed Server where DXP is to be installed on, and click *Finish*.
1. Shut down the Managed and Admin Servers.
1. With the Managed and Admin servers shut down, add the following property to the `portal-ext.properties` file in Liferay Home:

    ```properties
    mail.session.jndi.name=mail/MailSession
    ```

```{note}
After DXP is deployed, the `portal-ext.properties` file can be found in the domain's `autodeploy/ROOT/WEB-INF/classes` folder.
```

The changes take effect upon restarting the Managed and Admin servers.

## Deploying DXP

Follow these steps to deploy the DXP WAR file:

1. Verify that the designated Managed Server where you're deploying DXP is shut down.
1. In the Admin Server's console UI, select *Deployments* from the *Domain Structure* box on the left hand side.
1. Click *Install* to start a new deployment.
1. Select the DXP WAR file or its expanded contents on the machine. Alternatively, upload the WAR file by clicking the *Upload your file(s)* link. Click *Next*.
1. Select *Install this deployment as an application* and click *Next*.
1. Select the designated Managed Server where you're deploying DXP and click *Next*.
1. If the default name is appropriate for the installation, keep it. Otherwise, enter a different name and click *Next*.
1. Click *Finish*.
1. After the deployment finishes, click *Save* if the configuration is correct.
1. Start the Managed Server where you deployed DXP. DXP precompiles all the JSPs and then launches.

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

Congratulations! You're running DXP on WebLogic.

## Next Steps

You can [sign in as your administrator user](../../../getting-started/introduction-to-the-admin-account.md) and start building a solution on DXP/Portal. Or you can explore [additional setup](../../setting-up-liferay.md) topics:

* [Installing the Marketplace Plugin](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
* [Accessing EE Plugins During a Trial Period](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
* [Installing a Search Engine](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md)
* [Securing Liferay](../../securing-liferay.md)
* [Clustering for High Availability](../../setting-up-liferay/clustering-for-high-availability.md)