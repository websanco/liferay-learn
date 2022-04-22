## DevOps with Liferay

A key part of any product's lifecycle is deploying to production. System administrators and operations engineers play a vital role in this process. Traditionally, however, there have been significant barriers between the developers who create software and the administrators who deploy and manage that software's code in a production environment. Not uncommon is a constant ideological struggle between developers looking to increase development speed and system administrators aiming for operational stability. A separation of responsibilities and seemingly opposed goals lead to issues that negatively impact product, teams, and, ultimately, the business. How many times have you heard someone say, _"That's a development issue"_ or _"Sorry, that's an operations problem"_?

The team at Livingstone Hotels & Resorts needs to find a way to decrease the time to market for their deliverables. Their content, sites, and apps need to be updated quickly in order to make the most impact. After a series of evaluations and input from members of both the development and operations teams, management has decided that these teams can be taking steps to make better use of their time and more effectively deliver their products. 

The Livingstone team is prepared to make the move to restructure their organizational culture using a DevOps philosophy and methodology. They'll be aiming for improved inter-team dynamics and more automated processes and tools in order to reduce friction and decrease time to market. 

### What is DevOps? {#devops}

The DevOps philosophy aims to tear down the silos that often isolate development and operations teams by increasing communication and collaboration between these teams. This ultimately leads to the creation of products that are more stable, higher quality, and delivered more quickly. DevOps is a combination of _Development_ and _Operations_. The DevOps movement has its roots in Agile methodologies. It is _primarily_ a culture and set of values that has subsequently developed into a set of processes, practical tools, and practices for getting work done. These processes and tools feature a strong focus on automation and infrastructure as code. The movement is seeing incredible growth in the IT world and has been adopted by many industry professionals.

<div class="key-point">
Key Point:<br />
DevOps encapsulates both a philosophy and a collection of best practices and tools.
</div>

According to Damon Edwards and John Willis, two of the leaders in the DevOps movement, there are four core values that define DevOps: 

1. **Culture**: DevOps is not simply a job title or a set of tools. In order to make lasting impact, a complete shift is required in the way teams and businesses think about working together and how to get work done most effectively.

2. **Automation**: Automation plays a huge part in the DevOps methodology. People will always make mistakes, but automation is one way to improve quality by increasing predictability. Just as importantly, automating the repetitive tasks that take up a resource's precious time allows for freeing up team members to do more strategic work. For example, operations team members can work more closely with developers to understand the product and vice versa, so that both sides have a better picture of the entire lifecycle. This can influence how work is done and lead to better and more stable products. 

3. **Measurement**: Once a product is deployed to production, it is constantly being measured and monitored. Operations team members keep track of their applications, whether or not they are working as expected, how they are being used, and how they can make improvements. All of this data is organized and analyzed to make products better and make changes more quickly.

4. **Sharing**: Perhaps the most important ingredient for a successful DevOps team is making sure that feedback and communication are at the forefront of the team's values. Teams must constantly be communicating, both internally, with other teams, and with stakeholders. Team members should always be willing to share their expertise and wisdom, as this can only benefit everyone around them. Improved communication often leads to more opportunities for collaboration, a better understanding of the product and processes, and healthier teams overall.

DevOps takes these ideas and philosophies about how to do work and pairs them with processes and tools in order to implement this vision. In practical terms for the System Administrator, these sets of tools and practices are designed to make your work more effective, more automated, and maybe even more enjoyable. Keep in mind that DevOps does not mean that developers will take over your operations roles. It's a means of bridging the gap between roles for more successful work. 

We've talked a little about the philosophy behind DevOps. Now let's take a high-level look at its tools and how DevOps can integrate with Liferay.

### DevOps and Automation {#automation}

One of the most important practical components of the DevOps methodology is automation. Ultimately, you should aim to automate everything, including: 

* System Provisioning
* Configuration Management
* Application Building
* Testing
* Packaging

For example, let's take a look at system provisioning. Traditionally, there are a few ways that new systems are set up:

* _Bare Metal_: Physical machines, each of which hosts one server. Although common in smaller businesses that don't anticipate much change, this method is costly and scales poorly. Add to that the fact that creating clustered servers requires adding new, physical servers, and this option is not suitable for larger-scale projects.
* _Virtualization_: Takes bare metal servers and virtualizes their operating systems. By doing so, you now have the option to run multiple 'Virtual Machines' on one bare metal server. This allows for systems that can be created more quickly and managed more easily.
* _Containerization_: The next step above running VMs is containerization. A _Container Engine_ is installed on the host machine (be it with bare metal or on a VM) where _containers_ are deployed and orchestrated. A container can 'contain' apps, services, databases, and other components of a product. We'll look at containers a little more in-depth in the next section.

