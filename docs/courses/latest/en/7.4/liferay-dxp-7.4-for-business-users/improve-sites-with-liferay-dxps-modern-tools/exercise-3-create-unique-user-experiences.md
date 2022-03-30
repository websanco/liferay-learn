# Exercise 3: Create Unique User Experiences 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/-NWR91BD0oU

## Exercise Goals 

- Create an Experience for Mondego Employees 
	- Create a New User Segment 
	- Make Changes to the Welcome Page 
- Add a Custom Navigation Bar to the Welcome Page 
	- Create a Custom Navigation Menu 
	- Use Grid, Dropdown, and Menu Display Fragments to Create a Navigation Bar 

## Create a New Experience on the Mondego Community Welcome Page

1. **Sign In** to the Mondego Platform as your Administrator. 
2. **Open** the _Site Menu_. 
3. **Click** the _Site Selector_ (compass) icon. 
4. **Click** the _My Sites_ tab. 
5. **Choose** the _Mondego Community_ Site. 
6. **Click** the _Edit_ (pencil) icon on the _Welcome_ Page. 
7. **Click** _Default_ beside _Experience_ at the top left of the page. 
8. **Click** _New Experience_. 
9. **Type** `Mondego Employees` as the _Experience Name_. 
10. **Click** _New Segment_ beside _Audience_. 

## Add Conditions to the New User Segment 

1. **Type** `Mondego Employees` as the Title. 
2. **Go to** the _User_ section of the _Properties_ menu. 
3. **Drag and Drop** a _Site_ property onto the Conditions area. 
4. **Click** the _Select_ button next to _Site equals_. 
5. **Choose** _Mondego Community_. 
6. **Click** the _Duplicate_ (clipboard) icon beside _Select_ to duplicate the _Site_ property. 
7. **Click** the _Select_ button in the second _Site_ property. 
8. **Choose** _Mondego Group_. 
9. **Click** the _Duplicate_ icon again. 
10. **Click** the _Select_ button in the third _Site_ property. 
11. **Choose** _Mondego Publications_. 
12. **Go to** the _User_ section under _Properties_. 
13. **Drag and Drop** an _Email Address_ property below the last _Site_ property. 
14. **Click** the _equals_ drop-down. 
15. **Select** _contains_. 
16. **Type** `@mondego.com`. 

## Group the Site Properties and Define the Conditions 

1. **Click and Drag** the second _Site_ property onto the first to group them. 
	- A border should appear around the two properties to indicate they have been grouped. 
2. **Click and Drag** the third _Site_ property just below the second. 
	- The border should now appear around all three Site properties. 
3. **Click** the _And_ drop-down between the first and second properties. 
4. **Choose** _Or_. 
5. **Click** the _And_ drop-down between the second and third properties. 
6. **Choose** _Or_. 
	- Leave the default "And" between the grouped Site properties and the Email Address property to require Users in this Segment to have membership in one of the Sites AND a Mondego email address. 
7. **Click** _Save_ at the top right. 
8. **Click** _Save_ when you return to the _New Experience_ pop-up. 

## Make Changes to the Welcome Page in the Mondego Employees Experience 

1. **Click** the _Plus_ (+) icon to open the _Fragments and Widgets_ menu. 
2. **Go to** _Featured Content_ on the _Fragments_ tab. 
3. **Drag and Drop** a _Banner Center_ to the very bottom of the page above the footer. 
4. **Double-click** _Banner Title Example_ to edit the title. 
5. **Type** `Join the Community Team`. 
6. **Double-click** the _Paragraph_ component to edit. 
7. **Type** `Learn how you can support the Community as an Advisor, Expert, or Moderator`. 
8. **Double-click** the _Button_ component. 
9. **Type** `Learn More` as the new text. 
10. **Click** the main _Container_ component. 
	- This will be the outermost Container of the _Banner Center_ fragment. 
11. **Click** the _Styles_ tab in the _Browser_. 
12. **Go to** _Background Image_ under _Background_. 
13. **Click** _Select Image_. 
14. **Click** _Select File_. 
15. **Select** the `community-team-banner.jpg` from your unzipped exercise file folder. 
16. **Click** _Open_. 
17. **Click** _Add_. 

## Set the Mondego Employees Experience Priority 

1. **Click** the _Mondego Employees_ drop-down beside _Experience_ to open the _Experience_ menu. 
2. **Click** the _Prioritize Experience_ arrow (^) beside _Mondego Employees_. 
	- This will move the Mondego Employees Experience above the Default Experience. 
