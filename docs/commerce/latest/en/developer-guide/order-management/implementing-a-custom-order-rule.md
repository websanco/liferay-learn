# Implementing a Custom Order Rule

You can configure Order Rules in Liferay to check-out orders that meet a specific condition. The Minimum Order Amount rule is available out-of-the-box. It prevents check-out of orders below a specific value. To add a new order rule, you must implement the [`COREntryType`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/commerce/commerce-order-rule-api/src/main/java/com/liferay/commerce/order/rule/entry/type/COREntryType.java) interface. See [Order Rules](https://learn.liferay.com/commerce/latest/en/order-management/order-rules.html) for more information.

## Deploying the Custom Order Rule and Adding Language Keys

```{include} /_snippets/run-liferay-dxp.md
```

Then, follow these steps:

1. Download and unzip the Acme Commerce Order Rule.

   ```bash
   curl https://learn.liferay.com/commerce/latest/en/developer-guide/order-management/liferay-x9k1.zip

   unzip liferay-x9k1.zip
   ```

1. Build and deploy the example.

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   ```{note}
   This command is the same as copying the deployed jars to `/opt/liferay/osgi/modules` on the Docker container.
   ```

1. Confirm the deployment in the Docker container console.

   ```bash
   STARTED com.acme.x9k1.impl_1.0.0
   ```

1. Log in as an administrator, open the *Global Menu* (![Applications Menu icon](../../images/icon-applications-menu.png)), and click on *Control Panel* &rarr; *Language Override*. Click the Add button (![Add icon](../../images/icon-add.png)) and add the following keys.

   |  Language Key | Value  |
   |:---|:---|
   | x9k1-minimum-order-quantity  | X9K1 Minimum Order Quantity  |
   | minimum-quantity | Minimum Quantity  |

   ```{important}
   You can add language keys in the Language Override tool for Liferay DXP 7.4 U4+ or Liferay Portal 7.4 GA8+. For previous versions, you must add a `Language.properties` file under `/src/main/resources/content/` with the keys before building and deploying.
   ```

1. Open the *Global Menu* (![Applications Menu icon](../../images/icon-applications-menu.png)), click on *Commerce* &rarr; *Order Rules*.

1. Click the *Add* button (![Add icon](../../images/icon-add.png)), and enter the following information.

   **Name:** Minimum Order Quantity - 3

   **Description:** Testing minimum order quantity of 3 items

   **Type:** X9K1 Minimum Order Quantity

1. Click *Submit*.

1. In the *Configuration* section, set the *Minimum Quantity* to 3.

1. Enable the new order rule by clicking on the Active toggle.

1. Click *Publish*.

1. Open the *Global Menu* (![Applications Menu icon](../../images/icon-applications-menu.png)), click on *Control Panel* &rarr; *Sites*, and add a new Minium Demo site.

1. Log in as a buyer and add items to your cart. Click *Submit* to check-out.

You can see a warning message if the order quantity is less than 3. Check-out is not possible until you meet this condition.

## How the Custom Order Rule Works


## Conclusion