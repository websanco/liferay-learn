# Understanding Object Field Types

Object fields represent database columns that store specific types of data for an Object. Fields can be added to both custom and system Objects and can only store one type of data.

Liferay provides a user-friendly name for each field type, which corresponds to a specific data type in the backend. When making Headless API calls that include fields, you must use the backend data type names:

| Field Types (UI) | Data Type (HEADLESS) | Description |
| :--- | :--- | :--- |
| Boolean | Boolean | Stores true or false values |
| Date | Date | Stores date values |
| Text | String | Stores simple text values up to 280 characters |
| Long Text | Clob | Stores a text box value that supports up to 65,000 characters |
| Picklist | String | Stores a [Picklist](./using-picklists.md) string value |
| Integer | Integer | Stores an integer up to 9 digits in length |
| Long Integer | Long | Stores a large integer up to 19 digits in length |
| Decimal | Double | Stores a decimal number value that supports fractional portions |
| Precision Decimal | BigDecimal | Stores a high-precision decimal number without rounding |
| Relationship | Long | Stores the numeric ID for all related Object entries |

<!-- 
## Text Fields

### Text

### Long Text

## Picklist

## Numeric Fields 

### Integer

### Long Integer

### Decimal

### Precision Decimal

## Relationship Fields
-->

## Additional Information

* [Adding Fields to Objects](./creating-and-managing-objects/adding-fields-to-objects.md)
* [Headless Framework Integration](./understanding-object-integrations/headless-framework-integration.md)
