# Migrating from Audience Targeting to Segmentation and Personalization

Starting Liferay DXP 7.2, Liferay integrates all the Segmentation and Personalization functionality in the core product. You don't longer need to use the Audience Targeting app's functionality, but you must migrate the Audience Targeting segments into the core Liferay Segmentation.

There are three steps in this migration:

1. Upgrade to Liferay DXP 7.2+.
1. Migrate custom rules.
1. Migrate behavior-based features.

First, to upgrade to the latest version of Liferay DXP, follow the [upgrade guide](../../../installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.md). Most of your Audience Targeting configuration is automatically transferred into Liferay Segmentation.

Next, re-evaluate the Audience Targeting custom rules considering the Liferay Segmentation functionality. Some custom rules may have an equivalent (see [Migrating User Segments](./migrating-user-segments.md) for more information), but you may need to [migrate other rules manually](). You  

[Introduction to Segmentation Development](../developer-guide/introduction-to-segmentation-development.md)

Finally, you must migrate the behavior-based functionality in Audience Targeting. Starting Liferay DXP 7.2+, you manage behavior-based rules using Analytics Cloud. For more information, see the [Analytics Cloud documentation](https://learn.liferay.com/analytics-cloud/latest/en/individuals-and-segments/segments/segments.html)

## Related Information

- [Migrating from Audience Targeting to Segmentation and Personalization](./migrating-to-segmentation-and-personalization.md)
- [Manually Migrating from Audience Targeting](./manually-migrating-from-audience-targeting.md)
- [Creating and Managing User Segments](./segmentation/creating-and-managing-user-segments.md)