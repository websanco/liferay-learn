# Expression Builder Validations Reference

{bdg-secondary}`Available Liferay DXP U26 and Portal GA26`

With custom Objects, you can create field validations using Liferay's Expression Builder. This tool provides an integrated editor for quickly defining complex validation rules using predefined fields, operators, and functions. You can browse available elements in the conditions side panel.

```{important}
Expression Builder validations can only be used with text, numeric, date, and boolean field types.
```

## Text Fields

Liferay provides the following operators and functions for Text and Long Text fields. You can use any of these fields as arguments. With some elements, you can also add string values to compare with the field's value (e.g., `"foo"`, `"[A-Za-z]{1,50}"`).

### Concat

Combine multiple strings or text fields and return a single string that can be used with other validation functions.

```
concat(text_field_id, "<string>")
```

### Contains

Check if a text field contains a specific string value and return a Boolean. If the field *does* contain the value, it is invalid.

```
contains(text_field_id, "<string>")
```

### Does Not Contain

Check if a text field contains a specific string value and return a Boolean. If the field *does not* contain the value, it is invalid.

```
NOT(contains(text_field_id, "<string>"))
```

### Is a URL

Check if a text field is a URL and return a Boolean. If the field does not match a URL pattern, it is invalid. Valid entries must use standard URL elements (e.g., `http`, `://`, `.com`)

```
isURL(text_field_id)
```

### Is an Email

Check if a text field is an email and return a Boolean. If the field does not match a specific email or email pattern, it is invalid. Valid entries must use standard email elements (e.g., `@gmail`, `.com`)

```
isEmailAddress(text_field_id)
```

### Is Empty

Check if a text field is empty and return a Boolean. Empty fields are invalid.

```
isEmpty(text_field_id)
```

### Is Equal To

Check if a text field is equal to a specific string value and return a Boolean. If they are not equal, it is invalid.

```
text_field_id == "<string>"
```

### Not Equal

Check if a text field is different from a specific string value and return a Boolean. If they are equal, it is invalid.

```
text_field_id != "<string>"
```

### Matches

Check if a text field matches a specific string value or RegEx expression and return a Boolean. If the field does not match the value, it is invalid.

```
match(text_field_id, "<string>")

match(text_field_id, "<regex>")
```

## Numeric Fields

Liferay provides the following operators and functions for Integer, Long Integer, Decimal, and Precision Decimal fields. You can use any of these fields as arguments. With some elements, you can also add numeric values to compare with the field's value (e.g., `123`, `3.1415`).

### Contains

Check if a number field contains a specific numeric value and return a Boolean. If it *does*, the field is invalid.

```
contains(numeric_field_id, <number>)
```

### Does not Contain

Check if a number field contains a specific numeric value and return a Boolean. If it *does not*, the field is invalid.

```
NOT(contains(numeric_field_id, <number>))
```

### Is Decimal

Check if a number field is a decimal and return a Boolean. If it is, the field is invalid.

```
isDecimal(numeric_field_id)
```

### Is Integer

Check if a number field is an integer and return a Boolean. If it is, the field is invalid.

```
isInteger(numeric_field_id)
```

### Is Equal To

Check if a number field is equal to a specific numeric value and return a Boolean. If it is, the field is invalid

```
numeric_field_id == <number>
```

### Is Not Equal To

Check if a number field is different from a specific numeric value and return a Boolean. If it is, the field is invalid

```
numeric_field_id != <number>
```

### Is Greater Than

Check if a number field is greater than a specific numeric value and return a Boolean. If it is, the field is invalid.

```
numeric_field_id > <number>
```

### Is Greater Than or Equal To

Check if a number field is greater than or equal to a specific numeric value and return a Boolean. If it is, the field is invalid.

```
numeric_field_id >= <number>
```

### Is Less Than

Check if a number field is less than a specific numeric value and return a Boolean. If it is, the field is invalid.

```
numeric_field_id < <number>
```

### Is Less Than Or Equal To

Check if a number field is less than or equal to a specific numeric value and return a Boolean. If it is, the field is invalid.

```
numeric_field_id <= <number>
```

### Sum

Add multiple numeric fields together and return a single number that can be used with other validation functions.

