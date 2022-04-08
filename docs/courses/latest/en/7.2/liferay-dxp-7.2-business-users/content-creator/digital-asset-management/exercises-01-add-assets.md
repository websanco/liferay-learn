<h2 class="exercise">Exercises</h2>

## Add Assets to the Platform

<div class="ahead" />
<h4>Exercise Goals</h4>
<ul>
    <li>Create new document types</li>
    <li>Add assets to the platform</li>
</ul>
</div>

#### Create a Basic Employee Record Document Type
1. **Open** the _Menu_.
* **Click** the _Site Selector_.
	* The Site Selector looks like a compass and appears on the main _Site Administration_ panel.
* **Click** the _My Sites_ tab.
* **Choose** the _Livingstone Loop_ site.
* **Go to** _`Content & Data → Documents and Media`_ in the _Site Administration_ panel.  
* **Click** on the _Document Types_ tab near the top.  
* **Click** the _Add_ button on the top right.  
* **Type** `Basic Employee Record` in the _Name_ field.  
* **Type** `Employee records for Livingstone HR.` under _Description_.
* **Click** the _Main Metadata Fields_ section to open it.

<img src="../images/basic-employee-record.png" style="max-height:30%" />

#### Add a Text Field for the Full Name
1. **Drag** a _Text_ field into the right column.
* **Click** on the field to open the editing options. 
* **Double-click** to edit the _Field Label_ value. 
* **Type** `Full Name` in the box next to _Field Label_.  
* **Click** _Save_.  
* **Double-click** the _Required_ field value.
* **Click** _Yes_ to make it a required field.
* **Click** _Save_.

<img src="../images/name-field.png" style="max-height:40%" />

#### Add an Integer Field for the Employee ID Number
1. **Click** the _Fields_ tab at the top left of the editor.  
* **Drag** an _Integer_ field into the right column under the new _Full Name_ field.  
* **Click** on the _Integer_ field to edit it.
* **Double-click** to edit the _Field Label_ value for the _Integer_ field.
* **Type** `Employee ID Number` in the box next to _Field Label_.  
* **Click** _Save_.  
* **Double-click** the _Required_ field value.
* **Click** _Yes_ to make it a required field.
* **Click** _Save_.

<img src="../images/employee-id-field.png" style="max-height:40%" />

#### Add a Text Field for the Home Address
1. **Click** on the _Fields_ tab.  
* **Drag** a _Text_ field into the right column under the _Employee ID Number_ field.
* **Click** on the _Text_ field to edit it.
* **Double-click** to edit the _Field Label_ value.  
* **Type** `Home Address` in the box next to _Field Label_.  
* **Click** _Save_.  
* **Double-click** the _Required_ value field.
* **Click** _Yes_ to make it a required field.
* **Click** _Save_.

<img src="../images/address-field.png" style="max-height:40%" />

#### Add a Date Field for the Date of Birth
1. **Click** on the _Fields_ tab.  
* **Drag** a _Date_ field into the right column under the _Home Address_ field.
* **Click** on the _Date_ field to edit it.
* **Double-click** to edit the _Field Label_ value.  
* **Type** `Date of Birth` in the box next to _Field Label_.  
* **Click** _Save_.  
* **Double-click** the _Required_ field value.
* **Click** _Yes_ to make it a required field. 
* **Click** _Save_.

<img src="../images/birth-field.png" style="max-height:40%" />

#### Add a Text Field for the Employee Job Title
1. **Click** on the _Fields_ tab.  
* **Drag** a _Text_ field into the right column under the _Date of Birth_ field.
* **Click** on the _Text_ field to edit it.
* **Double-click** to edit the _Field Label_ value.   
* **Type** `Job Title` in the box next to _Field Label_.  
* **Click** _Save_.  
* **Double-click** the _Required_ field value.
* **Click** _Yes_ to make it a required field.
* **Click** _Save_.  
* **Click** the _Save_ button at the bottom.

<img src="../images/job-title-field.png" style="max-height:30%" />

#### Create a New Employee Record Document for Omar Miles
1. **Click** on the _Documents and Media_ tab at the top of the page.
* **Click** on the _Add_ button. 
* **Choose** the _Basic Employee Record_ option.
* **Click** _Browse/Choose File_.
* **Choose** the `omar-miles.pdf` file from your module exercises folder.
* **Type** `Omar Miles Employee Record` for the _Title_.

<img src="../images/omar-record.png" style="max-height:25%" />

