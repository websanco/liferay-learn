# Workflow Designer Overview

> Subscription Required

The Workflow Designer provides a graphical interface for creating workflows. The convenient drag and drop interface makes workflow design easier than writing XML definitions by hand. Instead, users can draw their workflows on a canvas.

![You can drag and drop nodes on the Canvas.](./workflow-designer-overview/images/04.png)

```tip::
   Alternately, you can upload XML scripts and modify the definition using the graphical interface. To learn more about uploading an XML script, see `Managing Workflows <./managing-workflows.md#uploading-a-new-workflow-definition>`_.
```

![Use the Source tab to upload an XML file.](./workflow-designer-overview/images/03.png)

The Workflow Designer supports all workflow node types:

* _Start_ and _End_ Nodes
* [_Fork_ and _Join_](./workflow-designer/using-forks-joins-and-conditions.md) Nodes
* [Condition](./workflow-designer/using-forks-joins-and-conditions.md)
* State
* [Task](./workflow-designer/creating-workflow-tasks.md)

Finally, you have the full power of Groovy (a supported Java-based scripting language) to perform necessary actions on assets being moved through your workflows.

![Users can add a Groovy script to their workflow nodes.](./workflow-designer-overview/images/05.png)

By default, only one workflow definition is installed: the Single Approver Workflow definition. You can download additional definitions here:

* [Category-Specific Definition](./workflow-designer-overview/category-specific-definition.xml)
* [Legal Marketing Definition](./workflow-designer-overview/legal-marketing-definition.xml)
* [Single Approver Definition with Scripted Assignment](./workflow-designer-overview/single-approver-definition-scripted-assignment.xml)
* [Single Approver Definition](./workflow-designer-overview/single-approver-definition.xml)

## Building Workflows

To build a new workflow or to upload one, navigate to the Global Menu (![Global Menu](../../../images/icon-applications-menu.png)) &rarr; _Process Builder_.

![Navigate to the Process Builder to manage workflows.](./workflow-designer-overview/images/01.png)

Click the (![Add icon](../../../images/icon-add.png)) to begin.

![Use drag and drop to build a workflow.](./workflow-designer-overview/images/02.png)

Each Workflow Node represents a specific point in an approval process, whether it starts the review process, approves or rejects the asset, or reassigns the task.

Workflow transitions link each node to create the desired flow in the review process. On exiting the first node, processing continues to the next node indicated by the transition.

Ready to work on workflows? Your next step is [creating workflow tasks](./workflow-designer/creating-workflow-tasks.md).

## Additional Information

* [Managing Workflows](./managing-workflows.md)
* [Workflow Designer Nodes Overview](./workflow-designer/workflow-designer-nodes-overview.md)
* [Forks and Joins Reference](./workflow-designer/forks-and-joins-reference.md)
* [Condition Node Reference](./workflow-designer/condition-node-reference.md)
* [Creating Workflow Tasks](./workflow-designer/creating-workflow-tasks.md)
* [Configuring Workflow Actions and Notifications](./workflow-designer/configuring-workflow-actions-and-notifications.md)
