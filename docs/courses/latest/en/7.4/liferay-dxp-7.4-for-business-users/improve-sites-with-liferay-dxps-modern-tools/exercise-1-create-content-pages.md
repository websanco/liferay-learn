# Exercise 1: Create Content Pages and Fragments

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/Ks8wbbdXmz4

## Exercise Goals 

- Create the Mondego Community Site 
- Add a Content Page to the Mondego Community Site 
- Edit the Content Page using the Site Content Creator Role 

## Create the Mondego Community Site 

1. **Sign In** as your Mondego Administrator. 
	- If you followed the steps in the Add Users and Manage Permissions module, this will be Elliot Quinn. If not, the default admin, Test Test, works as well. 
2. **Open** the _Global Menu_. 
3. **Go to** `Control Panel` &rarr; `Sites` &rarr; `Sites`. 
4. **Click** _Add_. 
5. **Choose** the _Blank_ Site Template. 
6. **Type** `Mondego Community` as the _Name_. 
7. **Click** _Save_. 

## Add a Content Page to the Mondego Community Site 

1. **Open** the _Site Menu_. 
2. **Go to** `Site Builder` &rarr; `Pages`. 
3. **Click** the _Add_ button. 
4. **Choose** _Public Page_. 
5. **Click** _Blank_. 
6. **Type** `Welcome` as the _Name_. 
7. **Click** _Add_. 

## Add Fragments and Widgets to the Content Page 

1. **Click** the _Fragments and Widgets_ icon (+) in the sidebar. 
2. **Go to** _Basic Components_ on the _Fragments_ tab. 
3. **Drag and Drop** a _Slider_ Fragment onto the page. 
4. **Go to** _Featured Content_ on the _Fragments_ tab. 
5. **Drag and Drop** a _Highlights_ Fragment onto the page. 
6. **Go to** the _Widgets_ tab. 
7. **Drag and Drop** an _Asset Publisher_ Widget onto the page. 

## Configure the Slider Fragment 

1. **Click** the _Slider_ Fragment. 
2. **Go to** the _General_ tab of the _Browser_. 
3. **Type** `2` for the _Number of Slides_. 
4. **Click** `01-01-image` under _Page Elements_. 
5. **Click** _Select Image_ under the _Image Source_ tab. 
6. **Click** _Select File_. 
7. **Select** `mondego-slider-1.jpg` from your exercise file folder. 
8. **Click** _Open_. 
9. **Click** _Add_. 
	- You should see the Mondego Group banner. 
10. **Click** `02-01-image`. 
11. **Click** _Select Image_ under the _Image Source_ tab. 
12. **Click** _Select File_. 
13. **Select** `mondego-slider-2.jpg` from your exercise file folder. 
14. **Click** _Open_. 
15. **Click** _Add_. 

## Configure the Highlights Fragment 

1. **Click** the _Container_ Fragment below the _Slider_ in the _Browser_. 
2. **Click** the _Plus_ (+) sign beside _Container_ to expand all the sub-sections if it is not already expanded. 
3. **Click** the _Grid_ fragment. 
4. **Click** the _Styles_ tab. 
	- Take note of the current Layout. It should read 3 Modules per Row. 
5. **Click** the _Tablet_ icon in the _Device Display_ at the top center of the page. 
	- Notice how the Layout changes to 1 Module per Row. 
6. **Click** _1 Module per Row_ under _Layout_. 
7. **Choose** _3 Modules per Row_. 
8. **Click** _Landscape Phone_ in the _Device Display_. 
	- The Layout should be set to 1 Module per Row. 
9. **Click** _Portrait Phone_ in _Device Display_. 
	- The Layout should be set to 1 Module per Row. 

## Hide the Slider Fragment in Landscape and Portrait Phone View 

1. **Click** the _Slider_ Fragment. 
2. **Check** the _Hide Fragment_ checkbox in the _Styles_ tab. 
	- The slider should disappear in this view, moving the next Fragment to the top of the page. 
3. **Click** _Landscape Phone_ in the _Device Display_. 
4. **Check** the _Hide Fragment_ checkbox in the _Styles_ tab. 
5. **Click** _Tablet_ in the _Device Display_ to view the slider in that view. 
6. **Click** _Desktop_ in the _Device Display_ to return to the Default view. 

## Configure the Asset Publisher Widget 

1. **Click** the _Asset Publisher_ Widget to select. 
2. **Click** the _Options_ icon (three dots) in the top right corner of the widget. 
3. **Click** _Configuration_. 
4. **Go to** the _Source_ section under _Setup &rarr; Asset Selection_. 
5. **Click** _Any_ under _Asset Type_ to open the drop-down. 
6. **Choose** _Blogs Entry_. 
7. **Click** the _Display Settings_ tab. 
8. **Click** the _20_ under _Number of Items to Display_. 
9. **Type** `5`. 
10. **Click** _Save_. 
	- Since we have not yet added any Blog Entries, the Asset Publisher widget will read "There are no results." 
11. **Close** the pop-up. 

## Add a Comment to the Slider Fragment 

1. **Click** the _Comments_ icon in the sidebar. 
2. **Click** the _Slider_ Fragment. 
3. **Type** `@naomi.engel Please add a Header to the second slide.` in the comment box. 
4. **Click** _Comment_. 
5. **Click** the first image in the grid fragment. 
6. **Type** `@naomi.engel Add images and headers for these cards.` in the comment box. 
7. **Click** _Comment_. 
8. **Click** _Publish_. 

## Manage Mentions for the Mondego Platform 

1. **Open** the _Global Menu_. 
2. **Go to** `Control Panel` &rarr; `Configuration` &rarr; `Instance Settings`. 
3. **Click** _Community Tools_ under _Content and Data_. 
4. **Click** _Mentions_ in the menu at the side. 
	- By default, Mentions is enabled for all Users on all Sites. 
