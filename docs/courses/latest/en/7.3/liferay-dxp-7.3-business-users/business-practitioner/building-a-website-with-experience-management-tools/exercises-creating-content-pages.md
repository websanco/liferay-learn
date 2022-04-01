## Creating Content Pages

<div class="ahead">

#### Exercise Goals
* Create the Livingstone Life Blog Site
* Create several Content Pages using Fragments and Widgets

</div>

#### Create the Livingstone Life Site
1. **Sign in** as your Administrative User.
	- If you have followed along with the other Site building modules, this should be Josiah Copeland.
2. **Open** the _Global Menu_.
3. **Go to** _`Control Panel  > Sites > Sites`_.
4. **Click** the _Add_ icon.
5. **Choose** the _Blank Site_ Site Template.
6. **Type** _`Livingstone Life`_ for _Name_.
7. **Click** _Save_.
8. **Click** _Open_ under _Membership Type_.
9. **Choose** _Restricted_ from the drop-down menu.
10. **Click** _Save_.

<br />

<img src="images/livingstone_life_site_created.png" style="max-width:100%;" />

#### Create a Home Page for the Livingstone Life Site
1. **Go to** _`Site Administration Panel  > Site Builder > Pages`_.
2. **Click** the  _Add_ icon (or the _New_ button).
3. **Choose** _Public Page_ from the drop-down menu.
4. **Choose** _Blank_ for the Page Template.
5. **Type** `Home` for _Name_.
6. **Click** _Add_.
7. **Click** the _Fragments and Widgets_ icon on the right sidebar.
8. **Drag and Drop** a _Banner Cover Center_ fragment onto the page from under the featured content section.
9. **Drag and Drop** a _Content Display_ fragment onto the page beneath the Banner.
10. **Drag and Drop** a _Content Ratings_ fragment onto the page beneath the Content Display.

<br />

<img src="images/livingstone_life_home.png" style="max-width:100%;" />

#### Configure the Banner Fragment 
1. **Click** the _Banner Cover Center_ fragment on the page. 
2. **Click** the _Image_ tab towards the bottom of the _Styles_ panel.
3. **Click** the _Select_ button.
4. **Click** the _Select File_ button.
5. **Choose** the `yoga.jpg` file from your exercise folder.
6. **Click** _Add_.
7. **Click** _01-title_ in the _Selection_ panel.  
8. **Double-click** the highlighted box. 
9. **Delete** the placeholder text. 
10. **Type** `Welcome to Livingstone Life`. 
11. **Click** _02-subtitle_ in the _Selection_ panel.  
12. **Double-click** the highlighted box. 
13. **Delete** the placeholder text. 
14. **Type** `Begin your journey towards a life worth living.`.  

<br />

<img src="images/home_banner.png" style="max-width:100%;" />

#### Create a Blog Post 
1. **Go to** _`Site Administration Panel  > Content & Data > Blogs`_.
2. **Click** the  _Add_ icon.
3. **Click** _Select File_.
4. **Click** _Select File_.
5. **Choose** `travel_blog.png` from your exercise folder.
6. **Click** _Add_.
7. **Type** `The Wild North` for _Title_.
8. **Type** `My six months in the Alaskan Wilderness` for _Subtitle_.
9. **Copy and paste** the text from the `wild-north.txt` file in your exercise folder for _Content_.
10. **Click** _Publish_.

<br />

<img src="images/blog_created.png" style="max-width:100%;" />

#### Configure the Content Display Fragment
1. **Go to** _`Site Administration Panel  > Site Builder > Pages`_.
2. **Click** the _Options_ icon next to the _Home_ page.  
3. **Choose** _Edit_ from the pop-up menu.  
4. **Click** the _Content Display_ fragment.
5. **Click** the _General Tab_ in the _Selection_ panel.
6. **Click** the _Add_ icon.
7. **Choose** _Select Content_ from the drop-down menu.
8. **Click** the _Blogs_ tab.
9. **Choose** _The Wild North_.  
10. **Click** _Publish_.

<br />

<img src="images/home_content_display.png" style="max-width:100%;" />

