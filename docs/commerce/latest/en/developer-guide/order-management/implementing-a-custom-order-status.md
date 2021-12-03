# Implementing a Custom Order Status

You can add a custom order status by implementing the `CommerceOrderStatus` interface. The Commerce Order Engine provides a standard order flow out-of-the-box, but you can customize it to fit your needs.

A custom order status is a new stage added to the existing order flow. You need a custom order status when you want an order fulfillment process the standard order flow doesn't handle. First, you'll learn how the order statuses work, and then you'll deploy a new example implementation.

## Overview of Order Statuses

The Liferay Order Engine has six main statuses: 1) Open, 2) In Progress, 3) Pending, 4) Processing, 5) Shipped, 6) Completed.

![Liferay Commerce contains six order statuses by default.](./implementing-a-custom-order-status/images/01.png)

The order engine performs checks against each order status to ensure correct order processing and to determine the next status to be applied to the order. Besides the main statuses mentioned above, orders can be transitioned to three alternate statuses.

![Orders can be transitioned to three alternate statuses.](./implementing-a-custom-order-status/images/02.png)

1. _On Hold_ - An order can be put on hold when it is in any of the non-final order statuses (Pending, Processing, Shipped).

1. _Cancelled_ - An order can be cancelled when it is in any of the non-final order statuses (Pending, Processing, Shipped).

1. _Partially Shipped_ - When there are multiple items in the order and not every item has been shipped, it transitions to the _Partially Shipped_ status.

![You can add a new order status to the order flow.](./implementing-a-custom-order-status/images/03.png)

You can add a custom order status to alter the out-of-the-box order flow. Below, you'll add an order status called *Scheduling* and place it between the existing *Pending* and *Processing* statuses. This custom stage represents orders waiting to be scheduled before they can be accepted. A custom field on the order tracks the scheduling status. See [Commerce Order Engine Overview](./commerce-order-engine-overview.md) for detailed information about each order status and its transitions.

## Deploy the Order Status

1. Start Liferay Commerce.

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.4.1-ga2
    ```

1. Download and unzip the Acme Commerce Order Status Method.

    ```bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/order-management/liferay-m4v7.zip

    unzip liferay-m4v7.zip
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
    STARTED com.acme.m4v7.impl_1.0.0
    ```

1. You must create a custom field to keep track of scheduling the order. Click the _Applications Menu_ (![Applications Menu](../../images/icon-applications-menu.png)) and navigate to _Control Panel_ →  _Custom Fields_.

1. Select Commerce Order from the list of items and click the _Add_ (![Add](../../images/icon-add.png)) button to add a new field. Select the _Dropdown_ option from the available fields and enter the information below. Click _Save_ when done.

   __Field Name__: m4v7Scheduling

   __Data Type__: Text

   __Values__: Pending, Confirmed (in two separate lines)

 ![Add a custom field to keep track of scheduling the order.](./implementing-a-custom-order-status/images/04.png)

1. Verify the example order status was added by opening your browser to `https://localhost:8080` and from the Applications Menu (![Applications Menu](../../images/icon-applications-menu.png)), navigate to your site and place an order.

1. Click the Applications Menu again, navigate to _Commerce_ →  _Orders_, and select the order that you placed. A new status _Scheduling_ and a button called Scheduling that sets the new order flow in motion appears in the order lifecycle. The new custom field is present under the _Custom Fields_ section of the order.

   ![The new order status in action.](./implementing-a-custom-order-status/images/05.gif)

## How the Custom Order Status Works

The example implementation is implemented in 3 steps. First, you must annotate the class for OSGi registration. Next, review the [`CommerceOrderStatus`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/order/status/CommerceOrderStatus.java) interface. Finally, finish the implementation of the custom `CommerceOrderStatus`.

