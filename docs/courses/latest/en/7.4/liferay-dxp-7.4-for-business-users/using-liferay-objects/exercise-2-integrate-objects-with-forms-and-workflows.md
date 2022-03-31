# Exercise 2: Integrate Objects with Forms and Workflows 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/vkgPvVDQOPY 

## Exercise Goals 

- Integrate the New Accounts Object with a Form 
- Create a Workflow for Approving the Integrated Form Entries 
- Display the Integrated Form Entries Using a Collection 

## Create a New Accounts Application Form 

1. **Go to** `Content & Data` &rarr; `Forms` in the _Site Administration Menu_. 
2. **Click** the _Add_ icon. 
3. **Type** `New Account Application` where it reads _Untitled Form_. 
4. **Type** `A form for processing applications for new accounts` where it reads _Add a short description for this form_. 
5. **Click** the _Settings_ icon. 

## Integrate the New Accounts Application Form with the New Accounts Object 

1. **Choose** _Object_ under _Select a Storage Type_. 
2. **Choose** _New Account_ under _Select Object_. 
3. **Click** _Done_. 
4. **Drag and Drop** a _Text_ field onto the first page of the form. 
5. **Type** `Name` for _Label_. 
6. **Click** the slider next to _Required Field_. 
7. **Click** the _Advanced_ tab. 
8. **Choose** _Account Holder_ under _Object Field_. 
9. **Click** the _Back_ arrow. 
10. **Drag and Drop** a _Text_ field onto the form. 
11. **Type** `Email` for _Label_. 
12. **Click** the slider next to _Required Field_. 
13. **Click** the _Advanced_ tab. 
14. **Choose** _Email_ under _Object Field_. 
15. **Click** the _Back_ arrow. 
16. **Drag and Drop** a _Numeric_ field onto the form. 
17. **Type** `Phone Number` for _Label_. 
18. **Click** the slider next to _Required Field_. 
19. **Click** the _Advanced_ tab. 
20. **Choose** _Phone Number_ under _Object Field_. 
21. **Click** the _Back_ arrow. 

## Add Remaining Fields to the New Accounts Application Form 

1. **Drag and Drop** a _Select from List_ field onto the form. 
2. **Type** `Account Type` for _Label_. 
3. **Click** the slider next to _Required Field_. 
4. **Click** the _Advanced_ tab. 
5. **Choose** _Account Type_ under _Object Field_. 
6. **Click** the _Back_ arrow. 
7. **Drag and Drop** a _Numeric_ field onto the first page of the form. 
8. **Type** `Initial Balance` for _Label_. 
9. **Click** the slider next to _Required Field_. 
10. **Select** _Decimal_ under _My numeric type is:_. 
11. **Click** the _Advanced_ tab. 
12. **Choose** _Initial Balance_ under _Object Field_. 
13. **Click** the _Back_ arrow. 
14. **Drag and Drop** a _Date_ field onto the first page of the form. 
15. **Type** `Date of Request` for _Label_. 
16. **Click** the slider next to _Required Field_. 
17. **Click** the _Advanced_ tab. 
18. **Choose** _Date of Request_ under _Object Field_. 
19. **Click** the _Back_ arrow. 
20. **Click** _Publish_. 

## Create a Workflow for the New Accounts Application Form 

1. **Go to** `Applications` &rarr; `Workflow` &rarr; `Process Builder` in the _Global Menu_. 
2. **Click** the _Add_ icon. 
3. **Type** `Account Approval Process` where it reads _Untitled Workflow_. 
4. **Click** on the _End Node_. 
5. **Delete** the default connector by clicking on the _x_ next to the End Node. 
6. **Drag and Drop** a _Task_ node into the workspace. 
7. **Double-click** the newly added _Task_ node. 
8. **Type** `Review 1` for _Name_. 
9. **Click** _Save_. 
10. **Choose** _Role_ under _Assignment Type_ next to _Assignments_. 
11. **Choose** _Site Content Reviewer_ under _Role_. 
12. **Click** _Save_. 
13. **Drag and Drop** a _Task_ node into the workspace. 
14. **Double-click** the newly added _Task_ node. 
15. **Type** `Review 2` for _Name_. 
16. **Click** _Save_. 
17. **Choose** _Role_ under _Assignment Type_ next to _Assignments_. 
18. **Choose** _Site Administrator_ under _Role_. 
19. **Click** _Save_. 
20. **Click** the _StartNode_. 
21. **Double-click** the _Name_ value in the _Properties_ tab that opens. 
22. **Type** `Submitted` for the _Name_. 
23. **Click** the _Save_ button. 
24. **Click** the _EndNode_. 
25. **Double-click** the _Name_ value in the _Properties_ tab that opens. 
26. **Type** `Approved` for the _Name_. 
27. **Click** the _Save_ button. 

