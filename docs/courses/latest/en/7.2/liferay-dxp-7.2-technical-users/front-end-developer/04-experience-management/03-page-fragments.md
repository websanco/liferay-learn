## Reducing Time to Market with Page Fragments

When it comes to being successful in the digital era, it often comes down to speeding up time-to-market. Web Teams and copy-writers need to get webpages and content created and out the door quickly to promote new products, updates, or to communicate to the different audiences using the platform. Because there are so many different hands involved in a successful Site launch, Liferay DXP 7.2 includes Site Building features that can be created by front-end developers and combined and added in a GUI by administrators to meet the ever-changing business demands.  

#### Building Livingstone's Pages with Fragments {#LivingstoneFragments}

With the growing business of _Livingstone Hotels & Resorts_, there are new Hotel and Resort offerings happening on a regular basis. To continue to thrive as a premier Hospitality company, the Livingstone team needs to regularly create new hotel and resort Sites with modern pages with the information travelers need to get the most out of their stay. In order to make this process of new Site building as streamlined as possible, Kaito and the development team can create editable Page Fragments that can be used by the web team. From there, the web team will simply need to create a new Site, add the Fragments as needed and customize the text and images to match the new hotel or resort being represented by the new Site.

<figure>
  <img src="../images/lecture-images/fragment.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.1 Livingstone Fragment Example</figcaption>
</figure>

#### Page Fragments {#LiferayFragments}

In order for the different Site building teams to quickly re-use and combine different aspects of a page, they can use _Page Fragments_. Page Fragments _Sections_ and _Components_ can be combined to provide layouts with specific text and image components that can be added to any part of a _Content Page_. Here are some examples of what developers can build with Page Fragments include:
* Calls to action  
* Banners
* Carousels  
* Customer Quotes  

These examples only scratch the surface of the possible uses of Page Fragments. Page Fragments allow for greater versatility and customization, also creating a space for easy collaboration. They are a great way for the development team and the marketing teams to collaborate and implement great design features straight into the different Site Pages.

<div class="key-point">
Key Point: <br>
Page Fragments are a combination of HTML, CSS, and JavaScript and act as reusable and editable parts of a Site Page.
</div>

<figure>
  <img src="../images/lecture-images/fragment-example.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.2 Fragment Example on a Content Page</figcaption>
</figure>

#### Creating Page Fragments {#Creating}

Page Fragments can be created using the Fragments NPM Generator or on the platform using the Fragment Editor. Page Fragments are managed in _Collections_. Collections are like folders that contain fragments. When creating a Content Page, admins will see the different Collections available and from there can choose the fragments they wish to use.

<div class="key-point">
Key Point: <br />
Fragments are organized in groups called <b>Collections</b> that can be used when creating <b>Content Pages</b>.
</div>

<figure>
  <img src="../images/lecture-images/collections.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.3 An example of a Collection</figcaption>
</figure>

<br />

The Fragment Editor should feel familiar to web developers. HTML, CSS, and JavaScript code can be added and edited directly in the editor. Source files (CSS, JavaScript, images, files) can also be uploaded and edited with the Fragment Editor.

<figure>
  <img src="../images/lecture-images/fragment-editor-example.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.4 The Fragment editor</figcaption>
</figure>

Once a Fragment is created, developers will be able to specify which parts of a Fragment can be edited. For example, a developer may create a banner with editable images and buttons with text that can be edited by the Marketing team.

Developers can specify elements as editable using the `<lfr-editable>` tag. In order for the tag to be valid, developers must add both a custom `id` and `type` to the tag. There are three types of assets that can be used as the `type` in the `<lfr-editable>` tag:
1. Text
2. Image
3. HTML

Using the `<lfr-editable>` tag is as easy as using any standard tag, with a specified id: 

```HTML
<h1><lfr-editable id="main-title" type="text">Take the trip of a lifetime</lfr-editable></h1>
<lfr-editable id="-cover-image" type="image"><img src=”http://abc.com/paris.img”/></lfr-editable>
<p>
  <lfr-editable id="body-text" type="text">
    Make your dreams of adventure a reality by escaping to one of Livingstone Hotels & Resorts 17 world class hotels across the globe.
  </lfr-editable>
</p>
```

<br />

Once this Fragment is placed on a _Content Page_, users will be able to edit the title text, body text, and even update the image in the fragment. Page Fragments also allow for widgets to be embedded. In order to embed a widget into a Page Fragment, all you must know is its registered name. For example, the Navigation Menu widget is registered as _nav_. The key is to use the `<lfr-widget>` tag and add in your widget's registered name. 

A Page Fragment with an embedded widget can look as follows: 

```html
<div class=”container-fluid”>
  <div class=”row”>
    <div class=”col-md-10”>
       <lfr-widget-nav />
    </div>
  </div>
</div>
```

Custom widgets can also be configured to be embeddable within fragments by adding the following within a portlet's `@Component` annotation: 

```java
"com.liferay.fragment.entry.processor.portlet.alias=[widget-name-here]]"
```