```
sum(firstNumeric_field_id, secondNumeric_field_id)
```

## Date Fields

Liferay provides the following operators and functions for Date fields. You can use any Date field as an argument. You can also set date values for comparing with your date field (e.g., `2020-03-19`).

### Future Dates

Check if a date field's value is in the future and return a Boolean. If it isn't, the field is invalid.

```
futureDates(date_field_id, "<yyyy-MM-dd>")
```

### Past Dates

Check if a date field's value is in the past and return a Boolean. If it isn't, the field is invalid.

```
pastDates(date_field_id, "<yyyy-MM-dd>")
```

### Range

Check if a date range begins with a past date and ends with a future date. If it doesn't, the field is invalid.

```
pastDates(date_field_id, "<yyyy-MM-dd>")
AND
futureDates(date_field_id, "<yyyy-MM-dd>")
```

## Math Operators

You can use any of the following math operators with numeric fields.

### Plus ( `+` )

Add numeric fields to create an expression.

```
firstNumeric_field_id + secondNumeric_field_id,
```

### Minus ( `-` )

Subtract numeric fields from one another to create an expression

```
firstNumeric_field_id - secondNumeric_field_id,
```

### Divide ( `/` )

Divide one numeric field by another to create an expression.

```
firstNumeric_field_id / secondNumeric_field_id,
```

### Times/Multiply ( `*` )

Multiply numeric fields to create an expression.

```
firstNumeric_field_id * secondNumeric_field_id,
```

## Logical Operators

Logical operators are used with multiple elements to aggregate complex conditions from multiple elements. Currently, Expression Builder validations provide the `AND` and `OR` operators.

* `AND`: This is a type of coordinating conjunction that is commonly used to indicate a dependent relationship.

* `OR`: This is another type of coordinating conjunction that indicates an independent relationship.

In the following example, the `AND` clauses are dependent on each other and must be taken together. By contrast, the `OR` clauses are logically separate and not co-dependent on each other.

```
match(cellphoneNumber_field_id, "^([1-9]{2}) (?:[2-8]|9[1-9])[0-9]{3}-[0-9]{4}$")
AND match(name_field_id, "[A-Za-z]{1,50}")
AND match(address_field_id, "[A-Za-z]{1,50}")
OR numeric_field_id == 1
OR match(lastName_field_id, "[A-Za-z]{1,50}")
```

## Validation Examples

The following validations are common examples.

### Name Validation (Text)

This validation restricts values to alphabetic characters and limits the number of allowed characters.

```
match(name_field_id, ("[A-Za-z]{1,50}")
```

This validation allows for numbers in last names.

```
match(lastName_field_id, "[A-Za-z][0-9]{1,50}")
```

### Password (Text)

This validation checks for valid passwords. It includes the following restrictions:

* At least 8 characters
* At least 5 unique characters
* At least 1 symbol
* At least 1 number
* No spaces allowed

```
match(password_field_id, "^(?=.*[A-Za-z])(?=.*d)(?=.*[@$!%*#?&])[A-Za-zd@$!%*#?&]{8,}$")
```

### Cell Phone Number (Numeric)

This validation checks for a valid phone number. It restricts entries to numeric characters, limits the number of characters, and sets a standard phone number pattern.

```
match(cellphoneNumber_field_id, "^([1-9]{2}) (?:[2-8]|9[1-9])[0-9]{3}-[0-9]{4}$")
```

### Postal Code (Numeric)

This validation checks for a valid postal code. It restricts entries to numeric characters, limits the number of characters, and sets a standard postal code pattern.

```
match(postal_field_id, "^([1-9]{2}) (?:[2-8]|9[1-9])[0-9]{3}-[0-9]{4}$")
```

### Specify Age Range (Date)

This example checks whether a user's is between 18-65 years old. If it isn't, the entries are invalid.

```
pastDates(date_field_id, startsFrom, responseDate, years, -120, endsOn, responseDate, years, -18)
AND
futureDates(date_field_id, startsFrom, responseDate, years, 0, endsOn, responseDate, years, 65)
```

## Additional Information

* [Creating Objects](./creating-objects.md)
* [Adding Fields to Objects](./adding-fields-to-objects.md)
* [Adding Custom Validations](./adding-custom-validations.md)
