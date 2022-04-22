<h2 class="exercise">Exercises</h2>

## Create an Asset Publisher Blog Display Widget Template

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
    <li>Configure an Asset Publisher to Display Blogs</li>
    <li>Import Blog examples to see the default display</li>
    <li>Create a Widget Template to customize the blog display presentation</li>
    <ul>
      <li>Use Clay classes to render the Blog cover image and basic information in rows and columns</li>
    </ul>
	</ul>
</div>

#### Create a New Livingstone Life Page
1. **Sign in** to Liferay in your browser.
2. **Open** the _Menu_.
3. **Go to** _`Site Builder → Pages`_ in the _Site Administration_ panel.
4. **Click** _Add_ at the top right to add a public page.
5. **Choose** _Widget Page_.
6. **Type** _Livingstone Life Blogs_ for the _Name_.
7. **Click** _Add_.
8. **Click** _1 Column_ for the Layout. 
9. **Click** _Save_.

<img src="../images/add-new-blogs-page.png" style="max-height: 24%;">

#### Import Assets to the Default Site
1. **Open** the _Menu_.
2. **Go to** _`Content & Data → Blogs`_ in the Site Administration panel.  
3. **Click** the _Options_ icon in the top right.  
4. **Choose** _Export/Import_.  
5. **Click** the _Import_ tab.  
6. **Click** _Select File_.  
7. **Choose** the `blogs-import.lar` from module exercises folder.  
8. **Click** _Continue_.  
9. **Click** the _Import_ button.  
10. **Close** the pop-up.  

<img src="../images/imported-blogs.png" style="max-height: 21%;">

#### Add an Asset Publisher to the Page
1. **Click** _Go to Site_ in the Site Administration panel.
2. **Go to** the _Livingstone Life Blogs_ page.
3. **Click** _Add_ in the top right corner to open the Widget Panel.  
4. **Open** _`Widgets → Highlighted`_.  
5. **Click** _Add_ next to the _Asset Publisher_. 
6. **Click** on the _Asset Publisher_ title on the Widget.
7. **Type** _Livingstone Life_ for the title.
8. **Press** enter to save the change.

<img src="../images/new-asset-publisher.png" style="max-height: 20%;">

#### Create a Global Snippets File in Visual Studio Code
1. **Open** _Visual Studio Code_.
2. **Go to** the _User Snippets_ menu.
	* Windows: `File → Preferences → User Snippets`
	* OSX: `Gear Icon → User Snippets`
3. **Click** on _New Global Snippets file..._ in the drop-down menu.
4. **Type** _lfr-widget-templates_ for the _name_.
	* The full file name will be `lfr-widget-templates.code-snippets`.
5. **Press** enter to save.

#### Add the Snippet Content
1. **Open** the `widget-template-snippets.json` in your module exercises folder.
2. **Copy** the contents of the file.   
3. **Paste** the contents of the file in the `lfr-widget-templates.code-snippets` file just created in Visual Studio Code.
	* Make sure to replace all the existing content in the file.
4. **Save** the file.

#### Add Code to Check for Assets
1. **Go to** the module exercise folder.  
2. **Drop** the `ap-blog-presentation-template.ftl` into your _Visual Studio Code_ editor.  
3. **Click** to highlight the `<#-- Insert 01-no-results-message here -->` comment.
4. **Type** `lfr-widget` to view the available code snippets.
5. **Choose** the `01-no-results-message` snippet.
  * Alternatively, you can type:

```HTML
<#if !themeDisplay.isSignedIn()>
  ${renderRequest.setAttribute("PORTLET_CONFIGURATOR_VISIBILITY", true)}
</#if>
<div class="alert alert-info">
  <@liferay_ui["message"] key="These are not the blogs you are looking for." />
</div>
```

#### Use the AssetRenderer to Retrieve Asset Information
1. **Click** to highlight the `<#-- Insert 02-asset-renderer here -->` comment.
2. **Type** `lfr-widget` to view the available code snippets.
3. **Choose** the `02-asset-renderer` snippet.
  * Alternatively, you can type:

```HTML
<#assign
  curEntry = curEntry
  assetRenderer = curEntry.getAssetRenderer()
  blogEntry = assetRenderer.getAssetObject()
  entryTitle = htmlUtil.escape(assetRenderer.getTitle(locale))
  viewURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry)
/>
```

#### Build the Div Structure for the Blog Presentation
1. **Press** _Enter_ after the closing angle bracket for the `<#assign curEntry.../>` line, before the `</#list>` tag.
* **Type** `<div class="col-md-4 entry-card lfr-asset-item">`.
* **Press** _Enter_.
* **Type** `</div>` to close the div.
* **Press** _Enter_ and _Tab_ after the `<div class="col-md-4 entry-card lfr-asset-item">` line above the closing div.
* **Type** `<@clay["image-card"]
            imageSrc="${blogEntry.getCoverImageURL(themeDisplay)}"
            href="${viewURL}"
            title="${entryTitle}"
            subtitle="${blogEntry.getDisplayDate()?date}"/>`.
  * Alternatively, you can use the `03-card-asset` snippet under the closing assign tag. This should give you:

```HTML
<div class="col-md-4 entry-card lfr-asset-item">
  <@clay["image-card"]
    imageSrc="${blogEntry.getCoverImageURL(themeDisplay)}"
    href="${viewURL}"
    title="${entryTitle}"
    subtitle="${blogEntry.getDisplayDate()?date}"/>
</div>
```

#### Add the new Widget Template to the Platform
1. **Go to** the default Site in your browser.
  * Make sure you're logged in.
* **Open** the _Menu_.
* **Go to** _`Site Builder → Widget Templates`_ in Site Administration.
* **Click** _Add_ at the top right.
* **Choose** the Asset Publisher Template.
* **Type** _Blog Presentation Template_ for the _Name_.
* **Open** the completed `ap-blog-presentation-template.ftl`.
* **Copy** the contents.
* **Paste** the contents into the Widget Template editor.
* **Click** _Save_ at the bottom.

#### Change the Asset Publisher Widget Template
1. **Click** _Go to Site_ in Site Administration.
2. **Go to** the _Livingstone Life Blogs_ page.
* **Click** the _Options_ menu at the top of the Asset Publisher.
* **Choose** _Configuration_.
  * You should be in the _Asset Selection_ section.
* **Choose** the _Blogs Entry_ asset type from the _Asset Type_ drop-down menu under _Source_.
* **Click** _Display Settings_ tab at the top.
* **Open** the _Display Template_ drop-down.
* **Choose** the new _Blog Presentation Template_.
* **Type** _6_ in the _Number of Items to Display_ field.
* **Choose** _Regular_ from the _Pagination Type_ drop-down menu.
* **Click** _Save_.
* **Close** the pop-up.

<img src="../images/widget-template-complete.png" style="max-height: 40%;">

---

#### Bonus Exercises
1. Look at the different Clay class options and modify the Template presentation
  * You can find the Clay components here: https://clayui.com/
2. Create new blogs and see how they propagate on the Asset Publisher
3. Change the configuration of the Asset Publisher