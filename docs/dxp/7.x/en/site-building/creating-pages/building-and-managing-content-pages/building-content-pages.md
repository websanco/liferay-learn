# Building Content Pages

```note::
   This information applies to Liferay DXP 7.3+. For previous Liferay DXP versions, see ![Liferay DXP 7.1 and 7.2](#liferay-dxp-7.1-and-7.2).
```

Once you've [added a Content Page](../adding-pages/adding-a-page-to-a-site.md), you may begin building your page by adding and configuring the various [Content Page elements](./content-pages-overview.md).

When you enter the Site Builder view, you can add or edit your content using two different editing modes:

* *Page Design*: Using this mode, you can edit the page design and content. This option is only available for users with permission to update the page and the page content.
* *Content Edition*: Using this mode, you can only edit the content. This option is available for users with permission to update the page, but not the page content.

The editing options available in the Content Page sidebar depend on the editing mode you choose.

![Choose between Page Design or Content Edition editing modes.](building-content-pages/images/18.png)

## Adding Elements to a Content Page

1. Enter the Site Builder view for your Content Page. Open the Product Menu and go to *Site Builder* &rarr; *Pages* under your Site's Menu.

    ![Begin by editing your new Content Page in the Site Builder menu.](building-content-pages/images/01.png)

    ```note::
       New Content Pages start empty and in a *Draft* status. The page is not visible until it is published.
    ```

    ![A Content Page is blank to begin with. You must add Fragments to it to build it.](./building-content-pages/images/02.png)

1. Open the Content Page sidebar and begin adding Page Fragment elements.

    ![Add Fragments to the page to create the mockup you want.](./building-content-pages/images/03.png)

