# Batch Object APIs

When you publish an Object, Liferay automatically generates REST APIs for it. These include the batch APIs for bulk POST, PUT, and DELETE operations. Here you'll use cURL commands and Java classes to call these batch APIs for a custom Object.

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

This tutorial provides sample cURL and Java code to demonstrate batch Object APIs.

To download and unzip this code, run the following command:

```bash
curl https://learn.liferay.com/dxp/latest/en/building-applications/objects/objects-tutorials/using-apis/liferay-t4r3.zip -O
```

```bash
unzip liferay-t4r3.zip
```

While the cURL scripts come ready for use, you must manually compile the Java source files before you can run them. To do this, go to the project's `java` folder, and run the `javac` command.

```bash
cd liferay-t4r3/java
```

```bash
javac -classpath .:* *.java
```

The sample code includes the following APIs:

| HTTP Method | HTTP Endpoint | Java Method | Description |
| :--- | :--- | :--- | :--- |
| DELETE | `/batch` | `deleteObjectNameBatch` | Deletes multiple Object entries |
| POST | `/batch` | `postObjectNameBatch` | Creates multiple Object entries using the details provided in the API call |
| PUT | `/batch` | `putObjectNameBatch` | Replaces multiple Object entries using the details provided in the API call |
| GET | `/` | `getCustomObjectPage` | Returns a complete list of Object entries in a Liferay instance; results can be paginated, filtered, searched, and sorted |

```{note}
The GET method is included for demonstration purposes.
```

## Calling the Custom Object APIs

For this exercise, you can use either the cURL commands or Java classes to call the Custom Object's APIs. The following output examples correspond to the cURL command, which slightly differs from the output for the provided Java classes.

1. Navigate to the `curl` or `java` folder in the `liferay-t4r3` project.

   **For cURL:**

   ```bash
   cd liferay-t4r3/curl
   ```

   **For Java:**

   ```bash
   cd liferay-t4r3/java
   ```

1. Execute the `CustomObject_POST_Batch`. This creates multiple Object entries.

   **For cURL:**

   ```bash
   ./CustomObject_POST_Batch.sh
   ```

   <!--
   **For Java:**

    ```bash
   java -classpath .:* CustomObject_POST_Batch
   ``` 
   -->

   The terminal should display a similar output.

   ```bash
   {
     "className" : "com.liferay.object.rest.dto.v1_0.ObjectEntry",
     "contentType" : "JSON",
     "errorMessage" : "",
     "executeStatus" : "INITIAL",
     "externalReferenceCode" : "",
     "failedItems" : [ ],
     "id" : 4,
     "importStrategy" : "ON_ERROR_FAIL",
     "operation" : "CREATE",
     "processedItemsCount" : 0,
     "startTime" : "2022-04-07T22:51:37Z",
     "totalItemsCount" : 0
   }

   ```

1. Run `CustomObjects_GET_FromCompany` to verify the entries were created. This returns a list of all Object entries.

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

   Copy each entry's ID for use with the following PUT and DELETE methods.

   ```bash
   {
     ...
     "items" : [ {
       ...
       "id" : 41985,
       ...
       "name" : "Able"
     }, {
       ...
       "id" : 41987,
       ...
       "name" : "Baker"
     }, {
       ...
       "id" : 41989,
       ...
       "name" : "Charlie"
     } ],
     "lastPage" : 1,
     "page" : 1,
     "pageSize" : 20,
     "totalCount" : 3
   }
   ```

1. Execute `CustomObject_PUT_Batch` using the entry ID for its parameter. This replaces the details of the specified entry with the details provided in the API call.

   **For cURL:**

   ```bash
   ./CustomObject_PUT_Batch.sh {entry-id}
   ```

   <!-- 
   **For Java:**

   ```bash
   java -classpath .:* -DentryId={entry-id} CustomObject_PUT_Batch
   ```
   -->

   ```bash
   {
     "className" : "com.liferay.object.rest.dto.v1_0.ObjectEntry",
     "contentType" : "JSON",
     "errorMessage" : "",
     "executeStatus" : "INITIAL",
     "externalReferenceCode" : "",
     "failedItems" : [ ],
     "id" : 6,
     "importStrategy" : "ON_ERROR_FAIL",
     "operation" : "UPDATE",
     "processedItemsCount" : 0,
     "startTime" : "2022-04-07T23:02:17Z",
     "totalItemsCount" : 0
   }
   ```

1. Run `CustomObjects_GET_FromCompany` to verify the entries were updated.

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

   ```bash
   {
     ...
     "items" : [ {
       ...
       "id" : 41985,
       ...
       "name" : "Dog"
     }, {
       ...
       "id" : 41987,
       ...
       "name" : "Easy"
     }, {
       ...
       "id" : 41989,
       ...
       "name" : "Fox"
     } ],
     "lastPage" : 1,
     "page" : 1,
     "pageSize" : 20,
     "totalCount" : 3
   }
   ```

1. Execute `CustomObject_DELETE_Batch` using the entry ID for its parameter. This deletes the specified entry.<!--CONFIRM HOW THESE WORK!!!!!-->

   **For cURL:**

   ```bash
   ./CustomObject_DELETE_Batch.sh {entry-id}
   ```

   <!--
   **For Java:**

   ```bash
   java -classpath .:* -DentryId={entry-id} CustomObject_DELETE_Batch
   ```
   -->

   ```bash
   ```

1. Run `CustomObjects_GET_FromCompany` to verify the entries were deleted.

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

## Examining the Sample cURL Scripts

### `CustomObject_POST_Batch.sh`

```{literalinclude} ./batch-object-apis/resources/liferay-t4r3.zip/curl/CustomObject_POST_Batch.sh
   :language: bash
```

### `CustomObject_PUT_Batch.sh`

```{literalinclude} ./batch-object-apis/resources/liferay-t4r3.zip/curl/CustomObject_PUT_Batch.sh
   :language: bash
```

### `CustomObject_DELETE_Batch.sh`

```{literalinclude} ./batch-object-apis/resources/liferay-t4r3.zip/curl/CustomObject_DELETE_Batch.sh
   :language: bash
```

## Examining the Sample Java Classes

### `CustomObject_POST_Batch.java`

```{literalinclude} ./batch-object-apis/resources/liferay-t4r3.zip/java/CustomObject_POST_Batch.java
   :language: bash
```

### `CustomObject_PUT_Batch.java`

```{literalinclude} ./batch-object-apis/resources/liferay-t4r3.zip/java/CustomObject_PUT_Batch.java
   :language: bash
```

### `CustomObject_DELETE_Batch.java`

```{literalinclude} ./batch-object-apis/resources/liferay-t4r3.zip/java/CustomObject_DELETE_Batch.java
   :language: bash
```

## Additional Information

* []()
* []()
* []()
