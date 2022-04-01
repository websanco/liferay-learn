## Architect for the Cloud

Deploying a Liferay solution onsite is a great way to control all of the layers of your apps directly. However, with managing all of the systems comes a plethora of additional needs:
- Machines for servers
- Network hardware including routers, firewalls, and load balancers
- Technical staff experienced in managing networked systems
- On-call staffing for emergencies and disaster recovery
- Additional storage space
- Utilities backup such as generators
- Additional bandwidth needs

Sometimes it's advantageous to outsource some or all of these layers to a third-party provider. Recently, the use of cloud providers has become widespread and well-supported.

<div class="key-point">
    Key Point: <br>
    Keep in mind that you are not limited to only two choices: everything in the cloud or everything local. There's an unlimited combination of onsite and cloud services to cover every need. 
</div>

It's possible to do everything onsite with a cloud-based hot backup. It's possible to do everything in the cloud with a local hot backup. It's also useful to run production from an onsite setup with cloud-based expansion for high-traffic overflow. Whether we're going to do everything in the cloud, or use some aspect of the cloud, we have to account for the difference in deployment.

### Recreating Your Stack in the Cloud {#recreate}

Cloud services cover the gamut: from load balancers to no-SQL databases and everything in between. To consider re-creating our production environment in the cloud, we need to reconstruct our layers:
- Firewall Tier
- Load Balancer Tier
- Web Tier
- Application Tier
    - Document repositories
    - Search servers
- Database Tier

Taking our fault-tolerant design and transposing it to the cloud results in a cloud-based fault-tolerant production system.

Our physical firewall tier translates to any number of technologies in the cloud. The goal is to have a protected network of virtual servers with protection against intrusion. Most cloud providers already protect each service against outside attacks, but there are additional steps we can take.

Some services provide a *virtual network*. This emulates a physical subnet with all machines that are members visible to one another, but outside machines can't see them. Additional services may include an *access control list*. This allows you to set up levels of access based on explicit membership. Similar to ACLs are *security groups*. This allows you to build groups of machines that have prescribed levels of access. The exact details will vary by cloud provider.

When building virtualized networks, it's easy to forget that these machines are visible only virtually and aren't physically in the same network. Most cloud services that implement virtual networks don't support multicast communication in that network. As a result, your clustered nodes (Web Tier and App Tier) will need to configure alternative communication protocols for *ClusterLink*.

While it's possible to run load-balancing software in a virtualized setting, most cloud providers have a service to handle load balancing. Load balancing may be configured by a set number of units or servers. Some load balancing services allow you to set flexible or elastic numbers, so that the number of load balancers can scale on demand. You can have load balancing services direct traffic directly to your Application Tier, eliminating the Web Tier entirely. Directing traffic to the App Tier will increase the load on the Java EE Application servers and may decrease performance and response time.

The middle tiers -middleware -are mostly provided by virtual machines in the virtual network. The Web Tier is made up of VMs running web servers like Apache or Nginx. Some cloud providers require static numbers of servers. Some providers allow for flexible server numbers based on demand. Setup will be similar to physical machines, with the exception of being able to choose RAM and CPU usage. While web servers can run on less, it's a good idea to make sure they have a healthy amount of computing power available. A good guideline is *4 virtual cores* and *7-8 GB RAM*. You may also want to enable a CDN to alleviate static resource load times.

The big area of implementation is usually the App Tier. You can use a cloud provider's virtual machines to build the App Tier. Since overhead on machine resources adds to the computing needs, it's a good idea to beef up your VMs. A good baseline is *16 virtual cores* and *30 GB RAM*. Most cloud providers allow for VM images to be used to spin up instances quickly. This feature is valuable when scaling on demand or restoring a hot backup.

Enterprise cloud services also provide additional services for documents. Specialized virtual document repositories provide the speed and fault tolerance of maintaining an NAS or SAN. There's the additional benefit of having an amount of backups built into the service along with disaster recovery. It's recommended to use the available cloud-based document repositories instead of running a virtualized document server.

Just like a real onsite deployment, we can add VMs to our App Tier that are set up to provide search, such as an Elasticsearch cluster. VM needs are similar to those for the Java EE Application servers in the App Tier. As with *ClusterLink* communication, visibility between servers will need to be managed directly in a virtual network.

