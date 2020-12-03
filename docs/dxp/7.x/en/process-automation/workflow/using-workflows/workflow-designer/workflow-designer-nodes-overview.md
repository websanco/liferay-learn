# Workflow Designer Nodes Overview

> Subscribers

A Workflow Node represents a specific point in an approval process, whether it begins the review process, approves or rejects the asset, or reassigns the task.

Below are the different types of nodes and the possible actions associated with each node.

## Types of Nodes

| Node | Description |
| --- | --- |
| Task |_Task_ nodes indicate the workflow task and its assignee. |
| Fork and Join | _Fork_ and _Join_ are paired nodes that allow users to spilt the review process to concurrent reviewers then rejoin for the next step. |
| Join XOR| The _Join XOR_ node allows the workflow to proceed as long as the transition from one of the parallel executions is invoked. |
| Condition | The _Condition_ node establishes a condition before the review process can proceed. |
| Start | The _Start_ node is the starting point. |
| End | The default _End_ node is a pre-configured state node that sets the workflow status to _Approved_. |
| State | _State_ nodes describe the status of the review process; they can be used for "created" (start) or "approved". |

### Start and End Nodes

Start and end nodes kick off the workflow processing and bring the asset to its final, approved state. Often you can use the default start and end nodes without modification. However, you can still configuring a start node, such as notifying users that the review process has begun.

End nodes have a default action that sets the workflow status to Approved using the Groovy scripting language:

```java
    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;

    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
```

### State Nodes

State nodes can have [Actions and Notifications](./configuring-workflow-actions-and-notifications.md). For example, users can create a node that sets the status to _Expired_. Here is a Groovy script that sets the workflow status as Expired:

```java
    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;

    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("expired"), workflowContext);
```

### Condition Nodes

A _Condition_ node checks an asset or its execution context, and depending on the result, send it to the appropriate transition. This node requires a script that sets a value to one of the transitions.

See the [Category Specific Definition](../workflow-designer-overview/workflow-processes/category-specific-definition.xml)

This script looks up the asset in question, retrieves its [asset category](https://help.liferay.com/hc/en-us/articles/360028820492-Defining-Categories-for-Content), and sets an initial `returnValue`. Then it checks to see if the asset has been marked with the *legal* category. If not it goes through *Content Review* (the content-review task in the workflow), and if it does it goes through *Legal Review* (the legal-review task in the workflow).

### Task Nodes

_Task_ nodes represent where all the work is done. Because _Task_ nodes can be complex, they are covered in two separate articles; see [Creating Workflow Tasks](./creating-workflow-tasks.md) and [Task Node Reference](./task-node-reference.md).

## Additional Information

* [Creating Workflow Tasks](./creating-workflow-tasks.md)
* [Workflow Task Node Reference](./workflow-task-node-reference.md)
* [Kaleo Forms](../../../user-guide/advanced-forms-usage/kaleo-forms/kaleo-forms.md)
* [Introduction to the Workflow Framework](https://help.liferay.com/hc/en-us/articles/360028727112-Introduction-to-The-Workflow-Framework)
* [Dynamic Data Lists](../../../user-guide/advanced-forms-usage/dynamic-data-lists/getting-started-with-dynamic-data-lists.md)
