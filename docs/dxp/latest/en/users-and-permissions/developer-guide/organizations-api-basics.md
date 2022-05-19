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

## Examine the Java Class