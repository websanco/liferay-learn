# Designing Object Views

{bdg-secondary}`Available Liferay 7.4 U10+ and 7.4 GA14+`

Object views define tables for displaying entries in an Object's application page. By default, Liferay automatically generates a default view for each published Object. This view orders all entry fields alphabetically. You can, however, design custom views to determine which Object fields are included in the table and how they're ordered.

![Design custom views for displaying Object entries.](./designing-object-views/images/01.png)

```{note}
Custom views can only be added to custom Objects. They cannot be added to system Objects.
```

Follow these steps to design a custom Object view:

1. Open the *Global Menu* (![Global Menu](../../../images/icon-applications-menu.png)), click the *Control Panel* tab, and go to *Objects*.

1. Begin editing the desired *Object*.

   ```{note}
   You can add views to both draft and published Objects.
   ```

1. Go to the *Views* tab and click the *Add* button (![Add Button](../../../images/icon-add.png)).

1. Enter a *name* and click *Save* to create a new blank view. You can now add fields to it.

   ![Create a blank view.](./designing-object-views/images/02.png)

1. Click the *view* to begin editing it.

1. In the *Basic Info* tab, determine whether to use the view as *default*.

   ```{note}
   This ensures the view is used for the Object's application page. If no custom layout is selected as default, the Object uses its automatically generated layout.
   ```

   ![Set the view as default.](./designing-object-views/images/03.png)

1. Go to the *View Builder* tab and click the *Add* button (![Add Button](../../../images/icon-add.png)).

1. Check all fields you want to include in the view table and click *Save*. Each selected field is used as a column in the application page table.

   Available options include both [custom fields](./adding-fields-to-objects.md) and general metadata fields (i.e., ID, Author, Creation Date, Modified Date, and Workflow Status).

   ```{note}
   Views do not support columns containing multiple entries (e.g., *Many to Many* relationship fields).
   ```

   ![Arrange the fields in any order you want.](./designing-object-views/images/04.png)

1. Drag and drop fields to arrange their order.

1. (Optional) [Change or localize](#renaming-columns) the display name for each view column.

1. Click *Save*.

Once saved, the custom view is used to display the Object's entries.

```{tip}
For 7.4 U21+, you can duplicate a view by clicking its *Actions* button (![Actions Button](../../../images/icon-actions.png)) and selecting *Duplicate*. This creates a complete copy of the original view with "(Copy)" appended to it's name. 
```

## Renaming Columns

{bdg-secondary}`Available Liferay DXP 7.4 U21+ and Portal 7.4 GA21+`

Follow these steps to change or localize the display name of view columns:

1. Select the desired *view* and go to the *View Builder* tab.

1. Click the *Actions* button (![Actions Button](../../../images/icon-actions.png)) for the desired column and select *Edit*.

   ![Click the Actions button for the desired column and select Edit.](./designing-object-views/images/05.png)

1. Enter a localizable name into the *Column Label* field and click *Edit*.

   ![Enter a localizable name and click Edit.](./designing-object-views/images/06.png)

1. Click *Save*.

## Setting a Default Sorting

{bdg-secondary}`Available Liferay DXP 7.4 U18+ and Portal 7.4 GA18+`

Follow these steps to set the default ordering/sorting for Object View columns:

1. Select the desired *view*, go to the *Default Sort* tab, and click the *Add* button (![Add Button](../../../images/icon-add.png)).

   ![Go to the Default Sort tab and click the Add button.](./designing-object-views/images/07.png)

1. Select a *column* and *sorting* order (i.e., Ascending or Descending).

   ![Select a column and sorting.](./designing-object-views/images/08.png)

1. Click *Save*.

Object entries are now sorted according to your default sorting.

If you create multiple default sorts, you can rearrange them to determine which is applied to the view. The view uses the sort that's placed first.

![Arrange sortings to determine which is used.](./designing-object-views/images/09.png)

## Filtering Views

Coming Soon!

## Additional Information

* [Creating and Managing Objects](../creating-and-managing-objects.md)
* [Adding Fields to Objects](./adding-fields-to-objects.md)
* [Designing Object Layouts](./designing-object-layouts.md)
