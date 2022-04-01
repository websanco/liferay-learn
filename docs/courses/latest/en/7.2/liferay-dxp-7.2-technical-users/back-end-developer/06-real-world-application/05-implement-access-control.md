---
description: 06 - Real World Application
title: Implement Access Control
order: 5
---

## Implement Access Control

Liferay has a robust security model that allows for the configuration of fine-grained access control. Liferay's access control system lets you define who can use an application and who is allowed to add and edit a model resource.

For example, all applications dealing with user and site management are only accessible for Liferay Administrators and not for regular users. Wiki Pages can be created and edited by any member of a site, while Blogs posts by default can only be edited by their original author.  

Access control in Liferay is based on four key concepts: Resources, Actions, Permissions, and Roles.

#### Resources

A resource in Liferay's permission framework is a generic representation of any application or model entity that can be used as an action target. There are two distinct kinds of resources: portlet and model resources.

#### Portlet Resources

A portlet resource represents a portlet application, like the Blogs or Wiki, and is identified in the permission system by a portlet's ID, defined by a `javax.portlet.name` property. By convention, a component's fully qualified classname with the dots replaced by underscores is used as the ID, as seen in the code snippet taken from Liferay's [Blogs Portlet](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/blogs): 

**BlogsPortlet.java**
```java
@Component(
	immediate = true,
	property = {
		...
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS,
		...
	},
	service = Portlet.class
)
public class BlogsPortlet extends BaseBlogsPortlet {
...
}
```

**BlogsPortletKeys.java**
```java
public class BlogsPortletKeys {

	public static final String BLOGS =
		"com_liferay_blogs_web_portlet_BlogsPortlet";

	...
}
```

> A best practice is to define the portlet's ID as a constant in a dedicated keys class in order to avoid ambiguities and misspellings. 

#### Model Resources

A Model Resource represents a model entity, usually managed by a portlet application. Examples of model resources include: 

* A wiki page
* A document
* A site

Model resources are usually identified and referenced by an entity's fully qualified class name, for example: 

* __Web Content:__ com.liferay.journal.model.JournalArticle	
* __BlogsEntry:__ com.liferay.blogs.model.BlogsEntry

#### Actions

Actions are operations that can be performed on a given resource, either on a portlet or a model resource.

Examples for portlet resource actions: 

* __ADD\_TO\_PAGE__: gives users the ability to add a portlet to a page
* __CONFIGURATION__: lets users access a portlet's configuration menu
* __VIEW__: lets users view the portlet

Examples for model resource actions:

* ADD_ENTRY
* UPDATE
* DELETE 
* PERMISSIONS
* VIEW

> The above example actions are taken from Liferay's core applications, but you can define custom actions for your own applications.

#### Action Types

There are two types of actions: 

1.  __Top-level actions:__ general model actions that can't be applied to an existing resource, for example, `ADD_ENTRY`.
1. __Resource actions:__  actions applied to an existing resource, for example, `DELETE`.

> By convention, the *top-level actions* are referenced by the package name of the respective service, for example, `com.liferay.blogs`, and the *resource actions* by the fully qualified name of the targeted model entity, for example, `com.liferay.blogs.model.BlogsEntry`.

#### Permissions

A permission is when a *resource* meets an *action* or, more formally: a permission is an *action* that can be performed on a *resource*. The permission to update a Blogs post, for example, is defined by the combination of the BlogsEntry's model resource identifier and the UPDATE action: `  com.liferay.blogs.model.BlogsEntry` + `UPDATE`.

#### Permission Scopes

The scope of a permission defines how broadly the permission applies to the resources in the portal. There are four options:

1. __Company__: grants a user permissions for every resource of the type within the portal instance
1. __Group__: gives users permissions for every resource within a specified group
1. __Group Template__: similar to *Group* scope, except that it does not automatically apply to a specific group. A user must be a member of a group (generally either a site or an organization), and they must have been given the role within that group before they are granted its permissions.
1. __Individual__: applies only to a single resource of the type

