---
description: 2 - OSGi Basics
title: Change Lifecycle State of an OSGi Bundle
order: 2
---

<h2 class="exercise">Exercises</h2>

## Change Lifecycle State of an OSGi Bundle

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
	<li>Start and stop the OSGi bundle</li>
		<ul>
			<li>Stop the Hello OSGi bundle</li>
			<li>Start the Hello OSGi bundle</li>
		</ul>
	</ul>
</div>

#### Stop the Hello OSGi Bundle

If the *Hello OSGi* application is not running, right-click the *hello-osgi* project to open the context menu and click *Run As → OSGi Framework*. You should see the *Hello World!!* message on the console:

<br />

<img src="../images/run-application.png" style="max-height: 25%;"/>

<br />

Use Gogo Shell to check the lifecycle state of our bundle:
1. **Enter** `lb` in the console.
	* You should see a complete list of the bundles deployed to the OSGi container and their state.
	* *Hello OSGi* should be in the *Active* state, which means that it has been deployed, installed, and started successfully:
	<img src="../images/check-bundle-state.png" style="max-height: 14%;"/>
	* Notice the ID of the bundle in the first column of the list.
1. **Enter** `stop [BUNDLE_ID]` to stop the _Hello OSGi_ bundle.
	* You should get a *Goodbye World!!* message from the `HelloBundleActivator` class on the console.
	<img src="../images/stop-the-bundle.png" style="max-height: 14%;"/>
1. **Enter** `lb` to check the bundle state again.
	* The bundle should now be in the *resolved* state, which means that all its dependencies have been satisfied, but the bundle is not running. Being in an *installed* state would mean that the bundle was deployed successfully, but some of its dependencies were not satisfied.

#### Start the Hello OSGi Bundle

1. **Enter** `start [BUNDLE_ID]` to start the bundle again.
	* You'll see the *Hello World!!* message from the `HelloBundleActivator` class on the console again:
	<img src="../images/restart-the-bundle.png" style="max-height: 100%;"/>

> Note that restarting the bundle could also have been done with the *refresh* command.
