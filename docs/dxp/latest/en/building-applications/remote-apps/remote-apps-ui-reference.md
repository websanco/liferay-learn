# Remote Apps UI Reference

Remote Apps uses Liferay's front-end infrastructure to register external JS applications with the Liferay platform and render them as Page widgets. To access Remote Apps, open the *Global Menu* (![Global Menu](../../images/icon-applications-menu.png)), click on the *Applications* tab, and go to *Remote Apps*.

To add a Remote App, click the *Add* button (![Add Button](../../images/icon-add.png)).

When creating or editing a Remote App, the following fields are available depending on the selected Remote App type (i.e., IFrame or Custom Element).

| Field | Description |
| --- | --- |
| Name | Determine the display name used for the Remote App entry and its widget. |
| Description | Enter a description for the Remote App entry. |
| Source Code URL | Enter a URL to the external application's source code for convenience. |
| Type | Determine the app's type. Currently, Liferay supports two types: IFrame and Custom Element. These types determine the contract between the Liferay platform and the external application. |
| URL (*IFrame*) | Enter the URL where the external application is hosted. The entry's widget renders the application in an `<iframe>` element that points to the remote app's URL. |
| HTML Element Name (*Custom Element*) | Enter the name for the custom element that's declared in its `.js` file. The `.js` file that creates the custom element must associate it with a name. This name must match the HTML Element Name value. |
| Use ES Modules (*Custom Element*) | Enable or disable [ES module](https://nodejs.org/api/esm.html) support for the application. |
| URL (*Custom Element*) | Enter individual URLs for each of the application's `.js` files. Click the *Add* button (![Add Button](../../images/icon-plus.png)) to add more URL fields. |
| CSS URL (*Custom Element*) | Enter individual URLs for each of the application's `.css` files. Click the *Add* button (![Add Button](../../images/icon-plus.png)) to add more CSS URL fields. |
| Instanceable | Determine whether the Remote App's widget is instanceable. When enabled, multiple instances of a widget can be added to the same Page. |
| Portlet Category Name | Determine the widget's location in the Widget menu. The default value is *Remote Apps*. |
| Friendly URL Mapping | Define the widget's Friendly URL Mapping, so you can refer to it using a more user readable URL. |
| Properties | Define default properties that are included in all instances of the app's widget. These properties are passed to the application by the widget as additional URL attributes so they be accessed programmatically. If desired, you can configure individual widget instances to override these entry properties. |

## Additional Information

* [Remote Apps](../remote-apps.md)
* [Understanding Remote App Types](./understanding-remote-app-types.md)
* [Creating a Basic Remote App](./creating-a-basic-remote-app.md)
