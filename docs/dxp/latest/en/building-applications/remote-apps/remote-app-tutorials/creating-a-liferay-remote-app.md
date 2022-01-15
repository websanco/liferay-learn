# Creating a Liferay Remote App

> Available for Liferay DXP 7.4+

Remote Apps is an application for integrating single-page applications into your Liferay solutions. It uses Liferay's front-end infrastructure to register external applications with the Liferay platform and render them as widgets. To simplify remote app creation, Liferay provides the [`create_remote_app.sh`](https://raw.githubusercontent.com/liferay/liferay-portal/master/tools/create_remote_app.sh) script. This script can be used to automatically generate React and Vue applications compatible with Liferay Remote Apps.

In this tutorial, you'll use `create_remote_app.sh` to create a simple "Hello World" React application. Then, you'll register it with Liferay Remote Apps using the Custom Element type<!--ALT: as a custom element-->. Once registered, you can deploy it as a Page widget.

## Prerequisites

Running `create_remote_app.sh` requires the latest versions of [Node.JS](https://nodejs.org/), [NPM](https://www.npmjs.com/), and [YARN](https://classic.yarnpkg.com/).

If you don't have YARN installed, but already have NodeJS and NPM, run this command:

```bash
npm install -g yarn.
```

## Run the `create_remote_app.sh` Script

When calling `create_remote_app.sh`, you must provide a valid HTML element name and specify the desired JS framework (e.g., `react`, `vue2`, `vue3`).

For this tutorial, use this [cURL command](https://github.com/liferay/liferay-portal/blob/master/tools/create_remote_app.sh.README.markdown) to run `create_remote_app.sh`.

```bash
curl -Ls https://github.com/liferay/liferay-portal/raw/master/tools/create_remote_app.sh | bash -s liferay-hello-world react
```

This calls the script with two arguments: a custom HTML element name (`liferay-hello-world`) and the desired JS framework (`react`).

When finished running, the script automatically creates a new React application in a folder named `liferay-hello-world`, which includes these elements:

```bash
liferay-hello-world
├── node_modules
├── README.md
├── package.json
├── public
│   └── index.html
├── src
│   ├── common
│   │   ├── services
│   │   │   └── liferay
│   │   │       ├── api.js
│   │   │       └── liferay.js
│   │   └── styles
│   │       ├── hello-world.scss
│   │       ├── index.scss
│   │       └── variables.scss
│   ├── index.js
│   └── routes
│       ├── hello-bar
│       │   ├── components
│       │   └── pages
│       │       └── HelloBar.js
│       ├── hello-foo
│       │   ├── components
│       │   └── pages
│       │       └── HelloFoo.js
│       └── hello-world
│           ├── components
│           └── pages
│               └── HelloWorld.js
└── yarn.lock
```

### Understanding the `index.js` File

   ```{literalinclude} ./creating-a-liferay-remote-app/resources/liferay-h5v7.zip/liferay-hello-world/src/index.js
       :language: js
   ```

This generated `index.js` file includes two customizations that are necessary for using the application with Liferay Remote Apps.

* WebComponent: In line 21, the application is declared a WebComponent so that it can connect to Remote Apps.
* ELEMENT_ID: In line 30, ELEMENT_ID is set to `liferay-hello-world`, instead of the conventional `<div id="root" />`. This is because a Remote App's HTML Element Name must match the application's `ELEMENT_ID`, and `<div id="root" />` does not work for this purpose.

### Understanding the React Routes

Routes are alternative  <!---->. The provided code includes three routes: `hello-world` (default), `hello-foo`, and `hello-bar`.

Using Liferay Portal's methods for creating Routes and Pages, you can use Remote App properties to determine which of these routes is displayed/used in Liferay. []()

## Building the Remote Application

After running `create_remote_app.sh`, navigate to the new `liferay-hello-world` folder and build the application:

```bash
yarn build
```

Confirm the build is successful.

```bash
Creating an optimized production build...
Compiled successfully.

File sizes after gzip:

  43.51 kB  build/static/js/main.114dde4a.js
  121 B     build/static/css/main.9877909d.css
```
<!-- 
  42.1 KB  build/static/js/2.39edd8d0.chunk.js
  783 B    build/static/js/runtime-main.8cc2266b.js
  528 B    build/static/js/main.ceffd519.chunk.js
  128 B    build/static/css/main.c3017b39.chunk.css
-->
This compiles the code into a `build` folder including these contents.

```bash
build
├── asset-manifest.json
├── index.html
└── static
    ├── css
    │   ├── main.9877909d.css
    │   └── main.9877909d.css.map
    └── js
        ├── main.114dde4a.js
        ├── main.114dde4a.js.LICENSE.txt
        └── main.114dde4a.js.map
```
<!-- 
build
├── asset-manifest.json
├── index.html
└── static
    ├── css
    │   ├── main.c3017b39.chunk.css
    │   └── main.c3017b39.chunk.css.map
    └── js
        ├── 2.39edd8d0.chunk.js
        ├── 2.39edd8d0.chunk.js.LICENSE.txt
        ├── 2.39edd8d0.chunk.js.map
        ├── main.ceffd519.chunk.js
        ├── main.ceffd519.chunk.js.map
        ├── runtime-main.8cc2266b.js
        └── runtime-main.8cc2266b.js.map
-->


```{note}
Unique file names are generated for every build. When testing your custom applications, remember to update them after future builds.
```

## Hosting the Application Files

This tutorial uses the Content Element type for the remote app. For this reason, the React application's `main.*.js` and `main.*.css` files must be hosted in a location accessible to Liferay Remote Apps. These files can be hosted in a remote server or in a data storage system with a dedicated URL. For our purposes, we'll upload them to Liferay's Document Library and copy their WebDAV URLs.<!--ALT: ...and host them using Resource URLs.-->

Follow these steps:

1. Start up a new Liferay DXP 7.4+ container and log in.

   ```docker
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
   ```

1. Open the *Site Menu* (![Site Menu]()), expand *Content & Data*, and go to *Documents and Media*.

1. Click the *Add* button (![Add Button]()) and select *Multiple Files Upload*.

1. Drag and drop the `main.*.js` and `main.*.css` files into the upload area.

   Alternatively, use *Select Files* to upload them.

   ![]()

1. Click *Publish*.

This adds the files to Documents and Media and assigns them unique URLs, which you'll use to create the Remote App.

To view each file's URL, click the *Info* icon (![Info Icon]()) and select a file. Copy each file's *WebDAV URL* and save them for use in the next step.

<!-- 
For example: 

* `http://localhost:8080/webdav/guest/document_library/main.114dde4a.js` 
* `http://localhost:8080/webdav/guest/document_library/main.9877909d.css`
-->

![]()

## Registering the Application with Remote Apps

Follow these steps to create a Remote App for the React application:

1. Open the *Global Menu* (![Global Menu]()), click on the *Applications* tab, and go to *Remote Apps*.

1. Click the *Add* button (![Add Button]()).

1. Enter these values:

   | Field | Value |
   | --- | --- |
   | Name | Liferay Hello World |
   | Type | Custom Element |
   | HTML Element Name | `liferay-hello-world` |
   | URL | WebDAV URL for `main.*.js` |
   | CSS URL | WebDAV URL for `main.*.css` |
   | Portlet Category Name | Remote Apps |

   ![Create a Custom Element Remote App for the React application.]()

1. Click *Save*.

Once saved, Liferay creates a widget that you can deploy to Site Pages. The widget is listed under the selected Portlet Category Name, and you can use it like any other Page widget.

![]()

## Additional Information

* []()
* []()
* []()
