# Using Nested Fields with Objects

When you publish an Object, Liferay automatically generates REST APIs for it. Beginning with Liferay 7.4 U_/GA_, <!--FINISH--> you can use nested fields with Object APIs to run complex queries involving multiple levels of Object relationships. Here you'll use cURL commands and Java classes to call Object APIs with nested fields.

Before proceeding, set up a new Liferay DXP/Portal 7.4 instance and download the provided `.sh` and `.java` tutorial code.

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
      | Label | `Able Object` |
      | Plural Label | `Able Objects` |
      | Name | `AbleObject` |

   Second Object:

      | Field | Value |
      | :--- | :--- |
      | Label | `Baker Object` |
      | Plural Label | `Baker Objects` |
      | Name | `BakerObject` |

   Third Object:

      | Field | Value |
      | :--- | :--- |
      | Label | `Charlie Object` |
      | Plural Label | `Charlie Objects` |
      | Name | `CharlieObject` |

1. Add the following text field to each Object draft.

   | Label | Field Name | Type | Required |
   | :--- | :--- | :--- | :--- |
   | Name | name | Text | &#10004; |

1. Define the following relationships.

   For Able Object:

      | Label | Relationship Name | Type | Object |
      | :--- | :--- | :--- | :--- |
      | Able to Baker | ableToBaker | One to Many | BakerObject |

   For Baker Object:

      | Label | Relationship Name | Type | Object |
      | :--- | :--- | :--- | :--- |
      | Baker to Charlie | bakerToCharlie | One to Many | CharlieObject |

1. [Publish](../../creating-and-managing-objects/creating-objects.md#publishing-object-drafts) each Object.

Once published, you can access each Object via Headless APIs.

## Preparing the Sample Code

This tutorial provides sample cURL and Java code to demonstrate using nested fields with Object APIs.

To download and unzip this code, run the following command:

```bash
curl https://learn.liferay.com/dxp/latest/en/building-applications/objects/objects-tutorials/using-apis/liferay-w4s7.zip -O
```

```bash
unzip liferay-w4s7.zip
```

While the cURL scripts come ready for use, you must manually compile the Java source files before you can run them. To do this, go to the project's `java` folder, and run the `javac` command.

```bash
cd liferay-w4s7/java
```

```bash
javac -classpath .:* *.java
```

The sample code includes POST commands for the three options as well as a GET command for CharlieObject

## Adding Related Object Entries

Follow these steps to add related entries to each Object:

1. Navigate to the `curl` or `java` folder in the `liferay-w4s7` project.

   **For cURL:**

   ```bash
   cd liferay-p8n6/curl
   ```

   **For Java:**

   ```bash
   cd liferay-w4s7/java
   ```

1. Execute `AbleObject_POST_ToCompany` to create an AbleObject entry.

   **For cURL:**

   ```bash
   ./AbleObject_POST_ToCompany.sh
   ```

   <!--
   **For Java:**

    ```bash
   java -classpath .:* AbleObject_POST_ToCompany
   ``` 
   -->

   Copy the entry's ID for use with the following POST command.

   ```bash
   {
     "id" : 41969,
     ...
     "name" : "Foo"
   }
   ```

1. Execute `BakerObject_POST_ToCompany` using the AbleObject entry ID as a parameter.

   **For cURL:**

   ```bash
   ./BakerObject_POST_ToCompany.sh {able-object-entry-id}
   ```

   <!--
   **For Java:**

    ```bash
   java -classpath .:* BakerObject_POST_ToCompany
   ``` 
   -->

   This creates a BakerObject entry related to the preceding AbleObject entry. Copy this entry's ID for use with the following POST command.

   ```bash
   {
     "id" : 41971,
     ...
     "name" : "Bar"
     "r_ableToBaker_c_ableObjectId" : 41969
   }
   ```

1. Execute `CharlieObject_POST_ToCompany` using the BakerObject entry ID as a parameter.

   **For cURL:**

   ```bash
   ./CharlieObject_POST_ToCompany.sh {baker-object-entry-id}
   ```

   <!--
   **For Java:**

    ```bash
   java -classpath .:* CharlieObject_POST_ToCompany
   ``` 
   -->

   This creates a CharlieObject entry related to the preceding BakerObject entry. Copy this entry's ID for use with the following GET command.

   ```bash
   {
     "id" : 41973,
     ...
     "name" : "Goo",
     "r_bakerToCharlie_c_bakerObjectId" : 41971
   }
   ```

1. Execute `CharlieObject_GET_ById` using the CharlieObject entry ID as a parameter.

   **For cURL:**

   ```bash
   ./CharlieObject_GET_ById.sh {charlie-object-entry-id}
   ```

   <!--
   **For Java:**

    ```bash
   java -classpath .:* CharlieObject_GET_ById
   ``` 
   -->

   This queries the entry using nested fields and returns the schema for all three Object entry levels (i.e., 0-2).

   ```bash
   {
     "r_bakerToCharlie_c_bakerObject" : {
       ...
       "id" : 41971,
       ...
       "r_ableToBaker_c_ableObject" : {
         ...
         "id" : 41969,
         ...
         "name" : "Foo"
       },
       "name" : "Bar",
       "r_ableToBaker_c_ableObjectId" : 41969
     },
     "name" : "Goo",
     "r_bakerToCharlie_c_bakerObjectId" : 41971
   }
   ```

## Examining the GET Code

```
```
<!-- With the GET method, you can determine whether to include nested fields and the number of nested levels to include (i.e., 0-5). You can call the GET method with nested field queries to determine which relationships are displayed. -->

http://localhost:8080/o/c/charlieobjects/${1}/?nestedFields=bakerObject,ableObject&nestedFieldsDepth=2

* `nestedFields`: determine the types of object entries that you want to include in your query (e.g., `bakerObject,ableObject`).
* `nestedFieldsDepth`: determine the depth of entries you want to include (0-5).


## Additional Information
