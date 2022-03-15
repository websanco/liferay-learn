# Improve Sites with Liferay DXP's Modern Tools

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/k20MnrwzL5Y
  
## Learning Objectives

* Learn how to use Content Pages to create dynamic and engaging web pages
* Understand how Master Page Templates and Style Books can be used to define common page elements and provide uniform styling
* Learn how to use Users and Experiences to provide unique, tailored Site experiences for different Users
	
## Tasks to Accomplish 

* Create the Mondego Community Site
* Create a Content Page for the Mondego Community Site
* Create a Master Page Template for the Mondego Community Site
* Create a Style Book for the Mondego Community Site
* Create the Mondego Employees Experience on the Mondego Community Site
* Add a Custom Navigation Bar to the Welcome Page

## Exercise Prerequisites

* Liferay DXP 7.4 set up and running
    - If you have not started your instance yet, first, make sure you have downloaded Docker, then use the following commands to get and start the Liferay Docker Image: 
        * `docker pull liferay/[product]:[version]`
        * `docker run -it -m 8g -p 8080:8080 liferay/[product]:[version]`
   - Once started, set up the instance with the following basic configuration:
       * Portal Name: Mondego
       * First Name: Test
       * Last Name: Test
       * Email: test@modego.com
   - Check out the Liferay Foundations: Introduction to Liferay DXP course if you have questions on starting a new instance of Liferay DXP.
* You should also have at least one additional User, Naomi Engel, created.
   - Uncheck _Require strangers to verify their email address_ in the User Authentication section of the Control Panel under Instance Settings
   - For more on creating Users, consult the module Add Users and Manage Permissions.