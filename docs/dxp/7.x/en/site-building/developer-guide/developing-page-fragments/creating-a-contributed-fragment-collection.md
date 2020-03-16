# Creating a Contributed Fragment Collection

Contributed Fragment Collections are deployable modules containing Page Fragments. The Fragments in a contributed Collection can be used just like regular Fragments, but aren't contained in the database, and can't be modified except by updating the module they came from. Like other default Fragments, you can also [copy the Fragment to another Collection](../../04-fragments/managing-page-fragments.md#managing-individual-page-fragments) and modify the code in the copy.

## Overview

1. [Deploy an Example](#deploy-an-example)
1. [Walk Through the Example](#walk-through-an-example)
1. [Additional Information](#additional-information)

## Deploy an Example

> Liferay DXP 7.3+

First you must deploy an example contributed Fragment Collection. Follow these steps:

1. Start the Docker container with a bind mount:

    ```bash
    docker run -d -it -p 8080:8080 -p 8000:8000 --name mylrdev -v C:\Users\liferay\Desktop\liferay-docker:/mnt/liferay liferay/portal:7.3.0-ga1
    ```

1. Download and unzip the [example contributed Fragment Collection](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/1583786778liferay62.zip):

    ```bash
    curl https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/1583786778liferay62.zip
    
    unzip 1583786778liferay62.zip
    ```

1. Build the contributed Collection's JAR.

    ```bash
    cd my-contributed-fragments
    ./gradlew build
    ```

1. Copy the contributed Collection's JAR to the `[host_folder]/deploy` folder for your Docker image's [bind mount](TODO), or create the `[host_folder]/deploy` folder if it doesn't exist.

    ```bash
    cp com.liferay.learn.fragments-1.0.0.jar path/to/your/bind/mount
    ```

    ```note::
    You must restart the Docker container if you're creating the `[host_folder]/deploy` folder for the first time in your bind mount.
    ```

1. Confirm the deployment to the Liferay Docker container console. The log message below should appear in the Docker console:

    ```bash
    INFO  [fileinstall-/opt/liferay/osgi/modules][BundleStartStopLogger:39] STARTED com.liferay.learn.fragments_1.0.0 [1121]
    ```

1. Verify that the contributed Collection and Fragment are available. Open your browser to `https://localhost:8080`, and open the Product Menu and go to *Site Builder* &rarr; *Page Fragments* under the Site Menu.

    ![The contributed Collection Fragment is listed with the default Fragments.](./creating-a-contributed-fragment-collection/images/01.png)

Great! You successfully deployed a contributed Fragment Collection. Next, you'll walk through the example and learn how it works.

## Walk Through the Example

A Fragment Collection contributor contains two key pieces: a Fragment Collection contributor class and the Fragment(s) resources. Follow these steps to create them:

* [Annotate the Fragment Collection Contributor Class for OSGi Registration ](#annotate-the-fragment-collection-contributor-class-for-osgi-registration)
* [Review the `BaseFragmentCollectionContributor` base Class](#review-the-basefragmentcollectioncontributor-base-class)
* [Complete the Fragment Collection Contributor Class](#complete-the-fragment-collection-contributor-class)

### Annotate the Fragment Collection Contributor Class for OSGi Registration

```java
@Component(
	service = FragmentCollectionContributor.class
)
public class FragmentsCollectionContributor extends BaseFragmentCollectionContributor {
  ...
}
```

### Review the `BaseFragmentCollectionContributor` Class

The `*FragmentCollectionContributor` class extends the `BaseFragmentCollectionContributor` class which itself implements the `FragmentCollectionContributor` interface.

Implement these methods:

```java
@Override
public String getFragmentCollectionKey() {
    return "My Collection";
}
```

> This returns the key/name that identifies the Fragment Collection.

```java
@Override
public ServletContext getServletContext() {
    return _servletContext;
}
```

> This returns the servlet context for the contributed Fragment Collection module.

### Complete the Fragment Collection Contributor Class

The Fragment Collection contributor class is comprised of back-end logic for defining what Collection to contribute the Fragment(s) to and the Fragment(s) themselves. Follow these steps: 

* [Configure the `ServletContext` for the module.](#configure-the-servletcontext-for-the-module)
* [Add the Fragment Resources](#add-the-fragment-resources)

#### Configure the `ServletContext` for the Module

Define the `ServletContext` in the class using the bundle's symbolic name so it can find the Fragment resources: 

```java
@Reference(
  target = "(osgi.web.symbolicname=com.liferay.learn.fragments)"
)
private ServletContext _servletContext;
```

> The value set for `osgi.web.symbolicname` matches the value for `Bundle-SymbolicName` in the [bnd.bnd file](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/1583786778liferay62.zip/my-contributed-fragments/bnd.bnd). These values must match for the `ServletContext` to locate the Fragment resources.
>
> Declare a unique value for `Web-ContextPath` in the bnd.bnd file so the `ServletContext` is correctly generated. In this example, `Web-ContextPath` is set to `/my-contributed-fragments`. See [bnd.bnd](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/1583786778liferay62.zip/my-contributed-fragments/bnd.bnd) for a reference on these values.
> Add the `-dsannotations-options` instruction in the bnd.bnd file and set it to `inherit` to specify to use DS annotations found in the class hierarchy of the component class. See [bnd.bnd](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/1583786778liferay62.zip/my-contributed-fragments/bnd.bnd) for a reference on these values.

#### Add the Fragment Resources

Next you need to include the Fragments that you want to contribute in your module. See [Developing Page Fragments Using the Generator](./developing-fragments-using-the-generator) for more information on creating Fragments.

1. In your module's `resources/` folder, create a folder structure that mirrors the `*FragmentCollectionContributor` class package with a `/dependencies` folder added last. The example has the structure `resources/com/liferay/learn/fragments/dependencies`.

    ```note::
        The class package name and resources package name must match (e.g. `[my.class.package.structure].dependencies`).
    ```

1. Copy the Fragments you want to distribute into the folder. See [tardis](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/1583786778liferay62.zip/my-contributed-fragments/src/main/resources/com/liferay/learn/fragments/dependencies/tardis) for a reference of a packaged Fragment.

1. Create a file named `collection.json` in the `[my.class.package.structure].dependencies` folder with this format:

    ```json
    {
        "fragments": [
            "[fragment-1]",
            "[fragment-2]",
            "[fragment-3]",
                ...
        ],
        "name": "[collection-name]"
    }
    ```
    
    The example has this configuration:
    
    ```json
    {
        "fragments": [
            "tardis"
        ],
        "name": "My Collection"
    }
    ```

    This is the same Collection name specified in the `getFragmentCollectionKey()` method of the `*FragmentCollectionContributor` class. If a Fragment isn't listed in `collection.json`, it's not available in the Contributed Collection, even if the files are included in the module.

### Additional Information

```note::
    All Fragments added through a Contributed Fragment Collection are available globally to all Sites.
```

You can also provide thumbnail images for your contributed Fragments and add translations if you like.

#### Providing Thumbnail Images

You can also provide thumbnail images for reference for your Fragments:

1. Under `resources/META-INF/resources` create a folder named `thumbnails`.
1. Copy thumbnail images into the folder with the format `[fragment-name].png` for each Fragment.

#### Providing Language Keys

Providing language keys in your Fragment gives you the option for translating the text you display. Here's how to do it:

1. You must define your language keys in the Fragment's collection folder. Create the `[COLLECTION]/src/main/resources/content/Language.properties` file.
1. Add your language keys. Here's an example configuration:

    ```properties
    applied-style=Applied Style
    this-is-the-style-that-will-be-applied=This is the style that will be applied.
    dark=Dark
    light=Light
    ```

See [Localizing Your Application](TODO) for more information about providing translations.