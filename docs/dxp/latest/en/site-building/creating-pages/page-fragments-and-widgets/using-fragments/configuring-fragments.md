# Configuring Fragments

```{toctree}
:maxdepth: 3

configuring-fragments/general-settings-reference.md
configuring-fragments/styles-reference.md
configuring-fragments/fragment-sub-elements-reference.md
configuring-fragments/localizing-fragment-configuration-fields.md
```

Liferay DXP provides configuration options for Fragments and their sub-elements. These options can vary, though some are common to all. To view available configuration options, begin editing a Page or Template and open the *Browser* panel (![Browser](../../../../images/icon-browser.png)) in the sidebar menu. Then, click on a Fragment or sub-element and select which options you'd like to configure from the available tabs.

```{note}
Some Fragment configuration fields can be localized. See [Localizing Fragment Configuration Fields](./configuring-fragments/localizing-fragment-configuration-fields.md) for more information.
```

## Fragment Configuration Options

Available Fragment configuration options are grouped into two tabs: General or Styles. Nearly all Fragments include both tabs. However, there are some exceptions:

* HTML, Paragraph, Separator, and Social do not have General settings.
* Collection Display does not have Styles settings.

With DXP's Responsive Layout Editor, you can also configure these settings for different viewports.

### General

The General tab includes both standard configuration options (e.g., visibility and frame) as well as options unique to each Fragment. See [General Settings Reference](./configuring-fragments/general-settings-reference.md) for more information.

### Styles

The Styles tab includes standard options for configuring a Fragment's style. This includes a Fragment's dimensions, background, borders, and more. See [Configuring Fragment Styles](./configuring-fragments/styles-reference.md) for more information.

## Fragment Sub-Element Settings

Many Fragments include sub-elements with their own configuration options. Available options depend on the sub-element's type and are organized into tabs: Mapping, Image Source, and Link. See [Fragment Sub-Elements Reference](./configuring-fragments/fragment-sub-elements-reference.md) for more information.

### Mapping

In the Mapping tab, you can map page elements to available assets by selecting the desired item (e.g., web content article, document, blog) and specifying which of its fields to display in the element (e.g., title, author, name).

### Image Source

In the Image Source tab, you can select an element's image, view its resolution, and provide its alt text.

### Link

In the Link tab, you can either manually add a URL to a page element, or select from an available content field. You can also specify the desired target (e.g., self, parent).

```{raw} html
:file: ../../../../landingpage_template.html
```

```{raw} html
:file: configuring-fragments/landing.html
```
