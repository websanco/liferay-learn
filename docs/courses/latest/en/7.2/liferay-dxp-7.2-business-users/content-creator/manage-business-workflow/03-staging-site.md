## Preparing Pages and Content for Production

Any public-facing website needs to be created and edited separately from the live site. Users should never deal with a site that is being changed in real time. You need a work-in-progress environment where you can make sure everything is reviewed and finalized before the site is updated. Because multiple teams often work on a platform at the same time, you also need this work-in-progress environment to be collaborative.

Businesses need to modify their pages for seasonal promotions, events, or campaigns. Pages on larger sites also tend to have groups of people working on them at the same time. You need a way to allow different versions of a page to be worked on and merged together without losing any of the new changes.

#### Going Live for Livingstone {#livingstone}

Josiah and his team need to make sure that everything is reviewed and prepared for production in a testing or UAT (User Accepting Testing) environment before customers see any changes. Natalia and Maria will have tons of content coming in, and Kaito needs to implement Martin's designs to put that content onto pages in sites across the platform. This should not impact the user session on the live site until everything has been tested, reviewed, and is ready for user engagement.

The public-facing Livingstone Hotels and Resorts sites have lots of pages that need to be worked on by multiple team members. To quicken the pace of production, Josiah wants to allow his team members to work on a page at the same time and simply put all the changes together as the team finishes them. He needs a way to allow team members to create a new git-like branch version to work on a page, which he can then approve and merge or review and send back to the person who worked on it.

While some businesses prefer a more manual publication process, others are looking for more efficiency and would benefit from an automated process. Natalia and the marketing team want to promote the holiday season by creating special versions of the Livingstone Hotels and Resorts site front page as well as all the front pages for the hotel locations. Martin has designed a holiday version of all these pages, so Kaito and Josiah are tasked with creating these new pages and putting them up come December. Kaito wants to maximize his efficiency by using the regular versions of these pages and editing them, but the team also needs to keep the normal versions for when the pages need to be switched back. Josiah and Kaito will use Page Versions for these special holiday pages. If using a manual publication method, a team member would need to be available to publish to live every time the holiday season comes around. With the Liferay Staging Scheduling feature, however, Josiah can schedule automatic content publishing for whatever date and time is necessary.

#### Staging {#staging}

<div class="key-point">
Key Point: <br />
Liferay's <strong>Staging</strong> environment provides a way to build content, design pages, structure sites, and set up widgets without the changes appearing on a live site.
</div>

<figure>
	<img src="../images/staging-menu.png" style="max-height:25%;" />
	<figcaption style="font-size: x-small">Fig.1 Staging as seen in the Site Administration panel</figcaption>
</figure>

<br />

Staging creates a work-in-progress environment where the Web Team can collaborate to debug and perfect their sites _before_ users see. There are two ways to configure Staging:

1. Local Live Staging  
2. Remote Staging

<figure>
	<img src="../images/staging-config.png" style="max-height:35%;" />
	<figcaption style="font-size: x-small">Fig.2 Configuring Staging in a site</figcaption>
</figure>

<br />

When staging is turned on, a couple of options show up:
* _Page Versioning_: This lets you work in parallel to different variations of the pages. It also lets you keep track of the history of changes in those pages.
* _Staged Content_: This makes it so the content is copied to staging, and it may not be possible to edit the content directly in live.

<figure>
	<img src="../images/staged-content.png" style="max-height:45%;" />
	<figcaption style="font-size: x-small">Fig.3 Configuring the content you want to be Staged</figcaption>
</figure>

<br />

Although Staging does work with pages, widget, and content, Staging should be used first and foremost as a work-in-progress environment for building pages and widgets. Content, as well as the widgets you use to place content on a page, is more flexible. You can choose to stage or not stage the Asset-based widgets listed in _Staged Content_ based on the administrative needs of the site.

For example, if you have a site that utilizes Message Boards, you can choose to stage them. However, this would require anyone who wanted to use the Board to log in to staging, post something, and then wait until a designated publish time. Since this does not make much sense to do, you generally do not need to stage Message Boards even if they are included in your site.

On the other hand, for your sites that use documents only meant for users to view and download (as opposed to allowing users to upload their own documents and interact with them as employees might need to do on your intranet, for example), you may choose to stage documents on the live public-facing sites because only members of your web team need to add them to those sites.

#### Local Live Staging {#local}

The easier way to set up your sandbox environment is _Local Live Staging_. Local Live Staging can be configured simply within the site.

<div class="key-point">
Key Point: <br />
In <strong>Local Live Staging</strong>, both the staged and live sites are hosted within the same Liferay instance using the same database and repositories.
</div>

<figure>
	<img src="../images/local-live.png" style="max-height:35%;" />
	<figcaption style="font-size: x-small">Fig.4 Using the local live staging configuration</figcaption>
</figure>

<br />

The main advantage to Local Live Staging is that content doesn't need to be moved across a network in order to be published. Local Live also doesn't require external hardware such as servers, or repositories, making it the less expensive option. One downside to Local Live is that because both the Staging environment and the live environment are running in the same instance, if Staging goes down, so does the live platform. This also doesn't allow for flexibility with testing new apps and configurations, since changes made to Staging will necessarily happen in live.

#### Remote Staging {#remote}

If you are looking for higher performance in your staging environment due to the size and number of changes made to your sites, _Remote Staging_ is the recommended option.

<div class="key-point">
Key Point: <br />
In Remote Live Staging, the staged and live environments exist on separate instances of Liferay.
</div>

Remote Live keeps staged and live content in separate databases, reducing the risk of corruption, failure, and other database issues. 

