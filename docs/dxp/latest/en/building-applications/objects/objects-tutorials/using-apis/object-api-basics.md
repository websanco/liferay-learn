# Object API Basics

When you publish an Object, Liferay automatically generates REST APIs for it. These APIs differ for Company and Site scoped Objects, but they all use the `c/[pluralobjectlabel]` naming pattern (e.g., `c/timeoffrequests`). You can use these APIs to create, access, update, and remove Object entries.

Here you'll use cURL commands and Java classes to perform basic CRUD operations for a custom Object, creating, reading, updating, and deleting Object entries.

Before proceeding, set up a new Liferay DXP/Portal 7.4 instance and download the provided `.sh` and `.java` tutorial code.

```{tip}
For a complete list of APIs generated for Site and Company Objects, see [Object's Headless Framework Integration](../../understanding-object-integrations/headless-framework-integration.md). You can also view and test an Object's APIs via the Liferay API Explorer at `[server]:[port]/o/api` (e.g., `localhost:8080/o/api`). REST APIs are listed under *REST Applications*.
```

## Setting Up a Liferay Instance

Run this command to start a DXP/Portal 7.4+ container:

```docker
docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
```

Once the container has started, log in and follow these steps to [create](../../creating-and-managing-objects/creating-objects.md) a basic Object for this tutorial:

1. Open the *Global Menu* (![Global Menu](../../../../images/icon-applications-menu.png)), click the *Control Panel* tab, and go to *Objects*.

1. Click the *Add* button (![Add Button](../../../../images/icon-add.png)) and enter these values:

   | Field | Value |
   | :--- | :--- |
   | Label | `Custom Object` |
   | Plural Label | `Custom Objects` |
   | Name | `CustomObject` |

1. Select the new *Object* draft, click on the *Field* tab, and add a single text *field*:

   | Label | Field Name | Type | Required |
   | :--- | :--- | :--- | :--- |
   | Name | name | Text | &#10004; |

1. Click on the *Details* tab and click *Publish*.

   ```{important}
   You must use the above values since they're used in the sample code.
   ```

