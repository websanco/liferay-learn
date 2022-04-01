## Creating Page Fragments

<div class="ahead">

#### Exercise Goals

* Install the NPM Liferay Fragments Generator
* Create a collection and an Editable Banner Fragment
* Add editable elements to the Fragment by using the editable tag

</div>

#### Install the NPM Fragments Generator
1. **Open** Command Prompt/Terminal.
* **Run** `npm install -g generator-liferay-fragments@1.7.0` to install the Liferay Fragments Generator.  
  * If you have not yet installed the node.js, yeoman, gulp dependencies, please refer to the front-end developer module 1 exercises.
* **Run** `yo` in the _Command Line/Terminal_ to see all the generators installed.
* **Choose** _Get me out of here!_ at the bottom.

#### Add a Livingstone Fragments Project
1. **Go to** your _`liferay`_ folder in your _Command Line/Terminal_.
	* Windows: _C:\liferay_
	* Unix Systems: _[userhome]/liferay_
* **Run** _`yo liferay-fragments`_.
* **Run** _`Livingstone Fragments`_ for the _Project name_.
* **Run** _`N`_ to deny sample content

<div class="page"></div>

#### Add a Collection in the new Project Folder
1. **Go to** the _livingstone-fragments_ project folder in the _Command Line/Terminal_.
	* Windows: _C:\liferay\livingstone-fragments_
	* Unix Systems: _[userhome]/liferay/livingstone-fragments_
2. **Run** _`npm run add-collection`_ in the project folder.
3. **Type** _`Livingstone Front Page`_ for the _Collection name_.
4. **Type** _`Livingstone Front Page Fragments`_ for the _Collection description_.

#### Add the Main Banner Fragment to the Collection
1. **Go to** the _livingstone-front-page_ folder in the _Command Line/Terminal_.
	* Windows: _C:\liferay\livingstone-fragments\src\livingstone-front-page_
	* Unix Systems: _[userhome]/liferay/livingstone-fragments/src/livingstone-front-page_
2. **Run** _`npm run add-fragment`_.
* **Run** `7.3.0` as the minimum liferay version for our fragment to be compatible with.
* **Run** _`01-main-banner`_ for the _Fragment name_.
* **Choose** _Section_ as the fragment type.
* **Choose** _Livingstone Front Page_ as the collection.

<br />

<img src="images/main_banner_fragment_structure.png" style="max-width: 40%">

<div class="note">
Note: the next two exercise sub-sections go over adding snippets to Visual Studio Code. If you have already done this you can skip ahead to the Open the Index HTML File and Add a Background Image sub-section.
</div>

<div class="page"></div>

#### Create a Global Snippets File in Visual Studio Code
1. **Open** _Visual Studio Code_.
* **Go to** the _User Snippets_ menu.
  * Windows: `File > Preferences > User Snippets`
  * OSX: `Gear Icon > User Snippets`
* **Click** on _New Global Snippets file..._ in the drop-down menu.
* **Type** _lfr-page-fragments_ for the _name_.
  * The full file name will be _lfr-page-fragments.code-snippets_.
* **Click** _Save_.

#### Add the Snippet Content
1. **Open** the `page-fragment-snippets.json` in your module exercises folder.
* **Copy** the contents of the file.   
* **Paste** the contents of the file in the `lfr-page-fragments.code-snippets` file just created in Visual Studio Code.
  * Make sure to replace all the existing content in the file.
* **Save** the file.

#### Open the Index HTML File and Add a Background Image
1. **Go to** your _01-main-banner_ folder.
2. **Open** the `index.html` file in _Visual Studio Code_.
3. **Delete** the sample code in the file.
3. **Type** `lfr` and choose the `06-beginning-image-div` snippet.
  * Alternatively, you can type the following:

```html
<div class="jumbotron main-background-image" style="background-image: url(https://images.pexels.com/photos/9537/sea-beach-sand-sun.jpg);">
</div>
```

<div class="page"></div>

