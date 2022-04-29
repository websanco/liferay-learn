# Including Resources and Widgets

<!-- Businesses often have the Design team create wireframes and mockups of the sites they want included on the platform. These mockups include not only the overall look-and-feel, but the kind of styled content expected on the different sites. The development team not only needs to implement the global look-and-feel changes, providing the framework for content, but they may also need to implement the entire mockup. It is useful for them to bring in the resources they need all at once and build the sites initially as close to those mockups as they can.

## Livingstone's Resource Needs {#livingstone}

The Livingstone Hotels & Resorts website will contain a number of separate sites for each individual hotel and resort. Each site requires a consistent user experience with different landing pages, types of content, fragments, etc. To help minimize the work of the different teams setting up and maintaining new Hotel and Resort sites, Kaito and team can provide content and fragment resources to be deployed and available with the theme.

## The Resources Importer {#resourcesimporter}

Liferay's _Resources Importer_ allows developers to deploy custom themes with predefined content. The Resources Importer is useful for adding assets, structures, templates (including Application Display Templates), metadata, and any other resources with the Theme. This will help provide a holistic style change to implement an initial mockup or wireframe design.

<div class="key-point">
Key Point: <br />
The Resources Importer creates a Site Template that can be used for creating new sites with a predefined look-and-feel.
</div>

<div class="note">
Note: Although the Resources Importer can be used in 7.2, it is <b>deprecated</b>. Functionality for the Resources Importer will be removed in upcoming versions.
</div>

These theme resources reside in the `src/WEB-INF/src` folder of any custom theme. All the resources will be stored within the structure of the `resources-importer` folder. The structure can include folder identifiers for specific resources, such as documents or web content. The file structure could look something like this:

<br />

```
resources-importer/
  document_library/
    documents/
  journal/
    articles/
    structures/
    templates/
  assets.json
  sitemap.json
```

<br />

Each of these folders corresponds to the API for each asset. The journal folder makes up the Web Content API, with three folders containing code for the different aspects of the Application: the article itself, the structure, and the template. Documents and Media only has one folder (documents) that contains any document folders or documents that need to be included.

With the necessary resources included, the Resources Importer can either create a Site Template or be used to import resources into a single site (either an entirely new one, or an existing one). To import the resources into a single site, developers can add the following properties to [theme-name]/src/WEB-INF/liferay-plugin-package.properties:

<br />

```
resources-importer-target-class-name=com.liferay.portal.kernel.model.Group
resources-importer-target-value=[site-name]
```

<br />

Liferay will check to see the value at `[site-name]` for an existing site, and if the site does not exist, it creates a new site with the name specified.

<figure>
	<img src="../images/resources-importer-example.png" style="max-height: 35%" />
	<figcaption style="font-size: x-small">Fig.1 Resources Importer folder example</figcaption>
</figure>

<br />

## JSON Files {#json}

<div class="key-point">
Key Point: <br />
The <b>sitemap.json</b> file allows developers to designate pages, page layouts, and content to display in the Site or Site Template that will be generated. 
</div>

This file not only describes the page structure of the site, but also how the resources included in the Theme will be displayed on a site. This includes:

- Pages of a to-be-generated site or site template  
- Layout templates  
- Widgets and Widget preferences (aka portlet preferences)
- Content to display

Let's take a public site welcome page, for example. This page may need to be defined as a two-column 50/50 layout that has a login widget and hello world widget. It also needs to include the page title as well as the friendly URL as `/home`. The sitemap.json file that describes this could be written like the following:

```json
{
  "layoutTemplateId": "2_columns_ii",
  "publicPages": [
    {
      "columns": [
        [ { "portletId": "com_liferay_login_web_portlet_LoginPortlet" } ]
        [ { "portletId": "com_liferay_hello_world_web_portlet_HelloWorldPortlet" } ]
      ],
      "friendlyURL": "/home",
      "name": "Welcome",
      "title": "Welcome",
    }
  ]
}
```

<div class="key-point">
Key Point: <br />
The <b>assets.json</b> file defines the metadata of the assets that are imported.
</div>

Tags can be applied to any content type from this file. Other assets can have more defined for them as well. For example, a Web Content Article can have an abstract (or summary) and small images attributed to it. The `assets.json` could look like the following:

<br />

```json
{
  "assets": [
    {
      "name": "company-logo.png",
      "tags": [ "logo", "company" ]
    },
    {
      "abstractSummary": "This is an abstract summary.",
      "name": "My_Example.xml",
      "smallImage": "company_logo.png",
      "tags": [ "web content" ]
    }
  ]
}
```

