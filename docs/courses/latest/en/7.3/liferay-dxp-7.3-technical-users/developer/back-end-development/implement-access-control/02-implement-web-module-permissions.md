## Implement Web Module Permissions

<div class="ahead">

#### Exercise Goals
- Define portlet permissions
- Define the permissions definition location
- Implement the top-level permission resource permission checker class
- Implement the model resource permission checker class
- Implement permission checking in the JSP files
- Implement permission checking in the management toolbar
- Test the application

</div>

#### Define the Permissions
1. **Create** a folder `src/main/resources/resource-actions` in the *gradebook-web* module.
2. **Create** a file `src/main/resources/resource-actions/default.xml` and implement as follows (switch to *Source* mode, if needed):

```xml
<?xml version="1.0"?>
<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action  apping 7.2.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_2_0.dtd">
<resource-action-mapping>
	<portlet-resource>
		<portlet-name>com_liferay_training_gradebook_web_portlet_GradebookPortlet</portlet-name>
		<permissions>
			<supports>
				<action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>			
				<action-key>ADD_TO_PAGE</action-key>
				<action-key>CONFIGURATION</action-key>
				<action-key>VIEW</action-key>
			</supports>
```
<!-- page break for pdf book -->
```xml
			<site-member-defaults>
				<action-key>VIEW</action-key>
			</site-member-defaults>
			<guest-defaults>
				<action-key>VIEW</action-key>
			</guest-defaults>
			<guest-unsupported>
				<action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
				<action-key>ADD_TO_PAGE</action-key>
				<action-key>CONFIGURATION</action-key>
			</guest-unsupported>
		</permissions>
	</portlet-resource>
</resource-action-mapping>
```

#### Define the Permissions Definition Location
1. **Create** a file `src/main/resources/portlet.properties` in the *gradebook-web* module.
2. **Implement** the file as follows:

```properties
resource.actions.configs=/resource-actions/default.xml
```
Implement a helper class in the *gradebook-web* module for checking top-level permissions. This is a permission checker class we'll call from the user interface.

#### Implement the Top-Level Resource Permission Checker Class
1. **Create** the class `com.liferay.training.gradebook.web.internal.security.permission.resource.AssignmentTopLevelPermission`.
2. **Implement** as follows:

```java
package com.liferay.training.gradebook.web.internal.security.permission.resource;
	
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.training.gradebook.constants.GradebookConstants;
	
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
	
/**
 * @author liferay
 */
@Component(
	immediate = true
)
public class AssignmentTopLevelPermission {
	
	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {
	
		return _portletResourcePermission.contains(
			permissionChecker, groupId, actionId);
	}
	
	@Reference(
		target = "(resource.name=" + GradebookConstants.RESOURCE_NAME + ")",
		unbind = "-"
	)
	protected void setPortletResourcePermission(
		PortletResourcePermission portletResourcePermission) {
	
		_portletResourcePermission = portletResourcePermission;
	}
	
	private static PortletResourcePermission _portletResourcePermission;
	
}
```

Next we need to implement a class for checking existing entity permissions.

#### Implement the Model Resource Permission Checker Class
1. **Create** the class `com.liferay.training.gradebook.web.internal.security.permission.resource.AssignmentPermission`.
2. **Implement** as follows:

```java
package com.liferay.training.gradebook.web.internal.security.permission.resource;
	
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.training.gradebook.model.Assignment;
	
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
	
/**
 * @author liferay
 */
@Component(
	immediate = true, 
	service = AssignmentPermission.class
)
public class AssignmentPermission {
	
	public static boolean contains(
			PermissionChecker permissionChecker, Assignment assignment,
			String actionId)
		throws PortalException {
	
		return _assignmentModelResourcePermission.contains(
			permissionChecker, assignment, actionId);
	}
	
	public static boolean contains(
			PermissionChecker permissionChecker, long assignmentId, String actionId)
		throws PortalException {
	
		return _assignmentModelResourcePermission.contains(
			permissionChecker, assignmentId, actionId);
	}
	
	@Reference(
		target = "(model.class.name=com.liferay.training.gradebook.model.Assignment)",
		unbind = "-"
	)
	protected void setEntryModelPermission(
		ModelResourcePermission<Assignment> modelResourcePermission) {
	
		_assignmentModelResourcePermission = modelResourcePermission;
	}
	
	private static ModelResourcePermission<Assignment>
	_assignmentModelResourcePermission;
	
}
```