#### Fill Out the Document with Employee Information
1. **Type** `Omar Miles` as the _Full Name_.
* **Type** `174903` as the _Employee ID Number_.
* **Type** `123 Main Street, Anytown, USA` as the _Home Address_.
* **Type** `04/18/1983` as the _Date of Birth_.
* **Type** `Editor, Livingstone Life` as the _Job Title_.
* **Click** _Publish_.

<img src="../images/employee-record-complete.png" style="max-height:40%" />

#### Import a New Document Type into the Global Site
1. **Open** the _Menu_.
* **Click** the _Site Selector_ in the _Site Administration_ panel. 
* **Click** on the _My Sites_ tab.
* **Choose** the _Global_ site.
* **Go to** _`Content & Data → Documents and Media`_ in the _Site Administration_ panel.
* **Click** on the _Options_ icon near the top right.
* **Choose** _Export/Import_ from the drop-down.
* **Click** on the _Import_ tab.
* **Click** _Select File_.
* **Choose** the `image-type.lar` file from your module exercises folder.
* **Click** `Continue → Import`.
	- This may take a few moments.
* **Close** the pop-up once the lar has been imported successfully.

<img src="../images/document-types-imported.png" style="max-height:40%" />

#### Add an Image Type Document to the Global Site
1. **Click** _Add_.
* **Choose** _Image Type_ from the drop-down.
	* This is the new Document Type we just added to the Global site.
* **Click** _Browse/Choose File_.
* **Choose** the `livingstone-banner-image.jpg` file from your module exercises folder.
	- The _Title_ field will automatically be populated with the file name.

<img src="../images/image-type-banner.png" style="max-height:40%" />

#### Fill Out the Banner Image Description
1. **Type** `Livingstone Banner` in the _Name_ field to add a more descriptive name.
* **Choose** `Front Page` from the _Image Type_ drop-down menu.
* **Click** _Publish_.

<img src="../images/image-type-complete.png" style="max-height:40%" />

#### Create Web Content that Utilizes the Banner Image
1. **Go to** the _`Content & Data → Web Content`_ in the _Site Administration_ panel.
* **Click** the _Add_ button near the top right corner.
* **Choose** _Basic Web Content_ from the drop-down.
* **Click** where it says _Untitled Basic Web Content_ at the top of the page.
* **Type** `Livingstone Banner`.
* **Click** in the field below where it says _Content_.
* **Click** the _Add_ button next to the field.
* **Choose** _Insert Image_.
	- This is the first of the four icons that appears.
* **Click** on the _livingstone-banner-image.jpg_ that we just uploaded.
* **Click** _Add_.
* **Type** `Livingstone Hotels & Resorts` beneath the image.
* **Click** and drag to highlight the text we just typed.
* **Choose** _Heading 1_ from the drop-down on the left.
* **Click** the _Publish_ button at the top right corner of the page.

<img src="../images/livingstone-banner.png" style="max-height:25%" />

#### Add the Livingstone Banner to the Hotel Landing Page Template
1. **Go to** _`Site Builder → Pages`_ in the _Site Administration_ panel.
* **Click** on the _Hotel Landing Page_.
* **Click** the _Options_ icon on the top left of the _Web Content Display_ at the top of the page.
* **Choose** _Configuration_.
* **Click** _Select_.
* **Choose** the _Livingstone Banner_.
* **Click** the _Save_ button.
* **Close** the pop-up.

<div class="note" />
Note: Our Web Content will not show up in the Web Content Display widget since this is a Page Template.
</div>

<img src="../images/web-content-display-added.png" style="max-height:40%;" />

<br />

#### View the Web Content in a Page
1. **Open** the _Menu_.
* **Click** the _Site Selector_.
	* The Site Selector looks like a compass and appears on the main _Site Administration_ panel.
* **Click** the _My Sites_ tab.
* **Click** _Child Sites_ beneath _Livingstone Hotels & Resorts_.
* **Choose** the _Hotel Americana_ site.
	- The Web Content we created is already on display thanks to the _Inherit Changes_ feature discussed in the last course module.

<img src="../images/hotel-americana.png" style="max-height:40%;" />

<br /><br /><br />

---

#### Bonus Exercises
1. Go through all the Hotel landing sites and check that the Web Content we created is displayed at the top of the page.
2. Change the the top Banner Web Content to have a _Barebone_ Application Decorator configuration in the Hotel Landing Page Page Template.
2. Add an _Image Type_ document to the main Livingstone site using the `grand-hotel.jpg` file provided in your module exercise folder. Use _Other_ as the image type.
