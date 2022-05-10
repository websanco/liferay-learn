# Styles Reference

Liferay Page Fragments include convenient style settings so you can configure their look and feel via the Liferay UI. Many of these options correspond to CSS classes and have defined tokens supported by Style Books. See [General Settings Reference](./general-settings-reference.md) and [Fragment Sub-Elements Reference](./fragment-sub-elements-reference.md) for additional Fragment configuration options.

Follow these steps to access a Fragment's *Styles* menu:

1. Begin editing a Page or Template that supports Fragments.

1. Open the *Browser* panel (![Browser](../../../../../images/icon-browser.png)) in the sidebar menu and select the desired *Fragment* under Page Elements.

   Alternatively, you can click on the desired Fragment in the Page or Template to open this menu.

   ```{note}
   Sub-elements within a fragment do not have independent style options.
   ```

1. Click the *Styles* tab in the Page Elements side menu.

![Access a Fragment's Styles settings in the Browser panel of the editing sidebar menu.](./styles-reference/images/01.png)

Many styles have defined tokens supported by Style Books. See [Creating a Style Book](../../../../site-appearance/style-books/using-a-style-book-to-standardize-site-appearance.md) and [Style Book Token Definitions](../../../../site-appearance/style-books/developer-guide/style-book-token-definitions.md) for more information.

## Spacing

```{note}
This feature requires Liferay DXP 7.4 U21+ or GA21+. If you are using an older version, see [this section](#liferay-7-4-u20-and-older) instead.
```

Configure the spacing around the Fragment by clicking on the regions surrounding the rectangle in the Spacing section. Click the outer buttons to configure the space between the Fragment's frame and the end of its surrounding context (the *margin* size); this affects margins such as the space between the Fragment's frame and the edge of the page, or the Fragment's Container. Click the inner buttons to configure the space between a Fragment's frame and its content (the *padding* size).

![Click the regions aroud the rectangle in the Spacing section to configure the spacing for the Fragment's margin or padding.](./styles-reference/images/02.png)

When you click one of these regions, a list appears with pre-defined options for how many pixels to set the chosen spacing size to. These sizes come from the numbered *Spacer* fields in the Spacing category when you [create or edit the Style Book](../../../../site-appearance/style-books/using-a-style-book-to-standardize-site-appearance.md#spacing).

```{note}
If the spacing options in your Style Book are defined in units other then pixels (such as `rem`), then they are converted to the equivalent number of pixels when you are selecting them in a Fragment's Styles configuration.
```

![When you click any of the regions, choose the desired spacing from the list of sizes shown.](./styles-reference/images/03.png)

## Text

Format the Fragment's text.

| Field | Description |
| :--- | :--- |
| Font Family | Specifies the font for an element. |
| Font Weight | Sets how thick or thin characters in text should be displayed. |
| Font Size | Sets the size of a font. |
| Text Color | Choose a color for the text. The available color palette can be configured by the Fragment developer. See [Fragment Configuration Types Reference](../../../../developer-guide/reference/fragments/fragment-configuration-types-reference.md) for more information. |
| Text Align | Specifies the horizontal alignment of text in an element. Choose from *Left*, *Right*, *Center*, or *Justify*. |

## Background

Configure the Fragment's background.

| Field | Description |
| :--- | :--- |
| Background Color | Choose a background color from the palette. The available color palette can be configured by the Fragment developer. See [Fragment Configuration Types Reference](../../../../developer-guide/reference/fragments/fragment-configuration-types-reference.md) for more information. |
| Image Source | If set to *Direct*, users can upload a background image. If set to *Mapping*, users can choose from Documents and Media, Web Content, Blogs, or Categories. |
| Background Image (*Direct*) | Select an image  from your library or enter an image URL. |
| Item (*Mapping*) | Map the background to a specific item from Documents and Media, Web Content, Blogs, or Categories. Once selected, click the *Actions* button (![Actions button](../../../../../images/icon-actions.png)) to edit the item and its permissions, or view its usages. |
| Field (*Mapping*) | Determine which of the item's fields is used for the background image. |

```{note}
When mapping the background to an item's field, you can only select from fields in its structure. For example, if you've selected a Web Content item, you can only select from image fields in its structure and not from images in it's content field.
```

## Borders

Determine the width, radius, and color of the Fragment's border.

| Field | Description |
| :--- | :--- |
| Border Width | Sets the width of an element's four borders. |
| Border Radius | Defines the radius of the element's corners and allows you to add rounded corners. |
| Border Color | Sets the color of an element's four borders. The available color palette can be configured by the Fragment developer. See [Fragment Configuration Types Reference](../../../../developer-guide/reference/fragments/fragment-configuration-types-reference.md) for more information. |

## Effects

Set the Fragment's opacity and determine whether it has a shadow.

| Field | Description |
| :--- | :--- |
| Opacity | Sets the transparency level; 100 is default and 0 is transparent. |
| Shadow | Adds a shadow around the box; can choose from a small, medium, or large shadow. |

## Liferay 7.4 U20 and Older

In earlier versions than Liferay DXP 7.4 U21 or GA21, the Spacing section is split into the *Margin* and *Padding* sections.

### Margin

Configure the space between a Fragment's frame and the end of its surrounding context (e.g., between the frame and the edge of the page, or between the frame and the edge of its Container or Grid).

| Field | Description |
| :--- | :--- |
| Margin Top | Sets the top margin of an element. |
| Margin Bottom | Sets the bottom margin of an element. |
| Margin Left | Sets the left margin of an element. |
| Margin Right | Sets the right margin of an element. |

### Padding

Configure the space between a Fragment's frame and its content.

| Field | Description |
| :--- | :--- |
| Padding Top | Sets the top padding (space) of an element. |
| Padding Bottom | Sets the bottom padding of an element. |
| Padding Left | Sets the left padding of an element. |
| Padding Right | Sets the right padding of an element. |

## Additional Information

* [Using Content Pages](../../../using-content-pages.md)
* [Using Fragments](../../using-fragments.md)
* [Creating a Style Book](../../../../site-appearance/style-books/using-a-style-book-to-standardize-site-appearance.md)
* [Style Book Token Definitions](../../../../site-appearance/style-books/developer-guide/style-book-token-definitions.md)
