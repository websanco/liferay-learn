## Getting Started with the Livingstone Platform

<div class="ahead">

#### Exercise Goals

* Set up and run an instance of Liferay DXP using Docker
* Take a walk-through of different platform features

</div>

#### Get and Start a Liferay Docker Image
1. **Open** your command line terminal. 
2. **Type** `docker pull liferay/[product]:[version]` and hit return to get the Docker image.
	* You should see a list of items downloading in your terminal. When complete, everything should read "Pull Complete."
3. **Type** `docker run -it -m 8g -p 8080:8080 liferay/[product]:[version]` and hit return to start the Docker image. 
4. **Open** your browser. 
5. **Go to** `https://localhost:8080`.

_Note: For Docker to run properly, you may need to go to Preferences → Resources in Docker Desktop and increase the memory. Once the Docker Image has been pulled and started, you can stop and restart your instance of Liferay DXP using Docker Desktop. Go to the_ Containers/Apps _tab and click stop/start or restart as needed._

#### Create the Livingstone Hotels & Resorts Site
1. **Sign in** to the platform using the default credentials.
	* Email: `test@liferay.com`
	* Password: `test`
* **Open** the _Global Menu_ (3x3 grid icon in upper right of screen). 
* **Go to** _Control Panel → Sites → Sites_. 
* **Click** the _Add_ button.
* **Click** _Blank Site_. 
* **Type** `Livingstone Hotels & Resorts` as the _Name_.
* **Click** _Add_. 

#### Disable Email Verification for New Users
1. **Open** the _Global Menu_. 
* **Go to** _Control Panel → Configuration → Instance Settings_. 
* **Click** _User Authentication_ under _Platform_. 
* **Click** the box next to _Require strangers to verify their email address?_ to disable. 
* **Click** _Save_. 

_Note: Since this is a local instance with no mail server set up, disabling email verification allows you to create and sign in as multiple users with different email address without requiring that the emails be verified._

#### Rename the Default Administrative User
1. **Open** the _Global Menu_. 
* **Go to** _Control Panel → Users → Users and Organizations_.
* **Click** _Test Test_.  
* **Type** `j.copeland` for the new _Screen Name_.
* **Type** `josiah.copeland@livingstone.com` for the new email address.
* **Type** `Josiah` for the new _First Name_.
* **Type** `Copeland` for the new _Last Name_.
* **Click** _Save_. 
* **Type** the default password and hit _Confirm_.

#### Change Josiah Copeland's Password
1. **Click** _Password_ in the menu on the left.
* **Type** a new password for your User.
* **Click** _Save_.

#### Explore the Site Administration Panel
1. **Click** the  _Menu_ icon (square directly to the left of _Home_).
	* This opens the _Site Administration_ Panel.  
* **Click** on _Design_ and view the related tools.
* **Click** on _Site Builder_ and view the related tools.
* **Click** on _Content & Data_ and view the related tools.
* **Click** on _Categorization_ and view the related tools.
* **Click** on _People_ and view the related tools.
* **Click** on _Configuration_ and view the related tools.
* **Click** on _Publishing_ and view the related tools.

#### Explore the Global Menu
1. **Open** the  _Global Menu_ (3x3 grid icon in upper right of screen).
	* Note: If the menu looks mostly blank upon opening, simply refresh the page and try again.  
* **View** the _Applications_ tab of the _Global Menu_.
	* Note: This tab will open by default when opening the _Global Menu_.
* **Click** on the _Control Panel_ tab.
* **View** the _Control Panel_ tab of the _Global Menu_.
	* Note: The _Instance Settings_ section used in the Bonus Exercises is located in the _Control Panel_ under _Configuration_.
* **Click** on _Livingstone Hotels & Resorts_ under _Sites_ to return to the main Site.

<br />

<img src="images/global_menu.png" style="max-width:95%;" />

---

#### Bonus Exercises:
1. Explore the _General_ section of _Instance Settings_ and change the main domain to use livingstone.com.
2. Go back to _Instance Settings_ and choose which languages to use on the platform in _Localization_.
3. Explore the _Users_ section of _Instance Settings_ and add some Default User Associations.
