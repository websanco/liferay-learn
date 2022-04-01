## Clustering the Portal

A key aspect of clustering is the ability of each node to talk across the cluster. As with database servers, app servers, and search indexes, Liferay needs to send and receive messages. Without a way to talk to all the servers in the cluster, each Liferay node acts alone. Solitary confinement of Liferay instances prevents the platform from working in a cluster. With Liferay comes a number of technologies:
- Search
- Caching
- Documents
- Scheduling

To coordinate these activities across a cluster, Liferay sends messages to other nodes.

How do we go from sending messages within an instance to sending messages between instances? Liferay already has a full-featured message bus for interprocess communications. Combined with tried-and-true network technologies, this messaging system can provide a robust channel for communication. The solution is provided in a simple package: **ClusterLink**.

*ClusterLink* provides communication between cluster nodes by combining two technologies:
- Liferay's message bus
- Multicast networking

<div class="key-point">
	Key Point: <br>
	Using <i>ClusterLink</i> Liferay instances can discover each other and exchange messages.
</div>

Liferay's message bus powers the management of messages within Liferay. With *ClusterLink*, it also manages messages between nodes in the cluster. Messages can be simple or complex, can be scheduled, and are persisted in the database. Data sent through the message bus is resilient and efficient. Channeling messages through the message bus to *ClusterLink* reduces the overall level of network chatter.

The network side of *ClusterLink* is powered by multicast networking. Multicast is an elegant solution to the complex problem of peer discovery. Most clustering or server farm-type communication requires that nodes be aware of each other. This can happen using *unicast* or *multicast* routing strategies.

One way for a group of servers to communicate is through strictly *unicast* means. *Unicast* simply refers to a routing strategy where packets are sent directly from one server to a recipient. This allows for highly reliable communication. The server and client are both aware of each other and often use lossless protocols to guarantee packet integrity.

In a cluster, each node is aware of the IP addresses of other nodes in the cluster. When a message is sent, each node can send the messages to the corresponding peers directly. Point-to-point communication is easy to implement, but results in a large amount of network traffic.

A more flexible routing strategy is *multicast*. *Multicast* allows a group to receive a message without the sender knowing who's in the group. A group member registers itself as a group peer at a destination *multicast* address. Anyone can send a message to the *multicast IP* destination. All group members receive the message. In a cluster, each node knows the *multicast IP*. Each node sends a "Hello!" message to register as a peer in the group. When a node sends a message to the *multicast IP*, all peers receive the message. *Multicast* routing results in lower traffic, since each node only has to send one message for all to receive it.

Whether routing messages using *unicast* or *multicast*, messages are sent using a standard transport protocol. Most *multicast* messages are sent using **UDP**. Most *unicast* messages are sent using **TCP**. Both *multicast* and *unicast* can use **UDP** or **TCP**. The main differences come down to performance versus integrity.

The most accurate, lossless transport protocol is **TCP**. **TCP** requires handshaking between the server and client, verifying the network connection between the two systems. Additionally, message integrity is usually verified by checksums and other acknowledgment information. If an error has occurred, it's possible to resend data and recover. The accuracy of **TCP** comes at the cost of speed and traffic. It takes longer and uses more bandwidth to send headers and verify message integrity.

To provide higher speed and message throughput, **UDP** can be used instead. **UDP** provides a simple way to send messages across the network without a lot of overhead. When a message is sent via **UDP**, the sender doesn't know if the recipient received the message. The recipient of the message also has no way to verify the integrity of the message and can't retrieve lost data. **UDP** can be thought of as a "send and forget" protocol. You can send a lot of data quickly, but you can't be sure it was received in its entirety.

*ClusterLink* defaults to *multicast* messaging using **UDP**. This allows a high amount of messages to be sent with little bandwidth. To help increase the integrity of the messaging, multiple *multicast* channels are set up to separate control messages from data messages. *ClusterLink* can also be configured to use **TCP**, use *unicast*, change the *multicast IP*, and change many other settings. At its simplest, *ClusterLink* automatically establishes a communication channel for cluster nodes without requiring any extra work.

All we have to do to cluster multiple Liferay instances is turn on *ClusterLink*. Simply enabling communication allows Liferay to operate effectively as a group. Once *ClusterLink* is on, you can refine the clustering options and settings in areas such as:
- Caching
- Documents
- Search

