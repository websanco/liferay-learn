# Workflow Definition Node Reference

_Node_ elements and their sub-elements are fundamental building blocks making up workflow definitions. Nodes often reflect the real life stages of the review process. This is a guide describing the different types of nodes and how to use them.

* [State Nodes](#state-nodes)
* [Condition Nodes](#condition-nodes)
* [Forks and Joins](#forks-and-joins)

## State Nodes

State nodes do not require user input. Usually, a State node begins or ends the workflow definition. The workflow does whatever is specified in the State node's `actions` tag (a notification and/or a custom script) and then moves to the provided transition.

The initial state node often only contains a transition:

```xml
<state>
    <name>created</name>
    <initial>true</initial>
    <transitions>
        <transition>
            <name>Determine Branch</name>
            <target>determine-branch</target>
            <default>true</default>
        </transition>
    </transitions>
</state>
```

If a notification or script is required in your State node, you can use an `actions` tag. Here's an `action` element containing a Groovy script. This is found in many terminal state nodes and marks the asset as approved in the workflow.

```xml
<actions>
    <action>
        <name>Approve</name>
        <description>Approve</description>
        <script>
            <![CDATA[
            com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(com.liferay.portal.kernel.workflow.WorkflowConstants.getLabelStatus("approved"), workflowContext);]]>
        </script>
        <script-language>groovy</script-language>
        <execution-type>onEntry</execution-type>
    </action>
</actions>
```

## Conditions

_Condition nodes_ determine whether a condition is met and if so, transitions the workflow to the appropriate node. For example, if a submitted document is a contract, it must go to the Legal team; otherwise it goes to the Marketing team.

Here's the `determine-branch` condition from the [Category Specific Approval](../designing-and-managing-workflows/workflow-designer-overview/resources/category-specific-definition.xml) workflow definition:

```xml
<condition>
    <name>determine-branch</name>
    <script>
        <![CDATA[
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
        ]]>
    </script>
    <script-language>groovy</script-language>
    <transitions>
        <transition>
            <name>Legal Review</name>
            <target>legal-review</target>
            <default>false</default>
        </transition>
        <transition>
            <name>Content Review</name>
            <target>content-review</target>
            <default>false</default>
        </transition>
    </transitions>
</condition>
```

This example checks the asset category to choose whether to transition to the _Legal Review_ task or the _Content Review_ task.

The `returnValue` variable points from the condition to a transition, and its value must match a valid transition name. This script looks up the asset in question, retrieves its asset category, and sets an initial `returnValue`. Then it checks to see if the asset has been marked with the _legal_ category. If not it goes through _Content Review_ (to the content-review task in the workflow), and if it does it goes through _Legal Review_ (to the legal-review task in the workflow).

## Forks and Joins

Forks split the workflow process and joins bring the process back to a unified branch. Processing must always be brought back using a Join (or a Join XOR), and the number of forks and joins in a workflow definition must be equal.

```xml
<fork>
    <name>fork-1</name>
    <transitions>
        <transition>
            <name>transition-1</name>
            <target>task-1</target>
            <default>true</default>
        </transition>
        <transition>
            <name>transition-2</name>
            <target>task-2</target>
            <default>false</default>
        </transition>
    </transitions>
</fork>
<join>
    <name>join-1</name>
    <transitions>
        <transition>
            <name>transition-4</name>
            <target>EndNode</target>
            <default>true</default>
        </transition>
    </transitions>
</join>
```

The workflow does not advance past the join until the asset transitions to it from both of the forks. To fork the workflow process but allow the processing to continue when only one fork is completed, use a Join XOR.

A Join XOR differs from a join in one important way: it removes the constraint that both forks must be completed before processing can continue. The asset must complete just one of the forks before processing continues.

```xml
<join-xor>
    <name>join-xor</name>
    <transitions>
        <transition>
            <name>transition3</name>
            <target>EndNode</target>
            <default>true</default>
        </transition>
    </transitions>
</join-xor>
```

## Task Nodes

[Task nodes](./workflow-task-node-reference.md) are at the core of the workflow definition. They're the part where someone interacts with the asset in some way. Tasks can also have sub-elements, including notifications, assignments, and task timers.

Here's the `content-review` task from the Category Specific Approval workflow, with some of the `role` assignment tags cut out for brevity:

```xml
<task>
    <name>content-review</name>
    <actions>
        <notification>
            <name>Review Notification</name>
            <template>You have a new submission waiting for your review in the workflow.</template>
            <template-language>text</template-language>
            <notification-type>email</notification-type>
            <notification-type>user-notification</notification-type>
            <execution-type>onAssignment</execution-type>
        </notification>
    </actions>
    <assignments>
        <roles>
            <role>
                <role-type>organization</role-type>
                <name>Organization Administrator</name>
            </role>
            ...
        </roles>
    </assignments>
    <task-timers>
        <task-timer>
            <name></name>
            <delay>
                <duration>1</duration>
                <scale>hour</scale>
            </delay>
            <blocking>false</blocking>
            <timer-actions>
                <timer-notification>
                    <name></name>
                    <template></template>
                    <template-language>text</template-language>
                    <notification-type>im</notification-type>
                </timer-notification>
            </timer-actions>
        </task-timer>
    </task-timers>
    <transitions>
        <transition>
            <name>approve</name>
            <target>approved</target>
            <default>true</default>
        </transition>
        <transition>
            <name>reject</name>
            <target>update</target>
            <default>false</default>
        </transition>
    </transitions>
</task>
```

## Additional Information

* [Crafting XML Workflow Definitions](./crafting-xml-workflow-definitions.md)
* [Workflow Task Node Reference](./workflow-task-node-reference.md)
