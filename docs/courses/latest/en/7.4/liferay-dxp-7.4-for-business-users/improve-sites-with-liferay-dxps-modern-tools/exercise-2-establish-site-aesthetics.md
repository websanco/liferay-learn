# Exercise 2: Establish Site Aesthetics 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/ja5uBzptelc

## Exercise Goals 

- Create a Master Page Template for the Mondego Community Site 
- Create a Style Book for the Mondego Community Site 

## Create a New Master Page Template 

1. **Sign In** to the Mondego Platform as an Administrator. 
	- This will be Elliot Quinn if you followed along with the module _Add Users and Manage Permissions with Liferay DXP_. 
2. **Open** the _Site Menu_. 
3. **Click** the _Site Selector_. 
4. **Click** the _My Sites_ tab. 
5. **Choose** the _Mondego Community_ Site. 
6. **Go to** `Design` &rarr; `Page Templates` in the _Site Administration Panel_. 
7. **Click** the _Add_ button on the _Masters_ tab. 
8. **Type** _Community_ as the _Name_. 
9. **Click** _Save_. 

## Add a Header and Footer to the Master Page Template 

1. **Open** the _Fragments and Widgets_ (+) menu. 
2. **Drag and Drop** an _Image_ fragment above the _Drop Zone_. 
3. **Type** `Header` into the _Search_ bar. 
4. **Drag and Drop** a _Header Light_ above the _Drop Zone_ and below the _Image_. 
5. **Type** `Footer` into the _Search_ bar. 
6. **Drag and Drop** a _Footer Nav Light_ below the _Drop Zone_. 
	- The Drop Zone is where Fragments and Widgets will appear for any Content Page that uses this Master Page Template. 

## Add Images to the Header and Footer 

1. **Select** the _Image_ fragment at the top of the page. 
2. **Double-click** the _Image_ fragment. 
3. **Click** _Select File_. 
4. **Choose** `mondego-community-header.jpg` from your unzipped exercise file folder. 
5. **Click** _Open_. 
6. **Click** _Add_. 
7. **Select** the _Header Light_ fragment in the Browser. 
8. **Click** the `01-logo` element. 
9. **Click** the _Add_ (+) button beside _Select Image_ under _Image Source &rarr; Image_. 
10. **Click** _Select File_. 
11. **Choose** `mondego-logo.jpg` from your unzipped exercise file folder onto the _Drag & Drop to Upload_ box. 
12. **Click** _Add_. 
13. **Click** the _Footer Nav Light_ fragment. 
14. **Click** the `01-logo` element. 
15. **Click** _Select Image_ under _Image Source &rarr; Image_. 
16. **Click** _Select File_. 
17. **Choose** the `mondego-logo.jpg` file from _Documents and Media_. 

## Update the Copyright in the Footer 

1. **Click** the _Footer Nav Light_ fragment. 
2. **Click** the `02-copy` element to highlight the element. 
3. **Double-click** the text box. 
4. **Delete** the placeholder text. 
5. **Type** `2022 Mondego Group` after the Copyright symbol. 

## Configure the Allowed Fragments for the Master Page Template 

1. **Click** the _Configure Allowed Fragments_ button in the center of the Drop Zone. 
2. **Click** the box beside _Footers_ to deselect. 
3. **Click** the box beside _Navigation Bars_ to deselect. 
4. **Click** _Save_. 
5. **Click** _Publish Master_ at the top right. 

## Make the Community Master Page Template the Default 

1. **Click** the _Options_ (three dots) icon beside _Community_ on the _Masters_ tab. 
2. **Choose** _Mark as Default_ from the menu. 
3. **Click** _OK_ when the pop-up asks if you want to replace _Blank_ as the default. 
	- You should now see a blue checkmark on the Community Master Page Template. 

## Apply the Community Master Page Template to Pages 

1. **Open** the _Site Menu_. 
2. **Click** _Home_ to go to the main _Welcome_ page. 
3. **Click** the _Edit_ (pencil) icon in the top corner. 
4. **Click** the _Page Design Options_ (paint roller) icon in the sidebar. 
5. **Choose** _Community_ instead of _Blank_. 
6. **Click** _Publish_. 
	- You should now see the Header and Footer we defined in our Master applied to the Welcome page we created in the last exercise. 

## Create a New Style Book for the Mondego Community Site 

1. **Open** the _Site Menu_. 
2. **Go to** `Design &rarr; Style Books`. 
3. **Click** the _Add_ button. 
4. **Type** `Community Main` as the _Name_. 
5. **Click** _Save_. 

## Change the Color System Colors 

1. **Go to** _Brand Colors_ under _Color System_. 
2. **Type** `#100455` as _Brand Color 1_. 
3. **Type** `#0DADBD` as _Brand Color 2_. 
4. **Go to** _Theme Colors_ under _Color System_. 
5. **Type** `#100455` as _Primary_. 
6. **Type** `#0DADBD` as _Secondary_. 

## Change the Button Colors 

1. **Click** _Color System_ to open the drop-down menu. 
2. **Choose** _Buttons_. 
3. **Go to** _Button Outline Primary_. 
4. **Type** `#100455` as _Border Color_. 
5. **Type** `#100455` as _Color_. 
6. **Type** `#B3E1E6` as _Hover Background Color_. 
7. **Type** `#100482` as _Hover Border Color_. 
8. **Type** `#100482` as _Hover Color_. 
9. **Click** _Publish_. 
10. **Click** _OK_ in the pop-up. 

## Make the Community Main Style Book the Default and View Changes 

1. **Click** the _Options_ (three dots) icon beside _Community Main_. 
2. **Choose** _Mark as Default_. 
3. **Open** the _Site Menu_. 
4. **Click** _Home_ at the top. 
5. **Click** the _Slider_ to view the second page. 
	- You should see that the color of the header has changed. 
6. **Hover** your mouse over the _Go Somewhere_ button under _Ask an Expert_. 
	- You should see the background color and text color of the button change. 

---

## Bonus Exercise 

1. Update the Header and Footer Master Page Template we created on the Mondego Group Site in the Build New Sites Module. Add the Mondego logo to the Header and Footer. Add one more fragment to either the Header or Footer. View the changes on Pages that use the template. 
2. Add a New Style Book to the Mondego Group Site. Adjust the Color System colors to match those on the Mondego Community Site. Adjust Spacing, Layout, or Typography settings. Apply the Style Book to only one page on the Site and view the changes. (You may need to add more content to pages to view some changes.) 

---

## Next Up

* [Creating Unique Digital Experiences](./create-unique-user-experiences.md)

## Previous Step

* [Using Master Page Templates and Style Books](./using-master-pages-style-books.md)