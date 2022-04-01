## Update Application with Localizable Elements

<div class="ahead">

#### Exercise Goals

- Update the service.xml for the Gradebook Application by making the Title field localizable
- Modify the Service module for a localizable Title field
- Modify the API module for a localizable Title field
- Modify the Web module for a localizable Title field

</div>
	
#### Update the service.xml
1. **Start** Developer Studio with the Gradebook-workspace if its not already started.
	- Make sure to start the server as well.
* **Open** the `service.xml` file.
* **Remove** the `title` column.
* **Create** a new section below the `<!-- Audit fields -->` with the following code:

```xml
<!-- Localization Fields -->

<column name="title" type="String" localized="true"></column>
```

<div class="page">

#### Build the Service
1. **Expand** the `training-workspace/modules/gradebook/build` in the *Gradle Tasks* panel.
* **Execute** the `buildService` task.

#### Update Assignment LocalServiceImpl
1. **Open** _AssignmentLocalServiceImpl_ in the gradebook-service module.
* **Find** the `addAssignment` class.
* **Replace** `String title` with `Map<Locale, String> titleMap`.
* **Replace** all instances of the `title` variable with `titleMap`.
* **Find** the `updateAssignment` overload.
* **Replace** `String title` with `Map<Locale, String> titleMap`.
* **Replace** all instances of the `title` variable with `titleMap`.
	- The completed class should look like this:

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
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.base.AssignmentLocalServiceBaseImpl;
import com.liferay.training.gradebook.validator.AssignmentValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the assignment local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.liferay.training.gradebook.service.AssignmentLocalService</code>
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssignmentLocalServiceBaseImpl
 */
@Component(
   property = "model.class.name=com.liferay.training.gradebook.model.Assignment", 
   service = AopService.class
)

public class AssignmentLocalServiceImpl extends AssignmentLocalServiceBaseImpl {
   
   public Assignment addAssignment(long groupId, Map<Locale, String> titleMap, String description,
         Date dueDate, ServiceContext serviceContext) throws PortalException {
	// Validate assignment parameters.
			_assignmentValidator.validate(titleMap, description, dueDate);
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
      assignment.setDescription(description);
      assignment.setGroupId(groupId);
      assignment.setModifiedDate(serviceContext.getModifiedDate(new Date()));
      assignment.setTitleMap(titleMap);
      assignment.setUserId(userId);
      assignment.setUserName(user.getScreenName());
      // Persist assignment to database.
      return super.addAssignment(assignment);
   }
   
   public Assignment updateAssignment(long assignmentId, Map<Locale, String> titleMap,
         String description, Date dueDate, ServiceContext serviceContext) throws PortalException {
	   // Validate assignment parameters.
	   _assignmentValidator.validate(titleMap, description, dueDate);
      // Get the Assignment by id.
      Assignment assignment = getAssignment(assignmentId);
      // Set updated fields and modification date.
      assignment.setModifiedDate(new Date());
      assignment.setTitleMap(titleMap);
      assignment.setDueDate(dueDate);
      assignment.setDescription(description);
      assignment = super.updateAssignment(assignment);
      return assignment;
   }
   
   public List<Assignment> getAssignmentsByGroupId(long groupId) {
      return assignmentPersistence.findByGroupId(groupId);
   }
   public List<Assignment> getAssignmentsByGroupId(long groupId, int start, int end) {
      return assignmentPersistence.findByGroupId(groupId, start, end);
   }
   public List<Assignment> getAssignmentsByGroupId(long groupId, int start, int end,
         OrderByComparator<Assignment> orderByComparator) {
      return assignmentPersistence.findByGroupId(groupId, start, end, orderByComparator);
   }
   public List<Assignment> getAssignmentsByKeywords(
      long groupId, String keywords, int start, int end,
      OrderByComparator<Assignment> orderByComparator) {
      return assignmentLocalService.dynamicQuery(
         getKeywordSearchDynamicQuery(groupId, keywords), start, end,
         orderByComparator);
   }
   public long getAssignmentsCountByKeywords(long groupId, String keywords) {
      return assignmentLocalService.dynamicQueryCount(
         getKeywordSearchDynamicQuery(groupId, keywords));
   }
   private DynamicQuery getKeywordSearchDynamicQuery(
      long groupId, String keywords) {
      DynamicQuery dynamicQuery = dynamicQuery().add(
         RestrictionsFactoryUtil.eq("groupId", groupId));
      if (Validator.isNotNull(keywords)) {
         Disjunction disjunctionQuery =
            RestrictionsFactoryUtil.disjunction();
         disjunctionQuery.add(
            RestrictionsFactoryUtil.like("title", "%" + keywords + "%"));
         disjunctionQuery.add(
            RestrictionsFactoryUtil.like(
               "description", "%" + keywords + "%"));
         dynamicQuery.add(disjunctionQuery);
      }
      return dynamicQuery;
   }
   
