# Organizations API Basics

You can [Create and Manage Organizations](../organizations/creating-and-managing-organizations.md) from the Application menu, but you can also use Liferay's REST APIs. Call these services to manage organizations.

## Adding an Organization

```{include} /_snippets/run-liferay-dxp.md
```

Then, follow these steps:

1. Download and unzip [Organizations API Basics](./liferay-w2h3.zip).

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/users-and-permissions/developer-guide/liferay-w2h3.zip -O
   ```

   ```bash
   unzip liferay-w2h3.zip
   ```

1. Use the cURL script to add a new Organization to your instance. On the command line, navigate to the `curl` folder. Execute the `Organization_POST_ToInstance.sh` script.

   ```bash
   ./Organization_POST_ToInstance.sh
   ```

   The JSON response shows a new Organization has been added:

   ```bash
   "comment" : "",
   "customFields" : [ ],
   "dateCreated" : "2022-05-19T17:38:19Z",
   "dateModified" : "2022-05-19T17:38:19Z",
   "externalReferenceCode" : "",
   "id" : "40922",
   "keywords" : [ ],
   "location" : { },
   "name" : "Able",
   "numberOfAccounts" : 0,
   "numberOfOrganizations" : 0,
   "numberOfUsers" : 0,
   "organizationContactInformation" : {
      "emailAddresses" : [ ],
      "postalAddresses" : [ ],
      "telephones" : [ ],
      "webUrls" : [ ]

   ```

1. Navigate to *Global Menu* &rarr; *Control Panel* &rarr; *User and Organizations*. Click the *Organizations* tab. See that a new Organization has been added.

   ![See that a new organization has been added.](./organizations-api-basics/images/01.png)

1. The REST service can also be called using the Java client. Navigate out of the `curl` folder and into the `java` folder. Compile the source files with the following command:

   ```bash
   javac -classpath .:* *.java
   ```

1. Run the `Organization_POST_ToInstance.java` class with the following command.

   ```bash
   java -classpath .:* Organization_POST_ToInstance
   ```

## Examine the cURL Command

The `Organization_POST_ToInstance.sh` script calls the REST service with a cURL command.

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/curl/Organization_POST_ToInstance.sh
    :language: bash
```

Here are the command's arguments:

| Arguments | Description |
| :--- | :--- |
| `-H "Content-Type: application/json"` | Indicates that the request body format is JSON. |
| `-X POST` | The HTTP method to invoke at the specified endpoint |
| `"http://localhost:8080/o/headless-admin-user/v1.0/organizations"` | The REST service endpoint |
| `-d "{\"name\": \"Able\"}"` | The data you are requesting to post |
| `-u "test@liferay.com:learn"` | Basic authentication credentials |

```{note}
Basic authentication is used here for demonstration purposes. For production, you should authorize users via [OAuth2](../../headless-delivery/using-oauth2.md).
```

The other cURL commands use similar JSON arguments.

## Examine the Java Class

The `Organization_POST_ToInstance.java` class adds an organization by calling the Organization-related service.

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/java/Organization_POST_ToInstance.java
   :dedent: 1
   :language: java
   :lines: 9-24
```

This class invokes the REST service using only three lines of code:

| Line (abbreviated) | Description |
| :----------------- | :---------- |
| `OrganizationResource.Builder builder = ...` | Gets a `Builder` for generating an `OrganizationResource` service instance. |
| `OrganizationResource organizationResource = builder.authentication(...).build();` | Specifies basic authentication and generates a `OrganizationResource` service instance. |
| `Organization organization = organizationResource.postOrganization(...);` | Calls the `organizationResource.postOrganization` method and passes the data to post. |

Note that the project includes the `com.liferay.headless.admin.user.client.jar` file as a dependency. You can find client JAR dependency information for all REST applications in the API explorer in your installation at `/o/api`.

```{note}
The `main` method's comment demonstrates running the class.
```

The other example Java classes are similar to this one, but call different `OrganizationResource` methods.

```{important}
See [OrganizationResource](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/headless/headless-admin-user-client/src/main/java/com/liferay/headless/admin/user/client/resource/v1_0/OrganizationResource.java) for service details.
```

Below are examples of calling other `Organization` REST services using cURL and Java.

## Get Organizations from Instance

You can list Organizations by executing the following cURL or Java command.

### Organizations_GET_FromInstance.sh

Command:

```bash
./Organizations_GET_FromInstance.sh
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/curl/Organizations_GET_FromInstance.sh
   :language: bash
```

### Organizations_GET_FromInstance.java

Command:

```bash
java -classpath .:* Organizations_GET_FromInstance
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/java/Organizations_GET_FromInstance.java
   :dedent: 1
   :language: java
   :lines: 11-22
```

The Instance's `Organization` objects appear in JSON.

## Get an Organization

Get a specific Organization with the following cURL or Java command.

```{tip}
Use ``Organizations_GET_FromInstance.[java|sh]`` to get instance ``Organization`` IDs.
```

### Organization_GET_ById.sh

Command:

```bash
./Organization_GET_ById.sh 1234
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/curl/Organization_GET_ById.sh
   :language: bash
```

### Organization_GET_ById.java

Command:

```bash
java -classpath .:* -DorganizationId=1234 Organization_GET_ById
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/java/Organization_GET_ById.java
   :dedent: 1
   :language: java
   :lines: 9-20
```

The `Organization` fields appear in JSON.

## Patch an Organization

Do a partial edit of an existing Organization with the following cURL and Java commands. Replace `1234` with your Organization's ID.

### Organization_PATCH_ById.sh

Command:

```bash
./Organization_PATCH_ById.sh 1234
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/curl/Organization_PATCH_ById.sh
   :language: bash
```

### Organization_PATCH_ById.java

Command:

```bash
java -classpath .:* -DorganizationId=1234 Organization_PATCH_ById
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/java/Organization_PATCH_ById.java
   :dedent: 1
   :language: java
   :lines: 9-25
```

## Put an Organization

Completely overwrite an existing Organization with the following cURL and Java commands. Replace `1234` with your Organization's ID.

### Organization_PUT_ById.sh

Command:

```bash
./Organization_PUT_ById.sh 1234
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/curl/Organization_PUT_ById.sh
   :language: bash
```

### Organization_PUT_ById.java

Command:

```bash
java -classpath .:* -DorganizationId=1234 Organization_PUT_ById
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/java/Organization_PUT_ById.java
   :dedent: 1
   :language: java
   :lines: 9-25
```

## Delete an Organization

Delete an existing Organization with the following cURL and Java commands. Replace `1234` with your Organization's ID.

### Organization_DELETE_ById.sh

Command:

```bash
./Organization_DELETE_ById.sh 1234
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/curl/Organization_DELETE_ById.sh
   :language: bash
```

### Organization_DELETE_ById.java

Command

```bash
java -classpath .:* -DorganizationId=1234 Organization_DELETE_ById
```

Code:

```{literalinclude} ./organizations-api-basics/resources/liferay-w2h3.zip/java/Organization_DELETE_ById.java
   :dedent: 1
   :language: java
   :lines: 8-17
```

The [API Explorer](../../headless-delivery/consuming-apis/consuming-rest-services.md) shows all of the `Account` services and schemas and has an interface to try out each service.
