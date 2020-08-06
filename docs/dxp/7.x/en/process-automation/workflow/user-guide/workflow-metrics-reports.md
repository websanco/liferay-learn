# Workflow Metrics: Reports

Open the Workflow Metrics from the Applications Menu (![Applications Menu](../../../images/icon-applications-menu.png)) (Control Panel &rarr; Workflow &rarr; Metrics) and you immediately see metrics on each workflow installed in the system.

![In this view, the only process with pending items is the Auto Insurance Application.](./workflow-metrics-reports/images/06.png)

A table view of all installed workflow processes shows you how many items are overdue, how many are on time, and how many are pending in the workflow process.

Access more detailed reports by clicking on one of the workflow processes.

The Reports UI has two main views, represented as tabs: _Dashboard_ and _Performance_.

The Dashboard view is displayed by default. Included are items currently in the workflow process, even those untracked by the SLA. Untracked items include those in the paused step of the workflow and items outside the scope of the configured SLA duration.

The Performance view shows metrics for items that have completed processing in the workflow.

```note::
   **Requires Elasticsearch:** To use Workflow Metrics, you must be using Elasticsearch to index your Liferay DXP data. Read `here <../../../using-search/installing-and-upgrading-a-search-engine/elasticsearch.rst>`_ to learn about installing Elasticsearch.
```

## Understanding the Dashboard

Three valuable reports on pending items are provided in the Dashboard: the Pending Items overview, the Workload by Step table, and the Workflow by Assignee table.

![The Dashboard tab shows on the pending items in the process.](./workflow-metrics-reports/images/11.png)

**Pending Items:** Pending Items shows you the overview of items by their SLA status. Drill down by clicking on any of the statuses to see the specific items that are enumerated in Pending Items.

**Workload by Step:** Workload by Step shows a breakdown of the items that are in each step of the workflow process, by their SLA status (Overdue or On Time).

**Workload by Assignee:** The Workload by Assignee chart shows a breakdown of the workflow process's assignees, in descending order by the number of items in their queue. This chart offers a valuable glimpse, but click the _View all Assignees_ link to see the [All Items](#viewing-all-workflow-items) view, featuring a more robust table with better filtering and search capabilities.

## Understanding Process Performance

The *Performance* tab shows metrics for items that have completed the workflow process. The time period is configurable for each data presentation (with a default of 30 days).

**Completed Items:** See overview metrics of the SLA status for all completed items. Untracked items are included here as well.

![View the completed items, broken down by SLA status.](./workflow-metrics-reports/images/02.png)

**Completion Velocity:** A line chart displays the completion rate for the workflow process. The default display shows the number of completed workflow instances per day, for the last 30 days.

The overall completion rate for the time period is displayed in the top of the chart (as _Inst/timeUnit_), while the trend-line is presented in the chart body. The overall performance metric and the chart body are updated when you select a new time period; the time unit changes depending on the total time period you're measuring. For the Last 30 Days duration and longer periods, the time unit is configurable (instances per day, week, or month):

![View the completion rate of items in a workflow process.](./workflow-metrics-reports/images/03.png)

**Performance by Step:** See how many SLA breaches each step is responsible for and the step's average completion time. If the step is part of at least one SLA definition, discover how many total SLA breaches have occurred at the step and the proportion (as a percentage) of the total breaches that occurred at this step of the process.

![View the performance of each workflow step.](./workflow-metrics-reports/images/04.png)

To see a full view of all the step performance metrics, click _View All Steps_.

**Performance by Assignee:** See each workflow assignee's average completion time and total completed tasks.

![View the completion rate of items in a workflow process over time.](./workflow-metrics-reports/images/05.png)

To see a full view of the workflow assignees, click _View All Assignees_.

## Filtering Metrics by Time

The time duration for which metrics are calculated and displayed is configurable.

**Today**: Calculate _Inst/Hour_ from _00:00_, or _12:00 AM_, of the current day until the current time (rounded to the nearest whole hour).

**Yesterday**: Calculate _Inst/Hour_  From _00:00-23:59_, or _12:00 AM to 11:59 PM_, of the previous day.

**Last 7 Days**: Calculate _Inst/Day_. The current day counts as 1 day, so this is from 6 days ago to the current day.

**Last 30 Days**: Calculate the _Inst/Week_ or the _Inst/Day_. The current day counts as 1 day, so this is from 29 days ago to the current day.