Turning on *ClusterLink* is pretty simple. Just add the following line to all nodes' `portal-ext.properties` files:
```properties
cluster.link.enabled=true
```
Once the nodes are restarted, they will register on the *multicast IP*, discover other peers, and begin sending messages to the group. Bringing a new node up in the cluster is a simple matter of starting a Liferay instance on the network with *ClusterLink* enabled and configured the same way.

### Configuring Multicast {#multicast}

When running a cluster in various networks, you may need to change some of the multicast settings. In a larger setup, you may have clusters in multiple environments:
- A *dev* environment for product development
- A *QA* environment for intermediate testing
- A *UAT* environment for pre-production staging
- A *prod* environment for the live site

One or all of these environments can exist in various subnets with their own clusters. You'll likely use different multicast IPs and ports for these environments.

Each environment will probably be separated by routers, firewalls, and other barriers to prevent traffic between subnets. You may have a networking administrator who has locked down areas of the network for each environment. In the environments where you need to cluster Liferay instances to simulate *prod*, you'll need to adjust the multicast settings.

<div class="note">
	Note: Modifying the default network configuration for ClusterLink is a low-level change.
</div>

Our standard area to look for settings is in the *System Settings* area of the *Control Panel*. Additional settings can be found in `portal.properties`. To override any of the settings, we'll need to add the overrides in our `portal-ext.properties`.

*ClusterLink* creates two channels to separate messaging:
- A *control* channel for system messages (like peers registering)
- A *transport* channel for regular messages (like indexing requests)

By default, there is only one of each channel. Since there could be many nodes in a cluster, and a lot of content, the *transport* channel can scale. If necessary, you can create up to ten separate transport channels. For every new channel, you have additional network traffic.

You can customize the channel names using:
```properties
cluster.link.channel.name.control
```
Since you can have up to ten `transport` channels, properties for each channel will be suffixed by an index number, from `0` to `9`. Channel properties can be configured using:
```properties
cluster.link.channel.properties.control
cluster.link.channel.properties.transport.0 ... 9
```
Properties can be defined inline in the `portal-ext.properties` file. Channel settings can also be written to an XML configuration file and referenced in the property.

When configuring multicast settings, you will be mostly interested in the *IP numbers* or *hostnames* and *port numbers*. The most important properties to adjust are:
- **`bind_addr`:** This is the *IP address* or *hostname* of the current node. This defaults to `localhost`.
- **`bind_port`:** The *port number* of the host for registering.
- **`mcast_group_addr`:** The *IP address* of the channel's group. This is what group peers listen on.
- **`mcast_port`:** The *port number* of the channel's group. This is what group peers listen on, combined with the above IP address.

You can also set system properties that affect all channels:
```properties
cluster.link.channel.system.properties
```

You can find additional useful settings related to *ClusterLink* in `portal.properties`. The complete list of channel properties can be found in the JGroups documentation at http://jgroups.org/manual/index.html#protlist. You can also find additional documentation through the *Liferay Developer Network* at https://portal.liferay.dev/.

Using these properties, you can move channels around the network landscape. This will help you adjust cluster nodes to fit within your network topology. You can also adjust the *ClusterLink* settings to accommodate unicast and TCP.

### Clustering the Cache {#cache}

Liferay handles a lot of data:
- Users
- Web content
- Images
- Documents
- Marketing data for audiences

Liferay makes extensive use of caching to maximize the resilience of reading and writing data and boost runtime performance. Some data needs to be stored as lists, others as single items. Some objects should be shared and others not. To maximize the efficiency of the cache, Liferay separates its caches into distinct areas:
- Persistence Layer cache: Caches the database queries and entities to decrease latency and reduce the load for the most common queries
- Business Layer cache: Used by portlets to cache the results from their operations

In a clustered environment, each caching area provides distinct behavior. Cache has been defined in two zones:
- *Single VM*
    - This is a business layer cache zone.
- *Multi VM*
    - This is a persistence layer cache zone.

*MultiVM*: Cache is cluster-wide, and data stored here should always be in sync across all servers in a cluster. MultiVM also contains the *Entity* and *Finder* caches needed for high-performance database queries.

<figure>
	<img src="../images/multivm-cache.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.1 MultiVM cache</figcaption>
</figure>

By default, only cache expiration is synchronized between nodes. When the cache is meant to be cleaned cluster-wide after the data has been updated, it is important to use the *MultiVM* cache. The default size of the *MultiVM* cache is 10000.

*SingleVM*: Data stored here can have different values across the servers in a cluster. SingleVM is good for external integrations like RSS feeds and when the cache doesn't need to be expired or updated cluster-wide.

