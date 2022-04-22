<h2 class="exercise">Exercises</h2>

## Create A Banner Fragment Using the Fragments Generator

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
        <li>Install the NPM Liferay Fragments Generator</li>
        <li>Create a Collection and an Editable Banner Fragment</li>
        <ul>
            <li>Add the Fragment with the Editable tag</li>
            <li>Import the Fragment into the Platform</li>
        </ul>
	</ul>
</div>

#### Install the NPM Fragments Generator
1. **Run** `npm install -g generator-liferay-fragments@1.x.x` to install the Liferay Fragments Generator.  
  * If you have not yet installed the node.js, yeoman, gulp dependencies, please refer to the front-end developer module 1 exercises.
2. **Run** `yo` in the _Command Line/Terminal_ to see all the generators installed.  
	* Type _Y or N_ if an initial prompt asks to collect data.
3. **Choose** _Get me out of here!_ at the bottom.

<br />

#### Add a Livingstone Fragments Project
1. **Go to** your _`liferay`_ folder in your _Command Line/Terminal_.
	* Windows: _C:\liferay_
	* Unix Systems: _[userhome]/liferay_
2. **Run** _`yo liferay-fragments`_.
3. **Run** _`Livingstone Fragments`_ for the _Project name_.
4. **Run** _`N`_ to deny sample content

<br />

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
3. **Run** _`01-main-banner`_ for the _Fragment name_.
4. **Choose** _Component_ for the _Fragment Type_.
5. **Choose** the _Livingstone Front Page_ for the Collection.

<img src="../images/exercise-images/main-banner-fragment-structure.png" style="max-height: 22%">

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
<h1 class="display-4">Plan your next trip now</h1>
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

#### Add two Buttons for Discovering and Booking
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
* **Type** `<lfr-editable id="main-title" type="text">` to make the text editable.
* **Click** to place your cursor after the _Livingstone Hotels & Resorts_ text.
* **Type** `</lfr-editable>` to close the editable tag.
* **Click** to place your cursor in front of the _Get ready..._ text.
* **Type** `<lfr-editable id="body-text" type="text">` to make the text editable.
* **Click** to place your cursor after the _Get ready..._ text.
* **Type** `</lfr-editable>` to close the editable tag.
* **Save** the file.
  * Make sure the heading and lead text sections look like this: 

```html
<h1 class="display-4">
  <lfr-editable id="main-title" type="text">Livingstone Hotels & Resorts</lfr-editable>
</h1>
<p class="lead">
    <lfr-editable id="body-text" type="text">
        Get ready for the journey of a lifetime. It's time to <strong>go anywhere</strong>
    </lfr-editable>.
</p>
```

#### Make the Buttons Editable
1. **Click** to place your cursor after the first `<a class="btn btn-default btn-theme-default btn-xl" href="#">` tag.
* **Type** `<lfr-editable id="first-button" type="text">` to make the text editable.
* **Click** to place your cursor after the _Discover_ text.
* **Type** `</lfr-editable>` to close the editable tag.
* **Click** to place your cursor after the second `<a class="btn btn-default btn-theme-primary btn-xl" href="#">` tag.
* **Type** `<lfr-editable id="second-button" type="text">` to make the text editable.
* **Click** to place your cursor after the _Book Now_ text.
* **Type** `</lfr-editable>` to close the editable tag.
* **Save** the file.
  * Make sure the heading and lead text sections look like this: 

```html
<div class="btn-group">
  <div class="btn-group-item">
    <a class="btn btn-default btn-theme-default btn-xl" href="#">
      <lfr-editable id="first-button" type="text">Discover</lfr-editable>
    </a>
  </div>

  <div class="btn-group-item">
    <a class="btn btn-primary btn-theme-primary btn-xl" href="#">
      <lfr-editable id="second-button" type="text">Book Now</lfr-editable>
    </a>
  </div>
</div>
```

#### Import the Fragment on to the Liferay Platform
1. **Run** _npm run gulp import_.
2. **Press** _Enter_ to accept the default _`localhost:8080`_ _host & port_.
3. **Type** in the administrator Username for the Platform.
  * If you see your admin email as the address, simply hit enter to accept.
4. **Type** your password.
5. **Choose** the default _Company ID_.
6. **Choose** the default Site _Group ID_.
  * If you installed the platform in module one, it should be _Livingstone Hotels & Resorts_.
7. **Go to** _`localhost:8080`_ in your browser.
8. **Go to** _`Menu → Site Builder → Page Fragments`_ to verify that it imported.

<br />

<img src="../images/exercise-images/imported-fragment-successful.png" style="max-height: 100%">

<br />

<div class="note">
  Note: There is a chance that the import command will not work. If you are running into this issue, you can simply add a new Collection and Fragment in <code>Menu → Site Builder → Page Fragments</code> and copy the html code to the new fragment.
</div>

<br />

---

#### Bonus Exercises
1. Create a Content Page, go to the Section Builder, and Add the new Fragment
2. Create a new Collection using the Fragments Generator
3. Create new Fragments using the Fragments Generator
  * Add editable elements
  * Include HTML, CSS, or JavaScript