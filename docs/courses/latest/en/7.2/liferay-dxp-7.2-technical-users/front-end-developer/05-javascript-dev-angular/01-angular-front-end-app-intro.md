## Introduction to Angular App Development

Front-end development is becoming more and more versatile. Front-end developers are building full-scale applications with JavaScript and other front-end frameworks, like Angular. Liferay understands this and meets front-end developers needs by providing easy front-end application development via tools like the Liferay NPM Bundler. In this section we will learn the basics of Angular and how to create a basic application using the NPM Bundler.

#### Livingstone's Requirements {#Livingstone}

Kaito Tanaka and his team of front-end developers have been tasked with creating a portlet to help Josiah Copeland with his administrative duties. They have decided that they want to use Angular because of its readability and quick development time. They need to be able to easily start from scratch and build an application that has all the required dependencies and files to work in Liferay. They also need an easy process to build and deploy the application for testing during development. The Liferay NPM Bundler provides Livingstone's front-end team with the perfect solution to meet all of their needs.

#### Angular and Liferay {#Angular}

Angular is a complete rewrite of AngularJS. It is a typescript-based framework created by Google. Creating an app with Angular is straightforward. Normally, it requires Node.js and the NPM package manager to provide the features and functionalities provided by the appropriate libraries to get your Angular app working properly.

<div class="key-point">
Key Point: <br />
Angular is a typescript-based front-end framework that requires Node.js and the NPM package manager to work. 
</div>

<br />

<figure>
	<img src="../images/lecture-images/angular-tools.png" style="max-width: 70%;" />
	<figcaption style="font-size: x-small">Fig.1 Node and npm are used to create Angular applications.</figcaption>
</figure>

<br />

Typically, the Angular CLI is used to create projects, generate application and library code, and perform a variety of development tasks like testing, bundling, and deploying. However, when creating Angular applications to be used in Liferay, you must use the Liferay Bundle Generator and Liferay npm Bundler to merge your files into a portlet bundle, adapt your routes and CSS, and deploy your bundle.

#### Liferay NPM Bundler {#NPM}

To use an existing Angular app on Liferay DXP or Liferay CE, the app must be made available as a widget. As mentioned above, you must use the Liferay Bundle Generator and Liferay npm Bundler in order to do this. This is done in two basic steps:

1. Install the Liferay Bundle Generator with the `npm install -g yo generator-liferay-bundle`.
2. Generate the files necessary to create an Angular portlet using `yo liferay-bundle`.

<div class="note">
Note: Creating an Angular bundle is similar to creating a theme using the Liferay Theme Generator. Follow the steps and Yeoman will create all the necessary files for you.
</div>

<div class="key-point">
Key Point: <br />
Before an Angular application can be used on a Liferay page, the Liferay Bundle Generator must be installed and the Liferay npm Bundler must be used to generate the necessary files for an Angular portlet.
</div>

The basic structure of an Angular bundle generated with the Liferay Bundle Generator:

* `[my-angular-portlet-bundle]`
	- `assets/` → CSS, HTML templates, and resources 
		* `css/` → CSS files 
			- `styles.css` → Default CSS file
		* `app/` → HTML templates 
			- `app.component.html` → Root component template
		* `features/` → @product@ bundle features 
			- `localization/` → Resource bundles 
				* `Language.properties` → Default language keys
		* `src/` → JavaScript an TypeScript files 
			- `app/` → Application modules and Components 
				* `app.component.ts` → Main component
				* `app.module.ts` → Root module
				* `dynamic.loader.ts` → Loads an Angular component dynamically for the portlet to attach to
		* `types/` 
			- `LiferayParams.ts` → Parameters passed by Liferay to the JavaScript module
		* `index.ts` → Main module invoked by the "bootstrap" module to initialize the portlet
		* `polyfills.ts` → Fills in browser JavaScript implementation gaps
	* `package.json` → npm bundle configuration
	* `README.md`
	* `.npmbuildrc` → Build configuration
	* `.npmbundlerrc` → Bundler configuration
	* `tsconfig.json` → TypeScript configuration

<div class="note">
Note: Those building on Windows will have to set <code>"process-serially": true</code> in the <code>.npmbundlerrc</code> file.
</div>

While different than building Angular apps with the traditional tools, using Liferay has a few benefits:

* You can build several modular Angular applications instead of one monolithic application
* Page management, permissions, and authentication are built-in with Liferay DXP
* Using Angular with Liferay allows you to integrate non-angular features, like tags and documents, into you application

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>_________________________ is a typescript-based front-end framework that requires Node.js and the NPM package manager to work.</li>
  <li>There are _________________________ basics steps for creating a new Angular application that can be used in Liferay:</li>
  <ul>
  	<li>_________________________</li>
  	<li>_________________________</li>
  </ul>
</ul>
</div>