#### Roles

Roles are entities that bind all the previously mentioned concepts to a user or a user group. In other words, __roles are collections of permissions__ that give users access to different parts of the platform and lets them perform certain tasks. 

Roles can be assigned to:

* Users
* Sites
* Organizations
* User groups

Roles are always defined in one of the following scopes: 

* Global
* Site or organization
* Within a site or organization (Team) 

Roles defined for the *Global* scope apply to the entire portal instance. The most prominent global role is the (portal) Administrator role, which gives all its members control over every resource of the portal instance.

Roles defined with the *Site* or *Organization* scope apply only within the respective site or organization. Examples for Site or Organization roles are the *Site Administrator* or *Organization Administrator* role, which give their members administrative privileges for a certain site or organization. 

__Permissions are always granted to roles__, not to users directly.

<br />

#### Wrapping it Up

Liferay's access control system can be summarized as follows:

* __Resources__ represent either an application or a model entity.
* __Permissions__ are __actions__ that can be performed on a resource.
* __Roles__ are collections of *permissions* that define a particular function within Liferay.
* Roles are assigned to users either directly or through a site, an organization, or a user group.
* Effective permissions are inherited from all the user's roles, so a Liferay user has all the permissions of all roles they belong to.

<img src="../images/permissioning-overview.png" style="max-height:100%"/>

Permissions for roles can be granted on an individual resource level. You can manage roles in *Control Panel → Roles*.

<img src="../images/permission-management.png" style="max-height:100%"/>

#### Permissions in the Database

There are two database tables storing the permissions information:

* ResourceAction
* ResourcePermission

Example of Blogs-related actions in the ResourceAction table:

<img src="../images/resourceaction-table.png" style="max-height:100%"/>

Example of Blogs-related permissions in the ResourcePermissions table:

<img src="../images/resourcepermission-table.png" style="max-height:100%"/>

#### Implementing Permissioning

To implement permissioning, you need to define permissions, manage the resource lifecycle, and implement permission checking. Where and how you define permissions depends on the application structure. 

If you are using Service Builder to create your custom entities and want to implement permissioning support for your application, you have to define *model permissions* in the service module and portlet permissions in the portlet module, typically the "web" module. 

Generally, the steps for implementing permissioning are:

1. Define the path to the file that defines the resources and permissions (`default.xml`). This is usually done in the `portlet.properties` file.
1. Define resources and permissions. This is usually done in a file called `default.xml`.
1. Manage the permissions of a resource lifecycle. On the service layer, this is usually done in the CRUD methods of the service implementation class. 
1. Create permission registrar classes.
1. Implement the permission checker class if necessary.
1. Implement permission checking wherever necessary.

Let's walk through an example:

#### Step 1 - Define the Path to the Permission Definition File

Create a file called `portlet.properties` in the `src/main/resources` folder of your module project and implement as follows:

```properties
#
# Input a list of comma delimited resource action configurations that will be
# read from the class path.
#
resource.actions.configs=resource-actions/default.xml
```

#### Step 2 - Create the Permission Definition File

Now you have to implement the actual file that defines module resources and the available permissions. Following the example, this file would be `src/main/resources/resource-actions/default.xml`.

As an example, below are permission definitions in a Liferay Blogs application. The first one defines the portlet permissions for two portlets in the *blogs-web* module. Notice that there are only *portlet-resource* definitions.

The second one is the permission definition file for model resources and is located in the *blogs-service* module. Take note of three things in particular:
1. *model-resource* tags instead of *portlet-resource* tags
1. Resources that have the *model-name* `com.liferay.blogs` are *top-level actions*, which are applied to generic model actions.
1. Resources have the full class path `com.liferay.blogs.model.BlogsEntry` as the *model-name* and are called *resource actions* and are applied to existing entities.

