# Specifications

A specification stores a single piece of Product information. This can be the Product’s dimensions, color, weight, capacity, or any other attribute. These details can help customers when comparing similar Products.

Specification data is ordered into groups, labels, and values to make them easy to display and maintain. 

| Name | Description |
| --- | --- |
| [Specification Group](#specification-groups) | Entity for grouping and organizing similar Specification Labels (e.g., `dimensions`) |
| [Specification Label](#specification-labels) | Entity for storing a Specification Value (e.g., `height`) |
| [Specification Value](#specification-values) | Product-specific data added to a Specification Label (e.g., `100 cm`) |

```{note}
Specifications defined at the SKU level override Specifications defined at the Product level. See [Overriding Product Level Information](./overriding-product-level-information.md) for more details.
``` 

## Specification Groups

1. Navigate to the _Global Menu_ (![Applications Menu icon](../../../images/icon-applications-menu.png)) → _Commerce_ → _Products_.
1. Click the _Specifications_ tab.
1. Click the _Specification Groups_ sub-tab. By default, there are five Specification Groups included:

    ![In the Specification Groups sub-tab, you can view, create, and manage Groups.](./specifications/images/01.png)

To add a new Specification Group, click the *Add* button (![Add Button](../../../images/icon-add.png)).

| Field | Description |
| --- | --- |
| Title | Shows the display name of the Specification Group. |
| Description | Explains what the specifications have in common |
| Priority | Establishes the order to display the specification groups with smaller numbers going first. |
| Key | Uniquely identifies this group programmatically. |

![To create a Specification Group, enter a title, description, priority and key in the form provided.](./specifications/images/02.png)

## Specification Labels

To create a specification label:

1. Navigate to the _Global Menu_ (![Applications Menu icon](../../../images/icon-applications-menu.png)) → _Commerce_ → _Products_.
1. Click the _Specifications_ tab.
1. Click the _Specifications Labels_ sub-tab.
1. There are nine Specification Labels included by default:

    ![In the Specification Labels sub-tab, you can view, create, and manage Specification Labels.](./specifications/images/03.png)

To add a new Specification Group, click the *Add* button (![Add Button](../../../images/icon-add.png)).

| Field | Description |
| --- | --- |
| Label | Shows the display name of the Label. |
| Description | Gives a description of the label. |
| Use in Faceted Navigation | Toggle this label to use in faceted navigation. |
| Default Specification Group | Choose a group to associate this label with. |
| Key | Uniquely identifies this group programmatically. |

![Use the provided form to create a Specification Label.](./specifications/images/04.png)

The label has been created, but assigning a value to it must be done on the Product level.

## Specification Values

 Groups and labels are scoped to the catalog, which means once they are created, they can be assigned to any Product in the catalog. Values, however, are scoped to the Product and have no impact anywhere but on the Product to which they are assigned.

 To create a specification label, you must first assign a label to a Product.

1. Navigate to the _Global Applications_ menu → _Commerce_ → _Products_.
1. Click on a Product (for example, _Torque Converter_. This sample Product is added when using the Minium accelerator.)
1. Click on the _Specifications_ sub-tab.
1. The following values have been added by default:

    ![The Specifications sub-tab displays the Specifications added to a Product.](./specifications/images/05.png)

1. Click the *Add* button (![Add Button](../../../images/icon-add.png)).
1. Check the boxes for one or more specification labels from the list of specification labels.
1. Click _Add_ to close the pop-up.

This assigns the selected labels to the Product.

Next, edit the label to give it a value.

1. Click on the 3-dot icon next to the new label.
1. Click _Edit_.

|Field | Description |
|----- | --------- |
| Value | Gives a short description of what the Product is made of |
| Group | Select a Specification Group. |
| Priority | Establishes the order to display the specification groups with smaller numbers going first. |

![To store a Specification Value, click on Edit and enter a value, group, and priority in the form provided.](./specifications/images/06.png)

You can also override the specification group that was selected when the label was created. When editing the label to assign a value, you can choose any group from the _Group_ drop-down box. This change applies only to the current Product and has no effect on other Products that use the same label.
