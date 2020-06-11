# Managing Structures

Web content structures can inherit characteristics from other structures. A child structure inherits all the parent's fields and settings. You can use this functionality to create a structure from one that already exists. For example, if you have a *Sports Article* structure and you want to create a new *In-depth Sports Article* structure, set *Sports Article* as the parent structure. The *In-depth Sports Article* inherits all its fields, letting you add new ones for more in-depth information.
<!-- Need to describe how to set this parent-child relationship in the UI -->

```note::
   Due to import/export operations, it's possible to have both a global and a Site-scoped structure with the same `structureKey`. If this happens, the Site-scoped structure takes precedence, and you can't access the global structure from that Site.
```

You can manage your structures using the different options in the *Actions* (![Action Menu](../../../../../../images/icon-actions.png) menu.

## Edit
Choose *Edit* to edit and configure the structure's content. For more information, read [Editing and Configuring Structures' Content](./03-editing-and-configuring-structures-content.md).

## Edit Default Values
Using this option you can set default values for the elements of your structure. For example, for a structure that used for confidential memos, you may want to add the "confidential" tag for all the articles created with that structure. Or you may want to make the content of all confidential memos non-searchable. 

To edit default values:

1. From the *Structures* tab, click the *Actions* menu and choose *Edit Default Values*
2. In the *Structure Default Values* page, configure the default values for your elements.
3. If you want to restore the original values of the structure, click *Reset Values*.
4. Click *Save*.

```tip::
   You can also use the *Predefined Value* setting for the structure field to set default values. For more information, read [Editing and Configuring Structures' Content](./03-editing-and-configuring-structures-content.md)
```

## Manage Templates
To understand how you can create templates with structures, see [Adding Templates with Structures](02-adding-templates.md)

## Permission
You configure permissions in structures in the same way you configure permissions for other elements. For more information, read [01-introduction-to-roles-and-permissions].

Consider the following information when setting permissions for structures:

* As a best practice, assign permissions to roles.
* The *View* permission allows users to view structures.
* Most users should not be able to edit structures.

![Configuring Structure Permissions](./managing-structures/images/01.png)

## Copy
You can use an existing structure to create a new one. For more information, read [creating-structures](./creating-structures.md)

## Delete
Deletes the structure.