[Publishing an Object](../../creating-and-managing-objects/creating-objects.md#publishing-object-drafts) creates and activates a new application for receiving and storing data. You can now access it via Headless APIs.

## Preparing the Sample Code

This tutorial provides sample cURL and Java code to demonstrate basic Object APIs.

To download and unzip this code, run the following command:

```bash
curl https://learn.liferay.com/dxp/latest/en/building-applications/objects/objects-tutorials/using-apis/liferay-v1s4.zip -O
```

```bash
unzip liferay-v1s4.zip
```

While the cURL scripts come ready for use, you must manually compile the Java source files before you can run them. To do this, go to the project's `java` folder, and run the `javac` command.

```bash
cd liferay-v1s4/java
```

```bash
javac -classpath .:* *.java
```

The sample code includes the following APIs:

| HTTP Method | HTTP Endpoint | Java Method | Description |
| :--- | :--- | :--- | :--- |
| GET | `/` | `getCustomObjectPage` | Returns a complete list of Object entries in a Liferay instance; results can be paginated, filtered, searched, and sorted |
| POST | `/` | `postObjectName` | Creates a new Object entry using the details provided in the API call |
| DELETE | `/{objectNameId}` | `deleteObjectName` | Deletes the specified Object entry and returns a 204 if the operation succeeds |
| GET | `/{objectNameId}` | `getObjectName` | Returns details for the specified Object entry |
| PATCH | `/{objectNameId}` | `patchObjectName` | Updates the fields specified in the API call for the specified Object entry; other fields remain unchanged |
| PUT | `/{objectNameId}` | `putObjectName` | Replaces the specified Object entry's details with those provided in the API call |

## Calling the Custom Object APIs

For this exercise, you can use either the cURL commands or Java classes to call the Custom Object's APIs. The following output examples correspond to the cURL command, which slightly differs from the output for the provided Java classes.

1. Navigate to the `curl` or `java` folder in the `liferay-v1s4` project.

   **For cURL:**

   ```bash
   cd liferay-v1s4/curl
   ```

   **For Java:**

   ```bash
   cd liferay-v1s4/java
   ```

1. Execute `CustomObject_POST_ToCompany`. This creates an Object entry.

   **For cURL:**

   ```bash
   ./CustomObject_POST_ToCompany.sh
   ```

   <!--
   **For Java:**

    ```bash
   java -classpath .:* CustomObject_POST_ToCompany
   ``` 
   -->

   The terminal displays the complete schema for the newly created entry. The provided API calls only include the `name` field, though you should copy the entry's ID for use with the following GET, PUT, and DELETE methods.

   ```bash
   {
     "id" : 41969,
     ...
     "name" : "Foo"
   }
   ```

1. Execute `CustomObjects_GET_FromCompany`. This returns a list of all Object entries.

   **For cURL:**

   ```bash
   ./CustomObjects_GET_FromCompany.sh
   ```

   <!-- 
   **For Java:**

   ```bash
   java -classpath .:* CustomObjects_GET_FromCompany
   ```
   -->

1. Execute `CustomObject_PUT_ById` using the entry ID for its parameter. This replaces the details of the specified entry with the details provided in the API call.

   **For cURL:**

   ```bash
   ./CustomObject_PUT_ById.sh {entry-id}
   ```

   <!-- 
   **For Java:**

   ```bash
   java -classpath .:* -DentryId={entry-id} CustomObject_PUT_ById
   ```
   -->

   ```bash
   {
     "id" : 41969,
     ...
     "name" : "Bar"
   }
   ```

1. Execute `CustomObject_DELETE_ById` using the entry ID for its parameter. This deletes the specified entry.

   **For cURL:**

   ```bash
   ./CustomObject_DELETE_ById.sh {entry-id}
   ```

   <!--
   **For Java:**

   ```bash
   java -classpath .:* -DentryId={entry-id} CustomObject_DELETE_ById
   ```
   -->

1. Execute `CustomObject_GET_ById` using the previous entry ID for its parameter. This returns the details for the specified entry if it exists.

   **For cURL:**

   ```bash
   ./CustomObject_GET_ById.sh {entry-id}
   ```

   <!--
   **For Java:**

   ```bash
   java -classpath .:* -DentryId={entry-id} CustomObject_GET_ById
   ```
   -->

   Since you deleted the entry in the preceding step, it returns the following message.

   ```bash
   {
     "status" : "NOT_FOUND",
     "title" : "No ObjectEntry exists with the primary key 41980"
   }
   ```

## Examining the Sample cURL Scripts

The following are examples of the tutorial's cURL commands.

### `CustomObject_POST_ToCompany.sh`

```{literalinclude} ./object-api-basics/resources/liferay-v1s4.zip/curl/CustomObject_POST_ToCompany.sh
   :language: bash
```

### `CustomObject_PUT_ById.sh`

```{literalinclude} ./object-api-basics/resources/liferay-v1s4.zip/curl/CustomObject_PUT_ById.sh
   :language: bash
```

## Examining the Sample Java Classes

The following are examples of the tutorial's Java commands.

### `CustomObject_POST_ToCompany.java`

```{literalinclude} ./object-api-basics/resources/liferay-v1s4.zip/java/CustomObject_POST_ToCompany.java
   :language: bash
```

### `CustomObject_PUT_ById.java`

```{literalinclude} ./object-api-basics/resources/liferay-v1s4.zip/java/CustomObject_PUT_ById.java
   :language: bash
```

## Additional Information

* [Headless Framework Integration](../../understanding-object-integrations/headless-framework-integration.md)
* [Batch Object APIs](./batch-object-apis.md)
* [Using Nested Fields with Objects](./using-nested-fields-with-objects.md)
