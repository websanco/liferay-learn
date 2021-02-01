# Workflow Task Node Reference

As the name implies, tasks are the part of the workflow where *work* is done. Tasks must be assigned to Users, who review the submitted asset and decide if an asset from the workflow is acceptable for publication or needs more work.

Unlike other workflow nodes, task nodes have Assignments, because a User is expected to *do something* (often approve or reject the submitted asset) when a workflow process enters the task node.

Commonly, task nodes contain task timers, assignments, actions (which can include notifications and scripts), and transitions. Notifications and actions are not limited to task nodes, but task nodes and their assignments deserve their own article (this one).

Check out the Review task in the Single Approver definition:

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
            <execution-type>onAssignment</execution-type>
        </notification>
        <notification>
            <name>Review Completion Notification</name>
            <template><![CDATA[Your submission was reviewed<#if taskComments?has_content> and the reviewer applied the following ${taskComments}</#if>.]]></template>
            <template-language>freemarker</template-language>
        <notification-type>email</notification-type>
            <recipients>
            <user />
            </recipients>
            <execution-type>onExit</execution-type>
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

There are two `actions` in the review task, both `<notification>`s. Each notification may contain a name, template, notification-type, execution-type, and recipients. Besides notifications, You can also use the `<action>` tag.
These have a name and a [script](./using-the-script-engine-in-workflow.md) and are more often used in state nodes than tasks.

## Assignments

Workflow tasks are completed by a User. Assignments make sure the right users can access the tasks. You can choose how you want to configure your assignments. Assignments can be added to

* Specific Roles
* Multiple Roles of a Role type (Organization, Site, or regular Role types)
* Asset creator
* Resource actions
* Specific Users

Additionally, you can write a script to define the assignment. For an example, see the [single-approver-definition-scripted-assignment.xml](../designing-and-managing-workflows/workflow-designer-overview/resources/single-approver-definition-scripted-assignment.xml).

```xml
<assignments>
    <roles>
        <role>
            <role-type>organization</role-type>
            <name>Organization Administrator</name>
        </role>
    </roles>
</assignments>
```

The above assignment specifies that an Organization Administrator must complete the task.

```xml
<assignments>
    <user>
        <user-id>20156</user-id>
    </user>
</assignments>
```

The above assignment specifies that only the User with the user ID of 20156 may complete the task. Alternatively, specify the `<screen-name>` or `<email-address>` of the User.

```xml
<assignments>
    <scripted-assignment>
        <script>
            <![CDATA[
                    import com.liferay.portal.kernel.model.Group;
                    import com.liferay.portal.kernel.model.Role;
                    import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
                    import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
                    import com.liferay.portal.kernel.util.GetterUtil;
                    import com.liferay.portal.kernel.workflow.WorkflowConstants;

                    long companyId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));

                    long groupId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID));

                    Group group = GroupLocalServiceUtil.getGroup(groupId);

                    roles = new ArrayList<Role>();

                    Role adminRole = RoleLocalServiceUtil.getRole(companyId, "Administrator");

                    roles.add(adminRole);

                    if (group.isOrganization()) {
                        Role role = RoleLocalServiceUtil.getRole(companyId, "Organization Content Reviewer");

                        roles.add(role);
                    }
                    else {
                        Role role = RoleLocalServiceUtil.getRole(companyId, "Site Content Reviewer");

                        roles.add(role);
                    }

                    user = null;
                ]]>
            </script>
        <script-language>groovy</script-language>
    </scripted-assignment>
</assignments>
```

The above assignment assigns the task to the *Administrator* Role, then checks whether the *group* of the asset is an Organization. If it is, the *Organization Content Reviewer* role is assigned to it. If it's not, the task is assigned to the *Site Content Reviewer* Role.

Note the `roles = new ArrayList<Role>();` line above. In a scripted assignment, the `roles` variable is where you specify any Roles the task is assigned to. For example, when `roles.add(adminRole);` is called, the Administrator Role is added to the assignment.

## Resource Action Assignments

Users can assign a task to a resource action such as an *Update* action. If your workflow definition specifies the UPDATE action in an assignment, then anyone who has permission to update the type of asset being processed in the workflow is assigned to the task. You can configure multiple assignments for a task.

*Resource actions* are operations performed by Users on an application or entity. For example, a User might have permission to update Message Boards Messages. This is called an UPDATE resource action, because the User can update the resource. If you're uncertain about what resource actions are, refer to the developer tutorial on the [permission system](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md) for a more detailed explanation.

To find all the resource actions that have been created, you need access to the Roles Admin application in the Control Panel (in other words, you need permission for the VIEW action on the Roles resource).

1. Navigate to the _Control Panel_ &rarr; _Users_ &rarr; _Roles_.
1. Add a new Regular Role. See [Managing Roles](../../../users-and-permissions/roles-and-permissions/creating-and-managing-roles.md) for more information.
1. Once the Role is added, navigate to the Define Permissions interface for the Role.
1. Find the resource whose action should define your workflow assignment.

Here's what the assignment's XML looks like:

```xml
<assignments>
    <resource-actions>
        <resource-action>UPDATE</resource-action>
    </resource-actions>
</assignments>
```

Now when the workflow proceeds to the task with the resource action assignment, Users with `UPDATE` permission on the resource (for example, Message Boards Messages) are notified of the task and can assign it to themselves (if the notification is set to Task Assignees). Specifically, Users see the tasks in their *My Workflow Tasks* application under the tab *Assigned to My Roles*.

Use all upper case letters for resource action names. Here are some common resource actions:

* UPDATE
* ADD
* DELETE
* VIEW
* PERMISSIONS
* SUBSCRIBE
* ADD_DISCUSSION

Determine the probable resource action name from the permissions screen for a resource. For example, in Message Boards, one of the permissions displayed on that screen is *Add Discussion*. Convert that to all uppercase and replace the space with an underscore, and you have the action name.

## Task Timers

Task timers trigger an action after a specified time period passes. Timers are useful for ensuring a task does not go unattended for a long time. Available timer actions include sending an additional notification, reassigning the asset, or creating a timer action.

```xml
<task-timers>
    <task-timer>
        <name></name>
        <delay>
            <duration>1</duration>
            <scale>hour</scale>
        </delay>
        <blocking>false</blocking>
        <recurrence>
            <duration>10</duration>
            <scale>minute</scale>
        </recurrence>
        <timer-actions>
            <timer-notification>
                <name></name>
                <template></template>
                <template-language>text</template-language>
                <notification-type>user-notification</notification-type>
            </timer-notification>
        </timer-actions>
    </task-timer>
</task-timers>
```

The above task timer creates a notification. Specify a time period in the `<delay>` tag, and specify what action to take when the time expires in the `<timer-actions>` block. The `<blocking>` element specifies whether the timer actions may recur. If blocking is set to `false`, timer actions may recur. In a `recurrence` element, specify the recurrence interval using a `duration` and a `scale`, as demonstrated above. The above recurrence element specifies that the timer actions run again every ten minutes after the initial occurrence. Setting blocking to true prevents timer actions from recurring.

```xml
<timer-actions>
    <reassignments>
       <assignments>
         <roles>
          <role>
              <role-type></role-type>
              <name></name>
          </role>
          ...
         </roles>
       </assignments>
    </reassignments>
</timer-actions>
```

The above snippet demonstrates how to set up a reassignment action.

Like `<action>` elements, `<timer-action>` elements can contain scripts. See [Using the Script Engine in Workflow](./using-the-script-engine-in-workflow.md) for more information.

```note::
   A `timer-action` can contain all the same tags as an `action`, with one exception: `execution-type`. Timer actions are always triggered once the time is up, so specifying and execution type of `onEntry`, for example, isn't meaningful inside a timer.
```

## Additional Information

* [Crafting XML Workflow Definitions](./crafting-xml-workflow-definitions.md)
* [Workflow Definition Node Reference](./workflow-definition-node-reference.md)