* [Annotate the class for OSGi Registration](#annotate-the-class-for-osgi-registration)
* [Review the `CommerceOrderStatus` interface](#review-the-commerceorderstatus-interface)
* [Complete the Order Status](#complete-the-order-status)

```{important}
Depending on the stage where you place the new status in the order lifecycle, you must tweak the next stage for correct order processing. Since this example places the new status between the Pending and Processing statuses, you must override the existing Processing status so it checks for the new status in its logic. 
```

### Annotate the class for OSGi Registration

```{literalinclude} ./implementing-a-custom-order-status/resources/liferay-m4v7.zip/m4v7-impl/src/main/java/com/acme/m4v7/internal/commerce/order/status/M4V7SchedulingCommerceOrderStatus.java
    :language: java
    :lines: 17-23
```

It is important to provide a distinct key for the order status so that Liferay Commerce can distinguish the new status from others in the order status registry. Specifying key that is already in use overrides the existing associated status. The priority of the order status determines its order in the order lifecycle. In this case, the Pending status has a priority of 30 and the processing status has a priority of 50. To place the status between the two, the priority must be between those two numbers, in this case 40.

```{note}
For this example implementation, a random integer is set as the key and 40 as the priority, but you can use variables for better readability within the code. See example [here](https://gist.github.com/aswinrajeevofficial/5d09d76ae11a1dc78c7d1fc388ae0306#file-m4v7schedulingcommerceorderstatus-java).
```

### Review the CommerceOrderStatus interface

Implement the following methods:

```java
public String getLabel(Locale locale);
```

This method returns the name of our order status. This name may be a language key that corresponds to the name that appears in the UI. In this case, it returns the string _Scheduling_.

```java
public int getKey();
```

This method returns the unique key of the order status. Using an existing key overrides that status.

```java
public int getPriority();
```

This method returns the priority of the order status. This is used to determine the stage at which this status is placed.

```java
public boolean isTransitionCriteriaMet(CommerceOrder commerceOrder) throws PortalException;
```

This method checks if the order has met all the transition criteria specified for the current order status.

```java
public CommerceOrder doTransition(CommerceOrder commerceOrder, long userId) throws PortalException;
```

Once the transition criteria for this status is met, the `doTransition` method performs all the actions necessary for the order to transition to this status.

```java
public boolean isComplete(CommerceOrder commerceOrder);
```

This method checks if the order status has completed its action and is ready for transition to the next status. For the Scheduling status, it checks if the custom field value is equal to Pending or if it's Confirmed and ready to transition to the Processing stage.

There are two more methods in the interface. The first one `public boolean isValidForOrder(CommerceOrder commerceOrder) throws PortalException` checks if the status is applicable for the order. The second one, `public boolean isWorkflowEnabled(CommerceOrder commerceOrder) throws PortalException` checks if there is a workflow associated with the status. There is no need to implement these methods for this example.

### Complete the Order Status

The order status implementation consists of implementing the methods for the Scheduling status and also tweaking the existing business logic present in the Processing status. 

* [Implement the `isTransitionCriteriaMet` method](#implement-the-istransitioncriteriamet-method)
* [Implement the `doTransition` method](#implement-the-dotransition-method)
* [Implement the `isComplete` method](#implement-the-iscomplete-method)
* [Override the existing _Processing_ status](#override-the-existing-processing-status)
* [Tweak the Processing status business logic](#tweak-the-processing-status-business-logic)

#### Implement the isTransitionCriteriaMet method

```{literalinclude} ./implementing-a-custom-order-status/resources/liferay-m4v7.zip/m4v7-impl/src/main/java/com/acme/m4v7/internal/commerce/order/status/M4V7SchedulingCommerceOrderStatus.java
    :dedent: 1
    :language: java
    :lines: 64-75
```

For the order to transition into the _Scheduling_ order status, it must be in the _Pending_ status. This is checked using the `getOrderStatus()` method on the `commerceOrder` object. The method returns `true` if the order is pending, and `false` otherwise.

#### Implement the doTransition method

```{literalinclude} ./implementing-a-custom-order-status/resources/liferay-m4v7.zip/m4v7-impl/src/main/java/com/acme/m4v7/internal/commerce/order/status/M4V7SchedulingCommerceOrderStatus.java
    :dedent: 1
    :language: java
    :lines: 26-33
```

Once the transition criteria for the order is met, it sets the order status as *Scheduling* using the unique key. Then it calls the `updateCommerceOrder()` method from `_commerceOrderService`, passing in the `commerceOrder` object to update the new status.

#### Implement the isComplete method

```{literalinclude} ./implementing-a-custom-order-status/resources/liferay-m4v7.zip/m4v7-impl/src/main/java/com/acme/m4v7/internal/commerce/order/status/M4V7SchedulingCommerceOrderStatus.java
    :dedent: 1
    :language: java
    :lines: 50-62
```

To complete the Scheduling stage, the Custom Field must be set to _Confirmed_. This custom attribute is retrieved through an `ExpandoBridge` using the key `m4v7Scheduling`. As it is a drop-down, the return value is present inside a String array and it's the first value. If the value is _Confirmed_, the method returns `true`, and if the array is empty it returns `false`.

#### Override the existing Processing status

```{literalinclude} ./implementing-a-custom-order-status/resources/liferay-m4v7.zip/m4v7-impl/src/main/java/com/acme/m4v7/internal/commerce/order/status/M4V7ProcessingCommerceOrderStatus.java
    :language: java
    :lines: 15-22
```

The existing Processing status must be overridden to tweak the logic present inside it. This is done by annotating the class for OSGi registration and using the same key and priority as the existing status. A `service.ranking` is set to 100 for the overridden status so that it takes priority over the existing one.

#### Tweak the Processing status business logic

```{literalinclude} ./implementing-a-custom-order-status/resources/liferay-m4v7.zip/m4v7-impl/src/main/java/com/acme/m4v7/internal/commerce/order/status/M4V7ProcessingCommerceOrderStatus.java
    :dedent: 1
    :language: java
    :lines: 53-73
```

Since the original Processing status checks for the Pending status in its methods, you must tweak them slightly to check for the newly added status. This is done using the unique key of the new status.

## Conclusion

Congratulations! You now know the basics for implementing the `CommerceOrderStatus` interface, and have added a new order status to Liferay Commerce.

## Additional Information

* [Commerce Order Engine Overview](./commerce-order-engine-overview.md)
