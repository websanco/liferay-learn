# Crafting XML Workflow Definitions

All Workflow definitions in Liferay DXP are written in XML format. To craft your own workflow definition, remember that definitions should reflect real life approval processes.

```tip::
   Subscribers using DXP can use a `graphical designer <../user-guide/workflow-designer/workflow-designer-overview.md>`_ to create workflows. If you've already begun crafting workflows in XML, you can upload them and continue in the GUI. 
```

1. Open the Global Menu (![Global Menu](../../../images/icon-applications-menu.png)). Under Workflow, select _Process Builder_. 

1. To add a new Workflow definition click the ![add](../../../images/icon-add.png) icon. 

1. You can type your workflow definition into the editor or import one you've created locally. 

Once you publish your workflow, it becomes available to be applied everywhere workflow can be enabled. 

## Existing Workflow Definitions

Only one workflow definition is installed by default: Single Approver. Several more are embedded in the Liferay source code. These definitions provide good examples of all the features described here.

* [Category Specific](../designing-and-managing-workflows/workflow-designer/workflow-designer-overview/resources/category-specific-definition.xml)
* [Legal Marketing](../designing-and-managing-workflows/workflow-designer/workflow-designer-overview/resources/legal-marketing-definition.xml)
* [Single Approver](../designing-and-managing-workflows/workflow-designer/workflow-designer-overview/resources/single-approver-definition.xml)
* [Single Approver Scripted Assignment](../designing-and-managing-workflows/workflow-designer/workflow-designer-overview/resources/single-approver-definition-scripted-assignment.xml)
Below you'll learn the basics by using the simplest workflow, Single Approver. The Single Approver workflow contains two required States: Start and End, which are named _created_ and _approved_. It also contains two Tasks: _review_ and _update_. These Tasks define _Actions_, such as _approve_, _reject_, and _resubmit_. 

When breaking down your workflow into its components, then, think about your States, Tasks, and Actions. Once you have them defined, you're ready to get to work. Now you're ready to put it all together by seeing how the Single Approver workflow works. 

## Schema