<figure>
	<img src="../images/singlevm-cache.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.2 SingleVM cache</figcaption>
</figure>

The *SingleVM* cache zone contains data stored local to each instance. No data from the *SingleVM* cache is visible across the cluster.

When clustering multiple Liferay instances together, all the cached values in different nodes of the cluster need to be consistent. This is called cache replication. Cache replication is enabled as part of turning on *ClusterLink*. Liferay uses *Ehcache* by default for cache replication.

If necessary, specific settings for each of the caches can be overridden. The Ehcache configuration location is defined at `portal.properties`. Each of the cache zones has its own Ehcache configuration file.
```conf
ehcache.single.vm.config.location=/ehcache/liferay-single-vm.xml
ehcache.multi.vm.config.location=/ehcache/liferay-multi-vm-clustered.xml
```
To override the Ehcache configuration, you need to create a new configuration file at the portal classpath and define that location at `portal-ext.properties`.

### Clustering the Scheduler {#scheduler}

Liferay often performs a variety of background tasks:

- Checking for updates
- Sending out notifications for Workflow
- Emailing message digests from the Message Boards
- Running reports
- Publishing content from staging to production
- Showing and hiding web content based on a schedule

All of these jobs need to be managed somehow. Liferay needs to schedule, execute, cancel, and persist jobs beyond the application lifecycle.

<div class="key-point">
	Key Point: <br>
	Scheduled jobs and tasks are jobs for the scheduler. Liferay's scheduler is based on <i>Quartz</i>.
</div>

In order to do its job, the scheduler has to operate independent of any application. By default, *Quartz* works silently in the background, ticking time away to perform its tasks. Some tasks are run immediately or in the same session. Many tasks are scheduled, persist, and run on their own time.

A lot of small jobs are tasks like sending emails, batching index requests, or checking for new messages. These tasks can be done during the lifecycle of the application. They don't need to be persisted, because they start and stop while Liferay is running. In *Quartz*, these are **memory jobs**.

Let's say we've got a Liferay session running. In this session is a chat app with a neat ReactJS front-end and an XMPP-based back-end. Our client app needs to check whether new messages have arrived. Since our chat app doesn't care about messages when the user isn't around, there's no need to schedule this task ahead of time. Instead, while our app is running, it'll start a **memory job** to check in 30 seconds. When the job is performed, it ends. Our app can then schedule the next job. This process can repeat until our app is quit. Since we don't care whether we get any messages while we're offline, this job doesn't need to persist.

What if we're in our reports app and have just put in a new report definition? We've written a report that tells us how many users have logged in and interacted with our app. We'd like to get the report results and see how many users we have for today. The reports app lets us generate the report instantly, but it can take some time to crunch the numbers. The app may also be working on other reports. The reports app can add a queue of **memory jobs** that execute and complete as soon as possible.

That's great when we only need a report right now, an email sent instantly, or messages received when somebody sends them. But what if we want that report once a day at 12:00? How do we set a job to check for an app update once a month? How can we schedule a website push to production on the 15th of each month at 1:00? *Quartz* also provides for **persistent jobs**.

Our administrator has just completed a big round of changes to the front page. There was a team of designers and writers that spent two weeks updating the latest content. They'd like to make a push to production, but they don't want to have to stay up until midnight to do it when nobody's on the site. They use the scheduling feature of staging to set a publication job to occur later that night. The staging app creates a **persistent job** to do the task at the right time.

Even if Liferay is shut down and started back up that day, the publication task will run. If a power surge occurs and knocks down the server, the next time Liferay starts up and sees that **persistent job** sitting around, it performs the task immediately. The scheduler will guarantee this task will get done, regardless of the application lifecycle. These jobs work great on their own, but what happens when we add more Liferay instances to the mix?

As we build our cluster, we'll want each instance of Liferay to talk to the others about what scheduled jobs they have. We wouldn't want Node 1 of the cluster to create a web publish job only to have Node 2 replace or delete it. When Node 1 creates the publication job, Node 2 should know about it. This is achieved by making *Quartz* cluster-aware.

Not all jobs are ripe for clustering. I may have an app that periodically checks the weather forecast. This is a **memory job** that doesn't need to be shared by the cluster. But I may need to schedule a reindex to happen soon. This is a **memory job** that *all* of the nodes should do. Liferay solves this by adding a new job, the **memory-clustered job**. Jobs that are **memory-clustered** are stored in memory, but are known to the cluster. When a **memory-clustered job** is set, only one node will execute the job. Once a reindex is accomplished, the other nodes don't need to perform the same action.