**blogs-web default.xml **
```xml
<?xml version="1.0"?>
<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

<resource-action-mapping>
	<portlet-resource>
		<portlet-name>com_liferay_blogs_web_portlet_BlogsAdminPortlet</portlet-name>
		<permissions>
			<supports>
				<action-key>ACCESS_IN_CONTROL_PANEL</action-key>
				<action-key>CONFIGURATION</action-key>
				<action-key>VIEW</action-key>
			</supports>
			<site-member-defaults>
				<action-key>VIEW</action-key>
			</site-member-defaults>
			<guest-defaults>
				<action-key>VIEW</action-key>
			</guest-defaults>
			<guest-unsupported>
				<action-key>ACCESS_IN_CONTROL_PANEL</action-key>
				<action-key>CONFIGURATION</action-key>
			</guest-unsupported>
		</permissions>
	</portlet-resource>
	<portlet-resource>
		<portlet-name>com_liferay_blogs_web_portlet_BlogsPortlet</portlet-name>
		<permissions>
			<supports>
				<action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
				<action-key>ADD_TO_PAGE</action-key>
				<action-key>CONFIGURATION</action-key>
				<action-key>VIEW</action-key>
			</supports>
			<site-member-defaults>
				<action-key>VIEW</action-key>
			</site-member-defaults>
			<guest-defaults>
				<action-key>VIEW</action-key>
			</guest-defaults>
			<guest-unsupported>
				<action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
				<action-key>CONFIGURATION</action-key>
			</guest-unsupported>
		</permissions>
	</portlet-resource>
</resource-action-mapping>
```

**blogs-service default.xml**
```xml
<?xml version="1.0"?>
<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

<resource-action-mapping>
	<model-resource>
		<model-name>com.liferay.blogs</model-name>
		<portlet-ref>
			<portlet-name>com_liferay_blogs_web_portlet_BlogsAdminPortlet</portlet-name>
			<portlet-name>com_liferay_blogs_web_portlet_BlogsPortlet</portlet-name>
		</portlet-ref>
		<root>true</root>
		<weight>1</weight>
		<permissions>
			<supports>
				<action-key>ADD_ENTRY</action-key>
				<action-key>PERMISSIONS</action-key>
				<action-key>SUBSCRIBE</action-key>
			</supports>
			<site-member-defaults>
				<action-key>SUBSCRIBE</action-key>
			</site-member-defaults>
			<guest-defaults />
			<guest-unsupported>
				<action-key>ADD_ENTRY</action-key>
				<action-key>PERMISSIONS</action-key>
				<action-key>SUBSCRIBE</action-key>
			</guest-unsupported>
		</permissions>
	</model-resource>
	<model-resource>
		<model-name>com.liferay.blogs.model.BlogsEntry</model-name>
		<portlet-ref>
			<portlet-name>com_liferay_blogs_web_portlet_BlogsAdminPortlet</portlet-name>
			<portlet-name>com_liferay_blogs_web_portlet_BlogsPortlet</portlet-name>
		</portlet-ref>
		<weight>2</weight>
		<permissions>
			<supports>
				<action-key>ADD_DISCUSSION</action-key>
				<action-key>DELETE</action-key>
				<action-key>DELETE_DISCUSSION</action-key>
				<action-key>PERMISSIONS</action-key>
				<action-key>UPDATE</action-key>
				<action-key>UPDATE_DISCUSSION</action-key>
				<action-key>VIEW</action-key>
			</supports>
			<site-member-defaults>
				<action-key>ADD_DISCUSSION</action-key>
				<action-key>VIEW</action-key>
			</site-member-defaults>
			<guest-defaults>
				<action-key>ADD_DISCUSSION</action-key>
				<action-key>VIEW</action-key>
			</guest-defaults>
			<guest-unsupported>
				<action-key>DELETE</action-key>
				<action-key>DELETE_DISCUSSION</action-key>
				<action-key>PERMISSIONS</action-key>
				<action-key>UPDATE</action-key>
				<action-key>UPDATE_DISCUSSION</action-key>
			</guest-unsupported>
		</permissions>
	</model-resource>
</resource-action-mapping>
```