#### Create a Contact Us Page for the Livingstone Life Site
1. **Go to** _`Site Administration Panel  > Site Builder > Pages`_.
2. **Click** the _Add_ button.  
3. **Choose** _Public Page_ from the pop-up menu.  
4. **Choose** _Blank_ for the Page Template.
5. **Type** `Contact Us` for _Name_. 
6. **Click** the _Fragments and Widgets_ icon on the right sidebar.
7. **Drag and Drop** a _Banner Cover Center_ fragment onto the page.
8. **Drag and Drop** a _Grid_ Layout Element onto the page beneath the Banner
9. **Drag and Drop** a _Paragraph_ component onto the page in the leftmost section of the Grid.
10. **Click** the _Widgets_ tab of the _Fragments and Widgets_ panel.
11. **Drag and Drop** a _Form_ widget in the center section of the Grid.
12. **Click** the empty section of the Grid.
13. **Choose** _2_ for _Number of Modules_ in the _Selection_ panel to remove the extra section.  

<br />

<img src="images/contact_us_page_created.png" style="max-width:100%;" />

<div class="page"></div>

#### Configure the Banner Fragment 
1. **Click** the _Banner Cover Center_ fragment on the page. 
2. **Click** the _Image_ tab towards the bottom of the _Selection_ panel.
3. **Click** the _Select_ button.
4. **Click** the _Select File_ button.
5. **Choose** the `contact_us_banner.jpg` file from your exercise folder.
6. **Click** _Add_.
7. **Click** _01-title_ in the _Selection_ panel.  
8. **Double-click** the highlighted box. 
9. **Delete** the placeholder text. 
10. **Type** `Contact Us`. 
11. **Click** _03-link_ in the _Selection_ panel.  
12. **Double-click** the highlighted box. 
13. **Delete** the placeholder text. 
14. **Type** `Submit a Request.`.  

<br />

<img src="images/contact_us_banner_complete.png" style="max-width:100%;" />

<div class="page"></div>

#### Create a Contact Form for the Contact Us Page 
1. **Go to** _`Site Administration Panel  > Content & Data > Forms`_.
2. **Click** the  _Add_ icon.
3. **Type** `Contact Us` in the field that reads _Untitled Form_.
4. **Click** the _Add_ icon.
5. **Drag and Drop** a _Text_ field onto the drop zone.
6. **Type** `Name` for _Label_.
7. **Click** the slider for _Required Field_.
8. **Click** the _Back_ icon.
9. **Drag and Drop** a _Text_ field onto the drop zone.
10. **Type** `Email` for _Label_.
11. **Click** the slider for _Required Field_.
12. **Click** the _Back_ icon.
13. **Click** _Publish_.

<br />

<img src="images/form_created.png" style="max-width:100%;" />

<div class="page"></div>

#### Configure the Grid Elements for the Contact Us Page
1. **Go to** _`Site Administration Panel  > Site Builder > Pages`_.
2. **Click** the _Options_ icon next to the _Contact Us_ page.  
3. **Choose** _Edit_ from the pop-up menu.  
4. **Click** the _Paragraph_ component.
5. **Copy and Paste** the text from the `contact-us.txt` file into the _Paragraph_ component.
6. **Click** the _Options_ icon on the _Blog_ widget.
7. **Choose** Configuration_ from the pop-up menu.
8. **Click** _Contact Us_.
9. **Click** _Save_.
10. **Close** the pop-up.  
11. **Click** _Publish_.

<br />

<img src="images/grid_configured.png" style="max-width:100%;" />

<div class="page"></div>

#### Create a Latest Page for the Livingstone Life Site
1. **Go to** _`Site Administration Panel  > Site Builder > Pages`_.
2. **Click** the _Add_ button.  
3. **Choose** _Public Page_ from the pop-up menu.  
4. **Choose** _Blank_ for the Page Template.
5. **Type** `Latest` for _Name_. 
6. **Click** the _Fragments and Widgets_ icon on the right sidebar.
7. **Click** the _Widgets_ tab of the _Fragments and Widgets_ panel.
8. **Drag and Drop** a _Container_ Layout Element onto the page.
9. **Drag and Drop** a _Asset Publisher_ widget in the Container.
10. **Click** the _Options_ icon for the _Asset Publisher_.
11. **Choose** _Configuration_ from the pop-up menu.
12. **Click** the _Source_ tab.
13. **Choose** _Blogs Entry_ for _Asset Type_.
14. **Click** _Save_.
15. **Close** the pop-up.
16. **Click** _Publish_.

<br />

<img src="images/latest_page_created.png" style="max-width:100%;" />

<br />

---

#### Bonus Exercises
1. Create a new blog entry about a must-see attraction in your area (e.g., theme park, restaurant, museum, etc.). Add your blog entry to the “Latest” page of the Livingstone Life Site.
2. Add a new Content Page to the Livingstone Life Site for featured blog articles. Be sure to add a banner for the page, as well as a method to display the blog entries.
