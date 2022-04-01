## Controlling Web Content Presentation

Administrators can make use of Liferay's Asset Framework in addition to Widget Pages in a Site. This gives them access to a number of different types of content as well as the ability to organize that content with metadata like tags. One of the most widely used Assets, due to its flexibility, is _Web Content_. Web Content can be anything from simple static text with images to full dynamic Carousels or even simple applications. This kind of flexibility is possible in large part because of _Web Content Templates_. Web Content Templates allow developers to create a consistent presentation that simplifies the creation and publication of content by reducing time for formatting and styling.

<figure>
  <img src="../images/lecture-images/content-presentation.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.1 Content Presentation</figcaption>
</figure>

#### Using Web Content Templates to Style Livingstone Content {#Livingstone}

The Livingstone webteam has different groups responsible for Site administration and front-end development. Natalia and Maria are responslbe for generating some of the design and marketing content for the different Livingstone hotel Sites, while Martin and Kaito are working together to create mockups and implementing those designs on the different Sites. Without the use of Templates, Natalia and Maria are making use of the out-of-the-box WYSIWYG editor to format and style the content. By creating Templates that control the presentation of the different kinds of Web Content, Kaito can help streamline the publication process.

#### Structuring and Designing Content {#StructuresTemplates}

Web Content can be used for any number of different types of content, ranging from static design to full press releases. This is done by determining what information needs to be included through the use of _Web Content Structures_. Structures are groups of fields for a specific type of web content. For example, a _Press Release_ structure could be created that has a headline, date of publication, logo, and text content. Creating multiple Structures allows copy-writers and content creators to see the different kinds of content they can add to the platform and ensures the correct content is included through the Structure.

<div class="key-point">
Key Point: <br />
<b>Web Content Structures</b> define different types of Web Content as well as what fields need to be included.
</div>

<figure>
  <img src="../images/lecture-images/structure-example.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.2 Press Release Structure</figcaption>
</figure>

Once Structures are created to define the different kinds of web content needed on the platform, Front-End Developers can create _Web Content Templates_ that use the Structure data to control the presentation. For example, if there was a Structure field named `headline`, the Template can include a line with `${Headline.getData()}` to present that information.

<div class="key-point">
Key Point: <br />
<b>Web Content Templates</b> take the data from Structures and provide a consistent presentation using FreeMarker, CSS, and JavaScript. 
</div>

<figure>
  <img src="../images/lecture-images/web-content-templates.png" style="max-height: 40%;" />
  <figcaption style="font-size: x-small">Fig.2 An example of a Web Content Template.</figcaption>
</figure>

#### Web Content Structures {#Structures}

Structures are based on Liferay's forms functionality allowing administrators build new web content types using the following fields:
* **Boolean**: Adds a checkbox
* **Date**: Adds a datepicker
* **Decimal**: Adds a field that requires a number with a decimal point
* **Documents and Media**: Choose a file from the Documents and Media repository
* **Geolocation**: Adds a map that displays a location
* **HTML**: Adds the Alloy Editor
* **Image**: Choose an image from either the Documents and Media repository or the computer's storage
* **Integer**: Adds a field to input non-fractional numbers
* **Link to Page**: Adds a field to insert a link
* **Number**: Adds a place to insert any number
* **Radio**: Adds radio button inputs for a User to choose from
* **Select**: Adds a select box
* **Separator**: Adds a line separator between fields
* **Text**: Used for titles and headings
* **Text Box**: Used for the body of content or long descriptions

<figure>
  <img src="../images/lecture-images/fields.png" style="max-height: 45%;" />
  <figcaption style="font-size: x-small">Fig.4 Field types that can be added to a Web Content Structure.</figcaption>
</figure>

<br />

Let's take a look at the Press Release example again. The Press Release Web Content can include the following fields to display the appropriate information:
* **Text**: Press Release Headline
* **Date**: Publication Date
* **Image**: Logo Image
* **HTML**: Article Text

#### Web Content Templates {#Templates}

Web Content Templates are FreeMarker templates that give developers access to Java API in Liferay as well as inherit the Theme's global styling by default. Web Content Templates, like Themes, can take advantage of Clay classes, and if the Theme has customized any of the base Clay styling, these changes also apply to Web Content Templates.

