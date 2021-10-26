# Events Analysis

As individuals visit and interact with your site, Analytics Cloud tracks these events. Many of these events that take place on your Liferay DXP instance are set up to be tracked out-of-the-box. You can also [define and track custom events](./tracking-events.md) by adding code snippets to your site. Use the events analysis tool to analyze and filter events based on different attributes. To analyze an event, 

1. Navigate to *Touchpoints* &rarr; *Events*. 

1. In the Analyze row, click the _Add_ icon (![Add](../../images/icon-add.png)) and select an event to analyze. Or use the search bar to find a specific event.

   ![Select an event to analyze.](./events-analysis/images/01.png)

   The Default events are already tracked by Analytics Cloud. The Custom events are events that you have defined and set up. See [Definitions for Events](../../workspace-data/definitions/definitions-for-events.md) to learn more.

   Hover over a specific event in the drop-down list to see the event's description. Click the _Pencil_ icon (![Pencil](../../images/icon-edit.png)) to edit the description.

1. In the Breakdown row, click the _Add_ icon (![Add](../../images/icon-plus.png)) and select breakdown attributes. 

    ![Select breakdowns for your event.](./events-analysis/images/02.png)

    Up to three attributes can be selected in the Breakdown row. Note that each type of event is made up of different attributes. Global attributes are attributes that are collected for all types of events. See [Event Attributes](../../workspace-data/definitions/definitions-for-event-attributes.md) to learn more.

    Hover over a specific attribute in the drop-down list to see the attribute's description and data typecast. Click the _Pencil_ icon (![Pencil](../../images/icon-edit.png)) to edit the description or data typecast.

    Analytics Cloud automatically tries to detect and cast the datatype based on the first data collected for that attribute. Data is cast as either boolean, date, duration, number, or string. The data typecast determines the parameters available for filtering the analysis. For example, selecting an attribute that has a date typecast has filtering options such as a before or after time period. Date, duration, and number typecasts also have a grouping feature to group the data by a set number. For example, a date typecast enables grouping the data by day, month, or year. 

    ![For date, duration, and number, select how to group the data.](./events-analysis/images/03.png)

1. In the Filter row, click the _Add_ icon (![Add](../../images/icon-plus.png)) and select attributes to further filter the event. The drop-down list shows all available attributes that can be used for filtering. The events analysis tool gives the flexibility to filter by attributes that are both related or unrelated to the events and breakdowns selected. 

1. The default time period is set to Last 30 days. Click the drop-down arrow to change to a different time period. Place a check on *Compare to Previous* to compare the event data with a previous time period.

    ![Select a different time period or compare to previous data.](./events-analysis/images/04.png)

    Note that there are three tabs (Total, Unique, and Average) to view the breakdown of the event. The Total tab shows the total number of events triggered by all users. The Unique tab shows the total number of unique users who triggered the event. The Average tab shows the average number of events per user.

1. To analyze a different event or see a different breakdown click the close icon (![Close](../../images/icon-close.png)) and close the event or breakdown. Make a new selection to see a new event analysis.

## Additional Information

* [Definitions for Events](../../workspace-data/definitions/definitions-for-events.md)
* [Definitions for Event Attributes](../../workspace-data/definitions/definitions-for-event-attributes.md)
