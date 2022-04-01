## Introduction to React App Development

Front-end development is becoming more and more versatile. Front-end developers are building full-scale applications with JavaScript and other front-end frameworks, like React. Liferay understands this and meets front-end developers needs by providing easy front-end application development via tools like the Liferay NPM Bundler. In this section we will learn the basics of React and how to create a basic application using the NPM Bundler.

#### Livingstone's Requirements {#Livingstone}

Kaito Tanaka and his team of front-end developers have been tasked with creating a portlet to help Josiah Copeland with his administrative duties. They have decided that they want to use React because of the ease of creating interactive UIs when developing in React. They need to be able to easily start from scratch and build an application that has all the required dependencies and files to work in Liferay. They also need an easy process to build and deploy the application for testing during development. The Liferay NPM Bundler provides Livingstone's front-end team with the perfect solution to meet all of their needs.

#### React and Liferay {#React}

React is a JavaScript library for building UIs developed by Facebook. Creating an app with React is straightforward and efficient with React's use of the Virtual DOM. Instead of rendering the entire page for each change made, the React library only renders the subcomponents that actually change by creating an in-memory data-structure cache and computing the resulting differences.

<div class="key-point">
Key Point: <br />
React is a JavaScript library and uses JSX and the Virtual DOM for efficient development and rendering. 
</div>

<br />

<figure>
	<img src="../images/lecture-images/react.png" style="max-width: 70%;" />
	<figcaption style="font-size: x-small">Fig.1 The React logo.</figcaption>
</figure>

<br />

JSX example:

```JSX
class App extends React.Component {
  render() {
    return (
      <div>
        <p>Header</p>
        <p>Content</p>
        <p>Footer</p>
      </div>
    ); 
  } 
}
```

Typically, `npx create-react-app`, a package runner tool of npm 5.2+, is used to create a new single-page application with React. However, when creating React applications to be used in Liferay, you must use the Liferay Bundle Generator and Liferay npm Bundler to merge your files into a portlet bundle, adapt your routes and CSS, and deploy your bundle.

#### Liferay NPM Bundler {#NPM}

To use an existing React app on Liferay DXP or Liferay CE, the app must be made available as a widget. As mentioned above, you must use the Liferay Bundle Generator and Liferay npm Bundler in order to do this. This is done in two basic steps:

1. Install the Liferay Bundle Generator with the `npm install -g yo generator-liferay-bundle`.
2. Generate the files necessary to create a React portlet using `yo liferay-bundle`.

<div class="note">
Note: Creating a React bundle is similar to creating a theme using the Liferay Theme Generator. Follow the steps and Yeoman will create all the necessary files for you.
</div>

<div class="key-point">
Key Point: <br />
Before a React application can be used on a Liferay page, the Liferay Bundle Generator must be installed and the Liferay npm Bundler must be used to generate the necessary files for a React portlet.
</div>

The basic structure of a React bundle generated with the Liferay Bundle Generator:

* `[my-react-portlet-bundle]`
	* `assets/` → CSS and resources files
		* `css/` → CSS files 
			- `styles.css` → Default CSS file
	* `features/` → @product@ bundle features 
		- `localization/` → Resource bundles 
			* `Language.properties` → Default language keys
	* `src/` → JavaScript an TypeScript files 
		- `App.Component.js` → Sample React component that you can remove
		- `index.js` → Main module used to initialize the portlet
	* `.babelrc` → Babel configuration
	* `.npmbuildrc` → Build configuration
	* `.npmbundlerrc` → Bundler configuration
	* `package.json` → npm bundle configuration
	* `README.md`

<div class="note">
Note: Those building on Windows will have to set <code>"process-serially": true</code> in the <code>.npmbundlerrc</code> file.
</div>

While different than building React apps with the traditional tools, using Liferay has a few benefits:

* You can build several modular React applications instead of one monolithic application
* Page management, permissions, and authentication are built-in with Liferay DXP
* Using React with Liferay allows you to integrate non-React features, like tags and documents, into you application

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>_________________________ is a JavaScript library and uses JSX and the Virtual DOM for efficient development and rendering.</li>
  <li>There are _________________________ basics steps for creating a new React application that can be used in Liferay:</li>
  <ul>
  	<li>_________________________</li>
  	<li>_________________________</li>
  </ul>
</ul>
</div>



