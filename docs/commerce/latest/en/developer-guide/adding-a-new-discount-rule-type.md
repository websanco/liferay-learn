# Adding a New Discount Rule Type

You can add a new discount rule type by implementing two interfaces: [CommerceDiscountRuleType](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-discount-api/src/main/java/com/liferay/commerce/discount/rule/type/CommerceDiscountRuleType.java) and [CommerceDiscountRuleTypeJSPContributor](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-discount-api/src/main/java/com/liferay/commerce/discount/rule/type/CommerceDiscountRuleTypeJSPContributor.java).

Discount rule types define conditions for evaluating when discounts are applied to an order. Liferay Commerce provides three discount rule types out-of-the-box: [AddedAllCommerceDiscountRuleTypeImpl](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-discount-service/src/main/java/com/liferay/commerce/discount/internal/rule/type/AddedAllCommerceDiscountRuleTypeImpl.java), [AddedAnyCommerceDiscountRuleTypeImpl](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-discount-service/src/main/java/com/liferay/commerce/discount/internal/rule/type/AddedAnyCommerceDiscountRuleTypeImpl.java), and [CartTotalCommerceDiscountRuleTypeImpl](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-discount-service/src/main/java/com/liferay/commerce/discount/internal/rule/type/CartTotalCommerceDiscountRuleTypeImpl.java).

![Out-of-the-box discount rule types](./adding-a-new-discount-rule-type/images/01.png "Out-of-the-box discount rule types")

## Overview

