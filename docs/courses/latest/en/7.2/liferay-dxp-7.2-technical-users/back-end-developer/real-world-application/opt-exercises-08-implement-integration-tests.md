---
description: 6 - Real World Application
title: Implement Integration Tests
order: 8
---

<h2 class="exercise">Optional Exercise</h2>

## Implement Integration Tests

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Gradebook test module</li>
		<li>Declare dependencies</li>
		<li>Configure test settings</li>
		<li>Implement the test class</li>
		<li>Final code review</li>
		<li>Run tests</li>
	</ul>
</div>

#### Create a Gradebook Test Module

**Option 1: Use the Command Line Blade tools**

1. **Open** command line shell in your Liferay Workspace `modules` folder.
1. **Run** command:
```bash
blade create -t activator -v 7.1  gradebook-test
```
1. **Run** Gradle refresh on the IDE.

**Option 2: Use Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__: "gradebook-test"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: activator
1. **Click** *Finish* to close the wizard.

Clean the unnecessary resources created by the wizard and create a new source folder for integration tests:

1. **Delete** the `src/main` folder.
1. **Create** a new __source folder__ `src/testIntegration` (watch the case).

#### Declare Dependencies

1. **Open** the `build.gradle`.
1. **Replace** the dependencies section with the following:

```groovy
testIntegrationCompile group: "com.liferay.portal", name: "com.liferay.portal.kernel"
testIntegrationCompile group: "com.liferay", name: "com.liferay.arquillian.extension.junit.bridge", version: "1.0.11"
testIntegrationCompile group: "com.liferay.portal", name: "com.liferay.portal.test", version: "5.3.2"
testIntegrationCompile group: "com.liferay.portal", name: "com.liferay.portal.test.integration", version: "5.1.7"
testIntegrationCompile group: "com.liferay", name: "com.liferay.osgi.util"

testIntegrationCompile group: "javax.portlet", name: "portlet-api"
testIntegrationCompile group: "javax.servlet", name: "javax.servlet-api"
testIntegrationCompile group: "org.springframework", name: "spring-core", version: "4.3.3.RELEASE"

testIntegrationCompile group: "com.liferay", name: "com.liferay.petra.string"
testIntegrationCompile group: "com.liferay", name: "com.liferay.petra.lang"
testIntegrationRuntime group: "com.liferay", name: "com.liferay.petra.concurrent"
testIntegrationRuntime group: "com.liferay", name: "com.liferay.petra.memory"
	
testIntegrationCompile project(":modules:gradebook:gradebook-api")
```

#### Configure Test Settings

Configure the Gradle settings for the test Tomcat bundle and error logging:

1. **Open** the `build.gradle`.
1. **Add** the following configuration after the dependencies:

```java	

testIntegration {
	ignoreFailures = false

	testLogging {
		events "started", "passed", "skipped", "failed", "standardOut", "standardError"
		showExceptions true
		exceptionFormat "full"
		showCauses true
		showStackTraces true
	}
}

// Cleans the test bundle on startup.

setUpTestableTomcat {
	enabled = true
}

startTestableTomcat {
	enabled = true
}

stopTestableTomcat {
	enabled = true
}
```

<br />

#### Implement the Test Class

1. **Create** a new Class `com.liferay.gradebook.service.impl.test.AssignmentLocalServiceImplTest.java` in the `testIntegration` source folder.
1. **Annotate** the class with `@RunWith` to make it an Arquillian test:
	```java
	@RunWith(Arquillian.class)
	public class AssignmentLocalServiceImplTest
	```
1. **Declare** the Liferay JUnit class rule variable :
	```java
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();	
	```
1. **Add** the required references to the end of the class:
	```java
	@Inject
	private AssignmentLocalService _assignmentLocalService;

	@Inject
	private GroupLocalService _groupLocalService;

	@Inject
	private UserLocalService _userLocalService;

	@Inject
	private Portal _portal;
	```
1. **Implement** the test methods as follows:

```java
@Test
public void testAddAssignment() throws Exception {

	int entriesCount = _assignmentLocalService.getAssignmentsCount();

	ServiceContext serviceContext = new ServiceContext();
	serviceContext.setUserId(getDefaultUserId());
	
	Map<Locale, String>titleMap = new HashMap<Locale, String>();
	titleMap.put(new Locale("en", "US"), "My Test Assignment Title");
	
	Map<Locale, String>descriptionMap = new HashMap<Locale, String>();
	titleMap.put(new Locale("en", "US"), "My Description");

	Date dueDate = new Date();
	
	_assignmentLocalService.addAssignment(getGuestGroupId(), titleMap, descriptionMap, dueDate, serviceContext);
			
	assertEquals(++entriesCount, _assignmentLocalService.getAssignmentsCount());		
}

@Test
public void testDeleteAssignment()
	throws Exception {

	int entriesCount = _assignmentLocalService.getAssignmentsCount();

	ServiceContext serviceContext = new ServiceContext();
	serviceContext.setUserId(getDefaultUserId());
	
	Map<Locale, String>titleMap = new HashMap<Locale, String>();
	titleMap.put(new Locale("en", "US"), "My Test Assignment Title");
	
	Map<Locale, String>descriptionMap = new HashMap<Locale, String>();
	titleMap.put(new Locale("en", "US"), "My Description");

	Date dueDate = new Date();
	
	Assignment assignment = _assignmentLocalService.addAssignment(getGuestGroupId(), titleMap, descriptionMap, dueDate, serviceContext);
			
	assertEquals(++entriesCount, _assignmentLocalService.getAssignmentsCount());		
	
	_assignmentLocalService.deleteAssignment(assignment);
	
	assertEquals(--entriesCount, _assignmentLocalService.getAssignmentsCount());		
}

private long getDefaultUserId()
	throws PortalException {

	return UserLocalServiceUtil.getDefaultUserId(
		_portal.getDefaultCompanyId());
}

private long getGuestGroupId()
	throws PortalException {

	String groupName = GroupConstants.GUEST;

	return _groupLocalService.getGroup(
		_portal.getDefaultCompanyId(), groupName).getGroupId();
}
```
	
