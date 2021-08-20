# Creating Content Page Compositions Using the Container Fragment

Layout and composition are the foundation of your [Content Page](./content-pages-overview.md) design. When you get this right, your Content Page is easier to read and navigate, resulting in a richer and more consistent user experience. Using the [Container Fragment](./page-fragments-user-interface-reference.md#container) in Liferay DXP, you can define drop zones for other Fragments, create Page compositions with a high degree of customization, and reuse these compositions across your Site Pages.

```tip::
   In addition to the Container Fragment, you can use the `Grid Fragment <./page-fragments-user-interface-reference.md#grid>`_ to customize the Content Page layout and create a responsive design. For more information, see `Building Responsive Layouts with the Grid Fragment <../../optimizing-sites/building-a-responsive-site/building-responsive-layouts-with-the-grid-fragment.md>`_.
```

When you [create a new Content Page](../adding-pages/adding-a-page-to-a-site.md), a default drop zone appears. You can add any Fragment to this area, but using the Container Fragment as the first element in your composition has important advantages:

- Greater layout control over the rest of the elements on your Content Page design, including the *Flex* Display Properties for [advanced compositions](#creating-advanced-compositions-with-the-container-fragment) (Liferay DXP 7.4+.)
- Fragment compositions that you can [save and reuse](../../displaying-content/using-fragments/saving-a-fragment-composition.md) in other Content Pages.
- Redirect users to another Page or URL when they click on the Fragment composition.
- Leverage [Style Books](../../site-appearance/style-books/using-a-style-book-to-standardize-site-appearance.md) to standardize your Site appearance.

![You can add Fragments on the by-default drop zone of a new Content Page.](./creating-content-page-compositions-using-the-container-fragment/images/01.png)

```note::
   When designing your Content Page layout, start with the Container Fragment. Add other Fragments on top of this Container for maximum flexibility and layout control.
```

## Example: Using the Container Fragment for a Banner Composition

This step-by-step example shows you how to create a banner-style composition using nested Container Fragments. The example also illustrates how to save the composition and adjust its settings in the Style Book.

![Example of a sample Fragment composition using the Container Fragment](./creating-content-page-compositions-using-the-container-fragment/images/02.png)

- [Step 1: Creating a New Content Page](#step-1-creating-a-new-content-page).
- [Step 2: Configuring the Container Fragment](#step-2-configuring-the-container-fragment).
- [Step 3: Saving the Fragment Composition](#step-3-saving-the-fragment-composition).
- [Step 4: Setting the Default Container Width in the Style Book](#step-3-setting-the-default-container-width-in-the-style-book).

### Step 1: Creating a New Content Page

1. Under the Site Menu, go to *Site Builder* &rarr; *Pages*.

1. Click *Add* (![Add Page](../../../images/icon-plus.png)) at the level where you want to add the new Page and select *Add Page*.

1. Select the *Blank* template or one of the existing templates.

1. In the *Add Page* dialog, type your Page's *Name*.

### Step 2: Configuring the Container Fragment

1. From the Content Page sidebar, select *Fragments and Widgets* (![Fragments and Widgets](../../../images/icon-cards2.png)) and drag and drop the *Container* Fragment over the default drop-zone.

    ![Drag and drop the Container Fragment on top of the Content Page drop zone.](./creating-content-page-compositions-using-the-container-fragment/images/03.png)

1. Click the Container Fragment and in the *Selection* (![Selection](../../../images/icon-pages-tree.png)) panel, click *Styles*.

1. Under the Padding area, set the four Padding options to `5` (A).

1. Still in the Styles area, in the *Background Color* selector, select a gray color value of `200` (B).

    ![Define the Padding and Background Color value of the Container Fragment.](./creating-content-page-compositions-using-the-container-fragment/images/04.png)

1. Select *Fragments and Widgets* (![Fragments and Widgets](../../../images/icon-cards2.png)) and drop a new Container Fragment over the existing one. This creates a nested Container composition that appears when you click on the *Selection* (![Selection](../../../images/icon-pages-tree.png)) panel.

    ![Drag and drop a new Container Fragment on top of the previous one.](./creating-content-page-compositions-using-the-container-fragment/images/05.gif)

1. From the Fragments and Widgets (![Fragments and Widgets](../../../images/icon-cards2.png)) panel, under Basic Components, drag and drop a *Heading* Fragment inside the inner Container in the composition.

    ![Drag and drop a Heading Fragment inside the inner Container Fragment.](./creating-content-page-compositions-using-the-container-fragment/images/06.png)

1. Drag and drop a *Paragraph* Fragment right below the Heading. Select the Paragraph in the composition and under the *Styles* section, choose a *Margin Top* of `3`.

    ![Drag and drop a Paragraph Fragment right under the Heading Fragment.](./creating-content-page-compositions-using-the-container-fragment/images/07.png)

1. From the Selection (![Selection](../../../images/icon-pages-tree.png)) panel, click the inner Container. Under the *General* tab, type the URL where you want to redirect users when they click on this area.

    ![Define a target link or Page for the inner Container Fragment.](./creating-content-page-compositions-using-the-container-fragment/images/08.png)

1. From the Fragments and Widgets (![Fragments and Widgets](../../../images/icon-cards2.png)) panel, under Basic Components, drag and drop a *Button* Fragment in the composition, right below the paragraph.

1. Select the _Button_ and under the Styles section, set a *Margin Top* of `3`. Under the *General* section, click the *Type* drop-down menu and choose _Link_.

    ![Configure the button Fragment type and top margin.](./creating-content-page-compositions-using-the-container-fragment/images/10.png)

1. In the Selection (![Selection](../../../images/icon-pages-tree.png)) panel, click the *link* for the Button Fragment. Under the Link section, type the URL you want to redirect users when they click the button.

    ![Configure the button Fragment target link.](./creating-content-page-compositions-using-the-container-fragment/images/09.png)

```note::
   You don't need to Publish the Content Page. In-progress work on a Content Page is automatically saved as a draft.
```

### Step 3: Saving the Fragment Composition

You can save Fragment compositions as new Fragments and reuse them in other Content Pages. To learn more, read [Saving a Fragment Composition](../../displaying-content/using-fragments/saving-a-fragment-composition.md).

![Saving your Fragment Composition from the Container Option menu.](./creating-content-page-compositions-using-the-container-fragment/images/11.png)

### Step 4: Setting the Default Container Width in the Style Book

By default, the Container Fragment uses a *Fluid* width configuration. You can change the default setting to *Fixed Width*, and make this property available for other Content Pages based on the same Style Book. In this example with two nested Containers, you may want to configure the outer Container to adapt to the Page width while limiting the maximum width of Container with the text. To learn more about Style Books in Liferay DXP, read [Using a Style Book to Standardize Site Appearance](../../site-appearance/style-books/using-a-style-book-to-standardize-site-appearance.md).

![You can change the Container Width property to use a fixed width or adapt to the Page width.](./creating-content-page-compositions-using-the-container-fragment/images/18.gif)

```warning::
   To avoid breaking your Site layout configuration, use a test environment when working with Style Books. Do not update a Style Book configuration for testing purposes on a production Site.
```

1. From the Selection (![Selection](../../../images/icon-pages-tree.png)) panel, click the inner Container Fragment.

1. Under the Styles area, set the *Container Width* property to *Fixed Width*.

    ![Change the inner Container Fragment width setting to Fixed Width.](./creating-content-page-compositions-using-the-container-fragment/images/12.png)

1. Click the *Page Design Option* panel (A) and under the *Style Book* section (B), verify the active Style Book (C).

    ![Verify the default Style Book in use for the Content Page.](./creating-content-page-compositions-using-the-container-fragment/images/14.png)

1. Under the Site Menu, go to *Design* &rarr; *Style Books*.

    ![Open the Style Books page under the Site Menu and Design section.](./creating-content-page-compositions-using-the-container-fragment/images/13.png)

1. Click the *Actions* (![Actions](../../../images/icon-actions.png)) menu for the Style Book corresponding to your Content Page and select *Edit*.

1. On the Style Book editor, select your Content Page from the *Page Preview* drop-down menu.

    ![Select the Content Page you want to modify from the Page Preview drop-down menu in the Style Book editor.](./creating-content-page-compositions-using-the-container-fragment/images/15.png)

1. Select the *Layout* option on the right-hand side drop-down menu, and set a maximum width value (in pixels) for the fixed width elements in the *Extra Large* option.

    ![Change the Extra Large option under the Style Book Layout setting to update the Container Fragment maximum width.](./creating-content-page-compositions-using-the-container-fragment/images/16.png)

1. Click *Publish* to update your Style Book with the new configuration.

## Creating Advanced Compositions with the Container Fragment

> Available: Liferay DXP 7.4+.

When you configure one or more Containers inside another Container Fragment, you can define advanced display options using a *Flex* display.

1. Under the Site Menu, go to *Site Builder* &rarr; *Pages*.

1. [Create a new Content Page](../adding-pages/adding-a-page-to-a-site.md) or open an existing page with a Container-based composition. You can use [this example](#example-using-the-container-fragment-for-a-banner-composition) to create a basic composition using the Container Fragment.

1. Click the *Selection* (![Selection](../../../images/icon-pages-tree.png)) panel.

1. Select the Container Fragment that groups the elements where you want to apply the advanced composition. For example, if you have a Container with Fragments in it, select the outer Container.

1. Under the Styles section, click the *Content Display* drop-down menu and select one of the available *Flex* options.

    ![Select the Container grouping the page elements you want to style using the Flex display options.](./creating-content-page-compositions-using-the-container-fragment/images/17.png)

1. Set the *Align Items* and *Justify Content* settings to your preference.

    ![Set the Align Items and Justify Content settings to your preference.](./creating-content-page-compositions-using-the-container-fragment/images/19.gif)

## Additional Information

- [Content Pages Overview](./content-pages-overview.md)
- [Content Page Editor User Interface Reference](./content-page-editor-user-interface-reference.md)
- [Using Page Fragments](../../displaying-content/using-fragments/using-page-fragments.md)
