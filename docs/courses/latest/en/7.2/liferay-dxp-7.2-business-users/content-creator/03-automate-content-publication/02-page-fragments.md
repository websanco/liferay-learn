## Simplifying Content Updates and Page Creation with Page Fragments

Some aspects of your sites will need more attention than others. A site banner, for instance, is an important part of every public-facing page. The site banner is the primary place for the web team to create immediate brand recognition and helps set the identity of the entire platform. 

Marketing teams often work on portions of a site together and in greater detail. A platform that requires coding expertise to modify different aspects of the page could be a problem for marketing teams.

#### Livingstone's Dynamic Digital Marketing Needs {#livingstone}

The Livingstone Hotels & Resorts web team needs to create a new dynamic banner that fits with their corporate identity, but ultimately the marketing team will be responsible for using and updating these banners on each of the sites. Martin needs a place to work with Kaito, the Web Developer, to incorporate the banners across the platform. They need a way to work together to quickly design and implement the visual presentation of the sites at a level below pages, while giving the marketing team the tools they need to maintain it from there.

#### Liferay's Page Fragments {#frags}

Liferay DXP uses _Page Fragments_ to create dynamic content-centric pages. A Page Fragment can be any aspect of the page that needs to be reusable and editable. 

<div class="key-point">
Key Point: <br />
<strong>Page Fragments</strong> are reusable parts of a Site Page that can be combined to create a Display Page Template or Content Page.
</div>

<figure>
  <img src="../images/blog-fragment.png" style="max-height:20%;">
  <figcaption style="font-size: x-small">Fig.1 Blog-type content created using Page Fragments</figcaption>
</figure>

Examples of what you can build with Page Fragments include:
- Calls to action
- Headers
- Quotes
- Titles

These examples only scratch the surface of what it is possible to create using Page Fragments. Page Fragments allow for greater versatility and customization, also creating a space for easy collaboration.

#### Creating Page Fragments {#create}

Creation of Page Fragments will be the responsibility of the front-end developers working to implement the designs provided. Once the Fragments are created, the Marketing team can take advantage of editing and reusing these fragments on any Livingstone Hotels & Resorts site.

<div class="key-point">
Key Point: <br />
The <strong>Fragment Editor</strong> can be used to create Page Fragments from the Liferay DXP UI.
</div>

<br />

<figure>
  <img src="../images/using-fragment-editor.png" style="max-height:50%;">
  <figcaption style="font-size: x-small">Fig.2 Editing a Page Fragment using the Fragment Editor</figcaption>
</figure>

<br />

The Fragment Editor allows you to edit Fragments from within the browser. It was designed with web developers in mind and allows them to create Page Fragments from within the Liferay UI using:

- HTML: The markup of the fragment. Fragments use standard HTML (not FreeMarker, unlike Templates) and include special tags that add dynamic behavior to elements within the fragment.
- CSS: Styles and positions the HTML for the Fragment
- JavaScript: Provides dynamic behavior to the Fragment

<div class="note">
Note: It is not necessary to include CSS and JavaScript to create a working Fragment. CSS provides style to the HTML you've used to create the Fragment, and JavaScript provides additional dynamic behavior (beyond editable elements) to the Fragment.
</div>

#### Creating Editable Elements {#edit}

It is also possible to specify which parts of the Fragment can be modified and which cannot. Elements within a particular Page Fragment can be either mandatory or removable as well as customizable. This allows Marketing and Content Teams to reuse Page Fragments over and over again instead of needing new Fragments every time they have to create unique Content.

<div class="key-point">
Key Point: <br />
<strong>Editable Elements</strong> can be created by adding <code><lfr-editable></code> tags around:
<ul>
  <li>Text</li>
  <li>Images</li>
  <li>Links</li>
</ul>
</div>
 
```html
<h1><lfr-editable id="main-title" type="text">Take the trip of a lifetime</lfr-editable></h1>

<lfr-editable id="main-image" type="image"><img src=”http://abc.com/paris.img”/></lfr-editable>

<p>
  <lfr-editable id="body-text" type="text">
    Make your dreams of adventure a reality by escaping to one of Livingstone Hotels & Resorts 17 world class hotels across the globe.
  </lfr-editable>
</p>
```

The `<lfr-editable>` tags require a unique id (one of four types) and content enclosed between them in order to create an editable element. 

In the example above, we're using ids for each element in the example (`main-title`, `main-image`, and `body-text`) and have elements of both the `text` and `image` type.  

There are four `type` values that can be used in the `<lfr-editable>` tag:

1. `text`
2. `image`
3. `rich-text`
4. `link`

These editable tags are what will allow Content Creators to modify and maintain the Fragments on various sites on the platform and even on multiple pages within the same site. In the above example, a Content Creator would be able to keep the core design while simply editing the title, image, or content when the Fragment is displayed on a page.

Other than editable elements, Fragments can be made to be more dynamic by embedding widgets within the Fragment. Portlets can be embedded as widgets within Fragment code using the `<lfr-widget-[name]>` tag. For example, to add the Navigation widget to a Fragment, you could use the following code: 

```html
<div>
	<lfr-widget-nav>
</div>
```

#### Reusing Page Fragments {#reuse}

Page Fragment administration can be found alongside the other assets in the _Site Administration_ panel under `Site Builder → Page Fragments`. Administrators can create what are called _Collections_ from this section of _Site Administration_, which helps to organize and distinguish the purpose of the grouped Fragments. Collections can be created to distinguish Fragments from one another and create groups like the following:

- Page Headers
- Banners
- Content list
- Videos
- Slideshows
- Forms
- Maps
- Calls to action/Best next steps
- Visual elements (dividers, spacers, lines)

<div class="key-point">
Key Point: <br />
<strong>Collections</strong> are groups of Page Fragments.
</div>

Within each Collection, several fragments can be created. Content Creators or Developers can utilize Fragments by creating _Content_ pages. These pages are built entirely of Fragments. Content Creators can create, edit, and maintain different landing pages using the existing Fragments within any Collections added to the site.

<br />

<figure>
	<img src="../images/new-collection.png" style="max-height:30%;">
	<figcaption style="font-size: x-small">Fig.3 Creating a new Collection of Page Fragments</figcaption>
</figure>

<br />

Using Page Fragments, Web Designers, Front-End Developers, and Content Creators can work together to rapidly build beautiful presentations on their pages. Page Fragments are one of Liferay's most powerful and useful features. They are the future of Web and Content design in Liferay DXP, combining the versatility of custom front-end development with the ease of an interactive UI.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>______________________ are reusable parts of a site page.</li>
  <li>The ______________________ can be used to create Page Fragments from the Liferay DXP UI.</li>
  <li>Editable Elements can be created using the ______________________ tags.</li>
  <li>Page Fragments can be managed by creating ______________________ in the new Site Administration section.</li>
</ul>
</div>
