<h2 class="exercise">Exercises</h2>

## Creating Workflow Definitions

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Create a Workflow definition</li>
    <li>Walk-through a Workflow process with Assets</li>
</ul>
</div>

#### Create a New Parallel Workflow with Kaleo Designer
1. **Go to** _`Control Panel → Workflow → Process Builder`_ in the _Menu_. 
* **Click** the _Add_ button to add a new Workflow.  
* **Type** `Parallel Review` for the _Title_.  
* **Click** on the _Connector_ to highlight it.
  * The transition connector is the line between the StartNode and EndNode.
* **Delete** the _Connector_.
  * This can be done by pressing the _Delete_ key on your keyboard.
* **Click** _Ok_ on the pop-up.
* **Drag** the _StartNode_ to the left-middle of the editor.
* **Drag** the _EndNode_ to the right-middle of the editor.

<img src="../images/kaleo-workflow-start.png" style="max-height:30%;" />

#### Add the Parallel Review Tasks
1. **Drag** a _Task_ node to the top-middle of the editor, between the _StartNode_ and _EndNode_.  
* **Click** the new node. 
* **Double-click** the _Name_ value to edit it.
* **Type** `Administrator Review` for the _Name_.   
* **Click** the _Save_ button. 
* **Click** the _Nodes_ tab.   
* **Drag** a _Task_ node to the bottom-middle of the editor, between the _StartNode_ and _EndNode_.  
* **Click** the new node. 
* **Double-click** the _Name_ value to edit it.
* **Type** `Copyediting Review` for _Name_.   
* **Click** the _Save_ button. 

<img src="../images/kaleo-workflow-added-tasks.png" style="max-height:30%;" />

#### Add the Fork and Join to Connect the Parallel Tasks
1. **Click** the _Nodes_ tab.   
* **Drag** a _Fork_ node to the right of the _StartNode_.  
* **Click** the _Fork_ node.
* **Double-click** the _Name_ value to edit it.
* **Type** `Stakeholder Review` for the _Name_.  
* **Click** the _Save_ button.  
* **Click** the _Nodes_ tab.  
* **Drag** a _Join_ node to the left of the _EndNode_.  
* **Click** the _Join_ node.
* **Double-click** the _Name_ value to edit it.
* **Type** `Review Complete` for the _Name_.  
* **Click** the _Save_ button.  

<img src="../images/kaleo-workflow-added-fork.png" style="max-height:32%;" />

#### Connect All of the Nodes with Transitions
1. **Drag** a _Transition_ arrow from the _StartNode_ to the _Stakeholder Review_ fork.
  * To drag a transition, you need to place your cursor on the edge of the node so that you see a thin plus sign. When you see that, you can click and drag the arrow to the next node, connecting it by positioning it to see the orange circle.
* **Drag** a _Transition_ arrow from the _Stakeholder Review_ fork to the _Administrator Review_ Task.
* **Drag** a _Transition_ arrow from the _Stakeholder Review_ fork to the _Copyediting Review_ Task.
* **Drag** a _Transition_ arrow from the _Administrator Review_ task to the _Review Complete_ Join.
* **Drag** a _Transition_ arrow from the _Copyediting Review_ task to the _Review Complete_ Join.
* **Drag** a _Transition_ arrow from the _Review Complete_ Join to the _EndNode_.

<img src="../images/kaleo-workflow-all-connected.png" style="max-height:35%;" />

#### Name the New Transitions
1. **Click** the first Transition to the left.
* **Double-click** the _Name_ value to edit it.
* **Type** `Submit for Review` for the _Name_.  
* **Click** the _Save_ button. 
* **Click** the top Fork Transition.
* **Double-click** the _Name_ value to edit it.
* **Type** `Admin Review` for the _Name_.  
* **Click** the _Save_ button. 
* **Click** the bottom Fork Transition.
* **Double-click** the _Name_ value to edit it.
* **Type** `Copyediting` for the _Name_.  
* **Click** the _Save_ button. 
* **Click** the top Join Transition.
* **Double-click** the _Name_ value to edit it.
* **Type** `Review Complete` for the _Name_.  
* **Click** the _Save_ button. 
* **Click** the bottom Join Transition.
* **Double-click** the _Name_ value to edit it.
* **Type** `Copyediting Complete` for the _Name_.  
* **Click** the _Save_ button. 
* **Click** the last Transition to the right.
* **Double-click** the _Name_ value to edit it.
* **Type** `Ready to Publish` for the _Name_.  
* **Click** the _Save_ button. 

<img src="../images/kaleo-workflow-naming-complete.png" style="max-height:35%;" />

