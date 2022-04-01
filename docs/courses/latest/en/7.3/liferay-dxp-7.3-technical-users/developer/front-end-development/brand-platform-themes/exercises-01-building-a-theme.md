## Create the Livingstone Fjord Theme

<div class="ahead">

#### Exercise Goals

* Install Visual Studio Code and add code snippets
* Use the NPM Theme Generator to create a new theme
	* Set up some initial theme variables
	* Modify the HTML source file and add Footer code
* Deploy the Initial Base theme

</div>

#### Install Visual Studio Code
1. **Go to** `https://code.visualstudio.com/`.
* **Click** to download the VS Code for your OS.
* **Double-click** to run the installer for your operating system.  
* **Click** through the installation steps.

#### Create a Global Snippets File in Visual Studio Code
1. **Open** _Visual Studio Code_.
* **Go to** the _User Snippets_ menu.
	* Windows: `File > Preferences > User Snippets`
	* OSX: `Gear Icon > User Snippets`
* **Click** on _New Global Snippets file..._ in the drop-down menu.
* **Type** _lfr-front-end_ for the _name_.
	* The full file name will be _lfr-front-end.code-snippets_.
* **Click** _Save_.

<div class="page"></div>

#### Add the Snippet Content
1. **Open** the `lfr-front-end-snippets.json` in your module exercises folder.
* **Copy** the contents of the file.   
* **Paste** the contents of the file in the `lfr-front-end.code-snippets` file just created in Visual Studio Code.
	* Make sure to replace all the existing content in the file.
* **Save** the file.

#### Start the Liferay-Tomcat Bundle
1. **Go to** the Tomcat server's `bin` directory:
	* Windows: _C:\liferay\bundles\liferay-dxp-[version]\tomcat-[version]\bin_ in the file manager
	* Mac/Linux: _[userhome]/liferay/bundles/liferay-dxp-[version]/tomcat-[version]/bin_ using the _Terminal_
* **Start** the Tomcat Server:
	* Windows: **Double-click** on the `startup.bat`.  
	* Mac/Linux: **Run** `./catalina.sh run` in your _Terminal_.

<div class="page"></div>

#### Generate the Base Theme
1. **Go to** the _liferay_ folder you already created in the _Command Line/Terminal_.
	- Windows: _C:\liferay_
	- Mac/Linux: _~/liferay_
* **Run** the `yo liferay-theme` command.  
	- This command will create the base theme files and install the necessary dependencies for deployment.
* **Type** `Livingstone Fjord Theme` to name your theme.  
* **Press** _Enter_.  
* **Press** _Enter_ to accept the default _themeId_.
	- This is how Liferay refers to your theme internally.
* **Choose** _7.3_ for the version.
* **Press** _Enter_.
* **Type** `Y` to use _Font Awesome_ in the theme.
* **Press** _Enter_.
* **Choose** _Local App Server_ when asked to select your deployment strategy.
* **Type** the directory:
	- Windows: _C:\liferay\bundles\liferay-dxp-[version]\tomcat-[version]_
	- Mac/Unix: _/Users/[username]/liferay/bundles/liferay-dxp-[version]/tomcat-[version]_
* **Press** _Enter_.
* **Press** _Enter_ again to use _http://localhost:8080_ for the URL.

<div class="note">
Note: If the Theme Generator returns an error, make sure you are using NPM 10.15.1. Additionally, some setups run into issues running scripts with npm. If this is the case, try creating an .npmrc file in the root of your project and add the <code>ignore-scripts=false</code> configuration.
</div>

<div class="page"></div>

#### Deploy the Base Theme to Our Liferay Instance
1. **Go to** _Livingstone Fjord Theme_ using `cd livingstone-fjord-theme` in the _Command Line_.
	- This is assuming you are still running _Command Prompt/Terminal_ from `C:\liferay`.
* **Run** the `npm run deploy` command.  
	- This makes the base theme available in our Liferay instance.
* **Open** your browser.
* **Go to** `localhost:8080` if you're not already there.
* **Sign in** to the _Livingstone Hotels & Resorts_ platform. 
* **Open** the _Site Administration Panel_.
* **Go to** _`Site Builder > Pages`_ in _Site Administration_ for the _Livingstone Hotels & Resorts_ Site.  
* **Open** the _Configuration menu_ next to _Public Pages_.
	* This is the gear icon.
* **Click** the _Change Current Theme_ button near the bottom.  
* **Choose** _Livingstone Fjord Theme_.  
* **Click** the _Save_ button at the bottom.  
* **Click** _`Home`_ in _Site Administration_ to see your theme.

#### Add the Directory Structure to the SRC Folder
1. **Go to** the _`livingstone-fjord-theme\src`_ folder.  
	- Windows: _`C:\liferay\livingstone-fjord-theme\src`_
	- Mac/Linux: _`~/liferay/livingstone-fjord-theme\src`_
