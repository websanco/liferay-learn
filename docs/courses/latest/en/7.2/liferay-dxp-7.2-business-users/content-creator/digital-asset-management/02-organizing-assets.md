## Organizing Assets with Metadata

As more content is added to the platform, it becomes increasingly necessary to organize it. Site Administrators should be able to quickly access content for review or to display content on pages within their sites. Likewise, Content Creators should be able to efficiently organize their content for Site Administrators and Content Managers, as well as for SEO purposes, when it is created. In this section, we dive into ways the assets created and managed in Liferay DXP can be organized.

#### Organizing Livingstone's Content {#livingstone}

Josiah wants to take advantage of the collaborative tools in Liferay to promote employee interactions. Josiah would like employees to be able to:

* Post questions and discussion points on message boards within the intranet
* Add new wiki pages for employees to find general information on company policy and FAQs
* Write internal blog posts about about what's new at _Livingstone Hotels & Resorts_ and what's happening around the global offices

Ideally, employees should be able to organize their contributions and interactions themselves so that other employees can find it without Josiah needing to manage every bit of content created. On the other hand, other types of Liferay Assets created in the Livingstone platform need to have a strict organizational structure that Josiah and his team of Site Administrators can define. For example, the thousands of documents that will eventually exist within Livingstone Loop should be organized in a logical structure so that HR employees, managers, and anyone else who needs to access documents can find them quickly. Liferay DXP utilizes out-of-the-box metadata that meets Livingstone's organizational needs.

#### Using Metadata for Organizations {#metadata}

Content can be organized and made more easily searchable using metadata. Aside from Metadata Sets defined by Document Types, users can organize content by attributing other metadata to Liferay Assets.

<div class="key-point">
Key Point: <br />
There are two types of <strong>metadata</strong> that can be used to organize any Asset on the platform:
<ul>
	<li>Tags</li>
	<li>Categories</li>
</ul>
</div>

<figure>
	<img src="../images/categorization-section.png" style="max-height: 30%" />
	<figcaption style="font-size: x-small">Fig.1 Where Tags and Categories can be added in the Site Administration panel</figcaption>
</figure>

<br />

Both types of metadata can be attributed to any asset in the platform, and both can be created and managed from within the _Categorization_ section of the _Site Administration_ panel. You can tag or categorize an asset when it is created or when editing an already existing asset. If you click on the _Metadata_ or _Categorization_ section (which one it is depends on the Asset type) when creating or editing an Asset, you will find an interface for adding Tags and Categories. If no categories are available to be added to the asset (which means no categories have been created), the _Select_ option will not appear.

#### Tags {#tags}

You probably already understand the concept of tagging content, which helps users organize their own content. The most familiar example may be hashtags on social media. In Liferay DXP, Tags are an important tool that can help organize information and make it easier for users to find the content they want. Tags are all-lowercase words or phrases that you can attach to any assets. Users can create posts or repost articles, and they can organize their content with a new tag or a trending tag as they see fit.

<div class="key-point">
Key Point: <br />
<strong>Tags</strong> are a “folksonomy”, which is an organizational system classified by the user.
</div>

Tags allow users to organize their own content and make it easy for others to find the content they are seeking. With the default permissions, Tags can be created by Site Administrators in the _Tags_ section of the _Site Administration_ panel, and by users as they create or edit content. Tags created in the _Site Administration_ panel are available for other users to add to their content when it is created.

<figure>
	<img src="../images/new-tag.png" style="max-height: 30%" />
	<figcaption style="font-size: x-small">Fig.2 A new tag created from Site Administration</figcaption>
</figure>

#### Auto-Tagging Assets {#auto}

In Liferay DXP 7.2, it is possible to tag content automatically. For example, Liferay DXP can scan an image on upload and apply tags that describe the image’s content. This lets you leverage tags without requiring your Content Creators to apply them manually. Currently, auto-tagging can be configured for images, text-based documents, and text-based Web Content.

<div class="key-point">
Key Point: <br />
<strong>Auto-tagging</strong> can be configured to automatically attribute tags to:
	<ul>
		<li>Images</li>
		<li>Text-based documents</li>
		<li>Text-based Web Content</li>
	</ul>
</div>

Asset auto-tagging is enabled by default. It can be configured at three levels:

- Global (System)
- Instance
- Site

If auto-tagging is enabled on the Global, or System, level, auto-tagging can function on any level. If it is disabled at this level, then auto-tagging will not work at any level. When auto-tagging is enabled globally, it is also enabled for each portal instance. However, auto-tagging can be disabled for an individual instance by the Platform Administrator. Similarly, if auto-tagging is enabled at the instance level, then it will also be enabled for each site in that instance; however, it can be disabled on any individual site by a Site or Platform Administrator.

