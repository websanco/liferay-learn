<h3 class="exercise">Exercises</h3>

## Set Up an Angular Application in Liferay

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Use the Liferay npm bundler to create a new angular portlet</li>
    <li>Deploy the Angular portlet to your Liferay instance</li>
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

#### Create a New Angular Portlet
1. **Add** a folder called _modules_ to your _liferay_ directory.
* **Go to** the new _liferay/modules_ directory in Terminal/Command Prompt.
* **Run** `yo liferay-js`.
* **Choose** _Angular Widget_.
* **Enter** `liferay-angular-app` as the name.
* **Enter** `Liferay Angular App` as the human readable description.
* **Enter** `Y` to the next two prompts.
* **Enter** `Angular` as the category under which your portlet should be listed.
* **Enter** `Y` to the _Do you have a local installation of Liferay for development_ prompt.
* **Enter** the full path to your liferay/bundles/liferay-[version] folder.
	- For example: `C:\liferay-front-end\bundles\liferay-dxp-7.2.10-ga1`.
* **Enter** `Y` to the _Do you want to generate sample code?_ prompt.

<img src="../images/exercise-images/npm-install-bundle.png" style="max-width: 80%" />

After a minute or so, your bundle should be installed. Next, we are going to make a few changes before deploying the bundle to your Liferay instance.

#### Modify Your Angular Portlet
1. **Go to** your new `liferay-angular-app` folder.
2. **Open** the `.npmbundlerrc` file with your favorite text editor.
3. **Add** the following at the bottom of the file:
```
"process-serially": true
```
	- This should go directly below `"dump-report": true,`
4. **Save** the file.

The portlet is now ready to be deployed and added to the Liferay instance.

#### Deploy Your Angular Portlet
1. **Open** your Terminal/Command Prompt window.
* **Go to** the `liferay-angular-app` directory in Terminal/Command Prompt.
* **Run** `npm i` to install/update the dependencies for your portlet.
* **Run** `npm run deploy`.
	- Wait for the portlet to deploy successfully.
4. **Open** your Liferay instance in your browser.
	- If it's not already opened, go to localhost:8080/
* **Sign In** if necessary.
* **Open** the _Add Panel_.
* **Go to** _Widgets → Angular_.
* **Click** and drag the Liferay Angular App onto the page.

<img src="../images/exercise-images/angular-app-on-page.png" style="max-width: 80%" />

Congratulations! The portlet has been added to the page.

---

#### Bonus Exercises:
1. Explore the file structure of the app we just generated.
* Make changes to files in the src folder and re-deploy the app to see your changes.
* Generate a new Angular application to create a second portlet.
