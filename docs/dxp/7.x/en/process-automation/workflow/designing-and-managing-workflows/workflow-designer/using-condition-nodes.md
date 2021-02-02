# Using Condition Nodes

> Subscribers

_Condition_ nodes are best used when you have multiple reviewers and must determine which reviewer should be assigned. For example different documents might require review by the Legal department or the Marketing team.

Much like [Fork and Join nodes](./using-forks-and-joins.md), a _Condition_ node splits the review path. However, unlike the _Fork_ node which requires the corresponding _Join_ node, the _Condition_ node stands alone. It acts as a gatekeeper by checking whether the specified conditions are met, and then assigns the asset to the right reviewer based on those conditions.

The [Category Specific Definition](../workflow-designer-overview/resources/category-specific-definition.xml) contains an example of a Conditions node:

1. Navigate to the _Global Menu_ &rarr;  _Applications_ &rarr; _Workflow_ &rarr; _Process Builder_.
1. Click the (![Add icon](../../../../images/icon-add.png)) to add a new workflow.
1. In the Workflow Designer Canvas, enter a name for the workflow.
1. Click the _Source_ tab.
1. Click _Import A File_.
1. Upload the [Category Specific Definition](../workflow-designer-overview/resources/category-specific-definition.xml).
1. Click the _Diagram_ tab.
1. Double Click on the _Condition_ node to configure the node's properties.
1. Click _Value_ next to _Script_.
1. The script appears: 

    ```groovy
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

    ![Add the Groovy Script which determines the review path.](./using-condition-nodes/images/01.png)

   The script loops through the asset's categories looking for the string `legal`. If it's found, workflow continues along the Legal Review path. Otherwise, it continues along the Content Review path. 

1. Click _Save_ or _Cancel_ to return to the Canvas.

![The Category Specific Approval definition starts with a Condition node.](./using-condition-nodes/images/02.png)

Notice how the _Condition_ node is connected to three different Task nodes:

* If the document is in the `legal` category, the asset is sent to the Legal Department.
* Otherwise, the asset is sent to the Marketing team.
* The _update_ node assigns the asset back to the original creator to make changes if the asset was rejected.

See [Creating Workflow Tasks](./creating-workflow-tasks.md) and [Using Task Nodes](./using-task-nodes.md) to learn how to configure Task nodes.

## Additional Information

* [Managing Workflows](../managing-workflows.md)
* [Workflow Nodes](./workflow-nodes.md)
* [Using Forks and Joins](./using-forks-and-joins.md)
* [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)
