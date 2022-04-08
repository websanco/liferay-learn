## Clustering the Servers

Preparing for high user traffic and server failure is a key aspect of business continuity. An essential tool for fault tolerance is clustering. Clustering allows us to distribute user load across multiple servers and provide backup machines when one goes down. Liferay is designed for high-availability situations and scales well when clustered. Clustering is included in Liferay DXP Digital Enterprise. A typical clustering setup provides for additional servers in all tiers for maximum fault tolerance.

<figure>
    <img src="../images/clustering-architecture.png" style="max-width: 100%">
    <figcaption style="font-size: x-small">Fig.1 Example clustered architecture</figcaption>
</figure>

Providing a clustered solution for fault tolerance addresses the same layers:
- Load balancer
- Web servers
- Java EE Application servers
- Database servers

Implementing a clustered solution applies directly to a fault-tolerant architecture.

Implementing the Load Balancing Tier and Web Tier can be done in various ways:
- Load balancers as separate appliances
- Load balancing handled by the web servers

In both cases, we want to have load balancing and web servers clustered for maximum redundancy. With an all-web server setup, we can simply cluster multiple web servers with load balancing features configured. In smaller setups, we can reduce this to one web server, but this creates a single point of failure.

The core of clustering configuration is in the Application Tier. This is where Liferay runs and uses additional services. The backbone of clustering the App Tier is in setting up our app servers for clustering. With our app servers in place, we also need Liferay to operate as a cluster. All of Liferay's external needs should be considered as well:
- Cache
- Search
- Documents

The back-end of our configuration centers on an adequately configured Database Tier. Maximum fault tolerance pushes us to have redundancy in the database setup:
- Database cluster
- Read-write database splits

However the Database Tier is set up, there will be one entry point to the database. In small setups, it's possible to use one database server, but this creates a single point of failure.

Our cluster will be a simple, smaller-scale setup:

- **Load Balancing Tier**
    - This will be handled by the Web Tier.
- **Web Tier**
    - HAProxy configured for:
        - Load balancing
        - Reverse proxy to forward traffic
- **App Tier**
    - 2 Tomcat clustered servers:
        - Liferay instance on each server
    - Elasticsearch server running in a cluster
- **Database Tier**
    - 1 MySQL database with a dedicated user

<br>

### Configuring the Load Balancer {#configbal}

In our cluster setup, the first layer a request will come into contact with is the Load Balancer Tier and the Web Tier. Depending on your needs, hardware availability, and network infrastructure, you may have a combined Load Balancer/Web Tier. Load balancing can be done separately through hardware appliances or dedicated servers. Load balancing may also be combined with the Web Tier by using a load balancer in a web server.

Once the load balancer distributes network traffic to specific workers, the web servers proxy that traffic to the Java EE Application servers. Many web servers like the Apache HTTP Server and Nginx contain features for proxying traffic and distributing among app servers. In this way, multiple configurations are possible:
- Load balancer appliance to a web server
    - **Example:** F5 load balancer to an Apache web server
- Load balancer to proxy on same web server
    - **Example:** Apache's load balancer to a proxy over AJP to the app servers
- Load balancer web server to proxy web server
    - **Example:** Nginx load balancer to an Apache server to proxy over AJP

<div class="key-point">
    Key Point: <br>
    For maximum fault tolerance, both the Load Balancer Tier and the Web Tier should provide server redundancy.
</div>

At least two load balancers and two web servers should be set up to handle incoming traffic. There are many ways to implement this, including IP virtual systems, hardware content switches, IP address redundancy, and more. Your network administrators will likely provide the setup for these systems. For simplicity, we'll be setting up a single load balancer and reverse proxy. This provides a single point of failure on entry and is not considered fault-tolerant. If we maintain a hot backup of the web tier, however, recovery time can be very fast.

We're implementing our Load Balancer Tier and Web Tier using HAProxy. We'll take a closer look at configuring the load balancer in the exercises for this chapter section. 

### Clustering the Java EE Application Servers {#clusterjavaee}

Traffic entering through the firewall and load balancer will pass through the web servers. The web servers, in turn, act as reverse proxies, passing traffic through to assigned Java Application servers. Whether app servers or servlet containers, they perform the same function in a cluster. We'll need to consider how the web servers communicate with the Java EE Application servers.

The web server can easily pass through traffic over HTTP, which requires no special work for the web server. However, additional request metadata such as the Java session ID (`JSESSIONID`) might be lost. We can also use the web server to forward traffic over AJP. This allows direct communication between the web server and Java Application server. Each node accepting AJP traffic can be identified by a unique name.

