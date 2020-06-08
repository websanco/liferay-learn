# Editing and Configuring the Structure Fields

You can edit the different fields that are part of a structure, as well as the properties of each field.

To edit a Structure:

1. Go to *Site Administrator* &rarr; *Content & Data* &rarr; *Web Content*.
2. Select the *Structures* tab.
3. Click the name of the structure you want to modify

```tip::
   You can also click the (![Action Menu](../../../../../../images/icon-actions.png) menu and choose *Edit*.
```

4. Add, remove, or reorganize the fields in the structure.
5. On the structure, click the field you want to configure and select *Settings*, or click the *Options* (![Options](../../../../images/icon-gear.png)) icon
6. Configure your desired settings for the field.

## Configurable Settings in the Structure Fields

```note::
   The available settings for a field depend on the type of field.
```

**Type:** Lists the type of field placed in the definition. This is not editable but is available to reference from a template.

**Field Label:** Sets the text that can be displayed with the field. This is the human-readable text that the user sees.

**Show Label:** Select *Yes* to display the Field Label.

**Required:** Select *Yes* to mark the field required. If a field is required, users must enter a value for it to submit content using the structure.

**Name:** The name of the field internally, automatically generated. Since this is the variable name that you can read the data from in a template or display page, you should enter a descriptive name.

```tip::
   Liferay DXP assigns a random name for each new field in the structure. Change this random name for something descriptive, so it's easier to identify the field when you read the data in a templaste or display page.
```

**Predefined Value:** When a user creates a new web content article based on a structure that has predefined values for various fields, the predefined values appear in the form as defaults for those fields.

Alternatively, you can define predefined values using the *Edit Default Values* option from the *Actions* menu for the structure. For more information, read [Managing Structures](managing-structures.md).

**Tip:** Each field can have a small help icon, with a tooltip attached that displays helpful information. If you want to provide text for the tooltip, you may enter it here.

**Indexable:** Select *Yes* to index the field for search.

**Localizable:** Select *Yes* to allow localization for the field.

**Repeatable:** Select *Yes* to make your field repeatable. Users can then add as many copies of this field as they need. For example, if you're creating a structure for articles, you might want a repeatable Author field in case you have multiple authors for a particular article.

**Multiple:** Select *Yes* to enable a multi-selection list. This is only available for the Select field.

**Options:** Changes the options available for selection. You can add and remove options as well as edit each individual option's display name and value. This is only available for the Radio and Select fields.

**Style:** Changes the style of the line separator. This is only available for the Separator field.
