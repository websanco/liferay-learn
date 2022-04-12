## Optional Exercise: Enable Workflows for Assignments

<div class="ahead">

#### Exercise Goals

- Add WorkflowInstance reference to service.xml
- Manage WorkflowInstance resources in the Assignment local service
- Create an Assignment workflow handler
- Implement support for status in the getter methods
- Implement support for workflow status in the ViewAssignmentsMVCRenderCommand
- Implement support for workflow status in the JSP files
- Test the application

<div class="note">
Note: This exercise is optional. It is not written as step-by-step exercises so that you can explore and experiment more.
</div>

</div>
	
#### Add WorkflowInstance Reference to service.xml

Remember how in the *Integrate with the Asset Framework* exercise we added the status fields to the Assignment entity. These fields are also required in integrating with the Workflow framework. 

We'll make the [WorkflowInstanceLink](https://github.com/liferay/liferay-portal/blob/7.3.x/portal-kernel/src/com/liferay/portal/kernel/model/WorkflowInstanceLink.java) service , which is responsible for creating workflow resources for model entities, available in the Assignment local service.

Open the `service.xml` in the *gradebook-service* module and add `WorkflowInstanceLink` to the Assignment entity's references. Then rebuild the service.

```xml
<reference entity="WorkflowInstanceLink" package-path="com.liferay.portal" />
```

<div class="page"></div>

#### Manage WorkflowInstanceLink Resources in the Assignment Local Service

Workflows are bound to model entities with WorkflowInstanceLink resources. Like with permission and Asset resources, we have to take care of managing these resources in the Assignment local service. Open the class `com.liferay.training.gradebook.service.impl.AssignmentLocalServiceImpl` in the *gradebook-service* module. Implement a new method for creating a WorkflowInstanceLink as shown in the code below. Organize the missing imports and save the class.

```java
protected Assignment startWorkflowInstance(
	long userId, Assignment assignment, ServiceContext serviceContext)
	throws PortalException {

	Map<String, Serializable> workflowContext = new HashMap();

	String userPortraitURL = StringPool.BLANK;
	String userURL = StringPool.BLANK;

	if (serviceContext.getThemeDisplay() != null) {
		User user = userLocalService.getUser(userId);

		userPortraitURL =
			user.getPortraitURL(serviceContext.getThemeDisplay());
		userURL = user.getDisplayURL(serviceContext.getThemeDisplay());
	}

	workflowContext.put(
		WorkflowConstants.CONTEXT_USER_PORTRAIT_URL, userPortraitURL);
	workflowContext.put(WorkflowConstants.CONTEXT_USER_URL, userURL);
```

<!-- pagebreak for pdf book -->

```java
	return WorkflowHandlerRegistryUtil.startWorkflowInstance(
		assignment.getCompanyId(), assignment.getGroupId(), userId,
		Assignment.class.getName(), assignment.getAssignmentId(),
		assignment, serviceContext, workflowContext);
}
```

Next, implement updating the status fields and managing WorkFlowInstances on creating and deleting Assignments by adding setting status fields and creating a WorkFlowInstanceLink in the `addAssignment()` method. Replace the method's code with the following. See the highlighted lines below for changes.

```java
public Assignment addAssignment(long groupId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
		Date dueDate, ServiceContext serviceContext) throws PortalException {

	// Validate assignment parameters.

	_assignmentValidator.validate(titleMap, descriptionMap, dueDate);

	// Get group and user.

	Group group = groupLocalService.getGroup(groupId);

	long userId = serviceContext.getUserId();

	User user = userLocalService.getUser(userId);

	// Generate primary key for the assignment.

	long assignmentId = counterLocalService.increment(Assignment.class.getName());

	// Create assigment. This doesn't yet persist the entity.

	Assignment assignment = createAssignment(assignmentId);

	// Populate fields.

	assignment.setCompanyId(group.getCompanyId());
	assignment.setCreateDate(serviceContext.getCreateDate(new Date()));
	assignment.setDueDate(dueDate);
	assignment.setDescriptionMap(descriptionMap);
	assignment.setGroupId(groupId);
	assignment.setModifiedDate(serviceContext.getModifiedDate(new Date()));
	assignment.setTitleMap(titleMap);
	assignment.setUserId(userId);
	assignment.setUserName(user.getScreenName());
	
	// Set Status fields.
	
	assignment.setStatus(WorkflowConstants.STATUS_DRAFT);
	assignment.setStatusByUserId(userId);
	assignment.setStatusByUserName(user.getFullName());
	assignment.setStatusDate(serviceContext.getModifiedDate(null));		

	// Persist assignment to database.
	
	assignment = super.addAssignment(assignment);

	// Add permission resources.

	boolean portletActions = false;
	boolean addGroupPermissions = true;
	boolean addGuestPermissions = true;
	resourceLocalService.addResources(group.getCompanyId(), groupId, userId, Assignment.class.getName(),
			assignment.getAssignmentId(), portletActions, addGroupPermissions, addGuestPermissions);
	
	// Update asset.

	updateAsset(assignment, serviceContext);

	// Start workflow instance and return the assignment.
	 
	return startWorkflowInstance(userId, assignment, serviceContext);
}
```

