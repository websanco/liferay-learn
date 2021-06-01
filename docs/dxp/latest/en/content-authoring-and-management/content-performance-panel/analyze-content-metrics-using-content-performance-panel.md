# Analyze Content Metrics Using the Content Performance Panel

> Available: Liferay 7.3+.

You can use the Content Performance panel to analyze metrics about your content, like the number of reads and views, traffic channels, or keywords driving people to your site.

You can access the Content Performance panel using the *View Metrics* (![View Metrics](../../images/icon-analytics.png)) option for your content. For more information, see [About the Content Performance Panel](./about-the-content-performance-panel.md).

The Content Performance panel contains two different areas:

- [Engagement](#analyzing-content-engagement)
- [Search Engine Traffic](#analyzing-search-engine-traffic)

## Analyzing Content Engagement

This area shows the number of Views and Reads of your content, and how these metrics change over time. Views and Reads are two key values to understand your content performance and discover content insights. A visitor accessing one particular content may or may not read this content. To distinguish the audience that only visit the content (number of Views) from the audience engaging with the content (number of Reads), Liferay uses a specialized algorithm that considers parameters like article length, language, or scroll behavior, among others.

![The Engagement area in the Content Performance sidebar provides Reads and Views information.](./analyze-content-metrics-using-content-performance-panel/images/06.png)

By default, the Engagement area shows the total number of views and reads for the content, along with the number of views and reads in the last seven days. You can change this period in the drop-down time selector, and analyze trends over time using the backward and forward buttons (![Arrow left](../../images/icon-angle-left.png) ![Arrow right](../../images/icon-angle-right.png)). Hover over any part of the line chart to show the Views and Reads for a specific time.

```note::
   If the Engagement chart shows a zero value for a certain period, it means that the information could not be collected or analyzed for that period.
```

When your content is localized in different languages, you can view metrics for each language in the *Languages Translated Into* section. To do so, click on the language icon selector and select the language.

```note::
   The language selector is only available for localized content.
```

![You can view metrics for each language in the Content Performance panel.](./analyze-content-metrics-using-content-performance-panel/images/03.png)

## Analyzing Search Engine Traffic

This part of the Content Performance sidebar provides information about the sources driving web traffic to your content. The sources can be:

- **Organic** --- People finding the content through a search engine.
- **Paid** --- People finding the content through paid keywords.

    ![The Search Engines Traffic area in the Content Performance panel provides information about your traffic sources.](./analyze-content-metrics-using-content-performance-panel/images/07.png)

You can click the sources in the graphic (1) to access additional metrics about these sources (2), or filter the information by country. The following table describes these metrics:

| Label | Description |
| --- | --- |
| Traffic Volume | The estimated number of visitors to your page. |
| Traffic Share | Percentage of traffic your content receives from the traffic source. |
| Best Keyword | Top five keywords driving traffic through organic search |
| Best Paid Keyword | Top five keywords driving traffic through paid search. |

```note::
   Keywords are one or more words that people use to find content.
```

![Click on the traffic channels to access additional details.](./analyze-content-metrics-using-content-performance-panel/images/02.png)

## Related Information

- [About the Content Performance Panel](./about-the-content-performance-panel.md)
- [About the Content Dashboard](../content-dashboard/about-the-content-dashboard.md)
- [Defining Categories and Vocabularies for Content](../tags-and-categories/defining-categories-and-vocabularies-for-content.md)
