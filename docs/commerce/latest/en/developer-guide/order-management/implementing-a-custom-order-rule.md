# Implementing a Custom Order Rule

You can configure Order Rules in Liferay to checkout orders that meet a specific condition. The Minimum Order Amount rule is available out-of-the-box. It prevents check-out of orders below a specific value. To add a new order rule, you must implement the [`COREntryType`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/commerce/commerce-order-rule-api/src/main/java/com/liferay/commerce/order/rule/entry/type/COREntryType.java) interface. See [Order Rules](https://learn.liferay.com/commerce/latest/en/order-management/order-rules.html) for more information.

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

```{important}
After activating an Order Rule, it applies to all Accounts, Account Groups, Order Types and Channels. To control the eligibility, click on the *Eligibility* tab of the Order Rule and select the appropriate option. 
```

## How the Custom Order Rule Works

This example consists of 8 main steps. First, you must annotate the class for OSGi registration. Second, review and complete the implementation of the `COREntryType` interface. Third, add a display context for the new order rule.

Then, add a utility class to retrieve the value of the minimum quantity to use in your display context and Order Rule implementation. After that, review and complete the implementation of the [`COREntryTypeJSPContributor`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/commerce/commerce-order-rule-api/src/main/java/com/liferay/commerce/order/rule/entry/type/COREntryTypeJSPContributor.java) interface for the new order rule. Finally, add a JSP to render the configuration part of the new order rule.

* [Annotate the Order Rule for OSGi Registration](#annotate-the-order-rule-for-osgi-registration)
* [Review the `COREntryType` interface](#review-the-corentrytype-interface)
* [Complete the `COREntryType` implementation](#complete-the-corentrytype-implementation)
* [Add a display context](#add-a-display-context)
* [Add a utility class to retrieve the minimum quantity value](#add-a-utility-class-to-retrieve-the-minimum-quantity-value)
* [Annotate the JSP contributor for OSGi Registration](#annotate-the-jsp-contributor-for-osgi-registration)
* [Review the `COREntryTypeJSPContributor` interface](#review-the-corentrytypejspcontributor-interface)
* [Complete the JSP contributor implementation](#complete-the-jsp-contributor-implementation)
* [Add a JSP to render the configuration of the Order Rule](#add-a-jsp-to-render-the-configuration-of-the-order-rule)

### Annotate the Order Rule for OSGi Registration

```{literalinclude} ./implementing-a-custom-order-rule/resources/liferay-x9k1.zip/x9k1-impl/src/main/java/com/acme/x9k1/internal/commerce/order/rule/entry/type/X9K1MinimumQuantityCOREntryTypeImpl.java
    :language: java
    :lines: 18-25
```

You must provide a distinct key for the order rule so that Liferay Commerce can distinguish it from others in the order rule registry. Specifying a key that is already in use overrides the existing associated type. The order determines its sort order in the drop down. In this case, the order is 1, and it appears as the second item in the drop down.

### Review the `COREntryType` interface

```java
public boolean evaluate(COREntry corEntry, CommerceOrder commerceOrder) throws PortalException;
```

This method evaluates the order rule and returns true or false depending on whether the condition is met.

```java
public String getErrorMessage( COREntry corEntry, CommerceOrder commerceOrder, Locale locale)  throws PortalException;
```

If the evaluated method returns false, this method returns a string containing the error message. This renders as a warning to the user.

```java
public String getKey();
```

This method returns the unique key of the order rule. Using an existing key overrides that order rule.

```java
public String getLabel(Locale locale);
```

This method returns the name of the order rule as it appears in the UI. This may be a language key or a string.

### Complete the `COREntryType` implementation

```{literalinclude} ./implementing-a-custom-order-rule/resources/liferay-x9k1.zip/x9k1-impl/src/main/java/com/acme/x9k1/internal/commerce/order/rule/entry/type/X9K1MinimumQuantityCOREntryTypeImpl.java
    :language: java
    :lines: 28-94
    :dedent: 1
```

To complete the Order Rule, you must implement the above methods. There are two utility methods added to get the order quantity and the minimum quantity configured in the order rule. The first overridden method is `evaluate()` and it checks if the current order passes the order rule or not. It returns true if it does and false otherwise.

The second method is to retrieve the error message for orders that don't satisfy the order rule. It returns a String converted from a StringBuilder that contains all the terms. The third method returns the unique key and the last method returns the label that appears on the UI.

There are two additional methods to get the minimum quantity of the order rule and the total order quantity. The first method is present in the utility class `X9K1MinimumQuantityUtil`. The second method is `_getOrderQuantity(CommerceOrder commerceOrder)`. It returns the total order quantity as a sum of individual product quantities in the order.

### Add a display context

```{literalinclude} ./implementing-a-custom-order-rule/resources/liferay-x9k1.zip/x9k1-impl/src/main/java/com/acme/x9k1/internal/commerce/order/rule/web/display/context/X9K1MinimumQuantityDisplayContext.java
    :language: java
    :lines: 7-19
```

The display context is used to retrieve the value for the minimum quantity configured for the order rule. The display context contains a single field of type `COREntry` and it is set using the created order rule. The display context has one method to retrieve the minimum quantity configured for the order rule and it uses the utility class detailed below.

### Add a utility class to retrieve the minimum quantity value

```{literalinclude} ./implementing-a-custom-order-rule/resources/liferay-x9k1.zip/x9k1-impl/src/main/java/com/acme/x9k1/internal/commerce/order/rule/entry/type/util/X9K1MinimumQuantityUtil.java
    :language: java
    :lines: 8-20
```

The `X9K1MinimumQuantityUtil` class retrieves the minimum quantity configured for the order rule. It retrieves the value using the property’s name as set in the JSPkey.

### Annotate the JSP contributor for OSGi Registration

```{literalinclude} ./implementing-a-custom-order-rule/resources/liferay-x9k1.zip/x9k1-impl/src/main/java/com/acme/x9k1/internal/commerce/order/rule/web/entry/type/X9K1MinimumQuantityCOREntryTypeJSPContributor.java
    :language: java
    :lines: 18-24
```

The `commerce.order.rule.entry.type.jsp.contributor.key` property determines the order rule for which the JSP contributor is implemented.

### Review the `COREntryTypeJSPContributor` interface

```java
public void render(long corEntryId, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception;
```

The `COREntryTypeJSPContributor` interface contains one method that renders a JSP. The method requires the id of the order rule and objects of type `HTTPServletRequest` and `HTTPServletResponse` as arguments.

### Complete the JSP contributor implementation

```{literalinclude} ./implementing-a-custom-order-rule/resources/liferay-x9k1.zip/x9k1-impl/src/main/java/com/acme/x9k1/internal/commerce/order/rule/web/entry/type/X9K1MinimumQuantityCOREntryTypeJSPContributor.java
    :language: java
    :lines: 26-52
    :dedent: 1
```

To complete the JSP Contributor, you must implement the `render()` method. It retrieves the `COREntry` using the `_corEntryLocalService` and the `corEntryId`. Then, it creates a new display context of type `X9K1MinimumQuantityDisplayContext` using the retrieved `corEntry`. This context is then set to the `httpServletRequest`. The `servletContext` references the `Bundle-Symbolic-Name` from the `bnd.bnd` file. The `JSPRenderer` renders a JSP file with the `renderJSP()` method. It accepts the relative path of the JSP, `servletContext`, `httpServletRequest`, and `httpServletResponse` as arguments.

### Add a JSP to render the configuration of the Order Rule

```{literalinclude} ./implementing-a-custom-order-rule/resources/liferay-x9k1.zip/x9k1-impl/src/main/resources/META-INF/resources/minimum_quantity.jsp
    :language: jsp
    :lines: 1-27
```

The JSP contains one input field to accept the minimum quantity for the order rule. It is retrieved through the display context and evaluated inside the custom order rule. The display context uses the utility class and fetches the field using the `minimum-quantity` name from the type settings configuration. The `getMinimumQuantity()` method retrieves the existing value, if any.

## Conclusion

Congratulations! You now know the basics for implementing the `COREntryType` interface, and have added a new order rule to Liferay Commerce.
