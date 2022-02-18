# Remote Apps UI Reference

Remote Apps uses Liferay's front-end infrastructure to register external JS applications with the Liferay platform and render them as Page widgets. To access Remote Apps, open the *Global Menu* (![Global Menu](../../images/icon-applications-menu.png)), click on the *Applications* tab, and go to *Remote Apps*.

To add a Remote App, click the *Add* button (![Add Button](../../images/icon-add.png)).

When creating or editing a Remote App, the following fields are available depending on the selected Remote App type (i.e., IFrame or Custom Element).

| Field | Description |
| --- | --- |
| Name | Determine the display name used for the Remote App entry and its widget. |
| Description | Enter a description for the Remote App entry. |
| Source Code URL | Enter a URL to the external application's source code for convenience. |
| Type | Determine how the external application is integrated into Site Pages during the render Page lifecycle: IFrame, or Custom Element. |
| URL (*IFrame*) | Enter the URL where the external application is hosted. The entry's widget renders the application in an `<iframe>` element that points to the remote app's URL. |
| HTML Element Name (*Custom Element*) | Determine a name for the custom HTML element. This value must match the application's `ELEMENT_ID`. <!--Q: How does this field work?--> |
| URL (*Custom Element*) | Enter individual URLs for each of the application's `.js` files. <!--Q: Should I qualify this in any other way? (e.g., does it need to be a WebDAV URL?)--> Click the *Add* button (![Add Button](../../images/icon-plus.png)) to add more URL fields. |
| CSS URL (*Custom Element*) | Enter individual URLs for each of the application's `.css` files. <!--Q: Should I qualify this in any other way? (e.g., does it need to be a WebDAV URL?)--> Click the *Add* button (![Add Button](../../images/icon-plus.png)) to add more CSS URL fields. |
| Instanceable | Determine whether the Remote App's widget is instanceable. When enabled, multiple instances of a widget can be added to the same Page. |
| Portlet Category Name | Determine the widget's location in the Widget menu. The default value is *Remote Apps*. |
| Friendly URL Mapping | Define the widget's Friendly URL Mapping, so you can refer to it using a more user readable URL. |
| Properties | Define properties that are included in the Remote App's widget. Properties added in this way are included by default in all instance's of the widget. However, you can add or override properties for individual instances of the widget in Site Pages via widget configuration. |

## Additional Information

* [Remote Apps](../remote-apps.md)
* [Understanding Remote App Types](./understanding-remote-app-types.md)
* [Creating a Liferay Remote App](./creating-a-liferay-remote-app.md)