Alongside these more traditional provisioning means, there are also options to do all this in the Cloud. Running your system on the Cloud is similar to managing a traditional system, except that the physical servers are outside your infrastructure. Cloud providers offer tools to manage virtual servers. The management and provisioning concepts are the same, but the environment is different. 

When thinking in terms of the DevOps philosophy, one of the initial goals should be to automate system provisioning as much as possible. This frees up system administrators to use their time and experience on more strategic tasks. Automating provisioning would entail: 

* Automating the creation of virtual servers
* Automating the configuration of software on servers

This can be done using containers or configuration management tools like Chef, Ansible, or Puppet. Containers can handle these kinds of tasks more quickly, and we'll be focusing on using containers throughout this course. Container generation and management can be automated using _Container Orchestrators_. A container orchestrator will tell your containers how they are to be deployed and run the tasks required to do so. 

<div class="note">
Note: It's a best practice for applications to be made up of multiple containers. This means, for example, the web app component, web service, database, etc.
</div>

For example, a simple Liferay stack could be made up of the following containers:

* The Liferay instance container
* The database container
* The Elasticsearch container

By combining orchestration and cloud environments, you can introduce _auto-scaling_. Auto-scaling is a mechanism where you provide the orchestrator with the minimum and maximum number of container instances that a container image can have. The orchestrator then decides when to bring instances up or down, according to load. This lowers costs when an application is idle and handles load spikes when necessary. Auto-scaling configuration is handled by the orchestrator in configuration files.

Throughout this course, we'll look closely at a containerized Liferay stack setup using Docker containers and Docker Compose.

### Continuous Integration, Delivery, and Deployment {#continuous}

In the DevOps mindset, system automation is only half of the equation. The picture is not complete without integrated application development and deployment. In order to include development, testing, and deployment stages of a product's lifecycle most effectively into a DevOps workflow, proponents of the DevOps way have settled on a set of best practices:

* Continuous Integration: Automating the integration, testing, and building of software
* Continuous Delivery: Automating the creation and testing of production-ready deliverable packages
* Continuous Deployment: Automating the deployment of software changes to the production environment

You can (and should) tackle these tasks one at a time. Again, incorporating any of these changes into an existing workflow requires commitment beyond simply using some of the tools some of the time.

The central piece of this continuous approach pipeline is the "build server", a tool that helps manage automated software changes (Jenkins, Travis, Bamboo, etc.). Developers commit software changes to a common code base, and the code base is built and tested automatically. The ultimate scenario is being able to push a code commit and trigger an entire chain of automation processes -- build, test, archive changes, build images, push images, run more tests, and finally automatically push to production. The key is to include many layers of safeguards and tests that verify quality, predictability, and repeatability.

Reaching this level of DevOps "nirvana" takes work and requires more than simple tooling changes. However, you will likely find that the benefits far outweigh the risks and growing pains that are required to implement the DevOps philosophy. 

### Integrating With DevOps Culture {#integrate}

Throughout this course, we'll take an introductory look at some key DevOps tools and practices implemented in a Liferay ecosystem. 

Keep in mind however that the tools are only half of the equation. While we'll be focusing on the practical ways to implement a DevOps toolchain in these lessons,tools will only make an impact insofar that there is a shift within team culture.Automating build processes, deploying containers in production, moving your infrastructure to the cloud will be most successful when all teams work together to communictate and support each other in aligning with business goals. To make DevOps more than just a buzzword, you and your teams must put the empahsis first on cultural transformation before desigining the perfect pipeline.

In order to have your Liferay solution make the most impact for your business it is essential that you balance both DevOps culture and tooling.

For more information on DevOps you can see the following resources:

* [What is DevOps?](https://theagileadmin.com/what-is-devops/) - Ernest Mueller
* [What DevOps Means to Me](https://blog.chef.io/2010/07/16/what-devops-means-to-me/) - John Willis
* [The Three Ways](https://itrevolution.com/the-three-ways-principles-underpinning-devops/) - Gene Kim

<div class="summary"><h3>Knowledge Check</h3>
<ul>
	<li>DevOps encapsulates both a __________________________ and a set of _______________________________________________________.</li>
	<li>Automating systems provisioning includes:</li>
	<ul>
		<li> Automating creation of ______________________________________</li>
		<li> Automating _________________________________ of _____________________________________ on servers</li>
	</ul>
	<li>Best practices for development integration with operations processes include:</li>
	<ul>
		<li>____________________________________________________</li>
		<li>____________________________________________________</li>
		<li>____________________________________________________</li>
	</ul>
</ul>
</div> 
