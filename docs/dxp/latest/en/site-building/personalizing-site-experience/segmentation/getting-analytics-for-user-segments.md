# Getting Analytics for User Segments

> **Subscribers**

To use Analytics Cloud with User Segments, you must first connect your DXP data source to Analytics Cloud and enable synchronization of users and analytics. For more information about Analytics Cloud, including instructions for connecting it with DXP, see the official [Analytics Cloud Documentation](https://learn.liferay.com/analytics-cloud/latest/en/index.html).

```{important}
Synchronization with Analytics Cloud is not instant, so once you have connected Analytics Cloud and Liferay DXP, you must first wait for the users and data to synchronize. After that completes, you can create Segments in Analytics Cloud to capture data in DXP.
```

Follow these steps to get Segment analytics:

1. [Create a Segment in Analytics Cloud](https://learn.liferay.com/analytics-cloud/latest/en/people/segments/creating-segments.html) if you haven't already.

    ```{note}
    Only Segments that contain at least one member are synchronized with Liferay DXP. This means that empty Segments created with Analytics Cloud are unavailable to use on Liferay DXP.
    ```

1. Once the Segment is synced, go to the *Segments* page.
1. Click on the new Segment to view and customize it.

> Product image may differ slightly depending on your Liferay DXP version.

![When you see Analytics Cloud Segments in the list of Segments, they are marked with the Analytics Cloud icon.](./getting-analytics-for-user-segments/images/02.png)

When you click on the Analytics Cloud Segment, you are directed to Analytics Cloud to edit the Segment and change your criteria. You cannot edit Analytics Cloud Segments in Liferay DXP.

## Related Information

* [Assigning Roles to User Segments](../../../users-and-permissions/roles-and-permissions/assigning-roles-to-user-segments.md)
* [Creating User Segments](./creating-and-managing-user-segments.md)
* [Personalizing Collections](../experience-personalization/personalizing-collections.md)