Implement code for deleting the WorkflowInstanceLink in `deleteAssignment()`. Replace the existing code with something similar to the following. See highlighted rows for changes and don't forget to organize missing imports.

```java
public Assignment deleteAssignment(Assignment assignment)
	throws PortalException {

	// Delete permission resources.

	resourceLocalService.deleteResource(
		assignment, ResourceConstants.SCOPE_INDIVIDUAL);

	// Delete the Asset resource.

	assetEntryLocalService.deleteEntry(
		Assignment.class.getName(), assignment.getAssignmentId());

	// Delete the workflow resource.

	workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
		assignment.getCompanyId(), assignment.getGroupId(),
		Assignment.class.getName(), assignment.getAssignmentId());
```

<!-- pagebreak for pdf book -->

```java
	// Delete the Assignment

	return super.deleteAssignment(assignment);
}	
```

#### Create an Assignment Workflow Handler

A workflow handler is an OSGi component that registers itself to the OSGi service registry as responsible for handling workflow status changes on defined model entities. For updating the workflow status, first create the actual worker method in the local service. This method should also sync the `visible` field of entity's Asset resource.

Open the class `com.liferay.training.gradebook.service.impl.AssignmentLocalServiceImpl` in the *gradebook-service* module. Implement** a new `updateStatus()` method. When you're done, organize the missing imports and rebuild the service.

```java
public Assignment updateStatus(
	long userId, long assignmentId, int status,
	ServiceContext serviceContext)
	throws PortalException, SystemException {

	User user = userLocalService.getUser(userId);
	Assignment assignment = getAssignment(assignmentId);

	assignment.setStatus(status);
	assignment.setStatusByUserId(userId);
	assignment.setStatusByUserName(user.getFullName());
	assignment.setStatusDate(new Date());

	assignmentPersistence.update(assignment);
```

<!-- pagebreak for pdf book -->

```java
	if (status == WorkflowConstants.STATUS_APPROVED) {

		assetEntryLocalService.updateVisible(
			Assignment.class.getName(), assignmentId, true);

	}
	else {

		assetEntryLocalService.updateVisible(
			Assignment.class.getName(), assignmentId, false);
	}

	return assignment;
}
```

Next we need to implement the WorkflowHandler component by creating a class `com.liferay.training.gradebook.service.workflow.AssignmentWorkflowHandler` in the *gradebook-service* module. The code should look similar to what is written below.

```java

package com.liferay.training.gradebook.service.workflow;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentLocalService;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
	* Assignments workflow handler.
	* 
	* @author liferay
	*/
@Component(
	immediate = true, 
	service = WorkflowHandler.class
)
public class AssignmentWorkflowHandler extends BaseWorkflowHandler<Assignment> {

	@Override
	public String getClassName() {

		return Assignment.class.getName();
	}

	@Override
	public String getType(Locale locale) {

		return _resourceActions.getModelResource(locale, getClassName());
	}

	@Override
	public Assignment updateStatus(
		int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		long userId = GetterUtil.getLong(
			(String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));

		long resourcePrimKey = GetterUtil.getLong(
			(String) workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	
		ServiceContext serviceContext =
			(ServiceContext) workflowContext.get("serviceContext");

		return _assigmentLocalService.updateStatus(
			userId, resourcePrimKey, status, serviceContext);
	}

	@Reference
	private AssignmentLocalService _assigmentLocalService;

	@Reference
	private ResourceActions _resourceActions;
	
}
```

#### Implement Support for Status in the Getter Methods

Now our entities support workflows on the service layer but we also have to be able to query them by their status so that for example draft entities are not shown to unauthorized users. 

For the sake of exercise, we'll add a new signature for `getAssignmentsByKeywords()` only, taking the `status` field into account. Open the class `com.liferay.training.gradebook.service.impl.AssignmentLocalServiceImpl` in the *gradebook-service* module and add new signatures for the `getAssignmentsByKeywords()` and `getAssignmentsCountByKeywords()`.

