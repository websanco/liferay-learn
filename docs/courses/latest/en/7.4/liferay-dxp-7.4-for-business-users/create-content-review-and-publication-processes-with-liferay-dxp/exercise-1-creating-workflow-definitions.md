# Exercise 1: Creating Workflow Definitions 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/ja5uBzptelc

## Exercise Goals 

* Create a Workflow definition 
* Activate the Workflow and go through a review process 

## Create a New Workflow with Kaleo Designer 

1. **Sign In** as your Marvin Robotics Administrator. 
	- If you followed the prerequisites, this will be with the test@marvinrobotics.com account. 
2. **Open** the _Global Menu_. 
3. **Go to** `Applications` &rarr; `Workflow` &rarr; `Process Builder`. 
4. **Click** the _Add_ button to add a new Workflow. 
5. **Type** `Marketing Content Review` for the _Title_. 
6. **Click** on the _Connector_ to highlight it. 
	- The transition connector is the line between the StartNode and EndNode. 
7. **Delete** the _Connector_. 
	- This can be done by pressing the _Delete_ key on your keyboard. 
8. **Click** _OK_ on the pop-up. 
9. **Drag** the _StartNode_ to the left-middle of the editor. 
10. **Drag** the _EndNode_ to the right-middle of the editor. 
11. **Click** the _StartNode_. 
12. **Double-click** the _Name_ value in the _Properties_ tab that opens. 
13. **Type** `Submitted` for the _Name_. 
14. **Click** the _Save_ button. 
15. **Click** the _EndNode_. 
16. **Double-click** the _Name_ value in the _Properties_ tab that opens. 
17. **Type** `Approved` for the _Name_. 
18. **Click** the _Save_ button. 

## Add the Review and Update Task Nodes 

1. **Click** the _Nodes_ tab. 
2. **Drag** a _Task_ node to the right of the _StartNode_. 
3. **Click** the new node. 
4. **Double-click** the _Name_ value in the _Properties_ tab to the left of the editor. 
5. **Type** `Manager Review` for the _Name_. 
6. **Click** the _Save_ button. 
7. **Click** the _Nodes_ tab. 
8. **Drag** a _Task_ node onto the editor between the _Manager Review_ and _EndNode_. 
9. **Click** the new node. 
10. **Double-click** the _Name_ value in the _Properties_ tab to edit it. 
11. **Type** `Final Review` for _Name_. 
12. **Click** the _Save_ button. 
13. **Click** the _Nodes_ tab. 
14. **Drag** a _Task_ node to the top left of the editor above the _StartNode_ and _Manager Review_. 
15. **Click** the new node. 
16. **Double-click** the _Name_ value in the _Properties_ tab to edit it. 
17. **Type** `Update Content` for _Name_. 
18. **Click** the _Save_ button. 

## Connect All of the Nodes with Transitions 

1. **Drag** a _Transition_ arrow from the _Submitted_ node to the _Manager Review_ Task. 
	- To drag a transition, place your cursor on the edge of the node so that you see a thin plus (+) sign. When it appears, click and drag the arrow to the next node, connecting it by positioning it to see the orange circle. 
2. **Drag** a _Transition_ arrow from the _Manager Review_ Task to the _Update Content_ Task. 
3. **Drag** a _Transition_ arrow from the _Update Content_ Task to the _Manager Review_ Task. 
4. **Drag** a _Transition_ arrow from the _Manager Review_ Task to the _Final Review_ Task. 
5. **Drag** a _Transition_ arrow from the _Final Review_ Task to the _Update Content_ Task. 
6. **Drag** a _Transition_ arrow from the _Final Review_ Task to the _Approved_ node. 

## Name the New Transitions 

1. **Click** the first Transition from _Submitted_ to _Manager Review_. 
2. **Double-click** the _Name_ value in the _Properties_ tab to edit it. 
3. **Type** `Submit for Review` for the _Name_. 
4. **Click** the _Save_ button. 
5. **Click** the Transition going from _Manager Review_ to _Update Content_. 
6. **Double-click** the _Name_ value in the _Properties_ tab to edit it. 
7. **Type** `Reject` for the _Name_. 
8. **Click** the _Save_ button. 
9. **Click** the Transition going from _Update Content_ to _Manager Review_. 
10. **Double-click** the _Name_ value to edit it. 
11. **Type** `Resubmit` for the _Name_. 
12. **Click** the _Save_ button. 
13. **Click** the Transition between _Manager Review_ and _Final Review_. 
14. **Double-click** the _Name_ value to edit it. 
15. **Type** `Initial Approval` for the _Name_. 
16. **Click** the _Save_ button. 
17. **Click** the Transition going from _Final Review_ to _Update Content_. 
18. **Double-click** the _Name_ value to edit it. 
19. **Type** `Reject` for the _Name_. 
20. **Click** the _Save_ button. 
21. **Click** the last Transition from _Final Review_ to _Approved_. 
22. **Double-click** the _Name_ value to edit it. 
23. **Type** `Final Approval` for the _Name_. 
24. **Click** the _Save_ button. 

