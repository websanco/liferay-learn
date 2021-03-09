# Workflow Designer Overview

> Subscribers

The Process Builder's workflow designer is a graphical interface for creating workflow process definitions. The convenient drag and drop interface makes workflow design easier than writing XML definitions by hand. It's accessed in the Control Panel &rarr; Workflow &rarr; Process Builder.

![Drag and drop nodes onto the designer canvas.](./workflow-designer-overview/images/01.png)

```tip::
   Alternately, you can write or upload XML definitions from the Source tab in the Process Builder. See `Managing Workflows <../managing-workflows.md#uploading-a-new-workflow-definition>`_.
```

The Workflow Designer supports all [workflow node](./workflow-nodes.md) types:

* [Start and End nodes](./workflow-nodes.md#start-and-end-nodes)
* [Fork and Join nodes](./using-forks-and-joins.md)
* [Condition nodes](./using-condition-nodes.md)
* [State nodes](./workflow-nodes.md#state-nodes)
* [Task nodes](./creating-workflow-tasks.md)

In addition to the functionality provided by the drag and drop interface, you have the full power of Groovy (a Java-based scripting language) to perform [programmatic actions](./../../developer-guide/using-the-script-engine-in-workflow.md) on assets being moved through your workflows.

By default, only one workflow definition is installed: the Single Approver Workflow definition. You can download additional definitions here:

* [Category-Specific Definition](./workflow-designer-overview/resources/category-specific-definition.xml)
* [Legal Marketing Definition](./workflow-designer-overview/resources/legal-marketing-definition.xml)
* [Single Approver Definition with Scripted Assignment](./workflow-designer-overview/resources/single-approver-definition-scripted-assignment.xml)
* [Single Approver Definition](./workflow-designer-overview/resources/single-approver-definition.xml)

## Building Workflows

To build a workflow, navigate to the Global Menu (![Global Menu](../../../../images/icon-applications-menu.png)) &rarr; Control Panel &rarr; Process Builder.

Click the (![Add icon](../../../../images/icon-add.png)) to begin.

Each Workflow Node represents a specific point in an approval process, whether it starts the review process, approves or rejects the asset, or reassigns the task.

Workflow transitions link each node to create the desired flow in the review process. On exiting the first node, processing continues to the next node indicated by the transition.

Ready to work on workflows? Your next step is [creating workflow tasks](./creating-workflow-tasks.md).

## Additional Information

* [Managing Workflows](./managing-workflows.md)
* [Workflow Nodes](./workflow-nodes.md)
* [Using Forks and Joins](./using-forks-and-joins.md)
* [Using Condition Nodes](./using-condition-nodes.md)
* [Creating Workflow Tasks](./creating-workflow-tasks.md)
* [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)
