## Controlling the User Experience in Liferay

Front-end developers need to control the design and branding of the content presentation of individual elements in addition to the Site as a whole. They need to customize how the pages are laid out and how content is displayed on those pages. In order to understand how to control the HTML structures throughout the platform, we should take a look at _FreeMarker Templates_.

#### User Experience in the Livingstone Platform {#Livingstone}

Kaito and the front-end team have been given designs and mockups from Martin and the design team. The team has been able to implement the responsive framework by implementing the Theme and Layout Modules. To keep in line with the brand identity of the company defined in these mockups, the team can take advantage of various kinds of templates to style _Livingstone Hotels & Resorts_ content and widgets. There are a number of _Livingstone_ resources that need to be styled. 

#### FreeMarker {#FreeMarker}

FreeMarker templates in Liferay act as the intermediary between the back-end code in Java and the front-end. They enhance HTML by adding constructs such as variables, conditional statements, and loops. Once it’s processed, the result is HTML, which is then styled by your CSS and displayed by the browser. FreeMarker provides a straightforward, clean, and simple method for incorporating dynamic content in a webpage. It permits the user to use a simple yet powerful template language to reference objects defined in the Java code.

<br />

<div class="key-point">
Key Point: <br />
FreeMarker has a number of constructs that can be used in addition to HTML:
<ul>
      <li>
        <b>Variables</b>: accessed by their names ${var_name}
      </li>
      <li>
        <b>Arithmetic</b>: +, -, /
      </li>
      <li>
        <b>Logical Operators</b>: &amp;&amp;, ||, &lt;=
      </li>
      <li>
        <b>Sequence slices</b>: ${profile.assets[1..]}
      </li>
      <li>
        <b>Including files</b>: [#include “nova.html”]
      </li>
      <li>
        <b>Comments</b>: [#– this is a comment –]
      </li>
      <li>
        <b>If, Else, Elseif Statements</b>: &lt;#if condition>&lt;/#if>
      </li>
      <li>
        <b>Macros/Taglibs</b>: &lt;@example.macro />
      </li>
    </ul>
</div>

<br />

In order to create a variable, it needs to be assigned to a Java method. For example:

```
<#assign show_header_search = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-header-search")) />
```
This variable can now be used in the FreeMarker template like so:

```
${show_header_search}
```

There are also several macros and taglibs that can be used to quickly add in functionality, and even embedded widgets, into your templates. As an example, if Kaito wanted to add a navigation widget to the footer of the Site pages, he could add the following into a custom theme's HTML source file:

```
<@liferay.navigation_menu
  instance_id="footer_navigation_menu"
  default_preferences="${freeMarkerPortletPreferences}"
/>
```

<div class="note">
Note: Developers also have access to Liferay's Experience Language implementation, Clay, when using FreeMarker templates. We can simply add any of these components to any of our HTML structures to inherit the styling needed for consistent design.
</div>

<br />

<figure>
  <img src="../images/lecture-images/freemarker.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.1 FreeMarker</figcaption>
</figure>

<br />

#### FreeMarker Templates {#Templates}

There are a number of different options on the platform that can be used to control the User Experience. To start, developers can make use of FreeMarker Templates on the platform to control things like widgets, content, and notifications.

<div class="key-point">
Key Point: <br />
FreeMarker is the templating language used in all of the front-end templates in Liferay. These templates include:
<ul>
  <li>Templates in Theme Modules</li>
  <li>Web Content Templates</li>
  <li>Widget Templates</li>
  <li>Workflow Notification Templates</li>
</ul>
</div>

<figure>
  <img src="../images/lecture-images/types-of-templates.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.2 Template Types</figcaption>
</figure>

Developers can control the presentation of the following with these Templates:
* **Widget Templates**: Developers can control the presentation of a number of widgets on the platform, such as the Asset Publisher and Navigation, using Widget Templates.
  - Custom-developed widgets and applications can also have WDTs, by using the WDT APIs, allowing for more presentation control on the platform.
* **Web Content Templates**: Web Content can be styled with Web Content Templates.
* **Workflow Notification Templates**: Workflow Notification Templates are freemarker templates that can be used to control the presentation of notifications sent out within a workflow process.
* **Theme Templates**: Theme Templates are customized in a Theme Module and control the Site Page structure and general design.
  * See Module 2 for more information.

Ultimately the goal of using templates is to control the presentation of content on Site Pages. With templates, front-end developers work in tandem with content creators to manage the user experience on the platform.

#### FreeMarker Templates in a Deployment Process {#AsCode}

In order to keep a clean separation of code and platform, the best practice for handling Templates is to treat them like code. Developers can create and deploy their templates in a deployment process by making use of the Resources Importer in a Theme. The Resources Importer is provided on the platform in 7.2 DXP and allows Developers import content, document and template resources along with the Theme. 

<div class="key-point">
Key Point: <br />
Developers can deploy the following Templates alongside Content and Document resources with the Theme Resources Importer:
<ul>
  <li>Web Content Structures and Templates</li>
  <li>Widget Templates</li>
</ul>
</div>

Developers can specify where resources will be imported in the following ways: 
1. Resources can be imported into an existing site template by specifying a value for the `resources-importer-target-value` property in the theme’s `liferay-plugin-package.properties` file:
```
resources-importer-target-value=[site-template-name]
```
2. Resources can be imported into a Site by uncommenting the `resources-importer-target-class-name` property and setting it to 
`com.liferay.portal.kernel.model.Group`:

```
resources-importer-target-class-name=com.liferay.portal.kernel.model.Group

resources-importer-target-value=[site-name] 
```

<div class="note">
  Note: For more information on the Resources Importer, you can reference Front-End Developer module 2.
</div>

#### Page Fragments {#PageFragments}

Page Fragments are another option developers have to control the user experience in a Site. Front-end Developers can use HTML, CSS, and JavaScript to create sections and components of the page, allowing the different content teams to rapidly create Site. 

<div class="key-point">
Key Point: <br />
Page Fragments are reusable HTML, CSS, and JavaScript <i>Sections</i> and <i>Components</i> that can be added to a Page.
</div>

<figure>
  <img src="../images/lecture-images/content-page.png" style="max-height: 31%;" />
  <figcaption style="font-size: x-small">Fig.3 Fragments on a Page</figcaption>
</figure>

Fragments are added to a specific kind of page called _Content Pages_. Content Pages include a menu where page creators have access to the following: 
  1. Sections and components created by developers
  2. The Section builder, which gives the ability to create new fragments by combining existing Sections and Components both out-of-the-box and provided by developers
  3. The Widget menu to combine Widgets with Fragments
  4. The Page Structure
  5. The Look and Feel configuration

<figure>
  <img src="../images/lecture-images/content-pages.png" style="max-height: 33%;" />
  <figcaption style="font-size: x-small">Fig.4 Content Page</figcaption>
</figure>

Page Fragments are created with HTML, CSS, and JavaScript and are created inside of _Collections_. Collections are where developers can manage and group Fragments and can be created on the plaform, or by using the Liferay Fragments NPM generator. Fragments come in two different types: _Sections_ and _Components_:
  * **Fragment Sections**: Sections are fragments of the page that define things like columns, padding, and spacing on the page.
  * **Fragment Components**: Components are fragments that contain text, rich text, and image content and can be added to Sections.

<figure>
  <img src="../images/lecture-images/sections-components.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.5 Sections and Components</figcaption>
</figure>

<div class="note">
  Note: For more information on using the npm workflow for developing Fragments, you can see our documentation: https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/page-fragments-desktop-tools
</div>

#### Layout Templates {#Layouts}

Developers still need to be able to control the structure of the pages in the case where the content team is using the traditional _Widget Page_. In order to control the structure of these Widget Pages, developers can create _Layout Templates_. 

<div class="key-point">
Key Point: <br />
Layout templates control the layout structure of content and widgets on a Widget page.
</div>

Layout Templates are modules that are created using the Liferay Theme Generator and are based on the Bootstrap 12 grid system. This means that developers can create multiple rows with anywhere from 1 to 12 columns. There are a number of out-of-the-box Layouts the administrators can use in addition to any new layouts created by developers.

<figure>
  <img src="../images/lecture-images/layout-templates.png" style="max-height: 70%;" />
  <figcaption style="font-size: x-small">Fig.6 Layout Templates on the Platform</figcaption>
</figure>

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>___________________________ Templates allow developers to customize and provide styling for things like widgets, content, and notifications.</li>
  <li>Developers can take advantage of the Theme ___________________________ to treat Templates like code and deploy them within a deployment process.</li>
  <li>___________________________ can be used to control the presentation of Web Content.</li>
  <li>___________________________ can be used to control the presentation of Widgets.</li>
  <li>___________________________ are reusable HTML, CSS, and JavaScript sections and components that can be added to a Content Page.</li>
  <li>___________________________ are used to control the grid-like structure on a Widget Page.</li>
</ul>
</div>