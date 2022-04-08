---
description: 6 - Real World Application
title: Troubleshoot Module Deployment
order: 10
---

<h2 class="exercise">Optional Exercise</h2>

## Troubleshoot Module Deployment

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Deploy the exercise modules</li>
		<li>Troubleshoot the failing deployment</li>
	</ul>
</div>

#### Deploy the Exercise Modules

1. **Copy** the two modules `failing-api` and `failing-service` from the provided `exercise-files/06-real-world-application/troubleshoot-module-deployment` folder to you `training-workspace/modules` folder.
1. **Run** Gradle refresh on the Liferay Workspace
1. **Deploy** the modules to the Liferay server

> You'll notice that the modules won't start and you won't even get any log message. 

#### Troubleshoot the Failing Deployment

1. **Login** to Gogo shell and list bundles using the `lb` command. You'll notice that the exercise modules *failing-api* and *failing-service* are in the `installed` state.
    > Remember that if a bundle can't go into the `resolved` state, there's a dependency problem. 
2. **Use** the Gogo commands we learned in the *Module 4 - Managing OSGi Bundles*  and find out what the two problems are.  As you work with the code, any changes will be hot deployed automatically. 

The exercise is completed when you get the following message in the log:

```bash
2019-04-08 11:12:54.374 INFO  [Thread-2496][BundleStartStopLogger:42] STARTED com.liferay.training.deployment.failing.service_1.0.0 [979]
2019-04-08 11:12:54.374 INFO  [Thread-2496][FailingService:36] ################################### 
2019-04-08 11:12:54.374 INFO  [Thread-2496][FailingService:37] Task completed succesfully!
2019-04-08 11:12:54.374 INFO  [Thread-2496][FailingService:38] ################################### 
2019-04-08 11:12:54.703 INFO  [Thread-2496][BundleStartStopLogger:39] STARTED com.liferay.training.deployment.failing.api_1.0.0 [979]
```

> __Hint:__ You might want to start with the Gogo shell tool for diagnosing dependency problems. You can ask your trainer any time for hints.
