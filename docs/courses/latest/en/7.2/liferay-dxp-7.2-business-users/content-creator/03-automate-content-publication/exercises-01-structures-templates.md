<h2 class="exercise">Exercises</h2>

## Creating Structures and Templates for Content Display

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Apply the Livingstone Fjord theme to the main site</li>
    <li>Create a Web Content Structure</li>
    <li>Create a Web Content Template utilizing the structure</li>
    <li>Schedule the Web Content to be published</li>
</ul>
</div>

#### Prepare the Livingstone Site for Displaying Content
1. **Click** the _Options_ icon next to the _Hello World_ widget on the _Home_ page for the site.
	- You should be in the Livingstone Hotels & Resorts site. If you aren't, use the Site Selector to navigate to the _Home_ page of that site.
* **Choose** _Remove_.
* **Click** _OK_ if prompted for confirmation by your browser.
* **Go to** _`Site Builder → Pages`_ in the _Site Administration_ panel.  
* **Click** on the _Configuration_ icon next to _Public Pages_.
	- This is the icon that looks like a gear.
* **Click** to expand the _Logo_ section at the bottom of the page.
* **Click** the _Change_ button.
* **Click** _Select_.
* **Choose** the `livingstone-logo.png` file from your Course Module exercises folder.
* **Click** _Done_.
* **Click** _Save_.

<img src="../images/added-theme.png" style="max-height:35%;" />

#### Create a Web Content Structure for News Articles
1. **Go to** _`Content & Data → Web Content`_ in the _Site Administration_ panel.
* **Click** the _Structures_ tab at the top.
* **Click** _Add_ at the top right.
* **Type** `News` for the _Name_ at the top of the page.  
* **Drop** a _Text_ field from the left into the right column.   
* **Drop** another _Text_ field under the first field.
* **Drop** a _Date_ field beneath the second _Text_ field.  
* **Drop** an _Image_ field beneath the _Date_ field.  
* **Drop** a _Text_ field beneath the _Image_ field.  
* **Drop** an _HTML_ field under the last _Text_ field.

<img src="../images/web-content-fields.png" style="max-height:30%;" />

#### Name the News Title Fields
1. **Click** the first _Text_ field at the top of the page.  
* **Double-click** the _Field Label_ value to edit the value name.  
* **Type** `Article Title`.  
* **Click** _Save_.  
* **Click** the second _Text_ field beneath the first.  
* **Double-click** the _Field Label_ value to edit the value name.  
* **Type** `Byline`.  
* **Click** _Save_.

<img src="../images/news-titles.png" style="max-height:20%;" />

#### Name the News Date and Image Fields
1.  **Click** the _Date_ field.
* **Double-click** the _Field Label_ value to edit the value name.  
* **Type** `Posted On`.
* **Click** _Save_.   
* **Click** the _Image_ field.  
* **Double-click** the _Field Label_ value to edit the value name.
* **Type** `Story Image` next to _Field Label_.  
* **Click** _Save_.

<img src="../images/date-and-image-titles.png" style="max-height:25%;" />

#### Name the News Content Fields
1. **Click** the _Text_ field beneath the Story Image.  
* **Double-click** the _Field Label_ value to edit the value name.  
* **Type** `Lead`.  
* **Click** _Save_.  
* **Click** the _HTML_ field. 
* **Double-click** the _Field Label_ value to edit the value name. 
* **Type** `Content` next to _Field Label_.  
* **Click** _Save_.
* **Type** `To be used by Content Creators adding news articles to the Livingstone Site` in the _Description_ field on the right.
* **Click** the _Save_ button in the top right corner of the screen.

<img src="../images/content-titles.png" style="max-height:35%;" />

#### Add a Template for the News Structure
1. **Click** the _Templates_ tab at the top. 
* **Click** _Add_ at the top right.
* **Type** `News` for the _Name_ at the top of the page.
* **Click** _Select_ under _Structure_ in the right column.
* **Choose** _News_.
* **Type** `Adds a consistent design to all News content on the Site.` in the _Description_.
* **Click** _Browse/Choose File_ next to the _Script File_ field.
* **Choose** the `news-template.ftl` file from your Course Module exercise folder.
* **Click** the _Save_ button in the top right corner.

