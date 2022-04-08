---
description: Real World Application
title: Implement the Assignment Editing View
order: 6
---

<h2 class="exercise">Exercises</h2>

## Implement the Assignment Editing View

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Implement the Assignment editing form JSP file</li>
		<li>Implement an MVC render command for switching to the editing view</li>
		<li>Implement an MVC action command for adding an Assignment</li>
		<li>Implement an MVC action command for editing an Assignment</li>
		<li>Implement an MVC action command for deleting an Assignment</li>
		<li>Test the application</li>
	</ul>
</div>

#### Implement the Assignment Editing Form

We'll use the AUI tag library to create the Assignment editing form:

1. **Create** a JSP file `src/main/resources/META-INF/resources/assignment/edit_assignment.jsp`.
1. **Implement** as follows:
	```java
	<%@ include file="/init.jsp"%>
	
	<%-- Generate add / edit action URL and set title. --%>
	
	<c:choose>
		<c:when test="${not empty assignment}">
			<portlet:actionURL var="assignmentActionURL" name="<%=MVCCommandNames.EDIT_ASSIGNMENT %>">
				<portlet:param name="redirect" value="${param.redirect}" />
			</portlet:actionURL>
	
			<c:set var="editTitle" value="edit-assignment"/>
		</c:when>
		<c:otherwise>
			<portlet:actionURL var="assignmentActionURL" name="<%=MVCCommandNames.ADD_ASSIGNMENT %>">
				<portlet:param name="redirect" value="${param.redirect}" />
			</portlet:actionURL>
	
			<c:set var="editTitle" value="add-assignment"/>
		</c:otherwise>
	</c:choose>
		
	<div class="container-fluid-1280 edit-assignment">
	
		<h1><liferay-ui:message key="${editTitle}" /></h1>
	
		<aui:model-context bean="${assignment}" model="${assignmentClass}" />
	
		<aui:form action="${assignmentActionURL}" name="fm">
	
			<aui:input name="assignmentId" field="assignmentId" type="hidden" />
			
			<aui:fieldset-group markupView="lexicon">
			
				<aui:fieldset>
				
					<%-- Title field. --%>
					
					<aui:input name="title">
					
					</aui:input>
	
					<%-- Description field. --%>
	
					<aui:input name="description">
						<aui:validator name="required" />
					</aui:input>
	
					<%-- Due date field. --%>
	
					<aui:input name="dueDate">
						<aui:validator name="required" />
					</aui:input>
				</aui:fieldset>
			</aui:fieldset-group>
			
			<%--Buttons. --%>
			
			<aui:button-row>
				<aui:button cssClass="btn btn-primary" type="submit" />
				<aui:button cssClass="btn btn-secondary" onClick="${param.redirect}" type="cancel" />
			</aui:button-row>
		</aui:form>
	
	</div>
	```

#### Implement an MVC Render Command for Switching to the Editing View

If you take a look at the user interface in the browser, you'll see the plus icon for adding assignments.
The button is wired in the `getCreationMenu()` method in the `AssignmentsManagementToolbarDisplayContext.java` class:

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

The renderURL for switching to the editing view is created, but no MVC Render Command is yet registered for the command name `MVCCommandNames.EDIT_ASSIGNMENT`:

1. **Create** a class `com.liferay.training.gradebook.web.portlet.action.EditAssignmentMVCRenderCommand`.
1. **Implement** as follows (notice the return value of the `render()` method):
	```java
	package com.liferay.training.gradebook.web.portlet.action;
	
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
	import com.liferay.portal.kernel.theme.PortletDisplay;
	import com.liferay.portal.kernel.theme.ThemeDisplay;
	import com.liferay.portal.kernel.util.ParamUtil;
	import com.liferay.portal.kernel.util.WebKeys;
	import com.liferay.training.gradebook.exception.NoSuchAssignmentException;
	import com.liferay.training.gradebook.model.Assignment;
	import com.liferay.training.gradebook.service.AssignmentService;
	import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
	import com.liferay.training.gradebook.web.constants.MVCCommandNames;
	
	import javax.portlet.PortletException;
	import javax.portlet.RenderRequest;
	import javax.portlet.RenderResponse;
	
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;
	
	/**
	 * MVC Command for showing edit assignment view.
	 * 
	 * @author liferay
	 */
	@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
			"mvc.command.name=" + MVCCommandNames.EDIT_ASSIGNMENT
		}, 
		service = MVCRenderCommand.class
	)
	public class EditAssignmentMVCRenderCommand implements MVCRenderCommand {
	
		@Override
		public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException {
	
			Assignment assignment = null;
	
			long assignmentId = ParamUtil.getLong(renderRequest, "assignmentId", 0);
	
			if (assignmentId > 0) {
				try {
					
					// Call the service to get the assignment for editing.
					
					assignment = _assignmentService.getAssignment(assignmentId);
				}
				catch (NoSuchAssignmentException nsae) {
					nsae.printStackTrace();
				}
				catch (PortalException pe) {
					pe.printStackTrace();
				}
			}
			
			ThemeDisplay themeDisplay =
				(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
			// Set back icon visible.
			
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
	
			portletDisplay.setShowBackIcon(true);
	
			String redirect = renderRequest.getParameter("redirect");
	
			portletDisplay.setURLBack(redirect);
	
			// Set assignment to the request attributes.
			
			renderRequest.setAttribute("assignment", assignment);
			renderRequest.setAttribute("assignmentClass", Assignment.class);
	
			return "/assignment/edit_assignment.jsp";
		}
	
		@Reference
		private AssignmentService _assignmentService;
	
	}
	```

Test and click the plus button. You should now be able to see the editing form, but it doesn't work yet. We still need to implement an MVC Action Command to handle the form submits.
<img src="../images/assignment-editing-form.png" style="max-height: 100%"/>

#### Implement an MVC Action Command for Adding an Assignment

We need MVC Action Commands to handle adding, editing, and deleting assignments. A single command can handle multiple command names, so we can handle adding and editing cases in the same class. For better modularity, however, we'll choose to implement these use cases in separate classes:

1. **Create** a class `com.liferay.training.gradebook.web.portlet.action.AddAssignmentMVCActionCommand`.
1. **Implement** as follows:
	```java
	package com.liferay.training.gradebook.web.portlet.action;
	
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
	import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
	import com.liferay.portal.kernel.service.ServiceContext;
	import com.liferay.portal.kernel.service.ServiceContextFactory;
	import com.liferay.portal.kernel.theme.ThemeDisplay;
	import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
	import com.liferay.portal.kernel.util.LocalizationUtil;
	import com.liferay.portal.kernel.util.ParamUtil;
	import com.liferay.portal.kernel.util.WebKeys;
	import com.liferay.training.gradebook.exception.AssignmentValidationException;
	import com.liferay.training.gradebook.model.Assignment;
	import com.liferay.training.gradebook.service.AssignmentService;
	import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
	import com.liferay.training.gradebook.web.constants.MVCCommandNames;
	
	import java.util.Date;
	import java.util.Locale;
	import java.util.Map;
	
	import javax.portlet.ActionRequest;
	import javax.portlet.ActionResponse;
	
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;
	
	/**
	 * MVC Action Command for adding assignments.
	 * 
	 * @author liferay
	 */
	@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
			"mvc.command.name=" + MVCCommandNames.ADD_ASSIGNMENT
		},
		service = MVCActionCommand.class
	)
	public class AddAssignmentMVCActionCommand extends BaseMVCActionCommand {
	
		@Override
		protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
	
			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Assignment.class.getName(), actionRequest);
	
			// Get parameters from the request.
	
			// Use LocalizationUtil to get a localized parameter.

			Map<Locale, String> titleMap =
				LocalizationUtil.getLocalizationMap(actionRequest, "title");
	
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(actionRequest, "description");
	
			Date dueDate = ParamUtil.getDate(
				actionRequest, "dueDate",
				DateFormatFactoryUtil.getDate(actionRequest.getLocale()));
	
			try {
	
				// Call the service to add a a new assignment.
				
				_assignmentService.addAssignment(
					themeDisplay.getScopeGroupId(), titleMap, descriptionMap, dueDate,
					serviceContext);

				sendRedirect(actionRequest, actionResponse);
			}
			catch (AssignmentValidationException ave) {
				
				ave.printStackTrace();

				actionResponse.setRenderParameter(
					"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);			
	
			}
			catch (PortalException pe) {
	
				pe.printStackTrace();
	
				actionResponse.setRenderParameter(
					"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);			
			}
		}
	
		@Reference
		protected AssignmentService _assignmentService;
	
	}
	```

