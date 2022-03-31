# Exercise 2: Enable CAPTCHA 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/wZIJUwKNVWw

## Exercise Goals 

- Enable CAPTCHA for Message Boards 
- Enable CAPTCHA for Forms 

## Enable CAPTCHA for Message Boards 

1. **Sign In** as your platform Administrator. 
2. **Open** the _Global Menu_. 
3. **Go to** `Control Panel` &rarr; `Configuration`. 
4. **Click** _System Settings_. 
5. **Click** _Security Tools_ in the _Security_ section. 
6. **Click** the checkbox beside _Message Boards Edit Category CAPTCHA Enabled_. 
7. **Click** the checkbox beside _Message Boards Edit Message CAPTCHA Enabled_. 
	- Note, that the number of Maximum Challenges is set to 1. This means that for a given portlet, a CAPTCHA is only required once. 
8. **Click** _Save_ at the bottom of the page. 

## Add a New Message Board Category to the Mondego Publications Site 

1. **Open** the _Personal Menu_. 
2. **Click** _My Sites_. 
3. **Click** the _My Sites_ tab. 
4. **Choose** _Mondego Publications_. 
	- Or whichever site you created that has the Mondego Community page. 
5. **Open** the _Site Menu_. 
6. **Go to** `Site Builder` &rarr; `Pages`. 
7. **Click** the _Community_ page. 
	- You should see the Message Board we added in the Add Users and Manage Permissions module.
8. **Click** the _Add Category_ button on the _Message Board_ widget. 
9. **Type** `Meet the Experts` as the _Name_. 
10. **Type** `Introductions to the Mondego Experts` as the _Description_. 
11. **Click** _Save_ at the bottom. 
	- The Mailing List section should pop open to reveal that the CAPTCHA is a required field. 
12. **Enter** the _Text Verification_. 
13. **Click** _Save_ again. 

## Add A New Thread to the Category 

1. **Click** _Meet the Experts_. 
2. **Click** _New Thread_ to add a new thread to the Category. 
3. **Type** `New Members` as the _Subject_. 
4. **Click** _Publish_. 
	- Note, that there is not a second CAPTCHA challenge because the maximum number of challenges for the portlet (1) has been reached. 

## Add a CAPTCHA to a Form 

1. **Open** the _Site Menu_. 
2. **Go to** `Content & Data &rarr; Forms`. 
3. **Click** the _Add_ button. 
4. **Type** `Suggestions` for the title. 
5. **Drag and Drop** a _Text_ field onto the form. 
6. **Type** `What would you like to see on the Publications Site?` for the _Label_. 
7. **Click** the _Required Field_ toggle. 
8. **Click** the _Settings_ icon in the top beside _Share_. 
9. **Click** the _Require CAPTCHA_ toggle. 
10. **Click** _Done_. 
11. **Click** the _Publish_ button. 
12. **Click** _Open Form_ in the pop-up success message. 
	- If you do not get an _Open Form_ link, click _Share_ in the top right corner, copy the link, and open it in a new tab to complete the form. 
13. **Go to** the new tab that opens and fill out the form and CAPTCHA. 

---

## Bonus Exercise 

1. Go back to the CAPTCHA Security Settings in the Control Panel. Increase the number of Maximum Challenges. Return to the Message Board and add Categories and Threads to see the changes. 

---

## Next Up

* [Securing Web Services](./securing-web-services.md)