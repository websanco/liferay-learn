# Deploying with the NPM Bundle Generator
<!-- 
<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Use the NPM Liferay Bundle generator to generate and deploy a React JavaScript module</li>
    <ul>
        <li>Set up NPM Bundler</li>
        <li>Create a React JavaScript module</li>
        <li>Deploy the module and view it on a page</li>
    </ul>
</div>

## Install the Liferay NPM Bundle Generator

1. **Run** `npm install -g generator-liferay-bundle` to install the Liferay NPM Bundle Generator.  
2. **Run** `yo` in the _Command Line/Terminal_ to see that the generator is installed.  
	* You should now see both the Liferay Bundle and Liferay Theme generators.
3. **Choose** _Get me out of here!_

<img src="../images/both-generators.png" style="max-width: 70%">

## Create a Basic React Module

1. **Go to** your _`liferay`_ folder in your _Command Line/Terminal_.
	* Windows: _C:\liferay_
	* Unix Systems: _[userhome]/liferay_	
2. **Run** `yo liferay-bundle` in your _Command Line/Terminal_.
3. **Choose** _React-based widget_.
4. **Type** _test-project_ for the _project name_.
5. **Press** _Enter_ to accept the default _Project Description_.
6. **Type** _Y_ to accept localization support.
7. **Type** _Y_ to accept configuration support.
8. **Press** _Enter_ to accept the default portlet category.
	* This will add your new react portlet to the _Widget → Sample_ section of the _Add_ menu.
9. **Type** _Y_ to indicate that you have a local installation for development.
10. **Type** the folder path for the liferay bundle.
	* Windows: _C:\liferay\bundles\liferay-dxp-[version]_
	* Unix Systems: _/Users/[username]/liferay/bundles/liferay-dxp-[version]_
11. **Type** _Y_ to generate sample code.

## Deploy the New React Module

1. **Go to** your _`test-project`_ project folder in your _Command Line/Terminal_.
2. **Run** _npm run deploy_ in your _Command Line/Terminal_.
	* You may need to choose whether or not you want to report usage stats to improve the tool.

## Add the New React Module to the Page

1. **Go to** _localhost:8080_ in your browser. 
2. **Sign in** if you are not already signed in.
3. **Open** the _Add_ menu at the top right.
4. **Open** the _Widgets_ drop-down.
5. **Open** the _Sample_ section.
6. **Drag and drop** the _Test Project_ widget to the page.

<img src="../images/react-sample-deploy.png" style="max-width: 80%">

---

## Bonus Exercises

1. Create any of the other JavaScript module types.
2. Create and deploy another module using blade. -->
