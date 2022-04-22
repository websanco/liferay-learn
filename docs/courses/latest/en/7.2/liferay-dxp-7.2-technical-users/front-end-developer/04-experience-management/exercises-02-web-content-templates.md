<h2 class="exercise">Exercises</h2>

## Create a Featured Hotels Structure and Template

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
        <li>Create a Structure for Featured Hotels</li>
        <ul>
            <li>Include four fields for text, images, and links</li>
        </ul>
        <li>Create a Template for the Features Hotels</li>
       <ul>
            <li>Format the different hotel fields into two rows with two columns</li>
        </ul>
	</ul>
</div>

#### Create a Global Snippets File in Visual Studio Code
1. **Open** _Visual Studio Code_.
2. **Go to** the _User Snippets_ menu.
	* Windows: `File → Preferences → User Snippets`
	* OSX: `Code → Preferences → User Snippets`
3. **Click** on _New Global Snippets file..._ in the drop-down menu.
4. **Type** _lfr-experience-management_ for the _name_.
	* The full file name will be `lfr-experience-management.code-snippets`.
5. **Click** _Save_.

#### Add the Snippet Content
1. **Open** the `experience-management-snippets.json` in your module exercises folder.
2. **Copy** the contents of the file.   
3. **Paste** the contents of the file in the `lfr-experience-management.code-snippets` file just created in Visual Studio Code.
	* Make sure to replace all the existing content in the file.
4. **Save** the file.

#### Create a Structure with Two Text Fields, an Image, and Link Field
1. **Go to** _`Site Administration → Content & Data → Web Content`_ in the _Menu_.  
* **Click** the _Structures_ tab at the top.  
* **Click** _Add_ at the top right.
* **Type** `Featured Hotels Content` for the _Name_.  
* **Drop** a _Text_ field from the left into the right column.
* **Drop** another _Text_ field under the first field.
* **Drop** an _Documents & Media_ field into the second Text field.  
* **Drop** a _link to page_ field beneath the Documents & Media field, and still nested within that second Text field.

<img src="../images/exercise-images/featured-hotels-structure.png" style="max-height:30%;">

#### Configure the Field Labels
1. **Click** the first _Text_ field at the top of the page.  
* **Double-click** the _Field Label_ value to edit the value name.  
* **Type** `Title`.  
* **Click** _Save_.  
* **Click** the second _Text_ field beneath the first.  
* **Double-click** the _Field Label_ value to edit the value name.  
* **Type** `Text1`.  
* **Click** _Save_.
* **Click** the _Documents & Media_ field.
* **Double-Click** the _Field Label_ value to edit the value name.
* **Type** `Image1`.
* **Click** _Save_.
* **Click** the _Link_ field.
* **Double-Click** the _Field Label_ value to edit the value name.
* **Type** `Link1`.
* **Click** _Save_.

<img src="../images/exercise-images/fields-filled-part-1.png" style="max-height:25%;">

#### Repeat the Field Set In Order to Feature Four Hotels
1. **Click** the _Add_ button at the top right of the Field set we just created for a second set.
2. **Click** on the _Add_ button again for a third set
3. **Click** on the _Add_ button again for a fourth set
    * Make sure you have a total of four field sets with Text, Documents & Media, and Link to Page fields.

<img src="../images/exercise-images/structure-part-2.png" style="max-height: 25%;">

#### Label the Second Set of Fields
1. **Click** the third _Text_ field.  
* **Double-click** the _Field Label_ value to edit the value name.  
* **Type** `Text2`.  
* **Click** _Save_.
* **Click** the _Documents & Media_ field.
* **Double-Click** the _Field Label_ value to edit the value name.
* **Type** `Image2`.
* **Click** _Save_.
* **Click** the _Link_ field.
* **Double-Click** the _Field Label_ value to edit the value name.
* **Type** `Link2`.
* **Click** _Save_.