```java
public List<Assignment> getAssignmentsByKeywords(
	long groupId, String keywords, int start, int end, int status,
	OrderByComparator<Assignment> orderByComparator) {

	DynamicQuery assignmentQuery = getKeywordSearchDynamicQuery(groupId, keywords);

	if (status != WorkflowConstants.STATUS_ANY) {
		assignmentQuery.add(RestrictionsFactoryUtil.eq("status", status));
	}

	return assignmentLocalService.dynamicQuery(
		assignmentQuery, start, end, orderByComparator);
}

public long getAssignmentsCountByKeywords(
	long groupId, String keywords, int status) {

	DynamicQuery assignmentQuery = getKeywordSearchDynamicQuery(groupId, keywords);

	if (status != WorkflowConstants.STATUS_ANY) {
		assignmentQuery.add(RestrictionsFactoryUtil.eq("status", status));
	}

	return assignmentLocalService.dynamicQueryCount(assignmentQuery);
}
```

These methods are called through the remote service, so let's add facades for them in the `com.liferay.training.gradebook.service.impl.AssignmentServiceImpl.java` class located in the *gradebook-service* module. When you're done, save the class and rebuild the service. Code for the new facade methods should be similar to the code below.

```java
public List<Assignment> getAssignmentsByKeywords(
	long groupId, String keywords, int start, int end, int status,
	OrderByComparator<Assignment> orderByComparator) {

	return assignmentLocalService.getAssignmentsByKeywords(
		groupId, keywords, start, end, status, orderByComparator);
}

public long getAssignmentsCountByKeywords(
	long groupId, String keywords, int status) {

	return assignmentLocalService.getAssignmentsCountByKeywords(
		groupId, keywords, status);
}	
```

#### Implement Workflow Support for Search

Implement a pre filter for Assignments search so that only approved entities are shown by creating a class `com.liferay.training.gradebook.internal.search.spi.model.query.contributor.AssignmentModelPreFilterContributor` in the *gradebook-service* module. Code should look something like this:

```java
package com.liferay.training.gradebook.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.training.gradebook.model.Assignment",
	service = ModelPreFilterContributor.class
)
public class AssignmentModelPreFilterContributor
	implements ModelPreFilterContributor {
	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		addWorkflowStatusFilter(
			booleanFilter, modelSearchSettings, searchContext);
	}

	protected void addWorkflowStatusFilter(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		workflowStatusModelPreFilterContributor.contribute(
			booleanFilter, modelSearchSettings, searchContext);
	}

	@Reference(target = "(model.pre.filter.contributor.id=WorkflowStatus)")
	protected ModelPreFilterContributor workflowStatusModelPreFilterContributor;

}
```

#### Implement Support for Workflow Status in the MVC Render Command

Next we'll change the implementation of the `ViewAssignmentsMVCRenderCommand`, which is responsible for listing the Assignments, so that it takes the `status` field into account and for the sake of exercise, exposes drafted entities only for the administrators.

Replace the contents of the `com.liferay.training.gradebook.web.portlet.action.ViewAssignmentsMVCRenderCommand` class with the code below. See the highlighted lines for changes.

```java
package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
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
		
		// Get the workflow status for the list.
		
		int status = getAllowedWorkflowStatus(renderRequest);

		// Call the service to get the list of assignments.

		List<Assignment> assignments =
			_assignmentService.getAssignmentsByKeywords(
				themeDisplay.getScopeGroupId(), keywords, start, end, status,
				comparator);

		// Set request attributes.

		renderRequest.setAttribute("assignments", assignments);
		renderRequest.setAttribute(
			"assignmentCount", _assignmentService.getAssignmentsCountByKeywords(
				themeDisplay.getScopeGroupId(), keywords, status));

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

	/**
		* Returns workflow status current user is allowed to see.
		* 
		* This simple example returns ANY status for company admin and
		* APPROVED for other users.
		* 
		* @param renderRequest
		* @return
		*/
	private int getAllowedWorkflowStatus(RenderRequest renderRequest) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		PermissionChecker  permissionChecker = themeDisplay.getPermissionChecker();
		
		int status;
		
		if (permissionChecker.isCompanyAdmin()) {
			status = WorkflowConstants.STATUS_ANY;
		} else {
			status = WorkflowConstants.STATUS_APPROVED;
		}
		
		return status;
	}	
	
	@Reference
	protected AssignmentPermission _assignmentPermission;

	@Reference
	protected AssignmentService _assignmentService;

	@Reference
	private Portal _portal;
}
```

#### Implement Support for Workflow Status in the JSP Files

