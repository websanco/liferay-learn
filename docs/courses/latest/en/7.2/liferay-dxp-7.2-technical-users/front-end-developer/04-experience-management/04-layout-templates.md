## Controlling Page Layouts with Layout Templates

Layout templates control the position of content and widgets in the content section of a page, in between the header and footer sections of a Page.

<figure>
  <img src="../images/lecture-images/layout-red.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.1 Layout Example</figcaption>
</figure>

<div class="key-point">
Key Point<br />
Layout templates allow you to set the rows and columns of a page and determine where content can be placed.
</div>

#### Using Layout Templates in the Livingstone Platform {#Livingstone}

The web team at Livingstone is making use of many of the Assets included in Liferay out-of-the-box, including Blogs and Web Content. This means on many of their Hotel and Resort Sites, as well as _Livingstone Life_ the marketing Site, they'll need to make use of traditional Layouts and Widgets. Kaito and his team can quickly new layouts using the NPM tools when the default layouts don't quite fit the use-case.

<figure>
  <img src="../images/lecture-images/default-layouts.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.2 Default Layouts</figcaption>
</figure>

<br />

For the individual hotel sites, the Design team wants to go with a more modern look. They have created mockups of how content and widgets should be displayed on a hotel's front page. They want to display a large block of content, such as a banner, at the very top of the page and include smaller sections of content after. They'd also like space for a banner at the bottom of the page.

<figure>
  <img src="../images/lecture-images/mock-up.png" style="max-height: 38%;" />
  <figcaption style="font-size: x-small">Fig.3 The Livingstone Mockup</figcaption>
</figure>

<br />

The default _1-2-1 layout_ provides a similar look to their mockups, but it's not exactly what they're looking for. Since none of the default layouts meet their needs, Kaito will need to create a custom page layout.

#### Creating Custom Layout Templates {#CustomLayout}

Layouts can have any number of rows with up to twelve columns per row. Columns in a layout use Bootstrap's Grid system (https://getbootstrap.com/docs/4.0/layout/grid/) to determine screen and column size. 

<div class="key-point">
Key Point: <br />
There are 12 sections in the fluid grid system, which can be divided up or combined as needed. Because the sections are part of Bootstrap's grid system, users do not need to specify percentages or pixel widths and the layouts are responsive by default.
</div>

As we saw in a previous course module, the Liferay Theme Generator can be used to create themes and themelets, but it can also be used to create layout templates. New layouts are created using the `yo liferay-theme:layout` command. This command creates a new folder for the layout template in the current working directory.

<figure>
  <img src="../images/lecture-images/new-layout-generator.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.4 Using the Layout Sub-Generator</figcaption>
</figure>

<br />

Once the command has been run, developers simple need to walk through the naming the template, assign the template id, and select the Liferay DXP version number to which the template will be deployed. Once that information has been set, creating a template with the generator tool is very simple. The generator will prompt developers for the number of columns they would like for the first row of the layout template. Then it will prompt the user for the width of each column to be created. 

<figure>
  <img src="../images/lecture-images/1-column-wide.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.5 Choosing Columns</figcaption>
</figure>

<br />

Once the width of columns in the first row have been set, the developer can then choose to add more rows, insert rows in between existing rows, remove rows, and finish and save the layout. 

<figure>
  <img src="../images/lecture-images/add-multiple-columns.png" style="max-height: 30%;" />
  <figcaption style="font-size: x-small">Fig.6 Adding Multiple Columns</figcaption>
</figure>

<br />

With each added row, the generator will show an illustration for how the layout is currently constructed. 

<figure>
  <img src="../images/lecture-images/layout-example.png" style="max-height: 30%;" />
  <figcaption style="font-size: x-small">Fig.7 A Complete Layout</figcaption>
</figure>

<br />

Once complete, developers can select the _Finish layout_ option. The generator will build the layout and then prompt for the path to the application server directory and the URL of the production or development site. 

<div class="note">
Note: Layout Template modules can be deployed individually or included in the theme through the Theme Settings. To include it in the theme, it must also be included in the `liferay-look-and-feel.xml` file using the layout-templates tag.
</div>

After creating a new layout using the generator, users will see `docroot/{layout-name}.ftl` in the layout template directory that reflects the layout you created. The generator also automatically installs the _npm_ dependencies used for deployment. After running `npm run gulp deploy`, you will also see a `dist` folder containing the module itself. This file can then be deployed on any server you're working with.

Once a layout is complete, it can be bundled and included with a theme by following the instructions discussed earlier:
1. Create a `layouttpl` folder in the `{theme}/src` folder.
2. Add the `.ftl` file to the `{theme}/src/layouttpl` folder.
3. Copy the `<layout-template>` structure from the `liferay-layout-templates.xml` found in _WEB-INF_ and paste it into the theme's `liferay-look-and-feel.xml`.

#### Creating Templates Manually {#Manually}

Layout Templates can also be created manually by creating a `.ftl` file. Let's take a look at the basic layout template file for the _Porygon-50-50_ layout to see what should be included when creating a `.ftl` file.

