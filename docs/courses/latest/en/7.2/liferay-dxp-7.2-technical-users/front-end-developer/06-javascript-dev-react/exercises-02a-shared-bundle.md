<h3 class="exercise">Exercises</h3>

## Creating a Shared Bundle

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
	<li>Create a shared package bundle that will share dependencies with the Liferay React App.</li>
    <li>Deploy the updated React App and view it in a Liferay Instance.</li>
</ul>
</div>

#### Create Shared Packages Bundle
1. **Open** a Terminal/Command Prompt window in your _liferay/modules_ directory.
* **Run** `yo liferay-js`.
* **Choose** `Shared Bundle`.
* **Enter** `react-npm-provider` as the name.
* **Enter** `React NPM Provider` as the human readable description.
* **Enter** `Y` to the next two prompts.
* **Enter** the installation path of your Liferay instance.
* **Enter** `Y` when asked if your export bundle needs an initializer.

<img src="../images/exercise-images/npm-install-bundle.png" style="max-width: 80%" />

Now you have a second React project that we will use to work in conjunction with the React App created in the last exercise. The first thing we need to do in this shared bundle is to modify the .npmbundlerrc and package.json files.

#### Update the .npmbundlerrc and package.json Files in the Shared Bundle
1. **Go to** the new _react-npm-provider_ directory.
* **Open** the `.npmbundlerrc` file with your favorite text editor.
* **Add** `"process-serially": "true"` beneath `"dump-report": true,`.
* **Save** and close the file.
* **Open** the `package.json` file with your favorite text editor.
* **Open** the `package.json` file from your Liferay React App with your favorite text editor.
* **Copy** the dependencies from your Liferay React App.
* **Paste** the dependencies into the shared bundle's `package.json` file.
* **Save** and close both files.

<img src="../images/exercise-images/shared-dependencies.png" style="max-width: 80%" />

Next we need to run npm install to add the dependencies to the React NPM Provider and to add some code in index.js to let us know when we have successfully connected the React NPM Provider with the Liferay React App (or anything else we share it with).

#### Install and Deploy the Shared Bundle
1. **Open** your Terminal/Command Prompt window in the React NPM Provider's root directory.
* **Run** `npm install`.
	- This will add all the packages the Bundle needs
* **Go to** the React NPM Provider's `src` folder.
* **Open** the `index.js` file with your favorite text editor.
* **Add** `console.log('It worked! Bootstrapped react-npm-provider');` at the bottom of the file.
	- This is after the init() function.
* **Save** and close the file.
* **Open** your Terminal/Command Prompt window.
	- You should still be in the React NPM Provider's root directory.
* **Run** `npm run deploy`.

Now that the React NPM Provider has been deployed, let's update the React App to use the dependencies we defined in the NPM Provider.

#### Update the React App
1. **Go to** the root folder for the Liferay React App.
* **Open** the `.npmbundlerrc` file with your favorite text editor.
* **Replace** `"process-serially": true` with the following:
```
"exclude": {
	    "*": true
	},
	"config": {
	    "imports": {
	        "react-npm-provider": {
	            "react": "^16.0.0",
				"react-dom": "^16.0.0"
	        },
	        "": {
	             "react-npm-provider": "^1.0.0"
	        }
	    }
	}
```
	- In the `"exclude"` we add a wildcard that tells the npm bundler to not include any dependencies listed in the Liferay React App's package.json.
	- In the `"imports"` we add the dependencies that need to be pulled from the provider.
	- The React NPM Provider is imported with no namespace because we need to access it from index.js
4. **Save** and close the file.
* **Go to** the `src` folder in the Liferay React App.
* **Open** the `AppComponent.js` file in your favorite text editor.
* **Add** `import 'react-npm-provider';` below the other two imports.
* **Save** and close the file.
* **Go to** the root folder of the Liferay React App.
* **Delete** the `build` folder.

Now that we've updated the Liferay React App we created in the last exercise by sharing dependencies with the React NPM Provider, we can deploy the Liferay React App and replace the old one with the new one in our Liferay instance.

#### Deploy the Liferay React App
1. **Open** your Terminal/Command Prompt window in the Liferay React App root directory.
* **Run** `npm run deploy`.
	- The App should deploy much more quickly then our previous deploys.
* **Go to** your Liferay instance in your browser.
* **Sign In** if you were logged out previously.
* **Click** the _Options_ icon at the top of the Liferay React App portlet on the page.
* **Choose** _Remove_ from the drop-down.
* **Open** the _Add panel_.
* **Go to** _Widgets → React_.
* **Add** the _Liferay React App_.
* **Open** the browser's console.
	- You should see the message we wrote in the React NPM Provider's index.js file.

<img src="../images/exercise-images/console-message.png" style="max-width: 80%" />

Congratulations! You have successfully shared packages across two different liferay bundles. In the next exercise we will add to these two projects to create a routing demo app.

---

#### Bonus Exercises:
1. Create your own Console message and re-deploy both projects to see the results.
* Create a simple React application using the index.js in the Liferay React App's src folder.