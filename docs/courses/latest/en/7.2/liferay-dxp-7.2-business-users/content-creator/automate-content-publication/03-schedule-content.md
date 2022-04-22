## Create Self-Publishing Pages

The more content you have in your platform, the more time it takes to manage. Automating the publication process can dramatically improve efficiency and time-to-market. In this section, we will look closely at how to configure Self-Publishing pages that automatically publish content created by Content Creators on your digital experience platform.

#### Livingstone's Automated Publishing {#livingstone}

As more content is created in the Livingstone platform, it has become more time-consuming for members of the Web Team like Maria Flores to update the sites she manages. Adding new Updates, News, and Press Releases to the Livingstone Hotel & Resorts corporate site can be a major time sink. Right now, Maria and the rest of the Web Team have to go through several steps to create and update the page with the new content:

* Add Web Content
* Write and format the content article
* Include any images needed for the article
* Publish the article
* Go to the page where the content should display
* Add the content to the page
* Configure the widget to display the content as desired

#### Asset Publisher {#ap}

Your web team should have a practical way to automate updates on pages they manage. The _Asset Publisher_ is the perfect solution.

<div class="key-point">
Key Point: <br />
The <strong>Asset Publisher</strong> contains a number of configuration options for choosing which assets to automatically display and at what time intervals.
</div>

<figure>
	<img src="../images/asset-publisher.png" style="max-height:14%;" />
	<figcaption style="font-size: x-small">Fig.1 An example of an Asset Publisher displaying a Web Content Article</figcaption>
</figure>

Since the Asset Publisher publishes assets, it excels at publishing mixed content types like images, documents, blogs, and of course, web content. This helps in creating a more dynamic web site: you can place user-created wiki entries, blog posts, or message board messages in context with your web content.

#### Configuring the Asset Publisher {#configure}

The Asset Publisher works by querying for mixed types of content on the fly. Since you can control what and how content is displayed from one location, the Asset Publisher helps to push up the most relevant content to your users. The configuration options of the Asset Publisher allow administrators to determine what, when, and how content is displayed.

<div class="key-point">
Key Point: <br />
Administrators are able to automate the content display process and create a self-publishing page once the Asset Publisher is fully configured.
</div>

The Asset Publisher has two starting options in the _Asset Selection_ section.

* _Manual_ lets you manually select content to display in list form.
* _Dynamic_ lets you set up filtering that can dynamically determine what shows up on the page.

<figure>
	<img src="../images/asset-selection.png" style="max-height:29%;" />
	<figcaption style="font-size: x-small">Fig.2 Choosing between dynamic and manual publishing</figcaption>
</figure>

<br />

The _Asset Selection_ configuration will display the following filtering options: 

* _Source_: Here, you can select the site scope and Asset type. Depending on the Asset type, new filtering options become available, such as field or structure.
* _Filter_: With this option, you can filter content based on tags or categories.
* _Ordering_: Here, you can choose how to group and order content by things like title, date, etc.

<figure>
	<img src="../images/asset-configuration.png" style="max-height:15%;" />
	<figcaption style="font-size: x-small">Fig.3 Choosing which Asset Type to display in an Asset Publisher</figcaption>
</figure>

<div class="note">
Note: You may see the <i>Custom User Attributes</i> section. This section has been deprecated and no longer works.
</div>

The _Display Settings_ option has a large number of options for adjusting display as needed. The first section includes the _Display Template_ section.

<br />

<figure>
	<img src="../images/display-settings.png" style="max-height:25%;" />
	<figcaption style="font-size: x-small">Fig.4 Configuring the Asset Publisher</figcaption>
</figure>

<br />

There are also a number of toggle-able options that can be changed:

* _Show Add Content Button_: This option allows you to turn the _Add_ button on or off from the widget itself. This ensures that nobody adds additional content manually once configured.
* _Display Template_: This section allows you to choose from a number of default display templates or any custom Application Display Templates that have been created. The display template will determine the next few configuration field options.
* _Show Available Locales_: If there are any translations available, they will show as an option.
* _Set as the Default Asset Publisher for this Page_: This is a very important option that allows us to create a Content Display Page. 
* _Additional Enable Options_: There are a number of additional options that can be enabled, such as comments, flags, print mode, etc.

<br />

<figure>
	<img src="../images/set-and-enable.png" style="max-height:47%;" />
	<figcaption style="font-size: x-small">Fig.5 The toggle-able options when configuring the Asset Publisher</figcaption>
</figure>

<br />

The _Show Metadata_ section allows you to determine what metadata you want to display with your assets.

* _Show Metadata Descriptions_: This option shows metadata descriptions such as tags, related assets, etc.

<br />

<figure>
	<img src="../images/show-metadata.png" style="max-height:40%;" />
	<figcaption style="font-size: x-small">Fig.6 Example metadata that can be used to configure the Asset Publisher</figcaption>
</figure>

#### Display Page Templates {#display}

_Display Page Templates_ provide a way of displaying content on a specialized page and provide a visual editor so that non-technical users can define how structured web content will be displayed. Designers and Content Creators are given greater control over the look and feel of web content, while also simplifying the management of navigation from any place within a site. Display Pages are created using existing Page Fragments and mapping Web Content to the editable elements in the Page Fragments.

<div class="key-point">
Key Point: <br />
<strong>Display Page Templates</strong> are a dynamic page template that allows you to configure and display Content by mapping it to existing Page Fragments.
</div>

With Display Pages, the front-end team can generate Page Fragments that can be used as templates to style the way Content is displayed once it is published. Display Pages are created and managed by going to _`Site Builder → Pages`_ in the _Site Administration_ panel and clicking on the _Display Page Templates_ tab.

<figure>
	<img src="../images/manage-display-page.png" style="max-height:35%;" />
	<figcaption style="font-size: x-small">Fig.7 A Display Page mapped for News Web Content</figcaption>
</figure>

<br />

When creating a new page, users can add Fragments to the Display Page and then choose which Asset fields to map to the editable elements in the Page Fragment. For example, a Fragment with an editable title, sub-title, and body text can be used to map to the Title, Summary, and Content fields for the Basic Web Content structure.

<br />

<figure>
	<img src="../images/map-fragment.png" style="max-height:35%;" />
	<figcaption style="font-size: x-small">Fig.8 Mapping Content fields to Page Fragment elements in a Display Page Template</figcaption>
</figure>

<br />

When mapping Web Content fields to the Fragment, users can select from all of the currently available Web Content structures. Once the Display Page has been created, it can be set as the default Display page for all Web Content of a certain type.

Users can then add an _Asset Publisher_ to a page to display new Web Content. When content that has been configured to display in a Display Page is selected, it will open in a new Display Page and display in the Fragment selected.

<br />

<figure>
	<img src="../images/display-page-content.png" style="max-height:30%;" />
	<figcaption style="font-size: x-small">Fig.9 Viewing a Display Page in a site</figcaption>
</figure>

<br />

The user is directed to the Display Page using what's called a _Canonical URL_. The Canonical URL includes the Site URL with a dash and the name of the article that needs to be displayed. For example, if we were looking at a Canonical URL for a web content article named "A New Hotel in the Making," the URL would be displayed as the following:
```
http://www.livingstone.com/web/guest/-/a-new-hotel-in-the-making
```
This link can be used as a reference elsewhere on the platform to direct users to the full display of a content article on its designated Display Page.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Using the Asset Publisher, users can automate _______________________ on their sites.</li>
  <li>The Asset Publisher can publish content manually or _______________________.</li>
  <li>_______________________ Templates allow you to map Content fields to Page Fragment elements.</li>
</ul>
</div>
