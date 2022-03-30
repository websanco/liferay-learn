# Exercise 1: Add Pages to the Platform with Page Templates 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/U2SjonCjw2Q

## Exercise Goals 
	
- Create the Mondego Group Site 
- Create a Master Page Template to use across the main Mondego Site 
- Create a Content Page Template for Informational Pages 
- Use the Account Content Page Template to create a page for Personal Banking Accounts  
- Create a Widget Page Template for a Landing Page 
- Use the Template to create a Landing Page for Mondego's Sites 

## Create the Mondego Group Main Site 

1. **Sign in** to Liferay as an Administrator. 
2. **Open** the _Global Menu_ at the top right. 
3. **Click** the _Control Panel_ tab. 
4. **Choose** _Sites_ under `Sites`. 
5. **Click** the _Add_ button at the top right. 
6. **Select** _Blank Site_ as the Site Template. 
7. **Type** `Mondego Group` as the _Name_. 
8. **Click** the _Save_ button. 
	- This will take you to the _Site Settings_ page. 

## Create a Master Page Template 

1. **Open** the _Site Menu_ at the top left. 
2. **Go to** `Design` &rarr; `Page Templates` in the _Site Administration_ panel. 
3. **Click** the _Add_ button at the top right. 
4. **Type** `Header and Footer` as the _Name_. 
5. **Click** the _Save_ button. 
6. **Go to** the _Fragments and Widgets_ section. 
	- Use the Plus sign on the right side to open _Fragments and Widgets_ if it does not automatically open. 
7. **Go to** `Fragments and Widgets` &rarr; `Navigation Bars`. 
8. **Drop** a _Header Dark_ at the top of the page above the _Drop Zone_. 
9. **Go to** `Fragments and Widgets` &rarr; `Footers`. 
10. **Drop** a _Footer Nav Dark_ at the bottom of the page below the _Drop Zone_. 
11. **Click** the _Footer Nav Dark_ to open the _Browser_. 
	- Alternatively, open the _Browser_ by clicking the arrow icon below the plus icon. 
12. **Click** the _02-copy_ element to find the textbox on the page. 
13. **Double Click** the text box to edit. 
14. **Type** `2021 Mondego Group` as the new copyright. 
15. **Click** the _Publish Master_ button at the top right of the page. 

## Create a Content Page Template Using the New Master 

1. **Click** the _Page Templates_ tab at the top beside _Masters_. 
2. **Click** the _New_ button to create a new Collection. 
3. **Type** `Informational Pages` as the _Name_. 
4. **Click** _Save_. 
5. **Click** the _Add_ button at the right to add a Page Template to the Collection. 
6. **Choose** _Content Page Template_. 
7. **Select** _Header and Footer_ as the Master Page. 
8. **Type** `Accounts` as the _Name_. 
9. **Click** the _Save_ button. 

## Add Fragments to the Page Template 

1. **Click** the _Fragments and Widgets_ icon (plus sign) on the right side to open the menu. 
2. **Go to** `Fragments` &rarr; `Featured Content`. 
3. **Drop** a _Banner Center_ onto the page. 
4. **Go to** `Fragments` &rarr; `Content Display`. 
5. **Drop** a _Content Display_ onto the page below the _Banner Center_. 
6. **Go to** `Widgets` &rarr; `Collaboration`. 
7. **Drop** a _Form_ widget onto the page below the _Content Display_. 
8. **Click** the _Publish_ button at the top right of the page. 

## Create a Page with the Accounts Page Template 

1. **Open** the _Menu_. 
2. **Go to** `Site Builder` &rarr; `Pages` in the _Site Administration_ panel. 
3. **Click** the _Add_ button at the top right. 
4. **Choose** _Public Page_. 
	- You should see three available Collections: Informational Pages (which we just created), Basic Templates, and Global Templates. 
5. **Select** the _Accounts_ Content Page Template. 
6. **Type** `Personal Banking` as the _Name_. 
7. **Click** the _Add_ button. 
	- You should see the Fragments we added to the Account Page Template as well as the Header and Footer established in the Master Page Template. 