## Connect All of the Nodes with Transitions

1. **Drag** a _Transition_ arrow from the _Submitted_ node to the _Review 1_ Task. 
	- To drag a transition, place your cursor on the edge of the node so that you see a thin plus (+) sign. When it appears, click and drag the arrow to the next node, connecting it by positioning it to see the orange circle. 
2. **Drag** a _Transition_ arrow from the _Review 1_ Task to the _Review 2_ Task. 
3. **Drag** a _Transition_ arrow from the _Review 2_ Task to the _Approved_ node. 

## Name the New Transitions 

1. **Click** the first Transition from _Submitted_ to _Review 1_. 
2. **Double-click** the _Name_ value in the _Properties_ tab to edit it. 
3. **Type** `Submit for Review` for the _Name_.  
4. **Click** the _Save_ button. 
5. **Click** the Transition going from _Review 1_ to _Review 2_. 
6. **Double-click** the _Name_ value in the _Properties_ tab to edit it. 
7. **Type** `Initial Approval` for the _Name_. 
8. **Click** the _Save_ button. 
9. **Click** the last Transition from _Review 2_ to _Approved_. 
10. **Double-click** the _Name_ value to edit it. 
11. **Type** `Final Approval` for the _Name_. 
12. **Click** the _Save_ button. 
13. **Click** _Publish_. 

## Add the Account Approval Process Workflow to New Account Entries 

1. **Open** the Global Menu. 
2. **Click** on the _Mondego Group_ site. 
3. **Go to** `Configuration` &rarr; `Workflow` in the _Site Administration Menu_. 
4. **Click** _Edit_ next to _New Account_. 
5. **Choose** _Account Approval Process_. 
6. **Click** _Save_. 

## Add the New Account Application Form to a Page 

1. **Click** _Home_ in the _Site Administration Panel_. 
2. **Click** the _Edit_ (small pencil) icon. 
3. **Click** the _+_ icon to open the Fragments and Widgets sidebar. 
4. **Drag and Drop** a _Container_ layout element onto the page. 
5. **Click** _Widgets_. 
6. **Drag and Drop** a _Form_ widget into the _Container_ element. 
7. **Click** the _Options_ icon in the _Form_ widget. 
8. **Click** _Configuration_. 
9. **Choose** _New Account Application_. 
10. **Click** _Save_. 
11. **Close** the pop-up. 
12. **Click** _Publish_. 

## Submit an Entry for the New Account Application Form 

1. **Fill out** the fields of the New Account Application Form. 
	- The exact details here don't really matter so feel free to fill the form out as yourself, a friend, or your Administrative User. 
2. **Click** _Submit_. 

## Approve the New Account Submission as a Site Content Reviewer 

1. **Sign out** as your administrator. 
	- Before you do this, be sure that you have created two additional Users, added them as members of the Mondego Group Site, and assigned them the Roles of Site Content Reviewer and Site Administrator, respectively. 
2. **Sign in** as your Site Content Reviewer. 
3. **Open** the _Personal Menu_. 
4. **Click** _My Workflow Tasks_. 
5. **Click** the _Assigned to My Role_ tab. 
6. **Click** the _Options_ icon. 
7. **Choose** _Assign to Me_. 
8. **Click** _Done_. 
9. **Click** the _Options_ icon. 
10. **Choose** _Initial Approval_. 
11. **Click** _Done_. 

## Approve the New Account Submission as a Site Administrator 

1. **Sign out** as your Site Content Reviewer. 
2. **Sign in** as your Site Administrator. 
3. **Open** the _Personal Menu_. 
4. **Click** _My Workflow Tasks_. 
5. **Click** the _Assigned to My Role_ tab. 
6. **Click** the _Options_ icon. 
7. **Choose** _Assign to Me_. 
8. **Click** _Done_. 
9. **Click** the _Options_ icon. 
10. **Choose** _Final Approval_. 
11. **Click** _Done_. 

