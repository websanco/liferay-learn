# Liferay Commerce Connector to Salesforce

Coming Soon!

<!-- 
> Subscription Required

Liferay provides a connector for integrating and pushing data between Salesforce and Liferay Commerce instances. This is achieved using Talend Job Designs and Liferay's Data Task Manager.

Once purchased, you can [download]() and [deploy]() the Salesforce Connector to a Liferay instance as an `.lpkg` file. This file contains multiple Talend Jobs that map data fields in Liferay Commerce to data fields in Salesforce. Once deployed, Dispatch Tasks are automatically created for each Talend Job. They can then be configured and run manually via the Dispatch UI, or scheduled to run at specific time intervals.

```note::
   The Liferay Commerce Connector to Salesforce is compatible with Liferay DXP 7.1.x+.
```

## Supported Entities

The following chart lists supported Salesforce entities with their Liferay counterparts. While some entity data can be transferred bidirectionally, some Liferay data cannot be transferred to Salesforce.

| Salesforce | Liferay | From Salesforce to Liferay | From Liferay to Salesforce |
| --- | --- | --- | --- |
| Account | Account | &#10004; | &#10004; |
| Account Address | Account | &#10004; | &#10004; |
| Account Contact | User | &#10004; |  |
| Price Book | Price List | &#10004; |  |
| Price Book Entry | Price Entry | &#10004; |  |
| Product | Product | &#10004; |  |
| Order | Order | &#10004; | &#10004; |
| OrderItem | Order Item | &#10004; | &#10004; |
| OrderNote | Order Note | &#10004; | &#10004; |

```note::
   Salesforce has no equivalent entity to Liferay Commerce Catalogs. 
   
   Importing Salesforce Products to Commerce requires a Catalog with `externalReferenceCode` equal to `SALESFORCE`. If such a Catalog does not already exist, Commerce automatically creates a default Catalog during the `Products` Task execution. This Catalog is then used to store This Catalog is created with default value, .  if such a Catalog does not already exist.   
```

## Deploying the Connector

Follow these steps to deploy the Salesforce connector to a DXP instance:

1. Download the *Commerce Connector to Salesforce* from the [Liferay Help Center](http://customer.liferay.com/downloads).

1. Copy the `.lpkg` file to the DXP instance's [`${liferay.home}/deploy`](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/liferay-home.html) folder.

1. Verify the following message displays in the server console:

   ```
   ```

1. Restart the DXP instance.

1. Once the server finishes restarting, confirm the deployment was successful by going to the *Dispatch* page in the Control Panel.

   If successful, four new Talend Dispatch Tasks were automatically created for each of the Talend Data Integration Jobs.

   ![]()

   Before running these tasks, you must configure their context parameters.

## Configuring Each Talend Dispatch Tasks

When created, the settings editor for each Talend Dispatch Task is automatically populated with default context parameters. These parameters can be used to provide necessary credentials and to modify each task's behavior in runtime.

```warning::
   Be careful when removing parameters from the settings editor. If you leave a key with a blank value, it will disappear when the settings are saved. If the keys and their values are lost, they cannot be restored, and you'll have to recreate the Talend Dispatch Task.
```

### Credentials Reference

Each task includes the following fields for entering Liferay and Salesforce credentials.

| Context Parameter | Description |
| --- | --- |
| `LiferayServerURL` | URL for the Liferay server |
| `LiferayUser` | Liferay account username |
| `LiferayPassword` | Liferay account password |
| `SalesForceURL` | Salesforce SOAP API Url |
| `SalesForceUser` | Salesforce account username |
| `SalesForcePassword` | Salesforce account password |
| `SalesForceToken` | Salesforce OAuth API token |

### Products Reference

| Context Parameter | Description | Default Value |
| --- | --- | --- |
| `catalogName` | The name assigned to the Catalog used for Salesforce data | |
| `catalogExternalReferenceCode` | The external reference code used when creating or fetching the catalog; must be `SALESFORCE` | `SALESFORCE` |
| `catalogDefaultCurrency` | The currency used for the Catalog |  |
| `catalogDefaultLanguage` | The language used for the Catalog |  |
| `catalogId` |  |  |
| `allowBackOrder` |  |  |
| `displayAvailability` |  |  |
| `productType` | The product type used when creating all products |  |
| `freeShipping` |  |  |
| `shippable` |  |  |
| `shippingSeparately` |  |  |

### Price_List Reference

| Context Parameter | Description | Default Value |
| --- | --- | --- |
| `catalogId` |  |  |
| `currencyCode` |  |  |
| `neverExpire` |  |  |
| `priority` |  |  |
| `hasTierPrice` |  |  |
| `standardPrice` |  |  |

### Accounts Reference

| Context Parameter | Description | Default Value |
| --- | --- | --- |
| `accountType` | The account type used when creating all accounts: 1 (personal) or 2 (business) | |
| `countryMapping_null` | The country mapping used for Account addresses, must use the following syntax `countryMapping_NAME=COUNTRY_ISO_CODE` (e.g., `countryMapping_Croatia=HR`) | |
| `regionMapping_null` | The region mapping used for Account addresses; must use the following syntax `regionMapping_NAME=REGION_ISO_CODE` (e.g., `regionMapping_Milan=MI`) | |

### Orders Reference

| Context Parameter | Description | Default Value |
| --- | --- | --- |
| `channelId` | | |
| `defaultCurrency` | | |
| `statusMapping_Activated` | | |
| `paymentStatusMapping_Payed` |  |  |

## Running Each Talend Dispatch Task

Once configured, you can run each task manually by clicking *Run Now*, or schedule them to run automatically. See [Using Dispatch]() for more information.

```important::
   Running these Talend Dispatch Tasks can be resource intensive and degrade platform performance. To reduce performance impact, avoid running them during peak use hours.
```

## Additional Information -->