1. Configure the editable portions of the Page Fragments to customize the look of your Content Page. Elements can be configured in some of these ways:

    * [Setting a Background Color](#setting-a-background-color)
    * [Using a Background Image](#using-a-background-image)
    * [Editing Text](#editing-text)
    * [Editing a Hyperlink](#editing-a-hyperlink)
    * [Editing an Image](#editing-an-image)
    * [Copying a Fragment](#copying-a-fragment)
    * [Saving a Fragment Composition](#saving-a-fragment-composition)
    * [Configuring Section Width and Padding](#configuring-section-width-and-padding)
    * [Configuring a Row's Columns](#configuring-a-row-s-columns)
    * [General Configuration](#general-configuration)
    * [Mapping Content](#mapping-content)

    ![Modify the Fragments to display the content you want.](./building-content-pages/images/04.png)

    ```tip::
       In progress work on a Content Page is automatically saved.
    ```

1. Click the *Publish* button in the top right to make the updates available to the live page.

### Content Page Editing

> Available: Liferay 7.3+

You can revert any action using the *Undo* (![Preview](../../../images/icon-undo.png)) or *Redo* (![Preview](../../../images/icon-redo.png)) buttons, or you can return to a previous version of your edits using the *History* (![Preview](../../../images/icon-time.png)) button.

![Revert your changes using the Undo, Redo, and History buttons.](./building-content-pages/images/17.png)

To preview your Content Page, click the *Preview* button (![Preview](../../../images/icon-preview.png)).

## Configuring Elements on a Content Page

Many elements that can be added to a content page can be configured and customized. Clicking on an element provides a management toolbar with access to available configurations for the selected element.

### Setting a Background Color

You can set the background color of a Layout:

1. Click the Layout to select it.
1. Click the (![Background Color](../../../images/icon-color.png)) icon to select a color.

![You can set the background color of a Layout.](./building-content-pages/images/05.png)

```note::
   The available color palette can be configured by the Fragment developer. See Fragment Configuration Types for more information.
```

### Using a Background Image

You can configure a background image for a Layout:

1. Click on the Layout to select it.
1. Select *Layout Background Image*, and choose the image to display.

    ![Layouts have options for background color, background image, and spacing.](./building-content-pages/images/06.png)

    ```note::
       Mapping a Layout background image is available in Liferay DXP 7.2 SP1+ and Liferay Portal 7.2 GA2+.
    ```

### Editing Text

1. Click on the text that you want to edit.
1. Replace the text if Plain text or use the inline text editor to update the Rich text styles, typographical emphasis, alignment, and list formatting.

![You can modify editable text.](./building-content-pages/images/07.png)

### Editing a Hyperlink

1. Click on the link, button, or image that you want to edit.
1. Click on (![Edit](../../../images/icon-edit.png)) to edit the link text, (![Link](../../../images/icon-link.png)) to edit the link properties, or (![Map](../../../images/icon-map.png)) to edit the link mapping (described earlier).

    From the Link Properties popup, you can define these link options:

    *Manual:* defines a manual link or maps it to an existing content field

    * *URL:* set the link's URL
    * *Target:* set the link's behavior

    *From Content Field:*

    * *Content:* set the content type
    * *Field:* set the field to display for the selected content

    A list of some of the available content fields is shown below:

    * Categories
    * Tags
    * Display Page URL
    * Description
    * Publish Date
    * Summary
    * Title
    * Last Editor Name
    * Author Name
    * Basic Web Content

![You can modify editable links.](./building-content-pages/images/08.png)

### Editing an Image

1. Click on the image you want to replace.
1. Click the (![Image Properties](../../../images/icon-edit.png)) icon.

    ![Editing an image allows you to enter a URL, select an image from Documents and Media or set a link for the image.](./building-content-pages/images/09.png)

1. Click *Select* to upload an image from Docs and Media or define an image URL. Click *Clear* to reset the image. You can also specify an
image description.

You can also [specify a background image for a layout](#using-a-background-image) and [provide a link for your image](#editing-a-hyperlink).

### Copying a Fragment

You can duplicate a Fragment on the page (Component, Section, Row, etc.) to save time:

1. Hover over the Fragment you want to copy.
1. Click the Fragment to select it.
1. Click the Duplicate Fragment button in the context menu that appears.

![You can duplicate Fragments on the page.](./building-content-pages/images/10.png)

```note::
  Duplicated Sections and Rows appear directly below the Section or Row that is duplicated. The Fragments, mappings, and customizations are duplicated as well.
```

```warning::
  Layouts (Sections or Rows) containing instanceable Widgets cannot be duplicated. In this case, a message will appear, indicating the Widget preventing the duplication.
```

### Saving a Fragment Composition

```note::
  Available: Liferay Portal 7.3 GA3+
```

You can save customized Fragment compositions (Section or Row layout Fragments) as new Fragments, so you can reuse them in your other Fragment-based pages:

1. Click the Row or Section composition to save.
1. Click (![Save icon](../../../images/icon-save.png)) in the context menu that appears.

  ![Click the Row or Section to bring up the Save Fragment composition button.](./building-content-pages/images/11.png)

1. In the Save as Fragment dialog that appears, provide a name, optional description and thumbnail, disable/enable options for including inline content such as fragment entries (links, images, text, etc.) that have been edited and [mapped content](#mapping-content), and select the Collection where you want to save the Fragment.

  ![Provide the information for the Fragment composition in the dialog that appears.](./building-content-pages/images/12.png)

```note::
   If no Site-specific Fragment Collection exists, the saved Fragment composition is automatically saved to a new Fragment Collection called Saved Fragments.
```

The saved Fragment composition can be used immediately in the Fragments sidebar and through the Page Fragments administrative application.

![The saved Fragment composition can be used immediately.](./building-content-pages/images/13.png)

Compositions can be exported/imported between sites just as any other Fragment.

```note::
  The latest version of the `Liferay Fragments Toolkit <../../developer-guide/developing-page-fragments/using-the-fragments-editor.md>`_ supports creation, export/import, and preview of Fragment compositions.
```

### Configuring Section Width and Padding

1. Click the Section to select it.
1. Click the (![Cog icon](../../../images/icon-control-menu-gear.png)) to open the Section's Configuration Menu.
1. Update the Section's *Container* setting to adjust the width styling (*Fixed Width* or *Fluid*), and update the *Padding Top* and *Padding Bottom* setting to adjust the padding for the Section container.

## Liferay DXP 7.1 and 7.2

You can also configure a background image and color for a layout Section. See [Using a Background Image](#using-a-background-image) for more information.

### Configuring a Row's Columns

1. Click the Row to select it.
1. Click the (![Cog icon](../../../images/icon-control-menu-gear.png)) to open the Row's Configuration Menu.
1. Update the *Number of Columns* setting (from 1 to 6) to specify the number of columns for the Row, and disable/enable the *Columns Gutter* setting to remove/add padding between columns.

![You can configure basic styling through the Section's Configuration Menu.](./building-content-pages/images/15.png)

You can adjust the width of the columns to create a more custom layout. Follow these steps:

1. Click on the Row to select it. A blue dot appears between each of the columns, indicating that they can be resized.
1. Click one of the blue dots and drag to the left or right to adjust the size of the column.

![You can adjust the spacing of columns in a Layout to create a custom Layout.](./building-content-pages/images/16.png)

### General Configuration

This appears for some out-of-the-box fragments and presents context specific fragment configurations. For a reference on these configurations, see Basic Component Configuration Reference.

### Mapping Content

You can also map these elements to content. You can set the *Content* for the element (web content article, document, or blog) and choose its applicable *Field* to display (e.g., title, author name, tags, etc.). You can configure this by selecting the element's *Map* button (![Map](../../../images/icon-map.png)).

```note::
   Many mapping improvements were released in Liferay DXP 7.2 SP1+ and Liferay Portal 7.2 GA2+. For example, mapping editable elements to text/URL fields of existing content and mapping Fragment background images to image fields of existing content. You can also `map Web Content Templates to Fragments <./mapping-web-content-templates-to-fragments.md>`_.
```

When you create Content Pages, you can create different **Experiences** for users based on User Segments. You can create a unique Experience on any Content Page for any existing User Segment. For more information, see the [Content Page Personalization guide](../../personalizing-site-experience/personalizing-site-experience.md).

## Related Information

* [Developing a Page Fragment](../../developer-guide/developing-page-fragments/developing-fragments-intro.md)
* [Using Master Pages](../defining-headers-and-footers/master-page-templates.md)
* [Changing Content Pages Look and Feel](./content-pages-overview.md#look-and-feel)
