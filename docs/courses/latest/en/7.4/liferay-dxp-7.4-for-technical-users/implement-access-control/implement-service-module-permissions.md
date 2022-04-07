# Implement Service Module Permissions

Coming Soon!

<!--

#### Exercise Goals

- Create the resource constants class
- Define the permissions
- Define the permissions definition location
- Implement permission resource management in the AssignmentLocalServiceImpl
- Create the permission registration classes
- Implement permission checking in the remote service class AssignmentServiceImpl
- Rebuild the service
- Test the application

</div>

Before proceeding, you **must remove all the test assignments you have created so far**. This is because the existing test entities don't have the resources to support permissioning and will cause errors.

To avoid misspellings in permission properties, we will create a constants class in the *gradebook-api* module.

#### Create the Resource Constants Class
1. **Create** a class `com.liferay.training.gradebook.constants.GradebookConstants` and implement as follows:

	```java
	package com.liferay.training.gradebook.constants;
	public class GradebookConstants {
		public static final String RESOURCE_NAME = "com.liferay.training.gradebook.model";
	}
	```

2. **Add** `com.liferay.training.gradebook.constants` to the exported packages in the `bnd.bnd` file. The file will look like this:

```properties
Bundle-Name: gradebook-api
Bundle-SymbolicName: com.liferay.training.gradebook.api
Bundle-Version: 1.0.0
Export-Package:\
	com.liferay.training.gradebook.constants,\
	com.liferay.training.gradebook.exception,\
	com.liferay.training.gradebook.model,\
	com.liferay.training.gradebook.service,\
	com.liferay.training.gradebook.service.persistence,\
	com.liferay.training.gradebook.validator
-check: EXPORTS
-includeresource: META-INF/service.xml=../gradebook-service/service.xml
```	

By default, permissions are defined in the file called `default.xml`. 

#### Define the Permissions
1. **Create** a folder `src/main/resources/resource-actions` in the *gradebook-service* module.
2. **Create** a file `src/main/resources/resource-actions/default.xml` and implement as follows  (switch to *Source* mode, if needed):

```xml
<?xml version="1.0"?>
<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.4.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_4_0.dtd">
<resource-action-mapping>
	<model-resource>
		<model-name>com.liferay.training.gradebook.model</model-name>
		<portlet-ref>
			<portlet-name>com_liferay_training_gradebook_web_portlet_GradebookPortlet</portlet-name>
		</portlet-ref>
	    <root>true</root>
	    <permissions>
			<supports>
				<action-key>ADD_ENTRY</action-key>
				<action-key>PERMISSIONS</action-key>
			</supports>
			<site-member-defaults />
			<guest-defaults />
			<guest-unsupported>
				<action-key>ADD_ENTRY</action-key>
				<action-key>PERMISSIONS</action-key>
			</guest-unsupported>
		</permissions>
	</model-resource>
	<model-resource>
		<model-name>com.liferay.training.gradebook.model.Assignment</model-name>
		<portlet-ref>
			<portlet-name>com_liferay_training_gradebook_web_portlet_GradebookPortlet</portlet-name>
		</portlet-ref>
		<permissions>
			<supports>
				<action-key>DELETE</action-key>
				<action-key>PERMISSIONS</action-key>
				<action-key>UPDATE</action-key>
				<action-key>VIEW</action-key>
				<action-key>ADD_SUBMISSION</action-key>
				<action-key>EDIT_SUBMISSION</action-key>
				<action-key>DELETE_SUBMISSION</action-key>
				<action-key>GRADE_SUBMISSION</action-key>
				<action-key>VIEW_SUBMISSIONS</action-key>
			</supports>
			<site-member-defaults>
				<action-key>VIEW</action-key>
				<action-key>ADD_SUBMISSION</action-key>
			</site-member-defaults>
			<guest-defaults>
				<action-key>VIEW</action-key>
			</guest-defaults>
			<guest-unsupported>
				<action-key>DELETE</action-key>
				<action-key>PERMISSIONS</action-key>			
				<action-key>UPDATE</action-key>
				<action-key>ADD_SUBMISSION</action-key>
				<action-key>DELETE_SUBMISSION</action-key>
				<action-key>EDIT_SUBMISSION</action-key>
				<action-key>GRADE_SUBMISSION</action-key>
				<action-key>VIEW_SUBMISSIONS</action-key>
			</guest-unsupported>
		</permissions>
	</model-resource>
</resource-action-mapping>	
```
	
> Notice that submission permissions are related to the optional exercises.

#### Define the Permissions Definition Location
1. **Create** a file `src/main/resources/portlet.properties` in the *gradebook-service* module.
2. **Implement** the file as follows:

```properties
resource.actions.configs=/resource-actions/default.xml
```

Permissions need container objects for the model entities. When we create an entity, we need to create and bind a resource object to that. Accordingly, we have to take care of cleaning up the resources when we delete the entity:

