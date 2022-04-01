---
description: Real World Application
title: Implement Assignment Remote Service
order: 3
---

<h2 class="exercise">Exercises</h2>

## Implement Assignment Remote Service

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Declare dependencies</li>
		<li>Implement the façade methods for the local service in the <code>AssignmentServiceImpl.java</code></li>
		<li>Do a final code review</li>
		<li>Rebuild and deploy the service </li>
	</ul>
</div>

#### Declare Dependencies

For our implementation, we need to make the Servlet and Portlet API available:

1. **Open** the `build.gradle` of *gradebook-service*.
1. **Implement** the new dependencies as follows:
	```groovy
	compileOnly group: "javax.portlet", name: "portlet-api"
	compileOnly group: "javax.servlet", name: "javax.servlet-api"
	```
	
#### Implement the Façade Methods

1. **Open** the `AssignmentServiceImpl.java` class. The empty class looks like this:
	```java
	/**
	 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
	 *
	 * This library is free software; you can redistribute it and/or modify it under
	 * the terms of the GNU Lesser General Public License as published by the Free
	 * Software Foundation; either version 2.1 of the License, or (at your option)
	 * any later version.
	 *
	 * This library is distributed in the hope that it will be useful, but WITHOUT
	 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
	 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
	 * details.
	 */
	
	package com.liferay.training.gradebook.service.impl;
	
	import com.liferay.portal.aop.AopService;
	import com.liferay.training.gradebook.service.base.AssignmentServiceBaseImpl;
	
	import org.osgi.service.component.annotations.Component;
	
	/**
	 * The implementation of the assignment remote service
	 *
	 * <p>
	 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.training.gradebook.service.AssignmentService</code> interface.
	 *
	 * <p>
	 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
	 * </p>
	 *
	 * @author Brian Wing Shun Chan
	 * @see AssignmentServiceBaseImpl
	 */
	@Component(
		property = {
			"json.web.service.context.name=gradebook",
			"json.web.service.context.path=Assignment"
		},
		service = AopService.class
	)
	public class AssignmentServiceImpl extends AssignmentServiceBaseImpl {
	
		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never reference this class directly. Always use <code>com.liferay.training.gradebook.service.AssignmentServiceUtil</code> to access the assignment remote service.
		 */
	}
	```
1. **Implement** the façade methods in the class as follows:
	```java
	public Assignment addAssignment(
		long groupId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {

		return assignmentLocalService.addAssignment(
			groupId, titleMap, descriptionMap, dueDate, serviceContext);
	}

	public Assignment deleteAssignment(long assignmentId)
		throws PortalException {

		Assignment assignment =
			assignmentLocalService.getAssignment(assignmentId);

		return assignmentLocalService.deleteAssignment(assignment);
	}

	public Assignment getAssignment(long assignmentId)
		throws PortalException {

		Assignment assignment =
			assignmentLocalService.getAssignment(assignmentId);

		return assignment;
	}
	
	public List<Assignment> getAssignmentsByGroupId(long groupId) {
	
		return assignmentPersistence.findByGroupId(groupId);
	}

	public List<Assignment> getAssignmentsByKeywords(
		long groupId, String keywords, int start, int end,
		OrderByComparator<Assignment> orderByComparator) {

		return assignmentLocalService.getAssignmentsByKeywords(
			groupId, keywords, start, end, orderByComparator);
	}

	public long getAssignmentsCountByKeywords(long groupId, String keywords) {

		return assignmentLocalService.getAssignmentsCountByKeywords(
			groupId, keywords);
	}

	public Assignment updateAssignment(
		long assignmentId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {

		return assignmentLocalService.updateAssignment(
			assignmentId, titleMap, descriptionMap, dueDate, serviceContext);
	}
	```
1. **Resolve** missing imports.

#### Final Code Review

The complete files will look like this:

```groovy
dependencies {
	compileOnly group: "com.liferay", name: "com.liferay.petra.lang"
	compileOnly group: "com.liferay", name: "com.liferay.petra.string"
	compileOnly group: "com.liferay", name: "com.liferay.portal.aop.api"
	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
	compileOnly group: "javax.portlet", name: "portlet-api"
	compileOnly group: "javax.servlet", name: "javax.servlet-api"
	compileOnly group: "org.osgi", name: "org.osgi.annotation.versioning"
	compileOnly group: "org.osgi", name: "org.osgi.core"
	compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"
	compileOnly project(":modules:gradebook:gradebook-api")
}

buildService {
	apiDir = "../gradebook-api/src/main/java"
}

group = "com.liferay.training.gradebook"
```

```java
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.training.gradebook.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.base.AssignmentServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the assignment remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.training.gradebook.service.AssignmentService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssignmentServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=gradebook",
		"json.web.service.context.path=Assignment"
	},
	service = AopService.class
)
public class AssignmentServiceImpl extends AssignmentServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>com.liferay.training.gradebook.service.AssignmentServiceUtil</code> to access the assignment remote service.
	 */
	public Assignment addAssignment(
		long groupId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {

		return assignmentLocalService.addAssignment(
			groupId, titleMap, descriptionMap, dueDate, serviceContext);
	}

	public Assignment deleteAssignment(long assignmentId)
		throws PortalException {

		Assignment assignment =
			assignmentLocalService.getAssignment(assignmentId);

		return assignmentLocalService.deleteAssignment(assignment);
	}

	public Assignment getAssignment(long assignmentId)
		throws PortalException {

		Assignment assignment =
			assignmentLocalService.getAssignment(assignmentId);

		return assignment;
	}

	public List<Assignment> getAssignmentsByGroupId(long groupId) {

		return assignmentPersistence.findByGroupId(groupId);
	}

	public List<Assignment> getAssignmentsByKeywords(
		long groupId, String keywords, int start, int end,
		OrderByComparator<Assignment> orderByComparator) {

		return assignmentLocalService.getAssignmentsByKeywords(
			groupId, keywords, start, end, orderByComparator);
	}

	public long getAssignmentsCountByKeywords(long groupId, String keywords) {

		return assignmentLocalService.getAssignmentsCountByKeywords(
			groupId, keywords);
	}

	public Assignment updateAssignment(
		long assignmentId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {

		return assignmentLocalService.updateAssignment(
			assignmentId, titleMap, descriptionMap, dueDate, serviceContext);
	}	
}
```

#### Rebuild and Deploy the Service

Now it's time to deploy our service to the server. 

1. **Run** the `buildService` Gradle task to regenerate the service.
1. **Start** the server if it's not running.
1. **Drag** the *gradebook-api* and *gradebook-service* modules onto the Liferay server to deploy the modules.

You should see a success log message if modules were deployed successfully:

```
2019-03-20 11:31:59.667 INFO  [pipe-start 984][BundleStartStopLogger:39] STARTED com.liferay.training.gradebook.api_1.0.0 [984]
2019-03-20 11:32:02.573 INFO  [pipe-start 985][BundleStartStopLogger:39] STARTED com.liferay.training.gradebook.service_1.0.0 [985]
```
