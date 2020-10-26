# Condition Node Reference

> Subscribers

_Condition_ nodes are best used when you have multiple reviewers and must determine which reviewer is to be assigned. For example, depending on the type of document, it might require review by the Legal department or the Marketing team.

Much like [Fork and Join nodes](./forks-and-joins-reference.md) node, a _Condition_ node splits the review path. However, unlike the _Fork_ node which requires the corresponding _Join_ node, the _Condition_ node stands alone. It acts as a gatekeeper by checking whether the specified conditions are met then assigning the asset to the right reviewer based on the answers.

The _Condition_ node is not used in the default [Single Approver definition](../workflow-designer-overview/resources/single-approver-definition.xml). For demonstration purposes, we are using the [Category Specific Definition](../workflow-designer-overview/resources/category-specific-definition.xml) instead.

To view how the Condition node is configured:

1. Navigate to the _Global Menu_ &rarr;  _Applications_ &rarr; _Workflow_ &rarr; _Process Builder_.
1. Click the (![Add icon](../../../../images/icon-add.png)) to add a new workflow.
1. In the Workflow Designer Canvas, enter a name for the workflow.
1. Click the _Source_ tab.
1. Click _import a file_.
1. Upload the [Category Specific Definition](../workflow-designer-overview/resources/category-specific-definition.xml).
1. Click the _Diagram_ tab.
1. Double Click on the _Condition_ node to configure the node's properties.
1. Click _Value_ next to _Script_.
1. You can view the view the following script:

    ```java
    import com.liferay.asset.kernel.model.AssetCategory;
    import com.liferay.asset.kernel.model.AssetEntry;
    import com.liferay.asset.kernel.model.AssetRenderer;
    import com.liferay.asset.kernel.model.AssetRendererFactory;
    import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    import com.liferay.portal.kernel.workflow.WorkflowHandler;
    import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

    import java.util.List;

    String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

    WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

    AssetRendererFactory assetRendererFactory = workflowHandler.getAssetRendererFactory();

    long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

    AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);

    AssetEntry assetEntry = assetRendererFactory.getAssetEntry(assetRendererFactory.getClassName(), assetRenderer.getClassPK());

    List<AssetCategory> assetCategories = assetEntry.getCategories();

         returnValue = "Content Review";

    for (AssetCategory assetCategory : assetCategories) {
    String categoryName = assetCategory.getName();

    if (categoryName.equals("legal")) {
        returnValue = "Legal Review";

        return;
        }
    }

    ```

    ![Add the Groovy Script which determines the review path.](./condition-node-reference/images/01.png)

1. Click _Save_ or _Cancel_ to return to the Canvas.

![The Category Specific Approval definition starts with a Condition node.](./condition-node-reference/images/02.png)

Notice how the _Condition_ node is connected to three different Task nodes:

* The _legal-review_ node; if the document is a certain type, then the asset is sent to the Legal Department;
* The _content-review_ node; otherwise, the asset is sent to the Marketing team;
* The _update_ node; if the asset is rejected, it is assigned back to the original creator to make the necessary changes.

See [Creating Workflow Tasks](./creating-workflow-tasks.md) and [Task Node Reference](./task-node-reference.md) to learn how to configure Task nodes.

```tip::
   The `returnValue` variable is the variable that points from the condition to a transition, and its value must match a valid transition in the workflow definition.
```

## Additional Information

* [Managing Workflows](../managing-workflows.md)
* [Workflow Designer Nodes Overview](./workflow-designer-nodes-overview.md)
* [Forks and Joins Reference](./forks-and-joins-reference.md)
* [Condition Node Reference](./condition-node-reference.md)
* [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)
