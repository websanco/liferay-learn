## Liferay DXP Problem Solving

Multiple technologies, services, and solutions can help businesses tackle the challenges that crop up in day-to-day work. Let's talk about some common business challenges for which Liferay has a solution by looking at the business needs of a company called Livingstone Hotels & Resorts. 

#### Livingstone Hotels & Resorts {#livingstone}

Livingstone Hotels & Resorts is a premier hospitality company with 120 hotels and resorts in 72 countries, six global offices, and a popular travel journal called _Livingstone Life_.

<figure>
	<img src="../images/livingstone-intro.png" style="max-height:40%;" />
	<figcaption style="font-size: x-small">Fig.1 Livingstone site created using fragments</figcaption>
</figure>

The Livingstone brand aims to give their customers memorable travel and vacation experiences at the right price point. For Livingstone, this means having affordable, mid-range, and luxury hotel tiers. Livingstone also partners with transportation services, restaurants, and local attractions for an immersive customer experience.

Livingstone's travel journal _Livingstone Life_ wants to provide helpful travel information and include feature articles and how-to guides for both their online and print presences.

As Livingstone continues to expand their impact and meet the needs of both their customers and employees, they're looking to use Liferay to improve efficiency and increase engagement, mirroring their own holistic approach towards their guests with Liferay's approach to its platform.

These are some business problems Livingstone wants to address with Liferay:
1. Development time by using existing features/services
2. Time to market
3. Administrative costs for site upkeep and user engagement
4. Gathering metrics and user feedback
5. Thousands of assets to organize and display

Here is the core team responsible for implementing solutions for these business problems and delivering the Livingstone Hotels & Resorts release:

**Omar Miles**                                                                                    | **Maria Flores**
:--------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------:
*Blog Editor*: responsible for curating and publishing content on the _Livingstone Life_ blog         |  *Webteam*: responsible for creating static web-content
<img src="../images/persona-images/omar-miles.jpg" style="max-width:60%; border-radius:7px;" />    |  <img src="../images/persona-images/maria-flores.jpg" style="max-width:60%; border-radius:7px;" />
**Josiah Copeland**                                                                                   |  **Natalia Michaels**  
*Administrator*: responsible for user management and overseeing the platform                             |  *Marketer*: responsible for advertising materials and news on the public site
<img src="../images/persona-images/josiah-copeland.jpg" style="max-width:60%; border-radius:7px;" />  |  <img src="../images/persona-images/natalia-michaels.jpg" style="max-width:60%; border-radius:7px;" />
**Martin Llewellyn**                                                                                  |  **Kaito Tanaka**  
*Designer*: responsible for creating Livingstone's brand identity and defining a site's look and feel     |  *Web Developer*: responsible for taking Martin's designs and implementing them
<img src="../images/persona-images/martin-llewellyn.jpg" style="max-width:60%; border-radius:7px;" /> |  <img src="../images/persona-images/kaito-tanaka.png" style="max-height:30%; border-radius:7px;" />

#### How Liferay Addresses Common Business Problems {#problems}

Liferay DXP is a platform that includes a number of widgets with varying functionality. Many of these widgets and applications are grouped in out-of-the-box _Application Suites_. The tools in these suites can be used alone or in tandem with additional Liferay products. Let's look at a few examples of how these suites solve some of the issues the Livingstone team is trying to address. 

<div class="key-point">
Key Point: <br/>
Liferay DXP's out-of-the-box <i>Application Suites</i> include:
<ul>
    <li> <b>Liferay Foundation</b>: This suite includes Liferay's core applications, such as menus, editors, and administration.</li>
    <li> <b>Liferay Collaboration</b>: This suite includes collaborative functionality including adding comments, mentions, and widgets such as Message Boards and Blogs.</li>
    <li> <b>Liferay Forms and Workflow</b>: This suite includes the workflow engine, rules engines, and the applications used to create and store data from forms.</li> 
    <li> <b>Liferay Web Experience</b>: This suite includes features that help create, manage, and track content.</li>
</ul>
</div>

#### Cutting Development Time {#development}

With the abundance of features available on the platform, the Livingstone team can use out-of-the-box features to accomplish their goals, only customizing or developing when absolutely necessary. For example, the Livingstone team has a loyalty program and an existing authentication system that stores their customers and customer status. The loyalty program has an impact on the following:
1. Which groups are available and what groups are organized into specific loyalty categories 
* Which sites customers in the loyalty program can access
* Which resources different customers can access

The team can cut development time by using the out-of-the-box _Liferay Foundation_ suite features. They can take advantage of two things, specifically, to cover their needs:
1. The authentication configuration that will allow them to integrate their existing user groups, importing and maintaining group distinctions
* The fine-grained permissions system in Liferay that allows them to control which resources different users can access  

