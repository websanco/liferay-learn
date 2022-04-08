## What are Sites?

Websites have a structure of pages that can be navigated by digital users and are used by virtually every modern business, but a website without content is like a book without pages. When you're building a website, you need to figure out how to include content and how to structure and display that content efficiently. So what is the best way to deliver content in a site for your business? That is the question this course will help you answer.

<figure>
	<img src="../images/lecture-images/site-examples-lrsite.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.1 Liferay Site Management</figcaption>
</figure>

#### Livingstone Hotels & Resorts {#livingstone}

Livingstone Hotels & Resorts has tasked Josiah Copeland with leading a team to build a new platform for the entire company. This platform needs to have an employee intranet, _Livingstone Loop_, that will improve communication and connectivity across multiple departments, increase employee enablement, retention, and morale by helping maintain a work/life balance, and engage employees to ensure content is actually being seen and read. As the platform administrator, Josiah is also in charge of empowering the web development team (Kaito Tanaka, Martin Llewellyn, and Maria Flores) to create the public website for Livingstone Hotels & Resorts. This website needs to:

* Attract customers with clear, persuasive content targeted to what they want
* Implement an intuitive user journey
* Present content that's useful and relevant that increases Livingstone's online presence while simultaneously establishing a connection with the reader
* Reach the appropriate audience

#### Sites in Liferay DXP {#sites}

Sites act as repositories in which all content and content types (Liferay Assets) are stored. Pages can be created within Sites in order to display content to Users. Josiah can add content to these pages via _Widgets_.

<br />

<div class="key-point">
Key Point: <br />
<strong>Widgets</strong> display Site content on pages.
</div>

Josiah can drag and drop widgets from the _Add menu_ at the top right of a page when on a site. Each Asset has a corresponding widget that can be used to display content on a site page. For example, Web Content can be displayed via the Web Content Display widget.

<br />

<figure>
	<img src="../images/lecture-images/ContentManagementApplications.png" style="max-height: 80%" />
	<figcaption style="font-size: x-small">Fig.2 Four of the most commonly used widgets in Liferay DXP</figcaption>
</figure>

<br />

When dragging widgets onto a page, it is important to know where that content can go and how it can be structured on the page. This is where _Page Layouts_ come in. 

In Liferay, widgets can be placed in the center of each page within a grid-like structure. The layout can be divided into up to twelve columns and any number of rows. Layout Templates control this grid. There are many default layouts, but developers can also create custom layouts. These can help unify the look of pages across a site.

<figure>
	<img src="../images/lecture-images/page-columns.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.3 An example of a simple two-column layout</figcaption>
</figure>

<br />

#### The Content Management System {#contentmanage}

Anyone that has used a website before probably has a pretty good idea of how sites look and work. Liferay Sites are similar in concept, but there are a few distinctions that make the creation and management of content and pages more effective. They serve as a foundational block of content, documents, and pages. Each Site contains its own unique group of content and provides a framework for managing Roles and permissions to control access. Content can be displayed to a wider audience on Site Pages.

<div class="key-point">
Key Point: <br />
Liferay <strong>Sites</strong> are groups of Content that can be displayed on Pages.
</div>

<figure>
	<img src="../images/lecture-images/sites-pages-content.png" style="max-height: 30%;" />
	<figcaption style="font-size: x-small">Fig.4 How Content is displayed in Liferay DXP</figcaption>
</figure>

<br />

With Liferay, it's easy to create content-centric Pages such as:

- Front Pages  
- Landing Pages
- Express Content Pages
  - Learning Management System course lessons
  - Intranet content pages

In Liferay, each individual Site holds its own set of content. In certain cases, content needs to be shared across multiple Sites. This is possible either through Parent-Child Site inheritance or by adding content to the Global Site. 

Sharing Content within a Site Hierarchy is specifically done by Site Administrators who have administrative access to each Site in question. When sharing content this way, content is only shared via the display through widgets on pages. We'll discuss this more later.

Sharing Content through the Global Site is generally recommended. Using the global scope helps to provide content types and design to every Site, which can help ensure consistency in a website. Using the Global Site will also prevent compatibility issues with Staging, as we'll discuss in a later chapter.

<br />

<figure>
	<img src="../images/lecture-images/global-or-hierchy.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.5 The Global Site and default site on the Livingstone platform</figcaption>
</figure>

#### The Asset Framework {#asset}

Content teams can work with a couple of content management features within their sites. The first is the traditional _Asset Framework_. The Asset Framework allows for many different kinds of content to be created within the Liferay platform. In addition, there are common features such as adding metadata for content organization or allowing users to interact with comments. This makes for an easy way to sort, find, and interact with the content in the platform.

<div class="key-point">
Key Point: <br />
The <strong>Asset Framework</strong> provides a number of different content types and features.
</div>

#### Experience Management Features in Liferay DXP 7.2 {#expmanage}

Content teams can also work with new Web Experience tools now available in Liferay 7.2. These tools are designed to empower business users in the creation and management of modern sites. These tools include:

- Content Creation tools including _Content Pages and Templates_, _Display Pages_, and _Page Fragments_
- _Navigation Menus_ are used to create custom navigation bars to be added to your pages.

We'll discuss these tools in further detail in a later module.

<div class="key-point">
Key Point:  <br />
<ul>
	<li><strong>Navigation Menus</strong> give you greater control over your user's ability to navigate your site.</li>
	<li>To create reusable types of content for Site Pages easily, you can use the following:</li>
	<ul>
		<li><strong>Content Pages</strong></li>
		<li><strong>Display Pages</strong></li>
		<li><strong>Page Fragments</strong></li>
	</ul>
</ul>
</div>

<figure>
	<img src="../images/lecture-images/modern-site-building-example.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.6 A Page Fragment used to create a modern site page</figcaption>
</figure>

#### Putting Everything Together {#everything}

Liferay has many great individual content features, but putting all the features together makes for a powerful content strategy. Containing and finding content is easy using Sites, and when combined with content-centric pages, Content Creators will have an easy time creating, editing, and displaying content. With Liferay's new content creation tools, users have an easier time than ever getting to that content thanks to Modern Navigation Tools--and the content looks even better. Finally, taking advantage of the Asset Framework features ties it all together, making it easy for content creators and administrators to organize content and for users to interact with that content.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>_______________________ are groups of content.</li>
	<li>Site Pages are the part of a Site where the content is _______________________.</li>
	<li>The _______________________ is a set of features that can be applied to all content.</li>
</ul>
</div>