#### Implement Permission Resource Management
1. **Open** the class `com.liferay.training.gradebook.service.impl.AssignmentLocalServiceImpl`.
2. **Replace** the `addAssignment()` method with the following:

	```java
	public Assignment addAssignment(
		long groupId, Map<Locale, String> titleMap, String description,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {	
		
		// Validate assignment parameters.

		_assignmentValidator.validate(titleMap, description, dueDate);
		
		// Get group and user.

		Group group = groupLocalService.getGroup(groupId);
		
		long userId = serviceContext.getUserId();
		
		User user = userLocalService.getUser(userId);
		
		// Generate primary key for the assignment.

		long assignmentId =
			counterLocalService.increment(Assignment.class.getName());
			
		// Create assigment. This doesn't yet persist the entity.
		
		Assignment assignment = createAssignment(assignmentId);
			
		// Populate fields.

		assignment.setCompanyId(group.getCompanyId());
		assignment.setCreateDate(serviceContext.getCreateDate(new Date()));
		assignment.setDueDate(dueDate);
		assignment.setDescription(description);
		assignment.setGroupId(groupId);
		assignment.setModifiedDate(serviceContext.getModifiedDate(new Date()));
		assignment.setTitleMap(titleMap);
		assignment.setUserId(userId);
		assignment.setUserName(user.getScreenName());
		
		// Persist assignment to database.

		assignment = super.addAssignment(assignment);

		// Add permission resources.	
			
		boolean portletActions = false;
		boolean addGroupPermissions = true;
		boolean addGuestPermissions = true;
			
		resourceLocalService.addResources(
			group.getCompanyId(), groupId, userId, Assignment.class.getName(),
			assignment.getAssignmentId(), portletActions, addGroupPermissions,
			addGuestPermissions);
		
		return assignment;
	}
	```

3. **Implement** a new signature for deleting assignments as follows:

	```java
	public Assignment deleteAssignment(Assignment assignment)
		throws PortalException {
			
		// Delete permission resources.
			
		resourceLocalService.deleteResource(
			assignment, ResourceConstants.SCOPE_INDIVIDUAL);
		
		// Delete the Assignment
			
		return super.deleteAssignment(assignment);
	}	
	```

4. **Resolve** missing imports.	

Don't worry about the errors when adding a new method. Errors will go away after you rebuild the service.

Now we will create classes for registering the model and top-level resource permissions.

#### Create the Permission Registrar Classes
1. **Create** a class  `com.liferay.training.gradebook.internal.security.permission.resource.definition.AssignmentModelResourcePermissionDefinition` in the *gradebook-service* module.
2. **Implement** as follows:

	```java
	package com.liferay.training.gradebook.internal.security.permission.resource.definition;

	import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
	import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
	import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
	import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
	import com.liferay.portal.kernel.security.permission.resource.WorkflowedModelPermissionLogic;
	import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
	import com.liferay.portal.kernel.service.GroupLocalService;
	import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;
	import com.liferay.training.gradebook.constants.GradebookConstants;
	import com.liferay.training.gradebook.model.Assignment;
	import com.liferay.training.gradebook.service.AssignmentLocalService;

	import java.util.function.Consumer;

	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;
		
	/**
	 * @author liferay
	 */
	@Component(
		immediate = true, 
		service = ModelResourcePermissionDefinition.class
	)
	public class AssignmentModelResourcePermissionDefinition
		implements ModelResourcePermissionDefinition<Assignment> {

		@Override
		public Assignment getModel(long assignmentId)
			throws PortalException {

			return _assignmentLocalService.getAssignment(assignmentId);
		}

		@Override
		public Class<Assignment> getModelClass() {
			
			return Assignment.class;
		}
			
		@Override
		public PortletResourcePermission getPortletResourcePermission() {
			
			return _portletResourcePermission;
		}
			
		@Override
		public long getPrimaryKey(Assignment assignment) {
			
			return assignment.getAssignmentId();
		}
			
		@Override
		public void registerModelResourcePermissionLogics(
			ModelResourcePermission<Assignment> modelResourcePermission,
			Consumer<ModelResourcePermissionLogic<Assignment>> modelResourcePermissionLogicConsumer) {
				
			modelResourcePermissionLogicConsumer.accept(
				new StagedModelPermissionLogic<>(
					_stagingPermission,
					"com_liferay_training_gradebook_web_portlet_GradebookPortlet",
					Assignment::getAssignmentId));

			// Only enable if you use (optional) workflow support

			//	modelResourcePermissionLogicConsumer.accept(
			//		new WorkflowedModelPermissionLogic<>(
			//			_workflowPermission, modelResourcePermission,
			//			_groupLocalService, Assignment::getAssignmentId));
		}

		@Reference
		private AssignmentLocalService _assignmentLocalService;

		@Reference
		private GroupLocalService _groupLocalService;
		
		@Reference(target = "(resource.name=" + GradebookConstants.RESOURCE_NAME + ")")
		private PortletResourcePermission _portletResourcePermission;

		@Reference
		private StagingPermission _stagingPermission;	

		@Reference
		private WorkflowPermission _workflowPermission;
	}
	```

3. **Create** a class for registering the Gradebook portlet resources and top level permissions:
	
	```java
	com.liferay.training.gradebook.internal.security.permission.resource.definition.AssignmentPortletResourcePermissionDefinition
	```

