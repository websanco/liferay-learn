# Adding Custom Styling to the Platform

<!-- Controlling styling across the platform is important for ensuring a consistent user experience. Theme modules are the context by which developers control not only global CSS, but also the specific styling of different components.

## Styling Livingstone's Platform {#livingstone}

CSS styles set the tone for widgets and content and control the style of things like typeface, margins, buttons, links, etc. In order for Kaito Tanaka to implement the overall design mockups, he needs to be able to customize global styles like header, footer, background, font, as well as specific css components like buttons and portlet styling. He'll be able to take advantage of the theme flexibility to do just that. 

## SCSS {#scss}

In the `_styled` base theme, the Clay base (the implementation of Lexicon) is included using _SCSS Partials_.

<div class="key-point">
Key Point: <br />
In Liferay DXP 7.2, the Clay base is built on Bootstrap and converts LESS to SASS to use the following:
<ul>
  <li>SCSS partials as well as other SASS features</li>
  <li>Bourbon, which is built using SASS, for a pre-defined Mixin Library</li>
</ul>
</div>

<figure>
  <img src="../images/bourbon-logo.png" style="max-height: 100%" />
  <br />
  <img src="../images/sass-logo.png" style="max-height: 20%" />
	<figcaption style="font-size: x-small">Fig.1 SASS and Bourbon</figcaption>
</figure>

SCSS Partials are scss files that lead with an underscore and contain snippets of css code. Having partials makes it much easier to stay modular and maintain the different Clay components along with other partials. We've seen this before with the `_custom.scss` file, which is the main SCSS source file for custom scss. The underscore with Partials lets SASS know that the file is a partial and should not be generated into CSS. These partials are then imported using the `@import` directive. During deployment, the main file that is read is the `main.scss` file, which imports all of the other main files in order:
```SCSS
@import "imports";
@import "base";
@import "portal";
@import "taglib";
@import "application";
@import "layout";
@import "navigation";
@import "portlet";
@import "widget";
@import "extras";
@import "custom";
```
Since the `_custom.scss` is being imported at the end of the file, all of the customized code added there will take precedence. 

This also allows developers to include their own custom partials. As long as the partial is imported into the `_custom.scss`, it will be read when the theme is deployed.

<figure>
  <img src="../images/base-css-folder.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.2 Base theme CSS folder</figcaption>
</figure>

## SASS Features {#scssfeatures}

SASS is similar to technologies like Less.js that use a pre-processor to allow for features not currently in CSS. 

<div class="key-point">
Key Point: <br />
SASS benefits developers by allowing them to include the following in their CSS:
<ol>
  <li> <i>Variables</i>: Allow developers to store and re-use code</li>
  <li> <i>Mixins</i>: Give the ability to group CSS declarations and re-use the group</li>
  <li> <i>Extending/Inheritance</i>: Allows developers to share properties between selectors</li>
  <li> <i>Nesting</i>: Allows developers to nest CSS selectors in a way that follows the same visual hierarchy of HTML</li>
  <li> <i>Operators</i>: Give developers the ability to do math operations within their CSS</li>
</ol>
</div>

## SASS Variables and Mixins {#varmixins}

We can see some examples of each of these throughout the default Classic theme SCSS files. For example, in the `_clay_custom.scss` partial, there are a number of default variables set that can be used throughout the other SCSS files:
```SCSS
$body-bg: #F1F2F5;
$bright-color: #1865FB;
$complementary-color: #30313F;
$dark-color: #272833;
$light-color: #E7E7ED;

$portlet-decorate-bg: #FFF;
$portlet-decorate-border: 1px solid $light-color;
```

While variables work for single lines of code, such as colors or borders, _Mixins_ can be used to re-use groups of CSS code, such as browser-specific CSS3 code. For example, if developers wanted to re-use border-radius CSS without having to rewrite the browser prefixes, they could do something like this:
```SCSS
@mixin border-radius($radius) {
  -webkit-border-radius: $radius;
     -moz-border-radius: $radius;
      -ms-border-radius: $radius;
          border-radius: $radius;
}
```

<br />

Once this mixin is created, it can be reused anywhere with `@include`, such as the following:
```SCSS
.box { 
  @include border-radius(10px); 
}
```

In order to avoid requiring developers to create common mixins for the CSS3 features, Liferay has also included the _Bourbon v4.x_ mixin library. This gives developers access to a number of pre-created mixins for use in any custom theme.

