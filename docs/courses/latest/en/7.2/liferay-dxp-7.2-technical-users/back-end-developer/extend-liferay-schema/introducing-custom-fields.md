---
description: 9 - Extend Liferay Schema
title: Introducing Custom Fields
order: 2
---

## Introducing Custom Fields

As a developer or system administrator, you may sometimes want to add a persistable and manageable attribute to an existing Liferay data model. Such use cases could be, for example:

* Adding an identification number for a user model
* Adding an author field to the page (layout) model 
* Adding a custom LDAP mapping attribute to the user group model

Custom fields are supported by all the registered portal assets and are automatically added to the asset editing forms and configurable for the displays. Custom fields also support permissioning.

For creating and managing custom fields, there is a management user interface in the *Control Panel → Configuration → Custom Fields*. There's also a programmatical API available:

<img src="../images/custom-fields.png" style="max-width: 100%;"/>

#### Supported Types {#types}

The following types are supported for custom fields:

* Selection of Integer Values
* Selection of Decimal Values
* Selection of Text Values
* Text Box
* Text Box–Indexed
* Text Field–Secret
* Text Field–Indexed
* Primitives
	* True/False
	* Date
	* Decimal number (64-bit)
	* Group of decimal numbers (64-bit)
	* Decimal number (32-bit)
	* Group of decimal numbers (32-bit)
	* Integer (32-bit)
	* Group of integers (32-bit)
	* Integer (64-bit)
	* Group of integers (64-bit)
	* Decimal number or integer (64-bit)
	* Group of decimal numbers or integers (64-bit)
	* Integer (16-bit)
	* Group of integers (16-bit)
	* Text
	* Group of text values
	* Localized text

#### Searchability {#search}

Setting a custom field to __searchable__ means that the value of the field is indexed and searchable. The indexed type can be chosen between a keyword and, for strings, a text type.

> When a searchability setting is changed, the indexes must be updated to make the change effective to the existing entities.

#### Accessing Custom Fields From Templates {#fields}

Custom fields can be accessed from FreeMarker templates by calling the expando bridge:

```xml
<#assign expandoAttribute = user.getExpandoBridge().getAttribute("my_sample_field") />
```
