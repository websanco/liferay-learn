## Simulating A Production Environment

Deploying from a bundle is the easiest way to install Liferay, but in most environments you already have an existing app server setup you'd like to use. If we can't use a bundle, how do we install from scratch?

As a webapp, Liferay can be installed on a wide range of Java servers:

- Web Container
- Web Profile Java EE Server
- Full Profile Java EE Server

<div class="key-point">
    Key Point: <br>
    If we want the full experience of Java EE and access to the Java EE suite of features, we should use a Full Profile Application Server.
</div>

### Using A Full Profile Application Server {#usingj2ee}

A Full Profile Application Server implements all of the features outlined in the Java EE Spec. For many cases, this will be more than is required for your project, and you'll want to go with a Web Profile Server. In other cases, you will need all or nearly all of the features, so you should go with the Full Profile Application Server.

<img src="../images/liferay-container-EE-web-server.png" style="max-width: 100%" />

If you're using a Full Profile Application Server, then you're probably not going to simply unzip a bundle and run it. You need an established server with other applications currently running on it and you need Liferay to coexist with those applications on the same server.

<img src="../images/liferay-full-profile.png" style="max-width: 100%" />

Installing a webapp generally requires that we:

- Install an app server
- Configure the app server's JVM settings
- Set up additional resources in the app server
- Add any JARs to the server's library folders
- Deploy the webapp in a specific context

This familiar process is the same basic outline for deploying Liferay. Liferay is a Java webapp that supports running other apps inside of its *OSGi Container*. The basic outline for deploying Liferay is:

- Install app server
- Configure app server
- Install Liferay's dependencies
- Configure any additional settings:
    - JVM
    - Database
    - Mail Server
- Deploy the Liferay WAR file
- Test and tweak settings as desired

The details of the procedure will be different for each specific server you choose, but the basic outline is the same. In the following exercises we'll be using a Wildfly server.

**Wildfly** is a popular Full Profile Application Java EE server. A fully-supported enterprise version is available as *JBoss EAP*. It can run as a standalone server or operate a group of domains.

Whatever server you're using, Wildfly, its enterprise-supported cousin JBoss EAP, or another server, you'll follow their documented procedure for:

- Installing the server
- Configuring the server container to run as a daemon if required
- Customizing any settings needed for deploying Liferay or your other webapps

### Supporting Apps {#support}

Liferay has a number of global dependencies:

- **JDBC connector:** We need to make available the JAR for the database we're using
- **Portlet API:** Apps in Liferay use the Java Portlet Specification and need access to the classes in this JAR
- **Liferay API:** Code running in Liferay and supporting apps have full access to the Liferay API through this JAR
- **OSGi Container:** Liferay runs an OSGi container that contains core functionality of the platform

All we need to do is copy the appropriate dependencies to our server's library folder to make it available to Liferay and supporting apps. We'll add these dependencies to our server in the exercises for this section.

### Customizing Server Settings {#customsettings}

Each server has different settings to customize:

- Java memory settings
- Character set mapping
- Timezone defaults
- Database connectors

Depending on your particular server, you'll need to tweak things differently. In order for Liferay to work properly, UTF-8 encoding and the GMT timezone has to be set. All of these options are available as a part of Liferay's official documentation at https://portal.liferay.dev/docs/7-2/deploy. Use the documentation to check what settings need to be changed for your server. For Wildfly, we need to configure:

- System Properties
- Character set and encoding for pages
- Deployment timeout
- Server Subsystems
- JAAS security settings
- JSP settings
- Server Settings
- Startup Script
- JVM settings

Most of these settings we can manage from a handy web-based UI. The JVM settings are managed through a start script on the server. Let's take a look at some of the settings we'll be using in our exercises:

We'll deploy the JDBC driver for our database for later use. We've created a `module.xml` with the following information to enable Wildfly to recognize the JARs we added:

