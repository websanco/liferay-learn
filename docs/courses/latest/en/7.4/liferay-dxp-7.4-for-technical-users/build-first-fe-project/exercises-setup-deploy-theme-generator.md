## Setting up and Deploying with the Liferay Theme Generator

<div class="ahead">

#### Exercise Goals

* Use the NPM Theme generator to quickly generate and deploy a theme
	* Set up a local instance of Liferay
	* Install the NPM tools to create and deploy front-end modules
	* Create a basic Theme Module
	* Deploy the basic Theme Module to the local instance of Liferay
	* Select the Theme Module on the platform

</div>

#### Install a Liferay Tomcat Bundle in a New Bundles Folder
1. **Create** a new _`bundles`_  folder in your liferay folder so that you have a directory that looks like this:
	* Windows: _C:\liferay\bundles_
	* Unix Systems: _[user-home]/liferay/bundles_
2. **Expand** the `liferay-dxp-[version]`  file to the bundles directory.  
	* Windows: liferay-dxp-[version].zip
	* Unix Systems: liferay-dxp-[version].tar.gz

_Note: This file can be found in the software materials provided by the instructor. Windows' built-in archive tool causes problems with Liferay when it tries to extract the large .zip file. We recommend using a third-party tool, like 7-Zip or WinZip._

#### Set the Tomcat Bundle to Developer Mode
1. **Copy** the _portal-ext.properties_ file from this module's exercise folder.
2. **Paste** the file into the _Liferay Home_ folder.
	* Windows: _C:\liferay\bundles\liferay-dxp-[version]_
	* Unix Systems: _[user-home]/liferay/bundles/liferay-dxp-[version]_

#### Install Node
1. **Go to** [https://nodejs.org/en/](https://nodejs.org/en/).
2. **Click** the appropriate installer for your operating system.
3. **Click** through the installer.
4. **Open** a _Command Line/Terminal_ window.  
5. **Run** `node -v`  in your _Command Line_ to verify your installation once you have installed node.js and npm.
6. Run `npm -version`  to double-check that the appropriate version is installed.

_Note: It is possible you may run into errors with some versions of Node. To avoid this, we recommend using Node version 16.13.2 and npm version 8.x.x._

#### Mac OSX and Linux: Redirect the NPM Global Command
1. **Create** a `.npmrc`  file in your user home.
	* You can navigate here in your _Terminal_ using `cd ~/[username]`.
2. **Add** the following in your `.npmrc`  file:
```
prefix=/Users/[username]/.npm-packages
```
3. **Save** the file.
4. **Open** your `.profile` file.
	* Use the shortcut `CMD + SHIFT + .`  to view hidden files in your user home.
5. **Add** the following to your `.profile`  file:
```
export NPM_PACKAGES=/Users/[username]/.npm-packages
export PATH=${PATH}:${NPM_PACKAGES}/bin
```
6. **Save** the file.
7. **Restart** the _Terminal_ for the changes to take place.

<br />

#### Install Yeoman and the Liferay Theme Generator
1. **Run** `npm install -g yo gulp`  to install the Yeoman and Gulp dependency.  
2. **Run** `npm install -g generator-liferay-theme@10.x.x`  to install the Liferay Theme Generator.
3. **Run** `yo`  in the _Command Line/Terminal_ to see that the generator is installed.  
	* Type _Y or N_ if an initial prompt asks to collect data.    
4. **Choose** _Get me out of here!_

<br />

#### Start the Liferay-Tomcat Bundle
1. **Go to** the Tomcat server's `bin`  directory:
	* Windows: _C:\liferay\bundles\liferay-dxp-[version]\tomcat-[version]\bin_ in the file manager
	* Mac/Linux: _[userhome]/liferay/bundles/liferay-dxp-[version]/tomcat-[version]/bin_ using the _Terminal_
2. **Start** the Tomcat Server:
	* Windows: **Double-click** on the `startup.bat`.  
	* Mac/Linux: **Run** `./catalina.sh run`  in your _Terminal_.

#### Deploy the Activation Key
1. **Copy** the _activation-key-digitalenterprisedevelopment-7.3-liferaycom.xml_ file from your provided materials.
2. **Paste** the file into your _liferay-dxp-[version]/deploy_ folder.

<br />

#### Complete the Liferay Setup Wizard
1. **Go to** _localhost:8080_ in the browser.
2. **Type** _Livingstone Hotels & Resorts_ as the _Portal Name_.
3. **Type** _Josiah_ for the _First Name_.
4. **Type** _Copeland_ for the _Last Name_.
5. **Type** `josiah.copeland@livingstone.com`  for the _Email_.
6. **Click** _Finish Configuration_.
7. **Click** _I Agree_ for the _Terms of Use_ placeholder.
8. **Type** a new password for your account.
9. **Click** _Save_.
10. **Choose** a password reminder query.
11. **Type** in an answer.
12. **Click** _Save_.

#### Create a New Theme to Deploy
1. **Go to** your _`liferay`_  folder in your _Command Line/Terminal_.
	* Windows: _C:\liferay_
	* Unix Systems: _[userhome]/liferay_
2. **Run** _yo liferay-theme_.
3. **Type** _My Test Theme_ for the _theme name_.
4. **Press** _Enter_  to accept the default _themeId_.
5. **Choose** _7.4_  for the _version_.
6. **Type** _N_  when asked to use _Font Awesome_ in the theme.
	* Font Awesome is no longer included by default and using it may cause errors. 
7. **Choose** _Local App Server_  for the _deployment strategy_.
8. **Add** the app server directory path:
	* Windows: _C:\liferay\bundles\liferay-dxp-[version]\tomcat-[version]_
	* Unix Systems: _[user-home]/liferay/bundles/liferay-dxp-[version]/tomcat-[version]_
9. **Press** _Enter_ to accept the default url.

<div class="note">
Note: Liferay themes created with the theme generator are bundled with Gulp. In order to run Gulp locally from the command line, we must add a script to the package.json file. Additionally, some setups run into issues running scripts with npm. If this is the case, try creating an .npmrc file in the root of your project and add the <code>ignore-scripts=false</code> configuration.
</div>

#### Deploy the Theme
1. **Go to** your new theme directory in the _Command Line/Terminal_:
	* Windows: _C:\liferay\my-test-theme_
	* Unix Systems: _[userhome]/liferay/my-test-theme_
* **Run** _npm run deploy_ in the _Command Line/Terminal_.

#### Select the New Theme on the Platform
1. **Log in** to _localhost:8080_ if you're not already logged in.
2. **Open** the _Menu_.
3. **Go to** _`Site Builder > Pages`_ in the _Site Administration_ panel.
4. **Click** on the configuration icon next to _Public Pages_.
5. **Click** the _Change Current Theme_ button.
6. **Choose** the _My Test_ theme.
7. **Click** _Save_.
8. **Click** _Home_ in the _Site Administration_ panel to see the base theme without any customization.
	* Note: My Test Theme does not include a login portlet. Be sure to change the theme back to Classic if you need to close your instance. 

<br />

---

#### Bonus Exercises
1. Change the theme back to the _Classic_ theme.
2. Explore the new theme project and add some css to the _custom.scss_ file in the _src_ folder and deploy the changes.
