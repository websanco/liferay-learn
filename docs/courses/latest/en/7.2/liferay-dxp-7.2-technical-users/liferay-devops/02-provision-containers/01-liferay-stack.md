## Setting Up A Liferay Solution

The goal of this module is to prepare you to use Liferay in a production environment. We'll begin by talking about the basics of a Liferay deployment and looking at examples of typical setups.

### The Liferay Stack {#stack}

Liferay is a web application and needs a "stack" of software to function. The first thing you'll need to run any web application is a server. The server can be either a simple _Web Container_ or a _Full Profile Java EE Application Server_.

Tomcat, Wildfly, Websphere, and Weblogic are all examples of popular application servers. You also need a database that will store all of the application data. In some cases, there's a unique database for a specific application; in other cases, multiple applications share a database. MySQL, Oracle Database, PostgreSQL, and DB2 are some of the more popular databases.

Liferay provides many features that may be customized using external services. In many production cases, you might want to use a service that is different from what is bundled with Liferay or offload that work to another server. With Liferay you can:

- Connect to an existing Active Directory service like LDAP
- Use an external document repository, like Documentum or Alfresco
- Handle indexing and search externally with a service like Elasticsearch or Solr

<div class="key-point">
	Key Point: <br>
	Liferay is platform-agnostic. As long as you can provide each piece of the stack, you can use a myriad of combinations to run Liferay.
</div>

<img src="../images/liferay-container-portal-stack.png" style="max-width: 42%" />

<br />
<br />

Let's break down the term "server", since it can be used in a few different ways. For one, we're not talking about the physical hardware; we're talking about the software that it runs.

<div class="key-point">
	Key Point: <br>
	In general, when we say "server", we mean a container that can be used to run a web application.
</div>

In some cases, this might be a **Web Container** (also known as a _Servlet Container_), which provides only the necessary components for deploying applications. We might also be talking about a **Full Profile Application Server**, which includes the full complement of Java EE technologies, including JPA, EJBs, as well as everything provided in the Web Container.

### Web Containers {#webcontainers}

A Web Container is going to be your most lightweight option for deploying Liferay. Web Containers implement only the web component contract of the Java EE architecture. They are primarily responsible for managing the lifecycle of servlets and mapping URLs to servlets so that they can be accessed by users. The container handles requests to servlets and files like JSPs. It also creates servlet instances, manages requests, and performs other tasks.

Liferay DXP can be run on web containers like Apache Tomcat.

<img src="../images/liferay-container-web-container.png" style="max-width: 100%" />

### Full Profile Application Server {#fullprofile}

A **Full Profile Application Server** is a server that implements the entirety of the Java EE architecture. It includes all of the Web Container, in addition to a number of other features like EJB,JAX Web Services, JavaMail, and many others. Some Web Containers may contain additional features beyond the minimum functionality, but they are only Full Profile Application Servers if they contain the entire spec.

Examples of Full Profile Application Servers are Weblogic, WebSphere and JBoss EAP.

<img src="../images/liferay-container-EE-web-server.png" style="max-width: 100%" />

<div class="key-point">
	Key Point: <br>
	Whether a Web Container or a Full Profile Web Server, part of the job of the application server is to provide the necessary libraries an application needs in order to run.
</div>

Liferay provides a _dependencies package_ that adds Liferay-specific libraries to your server so Liferay can be deployed. It contains:

- `hsql.jar:` Provides support for HSQL, which Liferay uses as a default database for testing and demonstration purposes
- `portal-kernel.jar:` Contains the core Liferay services
- `portlet.jar:` Provides support for portlets, which Liferay uses as a primary UI element
- `com.liferay.petra.*:` OSGi friendly modules providing utilities once found in `com.liferay.util.java`

### The Database {#database}

The database is responsible for storing the majority of your data. It stores everything from permissions configuration to user data, web content, and more. Liferay supports a large number of databases including:

- DB2
- MySQL
- MariaDB
- PostgreSQL
- Oracle Database
- SQL Server

<img src="../images/database.png" style="max-width:10%" />

What does all of this look like in practice? Setting up a simple stack would looking something like this:

1. Install Liferay on an Apache Tomcat Web Container with the necessary dependencies.
2. Connect Liferay to a MySQL database.
3. Provide configurations to connect to external Elasticsearch, a Documentum Repository, and LDAP services running on external application servers.

<div class="note">
    Note: For a full list of databases and other software and their versions supported by Liferay, you can find our compatibility matrix at https://liferay.com/services/support/compatibility-matrix
</div>

By the end of this course, we'll use Docker containers to have built something similar to a production stack:

<img src="../images/liferay-container-big-picture.png" style="max-height:40%" />

### Securing Your Instance {#secure}

Running Tomcat as a container makes it very easy to start and stop the server. Once the server is up and running we will want to find a way to secure the server.

What we need is a solution that:

- Separates the Liferay instance from the outside world while still allowing traffic through
- Allows for efficient serving of static content
- Contains efficient ways to compress, rewrite, and forward requests
- Gives us flexibility, in return for the additional effort of setting it up

<br />

<div class="key-point">
	Key Point: <br>
	By running a reverse proxy like HAProxy, we can separate our Java server from the outside world but still get the behavior we need.
</div>

On the backend, we leave our Tomcat instance:

- Running on a restricted User account
- Listening only on port `8080` or another unprivileged (>1024) port

On the frontend, we can set up HAProxy to:

- Listen on port `80` and accept all web traffic
- Forward requests to our Tomcat server
- Route responses from our Tomcat server back to the client
- Automatically compress, strip, or otherwise transform the response data
- Run on its own user account to prevent system-wide security breaches
- Terminate TLS (but not in this training)

It's the best of both worlds for very little overhead.

<!--
<div class="trainersguide" style="display:none;">
    Additional talking points, if trainer is familiar with them: Aspects of mod_proxy_http: Forwarding information about the request. Options for encryption of the Apache httpd->tomcat traffic.
</div>
-->


<div class="trainersguide" style="display:none;">
    Additional talking points for summary:

    * remind them of the fatality when running as root (when attacked)
    * Successful attacks don't necessarily mean that Liferay has a security issue. It can be template authors or third party components
    * Instead of fully-featured application server, a loadbalancer or reverse proxy does the same job
</div>

Let's get started by setting up our test environment. We'll take a look at a simple Tomcat setup to start.

<div class="summary"><h3>Summary</h3>

<ul>
	<li>Liferay is __________________________________________________, and as long as you can provide each piece of the stack, you can use a myriad of combinations to run Liferay</li>
	<li>A ____________________________________________________ provides only the necessary components for deploying applications.</li>
	<li>A _________________________________________________________ includes the full complement of Java EE technologies, including JPA, EJBs, and everything else provided in the Web Container.</li>
	<li>The ______________________________________________ stores all of your data including everything from permissions configuration to user data and web content</li>
	<li>Running a __________________________________________________________ allows users to separate their Java server from the outside world but still get the behavior needed</li>
</ul>
</div>
