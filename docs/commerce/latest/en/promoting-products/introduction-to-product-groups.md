# Introduction to Product Groups

Product Groups are static groups of products you can use for targeted discounts or pricing. Product Groups are ideal in two scenarios:

1. If you have products that are not part of a single category and want them to be priced differently for specific customers.

1. If you have products that are not part of a single category and want them to be discounted differently for specific customers.

```{note}
Product Groups don’t change the way Products are grouped or displayed for the buyer and are used for administrative purposes. 
```

## Creating a Product Group

1. Open the Applications Menu ![Applications Menu](../images/icon-applications-menu.png), click on the _Commerce_ tab, and go to _Pricing_ &rarr; _Product Groups_.

1. Click _Add_ (![Add](../images/icon-add.png)) and enter a name and description for the Product Group.

    ![Enter details for the Product Group.](./introduction-to-product-groups/images/01.png)

1. Click _Submit_.

This creates a Product Group. 

## Adding Products to a Product Group

Once the Product Group is created, you must add products to it. Below you'll select two products and add them to the Product Group so they can be used for targeted pricing or discounts.

1. Use the _Add Products_ search bar to find the products you want to add. The options appear automatically based on what you type.

   ![Search for products to be added to the Product Group.](./introduction-to-product-groups/images/02.png)

1. Click _Select_ next to the product of your choice to add it to the group.

This Product Group can now be linked to a _Price List_ or _Discount_.

```{note}
Apart from adding Products to a Product Group, you can also link a Product Group to a Product. To do this, select the Product of your choice from the Products list, select the Product Groups tab, and search for the Product Group you created. Click *Select* next to the Product Group of your choice to link the Product Group with the Product.
```

## Linking a Discount to a Product Group

1. Click the Applications Menu &rarr; _Commerce_ tab, and go to _Pricing_ &rarr; _Discounts_.

1. Click _Add_ (![Add](../images/icon-add.png)) and enter the following information:

    __Name__: Example Product Group Discount

    __Type__: Percentage

    __Apply To__: Product Groups

    ![Enter details for a Discount.](./introduction-to-product-groups/images/03.png)

1. Click _Submit_.

    This creates a new Discount and can be applied to Product Groups.

    In the Details page of the Discount, you can alter the type, add an amount, and set the active status and level for your discount. Read [Introduction to Discounts](./introduction-to-discounts.md) for more information. Once the discount is created, you must link it to the Product Group you created earlier.

1. Scroll down to the Select Product Group section on the Discount's Details page.

    ![Search for the Product Group to be added to the Discount.](./introduction-to-product-groups/images/04.png)

1. Type the name of the Product Group you created earlier. It automatically populates the results based on what you type.

1. Click _Select_ next to the Product Group. Once added, click _Publish_ to activate the discount for your Product Group.

You can validate it by checking the Product in your catalog to view the discount.

![The discount is applied to the product in the Product Group.](./introduction-to-product-groups/images/05.png)

## Linking a Price List to a Product Group

To link a Price List to a Product Group, you must first create a Price List and then create a Price Modifier that targets the Product Group you created.

1. Open the Applications Menu ![Applications Menu](../images/icon-applications-menu.png), &rarr; _Commerce_ tab and go to _Pricing_ &rarr; _Price Lists_.

1. Click _Add_ (![Add](../images/icon-add.png)) and enter a name, catalog, and currency for your Price List.

    ![Configure the Price List.](./introduction-to-product-groups/images/06.png)

1. Click _Submit_ when done.

1. Click _Add_ (![Add](../images/icon-add.png)) under the Price Modifiers section and enter the following information.

    __Name__: Wheels Modifier

    __Target__: Product Groups

    __Modifier__: Percentage/Replace/Fixed Amount

    ![Configure the Price Modifier.](./introduction-to-product-groups/images/07.png)

1. Click *Submit* when done.

    ![Detailed configuration view of the Price Modifier.](./introduction-to-product-groups/images/08.png)

    This creates the Price Modifier and opens a more detailed view to configure it.

    ```{note}
    The amount mentioned in a Price Modifier is not considered as a discount. For instance, if you select the Percentage modifier and set the amount to 10, it raises the price of the product by 10%. To reduce the price, use a negative percentage.
    ```

     ```{important}
    If there are two Price Lists that target the same product, you must create the new price list with a higher priority for the changes to take effect.
    ```

    ![Search for the Product Group to be added to the Price Modifier.](./introduction-to-product-groups/images/09.png)

1. Under the Product Groups section, search for the Product Group you created. It auto-populates the results based on what you type.

1. Click _Select_ next to the Product Group. Once added, click _Publish_ to activate the price list for your Product Group.

You can validate it by checking the product in your catalog to view the updated price.

![The Price Modifier is applied to the product in the Product Group.](./introduction-to-product-groups/images/10.png)

To view whether a discount or price list has been linked to a Product Group, click the _Applications Menu_ ![Applications Menu](../images/icon-applications-menu.png), click the _Commerce_ tab, and go to _Pricing_ &rarr; _Product Groups_. Under Price Lists and Discounts, you can see the associated entities.

## Additional Information

* [Introduction to Discounts](./introduction-to-discounts.md)
* [Introduction to Pricing](../managing-a-catalog/managing-prices/introduction-to-pricing.md)
* [Creating a Price List](../managing-a-catalog/managing-prices/creating-a-price-list.md)
* [Adding Products to a Price List](../managing-a-catalog/managing-prices/adding-products-to-a-price-list.md)
