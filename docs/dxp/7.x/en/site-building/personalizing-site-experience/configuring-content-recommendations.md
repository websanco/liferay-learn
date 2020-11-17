# Configuring Content Recommendations

As users browse your Liferay DXP site and interact with your content, Liferay Analytics Cloud automatically generates and ranks topics of interest based on their browsing behavior. You can use these topics of interest to recommend related content that your users are more likely to buy or consume. For more information about content recommendations, see [Understanding Content Recommendations](./understanding-content-recommendations.md).

Content recommendations work automatically as long as the following elements are in place:

1. Analytics Cloud is connected to your Liferay DXP instance and the Site content is synchronized.
2. You create a Dynamic Collection in Liferay DXP with different content.
3. You display the Dynamic Collection for your site using an Asset Publisher, a Collection Display Fragment, or a Collection Page.

## Connecting Analytics Cloud to Your Liferay DXP Instance

You must connect Analytics Cloud to your Liferay DXP instance to provide content recommendations to your users.

To learn how to connect Analytics Cloud to Liferay DXP and synchronize the site's content, see [Connecting Liferay DXP to Analytics Cloud](../../../../../../analytics-cloud/latest/en/getting-started/connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.md).

## Create a Dynamic Collection

To show users content recommendation based on their browsing behavior, you must use a Dynamic Collection. The Collection is the component that connects the topics of interest with the content recommendations in your site. Using a Collection, you define what type of content you want to recommend and, optionally, to what audiences.

To create the Dynamic Collection, follow the instruction in [Creating Collections](../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md#creating-a-dynamic-collection).

Consider the following information when you create the Dynamic Collection:

- You must enable the *Content Recommendation* option in the Collection. When this option is disabled, the Collection does not show recommended content.
- The Filter criteria in your Dynamic Collection is optional. For example, if you use the "promotions" tag for your content and only want to recommend promotions, you can use this tag.
- If you want to target content recommendations to a specific group of users (for example, "website visitors in Germany"), you can combine the Dynamic Collection with a Segment using a Personalized Variations. 

## Display the Dynamic Collection for Your Site

In Liferay DXP 7.2, you show the Content Sets using a Display Page with an Asset Publisher. In DXP 7.3+, in addition to the Asset Publisher, you can show the Collection's content using the Collection Display Fragment and the Collection Pages.

```note:
Collections are named Content Sets in Liferay DXP 7.2.
```

### Display the Collection or Content Set in a Display Page Using the Asset Publisher

> Liferay DXP 7.2+

To display the Collection using a Display Page and the Asset Publisher, you must complete the following steps:

1. Create a Display Page Template
1. Create a Display Page using the Template
1. Configure an Asset Publisher with the Collection (Liferay 7.3+) or Content Set (Liferay 7.2)

### Display the Collection using a Collection Page or Collection Display Fragment

> Liferay DXP 7.3+

A Collection Page is a type of page linked to a Collection. To show the Collection using a Collection page, see the [Displaying Collections Using a Collection Page](../../content-authoring-and-management/collections-and-collection-pages/displaying-collections-and-collection-pages.md#displaying-collections-using-a-collection-page) section in the [Displaying Collections and Collection Pages](../../content-authoring-and-management/collections-and-collection-pages/displaying-collections-and-collection-pages.md) topic.

A Collection Display Fragment is a type of Fragment that shows a Collection. You can use this Fragment to show your Collection on any Content Page, Page Template, or Display Page. To configure the Collection Display Fragment, see the section [Displaying Collections Using a Collection Display Fragment](../../content-authoring-and-management/collections-and-collection-pages/displaying-collections-and-collection-pages.md#displaying-collections-using-a-collection-display-fragment) in the Displaying [Collections and Collection Pages](../../content-authoring-and-management/collections-and-collection-pages/displaying-collections-and-collection-pages.md) topic.

## Analyzing Content Recommendations

Analytics Cloud generates the topics of interest for users, based on users' behaviour in your site. To analyze these interests, 

## Related Information

- [[Categories and Tags]]()
- [[Collections]]()
- [[Individual Profiles]](https://learn.liferay.com/analytics-cloud/latest/en/individuals-and-segments/individual-profiles/individual-profiles.html)
- [[Interests]](https://learn.liferay.com/analytics-cloud/latest/en/individuals-and-segments/interests.html)