## Understanding the File Structure {#filestructure}

The document_library/documents/ folder is where all the folders and files in the _Documents and Media_ repository will be placed. When deployed, those folders and files will show up on the platform through the Site Template or individual site.

The journal folder is where resources related to web content can be included. These resources, specifically, include the content articles themselves, structures, and web content templates. Structures can be added to this folder as .json files, templates as .ftl files, and web content articles as .xml files. A structure from this folder could be written:

```json
{
  "availableLanguageIds": ["en_US"],
  "defaultLanguageId": "en_US",
  "fields": [
    { "name": "Header", "type": "text", ... },
    { "name": "Body", " type": "textarea", ... }
  ]
}
```

<div class="note">
Note: The Web Content Structure or Template source can be found by clicking the Source tab when editing on the platform.
</div>

A simple custom template added might include:

```
<h1>${Header.getData()}</h1>
<p>${Body.getData()}</p>
```

Finally, you can include Web Content articles in the `resources/journal/articles` folder using XML. XML allows you to contain both the HTML formatting and the structure itself. For example, if we want to add the structure of the header and body example above, we can add an example_article.xml file containing the following:

```XML
<?xml versions="1.0"?>

<root available-locales="en_US" default-locale="en_US">
  <dynamic-element name="Header" type="text" index-type="keyword" instance-id="mdyl">
    <dynamic-content language-id="en_US"><! [CDATA[My Header]]>
    </dynamic-content>
  </dynamic-element>
  <dynamic-element name="Body" type="text_box" index-type="keyword" instance-id="opiq">
    <dynamic-content language-id="en_US"><! [CDATA[My body <em>with</em>HTML.]]>
    </dynamic-content>
  </dynamic-element>
</root>
```

With our `Example_Article.xml` resources included, we can insert it into a page column by adding the following code to `sitemap.json`:

```json
{
   "portletId": "com_liferay_journal_content_web_portlet_JournalContentPortlet",
   "portletPreferences": {
      "articleId": "Example_Article.xml",
      "groupId": "${groupId}",
      ...
   }
},
```

`portletId` is used to define the filepath for the type of portlet you are trying to use (in this case, a `JournalContentPortlet`). `portletPreferences` store the basic widget configuration data, the same properties that can be configured for widgets in the UI.

<figure>
	<img src="../images/application-configuration.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.2 Widget configuration</figcaption>
</figure>

From the `sitemap.json`, widget preferences can be set with a few lines of code. Here are some common properties to set:

- `articleId` sets the article to use  
- `groupId` selects a site  
- `portletSetupTitle_en_US` sets a title (in English)  
- `portletSetupUseCustomTitle` allows use of the custom title  
- `portletSetupPortletDecoratorId` sets the Application Decorator (barebone, borderless, decorate)

<div class="note">
Note: The portlet decorator can also be set up when embedding widgets into a theme.
</div>

## Site Initializers {#siteinitializers}

Liferay DXP 7.2 also includes a java module that allows developers to build sites with Site Resources, _Site Initializers_. 

<div class="key-point">
Key Point:<br />
Site Initializers are Java modules that allow developers to do the following:
<ul>
  <li> Create a Site Template that can be used to generate new sites with a theme
  <li> Include <i>Page Fragment</i> resources that will create the page structure
  <li> Include a Site Template thumbnail
</ul>
</div>

In order to create Site Initializers, developers need to create a module project. The project structure will look something like this:
```
[theme]-site-initializer/
  src/main/
    java/com/liferay/frontend/theme/[theme-name]/site/initializer/internal/
      ThemeSiteInitializer.java
    servlet/
      taglib/
        ThemeTopHeadInclude.java
    resources/
      com/liferay/frontend/theme/[theme-name]/site/initializer/internal/dependencies/
        fragments/
          fragment-collection/
            fragment.html
            fragment.png
        images/
          icon.png
          background.jpg
      META-INF/resources/images
        thumbnail.png
```

The `ThemeSiteInitializer.java` is the main source file for the Site Initializer project and implements the `SiteInitializer` public class. In this file, developers can use Liferay's serviceContext to pass contextual information for page and fragment services. Developers can also set the structure for the Site Template by referencing the Fragments included in the `fragments` folder of the project. For example, if we had three fragment collection folders named _home_, _features_, and _download_, we could add the following Java code to build our pages:

<br />