```xml
<?xml version="1.0"?>

<module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
    <resources>
        <resource-root path="com.liferay.petra.concurrent.jar" />
        <resource-root path="com.liferay.petra.executor.jar" />
        <resource-root path="com.liferay.petra.function.jar" />
        <resource-root path="com.liferay.petra.io.jar" />
        <resource-root path="com.liferay.petra.lang.jar" />
        <resource-root path="com.liferay.petra.memory.jar" />
        <resource-root path="com.liferay.petra.nio.jar" />
        <resource-root path="com.liferay.petra.process.jar" />
        <resource-root path="com.liferay.petra.reflect.jar" />
        <resource-root path="com.liferay.petra.string.jar" />
        <resource-root path="com.liferay.registry.api.jar" />
        <resource-root path="hsql.jar" />
<!--
        <resource-root path="mysql.jar" />
-->
        <resource-root path="portal-kernel.jar" />
        <resource-root path="portlet.jar" />
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

We've also added some information to `standalone.xml` that Liferay will need to deploy and run properly. Under the `<system-properties>` tag, we added two system properties:

```xml
<system-properties>
    <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
    <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING"
    value="true" />
</system-properties>
```

In addition, we've changed the `deployment-timeout` in the `<deployment>` tag:

```xml
  <deployment-scanner deployment-timeout="360" path="deployments"... />
```

We've added an additional security domain in `<security-domains>`:

```xml
 <security-domain name="PortalRealm">
    <authentication>
        <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
    </authentication>
</security-domain>
```

We've also enabled JSP development mode:

```xml
<jsp-config development="true" source-vm="1.8" target-vm="1.8" />
```

Most Java app servers have multiple methods of deployment:

- WAR, EAR, and JAR deployment
- Exploded directory deployment
- Hot deployment

Liferay ships as a convenient WAR file that can be deployed as-is to the app server. Many servers come with a management console to easily deploy with a simple UI. You can also deploy webapps through the file system and sometimes through a CLI.

Wildfly provides three methods of deployment:

- Web Management interface
- Console-based CLI
- File system

This flexibility allows you to choose what fits your environment and toolchain the best.

### Managing JNDI Connections {#managejndi}

Once we decide to fully install Liferay on a Java EE server, we can take advantage of offloading common resources to external servers:

- Database
- Mail

Not only do we need to decide which products to use, set up the servers, and migrate any data, we also need to configure Liferay to connect properly. Standard Java webapps and other enterprise solutions turn to *connection pools* for managing links to servers like the database.

<div class="key-point">
    Key Point: <br>
    A connection pool is just a collection of available connections to a server that can be handed out on request.
</div>

Liferay maintains its own internal connection pool for the database. This connection pool implementation is built on Hikari CP.

Liferay's default connection pool for the database establishes a link with Hypersonic.

<div class="note">
    Hypersonic is an embedded Java database
</div>

This connection is great for testing, demoing, and even some development. When we're setting up for real environments like development, QA, staging, or production, we'll want to use a well-established database server. Since the DBA has probably already set up the server, we only have to connect to it.

A simple way we can connect to our chosen database server is to use _Liferay's connection pool_. As a Java app, Liferay's connection is based on standard JDBC protocol. Using JDBC usually includes:

- Getting the correct Java database driver so the app knows how to connect
- Determining the hostname, port, username, password, and database name for the server
- Forming a standard JDBC connection string that includes much of this basic information

So modifying the internal connection pool means:

- Setting the default JDBC driver
- Configuring the database server username
- Setting the database user password
- Configuring the JDBC connection including the database name

The connection pool settings are configured as a set of application defaults. Liferay loads its application default configuration from a *properties* file. *Properties* files contain simple key-value pairs, similar to a `conf` file.

This file is called `portal.properties` and is stored inside Liferay. To override default settings in the *properties* file, we provide an extension to that file. If you create a file called `portal-ext.properties` inside your *Liferay Home* directory, Liferay will read it. When Liferay starts up, any settings you override in `portal-ext.properties` will override the defaults.

All of the JDBC settings are found in a section labeled `JDBC`:

```properties
    jdbc.default.driverClassName=org.hsqldb.jdbc.JDBCDriver
    jdbc.default.url=jdbc:hsqldb:${liferay.home}/data/hypersonic/lportal;
        hsqldb.write_delay=false
    jdbc.default.username=sa
    jdbc.default.password=
