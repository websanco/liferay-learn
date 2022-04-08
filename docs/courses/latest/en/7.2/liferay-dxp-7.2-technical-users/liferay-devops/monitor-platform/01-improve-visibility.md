## Monitoring Tools

A core aspect of business continuity and performance is monitoring your running applications.

<div class="key-point">
Key Point:<br>
Monitoring can cover a variety of systems:
<ul>
<li> IT Monitoring
	<ul>
    	<li> Network Monitoring
    	<li> Load Balancer Monitoring
    	<li> Web Server Monitoring
	</ul>
<li> Application Performance Monitoring
	<ul>
    	<li> JVM Monitoring
    	<li> Database Monitoring
	</ul>
<li> User Monitoring
	<ul>
    	<li> User Behavior Monitoring
    	<li> User Session Monitoring
	</ul>
</ul>
</div>

In addition to standard IT monitoring, we're interested in how Liferay performs in production and what users are doing.

### Application Performance Monitoring {#apm}

A core component of tracking the overall health of your deployment and determining performance needs is *Application Performance Monitoring*. A fundamental level of APM is JVM monitoring. Tracking the memory usage, garbage collection status, thread listing, and thread dumps provides valuable information. More extensive APM is provided by various vendors on top of the basic Java toolset.

<div class="key-point">
Key Point:<br>
Java EE provides numerous built-in tools and APIs for monitoring. Java also releases bundle-monitoring tools with a wide range of uses:
<ul>
	<li> JConsole
	<li> Java Mission Control
	<li> Java Flight Recorder
	<li> VisualVM
</ul>
</div>

Java Mission Control and Java Flight Recorder are part of the commercial Java packages offered by Oracle. They are bundled with the Oracle JDK, but require a commercial license to use in production. JConsole is standard in the JDK and requires no additional licensing. VisualVM is packaged with JDK 8, or a standalone version can be downloaded at https://visualvm.github.io. Third-party enterprise monitoring tools are also built on the same foundation of JVM monitoring.

Typically, simple monitoring can be done using JConsole. JConsole gives a UI that displays basic information on what's happening within the JVM. Things such as memory, threads, and classes can be evaluated with JConsole.

More robust monitoring can be done with VisualVM. Many standalone tools such as JConsole, jstat, and jinfo are included in VisualVM. By aggregating data VisualVM pulls from its plugins, you can view different data in one place. VirtualVM's functionality can easily be extended through both official and third-party plugins, allowing it to monitor many aspects of the JVM and, in turn, Liferay.

Advanced monitoring can be achieved using tools such as YourKit, NewRelic, and Dynatrace. Advanced monitoring not only gives you information about the JVM (memory usage, garbage collection, cache, etc.), it also goes beyond the JVM, giving you real-time displays on what's going on with the web server, browser, application server, and database. Although Liferay gives you the liberty to pick the tools you want to use, Liferay and Dynatrace provide a Liferay FastPack that can be used as a reference to tailor Dynatrace for Liferay DXP environments.

When using JConsole or VirtualVM, monitoring can be done either locally or remotely. Local monitoring is easy to set up, but uses a large amount of system resources. The most accurate mode of monitoring is done remotely, so that system resources on the target machine are almost exclusively used to run the application being monitored. Remote monitoring requires a direct connection to the target machine. Java provides the JMX extensions to allow monitoring tools to connect to a running VM.

Remote monitoring is enabled by turning on the JMX remote agent in the target VM. In a simple remote monitoring setup (such as JConsole or Java Mission Control), the **agents** provide real-time information to the **client**. Since the **client** is a separate machine from the app server nodes, it takes most of the resource consumption for running the monitoring software. This is an effective solution that offloads much of the processing to an external client machine. Enabling the JMX remote agent in a VM is done by passing the JVM the parameter: `-Dcom.sun.management.jmxremote`. More information on using JConsole can be found at http://docs.oracle.com/javase/8/docs/technotes/guides/management/jconsole.html.

JConsole monitoring can give us a lot of information, but it may be hard to digest. We may want more detailed information, recording VM data, better reporting, or faster performance.

<br />

<div class="key-point">
Key Point:<br>
A variety of third-party enterprise monitoring (APM, Application Performance Monitoring) tools can increase our efficiency in monitoring:
<ul>
	<li> YourKit
	<li> NewRelic
	<li> Dynatrace
