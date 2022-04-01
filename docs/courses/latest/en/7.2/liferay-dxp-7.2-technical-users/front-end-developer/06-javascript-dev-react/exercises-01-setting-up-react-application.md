<h3 class="exercise">Exercises</h3>

## Set Up a React Application in Liferay

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
	<li>Use the Liferay npm bundler to create a new React portlet</li>
    <li>Deploy the React portlet to your Liferay instance</li>
</ul>
</div>

#### Start Your Local Liferay Instance
1. **Go to** the Tomcat server's `bin` directory:
	* Windows: _C:\liferay\bundles\liferay-dxp-[version]\tomcat-[version]\bin_ in the file manager
	* Mac/Linux: _[userhome]/liferay/bundles/liferay-dxp-[version]/tomcat-[version]/bin_ using the _Terminal_
2. **Start** the Tomcat Server:
	* Windows: **Double-click** on the `startup.bat`.  
	* Mac/Linux: **Run** `./catalina.sh run` in your _Terminal_.

Once the instance starts up, you can move on to the next steps.

#### Create a New React Portlet
1. **Add** a folder called _modules_ to your _liferay_ directory.
* **Go to** the new _liferay/modules_ directory in Terminal/Command Prompt.
* **Run** `yo liferay-js`.
* **Choose** _React Widget_.
* **Enter** `liferay-react-app` as the name.
* **Enter** `Liferay React App` as the human readable description.
* **Enter** `Y` to the next two prompts.
* **Enter** `React` as the category under which your portlet should be listed.
* **Enter** `Y` to the _Do you have a local installation of Liferay for development_ prompt.
* **Enter** the full path to your liferay/bundles/liferay-[version] folder.
	- For example: `C:\liferay-front-end\bundles\liferay-dxp-7.2.10-ga1`.
* **Enter** `Y` to the _Do you want to generate sample code?_ prompt.

<img src="../images/exercise-images/npm-install-bundle.png" style="max-width: 80%" />

After a minute or so, your bundle should be installed. Next, we are going to make a few changes before deploying the bundle to your Liferay instance.

#### Modify Your React Portlet
1. **Go to** the _liferay-react-app_ folder that was just created.
1. **Open** the `.npmbundlerrc` file with your favorite text editor.
* **Add** the following at the bottom of the file:
	```
	"process-serially": true
	```
	- This should go directly below `"dump-report": true,`
* **Save** the file.

The portlet is now ready to be deployed and added to the Liferay instance.

#### Deploy Your React Portlet
1. **Open** your Terminal/Command Prompt window.
* **Go to** the `liferay-react-app` directory in Terminal/Command Prompt.
* **Run** `npm run deploy`.
	- Wait for the portlet to deploy.
* **Open** your Liferay instance in your browser.
	- If it's not already opened, go to localhost:8080/
* **Sign In** if necessary.
* **Open** the _Add Panel_.
* **Go to** _Widgets → React_.
* **Click** and drag the Liferay React App onto the page.

<img src="../images/exercise-images/react-app-on-page.png" style="max-width: 80%" />

Congratulations! The portlet has been added to the page.

---

#### Bonus Exercises:
1. Explore the file structure of the app we just generated.
* Make changes to files in the src folder and re-deploy the app to see your changes.
* Generate a new React application to create a second portlet.