The last thing to do is to show status in the user interface. Let's add the `status` column to the JSP file responsible for showing the Assignments list columns. Open the file `/src/main/resources/META-INF/resources/assignment/entry_search_column.jspf` in the *gradebook-web* module. Replace the file contents with code similar to that shown below.

```html
<%-- Generate assignment view  URL. --%>

<portlet:renderURL var="viewAssignmentURL">
	<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.VIEW_ASSIGNMENT %>" />
	<portlet:param name="redirect" value="${currentURL}" />
	<portlet:param name="assignmentId" value="${entry.assignmentId}" />
</portlet:renderURL>

<c:choose>

	<%-- Descriptive (list) view --%>

	<c:when
		test='${assignmentsManagementToolbarDisplayContext.getDisplayStyle().equals("descriptive")}'>

		<%-- User --%>

		<liferay-ui:search-container-column-user 
			showDetails="<%=false%>"
			userId="<%=entry.getUserId()%>" 
		/>

		<liferay-ui:search-container-column-text colspan="<%=2%>">

			<%
				String modifiedDateDescription =
					LanguageUtil.getTimeDescription(
							request, System.currentTimeMillis() 
							- entry.getModifiedDate().getTime(), true);
			%>

			<h5 class="text-default">
				<liferay-ui:message
					arguments="<%=new String[] {entry.getUserName(), modifiedDateDescription}%>"
					key="x-modified-x-ago" />
			</h5>

			<h4>
				<aui:a href="${viewAssignmentURL}">
					${entry.getTitle(locale)}
				</aui:a>
			</h4>
			
			<h5 class="text-default">
				<aui:workflow-status 
					markupView="lexicon" 
					showIcon="<%= true %>"
					showLabel="<%= false %>" 
					status="${entry.status}" 
				/>
			</h5>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			path="/assignment/entry_actions.jsp" />
	</c:when>

	<%-- Card view  --%>

	<c:when
		test='${assignmentsManagementToolbarDisplayContext.getDisplayStyle().equals("icon")}'>

		<%
			row.setCssClass("lfr-asset-item");
		%>

		<liferay-ui:search-container-column-text>

			<%-- Vertical card. --%>

			<liferay-frontend:icon-vertical-card
				actionJsp="/assignment/entry_actions.jsp"
				actionJspServletContext="<%= application %>" 
				icon="cards2" resultRow="${row}"
				title="${entry.getTitle(locale)}" 
				url="${viewAssignmentURL}">

				<liferay-frontend:vertical-card-sticker-bottom>
				
					<liferay-ui:user-portrait 
						cssClass="sticker sticker-bottom"
						userId="${entry.userId}" 
					/>
				</liferay-frontend:vertical-card-sticker-bottom>

				<liferay-frontend:vertical-card-footer>
					
					<%-- Workflow status --%>

					<aui:workflow-status 
						markupView="lexicon" 
						showIcon="<%= false %>"
						showLabel="<%= false %>" 
						status="${entry.status}" 
					/>					  
					
					<div class="truncate-text">
					
						<%-- Strip HTML --%>
						
						<%=HtmlUtil.stripHtml(entry.getDescription(locale)) %>
					</div>
				</liferay-frontend:vertical-card-footer>
			</liferay-frontend:icon-vertical-card>
		</liferay-ui:search-container-column-text>
	</c:when>
	
	<%-- Table view --%>

	<c:otherwise>
	
		<liferay-ui:search-container-column-text 
			href="${viewAssignmentURL}" 
			name="title" 
			value="<%= entry.getTitle(locale) %>" 
		/>

		<liferay-ui:search-container-column-user 
			name="author" 
			userId="${entry.userId}" 
		/>

		<liferay-ui:search-container-column-date 
			name="create-date"
			property="createDate" 
		/>

		<%-- Workflow status --%>

		<liferay-ui:search-container-column-status 
			name="status" 
		/>

		<liferay-ui:search-container-column-jsp 
			name="actions"
			path="/assignment/entry_actions.jsp" 
		/>
	</c:otherwise>
</c:choose>
```

<div class="page"></div>

#### Test the Application

To be able to test, we have to enable and define a workflow for Assignments. Go to localhost:8080 in your browser and sign in if necessary. Go to workflows in the Site Administration panel. Set the *Assignment workflow* to *Single Approver*. Open the Gradebook application and create an Assignment. The status on the list should now be pending. Create a new assignment. After refreshing the page, you should see a notification on your avatar image indicating a new workflow event.

Now you can manage the workflows for Assignments as for any other Liferay assets.