When customization is necessary, developers have access to a number of APIs, project templates, and development tools to make changes fast. We discuss these in more detail in the back-end and front-end development modules.

<figure>
	<img src="../images/roles-example.png" style="max-height:100%;" />
	<figcaption style="font-size: x-small">Fig.2 Configuring user roles on the platform</figcaption>
</figure>

#### Cutting Time to Market {#market}

Livingstone Hotels & Resorts is a rapidly expanding business with new hotels, resorts, and travel package offerings coming out on a regular basis. To make sure their customers are getting the best possible experience, they need be able to get new sites, pages, and content out quickly. 

The team can take advantage of the many reusable templates that the _Liferay Foundation_ and _Liferay Web Experience_ suites offer:
1. Reusable Site Templates can be used to build a foundation that allows Livingstone to rapidly create new hotel or resort sites.
* Reusable Page Templates can be used to pre-create different pages that can be added quickly and maintained by different content or marketing teams.
* Reusable Content and Widget Templates can be created and reused to simplify content presentation and ensure consistency for all new content.
* Reusable Page Fragments have editable components, giving marketing teams the ability to control more pieces of the page.

<figure>
	<img src="../images/barebone-bavarian-offer.png" style="max-height:30%;" />
	<figcaption style="font-size: x-small">Fig.3 Example of a reusable content template</figcaption>
</figure>

#### Cutting Administrative Costs with Self-Service and User Collaboration {#costs}

The _Livingstone Life_ website is intended to be a new project that allows the team to regularly publish travel blogs and collaborate with their user base on different service projects and events. They need to be able to automate publication and use tools that allow for user collaboration and feedback. 

The team can take advantage of features from the _Liferay Web Experience_ and _Liferay Collaboration_ suites:
1. They can use the Asset Publisher to create self-publishing pages by choosing blogs to display, customizing the presentation, and automating the publication process.
* They can use blogs with built-in collaborative features like likes, comments, and replies to write travel blogs and have them published on a regular basis.
* They can use Message Boards as well as other features to build community pages where their user base can interact and participate in service projects in their regions.

<figure>
	<img src="../images/adt-example-3-entry.png" style="max-height:100%;" />
	<figcaption style="font-size: x-small">Fig.4 Blog posts on the Liferay platform</figcaption>
</figure>

#### Gathering Metrics and User Feedback {#metrics}

In order to ensure the best experience for their customer base, the Livingstone team needs a way to gather metrics, internal feedback, and customer feedback on their platform so they can make metrics-driven decisions. 

The team can leverage the _Liferay Forms and Workflow_ suite and use Analytics Cloud to ensure they're getting all the information they need to continue to improve the experiences they deliver as well as their offerings:
1. Forms can be created with different fields and rules to gather the exact information the team needs.
* Sending Forms through a review workflow process will allow the team to make sure the feedback is being analyzed.
* Analytics Cloud integrates with the platform and allows the team to get even more touchpoints and user information from their customers.

<figure>
	<img src="../images/visual-data.png" style="max-height:100%;" />
	<figcaption style="font-size: x-small">Fig.5 Metrics data on Analytics Cloud</figcaption>
</figure>

#### Digital Asset Management {#assets}

Finally, with all the legal documents, contracts, employee documents, and marketing photos that the Livingstone team will need to manage for different hotels and resorts, the team needs features that can let them organize and categorize effectively so they can add and retrieve the information they need as easily as possible.

The team can take advantage of Asset Framework features and document organization in the _Liferay Web Exprience_ suite:
1. Documents can be organized in Folders that include their own business review processes and subfolders.
* Metadata can be added to the documents to ensure all the necessary data is stored and searchable.
* Existing CMIS document repositories can be integrated into different Sites as needed, allowing the team to work with existing structures.

<figure>
	<img src="../images/vocab-example.png" style="max-height:100%;" />
	<figcaption style="font-size: x-small">Fig.6 Organizing content using Vocabularies</figcaption>
</figure>

With all of these features and more, Liferay DXP can meet the Livingstone team's business needs.

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>Liferay includes four application suites that allow users to accomplish a number of business goals out-of-the-box:</li>
	<ul>
        <li> ______________________________: This suite includes the workflow engine, rules engines, and the applications used to create and store data from forms.</li> 
        <li> ______________________________: This suite includes Liferay's core applications, such as menus, editors, and administration.</li>
        <li> ______________________________: This suite includes features that help create, manage, and track content.</li>
        <li> ______________________________: This suite includes collaborative functionality such as adding comments, mentions, and widgets such as Message Boards and Blogs.</li>
	</ul>
</ul>
</div>  
