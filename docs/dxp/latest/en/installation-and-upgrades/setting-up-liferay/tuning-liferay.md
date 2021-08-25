# Tuning Liferay

There are several ways to tune Liferay's performance. This involves configuring the Java Virtual Machine and frameworks that support the Liferay application, monitoring performance and resources, and adjusting settings to meet your needs. Here's an overview of the tuning topics.

## Disabling Developer Settings

Some developer features aren't intended for production and must therefore be disabled to optimize performance. These include features that do these things:

* Accommodate debuggers
* Perform system checks
* Upgrade data on startup automatically
* Poll for code changes to apply automatically

Start with disabling all developer Portal Properties.

### Portal Developer Properties

Liferay's [Portal Properties](../reference/portal-properties.md) includes several properties that facilitate development. The [`portal-developer.properties`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-impl/src/portal-developer.properties) included in your Liferay installation declares all of the properties but is disabled by default. This file is only enabled if you referenced it in your `portal-ext.properties` file using this setting:

```properties 
include-and-override=portal-developer.properties
```

If you included Liferay's `portal-developer.properties` file or included your own developer properties files (e.g., `[Liferay Home]/portal-developer.properties`), disable them by commenting them out in your `portal-ext.properties` file:

```properties 
#include-and-override=portal-developer.properties
#include-and-override=${liferay.home}/portal-developer.properties
```

Similarly, if you enabled any developer properties individually, comment them out too.

Next, disable developer settings in your JSP engine.

### JSP Engine Settings

Many application servers' JSP Engines are set for development by default. Deactivate these settings prior to entering production:

**Development mode:** This makes the JSP container poll the file system for changes to JSP files. Since you don't change JSPs on-the-fly like this in production, turn off this mode.

**Mapped File:** In development, it's typical to generate static content with one print statement versus one statement per line of JSP text. In production, opt for the latter.

To disable development mode and the mapped file in Tomcat, for example, update the `$CATALINA_HOME/conf/web.xml` file's JSP servlet configuration to this:

```xml
<servlet>
    <servlet-name>jsp</servlet-name>
    <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>   
    <init-param>
        <param-name>development</param-name>
        <param-value>false</param-value>
    </init-param>
    <init-param>
        <param-name>mappedFile</param-name>
        <param-value>false</param-value>
    </init-param>
    <load-on-startup>3</load-on-startup>
</servlet>
```

Development mode and the mapped file are disabled.

Now that you have disabled non-production developer features, configure the application server's thread pool.

## Configuring the Thread Pool 

Each request to the application server consumes a worker thread for the duration of the request. When no threads are available to process requests, the request is queued to wait for the next available worker thread. In a finely tuned system, the number of threads in the thread pool are balanced with the total number of concurrent requests. There should not be a significant amount of threads left idle to service requests.

Use an initial thread pool setting of 50 threads and then monitor it within your application server's monitoring consoles. You may wish to use a higher number (e.g., 250) if your average page times are in the 2-3 second range. Too few threads in the thread pool might queue excessive requests; too many threads can cause excessive context switching.

In Tomcat, the thread pools are configured in the `$CATALINA_HOME/conf/server.xml` file's `Connector` element. The [Apache Tomcat documentation](https://tomcat.apache.org/tomcat-9.0-doc/config/http.html) provides more details. Here's an example thread pool configuration:

```xml
<Connector
    address="xxx.xxx.xxx.xxx"
    connectionTimeout="600000"
    maxConnections="16384"
    maxKeepAliveRequests="-1"
    maxThreads="75"
    minSpareThreads="50"
    port="8080"
    redirectPort="8443"
    socketBuffer="-1"
    URIEncoding="UTF-8"
/>
```

If you're testing a CPU-based load or if you're concerned about CPU capacity,  test using approximately 75 threads for every hardware thread available. For example, if your machine has 4 hardware threads, set `maxThreads=300`. If you're testing an I/O-based load or you're concerned about I/O capacity, use more threads or switch to using a non-I/O blocking connector. Test your system and adjust connection pool settings to meet your needs.

Additional tuning parameters around `Connector` are available, including the connector types, the connection timeouts, and TCP queue. Consult your application server's documentation for details.

## Configuring the Database Connection Pool

Database connection pools manage database connections for reuse, saving you from opening new connections with every new request. The pool provides a connection whenever Liferay needs to retrieve data from the database. If the pool size is too small, requests queue in the server waiting for database connections. If the size is too large, however, idle database connections waste resources.

Configure the pool size slightly larger than the thread pool size, unless the thread pool size is large (e.g., 200+). Under normal usage, most worker threads use at most one JDBC connection at a time. Some threads, such as threads that have nested transactions, however, use multiple database connections. Making the connection pool size slightly larger than the thread pool size accounts for such threads.

```{note}
If the thread pool size is large, making the connection pool a similar size won't help performance.
```

If the number of connections encroaches on your database connection limit, shrink your Counter data source's pool size. Since Counter database transactions are small, fast, and never nested, the Counter connection pool is a good candidate for reducing. For more information on the Counter data source, see [Database Configuration for Cluster Nodes](./clustering-for-high-availability/database-configuration-for-cluster-nodes.md).

Liferay can use C3PO, DBCP, HikariCP, or Tomcat for connection pooling. The connection pool provider is set using the [`jdbc.default.liferay.pool.provider`](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#JDBC) [Portal Property](../reference/portal-properties.md). HikariCP is the default.

```properties
jdbc.default.liferay.pool.provider=hikaricp
jdbc.default.maximumPoolSize=85
jdbc.default.minimumIdle=10
```

There are [JDBC connection pool Portal Properties](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#JDBC) for all of the supported connection pools. See your connection pool vendor's information for additional configuration details.

As with thread pools, monitor your connection pools and adjust them based on your performance tests.

## Configuring Your Java Virtual Machine

Your application server runs in a Java Virtual Machine (JVM). Memory management and garbage collection affect how fast Liferay responds to user requests. Please see [Tuning Your JVM](./tuning-your-jvm.md) for instructions next.