#### Add the Fluid Section and Left Column
1. **Press** _Enter_ and _Tab_ after the `<div class="jumbotron main-background-image" style="background-image: url(https://images.pexels.com/photos/9537/sea-beach-sand-sun.jpg);">` line.
2. **Type** `lfr` and choose the `07-add-container-left-column` snippet.
  * Alternatively, you can type the following within the first div:

```html
<section class="container-fluid container-fluid-max-xl">
  <div class="col-md-6 mr-auto text-left">
  </div>
</section>
```

#### Add the Banner Heading
1. **Press** _Enter_ and _Tab_ after the `<div class="col-md-6 mr-auto text-left">` line.
2. **Type** `lfr` and choose the `08-banner-heading` snippet.
  * Alternatively, you can type the following within the column div:

```html
<h1 class="display-4">Livingstone Hotels & Resorts</h1>
```

#### Add the Banner Lead Text
1. **Press** _Enter_ after the `</h1>` tag.
2. **Type** `lfr` and choose the `09-banner-lead-text` snippet.
  * Alternatively, you can type the following under the banner heading:

```html
<p class="lead">
  Get ready for the journey of a lifetime. It's time to <strong>go anywhere</strong>.
</p>
```

<div class="page"></div>

#### Add Two Buttons for Discovering and Booking
1.  **Press** _Enter_ after the `</p>` tag.
2. **Type** `lfr` and choose the `10-banner-buttons` snippet.
  * Alternatively, you can type the following under the feature title:

```html
<div class="btn-group">
  <div class="btn-group-item">
    <a class="btn btn-default btn-theme-default btn-xl" href="#">Discover</a>
  </div>

  <div class="btn-group-item">
    <a class="btn btn-primary btn-theme-primary btn-xl" href="#">Book Now</a>
  </div>
</div>
```

#### Make the Heading and Lead Text Editable
1. **Click** to place your cursor after the `<h1 class="display-4">` tag.
* **Type** `data-lfr-editable-id="header1" data-lfr-editable-type="text"` as attributes within the h1 tag to make the text editable.
* **Click** to place your cursor in front of the _Get ready..._ text.
* **Type** `<lfr-editable id="body-text" type="text">` to make the text editable.
* **Click** to place your cursor after the _Get ready..._ text.
* **Type** `</lfr-editable>` to close the editable tag.
* **Save** the file.
  * Make sure the heading and lead text sections look like this: 

```html
<h1 class="display-4" data-lfr-editable-id="header1" data-lfr-editable-type="text">
  Livingstone Hotels & Resorts
</h1>
<p class="lead" data-lfr-editable-id="body-text" data-lfr-editable-type="text">
      Get ready for the journey of a lifetime. It's time to <strong>go anywhere</strong>
</p>
```

#### Make the Buttons Editable
1. **Click** to place your cursor in the first `<a class="btn btn-default btn-theme-default btn-xl" href="#">` tag.
* **Type** `data-lfr-editable-id="first-button" data-lfr-editable-type="link"` as an attribute to make the text editable.
* **Click** to place your cursor in the second `<a class="btn btn-default btn-theme-primary btn-xl" href="#">` tag.
* **Type** `data-lfr-editable-id="second-button" data-lfr-editable-type="link"` to make the text editable.
* **Save** the file.
  * Make sure the heading and lead text sections look like this: 

```html
<div class="btn-group">
  <div class="btn-group-item">
    <a class="btn btn-default btn-theme-default btn-xl" href="#"    data-lfr-editable-id="first-button" data-lfr-editable-type="link">
      Discover
    </a>
  </div>

  <div class="btn-group-item">
    <a class="btn btn-primary btn-theme-primary btn-xl" href="#" data-lfr-editable-id="second-button" data-lfr-editable-type="link" 
      Book Now
    </a>
  </div>
</div>
```

<br />

---

#### Bonus Exercises
1. Create a new Collection using the Fragments Generator
2. Create new Fragments using the Fragments Generator. Make sure it has editable elements.
