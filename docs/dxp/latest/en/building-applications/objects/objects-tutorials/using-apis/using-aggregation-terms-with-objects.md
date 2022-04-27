# Using Aggregation Terms with Objects

When querying Object entries using REST APIs, you can use an Object's fields as facet criteria for aggregating entry data. To do this, add the `aggregationTerms` parameter to your GET request and specify the data fields you want to use as facet criteria. These criteria can include custom fields, default metadata fields, or relationship fields. The request response then groups the specified data facets into a single `facets` block.

Here you'll use the `aggregatedTerms` parameter with a basic custom Object.

Before proceeding, [set up](#setting-up-a-liferay-instance) a new Liferay DXP/Portal 7.4 instance and [prepare](#preparing-the-sample-code) the provided tutorial code.

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

1. Select the new *Object* draft, go to the *Fields* tab, and add the following text fields:

   | Label | Field Name | Type | Required |
   | :--- | :--- | :--- | :--- |
   | Name | name | Text | &#10004; |
   | Description | description | Text |  |

1. Go to the *Details* tab and click [*Publish*](../../creating-and-managing-objects/creating-objects.md#publishing-object-drafts).

   ```{important}
   For this tutorial, you must use the above values.
   ```

Once published, you can access the Object via Headless APIs.

## Preparing the Sample Code

Run the following commands to download and unzip the provided sample code:

```bash
curl https://learn.liferay.com/dxp/latest/en/building-applications/objects/objects-tutorials/using-apis/liferay-b3x5.zip -O
```

```bash
unzip liferay-b3x5.zip
```

These scripts include the following APIs:

| HTTP Method | HTTP Endpoint | Description |
| :--- | :--- | :--- |
| GET | `/` | Returns a complete list of Object entries in a Liferay instance; results can be paginated, filtered, searched, and sorted |
| POST | `/batch` | Creates multiple Object entries using the details provided in the API call |

## Using the Sample Code

1. After downloading the sample code, navigate to the `curl` folder in the `liferay-b3x5` project.

   ```bash
   cd liferay-b3x5/curl
   ```

1. Execute `Ables_POST_Batch`. This creates multiple Object entries.

   ```bash
   ./Ables_POST_Batch.sh
   ```

   The terminal should display a similar output.

   ```bash
   {
     "className" : "com.liferay.object.rest.dto.v1_0.ObjectEntry",
     "contentType" : "JSON",
     "errorMessage" : "",
     "executeStatus" : "STARTED",
     "externalReferenceCode" : "",
     "failedItems" : [ ],
     "id" : 1,
     "importStrategy" : "ON_ERROR_FAIL",
     "operation" : "CREATE",
     "processedItemsCount" : 0,
     "startTime" : "2022-04-27T00:18:23Z",
     "totalItemsCount" : 0
   }
   ```

1. Run `Ables_GET_FromCompany` to return a list of all created entries with the `aggregatedTerms` parameter.

   The response should include a `facets` block with two facet criteria, `dateModified` and `description`.

   ```bash
   ...
   "facets" : [ {
       "facetCriteria" : "dateModified",
       "facetValues" : [ {
         "numberOfOccurrences" : 3,
         "term" : "20220427001823"
       } ]
     }, {
       "facetCriteria" : "description",
       "facetValues" : [ {
         "numberOfOccurrences" : 1,
         "term" : "bar"
       }, {
         "numberOfOccurrences" : 1,
         "term" : "foo"
       }, {
         "numberOfOccurrences" : 1,
         "term" : "goo"
       } ]
   } ],
   ...
   ```

   This block is followed by the standard GET response.

   ```bash
   {
     ...
     "id" : 41969,
     ...
     "name" : "Able 1",
     "description" : "Foo"
     ...
   }, {
     ...
     "id" : 41971,
     ...
     "name" : "Able 2",
     "description" : "Bar"
   }, {
     ...
     "id" : 41973,
     ...
     "name" : "Able 3",
     "description" : "Goo"
   }
   ...
   ```

## Examining the Code

```{literalinclude} ./using-aggregation-terms-with-objects/resources/liferay-b3x5.zip/curl/Ables_GET_FromCompany.sh
   :language: bash
```

This batch GET method includes the `aggregationTerms` URL parameter with two values: `dateModified` and `description`. These determine the facet criteria used for aggregating data from Object entries in the request response.

## Additional Information

* [Object API Basics](./object-api-basics.md)
* [Batch Object APIs](./batch-object-apis.md)
* [Using Nested Fields with Objects](./using-nested-fields-with-objects.md)
