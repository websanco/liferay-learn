# Create Web Content Templates

Coming Soon!

<!--

Note to the editor: I had to change some snippet instructions to remove the !--, #--, or -- in order to comment out the entire file. Make sure to review and ensure the snippets are correct.

#### Exercise Goals

* Create a Structure for Featured Hotels that includes four fields for test, images, and links
* Create a Template for the Featured Hotels that formats different hotel fields into two rows with two columns

</div>

#### Create a Global Snippets File in Visual Studio Code
1. **Open** _Visual Studio Code_.
2. **Go to** the _User Snippets_ menu.
	* Windows: `File → Preferences → User Snippets`
	* OSX: `Gear icon → User Snippets`
3. **Click** on _New Global Snippets file..._ in the drop-down menu.
4. **Type** _lfr-experience-management_ for the _name_.
	* The full file name will be `lfr-experience-management.code-snippets`.
5. **Press** _Enter_ to open the file.

#### Add the Snippet Content
1. **Open** the `experience-management-snippets.json` in your module exercises folder.
2. **Copy** the contents of the file.   
3. **Paste** the contents of the file in the `lfr-experience-management.code-snippets` file just created in Visual Studio Code.
	* Make sure to replace all the existing content in the file.
4. **Save** the file.

<div class="page"></div>

#### Start the Liferay-Tomcat Bundle
1. **Go to** the Tomcat server's `bin` directory:
    * Windows: _C:\liferay\bundles\liferay-dxp-[version]\tomcat-[version]\bin_ in the file manager
    * Mac/Linux: _[userhome]/liferay/bundles/liferay-dxp-[version]/tomcat-[version]/bin_ using the _Terminal_
* **Start** the Tomcat Server:
    * Windows: **Double-click** on the `startup.bat`.  
    * Mac/Linux: **Run** `./catalina.sh run` in your _Terminal_.
* **Go to** `localhost:8080` in your browser if you're not already there.
* **Sign in** to the _Livingstone Hotels & Resorts_ platform. 

#### Create a Structure with Two Text Fields, an Image, and a Link Field
1. **Go to** _`Site Administration → Content & Data → Web Content`_ in the _Menu_.
* **Click** the _Structures_ tab at the top.  
* **Click** _Add_ at the top right.
* **Type** `Featured Hotels Content` for the _Name_.  
* **Drop** a _Text_ field from the _Builder_ at the right into the editor. 
    * Click the _Back_ (<) arrow to return to the _Builder_ after adding each field. 
* **Drop** another _Text_ field under the first field.
* **Drop** an _Image_ field onto the second _Text_ field to group them. 
* **Drop** a _Link to Page_ field just below the _Image_ field to group it with the _Text_ and _Image_ fields.
    * Hover your mouse over the _Fields Group_ label to confirm that the three fields are grouped together.

<br />

#### Configure the Field Labels and Field References
1. **Click** the first _Text_ field at the top of the page.  
* **Type** `Title` as the _Label_ under the _Basic_ tab.
* **Click** the _Advanced_ tab. 
* **Type** `Title` as the _Field Reference_.
* **Click** the second _Text_ field inside the _Fields Group_.  
* **Type** `Text1` as the _Label_ under the _Basic_ tab.
* **Type** `Text1` as the _Field Reference_ under the _Advanced_ tab.
* **Click** the _Image_ field.  
* **Type** `Image1` as the _Label_ under the _Basic_ tab.
* **Type** `Image1` as the _Field Reference_ under the _Advanced_ tab.
* **Click** the _Link to Page_ field.  
* **Type** `Link1` as the _Label_ under the _Basic_ tab.
* **Type** `Link1` as the _Field Reference_ under the _Advanced_ tab.

<br />

#### Duplicate the Field Group Three More Times to Feature Four Hotels
1. **Click** the _Fields Group_ label. 
* **Click** the _Options_ (three dots) icon beside the label.
* **Click** _Duplicate_ in the drop-down menu.
* **Click** _Duplicate_ again to add a third set of fields. 
* **Click** _Duplicate_ a third time to add a fourth set of fields.
    * Make sure you have a total of four field sets with Text, Image, and Link to Page fields.

<br />

#### Label the Second Set of Fields
1. **Click** the _Copy of Text1_ field in the first _Copy of Fields Group_.
* **Type** `Text2` as the _Label_ under the _Basic_ tab.
* **Type** `Text2` as the _Field Reference_ under the _Advanced_ tab.
* **Click** the _Copy of Image1_ field.  
* **Type** `Image2` as the _Label_ under the _Basic_ tab.
* **Type** `Image2` as the _Field Reference_ under the _Advanced_ tab.
* **Click** the _Copy of Link1_ field.  
* **Type** `Link2` as the _Label_ under the _Basic_ tab.
* **Type** `Link2` as the _Field Reference_ under the _Advanced_ tab.

#### Label the Third Set of Fields
1. **Click** the _Copy of Text1_ field in the second _Copy of Fields Group_.
* **Type** `Text3` as the _Label_ under the _Basic_ tab.
* **Type** `Text3` as the _Field Reference_ under the _Advanced_ tab.
* **Click** the _Copy of Image1_ field.  
* **Type** `Image3` as the _Label_ under the _Basic_ tab.
* **Type** `Image3` as the _Field Reference_ under the _Advanced_ tab.
* **Click** the _Copy of Link1_ field.  
* **Type** `Link3` as the _Label_ under the _Basic_ tab.
* **Type** `Link3` as the _Field Reference_ under the _Advanced_ tab.

<div class="page"></div>

