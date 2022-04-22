## Deploying to the Cloud

Cloud computing has changed the IT industry landscape and the way that most companies do business. By leveraging the power and flexibility of the cloud many administrative tasks and challenges that once required significant resources are now relatively simple to accomplish. It is easier than it ever has been before to build, scale, backup and maintain large production systems. While this isn't a course on cloud computing and architecting distributed systems, it's still important to understand the cloud and how Liferay and the cloud can go hand in hand.

### What is the Cloud? {#cloud}

When we talk about using the cloud we're talking about using distributed compute resources and servers that can be accessed via networks and the Internet. Deploying your application to the cloud means deploying your stack to managed servers, often housed in remote data centers. The cloud can provide businesses with access to virtually unlimited compute power and storage, especially when taking advantage of public cloud offerings. With the cloud it's easy to scale and manage systems and services.

There are 2 primary types of cloud environments commonly in use today:

1. The **Public Cloud**: This infrastructure is managed and maintained by a third party provider and publicly accessible to all of the provider's users. AWS is the largest example of a public cloud provider. You pay to accesses and use AWS' computing resources over the internet. When using a public cloud, keep in mind that the servers and infrastructure you are using is being shared with other users of the public cloud.

2. A **Private Cloud** is a dedicated infrastructure for one user, typically a single business or enterprise. Often these private resources are managed and maintained at locally by the business, but you can also purchase private infrastructure from third party provides. The resources can only be accessed via private network.  

You can also use a _hybrid cloud_ methodology, which is a blending of the 2.

Larger providers such as AWS, Azure and Google Cloud provide access at the infrastructure  level, and are commonly referred to as  _Infrastcture as as Service_ providers. This level of control and system design will be the most familiar to traditional System Administrators. The provider offers every piece of the infrastructure and network components, and you can design and plug all the pieces together as desired.

Many providers now also offer what is known as _Platforms as a Service_. With this option the infrastructure is managed and maintained by the provider, and the main focus is of the platform is deploying your code and applications. Everything going on behind the scenes, from networking to backups, is taken care of for you.

It's important to remember that you can implement DevOps tools and philosophy apart from the cloud. Also keep in mind that  using the cloud doesn't by default mean you're "doing DevOps". However, they both can go hand in hand and when brought together it becomes easier to take full advantage of all of the resources and benefits of both DevOps and the cloud.

### Liferay and the Cloud {#liferaycloud}

Liferay has been designed to work on any cloud environment be it using the public cloud or an internal private structure. While the Liferay platform would fall within the application tier of your stack, you can use the cloud to stand up the rest of your stack and be able to be manage and remotely maintain all of the services and setup required to run a Liferay stack.

Our in house Global Services teams are experts at designing and managing cloud deployments and can can assist you in transitioning your Liferay solution to either the public or private cloud. 

Liferay has also recently introduced a new platform as a service for easily deploying and managing Liferay DXP, **DXP Cloud**. This is a completely managed Liferay solution in the cloud that offers many features including managed backups, auto-scaling and advanced monitoring. With Liferay DXP Cloud, you can focus on using Liferay for your end goals and leave the system administration and management to the Liferay experts. We'll take a look at more DXP Cloud features throughout our lessons. For more information on DXP Cloud you can go to [https://www.liferay.com/products/dxp-cloud](https://www.liferay.com/products/dxp-cloud)

As we continue to move through the course, we'll also take a closer look at the practical steps required to enable a Liferay solution using the cloud.
