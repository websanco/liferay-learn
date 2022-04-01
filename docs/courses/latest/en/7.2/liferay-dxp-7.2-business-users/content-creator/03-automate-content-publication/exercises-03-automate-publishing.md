<h2 class="exercise">Exercises</h2>

## Automating Publishing with the Asset Publisher

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Create an automated self-publishing page using the Asset Publisher</li>
    <li>Create a Display Page using imported Page Fragments</li>
    <li>Map Web Content fields to the Display Page</li>
    <li>Add Web Content to be displayed via the Display Page</li>
</ul>
</div>

#### Create a What's New Page for Livingstone Life
1. **Open** the _Menu_.
* **Click** the _Site Selector_ in the _Site Administration_ panel.
* **Click** the _My Sites_ tab.
* **Choose** the _Livingstone Life_ site.
* **Go to** _`Site Builder → Pages`_ in the _Site Administration_ panel.
* **Click** the _Add_ button.
* **Click** _Global Templates_ in the sidebar.
* **Choose** _Asset Publisher Page_.
* **Type** `What's New` for the _Name_.
* **Click** the _Add_ button.

<img src="../images/livingstone-life-new.png" style="max-height:27%;" />

#### Configure the Asset Publisher
1. **Click** _Go to Site_ in the _Site Administration_ panel.
* **Click** the _What's New_ page in the _Navigation Menu_.
* **Click** the _Configure Page_ icon in the top right corner.
	- This is the icon that looks like a gear.
* **Click** to slide the _Inherit Changes_ slider from _YES_ to _NO_.
* **Click** _Save_.
* **Click** the _Options_ icon above the _Asset Publisher_.  
* **Click** on _Configuration_.  
* **Expand** the _Source_ section.
* **Choose** _Blogs Entry_ in the drop-down field.
* **Expand** the _Ordering_ section.
* **Choose** _Publish Date_ in the _Order by_ drop-down field.
* **Click** the _Save_ button.  
* **Close** the pop-up.
* **Click** the title _Asset Publisher_.
    - This should display a text box where you can change the title of the Asset Publisher.
* **Type** `Recently Published`.
* **Click** the checkmark to save your change.

<img src="../images/asset-publisher-configured.png" style="max-height:24%;" />

#### Import a Page Fragment for the Display Page
1. **Open** the _Menu_.
* **Click** the _Site Selector_ in the _Site Administration_ panel.
* **Click** the _My Sites_ tab.
* **Choose** the _Livingstone Hotels & Resorts_ site.
* **Go to** _`Site Builder → Page Fragments`_ in the _Site Administration_ panel.
* **Click** the _Options_ icon next to Collections on the left.
* **Choose** _Import_.
* **Click** _Browse/Choose File_.
* **Choose** the `display-page-fragment.zip` file from your Course Module exercises folder.
* **Click** _Import_.
* **Close** the pop-up.

<img src="../images/imported-display-page.png" style="max-height:20%;" />

#### Create a Display Page
1. **Go to** _`Site Builder → Pages`_ in the _Site Administration_ panel.
* **Click** on the _Display Page Templates_ tab.
* **Click** the _Add_ button.
* **Type** `News Content` for the _Name_.
* **Choose** _Web Content Article_ for the _Content Type_ drop-down.
* **Choose** _News_ for the _Subtype_ drop-down.
    * If you see two News options, click the top one.
* **Click** _Save_.
* **Open** the _Display Page Fragments_.
* **Click** to add the _News Fragment_ Fragment from the _Display Page Fragments_ collection.

<img src="../images/news-fragment.png" style="max-height:20%;" />

#### Map Web Content Fields to the Fragment
1. **Click** on _Map Article Title Here_.
* **Click** _Map_.
* **Choose** the _Article Title_ mapping field.
* **Click** on _Map Byline Here_.
* **Click** _Map_.
* **Choose** the _Byline_ mapping field.
* **Click** on _Map Posted On Date Here_.
* **Click** _Map_.
* **Choose** the _Posted On_ mapping field.

<img src="../images/mapped-title.png" style="max-height:33%;" />

#### Map Remaining Fields to the Fragment
1. **Click** on _Map Image Here_.
* **Click** _Map_.
* **Choose** the _Story Image_ mapping field.
* **Click** on _Map Lead Here_.
* **Click** _Map_.
* **Choose** the _Lead_ mapping field.
* **Click** on _Map Content Here_.
* **Click** _Map_.
* **Choose** the _Content_ mapping field.
* **Click** _Publish_.
  * Before we add our Web Content, let's make sure our new Display Page is the default display for any Web Content created with the News Structure.
* **Click** _`Options → Mark as Default`_ for the _News Content_ Display Page.

<img src="../images/news-display.png" style="max-height:25%;" />

#### Add New Web Content
Finally, let's create some new Web Content to test out our Display Page.
1. **Open** the _Menu_. 
* **Go to** _`Content & Data → Web Content`_ in the _Site Administration_ panel.
* **Click** _`Add → News`_.
    * Choose the top option if you see two.
* **Type** `New GM driven by desire to bring hotel back to its roots` in both the _Title_ and _Article Title_ fields.
* **Type** `Natalia Michaels` in the _Byline_ field. 
* **Choose** today's date in the _Posted on_ field.
* **Click** _Select_ under the _Story Image_ field.
* **Click** _Select File_.
* **Choose** the `lounge.jpg` file from your Course Module exercises folder.
* **Click** _Add_.
* **Type** `Dan Torrance is bringing fresh ideas to hotel management` in the _Lead_ field.
* **Open** the `news-text.txt` file from your Course Module exercises folder. 
* **Copy** the contents of the file.
* **Paste** the contents into the _Content_ field.
* **Click** _Publish_.

<img src="../images/new-web-content.png" style="max-height:30%;" />

#### View the Display Page
1. **Click** _Go to Site_ in the _Site Administration_ panel.
* **Click** on the _Hotels & Resorts_ page.
* **Click** the _Add_ icon to open the _Add_ panel.
* **Expand** the _`Widgets → Highlighted`_ section.
* **Click** to add an _Asset Publisher_ to the bottom of the page.
* **Click** on the _New GM driven..._ link displayed in the _Asset Publisher_.

<img src="../images/asset-displayed.png" style="max-height:40%;" />

---

#### Bonus Exercises
1. Create a second Display Page using any existing Page Fragments. Use the 4 Image Structure we imported in the first exercise.
2. Create new Web Content using the 4 Image Structure and view the mapped Web Content in your custom Display Page.