#### Label the Third Set of Fields
1. **Click** the fourth _Text_ field.  
* **Double-click** the _Field Label_ value to edit the value name.  
* **Type** `Text3`.  
* **Click** _Save_.
* **Click** the _Documents & Media_ field.
* **Double-Click** the _Field Label_ value to edit the value name.
* **Type** `Image3`.
* **Click** _Save_.
* **Click** the _Link_ field.
* **Double-Click** the _Field Label_ value to edit the value name.
* **Type** `Link3`.
* **Click** _Save_.

#### Label the Fourth Set of Fields
1. **Click** the fifth _Text_ field.  
* **Double-click** the _Field Label_ value to edit the value name.  
* **Type** `Text4`.  
* **Click** _Save_.
* **Click** the _Documents & Media_ field.
* **Double-Click** the _Field Label_ value to edit the value name.
* **Type** `Image4`.
* **Click** _Save_.
* **Click** the _Link_ field.
* **Double-Click** the _Field Label_ value to edit the value name.
* **Type** `Link4`.
* **Click** _Save_.
* **Click** the _Save_ button at the top right corner of the page.

<img src="../images/exercise-images/structure-complete.png" style="max-height:15%;">

#### Import Example Featured Hotels Content
1. **Go to** the _Web Content_ tab.
* **Click** the _Options_ icon in the top right.
* **Choose** _Export/Import_.
* **Click** the _Import_ tab.
* **Click** _Select File_.
* **Choose** the `featured-livingstone.lar` found in the _exercise-src_ folder.
* **Click** _Continue_.
* **Click** _Import_.
	* If the import process duplicates the template, use the template with the older Modified Date.
* **Close** the pop-up.

#### Add Content to the Livingstone Landing Page
1. **Click** _Go to Site_ in Site Administration.    
* **Click** the _Add_ button in the top right.
* **Open** _Content_ in the Add Menu.
* **Drag** the _2019 Featured Hotels Content_ to the top of the page.  

<img src="../images/exercise-images/no-template.png" style="max-height:20%;">

#### Begin Creating the Featured Hotels Tempalate
1. **Go to** your _exercise-src_ folder.
* **Open** the `featured-hotels-template.ftl` file with _Visual Studio Code_.
* **Click** to highlight the `<#-- Insert 01-div-header-and-body snippet here -->` comment.
* **Type** `lfr` and choose the `01-div-header-and-body` snippet.
    * Alternatively, you can type the following to replace the comment:

```html
<div class="card-type-asset">
    <div class="card-item-first aspect-ratio">
    </div>
    <div class="card-body">
    </div>
</div>
```

#### Add Code for the Image Header
1. **Press** _Enter_ and _Tab_ after the `<div class="card-header aspect-ratio">` line
* **Type** `lfr` and choose the `02-hotel-image` snippet.
    * Alternatively, you can type the following to replace the comment:

```html
<#if Text1.Image1.getData()?? && Text1.Image1.getData() != "">
    <a href="${Text1.Image1.getData()}">
        <img class="aspect-ratio-item-fluid" ${languageUtil.format(locale, "download-x", "Image1", false)} src="${Text1.Image1.getData()}">
    </a>
</#if>
```

#### Add Code for the Title and Link
1. **Press** _Enter_ and _Tab_ after the `<div class="card-body">` line.
* **Type** `lfr` and choose the `03-hotel-title-link` snippet.
    * Alternatively, you can Type the following to replace the comment:

```html
<h2 class="text-center">
    <a class="item-one" href="${Text1.Link1.getFriendlyUrl()}">${Text1.getData()}</a>
</h2>
```

#### Add the Second Rows and Columns
1. **Click** to highlight the `<#-- Insert 04-top-row-second-column snippet here -->` comment.
* **Type** `lfr` and choose the `04-top-row-second-column` snippet.
    * Alternatively, you can Type the following to replace the comment:

```html
<li class="card col-md-6">
    <div class="card-type-asset">
        <div class="card-item-first aspect-ratio">
            <#if Text2.Image2.getData()?? && Text2.Image2.getData() != "">
                <a href="${Text2.Image2.getData()}">
                    <img class="aspect-ratio-item-fluid" ${languageUtil.format(locale, "download-x", "Image2", false)} src="${Text2.Image2.getData()}">
                </a>
            </#if>
        </div>
        <div class="card-body">
            <h2 class="text-center">
                <a class="item-one" href="${Text2.Link2.getFriendlyUrl()}">${Text2.getData()}</a>
            </h2>
        </div>
    </div>
</li>
```

#### Add the Final Rows and Columns
1. **Click** to highlight the `<#-- Insert 05-second-row snippet here -->` comment.
* **Type** `lfr` and choose the `05-second-row` snippet.
    * Alternatively, you can Type the following to replace the comment:

```html
<ul class="list-unstyled row">
    <li class="card col-md-6">
        <div class="card-type-asset">
            <div class="card-item-first aspect-ratio">
                <#if Text3.Image3.getData()?? && Text3.Image3.getData() != "">
                    <a href="${Text3.Image3.getData()}">
                        <img class="aspect-ratio-item-fluid" ${languageUtil.format(locale, "download-x", "Image3", false)} src="${Text3.Image3.getData()}">
                    </a>
                </#if>
            </div>
            <div class="card-body">
                <h2 class="text-center">
                    <a class="item-one" href="${Text3.Link3.getFriendlyUrl()}">${Text3.getData()}</a>
                </h2>
            </div>
        </div>
    </li>
    <li class="card col-md-6">
        <div class="card-type-asset">
            <div class="card-item-first aspect-ratio">
                <#if Text4.Image4.getData()?? && Text4.Image4.getData() != "">
                    <a href="${Text4.Image4.getData()}">
                        <img class="aspect-ratio-item-fluid" ${languageUtil.format(locale, "download-x", "Image4", false)} src="${Text4.Image4.getData()}">
                    </a>
                </#if>
            </div>
            <div class="card-body">
                <h2 class="text-center">
                    <a class="item-one" href="${Text4.Link4.getFriendlyUrl()}">${Text4.getData()}</a>
                </h2>
            </div>
        </div>
    </li>
</ul>
```
3. **Save** the file.

#### Add the Template to the Platform and Include the Title Data
1. **Go to** the _Livingstone Hotels & Resorts_ site in the browser.
  * Make sure you're logged in.  
* **Go to** _`Site Administration → Content & Data → Web Content`_ in the _Menu_.  
* **Click** the _Templates_ tab in the top menu.
* **Click** the _Add_ at the top right.
* **Type** `Featured Hotels Template` as the Title at the top of the page.
* **Click** the _Select_ button under the _Structure_ field.
* **Choose** the `Featured Hotels Content` Structure.
* **Copy** the code from our new _featured-hotels-template.ftl_ template.
* **Paste** it into the Template editor, replacing the default text.
* **Click** to highlight `<#-- Insert title data here -->` at the very top of the code.
* **Click** the _Title_ field in the _Fields_ section to the left.
* **Click** _Save_.

#### Select the Template and View the Content
1. **Go to** the _Web Content_ tab.
* **Click** the _2019 Featured Hotels Content_.
* **Click** _Select_ under the _Default Template_ field in the right column.
* **Choose** the `Featured Hotels Template`.
    * Click _OK_ if the browser pop-up requests access.
* **Click** _Publish_ at the top right.
* **Click** _Go to Site_ in Site Administration.  

<img src="../images/exercise-images/grid-template.png" style="max-height: 100%">

<br />

---

#### Bonus Exercises
1. Create a Structure for a Press Release with the following:
    * Headline
    * Date of Publication
    * Logo
    * Content
2. Create a Template for the Press Release using the built in editor.
