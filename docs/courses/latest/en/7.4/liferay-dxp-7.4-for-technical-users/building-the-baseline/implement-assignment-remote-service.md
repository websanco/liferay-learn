# Implement Assignment Remote Service

Coming Soon!

<!--

#### Exercise Goals
- Declare dependencies
- Implement the facade methods for the local service in the <code>AssignmentServiceImpl.java</code>
- Do a final code review
- Rebuild and deploy the service

</div>
	
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

2. **Implement** the façade methods in the class as follows:

  ```java
  public Assignment addAssignment(
    long groupId, String title, String description,
    Date dueDate, ServiceContext serviceContext)
    throws PortalException {
    return assignmentLocalService.addAssignment(
       groupId, title, description, dueDate, serviceContext);
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
    long assignmentId, String title, String description,
    Date dueDate, ServiceContext serviceContext)
    throws PortalException {
    return assignmentLocalService.updateAssignment(
       assignmentId, title, description, dueDate, serviceContext);
  }
  ```

#### Final Code Review
1. **Resolve** missing imports.
   * Note: You can ignore any errors for unimplemented methods as these should be addressed in later modules. 
2. **Fix** indents and spacing by using automatic code formatting.
3. **Save** the file.

The complete file will look like this:

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
   
   public Assignment addAssignment(
      long groupId, String title, String description,
      Date dueDate, ServiceContext serviceContext)
      throws PortalException {
      return assignmentLocalService.addAssignment(
         groupId, title, description, dueDate, serviceContext);
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
      long assignmentId, String title, String description,
      Date dueDate, ServiceContext serviceContext)
      throws PortalException {
      return assignmentLocalService.updateAssignment(
         assignmentId, title, description, dueDate, serviceContext);
   }
}
```

#### Rebuild and Deploy the Service
1. **Run** the `buildService` Gradle task to regenerate the service.
* **Start** your Docker container if it's not running.
* **Run** `../gradlew deploy` to deploy the modules.

You should see a success log message if modules were deployed successfully:

```
2019-03-20 11:31:59.667 INFO  [pipe-start 984][BundleStartStopLogger:39] STARTED com.liferay.training.gradebook.api_1.0.0 [984]
2019-03-20 11:32:02.573 INFO  [pipe-start 985][BundleStartStopLogger:39] STARTED com.liferay.training.gradebook.service_1.0.0 [985]
```

-->