# Generating Model, Persistence, and Service Code

Service Builder makes it easy to define models and generate model, persistence, and service code for them. You'll experience this by defining a model called `Y7G4Entry` and generating code using Service Builder. Then you'll deploy your code to DXP and invoke a service that uses the code.

## Download the Example Project

Download and unzip the example project.

```bash
curl https://learn.liferay.com/dxp/latest/en/building-applications/data-frameworks/service-builder/service-builder-basics/liferay-y7g4.zip -O
```

```bash
unzip liferay-y7g4.zip
```

The `liferay-y7g4` project has two modules:

* `y7g4-api`
* `y7g4-service`

The API module (`-api`) provides the public interfaces and utilities. The service module (`-service`) provides the implementation.

## Examine the API Module

The API module has only a bnd metadata file and a Gradle build file. 

```
y7g4-api
 ├── bnd.bnd // Defines the module artifact, package exports, and includes the service XML file
 └── build.gradle // Declares dependencies
```

Here's the `bnd.bnd` file:

```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-api/bnd.bnd
```

The `Bundle-` headers describe the module artifact. The `Export-Package` header specifies the API packages to publish. See [Module Projects](../../../../liferay-internals/fundamentals/module-projects.md) for details on bnd metadata and how it's used.

The `build.gradle` file declares the module's dependency on DXP/Portal.

```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-api/build.gradle
:language: groovy
```

## Examine the Service Module

The Service Module has a bnd metadata file, a Gradle build file, and a service definition file. 

```
y7g4-service
 ├── bnd.bnd // Defines the module artifact, data schema version, and more
 ├── build.gradle // Declares dependencies and code generation parameters
 └── service.xml // Specifies models and their relationships
```

Here's the `bnd.bnd` file:

```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-service/bnd.bnd
```

Once again, the `Bundle-` headers describe the module artifact. Service metadata and a directive follow.

| Metadata | Description |
| :------- | :---------- |
| `Liferay-Require-SchemaVersion: 1.0.0` | Your application's data schema version. When you release application versions that have database schema changes, you'll increment the version. |
| `Liferay-Service: true` | The module provides a Liferay Service. |
| `-dsannotations-options: inherit` | OSGi service component classes inherit [OSGi Declarative Services](../../../../liferay-internals/fundamentals/apis-as-osgi-services.md) annotations from their class hierarchy. For example, extension classes can access all the services that ancestor fields reference via the `@Reference` annotation. |

Here's the `build.gradle` file:

```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-service/build.gradle
:language: groovy
```

The `buildService` task generates the service's API classes to the API module Java source folder specified by `apiDir`. The service module depends on DXP/Portal and the API module (in the sibling folder `y7g4-api`).

## Examine the Service Model Definition

The `service.xml` file defines the `Y7G4Entry` model entity. Service Builder generates model, persistence, and service classes per the `service.xml` file's specification.

Here's the `service.xml` file:

```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-service/service.xml
```

This file defines a `Y7G4Entry` model that has an ID (the primary key), name, and description.

### `service-builder` Element

The `service-builder` element attributes affect all model entities in the `service.xml` file.

| `service-builder` attribute | Description |
| :-------------------------- | :---------- |
| `dependency-injector` | Declares the dependency injector type. Declarative Services (`ds`) is the default. |
| `package-path` | Declares the leading package path for the generated classes. |
| `short-no-such-exception-enabled` | If set to `true`, use a truncated version of the entity name in `NoSuchY7G4EntryException` messages; otherwise use the complete entity name. |

### `namespace` Element

The global `namespace` element specifies the prefix for all the model entity database tables.

### `entity` Element

`entity` elements define model database tables and service types.

| `entity` attributes | Description |
| :------------------ | :---------- |
| `name` | The entity's name. Service Builder generates an entity table using the naming format `[namespace]_[name]` (for example, `Y7G4_Y7G4Entry`). |
| `local-service` | If `true`, generate service classes to call from within the JVM. |
| `remote-service` | If `true`, generate service classes, including web services classes, to call from outside of the JVM. |

### `column` Elements
Each `column` element defines a column in the entity's table. Here are the `Y7G4Entry` entity column elements:

| Column | Description |
| :----- | :---------- |
| `y7g4EntryId` | the model instance's ID (long integer) and primary key. |
| `name` | the instance's name (string). |
| `description` | the instance's description (string). |