3. **Click** _Publish_. 

## Preview the Mondego Employees Experience 

1. **Open** the _Site Menu_. 
2. **Click** _Home_ to view the home page. 
3. **Click** the _Simulation_ icon (to the right of the gear icon). 
4. **Go to** _Segments_. 
5. **Click** the checkbox next to _Mondego Employees_. 
	- You should see the banner we added to the Mondego Employees Experience. 
6. **Click** each of the screen size options to preview the differences. 
7. **Close** the _Simulation_. 

## Create a Custom Navigation Menu 

1. **Open** the _Site Menu_. 
2. **Go to** `Site Builder` &rarr; `Navigation Menus`. 
3. **Click** the _Add_ button. 
4. **Type** `Mondego Links` for the _Name_. 
5. **Click** _Add_. 

## Add a Submenu and URL Elements for Mondego Sites 

1. **Click** _New_ to add an element. 
2. **Choose** _Submenu_. 
3. **Type** `Mondego Sites` for the _Name_. 
4. **Click** _Save_. 
5. **Click** the _Add_ button at the top right to add another element. 
6. **Choose** _URL_. 
7. **Type** `Mondego Group` for the _Name_. 
8. **Type** `http://localhost:8080/web/mondego-group` for the _URL_. 
	- The URL will be the default Friendly URL generated when the Site was created. If you do not have these Sites created, consult the Build New Sites with Liferay DXP module. 
9. **Click** _Add_. 
10. **Click** the _Add_ button to add another element. 
11. **Choose** _URL_. 
12. **Type** `Mondego Matters` for the _Name_. 
13. **Type** `http://localhost:8080/web/mondego-matters` for the _URL_. 
14. **Click** _Add_. 
15. **Click** the _Add_ button to add another element. 
16. **Choose** _URL_. 
17. **Type** `Mondego Publications` for the _Name_. 
18. **Type** `http://localhost:8080/web/mondego-publications` for the _URL_. 
19. **Click** _Add_. 
20. **Click and Drag** the _Mondego Group_ element to the right to nest it below the _Mondego Sites_ submenu element. 
21. **Click and Drag** the _Mondego Matters_ element to the right to line up with _Mondego Group_. 
22. **Click and Drag** the _Mondego Publications_ element to the right to line up with _Mondego Group_ and _Mondego Matters_. 
	- You should now have three URL elements nested below the Mondego Sites Submenu element. 

## Add a Submenu and URL Elements for Mondego Regional Banks 

1. **Click** the _Add_ button at the top right. 
2. **Choose** _Submenu_. 
3. **Type** `Mondego Regional Banks` for the _Name_. 
4. **Click** _Save_. 
5. **Click** the _Add_ button to add another element. 
6. **Choose** _URL_. 
7. **Type** `Mondego North America` for the _Name_. 
8. **Type** `http://localhost:8080/web/mondego-north-america` for the _URL_. 
9. **Click** _Add_. 
10. **Click** the _Add_ button to add another element. 
11. **Choose** _URL_. 
12. **Type** `Mondego UK` for the _Name_. 
13. **Type** `http://localhost:8080/web/mondego-uk` for the _URL_. 
14. **Click** _Add_. 
15. **Click** the _Add_ button to add another element. 
16. **Choose** _URL_. 
17. **Type** `Mondego Japan` for the _Name_. 
18. **Type** `http://localhost:8080/web/mondego-japan` for the _URL_. 
19. **Click** _Add_. 
20. **Click and Drag** the _Mondego North America_ element to the right to nest it below the _Mondego Regional Banks_ submenu element. 
21. **Click and Drag** the _Mondego UK_ element to the right to line up with _Mondego North America_. 
22. **Click and Drag** the _Mondego Japan_ element to the right to line up with _Mondego North America_ and _Mondego UK_. 
	- You should now have three URL elements nested below the Mondego Regional Banks Submenu element.  

## Add a Navigation Bar to the Welcome Page for the Mondego Employees Experience 

