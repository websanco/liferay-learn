# Building Liferay Source 

This section aims to provide instructions for building Liferay Portal from source as quickly as possible. Using a nightly snapshot bundle no longer requires a full build using *ant all* which will save a lot of time when building Liferay Portal.

The first step is to ensure you have the tooling installed required to build Liferay Portal. Building Liferay Portal requires:

* Apache Ant
* Gradle
* Liferay Blade CLI

## Building Liferay Portal

The next step is to fork the *liferay-portal* repo from Github and configure the build to use a nightly snapshot bundle.

To build Liferay Portal from source, do the following:

* Clone the *liferay-binaries-cache-2020* repo to speed up builds:

```
git clone https://github.com/liferay/liferay-binaries-cache-2020 --branch master --single-branch --depth 1
```

* Fork the liferay-portal repo on Github: https://github.com/liferay/liferay-portal
* Clone the forked repo's master branch:

```
git clone https://github.com/<github-username>/liferay-portal
```

* Add the main liferay-portal repo as an upstream for fetching changes:

```
git remote add upstream https://github.com/liferay/liferay-portal
```

* Run from liferay-portal dir:

```
ant compile install-portal-snapshots
ant snapshot-bundle
git checkout `cat ../bundles/.githash` -b my-custom-change
```

* Run a second time to sync snapshots with snapshot hash:

```
ant compile install-portal-snapshots
```

Liferay core components can now be built and deployed to the snapshot bundle.  If a core component is built, then the bundle will need to be restarted as the components are not hot swappable.

* To build a core project (portal-impl, portal-kernel, etc) run:

```
ant deploy
```

* Start the snapshot bundle from *liferay-portal* directory:

```
../bundles/tomcat-9.0.17/bin/startup.sh | bat
```

A module can now be built from within liferay-portal without recompiling the whole platform. You must complete the steps above before compiling a module since it initializes the build environment.

* To build a module (portal-workflow-web, message-boards-web), run:

```
blade gw deploy
```

The following messages should appear in the bundle logs:

```
2019-05-20 20:25:08.613 INFO [fileinstall-/Users/jamie/Repos/bundles/osgi/portal][BundleStartStopLogger:42] STOPPED com.liferay.portal.workflow.web_3.0.0 [248]
```

```
2019-05-20 20:25:09.312 INFO [Refresh Thread: Equinox Container: a8a147ab-cda3-4184-b37e-17724c1005f4][BundleStartStopLogger:39] STARTED com.liferay.portal.workflow.web_3.0.0 [248]
```

## Making Changes

When making changes, it's best to start off by creating a ticket in [JIRA](https://issues.liferay.com/secure/Dashboard.jspa) and referencing the ticket number from within any commits and pull requests.

## JIRA

Create a ticket in JIRA by doing the following:

* Sign up for a [JIRA Account](https://issues.liferay.com/secure/Dashboard.jspa) to track progress on the feature, improvement, or bug fix you want to implement. We'll refer to these as *issues* from now on.
* [Submit a ticket](https://issues.liferay.com/secure/Dashboard.jspa) for your issue. Make sure to define/complete the below actions for your ticket.
* Describe the issue clearly. If it is a bug, include steps to reproduce it.
* Select an appropriate category for the issue.
* Select the earliest version of the product affected by the issue.
* Respond to the Contributor License Agreement displayed by clicking the *Contribute Solution button*.
* If a ticket already exists for the issue, participate via the existing ticket.

## Github

Submit your custom changes to Github using the following process:

* Commit logical units of work including a reference to your ticket in JIRA. For example: `LPS-83432 Make the example in CONTRIBUTING imperative and concrete`
* Test your changes thoroughly! Consider the wide variety of operating systems, databases, application servers, and other related technologies Liferay Portal supports. Make sure your changes in one environment don't break something in another environment.

Before pushing your branch to your fork on Github, it's a good idea to rebase on the updated version of upstream/master

* Fetch latest commits from master:

```
git fetch upstream
```

* Fetch latest snapshot bundle:

```
ant snapshot-bundle
```

* Rebase on latest snapshot revision:

```
git rebase `cat ../bundles/.githash`
```

* Push changes in your branch to your fork:

```
git push origin my-custom-change
```

* Submit the pull request to the liferay/liferay-portal repo.
* In the LPS ticket, provide a link to your GitHub pull request
  
You're done! Well, not quite---be sure to respond to comments and questions to your pull request until it is closed.