   @Override
   public Assignment addAssignment(Assignment assignment) {
      throw new UnsupportedOperationException("Not supported.");
   }
   @Override
   public Assignment updateAssignment(Assignment assignment) {
      throw new UnsupportedOperationException("Not supported.");
   }
   @Reference
   AssignmentValidator _assignmentValidator;
}
```

#### Update AssignmentServiceImpl
1. **Open** `AssignmentServiceImpl.java`.
* **Find** the `addAssignment` class.
* **Replace** `String title` with `Map<Locale, String> titleMap`.
* **Replace** all instances of the `title` variable with `titleMap`.
* **Find** the `updateAssignment` overload.
* **Replace** `String title` with `Map<Locale, String> titleMap`.
* **Replace** all instances of the `title` variable with `titleMap`.
	- The completed class should look like this:

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
		long groupId, Map<Locale, String> titleMap, String description,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {

		return assignmentLocalService.addAssignment(
			groupId, titleMap, description, dueDate, serviceContext);
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
		long assignmentId, Map<Locale, String> titleMap, String description,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {

		return assignmentLocalService.updateAssignment(
			assignmentId, titleMap, description, dueDate, serviceContext);
	}	
}
```

#### Rebuild and Deploy the Service
1. **Run** the `buildService` Gradle task to regenerate the service.
1. **Start** the server if it's not running.
1. **Drag** the *gradebook-api* and *gradebook-service* modules onto the Liferay server to deploy the modules if they're not already deployed.

You should see a success log message if modules were deployed successfully:

```
2019-03-20 11:31:59.667 INFO  [pipe-start 984][BundleStartStopLogger:39] STARTED com.liferay.training.gradebook.api_1.0.0 [984]
2019-03-20 11:32:02.573 INFO  [pipe-start 985][BundleStartStopLogger:39] STARTED com.liferay.training.gradebook.service_1.0.0 [985]
```

#### Update entry_search_columns.jspf
1. **Open** `entry_search_columns.jspf` from the gradebook-web module.
* **Replace** every `entry.getTitle` with `entry.getTitle(locale)`.

```jsp
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
				  
				  	<div class="truncate-text">
				  	
				  		<%-- Strip HTML --%>
				  		
				  		<%=HtmlUtil.stripHtml(entry.getDescription()) %>
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

		<liferay-ui:search-container-column-jsp 
			name="actions"
			path="/assignment/entry_actions.jsp" 
		/>
	</c:otherwise>
</c:choose>
```

#### Update AddAssignmentMVCActionCommand
1. **Open** `AddAssignmentMVCActionCommand` class.
* **Find** the comment `// Get parameters from the request.`.
* **Add** code for localization and the titleMap below that.
	- See the example below
