# Creating Workflow Tasks

> Subscribers

The default [Single Approver Definition](./workflow-designer-overview/resources/single-approver-definition.xml) is a great introduction to creating workflow tasks. It has only two task nodes: _Review_ and _Update_. The workflow enters the _Review_ node when a content creator submits an asset for review. In review, the asset can be accepted or rejected. If it's rejected, the process moves to the _Update_ task. The submitter can then modify the asset and resubmit it for review.

![The single approver definition has two task nodes.](./creating-workflow-tasks/images/01.png)

Task nodes are often the most complex parts of a workflow definition. They can have Assignments which can assign the task to users or a Resource Action (see [Using Task Nodes](./using-task-nodes.md)).

Task nodes also contain Notifications and Actions (defined in scripts). See [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md).

When the review is finished, Task nodes advance to the next node. In this case, the process moves to the _Approved_ End node.

## Creating the Single Approver Workflow

You'll create the workflow in 4 steps: 

* Create the workflow and give it a name
* Create the Review node
* Create the Update node
* Configure the End node

### Creating the Workflow

1. Go to the _Global Menu_ &rarr; _Applications_ &rarr; _Process Builder_.
1. Click the _Workflows_ tab.
1. Click _Add_ (![Add icon](../../../../images/icon-add.png)) to add a new workflow.
1. Give your workflow a descriptive name, like _My Single Approver_.

### Creating the Review Node

1. In the Workflow Designer Canvas, delete the old connector between the _Start_ node and _End_ node: select it with your mouse and hit the Del key on your keyboard.
1. Drag and drop the _Task_ node onto the canvas. 
1. Connect the Start node to the Task node by making sure no nodes are selected and then moving your pointer to the edge of the start node. When the cursor changes shape, you can click and drag a connector from the Start node to the Task node.
1. Select the connector and rename it _review_.
1. Click the _Task_ node to begin updating its properties.
1. Double click the _Name_ field to give the node a name: _review_.
1. Double click _Notifications_.
1. Enter this information:

    * **Name**: Review Notification
    * **Template Language**: Freemarker
    * **Template**: Enter this Freemarker notification: `${userName} sent you a ${entryType} for review in the workflow.`
    * **Notification Type**: Use the Ctrl key to select _Email_ and _User Notification_; this is a multiple select field.
    * **Execution Type**: On Assignment
    * **Recipient Type**: Task Assignees

    ![Configure the Task Node's notifications settings to send an email and user notification that an asset is ready for review.](./creating-workflow-tasks/images/02.png)

    Click _Add Section_.

    Enter this information:

   * **Name:** Review Completion Notification
   * **Template Language:** Freemarker
   * **Template:** Enter this Freemarker notification: `Your submission was reviewed<#if taskComments?has_content> and the reviewer applied the following ${taskComments}</#if>.`
   * **Notification Type:** Email
   * **Execution Type:** On Exit
   * **Recipient Type:** User

1. Click _Save_ when finished.
1. Double click _Assignments_. You can assign the review task to a Role, Role Type, a specific User, or a Resource Action. In this example, assign the review Task to a Role Type. Select these Roles, clicking _Add Section_ each time you must add a new Role:

   * Asset Library Administrator
   * Asset Library Content Reviewer
   * Asset Library Owner
   * Organization Administrator
   * Organization Content Reviewer
   * Organization Owner
   * Administrator
   * Portal Content Reviewer
   * Site Administrator
   * Site Content Reviewer
   * Site Owner

   For more information about Roles and Permissions, see [Understanding Roles and Permissions](../../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md) and [Assigning Users to Roles](../../../../users-and-permissions/roles-and-permissions/assigning-users-to-roles.md).

   ![Configure the Task Node's notifications settings to send an email and user notification that an asset is ready for review.](./creating-workflow-tasks/images/03.png)

1. Click _Save_ when finished.

1. Click the End node and rename it to _Approved_.

1. Connect the _Review_ node to the _Approved_ End node. Name the connector _approve_.

This _Task_ node is now configured; it sends a notification that a submission is ready for review to those Users assigned to a specific Role.

You can also assign the _Task_ node to a Resource Action instead of another user or a Role Type. To learn more see [Using Task Nodes](./using-task-nodes.md).

### Create the Update Node

1. Drag and drop another _Task_ node onto the canvas. 
1. Connect the review node to the new Task node by making sure no nodes are selected and then moving your pointer to the edge of the start node. When the cursor changes shape, you can click and drag a connector from the review node to the new Task node. 
1. Select the connector and rename it _reject_. 
1. Click the Task node to begin updating its properties. 
1. Double-click the _Name_ field to give the node a name: _update_. 
1. Double-click _Notifications_. 
1. Enter this information: 

   * **Name:** Creator Modification Notification
   * **Template Language:** Freemarker
   * **Template:** Enter this Freemarker notification: `Your submission was rejected by ${userName}; please modify and resubmit.` 
   * **Notification Type:** Use the Ctrl key to select _Email_ and _User Notification_; this is a multiple select field. 
   * **Execution Type:** On Assignment
   * **Recipient Type:** Task Assignees

1. Double-click _Assignments_. Select _User_ and click _Save_. 
1. Double-click _Actions_. Enter this information: 

   * **Name:** reject
   * **Script:**  
     ```groovy
      import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
      import com.liferay.portal.kernel.workflow.WorkflowConstants;

      WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("denied"), workflowContext);
      WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("pending"), workflowContext);
     ```
   * Leave the rest of the defaults: (Language _groovy_, Execution type _On Assignment_)

1. Click _Save_. 
1. Make sure nothing is selected and move your mouse pointer to the edge of the _update_ node. Drag a connector from the _update_ node back to the _review_ node. 
1. Name the new connector _resubmit_. 

![Your workflow is taking shape. All that's left is to connect the end node.](./creating-workflow-tasks/images/04.png)

### Configuring the End Node

The only thing left to do is rename the end node and configure it to set the workflow status to _approved_. 

1. Drag a connector from the _review_ node to the _End Node_. 
1. Rename the connector _approve_. 
1. Double-click on the _End Node_ and rename it to _Approved_. 
1. Click the _Publish_ button at the bottom to publish your workflow. 

Nice job! You've created your first workflow and learned how the workflow designer works. You can create much more powerful workflows with other node types, such as [Forks and Joins](./using-forks-and-joins.md) or [Conditions](./using-condition-nodes.md). These are covered next. 

## Additional Information

* [Activating Workflow](../../using-workflows/activating-workflow.md)
* [Workflow Nodes](./workflow-nodes.md)
* [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)
* [Using Task Nodes](./using-task-nodes.md)
