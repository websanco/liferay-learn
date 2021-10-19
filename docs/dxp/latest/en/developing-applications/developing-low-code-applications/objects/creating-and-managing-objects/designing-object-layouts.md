# Designing Object Layouts

An Object layout defines how its fields and relationships are organized and displayed when creating or editing an entry. By default, applications created with the Objects portlet use an automatically generated layout for their entries. This layout has a single tab that displays all Object fields in alphabetical order. However, you can design custom layouts to determine how and where elements are displayed.

```{important}
The automatically generated layout does not display Object relationships. To display relationships, you must create a custom layout with a dedicated relationships tab.
```

## Adding Object Entry Layouts

Follow these steps to add a Layout to an Object:

1. Open the *Objects* portlet, and click on the desired *Object*.

1. Click on the *Layouts* tab, and click on the *Add* button (![Add Button]()).

1. Enter a *Name*, and click on *Save*. This creates a blank layout.

You can now add tabs to your custom layout and determine how the Object's fields and relationships are organized for entry creation and editing.

## Designing Layout Tabs

Object entry elements can be organized into two types of tabs:

[**Fields**](#adding-fields-tabs): This type of tab is used to display Object entry fields. This includes fields created directly in the Object or those inherited from a *One to Many* relationship. <!--TASK: Add in One to One after implemented-->

[**Relationships**](#adding-relationships-tabs): This type of tab is used to display relationship tables and must be used for displaying the parent side of *One to Many* relationships and both sides of *Many to Many* relationships.

To design your layout, repeat the following steps until you've added all the desired [fields](#adding-fields-tabs) and [relationships](#adding-relationships-tabs). <!--SME INPUT: Are users supposed to be able to drag and drop layout elements (e.g, tabs, blocks, fields)?-->

```{important}
Only the first tab is displayed during Object entry creation. Other tabs are displayed only when editing the Object entry.

Also, the first tab in a layout cannot contain relationships, and a Relationships tab can only be added to a layout after it has a Fields tab.
```

### Adding Fields Tabs

Follow these steps to add a Fields tab:

1. Click on the desired layout, go to the *Layout* tab, and click on *Add Tab*.

1. Enter a *Label* for the tab. This value determines the tab's display name in the Liferay UI.

1. For tab type, select *Fields* and click on *Save*.

1. Click on *Add Block* for the tab to define a sub-section.

   ```{note}
   Fields cannot be added directly to a tab; they must be organized into blocks.
   ```

1. Enter a *Label* for the block, and click on *Save*. This value determines the block's display name in the Liferay UI.

1. Use the toggle button to determine whether or not the block is *collapsible*.

1. Click on *Add Field* to add fields to the block.

1. Select the desired field, and determine whether the field is displayed in one, two, or three columns.

   ```{note}
   A field can only be used once in a layout.
   ```

1. Click on *Save*.

### Adding Relationships Tabs

Follow these steps to add a Relationships tab:

1. Click on the desired layout, go to the *Layout* tab, and click on *Add Tab*.

1. Enter a *Label* for the tab. This value determines the tab's display name in the Liferay UI.

1. For tab type, click on *Relationships*, and then select from available *One to Many* or *Many to Many* relationships. This determines the relationship table displayed in the tab.

   ```{note}
   You can only select the Relationships type if the layout has at least one Fields tab. A relationships tab cannot be the first tab in an Object layout. This is because relationships cannot be set at entry creation, since an entry must first exist before it can be related to another Object entity. <!--REFINE-->
   ```

1. Click on *Save*.

## Selecting a Default Layout

Once you've added at least one required field to the Object Layout, you can set it as the Object's default Layout. This ensures your custom layout is used for displaying and creating Object entries. If no custom layout is select as the Object's default layout, the Object uses its automatically generated layout.

1. Click on the desired layout.

1. In the Basic Details tab, check the *Default* box.

1. Click on *Save*.

The layout will now be used for creating and editing the Object's entries.

## Additional Information

* [Creating Objects](./creating-objects.md)
* [Adding Fields to Objects](./adding-fields-to-objects.md)
* [Defining Object Relationships](./defining-object-relationships.md)
* [Managing Objects](./managing-objects.md)
