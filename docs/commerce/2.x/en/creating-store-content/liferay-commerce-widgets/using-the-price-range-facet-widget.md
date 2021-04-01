# Using the Price Range Facet Widget

Liferay Commerce includes the *Price Range Facet* widget for quickly filtering Product search results by price. To use this widget, ensure the Commerce Search Results widget is also deployed to the desired page.

```note::
   Starting with Liferay DXP 7.3 SP2, the Price Range Facet widget uses a Product's lowest price for filtering results.

   For earlier Commerce versions, the widget uses a Product's base price and only works with Products that have a single SKU. Products with multiple SKUs are not displayed when the facet is active.
```

![Use the Price Range Facet widget to filter Channel Products by price.](using-the-price-range-facet-widget/images/01.png)

Once you've added the Price Range Facet widget to a page, you can configure its fields:

1. Hover over the widget, click on the *Actions* button (![Actions Button](../../images/icon-actions.png)), and select *Configuration*.

   ![Configure the widget.](./using-the-price-range-facet-widget/images/02.png)

1. In the *Setup* tab, configure the following fields:

   **Show Input Range**: Determine whether the widget allows customers to enter a custom price range.

   **Ranges JSON Array**: Set predefined price ranges for the widget.

   ![Configure the widget's fields in the Setup tab.](using-the-price-range-facet-widget/images/03.png)

1. Click on *Save*.

See [Widget Reference](../liferay-commerce-widgets/widget-reference.md) to learn about other Commerce widgets.
