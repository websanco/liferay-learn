# Adding Custom Branding to Liferay with Themes

<!-- Though the default look-and-feel of Liferay may work for some businesses, others need to create their own brand identity across the platform. Customizing the look-and-feel of the platform is a necessary part of the development process.

## Perfecting the Livingstone Platform Design {#livingstone}

Martin Llewellyn has been hard at work creating the design mockups for the many Livingstone Hotels & Resorts sites. His primary work responsibilities are the following: 
* Designing graphic UI elements
* Developing UI mockups that demonstrate how the site should look and work
* Work on the style standards for fonts, colors, and images
* Gather and address user feedback on design
* Create original graphic designs

He has created the designs for different Hotel and Resort sites, the Livingstone Rewards sites, as well as the internal employee intranet. As head of the front-end development team, Kaito Tanaka is in charge of tailoring the user experience and implementing the design mockups on the platform. To start, he needs to learn to modify the presentation of the many site pages and content on the platform.

## Themes {#themes}

In Liferay, developers can control the global HTML, CSS styles, animations, icon sets, margins, and JavaScript by creating a _Theme_ module.

<div class="key-point">
Key Point: <br />
Developers can build custom Themes in order to implement their branding across the different sites on the platform.
</div>

Theme modules include a collection of packaged files with:
* SCSS  
* JavaScript  
* Images  
* FreeMarker Templates
* Theme Configurations

<figure>
	<img src="../images/fjord-example.png" style="max-height: 35%" />
	<figcaption style="font-size: x-small">Fig.1 Fjord Theme Example</figcaption>
</figure>

<br />

Themes define the basic user experience through global scaffolding and styling for site pages. Further customization is possible for the menus, panels, widget layouts, widget displays, and web content styling with other front-end modules and templates.

<div class="key-point">
Key Point: <br />
Custom themes are created on top of one of two base themes:
<ol>
	<li><b>Unstyled</b>: maintains basic functionality with no styling </li>
	<li><b>Styled</b>: inherits from the unstyled theme and adds the Clay Base, which is the SCSS implementation of the Lexicon Experience Language</li>
</ol>
</div>

These base themes provide a base that maintains Liferay functionality while allowing developers to apply custom styling. Base files are provided with base themes that can be customized. With these, there is no need to start from scratch, saving developers a lot of time.

To build and customize a new theme, we can do the following:
1. Create the theme (generate the source files from a base theme)
2. Customize HTML  
3. Define styles in SCSS  
4. Customize images  
5. Add JavaScript  
6. Finish theme module configuration

## Liferay Theme Generator {#themegenerator}

In order to build new themes, developers can take advantage of NPM and use the yeoman _Liferay Theme Generator_. This allows developers to work with tools that easily integrate into a DevOps process and quickly build and deploy new themes. 

<div class="key-point">
Key Point: <br />
The Liferay Theme Generator is a Yeoman generator that can be used for quick and easy development of Liferay themes.
</div>

The Liferay Theme Generator can be used by simply doing the following:

| Generator | Command to run | Description |
| --- | --- | --- |
| Theme | `yo liferay-theme` | Generate theme project |

With this, developers can rapidly generate the styled base theme and start with the basic html structures and styling, including the Clay base components that can be customized to meet business needs.

The Theme Generator also includes the following sub-generators:

| Sub-generator | Command to run | Description |
| --- | --- | --- |
| Layouts | `yo liferay-theme:layout` | Generate layout templates with an interactive VIM. |
| Themelets | `yo liferay-theme:themelet` | Create small, reusable, pieces of CSS and HTML for your themes. |
| Import | `yo liferay-theme:import` | Import pre-existing Liferay themes from an SDK into the npm process. |

Finally, the Theme Generator has a few different versions available to work with the different Liferay product versions. The requirements are as follows:

