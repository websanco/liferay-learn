# Elasticsearch Indexes Reference

The default Liferay DXP 7.3 indexes in your installation approximate the list below (subject to change). The default global *Index Name Prefix* is `liferay-`: it can be changed in the Elasticsearch 7 connector configuration. `20101` is the generated `companyId` of a given Company in your database. It is displayed as Instance ID in the UI and represents a [Virtual Instance](../../system-administration/configuring-liferay/virtual-instances/understanding-virtual-instances.md).

| Index ID                                              | Index Type    | Index Purpose |
| ----------------------------------------------------- | ------------- | ------------- |
| liferay-0                                             | System Index  | Searching in the System Settings application |
| liferay-20101                                         | Company Index | Searching the indexed assets of the Liferay DXP Virtual Instance |
| liferay-20101-search-tuning-rankings                  | App Index     | Primary data storage for the Result Rankings application |
| liferay-20101-search-tuning-synonyms                  | App Index     | Primary data storage for the Synonym Sets application for the given virtual instance |
| liferay-20101-workflow-metrics-instances              | App Index     | Store data about Workflow Instances for the Workflow Metrics application |
| liferay-20101-workflow-metrics-nodes                  | App Index     | Store data about Workflow Nodes for the Workflow Metrics application |
| liferay-20101-workflow-metrics-processes              | App Index     | Store data about Workflow Processes for the Workflow Metrics application |
| liferay-20101-workflow-metrics-sla-instance-results   | App Index     | Storage for SLA results per Workflow Instance for the Workflow Metrics application |
| liferay-20101-workflow-metrics-sla-task-results       | App Index     | Storage for SLA results per Workflow Task for the Workflow Metrics application |
| liferay-20101-workflow-metrics-tokens                 | App Index     | Store data about Workflow Tokens for the Workflow Metrics application |
| liferay-20101-workflow-metrics-transitions            | App Index     | Store data about Workflow Transitions for the Workflow Metrics application |

```important::
   Liferay 7.2 index names are more complex, as patches have introduced changes to the index naming pattern. See `Multi-Tenant Index Names <../getting-started/whats-new-in-search-for-73.md#multi-tenant-index-names>`__ for more information.
```

```note::
   Liferay DXP provides APIs for creating and using (writing to and reading from) custom Elasticsearch indexes that remain completely under your control. See the `Developer Guide <../developer_guide.html>`__ for information on using these APIs.
```

If you have a [Liferay Commerce](https://www.liferay.com/products/commerce) subscription and it is activated in your installation, you also have these indexes:

| Index ID                                                     | Index Type    | Index Purpose |
| ------------------------------------------------------------ | ------------- | ------------- |
| liferay-20101-commerce-ml-forecast                           | App Index     | Machine Learning capabilities |
| liferay-20101-product-content-commerce-ml-recommendation     | App Index     | Recommendation services       |
| liferay-20101-product-interaction-commerce-ml-recommendation | App Index     | Recommendation services       |

## Related Information

- [Multi-Tenant Index Names](../getting-started/whats-new-in-search-for-73.md#multi-tenant-index-names)
