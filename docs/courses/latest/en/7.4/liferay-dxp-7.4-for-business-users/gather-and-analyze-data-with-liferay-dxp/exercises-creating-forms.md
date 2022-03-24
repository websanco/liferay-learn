# Exercise 1: Creating Forms 

Coming Soon!

<!--

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/3py4H9VZve4

## Exercise Goals 
* Create an Element Set 
* Create two New Forms: 
	- Customer Satisfaction Survey 
	- New Product Interest 

## Create a New Element Set 
1. **Sign In** to your platform as an Administrator. 
2. **Open** the _Site Menu_. 
3. **Click** the _Site Selector_ (compass icon) in the _Site Administration_ panel. 
4. **Click** the _My Sites_ tab. 
5. **Click** the _Marvin Robotics_ Site. 
6. **Go to** `Content & Data`  &rarr; `Forms` in the _Site Administration_ panel. 
7. **Click** the _Element Sets_ tab. 
8. **Click** the _Add_ icon near the top right of the page. 
9. **Click** the _Untitled Form_ field to edit the title. 
10. **Type** `Webinar RSVPs` as the _Title_. 
11. **Type** `RSVP for the upcoming Marvin Robotics Webinar.` as the description. 

## Add Text Fields to the Element Set 
1. **Open** the _Builder_ sidebar if it is not already open. 
2. **Drag** a _Text_ field onto the page. 
3. **Type** `First Name` under _Label_ in the sidebar. 
4. **Click** the _Required Field_ toggle. 
5. **Click** the _Back_ (<) arrow to return to the _Builder_ menu. 
6. **Drag** another _Text_ field onto the page below _First Name_. 
7. **Type** `Last Name` under _Label_. 
8. **Click** the _Required Field_ toggle. 
9. **Click** _Back_. 
10. **Drag** a _Text_ field onto the page below _Last Name_. 
11. **Type** `Department` as the _Label_. 
12. **Click** _Back_. 

## Add an Email Field with Confirmation and Validation Enabled 
1. **Drag** a _Text_ field onto the page below the _Department_ field. 
2. **Type** `Email` under _Label_. 
3. **Click** the _Required Field_ toggle. 
4. **Click** the _Advanced_ tab and scroll down to view available options. 
5. **Click** the _Require Confirmation_ toggle. 
6. **Type** `The emails do not match` as the _Error Message_. 
7. **Click** the _Validation_ toggle. 
8. **Choose** _Is an email_ from the drop-down menu under _Accept if Input_. 
9. **Type** `Please enter a valid email address` as the _Error Message_. 
10. **Click** _Save_ at the bottom of the page. 
11. **Click** the _Back_ (<) arrow at the top next to _New Element Set_. 

## Create a Form Using the Element Set 
1. **Click** the _Forms_ tab next to _Element Sets_. 
2. **Click** the _Add_ button or _New Form_ button. 
3. **Type** `New Webinar` as the _Title_. 
4. **Click** the _Element Sets_ tab in the _Builder_. 
5. **Drag** the _Webinar RSVPs_ Element Set onto the Form page. 
6. **Click** the _Preview_ button at the top. 
	- This should open the Form in a new tab. 
7. **Click** into the fields to see the required fields, validation, and confirmation we established.  
	- You can also fill out the fields to test the error messages. 
8. **Close** the tab. 
9. **Click** _Save_ to save the Form as a draft. 
	- We will complete and publish forms in the next part of the exercise. 

## Create the Customer Satisfaction Form 
1. **Open** the _Site Menu_. 
2. **Click** the _Site Selector_. 
3. **Click** the _My Sites_ tab. 
4. **Click** the _Marvin Robotics Store_ Site. 
5. **Go to** `Content & Data  &rarr; Forms` in the _Site Administration_ panel. 
6. **Click** the _Add_ or _New Form_ button to add a new Form. 
7. **Click** the _Untitled Form_ field to edit the Form title. 
8. **Type** `Customer Satisfaction Survey` as the title. 
9. **Type** `The official customer satisfaction survey for Marvin Robotics customers.` as the description. 
10. **Type** `Product Satisfaction` as the _Page title_. 
11. **Type** `How satisfied are you with Marvin Robotics Products?` as the page description. 

## Add Single Selection Fields to the Form 
1. **Click** the _Builder_ icon near the top right if the _Builder_ does not automatically open. 
2. **Drag** a _Grid_ field onto the Form. 
3. **Type** `How satisfied are you with:` under _Label_. 
4. **Type** `The delivery of Marvin Robotics Products` as the first row option. 
5. **Type** `The quality of Marvin Robotics Products` as the second row option. 
6. **Type** `Very satisfied` for the first column option. 
7. **Type** `Somewhat satisfied` for the second column option. 
8. **Type** `Neutral` for the third column option. 
9. **Type** `Somewhat dissatisfied` for the fourth column option. 
10. **Type** `Very dissatisfied` as the fifth column option. 
11. **Click** the _Required Field_ toggle. 
12. **Click** the _Back_ (<) arrow beside _Grid_. 

