## Liferay DXP Administration Overview and Navigation

Now that we've looked at how Liferay DXP can solve numerous business problems, let's dive into the product itself. 

#### Menus and Administration {#menuadmin}

On the Liferay DXP platform, there are three primary menus that contain all the features discussed in the previous section:
1. The **Control Menu** 
2. The **Product Menu** 
3. The **Personal Menu**

#### Control Menu {#control}

The **Control Menu** is the menu at the top of each page. This menu will change what it displays based on context. For example, from a Site, administrators are given access to the following:
* The Product Menu to the left
* Page configuration to the right
* The Add menu that contains page widgets to the right
* The Simulation menu for testing responsive design to the right

<figure>
	<img src="../images/site-control-menu.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.1 The Control menu</figcaption>
</figure>

However, when navigating to one of the content applications, such as Web Content, the Control Menu will change to include the following:
* The Product Menu to the left
* The name of the context in the middle
* A tool tip including a description of the context 
* The Options menu to the right that contains configuration

<figure>
	<img src="../images/content-control-menu.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.2 The Control menu in context</figcaption>
</figure>

<div class="key-point">
Key Point: <br />
The <b>Control Menu</b>: 
<ul>
	<li>Appears for all logged-in users by default, but access is restricted based on Role</li>
	<li>Contains administrative tools to the left with <i>Page configuration</i>, the <i>Add menu</i>, and the <i>Simulation menu</i> to the right, when in the Site context</li>
	<li>Displays resource information and an Options menu when in the resource context</li>
</ul>
</div>

#### Product Menu {#product}

The **Product Menu**, seen as simply _the Menu_ in the product, is accessed on the left side of the Control Menu. This is the menu that contains the majority of the Application Suite features. Inside the Product Menu, there are two _Panels_ that distinguish the features:
1. The _Control Panel_
2. The _Site Administration Panel_

<div class="key-point">
Key Point: <br />
The <b>Product Menu</b> is the primary hub for configuring the following:
	<ul>
		<li> Platform-wide configuration settings</li>
		<li> Configuring Sites and creating content</li>
	</ul>
</div>

<br />

<figure>
	<img src="../images/product-menu.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.3 The Product menu</figcaption>
</figure>

<br />

#### Product Menu Panels {#panels}

The **Control Panel** is the top panel in _the Menu_. This panel contains the majority of the applications the are included in the _Liferay Foundation_ suite as well as some from the _Liferay Forms and Workflow_ suite. These applications are included in the following sections:
1. **Users**: Includes User management applications that allow admins to organize, control access, monitor, and provide password policies for Users on the platform
* **Sites**: Includes Site Applications that allow admins to create new Sites and Site Templates
* **Apps**: Includes access to the Liferay marketplace store and purchased menu, as well as the App and license manager, allowing admins to control which applications they need active on the platform
* **Configuration**: Includes a number of instance and system configuration applications
* **Workflow**: Includes the workflow process builder as well as the platform-level workflow configuration 
* **Change Lists**: Includes applications needed to control which lists of changes from multiple Sites to publish with staging

<br />

<figure>
	<img src="../images/control-panel.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.4 The Product menu panels</figcaption>
</figure>

<br />

The **Site Administration Panel** is where creating and managing Sites happens. This section contains the majority of the applications included in the _Liferay Web Experience_ and _Liferay Collaboration_ suites as well as configurations for the specific Site you're on. This panel includes the following sections:
1. **Site Builder**: This where pages, navigation, page fragments, and widget presentation is configured for the Site.
* **Content & Data**: All of the different content types can be created and configured in this section.
* **Categorization**: Categorization and tag metadata is created here to apply to the different assets in the Site.
* **Recycle Bin**: Just like your operating system, assets can be "deleted," which will move them to the Recycle Bin temporarily before permanent deletion.
* **People**: This is where User access to the Site can be controlled, and Personas, or "Segments," can be created to further refine who will see what content.
* **Configuration**: Site configuration and Workflow configuration for the Site can be done here.
* **Publishing**: Staging, Change Lists, and Importing content can be done here.

<br />

<figure>
	<img src="../images/site-admin-panel.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.5 The Site Administration panel</figcaption>
</figure>

<br /><br />

#### The Personal Menu {#personalmenu}

The **Personal Menu** can be found at the top right of each page and allows Users to manage their account settings or sign out of the platform. Users will have access to this menu, even if they do not have the permissions to access the other two menus. The Personal Menu includes:
1. Access to the following:
	* My Sites
	* User Profile
	* User Dashboard
2. Resources and processes related to the User
	* Notifications, Shared Content, workflow tasks, etc.
3. Basic Account settings
	* Name, email, screen name, Organizations, etc
4. The Sign out link

<figure>
	<img src="../images/user-panel.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.6 The Personal Menu</figcaption>
</figure>

#### Help Center and Documentation {#help}

For more information on many of the resources that were highlighted in this module or on development and system administration in Liferay, you can visit the Liferay Help Center at https://help.liferay.com. The Liferay Help Center is home to the official documentation, user guides, tutorials, and Learning Paths for developing in Liferay, with community-contributed resources like the Liferaypedia. You can also check out the Liferay Community site at https://community.liferay.com or our traditional documentation at https://dev.liferay.com. 

<figure>
	<img src="../images/help-liferay.png" style="max-height: 50%" />
	<figcaption style="font-size: x-small">Fig. 7 Visiting the Liferay Help Center</figcaption>
</figure>

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>The three primary menus on the Liferay DXP platform are:</li>
	<ul>
		<li>__________________________</li>
		<li>__________________________</li>
		<li>__________________________</li>
	</ul>
	<li> The product menu includes the following panels:</li>
	<ul>
		<li>__________________________</li>
		<li>__________________________</li>
	</ul>
</ul>
</div>  
