# Configuring Menu Displays 

To display and configure a [Menu Display](./using-the-navigation-menus-application.md), you can use the [Menu Display Fragment](../creating-pages/building-and-managing-content-pages/page-fragments-user-interface-reference.md#menu-display) (available in Liferay DXP 7.4+) or Menu Display Widget. With any of these two options, you can define the menu display options and create different navigation levels for your Site.

```{note}
Configuring the Menu Display Fragment is easier than the Menu Display Widget, and you have preview options working with [Master Page Template](../creating-pages/defining-headers-and-footers/creating-a-master-page-template.md) that Menu Display Widgets do not provide.
```

## Configuring the Menu Display Fragment

> Available: Liferay DXP/Portal 7.4+.

Using Liferay Navigation Menus application, you can create a navigation menu anywhere in your Content Page. This navigation menu is based on your Site's Public or Private Pages hierarchy, or any other hierarchy you define using Liferay's [Navigation Menus Application](./using-the-navigation-menus-application.md). You can also define the menu display style options and the number of sub-levels to include in the navigation.

The Menu Display Fragment appears under the Fragments and Widget section of your Content Page. You add and configure this Fragment as with other Fragments in your Content Pages. For more information, see [Adding Elements to Content Pages](../creating-pages/using-content-pages/adding-elements-to-content-pages.md) and [Configuring Fragments](../creating-pages/page-fragments-and-widgets/using-fragments/configuring-fragments.md).

![The Menu Display Fragment appears under the Fragments and Widget section of your Content Page.](./configuring-menu-displays/images/06.png)

You can build the hierarchy of pages and subpages in the Menu Display Fragment using two methods: 

- **Site Navigation Menus** (A) uses the menus you define using the Liferay DXP Navigation Menu for [Site navigation](./managing-site-navigation.md). By default, Liferay DXP provides two Site Menus based on the Public Pages and Private Pages hierarchies in your Site.
- **Contextual Menus** (B) shows a menu that is based on the Page context. For example, you can create a more advanced navigation by combining a first-level Menu Display Fragment linked to one of your Site Menus with a second-level Menu Display Fragment that only shows the child Pages. This way, when the user clicks through the different Pages in the first-level menu, the second-level menu changes dynamically to show the children Pages.

![You can define the hierarchy of the Menu Display Fragment from your Site Menus or from Contextual Menus.](./configuring-menu-displays/images/05.png)

## Configuring the Menu Display Widget

Below are all the options possible in the Menu Display widget. To access the Configuration menu, click ![Options icon](../../images/icon-widget-options.png) at the top of the Menu Display widget.

![Navigate to the Configuration menu.](./configuring-menu-displays/images/01.png)

### Navigation Menu

Select the desired navigation menu type; the desired navigation menu type appears in the Preview box.

| Navigation Menu Type | Description |
| :--- | :--- |
| **Public Pages Hierarchy** | Public Pages Hierarchy is based on the default hierarchy in the Site Builder. |
| **Primary Navigation** | Primary Navigation is the main navigation for a page. |
| **Secondary Navigation** | Secondary Navigation is a second level of navigation, possibly a sidebar or a separate menu within a page. |
| **Social Navigation** | Social Navigation is for menus that contain links for sharing content on social media or similar tasks. |

Alternately, you can choose a navigation menu by clicking the _Select_ button.

![Select the type of Navigation Menu.](./configuring-menu-displays/images/02.png)

### Display Templates

You can select a [Widget Template](../displaying-content/additional-content-display-options/styling-widgets-with-widget-templates.md) for navigation menus:

![Select the Display Template.](./configuring-menu-displays/images/03.png)

| Display Template | Description |
| :--- | :--- |
| **List Menu** | Displays all the items in a vertical list. |
| **Pills Horizontal** | Displays the items horizontally and uses a button style for highlighting. |
| **Pills Justified** | Like Pills Horizontal, but pads the items to fill out the horizontal space. |
| **Pills Stacked** | A vertical version of the pills style. |
| **Tabs** | Displays the items like navigation tabs. |
| **Tabs Justified** | Navigation tabs that fill horizontal space. |
| **Bar Minimally Styled** | A lightweight version of the default display that you see in the embedded menu on your page. |
| **Bar Minimally Justified Styled** | Like Bar Minimally Styled with horizontal padding. |
| **Bar Default Styled** | The default embedded menu. |
| **Split Button Dropdowns** | Displays each item as a button with a dropdown  for multiple navigation levels. |

### Menu Items to Show

You can configure which pages at what level from the menu appear in the widget. You can choose the starting level, how many levels deep to display, and how to display sub-levels.

![Select the Menu Items to Show.](./configuring-menu-displays/images/04.png)

| Field | Description |
| :--- | :--- |
| **Start with Menu Items In** | Select the type of Level: _Level_, _Level Relative to the Current Menu Item_, or _Select Parent_.  |
| **Number of Levels** | Select the level number to display, from 0 to 4. Level 0 represents the parent page, Level 1 represents the first level child page, and so forth. |
| **Levels to Display:** | Select the number of levels to display in the navigation, from 1 down to Unlimited. |
| **Expand Sublevels:** | Select _Auto_ or _All_ from this dropdown menu; this determines if hovering the mouse over the navigation reveals navigation levels one at a time automatically or all the levels at once. |

## Additional Information

- [Using the Navigation Menus Application](./using-the-navigation-menus-application.md)
- [Managing Site Navigation](./managing-site-navigation.md)
- [Adding Elements to Content Pages](../creating-pages/building-and-managing-content-pages/adding-elements-to-content-pages.md)
- [Content Page Editor User Interface Reference](../creating-pages/building-and-managing-content-pages/content-page-editor-user-interface-reference.md)

