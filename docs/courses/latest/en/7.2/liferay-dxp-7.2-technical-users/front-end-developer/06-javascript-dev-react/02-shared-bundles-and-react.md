## Shared Bundles and Moving React Applications to Liferay

If you are developing applications that share a lot of dependencies and use the same packages, it makes sense to bundle those dependencies to create lighter applications. Liferay understands this and meets front-end developers needs by providing shared bundle creation via the Liferay NPM Bundler.

Likewise, moving existing React applications to Liferay is also useful to front-end developers. You need to know which files need to be copied over and which new file structure needs to be created to make your application work in Liferay. In this section we will be covering both shared bundles and looking at how to change an existing React application to a Liferay portlet.

#### Livingstone Hotels & Resorts {#Livingstone}

Kaito Tanaka and his team of front-end developers want to minimize the size of their applications for easier deployment. They also want to use their existing React applications in Liferay. Kaito knows he can create a shared bundle with the Liferay Bundle Generator and needs to know what else he needs to do to share dependencies between portlets, which will make the individual portlet file sizes much smaller. He also knows that he can use the Liferay Bundle Generator to create a basic bundle for a React portlet in Liferay, but needs to know what else needs to be added or modified to get his team's React applications working in Liferay.

#### Using Shared Bundles with React Apps {#SharedBundles}

Shared Bundles determine packages used and shared among portlets so that multiple applications with the same dependencies do not need to have the packages they are dependent on built and deployed over and over again.

<div class="key-point">
Key Point: <br />
Shared Bundles can be created with the Liferay Bundle Generator and npm bundler in the same way that a React application can be created.
</div>

<br />

<figure>
	<img src="../images/lecture-images/npm-bundler-shared-packages.png" />
	<figcaption style="font-size: x-small">Fig.1 Using the Liferay Bundle Generator to create a shared bundle.</figcaption>
</figure>

<br/>

In order to use the shared bundle, you will need to copy all relevant (i.e. shared) dependencies from your application(s) `package.json` file to the shared bundle's `package.json`. Any changes made to the `.npmbundlerrc` will also need to be transferred over to the shared bundle as well. In your application(s) `.npmbundlerrc` you will need to exclude the application's dependencies found in the `package.json` with:

```
"exclude": {
    "*": true
}
```

Imports will also need to be added to the `.npmbundlerrc` to pull from the shared bundle and the `polyfill.ts`. When you build and deploy an application using a shared bundle, you will notice that the application is much lighter and deploys faster. We will walk through all of these steps in the next exercise.

#### Using Existing React Apps with Liferay {#ReactApp}

As mentioned in the last section, in order to run an existing React app on Liferay DXP the app should be available as a widget for using on site pages. You must use the Liferay Bundle Generator and Liferay npm Bundler to merge your files into a portlet bundle, adapt your routes and CSS, and deploy your bundle.

<br />

<div class="key-point">
Key Point: <br />
To move a React application to a Liferay portlet, start by generating a bundle with the Liferay Bundle Generator.
</div>

<br />

The next step in moving your React application to Liferay is to copy your app files, matching the types listed below, into your new project.

| File type | Destination | Comments |
| --------- | ----------- | -------- |
| CSS  | `assets/css/` | Overwrite `styles.css`. |
| JavaScript | `src/app/` |  Merge with all files **except** `index.js`---the main module merge is explained later. |
| Static resources | `assets/` | Include resources such as image files here. |

<div class="key-point">
Key Point: <br />
Your React application's CSS, JavaScript, and static resource files will need to be moved to the appropriate folders in your new Liferay bundle.
</div>

Your application will need to be updated to use portlet-level styling. Do this by importing all component CSS files through the main CSS file that the `package.json` file sets for your portlet (default is `styles.css`). Here's what the default setting looks like:

```json
"portlet": {
"com.liferay.portlet.header-portlet-css": "/css/styles.css",
...
}
```

You should also remove any CSS imports that you have in your JS files. 

<br />

<div class="key-point">
Key Point: <br />
When modifying your React application into a Liferay portlet, you need to import all component CSS files through the <code>styles.css</code> file in order to use portlet-level styling.
</div>

<br />

You will also need to update any static resource references to use the web-context value declared in your project's `.npmbundlerrc` file, and remove any imports for the resource. For example, if you have an image file called `logo.png` in your assets folder, you would use the format below:

```
/o/[web-context]/[resource]
```

Here's an example image resource:

```
<img alt="React logo" src="/o/react-guestbook-migrated/logo.png">
```

<div class="note">
Note: The assets folder is not included in the path.
</div>

<br />

<div class="key-point">
Key Point: <br />
Update resource paths based on the web-context value declared in the <code>.npmbundlerrc</code>.
</div>

<br />

In order for your React portlet to work in liferay, you will need to merge your entry module with src/index.js, configuring it to dynamically load components.

<div class="note">
Note: Components must be loaded dynamically to attach to the portlet's DOM. The DOM is determined at run time when the portlet's page is rendered.
</div>

Use the `HashRouter` for routing between component views, since Liferay requires hash routing for proper portal navigation. This should look like this:

```javascript
import { HashRouter as Router } from 'react-router-dom';    
```

Any JavaScript or JSX code you are moving into the Liferay portlet will need to go inside the `main()` function. The app should be rendered inside the `portletElementId` element passed in the `main()` function. This is required to render the React app inside the portlet.

<br />

<div class="key-point">
Key Point: <br />
A variety of changes need to be made to your <code>index.js</code>. The changes should look something like this: 
</div>

<br />

```javascript
import React from 'react';
import ReactDOM from 'react-dom';
//import './index.css';//removed for Portal Migration
import App from './App';
import { HashRouter as Router } from 'react-router-dom';

export default function main({portletNamespace, contextPath, portletElementId}) {
      ReactDOM.render((
        <Router>
          <App/>
        </Router>
      ),
      document.getElementById(portletElementId));
}
```

Lastly, you will need to merge your app `package.json` file's `dependencies` and `devDependencies` into the bundle's `package.json` file before your new React Liferay portlet will be ready to deploy. In two exercises, we will walk through all the changes discussed above to create a working Liferay portlet.

<div class="note">
Note: If you're building on Windows, don't forget to set <code>"process-serially": true</code> in your bundle's <code>.npmbundlerrc</code> file when moving a working React app into Liferay, just as we did in the last section.
</div>

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>Shared Bundles can be created with the _________________________ and _________________________.</li>
    <li>In order to turn your React application into a Liferay portlet, _________________________, _________________________, and _________________________ files need to be moved to the appropriate folders.</li>
    <li>Import all CSS components through the _________________________ file when moving a React application to Liferay.</li>
</ul>
</div>