1. **Open** the _Site Menu_. 
2. **Go to** `Site Builder` &rarr; `Pages`. 
3. **Click** the _Options_ (three dots) icon beside _Welcome_. 
4. **Choose** _Edit_. 
5. **Click** _Default_ beside _Experience_ in the top editing bar. 
6. **Click** _Edit Experience_ (the pencil icon) next to _Mondego Employees_ Experience. 
7. **Click** _Save_ in the pop-up. 
8. **Open** the _Fragments and Widgets_ menu sidebar (+). 
9. **Drag and Drop** a _Grid_ Fragment onto the page below the _Highlights_ fragment and above the _Banner Center_. 
10. **Go to** the _Fragments and Widgets_ menu. 
11. **Drag and Drop** a _Dropdown_ Fragment into the first module of the _Grid_ fragment. 
12. **Drag and Drop** a _Dropdown_ Fragment into the second module of the _Grid_ fragment. 
13. **Double-click** the textbox in the first _Dropdown_ to edit. 
14. **Type** `Mondego Sites`. 
15. **Double-click** the textbox in the second _Dropdown_ to edit. 
16. **Type** `Mondego Regional Banks`. 

## Add Menu Display Fragments to the Dropdown Fragments 

1. **Click** the _Dropdown_ Fragment in the first module. 
2. **Check** the _Keep Panel Open in Edit Mode_ checkbox under _General_ in the _Browser_. 
3. **Open** the _Fragments and Widgets_ menu sidebar. 
4. **Type** `Menu` in the search bar. 
5. **Drag and Drop** a _Menu Display_ Fragment into the _Place fragments here_ box below the first _Dropdown_. 
6. **Click** the _Dropdown_ Fragment in the second module. 
7. **Check** the _Keep Panel Open in Edit Mode_ checkbox under _General_ in the _Browser_. 
8. **Open** the _Fragments and Widgets_ menu sidebar. 
9. **Type** `Menu` in the search bar. 
10. **Drag and Drop** a _Menu Display_ Fragment into the _Place fragments here_ box below the second _Dropdown_. 

## Configure the Menu Display Fragments in the Dropdown Fragments 

1. **Click** the _Menu Display_ under the _Mondego Sites_ Dropdown Fragment. 
2. **Go to** the _General_ tab in the _Browser_. 
3. **Click** _Public Pages Hierarchy_ to select a new source. 
4. **Click** _Mondego Links_. 
5. **Click** _Mondego Sites_. 
6. **Click** the _Select This Level_ button. 
	- You should see three elements: Mondego Group, Mondego Matters, and Mondego Publications. 
7. **Click** the _Menu Display_ under the _Mondego Regional Banks_ Dropdown Fragment. 
8. **Go to** the _General_ tab in the _Browser_. 
9. **Click** _Public Pages Hierarchy_ to select a new source. 
10. **Click** _Mondego Links_. 
11. **Click** _Mondego Regional Banks_. 
12. **Click** the _Select This Level_ button. 
	- You should see three elements: Mondego North America, Mondego UK, and Mondego Japan. 

## Manage the Grid Fragment Layout for Mobile Devices 

1. **Click** the first _Dropdown_ under _Page Elements_ in the _Browser_. 
2. **Click** the checkbox to deselect _Keep Panel Open in Edit Mode_ on the _General_ tab. 
3. **Click** the second _Dropdown_ under _Page Elements_. 
4. **Click** the checkbox to deselect _Keep Panel Open in Edit Mode_ on the _General_ tab. 
5. **Click** the _Grid_ Fragment. 
6. **Click** the _Styles_ tab next to _General_ in the _Browser_. 
	- You should see that the current Styles are for Desktop view and that the Grid Layout is currently 3 Modules per Row. 
7. **Click** the _Tablet_ icon in the Device Display at the top center of the page and scroll down to view the Grid Layout. 
8. **Click** the _Landscape Phone_ icon to view the layout. 
9. **Click** the _Portrait Phone_ icon to view the layout. 
10. **Click** _3 Modules per Row_ under _Layout_ in the _Styles_ tab. 
11. **Choose** _1 Module per Row_. 
12. **Click** _Top_ under _Vertical Alignment_. 
13. **Choose** _Middle_. 
14. **Click** _Publish_. 
	- You can preview the changes on different devices and for different experiences by clicking the Simulation icon once you exit the editing mode. 

---

## Bonus Exercise 

1. Add a second Experience to the Welcome Page on the Mondego Community Site. Name the New User Segment "Content Creators" and set it to include only those assigned the Site Content Creator Role. Give the Content Creators Experience priority above the Default and Mondego Employees Experiences. Add Fragments and Content to the Welcome Page for that Experience and view those changes. 

---

## Next Up

* [Summary](./summary.md)