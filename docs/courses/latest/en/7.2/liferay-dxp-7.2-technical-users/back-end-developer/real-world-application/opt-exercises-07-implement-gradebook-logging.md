---
description: 6 - Real World Application
title: Implement Gradebook Logging
order: 7
---

<h2 class="exercise">Optional Exercise</h2>

## Implement Gradebook Logging

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Add the SLF4J API dependency to the service module's <code>build.gradle</code></li>
		<li>Add logging code to the Gradebook service</li>
		<li>Create and configure with <code>module-log4j.xml</code></li>
	</ul>
</div>

#### Add the SLF4J API dependency to the Service Module

1. **Open** the `build.gradle` of the *gradebook-service* module.
2. **Add** the dependency for SLF4J in the `dependencies` block:

```groovy
compileOnly group: "org.slf4j", name: "slf4j-api"
```

#### Add Logging Code to the Gradebook Service

1. **Open** the *com.liferay.training.gradebook.service.impl.AssignmentLocalServiceImpl* class in the *gradebook-service* module.
1. **Add** logger variable instantiation to the very end of the class:
	```java
	private static final Logger _logger = LoggerFactory.getLogger(AssignmentLocalServiceImpl.class);
	```
1. **Implement** logging code to the `addAssignment()` method:
	```java
	if (_logger.isInfoEnabled()) {
		_logger.info("User " + userId + " is adding an assignment.");
	}
	```
1. **Organize** imports and save the class. Changes should be deployed automatically.

If you test adding an assignment, you won't see any messages in the log yet because the logging level for the package `com.liferay` is set by default to `ERROR`. Next, we will add a Log4J configuration file and set the logging level for debugging purposes to `DEBUG`.

#### Create and Configure Logging with `module-log4j.xml`

1. **Create** a logging configuration file `src/main/resources/META-INF/module-log4j.xml` in the *gradebook-service* module.
1. **Implement** the file as follows:
	```xml
	<?xml version="1.0"?>
	<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
	<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
	
		<!-- Set logging level to info -->
	
	    <category name="com.liferay.training.gradebook.service.impl">
	        <priority value="INFO" />
	    </category>
	</log4j:configuration>
	```
1. **Create** a new Assignment in the Gradebook application. You should see a similar entry in your log:

```xml
	2018-09-24 09:18:50.880 INFO  [http-nio-8080-exec-2][AssignmentLocalServiceImpl:148] User 20139 is adding an assignment.
```