* **Create** the following folders:
	- _`js`_
	- _`templates`_  

_Note: We will be using npm run deploy throughout the course. If you decide to use gulp watch, you will not need to run npm run deploy._

<div class="page"></div>

#### Set Variables to Start
1. **Copy** the `liferay-look-and-feel.xml` file from the _`WEB-INF`_ folder in your _`exercise-src`_ folder.  
* **Paste** the file into the _`livingstone-fjord-theme\src\WEB-INF`_ folder.
	* You will need to replace the original file.

```xml
<?xml version="1.0"?>
<!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 7.3.0//EN" "http://www.liferay.com/dtd/liferay-look-and-feel_7_3_0.dtd">

<look-and-feel>
	<compatibility>
		<version>7.3.0+</version>
	</compatibility>
	<theme id="livingstone-fjord-theme" name="Livingstone Fjord Theme">
		<template-extension>ftl</template-extension>
		<settings>
			<setting configurable="false" key="color-palette" value="primary,success,danger,warning,info,dark,gray-dark,secondary,light,lighter,white" />
			<setting configurable="true" key="show-footer" type="checkbox" value="true" />
			<setting configurable="true" key="show-header" type="checkbox" value="true" />
			<setting configurable="true" key="show-main-navigation-in-full-screen" type="checkbox" value="false" />
			<setting configurable="false" key="show-site-name-default" value="true" />
			<setting configurable="false" key="show-site-name-supported" value="true" />
			<setting configurable="true" key="use-a-retina-logo" type="checkbox" value="true" />
			<!-- Insert snippet 02-theme-settings here -->
		</settings>
		<!-- Insert snippet 01-portlet-decorators here -->
	</theme>
</look-and-feel>
```

<div class="page"></div>

#### Modify Theme Templates
1. **Copy** the files in the _`exercise-src/templates`_ folder.
* **Paste** the contents into the _`livingstone-fjord-theme\src\templates`_ folder.
* **Open** the _Visual Studio Code_ editor.
* **Drop** the `portal_normal.ftl` file from the _`livingstone-fjord-theme\src\templates`_ file into the _Visual Studio Code_ editor.  

#### Modify the Header
1. **Click** to highlight the `<#-- Insert snippet 01-portal-normal-header here -->` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `ftl: 01-portal-normal-header` snippet.
	* Alternatively, you can just type in the following:

```html
	<#if show_header>
		<#include "${full_templates_path}/header.ftl" />
	</#if>
```

#### Add the Content Section
1. **Click** to highlight the `<#-- Insert snippet 02-portal-normal-main here -->` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `ftl: 02-portal-normal-main` snippet.

#### Keep the Footer Code Distinct
1. **Click** to highlight the `<#-- Type footer.ftl include here -->` comment.
* **Type** in the following to include the footer.ftl file and then _Save_ the file:

```html
	<#if show_footer>
		<#include "${full_templates_path}/footer.ftl" />
	</#if>
```

<div class="page"></div>

#### Add Code to the Footer FTL File
1. **Drop** the `footer.ftl` file from _`livingstone-fjord-theme\src\templates`_ into the _Visual Studio Code_ editor.   
* **Click** to highlight the `<#-- Insert 03-footer-ftl here -->` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `03-footer-ftl` snippet.
* **Save** the file.

#### Add a Sign-In Link to the Footer
1. **Drop** the `footer_navigation.ftl` file from _`livingstone-fjord-theme\src\templates`_ into the _Visual Studio Code_ editor.
* **Click** to highlight the `<#-- Insert snippet 04-footer-nav-sign-in here -->` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `04-footer-nav-sign-in` snippet.
	* Alternatively, you can type the following:

```html
	<#if !is_signed_in>
		<li class="nav-item" role="presentation">
			<a class="nav-link" data-redirect="${is_login_redirect_required?
			string}" href="${sign_in_url}" id="sign-in" rel="nofollow">
				${sign_in_text}
			</a>
		</li>
	</#if>
```

<div class="page"></div>

#### Add the Footer Navigation
1. **Click** to highlight the `<#-- Insert snippet 05-footer-nav-menu here -->` comment.
* **Type** `lfr` to view the available code snippets.
* **Choose** the `05-footer-nav-menu` snippet.
* **Save** the file.
	* Alternatively, you can type and save the following:

```html
<#foreach nav_item in nav_items>
	<li class="nav-item" role="presentation">
		<a class="nav-link" aria-labelledby="layout_${nav_item.getLayoutId()}"
		href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
			${nav_item.getName()}
		</a>
	</li>
</#foreach>
```

#### Deploy the Theme to See the HTML Changes
1. **Run** `npm run deploy` in the _Command Line_ or _Terminal_.
2. **Open** your browser.
	* You should already be at localhost:8080 and logged into your Liferay instance.
* **Click** to _Refresh_ the page after the changes to your theme have finished deploying.

<br />

<img src="images/updated_templates.png" style="max-width: 92%;" />