</ul>
</div>

<img src="../images/remote-monitoring.png" style="max-width:100%;">

Additionally, many enterprise monitoring solutions provide higher-performing and more fault-tolerant architectures.

A more sophisticated remote monitoring setup can be achieved through some third-party tools. In this setup, the simple **agent**-**client** architecture is made more robust. **Agents** send data to **collectors**, small servers for aggregating performance metrics. The **agent** and **collector** are in the same subnet, making it possible to retrieve near real-time data. **Collectors** do some post-processing and offload data to the monitoring **servers**. Monitoring **servers** perform analysis and make reports available to the **client**. Instead of storing data locally, data is cast onto a dedicated **storage server**.

<img src="../images/remote-monitoring-dyna.png" style="max-width:100%;">

An additional level of monitoring we can consider is how users interact with Liferay. Monitoring user behavior can provide useful information to business units that design the site. User behavior can also correlate with performance issues, allowing you to pinpoint where problems are and what users do as a result. User behavior and session tracking is also useful for security audits.

<div class="key-point">
Key Point:<br>
Understanding the habits of users on your website is important for a variety of reasons:
<ul>
	<li> You want to know what your most popular pages and apps are.
	<li> You want to see if there is important information that users are missing out on.
	<li> You also want to know if there is information being accessed improperly. Is there content that is supposed to require credentials that is being accessed without those credentials?
</ul>
</div>

Because of all this, monitoring is important for everyone from marketing to security to infrastructure.

### Integrating Monitoring Tools at the Site-Level {#integratesite}

There are many tools and services available to integrate monitoring into your site. **Google Analytics** remains one of the most popular services, and it has both free and premium versions. **Piwik** is a free open-source alternative to the many proprietary systems out there. There are many other tools available from various vendors including Adobe, Mint, Clicky, and more. All of these tools will help you do traffic analysis on your website to find out more about your users.

One of the options that's very easy to integrate in Liferay is *Google Analytics*, so we'll take a look at the process of setting up and gaining reporting data with it.

<img src="../images/monitoring-google-analytics.png" style="max-width:100%;">

Google Analytics can be enabled per site. Administrators can go to *Site Administration → Configuration → Settings*. The *Analytics* section can be found in the *Advanced* tab. From there, administrators can insert Google Analytics or Piwik ID.

<img src="../images/monitoring-analytics.png" style="max-width:100%;">

Once you have signed up for a Google Analytics account, you are asked to paste a snippet of JavaScript into every page you want analyzed. There is an ID number in this snippet of JavaScript. Instead of pasting the JavaScript, all you need to do is put your Google Analytics ID into the field provided, and you will be able to do traffic analysis with Google Analytics. You must do this on a site-by-site basis, but as long as your sites are accessed with the same domain name, you can add the same ID to each site.

Piwik integration is also available. Piwik is an open-source alternative to Google Analytics that you'd typically host yourself. Enabling Piwik follows the same process as enabling Google Analytics. Just enter your code in the box, and you'll start tracking data for that site.

Some specific industries, like banking, need very detailed tracking. Any site that handles financial transactions will need to account for every click and every action of every user on the site. Other industries also have strict auditing standards that require a deeper level of monitoring than page views and site traffic.

Liferay DXP provides a full suite of monitoring and auditing tools. **Liferay Monitoring** provides live monitoring of users on your platform. **Liferay Audit Tools** provides a toolset for a full audit of user actions on the site. The Monitoring application is primarily intended for development purposes and may cause performance issues if run in production with a large number of users. It is also not cluster-aware. The Audit Tools provide a full toolset that you can use in production, but is only available for DXP subscribers.

Since we are focusing on creating a production-like environment, we'll look at the Audit Tools. The Audit Service stores the audit trail from Liferay and your plugins. It can store the information into log files, a database, or both. The Audit Reports portlet allows you to search and browse audit events that have been stored in a database.

You can find the Audit Reports portlet in the _Control Panel_: *Control Panel → Configuration → Audit*. You can see your detailed audit trail for any action you've completed on the platform. If you click on one of the entries in the Audit Reports table, you're given a detailed analysis of that specific event. Depending on the amount of users, this list can get populated quickly. Therefore, it's a good idea to keep the *audit.message.com.liferay.portal.model.Layout.View* property set to false in your properties file. This causes less clutter for your audit event by disabling the view events, which is the most triggered event in the platform.

