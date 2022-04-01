## Content Feature Overview

At a basic level, users regularly return to different websites, platforms, and services because the information and content is relevant to them. A large part of crafting the best user experiences is a combination of design, availability, and relevance of content. Businesses also need to be able to organize their content effectively, ensure visibility with SEO, and increase searchability on the platform. In many cases, meeting all these needs requires using multiple services and different software. Fortunately, Liferay DXP comes out-of-the-box with the features businesses require to meet the needs of their users.

<figure>
	<img src="../images/coming-back-for-content.png" style="max-height: 35%" />
	<figcaption style="font-size: x-small">Fig.1 Coming back for content</figcaption>
</figure>

<br />

#### Livingstone Content Needs {#livingstone}

Livingstone's many sites have many different kinds of content. Josiah Copeland and the web team are responsible for managing content for all the sites leading up to launch. Let's take a look at some of their primary needs.

The Livingstone Loop site includes sensitive documents like contracts with vendors, customer information, and employee contracts. Josiah and his team need a way to organize all the different types of documents they have, and a way of giving employees access to specific documents. When everything is set up, they'll be handing off management of Livingstone Loop communications to a dedicated Loop team and giving administrative privileges for document management to the HR team.

Each of the Livingstone Hotels & Resorts sites will require primarily informational content as well as calls to action for booking. Josiah and the team need to be able to quickly generate these sites with some pre-existing content that can be modified by the different marketing teams responsible for maintaining the information on each site. 

Finally, for the Livingstone Life site, Omar Miles and his team will need to work on digitalizing existing content from their Livingstone Life magazine and writing new travel blogs to be published on a regular basis. 

For all of these use cases, different teams need to make sure all their digital assets are organized properly in order to improve searchability and make maintenance effective. 

<figure>
	<img src="../images/digital-asset-lifecycle.png" style="max-height: 40%" />
	<figcaption style="font-size: x-small">Fig.2 Digital Asset Lifecycle</figcaption>
</figure>

<br />

#### What are Assets? {#assets}

One of the primary ways of creating content on the platform is to work within the _Asset Framework_. The Asset Framework includes several content types as well as metadata and content organization features. These different types of content are known as _Assets_. There are a number of default Assets on the platform, and developers can also create new ones as needed.

<div class="key-point">
Key Point: <br/>
<ul>
	<li><b>Assets</b> are the various kinds of content that can be added to a platform.</li>
</ul>
</div>

Assets are site-specific, but can be shared across different Sites using Content Sets and the Global Site. Out-of-the-box Assets include:
* Web Content  
* Blogs  
* Documents and Media  
* Dynamic Data Lists  
* Forms  
* Knowledge Base
* Message Boards  
* Polls
* Wikis  

<br />

<figure>
	<img src="../images/asset-in-site-admin.png" style="max-height: 40%" />
	<figcaption style="font-size: x-small">Fig.3 Asset List</figcaption>
</figure>

<br />

<div class="key-point">
Key Point: <br/>
<ul>
	<li>Assets are site-specific, but can be shared across other Sites using:</li>
	<ul>
		<li>Content Sets</li>
		<li>Creating Assets in the Global Site</li>
	</ul>
</ul>
</div>

The Asset Framework includes common features, methods of organization, and metadata for all Assets. These features include:
* Metadata for classifying and organizing Assets  
* Permissions configuration for each Asset  
* Configuration for relating Assets together
* Tags and Categories 
* Asset Comment and Ratings
* Workflow Integration

#### Digital Asset Management Features {#damfeatures}

Along with the other features, Liferay has a number of organization features for the different types of content:
* Repositories
* Folders
* Document Types and Structures
* Tags and Categories

CMIS-compliant repositories can be integrated with your sites in order to distinguish documents and folders on the platform, or in your own repositories.

<div class="key-point">
Key Point: <br/>
<ul>
	<li>CMIS-compliant <b>Document Repositories</b> can be integrated into a site using the following services:</li>
	<ul>
		<li>Web Services</li>
		<li>AtomPub</li>
	</ul>
</ul>
</div>

<figure>
	<img src="../images/repo-options.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.4 Repository Options</figcaption>
