# Managing Structures

You can manage Structures from the Structures Page. Follow these steps:

1. Open the Product Menu and go to the Site Menu heading &rarr; *Content & Data* &rarr; *Web Content*.
1. Select the *Structures* tab.
1. Open the *Actions Menu* (![Action Menu](../../../../images/icon-actions.png)) next to the Structure and select one of the [available actions](#available-actions).
1. Click *Save* to apply your changes.

## Available Actions

The available options in a Structure's Actions Menu (![Action Menu](../../../../images/icon-actions.png)) are listed below.

**Edit:** Opens the Structure so you can update it. See [Configuring Structure Fields](./03-editing-and-configuring-structures-content.md) for more information.

**Edit Default Values:** Opens a form to set default values for the Structure's fields. See [below](#editing-default-values) for more information.

**Manage Templates:** Opens the *Templates* tab with a filtered set of Web Content Templates that use the Structure. From here you can manage (edit, copy, delete, and set permissions for) the Web Content Templates. See [Managing Templates](managing-templates.md) for more information.

**Permissions:** Opens the permissions configuration dialog for the Structure. See [below](#permissions-tips) for some useful tips.

**Copy:** Opens a form to copy the Structure and set its name. See [below](#copying-an-existing-structure) for more information.

**Delete:** Deletes the Structure

## Editing Default Values

You can set the default values for a Structure's fields. For example, a Structure used for confidential memos may want to add the "confidential" tag for all the articles created with that Structure, or you may want to make the content of all confidential memos non-searchable. From the Structures Page, open the Actions Menu for the Structure and follow these steps:

1. Choose *Edit Default Values*.
1. In the *Structure Default Values* page, configure the default values for your fields.

    ```tip::
      You can restore the original values of the Structure at any time by clicking the *Reset Values* button at the top.
    ```

1. Click *Save* to apply the changes.

```tip::
   You can also use the *Predefined Value* setting for the structure field to set default values. For more information, see Editing and `Configuring Structures' Content <./editing-and-configuring-structures-content.md)>`_
```

## Permissions Tips

Consider these points when setting permissions for Structures:

* Most users should not be able to edit Structures
* The *View* permission allows users to create Web Content that uses the Structure. You should enable this if you want your Structure to be used.

![Configuring Structure Permissions](./managing-structures/images/01.png)

See [Roles and Permissions](../../../../users-and-permissions/roles_and_permissions.rst) for more information on configuring Permissions.

## Copying an Existing Structure

You can copy an existing structure to create a new one. From the Structures Page, open the Actions Menu for the Structure and follow these steps:

1. Choose *Copy*.
1. Type a new *Name* for the Structure and, optionally, a new *Description*.
1. Optionally check the *Copy Templates* box if you want to copy the Web Content Templates associated with the Structure. 
1. Click *Copy*.

```tip::
   Liferay DXP generates a unique ID for the copied Structure. The new copy inherits all the attributes from the original Structure, including the name. To avoid confusing the copy with the original, use a different name for the copy.
```