#### Set the Assignments for the Review Tasks
1. **Click** the _Administrator Review_ task.
* **Double-click** the _Assignments_ value to edit it.
* **Click** the drop-down.  
* **Choose** _Role Type_. 
* **Choose** _Regular_ from the _Role Type_ drop-down options. 
* **Click** the _Role Name_ box. 
* **Choose** _Administrator_ from the options. 
* **Click** the _Save_ button. 
* **Click** the _Copyediting Review_ task.
* **Double-click** the _Assignments_ value to edit it.
* **Click** the drop-down.  
* **Choose** _Role Type_. 
* **Choose** _Site_ from the _Role Type_ drop-down options. 
* **Click** the _Role Name_ box. 
* **Choose** _Site Content Reviewer_ from the options. 
* **Click** the _Save_ button.

<img src="../images/kaleo-workflow-roles.png" style="max-height:50%;" />

#### Set the Notification for the Administrator Review Task
1. **Click** the _Administrator Review_ task.
* **Double-click** the _Notifications_ value to edit it.
* **Type** _`Administrator Review Notification`_ for the _Name_.  
* **Choose** _Text_ for the _Template Language_ drop-down.  
* **Type** _`There is an item ready for Administrative Review`_ for the _Template_.
* **Choose** _User Notification_ for the _Notification Type_.  
* **Click** to open the _Recipient Type_ drop-down. 
* **Choose** _Task Assignees_. 
* **Click** the _Save_ button.

<img src="../images/kaleo-workflow-notification.png" style="max-height:35%;" />

#### Set the Notification for the Copyediting Review Task
1. **Click** the _Copyediting Review_ task.
* **Double-click** the _Notifications_ value to edit it.
* **Type** _`Copyediting Review Notification`_ for the _Name_.  
* **Choose** _Text_ for the _Template Language_ drop-down.
* **Type** _`There is an item ready for copyediting`_ for the _Template_.
* **Choose** _User Notification_ for the _Notification Type_.  
* **Click** to open the _Recipient Type_ drop-down. 
* **Choose** _Task Assignees_. 
* **Click** the _Save_ button.

<img src="../images/kaleo-role-and-notification-added.png" style="max-height:40%;" />

#### Add a 3 Day Review Timer for the Administrator Review Task
1. **Click** on the _Administrator Review_ task.
* **Double-click** on the _Timers_ value box to edit it.
* **Type** _Review Timer_ for the _Name_.
* **Type** `3` for the duration.
* **Choose** _Day_ from the _Scale_ drop-down menu.
* **Click** the _Blocking_ checkbox. 
* **Choose** _Notification_ for the _Type_ drop-down. 
* **Type** _`IMMEDIATE ACTION REQUIRED`_ for the _Name_.
* **Choose** _Text_ for the _Template Language_ drop-down.
* **Type** _`It has been 3 days since the item was submitted for review. Please review immediately or contact your project manager if there are any blockers.`_ for the _Template_.
* **Choose** _User Notification_ for the _Notification Type_.  
* **Click** to open the _Recipient Type_ drop-down. 
* **Choose** _Role Type_. 
* **Choose** _Regular_ from the _Role Type_ drop-down options. 
* **Click** the _Role Name_ box. 
* **Choose** _Administrator_ from the options. 
* **Click** the _Save_ button. 

<img src="../images/kaleo-workflow-timer.png" style="max-height:40%;" />

#### Add a 3 Day Review Timer for the Copyediting Review Task
1. **Click** on the _Copyediting Review_ task.
* **Double-click** on the _Timers_ value box to edit it.
* **Type** _Review Timer_ for the _Name_.
* **Type** `3` for the duration.
* **Choose** _Day_ from the _Scale_ drop-down menu.
* **Click** the _Blocking_ checkbox. 
* **Choose** _Notification_ for the _Type_ drop-down. 
* **Type** _`IMMEDIATE ACTION REQUIRED`_ for the _Name_.
* **Choose** _Text_ for the _Template Language_ drop-down.
* **Type** _`It has been 3 days since the item was submitted for copyediting. Please review immediately or contact your project manager if there are any blockers.`_ for the _Template_.
* **Choose** _User Notification_ for the _Notification Type_.  
* **Click** to open the _Recipient Type_ drop-down. 
* **Choose** _Role Type_. 
* **Choose** _Site_ from the _Role Type_ drop-down options. 
* **Click** the _Role Name_ box. 
* **Choose** _Site Content Reviewer_ from the options.
* **Click** the _Auto Create_ button.
* **Click** the _Save_ button. 
* **Click** _Publish_ at the bottom left.

<img src="../images/kaleo-finished.png" style="max-height:40%;" />

---

#### Bonus Exercises
1. Create a new piece of Web Content for the Livingstone Hotels & Resorts site. Assign the Parallel Review workflow definition to the folder.
2. Add a new piece of Web Content article and go through the review workflow. You will need to have a user assigned to the Site Content Reviewer role.
