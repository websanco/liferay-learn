# Modify the Styling of the Themes

Coming Soon!

<!--

<div class="ahead">

#### Exercise Goals

* Customize and modify variables
  * Modify Clay Base variables
  * Add custom theme variables to the theme
* Modify components and global styles
  * Modify Clay Base partials
  * Add custom CSS and import it into the source file
* Add custom images

</div>

#### Add the SCSS File Structure to the Theme Source
1. **Copy** the contents of the _`exercise-src\css`_ folder.  
* **Paste** the files into your `livingstone-fjord-theme\src\css` folder.

#### Modify Clay Variables to Fit Branding 
1. **Drop** `_clay_variables.scss` from _`livingstone-fjord-theme\src\css`_ into the _Visual Studio Code_ editor. 
* **Click** to highlight the `// Insert snippet 01-custom-clay-variables here` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `01-custom-clay-variables` snippet.
* **Save** the file. 
  * Alternatively, you can type and save the following:

```SCSS
  $body-bg: white;
  $bright-color: #1865FB;
  $complementary-color: #869CAD;
  $dark-color: #242529;
  $light-color: #E7E7ED;
  $solid-color: #47647A;

  $portlet-decorate-bg: #FFF;
  $portlet-decorate-border: 1px solid $light-color;
```

#### Add Color Variables for the Theme
1. **Drop** `_colors.scss` from _`livingstone-fjord-theme\src\css\partials\variables`_ into the _Visual Studio Code_ editor. 
* **Click** to highlight the `// Insert snippet 02-colors-scss here` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `02-colors-scss` snippet.
* **Save** the file.
  * Alternatively, you can type and save the following:

```SCSS
  $fjord-primary: #5657A1;
  $fjord-info: #7BC6DC;
  $fjord-secondary: #BDCADB;
  $fjord-success: #57D192;
```

<div class="page"></div>

#### Import the Variable Partial Files
1. **Drop** `_variables.scss` from _`livingstone-fjord-theme\src\css\partials`_ into the _Visual Studio Code_ editor. 
* **Click** to highlight the `// Insert snippet 03-variables-scss here` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `03-variables-scss` snippet.
* **Save** the file.
  * Alternatively, you can type and save the following:

```SCSS
  @import "variables/colors";
```

#### Customize the Portlet Variables
1. **Drop** `_variables_custom.scss` from _`livingstone-fjord-theme\src\css\portlet`_ into the _Visual Studio Code_ editor. 
* **Click** to highlight the `// Insert snippet 04-portlet-variables-custom-scss here` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `04-portlet-variables-custom-scss` snippet.
* **Save** the file. 
  * Alternatively, you can type and save the following:

```SCSS
  $portlet-header-margin-bottom: map-get($spacers, 5);

  $portlet-topper-bg: #F1F5FF;
  $portlet-topper-border: #D7E5FF;
  $portlet-topper-color: map-get($theme-colors, primary);
  $portlet-topper-link-color: map-get($theme-colors, primary);
  $portlet-topper-link-hover-color: map-get($theme-colors, primary);

  $portlet-content-border-radius: 0 0 8px 8px;
  $portlet-topper-border-radius: 4px 4px 0 0;
```

#### Customize the Button Component
1. **Drop** the `_buttons.scss` file found in _`livingstone-fjord-theme\src\css\partials`_ into the _Visual Studio Code_ editor. 
* **Click** to highlight the `// Insert snippet 05-buttons-scss here` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `05-buttons-scss` snippet.
* **Save** the file.
  * Alternatively, you can type and save the following:

```SCSS
  .fjord-btn-primary {
    @include button-variant($fjord-primary, $fjord-primary);
  }
```

#### Add Custom Styling to the Footer
1. **Drop** the `_footer.scss` file found in _`livingstone-fjord-theme\src\css\partials`_ into the _Visual Studio Code_ editor.
* **Click** to highlight the `// Insert snippet 06-footer-scss here` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `06-footer-scss` snippet.
* **Save** the file.
  * Alternatively, you can type and save the following:
  
```SCSS
  .footer {
    a {
      color: inherit;
      &:hover,
      &:focus {
          color: white;
      }
    }
    // Insert snippet 10-social-media-footer-scss here
  }
```

#### Import Partials to Custom.scss
1. **Drop** the `_custom.scss` file found in _`livingstone-fjord-theme\src\css`_ into the _Visual Studio Code_ editor.
* **Press** *Enter* below the `/* inject:imports */` note.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `07-custom-scss-imports` snippet.
* **Save** the file.
  * Alternatively, you can type and save the following:

```SCSS
  @import "partials/variables";

  @import "partials/backgrounds";
  @import "partials/buttons";
  @import "partials/footer";
  @import "partials/header";
  @import "partials/texts";

  @import "portlet/portlet_decorator";
```

<div class="page"></div>

#### Add Theme Images
1. **Copy** the contents of the `images` folder from _`exercise-src`_.   
* **Paste** the contents into _`livingstone-fjord-theme/src/images`_.
  * You will need to replace the thumbnail image.

#### Deploy the Theme to See the Styling Changes
1. **Run** `npm run deploy` in the _Command Line_ or the _Terminal_.
  * Make sure you are in the `liferay/livingstone-fjord-theme` directory
  * If you're already running gulp watch, this isn't needed.
* **Open** your browser.
  * You should already have a tab open at localhost:8080
* **Click** to _Refresh_ the page after the theme changes have finished deploying.

<br />

<div class="note">
Note: Now you should be able to see all the styling changes we made to the platform. We changed the theme colors, sizes of various elements, as well as the image for our theme. If you have time, feel free to change some of these things and customize the theme however you'd like.
</div>

<div class="page"></div>

#### Add the Livingstone Logo
1. **Click** _`Site Administration > Site Builder > Pages`_ in the _Menu_ for the _Livingstone Hotels & Resorts_ site.
* **Click** the gear icon next to _Public Pages_.
* **Click** to expand the _Logo_ section near the bottom of the page.
* **Click** _`Change > Select`_.
* **Choose** the `livingstone-logo.png` file from the _`exercise`_ folder.
* **Click** _Done_.
* **Click** to set the _Show Site Name_ slider to _No_.
* **Click** _Save_.

<br />

---

#### Bonus Exercise
1. Change the theme colors and sizes of various elements using the files we created or updated in the exercise above. Be creative and make it look how you'd like it to look.

-->