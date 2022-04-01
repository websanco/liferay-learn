## Business Process Considerations

Staging provides an excellent way of creating, reviewing, and publishing content, but streamlining a workflow is the key to unlocking its full potential. It is critical to notify the web team, marketing team, and design team when it is time to make and review changes in order to optimize efficiency when building your platform. When reviewing pages in a site, it's also very important to be able to see what the page will look like on different devices with various screen sizes. Implementing all of this with a work-in-progress environment before pages are published is important to platform development.

#### Updating Livingstone's Platform Systematically {#livingstone}

So far, the Livingstone Hotels & Resorts platform has been updated in a kind of free-for-all. Some pages have had a specific workflow implemented, but a lot of the platform has been built by Content Creators adding their content while the Design team has created the layouts and look. Josiah has been left to organize it all. Josiah wants to define a workflow for the entire platform that notifies everyone when scheduled updates for the platform are going to go live, and by when they need to get things finished. He wants to create a system that minimizes labor while maximizing the amount of content they are able to produce without error.

#### Implementing Workflows Across the Platform {#work}

As we learned in an earlier section, creating a review process in Liferay DXP is easy using _Workflows_. Although publishing without a workflow might work well for smaller projects, publishing large-scale productions will go much more smoothly with a Workflow in place so that page changes can be reviewed and approved systematically. Without a review process, you can unintentionally publish content that is not up-to-date or that is not presented the way you want it to be.

<div class="key-point">
Key Point: <br />
Setting up a Workflow for both Content and Pages ensures that the best version of a site is published.
</div>

The best practice for a workflow starts when content is created. Content should be submitted for review, reviewed by an Administrator and/or a Content Reviewer, and placed on a page (or pages). Once all the content has been placed in a page, the page should be reviewed in Staging, and, once all the content for the latest update has been completed, the Site or Platform Administrator can publish everything to live.

<br />

<figure>
	<img src="../images/workflow-best-practice.png" style="max-height:25%;" />
	<figcaption style="font-size: x-small">Fig.1 The best practice workflow for Content Creation in Liferay DXP</figcaption>
</figure>

<br />

Administrators can set up a workflow just like this one for their Web Teams. Content Creators can create content and submit it for review. Once an Administrator approves, Content Creators can put their content onto the page via various content widgets (e.g., Asset Publisher, Web Content) that meet the requirements of Design. After all the content has been added to the page, it can go through its own review process. An Administrator will review the page and send it back to the team if there is anything that is not ready for publishing. Once everything looks the way it is supposed to, the Site or Platform Administrator publishes all the new content to live.

<div class="key-point">
Key Point: <br />
Using Staging and Workflows together is necessary to create the full business review process.
</div>

<br />

<figure>
	<img src="../images/workflow-config.png" style="max-height:40%;" />
	<figcaption style="font-size: x-small">Fig.2 Adding a workflow definition to Pages in a site</figcaption>
</figure>

<br />

As mentioned above, Pages can be configured for a full review process, similar to Content workflows. This process can be accessed in the _Site Administration_ menu in the _Workflow_ section. Simply assign an existing workflow definition to Page Revision, or create your own with the Kaleo Designer.

#### Putting Everything Together {#put}

From beginning to end, Content Creators add content to the platform, add metadata, assign it to a Display Page, and submit it for review. An Administrator or Content Reviewer reviews the content. Once the content is approved, Content Creators place it on the page (or the content is published automatically if automation is set up). Once all the content is on the page, a page workflow can be set up so that every page will be reviewed, edited, tested, and finally published to live.

Page reviews are used for that last double-check to ensure that everything your users are about to see is exactly how it should be. Testing of the navigation, action buttons, language translations, and accessibility should be done here. At this point, it is also a good idea to test outside of Liferay with screen-readers. 

Once the full functionality has been checked out, make a final check to ensure that everything looks good and that any last-minute adjustments have not changed the look of your pages. Once you are sure that everything works and that it all looks perfect, it is time to publish the site to live.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Content should be created, ______________________, and placed on a page.</li>
  <li>Pages should have ______________________ placed on them and be reviewed in Staging before publishing to live.</li>
  <li>Set Workflow definitions for Pages under the Configuration section of the ______________________.</li>
</ul>
</div>
