# Create the Livingstone Fjord Theme

<!-- <div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Install Visual Studio Code and add code snippets</li>
	<li>Use the NPM Theme Generator to create a new theme</li>
		<ul>
			<li>Set up some initial theme variables</li>
			<li>Modify the HTML source file and add Footer code</li>
	</ul>
	<li>Deploy the Initial Base theme</li>
	</ul>
</div>

## Install Visual Studio Code
1. **Go to** `https://code.visualstudio.com/`.
2. **Click** to download the VS Code for your OS.
3. **Double-click** to run the installer for your operating system.  
4. **Click** through the installation steps.

## Create a Global Snippets File in Visual Studio Code
1. **Open** _Visual Studio Code_.
2. **Go to** the _User Snippets_ menu.
	* Windows: `File → Preferences → User Snippets`
	* OSX: `Gear Icon → User Snippets`
3. **Click** on _New Global Snippets file..._ in the drop-down menu.
4. **Type** _lfr-front-end_ for the _name_.
	* The full file name will be _lfr-front-end.code-snippets_.
5. **Click** _Save_.

## Add the Snippet Content
1. **Open** the `lfr-front-end-snippets.json` in your module exercises folder.
2. **Copy** the contents of the file.   
3. **Paste** the contents of the file in the `lfr-front-end.code-snippets` file just created in Visual Studio Code.
	* Make sure to replace all the existing content in the file.
4. **Save** the file.

## Generate the Base Theme
1. **Go to** the _liferay_ folder you already created in the _Command Line/Terminal_.
	- Windows: _C:\liferay_
	- Mac/Linux: _~/liferay_
2. **Run** the `yo liferay-theme` command.  
	- This command will create the base theme files and install the necessary dependencies for deployment.
3. **Type** _Y_ or _N_ if prompted to collect data.  
4. **Press** _Enter_.  
5. **Type** `Livingstone Fjord Theme` to name your theme.  
6. **Press** _Enter_.  
7. **Press** _Enter_ to accept the default _themeId_.
	- This is how Liferay refers to your theme internally.
8. **Choose** _7.2_ for the version.
9. **Press** _Enter_.
10. **Type** Y to use _Font Awesome_ in the theme.
11. **Press** _Enter_.
12. **Choose** _Local App Server_ when asked to select your deployment strategy.
13. **Type** the directory:
	- Windows: _C:\liferay\bundles\liferay-dxp-[version]\tomcat-[version]_
	- Mac/Unix: _/Users/[username]/liferay/bundles/liferay-dxp-[version]/tomcat-[version]_
14. **Press** _Enter_.
15. **Press** _Enter_ again to use _http://localhost:8080_ for the URL.

<div class="note">
Note: If the Theme Generator returns an error, make sure you are using NPM 10.15.1.
</div>

<img src="../images/liferay-theme-generator-startup.png" style="max-height: 25%">

<div class="note">
Note: Liferay themes created with the theme generator are bundled with Gulp. In order to run Gulp locally from the command line, we must add a script to the package.json file. Additionally, some setups run into issues running scripts with npm. If this is the case, try creating an .npmrc file in the root of your project and add the <code>ignore-scripts=false</code> configuration.
</div>

## Add Gulp Scripts to package.json
1. **Go to** your _`livingstone-fjord-theme`_ folder.
* **Open** your `package.json` file.
* **Add** the following in the "scripts":
```
	"gulp": "node_modules/.bin/gulp",
```
* **Save** the file.

When you're finished, `package.json` should look something like this:

```
{
	"name": "livingstone-fjord-theme",
	"version": "1.0.0",
	"main": "package.json",
	"keywords": [
		"liferay-theme"
	],
	"liferayTheme": {
		"baseTheme": "styled",
		"fontAwesome": true,
		"screenshot": "",
		"templateLanguage": "ftl",
		"version": "7.2"
	},
	"devDependencies": {
		"compass-mixins": "0.12.10",
		"gulp": "3.9.1",
		"liferay-frontend-common-css": "1.0.4",
		"liferay-frontend-theme-styled": "4.0.21",
		"liferay-frontend-theme-unstyled": "4.0.17",
		"liferay-theme-tasks": "^9.5.0",
		"liferay-font-awesome": "3.4.0"
	},
	"scripts": {
		"gulp": "node_modules/.bin/gulp",
		"init": "gulp init",
		"build": "gulp build",
		"deploy": "gulp deploy",
		"extend": "gulp extend",
		"kickstart": "gulp kickstart",
		"status": "gulp status",
		"upgrade": "gulp upgrade",
		"watch": "gulp watch"
	}
}
```

