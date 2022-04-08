<h2 class="exercise">Exercises</h2>

## Configuring Default Settings via the Platform UI

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Configure the default language for the platform</li>
			<li>Configure the default time zone for the platform</li>
			<li>Manage an OSGi module via the App Manager in the UI and with Telnet</li>
		</ul>
</div>

#### Build and Start the Container for the Tomcat Service
1. **Open** a new _Terminal_ window.
2. **Go to** the _liferay-tomcat_ folder in your exercise source.
3. **Run** the following command to build the _tomcat_ and _mysql_ services:
```shell
docker-compose build liferay-tomcat-1 mysql
```
4. **Run** the following command to start the service and its dependencies:
```shell
docker-compose up -d liferay-tomcat-1
```

#### Log in to the Server and Find the Instance Settings

1. **Go to** `localhost:8081` in your browser to access the site.
2. **Sign in** to the platform with the following credentials:
    * User name: _test@liferay.com_
    * Password: _test_
3. **Click** through the startup steps if necessary.
	* Accept the _Terms of Use_.
	* Create a password reminder.
4. **Open** the *Menu* for your running instance.
5. **Go to** _`Control Panel → Configuration → Instance Settings`_.
6. **Click** on the *Localization* option on the *Instance Settings* page.

#### Set a New Default Language for the Platform

Notice there are a number of options for both locales and time zones. For now, let's focus on just the locales, specifically the default language. When setting a default language, whatever is selected will be applied first.

1. **Choose** *French (France)* under the *Default Language* drop-down menu.
2. **Click** *Save* at the bottom of the page to save the change.

<img src="../images/chapter-2/default-language-changed.png" style="max-width: 100%">

#### Verify the Language Change in a New Browser

You'll notice right away that nothing has changed. This is because the current user's default language is not French.

1. **Open** another browser or your current browser in private/incognito mode to see the changes in effect.
2. **Go to** *localhost:8081*, and you'll see specific areas of the homepage in the language you selected.

<img src="../images/chapter-2/liferay-french.png" style="max-width: 100%">

#### Revert to the Original Default Language

1. **Go to** your original browser again, where we were configuring the default language.
2. **Choose** *English (United States)* as the default language, under the *Default Language* drop-down menu.
3. **Click** *Save*.

You may notice the section below the default language, where you can see the heading *Available Languages*. Under that heading, we are given two boxes: *current* and *available*. Current languages are the languages that are being used by Liferay. Available languages are the languages that can be used but aren't being implemented. To make a language current or available, select the language and press the arrow to move it to the desired section.

<img src="../images/chapter-2/available-languages.png" style="max-width: 100%">

#### Set a Custom Time Zone for the Platform

While we are here in *Display Settings*, let's discuss changing the time zone.

1. **Click** on the *Time Zone* section on the right.
2. **Choose** whatever time zone is applicable to you.
3. **Click** *Save*.

This sets the time zone throughout the whole instance, but because the user setting is different from the instance setting, the user setting takes precedence. Liferay's time zone will now be based off of the default we set.

<img src="../images/chapter-2/time-zone.png" style="max-width: 100%">

#### Find the Site-Level Configuration Options

Now that we've seen how to set the locales at the instance level, we'll take a look at how to set them at the site level.

1. **Open** the *Menu*.
2. **Click** the _Site Selector_ (the compass icon).
3. **Choose** _Liferay DXP_.
4. **Open** the *Site Administration* panel.
5. **Go to** _`Configuration → Settings`_.
6. **Click** the *Languages* tab.

<img src="../images/chapter-2/site-languages.png" style="max-width: 100%">

We are given two options under *Languages*. The first option, *Use the default language options*, takes the values established in the *Instance Settings* of the *Control Panel*. The second option, *Define a custom default language and additional available languages for this Site*, allows you to take the current languages set by *Instance Settings* and disable them.

#### Find the App Manager Settings

Within your operations role, you may be tasked with managing or monitoring an application component or module. Modules, like properties, can be managed in two ways. The first way to manage modules is through the UI.

1. **Open** the *Menu*.
2. **Go to** _`Control Panel → Apps`_.
3. **Click** on *App Manager*.

<img src="../images/chapter-2/app-manager.png" style="max-width: 100%"> 

In the *App Manager*, we can manage all of the apps that are installed in Liferay. Let's take a look at the *Liferay Blogs Module*.

#### Deactivate a Module Using the App Manager in the UI

1. **Click** on *Liferay Blogs*, and you'll see all the modules that make up the Blogs app.
2. **Click** on the *Options* menu next to the _Liferay Blogs API_.
3. **Choose** *Deactivate*.
4. **Click** *OK* when prompted with *"Are you sure you want to deactivate this?"* 

You'll see that the status has been changed from *Active* to *Resolved*, letting us know that the Blogs app is ready to go but not running (effectively deactivated.)

<img src="../images/chapter-2/blogs-resolved.png" style="max-width: 100%">

#### Log in to a Telnet Session In Your Terminal

Another way to manage modules in Liferay is through the Gogo Shell. The Gogo Shell allows you to check the status and stop and start modules through the *Terminal*.

1. **Open** the *Terminal/Command Prompt* window to access the Gogo Shell.
	- If you are on Windows, you will need to *run Command Prompt as Administrator* and enable the telnet client:
		- **Type** `dism /online /Enable-Feature /FeatureName:TelnetClient` in the _Command Line_.
		- **Press** _Enter_.
<img src="../images/chapter-2/telnet-on.png" style="max-width: 100%">
2. **Type** `telnet localhost 11311`.
3. **Press** *Enter* to telnet into the Gogo Shell.

NOTE:
Telnet has been removed from the lastest MacOS releases. You can still access the Gogo Shell through Liferay's UI by going to _Control Panel → Configuration → Gogo Shell_.

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

<img src="../images/chapter-2/blogs-active.png" style="max-width: 100%">

---

#### Bonus Exercises: 

Try the following bonus exercise after completing the main exercises:

We mentioned in the lecture that you can control some default settings for user pages via `portal.properties`. 

1. Search the `portal.properties` for _Default User Private Layouts_.
	- You can find the file online at https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#Default%20User%20Private%20Layouts
2. Read through the file and see which changes you can make to the user pages.
3. Configure a default setting for user page layouts in `portal-ext.properties` and add the configuration to the platform to see changes.

This is a great opportunity to explore the additional configuration options in the `portal.properties` file.