For example, if a developer wanted to add a custom font to the theme, they can use the `@include font-face(...)` mixin as a quick way to set up a bunch of font values in one method:

```SCSS
@include font-face("open_sansregular", "../fonts/opensans-bolditalic-webfont")
```
This should generate CSS that looks like this:
```SCSS
@font-face {
  font-family: "open_sansregular";
  font-style: italic;
  font-weight: bold;
}
```

Other mixins work similarly. With Bourbon, we get greater functionality with fewer lines of code.

## SASS Inheritance, Nesting, and Operators {#sassfeat}

With Inheritance, developers can reference a set of CSS properties from another selector instead of having to rewrite the same code. For example, since Clay is built on top of Bootstrap, the developers of the classic theme were able to simply inherit the alert classes for application messages in the `_liferay_custom.scss` file:
```SCSS
/* ---------- Alert messages ---------- */

.portlet-msg-alert {
  @extend .alert-warning;
}

/* ---------- Success messages ---------- */

.portlet-msg-success {
  @extend .alert-success;
}
```

The classic theme also includes a number of nesting examples. As mentioned before, Nesting allows developers to parallel the nested div structure in the CSS. It also cuts back lines of code, making the theme more efficient.

<div class="note">
Note: Overly nested rules will result in over-qualified CSS that could prove difficult to troubleshoot and maintain. This is generally considered a bad practice.
</div>

In the `_custom.scss` of the classic theme, the developers have nested the classes under the header. This includes two levels of nesting, with top-level classes as well as anchor tags or hover properties within those classes:
```SCSS
header {
  .portlet, .field-wrapper, .form-group {
    margin-bottom: 0;
  }

  .text-default {
    color: #6B6C7E;
  }

  .field-wrapper {
    position: absolute;
    right: 4px;
    top: 2px;

    a {
      color: #FFF;
    }
  }

  .portlet {
    margin-bottom: 0;

    &:hover {
      z-index: 1034;
    }
  }
...
}
```
And finally, while there aren't any major examples of using operators within the classic theme, developers can use these operators to do helpful math within the CSS. For example, if developers wanted to calculate the widths of a class that outputs as a percentage, they could add something like the following:
```SCSS
class-a[role="main"] {
  float: left;
  width: 600px / 960px * 100%;
}
```
This would create a simple fluid grid based on 960px and would generate:
```SCSS
class-a[role="main"] {
  float: left;
  width: 62.5%;
}
```

<div class="note">
Note: For more information on Sass and Bourbon, you can go to <a href="https://sass-lang.com/">https://sass-lang.com/</a> and <a href="https://www.bourbon.io/">https://www.bourbon.io/</a>
</div>

## Customizing Styling {#customstyle}

Front-end developers can define background color, accent colors, widget styling, and other styling features globally within the `_custom.scss` file.

<div class="key-point">
Key Point:<br />
The <b>custom.scss</b> is the CSS source file for adding custom global styling.
</div>

All they need to do is add their custom scss to the `custom.scss` file found in their theme  `src/css`. But since themes are built using scss partials, the development team can get more modular than just adding everything to this individual file.

<div class="key-point">
Key Point:<br />
Clay base components can be modified by customizing the component partial file. Custom partial files can also be made to modularize custom css in a theme.
</div>

After the initial build of the theme, developers will see the `build` folder, which includes the base theme files. This will include all the css partials and files that can be modified, including styles for widgets, navigation, layout, and more. To modify any of these styles in a theme, developers simply need to copy the relevant folder or file into the custom theme's `src` folder and modify it from there.

If the developer team wants to change the button styles, for example, they can copy the `_button.scss` file and place it in the src/css folder. This approach allows for much better maintenance and organization in the future. 

<div class="note">
Note: It is a best practice for developers to only include the files they plan to modify.
</div>

<figure>
  <img src="../images/build-css.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.3 Example of choosing what to modify</figcaption>
</figure>

<div class="summary">
<h3>Knowledge Check</h3>
____________________ is used to control style across the platform.
<ul>
  <li>SASS is utilized with ____________________ to include a number of features as well as CSS3 mixins.</li>
  <ul>
    <li>Now variables, ____________________, nesting, inheritance, and ____________________ can be included in your themes.</li>
  </ul>
  <li>The Clay base structure is ____________________ in its use of SASS partials and the individual components that can be modified.</li>
  <li>For all other global styles, ____________________ is the main source file and can include partial imports.</li>
</ul>
</div> -->