## Set the Assignments for the Review Tasks 

1. **Click** the _Manager Review_ task. 
2. **Double-click** the _Assignments_ value in the _Properties_ tab to edit it. 
3. **Click** the drop-down to select _Assignment Type_. 
4. **Choose** _Role Type_. 
5. **Choose** _Site_ from the _Role Type_ drop-down options. 
6. **Click** the _Role Name_ box. 
7. **Choose** _Site Content Reviewer_ from the options. 
8. **Click** the _Save_ button. 
9. **Click** the _Final Review_ task. 
10. **Double-click** the _Assignments_ value to edit it. 
11. **Click** the drop-down. 
12. **Choose** _Role Type_. 
13. **Choose** _Site_ from the _Role Type_ drop-down options. 
14. **Click** the _Role Name_ box. 
15. **Choose** _Site Administrator_ from the options. 
16. **Click** the _Save_ button. 

## Set the Notification for the Manager Review Task 

1. **Click** the _Manager Review_ task. 
2. **Double-click** the _Notifications_ value to edit it. 
3. **Type** `Manager Review Notification` for the _Name_. 
4. **Choose** _Text_ from the _Template Language_ drop-down. 
5. **Type** `There is an item ready for Managerial Review` for the _Template_. 
6. **Choose** _User Notification_ for the _Notification Type_. 
7. **Click** to open the _Recipient Type_ drop-down. 
8. **Choose** _Task Assignees_. 
9. **Click** the _Save_ button. 

## Set the Notification for the Final Review Task 

1. **Click** the _Final Review_ task. 
2. **Double-click** the _Notifications_ value to edit it. 
3. **Type** `Final Review Notification` for the _Name_. 
4. **Choose** _Text_ from the _Template Language_ drop-down. 
5. **Type** `There is an item ready for final review` for the _Template_. 
6. **Choose** _User Notification_ for the _Notification Type_. 
7. **Click** to open the _Recipient Type_ drop-down. 
8. **Choose** _Task Assignees_. 
9. **Click** the _Save_ button. 

## Set the Notification for the Update Content Task 

1. **Click** the _Update Content_ task. 
2. **Double-click** the _Notifications_ value to edit it. 
3. **Type** `Update Content Notification` for the _Name_. 
4. **Choose** _Text_ from the _Template Language_ drop-down. 
5. **Type** `There is an item that needs updated` for the _Template_. 
6. **Choose** _User Notification_ for the _Notification Type_. 
7. **Click** to open the _Recipient Type_ drop-down. 
8. **Choose** _Task Assignees_. 
	- The Task Assignee should be the default value, _Asset Creator_. 
9. **Click** the _Save_ button. 

## Add a 3 Day Review Timer for the Manager Review Task 

1. **Click** on the _Manager Review_ task. 
2. **Double-click** on the _Timers_ value box to edit it. 
3. **Type** `Review Timer` for the _Name_. 
4. **Type** `3` for the duration. 
5. **Choose** _Day_ from the _Scale_ drop-down menu. 
6. **Click** the _Blocking_ checkbox. 
7. **Choose** _Notification_ for the _Type_ drop-down. 
8. **Type** `IMMEDIATE ACTION REQUIRED` for the _Name_. 
9. **Choose** _Text_ for the _Template Language_ drop-down. 
10. **Type** `It has been 3 days since the item was submitted for review. Please review immediately.` for the _Template_. 
11. **Choose** _User Notification_ for the _Notification Type_. 
12. **Click** to open the _Recipient Type_ drop-down. 
13. **Choose** _Role Type_. 
14. **Choose** _Site_ from the _Role Type_ drop-down options. 
15. **Click** the _Role Name_ box. 
16. **Choose** _Site Content Reviewer_ from the options. 
17. **Click** the _Save_ button. 

## Add a 3 Day Review Timer for the Final Review Task 

1. **Click** on the _Final Review_ task. 
2. **Double-click** on the _Timers_ value box to edit it. 
3. **Type** `Review Timer` for the _Name_. 
4. **Type** `3` for the duration. 
5. **Choose** _Day_ from the _Scale_ drop-down menu. 
6. **Click** the _Blocking_ checkbox. 
7. **Choose** _Notification_ for the _Type_ drop-down. 
8. **Type** `IMMEDIATE ACTION REQUIRED` for the _Name_. 
9. **Choose** _Text_ for the _Template Language_ drop-down. 
10. **Type** `It has been 3 days since the item was submitted for Final Review. Please review immediately.` for the _Template_. 
11. **Choose** _User Notification_ for the _Notification Type_. 
12. **Click** to open the _Recipient Type_ drop-down. 
13. **Choose** _Role Type_. 
14. **Choose** _Site_ from the _Role Type_ drop-down options. 
15. **Click** the _Role Name_ box. 
16. **Choose** _Site Administrator_ from the options. 
17. **Click** the _Save_ button. 
18. **Click** _Publish_ at the bottom left. 

