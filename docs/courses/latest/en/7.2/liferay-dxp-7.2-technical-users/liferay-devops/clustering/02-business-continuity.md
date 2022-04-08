## Business Continuity

An important aspect of configuring your Liferay deployment is not just optimizing the setup for how it should work, but being prepared for what to do when things don't work. In the computing world, disaster strikes frequently: the data center loses power, cloud services go down, and the wrong script gets run on the wrong server.

There are many considerations for disaster recovery. If the issue is related to the physical site where the servers reside, is there a separate physical site that contains the resources you need for recovery? How frequently is your data backed up? If your production database got compromised or corrupted, how much work would be lost when you restored from your backup? Do you have the capability to get all of these pieces put together in one place in a backup site that provides all of the data and infrastructure needed for getting your site back up? If not, what type of backup site do you have?

In general, any backup site needs to have:

- **A facility**: where you house your hardware and provide electricity, proper ventilation, and cooling
- **Connectivity**: You will need the proper connectivity and sufficient bandwidth to run a web server.
- **Network Infrastructure**: the necessary routers, switches, firewalls, etc.
- **Server Hardware**: You're not going to run a high traffic server off a Macbook Pro; you need the proper hardware to get your site up and running again.

That covers the core requirements for the site, but there are also considerations for how it's configured.

### Hot, Warm, and Cold Backup Sites {#backups}

Generally speaking, there are three kinds of backup sites: _hot_, _warm_, and _cold_. 

<br>

<div class="key-point">
	Key Point: <br>
	<ul>
		<li>A <b>Hot Site</b> is a perfect duplicate of the original site, at least from a software and data perspective.</li>
		<li>A <b>Warm Site</b> has the necessary hardware to get up and running, but does not contain a full or up-to-date duplicate of the software and data.</li>
		<li>A <b>Cold Site</b> is the infrastructure necessary to get your site back up and running, but without the data or software configured.</li>
	</ul>
</div>


A **Hot Site** can provide near instantaneous backup recovery, as everything is constantly synced with the original site. It is the best option in terms of speed of recovery, but also the most expensive option to maintain, as it requires a frequently synced, full duplicate of the original site.

A **Warm Site** is the middle ground between hot and cold. It provides for very quick recovery, if the data from the site can also be recovered.

A **Cold Site** is essentially the infrastructure necessary for recovery, but without anything configured or in place for that recovery. This means that software will need to be installed or updated, and data will need to be synced. In the case of a large installation, it could be days before a cold backup is up and running.

There are obviously pros and cons to each type of setup. The big pro for a Hot Site is the speed at which you can recover, but the tradeoff is the cost of maintaining such a site. The big pro of the Cold Site is that it's generally the cheapest. The big con is that you could have days or weeks of downtime. The Warm Site is the middle ground: cheaper to maintain than a Hot Site, but faster to recover than a Cold Site.

Say we have a VM with a Liferay bundle fully configured and running as a service on our system. Imagine that we also have some User data and content in there as well. What would the various types of backup Sites look like for our instance?

A Cold Site would look like:

- Another VM with a Liferay bundle installed, but not configured
- A recent backup stored separately

A Warm Site would look like:

- Another VM with a Liferay bundle installed and configured, but maybe not up-to-date
- It might have a fairly recent copy of the live instance's database, but the latest version might be stored separately

A Hot Site would look like:

- Another VM with a Liferay bundle installed, configured, and up-to-date
- It would be connected to a very recent database backup from the live instance

### HA Cluster Configuration {#clusterconfig}

High-availability Clusters (HA Clusters) minimize your downtime in the event of a failed node.

<div class="key-point">
	Key Point: <br>
	There are many possible configurations for HA Clusters, including:
	<ul>
		<li>Active-Active</li>
		<li>Active-Passive</li>
		<li>N+1</li>
	</ul>
</div>

HA Clusters will also use techniques like disk mirroring, redundant network connections, SAN, and even redundant power inputs so that a single point of failure won't cause a major disruption in services.

An N+1 Cluster is perhaps the simplest configuration. You have a cluster of some number of nodes (represented by N), and you have one additional node available to come online in case a node fails. There are several possible variations of N+1, such as N+M, in which "M" just denotes some number greater than 1. Depending on your configuration, the "1" or "M" nodes may permanently replace the original nodes that went down, or they may revert to being backup nodes once the original nodes can be brought back up.

<figure>
	<img src="../images/business-continuity-n1.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.1 N+ 1 cluster</figcaption>
</figure>

Active-Passive provides a fully-redundant instance of each node, which is only brought online when a node fails. This potentially requires the most hardware, as you need a full duplicate of your complete cluster.

<figure>
	<img src="../images/business-continuity-active-passive.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.2 Active-Passive cluster</figcaption>
</figure>

In an Active-Active configuration, all of your nodes are always live, and when a node fails, traffic is simply redistributed across the remaining nodes. In this case, all your nodes would need to have completely identical software and functionality. 

