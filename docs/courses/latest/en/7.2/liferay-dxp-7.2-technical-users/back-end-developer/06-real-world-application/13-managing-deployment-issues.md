---
description: 06 - Real World Application
title: Managing Deployment Issues
order: 13
---

## Managing Deployment Issues

In this section, we discuss module deployment-related issues and some methods for resolving them. We've already learned that only the Liferay web application itself is deployed to and managed by the Java application server. All Liferay applications run in a Liferay-embedded OSGi container, and that's why resolving module deployment issues typically involves Gogo Shell.

#### Deploying Modules Overview

There are multiple ways to deploy and undeploy Liferay modules. Understanding these methods is essential in troubleshooting, as the changes they make in the file system depend on the method. 

#### Autodeploy

Autodeploy is the default method of deploying modules to Liferay. You copy the module JAR or legacy WAR into the autodeploy folder, and it gets automatically deployed to the OSGi container. 

By default, the autodeploy folder is `LIFERAY_HOME/deploy`, but it can be configured with `auto.deploy` prefixed settings in the  [portal properties](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-impl/src/portal.properties). 

When a module JAR is read from the autodeploy folder, several things happen in the file system:

* JAR is copied to `LIFERAY_HOME/osgi/modules`.
* JSPs are compiled to `LIFERAY_HOME/work`.
* Runtime state is copied to `LIFERAY_HOME/osgi/state`.
* When using Tomcat, static resources are cached in:
	* `TOMCAT_HOME/work`
	* `TOMCAT_HOME/temp`

It's worth noticing that while the autodeploy method copies the module JARs to `LIFERAY_HOME/osgi/modules`, not all the deployment methods do that, and for the OSGi container runtime, it's not even needed: bytecode in `LIFERAY_HOME/osgi/state` is from where the OSGi container uses the module, and you shouldn't ever edit contents of that folder runtime. If you delete the module JAR in the modules folder, the module will get undeployed.

> Note that the autodeploy mechanism is not cluster-aware; modules deployed to a node's autodeploy folder are deployed only to that node.

#### Deploying from the Control Panel

In scenarios where autodeploy folder access is restricted, custom modules can also be deployed from the Control Panel. This method still uses the autodeploy folder internally, meaning that module JARS will also be copied to the `LIFERAY_HOME/osgi/modules` folder:

<img src="../images/deploy-from-control-panel.png" style="max-height:100%"/>

#### Deploying from the Gogo Shell

Modules can be installed from within the Gogo Shell directly using the standard Felix Shell commands:

```bash{7}
Trying 127.0.0.1...
Connected to localhost.
Escape character is '^]'.
____________________________
Welcome to Apache Felix Gogo

g! install file:/opt/install/com.liferay.training.bundle-1.0.jar
Bundle ID: 961
```

A few things should be noticed with this method:

* This method doesn't start the bundle automatically.
* The module JAR is not copied to `LIFERAY_HOME/osgi/modules`. 

#### Deploying in Dev Studio

When you deploy the modules in Liferay IDE using hot deploy, the autodeploy folder is not used. This method copies the compiled bytecode directly to `LIFERAY_HOME/osgi/state/BUNDLE_ID`.

#### Deploying Using Blade

Modules can be deployed using the Blade CLI. This method detects the running JVM automatically, building and deploying the module directly to `LIFERAY_HOME/osgi/state/BUNDLE_ID`. This method can only be used for JARS and not for legacy WARS:

```bash
liferay@liferay$ blade deploy
```

There are some advantages in using this method in your development environment. When run in the folder hierarchy, this command can deploy all the modules in the subdirectories too. The `blade watch` command is useful if you want to use the "hot deploy" feature directly from the _Command Line_. This command watches changes in the source folder and deploys changes automatically:

```bash
liferay@liferay$ blade deploy -w
```

<br />

#### Deploying Using the Gradle Wrapper Script

This method is meant to be used with Liferay Workspace with a Tomcat bundle. It tries to copy the compiled module JAR into `WORKSPACE_DIR/bundles/osgi/modules`:

```bash
liferay@liferay$ ./gradlew -w
```

#### Deploying Java EE Style WAR Modules

Liferay supports deploying legacy Java EE WAR-style web applications into the OSGi Container. When a WAR file is copied to the autodeploy folder, it gets automatically converted into a WAB bundle, which is then deployed to the OSGi Container. The WAB is an OSGi bundle and stands for *Web Application Bundle*. The WAB is specified in OSGi Compendium.

Liferay's WAB conversion mechanism creates an OSGi-compatible `MANIFEST.MF` file and a compatible folder structure. When a WAR is deployed, the following happens:

1. A WAR archive is copied to `LIFERAY_HOME/deploy`.
1. Liferay generates a WAB bundle on the fly.
1. Bundle runtime bytecode is created in `LIFERAY_HOME/osgi/state/BUNDLE_ID`.
1. The WAR archive gets stored in `LIFERAY_HOME/osgi/war`.

By default, the generated WABs are not stored but you can change the behavior in portal properties:

```properties
module.framework.web.generator.generated.wabs.store=true
module.framework.web.generator.generated.wabs.store.dir=${module.framework.base.dir}/wabs
```

#### Note on Marketplace Applications

