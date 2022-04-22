## Controlling Specific Permissions on Sites

Controlling access on specific sites is an important part of providing the best user experience. Administrators may need to give more permissions than Roles provide or may need to make sure that they're engaging the right personas with their content. Liferay comes out-of-the-box with features that give administrators control over their site resource permissions.

#### Resource Permissions Menus {#permissionmenus}

Each resource on the Site has an _Options_ menu that includes a _Permissions_ menu. These _Permissions_ menus display the different permissions on the resource in question as well as what each Role can do. 

<figure>
	<img src="../images/permissions-menu-default.png" style="max-width:88%" />
	<figcaption style="font-size: x-small">Fig.1 Permissions Menu</figcaption>
</figure>

<br />

Administrators can modify these permissions menus to grant or remove permissions for any Role available on the platform. 

#### Site Teams {#siteteams}

Site Administrators are typically responsible for managing any individual Site, but by default they do not have the platform permissions to create new Roles. There may be some cases, however, when Site Administrators need to be able to grant specific permissions to different Users rather than assigning a general Role to a group of Users. This can be done with Site _Teams_.

<div class="key-point">
Key Point: <br/>
<ul>
	<li><b>Teams</b> are groups of Site Members that can be given additional permissions on specific resources on a Site.</li>
</ul>
</div>

Teams can be created in Sites. They display in the _Permissions_ menu for each resource in the Site. This essentially allows Site Administrators to treat Users in the team as if they have a Site-specific Role on individual resources. For example, there may be different Users with the User, Content Reviewer, and Site Member Roles that need to be granted the same permissions. The permissions granted to these Teams will apply to every User in the team, regardless of any other Role they have. Administrators can also map User Groups to the Team. 

<figure>
	<img src="../images/site-team-in-permissions-menu.png" style="max-width:88%" />
	<figcaption style="font-size: x-small">Fig.2 Site Team in a Permissions Menu</figcaption>
</figure>

#### Livingstone Life Site Teams {#livingstonesiteteams}

Livingstone wants to use a new site called _Livingstone Life_ to serve as their online travel magazine. The site should increase SEO and customer trust by giving customers valuable free content. Specifically, the site will be used to digitize past articles and to create new blogs and videos that give helpful travel tips and highlight destinations with Livingstone accommodations. 

<figure>
	<img src="../images/blogs-on-page.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.3 Livingstone Life Blogs</figcaption>
</figure>

<br />

This site will include widgets and assets that promote a vibrant community of world travelers. Two of these widgets are the _Blogs_ and _Message Board_ widgets, which promote both outgoing promotions and community discussion and feedback. Although primary bloggers (like Omar Miles) will be given a custom "Blogger" role for this site, Josiah Copeland, as the Site Administrator, needs to create different moderation teams as bloggers spin up new pages for different discussions. Josiah can create different teams that are granted administrative permission on different message boards in the site. 

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>Administrators can grant or remove permissions on a Site resource in the __________________.</li>
	<li>__________________ are groups of Site Members that can be given additional permissions on specific resources on a Site.</li>
</ul>
</div>  