How do we turn on this magical cluster awareness? Liferay provides a simple switch that enables *Quartz* to be cluster-aware and establishes the **memory-cluster** job. This switch is *ClusterLink*. *ClusterLink* allows communication across the cluster automatically, including scheduling. When the scheduler sees *ClusterLink* turned on, it immediately adjusts *Quartz* to be cluster-aware.

The secret to getting *Quartz* working with *ClusterLink* is simple. Turn on *ClusterLink*. That's it. No tricks. Once *ClusterLink* is enabled in `portal-ext.properties`, everything is handled behind the scenes. That means we don't have to do anything to get our cluster to behave because it already does!

### Configuring for Unicast {#unicast}

So far we've focused on Liferay's preferred cluster setup, *multicast* clustering. We've also briefly mentioned *unicast*. Although multicast is usually the best option, some network environments or specific servers will require the use of unicast clustering. Let's take a closer look at unicast clustering and its configuration in Liferay.

Unicast is, in some cases, a simpler alternative to multicast clustering. The difference is especially noticeable in larger clusters, where it can reduce the number of potential lanes that information is flowing through. In a multicast setup, all of the nodes in a cluster are equals and communicate as peers. All communication is also broadcast to all nodes. In unicast, there is one "master" node that all of the other nodes communicate through. All communication is from one point to another point. In addition, ClusterLink multicast is typically UDP-based, while unicast is typically TCP-based.

<figure>
	<img src="../images/clustering-unicast-multicast.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.3 Multicast vs. Unicast clustering</figcaption>
</figure>

As we discussed earlier, while multicast involves communicating with all members of a group simultaneously, in unicast each communication occurs directly between two points. In multicast, if a message needs to be sent to all nodes, it can be sent once, and all nodes will receive it. In unicast, if a message needs to be sent to all nodes, it will need to be resent for each node. Earlier, we also talked about the differences between **TCP** and **UDP** networking. Since unicast uses TCP, it will be inherently more accurate, but at the cost of bandwidth. Multicast, which uses UDP, by default can communicate faster with less overhead, but with higher risk of packet lost.

Regardless of which is better, in some cases, you'll need to use unicast, so let's talk about the specifics of its configuration. Whether we're modifying existing multicast settings for *ClusterLink* or completing changing the protocol (**TCP** instead of **UDP**) *and* the routing method (*unicast*), we'll need to change the channel properties. Channel properties can be set via `portal-ext.properties` directly or offloaded to an external XML definition. Declaring properties inline in our `portal-ext.properties` file is simple, but it can look complex when we're trying to modify a lot of settings. Instead, offloading our settings to an XML file makes it easier to change those settings and easier to read.

To use an external XML file, make sure that you place the XML file in Liferay's *classpath* (inside `[LIFERAY_WAR]/WEB-INF/classes` is an easy place to put it). In the properties, instead of directly setting the channel properties, you'll put the path to the XML file instead. For instance, we can create a configuration file called `tcp.xml` and fill it with our unicast settings. We'd then want to place that in the classpath. We can group all of our custom settings in a folder for clarity, maybe called `mygroups`. So in `[SERVER]/[WEBAPPS_DIR]/ROOT/WEB-INF /classes/mygroups`, we'd put our `tcp.xml` file. We'd then set the channel properties to point to `mygroups/tcp.xml`.

You'll notice the settings look different in XML than they do in the properties file. The general idea is the same, but we're dealing with setting attributes on XML nodes instead of properties. Some of the important settings include:
- **TCP:** Setting our main packet protocol as TCP instead of UDP. This is done using the `<TCP />` node, with properties set as attributes.
- **TCPPING:** This is our messaging method. Instead of multicast with UDP, we're using unicast via TCP. Members are reached by pinging the host addresses over TCP.
- **initial_hosts:** This is a list of *all* of the cluster members by host name or IP address and port number.

<div class="summary"><h3>Summary</h3>

<ul>
	<li>_____________________________________ provides communication between cluster nodes by combining three technologies:</li>
	<ul>
		<li>Liferay's ______________________________________________</li>
		<li>________________________________________________________</li>
		<li>________________________________________________________</li>
	</ul>
	<li>___________________________________ networking allows a group to receive a message without the sender knowing who's in the group.</li>
	<li>In a clustered environment, caches are defined in two zones:</li>
	<ul>
		<li>_____________________________________: This is a business layer cache zone</li>
		<li>_____________________________________: This is a persistence layer cache zone</li>
	</ul>
</ul>
</div>
