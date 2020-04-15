# Creating a Contributed Fragment Collection

Contributed Fragment Collections are deployable modules containing Page Fragments. The Fragments in a contributed Collection can be used just like regular Fragments, but aren't contained in the database, and can't be modified except by updating the module they came from or [copying the Fragment to another Collection](../../04-fragments/managing-page-fragments.md#managing-individual-page-fragments) and modifying the code in the copy. You can create a contributed Fragment Collection in just a few steps: 

1. Annotate the Fragment Collection Contributor Class for OSGi Registration.
1. Override the `BaseFragmentCollectionContributor` methods.
1. Configure the `ServletContext` for the module.
1. Configure the BND file.
1. Add the Fragment Resources.
1. Build the JAR and copy it to the Docker Container.

This example uses a Docker image with a fresh install of Liferay DXP and runs on Liferay DXP 7.3+. 
 
```note::
  All Fragments added through a Contributed Fragment Collection are available globally to all Sites.
```

## Start Liferay DXP

Run the command below to start the Docker container:

```bash
docker run -it -p 8080:8080 liferay/portal:7.3.0-ga1
```


<!-- I pasted the above before looking at the rest of this. This tutorial doesn't follow the pattern we're looking for. There should be an example project already created with the name liferay-[letter-number-letter-number].zip that contains a deployable skeleton of the project. Readers should never have to start from scratch as your instructions below appear to indicate. The tutorial should start by having the reader deploy the project first to see what the skeleton does. This would mean your component class below is already done. Then you just explain how that part works, rather than having the reader do it. 

The skeleton perhaps wouldn't have the fragments in it. You'd have the reader do this by providing real (not the empty ones you have) working fragments in the project and then redeploying it to see how the fragments get imported properly. 

The main points here are 

1. All code should be real code. We don't want anything like "My Sample Fragment" or blank examples. 
2. Everything should be functional. It can be simple (like a banner or a card with static links), but it has to be something real. 
3. The pattern is deploy a skeleton that already includes the boring stuff nobody wants to do (structure, bnd files, component classes, and all other infrastructure stuff), modify or add to the skeleton to make it fully functional, redeploy the project and see the results. Throughout, describe what's going on so the reader could make the project from scratch if he/she wanted to. 
-->

## Annotate the Fragment Collection Contributor Class for OSGi Registration

Create a Component class that extends the `BaseFragmentCollectionContributor` class and implements the `FragmentCollectionContributor.class` service:

```java
@Component(
	service = FragmentCollectionContributor.class
)
public class FragmentsCollectionContributor extends BaseFragmentCollectionContributor {
  ...
}
```

## Override the `BaseFragmentCollectionContributor` Methods

The `*FragmentCollectionContributor` class extends the `BaseFragmentCollectionContributor` class which itself implements the `FragmentCollectionContributor` interface.

1. Override the `getFragmentCollectionKey()` method to return the key/name of the Fragment Collection to contribute the Fragments to:

    ```java
    @Override
    public String getFragmentCollectionKey() {
        return "My Collection";
    }
    ```

1. Override the `getServletContext()` method to return the servlet context for the contributed Fragment Collection module:

    ```java
    @Override
    public ServletContext getServletContext() {
        return _servletContext;
    }
    ```

## Configure the `ServletContext` for the Module

Define the `ServletContext` in the class using the bundle's symbolic name so it can find the Fragment resources: 

```java
@Reference(
  target = "(osgi.web.symbolicname=com.liferay.learn.fragments)"
)
private ServletContext _servletContext;
```

The `osgi.web.symbolicname` value must match the `Bundle-SymbolicName` in the bnd.bnd file.

## Configure the BND File

1. Declare a unique value for the `Web-ContextPath` in the `bnd.bnd` file so the `ServletContext` is correctly generated. In this example, `Web-ContextPath` is set to `/my-contributed-fragments`.

    ```properties
    Web-ContextPath: /my-contributed-fragments
    ```

1. Add the `-dsannotations-options` instruction in the `bnd.bnd` file and set it to `inherit` to specify to use DS annotations found in the class hierarchy of the Component class. 

    ```properties
    -dsannotations-options: inherit
    ```

See the example project's [bnd.bnd](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/liferay-l3m9.zip) for a reference of these values.

## Add the Fragment Resources

Next you need to include the Fragments that you want to contribute in your module. See [Developing Page Fragments with the Fragments Toolkit](./developing-page-fragments-with-the-fragments-toolkit.md) for more information on creating Fragments.

1. In your module's `resources/` folder, create a folder structure that mirrors the `*FragmentCollectionContributor` class package with a `/dependencies` folder appended to the end. The example has the structure `resources/com/liferay/learn/fragments/dependencies`.

    ```note::
        The class package name and resources package name must match (e.g. ``[my.class.package.structure].dependencies``).
    ```

1. Add the Fragments you want to distribute into the `dependencies` folder. Use the [Fragments Toolkit](./developing-page-fragments-with-the-fragments-toolkit.md) to create a packaged Fragment or create the packaged Fragment with your own tools with the [proper format](./developing-page-fragments-with-the-fragments-toolkit.md#collection-format-overview). You can also see the [example Fragment](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/liferay-l3m9.zip/my-contributed-fragments/src/main/resources/com/liferay/learn/fragments/dependencies/tardis) for a reference of a packaged Fragment.

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

## Build the Jar and Copy it to the Docker Container

You can download and unzip the [example contributed Fragment Collection](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/liferay-l3m9.zip) if you want to deploy it or compare your code at this point:

```bash
curl https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/developer-guide/developing-fragments/creating-a-contributed-fragment-collection/liferay-l3m9.zip

unzip liferay-l3m9.zip
```

1. Start the Docker container:

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.3.0-ga1
    ```

1. Build the contributed Collection's JAR.

    ```bash
    cd my-contributed-fragments
    ./gradlew build
    ```

1. Copy the contributed Collection's JAR to the Docker container:

    ```bash
    cp com.liferay.learn.fragments-1.0.0.jar docker-container-name:/path/to/deploy/folder
    ```

1. Confirm the deployment to the Liferay Docker container console. The log message below should appear in the Docker console:

    ```bash
    INFO  [fileinstall-/opt/liferay/osgi/modules][BundleStartStopLogger:39] STARTED com.liferay.learn.fragments_1.0.0 [1121]
    ```

1. Verify that the contributed Collection and Fragment are available. Open your browser to `https://localhost:8080`, and open the Product Menu and go to *Site Builder* &rarr; *Page Fragments* under the Site Menu.

    ![The contributed Collection Fragment is listed with the default Fragments.](./creating-a-contributed-fragment-collection/images/01.png)

Great! You successfully deployed a contributed Fragment Collection. Next, you'll walk through the example and learn how it works.
