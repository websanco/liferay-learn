## Building Sites

One of the primary ways you can provide a great digital experience for your users is by creating different websites or applications that allow users to access information, request services, and perform actions. Different groups of users need access to different kinds of content and services, so businesses need to manage multiple sites accessible by different devices. 

<figure>
	<img src="../images/devices-and-apps.png" style="max-height: 40%" />
	<figcaption style="font-size: x-small">Fig.1 Devices and Apps</figcaption>
</figure>

<br />

Many companies provide external and internal experiences by building and maintaining different kinds of sites, such as: 
* **Partner Portals** 
* **Intranets** 
* **Customer Portals** 
* **Public-Facing Websites** 
* **Marketing Sites**

When building these digital experiences, web teams need to be able to perform the following actions:
* Quickly create new sites and pages
* Create content to display on pages
* Easily edit and manage content

#### Livingstone Site Requirements {#livingstone}

Livingstone Hotels & Resorts has several websites that need to be created to provide both external and internal users with the experiences they expect from a premier global hospitality company. Here are a few of the main sites that Livingstone needs to create and maintain:
* **Livingstone Life**
* **Livingstone Hotels & Resorts**
* **Livingstone Loop**
* **Livingstone Rewards**

<figure>
	<img src="../images/intranet.png" style="max-height: 20%" />
	<figcaption style="font-size: x-small">Fig.1 Users Accessing the Platform</figcaption>
</figure>

<br />

The **Livingstone Life** site represents the digitalization of the Livingstone Life magazine, which has online articles on topics like travel tips and destination activity recommendations. This site will be primarily managed by _Omar Miles_, the head writer and editor of the Livingstone Life magazine, and his team of writers. They'll need the ability to publish travel-related blogs regularly and have a full copy-editing review process.

<figure>
	<img src="../images/travel-blog.png" style="max-height: 20%" />
	<figcaption style="font-size: x-small">Fig.2 Travel Blogs</figcaption>
</figure>

<br />

The **Livingstone Hotels & Resorts** sites will give users the ability to check availability, schedule stays, and get the latest information on deals and accommodations for each Livingstone location. As Livingstone expands their business, they need to be able to generate new sites quickly to ensure that Users are able to book their stays as soon as new locations are ready for business. All of these sites will be overseen by _Josiah Copeland_ and managed by the web team.

<figure>
	<img src="../images/hotel-example.png" style="max-height: 20%" />
	<figcaption style="font-size: x-small">Fig.3 Livingstone Hotel</figcaption>
</figure>

<br />

The **Livingstone Loop** site will be an intranet that helps the entire company function more efficiently by using different widgets and workflows. HR will need to be able to manage employee documents and employees will need access to news, calendar events, and collaborative posts from other employees. 

<figure>
	<img src="../images/intranet-collab.png" style="max-height: 20%" />
	<figcaption style="font-size: x-small">Fig.4 Intranet</figcaption>
</figure>

<br />

The **Livingstone Rewards** site will be a hotel rewards program site where users can sign up to earn points for free stays and more at different Livingstone locations around the world. 

<figure>
	<img src="../images/membership-program.png" style="max-height: 20%" />
	<figcaption style="font-size: x-small">Fig.5 Livingstone Rewards</figcaption>
</figure>

<br />

#### Liferay Sites {#sites}

In Liferay, any website requirement can be met using Liferay _Sites_. Individual Sites can be created for external and/or internal purposes and contain a number of applications and widgets. Administrators can create different pages and control access to the pages and resources in the Site.

<div class="key-point">
Key Point: <br/>
<ul>
	<li>A <b>Site</b> is a group of content and pages.</li>
</ul>
</div>

In order to ensure that businesses can provide the best user experience for their different Site needs, Site Administration includes the following sections:
* **Site Builder**: contains the navigation menu and page creation, as well as fragments and widget display templates
* **Content & Data**: contains document and content management features as well as some other asset data
* **Categorization**: contains metadata features for organizing assets
* **Recycle Bin**: contains recycle features and expiration time for assets
* **People**: contains membership administration for Site Members, Teams, and Segments
* **Configuration**: contains Site configuration settings as well as Workflow configuration
* **Publishing**: contains Staging configuration as well as functionality to export and import Site data

<figure>
	<img src="../images/site-administration.png" style="max-height: 35%" />
	<figcaption style="font-size: x-small">Fig.6 Livingstone Rewards</figcaption>
</figure>

<br />

#### Site Membership {#sitemembership}

Each Site can have a Site Type that determines how Users become members of that Site. When a User becomes a Site Member, they are given the Site Member Role. This Role gives them access to a Site's Private Pages and basic permissions, allowing the person with the Role to interact with widgets or content on the private pages.

<div class="key-point">
Key Point: <br/>
<ul>
	<li>Sites in Liferay have three different kinds of membership:</li>
	<ul>
		<li><b>Open</b>: When a Site has open membership, any User can become a member of the Site at any time.</li>
		<li><b>Restricted</b>: Restricted membership means a User can request membership to a Site, but the Site Administrator must approve the User's request before they can become a Site Member.</li>
		<li><b>Private</b>: With private membership, Users cannot request Site Membership. Site Administrators must manually select and add a User as a member.</li>
	</ul>
</ul>
</div>

#### Organization Sites and Membership {#orgsites}

Organizations represent business hierarchies such as departments or other business structures. If Users organized into an Organization need a web presence or Site of their own, administrators can create _Organization Sites_.

<div class="key-point">
Key Point: <br/>
<ul>
	<li>An <b>Organization Site</b> is set, by default, as a <i>Private Site</i> that only includes Organization Members as Site Members.</li>
	<ul>
		<li>Administrators can manually manage the membership at the Site level by changing the default.</li>
	</ul>
</ul>
</div>

<figure>
	<img src="../images/organization-site-membership.png" style="max-width: 100%" />
	<figcaption style="font-size: x-small">Fig.7 Collection of Permissions in Roles</figcaption>
</figure>

#### Site Hierarchies {#sitehierarchies}

Just as Organizations are hierarchies that represent the business grouping of Users, Sites can include child sites to represent a content and page hierarchy. When Organization Sites are created, Sites will automatically fit into a Site Hierarchy that mirrors the Organization Hierarchy. 

<div class="key-point">
Key Point: <br/>
<ul>
	<li>Site Hierarchies can be created to reflect hierarchical content structures.</li>
</ul>
</div>

Site Hierarchy membership can be managed individually by administrators of each Site or can be controlled by the top-level administrator. To do this, each child Site can limit their membership to the parent Site. This allows administrators to gate several different Sites and their content/pages behind a Private or Restricted Parent Site. 

<figure>
	<img src="../images/limit-membership.png" style="max-width: 100%" />
	<figcaption style="font-size: x-small">Fig.8 Parent Membership Limitation</figcaption>
</figure>

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>__________________ are groups of content and pages.</li>
	<li>Sites in Liferay have three different kinds of membership:</li>
	<ul>
		<li>__________________: any User can become a member of the Site at any time.</li>
		<li>__________________: Users can request membership of the Site.</li>
		<li>__________________: Users cannot request Site Membership but must be added by Administrators.</li>
	</ul>
	<li>An Organization Site is a __________________ Site that includes Organization members as Site Members.</li>
	<li>__________________ can be created to reflect hierarchical content structures.</li>
</ul>
</div>  