* **Add** the missing imports:

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

        String description = ParamUtil.getString(actionRequest, "description", null);

        Date dueDate = ParamUtil.getDate(actionRequest, "dueDate", null);

        try {

			// Call the service to add a new assignment.
			
			_assignmentService.addAssignment(
				themeDisplay.getScopeGroupId(), titleMap, description, dueDate, serviceContext);

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

#### Update EditAssignmentMVCActionCommand
1. **Open** `EditAssignmentMVCActionCommand` class.
* **Find** the comment `// Get parameters from the request.`.
* **Add** code for localization below that:
* **Add** the missing imports.

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

		// Use LocalizationUtil to get a localized parameter.

		Map<Locale, String> titleMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "title");

		String description = ParamUtil.getString(actionRequest, "description", null);

		Date dueDate = ParamUtil.getDate(actionRequest, "dueDate", null);

		try {
			
			// Call the service to update the assignment
			
			_assignmentService.updateAssignment(
				assignmentId, titleMap, description, dueDate, serviceContext);

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

#### Update the Assignment Validator Interface
1. **Open** the `AssignmentValidator` interface in the gradebook-api module.
* **Replace** `String title` with `Map<Locale, String> titleMap`.
* **Add** missing imports.

```java
package com.liferay.training.gradebook.validator;

import com.liferay.training.gradebook.exception.AssignmentValidationException;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

public interface AssignmentValidator {
```
<!-- pagebreak-->
```java
	/**
	 * Validates an Assignment
	 * 
	 * @param title
	 * @param description
	 * @param dueDate
	 * @throws AssignmentValidationException
	 */
	public void validate(
            Map<Locale, String> title, String description, Date dueDate)
			throws AssignmentValidationException;
}
```

#### Update the Assignment Validator Service Component
1. **Open** the `AssignmentValidatorImpl` class.
* **Update** the `title` variable from a `String` to `titleMap` defined as `Map<Locale, String>`.
* **Add*** missing imports.

```java
package com.liferay.training.gradebook.util.validator;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.training.gradebook.exception.AssignmentValidationException;
import com.liferay.training.gradebook.validator.AssignmentValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	service = AssignmentValidator.class
)
public class AssignmentValidatorImpl implements AssignmentValidator {

	/**
	 * Validates assignment values and throws
	 * {AssignmentValidationExceptionException} if the assignment values are not
	 * valid.
	 * 
	 * @param titleMap
	 * @param description
	 * @param dueDate
	 * @throws AssignmentValidationException
	 */
	public void validate(
		Map<Locale, String> titleMap, String description, Date dueDate)
		throws AssignmentValidationException {

		List<String> errors = new ArrayList<>();
		
		if (!isAssignmentValid(titleMap, description, dueDate, errors)) {
			throw new AssignmentValidationException(errors);
		}
	}
	
	/**
	 * Returns <code>true</code> if the given fields are valid for an
	 * assignment.
	 * 
	 * @param titleMap
	 * @param description
	 * @param dueDate
	 * @param errors
	 * @return boolean <code>true</code> if assignment is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isAssignmentValid(
		final Map<Locale, String> titleMap, final String description,
		final Date dueDate, final List<String> errors) {

		boolean result = true;

		result &= isTitleValid(titleMap, errors);
		result &= isDueDateValid(dueDate, errors);
		result &= isDescriptionValid(description, errors);

		return result;
	}

	/**
	 * Returns <code>true</code> if description is valid for an assignment. If
	 * not valid, an error message is added to the errors list.
	 * 
	 * @param description
	 * @param errors
	 * @return boolean <code>true</code> if description is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isDescriptionValid(
		final String description, final List<String> errors) {

			boolean result = true;

			// Verify the map has something

			if (description == "") {
				errors.add("assignmentDescriptionEmpty");
				result = false;
			}
		return result;
	}
	

	/**
	 * Returns <code>true</code> if due date is valid for an assignment. If not
	 * valid, an error message is added to the errors list.
	 * Note that this error can't ever happen in the user interface because
	 * we are always getting a default value on the controller layer (Action Commands)
	 * However, this service could be access through the WS Api, which is why we need it.
	 * 
	 * @param dueDate
	 * @param errors
	 * @return boolean <code>true</code> if due date is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isDueDateValid(
		final Date dueDate, final List<String> errors) {

		boolean result = true;

		if (Validator.isNull(dueDate)) {
			errors.add("assignmentDateEmpty");
			result = false;
		}

		return result;
	}

	/**
	 * Returns <code>true</code> if title is valid for an assignment. If not
	 * valid, an error message is added to the errors list.
	 * 
	 * @param titleMap
	 * @param errors
	 * @return boolean <code>true</code> if the title is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isTitleValid(
		final Map<Locale, String> titleMap, final List<String> errors) {

		boolean result = true;

		// Verify the map has something

		if (MapUtil.isEmpty(titleMap)) {
			errors.add("assignmentTitleEmpty");
			result = false;
		}
		else {

			// Get the default locale.

			Locale defaultLocale = LocaleUtil.getSiteDefault();

			if ((Validator.isBlank(titleMap.get(defaultLocale)))) {
				errors.add("assignmentTitleEmpty");
				result = false;
			}
		}

		return result;
	}
}
```

#### Update Implement Validation in the Assignment Service
1. **Open** the `AssignmentLocalServiceImpl` class in the gradebook-service module.
* **Update** the `title` variable from a `String` to `titleMap` defined as `Map<Locale, String>`.
* **Add*** missing imports.
* **Rebuild** the service.

```java


```

#### Test Localization
1. **Go to** localhost:8080 in your browser.
2. **Refresh** the page.
	* Make sure the module has restarted successfully before refreshing the page.
3. **Click** the _Options_ icon next to the _Test Assignment_.
4. **Choose** _Edit_.
5. **Click** the _Localization_ icon (default should be an American flag with _en-US_).
6. **Choose** the French option (_fr-Fr_ with the French flag).
7. **Type** `Le Attribution de Test` for _Title_ (The original English title will display beneath the field).  
8. **Click** _Save_.

<br />

<img src="images/localized_assignment_title.png" style="max-height:40%;" />