Sources: 
* https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-web/src/main/resources/resource-actions/default.xml
* https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-service/src/main/resources/resource-actions/default.xml

#### Step 3 - Manage the Permission Resources Lifecycle

Permission resources are permission container objects bound to the model entities. We need to take care of adding, updating, and deleting permission resources for our entities. 

In a Service Builder project, adding is usually done in the method adding a new entity and deletion respectively in the entity deletion method. Portal services for managing permission resources are `com.liferay.portal.kernel.service.ResourceService` and `com.liferay.portal.kernel.service.ResourceLocalService`.

Below is an excerpt of resource handling methods in a Blogs application's local service class: [com.liferay.blogs.service.impl.BlogsEntryLocalServiceImpl.java](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/service/impl/BlogsEntryLocalServiceImpl.java): 

**Adding resources**
```java
@Override
public void addEntryResources(
		BlogsEntry entry, boolean addGroupPermissions,
		boolean addGuestPermissions)
	throws PortalException {

	resourceLocalService.addResources(
		entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
		BlogsEntry.class.getName(), entry.getEntryId(), false,
		addGroupPermissions, addGuestPermissions);
}

@Override
public void addEntryResources(
		BlogsEntry entry, ModelPermissions modelPermissions)
	throws PortalException {

	resourceLocalService.addModelResources(
		entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
		BlogsEntry.class.getName(), entry.getEntryId(), modelPermissions);
}
```

**Updating resources**
```java
@Override
public void updateEntryResources(
		BlogsEntry entry, ModelPermissions modelPermissions)
	throws PortalException {

	resourceLocalService.updateResources(
		entry.getCompanyId(), entry.getGroupId(),
		BlogsEntry.class.getName(), entry.getEntryId(), modelPermissions);
}

@Override
public void updateEntryResources(
		BlogsEntry entry, String[] groupPermissions,
		String[] guestPermissions)
	throws PortalException {

	resourceLocalService.updateResources(
		entry.getCompanyId(), entry.getGroupId(),
		BlogsEntry.class.getName(), entry.getEntryId(), groupPermissions,
		guestPermissions);
}
```

**Resource Deletion**
```java
@Indexable(type = IndexableType.DELETE)
@Override
@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
public BlogsEntry deleteEntry(BlogsEntry entry) throws PortalException {

	// Order is important. See LPS-81826.

	// Ratings

	ratingsStatsLocalService.deleteStats(
		BlogsEntry.class.getName(), entry.getEntryId());

	// Entry

	blogsEntryPersistence.remove(entry);

	// Resources

	resourceLocalService.deleteResource(
		entry.getCompanyId(), BlogsEntry.class.getName(),
		ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

	// Image

	imageLocalService.deleteImage(entry.getSmallImageId());

	// Subscriptions

	subscriptionLocalService.deleteSubscriptions(
		entry.getCompanyId(), BlogsEntry.class.getName(),
		entry.getEntryId());

	// Statistics

	blogsStatsUserLocalService.updateStatsUser(
		entry.getGroupId(), entry.getUserId(), entry.getDisplayDate());

	// Asset

	assetEntryLocalService.deleteEntry(
		BlogsEntry.class.getName(), entry.getEntryId());

	// Attachments

	long coverImageFileEntryId = entry.getCoverImageFileEntryId();

	if (coverImageFileEntryId != 0) {
		PortletFileRepositoryUtil.deletePortletFileEntry(
			coverImageFileEntryId);
	}

	long smallImageFileEntryId = entry.getSmallImageFileEntryId();

	if (smallImageFileEntryId != 0) {
		PortletFileRepositoryUtil.deletePortletFileEntry(
			smallImageFileEntryId);
	}

	// Comment

	deleteDiscussion(entry);

	// Expando

	expandoRowLocalService.deleteRows(entry.getEntryId());

	// Friendly URL

	friendlyURLEntryLocalService.deleteFriendlyURLEntry(
		entry.getGroupId(), BlogsEntry.class, entry.getEntryId());

	// Trash

	trashEntryLocalService.deleteEntry(
		BlogsEntry.class.getName(), entry.getEntryId());

	// Workflow

	workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
		entry.getCompanyId(), entry.getGroupId(),
		BlogsEntry.class.getName(), entry.getEntryId());

	return entry;
}
```