<br />

#### Developing Fragments with the Fragments Generator {#Reusing}

For those working with NPM tools, the primary way to build, manage, and deploy Fragments is by making use of the Liferay Fragments Generator. Just like the other generators, the Fragments generator requires the following:
* NodeJS 8+
* NPM 6+
* Yeoman 2+

To install the generator, developers can run `npm install -g generator-liferay-fragments`.  

<div class="key-point">
Key Point: <br />
The Liferay Fragments Generator can be used to create Collections and Fragment projects that can be imported into the platform by following this process:
<ul>
  <li>Run <code>yo liferay-fragments</code> to create the new fragment project structure.</li>
  <li>Create Collections by running <code>npm run add-collection</code></li>
  <li>Create Fragments inside the Collection with <code>npm run add-fragment</code></li>
  <li>Import the Fragments into the platform with <code>npm run import</code></li>
</ul>
</div>

<br />

When creating Page Fragments in a collection, they need to include the following three primary files:
* `index.html`
* `index.css`
* `index.js`

These files need to be properly arranged to ensure that the metadata gets added to the platform correctly. The Generate will handle that for you when creating Page Fragments. 

Finally, Developers can connect to a running Liferay server to do things like export and import fragments. They have the ability to run these commands:
1. `npm run import`: This is used to send the collections and fragments to the server
2. `npm run import:watch`: This is used to automatically send collection and fragment changes to the platform
3. `npm run compress`: This is used to compress the collections and files into a zip file.
4. `npm run export`: This will take existing fragments on the platform and bring them into the npm process.

<br />

#### Tool-Agnostic Fragment Development {#Developing}

Front-End Developers also have the ability to create Page Fragments using any of the development tools they would use in their current workflow. As Fragments are just HTML, CSS, and JavaScript, any text editor or tool will work to create Page Fragments.

<div class="key-point">
Key Point: <br />
Developers can use any tools they're currently using in their workflow to create Collections of Fragments and import them into Liferay.
</div>

To start, developers need to create a Fragment structure like the following:
```
collection-name/  
  * collection.json
  * fragment-name01/
  * fragment-name02/
  ...
```

<br />

The `collection-name/` folder acts as the internal identifier for the collection and allows for re-imports. The `collection.json` file is where users can specify the user-friendly name and description for the Fragment Collection that will be visible on the platform. 

```json
{
  "name":"Livingstone Front Page Fragments",
  "description":"Basic Fragments for Livingstone Pages"
}
```

Each Page Fragment should be structured as follows:

```
fragment-name-01/
	* fragment.json
	* src/
		* index.html
		* index.js
		* index.css
```

<br />

The `fragment-name-01/` folder acts as the internal identifier for the Fragment and allows for re-imports. Each folder containing individual Fragments contains a `fragment.json` file that specifies the path for source files and user-friendly name for the Fragment. Users should also include a `src` folder with the HTML, CSS, and JavaScript that make up the Fragment.

```json
{
  "jsPath":"/collection-name/fragment-01/src/index.js",
  "htmlPath":"/collection-name/fragment-01/src/index.html",
  "cssPath":"/collection-name/fragment-01/src/index.css",
  "name":"01-Main Banner"
}
```

Users can package as many Fragments as needed in a Collection. 

<figure>
  <img src="../images/lecture-images/fragment-structure.png" style="max-height: 35%;" />
  <figcaption style="font-size: x-small">Fig.5 The structure of a Page Fragment collection created locally</figcaption>
</figure>

<br />

Once Fragments are created and organized as desired, users can zip the file and upload it to the Liferay platform.

#### Display Page Templates {#DisplayPage}

Page Fragments created by Front-End Developers can also be used as a stand-in for Web Content Templates when using _Display Page Templates_. 

<div class="key-point">
Key Point: <br />
<b>Page Display Templates</b> allow administrators to create re-usable pages in a Site and include the ability to map Web Content Structure, document metadata, and blog fields to Page Fragment fields. 
</div>

This option gives developers the ability to control the front-end presentation of Blogs, Documents, and Web Content through Page Fragments. 

<figure>
  <img src="../images/lecture-images/mapping-example.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.6 Mapping Fragment Fields</figcaption>
</figure>

<div class="note">
  Note: For more information on how to build Display Page Templates, you can see our documentation: <a href="https://portal.liferay.dev/docs/7-2/user/-/knowledge_base/u/creating-display-pages">https://portal.liferay.dev/docs/7-2/user/-/knowledge_base/u/creating-display-pages</a>
</div>

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Page Fragments are written in a combination of _________________________, _________________________, and _________________________.</li>
  <li>Page Fragments can be displayed on special _________________________ Pages.</li>
  <li>Page Fragments are organized in _________________________ of commonly used fragments.</li>
  <li>Developers can create Collections and Fragments using the _________________________ Generator.</li>
  <li>Page Fragments can be used as Front-end views of _________________________, _________________________, and _________________________.</li>
</ul>
</div>