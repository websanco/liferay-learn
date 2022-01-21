# Using Object Data with React Charts

With Liferay Objects, you can build and extend applications without needing to develop code or deploy modules. The following tutorial demonstrates how to use Liferay [Objects](../../objects.md), [Headless APIs](../understanding-object-integrations/headless-framework-integration.md), and Remote Apps with React [FusionCharts](https://www.fusioncharts.com/dev/getting-started/react/your-first-chart-using-react) to create dynamic visuals for data dashboards.

First, you'll set up a new DXP instance with [CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS) enabled for Object API calls. Then, you'll create an Object for receiving and storing data. After adding data to the Object using REST APIs, you'll download and build the provided React application. Once the code is compiled, you'll host the generated `.js` file in the Liferay Document Library and copy its WebDAV URL. Finally, you'll use this URL to create a Remote App for the React chart and deploy it as a Page widget.

The chart is configured to automatically call the Object via Headless API every five seconds, which returns the Object's data and updates the chart dynamically.

![Use Liferay Objects with React charts to create dynamic visuals for data dashboard.](./using-object-data-with-react-charts/images/01.gif)

## Setting Up Liferay DXP

Before proceeding, start up a new Liferay DXP 7.4+ container.

```docker
docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
```

Once started, follow these steps to add the `/o/c/*` URL pattern to the *Default Portal CORS Configuration*:

1. Open the *Global Menu* (![Global Menu](../../../images/icon-applications-menu.png)), click on the *Control Panel* tab, and go to *System Settings* &rarr; *Security Tools*.

1. Go to the *Portal Cross-Origin Resource Sharing (CORS)* tab and click *Default Portal CORS Configuration*.

   ![Click on Default Portal CORS Configuration](./using-object-data-with-react-charts/images/02.png)

1. Add a *URL Pattern* with the `/o/c/*` value and click *Save*. This enables CORS for all Object APIs.

   ![Add the /o/c/* URL Pattern for Object APIs.](./using-object-data-with-react-charts/images/03.png)

## Creating an Object for the React Chart

Follow these steps to create an Object for the React chart:

1. Open the *Global Menu* (![Global Menu](../../../images/icon-applications-menu.png)), click on the *Control Panel* tab, and go to *Objects*.

1. Click the *Add* button (![Add Button](../../../images/icon-add.png)) and enter these values.

   | Field | Value |
   | --- | --- |
   | Label | X3J8-Object |
   | Plural Label | X3J8-Objects |
   | Name | X3J8Object |

   ```{note}
   These values are necessary, since they are used in the provided React App.
   ```

1. Select the new *Object* draft, click on the *Field* tab, and add these *fields*.

   | Label | Field Name | Type | Required |
   | --- | --- | --- | --- |
   | Label | label | String | &#10004; |
   | Value | value | Integer | &#10004; |

1. Click on the *Details* tab and click *Publish*.

[Publishing an Object](../creating-and-managing-objects/creating-objects.md#publishing-object-drafts) creates and activates a new application for receiving and storing data. You can now access it via the Liferay UI or Headless APIs.

## Adding Data to the Object

Follow these steps to add data to your newly published Object using Headless APIs:

1. Open the Liferay *API Explorer* (i.e., `localhost:8080/o/api`), click on the *REST Services* drop-down menu, and select `c/x3j8objects`.

1. Enter this script into the request body for the batch `POST` API.

   ```json
   [
     {
       "label": "FOO",
       "value": "250"
     },
     {
       "label": "BAR",
       "value": "200"
     },
     {
       "label": "GOO",
       "value": "150"
     },
     {
       "label": "BAZ",
       "value": "100"
     },
     {
       "label": "QUX",
       "value": "50"
     }
   ]
   ```

1. Click *Execute* to add the data entries to the Object.

Once you've finished adding data, you can set up the provided React server. This includes a FushionChart implementation that makes API calls to X3J8-Object and displays its data.

## Setting Up the React Chart

Follow these steps to download and build the React application:

1. Download and unzip the [React project](./liferay-x3j8.zip).

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/building-applications/objects/building-solutions-with-objects/liferay-x3j8.zip -O
   ```

   ```bash
   unzip liferay-x3j8.zip -d liferay-x3j8
   ```

   ```bash
   cd liferay-x3j8
   ```

1. Verify you have `node` and `yarn` installed. If you don't, run the following command and follow the prompts:

   ```bash
   ./setup_tutorial.sh
   ```

1. cd x3j8-remote-app

1. (Optional) Start the React server.

   ```bash
   yarn start
   ```

   Once started, you can go to `localhost:3000` to view the React chart. It's configured to call the `c/x3j8objects` service using Basic Authentication and retrieve the Object's data every five seconds.

   ![View the React chart at localhost:3000.](./using-object-data-with-react-charts/images/04.png)

1. Build the React project.

   ```bash
   yarn build
   ```

   This command creates an optimized production build, which includes the files necessary for running the application as a Remote App.

1. Verify the build is successful and take note of the application's `.js` file.

   ```bash
   Creating an optimized production build...
   Compiled successfully.

   File sizes after gzip:

     523.77 kB  build/static/js/main.5a6819d5.js
     20 B       build/static/css/main.31d6cfe0.css
   ```

   ```{note}
   The `.css` file is unnecessary for this tutorial.
   ```

After compiling the code, host the application's `.js` file and copy it's WebDAV URL.

## Hosting the Application's `.js` File

Follow these steps to host the React Chart's `.js` file in the Liferay Document Library:

1. Open the *Site Menu* (![Site Menu](../../../images/icon-product-menu.png)), expand *Content & Data*, and go to *Documents and Media*.

1. Drag and drop the `.js` file into the upload area.

   When uploaded to the Document Library, the file is assigned a unique WebDAV URL, which you'll use to create the Remote App.

1. Click the *Info* icon (![Info Icon](../../../images/icon-information.png)) and select the *uploaded file*.

1. Copy the file's *WebDAV URL* and save it for use in the next step.

   For example, `http://localhost:8080/webdav/guest/document_library/main.5a6819d5.js`.

   ![Copy the .js file's WebDAV URL.](./using-object-data-with-react-charts/images/05.png)

## Creating a Remote App for the React Chart

Follow these steps to create a Remote App for the React chart:

1. Open the *Global Menu* (![Global Menu](../../../images/icon-applications-menu.png)), click on the *Applications* tab, and go to *Remote Apps*.

1. Click the *Add* button (![Add Button](../../../images/icon-add.png)).

1. Enter these values:

   | Field | Value |
   | --- | --- |
   | Name | X3J8-Remote-App |
   | Type | Custom Element |
   | HTML Element Name | `x3j8-remote-app` |
   | URL | WebDAV URL for the `.js` file |
   | Portlet Category Name | Remote Apps |

1. Click *Save*.

Once Saved, Liferay creates a widget for the Remote App that you can deploy to Site Pages. This widget is listed under the application's Portlet Category Name (i.e., *Remote Apps* for this tutorial).

![You can deploy the remote app widget to Site Pages.](./using-object-data-with-react-charts/images/06.png)

## Additional Information

* [Objects Overview](../../objects.md)
* [Headless Framework Integration](../understanding-object-integrations/headless-framework-integration.md)
* [Setting Up CORS](../../../installation-and-upgrades/securing-liferay/securing-web-services/setting-up-cors.md)
