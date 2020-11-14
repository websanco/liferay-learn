# Gross and Net Price Calculations

Liferay Commerce allows users use either the gross price or the net price as the basis to calculate promotions and [discounts](../../promoting-products/introduction-to-discounts.md). Configuring Gross or Net Price Type is done at the [Price List](../creating-a-price-list.md) level.  

## Calculations

If the price list contains gross price entries:

* The product price calculation engine calculates the net price by subtracting the taxes from the gross price.
* The net price is then used to calculate the unit price.
* Other price modifiers are then applied.

If the promotion contains gross price entries:

* The product price calculation engine calculates the net price by subtracting the taxes from the gross price
* The net price is then used to calculate the unit promo price
* Other price modifiers are then applied


## Discounts

 If discount amount is gross, then the net amount is calculated and applied to the net price of the product.

If discount amount is gross:

* Discount is a fixed amount: the taxes are removed from the discount amount before applying the discount calculation algorithm
* Discount is a percentage: the net percentage is recalculated before applying the discount. N = L - T(100-L) with T in %

Taxes are calculated on the final price

## Calculations When an Item is Added to a Cart



## Additional Information

* [Setting Gross and Net Price](./setting-gross-and-net-price.md)
* [Introduction to Discounts](../../promoting-products/introduction-to-discounts.md)
* [Creating a Price List](../creating-a-price-list.md)
* [Managing Channels](../../starting-a-store/channels/managing-channels.md)
