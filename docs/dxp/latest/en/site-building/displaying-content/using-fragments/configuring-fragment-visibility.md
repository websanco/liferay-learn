# Configuring Fragment Visibility

> Available: Liferay DXP 7.4+.

[Fragments](../using-fragments/using-page-fragments.md) allow you to customize the layout and information on your [Content Pages](../../creating-pages/building-and-managing-content-pages/content-pages-overview.md). Starting with Liferay DXP 7.4, you can configure the Fragment's visibility based on the screen size the client uses to access the Page. This way, you can adapt your content to each platform and improve the user experience.

You configure the Fragment visibility for each screen size from the Content Page editor, using the Viewport control on the top of the Page.

![Configure the Fragment's visibility on your Content Page using the Viewports control.](./configuring-fragment-visibility/images/01.png)

The Fragment's visibility and styles you apply to one viewport on the left are applied to all the viewports on the right, unless you specify different styles for one of the right-side viewports. So, for example, if you hide a Fragment on the Desktop viewport, the Fragment is also hidden on the mobile viewports. But if you make the Fragment visible on one mobile viewport, it remains visible on that viewport. From a user experience perspective, in this example, the Fragment is hidden when accessing the Page from a computer, but it's visible when using a smartphone.

This tables summarizes each viewport's behavior:

| Viewport | Description |
| --- | --- |
| Desktop | It's the default viewport. The Fragment styles and visibility you define here apply to all other viewports, unless you specify another style or visibility in another viewport. |
| Tablet | It's the viewport for tablet-size screens. The Fragment styles and visibility you define here apply to the phone viewports, unless you specify a style or visibility in the landscape or portrait phone viewports. |
| Landscape Phone | The Fragment styles and visibility you define on this viewport apply to portrait phone viewport as well, unless you specify another style or visibility in the portrait phone viewport. |
| Portrait Phone | This viewport inherits the style and visibility changes you make in any other viewports, unless you specify another style or visibility here. |

## Changing Fragment Visibility

You can change the Fragment visibility on your Content Pages in three different ways:

1. From the Content Page editor sidebar, under the *Browser* (![Browser](../../../images/icon-cursor.png)) &rarr; *Page Elements* area, click the *Hide* (![Hide](../../../images/icon-hide.png)) or *Unhide* (![Unhide](../../../images/icon-preview.png)) control next to the Page Fragment.

      ![Changing the Fragment's visibility from the Page Elements area in the Content Page editor sidebar.](./configuring-fragment-visibility/images/03.gif)

1. From the Content Page editor sidebar, under the *Browser* (![Browser](../../../images/icon-cursor.png)) &rarr; *Styles* area, check or uncheck the *Hide Fragment* box:

      ![Changing the Fragment's visibility from the Page Elements area in the Content Page editor sidebar.](./configuring-fragment-visibility/images/02.gif)

1. From the Fragment's Actions menu (![Actions](../../../images/icon-widget-options.png)), select the *Hide Fragment* (![Hide Fragment](../../../images/icon-hide.png)) option:

      ![Changing the Fragment's visibility from the Page Elements area in the Content Page editor sidebar.](./configuring-fragment-visibility/images/04.gif)

```{tip}
To restore a Fragment you have hidden, from the Browser Page Elements area, click the *Hide* (![Hide](../../../images/icon-hide.png)) or *Unhide* (![Unhide](../../../images/icon-preview.png)) control next to the Page Fragment.
```

## Example: Using Fragment Visibility

Consider this example. Your insurance company wants to promote a new mobile app that lets customers manage their insurance policies. You want to promote this app when users access your Page from a smartphone. In this case, you can create a promotional banner for your new mobile app and make this banner visible only on the Smartphone viewports.

To do so,

1. From the default Desktop viewport, add a new Fragment with your promotional text.
1. Hide the Fragment with your promotional text. The Fragment is now hidden in all viewports.
1. Click the *Landscape Phone* viewport. Changes on this viewport affect the Portrait Phone viewport as well.
1. [Change the Fragment visibility](#changing-fragment-visibility) to unhide your promotional text in the Phone viewports.
1. Click on the different viewports to see the effect. The promotional text is now available in the Landscape Phone and Portrait Phone viewports only.

The following video illustrates this example:

![Changing a Fragment's visibility for a specific viewport in the Content Page editor.](./configuring-fragment-visibility/images/05.gif)

```{tip}
To restore the default viewport visibility, click on the viewport and click the Restore Default (![Restore Default](../../../images/icon-restore.png)) button under the Fragment Styles area:

![Restore the default viewport Fragment visibility using the Restore Default button.](./configuring-fragment-visibility/images/06.png)
```

## Related Information

- [Managing Page Fragments](./managing-page-fragments.md)
- [Creating Content Page Compositions Using the Container Fragment](../../creating-pages/building-and-managing-content-pages/creating-content-page-compositions-using-the-container-fragment.md)
- [Page Fragments User Inteface Reference](../../creating-pages/building-and-managing-content-pages/page-fragments-user-interface-reference.md)