<img src="../images/news-template-added.png" style="max-height:32%;" />

#### Import More Structures and Templates
1. **Click** on the _Web Content_ tab. 
* **Click** the _Options_ icon near the top right.
* **Choose** _Export/Import_ from the drop-down.
* **Click** on the _Import_ tab.
* **Click** _Select File_.
* **Choose** the `structures-templates.lar` file from your Course Module exercise folder.
* **Click** _Continue_.
* **Click** the _Import_ button.
* **Close** the pop-up.

<img src="../images/imported-content.png" style="max-height:15%;" />

#### Add a Template to the Imported 4 Image Grid Structure
1. **Click** on the _Templates_ tab.
* **Click** on the _4 Image Grid Template_.
* **Click** _Browse/Choose File_ under the template editor.
* **Choose** the `4-image-template.ftl` file from your Course Module exercise folder.
* **Click** _Save_.

<img src="../images/4-column-template.png" style="max-height:30%;" />

#### Create the Featured Hotel Content
1. **Click** on the _Web Content_ tab.
* **Click** _Add_.
* **Choose** the _4 Image Grid_ Structure.
* **Type** `Featured Hotels` for the _Name_ at the top.
* **Type** `Livingstone Group Hotels` for the _Title_ field.
* **Click** the _Select_ button under the _image 1_ field.
* **Click** _Select File_.
* **Choose** the `playaazul.jpg` file from your Course Module exercise folder.
* **Click** _Add_.
* **Type** `Playa Azul Hotel` for the _text 1_ field.

<img src="../images/1-image-4-grid.png" style="max-height:35%;" />

#### Add More Text and Images for the Featured Hotels Content
1. **Click** the _Select_ button under the _image 2_ field.
* **Click** _Select File_.
* **Choose** the `grandlivingstone.jpg` file from your Course Module exercise folder.
* **Click** _Add_.
* **Type** `Grand Livingstone Hotel` for the _text 2_ field.
* **Click** the _Select_ button under the _image 2_ field.
* **Click** _Select File_.
* **Choose** the `ironrose.jpg` file from your Course Module exercise folder.
* **Click** _Add_.
* **Type** `The Iron Rose` for the _text 3_ field.

<img src="../images/3-image-4-grid.png" style="max-height:28%;" />

#### Add the Last Text and Image for the New Web Content
1. **Click** the _Select_ button under the _image 4_ field.
* **Click** _Select File_.
* **Choose** the `purple.jpg` file from your Course Module exercise folder.
* **Click** _Add_.
* **Type** `Purple Palace Hotel` for the _text 4_ field.

<img src="../images/featured-hotels-finished.png" style="max-height:20%;" />

#### Assign a Default Template Before Publishing the Web Content
1. **Open** the _Default Template_ section of the configuration pane on the right.
* **Click** _Select_.
* **Choose** _4 Image Grid_.
	- Your browser may open a pop-up. Click _OK_.
* **Click** _Publish_ in the top right corner of the page.

<img src="../images/template-added.png" style="max-height:50%;" />

#### Place the Content on the Welcome Page
1. **Go to** _Go to Site_ in the _Site Administration_ panel.    
* **Open** the _Add_ panel.  
* **Click** to expand the _Content_ section.
* **Drag** the _Featured Hotels_ content onto the page under the banner.
* **Click** the _Options_ icon for the top Web Content Display.
* **Choose** _Look and Feel Configuration_.
* **Choose** _Barebone_ under _Application Decorators_.
* **Click** _Save_.
* **Close** the pop-up.

<img src="../images/4-image-finished.png" style="max-height:40%;" />

---

#### Bonus Exercises
1. Create a Structure for a Call to Action. Include fields for the Call to Action Message and an Image.
2. Create a simple Template for your Call to Action. Use the `<h1>` tag for the message and add the image below.
3. Use the Call to Action structure to add new Web Content to the main Livingstone site. Be sure to see how the content looks using the Web Content Display widget on the _Home_ page.
