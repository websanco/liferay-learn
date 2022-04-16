# Using Nested Fields with Objects

When you publish an Object, Liferay automatically generates REST APIs for it. When calling these APIs, you can use nested fields to run complex queries involving multiple levels of Object relationships. This means you can use the `nestedFields` parameter with GET calls to return multiple levels of related Objects. You can also use the `nestedFieldsDepth` parameter to determine the depth of Object entries included in the query: `0-5`.

Here you'll use cURL scripts to call Object APIs with nested fields. Before proceeding, set up a new Liferay DXP/Portal 7.4 instance and download the provided tutorial code.

```{tip}
For a complete list of APIs generated for Site and Company Objects, see [Object's Headless Framework Integration](../../understanding-object-integrations/headless-framework-integration.md). You can also view and test an Object's APIs via the Liferay API Explorer at `[server]:[port]/o/api` (e.g., `localhost:8080/o/api`). REST APIs are listed under *REST Applications*.
```

## Setting Up a Liferay Instance

1. Run this command to start a DXP/Portal 7.4+ container:

   ```docker
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
   ```

1. Once the container has started, log in and [create](../../creating-and-managing-objects/creating-objects.md) three Object drafts.

   First Object:

      | Field | Value |
      | :--- | :--- |
      | Label | `Able` |
      | Plural Label | `Ables` |
      | Name | `Able` |

   Second Object:

      | Field | Value |
      | :--- | :--- |
      | Label | `Baker` |
      | Plural Label | `Bakers` |
      | Name | `Baker` |

   Third Object:

      | Field | Value |
      | :--- | :--- |
      | Label | `Charlie` |
      | Plural Label | `Charlies` |
      | Name | `Charlie` |

1. Add the following text field to each Object draft.

   | Label | Field Name | Type | Required |
   | :--- | :--- | :--- | :--- |
   | `Name` | `name` | Text | &#10004; |

1. Define the following relationships.

   For Able:

      | Label | Relationship Name | Type | Object |
      | :--- | :--- | :--- | :--- |
      | `Able to Baker` | `ableToBaker` | One to Many | Baker |

   For Baker:

      | Label | Relationship Name | Type | Object |
      | :--- | :--- | :--- | :--- |
      | `Baker to Charlie` | `bakerToCharlie` | One to Many | Charlie |

1. [Publish](../../creating-and-managing-objects/creating-objects.md#publishing-object-drafts) each Object.

Once published, you can access each Object via Headless APIs.

## Preparing the Sample Code

Run these commands to download and unzip the sample code:

```bash
curl https://learn.liferay.com/dxp/latest/en/building-applications/objects/objects-tutorials/using-apis/liferay-w4s7.zip -O
```

```bash
unzip liferay-w4s7.zip
```

The sample code includes POST commands for each Object, as well as a GET command for `Charlie`.

## Using the Sample Code

Follow these steps to add and query related Object entries:

1. Navigate to the `curl` folder in the `liferay-w4s7` project.

   ```bash
   cd liferay-p8n6/curl
   ```

1. Execute `Able_POST_ToCompany` to create `Able` entries.

   ```bash
   ./Able_POST_ToCompany.sh
   ```

   Copy the first entry's ID for use with the following POST command.

   ```bash
   {
     "id" : 41969,
     ...
     "name" : "Able 1"
   }

   {
     "id" : 41971,
     ...
     "name" : "Able 2"
   }

   {
     "id" : 41973,
     ...
     "name" : "Able 3"
   }
   ```

1. Execute `Baker_POST_ToCompany` using the `Able` entry ID as a parameter.

   ```bash
   ./Baker_POST_ToCompany.sh {able-entry-id}
   ```

   This creates `Baker` entries related to the specified `Able` entry. Copy the first `Baker` entry ID ID for use with the following POST command.

   ```bash
   {
     "id" : 41975,
     ...
     "name" : "Baker 1"
     "r_ableToBaker_c_ableId" : 41969
   }

   {
     "id" : 41977,
     ...
     "name" : "Baker 2"
     "r_ableToBaker_c_ableId" : 41969
   }

   {
     "id" : 41979,
     ...
     "name" : "Baker 3"
     "r_ableToBaker_c_ableId" : 41969
   }
   ```

1. Execute `Charlie_POST_ToCompany` using the `Baker` entry ID as a parameter.

   ```bash
   ./Charlie_POST_ToCompany.sh {baker-entry-id}
   ```

   This creates `Charlie` entries related to the preceding `Baker` entry. Copy the first  entry's ID for use with the following GET command.

   ```bash
   {
     "id" : 41981,
     ...
     "name" : "Charlie 1",
     "r_bakerToCharlie_c_bakerId" : 41975
   }

   {
     "id" : 41983,
     ...
     "name" : "Charlie 2",
     "r_bakerToCharlie_c_bakerId" : 41975
   }

   {
     "id" : 41985,
     ...
     "name" : "Charlie 3",
     "r_bakerToCharlie_c_bakerId" : 41975
   }
   ```

1. Execute `Charlie_GET_ById` using the `Charlie` entry ID as a parameter.

   ```bash
   ./Charlie_GET_ById.sh {charlie-entry-id}
   ```

   This queries the entry using nested fields and returns the schema for all three levels of the related Objects.

   ```bash
   {
     "r_bakerToCharlie_c_baker" : {
       ...
       "id" : 41975,
       ...
       "r_ableToBaker_c_able" : {
         ...
         "id" : 41969,
         ...
         "name" : "Able 1"
       },
       "name" : "Baker 1",
       "r_ableToBaker_c_ableId" : 41969
     },
     "name" : "Charlie 1",
     "r_bakerToCharlie_c_bakerId" : 41975
   }
   ```

## Examining the GET Script

```{literalinclude} ./using-nested-fields-with-objects/resources/liferay-w4s7.zip/curl/Charlie_GET_ById.sh
   :language: bash
```

The provided GET method calls a URL with the `nestedFields` and `nestedFieldsDepth` parameters.

* `nestedFields`: Determines the types of entries included in the query (e.g., `able,baker`).
* `nestedFieldsDepth`: Determines the depth of entries you want to include and can be set between 0-5.

## Additional Information

* [Headless Framework Integration](../../understanding-object-integrations/headless-framework-integration.md)
* [Object API Basics](./object-api-basics.md)
* [Batch Object APIs](./batch-object-apis.md)
