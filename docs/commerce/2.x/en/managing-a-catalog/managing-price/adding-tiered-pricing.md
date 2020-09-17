# Adding Tiered Pricing

Tiered pricing allows store owners to vary the price of a product based on the quantity ordered. A store could offer reduced prices for higher quantities ordered. Prices that are set via Tiered Pricing will take precedence over prices set directly against a given SKU for the associated users if a buyer meets the minimum quantity specified. This article describes how to add tiered pricing to an existing price list.

To create a pricing tier for a product in a price list:

1. Go to the _Global Applications_ menu &rarr; _Commerce_ &rarr; _Price Lists_.
1. Click on a price list (_VIP Customers_ in this example).
1. Click the _Entries_ tab.
1. Click on a product (_Premium Brake Fluid_ in this example).
1. In the _Price Tiers_ section, click the _Tiered Pricing_ radio button.
1. Click the (![Add icon](../../images/icon-add.png)) button.
1. Enter the following:
    * **Quantity**: 20 (This is the minimum quantity needed to receive the price for bulk quantity.)
    * **Tier Price**: 90.00
    * **Override Discount**: Yes
    * **Discount Levels**: 1
    * **Publish Date**: Enter a date and time.

    ![Add a tiered pricing entry.](./adding-tiered-pricing/images/02.png)

1. Click _Submit_ when finished.
1. Close the _Add New Price Tier_ window.
1. Click the _Save_ button in the _Edit Price_ window.

    ![Add a tiered pricing entry.](./adding-tiered-pricing/images/03.png)

The new price tier has been added; this is a Level 1 discount for buyers who have been offered this price list and have purchased more than 20 quantity at a price $90 USD.

## Commerce 2.1 and Below

1. Navigate to the _Control Panel_ → _Commerce_ → _Price Lists_.
1. Click on a price list (_VIP Customers_ in this example).
1. Click the _Entries_ sub-tab.
1. Click on a product (_Premium Brake Fluid_ in this example).
1. Click the _Tier Price Entries_ sub-tab.
1. Click the (![Add icon](../../images/icon-add.png)) button.
1. Enter the following fields (example values shown below):
    * **Price**: 90
    * **Promo Price**: (leave it blank)
    * *Minimum Quantity*: 20 (This is the minimum quantity needed to receive the price for bulk quantity.)

        ![Add a tiered pricing entry](./adding-tiered-pricing/images/01.png)

1. Click Save.

A pricing tier ($90) has been created for orders that reach the minimum quantity (20) for the product (_Premium Brake Fluid_) on the price list (_VIP Customers_). Repeat the steps to add successive price tiers to the same product and price list (for other minimum quantities), or to add a price tier to another product and price list.

## Additional Information

**Note**: Price Tier Entries can also be reached by directly viewing Product SKUs.

* [Creating a Price List](./creating-a-price-list.md)
* [Adding Products to a Price List](./adding-products-to-a-price-list.md)