Liferay Marketplace applications are packaged in Liferay's proprietary LPKG package format. Although the autodeployment process of LPKG packages is the same as for any deployable package type, the LPKG packages are stored in `LIFERAY_HOME/osgi/marketplace`.

<br />

#### Undeploying Modules Overview

Let's take a look at the common different undeployment methods and some caveats in using them.

#### Deleting a Module JAR

If the deployment method copied the JAR to `LIFERAY_HOME/osgi/modules/MODULE.jar`, removing this file undeploys the module automatically. This is the most common use case.

#### Undeploying from the Control Panel

Undeploying the module from the Control Panel removes the module state from `LIFERAY_HOME/osgi/state`, but does not remove the module JAR from `LIFERAY_HOME/osgi/modules`:

<img src="../images/undeploy-from-control-panel.png" style="max-height:100%"/>

A couple things are worth noticing when this method is used. Undeploying from the Control Panel adds an entry for the Blacklister module, preventing a reinstall of the module. Before you can reinstall, you have to remove the blacklist entry. Another thing is that removing the blacklisting automatically reinstalls the module if the JAR is still present in `LIFERAY_HOME/osgi/modules`:

<img src="../images/blacklist-entry.png" style="max-height:100%"/>

#### Undeploying from Gogo Shell

Modules can be uninstalled directly from the OSGi container using the `uninstall` command. This method does not remove the module JAR from `LIFERAY_HOME/osgi/modules`:

<br />

```bash
Trying 127.0.0.1...
Connected to localhost.
Escape character is '^]'.
____________________________
Welcome to Apache Felix Gogo

g! uninstall 961
```

<br />

#### Module Deployment Recap

Understanding the file-level changes during deployment helps to troubleshoot deployment-related issues. The following diagrams summarize the changes during the deployment process:

<img src="../images/deploy-process.png" style="max-height:100%"/>

As seen in the folder structure:

<img src="../images/deployment-related-directories.png" style="max-height:100%"/>

#### Troubleshooting Deployment Issues

Steps to clean the platform and the module state completely are generally as follows:

1. Undeploy the module.
1. Shut down the portal.
1. Delete the module JAR, WAR, or LPKG.
1. Delete the module resources from `LIFERAY_HOME/osgi/state`.
1. Delete the module configuration file `LIFERAY_HOME/osgi/configs`.
1. Delete the compiled JSPs from `LIFERAY_HOME/work`.
1. Delete the content application server's temp folders. In Tomcat:
	* `TOMCAT_HOME/temp/`
	* `TOMCAT_HOME/work/`

Let's discuss some of the common module deployment-related scenarios.

#### Undeployed Module Reappears After Restart

#### Symptoms

The undeployed module is installed and active after restart.

#### Possible Cause

The module JAR, WAR, or LPKG file was not removed by the undeploy method.

#### Suggested Solution

Delete the module JAR, LPKG, or WAR file from the respective folder.

#### Module Redeployment Fails

#### Symptoms

Redeploying a module fails. The module is either not deployed at all or just doesn't start.

#### Possible Cause

An old module JAR file might be blocking the redeployment. This might happen, for example, if you were using Gradle deploy or autodeploy and __undeployed__ the module from within Gogo Shell, which doesn't remove the module JAR. There might also be a blacklist entry for the module.

#### Possible Solutions

* Delete the module JAR from `LIFERAY_HOME/osgi/modules`.
* Check blacklisting settings in *Control Panel → Configuration → System Settings → Platform → Bundle Blacklist*.

### Module Resources Not Refreshing

#### Symptoms

Redeploying the module won't refresh static resources like CSS and JavaScript files or compiled JSP files. 

#### Possible Cause

Caching settings might prevent resources from refreshing. There might also be a system time skew or faulty timezone settings letting the system know that current resources are newer than the updated ones.

#### Possible Solutions

* Check that the portal is running on development mode. 
* Check that both the development and server machine clocks are synchronized. 
* Check that the portal JVM is running on either the GMT or UTC timezone.

To clear the JSP cache in production, you can also try to clear the direct servlet cache from *Control Panel → Server Administration → Resources → Clear the direct servlet cache*.

#### Module Gets Installed But Doesn't Start

This issue type usually falls into two possible categories: 

1. Unsatisfied module dependencies
1. Unsatisfied module requirements

#### Unsatisfied Module Dependencies

#### Symptoms

The module deploys but remains in the *installed* state and doesn't become *active*.

#### Possible Cause

The module didn't make it to the *resolved* state because some of the module's dependencies are missing or couldn't get activated. This might also happen because of the wrong dependency compile scope.

#### Possible Solutions

* Check the compile scopes in `build.gradle` if any resource should be included in the build (`compileInclude`).
* Use the Gogo `diag` command to find any missing dependencies in the container.

#### Unsatisfied Module Requirements

#### Symptoms

The module is in the *active* state, but some of its service components seem not to be working. 

#### Possible Cause

OSGi bundles and components have different lifecycles; a bundle may start even if some of its components do not. Usually this happens when some of the component's references (`@Reference`) are not satisfied, i.e., not found or started. There might also be a circular reference. 

#### Possible Solutions

* Use the Gogo Shell to find the failing component id by running `ds:unsatisfied BUNDLE_ID`. Then run `scr:info COMPONENT_ID` to find unsatisfied references.
Find out why the reference was not available.
* Run `ds:softCircularDependency` to detect circular references.