Whether forwarding traffic over HTTP or AJP, we need to assign each Java Application server node as a worker to the load balanced web server. With AJP forwarding, we only need to assign a JVM route name to each node. Configuration is a little different for each server, but the goal is the same: assign a route name to a server that will be accessed over AJP.

When the load balancer is choosing a server to forward to, we have the option of using *sticky sessions*. This business continuity practice enables a user that begins a session on one server to be sent back to the server in subsequent requests. If a server goes down, the user is moved to a new node to start a new session. If session data is not important and can be easily reconstituted or replaced, this is a high-performance option. If session data is important and must be retained (such as a shopping cart), *sticky sessions* are the first step in a complete failover solution.

<br>

<div class="key-point">
    Key Point: <br>
    <i>Sticky sessions</i> are almost entirely a function of the load balancer and/or web server.
</div>

<br>

The only configuration in the Java EE Application server is possibly setting JVM route names.

If we have mission-critical data in our sessions, we may want to provide failover. Full replication is resource-intensive. Every session created is copied to all nodes in the cluster. There is some increased network chatter as a result. Our Java EE Application server is Tomcat and provides a simple mechanism for replication.

While *sticky sessions* are controlled mostly in the Load Balancer Tier and the Web Tier, _replication_, on the other hand, is entirely a function of the App Tier. All *Session Replication* takes place in the Java Application server nodes. All configuration for replication, including tweaking detailed settings, happens in the Java EE Application servers. To set up *Session Replication* in Tomcat:
- Enable Tomcat clustering
- Enable webapps to be distributed among the cluster

To implement a fault-tolerant and continuous App Tier, you should set up both *sticky sessions* and *Session Replication*.

### Deploying Applications in a Cluster {#appcluster}

Once we've set up an entire clustered environment with clustered load balancers, clustered web servers, and clustered Java EE Application servers, we'll need to consider how to manage deploying apps in this environment. For general deployment, each app will need to be deployed to each node in the cluster.

With standard Java webapps like servlets, handling cluster deployment is pretty straightforward:
- Manually deploy packaged WAR files to each node
- Use `rsync` to bulk copy WAR files to the deploy directories of each node
- Use built-in Java EE Application server cluster deployment mechanisms to deploy a WAR file across a cluster

<div class="key-point">
    Key Point: <br>
    Deploying Java servlets and WAR-based plugins can be done easily using the Java Application server's cluster deployment mechanism.
</div>

Tomcat has a mechanism called a *Farm of Tomcats*. You can set up a Tomcat farm in the `<Cluster>` node configuration. When a Farm is enabled, webapps installed on one node get replicated across the cluster. The replication itself is bandwidth-intense, but only needs to happen when they're installed.

Other Java Application servers, such as Wildfly or JBoss, have similar mechanisms. You can set up a group of app servers in a domain with a *domain controller*. The *domain controller* can get domain/cluster status, health, and start/stop servers. Additionally, you can deploy a WAR file through a domain controller and have that propagated across the domain. This is functionally the same as the Tomcat Farm concept.

This is all well and good when it comes to standard Java WAR deployment. What happens when this transfers over to modules? Modules are distributed as JAR files (or LPKG files from Marketplace). When modules are installed, they are located in the `/osgi/modules` directory of *Liferay Home*. This doesn't match what most servers know about installing apps in a cluster.

This issue is solved by most Java Application servers. In addition to the cluster of domain deployment, many servers have the option of setting up special replication settings. You can generally assign folders, files, etc. that should be replicated across all nodes in the cluster. In this way, you could sync the `/osgi/modules` folder between all nodes. This has the same effect as a Tomcat Farm or server domain group. If the server doesn't support folder replication, you can also set up a `cron` job to run `rsync` on a particular folder like `/deploy` or `/osgi/modules`. As long as the same set of apps and modules are installed on all nodes, whichever mechanism is easiest and most reliable is the best for you.

<div class="summary"><h3>Summary</h3>

<ul>
    <li>__________________________ allows us to distribute user load across _____________________________________ and provide backup machines when one goes down.</li>
    <li>Liferay is designed for ________________________________________________________________________ and ___________________________ well when clustered.</li>
    <li>When a ________________________________________ is enabled, webapps installed on one node get ________________________________________ across the cluster.</li>
    <li>______________________ is the idea of replicating a user's session on another node.</li>
    <li>______________________ means that a user will stay on the node to which he or she initially connected.</li>
</ul>
</div>