<div class="key-point">
	Key Point: <br>
	Active-Active is the preferred configuration for Liferay, since:
	<ul> 
		<li>The nodes should all be homogeneous anyway</li>
		<li>It improves scaling and is useful beyond instances where a node fails</li>
	</ul>
</div>

It improves scaling and is useful beyond instances where a node fails.

<figure>
	<img src="../images/business-continuity-active-active.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.3 Active-Active cluster</figcaption>
</figure>

Let's take a look at a two-node example to demonstrate what each High-Availability strategy might look like.

**N+1**: We would create one additional node with the same configuration and leave it offline until there was some failure that necessitated bringing it online.

<figure>
	<img src="../images/business-continuity-practical-n1.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.4 Simple N+1 example</figcaption>
</figure>

**Active-Passive**: We would create an example duplicate of our setup. We would leave it offline, but swap it in if there were a failure in one of our active nodes.

<figure>
	<img src="../images/business-continuity-practical-active-passive.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.5 Simple Active-Passive example</figcaption>
</figure>

**Active-Active**: We would add additional nodes to our configuration, but they would remain online, improving both regular performance and disaster recovery.

<figure>
	<img src="../images/business-continuity-practical-active-active.png" style="max-width: 100%">
	<figcaption style="font-size: x-small">Fig.6 Simple Active-Active example</figcaption>
</figure>

### Fault Tolerance Strategies for the Cloud {#cloudstrat}

The goal of business continuity and fault tolerance is simple: maintain maximum uptime, no matter the circumstances. When deploying to a cloud infrastructure, the end goal is the same. There are two major differences in approaching a cloud deployment versus an in-house deployment: the physical proximity of servers and additional assistance and services. Most Enterprise cloud providers offer servers and additional assistance during catastrophic failures and routine maintenance. Using these features may make a cloud deployment less labor-intensive than a standard deployment.

Cloud deployments have the same layers and high-level configuration as standard deployments:

- Load balancer tier
- Web tier
- App tier
    - Java EE Application servers and Liferay
    - Document storage
    - Search servers
- Database tier
    - Database servers and cluster
    - Database backups

What's unique in a cloud deployment is how many or all of these layers can be provided by different services working together.

Most cloud providers at a minimum provide you with the ability to quickly spin up server VMs. In this way, it's relatively easy to create load balancing web servers in the cloud. Even better, many cloud providers offer a load balancing service. Cloud load balancing services like Amazon's Elastic Load Balancer can be set up in a fraction of the time it takes to set up a standard web server instance. Services for load balancing in the cloud also separate themselves from the low-level hardware, so you can quickly expand capability to handle more traffic dynamically. If you use cloud-ready enterprise load balancing services, this is a fault-tolerant strategy for balancing your traffic load.

