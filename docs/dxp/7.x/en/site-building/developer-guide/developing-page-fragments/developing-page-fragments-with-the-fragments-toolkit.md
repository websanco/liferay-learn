# Creating Page Fragments with the Fragments Toolkit

<!-- This article has the same issue as the prior one. Please see the comment there, and please let me know if you have any questions. -->

You can use the Fragments Toolkit to create Fragments and Collections from the Command Line. Here you'll learn how to use the Fragments Toolkit to create Fragments. This just takes a few steps:

1. Create a Collection.
1. Create a Fragment in the Collection.
1. Compress the Fragment Collection and copy it to the Docker Container.

This example uses a Docker image with a fresh install of Liferay DXP.

## Create a Collection

1. Install the Fragments Generator using the instructions in the project's [README](https://github.com/liferay/generator-liferay-fragments/blob/master/README.md) if it's not installed.
1. Run the Fragments Generator with the `yo liferay-fragments` command and answer No (n) to creating a sample project:

    ```bash
    yo liferay-fragments
    
    ? Project name My Fragments
    ? Add sample content? No
    ```

    After answering the prompts the project structure is created:
    
    ```bash
    Creating directory
       create src\.gitkeep
       create .editorconfig
       create .gitignore
       create package.json
       create README.md    
    ```

1. Create a Collection for the Fragment(s) in the project with the `npm run add-collection` command and answer the prompts to provide a name and an optional descriptions:

    ```bash
    C:\Users\liferay\Desktop\projects>cd my-fragments
    
    C:\Users\liferay\Desktop\projects\my-fragments>npm run add-collection
    
    > my-fragments@1.0.0 add-collection C:\Users\liferay\Desktop\projects\my-fragments
    > yo liferay-fragments:collection
    
    ? Collection name (required) My Collection
    ? Collection description (optional)
       create src\my-collection\collection.json
    ```

## Create a Fragment in the Collection

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

    ```note::
        The ``fragment.json`` defines the paths to the Fragment's CSS, HTML, and JavaScript. If you change any of these file names, update their path in the ``fragment.json`` to reflect the change.
    ```

1. Write the Fragment's HTML (index.html):

    ```html
    <div class="fragment-1">
      Fragment 1
      <img src="[resources:01.png]">
    </div>
    ```

    ```tip::
        Give the main wrapper element for the Component in ``index.html`` a unique ID so it doesn't conflict with other Components on the page.
    ```

    The HTML references an included resource. See [Including Default Resources in Fragments](./including-default-resources-in-fragments.md) for more information.

1. Write the Fragment's CSS (styles.css):

    ```css
    .fragment-1 {
      color: inherit;
    }
    ```

1. Write the Fragment's JavaScript (main.js):

    ```javascript
    const content = fragmentElement.querySelector('.fragment-1');
    ```

    ```note::
        The Fragment and configuration object are passed in as arguments in the JavaScript and are available as the parameters ``fragmentElement`` and ``configuration``.
    ```

You can optionally provide [configuration options](./making-a-page-fragment-configurable.md) (configuration.json) for the Fragment.

### Collection Format Overview

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

        * `configuration.json`: a JSON file that defines the Fragment's configuration. See [Making Fragments Configurable](./making-fragments-configurable.md) for more information.

        * `main.js`: the JavaScript source for the Fragment

        * `thumbnail.png`: the thumbnail that's displayed when the Fragment is in a list

    * `resources/`: a folder containing any additional images or other external files needed for the Fragment. See [Including Default Resources in Fragments](./including-default-resources-with-fragments.md) for more information.

## Compress the Fragment Collection and Copy it to the Docker Container

You can download and unzip the [example Fragment Collection](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/dev/01-developing-page-fragments/developing-page-fragments-using-the-fragments-toolkit/liferay-x2y6.zip) if you want to deploy it or compare your code at this point:

```bash
curl https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/dev/01-developing-page-fragments/developing-page-fragments-using-the-fragments-toolkit/liferay-x2y6.zip

unzip liferay-x2y6.zip
```

1. Compress the Fragment Collection into a ZIP file and Press Enter to accept the defaults for the prompts to configure the ZIP for [auto-deployment](./auto-deploying-fragments.md):

    ```bash
    cd my-fragments
    npm run compress
    
    ? Add deployment descriptor? Yes
    ? Deployment descriptor company Web ID? liferay.com
    ? Deployment descriptor group key? Guest
    ```
    
    This automatically imports the Fragment Collection into your server when you copy it to your Docker container.

1. Start the Docker container:

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.3.0-ga1
    ```

1. Copy the Fragment Collection's ZIP to the Docker container. Alternatively, you can [import the Fragment manually](TODO:managing-fragments) instead.
    
    ```bash
    cp liferay-fragments.zip docker-container-name:/path/to/deploy/folder
    ```

1. Confirm the deployment to the Liferay Docker container console. The log message below should appear in the Docker console:

    ```bash
    INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:263] Processing liferay-fragments.zip
    ```

1. Verify that the Fragment Collection is available. Open your browser to `https://localhost:8080`, and open the Product Menu and go to Site &rarr; *Site Builder* &rarr; *Page Fragments*. The Collection is listed with the other Collections.

    ![The Collection is available.](./developing-page-fragments-with-the-fragments-toolkit/images/01.png)
    
Great! Now you know how to use the Fragments Toolkit to create Collections and Fragments and import them into your Docker container.