## Assign a User to the Review Roles 

1. **Open** the _Global Menu_. 
2. **Go to** `Control Panel` &rarr; `Users` &rarr; `Users and Organizations`. 
3. **Select** your main Administrative User. 
	- This will be Test Test if you followed the prerequisites. 
4. **Click** _Roles_ in the menu at the left. 
5. **Click** _Select_ beside _Site Roles_. 
6. **Click** _Choose_ beside _Marvin Robotics_. 
7. **Choose** _Site Content Reviewer_. 
8. **Click** _Select_ beside _Site Roles_. 
9. **Click** _Choose_ beside _Marvin Robotics_. 
10. **Choose** _Site Administrator_. 
11. **Click** _Save_. 
	- Note: For simplicity, we are only using one User for this exercise. If you would like to complete the exercise using additional Users, first go to Control Panel &rarr; Configuration &rarr; Instance Settings. Open the User Authentication Settings and deselect "Require strangers to verify their email address?".Then, you can add a new Users under Users and Organizations, providing them with a username, email address, first and last name, and password. Be sure to assign new Users Membership to the Marvin Robotics Site before assigning them Roles. For more information on Users and Roles, consult the module "Add Users and Manage Permissions." 

## Activate the Marketing Content Review Workflow 

1. **Open** the _Global Menu_. 
2. **Click** _View All_ on the _Sites_ tab to view all Sites. 
3. **Click** the _My Sites_ tab. 
4. **Select** the _Marvin Robotics_ Site. 
5. **Open** the _Site Menu_. 
6. **Go to** `Content & Data` &rarr; `Web Content`. 
7. **Click** the _Add_ button at the top right. 
8. **Choose** _Folder_. 
9. **Type** `Marketing` as the _Name_. 
10. **Click** _Save_. 
11. **Open** the _Options_ (three dots) icon beside the new folder. 
12. **Select** _Edit_. 
13. **Go to** the _Structure Restrictions and Workflow_ section. 
	- You may need to expand this section by clicking the arrow (>). 
14. **Click** _Default Workflow for This Folder (Marketing)_. 
15. **Choose** _Marketing Content Review_ from the drop-down menu. 
16. **Click** _Save_. 

## Add a Web Content Article to the Marketing Folder 

1. **Click** the _Marketing_ folder. 
2. **Click** the _Add_ button to add content to the folder. 
3. **Choose** _Basic Web Content_. 
4. **Type** `New Products January 2022` for the _Title_. 
5. **Type** `Marvin Robotics is excited to announce the launch of three new Robotic Arms this January.` as the _Content_. 
6. **Click** _Submit for Publication_ at the top right. 
	- You should see a blue _Pending_ label underneath the title and a red notification bubble on the Personal Menu icon. 

## Go Through the Workflow Process 

1. **Open** the _Personal Menu_. 
2. **Click** _Notifications_. 
	- You should see a list of all Notifications. Unread Notifications will appear in Bold. 
3. **Click** the top Notification, _There is an item ready for Managerial Review_. 
4. **Click** the _Options_ (three dots) icon next to _Assigned to_. 
5. **Choose** _Assign to Me_. 
6. **Click** _Done_ in the pop-up. 
7. **Click** the _Options_ icon. 
8. **Select** _Initial Approval_. 
9. **Type** `Looks good.` in the _Comment_ box. 
10. **Click** _Done_. 
	- You should now see a list of all workflow tasks assigned to you. Additionally, you should see a new Notification bubble on the Personal Menu icon. 
11. **Click** the _Assigned to My Roles_ tab next to _Assigned to Me_. 
12. **Click** _New Products January 2022_. 
13. **Click** the _Options_ icon next to _Assigned to_. 
14. **Choose** _Assign to Me_. 
15. **Click** _Done_ in the pop-up. 
16. **Click** the _Options_ icon. 
17. **Choose** _Reject_. 
18. **Type** `Needs more detail.` in the _Comment_ box. 
19. **Click** _Done_. 
20. **Open** the _Personal Menu_. 
21. **Click** _Notifications_. 
	- You should now see the latest Notification is "There is an item that needs updated." 

---

## Bonus Exercises 

1. Add a few more lines to the New Products January 2022 Web Content Article. Resubmit the article and go through the Marketing Content Workflow again, this time Approving it at both the Manager Review and the Final Review. 
2. Create another Workflow. Use Fork and Join nodes to allow two kinds of Review, Copyedit and Content, at the same time. (The Fork node should be placed between the StartNode and two Task nodes and the Join node should be placed after the Task nodes and before the EndNode.) 

---

## Next Up

* [Preparing Pages and Content for Production](./preparing-pages-content-for-production.md)

## Previous Step

* [Create Custom Workflow Definitions](./create-custom-workflow-definitions.md)