#### Step 4 - Create Permission Registrar Classes

Next, we'll have to create permissions registrar classes. These follow what you did in `default.xml`: you need one for your portlet permissions and one for each of your entities. By convention, registrar classes are placed in the sub-package `internal.security.permission.resource`.

**BlogsEntryModelResourcePermissionDefinition.java**
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

package com.liferay.blogs.internal.security.permission.resource.definition;

import com.liferay.blogs.constants.BlogsConstants;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
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

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = ModelResourcePermissionDefinition.class)
public class BlogsEntryModelResourcePermissionDefinition
	implements ModelResourcePermissionDefinition<BlogsEntry> {

	@Override
	public BlogsEntry getModel(long entryId) throws PortalException {
		return _blogsEntryLocalService.getBlogsEntry(entryId);
	}

	@Override
	public Class<BlogsEntry> getModelClass() {
		return BlogsEntry.class;
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Override
	public long getPrimaryKey(BlogsEntry blogsEntry) {
		return blogsEntry.getEntryId();
	}

	@Override
	public void registerModelResourcePermissionLogics(
		ModelResourcePermission<BlogsEntry> modelResourcePermission,
		Consumer<ModelResourcePermissionLogic<BlogsEntry>>
			modelResourcePermissionLogicConsumer) {

		modelResourcePermissionLogicConsumer.accept(
			new StagedModelPermissionLogic<>(
				_stagingPermission, BlogsPortletKeys.BLOGS,
				BlogsEntry::getEntryId));
		modelResourcePermissionLogicConsumer.accept(
			new WorkflowedModelPermissionLogic<>(
				_workflowPermission, modelResourcePermission,
				_groupLocalService, BlogsEntry::getEntryId));
	}

	@Reference
	private BlogsEntryLocalService _blogsEntryLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(target = "(resource.name=" + BlogsConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private StagingPermission _stagingPermission;

	@Reference
	private WorkflowPermission _workflowPermission;

}
```

**BlogsPortletResourcePermissionDefinition.java**
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

package com.liferay.blogs.internal.security.permission.resource.definition;

import com.liferay.blogs.constants.BlogsConstants;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.StagedPortletPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.PortletResourcePermissionDefinition;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(
	immediate = true, service = PortletResourcePermissionDefinition.class
)
public class BlogsPortletResourcePermissionDefinition
	implements PortletResourcePermissionDefinition {

	@Override
	public PortletResourcePermissionLogic[]
		getPortletResourcePermissionLogics() {

		return new PortletResourcePermissionLogic[] {
			new StagedPortletPermissionLogic(
				_stagingPermission, BlogsPortletKeys.BLOGS_ADMIN)
		};
	}

	@Override
	public String getResourceName() {
		return BlogsConstants.RESOURCE_NAME;
	}

	@Reference
	private StagingPermission _stagingPermission;

}
```

Sources:
* BlogsPortletResourcePermissionDefinition: https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/security/permission/resource/definition/BlogsPortletResourcePermissionDefinition.java
* BlogsEntryModelResourcePermissionDefinition: https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/security/permission/resource/definition/BlogsEntryModelResourcePermissionDefinition.java

### Step 5 - Implement Permission Checkers

Next, we'll need a permission checker class for portlets and all the entities. In the Liferay Blogs application, both are placed in the web module:

<br /><br />

**BlogsPermission.java**
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

package com.liferay.blogs.web.internal.security.permission.resource;

import com.liferay.blogs.constants.BlogsConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true)
public class BlogsPermission {

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		return _portletResourcePermission.contains(
			permissionChecker, groupId, actionId);
	}

	@Reference(
		target = "(resource.name=" + BlogsConstants.RESOURCE_NAME + ")",
		unbind = "-"
	)
	protected void setPortletResourcePermission(
		PortletResourcePermission portletResourcePermission) {

		_portletResourcePermission = portletResourcePermission;
	}

	private static PortletResourcePermission _portletResourcePermission;

}
```

**BlogsEntryPermission.java**
```java
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

