# Remote Apps UI Reference

Remote Apps use Liferay's front-end infrastructure to register external JavaScript applications and render them as Page widgets. To access Remote Apps, open the *Global Menu* (![Global Menu](../../images/icon-applications-menu.png)), click the *Applications* tab, and go to *Remote Apps*.

To add a remote app, click the *Add* button (![Add Button](../../images/icon-add.png)).

When creating or editing a remote app, the following fields are available depending on the selected remote app type (i.e., IFrame or Custom Element).

| Field | Description |
| --- | --- |
| Name | The display name used for the remote app entry and its widget. |
| Description | A description for the remote app entry. |
| Source Code URL | A URL to the external application's source code for convenience. |
| Type | Currently, Liferay supports two types: IFrame and Custom Element. These types determine the contract between the Liferay platform and the external application. |
| URL (*IFrame*) | A URL to where the external application is hosted. The entry's widget renders the application in an `<iframe>` element that points to the remote app's URL. |
| HTML Element Name (*Custom Element*) | The name for the custom element that's declared in its `.js` file. The `.js` file that creates the custom element must associate it with a name. This name must match the HTML Element Name value. |
| Use ES Modules (*Custom Element*) | Determine whether the browser sees the application's JS injection as an [ES module](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Modules). When enabled, Liferay uses `<script src="js url" type="module">` when building the remote app so the application is treated as an ES6 browser module. |
| URL (*Custom Element*) | Enter individual URLs for each of the application's `.js` files. Click the *Add* button (![Add Button](../../images/icon-plus.png)) to add more URL fields. |
| CSS URL (*Custom Element*) | Enter individual URLs for each of the application's `.css` files. Click the *Add* button (![Add Button](../../images/icon-plus.png)) to add more CSS URL fields. |
| Instanceable | Determine whether the remote app's widget is instanceable. When enabled, multiple instances of a widget can be added to the same Page. |
| Portlet Category Name | Enter the widget's category in the Widget menu. The default value is *Remote Apps*. |
| Friendly URL Mapping | Define the widget's Friendly URL Mapping, so you can refer to it using a more user readable URL. |
| Properties | Define default properties that are included in all instances of the app's widget. These properties are passed to the application by the widget as additional URL attributes so they be accessed programmatically. If desired, you can configure individual widget instances to override these entry properties. |

## Additional Information

* [Remote Apps](../remote-apps.md)
* [Understanding Remote App Types](./understanding-remote-app-types.md)
* [Creating a Basic Remote App](./remote-apps-tutorials/creating-a-basic-remote-app.md)
