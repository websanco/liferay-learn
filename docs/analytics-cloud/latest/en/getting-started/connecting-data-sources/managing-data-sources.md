# Managing Data Sources

Liferay Analytics Cloud requires two kinds of data. First, web analytics data on user interactions with Liferay DXP Pages and Assets. Second, profile data on the users themselves. Analytics Cloud syncs these two kinds of data so that you can see how users are interacting with your site and who those users are. First, provide Analytics Cloud with data sources.

Both kinds of data can be obtained from a Liferay DXP instance. If you have other user profile data in addition to what is already stored in Liferay DXP user models, you can import it from a CSV file or add with contact data from a Salesforce system.

Contact data is merged and consolidated into a single customer view. Liferay DXP data sources let you select Organizations and User Groups to sync. Individual contacts are matched by email address. You can define the contact data model using whatever field names and types you want, but Analytics Cloud makes it easy to define trivial model fields by suggesting values.

## Viewing Your Data Sources

Your data sources can be viewed from the navigation panel.

1. Select Settings.

1. Click on Data Sources.

The Data Sources page appears and lists all existing data sources.

![View, edit, and add data sources from the Data Sources page.](managing-data-sources/images/01.png)

Unless a teammate has already added a data source, the list is empty. To add a new data source, see the following tutorials:

* [Adding a Liferay DXP Data Source](./connecting-liferay-dxp-to-analytics-cloud.md)
* [Adding a CSV Data Source](../../individuals-and-segments/individual-profiles/adding-a-csv-data-source.md)
* [Adding a Salesforce Data Source](../../individuals-and-segments/individual-profiles/adding-a-salesforce-data-source.md)

Once you’ve created your data sources, you might need to modify them from time to time.

## Modifying a Data Source

The Data Sources page shows all of the Data Sources added to Analytics Cloud and can be searched by keyword. You can edit data sources in the following ways:

* Name of the data source
* [Contact mapping](../../individuals-and-segments/individual-profiles/mapping-contact-data.md)
* Enable/Disable Contact or Analytics sync

Here’s how to delete a data source:

1. Click on the listed data source. The data source’s page appears.

1. Click *Delete Data Source*.

The Data Source is deleted.

## Next Steps

* [Connecting Liferay DXP to Analytics Cloud](./connecting-liferay-dxp-to-analytics-cloud.md)