Staging is built using a data handler. This means that Staging essentially acts as a mediator between the staged site and the live one. LAR packages from Staging are passed to and published on the live site, so Staging publications are subject to the platform's file size limit. Because of this limitation, we recommend administrators make smaller and more frequent publications with Staging turned on. This will prevent a potential failure to publish as well as minimize publication times, especially since Remote Live has to publish over the network.

For example, one piece of web content can include the following data:

- Structure
- Template
- Metadata added
- Abstract/summary
- Images included
  - If an image is in a folder, Staging will publish the folder and other documents in the folder.
- Actual text/formatting

This is just one article. You can imagine that trying to publish hundreds of these would, at the very least, take a considerable amount of time, if not fail completely.

#### Page Versioning {#version}

There is a simple solution to all of these needs: Liferay's _Page Versioning_. This is a setting available to both Public and Private pages within a site. Page Versioning allows for branching page versions that can either be maintained in parallel or merged with the main variation by an administrator. Versioning also provides a history of all publications, including a _Roll-Back_ feature in case administrators or users prefer the old version of a page or if a change was made that should not have been.

<div class="key-point">
Key Point: <br />
<strong>Page Versioning</strong> allows for the creation of multiple versions of the same page or page set. This can be used for collaboration on the same page or to create distinct versions to be published for various promotional reasons.
</div>

<figure>
	<img src="../images/staging-page-versioning.png" style="max-height:30%;" />
	<figcaption style="font-size: x-small">Fig.5 Configuring Page Versioning for Public and Private pages</figcaption>
</figure>

<br />

Page Versioning is set as an option when configuring the Staged environment. The administrator can choose to allow versioning for both public and private pages, just one or the other, or not allow it at all. Once a site is staged with page versioning allowed, simply go to the site with pages you want to create variations of and click the _Options_ menu at the top right to see the Page Versioning options.

<div class="key-point">
Key Point: <br />
There are two ways Page Versioning can be done:
<ol>
	<li>Page Variations</li>  
	<li>Site Pages Variation</li>
</ol>
</div>

<figure>
	<img src="../images/page-versioning.png" style="max-height:30%;" />
	<figcaption style="font-size: x-small">Fig.6 The two different page versioning options</figcaption>
</figure>

<br />

Page Variations are different variations of a single page in your Liferay DXP site. Site Pages Variations are different variations of a set of site pages. A Page Variation can only exist inside a Site Pages Variation.

Site Pages Variations are useful when you must plan multiple page sets for your site at once. For example, if you wanted to create a different look and feel for your site during the holidays, you could save two different Site Pages Variations, publishing the holiday-themed pages during the holidays and returning to normal after they are over. On the other hand, Page Variations allow you to work in parallel with other people on the same page. Changes can be merged together or you can select one version of the page to publish.

#### Automated Content Publishing {#auto}

You will want to schedule most of the changes you make in your platform. If we created the Holiday Site Pages Variation from the example above, Liferay allows us to automate its publication every year. Even when it comes to routine site maintenance, it is best to schedule updates for times of low-traffic instead of having someone wait to manually publish it at the correct time.

<br />

<div class="key-point">
Key Point: <br />
<strong>Scheduling</strong> allows Administrators to automate the publication process at a scheduled point in the future.
</div>

This can serve two purposes:

* Users can schedule a change to occur during a low traffic time of day to minimize potential user impact.
* Users can add or remove time-sensitive content on a schedule.

This makes scheduling useful for both regular maintenance as well as seasonal content changes. Variations can be used in conjunction with scheduling tools to have different page or site content published on a schedule. For example, you may want to change the look of a site for the holiday season. You can update Page Layouts to feature content that makes sense for the season. Combining repeatable scheduling and versioning features, you can create basic layouts and content for each season and set a schedule for when the site should be updated with seasonal content.

#### Setting Up Publishing Schedules {#schedule}

Administrators can define a Start Date for when the changes will be published and an End Date for when the changes will revert back. They can also select the _No end date_ option if the change is permanent. If, instead, they need to set a recurring update, as required for the _Livingstone Hotels & Resorts_ home page, they can define a publishing schedule that will automatically publish content daily, weekly, monthly, or yearly. For the Tuesday morning updates, the administrator will configure the updates to repeat weekly on Tuesdays at 4:00 A.M., with no end date.

<div class="key-point">
Key Point: <br />
The <strong>Date</strong> section of the Advanced Options of Staging publication is where Scheduling is configured for a new publication process. 
</div>

The content will appear and disappear on schedule, with no further action required from the users. Scheduled events can be managed from the _Schedule_ tab in the Staging menu. Here, the scheduled event can be updated or deleted. 

<br />

<figure>
	<img src="../images/schedule-menu.png" style="max-height:35%;" />
	<figcaption style="font-size: x-small">Fig.7 The Date section of Advanced Publication Settings</figcaption>
</figure>

<br />

In order to schedule seasonal variations for sites and pages, Administrators can select _Site page variations_ from the _Pages_ section when creating a publication process. Administrators would first choose the start and end dates under the _Date_ section, and then select the page variation they would like to schedule, which pages to publish, and any additional look and feel options.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  	<li>There are two types of Staging:</li>
	  <ul>
	    <li>_____________________________</li>
	    <li>_____________________________</li>
	  </ul>
	<li>Administrators can choose which _____________________________ they would like to have within the one-directional staging process.</li>
	<li>It's a best practice to make smaller and more frequent publications when using _____________________________ to avoid failure to publish and long publication times.</li>
	<li>_____________________________ allows for different versions of pages to be created for promotions and campaigns.</li>
	<li>With the _____________________________ feature, Administrators can schedule automatic content publishing for sites.</li>
</ul>
</div>
