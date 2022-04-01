<h3 class="exercise">Exercises</h3>

## Creating A Real World Application with Angular

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Take a look at a Salesforce portlet created with Angular</li>
    <li>Deploy the Salesforce Demo Application</li>
</ul>
</div>

#### Copy the Repository Source

1. **Copy** the `salesforce-angular.zip` file from the exercise folder to your _liferay_ folder. 
2. **Extract** the contents of the folder.
3. **Navigate** into the _salesforce-angular_ folder.

Here you'll find a repository containing a complete Salesforce application created with Angular. This is a gradle based project that takes advantage of Liferay's Javascript tooling. Let's take a look at some of the files.

#### Examing the Repo
1. **Open** the modules folder.
	* You'll see the Angular npm provider, a folder with code for the Salesforce app modules and a folder with testing code.
2. **Navigate** to the _salesforce_ folder.
	* Here you'll find the module components that make up the Salesforce portlet.
3. **Navigate** to _salesforce-web/src/main/resources/META-INF/resources_
	* Here we find the code for the web module component. 
4. **Click** into the _js_ folder.
	* This should look familiar to you as it is a similar structure to the code generated when using the `yo liferay-js` generator.

You can find the code for the application in the _app_ folder.

#### Generate and Deploy the Salesforce Application

1. **Navigate** back up to the top level of the _salesforce-angular_ folder (_liferay/salesforce-angular_)
2. **Run** the following command to generate deployable modules:
	* Windows: `gradlew assemble`
	* Unix: `./gradlew assemble`
	NOTE: These commands download and set up an npm enviroment and download all the required npm modules. Depending on your system and internet connection this may take a long time to run. You can find the pre-generated modules that are ready to deploy in the _angular-modules.zip_ file provided in the exercise folder. 
3. **Find** the generated modules (either in the _zip_ file provided in the exercise folder or in _salesforce/[module-name]/build/libs_).
4. **Copy** the _jar_ files and paste them into the _deploy_ folder of your running Liferay instance.

#### Add the Salesforce Portlet to the Page

1. **Go to** `localhost:8080` in your browser and sign-in to Liferay as needed.
2. **Click** on the _Add_ button on your main site page in Liferay.
3. **Expand** the _Salesforce POC_ section, under _Widgets_.
4. **Add** the _Salesforce_ portlet to the page.
5. **Click** on _Options -> Configuration_ for the portlet.
6. **Choose** _Accounts_ from the entity dropdown menu.
7. **Click** _Save_.
7. **Close** the pop-up.

Congratulations! You've just built and deployed the Salesforce app.

<img src="../images/exercise-images/salesforce-account.png" style="min-width: 60%" />

---

#### Bonus Exercises:
1. Create your own Angular portlet and deploy it to the Liferay instance.
