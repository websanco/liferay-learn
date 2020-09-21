# Crafting XML Workflow Definitions

All Workflow definitions in Liferay DXP are written in XML format. To craft your own workflow definition, remember that each definition ought to reflect the real life approval process - when an asset enters the review process, approval or rejection by the reviewer, and then exiting the review process to be published on a site or in a document repository.

```tip::
   Subscribers using DXP have the option of using a graphical designer to edit their workflows. They have the option to upload an XML file and then continue configuring their workflows.
```

## Existing Workflow Definitions

Only one workflow definition is installed by default: Single Approver. Several more are embedded in the Liferay source code, but are available here. These definitions provide good reference material for many of the workflow features and elements described in these articles.

* [Category Specific](../user-guide/workflow-designer-overview/workflow-processes/category-specific-definition.xml)
* [Legal Marketing](../user-guide/workflow-designer-overview/workflow-processes/legal-marketing-definition.xml)
* [Single Approver](../user-guide/workflow-designer-overview/workflow-processes/single-approver-definition.xml)
* [Single Approver Scripted Assignment](../user-guide/workflow-designer-overview/workflow-processes/single-approver-definition-scripted-assignment.xml)

## Schema

The XML structure of a workflow definition is defined in this XSD file: [`liferay-workflow-definition-7_3_0.xsd`](https://www.liferay.com/dtd/liferay-workflow-definition_7_3_0.xsd).

Declare the schema at the top of the workflow definition file:

```xml
<?xml version="1.0"?>
<workflow-definition
    xmlns="urn:liferay.com:liferay-workflow_7.3.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="urn:liferay.com:liferay-workflow_7.3.0
        http://www.liferay.com/dtd/liferay-workflow-definition_7_3_0.xsd">
```

To learn more, you can download the [`liferay-workflow-definition-7_3_0.xsd` XSD file](https://www.liferay.com/dtd/liferay-workflow-definition_7_3_0.xsd).

## Metadata

Give the definition a name, description, and version:

```xml
<name>Category Specific Approval</name>
<description>A single approver can approve a workflow content.</description>
<version>1</version>
```

Metadata tags are optional. If present the first time a definition is saved, the `<name>` tag serves as a unique identifier for the definition. If not specified (or added sometime after the first save), a random unique name is generated and used to identify the workflow.

## Start and End Nodes

Each workflow definition begins and ends with a _State Node_. Create a _Start_ node like this one from the [Single Approver](../user-guide/workflow-designer-overview/workflow-processes/single-approver-definition.xml):

```xml
    <state>
        <name>created</name>
        <metadata>
			<![CDATA[{"xy":[36,51]}]]>
        </metadata>
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

* The `<![CDATA]>` contains the _xy_ coordinates in the Workflow Designer.
* The node transitions to the [_Task_ node](./workflow-task-node-reference.md) for review.

The _End_ node may look like this:

```xml
    <state>
        <name>approved</name>
		<metadata>
			<![CDATA[
				{"xy":[380,51]}
			]]>
        </metadata>
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

Here, the final state of the workflow is that the submission is approved and ready for publication. There are no notifications - the original author is not informed that it has been published, but that is one possibility.

See the [Workflow Definition Node Reference](./workflow-definition-node-reference) to learn more.

## Task Nodes

Task nodes are fundamental parts of a workflow definition because this is where the actual work is done. Tasks have to be assigned to users to review the submitted asset then approve or reject the submission.

Unlike other workflow nodes, task nodes have Assignments; you can assign the task to a specific or a specific DXP [Role](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md).

In addition, task nodes contain task timers, actions (which can include notifications and scripts), and transitions. See the [Workflow Task Node Reference](./workflow-task-node-reference.md) to learn more.

### Creating an Update Task Node

When an asset needs more work before being published, you need create a _Task_ node which encompasses this process. In this example from the _Single Approver_ workflow definition, this _Task_ node uses the action `<name>reject</name>` node where the submission is rejected. Once the asset has been reviewed, a notification is sent to the original author. The asset is also reassigned to the original author.

```xml
    <task>
		<name>update</name>
		<metadata>
			<![CDATA[{"transitions":{"resubmit":{"bendpoints":[[303,140]]}},"xy":[328,199]}]]>
		</metadata>
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

### Creating a Review Task Node

Add a _Task_ node for the review process. In this sample, include a `<notification>` element which notifies the reviewer that a submission is ready for review. Because _Task_ nodes require an actual user to complete, you can assign this to an actual User or a specific Role. In the latter case, anyone assigned to that Role will be able to review the asset. Lastly, if the submission is approved, add a `<transition>` to the _Approved_ state and to the _End_ node.

```xml
    <task>
        <name>review</name>
        <metadata>
			<![CDATA[{"xy":[168,36]}]]>
        </metadata>
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

The _Review_ Task node has been added and configured.

Once you have your nodes connected in a logical order, the workflow definition is ready for use.  

## Additional Information

* [Managing Workflows](../user-guide/managing-workflows.md)
* [Workflow Designer Overview](../user-guide/workflow-designer-overview.md)
* [Understanding Roles and Permissions](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)
