## Roles and Permissions

With all of the assets and data businesses are required to manage and secure, controlling user access is incredibly important. Managing different public and private sites along with their different assets requires a flexible approach to access control. Some of this can be handled by system administrators, but much of this requires a platform that has a great way of handling permissions. System administrators or Ops teams are responsible for securing the different servers, application servers, file system, etc. In essence, their primary job is ensuring uptime and system security. 

<div class="note">
Note: You can learn more about system security in the Liferay DevOps course.
</div>

Platform administrators may not be responsible for securing the entire server set up but they are responsible for ensuring Users have the access they need to have the best possible user experience. They need to control access to the following on the platform:
1. Administrative access
2. Site access
3. Page access
4. Asset access

<figure>
	<img src="../images/user-access.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.1 User Access</figcaption>
</figure>

<br />

In order to ensure that administrators have as much control on the platform as needed, Liferay comes out-of-the-box with a very fine-grained permissions system.

<br />

#### Controlling Access for Livingstone Hotels & Resorts {#controlaccess}

The Livingstone Hotels & Resorts web team is comprised of many people with different job functions on the platform. Different groups in the team are responsible for managing the following:
1. Organization of User Accounts into Organizations and User Groups
2. Creation of Livingstone sites
	* Public Hotel and Resort sites
	* Livingstone Loop, an internal employee site
	* Livingstone Rewards site and dashboards
	* Department sites
	* Livingstone Life Travel Blog site
3. Platform Configuration
4. Development of Reservation widgets for Hotel sites
5. Development of front-end modules to establish branding
6. Inventory Management

Managing these different aspects of the platform requires different kinds of Roles. Let's revisit the core team responsible for implementing the Livingstone solution to determine what they need to be able to do on the platform: 
* **Josiah Copeland**: Responsible for User organization and ensuring Users have access to the proper resources
* **Maria Flores**: Responsible for creating static design content as well as press releases and news articles on the main _Livingstone Hotels & Resorts_ site
* **Omar Miles**: Responsible for curating and publishing travel blogs on the _Livingstone Life_ site
* **Natalia Michaels**: Responsible for marketing, advertising, and promotional copy on the different Hotel and Resort sites
* **Martin Llewellyn**: Responsible for creating Livingstone's brand identity with design mockups for the different sites
* **Kaito Tanaka**: Responsible for customizing the look-and-feel of the platform based on the design mockups

Each of the team members needs access to different levels of the platform to do their specific job roles. Some need access to User management, while others need access to site and asset management. 

#### Liferay's Fine-Grained Permissions {#finegrained}

Liferay DXP comes with a built-in permissions system that allows administrators to control which Users can perform actions on which resources. Permissions are, by definition, the actions Users can be granted on different resources on the platform. If a User needs to create content, for example, the User needs to be able to _add_ and _edit_ a certain kind of content, such as _Web Content_.

<div class="key-point">
Key Point: <br/>
<ul>
	<li><b>Permissions</b> are actions that can be taken on platform resources.</li>
</ul>
</div>

<figure>
	<img src="../images/permissions-menu-example.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.2 Widget Permissions Menu</figcaption>
</figure>

<br />

To see what this would actually look like on the platform, let's take a look at one of the Livingstone examples, specifically _Maria Flores_. Maria needs to create design content as well as press release articles and news articles. This means she generally needs to _do_ the following things:
* _Add_ content
* _Edit_ content
* _Upload_ images
* _Add_ content to pages

The resources she will be primarily working with to accomplish these goals include the following:
* Web Content
* Documents & Media
* Page display widgets

With the actions needed and the resources identified, we know what permissions Maria needs to be given.

#### Using Roles to Group and Apply Permissions to Users {#roles}

In order to grant specific permissions to Users, administrators need to use _Roles_. In Liferay DXP, there are a number of out-of-the-box Roles as well as the ability to create custom Roles. 

<figure>
	<img src="../images/roles.png" style="max-width: 65%" />
	<figcaption style="font-size: x-small">Fig.3 Collection of Permissions in Roles</figcaption>
</figure>

<div class="key-point">
Key Point: <br/>
<ul>
	<li><b>Roles</b> are a collection of permissions that can be granted to Users for the following contexts:</li>
	<ul>
		<li>The entire Platform</li>
		<li>Organizations</li>
		<li>Sites</li>
	</ul>
</ul>
</div>

These three levels are represented by these different Role Types:
* **Regular Roles**: The permissions for Regular Roles are defined for the platform as a whole. Assigning a Regular Role to a User means they'll have the permissions defined in that role across the entire platform.
* **Organization Roles**: Organization Role permissions that are defined only apply to members of an organization. Assigning an Organization Role to a User means they'll have the permissions defined in that Role for their Organization, Sub-Organizations, and/or Organization Sites. 
* **Site Roles**: Permissions for the Site Role apply to a specific Site. Assigning a Site Role to a User means they'll have the permissions defined in that Role for specific Sites. 