We'll put our entity permission checking object into the request attributes of our main view so that it can be used in the JSP files.

#### Implement Permission Checking in the JSP Files
1. **Open** the class `com.liferay.training.gradebook.web.portlet.action.ViewAssignmentsMVCRenderCommand`
2. **Add** a service reference for the permission checker:

	```java
	@Reference
	protected AssignmentPermission _assignmentPermission;
	```

3. **Add** the checker into the request attributes in the `render()` method:

	```java
	renderRequest.setAttribute("assignmentPermission", _assignmentPermission);		
	```
	
Your final class should now look like this:

```java
package com.liferay.training.gradebook.web.portlet.action;
	
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;
import com.liferay.training.gradebook.web.display.context.AssignmentsManagementToolbarDisplayContext;
import com.liferay.training.gradebook.web.internal.security.permission.resource.AssignmentPermission;
	
import java.util.List;
	
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
	
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
	
/**
 * MVC command for showing the assignments list.
 * 
 * @author liferay
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.Gradebook, 
		"mvc.command.name=/",
		"mvc.command.name=" + MVCCommandNames.VIEW_ASSIGNMENTS
	}, 
	service = MVCRenderCommand.class
)
public class ViewAssignmentsMVCRenderCommand implements MVCRenderCommand {
	
	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {
	
		// Add assignment list related attributes.
	
		addAssignmentListAttributes(renderRequest);
			
		// Add Clay management toolbar related attributes.
	
		addManagementToolbarAttributes(renderRequest, renderResponse);
			
		// Add permission checker.
	
		renderRequest.setAttribute(
			"assignmentPermission", _assignmentPermission);		
	
		return "/view.jsp";
	}
	
	/**
	 * Adds assigment list related attributes to the request.
	 * 
	 * @param renderRequest
	 */
	private void addAssignmentListAttributes(RenderRequest renderRequest) {
	
		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
		// Resolve start and end for the search.
	
		int currentPage = ParamUtil.getInteger(
			renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
			SearchContainer.DEFAULT_CUR);
	
		int delta = ParamUtil.getInteger(
			renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
			SearchContainer.DEFAULT_DELTA);
	
		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;
	
		// Get sorting options.
		// Notice that this doesn't really sort on title because the field is
		// stored in XML. In real world this search would be integrated to the
		// search engine  to get localized sort options.
	
		String orderByCol =
			ParamUtil.getString(renderRequest, "orderByCol", "title");
		String orderByType =
			ParamUtil.getString(renderRequest, "orderByType", "asc");
	
		// Create comparator
	
		OrderByComparator<Assignment> comparator =
			OrderByComparatorFactoryUtil.create(
				"Assignment", orderByCol, !("asc").equals(orderByType));
	
		// Get keywords.
		// Notice that cleaning keywords is not implemented.
	
		String keywords = ParamUtil.getString(renderRequest, "keywords");
	
		// Call the service to get the list of assignments.
	
		List<Assignment> assigments =
			_assignmentService.getAssignmentsByKeywords(
				themeDisplay.getScopeGroupId(), keywords, start, end,
				comparator);
	
		// Set request attributes.
	
		renderRequest.setAttribute("assignments", assigments);
		renderRequest.setAttribute(
			"assignmentCount", _assignmentService.getAssignmentsCountByKeywords(
				themeDisplay.getScopeGroupId(), keywords));
	
	}
	
	/**
	 * Adds Clay management toolbar context object to the request.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 */
	private void addManagementToolbarAttributes(
		RenderRequest renderRequest, RenderResponse renderResponse) {
	
		LiferayPortletRequest liferayPortletRequest =
			_portal.getLiferayPortletRequest(renderRequest);
	
		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(renderResponse);
	
		AssignmentsManagementToolbarDisplayContext assignmentsManagementToolbarDisplayContext =
			new AssignmentsManagementToolbarDisplayContext(
				liferayPortletRequest, liferayPortletResponse,
				_portal.getHttpServletRequest(renderRequest));
	
		renderRequest.setAttribute(
			"assignmentsManagementToolbarDisplayContext",
			assignmentsManagementToolbarDisplayContext);
	
	}
	
	@Reference
	protected AssignmentPermission _assignmentPermission;
	
	@Reference
	protected AssignmentService _assignmentService;
	
	@Reference
	private Portal _portal;
}
```

So far, everybody has been able to see the assignment actions menu. Now we'll hide them from unauthorized users. 