After you've assigned user traffic to various servers, you'll need to provide an efficient web front-end to forward to the Java EE Application server tier. Since load balancing can be handled by a separate service, it's possible to eliminate the web tier from simpler deployments. There are, however, some advantages to using a fast web server to serve static content, provide cached pages, compress outgoing content, and strip unneeded whitespace characters. Setting up a web tier in this arena is the same as a standard deployment, with the exception that we are working on VMs not physically near us. Additionally, cloud deployments can use various CDNs to streamline delivery of static content based on a server's geographic location (services such as Amazon's CloudFront or Akamai.)

Similar to the Web Tier, the App Tier features high-performing servers that run the backbone of the deployment: Java EE Application servers with Liferay deployed. Setup is similar to a standard deployment, again considering we are working with remote VMs. Additional cloud services we should consider in the App Tier:

- Document storage
- Search servers

It's not feasible to create an effective NAS or SAN storage solution through remote VMs. It is somewhat feasible to create a CMIS server, or other media repository, using the VMs a cloud service provides. In many cases, though, a cloud provider already offers a viable document storage solution that is optimized for its environment. Amazon's Simple Storage Service (S3) provides a scalable solution that behaves more like an NAS in performance and is very easy to manage. Additionally, some amount of redundancy and backup is included in cloud storage solutions. Much like the Load Balancer Tier, you gain efficiency and time by using a document storage service instead of running your own server.

An important aspect of document storage is adequate backups. Some amount of basic backup is usually included as part of any document storage service. You'll want long-term storage solutions as well. Most cloud providers offer ways to export your data, where you can provide physical local backups. Additionally, there are some cloud services for storing long-term backups at low cost, in a way that's secure and reliable. Amazon Glacier is an example service. These types of document storage backups are *cold backups*.

To make effective use of virtualized resources, you'll likely offload search to a separate search server. For most use cases, using the same VM-based server infrastructure works well. You can add cloud-based storage to back up the indexes for additional redundancy.

Databases are traditionally made redundant through clusters, read-write databases, and more. Cloud providers also offer services to use a scalable database service instead of running your own servers. Amazon's Relational Database Service is an example of this. Although you won't have full control over the parameters of the database server, it's generally highly-secure, actively-managed, backed-up, and low-cost. This can save a lot of overhead when dealing with data. Cloud-based database services are highly cost-effective with big data as well.

As with document storage, database schemas and entries should be backed up. This can be done by manually exporting and downloading for offsite storage and by using additional cloud-based storage services to back up the database dump in another location. Using multiple methods, from hot to cold, provides the greatest security. If you're using a cloud-based database service instead of your own servers, there is already an amount of backup built into the system.

In all, building a fault-tolerant solution in the cloud is very similar to an in-house solution. Unlike in-house solutions, cloud solutions often provide services to replace whole servers for specific features of your deployment. In many cases, you can gain higher redundancy and resiliency for lower cost. Make sure you check what options are available to you from your service provider in setting up the layers of your application.

### Auto-Scaling {#autoscale}

Most of the business continuity practices have focused on maintaining a steady, predictable flow of traffic to your sites. This works for most use cases, but not all of the time. Let's say you have a major ad push during a widely-televised event. Everyone's going to visit your site after seeing your ad. For this limited period of time, your user traffic will spike abnormally high. You don't expect to sustain this level of traffic long-term, so it doesn't make sense to build out new servers for this one event.

If you can't handle the rare occasion that traffic spikes enormously, then what can you do? If you can't match the scale of traffic, you could have widespread server outages. Alternatively, you can throttle network traffic, causing much longer loading times for users. Rather than provide a subpar experience to your users, you'd like to be able to *scale* with demand.

<div class="key-point">
	Key Point: <br>
	<i>Scaling</i> is the simple process you use to add new server nodes to meet incoming demand. You can scale manually or automatically.
</div>

When deploying in the cloud, your provider may offer *Auto Scaling* services. In an *Auto Scaling* environment, you can specify how many instances you want to add as traffic spikes. When a special event occurs or some external factors drive traffic, new instances spin up to meet demand. There may be momentary delays in response time, but not as slow as throttling.

We're deploying a solution for a pizza company using Liferay. In the standard production environment, there's a cluster of three Liferay instances. This cluster is enough to handle the standard amount of traffic the pizza company receives. They get about 5,000-10,000 customers a day who browse, place orders, and share products with their friends. They're planning a big promotion around the Super Bowl. With a 50% discount on pizza deliveries during the game, they expect a big traffic spike leading up to the game and just after it.

They decided to turn on *Auto Scaling* with their cloud provider. As a best estimate, they think traffic could double or triple during its peak. During such heavy traffic, they'd like to be able to:

- Maintain uptime
- Accept orders
- Fulfill orders

They estimate another 2-3 servers max will help offset the increased resource need.

The day of the Super Bowl arrives, and there's a steady and rising increase of traffic. Given the *Auto Scaling* settings, a new server instance is brought online. As traffic rises and peaks into the day, the pizza company sees up to 2.4 times increase in traffic. A total of two additional servers are brought online at the peak. The next day, subsiding demand from the spike means one of the server instances is stopped. Over the next few days, traffic returns to normal. The pizza company was able to handle the traffic increase gracefully at a fraction of the cost of running those additional servers full-time.

When bringing up new Liferay nodes, one issue that comes up is licensing. Each instance of **Liferay DXP Digital Enterprise** must be licensed properly. Your **Liferay DXP** instance can be managed through **Liferay Connected Services** (LCS). **Liferay Connected Services** installs as a simple app on each instance. Once installed, all connected instances can be managed through the LCS website: (http://lcs.liferay.com). LCS allows you to manage:

- Upgrades
- Patches
- Licenses

You can register new instances easily through LCS.

When spinning up new instances to meet demand, it doesn't make a lot of sense to pay for a full machine that you'll only use a fraction of the time. It also doesn't make sense to purchase additional Liferay licenses that you only use part of the time. **Liferay Connected Services** provides a new licensing option for these cases: *Elastic Licensing*. *Elastic Licensing* allows you to dynamically add new Liferay instances, fully-licensed, for a short period of time. Since you're only using a license for a small period of time, just like an *Auto Scaled* machine, you only pay for what you use. Speak with your Liferay representative for more information on **Liferay Connected Services** and the *Elastic Licensing* model.

<div class="summary"><h3>Summary</h3>

<ul>
	<li>There are three kinds of backup sites: </li>
	<ul>
		<li>_______________________________: A perfect duplicate of the original site, at least from a software and data perspective</li>
		<li>_______________________________: Has the necessary hardware to get up and running, but does not contain a full or up-to-date duplicate of the software and data</li>
		<li>_______________________________: The infrastructure necessary to get your site back up and running, but without the data or software configured</li>
	</ul>
	<li>________________________________________________________ (____________________________) minimize your downtime in the event of a failed node</li>
	<li>______________________________________ is the simple process you use to add new server nodes to meet incoming demand</li>
</ul>

</div>
