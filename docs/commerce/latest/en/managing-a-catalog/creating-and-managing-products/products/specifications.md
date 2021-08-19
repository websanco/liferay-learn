# Specifications

A specification stores a single piece of product information. This can be the product’s dimensions, color, weight, capacity, or any other attribute. Specifications simplifies the process of comparing similar products.

Specification data is ordered into groups, labels and values to make them easy to display, maintain. 

| Name | Description |
| --- | --- |
| [Specification Group](#specification-groups) | Specifications with common characteristics can be organized into a group |
| [Specification Label](#specification-labels) | Names a specification |
| [Specification Value](#specification-values) | Provides product-specific information |

```{note}
Specifications defined at the Product level will be overridden by specifications defined at the SKU level. See [Overriding Product Level Information](./overriding-product-level-information.md) for more details.
``` 

## Specification Groups

1. Navigate to the _Global Menu_ (![Applications Menu icon](../../../images/icon-applications-menu.png)) → _Commerce_ → _Products_.
1. Click the _Specifications_ tab.
1. Click the _Specification Groups_ sub-tab. By default, there are five Specification Groups included:

    ![The Specifications tab with the Specification Groups sub-tab selected showing five default Specification Groups](./specifications/images/01.png)

To add a new Specification Group, click the (+) button.

| Field | Description |
| --- | --- |
| Title | Shows the display name of the Specification Group. |
| Description | Explains what the specifications have in common |
| Priority | Establishes the order to display the specification groups with smaller numbers going first. |
| Key | Uniquely identifies this group programmatically. |

![Form to add a Specification Group that is displayed after clicking the (+) button](./specifications/images/02.png)

## Specification Labels

To create a specification label:

1. Navigate to the _Global Menu_ (![Applications Menu icon](../../../images/icon-applications-menu.png)) → _Commerce_ → _Products_.
1. Click the _Specifications_ tab.
1. Click the _Specifications Labels_ sub-tab.
1. There are nine Specification Labels included by default:

    ![The Specifications tab with the Specification Labels sub-tab selected showing nine default Specification Groups](./specifications/images/03.png)

To add a new Specification Group, click the (+) button.

| Field | Description |
| --- | --- |
| Label | Shows the display name of the Label. |
| Description | Gives a description of the label. |
| Use in Faceted Navigation | Lets you toggle this label in faceted navigation. |
| Default Specification Group | Lets you select a group to associate this label with. |
| Key | Uniquely identifies this group programmatically. |

![Form to add a Specification Label that is displayed after clicking the (+) button](./specifications/images/04.png)

The label has been created, but assigning a value to it must be done on the product level.

## Specification Values

 Groups and labels are scoped to the catalog, which means, once they are created, they can be assigned to any product in the catalog. Values, however, are scoped to the product, and have no impact anywhere but on the product to which they are assigned.

 To create a specification label, you must first assign a label to a product.

1. Navigate to the _Global Applications_ menu → _Commerce_ → _Products_.
1. Click on a product (for example, _Torque Converter_. This sample product is added when using the Minium accelerator.)
1. Click on the _Specifications_ sub-tab.
1. The following values have been added by default:

    ![The Specifications sub-tab selected for a product from the Products list](./specifications/images/05.png)

1. Click the (+) button.
1. Check the boxes for one or more specification labels from the list of specification labels.
1. Click _Add_ to close the pop-up.

This assigns the selected labels to the product.

Next, edit the label to give it a value.

1. Click on the 3-dot icon next to the new label.
1. Click _Edit_.

|Field | Description |
|----- | --------- |
| Value | Gives a short description of what the product is made of |
| Group | Lets you select a Specification Group. |
| Priority | Establishes the order to display the specification groups with smaller numbers going first. |

![Form to give a label value that is displayed after clicking Edit](./specifications/images/06.png)

You can also override the specification group that was selected when the label was created. When editing the label to assign a value, you can choose any group from the _Group_ drop-down box. This change applies only to the current product and has no effect on any other products which use the same label.
