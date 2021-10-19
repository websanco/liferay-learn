# Adding Fields to Objects

Fields are data definitions that represent a database column and are used to store different [types of values](#field-type-reference) for Liferay Objects. You can add fields to any published or unpublished Object, including both system and custom Objects.

When an Object is [published](./creating-objects.md#publishing-object-drafts), an initial database table is created with the draft's data definitions. This table includes all Object fields and relationships that exist at the time of publishing. Fields and relationships added to an Object after publishing are added to a side table (i.e., `[Initial_Table_Name]_x`). <!--SME INPUT-->

```{important}
If a field is saved to an Object draft, you can edit any of its settings and values. However, once fields are published or saved to a published Object, only its Label can be edited. All other values and settings cannot be changed. 

Also, fields included in an Object when it is published cannot be removed. You can only remove fields in an Object draft or those added to an Object after it's been published. In other words, no fields included in the Object's initial database table can be removed.
```

Follow these steps to add a new field to an Object:

1. Open the *Objects* portlet.

1. Click on the desired Object.

1. Click on the *Fields* tab, and click on the *Add* button (![Add Button]()).

1. Enter a *Label* and *Field Name*.

   **Label**: This value identifies the field in the Objects UI and can be localized after the field is created.

   **Field Name**: This value determines the field's name in the backend and uses camel case.<!--SME INPUT--> Once a field is published, this value cannot be changed.

1. Select a field *Type*. See [Field Type Reference](#field-type-reference) for more information.

1. Determine whether or not the field is *Mandatory*.

1. Click on *Save*.

If you've saved the field to an Object draft, you can click on the field to determine whether or not it is searchable. All fields are searchable by default.

## Field Type Reference

| Type | Description |
| --- | --- |
| BigDecimal | <!--SME INPUT--> |
| Boolean | A logical, binary value of either *true* or *false* |
| Date | A value indicating a specific day, month, and year |
| Double | A 64 bit numerical value with a floating point |
| Integer | A 32 bit numerical value without a floating point |
| Long | A 64 bit numerical value without a floating point |
| Picklist | A string value stored in a [picklist](../using-picklists.md). |
| String | A sequence of characters (e.g., letters, numbers, punctuation) |

## Additional Information

* [Creating Objects](./creating-objects.md)
* [Defining Object Relationships](./defining-object-relationships.md)
* [Designing Object Layouts](./designing-object-layouts.md)
* [Managing Objects](./managing-objects.md)
