# Creating a Catalog Page

The [Catalog](./commerce-storefront-pages/catalog.md) page is the primary way for customers to browse, search, and select Channel Products. The following widgets are recommended for the Catalog page: _Search Bar_, _Options Facets_, _Specification Facet_, _Category Facet_, _Sort_, and _Search Results_. To learn more about these and other Commerce widgets, see [Widget Reference](./liferay-commerce-widgets/widget-reference.md).

This article documents an example of how to build a Catalog page without using an [accelerator](../starting-a-store/accelerators.md).

## Prerequisites

See the following links to learn more about Site and Page Creation:

* [Creating Page Fragments](https://help.liferay.com/hc/en-us/articles/360018171331-Creating-Page-Fragments)
* [Building Content Pages from Fragments](https://help.liferay.com/hc/en-us/articles/360018171351-Building-Content-Pages-from-Fragments-)
* [Creating and Managing Navigation Menus](https://help.liferay.com/hc/en-us/articles/360018171531-Creating-and-Managing-Navigation-Menus)
* [Using Application Display Templates](https://help.liferay.com/hc/en-us/articles/360017892632-Styling-Widgets-with-Application-Display-Templates)

## Add and Configure the Page

1. Create a Page using the _Widget_ template.
1. The _30-70 Column_ Layout is the default selection. This layout has a narrower left column for the widgets that refine the search and a wider right column for the _Search Results_ widget. Storefront designers can choose different layouts for a different style.

## Add the Widgets to the Page

1. Drag and drop the widgets into the desired positions.
1. Click the _3-dot icon_ then _Look and Feel Configurations_ to add or remove the widget titles.

After creating and configuring the page with the widgets, a basic Catalog page might look like this:

![Blank Catalog Page](./creating-a-catalog-page/images/02.png)

## Configure the _Search Bar_ Widget

The _Search Bar_ widget must be configured to display data from the Global site scope. This is because all products are stored at the global level and not the site level.

1. Click on the _Actions_ button (![Actions Button](../images/icon-actions.png)) for the Search Bar widget, and select _Configuration_.

    ![Click on the Actions button for the Search Bar widget, and select Configuration.](./creating-a-catalog-page/images/03.png)

1. Set the widget's Scope to _Everything_.

   This is essential because Products are created in Catalogs at the global level and are not Site-scoped.

    ![Set the widget's Scope to Everything.](./creating-a-catalog-page/images/04.png)

1. Click _Save_.

The _Search Bar_ widget is now configured to display all Channel content.

## Displaying the Full Catalog Without a Search (Optional)

Liferay Commerce allows store managers to display all the products in a catalog in the _Search Results_ widget without having buyers first enter a search query. This is done by configuring the _Search Options_ widget.

1. Click the _Configure additional search options in this page_ in the _Search Options_ widget.
1. Check the checkbox for _Allow Empty Searches_.
1. Click _Save_.
1. Close the _Configuration_ window.

Once this option has been enabled, all the products in this catalog are displayed in the _Search Results_ widget.

![Empty Search](./creating-a-catalog-page/images/01.png)

## Configure Product Channel Filters (Optional)

If there is more than one store site that is housed on the Liferay Commerce instance, the entire catalog becomes searchable on every store site.

To scope product to specific sites, see [Configuring Product Visibility Using Channels](../starting-a-store/channels/configuring-product-visibility-using-channels.md).