The structure of a workflow definition is defined in its XSD file: [`liferay-workflow-definition-7_3_0.xsd`](https://www.liferay.com/dtd/liferay-workflow-definition_7_3_0.xsd).

Declare the schema at the top of the workflow definition file:

```xml
<?xml version="1.0"?>
<workflow-definition
    xmlns="urn:liferay.com:liferay-workflow_7.3.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="urn:liferay.com:liferay-workflow_7.3.0
        http://www.liferay.com/dtd/liferay-workflow-definition_7_3_0.xsd">
```

## Metadata

Give the definition a name, description, and version:

```xml
<name>Single Approver</name>
<description>A single approver can approve a workflow content.</description>
<version>1</version>
```

## Start and End Nodes

Each workflow definition begins and ends with a _State Node_. Create a _Start_ node like this one from the [Single Approver](../designing-and-managing-workflows/workflow-designer/workflow-designer-overview/resources/single-approver-definition.xml):

```xml
	<state>
		<name>created</name>
		<initial>true</initial>
		<transitions>
			<transition>
				<name>review</name>
				<target>review</target>
			</transition>
		</transitions>
	</state>
```

In this example, the _Start_ node has the following properties:

* It is the initial state. 
* The node transitions to a [_Task_ node](./workflow-task-node-reference.md) called _review_.

The _End_ node looks like this:

```xml
	<state>
		<name>approved</name>
		<actions>
			<action>
				<name>approve</name>
				<script>
					<![CDATA[
						import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
						import com.liferay.portal.kernel.workflow.WorkflowConstants;

						WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
					]]>
				</script>
				<script-language>groovy</script-language>
				<execution-type>onEntry</execution-type>
			</action>
		</actions>
	</state>
```

When the workflow transitions to the final state, the submission is approved. To keep the example simple, there are no notifications, but they could have been added. The end node has a [script](./using-the-script-engine-in-workflow.md) to set the workflow status to `approved`. 

See the [Workflow Definition Node Reference](./workflow-definition-node-reference.md) to learn more.

## Task Nodes

Task nodes define what Users must do in the workflow process. Unlike other workflow nodes, task nodes have Assignments; you can assign the task to a User or a [Role](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md).

Task nodes can contain task timers, actions (which can include notifications and scripts), and transitions. See the [Workflow Task Node Reference](./workflow-task-node-reference.md) to learn more. The Single Approver workflow contains two Tasks: _review_ and _update_. 

### Creating a Review Task Node

The review Task has two outcomes: reject or approve. This one includes a `<notification>` element that tells the reviewer when a submission is ready for review. If assigned to a User, that User must review the asset. If assigned to a Role, anyone with that Role can review the asset. 

There are two possible transitions: _approve_ and _reject_. When approved, workflow transitions to the end State, which was earlier called `approved`. When rejected, workflow translations to the update Task. 

```xml
    <task>
        <name>review</name>
        <actions>
			<notification>
				<name>Review Notification</name>
				<template>${userName} sent you a ${entryType} for review in the workflow.</template>
				<template-language>freemarker</template-language>
				<notification-type>email</notification-type>
				<notification-type>user-notification</notification-type>
				<recipients receptionType="to">
					<assignees />
				</recipients>
				<execution-type>onAssignment</execution-type>
			</notification>
			<notification>
				<name>Review Completion Notification</name>
				<template><![CDATA[Your submission was reviewed<#if taskComments?has_content> and the reviewer applied the following ${taskComments}</#if>.]]></template>
				<template-language>freemarker</template-language>
				<notification-type>email</notification-type>
				<recipients receptionType="to">
					<user />
				</recipients>
				<execution-type>onExit</execution-type>
			</notification>
		</actions>
		<assignments>
			<roles>
				<role>
					<role-type>depot</role-type>
					<name>Asset Library Administrator</name>
				</role>
				<role>
					<role-type>depot</role-type>
					<name>Asset Library Content Reviewer</name>
				</role>
				<role>
					<role-type>depot</role-type>
					<name>Asset Library Owner</name>
				</role>
				<role>
					<role-type>organization</role-type>
					<name>Organization Administrator</name>
				</role>
				<role>
					<role-type>organization</role-type>
					<name>Organization Content Reviewer</name>
				</role>
				<role>
					<role-type>organization</role-type>
					<name>Organization Owner</name>
				</role>
				<role>
					<role-type>regular</role-type>
					<name>Administrator</name>
				</role>
				<role>
					<role-type>regular</role-type>
					<name>Portal Content Reviewer</name>
				</role>
				<role>
					<role-type>site</role-type>
					<name>Site Administrator</name>
				</role>
				<role>
					<role-type>site</role-type>
					<name>Site Content Reviewer</name>
				</role>
				<role>
					<role-type>site</role-type>
					<name>Site Owner</name>
				</role>
            </roles>
        </assignments>
        <transitions>
			<transition>
				<name>approve</name>
				<target>approved</target>
			</transition>
			<transition>
				<name>reject</name>
				<target>update</target>
                <default>false</default>
            </transition>
        </transitions>
    </task>
```

The _Review_ Task node has been added and configured. Excellent! Now you have only the update Task left. 

### Creating an Update Task Node

If a submission enters the _reject_ Transition in the Review Task, it comes to the update Task so it can be re-submitted for review. When the asset arrives here, the similarly named _reject_ Action runs and assigns the workflow status to `denied` and then `pending`. A notification is sent to the original author. The asset is also reassigned to the original author. From here, the original author can resubmit the asset, presumably after editing it to resolve whatever issues caused it to be rejected. 

```xml
    <task>
		<name>update</name>
		<actions>
			<action>
				<name>reject</name>
				<script>
					<![CDATA[
						import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
						import com.liferay.portal.kernel.workflow.WorkflowConstants;

						WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("denied"), workflowContext);
						WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("pending"), workflowContext);
					]]>
				</script>
				<script-language>groovy</script-language>
				<execution-type>onAssignment</execution-type>
			</action>
			<notification>
				<name>Creator Modification Notification</name>
				<template>Your submission was rejected by ${userName}, please modify and resubmit.</template>
				<template-language>freemarker</template-language>
				<notification-type>email</notification-type>
				<notification-type>user-notification</notification-type>
				<recipients receptionType="to">
					<user />
				</recipients>
				<execution-type>onAssignment</execution-type>
			</notification>
		</actions>
        <assignments>
			<user />
        </assignments>
		<transitions>
			<transition>
				<name>resubmit</name>
				<target>review</target>
            </transition>
        </transitions>
     </task>
```

## Conclusion

Great! Now add the closing tag: 

```xml
</workflow-definition>
```

The Single Approver workflow is now complete. Now that you see how workflows are created, you can learn about all the other possible options, such as forks, joins, and conditions. Liferay's workflow system can implement any processes you need. 

## Additional Information

* [Workflow Definition Node Reference](./workflow-definition-node-reference.md)
* [Workflow Task Node Reference](./workflow-task-node-reference.md)
* [Using the Script Engine in Workflow](./using-the-script-engine-in-workflow.md)
* [Managing Workflows](../designing-and-managing-workflows/managing-workflows.md)
* [Workflow Designer Overview](../designing-and-managing-workflows/workflow-designer/workflow-designer-overview.md)
* [Understanding Roles and Permissions](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)
