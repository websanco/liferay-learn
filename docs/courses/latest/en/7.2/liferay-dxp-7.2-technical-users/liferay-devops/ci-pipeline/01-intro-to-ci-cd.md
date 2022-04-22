## Introduction to CI/CD Pipelines

System automation is great, but a DevOps approach would not be complete if automated system provisioning isn't integrated with the development and deployment of the application. Here is where Continuous Integration, Delivery, and Deployment come into play to close the loop.

> Livingstone Hotels & Resorts wants to create a seamless process between development and deployment for changes made to their platform. Ideally, they want to make it possible to push changes they make straight to deployment with automated safeguards and testing in place to ensure quality.

<div class="key-point">
Key Point: <br />
The following are a collection of good practices meant to improve software quality by automating building, testing, packaging, and deploying software:
	<ul>
		<li><b>Continuous Integration</b>: We focus on integrating, testing, and building software changes automatically.</li>
		<li><b>Continuous Delivery</b>: We focus on being able to create a production-ready deliverable package (that has been tested across multiple environments) with a single click.</li>
		<li><b>Continuous Deployment</b>: We focus on being able to deploy software changes in production automatically and without human intervention.</li>
	</ul>
</div>

CI/CD/CD can be tackled incrementally, meaning that you can start implementing a Continuous Integration workflow and then later on a Continuous Delivery process as your Software Development LifeCycle (SDLC) matures. The central piece of this approach is called the _Build Server_, a product that helps you orchestrate these software changes. There are a lot of them, like Jenkins, Travis, or Bamboo, each with its own peculiarities and capabilities.
The terminology varies inside different build servers, but for the purpose of this course, we can say that a CI/CD/CD approach is built by creating _Jobs_ in the build server using _Pipelines_ that are comprised of _Steps_:

<img src="../images/pipeline.png" style="max-width:100%;">

<br>

Imagine a pipe that has valves. You can open or close the valves to control the water flow according to your parameters. CI/CD/CD pipelines act in the same way: they describe how your software is built, tested, or deployed in sequential steps:

<br>

<img src="../images/jenkins-pipeline.png" style="max-width:100%;">

What are the benefits of implementing a CI/CD/CD pipeline?
- Bugs are caught earlier, making them easier and cheaper to fix.
- The software is built on a different machine from the developer’s, which prevents “works on my machine” errors.
- It is easier to see what software change caused a bug, so you can roll back to different versions without guesswork.
- The CI/CD/CD pipeline minimizes human error.
- Keeps your software potentially deliverable at all times, so no more waiting to download, compile, and package the code

Now that we have a general understanding of these concepts and how they make life easier for us, let’s look at each one in detail and how we can apply them in Liferay projects.

#### Continuous Integration {#ci}

<div class="key-point">
Key Point: <br />
<i>Continuous Integration</i> (CI) is where developers commit software changes to a common <i>Codebase</i>, and that codebase is built and tested automatically.
</div>

How the common codebase is managed depends on your _Software Configuration Management_ (SCM) software (GIT, SVN, etc.) and your SDLC workflow. That _common development ground_ could be a branch where every developer commits, or maybe individual branches that are merged periodically in a _development branch_. For example, this course was written according to the latter process. This will depend on how your company manages software and what workflow you use. If you don’t have any SDLC workflow or release plan and use GIT, you can use GIT flow. That codebase would ideally contain tests that would be executed automatically on every build by the build server, making sure that the commit the developer pushes doesn’t break tests.

We will not get too deep into testing in this section, but tests can essentially be scoped in three categories:
1. Unit tests - test actual classes and objects; check that code is correct and that methods and objects do what they're supposed to do, with no interaction outside our code
2. Integration tests - test the behavior of our code when interacting with other components (e.g., services, databases, utilities, etc.)
3. Functional tests - test actual user behavior (e.g., clicking and linking through the entire application flow)

<div class="note">
Note: All these tests can be coded using popular frameworks like JUnit, Mockito, Selenium, Cucumber, and so on.
</div>

#### Continuous Delivery {#cdel}

<div class="key-point">
Key Point: <br />
The first <i>CD</i>, Continuous Delivery, is the practice of building, testing, deploying, and packaging an application, along with its configuration and system changes, into production with a single click.
</div>

This makes rolling back changes easier, meaning there are fewer user errors and everything can be tracked and monitored more efficiently. This does not mean these changes will occur automatically. They still need to be triggered by a human, but the process is streamlined to the point that it only takes one click to start and is automated from that point forward.

<div class="note">
Note: For Continuous Delivery to work, you need to integrate Systems Provisioning with Application Development. This means that both orchestration and image-building could be included in the CI pipeline to build and deploy images to different environments with a single click.
</div>

#### Continuous Deployment {#cdep}

_Continuous Deployment_, the second CD, is the ultimate scenario, automation nirvana.

<div class="key-point">
Key Point: <br />
By implementing Continuous Deployment in your DevOps approach, you will be able to push a code commit that will trigger a whole chain of automation to build, test, and archive the code changes, build, push, test, and promote images, and push these changes to production without human intervention.
</div>

Continuous Deployment is almost like _pushing into production_, but with a full chain of safeguards and tests that ensure quality, predictability, and repeatability. With Continuous Deployment, you shorten the Time to Market dramatically, because changes only need hours and not months to be deployed.

<div class="summary"><h3>Summary</h3>
    <ul>
    	<li>CI, CD, and CD are a collection of ___________________ that improve software quality through the automation of building, testing, packaging, and deploying software.</li>
    	<li>Continuous Integration is where developers commit software changes to a common _______________, and that _______________ is built and tested automatically.</li>
    	<li>Continuous _______________ is the practice of building, testing, deploying, and packaging an application, along with its configuration and system changes, into production with a single click.</li>
        <li>Implementing Continuous _______________ allows a commit to trigger a whole chain of automated steps that safely push the changes made into production.</li>
    </ul>
</div>
