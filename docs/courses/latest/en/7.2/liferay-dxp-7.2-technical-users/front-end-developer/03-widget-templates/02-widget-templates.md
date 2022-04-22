## Customizing Widget Presentation

In order to ensure brand consistency, developers need to be able to use and develop functionality as well as control the presentation. Liferay DXP comes with a number of widgets that can be used to aggregate and display content on Site Pages, providing the functionality needed to provide different User Experiences. Developers can make use of _Widget Templates_ to ensure that the Widget presentation are in line with the branding of the platform.

<img src="../images/widget-content-list.png" style="max-height: 100%;">

#### Displaying Livingstone's Content {#livingstone}

The Livingstone webteam is responsible for developing and maintaining Blog and Article content that was previously published in their magazine, called _Livingstone Life_. The team will be using Widget pages along with a number of different widgets like the Asset Publisher, Blogs, Tags and Category Navigation. In order to ensure an consistant User Experience and the best presentation, Kaito and his team will need to create Widget Templates for these different Widgets.

<img src="../images/wt-example.png" style="max-height: 100%;">

#### Using Widget Templates {#widgettemplates}

Widget Templates are FreeMarker templates that can be used to modify the presentation of a number of existing widgets as well as custom applications built by back-end developers. They reinforce styling, but their main purpose is to display, or _render_, different types of assets. 

Many Liferay widgets provide full Widget Template support:
* Asset Publisher
* Blogs
* Breadcrumb
* Categories Navigation
* Language Selector
* Media Gallery
* Navigation Menu
* RSS Publisher
* Site Map
* Tags Navigation
* Wiki

Developers can add to this list by using the Asset Framework API to enable Widget Templates on custom widgets.

<img src="../images/default-wt.png" style="max-height: 100%;">

<div class="key-point">
Key Point: <br />
Widget Templates can be created for default and custom widgets in order to change the widget presentation on any Site page.
</div>

With Widget Templates, developers can:
* Create multiple front-ends for a single widget that can be selected by a Site Administrator
* Develop with advanced back-end functionality and tag libraries through the GUI without having to deploy changes to the server
* Take advantage of Clay styles and components

<div class="note">
Note: For more detailed information on creating the Java components needed to register a widget for use with Widget Templates, setting up the permissions for the widget in the widgets's <code>default.xml</code>, and exposing the Widget Template options via the Widget's JSP, check out the Liferay documentation at <a href="https://portal.liferay.dev/docs/7-0/tutorials/-/knowledge_base/t/implementing-application-display-templates">https://portal.liferay.dev/docs/7-0/tutorials/-/knowledge_base/t/implementing-application-display-templates</a>. 
</div>

#### Creating Widget Templates {#creating}

Widget Templates can be created globally or for individual Sites. This can be done by going to _`Site Builder → Widget Templates`_ in the Site Administration panel or by deploying Widget Templates through a Liferay Theme's resources importer. When developing on the platform itself, developers can make use of the _Template Editor_ to make use of a number of common variables used in the specific Widget Template being developed. For example, the Asset Publisher widget displays any number of configured Assets. The _Asset Entries_ Field in the editor gives developers a starting point by using an `#if` statement that checks for content entries:

```html
<#if entries?has_content>
  <#list entries as curEntry>
    ${curEntry.getTitle(locale)}
  </#list>
</#if>
```

<img src="../images/template-editor.png" style="max-height: 100%;">

Once created, administrators can select the Widget Template presentation on widgets by opening a widget's _Options_ menu and selecting the _Configuration_ menu. Any available Widget Templates can be set for the widget under the _Display Template_ section. 

<img src="../images/wt-config.png" style="max-height: 100%;">

<div class="note">
Note: Administrators can set default Widget Templates for all Sites or specific Sites. To learn more about this process check out the Liferay documentation at <a href="https://portal.liferay.dev/docs/7-2/user/-/knowledge_base/u/setting-a-default-widget-template">https://portal.liferay.dev/docs/7-2/user/-/knowledge_base/u/setting-a-default-widget-template</a>. 
</div>

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Developers can customize the look-and-feel of widgets using ___________________________.</li>
  <li>Widget Templates can be used on both ___________________________ and ___________________________ widgets.</li>
  <li>Widget Templates are created with ___________________________, and developers can take advantage of the default variables in the Liferay Template Editor.</li>
</ul>
</div>