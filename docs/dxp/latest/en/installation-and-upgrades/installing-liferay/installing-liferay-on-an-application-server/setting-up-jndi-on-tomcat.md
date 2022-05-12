# Setting Up JNDI on Tomcat

{bdg-secondary}`Applicable for Liferay DXP 7.4+ or Liferay Portal 7.4+`

To set up JNDI resources, you need to put the necessary JDBC drivers into the Tomcat lib directory (i.e. `tomcat-9.0.56\lib`). For example, if you are using an Oracle database, copy the `ojdbc8.jar` file. If you are using a [Hikari Connection Pool](https://github.com/brettwooldridge/HikariCP), you will need to copy the `hikaricp.jar` and `slf4-api.jar` files as well.

After placing the necessary files, define your JNDI resources.

For example, in the `tomcat-9.0.56\conf\Catalina\localhost\ROOT.xml` file:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<Context crossContext="true">
	<JarScanner className="com.liferay.support.tomcat.util.scan.NOPJarScanner" />

	<Manager pathname="" />

	<Resource name="jdbc/liferay" auth="Container"
		factory="com.zaxxer.hikari.HikariJNDIFactory"
		type="javax.sql.DataSource"
		minimumIdle="5" 
		maximumPoolSize="10"
		connectionTimeout="300000"
		driverClassName="oracle.jdbc.OracleDriver"
		jdbcUrl="jdbc:oracle:thin:@192.168.1.213:1521/liferay"
		dataSource.user="liferay"
	dataSource.password="password" />
</Context>
```

Then in your `portal-ext.properties` file, use the JNDI reference: `jdbc.default.jndi.name=jdbc/liferay`.

Note that Liferay uses a shielded class loader in order to isolate the webapp class loader from the OSGi containers. Using `PortalClassLoaderUtil.getClassLoader()` returns the shielded class loader. Therefore, when invoking `getClassLoader()` use the following:

```java
PortalClassLoaderUtil.getClassLoader().getClass().getClassLoader()
```