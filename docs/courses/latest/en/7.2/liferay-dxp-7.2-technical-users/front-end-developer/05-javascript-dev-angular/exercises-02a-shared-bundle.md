<h3 class="exercise">Exercises</h3>

## Creating a Shared Bundle

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Create a shared package bundle that will share dependencies with the Liferay Angular App.</li>
    <li>Deploy the updated Angular App and view it in a Liferay Instance.</li>
</ul>
</div>

#### Create Shared Packages Bundle
1. **Open** a Terminal/Command Prompt window in your _liferay/modules_ directory.
* **Run** `yo liferay-js`.
* **Choose** `Shared Bundle`.
* **Enter** `angular-npm-provider` as the name.
* **Enter** `Angular NPM Provider` as the human readable description.
* **Enter** `Y` to the next two prompts.
* **Enter** the installation path of your Liferay instance.
* **Enter** `Y` when asked if your export bundle needs an initializer.

<img src="../images/exercise-images/npm-shared-bundle.png" style="max-width: 80%" />

Now you have a second angular project that we will use to work in conjunction with the Angular App created in the last exercise. The first thing we need to do in this shared bundle is to modify the .npmbundlerrc and package.json files.

#### Update the .npmbundlerrc and package.json Files in the Shared Bundle
1. **Go to** the new _angular-npm-provider_ directory.
* **Open** the `.npmbundlerrc` file with your favorite text editor.
* **Add** `"process-serially": "true"` beneath `"dump-report": true,`.
* **Save** and close the file.
* **Open** the `package.json` file for the new Shared Bundle with your favorite text editor.
	- We will be copying dependencies over from the Liferay Angular App to the Shared Bundle.
* **Open** the `package.json` file from your Liferay Angular App (_liferay-angular-app_) with your favorite text editor.
* **Copy** the dependencies from your Liferay Angular App.
* **Paste** the dependencies into the shared bundle's `package.json` file.
* **Save** and close both files.

<img src="../images/exercise-images/shared-dependencies.png" style="max-width: 80%" />

Next we need to run npm install to add the dependencies to the Angular NPM Provider and to add some code in index.js to let us know when we have successfully connected the Angular NPM Provider with the Liferay Angular App (or anything else we share it with).

#### Install and Deploy the Shared Bundle
1. **Open** your Terminal/Command Prompt window in the Angular NPM Provider's root directory.
* **Run** `npm install`.
	- This will add all the packages the Bundle needs
* **Go to** the Angular NPM Provider's `src` folder.
* **Open** the `index.js` file with your favorite text editor.
* **Add** `console.log('It worked! Bootstrapped angular-npm-provider');` at the bottom of the file.
	- This is after the init() function.
* **Save** and close the file.
* **Open** your Terminal/Command Prompt window.
	- You should still be in the Angular NPM Provider's root directory.
* **Run** `npm run deploy`.

Now that the Angular NPM Provider has been deployed, let's update the Angular App to use the dependencies we defined in the NPM Provider.

#### Update the Angular App
1. **Go to** the root folder for the Liferay Angular App.
* **Open** the `.npmbundlerrc` file with your favorite text editor.
* **Replace** `"process-serially": true` with the following:
```
"exclude": {
	    "*": true
	},
	"config": {
	    "imports": {
	        "angular-npm-provider": {
	            "@angular/animations": "^6.0.3",
	            "@angular/common": "^6.0.3",
	            "@angular/compiler": "^6.0.3",
	            "@angular/core": "^6.0.3",
	            "@angular/forms": "^6.0.3",
	            "@angular/http": "^6.0.3",
	            "@angular/platform-browser": "^6.0.3",
	            "@angular/platform-browser-dynamic": "^6.0.3",
	            "@angular/router": "^6.0.3",
	            "core-js": "^2.5.4",
	            "rxjs": "~6.0.0",
	            "zone.js": "^0.8.26"
	        },
	        "": {
	             "angular-npm-provider": "^1.0.0"
	        }
	    }
	}
```
	- In the `"exclude"` we add a wildcard that tells the npm bundler to not include any dependencies listed in the Liferay Angular App's package.json.
	- In the `"imports"` we add the dependencies that need to be pulled from the provider.
	- The Angular NPM Provider is imported with no namespace because we need to access it from index.js
4. **Save** and close the file.
* **Go to** the `src` folder in the Liferay Angular App.
* **Open** the `polyfill.ts` file in your favorite text editor.
* **Add** `import 'angular-npm-provider';` below the other two imports.
* **Save** and close the file.
* **Go to** the root folder of the Liferay Angular App.
* **Delete** the `build` folder.

Now that we've updated the Liferay Angular App we created in the last exercise by sharing dependencies with the Angular NPM Provider, we can deploy the Liferay Angular App and replace the old one with the new one in our Liferay instance.

#### Deploy the Liferay Angular App
1. **Open** your Terminal/Command Prompt window in the Liferay Angular App root directory.
* **Run** `npm run deploy`.
	- The App should deploy much more quickly then our previous deploys.
* **Go to** your Liferay instance in your browser.
* **Sign In** if you were logged out previously.
* **Click** the _Options_ icon at the top of the Liferay Angular App portlet on the page.
* **Choose** _Remove_ from the drop-down.
* **Open** the _Add panel_.
* **Go to** _Widgets → Angular_.
* **Add** the _Liferay Angular App_.
* **Open** the browser's console.
	- You should see the message we wrote in the Angular NPM Provider's index.js file.

<img src="../images/exercise-images/console-message.png" style="max-width: 80%" />

Congratulations! You have successfully shared packages across two different liferay bundles.

---

#### Bonus Exercises:
1. Create your own Console message and re-deploy both projects to see the results.
* Create a simple Angular application using the typescript files in the Liferay Angular App's src folder.