```html
<div class="porygon-50-50-width-limited" id="main-content" role="main">
  <div class="portlet-layout row">
    <div class="col-md-2 portlet-column portlet-column-first" id="column-1">
      $processor.processColumn("column-1", "portlet-column-content 
      portlet-column-content-first")
    </div>
    <div class="col-md-4 portlet-column" id="column-2">
      $processor.processColumn("column-2", "portlet-column-content")
    </div>
    <div class="col-md-4 portlet-column" id="column-3">
      $processor.processColumn("column-3", "portlet-column-content")
    </div>
    <div class="col-md-2 portlet-column portlet-column-last" id="column-4">
      $processor.processColumn("column-4", "portlet-column-content 
      portlet-column-content-last")
    </div>
  </div>
</div>
```

<div class="note">
Note: The porygon examples come from the Porygon Theme developed by Liferay.
</div>

Let's break it down even further by looking at the first column of the layout.

```html
<div class="col-md-2 portlet-column portlet-column-first" id="column-1">
  $processor.processColumn("column-1", "portlet-column-content 
  portlet-column-content-first")
</div>
```
  
You'll notice three things that make up the column:
1. The id
* The classes
* The `$processor.processColumn`

The `column-1` id is a unique identifier for the column that matches the ID passed to `$processor.processColumn`.

There are a few classes we can see for the column. The`col-md-2` class comes from Bootstrap's grid system and determines two things. It sets the percentage-based width of the element. It also defines the media query breakpoint for when this element expands to 100% width. 

<div class="note">
Note: 12 is the maximum amount, so <code>col-md-2</code> indicates <i>2/12</i> width, or <i>16.66%</i>.
</div>

All column containers are required to use the `portlet-column` class. When a row has more than one column, the first column must be noted as the `portlet-column-first` class. The last column should be the `portlet-column-last` class. If a row is made up of only one column, the column should be the `portlet-column-only` class.

The `$processor.processColumn` is necessary to render our layouts. The `$processor.processColumn` function takes the CSS column ID and the list of CSS classes as its two arguments. 
  * The column ID should match the ID of the parent `<div>`.
  * The additional classes added to the content element should match the classes in the parent `<div>` with `-content` appended. 

When looking at the example template, you'll notice the `col-md-{size}` Bootstrap grid class used on every column. The different sizes available are: 
* `xs`
* `sm`
* `md`
* `lg` 

The medium size is used by default, but the others can be used in layout templates as well. Setting the column classes to `col-lg-{size}` means the columns would expand to 100% width at a larger screen width than `col-md-{size}`. These classes can also be mixed to achieve more advanced layouts.

For example, on medium-sized view ports like tablets, `column-1` would be 33.33% width and `column-2` would be 66.66% width. On small-sized view ports, both `column-1` and `column-2` would be 50% width.

<br />

```html
<div class="portlet-layout row">
    <div class="col-md-4 col-sm-6 portlet-column portlet-column-first" 
    id="column-1">
        $processor.processColumn("column-1", "portlet-column-content 
        portlet-column-content-first")
    </div>
    <div class="col-md-8 col-sm-6 portlet-column portlet-column-last" 
    id="column-2">
        $processor.processColumn("column-2", "portlet-column-content 
        portlet-column-content-last")
    </div>
</div>
```

#### Embedding Widgets into Layout Templates {#Embedding}

It's also possible to embed widgets into layout templates. This is very similar to embedding a widget into a theme. The major difference is that developers can be more flexible with where to apply a layout. If the widget does not have a Java class that extends `BasePortletProvider`, developers can use `$theme.runtime()`. If it does, a widget can be embedded using `$processor.processPortlet("CLASS_NAME", ACTION)`.

<div class="note">
Note: In DXP, the application id is no longer a number but a snake case string of the application class path.
</div>

The login widget would be embedded into a layout as follows:
```html
<div class="columns-1-2" id="main-content" role="main">
    <div class="portlet-layout">
        <div class="portlet-column portlet-column-only" id="column-1">
            $theme.runtime("com_liferay_login_web_portlet_LoginPortlet")

            $processor.processColumn("column-1", "portlet-column-content 
            portlet-column-content-only")
        </div>
    </div>
    ...
</div>
```

The `$processor.processPortlet` declaration requires two parameters:

* The class name of the entity type you want the application to handle
* The type of action

For example, if you wanted to embed the _Breadcrumb_ widget into the layout, you could use the following:
```
$processor.processPortlet("com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry", $portletProviderAction.VIEW)
```

If you want a widget, such as the search or language widget, to be embedded on every site page, you should embed the widget in the theme. If you don't want an embedded  widget with a particular layout to be displayed on every page, you can embed it in the layout template. This gives developers as much flexibility as they need when embedding widgets.

<div class="summary">
<h3>Knowledge Check</h3>
  <ul>
    <li>Layout templates allow you to set the ___________________________ and ___________________________ of a page and determine where content can be placed.</li>
    <li>Liferay provides multiple default page layout ___________________________ out of the box.</li>
    <li>Layouts can have any number of rows with ___________________________ columns.</li>
    <li>Columns in a layout use ___________________________ Grid system to determine screen and column size.</li>
    <li>Layout templates can be created with the ___________________________ or manually.</li>
    <li>Layout templates can have ___________________________ embedded in them.</li>
  </ul>
</div>