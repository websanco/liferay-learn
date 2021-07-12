# Manually Migrating from Audience Targeting

Due to the similarities between Audience Targeting user segments and Liferay 7.2+ Segments, most of your configuration is automatically transferred into Liferay Segmentation during the upgrade (see [Migrating User Segments](./migrating-user-segments.md) for more information.) However, some Audience Targeting rules do not have a direct equivalent in Liferay 7.2+ Segmentation. You can find the recommended solution for each rule type here.

## User Attribute Rules

Some User Attributes, like Gender or Age, do not have a direct equivalent in Liferay 7.2+. User Attributes retrieved from external sources like Facebook also do not have a replacement. To replace these, you must [create a custom user field](../../../users-and-permissions/users/adding-custom-fields-to-users.md) and use that to define your new Segment.

## Session Rules

For Session attributes that do not have a direct equivalent, use a URL field for the current URL or a previously visited URL on your site as criteria. For more advanced session tracking needs, use a Cookie.

## Behavior Rules

Starting with Liferay 7.2+, behavior-based rules are managed using Analytics Cloud. For more information, see the [Analytics Cloud documentation](https://learn.liferay.com/analytics-cloud/latest/en/people/segments/segments.html).

## Migrating Custom Rules

Before migrating to Liferay 7.2+, evaluate any Audience Targeting custom rules with consideration to newer Liferay Segmentation functionality. Start by checking how the [Liferay Segments' properties](../segmentation/segments-editor-ui-reference.md) can replace your Audience Targeting custom rules.

If you need to re-implement a rule entirely, follow the information in [Introduction to Segmentation Development](../developer-guide/introduction-to-segmentation-development.md).

## Migrating Display Properties

With Audience Targeting, you could display personalized content using the User Segment Content Display or an Asset Publisher personalization. The method you use to personalize content with Audience Targeting determines the way to personalize this content in Liferay Segmentation.

| Audience Targeting Method | Liferay Segmentation Method |
| --- | --- |
| User Segment Content Display | [Manual Content Sets or Collections](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md#creating-a-manual-collection) |
| Asset Publisher Personalization | [Dynamic Content Sets](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md#creating-a-dynamic-collection) |

```note::
   For users on Liferay 7.2, Collections are referred to as `Content Sets <../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md#liferay-dxp-7-2>`_.
```

Whether you use Content Sets or Collections, you can personalize the content using [Personalized Variations](../experience-personalization/personalizing-collections.md).

In addition, the [Content Page personalization](../../../site-building/personalizing-site-experience/experience-personalization/content-page-personalization.md) feature may fulfill a use case that you were previously solving with one of the Audience Targeting methods.

## Related Information

- [Create a Custom User Field](../../../users-and-permissions/users/adding-custom-fields-to-users.md)
- [Segments Editor UI Reference](../segmentation/segments-editor-ui-reference.md)
- [Creating Collections](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md)
- [Analytics Cloud Segments](https://learn.liferay.com/analytics-cloud/latest/en/people/segments/segments.html)
