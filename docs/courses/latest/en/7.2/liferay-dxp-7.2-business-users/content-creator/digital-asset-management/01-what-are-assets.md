## What are Assets?

Once your platform is up and running, it's time to begin creating content. Remember that sites act as content repositories, so you will want to make sure you are working in the appropriate site before you start creating content in Liferay DXP. Then you can start creating a variety of content that can be used throughout the platform using tools that come with Liferay DXP out-of-the-box.

#### Content in the Livingstone Platform {#livingstone}

Maria Flores works as a content creator for Livingstone Hotels & Resorts and has been tasked with creating content for the public-facing Livingstone Hotels & Resorts site. She believes that the content on a page is the most important part of a site and wants to make sure the her content is meaningful by reaching the right audience. Maria also wants site content to be presented in an attractive and engaging way, in line with the mockups provided by the Design Team.

#### Liferay Assets {#assets}

With Liferay, you can create different kinds of content. Any individual piece of content created in Liferay DXP is called an _Asset_.

<div class="key-point">
Key Point: <br />
<strong>Assets</strong> are all the different content types that can be created in a site.
</div>

The different Assets you can create in Liferay DXP include: 

* _Web Content_: the most widely-used Asset for displaying content in Liferay. Web Content has a backbone structure and can be as basic as plain text and images or include a custom design with Templates provided by your Front-End team.
* _Documents and Media_: You can create and import all kinds of documents and files to your platform including:
	* Video
	* Audio
	* Text
	* Images
* _Blogs_: Liferay provides powerful blog creation and presentation tools.
* _Wikis_: You can also create collaborative Wikis where users can interact and work together.
* _Message Boards_: Liferay provides robust forum creation tools that inherit the abilities and features unique to the platform.
* _Forms_: You can create all kinds of Forms to gather and analyze data from users on the platform.
* _Polls_: A little simpler than Liferay Forms, Polls are used to present questions to users and gather their quick responses.
* _Bookmarks_: With bookmarks, you can collect, store, and organize URLs.

#### The Asset Framework {#framework}

<div class="key-point">
Key Point: <br />
<strong>Assets</strong> share the common <strong>Asset Framework</strong> features and functions:
<ul>
	<li>Content teams can organize Assets with folders and by assigning them metadata.</li>
	<li>Assets can be related to each other to provide the best possible user experience.</li>
	<li>Users can comment, reply, rate, and flag collaborative content when displayed on a site.</li>
	<li>Administrators can also set permissions on Assets to determine who can see and update the content.</li>
</ul>
</div>

For example, a content creator can relate a blog post to an image gallery that supplements the blog or a Wiki to Web Content covering a similar subject. Each of these Assets can be organized with tags to make them more easily searchable. Users can also comment on and rate blog posts or images. This level of control over content in Liferay allows for powerful content management. 

<br />

<figure>
	<img src="../images/related.png" style="max-height: 39%;">
	<figcaption style="font-size: x-small">Fig.1 A blog post that has been related to an image gallery</figcaption>
</figure>

The _Asset Framework_ also allows you to access customizable publishing tools like the _Asset Publisher_ and _Content Sets_. With these tools, you can configure the types and ways in which your content is displayed. We will look at publishing content a little later. For now, keep in mind that _Assets_ are the different types of content in Liferay. 

#### Page Fragments and Content Pages {#frags}

Users with web design skills can create editable _Page Fragments_ using HTML, CSS, and JavaScript. Business users and Marketing teams can then take Page Fragments and place them on _Content Pages_ as desired. Content Pages are designed to accelerate and simplify content creation and are, as the name suggests, content-centric. Content editors can directly enter and update text and images in-line by leveraging the rich editor.

<div class="key-point">
Key Point: <br />
<strong>Page Fragments</strong> are editable bits of HTML, CSS, and JavaScript that can be used to build <strong>Content Pages</strong>.
</div>

There are a few differences between Content Pages and the Layout/Widget pages you may be used to:

* _Content Pages_:
	* Content Pages are built by adding Page Fragments to a page and editing their text and images directly on the page, no coding experience necessary.
	* They are recommended for pages with a heavy focus on content specific to that page.