<div class="key-point">
Key Point: <br />
Web Content Templates are FreeMarker templates that give developers access to Liferay's APIs,  inherit global CSS from Themes, and can make use of Clay classes and components.
</div>

Taking advantage of Clay classes in Web Content Templates can give developers the tools they need to provide a consistent look-and-feel with the rest of the platform. We've already seen how Clay components can be used together to build the perfect design, and developers can do just that by referencing Structure fields and using these components together. 

For example, developers could put together Clay classes like _aspect-ratio, card, and sticker_ classes, among others, to take text and image information and build a nicely designed card.

```HTML
<div class="aspect-ratio card-item-first">
  <div class="custom-control custom-checkbox">
    <label>
      <input class="custom-control-input" type="checkbox">
        <img alt="thumbnail" class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="${image.getData()}">
      <span class="sticker sticker-bottom-left sticker-danger rounded-circle">${acronym.getData()}</span>
    </label>
  </div>
</div>
```

<figure>
  <img src="../images/lecture-images/content-template-clay-example.png" style="max-height: 100%;" />
  <figcaption style="font-size: x-small">Fig.5 Clay in a Web Content Template.</figcaption>
</figure>

#### Generic Templates, Embedding, and Preferences {#GenericTemplates}

Web Content Templates can also be used without an associated Structure. These kinds of templates are called _Generic Templates_ and can be imported into other Templates via the FreeMarker include: `<#include "${templatesPath}/TEMPLATE-ID" />`.

<div class="key-point">
Key Point: <br />
<b>Generic Templates</b> allow developers to create stand-alone Templates that are not associated with a Web Content Structure, to be reused in other Templates.
</div>

<br />

Using Generic Templates is one way of modularizing template development. If there are certain designs, widgets, or even full web content, that should be used across multiple Web Content Templates, Generic Templates allow developers to maintain that flexibility.

Widgets can be embedded into Templates. For example, if a developer wanted to embed the Currency Converter widget for Templates to be used for international Hotel Sites, they could implement it in a Template using the following FreeMarker code:

```HTML
<@liferay_portlet_ext["runtime"] portletName="com_liferay_currency _converter_web_portlet_CurrencyConverterPortlet" />
```

<div class="key-point">
Key Point: <br />
Just like in themes, the UI Taglibs can also be used with Templates, providing reusable components that simplify display.
</div>

Access Taglibs using this FreeMarker syntax: `<@taglib_name["tag-name"] attribute=value />`. In addition to the taglibs we looked at the themes module, there are a couple of other useful taglibs:

- `liferay_ui`: general components for displaying data  
- `liferay_frontend`: Clay components

Developers can use these taglibs to embed different assets within their Templates:

```HTML
<@liferay_ui["asset-display"]
  className=article.className
  classPK=getterUtil.getLong(article.classPK, 0)
  template="full_content" 
/> 
```

Finally, Template developers can include preferences, similar to widget preferences that set configuration. Setting preferences allows developers to store settings and use them in the Template or other Templates that can reference the preferences. Setting preferences can be done by creating a new `VOID` assignment in order to run the method silently. This makes it so the return values are not displayed on a page:

```HTML
<#assign VOID = freeMarkerPortletPreferences.setValue("mySetting", "stuff") />
...
<#assign mySetting = freeMarkerPortletPreferences.getValue("mySetting") />
```

As an advanced example, if one Web Content Structure and Template was referencing another Structure/Template, preferences can be used in the referenced Template to set an identifier:

```
<#assign VOID = freeMarkerPortletPreferences.setValue("view", "exampleView") />
```

Then, in the Template referencing the other, logic can be added that provides a particular display based on the presence of the preference:

```HTML
<#if view?contains("exampleView")>
{...}
<#else>
{...}
``` 

With all this available to developers, Web Content Templates can be a powerful tool to meet the design needs of the platform.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>___________________________ are used to create new types of content with defined fields to determine what information needs to be added.</li>
  <li>___________________________ reference Structures fields to control the presentation of Web Content.</li>
  <li>Templates inherit SCSS and Clay components from a ___________________________.</li>
  <li>___________________________ are stand-alone Templates that can be used to include reusable code.</li>
</ul>
</div>