5. **Click** _Define Mentions Capability for Users_. 
	- You should see two options with checkboxes: Site Members Can Mention Each Other and Friends Can Mention Each Other. 
6. **Click** _All Users Can Mention Each Other_ to return to the default setting. 
7. **Click** _Save_. 

## Assign Naomi Engel the Site Content Creator Role 

1. **Open** the _Global Menu_. 
2. **Go to** `Control Panel` &rarr; `Users` &rarr; `Users and Organizations`. 
3. **Click** _Naomi Engel_. 
4. **Go to** _Memberships_ in the menu on the left. 
5. **Click** _Select_ next to _Sites_. 
6. **Choose** _Mondego Community_. 
7. **Click** _Save_. 
8. **Click** _Roles_ in the menu. 
9. **Click** _Select_ beside _Site Roles_. 
10. **Choose** _Mondego Community_. 
11. **Choose** _Site Content Creator_. 
12. **Click** _Save_. 

## Define the Site Content Creator Role Permissions 

1. **Open** the _Global Menu_. 
2. **Go to** `Control Panel` &rarr; `Users` &rarr; `Roles`. 
3. **Click** the _Site Roles_ tab. 
4. **Click** on the _Site Content Creator_ Role. 
5. **Click** the _Define Permissions_ tab. 
6. **Open** the _Site and Asset Library Administration_ drop-down. 
7. **Open** the _Site Builder_ drop-down. 
8. **Click** the _Pages_ option. 
9. **Check** _Access in Site and Asset Library Administration_ under _General Permissions_. 
10. **Check** _View_ under _General Permissions_. 
11. **Check** _View in Site and Asset Library Administration Menu_ under _Site_. 
12. **Check** _Update Page Content_ under _Page_. 
13. **Check** _View_ under _Page_. 
14. **Click** _Save_. 

## Sign In as Naomi Engel 

1. **Open** the _Personal Menu_. 
2. **Click** _Sign Out_. 
3. **Click** _Sign In_. 
4. **Sign In** as _Naomi Engel_. 
	- If you followed the steps in the Add Users and Manage Permissions module, you will use the email namoi.engel@mondego.com and the password _t3st_. If you have never signed in as Naomi Engel before, you will also need to complete the following steps. 
5. **Click** _I Agree_ to the _Terms of Use_. 
6. **Type** a _New Password_ and _Enter Again_. 
7. **Click** _Save_. 
8. **Choose** a _Password Reminder_ question. 
9. **Type** an answer to the _Password Reminder_. 
10. **Click** _Save_. 

## Edit the Content Page as Naomi Engel 

1. **Open** the _Personal Menu_. 
2. **Click** _Notifications_ to view your notifications. 
	- You should see a red notification bubble on the Personal Menu icon and two unread notifications in your Notifications List. 
3. **Open** the _Personal Menu_. 
4. **Click** _My Sites_. 
5. **Click** the _My Sites_ tab. 
6. **Choose** _Mondego Community_. 
7. **Click** the _Edit_ (pencil) icon at the top of the _Welcome_ page. 
8. **Open** the _Browser_ sidebar. 
9. **Click** the _Page Content_ tab. 
	- You will see a list of all editable content on the page, including documents and inline text. 
10. **Click** the _Edit_ (pencil) icon beside _Slide 2 Title_ to edit. 
11. **Type** `Mondego Makes a Difference` as the title. 
12. **Double-click** the textbox to open the formatting options menu. 
13. **Click** _Heading 2_. 
14. **Select** _Heading 1_. 
15. **Click** the _Color_ (paint bucket) icon. 
16. **Choose** _Primary_. 
17. **Double-click** the _Slide 2 Subtitle_ textbox to edit it. 
18. **Delete** the text. 

## Add Images to the Highlights Fragment 

1. **Click** the _Page Elements_ tab in the _Browser_. 
2. **Click** the '+' icon next to the Container. 
3. **Open** all drop-downs to find the first image-square element. 
4. **Click** the `image-square` under the first _Module_ of the _Grid_. 
5. **Click** _Select Image_ under _Image Source_. 
6. **Drag and Drop** `ask-an-expert.jpg` from the exercise file folder onto _Drag & Drop to Upload_. 
7. **Click** _Add_. 
8. **Double-click** the _Heading Example_ textbox to edit. 
9. **Type** `Ask an Expert`. 
10. **Double-click** the image placeholder in the second module. 
11. **Drag and Drop** `personal-finance.jpg` from the exercise file folder onto _Drag & Drop to Upload_. 
12. **Click** _Add_. 
13. **Double-click** the _Heading Example_ textbox to edit. 
14. **Type** `Manage Your Finances`. 
15. **Double-click** the image placeholder in the third module. 
16. **Drag and Drop** `investment-management.jpg` from the exercise file folder onto _Drag & Drop to Upload_. 
17. **Click** _Add_. 
18. **Double-click** the _Heading Example_ textbox to edit. 
19. **Type** `Invest with Confidence`. 
20. **Click** _Publish_. 

---

## Bonus Exercises 

1. Add two more Content Pages, called "Join the Community" and "FAQ." Add at least two Fragments of your choice to each page and adjust the settings for at least one fragment to Hide or Show on different devices. 
2. Grant the Site Content Creator Role to another User and edit the Fragments of the Welcome Page as that User. Add text to the three paragraph elements in the Highlights Fragment. 

---

## Next Up

* [Using Master Page Templates and Style Books](./using-master-pages-style-books.md)

## Previous Step

* [Create Content Pages](./create-content-pages.md)