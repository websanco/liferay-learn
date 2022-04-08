## Streamlining Content Publication with Structures and Templates

Your platform needs a consistent design and structure throughout. A consistent design empowers your users and creates a recognizable brand image for your business. Consistent structures make it easier for Content Creators to get their jobs done efficiently. When design is taken care of globally, it streamlines your entire platform.

#### Livingstone's Custom Content {#livingstone}

The Livingstone Hotels & Resorts web development team needs to create a consistent look across all the different Livingstone Hotels & Resorts sites. Here are the responsibilities of different team members:

* Maria Flores needs to create static content like banners and links.
* Natalia Michaels needs to create press releases and advertisements for different hotels and resorts on a regular basis.
* Martin Llewellyn has to come up with mockups for the landing page of the public-facing website as well as the presentation of Natalia and Maria's content.
* Josiah Copeland and the administrative team are responsible for setting up different content types.
* Kaito Tanaka needs to implement Martin's designs in the pages across various sites.

With all these different people working on content, it's important to have an easy way of keeping design consistent across the platform.

#### Structures and Templates {#sandt}

Web Content Structures and Templates can simplify the content publishing process. Administrators are able to create structures to guide and simplify the creation of content in the platform. Front-end developers can create various template types to meet the design team's requirements. The templates can then be implemented across the entire platform, creating a consistent design for your brand. Using Web Content Structures, as well as Layout, Page, and Site Templates, brings a consistent look to your Liferay platform.

<div class="key-point">
Key Point: <br />
The consistent use of Structures and Templates in the platform provides a brand identity across all your sites.
</div>

<figure>
	<img src="../images/consistent-design.png" style="max-height:30%;" />
	<figcaption style="font-size: x-small">Fig.1 Using a consistent design across sites strengthens your brand identity and improves the overall user experience</figcaption>
</figure>

#### Web Content Structures {#struct}

Since there are many use cases for Web Content, this allows administrators to be more specific about what kind of content is needed. For example, Site Administrators can create Press Release, Event, and News Structures, which allows Content Creators working under them to create unique content of those types.

<div class="key-point">
Key Point: <br />
<strong>Web Content Structures</strong> determine both the type of Web Content as well as which fields need to be filled out before the content can be published.
</div>

<figure>
	<img src="../images/web-content-structure-form.png" style="max-height:35%;" />
	<figcaption style="font-size: x-small">Fig.2 Adding a New Web Content Structure to the platform</figcaption>
</figure>

Once these Structures are created, they can be given certain fields that need to be filled out. For example, the _Press Release_ might need the following information:
* Press Release Headline
* Secondary Headline
* Release Date
* Article Text

Since Structures are based on Liferay's Forms functionality, Administrators and Content Creators can create the above examples with the following fields:
- Boolean: Adds a checkbox  
- Date: Adds a date-picker  
- Decimal: Adds a field that requires a number with a decimal point  
- Documents and Media: Choose a file from the _Documents and Media_ repository  
- Geolocation: Adds a map that displays a location  
- HTML: Adds the Alloy Editor  
- Image: Choose an image from either the _Documents and Media_ repository or the computer's storage  
- Integer: Adds a field to input non-fractional numbers
- Link to Page: Adds a field to insert a link  
- Number: Adds a place to insert any number  
- Radio: Adds radio button inputs for a user to choose from  
- Select: Adds a select box  
- Separator: Adds a line separator between fields  
- Test: Used for titles and headings  
- Text Box: Used for the body of content or long descriptions

<figure>
	<img src="../images/structures-items.png" style="max-height:30%;" />
	<figcaption style="font-size: x-small">Fig.3 The field types that can be added to a Web Content Structure</figcaption>
</figure>

Structures simplify the process of adding content, so once Administrators create them, Content Creators have a clear idea of what kinds of Web Content they need to create and what information needs to be included in each Web Content article that will eventually be published to their sites.

#### Web Content Templates {#temp}

With Structures in place, there is a way for the front-end team to take some of the design responsibility off the Content team. Instead of putting the design and formatting responsibility on Content Creators every time they create new Web Content articles, the Front-End team can create _Web Content Templates_ that go along with the Structures the Content Creators are using to create their Web Content articles.

<div class="key-point">
Key Point: <br />
<strong>Web Content Templates</strong> take the data from Structures and provide a consistent presentation using FreeMarker, CSS, and JavaScript.
</div>

<figure>
	<img src="../images/4-image-finished.png" style="max-height:30%;" />
	<figcaption style="font-size: x-small">Fig.4 Web Content utilizing a Web Content Template for its display in the site</figcaption>
</figure>

<br />

Web Content Templates are special FreeMarker Templates that allow front-end developers to use FreeMarker (as well as standard HTML, CSS, and JavaScript) to provide a design for any given Structure. These templates grab the data from the Structures and plug them into a specific FreeMarker template. Once created, Content Creators can choose to add a specific type of Web Content (defined by a Structure), fill in the fields, and then publish it. The Web Content Template associated with the Web Content Structure will control the presentation and provide consistent look-and-feel. The only thing the writers need to focus on is good copy and, in some cases, minor formatting.

#### Assigning Permissions on Structures and Templates {#permissions}

Structures and Templates provide direct access to Liferay’s APIs, which makes them powerful, but it also means that they can be dangerous in the wrong hands. Only trusted users should be given access.

<div class="key-point">
Key Point: <br />
We recommend you create at least two Roles that deal with access to Structures and Templates:
<ul>
	<li>Content Developer</li>
	<li>Content Creator</li>
</ul>
</div>

<figure>
	<img src="../images/structures-templates-permissions.png" style="max-height:26%;" />
	<figcaption style="font-size: x-small">Fig.5 Add Web Content Structure and Template Permissions in Roles defined from the Control Panel</figcaption>
</figure>

<br />

Content Developers get full access to create and edit Structures and Templates. Front-End and Design team members should be assigned this Role (Administrators will already have access to Structures and Templates in the default Platform Administrator and Site Administrator roles). On the other hand, Content Creators only need permission to view Structures and Templates so they can use them to create Web Content articles.

Access to specific Templates and Structures can be given individually via the configuration page for a Template or Structure. Here you can set permissions for Roles or Teams. Use this option to provide access to templates on a case-by-case basis for users who might need access to edit a particular Template or Structure, but they should not have access to all Templates or Structures.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Web Content Structures identify the ______________________ of Web Content as well as the fields needed.</li>
  <li>Web Content Templates take the Structure data and wrap it in a ______________________ template to control presentation.</li>
  <li>We recommend that at least two Roles are created to deal with access to Structures and Templates in the platform:</li>
  <ul>
  	<li>______________________</li>
  	<li>______________________</li>
  </ul>
</ul>
</div>
