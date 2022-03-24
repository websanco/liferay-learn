# Secure Your Liferay DXP Platform 

```{toctree}
:maxdepth: 2

secure-your-liferay-dxp-platform/securing-liferay-with-user-authentication.md
secure-your-liferay-dxp-platform/exercises-create-a-password-policy.md
secure-your-liferay-dxp-platform/exercises-enable-captcha.md
secure-your-liferay-dxp-platform/securing-web-services.md
secure-your-liferay-dxp-platform/securing-content-and-fine-tuning-security.md
secure-your-liferay-dxp-platform/summary.md
```

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/dxl9QxBi-xY

## Learning Objectives

* Understand what security tools are built into Liferay DXP
* Learn how to add security measures to an instance of Liferay
	
## Tasks to Accomplish 

* Create a new Password Policy
* Enable CAPTCHA for Message Boards and Forms

## Exercise Prerequisites

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
* The Mondego Publications Site created with one page, _Community_, and with a Message Board widget added to the page
	- See the Build New Sites with Liferay DXP Module for more information on creating the Publications Site
* The Organization _Mondego Investments_ created
	- See the Add Users and Manage Permissions with Liferay DXP module for more information on Organizations
* _Require strangers to verify their email address_ disabled from the User Authentication section of the Control Panel under Instance Settings

## Next Steps

* [Securing Liferay with User Authentication](./secure-your-liferay-dxp-platform/securing-liferay-with-user-authentication.md) 
* [Exercise 1: Create a Password Policy](./secure-your-liferay-dxp-platform/exercises-create-a-password-policy.md) 
* [Exercise 2: Enable Captcha](./secure-your-liferay-dxp-platform/exercises-enable-captcha.md) 
* [Securing Web Services](./secure-your-liferay-dxp-platform/securing-web-services.md) 
* [Securing Content and Fine-Tuning Security](./secure-your-liferay-dxp-platform/securing-content-and-fine-tuning-security.md) 
* [Summary](./secure-your-liferay-dxp-platform/summary.md) 
