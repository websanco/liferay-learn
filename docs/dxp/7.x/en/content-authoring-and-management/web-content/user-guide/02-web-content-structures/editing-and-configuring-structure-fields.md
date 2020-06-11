# Configuring Structure Fields

You can edit the fields in a Structure, as well as the properties of each field. To edit a Structure, follow these steps:

1. Open the Product Menu and go to the Site Menu heading &rarr; *Content & Data* &rarr; *Web Content*.
1. Select the *Structures* tab.
1. Click the name of the Structure you want to modify.

    ```tip::
       You can also open the Actions Menu next to the Structure and select *Edit*.
    ```

1. Add, [remove](#structure-field-options), or reorganize the fields in the Structure.
1. Select the field you want to configure and click the *Settings* tab, or hover over the field and click the *Settings* (![Settings icon](../../../../images/icon-control-menu-gear.png)) icon in the context menu that appears. You can perform [other actions](#structure-field-options) in the context menu as well.
1. [Configure your desired settings](#configurable-settings) for the field and *Save* the changes.

## Configurable Settings

```note::
   The available settings for a field depend on the type of field. A setting listed below may not be available for a field.
```

**Type:** The type of field placed in the definition. This is not editable but is available to reference from a Web Content Template.

**Field Label:** The text to display above the field to the user

**Show Label:** Whether to show the *Field Label.* The default value is *Yes*.

**Required:** Whether to mark the field as required. If a field is required, users must enter a value for it to submit content using the Structure. The default value is *No*.

**Name:** The variable name used to retrieve data for the field in Web Content Templates. You should enter a descriptive name. The default value is automatically generated.

```tip::
   Liferay DXP assigns a random name for each new field in the Structure. Replace the default value with something more descriptive, so it's easier to identify the field when you read the data in a Web Content Template or Display Page.
```

**Predefined Value:** The default placeholder value for a field. Alternatively, you can define predefined values using the *Edit Default Values* option. See [Managing Structures](./managing-structures.md#edit-default-values) for more information.

**Tip:** Help text to display with the field when the user hovers over the help icon

**Indexable:** Whether to index the field for search. The default value is *Yes*.

**Localizable:** Whether the field can be localized in the user's language. The default value is *Yes*.

**Repeatable:** Whether the user can duplicate the field while creating the Web Content. For example, you can have a repeatable author field so the user can duplicate the field to enter a second author for an article. The default value is *No*. If set to *Yes*, the user can click a `+` icon on the field to duplicate it while creating the Web Content.

**Multiple:** Whether a user can select multiple options in a *Select* field. The default value is *No*.

**Options:** The available options for a *Select* field or *Radio* field. You can add and remove options as well as edit each individual option's display name (the name the user sees when creating the Web Content) and value (the value stored for the option, which can be retrieved in the Web Content Template) pair.

**Style:** Optional CSS styles for the *Separator* field.

## Structure Field Options

When you hover over a Structure field, these options are available in the context menu that appears:

* **Settings** (![Settings](../../../../images/icon-settings.png)): Changes the name and label and set other information about the field, like whether or not it is required.
* **Delete** (![Delete](../../../../images/icon-app-trash.png)): Removes the field from the structure.
* **Duplicate** (![Duplicate](../../../../images/icon-duplicate.png)): Duplicates the field and all its settings and iterates the *Name* to avoid conflicts.