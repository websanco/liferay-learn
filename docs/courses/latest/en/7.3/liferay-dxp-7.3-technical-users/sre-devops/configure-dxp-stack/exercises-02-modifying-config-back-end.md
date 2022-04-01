## Modifying Configurations through the Back-End

<div class="ahead">

#### Exercise Goals

* Manage an OSGi module with Telnet
* Create a new document MIME type
* Export and deploy the configuration on a separate container

</div>

#### Log in to a Telnet Session in Your Terminal
1. **Start** the Tomcat docker container if it is not already running.
* **Open** the *Terminal/Command Prompt* window to access the Gogo Shell.
	- If you are on Windows, you will need to *run Command Prompt as Administrator* and enable the telnet client:
		- **Type** `dism /online /Enable-Feature /FeatureName:TelnetClient` in the _Command Line_.
		- **Press** _Enter_.
* **Type** `telnet localhost 11311`.
* **Press** *Enter* to telnet into the Gogo Shell.

<div class="note">
Note: Telnet has been removed from the lastest MacOS releases. You can still access the Gogo Shell through Liferay's UI by going to Control Panel → Configuration → Gogo Shell.
</div>

<div class="page"></div>

#### Examine and Restart a Module Using Telnet
1. **Type** `lb blogs`.
2. **Press** *Enter* to search for the Blogs module to show all the modules with the word "blogs".
    - We see there's a long list of modules related to the Blogs application that are *active*, but our Blogs API is *resolved*.
3. **Type** `start [ID for Liferay Blogs API]` in the _Terminal_.
	- This is the bundle ID for the Blogs API.
4. **Press** *Enter*.
5. **Type** `lb blogs`.
6. **Press** *Enter* to see that the _Liferay Blogs API_ is now active.
6. **Type** `disconnect` to end the telnet session.

If you refresh the App Manager in the browser, you'll see that the module is marked as _Active_ once again.

#### Find the Documents and Media Settings in System Settings
1. **Go to** `localhost:8081` in your browser of choice.
* **Open** the *Applications Menu*.
* **Go to** _`Control Panel → Configuration → System Settings`_.
* **Find** the *Content and Data* section near the top of the _System Settings_ menu.
* **Click** on *Documents and Media*.

#### Create a New MIME Type for Markdown Files
1. **Click** on _Service_ on the left-hand side.
2. **Click** the "+" next to the top entry.
3. **Create** a new *MIME type* for `text/markdown`.
4. **Click** *Save* at the bottom of the page.

<br />

<img src="images/text_mark.png" style="max-width:80%">

#### Export the New MIME Type Configuration
1. **Click** on the *Options* button next to *Service* at the top of the page.
2. **Choose** *Export* from the options that appear.
3. **Click** *Save* or *Save As* if prompted by your browser.
4. **Click** *Ok*.

<br />

<img src="images/export_service.png" style="max-width: 100%">

#### Find the Exported Configuration and Add it to the Tomcat Volume
1. **Go to** your *Downloads* folder.
    - You should see your custom _Documents and Media_ configuration file: `com.liferay.document.library.configuration.DLConfiguration.config`
2. **Copy** the configuration file to the _configs_ folder in the mounted volume for the Tomcat server:
    - _liferay/liferay-tomcat/config_
    - You may see some configuration files already in the folder.

#### Start a New Tomcat Container for Testing the Configuration
1. **Start** a new Tomcat instance to test the settings:
	- Open a new _Terminal_.
	- Run the following command from your _tomcat_ folder:

```bash
docker-compose build liferay-tomcat-1 #To create a container from the image
docker-compose up -d liferay-tomcat-1 #To start the container

```

- Restart the container if it is currently running:

```shell
docker-compose restart liferay-tomcat-1
```

2. **Go to** *localhost:8080* in your web browser.
3. **Sign in** to Liferay.
    - User Name: *test@liferay.com*
    - Password: *test*

#### Confirm the Imported Configuration is on the Tomcat Server
1. **Open** the *Menu*.
2. **Go to** _`Control Panel → Configuration → System Settings`_.
3. **Click** on *Documents and Media*.
4. **Click** on _Service_.
    - You should see that the MIME type configuration has been added to the new Tomcat instance.

<div class="page"></div>

---

#### Bonus Exercises:
1. Search `portal.properties` for _Default User Private Layouts_.
	- You can find the file online at https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#Default%20User%20Private%20Layouts
2. Read through the file and see which changes you can make to the user pages.
3. Configure a default setting for user page layouts in `portal-ext.properties` and add the configuration to the platform to see changes.

This is a great opportunity to explore the additional configuration options in the `portal-ext.properties` file.
