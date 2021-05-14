# Managing Data Sources

After [connecting data sources](../connecting_data_sources.html) to your workspace, you can view and make changes to your data sources from the settings page. Navigate to *Settings* &rarr; *Workspace Data* &rarr; *Data Sources*. A list of all the data sources connected to your workspace is displayed.

![See the list of connected data sources in settings.](./managing-data-sources/images/01.png)

If you haven't already connected a Liferay DXP instance, see [connecting Liferay DXP to Analytics Cloud](../connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.md).

Other data sources such as Salesforce data or CSV data can also be added. See [adding a Salesforce data source](../connecting-data-sources/adding-a-salesforce-data-source.md) or [adding a CSV data source](../connecting-data-sources/adding-a-csv-data-source.md).

To view or make changes to your Liferay DXP data source, click on the name of the connected data source.

![Click on the data source name to open a new page.](./managing-data-sources/images/02.png)

The *Current Status* panel on the right shows the status of the data source. **Active** for a data source that is connected and syncing data. **Connected** for a data source that is connected but does not have Sites and Contacts configured. **Disconnected** for a data source that is no longer connected.

**Name:** The name of the data source. To rename, click the edit icon (![Edit](../images/icon-edit.png)) and input a new name. Click the checkmark to save.

**DXP Instance ID:** This displays the unique ID of your DXP instance. 

If you choose to disconnect the DXP instance from the workspace click the *Disconnect* button. A new form will pop up to confirm your decision. Note, disconnecting the data source will stop any syncing of analytics data.

**Synced Sites:** This shows the status of whether your DXP Sites are synced with Analytics Cloud. To configure, see [syncing sites to a property](../connecting-data-sources/scoping-sites-and-individuals-using-properties.html#syncing-sites-to-a-property).

**Synced Contacts:** This shows the status of whether your DXP contacts are synced with Analytics Cloud. To configure, see [syncing contacts to a property](../connecting-data-sources/scoping-sites-and-individuals-using-properties.html#syncing-contacts-to-a-property)

Note that this manage page will look different for a Salesforce data source or a CSV data source.

## Deleting a Data Source

If you choose to delete the data source, click the *Delete Data Source* button at the top right of the page. A new form will pop up to confirm your decision. Note, deleting the data source will remove the connection and delete all synced data.