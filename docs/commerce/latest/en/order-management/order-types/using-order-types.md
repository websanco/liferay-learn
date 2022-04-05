# Using Order Types

To support the example scenario of stock and urgent orders, you can create two Order Types. Stock orders are those that have regular pricing for the buyer whereas urgent orders are those that incur a surcharge.

```{important}
For orders created using the *Add Order* or *Create New Order* button, buyers can choose the Order Type from a dropdown menu. This updates the product page with the correct prices linked to that Order Type, if any.
```

## Creating an Order Type

If there is only one Order Type created, it is automatically assigned to a new order. For Products added to the cart directly, the Order Type with the lower Order number takes precedence over the others.

1. Open the *Global Menu* (![Applications Menu icon](../../images/icon-applications-menu.png)) and click on *Commerce* &rarr; *Order Types*.
1. Click the *Add* button(![Add icon](../../images/icon-add.png)), and enter the following information.

    **Name:** Stock

    **Description:** Order Type for all regular orders

1. Click *Save*.

    This creates the new Order Type and opens a configuration page. The *Order Number* field determines the sorting order of the Order Type in the dropdown menu. Set the value for the Order Number as 0 for the Stock Order Type. Create** one more Order Type with the following information.

    **Name:** Urgent

    **Description:** Order type for urgent orders that is subject to a 10% surcharge

Set the value for the Order Number as 1 for this Order Type. You can use the *Active* toggle to enable it. Under the *Eligibility* tab, you can link the Order Type to a specific Channel or all Channels.

## Linking an Order with an Order Type

You can create a custom Price List and link it to the Urgent Order Type. To add a surcharge, you can create a Price Modifier on the Price List.

1. Open the *Global Menu* (![Applications Menu icon](../../images/icon-applications-menu.png)), and click on *Commerce* &rarr; *Price Lists*.
1. Click the *Add* button(![Add icon](../../images/icon-add.png)) to create a new Price List and enter the following information.

    **Name:** Urgent Order Surcharge

    **Catalog:** Minium

    **Currency:** USD

    ![Enter the name, catalog, and default currency and click Submit to create the Price List.](./using-order-types/images/01.png)

1. Click *Submit*.
1. Under the Price Modifiers tab, click the *Add* button(![Add icon](../../images/icon-add.png)) to create a new Price Modifier and enter the following information.

    **Name:** 10% Surcharge

    **Target:** Catalog

    **Modifier:** Percentage

    ![Enter the name, target, and modifier and click Submit to create the Price Modifier.](./using-order-types/images/02.png)

1. Click *Submit*.
1. Click on the new Price Modifier and activate it using the *Active* toggle and set the *Amount* to 10.
1. Click *Save*.

    This creates the new Price List with the Price Modifier. You must link it to the Order Type to add the surcharge.

1. Click the *Eligibility* tab and scroll down to *Order Type Eligibility*.
1. Search for the Urgent Order Type and click *Select*.
1. Click *Publish*.

    ![Based on the Order Type, Products in the catalog automatically display their correct prices.](./using-order-types/images/03.gif)
