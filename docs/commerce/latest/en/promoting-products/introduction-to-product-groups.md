# Introduction to Product Groups

Product Groups in Liferay Commerce are static groups of products that can be used for targeted discounts or pricing. Product Groups can be useful in two scenarios:

1. If you have products that are not part of a single category and want them to be priced differently for specific customers.

1. If you have products that are not part of a single category and want them to be discounted differently for specific customers.

```{note}
Product Groups don’t change the way Products are grouped or displayed for the buyer and are used on the administrative side. 
```

## Creating a Product Group

To create a Product Group, follow these steps.

1. Open the Applications Menu ![Applications Menu](../images/icon-applications-menu.png), click on the _Commerce_ tab, and go to _Pricing_ &rarr; _Product Groups_.

1. Click the Add (![Add](../images/icon-add.png)) button and enter a name and description for the Product Group.

1. Click _Submit_.

This creates a Product Group and we must now add products to it.

## Adding Products to a Product Group

Once the Product Group is created, you must add products to it. In this use case, we select two products and add them to the Product Group so that they can be used for targeted pricing or discounts.

1. Use the _Add Products_ search bar to find the products you want to add. The options are displayed automatically based on what you type.

2. Click _Select_ that is present next to the product of your choice to add them to the group.

This Product Group can now be linked to a _Price List_ or _Discount_.

## Linking a Discount to a Product Group

Let's create a new discount to target our Product Group.

1. Click the Applications Menu, click on the _Commerce_ tab, and go to _Pricing_ &rarr; _Discounts_.

1. Click the Add (![Add](../images/icon-add.png)) button and enter the following information.

    __Name__: Example Product Group Discount
    __Type__: Percentage
    __Apply To__: Product Groups

1. Click _Submit_.

This creates a new Discount and can be applied to Product Groups.

In the Details page of the Discount, you can alter the type, add an amount, set the active status and level for your discount. Read Introduction to Discounts for more information. Once the discount is created, we need to link it to the Product Group we created earlier.

1. Scroll down to the Select Product Group section on the Discount's Details page.

1. Type the name of the Product Group we created earlier. It automatically populates the results based on what you type.

1. Click Select that is present next to the Product Group. Once added, click Publish to activate the discount for your Product Group.

You can validate it by checking the Product in your catalog to view the discount.

## Linking a Price List to a Product Group

To link a Price List to a Product Group, you need to first create a Price List and then create a Price Modifier that targets the Product Group you created. Follow the steps below to do so.

1. Open the Applications Menu ![Applications Menu](../images/icon-applications-menu.png), click on the _Commerce_ tab, and go to _Pricing_ &rarr; _Price Lists_.

1. Click the Add (![Add](../images/icon-add.png)) button, and enter a name, catalog and currency for your Price List.

1. Click _Submit_ when done.

1. Click the Add (![Add](../images/icon-add.png)) button under the Price Modifiers section and enter the following information.

    __Name__: Wheels Modifier
    __Target__: Product Groups
    __Modifier__: Percentage/Replace/Fixed Amount

    This creates the Price Modifier and open up a more detailed view to configure it.

    ```{note}
    The amount mentioned in a Price Modifier is not considered as a discount. For instance, if you select the Percentage modifier and set the amount to 10, it raises the price of the product by 10%. To reduce the price, use a negative percentage.
    ```

1. Under the Product Groups section, search for the Product Group that you created. It auto-populates the results based on what you type.

1. Click Select that is present next to the Product Group. Once added, click Publish to activate the price list for your Product Group.

To view whether a discount or price list has been linked to a Product Group, click the _Applications Menu_ ![Applications Menu](../images/icon-applications-menu.png), click on the _Commerce_ tab, and go to _Pricing_ &rarr; _Product Groups_. Under the Price Lists section and the Discounts section, you should be able to view the associated entities.

## Additional Information

* [Introduction to Discounts](./introduction-to-discounts.md)
* [Introduction to Pricing](../managing-a-catalog/managing-prices/introduction-to-pricing.md)
* [Creating a Price List](../managing-a-catalog/managing-prices/creating-a-price-list.md)