## Add Multiple Selection and Text Fields to the Form 
1. **Drag** a _Multiple Selection_ field onto the Form. 
2. **Type** `Which Marvin Robotics Product would you purchase again?` under _Label_. 
3. **Type** `TS2-100 Robotic Machine, TS2-40 Robotic Machine, CS9 Controller, FastPickerTP80` as the four options. 
4. **Click** _Back_. 
5. **Drag** a _Text_ field onto the Form. 
6. **Type** `What product or service do you like best?` as the _Label_. 
7. **Select** _Multiple Lines_. 
8. **Click** _Back_. 
9. **Drag** another _Text_ field onto the Form. 
10. **Type** `What product or service could be improved?` as the _Label_. 
11. **Select** _Multiple Lines_. 
12. **Click** _Back_. 

## Add a Second Page for Customer Service Satisfaction Questions 
1. **Click** the _New Page_ button. 
2. **Type** `Customer Service Satisfaction` as the _Page title_. 
3. **Type** `How is our Customer Service team doing?` as the page description. 
4. **Drag** a _Single Selection_ field onto the Form. 
5. **Type** `How do you rate our customer communication?` as the _Label_. 
6. **Type** `Excellent` as the first option. 
7. **Type** `Good` as the second option. 
8. **Type** `Neutral` as the third option. 
9. **Type** `Needs Improvement` as the fourth option. 
10. **Click** the _Required Field_ toggle. 
11. **Click** _Back_. 
12. **Drag** a _Text_ field onto the Form. 
13. **Type** `How can we better serve you?` as the _Label_. 
14. **Select** _Multiple Lines_. 

## Format the Success Page 
1. **Click** on _Thank you._ under _Success Page_. 
2. **Type** `Success!` to replace _Thank you._ 
3. **Type** `Your feedback is valuable to us. Thank you for filling out this survey.` to replace _Your information was successfully received. Thank you for filling out the form._ 
4. **Click** the _Publish_ button. 
5. **Click** the _Open Form_ button on the success message to view the complete form. 
	- You can also click the _Share_ button at the top next to _Preview_ to access the link to the survey. 

## Create the New Product Interest Form 
1. **Click** the _Back_ (<) arrow beside _Edit Form_. 
	- If you still have the Customer Satisfaction Survey opened in a new tab, close the tab and then click the _Back_ arrow. 
2. **Click** the _Add_ icon near the top right to create a new Form. 
3. **Click** _Untitled Form_ field to edit the Form title. 
4. **Type** `New Product Interest` in the _Title_ section. 
5. **Type** `Help Marvin Robotics move forward.` as the description. 

## Add Text Fields to the New Product Interest Form 
1. **Open** the _Builder_ icon near the top right if it does not automatically open. 
2. **Drag** a _Text_ field onto the Form. 
3. **Type** `How have Marvin Robotics products met your manufacturing needs?` as the _Label_. 
4. **Select** _Multiple Lines_. 
5. **Click** the _Required Field_ toggle. 
6. **Click** the _Back_ arrow by _Text_. 
7. **Drag** another _Text_ field onto the Form. 
8. **Type** `What manufacturing needs are not currently met by Marvin Robotics products?` as the _Label_. 
9. **Select** _Multiple Lines_. 
10. **Click** the _Required Field_ toggle. 
11. **Click** _Back_. 

## Add Multiple Selection and Text Fields to the New Product Interest Form 
1. **Drag** a _Multiple Selection_ field onto the Form. 
2. **Type** `What improvements would you like to see in future Marvin Robotics products?` as the _Label_. 
3. **Type** `Increased Load, Increased Range of Motion, Faster Movement Speed, Smaller Footprint, Lower Cost, Other` as the six options. 
4. **Click** the _Required Field_ toggle. 
5. **Click** _Back_. 
6. **Drag** a _Text_ field onto the Form. 
7. **Type** `List any additional improvements, recommendations, or comments for Marvin Robotics Products.` as the _Label_. 
8. **Select** _Multiple Lines_. 
9. **Click** _Publish_. 
10. **Click** the _Open Form_ button to view the newly created form. 

---

## Bonus Exercises 
1. Create a new Form on the main Marvin Robotics Site for gathering feedback from employees. The Form should be at least two pages long with no more than five fields on a page. 
2. Create an Element Set for commonly asked questions about customer experience and satisfaction. Use the Element Set to create a Returning Customer Satisfaction Form. 

---

## Next Up

* [Exercise 2b: Create User Groups](./exercises-create-user-groups.md)
-->