</figure>

<br />

The concept of the _folder_ is demonstrated with most available Assets. At times, however, the term may be different. For example, while we organize Web Content and Documents in folders, Message Boards have _Categories_ and Wikis have _Wiki Nodes_. Message Board Categories and Wiki Nodes still provide the same organization and permissions that folders do. The only Assets that don't include this feature are Blogs.

<figure>
	<img src="../images/document-folders.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.5 Documents on the Platform</figcaption>
</figure>

#### Types and Structures {#typesandstructures}

Web Content and Documents are two assets that include features that further specify types. Web Content includes _Structures_. Structures are named after the type of Web Content and fields that can be added to specify what content needs to be included. 

For example, a Press Release web content structure can be added that includes _Title_, _Publication Date_, and _Article_ fields. This means that whenever a Press Release is created by a copywriter, they will need to include all this information. 

<figure>
	<img src="../images/press-release-example.png" style="max-height: 40%" />
	<figcaption style="font-size: x-small">Fig.6 Press Release Example</figcaption>
</figure>

<br />

Administrators can also add what are known as _Document Types_. Document Types are similar to Structures because you can identify specific kinds of documents. The metadata you add to Document Types, however, can be used to store information in the database, improving searchability as well as organization. 

For example, if an administrator wanted to be able to manage contracts on the platform more easily, they could create a Contract Document Type. This would allow them to include the actual contract document along with the ability to add additional metadata such as _Expiration Date_, _Contract Type_, _Status_, etc. Using Document Types together with Folders and Repositories can make the management of many documents that much easier.

<figure>
	<img src="../images/doc-type-example.png" style="max-height: 40%" />
	<figcaption style="font-size: x-small">Fig.7 Document Type Example</figcaption>
</figure>

<br />

<div class="key-point">
Key Point: <br/>
<ul>
	<li><b>Web Content Structures</b> and <b>Document Types</b> can be created and used to further identify specific kinds of Documents or Web Content. In addition, the information stored will increase searchability.</li>
</ul>
</div>

#### Page Fragments {#pagefragments}

In addition to using Assets with their common features, administrators can use Page Fragments. _Page Fragments_ are reusable parts of a page that consist of HTML, CSS, and JavaScript code. They can be used to quickly create editable parts of the page, allowing developers to start content creation on a Site, but giving content and marketing teams the ability to quickly edit and update the content.

<figure>
	<img src="../images/fragment-editor.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.8 Fragment Editor</figcaption>
</figure>

<br />

<div class="key-point">
Key Point: <br/>
<ul>
	<li><b>Page Fragments</b> can be used in addition to Assets on a Site to create the reusable sections and components for the page.</li>
</ul>
</div>

Front-End Developers can use the editor to input their own HTML, CSS, and JavaScript with an output in the bottom right corner. Alternatively, they can take advantage of the Fragments CLI. With the CLI, fragments can be built from scratch, exported, and imported onto the platform. 

<div class="note">
	<b>Note</b>: You can see the Fragment CLI here: <a href="https://github.com/liferay/generator-liferay-fragments#liferay-fragments-cli">https://github.com/liferay/generator-liferay-fragments#liferay-fragments-cli</a>
</div>

Administrators working on Content Pages can also take advantage of the new _Section Builder_ tool to create new sections on pages without code. The Section Builder allows you to add and modify layouts as well as add basic editable components within the chosen layout. This simplifies the ability to create basic page sections, while reserving any advanced section requirements for the front-end developers using the Fragment Editor.

<div class="key-point">
Key Point: <br/>
<ul>
	<li>The <b>Section Builder</b> can be used to create different fragments of the page by adding the layout and components.</li>
</ul>
</div>

<figure>
	<img src="../images/section-builder.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.8 Section Builder</figcaption>
</figure>

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>An __________________ is one of the types of content on the platform.</li>
	<li>Assets can be shared using the following:</li>
	<ul>
		<li>The __________________ Site</li>
		<li> Content __________________</li>
	</ul>
	<li>__________________ and __________________ can be created to identify specific kinds of documents and web content.</li>
	<li>__________________ are reusable sections and components for pages.</li>
</ul>
</div>  
