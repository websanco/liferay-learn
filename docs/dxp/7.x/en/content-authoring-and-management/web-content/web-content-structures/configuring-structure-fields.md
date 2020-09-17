# Configuring Structure Fields

You can edit Structure fields and their properties:

1. Open the Product Menu and go to the Site Menu heading &rarr; *Content & Data* &rarr; *Web Content*.
1. Select the *Structures* tab.
1. Click the name of the Structure you want to modify.

    ```tip::
       You can also open the Actions Menu next to the Structure and select *Edit*.
    ```

1. Add, [remove](#structure-field-options), or reorganize the fields in the Structure.
1. Select the field you want to configure and click the *Settings* tab, or hover over the field and click the *Settings* (![Settings icon](../../../images/icon-control-menu-gear.png)) icon in the context menu that appears. You can also perform [other actions](#structure-field-options) in the context menu.
1. [Configure your desired settings](#configurable-settings) for the field and *Save* the changes.

```tip::
   When you create or `edit a Structure <./configuring-structure-fields.md>`_, the *View* tab is the default. You can switch to the *Source* tab to view the Structure's raw XML and modify it directly.
```

## Configurable Settings

```note::
   Field settings depend on the type of field. A setting listed below may not apply for a field.
```

**Type:** The type of field placed in the definition. This is not editable but is available to reference from a Web Content Template.

**Field Label:** The text to display above the field. 

**Show Label:** Whether to show the *Field Label.* The default value is *Yes*.

**Required:** Whether to mark the field as required. If a field is required, Users must enter a value for it to submit content using the Structure. The default value is *No*.

**Name:** The variable name used in Web Content Templates for this field. You should enter a descriptive name. A default name is automatically generated.

```tip::
   Liferay DXP assigns a random name for each new field in the Structure. Replace the default value with something more descriptive, so template developers can identify the field to place its data in a Web Content Template or Display Page.
```

**Predefined Value:** The default placeholder value for a field. Alternatively, you can define predefined values using the *Edit Default Values* option. See [Managing Structures](./managing-structures.md#edit-default-values) for more information.

**Tip:** Help text to display with the field when the user hovers over the help icon.

**Indexable:** Whether to index the field for search. The default value is *Yes*.

**Localizable:** Whether the field can be localized in the user's language. The default value is *Yes*.

**Repeatable:** Whether the User can duplicate the field while creating the Web Content. For example, you can have a repeatable author field so the User can duplicate the field to enter a second author for an article. The default value is *No*. If set to *Yes*, users can click a `+` icon on the field to duplicate it while creating the Web Content.

**Multiple:** Whether a Select field can have multiple selected options. The default value is *No*.

**Options:** The available options for a *Select* field or *Radio* field. You can add and remove options as well as edit each individual option's display name (the name the User sees when creating the Web Content) and value (the value stored for the option, which can be retrieved in the Web Content Template) pair.

**Style:** Optional CSS styles for the *Separator* field.

## Structure Field Options

When you hover over a Structure field, these options appear in its context menu:

* **Settings** (![Settings](../../../images/icon-settings.png)): Changes the name, label, and other information about the field, like whether or not it is required.
* **Delete** (![Delete](../../../images/icon-app-trash.png)): Removes the field from the structure.
* **Duplicate** (![Duplicate](../../../images/icon-duplicate.png)): Duplicates the field and all its settings and iterates the *Name* to avoid conflicts.

## Related Information

* [Creating Structures](./creating-structures.md)
* [Managing Structures](./managing-structures.md)
* [Understanding Web Content Structures](./understanding-web-content-structures.md)
