# Defining Object Relationships

Relationships are connections between different Objects that enable you to relate their entities (e.g., a field in an Object can relate to an entity in another Object). You can add relationships to any published or unpublished Object, including both system and custom Objects.

Objects provides the following types of relationships: <!--TASK: Add One to One after it's been implemented-->

**One to Many**: This type enables users to relate one of the current Object's entities to multiple entities in another Object. When selected, a new field is added to entities on the child side of the relationship (i.e., the 'many' side), while a table is added to the 'parent' side (i.e., the 'one' side). On the child side, the new field can be added to a [Fields Tab](./designing-object-layouts.md#adding-fields-tabs) in the Object's custom layout and is used to relate its entities to a single entity on the parent side of the relationship. On the parent side, the new table can be added to a [Relationships Tab](./designing-object-layouts.md#adding-relationships-tabs) in the Object's custom layout and is used to list all entities related to the current parent entity. <!--REFINE-->

**Many to Many**: This type enables users to relate multiple entities from the current Object to multiple entities in another Object. When selected, a new database table is created for both sides of the relationship. This table can be added to a [Relationships Tab](./designing-object-layouts.md#adding-relationships-tabs) in either Object's custom layout and is used to list all related entities. <!--REFINE-->

```{important}
You must create a custom layout to display relationships for Object entries. See [Designing Object Layouts](./designing-object-layouts.md) for more information. 
```

Follow these steps to add a relationships to an Object:

1. Open the *Objects* portlet.

1. Click on the desired Object.

1. Click on the *Relationships* tab, and click on the *Add* button (![Add Button]()).

1. Enter a *Label* and *Relationship Name*.

   **Label**: This value identifies the relationship in the Objects UI and can be localized after the relationship is created.

   **Relationship Name**: This value determines the relationship's name in the backend and uses camel case.<!--SME INPUT--> Once the relationship is published, this value cannot be changed.

1. Select a relationships *Type*: `One to Many` or `Many to Many`. <!--TASK: Add One to One after it's been implemented-->

1. Select an *Object* to relate to the current Object.

1. Click on *Save*.

   ```{important}
   While the Object is a draft, you can edit and remove any saved relationships from the parent side. Relationships cannot be edited from the child side.

   However, when an Object is published, any existing or newly added relationships cannot be removed. Also, users can only edit a relationship's Label and Deletion Type.
   ```

1. (Optional) When a relationship is saved, you can click on it to determine its Deletion Type. This setting determines how entry deletion is handled when the entry is related to another entry. Available options include *Prevent*, *Disassociate*, and *Cascade*.

   **Prevent** (Default): Entries on the parent side cannot be deleted if they are related to any child entries.
   **Disassociate**: Entries on the parent side can be freely deleted without affecting any related child entries.
   **Cascade**: Entries on the parent side can be freely deleted, but this also any related child entries, provided the user has the requisite permissions for the child Object.

Once saved, your new relationship can be [added to custom layouts](designing-object-layouts.md) for the related Objects. Users can then use the relationship fields and tables to relate Object entries to one another. <!--REFINE-->

## Additional Information

* [Creating Objects](./creating-objects.md)
* [Adding Fields to Objects](./adding-fields-to-objects.md)
* [Designing Object Layouts](./designing-object-layouts.md)
* [Managing Objects](./managing-objects.md)