## Create a Display Page Template for New Account Entries 

1. **Sign out** as your Site Administrator. 
2. **Sign in** as your Platform Administrator. 
3. **Go to** `Design` &rarr; `Page Templates` in the _Site Administration Menu_. 
4. **Click** the _Display Page Templates_ tab. 
5. **Click** the _Add_ icon. 
6. **Choose** _Blank_ for the Master Page. 
7. **Type** `New Accounts` for _Name_. 
8. **Choose** _New Account_ for _Content Type_. 
9. **Click** _Save_. 

## Design the New Accounts Display Page Template 

1. **Drag and Drop** a _Container_ element onto the page. 
2. **Drag and Drop** a _Heading_ component into the _Container_. 
3. **Click** the _Heading_. 
4. **Click** _element-text_ in the sidebar. 
5. **Choose** _Account Holder_ under _Field_. 
6. **Click** the _+_ icon to open the Fragments and Widgets sidebar. 
7. **Drag and Drop** a _Grid_ element onto the page underneath the _Heading_. 
8. **Click** the _Grid_. 
9. **Choose** _2_ for _Number of Modules_. 
10. **Click** the box to uncheck _Show Gutter_. 
11. **Click** the _+_ icon to open the Fragments and Widgets sidebar. 
12. **Drag and Drop** a _Paragraph_ component into the left section of the _Grid_. 
13. **Click** the _Paragraph_. 
14. **Click** _element-text_ in the sidebar. 
15. **Choose** _Account Type_ under _Field_. 
16. **Drag and Drop** a _Paragraph_ component into the right section of the _Grid_. 
17. **Click** the _Paragraph_. 
18. **Click** _element-text_ in the sidebar. 
19. **Choose** _Initial Balance_ under _Field_. 
20. **Click** the _+_ icon to open the Fragments and Widgets sidebar. 
21. **Drag and Drop** a _Separator_ component beneath the _Grid_. 
22. **Drag and Drop** a _Grid_ element onto the page underneath the _Separator_. 
23. **Click** the _Grid_. 
24. **Choose** _2_ for _Number of Modules_. 
25. **Click** the box next to _Show Gutter_. 
26. **Click** the _+_ icon to open the Fragments and Widgets sidebar. 
27. **Drag and Drop** a _Paragraph_ component into the left section of the _Grid_. 
28. **Click** the _Paragraph_. 
29. **Click** _element-text_ in the sidebar. 
30. **Choose** _Email_ under _Field_. 
31. **Click** the _+_ icon to open the Fragments and Widgets sidebar. 
32. **Drag and Drop** a _Paragraph_ component into the right section of the _Grid_. 
33. **Click** the _Paragraph_. 
34. **Click** _element-text_ in the sidebar. 
35. **Choose** _Phone Number_ under _Field_. 
36. **Click** the _+_ icon to open the Fragments and Widgets sidebar. 
37. **Drag and Drop** a _Separator_ component beneath the _Grid_. 
38. **Drag and Drop** a _Container_ element onto the page beneath the _Separator_. 
39. **Drag and Drop** a _Paragraph_ component into the _Container_. 
40. **Click** the _Paragraph_. 
41. **Click** _element-text_ in the sidebar. 
42. **Choose** _Date of Request_ under _Field_. 
43. **Click** _Publish_. 

## Add a Private Page for Displaying New Account Entries 

1. **Go to** `Site Builder` &rarr; `Pages` in the _Site Administration Menu_. 
2. **Click** _Pages_ where it reads _Pages_ &rarr; _Public Pages_. 
3. **Click** the _Add_ icon. 
4. **Choose** _Private Page_. 
5. **Click** _Blank_ for the template. 
6. **Type** `New Account Applications` for _Name_. 
7. **Click** the _+_ icon to open the Fragments and Widgets sidebar. 
8. **Drag and Drop** a _Collection Display_ fragment onto the page. 
9. **Click** the _Collection Display_. 
10. **Click** the _Add_ icon next to _Select Collection_. 
11. **Click** the _Collection Providers_ tab. 
12. **Choose** _New Accounts_. 
13. **Choose** _Table_ under _List Style_. 
14. **Click** _Publish_. 

---

## Bonus Exercise 

1. Create a workflow for reviewing PTO requests that includes at least two different reviewers. Submit a request and go through the approval process using the newly created workflow. 

---

## Next Up

* [Summary](./summary.md)