## Create a Global Landing Page Template 

1. **Open** the _Site Menu_. 
2. **Click** the _Site Selector_ icon beside the current Site (Mondego Group). 
	- The _Site Selector_ allows you to choose which Site you want to access in your Liferay platform. Once selected, you will be able to access and edit the Site with the _Site Administration_ panel. 
3. **Click** the _My Sites_ tab. 
4. **Choose** the _Global_ Site. 
5. **Go to** `Design` &rarr; `Page Templates` in the _Site Administration_ panel. 
	- You'll see the default global Page Templates here already: Search, Wiki, and Blog. 
6. **Click** the _Add_ icon in the top right corner. 
7. **Type** `Mondego Landing Page` as the _Name_. 
8. **Type** `A landing page for the various Mondego sites.` as the _Description_. 
9. **Click** the _Save_ button. 

## Configure the Widget Page Template 

1. **Click** on the new _Mondego Landing Page_ template. 
	- This will take you to the new template page. 
2. **Click** the _Configure Page_ (gear) icon at the top right. 
3. **Choose** _2-2 Columns_ from the grid of options under the _General_ tab. 
4. **Click** _Save_. 
5. **Click** the _Back_ (<) icon. 

## Add Language and Content Widgets to the Template 

1. **Click** the _Add_ icon in the top right corner of the page. 
2. **Open** the `Widgets` &rarr; `Tools` section. 
3. **Drop** a _Sign In_ widget into the first column. 
4. **Add** a _Language Selector_ from the `Widgets` &rarr; `Tools` section to the right column. 
5. **Go to** the `Widgets` &rarr; `Highlighted` section. 
6. **Add** a _Web Content Display_ to the first column below the _Sign In_. 
7. **Add** an _Asset Publisher_ from the `Widgets` &rarr; `Highlighted` section and place it in the right column beside the _Web Content Display_. 
8. **Click** the _Back_ (<) icon. 
	- If your browser opened a new tab when you clicked on the _Mondego Landing Page_ template, close the tab to get back to the _Pages_ section of the _Global_ Site. 

## Create a Page with the Landing Page Template 

1. **Open** the _Menu_. 
2. **Click** on the _Site Selector_ in the _Site Administration_ panel. 
3. **Choose** the _Mondego Group_ Site. 
4. **Go to** `Site Builder` &rarr; `Pages` in the _Site Administration_ panel. 
5. **Click** the _Add_ button next to _Public Pages_. 
6. **Choose** _Add Page_. 
7. **Go to** the _Global Templates_ tab. 
	- You should see the three default Page Templates plus the one we added above. 
8. **Choose** the _Mondego Landing Page_. 
9. **Type** `Home` for the _Name_. 
10. **Click** _Add_. 
	- When the configuration page loads, note that the _Inherit Changes_ slider is set to _YES_. We want to keep this for now so that any changes we make to this Page Template in the future change this Page, too. 
11. **Click** the _Save_ button. 
12. **Click** _Home_ in the _Site Administration_ panel. 
13. **Click** _Home_ in the navigation menu. 

## Update the Master Page Template for the Landing Page 

1. **Click** the _Configure Page_ (gear) icon. 
2. **Click** the _Look and Feel_ tab. 
3. **Select** _Change Master_ under the _Master_ section. 
4. **Choose** _Header and Footer_ as the new Master. 
5. **Click** the _Done_ button. 
6. **Click** the _Save_ button. 
7. **Click** the _Back_ (<) icon. 
	- The _Home_ page should now have the same header and footer as the _Personal Banking_ page. 

--- 

## Bonus Exercises: 

1. Add another Content Page Template called Credit Cards to the Mondego Group Site. Pick two Fragments and one Widget to use on the page. Create at least one new page on the Mondego Group Site using the new template. View the new page in the Navigation Menu. 
2. Create another Widget Page Template on the Global Site to use for Financial Advice. Use a Web Content Display widget, a Blogs widget, and a Message Boards widget. Choose whichever layout you prefer. 

---

## Next Up

* [Creating Sites with Site Templates](./create-sites-with-site-templates.md)