<figure>
	<img src="../images/default-roles.png" style="max-height: 35%" />
	<figcaption style="font-size: x-small">Fig.4 Role Types</figcaption>
</figure>

<br />

Each of these scopes comes with a few predefined Roles. By default, there are four Regular Roles:
* Administrator: Users with administrative privileges
* User: Regular User
* Power User: User-defined
* Guest (Visitor): All non-logged in Users

There are three default Site/Organization Roles:
* _Owner_: Owners are super Users of their Site or Organization and can assign Site Roles, including administrator Roles, to Users.
* _Administrator_: Administrators are Super Users of their Site or Organization but cannot make other Users into Site/Organization administrators.
* _Member/User_: All Users who belong to a Site/Organization have this Role in that Site or Organization. 

The scope of each Role will determine the context in which the Role can be granted. 
* Regular Roles can be granted to Users in _Control Panel → Users → Roles_.
* Organization Roles can be granted in context of an Organization, or in the User Account by selecting the Role and Organization context.
* Site Roles can be granted in context of a Site, or in the User Account by selecting the Role and Site context.

<figure>
	<img src="../images/role-assignment.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.5 Regular Administrator Assignment</figcaption>
</figure>

<br />

You can take advantage of these different scopes to provide distributed administration that reflects real-world hierarchies and job functions. Administrators can also edit non-administrator Roles if there are Permissions they need to add or remove. 

#### Creating Custom Roles to Meet Business Requirements {#customroles}

Many of these default Roles will be sufficient to meet some basic use cases, like having a platform-wide administrator or an Organization administrator for different organization structures. If your business requires more custom Roles, they can be created on the platform for each scope.

<figure>
	<img src="../images/custom-role.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.6 Collection Permissions in Roles</figcaption>
</figure>

<br />

When creating custom Roles, it's important to consider the following:
* What actions and resources are required for the Role?
* What Scope will this Role primarily function in?

After answering these questions, the creation of the new Role should follow the _principle of least permissions_. This principle ensures a more secure platform by only including specific permissions needed to complete the job. For example, if a copywriter only needs to work with adding and editing Web Content, the copywriter should not be given a Role with all permissions for Web Content, but only the _Add_ and _Update_ permission for Web Content.

<div class="key-point">
Key Point: <br/>
<ul>
	<li>The <b>principle of least permissions</b> is a best practice to follow to ensure security on the platform.</li>
</ul>
</div>

Custom Roles can be created in the _Control Panel → Users → Roles_ section of the _Menu_. Administrators give new custom Roles a name, and then need to define what specific permissions should be included. 

<figure>
	<img src="../images/defining-permissions.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.7 Define Permissions Menu</figcaption>
</figure>

#### Roles and Workflow {#rolesworkflow}

Liferay's Workflow engine can also create Roles that are identified in the Workflow definition. Targeting a Role in a workflow definition is how administrators can identify that Users with that Role can enter the workflow review process. Here is how the workflow definition will manage Roles:
1. If the Role  exists, it will simply target the existing Role.
2. If the Role does not exist, it will auto-generate the Role.

<div class="key-point">
Key Point: <br/>
<ul>
	<li>A Role assigned in a Workflow Definition that does not already exist will be auto-generated.</li>
</ul>
</div>

When a Role is auto-generated, it's not given any permissions outside of the workflow. A User with an assigned Role has the following abilities:
1. Able to receive notifications when a workflow process begins
2. Able to assign a workflow task to themselves
3. Able to provide feedback in the workflow process (e.g., approving or rejecting)

If an administrator wants to add specific permissions, such as giving a reviewer the ability to copy-edit content, the administrator will need to modify the assigned workflow Roles. There are three auto-generated workflow Roles from the basic workflow definition, out-of-the-box:
1. Portal Content Reviewer
2. Organization Content Reviewer
3. Site Content Reviewer

<figure>
	<img src="../images/roles-in-workflow.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.8 Role Assignments in Workflow</figcaption>
</figure>

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>Permissions are a __________________ on __________________ on the platform.</li>
	<li>__________________ are a collection of Permissions that can be applied to Users.</li>
	<li>The three Role scopes that can be used or created are:</li>
	<ul>
		<li>__________________</li>
		<li>__________________</li>
		<li>__________________</li>
	</ul>
	<li>Workflow can __________________ assigned Roles if they don't already exist.</li>
</ul>
</div>  
