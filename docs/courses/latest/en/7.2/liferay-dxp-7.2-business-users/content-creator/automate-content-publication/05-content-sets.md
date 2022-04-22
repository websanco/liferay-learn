## Content Sets and Personalization

At some point, it will make sense to start grouping all the different types of content in a site in different ways depending on how you want content to be displayed to your users. The Asset Publisher already works to do this as previously discussed, but you may want to have adaptable groups of content that change depending on who is viewing the content. To do this, Content Sets have been introduced for Liferay DXP 7.2.

#### Content Groupings for Livingstone's Platform {#livingstone}

Livingstone Hotels & Resorts wants to display different groups of content on the Livingstone Life site depending on whether or not a user is subscribed to the blog. Josiah is tasked with creating a unique group of Omar's content to show to subscribers of Livingstone Life and a different group of content to non-subscribers. Content Sets are a great solution.

#### Content Sets {#sets}

A Content Set is exactly what it sounds like: a set of content items. An administrator can define a list of content, and then that list can be displayed on a page. The way that the Content Set is displayed is determined by the method used to display it. For example, if the Content Set is being viewed on a mobile device, it could be displayed as a simple list of titles, and selecting a title would cause the full article text to be displayed in a readable form. The same Content Set could be displayed in a web browser with the full content of each article.

<div class="key-point">
Key Point: <br />
<strong>Content Sets</strong> are groups of Content meant to be displayed together.
</div>

<figure>
	<img src="../images/content-sets.png" style="max-height:40%;" />
	<figcaption style="font-size: x-small">Fig.1 Content Sets as viewed in Site Administration</figcaption>
</figure>

<br />

Content Sets take the idea behind the Asset Publisher, i.e., lists of different types of content organized based on metadata, and expands it outside of the context of that single widget. Content Lists are created outside the context of a specific application or widget and can be used and re-used in different areas of the platform.

#### Creating Content Sets {#create}

Content Creators can create Content Sets from under the _Site Builder_ section of the _Site Administration_ panel. Content Sets can use either manual or dynamic content selection (similar to how the Asset Publisher can be configured) to choose which pieces of content you want to include.

<div class="key-point">
Key Point: <br />
There are two ways to create Content Sets:
<ul>
	<li><strong>Manually</strong></li>
	<li><strong>Dynamically</strong></li>
</ul>
</div>

<figure>
	<img src="../images/manual-dynamic.png" style="max-height:35%;" />
	<figcaption style="font-size: x-small">Fig.2 Choose between Manual and Dynamic selection</figcaption>
</figure>

<br />

Defining Content Sets manually means that you can choose each bit of content you want to include, but content must be changed manually any time you want to update it. Defining Content Sets dynamically means that you choose content based on certain parameters, and any content that meets those parameters will be displayed, so the Content Set will update automatically.

#### The Asset Publisher and Content Sets {#asset}

Content Sets are primarily displayed through the Asset Publisher. However, custom applications can be built that utilize Content Sets. Content Sets can also be created using the Asset Publisher. 

<div class="key-point">
Key Point: <br />
The <strong>Asset Publisher</strong> is used to display Content Sets and can even be used to create them.
</div>

<figure>
	<img src="../images/asset-publisher-create-sets.png" style="max-height:50%;" />
	<figcaption style="font-size: x-small">Fig.3 The Asset Publisher configuration can be used to create Content Sets</figcaption>
</figure>

<br />

To create a Content Set using the Asset Publisher, an Asset Publisher must be configured on a page. From there, it is as simple as the click of a mouse: go into the configuration of an existing Asset Publisher and click _Create a content set from this configuration_. By clicking that button, you create a Content Set with the same configuration as the Asset Publisher that is added to the _Content Sets_ section of the _Site Administration_ panel.

#### Content Set Personalization {#person}

In the last section, we covered User Segments and Content Page personalization. Content Set personalization makes up the last aspect of Personalization Experience Management in Liferay DXP. Distinct variations of a Content Set can be created and assigned to different User Segments similar to how Content Pages can have multiple Experiences created for different Segments.

<div class="key-point">
Key Point: <br />
<strong>Personalized Variants</strong> of Content Sets can be created to target different User Segments.
</div>

<figure>
	<img src="../images/personalized-variation.png" style="max-height:19%;" />
	<figcaption style="font-size: x-small">Fig.4 Multiple variations of Content Sets can be created for different User Segments</figcaption>
</figure>

To create a Personalized Variation of a Content Set, the Content Set must already be created. When a Content Set is created, it becomes the _Default Content Set_ similar to how when a Content Page is created, it becomes the Default Experience for that Content Page. To create a new Personalized Variation from an existing Content Set, access the Content Set from the _Site Administration_ panel and click the _New Personalized Variation_ button. From here, you can change how the Content Set is configured and select the User Segment you want to be able to see this variation instead of the default variation.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Groups of content that are meant to be displayed together can be organized into __________________________.</li>
  <li>The two ways of creating Content Sets are:</li>
  <ul>
  	<li>__________________________</li>
  	<li>__________________________</li>
  </ul>
  <li>Use the __________________________ to display and even create Content Sets.</li>
  <li>Target different User Segments by creating __________________________ of Content Sets.</li>
</ul>
</div>
