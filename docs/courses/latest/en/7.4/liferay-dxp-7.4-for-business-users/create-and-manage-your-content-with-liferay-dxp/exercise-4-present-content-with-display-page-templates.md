# Exercise 4: Present Content with Display Page Templates 

Coming Soon!

<!--
[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/ja5uBzptelc

## Exercise Goals 

- Create Display Page Templates for: 
	- Web Content Articles 
	- Categories 
- Preview the Display Page Layouts Using Published Content 

## Create a Display Page Template for Product Highlights 
1. **Sign In** to your Liferay platform as an Administrator if you are not already. 
2. **Open** the _Global Menu_. 
3. **Select** _Marvin Robotics Store_ on the _Sites_ tab. 
4. **Go to** `Design` &rarr; `Page Templates` in the _Site Menu_. 
5. **Click** the _Display Page Templates_ tab. 
6. **Click** the _Add_ button at the top right. 
7. **Type** `Product Highlights` for the _Name_. 
8. **Choose** _Web Content Article_ from the _Content Type_ drop down. 
9. **Choose** _Basic Web Content_ from the _Subtype_ drop down. 
10. **Click** _Save_. 

## Add Fragments to the Product Highlights Display Page Template 
1. **Open** the _Fragments and Widgets_ menu (the plus sign) in the sidebar at the right. 
2. **Go to** `Fragments` &rarr; `Basic Components`. 
3. **Drop** a _Heading_ fragment onto the page. 
4. **Go to** `Fragments` &rarr; `Layout`. 
5. **Drop** a _Grid_ fragment onto the page below the _Heading_. 
6. **Click** the _Grid_ element. 
7. **Go to** _Number of Modules_ under `Browser` &rarr;`General`. 
8. **Select** _2_. 
9. **Open** the _Fragments and Widgets_ menu. 
10. **Go to** `Fragments` &rarr; `Basic Components`. 
11. **Drop** an _Image_ fragment into the left column of the _Grid_. 
12. **Drop** a _Paragraph_ fragment into the right column of the _Grid_. 

## Map Basic Web Content Articles to the Fragments 
1. **Open** the _Browser_ (arrow icon). 
2. **Select** the _Title_ element under the _Heading_ fragment. 
3. **Go to** _Field_ under the _Mapping_ tab. 
4. **Choose** _Title_ under _Basic Information_ from the drop-down menu. 
5. **Click** the _Image_ fragment. 
6. **Select** the _image-square_ element in the _Browser_. 
7. **Go to** the _Image Source_ tab. 
8. **Choose** _Mapping_ from the drop-down menu under _Source Selection_. 
9. **Go to** _Field_. 
10. **Choose** _Small Image_ under _Featured Image_ in the drop-down menu. 
11. **Click** the _Paragraph_ fragment. 
12. **Select** the _element-text_ element in the _Browser_. 
13. **Go to** _Field_ under the _Mapping_ tab. 
14. **Choose** _Content_ under _Content (Basic Web Content)_ from the drop-down menu. 
15. **Click** _Publish_ at the top right. 

## Set Product Highlights as the Default Template 
1. **Open** the _Options_ menu (three dots) beside _Product Highlights_. 
2. **Click** _Mark as Default_. 

## Create a Web Content Article 
1. **Open** the _Site Menu_. 
2. **Go to** _Content & Data &rarr; Web Content_. 
3. **Click** the _Add_ button. 
4. **Choose** _Basic Web Content_. 
5. **Type** `TS2-100 Robotic Machine` for the _Title_. 
6. **Copy & Paste** the text from `TS2-100 Product Highlights.txt` into the _Content_ box. 
	- You should find the _TS2-100 Product Highlights.txt_ file in your unzipped module exercise files. 
7. **Go to** _Featured Image_ in the _Properties_ sidebar on the right. 
8. **Choose** _From URL_ from the drop-down menu. 
9. **Open** the _Global Menu_. 
10. **Right Click** _Asset Libraries_ under `Applications &rarr; Content` and _Open Link in New Tab_. 
11. **Go to** `Product Assets &rarr; Documents and Media &rarr; Product Images`. 
12. **Click** on the _TS2-100 Robotic Machine_ image. 
13. **Click** the _Info_ icon. 
14. **Copy** the _Latest Version URL_. 
15. **Go to** the _New Web Content Article_ tab in your browser. 
16. **Paste** the URL link into the box below _Featured Image_. 
17. **Click** _Publish_. 

## Preview the Web Content Article 
1. **Open** the _Options_ menu beside the _TS2-100 Robotic Machine_ Web Content. 
2. **Click** _Preview_. 
	- You should see that the Web Content Article we just created now has the format we established in the template: the heading at the top, an image on the left, and the text on the right. 
	- Note: You can also preview different types of content on your Display Page Template while you are editing the Display Page Template. This is done using the _Preview With_ option at the top right and selecting the Item you want to preview. 

## Create a Display Page Template for Categories 
1. **Open** the _Site Menu_. 
2. **Go to** _Design &rarr; Page Templates_. 
3. **Click** the _Display Page Templates_ tab. 
4. **Click** the _Add_ button at the top right. 
5. **Type** `Product Bundles` for the _Name_. 
6. **Choose** _Categories_ from the _Content Type_ drop down. 
7. **Click** _Save_. 

## Add Fragments to the Product Bundles Display Page Template 
1. **Open** the _Fragments and Widgets_ menu (the plus sign) in the sidebar at the right. 
2. **Go to** `Fragments` &rarr; `Basic Components`. 
3. **Drop** a _Heading_ fragment onto the page. 
4. **Drop** an _Image_ fragment below the _Heading_. 
5. **Go to** `Fragments` &rarr; `Layout`. 
6. **Drop** a _Grid_ fragment onto the page below the _Heading_. 
7. **Click** the _Grid_ element. 
8. **Go to** _Number of Modules_ under `Browser` &rarr; `General`. 
9. **Select** _2_. 
10. **Drag** the divider to resize the modules and make the left side smaller. 
11. **Open** the _Fragments and Widgets_ menu. 
12. **Go to** `Fragments` &rarr; `Basic Components`. 
13. **Drop** a _Button_ fragment into the left column of the _Grid_. 
14. **Drop** a _Paragraph_ fragment into the right column of the _Grid_. 

## Map Category Basic Information to the Fragments 
1. **Open** the _Browser_ (arrow icon). 
2. **Select** the _Title_ element under the _Heading_ fragment. 
3. **Go to** _Field_ under the _Mapping_ tab. 
4. **Choose** _Vocabulary_ under _Basic Information_ in the drop-down menu. 
5. **Click** the _Image_ fragment. 
6. **Select** the _image-square_ element in the _Browser_. 
7. **Go to** the _Image Source_ tab. 
8. **Choose** _Mapping_ from the drop-down menu under _Source Selection_. 
9. **Go to** _Field_. 
10. **Choose** _Main Image_ in the drop-down menu. 
11. **Click** the _Button_ fragment. 
12. **Click** the _link_ element. 
13. **Go to** _Field_ under the _Mapping_ tab. 
14. **Choose** _Name_ from the drop-down menu. 
15. **Click** the _Paragraph_ fragment. 
16. **Select** the _element-text_ element in the _Browser_. 
17. **Go to** _Field_ under the _Mapping_ tab. 
18. **Choose** _Description_ from the drop-down menu. 
19. **Click** _Publish_ at the top right. 

## Set Product Bundles as the Default Template 
1. **Open** the _Options_ menu (three dots) beside _Product Bundles. 
2. **Click** _Mark as Default_. 

## Create a Product Bundles Vocabulary 
1. **Open** the _Site Menu_. 
2. **Go to** `Categorization` &rarr; `Categories`. 
3. **Click** the _Add_ button beside _Vocabularies_. 
4. **Type** `Product Bundles` for the _Name_. 
5. **Click** _Save_. 
6. **Click** _OK_ on the pop-up notification. 

## Add a Multi-Product Bundles Category 
1. **Click** the _Add_ button at the right to create a new Category. 
2. **Type** `Multi-Product Bundles` for the _Name_. 
3. **Copy & Paste** the text from the `Multi-Product Bundles Description.txt` file into the _Description_. 
	- You should have this file unzipped in your module exercise folder. 
4. **Click** _Save_. 
5. **Open** the _Options_ menu (three dots) to the right of the _Multi-Product Bundles_ category. 
6. **Choose** _Edit_. 
7. **Click** on the _Images_ tab. 
8. **Click** the _Add_ button in the top right. 
9. **Drag & Drop** the _Multi-Product Bundles Title Card.jpeg_ from your exercise files folder. 
10. **Click** _Publish_. 

## Preview the Product Bundles Category Display Page 
1. **Click** _Product Bundles_ to view the _Categories_. 
2. **Click** the _Options_ menu beside _Multi-Product Bundles_. 
3. **Choose** _View Display Page_. 

---

## Bonus Exercise 
1. Create another Display Page Template. Choose any Content Type you want. Add Fragments to the page and map content elements to the Fragments. Preview the Display Page Template using the Preview feature in the top right corner. 
-->