#### Final Code Review

The complete files will look like this: 

**build.gradle**
```groovy
dependencies {
	testIntegrationCompile group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	testIntegrationCompile group: "com.liferay", name: "com.liferay.arquillian.extension.junit.bridge", version: "1.0.11"
	testIntegrationCompile group: "com.liferay.portal", name: "com.liferay.portal.test", version: "5.3.2"
	testIntegrationCompile group: "com.liferay.portal", name: "com.liferay.portal.test.integration", version: "5.1.7"
	testIntegrationCompile group: "com.liferay", name: "com.liferay.osgi.util"

	testIntegrationCompile group: "javax.portlet", name: "portlet-api"
	testIntegrationCompile group: "javax.servlet", name: "javax.servlet-api"
	testIntegrationCompile group: "org.springframework", name: "spring-core", version: "4.3.3.RELEASE"

	testIntegrationCompile group: "com.liferay", name: "com.liferay.petra.string"
	testIntegrationCompile group: "com.liferay", name: "com.liferay.petra.lang"
	testIntegrationRuntime group: "com.liferay", name: "com.liferay.petra.concurrent"
	testIntegrationRuntime group: "com.liferay", name: "com.liferay.petra.memory"
		
	testIntegrationCompile project(":modules:gradebook:gradebook-api")
}

testIntegration {
	ignoreFailures = false

	testLogging {
		events "started", "passed", "skipped", "failed", "standardOut", "standardError"
		showExceptions true
		exceptionFormat "full"
		showCauses true
		showStackTraces true
	}
}

// Cleans the test bundle on startup.

setUpTestableTomcat {
	enabled = true
}

startTestableTomcat {
	enabled = true
}

stopTestableTomcat {
	enabled = true
}
```

**AssignmentLocalServiceImplTest.java**
```java

package com.liferay.training.gradebook.service.impl.test;

import static org.junit.Assert.assertEquals;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentLocalService;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author liferay
 */
@RunWith(Arquillian.class)
public class AssignmentLocalServiceImplTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddAssignment() throws Exception {

		int entriesCount = _assignmentLocalService.getAssignmentsCount();

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(getDefaultUserId());
		
		Map<Locale, String>titleMap = new HashMap<Locale, String>();
		titleMap.put(new Locale("en", "US"), "My Test Assignment Title");
		
		Map<Locale, String>descriptionMap = new HashMap<Locale, String>();
		titleMap.put(new Locale("en", "US"), "My Description");

		Date dueDate = new Date();
		
		_assignmentLocalService.addAssignment(getGuestGroupId(), titleMap, descriptionMap, dueDate, serviceContext);
				
		assertEquals(++entriesCount, _assignmentLocalService.getAssignmentsCount());		
	}

	@Test
	public void testDeleteAssignment()
		throws Exception {

		int entriesCount = _assignmentLocalService.getAssignmentsCount();

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(getDefaultUserId());
		
		Map<Locale, String>titleMap = new HashMap<Locale, String>();
		titleMap.put(new Locale("en", "US"), "My Test Assignment Title");
		
		Map<Locale, String>descriptionMap = new HashMap<Locale, String>();
		titleMap.put(new Locale("en", "US"), "My Description");

		Date dueDate = new Date();
		
		Assignment assignment = _assignmentLocalService.addAssignment(getGuestGroupId(), titleMap, descriptionMap, dueDate, serviceContext);
				
		assertEquals(++entriesCount, _assignmentLocalService.getAssignmentsCount());		
		
		_assignmentLocalService.deleteAssignment(assignment);
		
		assertEquals(--entriesCount, _assignmentLocalService.getAssignmentsCount());		
	}

	private long getDefaultUserId()
		throws PortalException {

		return UserLocalServiceUtil.getDefaultUserId(
			_portal.getDefaultCompanyId());
	}

	private long getGuestGroupId()
		throws PortalException {

		String groupName = GroupConstants.GUEST;

		return _groupLocalService.getGroup(
			_portal.getDefaultCompanyId(), groupName).getGroupId();
	}

	@Inject
	private AssignmentLocalService _assignmentLocalService;

	@Inject
	private GroupLocalService _groupLocalService;

	@Inject
	private UserLocalService _userLocalService;

	@Inject
	private Portal _portal;

}
```

#### Run the Test

1. **Run** the Gradle `testIntegration` task.

Watch the output on the console.
