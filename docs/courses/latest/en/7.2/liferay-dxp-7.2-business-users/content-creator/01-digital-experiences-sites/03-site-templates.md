## Getting Sites Out to Market Quickly with Site Templates

Companies can grow and change very quickly, so having a platform that reflects that by adapting to change seamlessly is important. Businesses need the ability to create a vast variety of content rapidly and to build sites that display that content in a way that makes sense. The creation of new sites should be easy and efficient, meeting the demand of modern businesses.

<br/>

#### Adding Sites to the Livingstone Platform {#livingstone}

Josiah has been tasked with creating and overseeing all of Livingstone's hotel and resort sites as well as their regional office sites for the _Livingstone Life_ magazine. Each of the hotels, resorts, and offices require consistent sets of pages, widgets, styling, and even content. Similar to Page Templates in the last section, Livingstone's Marketing team requires that similar types of sites offer similar user experiences. Josiah needs a way to create sites with the same page structure quickly, avoiding the time sink of creating the same site type over and over manually. It would be even more helpful if the site pages could come pre-packaged with the widgets and content expected of a site of that type.

<br/>

#### What are Site Templates? {#templates}

In the same way that users can create Page Templates, they can also create _Site Templates_ for rapid site creation. Instead of manually creating each site, the site development team or platform administrator can create and use site templates to rapidly create new sites of the same type.

<div class="key-point">
Key Point: <br />
<b>Site Templates</b> are predefined sets of pages, layouts, widgets, and content.
</div>

New site templates can be created by going to _`Control Panel → Sites → Site Templates`_. There are two default templates, _Intranet Site_ and _Community_, that are intended as Private and Public Sites, respectively. Since Site Templates are _Page Sets_, they need to be set as either the Public or Private Pages of a site.

<figure>
	<img src="../images/lecture-images/new-site-template.png" style="max-height: 20%" />
	<figcaption style="font-size: x-small">Fig.1 Adding a new site template from the Control Panel in Liferay DXP</figcaption>
</figure>

<br />

New sites created from a site template have all the pages and widgets that were added in the site template. To view the pages of a new site, click _`Sites → Sites`_ in the _Control Panel_, and then click the _Options_ icon and choose _`Go to Public/Private Pages`_ next to one of your new sites or navigate to the site using the _Site Selector_ in the _Site Administration_ panel.

#### Rapidly Generating New Sites {#rapid}

Site templates streamline the site creation process for administrators, making it easy to create sites quickly. Although the pages and widgets of sites created from the same site template are the same, each site will quickly be filled with unique information as users add and share content within the sites. After a site is created from a site template, site administrators and developers can add new pages, widgets, and content to the site as necessary.

<div class="key-point">
Key Point: <br />
A site created from a site template will have the same pages, widgets, and content as the template.
</div>

<figure>
	<img src="../images/lecture-images/new-page-site-template.png" style="max-height: 27%" />
	<figcaption style="font-size: x-small">Fig.2 Adding a new page to a site created from a site template</figcaption>
</figure>

Page management for a Site Template is a little different from page management for Page Templates. In a Site Template, not only can an administrator create pages, add widgets, and change configurations, but administrators can also include content. Content created in a Site Template will act as the default content of a new site created using that Site Template. This can be particularly helpful with Page Fragments and Content Pages. Creating a site with pages already filled with content allows for rapid site generation and enables the Marketing and Content teams to start work in the site without needing further development from the web development team.

<div class="note">
Note: Content inherited from Site Templates will be an individual instance of that content tied to the new Site ID. 
</div>

#### Propagation of Changes for Sites Built from Site Templates {#propagation}

A Page Template's _Inherit Changes_ option has a parallel option for Site Templates. Changes made to a Site Template can be propagated to sites whose page sets are linked to the Site Template. This link is created when you add a site based on a Site Template with the _Enable propagation of changes from the Site template_ box checked. Unlike the Page Template option, however, keeping this option turned on for the Site Template does not restrict configuration in the new site. This means that administrators can make whatever changes to the site that they might need, and, if necessary, have the option to revert everything back to the Site Template base.

<div class="key-point">
Key Point: <br />
Enabling the propagation of changes from the Site Template links a site to its site template, ensuring all changes made to the template are reflected in the site.
</div>

<figure>
	<img src="../images/lecture-images/site-template-with-apps.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.3 A site created with Livingstone's Regional Office site template that has had additional pages added to it</figcaption>
</figure>

<br />

With the propagation of changes from the site template enabled, new pages, content, and widgets can still be added to a site created with the site template. However, the default pages of the Site Template cannot be reordered, removed, or replaced. If a site created from a site template has custom site pages as well, the site template pages always appear first. The custom pages will appear after the site template pages.

<div class="key-point">
Key Point: <br />
Pages added to a site created from a Site Template are unaffected by changes propagated from the Site Template.
</div>

Change the template from the Control Panel to make changes to Site Template pages. To make changes to or to add custom pages on a site created from a Site Template, go to _`Site Builder → Pages`_ in the _Site Administration_ panel for the site you are customizing.

<div class="summary">
<h3>Knowledge Check</h3>
	<ul>
	  <li>________________________ can be created to predefine page set widgets, configuration, and even content, to rapidly generate new sites.</li>
	  <li>Turn on the Enable Propagation of changes from the Site template option in order to inherit ________________________ from the template to the Site Pages.</li>
	  <li>Inherited changes from a _______________________ template lock down the page from configuration changes, while inherited changes from a Page Template still allow administrative flexibility.</li>
	</ul>
</div>
