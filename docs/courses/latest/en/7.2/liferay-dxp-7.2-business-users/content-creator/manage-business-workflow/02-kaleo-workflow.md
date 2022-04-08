## Kaleo Workflow Definitions

In the last section, we saw the default Single Approver workflow and walked through how it works. But, many times, content submissions require review tasks with multiple reviewers. Liferay DXP comes out of the box with tools to help you create the exact workflow you need for your Content Creation processes.

#### Livingstone's Custom Workflows {#livingstone}

Content added to the front page for the Livingstone Hotels & Resorts corporate site needs to be reviewed by both Josiah, the platform administrator, and Natalia, the marketing lead. The Single Approver definition won't work in this case, as several reviewers must go over the content. Additionally, because of the nature of the content added to the page, reviews must be completed in a timely manner to make sure that time-sensitive content is displayed as soon as possible. For this reason, a requirement has been set that all reviews for corporate site content be completed and submitted within 72 hours. A new workflow definition will need to be created to meet the following requirements for this new business review process:

* Hold each reviewer to a time limit (deadline)
* Require multiple approvals

<figure>
	<img src="../images/reject-approve.png" style="max-height:19%;" />
	<figcaption style="font-size: x-small">Fig.1 Livingstone's workflow, visualized</figcaption>
</figure>

#### Creating Custom Definitions with the Kaleo Designer {#custom}

Administrators can take advantage of the _Kaleo Designer_ to create their custom workflow definition graphically and set up all the components for the workflow.

<div class="key-point">
Key Point: <br />
The <strong>Kaleo Designer</strong> allows administrators to create new workflows with a graphical editor instead of having to write the entire process in XML.
</div>

You will remember that there are four key parts to any workflow definition: States, Transitions, Tasks, and Task Assignments.

States have the following properties:

* _Actions_: Different actions can be added to this part of the workflow, like scripts.
* _Notifications_: One or more notifications can be created and sent out to different audiences.
* _Description_: Provides a description of the specific part of the workflow
* _Name_: Defines the database name
* _Type_: Shows the type of node in use

<figure>
	<img src="../images/start-state.png" style="max-height:40%;" />
	<figcaption style="font-size: x-small">Fig.2 The details of the Start Node</figcaption>
</figure>

<br />

Remember that tasks represent actual tasks that need to be fulfilled in the review process. Tasks have the following properties:

* *Description*: Provides a description of the Task
* *Name*: Defines the database name
* *Type*: Shows the type of node in use
* *Actions*: Different actions can be added to this part of the workflow, like Task Timers or scripts.
* *Notifications*: One or more notifications can be created and sent out to different audiences.
* *Assignment*: This is where you can assign who will be responsible for performing the task.

<br />

<figure>
	<img src="../images/task.png" style="max-height:40%;" />
	<figcaption style="font-size: x-small">Fig.3 The details of a default Task node</figcaption>
</figure>

<br />

Tasks and Task Assignments are distinct in concept, but one in practice. Task Assignments have different Assignment Types, including the following:

* *Asset Creator*: Assigns the task to the original Content Writer or Content Creator of the asset
* *Resource Actions*: Assigns by permissions 
  * For example, you can assign something to a particular user who has permission to edit a blog post.
* *Role*: Assigns by a single role
* *Role Type*: Assigns by one or more roles
* *Scripted Assignment*: Assigns via script
* *User*: Assigns to one or more specific users

Both States and Tasks can have different actions added to them. Actions can be specific or scripted. For example, Tasks can include _Task Timers_ that can be used to prevent bottlenecks. Task Timers perform certain actions based on a specified amount of time, such as re-assignment, email notifications, etc. For anything more advanced, scripts can be added as different States or Tasks in the workflow.

<div class="key-point">
Key Point: <br />
<strong>Assignments</strong> in the workflow can be designated by Role or Role Type.
</div>

One benefit of using the Role Type assignment is that users can auto-generate roles that do not currently exist just for workflow. If a role does not currently exist, a workflow creator can simply place it in the workflow definition. Once the definition is saved, the new role will show up in the role list.

<figure>
	<img src="../images/script.png" style="max-height:25%;" />
	<figcaption style="font-size: x-small">Fig.4 Assigning a Task in the Kaleo Designer</figcaption>
</figure>

<br />

States and Tasks can also include one or more notifications. Notifications can be sent out via email, user notification on the platform, and through instant/private message via Social Office configuration. Workflow creators can determine how many, when, and to whom notifications go out during a workflow process.

<div class="key-point">
Key Point: <br />
<strong>Notifications</strong> can be written in simple text or as styled templates using FreeMarker.
</div>

<figure>
	<img src="../images/notification.png" style="max-height:31%;" />
	<figcaption style="font-size: x-small">Fig.5 Setting up notifications for an assignment in Kaleo</figcaption>
</figure>

#### Advanced Options {#advanced}

The review process for your site may require simultaneous and timely reviews in order to avoid delays. Site Administrators want to make sure that content is reviewed within 72 hours of being posted to comply with these requirements. To allow for multiple reviews, Administrators can update the current definition to include a fork and join. Using the fork and join will allow for a more streamlined process where reviewers are notified and can review simultaneously.

<div class="key-point">
Key Point: <br />
Workflow has additional advanced options that allow Administrators to fit different use cases:
<ul>
  <li><i>Forks</i>: Allow you to create parallel tasks</li>
  <li><i>Joins</i>: Join the forked tasks and move on to the next part of the workflow</li>
  <li><i>Conditions</i>: Require some scripted condition in order to move to different parts of the workflow</li>
  <li><i>Task Timers</i>: Prevent bottlenecks in a task by performing an action after a specified period of time</li>
</ul>
</div>

<figure>
	<img src="../images/advanced.png" style="max-height:39%;" />
	<figcaption style="font-size: x-small">Fig.6 All the node types that can be added to a workflow with the Kaleo Designer</figcaption>
</figure>

<br />

To allow for timed reviews, Administrators can add _Task Timers_ to the definition. Adding Task Timers to each task will allow the administrator to set the 72-hour timer, notifying reviewers if they have not reviewed in time. Here's what a Task Timer looks like in XML, including the time set as _delay_ and the action set as _timer-actions_.

```XML
<task-timer>
    <name>Review Reminder</name>
    <delay>
      <duration>3</duration>
      <scale>days</scale>
    </delay>
    <timer-actions>
      <timer-notification>
        <name></name>
        <template></template>
        <template-language></template-language>
        <notification-type></notification-type>
      </timer-notification>
    </timer-actions>
  </task-timer>
```

Task Timers can also be set in the Kaleo Designer.

<figure>
	<img src="../images/task-timer.png" style="max-height:40%;" />
	<figcaption style="font-size: x-small">Fig.7 Creating a task timer with Kaleo</figcaption>
</figure>

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Liferay Workflow tools allow for advanced ____________________________________ creation.</li>
  <li>Workflows can be defined to include ________________ timers, conditions, and even script actions.</li>
  <li>With the Kaleo designer, Administrators can _______________________ design workflow definitions.</li>
</ul>
</div>
