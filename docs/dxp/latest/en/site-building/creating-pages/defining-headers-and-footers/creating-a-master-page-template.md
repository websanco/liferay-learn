# Creating a Master Page Template

> Available: Liferay DXP 7.3+

Although Portal includes default Master Page Templates that you can use to define the look and feel of the Headers and Footers for your Site's pages, you may want a more custom solution. You can create custom Master Page Templates for this.

Follow these steps to create a Master Page Template:

1. Open Product Menu and go to Site &rarr; *Design* &rarr; *Page Templates*.
1. Under the Masters tab, click *Add* (![Add](./../../../images/icon-add.png) to create a new Master Page Template.
1. Enter the *Name* of your new Master Page Template.
1. Click *Fragments and Widgets* (![Fragments and Widgets](./../../../images/icon-add-widget.png)) on the sidebar and add Fragments for the common elements that you require.

    ![Add the Master Page Template's common elements from the Footers and Navigation Bars sections.](./creating-a-master-page-template/images/02.png)

1. Optionally drag and drop the Drop Zone to a new location in the layout. For example, you can add a Grid with two Modules and move the Drop Zone to one Module, so users can only add Page Fragments to that portion of the Grid.

    ![You can move the Drop Zone to control where users can add Page Fragments.](./creating-a-master-page-template/images/03.gif)

1. Optionally specify which Page Fragments can be added to the Drop Zone by clicking the *Configure Allowed Fragments* button.

    - Check the Page Fragments you want to allow in this Master Page Template.
    - Check the *Select New Fragments Automatically* box to include new fragment types in the list of allowed fragments.

    ![Check and uncheck Fragments from the Allowed Fragments dialog to specify whether they can be added to a page that uses this Master Page Template.](./creating-a-master-page-template/images/04.png)

1. Click *Save* to close the *Allowed Fragments* dialog.
1. To preview your Content Page, click the *Preview* button (![Preview](../../../images/icon-preview.png)).
1. Click *Publish Master* to create the Master Page Template.

You can revert any action using the *Undo* (![Preview](../../../images/icon-undo.png)) or *Redo* (![Preview](../../../images/icon-redo.png)) buttons, or you can return to a previous version of your edits using the *History* (![Preview](../../../images/icon-time.png)) button.

```note::
  If a custom Master Page Template is used for a page, the Theme for the page is defined through the Master Page Template and can't be changed through the page's settings. See `Changing a Master Page Template's Theme <./managing-master-page-templates.md#changing-a-master-page-template-s-theme>`_ for more information.
```

## Additional Information

- [Managing Master Page Templates](./managing-master-page-templates.md)
- [Master Pages Templates](./master-page-templates.md)
