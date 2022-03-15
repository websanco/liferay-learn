# Build New Sites with Liferay DXP

#### Learning Objectives

* Understand how to add Pages and create Page Templates
* Learn what Site Templates are and how to use them
* Learn how to manage Sites and configure Site Settings

#### Tasks to Accomplish

* Add Pages to the default Site
* Create a Landing Page Template to be used across the platform
* Create a Site Template for Mondego's Bank Branches to rapidly add Sites to the platform
* Manage Site configurations
* Add a Child Site to the main Mondego Site

#### Exercise Prerequisites

* Unzipped module exercise files in the following folder structure:
	- Windows: `C:\liferay`
	- Unix Systems: `[user-home]/liferay`
* Liferay DXP 7.4 set up and running
	- If you have not started your instance yet, first, make sure you have downloaded Docker, then use the following commands to get and start the Liferay Docker Image: 
        * `docker pull liferay/[product]:[version]`
        * `docker run -it -m 8g -p 8080:8080 liferay/[product]:[version]`
    - Once started, set up the instance with the following basic configuration:
		* Portal Name: Mondego
		* First Name: Test
		* Last Name: Test
		* Email: test@modego.com
	- Check out the Liferay Foundations: Introduction to Liferay DXP course if you have questions on starting a new instance of Liferay DXP