_Image auto-tagging_ works for images uploaded to the _Documents and Media_ library. Image auto-tagging is disabled by default. Enabling it requires that Asset auto-tagging is enabled (this is enabled by default but can be changed by administrators) and that an image auto-tagging provider is enabled (this is disabled by default).

<div class="note">
Note: Tags applied automatically are currently English-only.
</div>

There are three image auto-tagging providers available to use with Liferay DXP:

- TensorFlow
- Google Cloud Vision
- Microsoft Cognitive Services

<div class="note">
Note: TensorFlow is an open-source library that provides machine learning capabilities. TensorFlow image auto-tagging in Liferay DXP is based on TensorFlow’s LabelImage sample for Java, and uses the Inception5h model. Use this with caution, since its accuracy is limited.
</div>

<figure>
	<img src="../images/image-auto-tag-providers.png" style="max-height: 35%" />
	<figcaption style="font-size: x-small">Fig.3 The three image auto-tagging providers</figcaption>
</figure>

<br />

Both text-based documents and text-based Web Content can have _Text auto-tagging_ enabled. Text auto-tagging is disabled by default. Enabling it requires that asset auto-tagging is enabled (this is enabled by default but can be changed by administrators), that a text auto-tagging provider is configured (disabled by default), and that the text-based asset for which you want to use auto-tagging is enabled within the auto-tagging provider.

There are two text auto-tagging providers available to use with Liferay DXP:

- OpenNLP
- Google Cloud Natural Language

<div class="note">
Note: OpenNLP uses the open source Apache OpenNLP library to analyze and automatically tag portal content. Three models are used: location name finder, organization finder, and person name finder. Use this provider with caution, as its accuracy may be limited.
</div>

<figure>
	<img src="../images/text-auto-tag-providers.png" style="max-height: 45%" />
	<figcaption style="font-size: x-small">Fig.4 The two text auto-tagging providers</figcaption>
</figure>

#### Categories {#categories}

Although Tags make for improved searchability, they lack an organizational structure. _Categories_ solve that problem. 

<div class="key-point">
Key Point: <br />
<strong>Categories</strong> are a taxonomy and have a strict hierarchical structure.
</div>

Where Tags are a "folksonomy", Categories are a taxonomy. Categories exist to help administrators organize content in a clearly defined, hierarchical structure. Tags are like a topical concordance, while categories are more like a hierarchical table of contents.

Categories are organized with _Vocabularies_. Vocabularies identify the topic of category groups. For example, if an administrator wanted to create different categories for Office Events, News, and Updates, he or she could create the appropriate vocabularies. Once the vocabularies are created, Administrators can add the necessary category structures.

You can add your Vocabularies and Categories for a site from within the _Site Administration_ panel. If you need category hierarchies to apply to all sites, create Vocabularies and Categories in the _Global Site_.

<figure>
	<img src="../images/new-vocab.png" style="max-height: 38%" />
	<figcaption style="font-size: x-small">Fig.5 Creating a new Vocabulary</figcaption>
</figure>

<br />

Unlike Tags, vocabularies and categories have configuration options and can be locked down with permissions. Vocabularies have three configuration settings:

- Allow Multiple Categories: This option allows Administrators to determine whether or not a user can use multiple categories from this vocabulary on a single asset.
- Associated Asset Types: This option allows you to determine which Assets users can use the categories on.
- Permissions: This is a general option for view permissions.

Once your vocabularies are created, category hierarchies can be created. Categories are simple keywords that can have the following:

- Name: This is the required name for the category keyword itself.
- Properties: This allows you to add key:value pairs that can be leveraged by developers for advanced use cases.
- Permissions: Like vocabularies, this option is for general view permissions for the category.

When creating sub-categories, there will be breadcrumbs in Site Administration to easily navigate to higher-level categories. Once everything is set up, a _Categories Navigation_ widget can be added to the page to allow users to find content based on category structures. This widget can be configured to show all or specific vocabularies. Once placed on a page, clicking on _Categories_ can change the display of different widgets to show any relevant content.

Now that we have categories, Tags, and Document Types set up, let's help Josiah set up fine-tuned folder restrictions for _Livingstone Loop_. 

<div class="summary-chapter">
<h3>Knowledge Check</h3>
<ul>
  <li>Tags are a folksonomy: they are defined by ____________________.</li>
  <li>____________________ are metadata with an organizational structure.</li>
  <li>Auto-tagging can be configured for ____________________, text-based ____________________, and text-based ____________________.</li>
  <li>Categories are a ____________________: they have a clear and defined hierarchical structure.</li>
  <li>Categories are organized using ____________________.</li>
</il>
</div>
