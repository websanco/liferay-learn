---
description: 06 - Real World Application
title: Logging
order: 10
---

## Logging

Logging is a powerful method for emitting application metrics and statistics and for helping with troubleshooting and resolving production issues. When properly used, logging improves application quality and maintainability. Examples of use cases:

* Adding additional, contextual information like user ID or thread ID to stack traces
* Collecting and sending metrics like execution timers
* Emitting a warning when a threshold, for example, for group count, is exceeded

Although the role of logging in troubleshooting can be essential, in many cases, logging doesn't expose the root cause, but just detects a problem. Debugging helps find the underlying problem.

#### Overview

The following out-of-the-box options are available to implement logging in your custom modules:

* Java native logger 
* Liferay native logger
* [SLF4J](https://www.slf4j.org/) logger 
* OSGi Log Service

Except for the Java native logger, all the options use SLF4J and [Log4J](https://logging.apache.org/log4j/2.x/) as an implementation in the background. Using Java native logging is usually not recommended because of its limitations and performance compared to SLF4J implementations.

Let's take a look at the different ways to invoke logging. 

#### Using Liferay Native Logger 

Liferay native logger is called via `LogFactoryUtil.getLog(CLASS_NAME)`. 

```java
package com.liferay.training.sample.log;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.math.BigDecimal;

public class EmployeeHandler {

	public void setSalary(BigDecimal salary) {
		
		if (_log.isInfoEnabled()) {
			if (salary.compareTo(MAX_SALARY) == 1) {
				_log.info("Alert: suspiciously high salary: "  + salary  + ".");
			}
		}
		
		_salary = salary;
	}

	private BigDecimal _salary;

	public static final BigDecimal MAX_SALARY;
	
	static {
		MAX_SALARY = new BigDecimal(10000.0); 
	}
	
	private static final Log _log = LogFactoryUtil.getLog(EmployeeHandler.class);

}
```

#### Using SLF4J Logger

In this approach, you'd first declare the dependency for the SLF4J API. The logger could then be invoked by calling the SLF4J `LoggerFactory`, as in the example below. This logging approach is the recommended one, as it doesn't bind the code to any specific implementation:

<br />

**build.gradle**
```groovy
compileOnly group: "org.slf4j", name: "slf4j-api", version: "1.7.2"
```

**Java class**
```java
package com.liferay.training.sample.log;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeHandler {

	public void setSalary(BigDecimal salary) {

		if (_log.isInfoEnabled()) {
			if (salary.compareTo(MAX_SALARY) == 1) {
				_log.info("Alert: suspiciously high salary: "  + salary  + ".");
			}
		}

		_salary = salary;
	}

	private BigDecimal _salary;

	public static final BigDecimal MAX_SALARY;

	static {
		MAX_SALARY = new BigDecimal(10000.0);
	}

	private static final Logger _log = LoggerFactory.getLogger(EmployeeHandler.class);

}
```

#### OSGi Log Service

OSGi Log Service is a message logger service for the OSGi framework, specified in the OSGi Compendium. OSGi Log Service has three main components:

* __LogService__: service interface for storing logs
* __LogReaderService__: service interface for reading and dispatching log entries
* __LogListener__: interface for the listener of log entry objects 

In Liferay's Equinox Log Bridge implementation, there's an SLF4J log listener implementation using Log4J as the back-end:

<img src="../images/osgi-logservice-liferay.png" style="max-height:60%"/>

OSGi LogService can be referenced with Declarative Services via the OSGi `@Reference` annotation:

```java
package com.liferay.training.sample.log;

import java.math.BigDecimal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

@Component(
	service = EmployeeHandlerService.class
)
public class EmployeeHandlerServiceImpl implements EmployeeHandlerService {

	@Override
	public void setSalary(BigDecimal salary) {
		if (salary.compareTo(MAX_SALARY) == 1) {
			_log.log(LogService.LOG_INFO, "Alert: suspiciously high salary: " + salary + ".");
		}
	}

	private BigDecimal _salary;

	public static final BigDecimal MAX_SALARY;

	static {
		MAX_SALARY = new BigDecimal(10000.0);
	}

	@Reference
	private LogService _log;
}
```

For an OSGi LogService to start Liferay logging, there has to be a level definition. There are two ways to define this:

* `MODULE_ROOT/src/main/META-INF/module-log4j.xml`
* *Control Panel → Configuration → Server Administration → Log Levels*

> It should be noticed that this logging approach is limited to OSGi components.

#### Configuring Logging

Logging levels can be globally configured platform-wide from the Control Panel. Changes made there won't, however, persist after restart:

<img src="../images/control-panel-log-level-configuration.png" style="max-height:100%"/>

Persistent level settings as well as all the other LOG4J configuration settings like appenders are set with `LIFERAY_WEBAPP_ROOT/WEB-INF/classes/META-INF/portal-log4j-ext.xml`. In the example below, the level for the `com.liferay.blogs` is set to `INFO`:

```xml
<?xml version="1.0"?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

	<appender name="FILE" class="org.apache.log4j.rolling.RollingFileAppender">
		<rollingPolicy class="org.apache.log4j.rolling.TimeBasedRollingPolicy">
			<param name="FileNamePattern" value="@liferay.home@/logs/sql.%d{yyyy-MM-dd}.log" />
		</rollingPolicy>
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d{ABSOLUTE} %-5p [%c{1}:%L] %m%n" />
		</layout>
	</appender>

	<logger name="com.liferay.blogs">
		<priority value="INFO" />
		<appender-ref ref="FILE" />
	</logger>
	
</log4j:configuration>
```

On a module level, you can define settings for a single module or multiple modules within a module in `MODULE_ROOT/src/main/resources/META-INF/module-log4j.xml` or outside the module , in `LIFERAY_HOME/osgi/log4j/BUNDLE_SYMBOLIC_NAME-log4j-ext.xml`. 

<img src="../images/module-log4j.png" style="max-height:100%"/>

In case of fragment bundles, levels for the host bundle can be defined in the fragment bundle's `MODULE_HOME/META-INF/module-log4j-ext.xml`. 

Below is an example of the `module-log4j.xml` configuration file. Note the package-naming syntax difference when configuring the OSGi Log Service and native or SLF4J loggers: the OSGi Log Service-related categories have to begin with `osgi.logging`, and the dots in the package name have to be replaced with underscores. This same syntax applies when configuring through the *Control Panel*, too:

```xml
<?xml version="1.0"?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

	<!-- OSGi Log Service logger configuration -->

	<category name="osgi.logging.com_liferay_training_sample.logservice">
		<priority value="INFO" />
	</category>
 
	<!-- Native or SLF4J API logger configuration -->

	<category name="com.liferay.training.sample.">
		<priority value="INFO" />
	</category>
	
</log4j:configuration>
```

#### Other Logging-Related Settings

Although SQL logging is not directly related to Liferay's platform, you can enable logging for Hibernate queries in portal-ext.properties with the following setting:

```xml
hibernate.show_sql=true
```

JavaScript logging can accordingly be enabled with:

```xml
javascript.log.enabled=true
```

> Remember to disable Hibernate logging in production systems as it produces excessive amount of data.

#### Wrapping it Up

If you choose to use platform-provided logging functionalities, using SLF4J Logger is recommended for the best code portability. 

The logging approach should always be designed and standardized properly. A misuse of logging severities, not readable log messages, or missing contextual information do not necessarily bring any extra value or quality to the application. Properly designed logging can, on the other hand, make applications much easier to maintain and dramatically help with troubleshooting issues.

Too extensive and excessive logging severely impairs application performance. As a rule of thumb, remember also to check if a logging level is enabled before calling the logger, because the clause inside the log call is otherwise evaluated and may impact performance:

```java
if (_log.isDebugEnabled()) {
    _log.debug(DO_AN_EXPENSIVE_FUNCTION_CALL);
}
```

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>_____________ is a powerful method for emitting _____________  and for helping with _____________ production issues.</li>
	<li>For the best code portability, using _____________ logger is recommended.</li>
	<li>Too extensive and excessive logging severely _____________ application performance.</li>
</ul> 
</div>