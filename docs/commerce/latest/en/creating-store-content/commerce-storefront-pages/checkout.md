# Checkout

The Checkout page contains the _Checkout_ widget. It becomes active once a buyer clicks _Checkout_ to submit an order. You can also implement a custom checkout step using extension points. See [Implementing a Custom Checkout Step](../../developer-guide/implementing-a-custom-checkout-step.md) for more information.

![When there's no active order, the Checkout widget is empty.](./checkout/images/01.png)

During the checkout process, there are steps to enter the buyer's shipping and billing addresses, select a shipping method, and to review the order and confirm it.

## Entering a Shipping Address

![Enter a shipping address for the order.](./checkout/images/02.png)

Note that if the buyer checks the _Use shipping address as billing address_, this will skip the Billing Address page.

## Selecting Shipping Method

![Select the appropriate shipping method for the order.](./checkout/images/03.png)

Shipping methods available to a buyer are configured by changing _Shipping Methods_ in _Site Administration_ → _Commerce_ → _Settings_ -> _Shipping Methods_. See [Using the Flat Rate Shipping Method](../../store-management/configuring-shipping-methods/using-the-flat-rate-shipping-method.md) for more information.

## Entering a Billing Address

As noted above, this page appears only if the buyer's billing address is different from the shipping address.

![Enter a billing address for the order.](./checkout/images/04.png)

## Viewing the Order Summary

![View the details of the order before confirming it.](./checkout/images/05.png)

## Confirming the Order

![Confirm the order and go to the Order Details page to view the placed order.](./checkout/images/06.png)

When an order is confirmed, the order is ready for processing by the seller. See [Order Life Cycle](../../order-management/orders/order-life-cycle.md) for more information.

```{note}
The *Checkout* widget supports Liferay's Adaptive Media out-of-the-box. See [Serving Device and Screen Optimized Media](https://learn.liferay.com/dxp/latest/en/content-authoring-and-management/documents-and-media/publishing-and-sharing/serving-device-and-screen-optimized-media.html) for more information.
```

## Additional Information

* [Widget Reference Guide](../liferay-commerce-widgets/widget-reference.md)
* [Creating Pages](https://help.liferay.com/hc/en-us/articles/360018171291-Creating-Pages)
* [Implementing a Custom Checkout Step](../../developer-guide/implementing-a-custom-checkout-step.md)
* [Using the Flat Rate Shipping Method](../../store-managemen/configuring-shipping-methods/using-the-flat-rate-shipping-method.md)
