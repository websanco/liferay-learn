# Creating a Price List

Price Lists provide a convenient way to set alternate prices for a product, as well as alternate currencies for selected Accounts and Account Groups.

Follow these steps to create a new Price List:

1. Open the *Global Menu* (![Applications Menu icon](../../images/icon-applications-menu.png)), click on the *Commerce* tab, and go to *Pricing* &rarr; *Price Lists*.

1. Click on the *Add* button (![Add icon](../../images/icon-add.png)).

1. Enter a *Name*, *Catalog*, and *Currency* for the new Price List. While its name and currency can be changed at any time after creation, its catalog cannot be changed.

   ```note::
      Creating a Price List requires your Commerce instance to have at least one `Catalog <../catalogs/creating-a-new-catalog.html>`_.
   ```

1. Click on *Submit*.

Once created, you can configure its general details, including the list's relative priority, parent Price List, and price type (i.e., net or gross). You can also determine The Price List's associated Accounts and Account Groups, populate its entries, and set its Price Modifiers. When finished, click on *Publish* to make your changes live, or *Save as Draft* to publish them at a later time.

![Configure the newly created price list.](./creating-a-price-list/images/02.png)

```note::
   When a catalog is first created, a Base Price List is automatically created to store the base price for each of its entries. For these Price Lists, only the *Details* and *Entries* tabs are available. Eligibility and price modifier settings are only available to subsequently created Price Lists.
```

## Determining the Price List's Eligibility

Follow these steps to determine the Accounts and Account Groups to which the Price List is made available:

1. Click on the *Price List* you want to configure, and go to the *Eligibility* tab.

1. Use the radio buttons to determine *Account* and *Channel* eligibility.

   * Account Eligibility: choose from *All Accounts*,*Specific Accounts*, and *Specific Account Groups*.

   * Channel Eligibility: choose from *All Channels* and *Specific Channels*.

1. If you restrict Price List eligibility, use the provided search bar to locate the desired Accounts, Account Groups, or Channels, and click on *Select*.

1. Click on *Publish* when finished.

Once configured, the Price List is accessible for your selected accounts, account groups, and channels.

## Populating the Price List's Entries

Follow these steps to add products to the Price List:

1. Click on the *Price List* you want to configure, and go to the *Entries* tab.

1. Use the provided search bar to locate the desired products, and then click on *Select* to add them to the selected Price List.

   When searching for products, you can use an SKU or keywords and phrases. Using a keyword or phrase returns all the applicable products by their SKU.

   ![Search for your products by SKU, keyword, or phrase.](./creating-a-price-list/images/03.png)

1. Click on *Publish* to populate the Price List.

## Adding Price Modifiers

Follow these steps to add a new price modifier to a Price List:

1. Click on the *Price List* you want to configure, and go to the *Price Modifiers* tab.

1. Click on the *Add* button (![Add icon](../../images/icon-add.png)).

1. Enter a *Name*, *Target*, and type of price *Modifier* you want to apply (i.e., Percentage, Replace, Fixed Amount).

1. Click on *Submit* when finished.

Once created, you can finish configuring the modifier by clicking on it in the Price Modifiers tab.

```note::
   Available configuration options vary depending on the selected target for your modifier.
```

### Configuring a Modifier's General Information

Follow these steps to configure a Price Modifier's general details:

1. Click on the *Price Modifier* you want to edit, and go to the *Info* tab.

1. Enter an *Amount* to use for the selected type of modifier.

1. Optionally, manually enter the Price Modifier's Priority, which is used as a tie breaker in the case of multiple matches.

1. Slide the *Active* toggle to *YES*.

1. Optionally, you can *schedule* when the Price Modifier takes effect, as well as if and when it expires.

1. Click on *Save* when finished.

   ![Configure the price modifier.](./creating-a-price-list/images/05.png)

### Configuring a Modifier's Target Details

Once you've determined a Price Modifier's general details, additional configuration options are available depending on the modifier's selected target. These settings are located in a dedicated tab of the Price Modifier editing window. 

In this tab, follow these steps to select the specific *Categories*, *Products*, or *Product Groups* that are affected by the modifier:

1. Click on the *Price List* you want to configure, and go to the *Price Modifiers* tab.

1. Click on the additional editing tab (e.g., *Categories*, *Products*, or *Product Groups*).

1. Use the provided search bar to locate the desired target for the modifier, and then click on *Select* to make them subject to the price modifier.

   ![Click on Select to add them to the price modifier.](./creating-a-price-list/images/04.png)

1. After selecting the desired targets, return to the *Info* tab, and click on *Save*.

## Commerce 2.1 and Below

To add a price list:

1. Navigate to the _Control Panel_ → _Commerce_ → _Price Lists_.
1. Click the (![Add icon](../../images/icon-add.png)) button.
1. Enter the following fields (example values shown below):
    * **Catalog**: Sahara.com
    * **Name**: VIP Customers
    * **Store Currency**: USD
    * **Priority**: 1.0
1. Under _Account Groups_, click _Select_.
1. Choose one or more Account Groups to have access to this price list (example account groups shown below):
    * _General_
    * _Auto Repair Shops_
1. Click _Add_.
1. Under _Accounts_, click _Select_.
1. Choose one or more Accounts to have access to this price list (example accounts shown below):
    * Mike’s Car Repair
    * Fountain Valley Car Wash
1. Click _Add_.
1. Choose a Schedule for the price list. (Leave the _Never Expire_ checkbox checked in this example. Alternatively, you can set a date range for when the price list is active.)

    ![Adding a price list](./creating-a-price-list/images/01.png)

1. Click _Publish_.

The price list (_VIP Customers_ in this example) has been created and applied to the chosen Account Groups and Accounts. Repeat the steps to create other price lists as necessary.

## Additional Information

* [Creating a New Account Group](../../account-management/creating-a-new-account-group.md)
* [Creating a New Account](../../account-management/creating-a-new-account.md)
* [Adding Products to a Price List](./adding-products-to-a-price-list.md)
* [Adding Tiered Pricing](./adding-tiered-pricing.md)
