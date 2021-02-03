# Manually Migrating from Audience Targeting

Due to the similarities between Audience Targeting user segments and Liferay DXP 7.2+ Segments, certain data is migrated automatically during the Liferay upgrade process. However, some Audience Targeting rules do not have a direct equivalent in Liferay DXP 7.2+. You can find the recommended solution for each rule type here.

## User Attribute Rules

Some User Attributes, like Gender or Age, do not have a direct equivalent in Liferay DXP 7.2. User Attributes retrieved from external sources like Facebook also do not have a replacement. To replace these, you must [create a custom user field](../../../users-and-permissions/devops/adding-custom-fields-to-users.md) and use that to define your new Segment.

## Session Rules

For Session attributes that do not have a direct equivalent, use a URL field for the current URL or a previously visited URL on your site as criteria. For more advanced session tracking needs, use a Cookie.

## Behavior Rules

Starting Liferay DXP 7.2+, you manage behavior-based rules using Analytics Cloud. For more information, see the [Analytics Cloud documentation](https://learn.liferay.com/analytics-cloud/latest/en/individuals-and-segments/segments/segments.html).

## Migrating Custom Rules

Before migrating to Liferay DXP 7.2+, you should re-evaluate the Audience Targeting custom rules considering the Liferay Segmentation functionality. Start by checking how the [Liferay Segments' properties] can replace your Audience Targeting custom rules 





## Migrating Display Properties

The method you use to personalize content with Audience Targeting determines the way to personalize this content in Liferay Segmentation.

### User Segment Content Display

If you use the User Segment Content Display in Audience Targeting, you can define manual content sets with personalized variationin Liferay DXP 7.2+.

### Asset Publisher Personalization

To display a dynamic list of content for different audiences in Audience Targeting, you use the Asset Publisher with the Segments filter. To achieve the same functionality in Liferay Segmentation, you can create a dynamic contente set with personlaized variots and display 
Using the  you can display 

## Related Information

- [Create a Custom User Field](../../../users-and-permissions/devops/adding-custom-fields-to-users.md)
- [Analytics Cloud Segments](https://learn.liferay.com/analytics-cloud/latest/en/individuals-and-segments/segments/segments.html)

