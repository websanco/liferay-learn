# Database Templates

Below are templates (example [portal properties](./portal-properties.md) and [Docker environment variables](https://docs.docker.com/engine/reference/commandline/run/#set-environment-variables--e---env---env-file)) for configuring various databases as a built-in data source for Liferay DXP.

## MariaDB

### Portal Properties

```properties
jdbc.default.driverClassName=org.mariadb.jdbc.Driver
jdbc.default.url=jdbc:mariadb://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
jdbc.default.username=
jdbc.default.password=
```

### Docker Variables

```bash
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=org.mariadb.jdbc.Driver \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL="jdbc:mariadb://hostname:3306/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false" \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME= \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD= \
```

## MySQL

```important::
   MySQL Connector/J 8.0 is highly recommended for use with MySQL Server 8.0 and 5.7.
```

### Portal Properties

```properties
jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.default.url=jdbc:mysql://localhost/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&serverTimezone=GMT&useFastDateParsing=false&useUnicode=true
jdbc.default.username=
jdbc.default.password=
```

### Docker Variables

```bash
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=com.mysql.cj.jdbc.Driver \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL="jdbc:mysql://hostname:3306/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false" \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME= \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD= \
```

## PostgreSQL

### Portal Properties

```properties
jdbc.default.driverClassName=org.postgresql.Driver
jdbc.default.url=jdbc:postgresql://localhost:5432/lportal
jdbc.default.username=
jdbc.default.password=
```

### Docker Variables

```bash
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=org.postgresql.Driver \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL="jdbc:postgresql://hostname:3306/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false" \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME= \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD= \
```

See the [default portal properties](https://docs.liferay.com/ce/portal/7.4-latest/propertiesdoc/portal.properties.html#JDBC) for more database templates.