* _Widget Pages_:
	* Widget pages are built by dragging and dropping widgets to a page layout of columns and rows.
	* Widget pages are recommended for pages focused on displaying existing interactive content, displaying lists of reusable content or displaying several reusable content details.

#### Creating Page Fragments {#createfrags}

Page Fragments are reusable pieces of content that can be combined with Assets to create pages that can be easily maintained and edited by Marketing and content teams.

<div class="key-point">
Key Point: <br />
Use the <strong>Fragment Editor</strong> to create Page Fragments or save a completed section of a page as a Page Fragment and reuse it in multiple pages. 
</div>

Content creators with basic web design skills can try their hand at creating content using Page Fragments. All Fragments are part of _Collections_, so content creators will have to create a Fragment collection when building their first Page Fragment. Collections group Page Fragments together for later use and administrative flexibility.

Fragments should be grouped by purpose. When creating new Fragment collections, try to create categories that can be clearly understood and reused. For example, you could create Collections for: 

* Page Headers
* Banners
* Image Slide Shows
* Calls to Action
* Quotes

<figure>
	<img src="../images/new-collection.png" style="max-height:40%;">
	<figcaption style="font-size: x-small">Fig.2 Adding a new Collection to a site</figcaption>
</figure>

<br />

Once a content creator has created his or her collection, he or she can open up the Fragment Editor and create a new Fragment with HTML, CSS, and JavaScript to feature in whichever page it should be featured.

Fragments are designed to be reusable and easily editable once added to a page. The Design and Web Teams can work to create pixel-perfect page templates that other users can take and use across the platform. Using the Page Template Editor, content creators and business users can place available Page Fragments on a page and quickly design and publish beautiful site pages.

#### Document Types and Metadata {#doctypes}

While uploading documents using the _Basic Document_ type works for some uploads, as the number of uploaded files increases, creating categories and types of documents will make file management easier and more efficient. Administrators can use **Document Types** and metadata to organize their content. They can add metadata to different kinds of documents to make it easier to organize and search by creating custom **Document Types**. 

<br />

<figure>
	<img src="../images/basic-document.png" style="max-height:30%;" />
	<figcaption style="font-size: x-small">Fig.3 Adding a Basic Document to a site</figcaption>
</figure>

<br />

Document Types give the user the ability to describe and categorize documents by adding custom metadata to uploaded files. This metadata not only helps distinguish files, it also makes files searchable by metadata and allows the user to take advantage of Liferay's search features. 

<div class="key-point">
Key Point: <br />
<strong>Document Types</strong> associate a set of custom metadata fields with files of a certain type as soon as they are added to the platform.
</div>
  
Document managers can benefit from adding document types, as they not only distinguish what kind of documents they need to upload, but can then add metadata. By adding metadata descriptions to files as they are uploaded, users are defining how the documents will be categorized. 

For example, instead of having all uploaded PDFs show up as Basic Document types, administrators can distinguish the PDF types that contain content that relates to their marketing team from the types that contain financial reports. They can create a Document Type called _Marketing Data_ and another called _Financial Reports_ and include additional information that needs to be filled out. This way, it's clear which documents are which, and the metadata will be recorded with every submission.

<div class="note">
Note: Metadata is data that describes other data. For digital files, metadata is added to the digital objects and describes them using metadata fields. For example, an MP3 file for a particular song may contain data about the track artist and track length.
</div>

If an administrator finds that there are some common sets of metadata that should span multiple Document Types, the administrator can create reusable **Metadata Sets**. Metadata Sets are metadata fields that are grouped together outside of any particular Document Type. For example, there may be some commonality between multiple different kinds of Employee Document types. An administrator could create a Metadata Set that includes fields for _Employee Name_ and _Employee ID_. He would then be able to simply add these to any new Employee Document types without having to manually create them again.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Assets are all the different ___________________ types in a site.</li>
  <li>The Asset ___________________ allows users to manage, organize, and present content effectively.</li>
  <li>_________________ can be used to build Content Pages.</li>
  <li>Users can also create Document Types, which are groups of _________________that improve document organization and SEO.</li>
</ul>
</div>