## Deploy the Base Theme to Our Liferay Instance
1. **Go to** _Livingstone Fjord Theme_ using `cd livingstone-fjord-theme` in the _Command Line_.
	- This is assuming you are still running _Command Prompt/Terminal_ from `C:\liferay`.
2. **Run** the `npm run gulp deploy` command.  
	- This makes the base theme available in our Liferay instance.
3. **Open** your browser.  
4. **Sign in** to the _Livingstone Hotels & Resorts_ platform. 
5. **Open** the _Menu._
5. **Go to** _`Site Builder → Pages`_ in _Site Administration_ for the _Livingstone Hotels & Resorts_ Site.  
6. **Open** the _Configuration menu_ next to _Public Pages_.
	* This is the gear icon.
7. **Click** the _Change Current Theme_ button near the bottom.  
8. **Choose** _Livingstone Fjord Theme_.  
9. **Click** the _Save_ button at the bottom.  
10. **Click** _`Go to Site`_ in _Site Administration_ to see your theme.

<br />

<img src="../images/new-theme-no-style.png" style="max-height: 100%">

<br />

## Add the Directory Structure to the SRC Folder
1. **Go to** the _`livingstone-fjord-theme\src`_ folder.  
	- Windows: _`C:\liferay\livingstone-fjord-theme\src`_
	- Mac/Linux: _`~/liferay/livingstone-fjord-theme\src`_
2. **Create** the following folders:
	- _`js`_
	- _`templates`_  

<div class="note">Note: We will be using npm run deploy throughout the course. If you decide to use gulp watch, you will not need to run npm run deploy.</div>

## Set Variables to Start
1. **Copy** the `liferay-look-and-feel.xml` file from the _`WEB-INF`_ folder in your _`exercise-src`_ folder.  
2. **Paste** the file into the _`livingstone-fjord-theme\src\WEB-INF`_ folder.
	* You will need to replace the original file.

## Modify Theme Templates
1. **Copy** the files in the _`exercise-src/templates`_ folder.
2. **Paste** the contents into the _`livingstone-fjord-theme\src\templates`_ folder.
3. **Open** the _Visual Studio Code_ editor.
4. **Drop** the `portal_normal.ftl` file from the _`livingstone-fjord-theme\src\templates`_ file into the _Visual Studio Code_ editor.  

## Modify the Header
1. **Click** to highlight the `<#-- Insert snippet 01-portal-normal-header here -->` comment.
2. **Type** `lfr` to view the available code snippets.
3. **Choose** the `ftl: 01-portal-normal-header` snippet.
	* Alternatively, you can just type in the following:
```FreeMarker
<#if show_header>
		<#include "${full_templates_path}/header.ftl" />
</#if>
```

## Add the Content Section
1. **Click** to highlight the `<#-- Insert snippet 02-portal-normal-main here -->` comment.
2. **Type** `lfr` to view the available code snippets.
3. **Choose** the `ftl: 02-portal-normal-main` snippet.

<br /><br />

## Keep the Footer Code Distinct
1. **Click** to highlight the `<#-- Type footer.ftl include here -->` comment.
2. **Type** in the following to include the footer.ftl file:
```FreeMarker
<#if show_footer>
		<#include "${full_templates_path}/footer.ftl" />
</#if>
```
3. **Save** the file.

## Add Code to the Footer FTL File
1. **Drop** the `footer.ftl` file from _`livingstone-fjord-theme\src\templates`_ into the _Visual Studio Code_ editor.   
2. **Click** to highlight the `<#-- Insert 03-footer-ftl here -->` comment.
3. **Type** `lfr` to view the available code snippets.
4. **Choose** the `03-footer-ftl` snippet.
5. **Save** the file.

## Add a Sign-In Link to the Footer
1. **Drop** the `footer_navigation.ftl` file from _`livingstone-fjord-theme\src\templates`_ into the _Visual Studio Code_ editor.
2. **Click** to highlight the `<#-- Insert snippet 04-footer-nav-sign-in here -->` comment.
3. **Type** `lfr` to view the available code snippets.
4. **Choose** the `04-footer-nav-sign-in` snippet.
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

## Add the Footer Navigation
1. **Click** to highlight the `<#-- Insert snippet 05-footer-nav-menu here -->` comment.
2. **Type** `lfr` to view the available code snippets.
3. **Choose** the `05-footer-nav-menu` snippet.  
	* Alternatively, you can type the following:
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
4. **Save** the file.

## Deploy the Theme to See the HTML Changes
1. **Run** `npm run gulp deploy` in the _Command Line_ or _Terminal_.
	* If you're already running gulp watch, this isn't needed.

<img src="../images/updated-templates.png" style="max-height: 100%"> -->
