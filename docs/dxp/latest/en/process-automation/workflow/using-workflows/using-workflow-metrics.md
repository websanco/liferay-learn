# Using Workflow Metrics

> Subscription

_Workflow Metrics_ gives insight into the time spent to complete certain workflow events. To use it, set up deadlines on a workflow process's events. These deadline configurations are referred to as SLAs (Service Level Agreements). Once defined, Workflow Reports measure compliance with the SLAs. They're like a contract between the workflow participants and Users submitting workflow items. _Workflow Reports_ shows data for all processes with SLAs, including each workflow item's SLA status: on time or overdue.

```important::
   * *Editing a Workflow with SLAs:* Editing a workflow (e.g., removing nodes, editing a task name) with SLAs defined on it may invalidate the SLA for items already in the workflow/SLA pipeline.

   * *Creating or Editing SLAs for Active Processes:* Editing an SLA's duration or defining a new SLA while items are already in the workflow process causes a recalculation for all instances currently in the workflow. Completed workflow instances are not recalculated.
```

## Prerequisites

To use _Workflow Metrics_, you must be using Elasticsearch to index your DXP data. To learn more, read the [Installing Elasticsearch](../../../using-search/installing-and-upgrading-a-search-engine/elasticsearch/installing-elasticsearch.md) article.

## Adding SLAs

1. Navigate to the _Control Panel_ &rarr; _Workflow_ &rarr; _Metrics_.
1. Click on the title of the Process.
1. If there's no SLA for the process, a warning message stating as much appears. Click the _Add a new SLA_ link from the warning to access the New SLA form directly.

   Alternatively, click the Options (![Options](../../../images/icon-options.png)) menu and select _SLA Settings_.

   ![Add SLAs to a workflow definition from the Metrics application.](./using-workflow-metrics/images/01.png)

1. On the SLAs screen, click the _Add_ button (![Add](../../../images/icon-add.png)).
1. In the New SLA form, give the SLA a Name and Description.
1. Define the time frame for the SLA, specifying three things:

    * **Start**: Enters Task: Review
    * **Pause**: On Task: Update
    * **Stop**: Process Ends: Approved

1. Define the duration (i.e., the deadline) for the SLA. Fill out at least one of the two time boxes.

    * **Days:** 1
    * **Hours:** 00:00

    ![SLA example](./using-workflow-metrics/images/03.png)

1. Click _Save_.

![Manage SLAs from the SLAs screen.](./using-workflow-metrics/images/02.png)

### Valid Start and Stop Events

Any workflow task can be used as a start or end parameter for the SLA.

When the item makes it to the event defined here, the SLA timer begins counting. Choose between these options:

| Start Event | Description |
| --- | --- |
| The Start Node | The SLA timer starts when entering the _created_ node. |
| Entry into a Task | The SLA clock begins when the workflow transitions to the task.
| Exit from a Task | The SLA clock begins when the workflow transitions out of the task. For example, the SLA timer starts when leaving the _Review_ Task. |

If the item makes it to the Stop event before the defined SLA duration (the deadline), it's _On Time_ according to the SLA. If it fails to make it to the Stop event in the specified duration, it's _Overdue_. When defining the tasks to act as the SLA's Stop Events, choose between these options:

| Stop Event | Description |
| --- | --- |
| Entry into a Task | The SLA clock stops when the workflow transitions to the task (for example, an _Update_ Task). |
| Exit from a Task | The SLA clock stops when the workflow transitions out of the task (for example, a _Review_ Task). |
| The end node | The SLA timer stops when entering the _approved_ node. |

The Pause field defines an event in the workflow when time should stop counting. For the Single Approver workflow, you might choose to pause the SLA timer when the item is in the Update task. The SLA can be paused at any task that falls between the start node and the end node, and it is defined by setting the node(s) when the SLA should be paused. _The SLA timer is paused the entire time a workflow item is in the specified node_.

### Durations

| Unit of Time | Instruction |
| --- | --- |
| Days | Enter a whole number for number of days. If there are partial days such as 36 hours, use in combination with the _Hours_ field; express it 1 Day and 12 Hours. |
| Hours | Enter the number of hours and minutes. The values in the _Hours_ box must not exceed _23:59_; if the duration exceeds one day, use in combination with the _Days_ field. |

## Configuring the SLA Job Interval

The time interval between metrics recalculation is called the _SLA Job Interval_. The default value is 1 minute. To change it,

1. From the Applications Menu (![Applications Menu](../../../images/icon-applications-menu.png)), go to the System Settings application and open the Workflow Metrics entry.

2. Change the Check SLA Job Interval configuration value and click _Save_.

Alternatively,

1. Create a configuration file called

   ```
   com.liferay.portal.workflow.metrics.internal.configuration.WorkflowMetricsConfiguration.config
   ```

2. Give it contents like these:

   ```properties
   checkSLAJobInterval="1"
   ```

3. Place it in `[Liferay Home]/osgi/configs`.

Workflow Metrics data is read from the search engine to calculate metrics. When configuring the interval value, consider these competing performance concerns:

- A long time between metrics recalculation events corresponds with more search documents being read during each recalculation.
- A short time between metrics recalculation events corresponds with a higher frequency of read requests to the search engine.

## Re-Indexing Workflow Metrics

A _Re-Index_ action in Liferay DXP completely deletes, then recreates, search indexes based on mapping files. Because metrics are also stored in the database, there's no danger of data loss when re-indexing. To Re-index Workflow Metrics on Liferay 7.3,

1. From the Metrics application, open the Options menu (![Options](../../../images/icon-options.png)) and click _Settings_.

2. Now in the Workflow Index Actions screen, click _Reindex All_ for the Workflow Indexes entry.

   This option acts on every index in the Workflow Metrics application. More granular options are available as well.

On Liferay 7.2, Workflow Metrics are re-indexed from the Search administrative panel (Control Panel &rarr; Configuration &rarr; Search).

A re-index of Workflow Metrics is required when the [search engine](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md) is first configured and each time it's upgraded. A good rule of thumb is that Workflow Metrics should be re-indexed each time Liferay DXP's main search indexes are re-indexed.

From the overall metrics of a workflow process down to the details on a single item in the workflow, the new Workflow Metrics functionality gives you insights into the time it takes to _get things done_ in Liferay DXP.

## Additional Information

* [Creating Tasks in the Workflow Designer](https://help.liferay.com/hc/articles/360028821932-Creating-Tasks-in-the-Workflow-Designer)
* [Workflow Task Nodes](../developer-guide/workflow-task-node-reference.md)
