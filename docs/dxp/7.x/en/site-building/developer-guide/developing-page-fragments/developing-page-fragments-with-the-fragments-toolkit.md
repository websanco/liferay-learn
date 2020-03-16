# Creating Page Fragments with the Fragments Toolkit

You can use the Fragments Toolkit to create Fragments and Collections from the Command Line.

## Overview

1. [Deploy an Example](#deploy-an-example)
1. [Walk Through an Example](#walk-through-an-example)
1. [Additional Information](#additional-information)

## Deploy an Example

> Liferay DXP 7.3+

First you must deploy an example contributed Fragment Collection. Follow these steps:

1. Start the Docker container with a bind mount:

    ```bash
    docker run -d -it -p 8080:8080 -p 8000:8000 --name mylrdev -v C:\Users\liferay\Desktop\liferay-docker:/mnt/liferay liferay/portal:7.3.0-ga1
    ```

1. Download and unzip the [example Fragment Collection](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/dev/01-developing-page-fragments/developing-page-fragments-using-the-fragments-toolkit/1583876874liferay92.zip):

    ```bash
    curl https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/dev/01-developing-page-fragments/developing-page-fragments-using-the-fragments-toolkit/1583876874liferay92.zip
    
    unzip 1583876874liferay92.zip
    ```

1. Build the Fragment Collection's ZIP and press Enter to accept the defaults for the prompts to configure the ZIP for [auto-deployment](./auto-deploying-fragments.md). Copy the Fragment Collection's ZIP to the `[host_folder]/deploy` folder for your Docker image's [bind mount](TODO), or create the `[host_folder]/deploy` folder if it doesn't exist.]. Alternatively, you can [import the Fragment manually](TODO:managing-fragments) instead.

    ```bash
    cd my-fragments
    npm run compress
    
    ? Add deployment descriptor? Yes
    ? Deployment descriptor company Web ID? liferay.com
    ? Deployment descriptor group key? Guest
    
    cp liferay-fragments.zip path/to/your/bind/mount
    ```
    
    ```note::
    You must restart the Docker container if you're creating the `[host_folder]/deploy` folder for the first time in your bind mount.
    ```

1. Confirm the deployment to the Liferay Docker container console. The log message below should appear in the Docker console:

    ```bash
    INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:263] Processing liferay-fragments.zip
    ```

1. Verify that the Fragment Collection is available. Open your browser to `https://localhost:8080`, and open the Product Menu and go to *Site Builder* &rarr; *Page Fragments* under the Site Menu. The Collection is listed with the other Collections.

    ![The Collection is available.](./developing-page-fragments-with-the-fragments-toolkit/images/01.png)

Great! You successfully deployed a Fragment Collection. Next, you'll walk through the example and learn how it works.

## Walk Through the Example

* [Generate a Fragment Collection](#generate-a-fragment-collection)
* [Add Fragments to the Collection](#add-fragments-to-the-collection)

### Generate a Fragment Collection

1. Install the Fragments Generator using the instructions in the project's [README](https://github.com/liferay/generator-liferay-fragments/blob/master/README.md) if it's not installed.
1. Run the Fragments Generator and answer No (n) to creating a sample project:

    ```bash
    yo liferay-fragments
    ```

    After answering the prompts the project structure is created:
    
    ```bash
    ? Project name My Fragments
    ? Add sample content? No
    
    Creating directory
       create src\.gitkeep
       create .editorconfig
       create .gitignore
       create package.json
       create README.md    
    ```

1. Create a Collection for the Fragment(s) in the project:

    ```bash
    C:\Users\liferay\Desktop\projects>cd my-fragments
    
    C:\Users\liferay\Desktop\projects\my-fragments>npm run add-collection
    
    > my-fragments@1.0.0 add-collection C:\Users\liferay\Desktop\projects\my-fragments
    > yo liferay-fragments:collection
    
    ? Collection name (required) My Collection
    ? Collection description (optional)
       create src\my-collection\collection.json
    ```

### Add Fragments to the Collection

1. Navigate to the Collection's folder and create a Fragment:

    ```bash
    cd src\my-collection
    npm run add-fragment
    ```
    
    The CLI starts the process:
    
    ```bash
    > my-fragments@1.0.0 add-fragment C:\Users\liferay\Desktop\projects\my-fragments
    > yo liferay-fragments:fragment
    ```

1. Enter a name and select a Fragment type.

    ```bash
    ? Fragment name (required) Fragment 1
    ? Fragment type Component
    ```

    ```note::
      In versions prior to Liferay DXP 7.3, you can add a Section or a Component. In Liferay Portal 7.3+, all Page Fragments are Components.
    ```

1. Press Enter to select the Collection you just created (`My Collection`).

    ```bash
    ? Choose a collection (my-collection)
       create src\my-collection\fragment-1\index.html
       create src\my-collection\fragment-1\main.js
       create src\my-collection\fragment-1\styles.css
       create src\my-collection\fragment-1\fragment.json
       create src\my-collection\fragment-1\configuration.json
    ```

1. Modify the Fragment's CSS (styles.css), JavaScript (main.js), HTML 
(index.html), and provide [configuration options](./making-a-page-fragment-configurable.md) 
(configuration.json). See the Fragment in the [example Fragment Collection](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/dev/01-developing-page-fragments/developing-page-fragments-using-the-fragments-toolkit/1583876874liferay92.zip/) for reference.

```tip::
    Give the main wrapper element for the Component in `index.html` a unique ID so it doesn't conflict with other Components on the page.
```

## Additional Information

### Collection Format

The generated Fragment Collection has the project structure below. You can manually create this project structure with your own tools if you wish and compress it into a ZIP file.

* `collection.json`: a text file which describes your collection with the format `{"name":"collection name>","description":"collection description"}`.

* `language.properties`: the language keys defined for the collection.

    * `[fragment-name]/`: a folder containing all of the files for a Page Fragment.

        * `fragment.json`: a text file that describes a Page Fragment with this format:

          ```json
          {
              "cssPath": "styles.css",
              "configurationPath": "configuration.json",
              "htmlPath": "index.html",
              "jsPath": "main.js",
              "name": "fragment name",
              "type": "fragment type",
              "readyOnly": "false",
              "cacheable": "false"
           }
           ```

          Update the `*Path` properties in your `fragment.json` file if you change the CSS, JavaScript, or HTML file names.

        * `styles.css`: the CSS source for the Fragment

        * `index.html`: the HTML source for the Fragment

        * `configuration.json`: a JSON file that defines the Fragment's configuration

        * `main.js`: the JavaScript source for the Fragment

        * `thumbnail.png`: the thumbnail that's displayed when the Fragment is in a list

    * `resources/`: a folder containing any additional images or other external files needed for the Fragment. See [Including Default Resources in Fragments](./including-default-resources-with-fragments.md) for more information.