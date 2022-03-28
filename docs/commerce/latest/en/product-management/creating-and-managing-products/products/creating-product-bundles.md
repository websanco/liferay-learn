# Creating Product Bundles

Liferay Commerce supports the creation of *Product bundles* using [Product Options](./using-product-options.md). With Options, you can define values that link the current Product with other SKUs, whether Simple, Virtual, or Grouped. Once defined, customers can select different Option values when adding the Product to their cart.

For example, consider a computer hardware distributor. Customers can select a specific computer model with a standard case, power supply, motherboard, and OS system, but they must choose between other hardware components (e.g., CPU, GPU, RAM, storage). Each Product variant has its own price based on the selected components. The total cost is calculated by adding the price of the selected value to the price of the current Product.

```{note}
In addition to Product bundles, Liferay Commerce provides Grouped Products for combining multiple SKUs for sale. This Product type groups SKUs for customer convenience or special promotions. Unlike Product bundles, each Grouped Product has its own SKU, and its final cost is determined by its own price entry. See [Creating a Grouped Product](../product-types/creating-a-grouped-product.md) for more details.
```

Follow these steps to create a Product bundle:

1. Open the *Global Menu*, click on the *Commerce* tab, and go to *Product Management* &rarr; *Products*.

1. Click on the *Product* you want to use as the base Product for the bundle and go to the *Options* tab.

1. In the *Add Options* field, enter a *name* for your new bundle Option, and click on *Create New*.

   A new Option template is created and applied to your Product using the default Option settings.

   ```{tip}
   Alternatively, you can create an Option Template via the *Options* page in the Commerce tab of the Global Menu. This method is best for commonly used types of bundles. See [Using Product Options](./using-product-options.md) for more information.
   ```

   ![Go to the Product's Options tab, and create a new Option for your bundle.](./creating-product-bundles/images/01.png)

1. Click on the new *Option* to edit its details and values.

1. Configure the following Option settings:

   * **Description** (Optional): Add a localized *description*.
   * **Position**: Set the Option's priority to determine the order in which it's displayed relative to other Options.
   * **Use in Faceted Navigation**: Determine whether the Option can be used to filter Product search results.
   * **Required**: Determine whether users must select an Option value before adding the Product to the cart.
   * **SKU Contributor**: Determine whether the Option's values are used to generate unique Product SKUs.
   * **Field Type**: Choose the type of field used for the Option.
   * **Price Type**: Choose whether to use *Static* or *Dynamic* pricing for Option values.

      ```{note}
      *Dynamic* uses the link SKU's price for a value, while *Static* uses a fixed price set directly in the value. Dynamic and Static prices are added to the current Product's base price.

      Also, since Product bundles use the *Price Type* attribute, they can only use the *Single Selection* and *Select from List* field types.
      ```

   ![This form is used to configure the Option's settings.](./creating-product-bundles/images/02.png)

1. Scroll down to Values, click the *Add* button (![Add Button](../../../images/icon-add.png)), and enter the following details for your value:

   * **Name**: Set the display name used for the value. If SKU Contributor is enabled, this name is used for the value's SKU.
   * **Position**: Set the value's priority to determine its placement in the Option's field.
   * **Key**: This field is auto-generated to match the Name field, though you can set it independently.

   Repeat this process until you've added all the desired values.

   ![Use this form to add values to the created Option.](./creating-product-bundles/images/03.png)

1. Once created, click on a value to access these additional settings:

   * **Default**: Determine whether the Option defaults to the selected value.
   * **Delta Price** (For Static Only): Set a specific price for the value.
   * **Product**: Link an existing SKU to the value and specify its quantity.

   ```{important}
   Each of the Option's values must be unique. This means the same Product+quantity combination cannot be used for multiple values in the same Option. However, the same value can be used in other Options added to the Product.

   Also bundle values cannot link to other Product bundles, to Products with a *required* option, or to Products with *subscription* enabled. If the bundle uses *dynamic* pricing, it can only link to *APPROVED* Product instances.
   ```

1. (Optional) If SKU Contributor is enabled for your Option, go to the *SKUs* tab in the Product's Page, click the Add Button, and select *Generate All SKU Combinations*.

   This generates a new SKU for each Option value, which you can edit in the SKUs tab.

   ```{important}
   When enabled, customers *must* select a value, even if Required is not enabled for the Option.
   ```

1. Verify your Product Bundle appears in the Product's Display Page.  

![Customers can make a selection from the Product Bundle Options on the Product's Display Page.](./creating-product-bundles/images/04.png)

## Additional Information

* [Using Product Options](./using-product-options.md)