| Liferay Version | Liferay Theme Generator Version | Command |
| --- | --- | --- |
| 6.2 | 7.x.x | `npm install -g generator-liferay-theme@^7.x.x` |
| 7.0 | 7.x.x or 8.x.x | Same as above or below |
| 7.1 | 8.x.x | `npm install -g generator-liferay-theme@^8.x.x` |
| 7.2 | 9.x.x | `npm install -g generator-liferay-theme |

<div class="note">
Note: Running <code>npm install -g generator-liferay-theme</code> will always give you the latest version of the generator in line with the latest product. In order to ensure you have the correct version that corresponds with the product version you're working with, you should use <code>npm install -g generator-liferay-theme@^[version].x.x</code>.
</div>

## Customizing the Theme Source Files {#themesrc}

In order for developers to customize the base theme, they'll need to take the source files from the base and put them in a new folder. Every customized folder and file needs to be added to the `src` folder. This means the files need to be copied from the `build` folder, which includes the base theme files, to the `src` folder.

| File | Description |
| --- | --- |
| `build` | Folder containing the Base Theme files |
| `gulpfile.js` | Registers tasks from `liferay-theme-tasks` to your theme |
| `liferay-theme.json` | Generated file created by `gulp init` that stores app server-related configuration |
| `node_modules` | Directory where npm dependencies are installed |
| `package.json` | Where theme metadata is defined and npm dependencies are declared |
| `src` | Folder for customized files of the theme, similar to the _diffs directory in Plugins SDK Themes|
| `src/WEB-INF` | Metadata files such as `plugin-package.properties` and `liferay-look-and-feel.xml` |

<div class="note">
Note: the build folder gets created after a <i>gulp build</i> or <i>gulp deploy</i>.
</div>

<div class="key-point">
Key Point: <br />
There are four main source files in a Liferay theme: 
<ol>
	<li><b>portal_normal.ftl</b>: contains the main structure of the page; is the main HTML source file</li>
	<li><b>_custom.scss</b>: used as the scss source file for global styling</li> 
	<li><b>main.js</b>: used for global JavaScript</li>
	<li><b>liferay-look-and-feel.xml</b>: used for theme configuration</li>
</ol>
</div>

The order of deployment will deploy the Base theme (`styled` or `unstyled`) first, which includes all of the base files, and then will deploy the theme's `src` folder, which includes customizations. 

<figure>
	<img src="../images/build-templates-example.png" style="max-height: 100%" />
	<br />
	<img src="../images/src-templates-example.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.2 Mirroring build files in the SRC folder</figcaption>
</figure>

## Customizing the Theme HTML Files {#customizehtml}

To start, let's focus on customizing the html of the themes, starting with the `portal_normal.ftl` file. As stated above, the `portal_normal` file contains the main structure of every site page and is the main HTML source file.

The `portal_normal.ftl` breaks down into three main sections within the body wrapper div:
1. _The Banner Section_: includes the top part of a page with its sections
2. _The Content Section_: includes the code needed to render widgets and content
3. _The Footer Section_: includes the bottom of the page that can be customized

<figure>
	<img src="../images/page-breakdown.png" style="max-height: 36%" />
	<figcaption style="font-size: x-small">Fig.3 The three main sections in an HTML source file</figcaption>
</figure>

The `portal_normal` includes a number of the FreeMarker features throughout the file. For example, right above the wrapper div, developers can find the control menu macro that adds the control menu at the top of the platform.
```FreeMarker
<@liferay.control_menu />
```
In most cases, the control menu macro is left alone there, but developers also have the option to customize how the control menu is displayed. For example, they can use the `permissionChecker` API to only display the control menu to administrators. 

Another example can be found in the header with include navigation. This uses a FreeMarker _If_ statement combined with an `#include` directive to read the `navigation.ftl` file.
```FreeMarker
<#if has_navigation && is_setup_complete>
	<#include "${full_templates_path}/navigation.ftl" />
</#if>
```
The above example shows that it's a good practice to keep the `portal_normal` uncluttered by including external files instead of adding all the code here. Next, let's look at the additional FreeMarker files included in the base theme.

## Structuring Navigation, Pop-Ups, and Widgets {#navwidgets}

Some aspects of the `portal_normal` are simply read and kept as separate `ftl` files. In the Base Themes, this includes the `navigation.ftl`, `portal_pop_up.ftl`, and `portlet.ftl` files. Each of these files can be used to control the HTML mark-up for the navigation, pop-ups, and widgets. 

Each of these files can be used to modify the corresponding aspects of a site page. They also stand to show that developers can create new ftl files for new FreeMarker structures. For example, in the case where developers want to include a more defined structure in the footer with navigation and social media links, they could create a new `footer.ftl` file with the FreeMarker markup and include it in the `portal_normal.ftl` file using the following:
```FreeMarker
<#include "${full_templates_path}/footer.ftl" />
```

## Using and Customizing FreeMarker Variables {#variables}

Each of these files has a number of variables and macros in use. But where do these variables come from? 

The `init.ftl` file is a special file found in the base theme that includes all of the variables and Liferay APIs available by default. This is where developers can find the variables used throughout the FreeMarker files in their base theme. For example, at the top, the `init.ftl` file includes some common variables that are used throughout:

```FreeMarker
<#-- ---------- Common variables ---------- -->

<#assign
	theme_display = themeDisplay
	portlet_display = portletDisplay

	layoutSet = layout.getLayoutSet()

	theme_timestamp = themeDisplay.getTheme().getTimestamp()
	theme_settings = themeDisplay.getThemeSettings()

	root_css_class = languageUtil.get(locale, "lang.dir")
	css_class = htmlUtil.escape(bodyCssClass!)

	css_class = css_class + " " + htmlUtil.escape(theme_display.getColorScheme().getCssClass()) + " yui3-skin-sam"

	page_group = layout.getGroup()
/>
```

At the bottom of the file, the init.ftl includes a line that reads an `init_custom.ftl` file:
```FreeMarker
<#-- ---------- Custom init ---------- -->

<#include "${full_templates_path}/init_custom.ftl" />
```
This allows developers to add any new variables or macros to the theme without needing to modify the `init.ftl` file directly. 

This is important because of the order of deployment mentioned above. When the base theme is deployed first, it will include the `init.ftl` file, which is needed for any and all of the FreeMarker templates that were not customized. After this, when the `src` folder is deployed, the custom variables and macros found in the `init_custom.ftl` will be read.

With this understanding of how the HTML structure works within a Theme, we can begin by setting the foundation of the theme by making the HTML modifications and additions needed.

<div class="summary"><h3>Summary</h3>

Themes are used to modify the look-and-feel of sites to utilize Liferay to reflect your brand image.

<ul>
  <li>____________________, ____________________, ____________________, and ____________________ can be implemented to change the overall look and structure of pages in Liferay.</li>
  <li>____________________ themes built using the liferay-theme generator include the Clay Base.</li>
  <li>The ____________________ is the HTML source file in a theme.</li>
  <li>_custom.scss can be modified to change ____________________ styling.</li>
  <li>main.js contains JavaScript to be loaded on ____________________ page.</li>
  <li>liferay-look-and-feel.xml can be used to add ____________________ to the platform.</li>
</ul>
</div> -->
