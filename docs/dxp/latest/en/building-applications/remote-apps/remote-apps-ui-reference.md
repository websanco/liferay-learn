# Remote Apps UI Reference

To access Remote Apps, open the *Global Menu* (![Global Menu](../../images/icon-applications-menu.png)), click on the *Applications* tab, and go to *Remote Apps*.

## Adding and Editing Remote Apps

To add a Remote App, click the *Add* button (![Add Button](../../images/icon-add.png)).

When creating or editing a Remote App, the following fields are available depending on the selected Remote App type (i.e., IFrame or Custom Element).

| Field | Description |
| --- | --- |
| Name | Determine the display name used for the Remote App entry and its widget. |
| Description | Enter a description for the Remote App entry. |
| Source Code URL | Enter a URL to the external application's source code for convenience. |
| Type | Determine how the external application is integrated into Site Pages during the render Page lifecycle: IFrame, or Custom Element. |
| URL (*IFrame*) | Enter the URL where the application is hosted. Widget renders an `<iframe>` pointing to the remote app URL. |
| HTML Element Name (*Custom Element*) | __.<!--FINISH--> This value must match the application's `ELEMENT_ID`. |
| URL (*Custom Element*) | Enter individual URLs for each of the application's hosted `.js` files. Click the *Add* button (![Add Button](../../images/icon-plus.png)) to add more URL fields. |
| CSS URL (*Custom Element*) | Enter individual URLs for each of the application's hosted `.css` files. Click the *Add* button (![Add Button](../../images/icon-plus.png)) to add more CSS URL fields. |
| Instanceable | Determine whether the Remote App's widget is instanceable. When enabled, multiple independent instances of a widget can be added to the same Page |
| Portlet Category Name | Determine the category for the Remote App's widget.  <!--FINISH--> |
| Friendly URL Mapping | Define the widget's Friendly URL, so you can refer to it using a more user readable URL. |
| Properties | Add properties to the widget that are __ during rendering. Properties added in this way are added by default to all instance's of the Remote App widget. However, you can add or override properties for individual instances of the widget in Site Pages via widget configuration. |

## Additional Information

* [Remote Apps](../remote-apps.md)
* [Understanding Remote App Types](./understanding-remote-app-types.md)
* [Creating a Liferay Remote App](./creating-a-liferay-remote-app.md)
