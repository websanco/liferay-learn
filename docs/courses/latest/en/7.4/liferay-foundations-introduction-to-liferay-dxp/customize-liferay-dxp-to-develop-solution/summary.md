# Summary

## Starting Liferay

* The latest versions of Liferay DXP are available as Docker images. With Docker installed, users can fetch and start Liferay DXP using the Docker image.
   * Read more on Using Liferay Docker Images: https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/using_liferay_docker_images.html

## Building Liferay Projects

* Liferay includes a variety of developer tools, such as Liferay Workspace, Blade CLI, Liferay Theme Generator, Liferay JS Generator, Liferay IntelliJ Plugin, and Liferay Developer Studio.
* Liferay Workspace is a Gradle-based environment that holds projects and their configuration. Liferay Workspace can be used for the full development life cycle: creating, building, deploying, and testing projects.
* Blade CLI is a command line interface used to create and build projects. Blade CLI can also be used to create a Liferay Workspace. Projects created in Blade CLI can also be imported into an IDE.
* Liferay Developer Studio is an integrated development environment (IDE) based on Eclipse. Liferay Developer Studio, which runs on Blade CLI, can also be used to generate a Liferay Workspace .
* The REST Builder is a tool used to create headless REST APIs.
* Additional tools available to developers in Liferay include the npm Bundler, IntelliJ Plugins, Maven Plugins, and Gradle Plugins.

## Fundamentals of Developing Liferay Projects 

* Module projects consist of three things: Code, Build Scripts, and Metadata. Code includes Java classes and resources. Build Scripts are the Gradle files for building and deploying the module. Metadata is the Bnd file that specifies packages and capabilities.
* Configuring Dependencies allows modules to leverage packages from other modules by finding artifacts, specifying dependencies, and resolving third-party library package dependencies.
* Users can add functionality from another module by importing packages from that module into the current module's classpath. This process may need to be completed manually, but some module projects automatically detect and add packages.
* Packages are private by default and must be exported in order to be imported into other modules.
* Semantic Versioning is a three tiered versioning system used to communicate programmatic compatibility of a package or module. The three tiers include: MAJOR, incompatible, API-breaking changes; MINOR, changes that affect only providers of the API; and MICRO, a backwards-compatible bug fix.
* The Gogo Shell provides a way to interact with the module framework. The most secure and safest way to access Gogo shell is through the Control Panel in Liferay DXP.

## Core Frameworks for Application Development 

* The Message Bus API provides a way to exchange messages and is composed of destinations, listeners, the message bus, messages, and senders. Messages can be sent synchronously or asynchronously.
* Dispatch is a flexible framework built on Liferay's Scheduler Engine. Dispatch can be used to add, execute, and schedule tasks containing any custom logic. The essential parts of the framework include: DispatchTaskExecutor, DispatchTrigger, DispatchMessageListener, DispatchTaskExecutorRegistry, DispatchLog, Dispatch Configurator.
* Liferay's configuration framework allows users to add a settings UI for an MVC Portlet. By defining configurable attributes, the configuration framework auto-generates a UI. Configurations can be further customized through categorization and scoping.

## Customizing Site Appearance

* Themes customize the default look and feel of a Site, allowing developers to define the style and visual identity across the entirety of a Site.
* Style Books are sets of visual rules that apply to a Site to provide consistent experiences across its pages including spacing, colors, and fonts
* Style Book token definitions encompass all the options available to a Style Book. They are defined by theme and can be edited to add additional options for further customization.
* Page Fragments are made of CSS, HTML, and JavaScript and form the building blocks of Content Pages. They can be created using the Fragments Editor within the UI or the Fragments Toolkit.

## Liferay as a Headless Delivery Platform

* Liferay provides a suite of APIs in Liferay that are essential for fetching data in a machine-readable format. Liferay's headless APIs provide ways to break collections into manageable pieces to retrieve only the desired data.
* There are three different approaches for clients to connect to Liferay DXP via web API: Headless REST APIs, GraphQL API, and Plain Web/REST Services.
* Headless APIs allow for RESTful interactions with Liferay DXP resources and follow OpenAPI specification.
* The GraphQL API is a query language. The GraphQL API supports interactions similar to headless APIs but with more flexibility.
* Plain Web/REST Services are an older framework closely tied to Liferay DXP's internal working but lacking the power and flexibility of newer headless options.
* OAuth 2.0 is an industry-standard authorization protocol. OAuth 2.0 authorizes password-less access to portions of user-owned resources and other permissioned resources.