1. [**Deploy an Example**](#deploy-an-example)
1. [**Walk Through the Example**](#walk-through-the-example)
1. [**Additional Information**](#additional-information)

## Deploy an Example

First, you must deploy an example discount rule type on your instance of Liferay Commerce. Follow these steps:

1. Start Liferay Commerce.

    ```bash
    docker run -it -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
    ```

1. Download and unzip the [Acme Commerce Discount Rule Type](./liferay-m6a8.zip).

    ```bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/liferay-m6a8.zip -O
    ```

    ```bash
    unzip liferay-m6a8.zip
    ```

1. Build and deploy the example.

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
       This command is the same as copying the deployed jars to ``/opt/liferay/osgi/modules`` on the Docker container.
    ```

1. Confirm the deployment in the Liferay Docker container console.

    ```bash
    STARTED com.acme.m6a8.web_1.0.0
    ```

1. Verify that the example discount rule type was added. Open your browser to `https://localhost:8080`. Then click the Applications Menu (![Applications Menu](../images/icon-applications-menu.png)) and navigate to _Commerce_ → _Discounts_. Click _Edit_ within the menu for any discount. Scroll down to the _Rules_ section, click the (+) icon to add a new discount rule. The new discount rule type ("Has a minimum number of products") is present under the _Type_ dropdown.

```note::
   In Liferay Commerce 2.1 and earlier, find the discounts by navigating to *Control Panel* → *Commerce* → *Discounts*. Click any *Edit* within the menu for any discount and then click *Rules* at the top of the screen.
```

![New discount rule type](./adding-a-new-discount-rule-type/images/02.png "New discount rule type")

Congratulations, you've successfully built and deployed a new discount rule type that implements `CommerceDiscountRuleType`.

Next, you'll dive deeper to learn more.

## Walk Through the Example

Now it's time to review the example you deployed. There are two classes: a discount rule type class and a JSP contributor for a custom UI input. Follow these steps: 

* [Annotate the Discount Rule Type Class for OSGi Registration](#annotate-the-discount-rule-type-class-for-osgi-registration)
* [Review the `CommerceDiscountRuleType` Interface](#review-the-commercediscountruletype-interface)
* [Annotate the JSP Contributor Class for OSGi Registration](#annotate-the-jsp-contributor-class-for-osgi-registration)
* [Review the `CommerceDiscountRuleTypeJSPContributor` Interface](#review-the-commercediscountruletypejspcontributor-interface)
* [Complete the Discount Rule Type](#complete-the-discount-rule-type)

### Annotate the Discount Rule Type Class for OSGi Registration

```java
@Component(
    property = {
    "commerce.discount.rule.type.key=m6a8",
    "commerce.discount.rule.type.order:Integer=51"
    },
    service = CommerceDiscountRuleType.class
)
public class M6A8CommerceDiscountRuleTypeImpl
    implements CommerceDiscountRuleType {
```

> It is important to provide a distinct key for the discount rule type so that Liferay Commerce can distinguish the new type from others in the [discount rule type registry](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-discount-service/src/main/java/com/liferay/commerce/discount/internal/rule/type/CommerceDiscountRuleTypeRegistryImpl.java). Declaring a key that is already in use overrides the existing associated type.
>
> The `commerce.discount.rule.type.order` value indicates how far in the list of available discount rule types this type appears. For example, the ["added all" discount rule type](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-discount-service/src/main/java/com/liferay/commerce/discount/internal/rule/type/AddedAllCommerceDiscountRuleTypeImpl.java) has a value of 50. Giving your discount rule type a value of 51 ensures that it appears immediately after the "added all" type.

### Review the `CommerceDiscountRuleType` Interface

Implement the following methods:

```java
public boolean evaluate(
        CommerceDiscountRule commerceDiscountRule,
        CommerceContext commerceContext)
    throws PortalException;
```

> This method is where you implement the business logic for evaluating when the discount rule is applied.

```java
public String getKey();
```

> This provides a unique identifier for the discount rule type in the discount rule type registry. The key can be used to fetch the new type from the registry.

```java
public String getLabel(Locale locale);
```

> This returns a text label that describes how the discount rule is applied. See the implementation in [M6A8CommerceDiscountRuleTypeImpl.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/src/main/java/com/acme/m6a8/web/internal/commerce/discount/rule/type/M6A8CommerceDiscountRuleTypeImpl.java) for a reference in retrieving the label with a language key.

### Annotate the JSP Contributor Class for OSGi Registration

```java
@Component(
    property = "commerce.discount.rule.type.jsp.contributor.key=m6a8",
    service = CommerceDiscountRuleTypeJSPContributor.class
)
public class M6A8CommerceDiscountRuleTypeJSPContributor
    implements CommerceDiscountRuleTypeJSPContributor {
```

> It is important to provide a distinct key for the JSP contributor so that Liferay Commerce can distinguish the contributor from others in the [discount rule type JSP contributor registry](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-discount-api/src/main/java/com/liferay/commerce/discount/rule/type/CommerceDiscountRuleTypeJSPContributorRegistry.java). Declaring a key that is already in use overrides the existing associated type.

### Review the `CommerceDiscountRuleTypeJSPContributor` Interface

Implement the following method:

```java
public void render(
        long l, long l1, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws Exception;
```

> This is where the code to render a custom UI input for our discount rule type goes.

### Complete the Discount Rule Type

The discount rule type is comprised of back-end logic for evaluating when to apply a discount rule to an order, logic to render UI inputs for the discount rule type, and the custom UI inputs themselves. Follow these steps: 

* [Configure the `ServletContext` for the module.](#configure-the-servletcontext-for-the-module)
* [Implement the `CommerceDiscountRuleTypeJSPContributor`'s `render` method.](#implement-the-commercediscountruletypejspcontributors-render-method)
* [Add the evaluation logic to `evaluate`.](#add-the-evaluation-logic-to-evaluate)
* [Add a JSP to render the custom UI input.](#add-a-jsp-to-render-the-custom-ui-input)
* [Add the language keys to `Language.properties`.](#add-the-language-keys-to-languageproperties)

#### Configure the `ServletContext` for the Module

Define the `ServletContext` in the JSP contributor class using the bundle's symbolic name so it can find the JSP: 

```java
@Reference(target = "(osgi.web.symbolicname=com.acme.m6a8.web)")
private ServletContext _servletContext;
```

> The value set for `osgi.web.symbolicname` matches the value for `Bundle-SymbolicName` in the [bnd.bnd file](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/bnd.bnd). These values must match for the `ServletContext` to locate the JSP.
>
> Declare a unique value for `Web-ContextPath` in the bnd.bnd file so the `ServletContext` is correctly generated. In this example, `Web-ContextPath` is set to `/m6a8-web`. See the [bnd.bnd file](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/bnd.bnd) for a reference on these values.

#### Implement the `CommerceDiscountRuleTypeJSPContributor`'s `render` Method

```java
@Override
public void render(
        long commerceDiscountId, long commerceDiscountRuleId,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws Exception {

    _jspRenderer.renderJSP(
        _servletContext, httpServletRequest, httpServletResponse,
        "/view.jsp");
}
```

> Use a `JSPRenderer` to render the JSP for the discount rule type's custom UI input (in our example, [view.jsp](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/src/main/resources/META-INF/resources/view.jsp)). Provide the `ServletContext` as a parameter to find the JSP.

#### Add the Evaluation Logic to `evaluate`

```java
@Override
public boolean evaluate(
        CommerceDiscountRule commerceDiscountRule,
        CommerceContext commerceContext)
    throws PortalException {

    CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

    if (commerceOrder == null) {
        return false;
    }

    List<CommerceOrderItem> commerceOrderItems =
        commerceOrder.getCommerceOrderItems();

    int mininumNumberOfItems = GetterUtil.getInteger(
        commerceDiscountRule.getSettingsProperty(
            commerceDiscountRule.getType()));

    if (commerceOrderItems.size() >= mininumNumberOfItems) {
        return true;
    }

    return false;
}
```

> Implement any conditions here that must be true for a discount rule to be applied. This example checks that the order contains at least a minimum number of items, using a minimum value defined by a custom UI input (stored as a String within the [CommerceDiscountRule](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-discount-service/src/main/java/com/liferay/commerce/discount/model/impl/CommerceDiscountRuleImpl.java)).
>
> The `CommerceOrder` object represents information about the order being evaluated. See [CommerceOrder.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/cmodules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrder.java) and [CommerceOrderModel.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrderModel.java) to find more information you can get from a `CommerceOrder`.

#### Add a JSP to Render the Custom UI Input

The example uses a JSP called `view.jsp` with a numeric input for a minimum number of products.

```jsp
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<aui:input label="minimum-number-of-items" name="typeSettings" type="text">
    <aui:validator name="digits" />
    <aui:validator name="min">1</aui:validator>
</aui:input>
```

> Implement UI elements to present when defining a discount rule. These appear immediately after selecting the discount rule type. Defining an input causes the saved value to be stored in the discount rule's settings properties.
>
> See [Using AUI Taglibs](https://help.liferay.com/hc/en-us/articles/360020189212-Using-AUI-Taglibs) for more information on using AUI inputs.

#### Add the Language Keys to `Language.properties`

Add the language keys and their values to a [Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/src/main/resources/content/Language.properties) file:

```properties
has-a-minimum-number-of-items=Has a Minimum Number of Items
minimum-number-of-items=Minimum Number of Items
```

> See [Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application) for more information.

## Conclusion

Congratulations! You now know the basics for implementing the `CommerceDiscountRuleType` interface, and have added a new discount rule type with a custom UI input to Liferay Commerce.

## Additional Information

* [Creating a Discount](../../promoting-products/creating-a-discount.md)
* [Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
* [Using AUI Taglibs](https://help.liferay.com/hc/en-us/articles/360020189212-Using-AUI-Taglibs)