#### Label the Fourth Set of Fields
1. **Click** the _Copy of Text1_ field in the third _Copy of Fields Group_.
* **Type** `Text4` as the _Label_ under the _Basic_ tab.
* **Type** `Text4` as the _Field Reference_ under the _Advanced_ tab.
* **Click** the _Copy of Image1_ field.  
* **Type** `Image4` as the _Label_ under the _Basic_ tab.
* **Type** `Image4` as the _Field Reference_ under the _Advanced_ tab.
* **Click** the _Copy of Link1_ field.  
* **Type** `Link4` as the _Label_ under the _Basic_ tab.
* **Type** `Link4` as the _Field Reference_ under the _Advanced_ tab.

#### Hide the Fields Group Labels
1. **Click** the first _Fields Group_. 
* **Click** the toggle beside _Show Label_ to hide the label.
* **Click** the next _Fields Group_, the first _Copy of Fields Group_. 
* **Click** the _Show Label_ toggle to hide.
* **Click** the next _Fields Group_, the second _Copy of Fields Group_. 
* **Click** the _Show Label_ toggle to hide.
* **Click** the last _Fields Group_, the third _Copy of Fields Group_. 
* **Click** the _Show Label_ toggle to hide.
* **Click** _Save_ at the top right.

<div class="page"></div>

#### Create the Featured Hotel Content
1. **Click** on the _Web Content_ tab.
* **Click** _Add_.
* **Choose** the _Featured Hotels Content_ Structure.
* **Type** `Featured Hotels` for the _Name_ at the top.
* **Type** `Livingstone Group Hotels` for the _Title_ field.
* **Type** `Playa Azul Hotel` for the _Text1_ field.
* **Click** the _Select_ button beside the _Image1_ field.
* **Click** _Select File_.
* **Choose** the `playaazul.jpg` file from your Course Module exercise folder.
* **Click** _Add_.

#### Add More Text and Images for the Featured Hotels Content
1. **Type** `Grand Livingstone Hotel` for the _Text2_ field.
* **Click** the _Select_ button beside the _Image2_ field.
* **Click** _Select File_.
* **Choose** the `grandlivingstone.jpg` file from your Course Module exercise folder.
* **Click** _Add_.
* **Type** `The Iron Rose` for the _Text3_ field.
* **Click** the _Select_ button beside the _Image3_ field.
* **Click** _Select File_.
* **Choose** the `ironrose.jpg` file from your Course Module exercise folder.
* **Click** _Add_.

<div class="page"></div>

#### Add the Last Text and Image for the New Web Content
1. **Type** `Purple Palace Hotel` for the _Text4_ field.
* **Click** the _Select_ button beside the _Image4_ field.
* **Click** _Select File_.
* **Choose** the `purple.jpg` file from your Course Module exercise folder.
* **Click** _Add_.
* **Click** _Publish_ in the top right corner.

#### Add Content to the Livingstone Landing Page
1. **Click** _Home_ at the top of the Site Administration panel.    
* **Click** the _Edit_ icon in the top right.
* **Open** the _Fragments and Widgets_ tab in the sidebar.
* **Drag** the _Web Content Display_ widget from the _Widget_ tab to the top of the page.
* **Click** the _Options_ icon on the right of the new display.
* **Choose** _Configuration_.
* **Click** the _Select_ button in the pop-up.
* **Choose** the _Featured Hotels Content_ web content that we just created.
* **Click** _Save_.
* **Close** the pop-up.
* **Click** _Publish_.

<br />

#### Begin Creating the Featured Hotels Template
1. **Go to** your _exercise-src_ folder.
* **Open** the `featured-hotels-template.ftl` file with _Visual Studio Code_.
* **Click** to highlight the `< Insert 01-div-header-and-body snippet here >` comment.
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
1. **Click** to highlight the `< Insert 04-top-row-second-column snippet here >` comment.
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
1. **Click** to highlight the `< Insert 05-second-row snippet here >` comment.
* **Type** `lfr` and choose the `05-second-row` snippet and _Save_ the file.
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

<div class="page"></div>

#### Add the Template to the Platform and Include the Title Data
1. **Go to** the _Livingstone Hotels & Resorts_ site in the browser.
  * Make sure you're logged in.  
* **Go to** _`Site Administration → Content & Data → Web Content`_ in the _Menu_.  
* **Click** the _Templates_ tab in the top menu.
* **Click** the _Add_ at the top right.
* **Type** `Featured Hotels Template` as the Title at the top of the page.
* **Click** _Select Structure_ under the _Structure_ field in the _Properties_ menu.
* **Choose** the `Featured Hotels Content` Structure.
* **Copy** the code from our new _featured-hotels-template.ftl_ template.
* **Paste** it into the Template editor, replacing the default text.
* **Click** to highlight `< Insert title data here >` at the very top of the code.
* **Click** the _Title_ field in the _Fields_ section to the left.
* **Click** _Save_.

#### Select the Template and View the Content
1. **Go to** the _Web Content_ tab.
* **Click** the _Featured Hotels Content_.
* **Click** _Select_ under the _Default Template_ field in the right column.
* **Choose** the `Featured Hotels Template`.
    * Click _OK_ if the browser pop-up requests access.
* **Click** _Publish_ at the top right.
* **Click** _Home_ in Site Administration to view the content.

<br />

---

#### Bonus Exercises
1. Update the Featured Hotels Template to your liking.
2. Create a Structure for a Press Release with the following:
    * Headline
    * Date of Publication
    * Logo
    * Content
3. Create a Template for the Press Release using the built-in editor.

-->