**Last 90 Days**: Calculate the _Inst/Month_, _Inst/Week_, or _Inst/Day_. The current day counts as 1 day, so this is from 89 days ago to the current day.

**Last 180 Days**: Calculate the _Inst/Month_ or _Inst/Week_. The current day counts as 1 day, so this is from 179 days ago to the current day.

**Last Year**: Calculate the _Inst/Month_ or _Inst/Week_. The current day counts as 1 day, so this is from 364 days (365 for a leap year) ago to the current day.

## Viewing all Workflow Items

There's a table view for all the items in the workflow that provides handy filters for detailed inquiries. To access it, hover over the SLA status you're interested in, from either the Dashboard or Performance tabs. Click on it to enter the All Items screen from the overview report and a more detailed table appears, including the following columns:

**ID**: This is the workflow item's numeric identifier to the system. Importantly, you can click this to enter the Item Detail view.

**Item Subject**: This shows a human readable summary of the item, to help administrators identify the item.

**Process Step**: This identifies where the item is in the workflow.

**Created By**: This shows the user name of the submitting User.

**Creation Date**: This shows the date and time the item was submitted to the workflow.

The All Items view can be filtered so you can find the subset of items you want to analyze.

### Filtering by SLA Status

Filter items based on whether they're Overdue, On Time, or Untracked.

![Filter by SLA status: Overdue, On Time, or Untracked.](./workflow-metrics-reports/images/10.png)

**Overdue**: Overdue items have breached at least one SLAs defined deadline.

**On Time**: On Time items have not breached _any_ SLA deadline.

**Untracked**: Untracked items are items in the workflow process that aren't currently under the purview of an SLA. The can be in a task identified as a _Pause_ in the SLA, or perhaps outside the scope of the SLA entirely, if the SLA isn't defined for the entire process (Process Begins to Process Ends in the SLA Definition screen).

### Filtering by Process Status and Completion Period

Filter items based on whether they're Pending or Completed in the workflow process.

If you filter by the Completed status, you'll get an additional filtering option: filter items by the Completion Period.

![Filter by Process Status and Completion Period.](./workflow-metrics-reports/images/09.png)

### Filtering by Process Step

Filter items based on where they are in the workflow definition. For example, in the Single Approver workflow process, you can choose to see a report including all items in the Review task. This is different for each workflow definition.

### Combining Filters

Use a combination of filters to find just the items you need to see. For example, below are all items in the Single Approver process's Review task that have the status Completed or Pending, whether On time or Overdue. Untracked items aren't shown.

![Combine filters to see just the items you want.](./workflow-metrics-reports/images/08.png)

## Item Details

To see the metrics for a single workflow process item, click the ID field while in the All Items view. A pop-up shows you more detailed information on the item.

![Item Details include SLA status information and whether the item is Resolved or Open.](./workflow-metrics-reports/images/07.png)

From here you can view detailed information about the asset and even click *Go to Submission Page*, which redirects you to the item's view in the Submissions section of the Control Panel.

The top of the Item Detail view is important. It shows you the information about the due date for the item in the SLA, and its SLA completion status: _Open_ or _Resolved_.

**Open**: The defined SLA goals are not yet met. Open items can be of status Overdue or On Time.

**Resolved**: The defined SLA goals are completed. Resolved items can be of status Overdue or On Time.

## Re-Indexing Workflow Metrics

A _Re-Index_ action in Liferay DXP completely deletes, then recreates, search indexes based on mapping files. Because metrics are also stored in the database, there's no danger of data loss when re-indexing. To Re-index Workflow Metrics,

1. From the Metrics application, open the Options menu ([Options](../../../images/icon-options.png)) and click _Settings_.

2. Now in the Workflow Index Actions screen, click _Reindex All_ for the Workflow Indexes entry. 

   This option acts on every index in the Workflow Metrics application. More granular options are available as well.

A re-index of Workflow Metrics is required when the [search engine](../../../using-search/installing-and-upgrading-a-search-engine/elasticsearch.rst) is first configured and each time it's upgraded. A good rule of thumb is that Workflow Metrics should be re-indexed each time Liferay DXP's main search indexes are re-indexed.

From the overall metrics of a workflow process down to the details on a single item in the workflow, the new Workflow Metrics functionality gives you insights into the time it takes to _get things done_ in Liferay DXP.

## Additional Information

* [Using Workflow Metrics](./using-workflow-metrics.md)
* [Building Workflows](./building-workflows.md)
