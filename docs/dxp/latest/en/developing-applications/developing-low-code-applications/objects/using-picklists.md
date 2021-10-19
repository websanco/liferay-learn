# Using Picklists

Picklists is an application/framework<!--w/c--> for creating standard lists of values that are understood across the Liferay Portal. The process of creating a Picklist includes creating the initial list and adding items to it. Once these values are defined, they can be used in any supported applications.

Follow these steps to create a Picklist:

1. Open the *Global Menu* (![Global Menu]()), go to the *Control Panel* tab, and click on *Picklists*.

1. Click on the *Add* button (![Add Button]()).

1. Enter a *Name* for the Picklist. This value is used to identify the list in the Liferay UI and can be localized after creation.

1. Click on the new Picklist.

1. Under Items, click on the *Add* button (![Add Button]()).

1. In the modal window, enter a *Name* and *Key* for the item.

   **Name**: This determines the item's display name and can be localized after creation.

   **Key**: This determines the standard value saved/understood<!--w/c--> by applications in the backend and uses camel case.

   ```{note}
   Once created, an Item's key cannot be changed, but you can edit its name at any time.
   ```

1. Click on *Save*. This immediately updates the Picklist with the new item.

1. Repeat the above steps to add additional items to the list.

Once a list is created, you can select it for Object fields with the Picklist value type. When users access this field in an Object's layout, it appears as a drop-down menu that lists the Picklist's items.

```{important}
While a list is used by an Object field, it cannot be deleted. However, list items can be edited and removed.

Also, updating or deleting a Picklist item will automatically update all Object entries using the item value.
```

## Additional Information

* [Adding Fields to Objects](./creating-and-managing-objects/adding-fields-to-objects.md)
* [Designing Object Layouts](./creating-and-managing-objects/designing-object-layouts.md)
