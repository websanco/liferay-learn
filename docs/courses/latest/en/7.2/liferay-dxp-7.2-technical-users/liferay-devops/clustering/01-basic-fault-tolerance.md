## Basics of Fault Tolerance

In order to understand managing fault tolerance in Liferay, we first need to understand Liferay's architecture. Liferay is a Web Application, which comes with its own set of requirements:

* Java Application Server
* Liferay Web Application
* Database

Exposing the application over the web creates additional points of failure. 

<div class="key-point">
	Key Point: <br>
	With so many moving parts, it's important to make sure that your Liferay setup survives potential failures.
</div>

In a full production setup, you have many layers to consider: Load balancers, Web servers, App servers, and Database servers. To ensure fault tolerance, it's vital to have the minimum setup in each layer. As an example, let's explore a minimal setup for production.

<figure>
	<img src="../images/reference-architecture.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.1 Minimal production setup</figcaption>
</figure>

Just behind the firewall is our first layer, the load balancers. In our minimal example, we have two load balancers. This provides some redundancy: if one were to fail, the other could pick up quickly.

The load balancers forward requests to the web tier. Typically, this is provided by web servers like Apache or IIS. Again, providing at least two web server nodes gives us a comfortable minimum of redundancy. Web servers are fairly quick to bring up, so adding new nodes or restoring failed nodes is relatively simple.

Application Servers are the core of our system, and the layer responsible for directly supporting Liferay. App servers exist in a cluster in this tier with a minimum of two nodes. This provides failover if disaster should strike.

Sometimes overlooked, the database tier provides the necessary data backbone for our apps. Database servers can be easily clustered and set up for redundancy. If one database should fail, there will be resilience in the database layer, and data will still be accessible.

Liferay provides the ability to guard against failure through clustering. 

<div class="key-point">
	Key Point: <br>
	Clustering is the practice of having multiple server instances sharing the load for serving a website.
</div>

You have multiple "redundant" instances of a web application working with one data set to provide improvements in speed and stability. In order for a cluster to work properly, you need each instance, or **node**, to be able to communicate correctly with several other components of Liferay:
- Cache
- Index
- Document Store

The cache contains all of the live data for users accessing a website. If a website knows that you're logged in, and what page you accessed last, it's because of the cache. When clustering, the cache needs to be configured appropriately, so that it is aware of the various nodes making up the cluster and can respond.

Liferay's index also needs to be cluster-aware. Changes made on any node need to be properly indexed across all nodes for consistency. By default, Liferay embeds Elasticsearch for search and indexing. In a production environment, clustered or not, you will want to configure an external server for search and indexing.

Like the index, the document store is, by default, embedded in Liferay. The document store needs to be configured to be cluster-aware so that uploads sync across cluster nodes. You can also configure an external document store like Documentum or Alfresco to work with Liferay, clustered or not.

Now that we've covered the basics of Liferay architecture for clustering, we'll take a more practical look into creating an unbreakable Liferay.

### Fault-Tolerant Configurations {#config}

A typical production configuration has a few goals. One is to handle traffic to the website with no issues. The other is to respond well when issues do occur. 

<div class="key-point">
	Key Point: <br>
	<i>Fault tolerance</i> sets up your system in a way that allows it to respond to failures.
</div>

Let's take a look at some potential scenarios and how we can work to handle them in a way that provides the best possible user experience.

When protecting against system faults, there are a few areas to consider:

- User load spikes
- Scheduled maintenance
- Emergency maintenance
- Hardware failure and unexpected system shutdowns
- Unwanted system intrusion

The main goal of fault tolerance is to maintain uptime regardless of system failures or necessary maintenance.

One of the key reasons to use a cluster is load management. You can only upgrade the processing power and memory of a single server so many times before you need to add more servers to handle the load. When building a cluster, one of the primary concerns with how many nodes you have is going to be how many you need to handle user traffic at peak volume. Providing enough nodes to allow for standard traffic with additional load available for spikes can help stave off system crashes and network failure.

Another key reason to have a cluster is to manage uptime and maintenance. Eventually, you're going to need to shut down or restart your server for some reason. Maybe you're updating a key application or patching vulnerabilities. Whatever the reason, your servers are going to go down one way or another. If you only have one node, that means your website goes down for the duration of anything that requires a restart, and any visitors to your site will get an error. If you have multiple nodes, you can rotate through them to keep the website up while you perform maintenance.

For example, let's say that you have a 6-node cluster. When performing maintenance, you don't need to take all six down at once, but you also can't take them down one at a time. At some point, outside of peak hours, you can rotate through your servers like this:

1. Take servers 1-3 offline
2. Perform necessary updates on servers 1-3 while servers 4-6 remain running
3. Bring servers 1-3 back online (don't connect them back up with 4-6 yet), and switch those servers to live while bringing servers 4-6 down
4. Perform necessary updates on servers 4-6 while servers 1-3 handle your traffic
5. Bring 4-6 back up, and you have a full cluster again

Sooner or later, your hardware is going to fail. It will probably happen when you least expect it. If, however, you have a clustered system running on separate hardware, and not a strictly virtualized cluster, you'll be in good shape. If you only have one server, a catastrophic hardware failure means that you need to replace the system with a backup as fast as possible. If you have a cluster, the other nodes can continue running and serving your site until you can replace the hardware and get your cluster back up to full strength.

In addition to your backup strategy and other continuity practices, it's vital to provide adequate fault tolerance for your deployments. The reference architecture is a minimum configuration for a single data center and cluster. Additional nodes mean additional fault tolerance and data centers.

### Server Affinity {#affinity}

Imagine, if you will, a new dystopia, where you sit down at your favorite chain restaurant to order, and suddenly you find yourself at the same table but at a different restaurant in the next town. Confused, you provide your order to the new waiter, and when you're done, you find yourself back at the first restaurant, but they're asking you to leave, because you've been there for too long without ordering. Not an ideal situation, clearly. Without configuring Load Balancing behavior, this is what can happen to sessions in your cluster. Users can find themselves losing data or getting bounced from node to node, with very confusing results.

Perhaps the most logical solution to this problem is **"sticky sessions"** or **Server Affinity**. This means that a user will stay on the node that he or she initially connected to (or the restaurant at which he or she was originally seated). Sticky sessions are a very simple solution to what seems like a complicated problem. When a user connects to a cluster node, his or her session "sticks" to that node and will remain on it. The biggest benefits to sticky sessions are the simplicity and the low overhead. The potential disadvantage of sticky sessions is that the simplicity means you can't handle difficult cases. For instance:

- If the traffic across nodes becomes unbalanced, there's no way to redirect users to a less trafficked node
- If a node fails, the user may be bumped to another node, but he or she will lose his or her session

There are two pieces to the sticky session configuration: the **Load Balancer** and the **Application Server**. The Load Balancer is responsible for directing traffic to the various nodes in your cluster. You need to configure the Load Balancer to:

1. Be aware of all the nodes of the cluster
2. Route traffic so that users who connect to a specific node will remain on that node

Each Application Server will be responsible for serving up each node in the cluster. You need to configure each node to work with the Load Balancer.

### Using a Full-Featured Web Server As a Load Balancer {#loadbalance}

Instead of a hardware Load Balancer, you can also use a web server like Apache. Apache uses the concept of **workers** to manage cluster nodes - each node in the cluster is a worker. All incoming traffic will go to Apache and is directed to a worker.

Each nodes would run as a Liferay-Tomcat bundle. Those nodes are then configured as "workers" for Apache, so that Apache can manage traffic between the nodes.

<figure>
	<img src="../images/fault-tolerance-load-balancer-workers.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.2 Managing nodes as workers</figcaption>
</figure>

There are a few technologies used to route the traffic between nodes. Apache uses the `mod_jk` or `mod_proxy_balancer` modules to forward the traffic, which the Apache JServ Protocol (AJP) will then transport to the workers.

<figure>
	<img src="../images/fault-tolerance-load-balancer-ajp.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.3 Routing traffic between nodes</figcaption>
</figure>

### Session Replication {#repl}

Now that we've taken a look at the specific technologies, let's head back to our restaurant for a different scenario. What if, instead of ensuring that you're not magically transported to another restaurant while you're eating, the solution was just to make every restaurant in the chain prepare the meal that you requested? This would be **Session Replication**.

Session Replication is the idea of replicating a user's session on another node. In some cases, this might be every other node on the cluster, while in others, it might be as little as one other node. The goal is that, if the user is redirected to another node during his or her session, all session data from the previous node will be available on the new node. Instead of worrying about who is on what node, this creates a seamless experience when a user, inevitably, finds himself or herself on a different node from the one he or she started on.

The benefits of Session Replication are pretty obvious. No matter what node you're on, the user never experiences session data loss. Taken to the extreme where every node contains all of the session data for every user, this is probably the most "fault-tolerant" strategy, since the failure of a node would have no noticeable effect on a user. The biggest drawback to Session Replication is that it is resource-intensive.

<figure>
	<img src="../images/fault-tolerance-extreme-session-replication.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.4 Session Replication</figcaption>
</figure>

Configuration for Session Replication is similar to what you would do for sticky sessions, but you need to open up a few more lines of communication. After you've configured your Load Balancer, you also need to configure your cluster nodes to communicate with each other so that session data can be replicated in real time.

In order to fully optimize a configuration, you should combine Session Replication and sticky sessions to create a stable, efficient cluster, which gracefully handles any issues. 

<div class="summary"><h3>Summary</h3>

<ul>
	<li>______________________________________________ is setting up your system in a way that allows it to respond to failures.</li>
	<li>The main goal of __________________________________ is to _________________________________________ regardless of system failures or necessary maintenance.</li>
</div>
