# Account Management

```{toctree}
:maxdepth: 3

account-management/creating-a-new-account.md
account-management/inviting-users-to-an-account.md
account-management/adding-addresses-to-an-account.md
account-management/account-roles.md
account-management/creating-a-new-account-group.md
account-management/using-organizations-to-create-a-sales-network.md
account-management/adding-organizations-to-accounts.md
```

In Liferay Commerce, Accounts are used to store customer information, such as billing and shipping addresses, order details, and VAT number. Once created, Accounts can be sorted into groups based on shared criteria (e.g., geographic region). These Account Groups can be used to target customers with specific Price Lists, Promotions, and Discounts.

## Account Types

Commerce provides two Account types: Business and Personal. Each one is used for the different [Site Type](../starting-a-store/sites-and-site-types.md) (i.e., B2B, or B2C).

* **Business Accounts**: Business Accounts can have multiple users associated with it. These users can be assigned [Account roles](./account-management/account-roles.md) to delegate responsibilities on behalf of the Account. In a B2B Site, customers must be associated with a Business Account to make purchases.

* **Personal Accounts**: Personal Accounts can only be associated with a single user. In a B2C Site, authenticated customers are automatically assigned a Personal Account and are able to make purchases individually.

  ```{note}
  Liferay User Account Profile information is imported to the Personal Account at the time of creation. Any additional updates to a Liferay User Account Profile are not automatically propagated to the Personal Account.
  ```
<!-- TASK: Add the Guest Account type; also note that B2X Sites recognize both Business and Personal Accounts-->
## Common Account Management Actions

See the following articles to learn more about common Account Management actions:

* [Creating a New Account](./account-management/creating-a-new-account.md)
* [Inviting Users to an Account](./account-management/inviting-users-to-an-account.md)
* [Adding Addresses to an Account](./account-management/adding-addresses-to-an-account.md)
* [Creating a New Account Group](./account-management/creating-a-new-account-group.md)

## Additional Information

* [Sites and Site Types](../starting-a-store/sites-and-site-types.md)
* [Account Roles](./account-management/account-roles.md)