```java
@Override
	public void initialize(long groupId) throws InitializationException {
		try {
      ...
      List<FragmentEntry> homeFragmentEntries = _addFragmentEntries(
				fragmentCollection.getFragmentCollectionId(), fileEntriesMap,
				_PATH + "/fragments/home", serviceContext);

			List<FragmentEntry> downloadFragmentEntries = _addFragmentEntries(
				fragmentCollection.getFragmentCollectionId(), fileEntriesMap,
				_PATH + "/fragments/download", serviceContext);

			homeFragmentEntries.addAll(downloadFragmentEntries);

			List<FragmentEntry> featuresFragmentEntries = _addFragmentEntries(
				fragmentCollection.getFragmentCollectionId(), fileEntriesMap,
				_PATH + "/fragments/features", serviceContext);

			homeFragmentEntries.addAll(featuresFragmentEntries);

			_addLayout(
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"Home", homeFragmentEntries, _PATH + "/fragments/home",
				serviceContext);

			_addLayout(
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"Features", featuresFragmentEntries,
				_PATH + "/fragments/features", serviceContext);

			_addLayout(
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"Download", downloadFragmentEntries,
				_PATH + "/fragments/download", serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new InitializationException(e);
		}
	}
```

Once the Site Initializer is deployed, it will generate a new Site Template option that admins can use to create new sites. Once a new site is created using the new template, all of the pages and fragments designated in the project will be on full display. This method can be used to keep all the different pieces of a site within a DevOps deployment process.

<br />

## Embedding Widgets {#embedding}

You may also need to include widgets in different parts of the page, such as the header or footer. For example, if you want to add a footer search, navigation, or even language widgets to each page, you can use one of three different taglibs.

<div class=key-point>
Key Point: <br />
Embedding widgets into the portal_normal.ftl file will render them on every site page.
</div>

Taglibs are used to embed widgets or content. There are three that you can use:

1. `<@liferay_portlet["runtime"]`  
2. `<@liferay_journal["journal-article"]`  
3. `<@liferay_ui["asset-display"]`

The Runtime taglib expects two parameters: 
1. `portletProviderAction`, which requests the portlet provider to perform an action for display
2. `portletProviderClassName`, which requires the full class name of the entity on which the action is to be performed 

These two parameters are always coupled together. This method only works for some widgets, a list of which can be found at <a href="https://github.com/liferay/liferay-portal">https://github.com/liferay/liferay-portal</a> or by searching the code for `extends BasePortletProvider`. 

If you want to use runtime for other widgets, you need to add `portletName`, which is looking for a widget id written as a string reference from the application class path:
```FreeMarker
<@liferay_portlet["runtime"]
    portletName="CLASS_NAME"
/>
```
You can also embed Web Content using the `<@liferay_journal["journal-article"]` taglib.

The `<@liferay_journal["journal-article"]` taglib requires the following:  
1. Article ID - The id of the Web Content Article you wish to display  
2. Template Key - The id of any Web Content Template you want to identify  
3. groupId - The site id where the content is available
```FreeMarker
<@liferay_journal["journal-article"] 
    articleId="ARTICLE_ID" 
    ddmTemplateKey="TEMPLATE_KEY" 
    groupId=${group_id} 
/> 
```

Finally, developers can embed other specific assets, such as wiki articles or blogs, using the `<@liferay_ui["asset-display"]` taglib.

The <@liferay_ui["asset-display"] taglib requires the following:
1. Class Name - The Java Class Name of the asset (the content type, such as blogs or documents)  
2. Class PK - The Primary Key id of the specific asset to display (the specific blog or document you want to display) 
3. Template - This identifies the template used to display the asset
```
<@liferay_ui["asset-display"] 
    className="JAVA_CLASS_NAME" 
    classPK="CLASS PK (RESOURCE PK) OF ASSET" 
    template="full_content" 
/> 
```

Using these taglibs will give developers like Kaito the flexibility they need to embed and configure asset or widget resources in their themes.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Adding content into your themes is possible through the ______________________ Importer and by embedding widgets.</li>
  <li>______________________ are Java modules that create Site Templates using ______________________.</li>
  <li>Embedding widgets using special ______________________ in the portal_normal.ftl file will render them on every site page.</li>
  <li>Taglibs are used to embed widgets or content. There are three that you can use:</li>
  <ul>
    <li>______________________</li>
    <li>______________________</li>
    <li>______________________</li>
  <ul>
</ul>
</div> -->