As with documents, it's possible to set up VMs in the cloud running database servers. However, most services also provide cloud-based database systems. They may cost more per unit to use, but they are also fully-supported and backed up. Additionally, you don't need to configure or set up any server machines. It's recommended to use a cloud-based, well-supported database service when deploying to the cloud.

When deploying to the cloud, we need to consider:

- *Firewall*
    - Virtual networks, ACLs, and security groups
- *Load Balancer Tier*
    - Load balancer services, VMs with software load balancing
- *Web Tier*
    - VMs with web servers configured
- *App Tier*
    - VMs with Java EE Application servers and Liferay installed
    - Document repository service being used
    - Possible CDN connection
- *Database Tier*
    - Use a well-established database service in the cloud.

We'll put this to the virtual test and walk through a deployment in a specific cloud provider.

As mentioned earlier, you can also take advantage of **DXP Cloud** to gain access to an official Liferay solution in the cloud. DXP Cloud is a PaaS solution that offers a completely hosted and managed Liferay stack so that you can focus using Liferay for your needs. You can take a look at our _Getting Started with DXP Cloud_ course for more information.

### Use Case: Deploying to Amazon Web Services {#aws}

We'll take these general guidelines and principles and apply them specifically to *Amazon Web Services*. AWS is a well-established cloud provider with a wide variety of services. It's possible to run our entire deployment in the cloud, so let's check how we might do that.

The Firewall tier can be established using Amazon's *Virtual Private Cloud*. This creates a simulated, virtual subnet, emulating participating machines existing in the same physical space. VPC doesn't support multicast, so *ClusterLink* will have to be configured to use one of the alternate unicast protocols:
- TCP Ping
- JDBC Ping
- S3 Ping

In addition to VPC, you can define *security groups* and *access control lists*.

Instead of rolling your own load balancer, you can use Amazon's *Elastic Load Balancer*. This works well in conjunction with the VPC and virtual machines. Amazon provides an easy interface that allows you to configure, start, stop, and add new load balancers to your network as needed. It's faster to set up than installing your own load balancing software.

The Web Tier in Amazon is implemented using standard virtual machines. The virtual machine service is *Amazon Elastic Cloud 2*. EC2 instances can be configured with a wide range of computing power. In our example, Liferay would run well with EC2 instances with high settings:
- **Instance type:** 4 X-Large
- **vCPU:** 4
- **Memory:** 16 GB
- **Processor:** Intel Xeon

Amazon calculates a CPU as a *vCPU*, which gives a slightly reduced amount of computing power. A *vCPU* is close to 70%-80% of a real CPU of the same type.

Like the Web Tier, the App Tier is made from standard VMs. In Amazon, each EC2 instance would be a bit larger than the Web Tier:
- **Instance type:** 4.4 X-Large
- **vCPU:** 16
- **Memory:** 30 GB
- **Processor:** Intel Xeon

As with the Web Tier, this allows for some overhead due to the fact that we are running in a virtualized environment.

Amazon provides a document service called *Simple Storage Service* (S3). S3 is a dynamically expandable document repository. Liferay provides an easy way to connect to S3 document stores. The built-in redundancy, backup, and tech support provided with S3 probably outweighs running your own document servers.

As with documents, it's possible to run EC2 instances in Amazon that contain your database server of choice. Amazon also provides the *Elastic RDBS* service. Amazon's RDBS service simulates a standard database server, but is preconfigured to be highly-available, fault-tolerant, and backed up. RDBS service costs a bit more than running the EC2 instances, but saves the hassle of setting up, running, and maintaining the database in-house.

Overall, even applied to a specific cloud vendor like Amazon, the pattern is simple. There are a few key differences compared to physical deployments:
- Additional database, load balancer, and document services
- Increased computing power needed due to overhead
- Lack of multicast support

With these exceptions in place, it is straightforward to complement your solution with a cloud-based implementation or to run production in the cloud entirely.

<div class="summary"><h3>Summary</h3>

<ul>
	<li>By _____________________________________________________________________________ users can outsource some or all of their production environment layers to a ________________________________________________________________</li>
	<li>Users are not limited to choosing between having everything ________________________________________________________________________ or _______________________________________</li>
	<li>There are an unlimited combination of_________________________________ and ______________________________________________________________ to cover every need</li>
</ul>
</div>