```

This is an extremely simple way to manage the connection to the database. Simply provide these settings in a properties file with your configuration, and Liferay will connect to your database.

Although simple and easy to manage, this method has some disadvantages:

- In order to configure the connection, the database user and password are stored in clear text
- You don't have full control over the connection pool settings.
- Connection pool configuration can't be modified at runtime.
- Any time you need to change part of the connection, you need to modify `portal-ext.properties`.
- There is no simple interface for managing the connection.

We'll need to find a more sustainable, secure method for configuring external connections.

### Using JNDI {#usejndi}

Instead of directly configuring connections to JDBC or the mail server, we can *point* to the right connection. Using a Java app server gives us access to JNDI, the virtual directory name interface.

<div class="key-point">
    Key Point: <br>
    With JNDI admins can map real connections and connection pools to a directory name and then configure Liferay to connect to that name.
</div>

Any changes to the connection are now independent of Liferay. How would this apply to a database server connection? JNDI provides a nifty virtual directory where we can create new *names*. So, we'll create a name called `liferay/Database`:

<img src="../images/jndi-name.png" style="max-width: 100%" />

With this new name, we can tell any application that wants this database to connect to the JNDI name `liferay/Database`:

<img src="../images/jndi-liferay.png" style="max-width: 100%" />

In our app server, we can create a JDBC connection. We can then tell the app server we want that connection to be mapped to the *name*:

<img src="../images/jndi-implementation.png" style="max-width: 100%" />

Now, when Liferay wants a connection to the database, it asks for the connection with the JNDI name `liferay/Database`. The app server provides Liferay with the appropriate database connection based on that name. Any changes to the connection can be made on-the-fly in the app server:

-   Connection pool size
-   Timeout length
-   Database being connected
-   Server address
-   Username
-   Password

Additionally, the app server can be responsible for securing the password for the database server.

Just like changing the JDBC connection settings directly, setting a JNDI name in Liferay is done with the properties file. You can set a JNDI name for different connections:

- Database Server
- Mail Server

These are done with very simple properties:
```properties
jdbc.default.jndi.name
mail.session.jndi.name
```

Setting up our app server to connect via a JNDI name is as easy as setting these two properties.

The most important external server we want to use is the database. This connection can be configured in Liferay's internal connection pool. We can also configure the app server to manage the database connection and expose it through JNDI.

Full Profile Application Servers like Wildfly fully support the broad range of Java EE. Support for JTA to manage database transactions is included. You can choose between supporting *XA* and *non-XA* datasources.

- *XA* (*Extended Architecture*) is part of a set of standards for distributed transaction processing.
     - With an *XA* datasource, database transactions are effective across a range of servers.
- A *non-XA* datasource is equal to a standalone database server-transactions only take effect on that datasource.

DBAs typically handle any setup and configuration of the database servers and can let you know if your data source needs *XA* support.

### Configuring Data Sources {#configdata}

Many Full Profile Application Servers have robust management consoles to manage connections and more. Configuration can also be done through XML and properties files in most cases. We'll configure our Wildfly datasource configuration in the XML files directly, but you could also setup in the console.

With a database connection in hand and mapped to a JNDI name, Liferay will be able to use a datasource on startup. To set up a datasource in an app server:

- Install the JDBC driver for your database.
- Configure Liferay to use a JNDI name.
- Add a datasource in your app server.
- Configure the app server datasource to use the same JNDI name.

We will use a similar procedure to map a mail server to a JNDI name. Liferay sends notifications, subscriptions, and password reset requests as emails. Your devs may have created applications that send mail through Liferay as well. Integrating with an external mail server is as simple as setting up a datasource connection.

To complete our setup, we need to connect this JNDI name to a mail session in our app server. Opening a mail session in the app server is simple and requires little configuration:
- Host name and port number
- JNDI name

The mail session acts as a reference to our real mail server. You can use any mail server you'd like, such as Postfix, sendmail, or an external service provider. For outgoing mail, set up an SMTP server. For incoming mail, you can use a POP or IMAP server. Most full--featured mail servers can support some or all protocols. In our case, we'd like to test the capabilities of sending mail without *actually* sending mail. To allow us to both configure a mail server and see messages being sent, we'll use an SMTP server that's nothing more than a façade.

*FakeSMTP* allows us to accept SMTP commands on a socket and capture the outgoing messages. This way we can both configure and connect to a live server and read the messages without them ever being sent.

<div class="summary"><h3>Summary</h3>
<ul>
	<li>A _____________________________________ implements all the features outlined in the ___________________________</li>
	<li>Liferay is a _______________________________________ that supports running other apps inside of its ___________________________________________________</li>
	<li>Standard Java webapps and other enterprise solutions turn to ________________________________________________________ for managing links to servers.</li>
	<li>A simple way to connect to an external server is to use __________________________________________________________________________________.</li>
	<li>JNDI is a virtual ________________________________________________________________.</li>
	<li>With JNDI, users can map ______________________________ to ______________________________.</li>
</ul>
</div>
