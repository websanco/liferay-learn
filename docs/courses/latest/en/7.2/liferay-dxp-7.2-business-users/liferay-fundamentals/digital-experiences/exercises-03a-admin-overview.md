<h2 class="exercise">Exercises</h2>

## Getting Started with the Livingstone Platform

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Set up and run an instance of Liferay using one of the following methods:</li>
    <ul>
        <li>DXP Cloud Instance</li>
        <li>Local Instance</li>
    </ul>
	<li>Add the Livingstone company logo to the platform</li>
</ul>
</div>

Note: The following instructions pertain only to setting up a local instance of Liferay DXP. For DXP Cloud set up instructions, please consult the DXP Cloud Journey.

#### Get and Start a Liferay Docker Image
1. **Open** your command line terminal.
2. **Type** `docker pull liferay/[product]:[version]` and hit return to get the Docker image.
	* You should see a list of items downloading in your terminal. When complete, everything should read "Pull Complete."
3. **Type** `docker run -it -m 8g -p 8080:8080 liferay/[product]:[version]` and hit return to start the Docker image. 
4. **Open** your browser. 
5. **Go to** `https://localhost:8080`.

_Note: For Docker to run properly, you may need to go to Preferences → Resources in Docker Desktop and increase the memory. Once the Docker Image has been pulled and started, you can stop and restart your instance of Liferay DXP using Docker Desktop. Go to the_ Containers/Apps _tab and click stop/start or restart as needed._


#### Create the Livingstone Hotels & Resorts Site
1. **Sign in** using the default credentials.
	* Email: `test@liferay.com`
	* Password: `test` 
* **Open** the _Menu_. 
* **Go to** _Control Panel → Sites → Sites_. 
* **Click** the _Add_ button.
* **Click** _Blank Site_. 
* **Type** `Livingstone Hotels & Resorts` as the _Name_.
* **Click** _Save_.

#### Disable Email Verification for New Users
1. **Open** the _Menu_. 
* **Go to** _Control Panel → Configuration → Instance Settings_. 
* **Click** _User Authentication_ under _Platform_. 
* **Click** to uncheck the box next to _Require strangers to verify their email address?_ if it is not already disabled.
* **Click** _Save_. 

_Note: Since this is a local instance with no mail server set up, disabling email verification allows you to create and sign in as multiple users with different email address without requiring that the emails be verified._

#### Rename the Default Administrative User
1. **Open** the _Menu_. 
* **Go to** _Control Panel → Users → Users and Organizations_.
* **Click** _Test Test_.  
* **Type** `j.copeland` for the new _Screen Name_.
* **Type** `josiah.copeland@livingstone.com` for the new email address.
* **Type** `Josiah` for the new _First Name_.
* **Type** `Copeland` for the new _Last Name_.
* **Click** _Save_. 

#### Change Josiah Copeland's Password
1. **Click** _Password_ in the menu on the left.
* **Type** a new password for your User.
* **Click** _Save_.

#### Add the Livingstone Logo to the Platform
1. **Go to** _`Control Panel → Configuration → Instance Settings`_ in the _Menu_.  
* **Click** on _`Instance Configuration`_ in the _Platform_ section.
* **Uncheck** the _Allow site administrators to use their own logo_ check box.
* **Click** _Change_ under the default logo.
* **Click** _Select_.
* **Choose** the `livingstone-logo.png` from your exercises folder.
* **Click** _Done_.
* **Click** the _Save_ button at the bottom.

<img src="../images/logo-added.png" style="max-height: 100%" />

---

#### Bonus Exercises:
1. Explore the _General_ Section and change the Mail Domain to use livingstone.com.
2. Go back to Instance Settings and choose which languages to use on the platform in _Localization_.
3. Explore the _Users_ section of Instance Settings and add some Default User Associations.
