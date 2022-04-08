---
description: 9 - Extend Liferay Schema
title:  Expando API
order: 3
---

## Expando API

The underlying API for the custom fields is called the *Expando API*. That's why, when you take a look at Liferay's code, you will notice that custom fields are referred to there as Expando fields.

In an example use case for the Expando API, you could be populating a custom user attribute from the LDAP directory at login time. Expando API also allows you to create virtual tables not related to any model class, extending the features available from the management user interface.

#### Expando Data Model {#data}

The Expando virtual data model has four entities. Let's take a look at them on the database level.

#### ExpandoTable {#table}

ExpandoTable represents a virtual table definition for a model class and is bound to it with the __classNameId__ field:

<img src="../images/expando-table.png" />

#### ExpandoColumn {#column}

ExpandoColumn represents a single column definition in a virtual table. The *Name* field is the custom field name:

<img src="../images/expando-column.png" />

#### ExpandoRow {#row}

ExpandoRow represents a data row for a model instance and is bound to a model instance with the *classPK* field:

<img src="../images/expando-row.png" />

#### ExpandoValue {#value}

ExpandoValue represents a single custom field value for an asset instance and is bound to that model instance with the *classPK* field:

<img src="../images/expando-value.png" />

### Data Model Logical View {#view}

The diagram below illustrates the Expando data model logical view:

<img src="../images/expando-logical-architecture.png" />

#### Using Expando Services {#services}

All the entities of the Expando data model have their local and remote services:

* __ExpandoTables:__
	* ExpandoTableLocalService
	* ExpandoTableService
* __ExpandoColumns:__ 
	* ExpandoColumnLocalService
	* ExpandoColumnService
* __ExpandoRows:__ 
	* ExpandoRowLocalService
	* ExpandoRowService
* __ExpandoValues:__
	* ExpandoValueLocalService
	* ExpandoValueService

> As always with Liferay's core services, the local service variant is for privileged access without access control.

#### Using the Expando API {#use}

Create an Expando table programmatically:

```java
protected long getExpandoTableId(long companyId, String className) 
	throws Exception {

	// Try to get the expando table for user. Create if not found.

	ExpandoTable expandoTable = null;

	try {

		expandoTable = _expandoTableLocalService.getDefaultTable(companyId, className);

	} catch (NoSuchTableException ne) {

		expandoTable = _expandoTableLocalService.addDefaultTable(companyId, className);
	}

	return expandoTable.getTableId();
}
```

Set an Expando field value:

```java
protected void addExpandoValue(HttpServletRequest request, String value) 
		throws Exception {
	
	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

	long companyId = themeDisplay.getCompanyId();
	
	long expandoTableId = getExpandoTableId(companyId, User.class.getName());

	long expandoColumnId = getExpandoColumnId(expandoTableId, ID_COLUMN);

	long classNameId = _portal.getClassNameId(User.class);

	long classPK = themeDisplay.getUser().getPrimaryKey();

	_expandoValueLocalService.addValue(classNameId, expandoTableId, expandoColumnId, 
			classPK, value);
}
```

Just like FreeMarker templates, Expando attributes can be accessed in the user interface by calling the Expando bridge of an entity:

```java
themeDisplay.getUser().getExpandoBridge().getAttribute("identification_number")
```

All custom attributes can be listed conveniently using the liferay-ui tag library:

```html
<liferay-ui:custom-attribute-list
	className="<%= User.class.getName() %>"
	classPK="<%= userId >"
	label="true"
/>
```

<br />

<div class="summary-chapter">
<h3>Knowledge Check</h3>
<ul>
	<li>The Expando virtual _____________________ has four entities:
		<ol>
			<li>______________________</li>
			<li>______________________</li>
			<li>______________________</li>
			<li>______________________</li>
		</ol>
	</li>
	<li>The ___________________________ variant is for privileged access without access control.</li>
</ul>
</div>