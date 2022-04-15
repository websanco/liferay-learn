# Batch Object APIs

When you publish an Object, Liferay automatically generates REST APIs for it. These include the batch APIs for bulk POST, PUT, and DELETE operations. Here you'll use cURL commands to call these batch APIs for a custom Object.

Before proceeding, set up a new Liferay DXP/Portal 7.4 instance and download the provided tutorial code.

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
   | Label | `Able` |
   | Plural Label | `Ables` |
   | Name | `Able` |

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

Run this commands to download and unzip the sample cURL scripts:

```bash
curl https://learn.liferay.com/dxp/latest/en/building-applications/objects/objects-tutorials/using-apis/liferay-t4r3.zip -O
```

```bash
unzip liferay-t4r3.zip
```

The provided scripts include the following batch APIs:

| HTTP Method | HTTP Endpoint | Description |
| :--- | :--- | :--- |
| DELETE | `/batch` | Deletes multiple Object entries |
| POST | `/batch` | Creates multiple Object entries using the details provided in the API call |
| PUT | `/batch` | Replaces multiple Object entries using the details provided in the API call |

```{note}
The GET method is also included for demonstration purposes. This returns a complete list of Object entries in a Liferay instance.
```

## Calling the Custom Object's APIs

1. After downloading the sample code, navigate to the `curl` folder in the `liferay-t4r3` project.

   ```bash
   cd liferay-t4r3/curl
   ```

1. Execute `Ables_POST_Batch`. This creates multiple Object entries.

   **For cURL:**

   ```bash
   ./Ables_POST_Batch.sh
   ```

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

1. Run `Ables_GET_FromCompany` to verify the entries were created. This returns a list of all Object entries.

   ```bash
   ./Ables_GET_FromCompany.sh
   ```

   Copy each entry's ID for use with the following PUT and DELETE methods.

   ```bash
   {
     ...
     "items" : [ {
       ...
       "id" : 41985,
       ...
       "name" : "Able 1"
     }, {
       ...
       "id" : 41987,
       ...
       "name" : "Able 2"
     }, {
       ...
       "id" : 41989,
       ...
       "name" : "Able 3"
     } ],
     "lastPage" : 1,
     "page" : 1,
     "pageSize" : 20,
     "totalCount" : 3
   }
   ```

1. Execute `Ables_PUT_Batch` with each entry ID as a parameter. This replaces the details of the specified entry with the details provided in the API call.

   ```bash
   ./Ables_PUT_Batch.sh {first-entry-id} {second-entry-id} {third-entry-id}
   ```

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

1. Run `Ables_GET_FromCompany` to verify the entries were updated.

   ```bash
   ./Ables_GET_FromCompany.sh
   ```

   ```bash
   {
     ...
     "items" : [ {
       ...
       "id" : 41985,
       ...
       "name" : "Able 4"
     }, {
       ...
       "id" : 41987,
       ...
       "name" : "Able 5"
     }, {
       ...
       "id" : 41989,
       ...
       "name" : "Able 6"
     } ],
     "lastPage" : 1,
     "page" : 1,
     "pageSize" : 20,
     "totalCount" : 3
   }
   ```

1. Execute `Ables_DELETE_Batch` with each entry ID as a parameter. This deletes the specified entries.

   ```bash
   ./Ables_DELETE_Batch.sh {first-entry-id} {second-entry-id} {third-entry-id}
   ```

1. Run `Ables_GET_FromCompany` to verify the entries were deleted.

   ```bash
   ./Ables_GET_FromCompany.sh
   ```

   Since you deleted the entries in the preceding step, it should return an entries `NOT FOUND` error.

## Examining the Sample cURL Scripts

### `Ables_POST_Batch.sh`

```{literalinclude} ./batch-object-apis/resources/liferay-t4r3.zip/curl/Ables_POST_Batch.sh
   :language: bash
```

### `Ables_PUT_Batch.sh`

```{literalinclude} ./batch-object-apis/resources/liferay-t4r3.zip/curl/Ables_PUT_Batch.sh
   :language: bash
```

### `Ables_DELETE_Batch.sh`

```{literalinclude} ./batch-object-apis/resources/liferay-t4r3.zip/curl/Ables_DELETE_Batch.sh
   :language: bash
```

## Additional Information

* [Headless Framework Integration](../../understanding-object-integrations/headless-framework-integration.md)
* [Object API Basics](./object-api-basics.md)
* [Using Nested Fields with Objects](./using-nested-fields-with-object.md)