#### Implement an MVC Action Command for Editing an Assignment

1. **Create** a class `com.liferay.training.gradebook.web.portlet.action.EditAssignmentMVCActionCommand`.
1. **Implement** as follows:
	```java
	package com.liferay.training.gradebook.web.portlet.action;
	
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
	import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
	import com.liferay.portal.kernel.service.ServiceContext;
	import com.liferay.portal.kernel.service.ServiceContextFactory;
	import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
	import com.liferay.portal.kernel.util.LocalizationUtil;
	import com.liferay.portal.kernel.util.ParamUtil;
	import com.liferay.training.gradebook.exception.AssignmentValidationException;
	import com.liferay.training.gradebook.model.Assignment;
	import com.liferay.training.gradebook.service.AssignmentService;
	import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
	import com.liferay.training.gradebook.web.constants.MVCCommandNames;
	
	import java.util.Date;
	import java.util.Locale;
	import java.util.Map;
	
	import javax.portlet.ActionRequest;
	import javax.portlet.ActionResponse;
	
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;
	
	/**
	 * MVC Action Command for editing assignments.
	 * 
	 * @author liferay
	 *
	 */
	@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
			"mvc.command.name=" + MVCCommandNames.EDIT_ASSIGNMENT
		},
		service = MVCActionCommand.class
	)
	public class EditAssignmentMVCActionCommand extends BaseMVCActionCommand {
	
		@Override
		protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
	
			ServiceContext serviceContext =
				ServiceContextFactory.getInstance(Assignment.class.getName(), actionRequest);
	
			// Get parameters from the request.
			
			long assignmentId = ParamUtil.getLong(actionRequest, "assignmentId");
	
			Map<Locale, String> titleMap =
				LocalizationUtil.getLocalizationMap(actionRequest, "title");
	
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(actionRequest, "description");
	
			Date dueDate = ParamUtil.getDate(
				actionRequest, "dueDate",
				DateFormatFactoryUtil.getDate(actionRequest.getLocale()));
			
			try {
				
				// Call the service to update the assignment
				
				_assignmentService.updateAssignment(
					assignmentId, titleMap, descriptionMap, dueDate, serviceContext);
	
				sendRedirect(actionRequest, actionResponse);
			}
			catch (AssignmentValidationException ave) {
				
				ave.printStackTrace();
				
				actionResponse.setRenderParameter(
					"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);			
	
			}
			catch (PortalException pe) {
				
				pe.printStackTrace();
	
				actionResponse.setRenderParameter(
					"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);			
			}
		}
	
		@Reference
		protected AssignmentService _assignmentService;
	}
	```
	
#### Implement an MVC Action Command for Deleting an Assignment
	
1. **Create** a class `com.liferay.training.gradebook.web.portlet.action.DeleteAssignmentMVCActionCommand`.
1. **Implement** as follows:
	```java
	package com.liferay.training.gradebook.web.portlet.action;
	
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
	import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
	import com.liferay.portal.kernel.util.ParamUtil;
	import com.liferay.training.gradebook.service.AssignmentService;
	import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
	import com.liferay.training.gradebook.web.constants.MVCCommandNames;
	
	import javax.portlet.ActionRequest;
	import javax.portlet.ActionResponse;
	
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;
	
	/**
	 * MVC Action Command for deleting assignments.
	 * 
	 * @author liferay
	 */
	@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
			"mvc.command.name=" + MVCCommandNames.DELETE_ASSIGNMENT
		},
		service = MVCActionCommand.class
	)
	public class DeleteAssignmentMVCActionCommand extends BaseMVCActionCommand {
	
		@Override
		protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
	
			// Get assignment id from request.
	
			long assignmentId = ParamUtil.getLong(actionRequest, "assignmentId");
	
			try {
	
				// Call service to delete the assignment.
	
				_assignmentService.deleteAssignment(assignmentId);
	
			}
			catch (PortalException pe) {
				pe.printStackTrace();
			}
	
		}
	
		@Reference
		protected AssignmentService _assignmentService;
	}
	```	

<br /><br />

#### Test the Application

After the module has restarted successfully, refresh the browser window and try adding, editing, viewing, deleting Assignments:

<img src="../images/first-assignments.png" style="max-height: 100%"/>

	
	
