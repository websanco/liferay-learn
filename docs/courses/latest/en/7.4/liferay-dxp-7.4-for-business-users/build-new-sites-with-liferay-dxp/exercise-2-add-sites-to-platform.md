# Exercise 2: Add Sites to the Platform 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/tI0sAorMK_4

## Exercise Goals 

- Create a Site Template for Mondego Regional Office locations 
- Use the template to create Sites for some of Mondego's regional banks 

## Create a Site Template for Mondego Regional Offices 

1. **Open** the _Global Menu_. 
2. **Go to** `Control Panel` &rarr; `Sites` &rarr; `Site Templates` in the _Menu_. 
3. **Click** the _Add_ icon near the top right. 
4. **Type** `Regional Bank Office Site` as the _Name_. 
5. **Type** `A site template for Mondego's regional bank offices` as the _Description_. 
6. **Click** _Save_. 
7. **Click** the _Regional Bank Office Site_. 
	- This will open up our Site Template in a new tab. 
8. **Go to** `Site Builder` &rarr; `Pages` in the _Site Administration_ panel. 
9. **Click** the _Options_ icon next to the default _Home_ Page. 
10. **Choose** _Delete_. 
11. **Click** _OK_ in the pop-up asking if you are sure you want to delete this. 
12. **Click** the _New_ button in the center of the page. 
13. **Choose** _Private Page_. 
14. **Click** the _Global Templates_ tab. 
15. **Choose** _Mondego Landing Page_. 
16. **Type** `Home` as the _Name_. 
17. **Click** _Add_. 
	- When the configuration page loads, make sure the _Inherit Changes_ slider is set to _YES_. 
18. **Click** the _Save_ button at the bottom of the page. 
19. **Click** the _Back_ (<) icon. 

## Add a Personal Banking Page to the Site Template 

1. **Click** the _Add_ icon near the top right. 
2. **Choose** _Add Child Page of Home_ in the drop-down. 
3. **Choose** _Add Site Template Page_. 
4. **Choose** _Blank_ from the _Basic Templates_ to add a new Content Page. 
5. **Type** `Personal` as the _Name_. 
6. **Click** _Add_. 
	- For now, we will leave the Content Page blank. 
7. **Click** the _Publish_ button at the top right. 

## Add Child Pages for Checking, Savings, and Mobile Banking 

1. **Click** the _Add_ icon to the right of the _Personal_ Page we just created. 
2. **Choose** _Add Page_. 
3. **Choose** the _Blank_ Content Page. 
4. **Type** `Checking` as the _Name_. 
5. **Click** _Add_. 
6. **Click** the _Publish_ button at the top right. 
7. **Click** the _Add_ icon to the right of the _Personal_ Page. 
8. **Choose** _Add Page_. 
9. **Choose** the _Blank_ Content Page. 
10. **Type** `Savings` as the _Name_. 
11. **Click** _Add_. 
12. **Click** the _Publish_ button at the top right. 
13. **Click** the _Add_ icon to the right of the _Personal_ Page. 
14. **Choose** _Add Page_. 
15. **Choose** the _Blank_ Content Page. 
16. **Type** `Online and Mobile Banking` as the _Name_. 
17. **Click** _Add_. 
18. **Click** the _Publish_ button at the top right. 
	- To view the newly created Child Pages, click the Arrow icon to the right of the Personal Page. 

## Finish Creating the Page Structure for the Site Template 

1. **Click** the _Add_ icon at the top right. 
2. **Choose** _Add Site Template Page_. 
3. **Choose** _Blank_ Content Page. 
4. **Type** `Business` as the _Name_. 
5. **Click** _Add_. 
6. **Click** the _Publish_ button at the top right of the page. 
7. **Click** the _Add_ icon at the top right. 
8. **Choose** _Add Site Template Page_. 
9. **Choose** _Blank_ Content Page. 
10. **Type** `The Mondego Story` as the _Name_. 
11. **Click** _Add_. 
12. **Click** the _Publish_ button at the top right of the page. 
13. **Click** the _Add_ icon at the top right. 
14. **Choose** _Add Site Template Page_. 
15. **Choose** _Widget Page_. 
16. **Type** `Contact Us` as the _Name_. 
17. **Click** _Add_. 
18. **Click** the _Save_ button at the bottom of the page. 
19. **Click** the _Back_ (<) icon. 

## Create a New Site Using the New Site Template 

1. **Go to** `Control Panel` &rarr; `Sites` &rarr; `Sites` in the _Global Menu_. 
2. **Click** the _Options_ icon next to _Mondego Group_. 
3. **Choose** _Add Child Site_. 
4. **Choose** _Regional Bank Office Site_. 
5. **Type** `Mondego North America` as the _Name_. 
	- Leave the checkbox unchecked. 
6. **Click** the _Save_ button at the bottom. 
7. **Click** on `Site Builder` &rarr; `Pages` in the _Site Administration_ panel for the _Mondego North America_ Site. 
	* You'll see that the Pages we created in the template have been generated for this Site. 

## Create Two More Regional Bank Office Sites with the Site Template 

1. **Go to** `Control Panel` &rarr; `Sites` &rarr; `Sites` in the _Global Menu_. 
2. **Click** the _Options_ icon next to _Mondego Group_. 
3. **Choose** _Add Child Site_. 
4. **Click** _Regional Bank Office Site_ for the Site template. 
5. **Type** `Mondego UK` as the _Name_. 
6. **Click** the _Save_ button. 
7. **Go to** `Control Panel` &rarr; `Sites` &rarr; `Sites` in the _Global Menu_. 
8. **Click** the _Options_ icon next to _Mondego Group_. 
9. **Choose** _Add Child Site_. 
10. **Click** _Regional Bank Office Site_ for the Site template. 
11. **Type** `Mondego Japan` as the _Name_. 
12. **Click** the _Save_ button. 
13. **Go to** `Control Panel` &rarr; `Sites` &rarr; `Sites` in the _Global Menu_. 
14. **Click** the _Mondego Group_ Site to see the Child Sites we created above. 

---

## Bonus Exercises 

1. Create a Site Template for Mondego's Business Banking sites. The template should have at least three Content Pages that include at least one fragment or widget on each. 
2. Add Mondego Regional Office Sites for Mondego locations in Chile and Germany. Make sure they are created as Child Sites of the main Mondego Site. 
3. Add a custom Content Page to one of the new Mondego Regional Office Sites created from the Site Template. Navigate to the new Site and look at the Pages you added within the Site itself. 

---

## Next Up

* [Managing Site Configuration](./managing-site-configuration.md)
