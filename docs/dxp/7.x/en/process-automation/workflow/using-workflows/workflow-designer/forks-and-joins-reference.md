# Forks and Joins Reference

> Subscribers

This reference article documents how to use the _Fork_, _Join_ and _Join XOR_ nodes. These nodes are not used in the default [Single Approver](../workflow-designer-overview/resources/single-approver-definition.xml) definition.

However, these nodes are very useful for workflow processes that have multiple reviewers like the [Legal-Marketing Definition](../workflow-designer-overview/workflow-processes/legal-marketing-definition.xml) sample.

Furthermore, you can split the review task so that two reviewers can the asset without waiting for the other. In the Legal-Marketing example workflow, the process is linear: the workflow goes from Marketing to Legal before publication, but you can use _Fork_ and _Join_ (or Join XOR) to build a concurrent workflow process.

## Using the Fork and Join Nodes

Follow the steps below:

1. Go to the _Global Menu_ &rarr; _Applications_ &rarr; _Process Builder_.
1. Click the _Workflows_ tab.
1. Click the (![Add icon](../../../../images/icon-add.png)) to add a new workflow.
1. In the Workflow Designer Canvas, delete the old connector between the _Start_ node and _End_ node.
1. Drag and drop the _Fork_ node onto the canvas then connect _Start_ node to the _Fork_ node. (You can rename the nodes and connectors later.)
1. Drag and drop two _Task_ nodes then connect the _Fork_ node to the two _Task_ nodes.

    ![Add the Fork and then the Task nodes.](./forks-and-joins-reference/images/02.png)

1. Drag and drop the _Join_ node then connect the two _Task Nodes_ to the _Join_ Node.
1. Connect the _Join_ node to the _End_ Node.
1. Rename the nodes and connectors and add any [Actions or Notifications](./configuring-workflow-actions-and-notifications.md).

    ![Publish the workflow when finished.](./forks-and-joins-reference/images/01.png)

1. Click _Publish_ when finished.

### Using the Join XOR Node

With a regular Join node, for the workflow to proceed beyond the join, the transition from both parallel executions must be invoked. However, if you use a Join XOR node instead, the workflow proceeds as long as the transition from one of the parallel executions is invoked.

## Additional Information

* [Activating Workflow](../activating-workflow.md)
* [Workflow Designer Nodes Overview](./workflow-designer-nodes-overview.md)
* [Creating Workflow Tasks](./creating-workflow-tasks.md)
* [Condition Node Reference](./condition-node-reference.md)
* [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)