package com.liferay.blogs.web.internal.security.permission.resource;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = BlogsEntryPermission.class)
public class BlogsEntryPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, BlogsEntry entry,
			String actionId)
		throws PortalException {

		return _blogsEntryModelResourcePermission.contains(
			permissionChecker, entry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException {

		return _blogsEntryModelResourcePermission.contains(
			permissionChecker, entryId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.blogs.model.BlogsEntry)",
		unbind = "-"
	)
	protected void setEntryModelPermission(
		ModelResourcePermission<BlogsEntry> modelResourcePermission) {

		_blogsEntryModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<BlogsEntry>
		_blogsEntryModelResourcePermission;

}
```

Sources:
* [BlogsEntryPermission](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/security/permission/resource/BlogsEntryPermission.java)
* [BlogsPermission](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/security/permission/resource/BlogsPermission.java)

#### Step 6 - Implement Permission Checking

The final step is to make use of the permissions. 

In the user interface, you should check permissions to control viewing, adding, and deleting entities. You also have to implement permission checking on the service layer, as services can be accessed outside your application user interface. The permission checking should be done on your remote service implementation class. This is why user-level access to services should, therefore, always be done through remote service classes. Local service classes __should not__ implement permission checking.

The examples below from a Blogs applications [BlogsEntryServiceImpl](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/service/impl/BlogsEntryServiceImpl.java) and from a [JSP](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/entry_action.jsp) file class demonstrates permission checking:

**BlogsEntryServiceImpl.java**
```java
@Override
public BlogsEntry addEntry(
		String title, String subtitle, String description, String content,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, boolean allowPingbacks,
		boolean allowTrackbacks, String[] trackbacks,
		String coverImageCaption, ImageSelector coverImageImageSelector,
		ImageSelector smallImageImageSelector,
		ServiceContext serviceContext)
	throws PortalException {

	_portletResourcePermission.check(
		getPermissionChecker(), serviceContext.getScopeGroupId(),
		ActionKeys.ADD_ENTRY);

	return blogsEntryLocalService.addEntry(
		getUserId(), title, subtitle, description, content,
		displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
		displayDateMinute, allowPingbacks, allowTrackbacks, trackbacks,
		coverImageCaption, coverImageImageSelector, smallImageImageSelector,
		serviceContext);
}
```

**entry_action.jsp**
```html
<c:if test="<%= BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.PERMISSIONS) %>">
	<liferay-security:permissionsURL
		modelResource="<%= BlogsEntry.class.getName() %>"
		modelResourceDescription="<%= BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) %>"
		resourceGroupId="<%= String.valueOf(entry.getGroupId()) %>"
		resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
		var="permissionsEntryURL"
		windowState="<%= LiferayWindowState.POP_UP.toString() %>"
	/>

	<liferay-ui:icon
		label="<%= true %>"
		message="permissions"
		method="get"
		url="<%= permissionsEntryURL %>"
		useDialog="<%= true %>"
	/>
</c:if>
```

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
 	<li>Permissions are _____________ that can be performed on a given _____________.</li>
	<li>Roles are collections of _____________.</li>
	<li>To implement permissioning in your application:
	<ul>
		<li>Define _____________ to the permission definition file.</li>
		<li>Define _____________ and _____________.</li>
		<li>Manage _____________ lifecycle.</li> 
		<li>Implement permission _____________ classes.</li>
		<li>Implement permission _____________ classes, if necessary.</li>
		<li>Implement permission _____________  where ever necessary.</li>
	</ul>
</ul>
</div>
