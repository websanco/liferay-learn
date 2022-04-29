# Front-End Development Tools in Liferay
<!-- 
Front-end developers need to be familiar with efficient development tools in order to implement business and design requirements. They also need to use tools that can work within a DevOps process. There are several tools available to developers working on the Liferay platform that allow them to do just that. 

## The Livingstone Team Toolset {#livingstonetoolset}

Kaito Tanaka and his team of front-end developers need to create the following for the new Livingstone sites:
* New Themes for the different Livingstone Hotel and Resort sites
* Customized Layouts for specific widget pages
* Different Templates to control the content and widget presentation 
* Editable fragments for the page presentation on their sites
* React applications for Salesforce integration as well as other JavaScript components

<figure>
	<img src="../images/react-example.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.1 React Code Example</figcaption>
</figure>

<br/>

## Understanding Tools for Liferay DXP {#tools}

It's important to note that Liferay is tool-agnostic. Anything from a command prompt, text editor, or a full-blown IDE can be used to develop. Liferay DXP has introduced a number of new tools the team can take advantage of to simplify front-end module development. Developers can take advantage of Eclipse and IntelliJ IDEA IDE plugins, Yeoman generators within an npm workflow, or Liferay's Blade CLI.

Using these tools can give developers the edge by taking less time to get things set up and more time developing.

<div class="key-point">
Key Point: <br />
<li>Front-end developers can create theme, layout, and JavaScript modules using the following:</li>
  <ul>
      <li>
        <b>Liferay NPM Toolkit</b>: This includes yeoman generators to build and deploy themes, layouts, fragments, and JavaScript modules. 
      </li>
      <li>
        <b>Blade CLI and Liferay Workspace</b>: Blade includes a number of project templates that can be used in a standalone environment or with a Liferay Workspace.
      </li>
      <li>
        <b>Liferay Developer Studio</b>: This is a standalone package built on Eclipse that includes the Liferay IDE plugins. Alternatively, developers can simply add the Liferay IDE plugins to their existing eclipse IDE.
      </li>
      <li>
        <b>Liferay IntelliJ Plugin</b>: This plugin can be integrated with IntelliJ IDEA and allows developers to create Gradle and Maven-based projects.
      </li>
    </ul>
</div>

## Developing with IntelliJ IDEA and Eclipse {#ide}

Developers can use Liferay Developer Plugins with Eclipse or IntelliJ IDEA to develop different kinds of modules for the platform. These plugins give developers the tools to do the following:
* Create a Liferay Workspace (Gradle and Maven based)
* Create Liferay projects (Gradle and Maven based)
* Set up a Liferay Tomcat server for project deployment and debugging
* Add line markers for each entity in the service editor
* Syntax checking, highlighting, and code completion (e.g., Bnd and XML files)

These plugins can be installed through the Eclipse and IntelliJ marketplace. Alternatively, Liferay Developer Studio, which includes a pre-packaged eclipse set-up, is available for download from the Help Center.

<figure>
	<img src="../images/intellij-marketplace-installation.png" style="max-height: 26%" />
	<figcaption style="font-size: x-small">Fig.2 IntelliJ Marketplace Example</figcaption>
</figure>

<div class="note">
  Note: For more information on this set-up, you can check the following documentation:
  <ul>
    <li>Liferay IDE: <a href="https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/installing-liferay-ide">https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/installing-liferay-ide</a> 
    </li>
    <li>Liferay IntelliJ Plugin: <a href="https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/installing-the-liferay-intellij-plugin">https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/installing-the-liferay-intellij-plugin</a>
    </li>
  </ul>
</div>

## Using the Blade CLI's Theme Template {#bladecli}

Developers can also use the Blade CLI tool to build Liferay module projects. This tool can be used to create projects with a Liferay Workspace or in a standalone environment. Blade CLI is a command line tool bootstrapped to a Gradle-based environment that allows developers to do the following:
* Create projects (Gradle or Maven) that can be used with any IDE or development environment
* Create/manage Liferay Portal instances
* Deploy modules (Gradle or Maven)

For Liferay DXP 7.2, this toolset can be used to create and deploy theme and layout template modules, but does not support JavaScript module development.

<figure>
	<img src="../images/blade-installer-workspace-init.png" style="max-height: 35%" />
	<figcaption style="font-size: x-small">Fig.3 Blade CLI Set-up</figcaption>
</figure>

<div class="note">
  Note: For more information on this set-up, you can check the following documentation.
  <ul>
    <li>Blade CLI: <a href="https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/blade-cli">https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/blade-cli</a> 
    </li>
  </ul>
</div>

## Using the Liferay NPM Toolkit {#npmtoolkit}

Finally, developers can use the Liferay NPM toolkit, which includes the following project generators:
* **Liferay Theme Generator**: used to create theme and layout template projects
* **Liferay Bundle Generator**: used to create JavaScript portlet projects
* **Liferay Fragments Generator**: used to create page fragments that can be deployed to the platform

These three generators give front-end developers the ability to rapidly create and deploy new projects.

<div class="key-point">
Key Point: <br />
<li>The Liferay Theme, Bundle, and Fragment Generators are built with the following:</li>
  <ul>
      <li>
        <b>
         Node.js
        </b>:
        the JavaScript Runtime Environment that provides the platform for building and testing our modules. Node.js is accessed via <em>NPM</em>, the Node.js package manager.
      </li>
      <li>
        <b>
         Yeoman
        </b>: a powerful tool that gives us the ability to rapidly create new projects using generators. This tool lets us build from an existing template by running a simple command.
      </li>
      <li>
        <b>
          Gulp tasks
        </b>: gives us a number of commands we can use to build, package, and deploy our modules 
      </li>
    </ul>
</div>

<figure>
	<img src="../images/liferay-bundle-projects.png" style="max-height: 100%" />
	<figcaption style="font-size: x-small">Fig.4 Creating JavaScript Projects</figcaption>
</figure>

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Developers can create projects using IDE plugins for the following IDEs:</li>
    <ul>
      <li>____________________</li> 
      <li>____________________</li> 
    </ul>
  <li>____________________ CLI can be used as a stand-alone toolset or with Liferay Workspace.</li>
  <li>The Liferay NPM Toolkit includes the following generators:</li>
    <ul>
      <li>____________________: used to create theme and layout template projects</li>
      <li>____________________: used to create JavaScript portlet projects</li>
      <li>____________________: used to create page fragments that can be deployed to the platform</li>
    </ul>
</ul>
</div> -->
