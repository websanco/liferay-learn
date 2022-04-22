## Manage and Organize Users

Each business has its own way of organizing its users. This can be an internal departmental structure, or in relation to customers, partners, etc. Many businesses have a traditional hierarchical business structure, while other more modern businesses have more lateral team structures. Whether you're coming with the hierarchy or lateral structures, administrative teams need to know the most effective way to organize their Users on the Liferay DXP platform.

<figure>
	<img src="../images/future-now.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.1 Example Business Hierarchy</figcaption>
</figure>

#### Livingstone's Organization Structure {#livingstoneorgs}

Livingstone Hotels & Resorts has both hierarchical structures and lateral teams that work across their hierarchical structures. Here are the User structures they will need to represent:
1. Department Structure 
    * To start, the main departments that will be working on the platform are the Administration, Hotel and Resort maintenance, and Marketing departments. The platform will manage and support the following Sites:
        * The Livingstone employee intranet, _Livingstone Loop_
        * The _Livingstone Supply Management_ subsidiary Site for handling the procurement of all Livingstone's supplies
        * The Marketing _Livingstone Life_ site used for travel blogs and marketing campaigns
2. Hotel & Resort Groups 
     * Different budget and luxury hotels and resorts will have many sites and groups responsible for maintenance and updates. Each group should be hierarchically represented by what type of hotel as well as the region.
3. Livingstone Rewards Customer Groups
    * Livingstone Rewards members will fit into one of three categories: silver, gold, and platinum. These members will need to be grouped outside of the hierarchical structure and have access to their own dashboard with membership and point information.

Josiah Copeland and the Livingstone team will need to organize the imported Users from the LDAP server to reflect these different User groupings.

<figure>
	<img src="../images/livingstone-org-structure.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.2 Livingstone Department Hierarchy</figcaption>
</figure>

#### Hierarchical Organizations in Liferay {#organizations}

In order to represent hierarchical business structures, administrators can take advantage of _Organizations_.

<div class="key-point">
Key Point:<br />
<b>Organizations</b> give you the ability to organize Users hierarchically according to the structure of real-world groups and companies.
</div>

With Organizations, administrators can create as many levels as needed to accurately reflect real-world structures. Multiple parent Organizations can be made to represent different hierarchies, such as departments, partners, products, etc. Once created, Users can be placed in any Organization they need to belong to. 

It's important to note that members of a parent Organization are not always members of its Sub-Organizations. If there are members that interact with multiple levels of the Organization hierarchy, they'll need to be added to each. Organization Administrators, on the other hand, can manage all the Users in their organization as well as all of its Sub-Organizations.

<figure>
	<img src="../images/organization-roles.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.3 Organization Administrator Example</figcaption>
</figure>

<br />

#### Organization Sites {#sites}

Some Organizations require some kind of web presence. Examples include an Organization representing a product team requiring a public site, or an internal HR organization managing an intranet. When this is the case, administrators can create _Organization Sites_. 

<div class="key-point">
Key Point:<br />
<i>Organization Sites</i> are Sites that can be created in conjunction with an Organization. This means:
<ul>
    <li>Organization Administrators become the Site Administrator with the ability to create and manage pages and content on the Organization Site.</li>
    <li>Members of the Organization become members of the Site, giving them access to private pages and basic Site permissions.</li>
</ul>
</div>

Membership of an Organization Site is restricted to the members of the Organization by default, and Organization Roles can be given to members to determine what level of access they will have in the Organization Site. Controlling is discussed more in the next module.

<figure>
	<img src="../images/organization-sites.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.4 Organization Site Relationship</figcaption>
</figure>

#### Users Groups in Liferay {#usergroups}

Sometimes Users need to be organized outside the Organizational hierarchy. Users in different departments, for example, might need to be grouped together for a specific reason. A single hierarchical structure will not meet the needs of all users all of the time.

