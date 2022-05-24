# Setting Up JNDI on Tomcat

{bdg-secondary}`Applicable for Liferay DXP 7.4+ or Liferay Portal 7.4+`

To set up JNDI resources, you must put the necessary JDBC drivers in the Tomcat lib directory (i.e. `tomcat-9.0.56/lib`). For example, if you use an Oracle database, copy the `ojdbc8.jar` file. If you use the default [Hikari Connection Pool](https://github.com/brettwooldridge/HikariCP), you must copy the `hikaricp.jar` and `slf4-api.jar` files as well.

After copying the necessary files, define your JNDI resources.

For example, modify the `tomcat-9.0.56/conf/Catalina/localhost/ROOT.xml` file:

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

In your `portal-ext.properties` file, use the JNDI reference: `jdbc.default.jndi.name=jdbc/liferay`.