# Using Fragments

```{toctree}
:maxdepth: 3

using-fragments/default-fragments-reference.md
using-fragments/using-layout-elements.md
using-fragments/configuring-fragments.md
using-fragments/mapping-and-linking-fragment-elements.md
using-fragments/configuring-fragment-visibility.md
using-fragments/editing-fragment-elements.md
using-fragments/creating-dropdown-menus-with-fragments.md
using-fragments/duplicating-fragments.md
using-fragments/saving-fragment-compositions.md
using-fragments/managing-fragments.md
using-fragments/propagating-fragment-changes.md
```

Page *Fragments* are extensible and reusable drag-and-drop elements that you can use to build [Content Pages](../using-content-pages.md) and Templates (i.e., [Masters](../defining-headers-and-footers/master-page-templates.md), [Pages](../adding-pages/creating-a-page-template.md), [Display Pages](../../displaying-content/using-display-page-templates/about-display-page-templates-and-display-pages.md)). They are built using CSS, HTML, and JavaScript and can provide both structure and functionality to a Page.

![Build Content Pages and Templates with Fragments.](./using-fragments/images/01.png)

Liferay provides a variety of Fragments out-of-the-box. However, you can also develop custom Fragments using either the [Page Fragment editor](../../developer-guide/reference/fragments/page-fragment-editor-interface-reference.md) or [Fragments Toolkit](../../developer-guide/developing-page-fragments/using-the-fragments-toolkit.md). Additionally, you can [embed Liferay widgets](../../developer-guide/reference/fragments/fragment-specific-tags-reference.md#including-widgets-within-a-fragment) into Fragments or add editable fields. See [Developing Page Fragments](../../developer-guide/developing-page-fragments/developing-fragments-intro.md) to learn more.

All Page Fragments are organized into Sets, which group related Fragments for easier management and use. Each Fragment Set can also include resources that can be referenced directly in its Fragments' code. See [Including Default Resources in Page Fragments](../../developer-guide/developing-page-fragments/including-default-resources-with-fragments.md) for more information.

After adding Fragments to a Page or Template, you can configure and customize them and their sub-elements. These options can vary, though some are common to all. See [Configuring Fragments](./using-fragments/configuring-fragments.md) to learn more about available options.

```{raw} html
:file: ../../../landingpage_template.html
```

```{raw} html
:file: using-fragments/landing.html
```
