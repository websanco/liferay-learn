# Overriding Module Language Translations in Earlier Versions

```{important}
For Liferay DXP 7.4 U4 (Update 4) and above or Liferay Portal 7.4 GA8 and above, the [Language Override tool](../../system-administration/configuring-liferay/changing-language-tranlations.md) is the recommended approach. If you're working with Liferay DXP/Portal 7.4+, please follow the instructions for [Overriding Global Language Translations](./overriding-global-language-translations.md).
```

Overriding Liferay application specific language translations in earlier versions is similar to overriding global language translations in earlier versions but there are additional steps. 

## Examining Module Language Translations

To override a module's language translation, you must first gather information about the module in [Gogo shell](../fundamentals/using-the-gogo-shell.md). For example if you wish to override a language translation in the blogs module, `grep` for the keyword "blogs". The Gogo command and output might look like this: 

 ```
 g! lb | grep Blogs
 Output
 418|Active     |   10|Liferay Collaboration - Liferay Blogs - API (1.0.0)|1.0.0
 419|Active     |   10|Liferay Blogs API (6.4.5)|6.4.5
 420|Active     |   10|Liferay Blogs Item Selector API (4.0.5)|4.0.5
 421|Active     |   10|Liferay Blogs Recent Bloggers API (4.0.5)|4.0.5
 570|Active     |   10|Liferay Adaptive Media Blogs Editor Configuration (4.0.5)|4.0.5
 571|Active     |   10|Liferay Adaptive Media Blogs Item Selector Web (4.0.5)|4.0.5
 572|Active     |   10|Liferay Adaptive Media Blogs Web (4.0.9)|4.0.9
 573|Resolved   |   10|Liferay Adaptive Media Blogs Web Fragment (4.0.6)|4.0.6
 671|Active     |   15|Liferay Sharing Blogs (2.0.6)|2.0.6
 1126|Active     |   10|Liferay Collaboration - Liferay Blogs - Impl (1.0.0)|1.0.0
 1127|Active     |   10|Liferay Blogs Editor Configuration (4.0.8)|4.0.8
 1128|Active     |   15|Liferay Blogs Item Selector Web (5.0.9)|5.0.9
 1129|Active     |   10|Liferay Blogs Layout Prototype (5.0.8)|5.0.8
 1130|Active     |   10|Liferay Blogs Reading Time (3.0.11)|3.0.11
 1131|Active     |   15|Liferay Blogs Recent Bloggers Web (5.0.11)|5.0.11
 1132|Active     |   10|Liferay Blogs Service (4.0.24)|4.0.24
 1133|Active     |   10|Liferay Blogs UAD (5.0.6)|5.0.6
 1134|Active     |   15|Liferay Blogs Web (5.0.36)|5.0.36
 true
 ```

Take note of the module's ID number. Use the `headers` command to get a list of the bundle's headers. In this case it's 1134 for the Liferay Blogs Web module.

 ```
 g! headers 1134
 Output
 Bundle headers:
 Bnd-LastModified = 1601503219290
 Bundle-ManifestVersion = 2
 Bundle-Name = Liferay Blogs Web
 Bundle-SymbolicName = com.liferay.blogs.web
 Bundle-Vendor = Liferay, Inc.
 Bundle-Version = 5.0.36
 ...
 Web-ContextPath = /blogs-web
 ```

Note the `Bundle-SymbolicName`, `Bundle-Version`, and the `Web-ContextPath`. The `Web-ContextPath` value following the `/` is the module's context name.

Use the bundle symbolic name or context name to find the language translations specific to the module. Find the module's JAR file and examine its language translations. Liferay follows this module JAR file naming convention:

```
[bundle symbolic name]-[version].jar
```

For example, the Blogs Web version 5.0.36 module is in `com.liferay.blogs.web-5.0.36.jar`.

Here's where to find the module:

* Liferay's [Nexus repository](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/)
* `[Liferay Home]/osgi/modules`
* Source code at [`liferay-[dxp|portal]/modules/apps`](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps).

The language property files are in the module's `src/main/resources/content` folder. Identify the language translations you want to override in the `Language[xx_XX].properties` files.

Note that the language translations for different languages and locales can be identified by the filename ending. For example, `Language_ja.properties` is for Japanese.

The example changes the default `Add Blog Entry` language translation to a custom translation. Now it's time to deploy it. 

```{include} /_snippets/run-liferay.md
```

Then, follow these steps:

1. Download and unzip `liferay-e6u7.zip`.

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/liferay-internals/extending-liferay/liferay-e6u7.zip -O
    ```

    ```bash
    unzip liferay-e6u7.zip
    ```

1. From the module root, build and deploy.

    ```bash
    cd liferay-e6u7
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    This command is the same as copying the deployed jars to `/opt/liferay/osgi/modules` on the Docker container.
    ```

1. Confirm the deployment in the Liferay Docker container console.

    ```bash
    STARTED com.acme.e6u7.impl_1.0.0 [1650]
    ```

1. Navigate to *Content & Data* &rarr; *Blogs*. Move your cursor over the add icon (![Add](../../images/icon-add.png)). The message now shows the custom language translation.

    ![The custom language translation is now being used.](./overriding-module-language-translations/images/01.png)

1. The tutorial code also includes examples for other locales. For example, use the language selector to select Brazilian Portuguese or Japanese to see the custom language translation. The module overrides language translations for each locale you include in the module.

    ![A custom language translation is also used for Portuguese and Japanese](./overriding-module-language-translations/images/02.png)

Now you can see how the code works.

## Create the Language Properties File

First select the translations you wish to override. For example, the tutorial code overrides the `Add Blog Entry` language translation.

Once you've decided which translations to override, create a language properties file in your module's `src/main/resources/content` folder. In your file define the translations your way. Make sure the filename matches the locale you wish to override. For example, if Japanese, use `Language_ja.properties`.

```{literalinclude} ./overriding-module-language-translations/resources/liferay-e6u7.zip/e6u7-impl/src/main/resources/content/Language_ja.properties
:language: properties
```

## Create the Language Resource Bundle

In your module, create a class that extends `java.util.ResourceBundle` for the locale you're overriding. Here's an example resource bundle class for the `en_US` locale:

```{literalinclude} ./overriding-module-language-translations/resources/liferay-e6u7.zip/e6u7-impl/src/main/java/com/acme/e6u7/internal/language/E6U7EnglishResourceBundle.java
:language: java
:lines: 10-26
```

The class's `_resourceBundle` field is assigned a `ResourceBundle`. The call to `ResourceBundle.getBundle` needs two parameters. The `content.Language_en_US` parameter is the language file's qualified name with respect to the module's `src/main/resources/content` folder. The second parameter is a `control` that sets the language syntax of the resource bundle. To use language syntax identical to Liferay's syntax, import Liferay's `com.liferay.portal.kernel.language.UTF8Control` class and set the second parameter to `UTF8Control.INSTANCE`. 

The class's `@Component` annotation declares it an OSGi `ResourceBundle` service component. Its `language.id` property designates it for the `en_US` locale. 

```{literalinclude} ./overriding-module-language-translations/resources/liferay-e6u7.zip/e6u7-impl/src/main/java/com/acme/e6u7/internal/language/E6U7EnglishResourceBundle.java
:language: java
:lines: 10
```

The class overrides these methods:

**`handleGetObject`:** Looks up the translation in the module's resource bundle (which is based on the module's language properties file) and returns the key's value as an `Object`. 

**`getKeys`:** Returns an `Enumeration` of the resource bundle's keys. 

Your resource bundle service component redirects the default language translations to your module's language translatio overrides.

**Note:** Module language translation overrides for multiple locales require a separate resource bundle class for each locale. For example, the tutorial code has one for English, Japanese, and Portuguese. Each resource bundle must specify its locale in the `language.id` component property definition and in the language file qualified name parameter. For example, here is what they look like for the Japanese locale.

Component definition:

```{literalinclude} ./overriding-module-language-translations/resources/liferay-e6u7.zip/e6u7-impl/src/main/java/com/acme/e6u7/internal/language/E6U7JapaneseResourceBundle.java
:language: java
:lines: 10
```

Resource bundle assignment:

```{literalinclude} ./overriding-module-language-translations/resources/liferay-e6u7.zip/e6u7-impl/src/main/java/com/acme/e6u7/internal/language/E6U7JapaneseResourceBundle.java
:dedent: 1
:language: java
:lines: 23-24
```

## Prioritize Your Module's Resource Bundle

For the target module to use your custom language translations, you must specify your resource bundle in the OSGI manifest header. List your module first to prioritize its resource bundle over the target module resource bundle. This aggregates the two resources together. Here's an example of our tutorial module `com.acme.e6u7.impl` prioritizing its resource bundle over the target module `com.liferay.blogs.web`'s resource bundle:

```{literalinclude} ./overriding-module-language-translations/resources/liferay-e6u7.zip/e6u7-impl/bnd.bnd
:language: properties
:lines: 4-12
```

The example `Provide-Capability` header has two parts: 

1.  `liferay.resource.bundle;resource.bundle.base.name="content.Language"` declares that the module provides a resource bundle with the base name `content.Language`. 

1. The `liferay.resource.bundle;resource.bundle.aggregate:String=...` directive specifies the list of bundles with resource bundles to aggregate, the target bundle, the target bundle's resource bundle name, and this service's ranking:

    * `"(bundle.symbolic.name=com.acme.e6u7.impl),(bundle.symbolic.name=com.liferay.blogs.web)"`: The service aggregates resource bundles from bundles `com.acme.e6u7.impl` and `com.liferay.blogs.web`. Aggregate as many bundles as desired. Listed bundles are prioritized in descending order. 
    * `bundle.symbolic.name=com.liferay.blogs.web;resource.bundle.base.name="content.Language"`: Override the `com.liferay.blogs.web` bundle's resource bundle named `content.Language`. 
    * `service.ranking:Long="2"`: The resource bundle's service ranking is `2`. The OSGi framework applies this service if it outranks all other resource bundle services that target `com.liferay.blogs.web`'s `content.Language` resource bundle. 
    * `servlet.context.name=blogs-web`: The target resource bundle is in servlet context `blogs-web`. 

```{note}
If your override isn't showing, use [Gogo shell](../fundamentals/using-the-gogo-shell.md) to check for competing resource bundle services. It may be that another service outranks yours. To check for competing resource bundle services whose aggregates include `com.liferay.blogs.web`’s resource bundle, for example, execute this Gogo shell command:

`services "(bundle.symbolic.name=com.liferay.blogs.web)"`
```

```{note}
You can continue to use your language translation override in DXP 7.4+ if the language key name is the same---check the [`/modules/apps/portal-language/portal-language-lang/src/main/resources/content/Language[_xx_XX].properties`](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-language/portal-language-lang/src/main/resources/content) file. Optionally, you can simplify your module by removing the `ResourceBundle` class and replacing the `Provide-Capability` header in your `bnd.bnd` file with the header demonstrated in the [Overriding Global Language Translations](./overriding-global-language-translations.md#declare-the-oOverride-in-the-bnd-file).
```

Search the results for resource bundle aggregate services whose ranking is higher.

## Related Information

* [Overriding Global Language Translations](./overriding-global-language-translations.md)
* [Changing Language Translations](../../system-administration/configuring-liferay/changing-language-translations.md)