You can take a look at the Audit trails on the platform as follows:

1. **Go to** *Control Panel → Configuration → Audit* in the *Menu*.
    - You'll see a list of entries that summarize the actions you've made since installing the audit plugin.
2. **Click** on one of the entries to view the action in detail.
    - The Audit plugin tracks every user's activity across the platform, giving administrators a powerful tool for monitoring resource actions.

<img src="../images/audit.png" style="max-width:100%;">


<p style="page-break-before: always"></p>

### Analyzing Logs {#logs}

When you start talking about "analyzing logs", usually one of two things come to mind. Either you're doing a quality check on these:

<figure>
	<img src="../images/logging-tree-logs.jpg" style="max-width:100%;">
	<figcaption style="font-size: x-small">Image CC by-sa 3.0 https://commons.wikimedia.org/wiki/File:The_lumberjack_was_here.jpg</figcaption>
</figure>

Or you're trying to figure out what's going on here:
```log
0 [ROOT] created a ThreadLocal with key of type
[com.twitter.jsr166e.Striped64.ThreadHashCode]
(value [com.twitter.jsr166e.Striped64$ThreadHashCode@18636168])
and a value of type [com.twitter.jsr166e.Striped64.HashCode]
(value [com.twitter.jsr166e.Striped64$HashCode@56dace91])
but failed to remove it when the web application was stopped.
Threads are going to be renewed over time to try and avoid a probable memory leak.
```
In Liferay, we're talking about the latter. To find Liferay's logs, you can look in two places:

<div class="key-point">
Key Point:<br>
The primary logs with app server and runtime information are typically found in [liferay-home]/[app-server]/logs. Additional logs with Liferay-specific logging are found in [liferay-home]/logs.
</div>

Logs are stored in multiple formats by default and labeled by date. For example, a log created on April 22th, 2018 will create a `liferay.2018-04-22.xml` and a `liferay.2018-04-22.log`. Let's take a quick look at the logs we have in our local Liferay-Tomcat bundle.

You can find the following logs in one of your nodes:

- **`[liferay-home]/logs/liferay.[date].log`**: Standard Liferay Log4j log, configured in `portal-log4j.xml` from `portal-impl.jar`
- **`[liferay-home]/[app-server]/logs/catalina.out`**: Tomcat's `stdout` and `stderr` streams directed to this file by `catalina.sh` during startup
- **`[liferay-home]/[app-server]/logs/catalina.[date].log`**: Standard Tomcat logs; **`host-manager.[date].log` and `manager.[date].log`**: Tomcat's Manager webapp logs
- **`[liferay-home]/[app-server]/logs/localhost.[date].log`**: Additional Tomcat log
- **`localhost_access_log.[date].txt`** - Tomcat access log configured in `[liferay-home]/[app-server]/conf/server.xml`

Unless otherwise specified, Tomcat logs are configured in `[liferay-home]/[app-server]/logs/conf/logging.properties`.

Log levels can be configured through *Server Administration* in the _Control Panel_. You can configure eight levels of log detail for many different categories covering different aspects of the system and modules. The levels range from *None*, which will turn off logging for that category, to *All*, which will log absolutely everything that can possibly be logged.

<div class="key-point">
Key Point:<br>
If you have a custom class that is not in the list, you can use the Add Category tab to add the class to the list and set a log level for it. This makes it very easy to debug functionality added to Liferay by your developers.
</div>

<img src="../images/logging-log-levels.png" style="max-width:100%;">

<div class="summary"><h3>Summary</h3>

<ul>
	<li>A core aspect of business continuity and performance is ____________________________ your running applications.</li>
	<li>_____________________________ can cover a variety of systems:</li>
	<ul>
		<li>_____________________________________________ </li>
		<li>____________________________________________________________________ </li>
		<li>_____________________________________________ </li>
	</ul>
	<li>_____________________________________________________ provides a toolset for a full audit of user actions on the site.</li>
	<li>To find Liferay's logs, you can look in two places:</li>
	<ul>
		<li>___________________ logs with app server and runtime information are found in ________________________________________________________________.</li>
		<li>Additional logs with ___________________________________________________ logging are found in ________________________________________________________________.</li>
	</ul>
</ul>
</div>
