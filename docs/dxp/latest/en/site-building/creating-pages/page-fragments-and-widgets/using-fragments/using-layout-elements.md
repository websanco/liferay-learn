# Using Layout Elements

> Available: Liferay Portal 7.3 GA2+; previously called Layouts with *panels* and *rows*.

*Layout Elements* define drop zones that you can use to design Page and Template layouts. By Default, Liferay includes two Layout Elements: [*Containers*](#containers) and [*Grids*](#grids). Once added, you can drag and drop widgets or other Fragments into these drop zones, including additional containers or grids to create even more complex layouts. After designing a layout, you can save layout compositions to reuse them in other Pages and Templates. See [Saving Fragment Compositions](./saving-fragment-compositions.md) for more information.

When you create a new Page or Template that supports Fragments, a default drop zone appears. You can add any Fragment to this area, but using the Container Fragment as the first element in your composition has important advantages:

* Greater layout control over Page design, including *Flex* Display Properties for [advanced compositions](./saving-fragment-compositions.md#creating-advanced-compositions-with-the-container-fragment) (Liferay DXP 7.4+)
* Ability to quickly [save and reuse](./saving-fragment-compositions.md) compositions in other Pages and Templates
* Ability to add a URL or Page redirect to a Fragment composition
* Ability to leverage [Style Books](../../../site-appearance/style-books/using-a-style-book-to-standardize-site-appearance.md) to standardize your Site appearance

Once you've added a Container, you can then use the Grid Fragment to customize content layout for the different viewports. See [Building Responsive Layouts with the Grid Fragment](./../../../optimizing-sites/building-a-responsive-site/building-responsive-layouts-with-the-grid-fragment.md) to learn more.

## Containers
<!--TASK: Add Image-->
The Container Fragment adds a configurable drop zone to a Content Page. Each Container includes standard [General](./configuring-fragments/general-settings-reference.md) and [Styles](./configuring-fragments/styles-reference.md) options. You can also configure its width and add a link to it. See [Container Options](./configuring-fragments/general-settings-reference.md#container-options) for more information.

## Grids
<!--TASK: Add Image-->
The Grid Fragment adds multiple drop zone modules to a Page or Template. These modules can be arranged horizontally and vertically.

In addition to standard [General](./configuring-fragments/general-settings-reference.md) and [Styles](./configuring-fragments/styles-reference.md) options, you can determine the number of modules for each Grid, the number of modules per row, and the vertical alignment of each module's content. You can also manually adjust the width of each module and add or remove padding between them. See [Grid Options](./configuring-fragments/general-settings-reference.md#grid-options) for more information.

## Additional Information

* [Default Fragments Reference](./default-fragments-reference.md)
* [Saving Fragment Compositions](./saving-fragment-compositions.md)
* [Configuring Fragments](./configuring-fragments.md)