<div class="key-point">
Key Point: <br />
<b>User Groups</b> are logical groupings of Users that traverse the Organizational structure.
</div>

There are two primary ways to create User Groups on the platform:
1. User Groups are created by default when an LDAP group is imported, by default.
2. User Groups can be manually created to intentionally create new groupings for a specific purpose.

<figure>
	<img src="../images/user-groups.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.5 User Group Transcending Organization Structure</figcaption>
</figure>

<br />

In addition to standing outside of the Organization structure, User Groups also allow administrators to do the following:
1. Manage Site Membership by assigning entire User Groups to a Site
2. Assign Permissions by assigning Roles to the entire User Group
3. Manage User Profiles or Dashboards by creating predefined pages that the entire User Group has access to.

#### User Group Sites {#usergroupsites}

By default, Users have a Profile and Dashboard on the platform. The Profile is essentially a public set of pages a User can manage, while the Dashboard is a User's set of private pages that Users can manage with different widgets and content. 

<br />

<figure>
	<img src="../images/User-dashboard-profile.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.6 User Profile and Dashboard</figcaption>
</figure>

<br />

Administrators can manage User pages manually, but certain kinds of Users need to have a particular experience on their Dashboard or Profile. By grouping Users into User Groups, administrators can manage their Profile and/or Dashboard with _User Group Sites_.

<div class="key-point">
Key Point: <br />
A <b>User Group Site</b> is a template of pages that is added to the Profile or Dashboard of every User Group member.
</div> 

With User Group Sites, you can deliver Users more tailored experiences outside of the normal context of a full Site. For example, if a particular group of Users is responsible for blogging about company products or information, they can be assigned to a Marketing User Group and given a Profile with all the necessary blogging widgets. 

Livingstone can take advantage of User Groups and User Group Sites in order to group and provide a personalized Dashboard for Livingstone Rewards members with their membership and point information included.

Although Organizations and User Groups are two different ways of organizing Users on the platform, it can be beneficial to use both methods to provide the best User experience for both the administrators as well as the Users themselves. 

<br />

#### GDPR Compliance {#gdpr}

With the introduction of the GDPR laws, companies will also need to be more considerate about how they manage User data. 

<div class="key-point">
Key Point: <br />
Effective May 25, 2018, companies under the jurisdiction of the European Union have to comply with the General Data Protection Requirement (GDPR).
</div>

Here are examples of some broad rules that apply to most, if not all, businesses:
* **Lawfulness, fairness, and transparency (article 5(1)(a))** requires businesses to be forthright and transparent about what personal data is being collected and why.
* **Data minimization (article 5(1)(c))** is a principle that contrasts with some modern businesses’ adoption of a “data maximization” mindset. 
* **Explicit consent (article 6(1)(a))** from the individual is legally required to process non-essential personal data. This will particularly affect marketers that want to collect information to target users with marketing material.
* **The right to erasure (article 17)** empowers individuals with the right to request businesses to erase all personal data from their systems, given the business is not legally required to keep the data (like bank records).
* **The right to data portability (article 20)** similarly gives individuals the right to request businesses to export all personal data from their systems. This prevents vendor lock-in where individuals are unable to choose a competing service due to the magnitude and complexity of personal data with a particular business.

Liferay incorporates strong data protection and security capabilities with User Management, which can help your business move into GDPR-compliance. Out-of-the-box capabilities include user management features, powerful SEO for discovering data, flexible data classification, a granular permission system, and a highly customizable framework. Soon, even more capabilities will be added, including data erasure, anonymization, and management portability to protect Users.

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li> ____________________ give you the ability to organize Users hierarchically according to the structure of real-world groups and companies.</li>
	<li> ____________________ are logical groupings of Users that traverse the Organizational structure.</li>
	<li> ____________________ are Sites that can be created in conjunction with an Organization.</li>
	<li> ____________________ is a template of pages that is added to the Profile or Dashboard of every User Group member.</li>
</ul>
</div>  
