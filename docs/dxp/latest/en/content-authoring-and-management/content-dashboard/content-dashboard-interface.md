# Content Dashboard Interface

> Available: Liferay 7.3+

The Content Dashboard interface has the following areas:

- [Content Audit Tool](#content-audit-tool) (1)
- [Contents list](#contents-list) (2)
- [Content Info sidebar](#content-info-sidebar) (3)

![The Content Dashboard user interface contains three areas of information.](./content-dashboard-interface/images/05.png)

When you [open the Content Dashboard](#accessing-the-content-dashboard), you see the [Content Audit Tool](#content-audit-tool) (1) at the top of the page and the [Contents list](#contents-list) (2) at the bottom. From the Contents list, you can access the [Content Info sidebar](#content-info-sidebar) (3) and the [Content Performance panel](../content-performance-panel/about-the-content-performance-panel.md). The Content Info sidebar and the Content Performance panel can collapse. They show additional details for each item in the Contents List.

To access the Content Info sidebar, click the *Actions Menu* (![Action Menu](../../images/icon-actions.png)) for the content and select the *Info* option. Alternatively, hover over the article and click *Info* (![Info](../../images/icon-information.png)). For information on the *View Metrics* (![Metrics](../../images/icon-analytics.png)) option, read [About the Content Performance Panel](../content-performance-panel/about-the-content-performance-panel.md).

## Accessing the Content Dashboard

1. Click on the Global Menu and, under the *Content* area, click *Content Dashboard*.
1. If the Global Menu is disabled, open the Product Menu and, under the *Site* Menu, go to *Content* &rarr; *Content Dashboard*.

    ![Open the Content Dashboard from the Global Menu.](./content-dashboard-interface/images/03.png)

## Content Audit Tool

This tool is the graphical area at the top of the Content Dashboard page. The chart represents the total number of assets for the combination of categories in the global Vocabularies you select. This chart takes into account:

- Web content articles exclusively, across all Sites and Asset Libraries.
- Content you can access.
- The existing [filters](#filtering-and-sorting-content-in-the-contents-list) in the [Contents list](#contents-list).
- A maximum of two global Vocabularies (for the *x* and *y* axes.)

![Overview of the Content Audit Tool in the Content Dashboard](./content-dashboard-interface/images/10.png)

```{tip}
If the Content Audit Tool doesn't show the expected chart, [verify your Categories](../tags-and-categories/defining-categories-and-vocabularies-for-content.md) for the global Vocabularies and ensure that [your content is categorized](../tags-and-categories/organizing-content-with-categories-and-tags.md).
```

The following video shows and overview of the Content Audit tool:

<video width="100%" height="100%" controls>
    <source src="./content-dashboard-interface/images/14.mp4" type="video/mp4">
</video>

In the Content Audit Tool interface you can find:

- Chart legend (1)
- *X*-axis Categories for the first global Vocabulary (2)
- *Y*-axis Categories for the second global Vocabulary (3)
- Bar chart (4)

![Overview of the Content Performance user interface](./content-dashboard-interface/images/04.png)

### Customizing the Vocabularies and Categories in the Content Audit Tool

By default, the bar chart shows the total number of assets for each Category in the selected global Vocabularies, but you can filter this information in different ways:

- Use the [filters](#filtering-and-sorting-content-in-the-contents-list) in the [Contents list](#contents-list).
- Filter categories in the chart by checking or unchecking the Category name in the chart legend.
- Click one of the bars to zoom-in on a specific Category's results.
- Change the Vocabularies.

To change the Vocabularies:

1. Click the *Configure Chart* icon (![gear icon](../../images/icon-settings.png)) in the Content Audit area.
1. In the dual listbox, click the Vocabulary in the *Available* or *In Use* lists and click the selection buttons (![Angle Left](../../images/icon-angle-left.png) ![Angle right](../../images/icon-angle-right.png)) to add or remove the Vocabularies you want to show.
1. Click the *Up* (![Up](../../images/icon-angle-up.png)) and *Down* (![Down](../../images/icon-angle-down.png)) buttons to order of the Vocabularies in the chart axis.
1. Click *Save*.
1. Click the *Flip Axes* (![Flip Axes](../../images/icon-change.png)) icon in the Content Audit area if you must switch the axis assigned to the selected vocabularies.

    ![Select the Vocabularies in the Content Audit Tool](./content-dashboard-interface/images/12.png)

```{note}
You can select a maximum of two Vocabularies defined at the Global Site level.
```

A gray bar denotes content without any categories in one of the selected Vocabularies. Chart labels for this content are distinctive. For instance, when you choose the Audience Vocabulary, the content that is not categorized under Audience shows the *No Audience Specified* label.

## Contents List

By default, this area shows all the content you have access to, across all sites and Asset Libraries. From here, you can sort and filter this content using built-in or custom filters. The Content Audit Tool chart changes dynamically based on the filters that you set.

In the Contents list you can find the following elements:

- Filter and Sort selector (1)
- Filter editor (2)
- Search field (3)
- List of contents (4)

![The Contents list contains several elements to help you find content.](./content-dashboard-interface/images/06.png)

In the list of contents, you can find the following columns:

| Column | Description |
| --- | --- |
| Title | Article title(*). |
| Author | Author's name. |
| Subtype | Article's [Subcategory](../tags-and-categories/defining-categories-and-vocabularies-for-content.md#creating-subcategories). |
| Site or Asset Library | Location of the article in the Liferay DXP instance. |
| Status | Article workflow status. Articles with more than one version in different statuses show all than statuses. |
| *First Vocabulary* | This is the first Vocabulary in the Content Audit Tool. |
| *Second Vocabulary* | The optional second Vocabulary in the Content Audit Tool. |
| Modified Date | Date or time of the last article update. |

(*) The *Page* (![Page](../../images/icon-page.png)) icon denotes articles based on a [Display Page Template](../../site-building/displaying-content/using-display-page-templates/about-display-page-templates-and-display-pages.md).

```{note}
The *First Vocabulary* and *Second Vocabulary* columns depend on the Vocabularies you select.
```

For each entry in the Contents list, you can access the following options using the *Actions* menu (![Actions Menu](../../images/icon-actions.png)) or hovering over the article and clicking the corresponding option:

- *View* (![View](../../images/icon-preview.png)): Opens the content based on a Display Page Template in view mode.
- *Edit* (![Edit](../../images/icon-edit.png)): Opens the content in edit mode.
- *Info* (![Information](../../images/icon-information.png)): Opens the [Content info](#content-info-sidebar) sidebar.
- *View Metrics* (![View Metrics](../../images/icon-analytics.png)): Opens the [Content Performance panel](../content-performance-panel/about-the-content-performance-panel.md).

    ![Access the Content info sidebar and Content Performance panel from the Actions menu or the icons on each content row.](./content-dashboard-interface/images/11.png)

```{note}
Use the *View*, *Info*, and *View Metrics* options for content based on a Display Page Template. Use the *Edit* option when you have Edit permission for the content.
```

### Filtering and Sorting Content in the Contents List

1. Click the *Filter and Order* drop-down menu.
1. Select one of the predefined filters or a custom filter (denoted by three dots) to create your own filter criteria.
1. Select the content you want to filter.

The Filter Editor area shows your filters and the total results. To remove one or more filters, click the *Close* (![Close](../../images/icon-times.png)) button next to the filter's name, or click *Clear* to remove all filters and reset the Contents list. In addition to the predefined and custom filters, you can search and filter the assets in the Content View using keywords in the Search field. To understand how the combination of different filters works, read [Understanding How Filtering Works in the Contents List](#understanding-how-filtering-works-in-the-contents-list).

![Use the filter editor to remove a specific filter or all of them from the filter area.](./content-dashboard-interface/images/01.png)

You can also filter the content by clicking one of the bars in the [Content Audit Tool](#content-audit-tool) area and zoom-in on a specific Category's results.

![Click on one of the bars in the Content Audit Tool to filter by the bar's Category.](./content-dashboard-interface/images/08.png)

To sort the content,

1. Click the *Filter and Order* drop-down menu.
1. Under the *Order By* section, select the order for the items.

```{note}
When you close the Content Dashboard page, the default filter and sorting option are set again.
```

### Understanding How Filtering Works in the Contents List

- When you use the Categories or Tags filters with more than one criteria, the results correspond to content that meets all the criteria (see example A.)
- When you choose multiple options for the same filter criteria, the Contents list shows assets matching at least one option (see example B.)
- The criteria from different filters is combined using the `and` operator (see example C.)

| Example| Filter By | Filter Example | Contents List Results |
| --- | --- | --- | --- |
| A | Categories | Categories: *Entrepreneur* and *Family* | Only assets that belong to both the *Entrepreneur* `and` Family Categories. |
| B | Authors | Authors: *Peter* and *Linda* | Assets authored by *Peter* `or` _Linda_ `or` both. |
| C | Categories, Authors | Category is _Entrepreneur_ and Author is _Linda_ | Only assets under the *Entrepreneur* Category `and` authored by Linda. |

## Content Info Sidebar

This sidebar provides access to different metadata for the asset selected in the Contents list, including the asset owner, available languages for the content, tags and categories for the asset, or review date.

![Access the Information sidebar from the assets in the Contents list.](./content-dashboard-interface/images/07.png)

To open the Information sidebar,

1. Click the Actions Menu (![Actions Menu](../../images/icon-actions.png)) next to the asset and select *Info*.
1. Alternatively, hover over the asset and click the *Info* (![Information icon](../../images/icon-information.png)) icon.

    ![The Information sidebar provides different metadata for your content.](./content-dashboard-interface/images/09.png)

## Related Information

- [About the Content Dashboard](./about-the-content-dashboard.md)
- [About the Content Performance Panel](../content-performance-panel/about-the-content-performance-panel.md)
- [Defining Categories and Vocabularies for Content](../tags-and-categories/defining-categories-and-vocabularies-for-content.md)