4. **Implement** as follows:

```java
package com.liferay.training.gradebook.internal.security.permission.resource.definition;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.StagedPortletPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.PortletResourcePermissionDefinition;
import com.liferay.training.gradebook.constants.GradebookConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author liferay
 */
@Component(
	immediate = true, 
	service = PortletResourcePermissionDefinition.class
)
public class AssignmentPortletResourcePermissionDefinition
	implements PortletResourcePermissionDefinition {
		
	@Override
	public PortletResourcePermissionLogic[] getPortletResourcePermissionLogics() {
			
		return new PortletResourcePermissionLogic[] {
			new StagedPortletPermissionLogic(
				_stagingPermission,
				"com_liferay_training_gradebook_web_portlet_GradebookPortlet")
		};
	}

	@Override
	public String getResourceName() {
			
		return GradebookConstants.RESOURCE_NAME;
	}

	@Reference
	private StagingPermission _stagingPermission;
	
}
```
	
Now let's implement the permission checking in our remote service class. Let's go through the implementation steps first.

We need references to the portlet and model resource permission services:

```java
@Reference(
	policy = ReferencePolicy.DYNAMIC,
	policyOption = ReferencePolicyOption.GREEDY,
	target = "(model.class.name=com.liferay.training.gradebook.model.Assignment)"
)
private volatile ModelResourcePermission<Assignment>
	_assignmentModelResourcePermission;

@Reference(
	policy = ReferencePolicy.DYNAMIC,
	policyOption = ReferencePolicyOption.GREEDY,
	target = "(resource.name=" + GradebookConstants.RESOURCE_NAME + ")"
)
private volatile PortletResourcePermission _portletResourcePermission;	
```

Note that for a dynamic reference to work, the field **must** be declared with the volatile modifier so that field value changes made by service component runtime are visible to other threads.

Then we'll implement permission checking in `addAssignment()`, `deleteAssignment()` and `updateAssignment()` methods:

```java
public Assignment getAssignment(long assignmentId)
	throws PortalException {

	// Check permissions.

	_assignmentModelResourcePermission.check(
		getPermissionChecker(), assignmentId, ActionKeys.VIEW);

	Assignment assignment =
		assignmentLocalService.getAssignment(assignmentId);
	
	return assignment;
}
```

FindBy finder calls are transformed into *Filtered Finder* queries, which take permissions into account:

```java
assignmentPersistence.filterFindByGroupId(groupId);
```

Take a look at the `AssignmentPersistenceImpl` class and for example `filterFindByGroupId()` method  to see how filtered finders are implemented.

In this exercise, we'll allow everybody to see and search the assignments list by the keyword search method `getAssignmentsByKeywords`. We just don't allow unauthorized users to view, update or delete them.

#### Implement Permission Checking in the Remote Service
1. **Implement** the class `com.liferay.training.gradebook.service.impl.AssignmentServiceImpl.java` as follows (You'll see an error for the `filterFindBy` method because it's not yet generated): 

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
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.training.gradebook.constants.GradebookConstants;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.base.AssignmentServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

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
		long groupId, Map<Locale, String> titleMap, String description,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {

		// Check permissions.
		
		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ENTRY);
		
		return assignmentLocalService.addAssignment(
			groupId, titleMap, description, dueDate, serviceContext);
	}

	public Assignment deleteAssignment(long assignmentId)
		throws PortalException {

		// Check permissions.
		
		_assignmentModelResourcePermission.check(
			getPermissionChecker(), assignmentId, ActionKeys.DELETE);
		
		Assignment assignment =
			assignmentLocalService.getAssignment(assignmentId);

		return assignmentLocalService.deleteAssignment(assignment);
	}

	public Assignment getAssignment(long assignmentId)
		throws PortalException {

		Assignment assignment =
			assignmentLocalService.getAssignment(assignmentId);
		
		// Check permissions.

		_assignmentModelResourcePermission.check(
			getPermissionChecker(), assignment, ActionKeys.VIEW);

		return assignment;
	}
	
	public List<Assignment> getAssignmentsByGroupId(long groupId) {
	
		return assignmentPersistence.filterFindByGroupId(groupId);
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
		long assignmentId, Map<Locale, String> titleMap, String description,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {

		// Check permissions.

		_assignmentModelResourcePermission.check(
			getPermissionChecker(), assignmentId, ActionKeys.UPDATE);

		return assignmentLocalService.updateAssignment(
			assignmentId, titleMap, description, dueDate, serviceContext);
	}
	
	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(model.class.name=com.liferay.training.gradebook.model.Assignment)"
	)
	private volatile ModelResourcePermission<Assignment>
		_assignmentModelResourcePermission;
	
	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(resource.name=" + GradebookConstants.RESOURCE_NAME + ")"
	)
	private volatile PortletResourcePermission _portletResourcePermission;	
}
```

<div class="page"></div>

#### Rebuild the Service
1. **Run** the `buildService` task to deploy the changes.

#### Test the Application
1. **Sign out** of the portal.
2. **Try** to add an assignment. You should get an error message.

-->