We'll also add an option to manage entity permissions. For that purpose, we'll use the `<liferay-security>` tag library:

#### Add Option to Manage Entity Permissions
1. **Declare** the `<liferay-security>` taglib in `src/main/resources/META-INF/resources/init.jsp`:

	```html
	<%@ taglib prefix="liferay-security" uri="http://liferay.com/tld/security" %>.
	```

2. **Open** the file `src/main/resources/META-INF/resources/assignment/entry_actions.jsp`
3. **Wrap** all the actions with permission checks so that only authorized users can access the functions and add a permissions menu option. Replace the contents of the file with the following:

```html
<%@ include file="/init.jsp"%>
	
<c:set var="assignment" value="${SEARCH_CONTAINER_RESULT_ROW.object}" />
	
<liferay-ui:icon-menu markupView="lexicon">
	
	<%-- View action. --%>
		
	<c:if test="${assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'VIEW' )}">
		<portlet:renderURL var="viewAssignmentURL">
			<portlet:param name="mvcRenderCommandName"
				value="<%=MVCCommandNames.VIEW_ASSIGNMENT %>" />
			<portlet:param name="redirect" value="${currentURL}" />
			<portlet:param name="assignmentId" value="${assignment.assignmentId}" />
		</portlet:renderURL>
	
		<liferay-ui:icon message="view" url="${viewAssignmentURL}" />
	</c:if>
	
	<%-- Edit action. --%>
			
	<c:if test="${assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'UPDATE' )}">
			
		<portlet:renderURL var="editAssignmentURL">
			<portlet:param name="mvcRenderCommandName"
				value="<%=MVCCommandNames.EDIT_ASSIGNMENT %>" />
			<portlet:param name="redirect" value="${currentURL}" />
			<portlet:param name="assignmentId" value="${assignment.assignmentId}" />
		</portlet:renderURL>
		
		<liferay-ui:icon message="edit" url="${editAssignmentURL}" />	
	</c:if>
		
	<%-- Permissions action. --%>
		 
	<c:if test="${assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'PERMISSIONS')}">
	
		<liferay-security:permissionsURL
			modelResource="com.liferay.training.gradebook.model.Assignment"
			modelResourceDescription="${assignment.getTitle(locale)}"
			resourcePrimKey="${assignment.assignmentId}" 
			var="permissionsURL" 
		/>
	
		<liferay-ui:icon message="permissions" url="${permissionsURL}" />
	</c:if>
		
	<%-- Delete action. --%>
		
	<c:if test="${assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'DELETE')}">
		
		<portlet:actionURL name="<%=MVCCommandNames.DELETE_ASSIGNMENT %>" var="deleteAssignmentURL">
			<portlet:param name="redirect" value="${currentURL}" />
			<portlet:param name="assignmentId" value="${assignment.assignmentId}" />
		</portlet:actionURL>
		
		<liferay-ui:icon-delete url="${deleteAssignmentURL}" />
	</c:if>
</liferay-ui:icon-menu>
```	

The last thing we need to do is to hide the plus button on the management toolbar for adding assignments. Let's add a permission check to the management toolbar backing class.

#### Implement Permission Checking in the Management Toolbar
1. **Open** the class `com.liferay.training.gradebook.web.display.context.AssignmentsManagementToolbarDisplayContext.java`.
2. **Implement** permission checking in the `getCreationMenu()` method as follows:

	```java
		public CreationMenu getCreationMenu() {
		
			// Check if user has permissions to add assignments.
		
			if (!AssignmentTopLevelPermission.contains(
					_themeDisplay.getPermissionChecker(),
					_themeDisplay.getScopeGroupId(), "ADD_ENTRY")) {
		
				return null;
			}
		
			// Create the menu.
		
			return new CreationMenu() {
				{
					addDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(
								liferayPortletResponse.createRenderURL(),
								"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT,
								"redirect", currentURLObj.toString());
							dropdownItem.setLabel(
								LanguageUtil.get(request, "add-assignment"));
						});
				}
			};		
		}
	```

3. **Resolve** missing imports.

#### Test the Application
1. **Go to** localhost:8080 in your browser.
2. **Sign out** of your Liferay DXP instance and test whether you can add, edit, or delete Assignments.
3. **Create** a new user with just the *User role*.
	* Test whether you can add, edit, or delete Assignments.
	* Add the *Add Entry* permission to the role and test again.

