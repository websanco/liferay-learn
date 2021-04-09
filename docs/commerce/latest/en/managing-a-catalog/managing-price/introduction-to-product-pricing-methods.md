# Introduction to Product Pricing Methods

Liferay Commerce provides a variety of pricing methods that you can use to set different prices for a product that are applied under specified conditions. Most of these methods apply prices within a [pricing hierarchy](#pricing-hierarchy), where some prices supersede others. Though you can also apply *discounts* to modify a product's unit or promo price without superseding it.

## Pricing Hierarchy

Commerce's pricing hierarchy determines the price applied to a product based on a variety of specified factors (e.g, the combination of products, product quantity, buyer profile, or order total). If more than one price applies to a given transaction, then the price with the highest ranking supersedes the others.

The hierarchy consists of the following levels:

**Base Price**: This is the starting price of the product which is [set](./setting-a-products-base-price.md) in the product SKU sub-tab. Base prices are stored in the catalogs Base Price List generated at catalog creation. It serves as the standard price for the product when no overrides (e.g., other Price Lists or Promotions) are applied to it.

**Custom Price List**: Prices in a custom Price List can be *higher* or *lower* than the product's base price and are applies to specified products for buyers in selected [Accounts](../../account-management/creating-a-new-account.md) and [Account Groups](../../account-management/creating-a-new-account-group.md). If a custom price list applies to a transaction, it supersedes the base price. See [Creating a Price List](./creating-a-price-list.md) for more information.

**Tiered Price**: This price applies to orders that meet specified minimum quantity requirements. It is only available in the context of a [Price List](./creating-a-price-list.md) and supersedes the list's price. See [Adding Tiered Pricing](./adding-tiered-pricing.md) for more information.

**Promo Price**: This price can be applied to base prices, list prices, or tiered prices and supersedes the price to which it is applied. However, if a price is superseded by another list or tiered price, the promo price is also superseded. When active, both the original and promo price appear together on the product page so that buyers see the markdown.

**Discount**: Discounts operate outside the price hierarchy and modify prices instead of superseding them. They are applied on top of the best price, whether unit or promo. See [Introduction to Discounts](./../../promoting-products/introduction-to-discounts.md) to learn more.

The following diagram visualizes the pricing hierarchy:

   ![Pricing hierarchy](./introduction-to-product-pricing-methods/images/01.png)

## Pricing Method Reference

| Pricing Method | Summary | Overrides the Base Price? | Where is it set? | Applies to who? | Applies to what? |
| --- | --- | :---: | --- | --- | :---: |
| Base Price | The base price | n/a | Product SKU | All buyers | Product SKU |
| Base Promo | Marked down price | Yes | Product SKU | All buyers | Product SKU |
| Price List (List Price, List Promo) | Special pricing (or currencies) per product and buyer | Yes | Price Lists | Selected buyers (Accounts & Account Groups) | Individual product SKUs |
| Tiered Price List (Tiered Price, Tiered Promo) | Special pricing (or currencies) per product and buyer _at bulk quantities_ | Yes | Price Lists | Selected buyers (Accounts & Account Groups) | Individual product SKUs |
| Discount | Modifies price for a group of products or buyers (Can limit quantity and use coupon codes) | No | Discounts | Selected Buyers (Accounts & Account Groups or those who meet certain qualifications) | Groups of products (or Individual product SKUs) |

## Additional Information

* [Setting a Product's Base Price](./setting-a-products-base-price.md)
* [Creating a Price List](./creating-a-price-list.md)
* [Adding Products to a Price List](./adding-products-to-a-price-list.md)
* [Adding Tiered Pricing](./adding-products-to-a-price-list.md)
* [Creating a Discount](../../promoting-products/creating-a-discount.md)
