# Using Headless REST APIs with Liferay

```{toctree}
:maxdepth: 2

using-headless-rest-apis-with-liferay-dxp/using-liferay-dxp-as-a-headless-platform.md
using-headless-rest-apis-with-liferay-dxp/producing-apis-with-rest-builder.md
using-headless-rest-apis-with-liferay-dxp/exercise-1-use-headless-apis-with-a-remote-react-app.md
using-headless-rest-apis-with-liferay-dxp/summary.md
```

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/SAzCK5cn_-k

## Learning Objectives

* Understand the advantages and disadvantages of using Liferay DXP's Headless features and capabilities
* Learn how to connect a remote application to Liferay DXP using Headless APIs

## Tasks to Accomplish

* Identify the proper Headless API needed to use Liferay Objects data in a remote application
* Connect a remote application to Liferay DXP using Headless APIs

## Exercise Prerequisites

* Liferay DXP 7.4 set up and running
    - If you have not started your instance yet, first make sure you have downloaded Docker, then use the following commands to get and start the Liferay Docker Image: 
        * `docker pull liferay/[product]:[version]`
        * `docker run -it -m 8g -p 8080:8080 liferay/[product]:[version]`
	- Once started, set up the instance with the following basic configuration:
		* Portal Name: Mondego
		* First Name: Test
		* Last Name: Test
		* Email: test@liferay.com
   - Check out the Liferay Foundations: Introduction to Liferay DXP course if you have questions on starting a new instance of Liferay DXP
* Unzipped module exercise files in the following folder structure:
	- Windows: `C:\liferay`
	- Unix Systems: `[user-home]/liferay`
* Node.js installed (v 16.13.2 used in exercise videos)
* Create React App installed 
  	- To install, run `npm install -g create-react-app` in your terminal
* A create-react-app project created with the appropriate files replaced with those located within the _prerequisites_ folder
* The React-Bootstrap framework installed in your project
  	- To install, first run `npm install react-bootstrap`, then `npm install bootstrap` in your terminal
* React Router v5.2 installed in your project
  	- To install, run `npm install react-router-dom@5.2.0` in your terminal
* An IDE, such as Visual Studio Code, installed
* An running instance of Liferay DXP 7.4 with the following contents:
  	- A picklist called _Account Types_ with options for _Savings_, _Checking_, and _Individual Retirement_.
  	- A Liferay Object called _Bank Accounts_ with at least two entries containing the following fields:

| Label		      | Type		  | Mandatory	  |				
|:--------------- |:-------------:|--------------:|
| Account Holder  | Text          | Yes           |
| Account Number  | Integer       | Yes           |
| Account Type    | Picklist      | Yes           |
| Account Balance | Decimal       | Yes           |


## Next Steps

* [Using Liferay DXP as a Headless Platform](./using-headless-rest-apis-with-liferay-dxp/using-liferay-dxp-as-a-headless-platform.md)
* [Producing APIs with REST Builder](./using-headless-rest-apis-with-liferay-dxp/producing-apis-with-rest-builder.md)  
* [Exercise 1: Use Headless APIs With a Remote React App)](./using-headless-rest-apis-with-liferay-dxp/exercise-1-use-headless-apis-with-a-remote-react-app.md) 
* [Summary](./using-headless-rest-apis-with-liferay-dxp/summary.md) 


