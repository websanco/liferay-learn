# Adding Custom Validations

{bdg-secondary}`Available 7.4 U27+ and GA27+`

With custom Objects, you can add validations for both custom and metadata fields. Validations set rules used for determining valid field entries and are defined using either [Groovy](https://groovy-lang.org/) scripts or Liferay's [expression builder](./expression-builder-validations-reference.md). Each validation has its own trigger, conditions, and error text, which you can set via the Objects UI. When triggered, the validation checks for valid field entries according to your defined conditions and displays your error text for invalid entries.

![Create validations for custom and metadata Object fields.](./adding-custom-validations/images/01.png)

```{note}
The desired data fields must exist before you can create validations for them.
```

Follow these steps to add a custom validation:

1. Open the *Global Menu* (![Global Menu](../../../images/icon-applications-menu.png)), click the *Control Panel* tab, and go to *Objects*.

1. Begin editing a custom Object.

1. Go to the *Validations* tab and click the *Add* button (![Add Button](../../../images/icon-add.png)).

1. Enter a *label* and select a validation type: *Groovy* or *Expression Builder*.

   ![Enter a label and select a validation type.](./adding-custom-validations/images/02.png)

1. Click *Save*.

1. Begin editing the newly created validation.

1. Go to the *Conditions* tab and add conditions to the validation.

   ![Add conditions to the validation.](./adding-custom-validations/images/03.png)

   When using Groovy, you can browse and add available data fields to your conditions via the side panel. See [Using Groovy Validations](#using-groovy-validations) for more information.

   When using Expression Builder, you can browse and add fields, operators, and functions to your conditions via the side panel. See [Using Expression Builder Validations](#using-expression-builder-validations) for more information.

   ```{tip}
   Conditions can include multiple fields and functions for complex validations.
   ```

1. Enter a localizable *error message*. This message is displayed whenever the validation is triggered and field entries do not meet one or more of the defined conditions.

1. Go to the *Basic Info* tab and *activate* the validation.

1. Select a *Trigger Event* to determine when the validation is run for field entries.

   ```{note}
   Each validation can only have one trigger event.
   ```

   ![Activate the validation and set a trigger event.](./adding-custom-validations/images/04.png)

1. Click *Save*.

Once activated, the validation is run for all new Object entries.

## Using Groovy Validations

The Groovy validation type supports all standard Groovy Script capabilities. However, when defining Groovy conditions, you must use the `invalidFields` variable. This variable should return `true` for invalid field values and `false` for valid field values. The error message only appears when `invalidFields` returns `true`.

```{note}
The Groovy type does not currently have a check syntax validation.
```

![Use the side panel to add field elements to your Groovy validations.](./adding-custom-validations/images/05.png)

## Using Expression Builder Validations

The Expression Builder type provides predefined fields, operators, and functions that you can access in the Conditions side panel. Clicking an element adds it to the conditions editor. These functions return a Boolean value. For some, `true` indicates an valid entry, while for others `true` indicates an invalid entry. See [Expression Builder Validations Reference](./expression-builder-validations-reference.md) for a complete list of provided operators and functions.

```{important}
Expression Builder validations can only be used with text, numeric, date, and boolean field types.
```

![Use the side panel to add field, operator, and function elements to your validation.](./adding-custom-validations/images/06.png)

## Available Fields Reference

When constructing conditions, you can use any of the Object's custom or metadata fields. You can also select from relationship fields on the 'one' side of a relationship.

The following chart lists all default metadata fields included in custom Objects:

| Field | Description |
| :--- | :--- |
| `companyId` | Portal instance where the entry was created |
| `createDate` | When the entry was created |
| `externalReferenceCode` | External reference code for the entry |
| `groupId` | Site ID in where the entry was created |
| `lastPublishDate` | Date when the entry was last published |
| `modifiedDate` | Date when the entry was last modified |
| `mvccVersion` | MVCC version of the entry |
| `objectDefinitionId` | ID of the entry's Object |
| `objectEntryId` | ID for the entry |
| `status` | Workflow status for the entry |
| `statusByUserId` | ID of the assigned user in Workflow |
| `statusByUserName` | Name of the assigned user in Workflow |
| `statusDate` | Date when the Workflow status was last updated |
| `userEmailAddress` | Email address for the entry's author |
| `userFirstName` | First name of the entry's author |
| `userId` | ID of the entry's author |
| `userLastName` | Last name of the entry's author |
| `userName` | User name of the entry's author |
| `uuid` | Unique universal ID for the entry |

```{note}
Besides custom fields, the following metadata fields are listed in the editing sidebar for easy access:

* Author (`userName`)
* Created Date (`createDate`)
* ID (`objectEntryId`)
* Modified Date (`lastPublishDate`)
* User Email (`userEmailAddress`)
* Workflow status (`status`)
```

## Additional Information

* [Creating Objects](./creating-objects.md)
* [Adding Fields to Objects](./adding-fields-to-objects.md)
* [Expression Builder Validations Reference](./expression-builder-validations-reference.md)
