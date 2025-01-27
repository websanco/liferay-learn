# Adding Products to a Price List

Once you've created a [Price List](./creating-a-price-list.md), create price entries to add Product SKUs to it. These entries are used when determining a Product's unit price and override an SKU's base price for eligible customers.

Follow these steps to add price entries to a Price List for Product SKUs:

1. Open the *Global Menu* (![Applications Menu icon](../images/icon-applications-menu.png)), click on the *Commerce* tab, and go to *Pricing* &rarr; *Price Lists*.

1. Click on the *Price List* you want to configure, and go to the *Entries* tab.

1. Use the provided search bar to locate the desired products, and then click on *Select* to add them to the selected Price List.

   When searching for products, you can use an SKU or keywords and phrases. Using a keyword or phrase returns all the applicable products by their SKU.

   ![Search for your products by SKU, keyword, or phrase.](./adding-products-to-a-price-list/images/04.png)

1. Click on *Publish* to populate the Price List.

## Commerce 2.1 and Below

Once you have a [price list](./creating-a-price-list.md) created, you can add products to it. This article describes two ways to add products to a price list. Prices that are set via a Price List will take precedence over prices set directly against a given SKU for the associated users.

### Adding Products to a Price List Through the Price Lists Menu

When adding a large number of existing products to a new price list, populate the price list quickly using the following method:

1. Navigate to the _Control Panel_ → _Commerce_ → _Price Lists_.
1. Click on a price list (_VIP Customers_ in this example).
1. Click the _Entries_ sub-tab.
1. Click the (+) button.
1. Choose one or more products (_Premium Brake Fluid_ and _Premium Brake Pads_ in this example) to be added to the price list.
1. Click _Add_.
1. Click on a product (_Premium Brake Fluid_ in this example).
1. Enter the following fields:
    * **Price**: 100
    * **Promo Price**: 95

        ![Adding a product to a price list](./adding-products-to-a-price-list/images/01.png)

1. Click _Save_.

The product is now added to the price list. Repeat as necessary.

### Adding Products to a Price List Through the Products Menu

When creating a new product, you can easily add it to an existing price list using this method:

1. Navigate to the _Control Panel_ → _Commerce_ → _Products_.
1. Click on a product (_Premium Brake Fluid_ in this example).
1. Click the _SKUs_ tab.
1. Click on a SKU (_MIN93017_)
1. Click _Price Lists_.

    ![Adding Products to a Price List in 2.1](./adding-products-to-a-price-list/images/02.png)

1. Click the Add (![Add icon](../images/icon-add.png)) button.
1. Choose one or more price lists (_VIP Customers_ in this example) to which this SKU will be added.
1. Click _Add_.

The price list is now associated with the product.

![Adding Products to a Price List in 2.1](./adding-products-to-a-price-list/images/03.png)

The product (_Premium Brake Fluid_) is now added to the price list (_VIP Customers_). Repeat the steps to add additional products to this price list or another list.

## Additional Information

* [Creating a Price List](./creating-a-price-list.md)
* [Using Price Tiers](./using-price-tiers.md)
