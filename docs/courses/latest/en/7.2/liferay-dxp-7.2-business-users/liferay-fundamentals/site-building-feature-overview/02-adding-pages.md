## Adding and Managing Pages

Sites are distinct groups of content, which means that administrators can choose to manage content that is restricted to a certain group of Users that have access to the Site Administration panel, or they can display content on _Pages_ to engage with their Users. Pages in Sites have a number of different configurations and options that ensure the best User experiences are possible. 

<figure>
	<img src="../images/page-example.png" style="max-height: 35%" />
	<figcaption style="font-size: x-small">Fig.1 Page example</figcaption>
</figure>

<br />

#### Livingstone Site Pages {#livingstonepages}

Each of the core Livingstone team members will be responsible for different aspects of the site lifecycle. Josiah Copeland will need a way to rapidly generate new sites and pages for the different location sites. Maria Flores and Natalia Michaels will primarily be responsible for generating content for the sites. 

Here are some examples of the different kinds of pages Josiah needs to generate during this process:
* Public Home pages for all the different hotel and resort sites
* Public Offer pages for each hotel and resort with self-updating offer lists
* Private Livingstone Rewards pages for members of the loyalty program
* Private Intranet Communications pages for employees

Some of these pages are unique, while others, such as the hotel and resort pages, will all have the same general format and should be built from the same template. 

#### Page Types and Templates {#pagetypes}

Liferay DXP includes several different page types and templates that empower administrators to create pages that align with multiple use cases. These different kinds of pages can have widgets or coded fragments, not only to provide the best experience for users, but also to manage the pages better. 

<figure>
	<img src="../images/build-pages.png" style="max-height: 20%" />
	<figcaption style="font-size: x-small">Fig.2 Pages Options</figcaption>
</figure>

At the highest level, pages are separated into two groups: _Public Pages_ and _Private Pages_. These pages can be used together with Site Types to create Sites with the flexibility you need. For example, Private Sites, where membership is wholly defined by administrators, can have Public Pages. Or, on the other hand, Public Sites, open to any User for membership, can have private pages. This set-up provides additional flexibility in context of Site Hierarchies. For example, a parent Private Site can have public pages with child Public Sites with private pages. Administrators can craft the Site structures they need and ensure Users can access the content they need.

<div class="key-point">
Key Point: <br/>
<ul>
	<li>Pages are separated into two groups:</li>
	<ul>
		<li><b>Public Pages</b> are generally accessible to anyone that visits the Site.</li>
		<li><b>Private Pages</b> are only accessible to Site Members.</li>
	</ul>
</ul>
</div>

<figure>
	<img src="../images/public-private.png" style="max-width: 95%" />
	<figcaption style="font-size: x-small">Fig.3 Public and Private Pages</figcaption>
</figure>

<div class="key-point">
Key Point: <br/>
<ul>
	<li>You can choose from several different Page Types when creating new pages. The main page types include the following:</li>
	<ul>
		<li><b>Widget Pages</b>: Pages that can have widgets and content added</li>
		<ul>
			<li>These pages are essentially the basic pages from previous versions.</li>
		</ul>
		<li><b>Content Pages</b>: Pages that are editable inline and include page fragments as well as widgets</li>
	</ul>
</ul>
</div>

In addition to the main types, administrators can create several other types of pages or navigation options: 
* **Full Page Application**: Pages that only include one application/widget selected in the page configuration, such as _Blogs, Documents & Media, Forms_, etc.
* **Page Set**: Creates a Title in the navigation for a group of pages
* **Link to a Page of this Site**: Creates a page link to any page in the Site
* **Panel**: Pages that include a left panel that lists widgets, allowing Users to switch between them
* **Embedded**: Pages that use an iFrame to embed external links
* **Link to URL**: Links to any URL in the navigation menu

<figure>
	<img src="../images/page-types.png" style="max-height: 35%" />
	<figcaption style="font-size: x-small">Fig.4 Page Types</figcaption>
</figure>

<br />

Administrators can also create and use reusable Page Templates to rapidly create new pages that have a preset structure of widgets and configuration. By default, when creating new pages, there are global page template options that include some basic structures for Blogs, Search, and Wikis. These default Templates are for Widget pages, specifically. 

<figure>
	<img src="../images/page-templates.png" style="max-width: 100%" />
	<figcaption style="font-size: x-small">Fig.5 Page Templates</figcaption>
</figure>

<div class="key-point">
Key Point: <br/>
<ul>
	<li><b>Page Templates</b> can be created in the following context:</li>
	<ul>
		<li><b>Global Page Templates</b>: These Page Templates are specifically Widget Page Templates that can be accessed from any Site once created.</li>
		<li><b>Site Page Templates</b>: These Page Templates can be either Widget or Content Page Templates and are only usable in the Site in which they were created.</li>
	</ul>
</ul>
</div>

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>Pages are separated into two major groups:</li>
	<ul>
		<li>__________________: visible to anyone that visits the Site</li>
		<li>__________________: only visible to Site Members</li>
	</ul>
	<li>There are two main Page Types that can be created:</li>
	<ul>
		<li>__________________</li>
		<li>__________________</li>
	</ul>
</ul>
</div>  