For more information on `service.xml` elements, see the [Liferay Service Builder DTD](https://learn.liferay.com/reference/latest/en/dxp/definitions/liferay-service-builder_7_4_0.dtd.html).

## Generate the Persistence Code

Invoke Service Builder to generate persistence code and database scripts.

```bash
cd liferay-y7g4
```

```bash
./gradlew y7g4-service:buildService
```

Output:

```
> Task :y7g4-service:buildService
Building Y7G4Entry
Writing src/main/java/com/acme/y7g4/service/persistence/impl/Y7G4EntryPersistenceImpl.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/persistence/Y7G4EntryPersistence.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/persistence/Y7G4EntryUtil.java
Writing src/main/java/com/acme/y7g4/service/persistence/impl/Y7G4EntryModelArgumentsResolver.java
Writing src/main/java/com/acme/y7g4/model/impl/Y7G4EntryModelImpl.java
Writing src/main/java/com/acme/y7g4/model/impl/Y7G4EntryBaseImpl.java
Writing src/main/java/com/acme/y7g4/model/impl/Y7G4EntryImpl.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4EntryModel.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4Entry.java
Writing src/main/java/com/acme/y7g4/model/impl/Y7G4EntryCacheModel.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4EntryWrapper.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4EntrySoap.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4EntryTable.java
Writing src/main/java/com/acme/y7g4/service/impl/Y7G4EntryLocalServiceImpl.java
Writing src/main/java/com/acme/y7g4/service/base/Y7G4EntryLocalServiceBaseImpl.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/Y7G4EntryLocalService.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/Y7G4EntryLocalServiceUtil.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/Y7G4EntryLocalServiceWrapper.java
Writing src/main/resources/META-INF/module-hbm.xml
Writing src/main/resources/META-INF/portlet-model-hints.xml
Writing ../y7g4-api/src/main/java/com/acme/y7g4/exception/NoSuchY7G4EntryException.java
Writing src/main/java/com/acme/y7g4/service/persistence/impl/constants/Y7G4PersistenceConstants.java
Writing src/main/resources/META-INF/sql/tables.sql
Writing src/main/resources/META-INF/sql/tables.sql
Writing src/main/resources/service.properties

BUILD SUCCESSFUL in 3s
1 actionable task: 1 executed
```

Service Builder generates Java classes, database scripts, and configuration files for the model, persistence, and services. The file paths are relative to the `y7g4-service` module. 

Here's an overview of the generated structure:

```
liferay-y7g4
├── y7g4-api
│   └── src
│       └── main
│           └── java
│               └── com
│                   └── acme
│                       └── y7g4
│                           ├── exception // Public exception classes & interfaces
│                           ├── model // Public model classes & interfaces
│                           └── service // Public persistence and service classes
│                                       // & interfaces
└── y7g4-service
    └── src/main
        ├── java/com/acme/y7g4
        │                 ├── model // Model implementation
        │                 └── service // Persistence and service implementation
        └── resources
            ├── META-INF
            │   ├── module-hbm.xml // Hibernate object relational map configuration
            │   ├── portlet-model-hints.xml // Provides field type information for the UI
            │   └── sql
            │       ├── indexes.sql
            │       ├── sequences.sql
            │       └── tables.sql
            └── service.properties // Tracks the service build version
```

The model, persistence, and service implementation classes were generated to the Java package path `com.acme.y7g4`. Learn about the classes at [Understanding Service Builder Generated Classes](./understanding-service-builder-generated-classes.md).

SQL scripts and the persistence configuration were generated to the `resources/META-INF` folder.

The `module-hbm.xml` file specifies the Hibernate object relational map.

```xml
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">

<hibernate-mapping auto-import="false" default-lazy="false">
    <import class="com.acme.y7g4.model.Y7G4Entry" />
    <class name="com.acme.y7g4.model.impl.Y7G4EntryImpl" table="Y7G4_Y7G4Entry">
        <id access="com.liferay.portal.dao.orm.hibernate.LiferayPropertyAccessor" name="y7g4EntryId" type="long">
            <generator class="assigned" />
        </id>
        <property access="com.liferay.portal.dao.orm.hibernate.LiferayPropertyAccessor" name="description" type="com.liferay.portal.dao.orm.hibernate.StringType" />
        <property access="com.liferay.portal.dao.orm.hibernate.LiferayPropertyAccessor" name="name" type="com.liferay.portal.dao.orm.hibernate.StringType" />
    </class>
</hibernate-mapping>
```

The `module-hbm.xml` file maps `Y7G4EntryImpl` objects to the `Y7G4_Y7G4Entry` table. For more information on mapping with Hibernate, visit [Hibernate](https://hibernate.org).

The `tables.sql` script specifies the `Y7G4_Y7G4Entry` table.

```sql
create table Y7G4_Y7G4Entry (
    y7g4EntryId LONG not null primary key,
    description VARCHAR(75) null,
    name VARCHAR(75) null
);
```

`y7g4EntryId` is the primary key. `name` and `description` are attributes. When you deploy the module, DXP/Portal creates the table by running the `tables.sql` script.

Since this `service.xml` file's elements don't specify indexes or sequences, the `indexes.sql` or `sequences.sql` scripts are empty.

## Deploy the Persistence Layer and Services

It's time to create the persistence layer and services by deploying the generated code to a DXP server. The server uses a data source on a separate MariaDB database server---it's easier to examine a database on MariaDB than the bundled Hypersonic server. After deploying everything, you'll verify the tables and test the services.

### Create the Database

1. Start a MariaDB Docker container.

    ```bash
    docker run --name some-mariadb -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mariadb:10.2
    ```

1. [Create the DXP database](../../../../installation-and-upgrades/reference/database-configurations.md) from within the MariaDB Docker container.

    Sign in to the database server.

    ```bash
    docker exec -it some-mariadb bash -c "/usr/bin/mysql -uroot -pmy-secret-pw"
    ```

    Create a database for DXP.

    ```sql
    create database dxp_db character set utf8;
    ```

    End your database session.

    ```bash
    quit
    ```

1. Get the MariaDB container IP address by invoking Docker's `network inspect`](https://docs.docker.com/engine/reference/commandline/network_inspect/) command on the default network (`bridge`) 

    ```bash
    docker network inspect bridge
    ```

Example output:

```
"Containers": {
    "162f5350ee9ba7c47c1ba91f54a84543aeada7feb35eb8153743b13ef54cb491": {
        "Name": "some-mariadb",
        "EndpointID": "8e97e35fb118e2024a52f2ecbfd40b0a879eba8dc3bc5ffceea8bb117c10bebc",
        "MacAddress": "02:42:ac:11:00:02",
        "IPv4Address": "172.17.0.2/16",
        "IPv6Address": ""
    },
```

Use the first part of the `IPv4Address` value for the `some-mariadb` container. The IP address from the example is `172.17.0.2`.

### Start the Server

In a separate terminal, start DXP using the following command. Make sure to replace `[IP address]` with the `some-mariadb` container IP address.

```bash
docker run -it \
--add-host some-mariadb:[IP address] \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_JNDI_PERIOD_NAME="" \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=org.mariadb.jdbc.Driver \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL="jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false" \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME=root \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD=my-secret-pw \
-m 8g \
-p 8080:8080 \
liferay/portal:7.4.2-ga3
```

### Deploy the Modules

Deploy the modules to create the database table and to install the services.

```bash
./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
```

Console output:

```bash
STARTED com.acme.y7g4.service_1.0.0 [1423]
STARTED com.acme.y7g4.api_1.0.0 [1422]
```

### Check the Tables

Verify and validate the database table.

1. Sign in to the database server.

    ```bash
    docker exec -it some-mariadb bash -c "/usr/bin/mysql -uroot -pmy-secret-pw"
    ```

1. Connect to the database.

    ```sql
    connect dxp_db;
    ```

1. List the database tables to verify the `Y7G4_Y7G4Entry` table.

    ```sql
    show tables;
    ```

    Results:

    ```
    +--------------------------------+
    | Tables_in_dxp_db               |
    +--------------------------------++
    | AMImageEntry                   |
    | AccountEntry                   |
    | AccountEntryOrganizationRel    |
    | ...                            |
    | Y7G4_Y7G4Entry                 |
    +--------------------------------+
    ```

1. List the `Y7G4_Y7G4Entry` table columns.

    ```sql
    SHOW COLUMNS FROM Y7G4_Y7G4Entry;
    ```

    Results:

    ```
    +-------------+-------------+------+-----+---------+-------+
    | Field       | Type        | Null | Key | Default | Extra |
    +-------------+-------------+------+-----+---------+-------+
    | y7g4EntryId | bigint(20)  | NO   | PRI | NULL    |       |
    | description | varchar(75) | YES  |     | NULL    |       |
    | name        | varchar(75) | YES  |     | NULL    |       |
    +-------------+-------------+------+-----+---------+-------+
    ```

    Everything is in place.

1. End your database session.

    ```bash
    quit
    ```

### Test the Services

Invoke the services to populate the database with `Y7G4Entry` data.

1. Visit DXP in your browser at `http://localhost:8080`.

1. Sign in using the default credentials:

    **User Name:** `test@liferay.com`

    **Password:** `test`

1. Navigate to the Script console at *Control Panel* &rarr; *Server Administration* &rarr; *Script*.

1. Add an entry by executing the following script.

    ```groovy
    import com.acme.y7g4.service.Y7G4EntryLocalServiceUtil;

    import com.liferay.portal.kernel.dao.orm.QueryUtil;

    entry = Y7G4EntryLocalServiceUtil.createY7G4Entry(1234);

    entry.setName("Mop floors");
    entry.setDescription("Mop the kitchen and bathroom floors with soap and water.");

    Y7G4EntryLocalServiceUtil.addY7G4Entry(entry);

    entries = Y7G4EntryLocalServiceUtil.getY7G4Entries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

    for (entry in entries){
        out.println(entry);
    }
    ```

    Output:

    ```
    {y7g4EntryId=1234, description=Mop the kitchen and bathroom floors with soap and water., name=Mop floors}
    ```

    The newly added Y7G4Entry is printed in JSON format.

Here's what the script did:

1. Imported the generated static utility class `Y7G4EntryLocalServiceUtil`.
1. Created a `Y7G4Entry` instance with the ID (`long`) `1234`.
1. Populated the `Y7G4Entry` instance's `name` and `description` attributes.
1. Added the `Y7G4Entry` to the database.
1. Got all the `Y7G4Entry` instances from the database and printed them.

## What's Next

Now that you know how to define a model and generate persistence code and service code for it, you should examine the generated service classes. Continue with [Understanding and Extending Generated Classes](./understanding-service-builder-generated-classes.md).

## Additional Information

* [Invoking a Service Locally](./invoking-a-service-locally.md)
* [What is Liferay Workspace](../../../tooling/liferay-workspace/what-is-liferay-workspace.md)
