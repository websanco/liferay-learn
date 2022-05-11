# Exercise 2: Implement the Main View

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/QRsxMaEI8xs

> The exercise video above uses DXP 7.3. To complete the exercise using DXP/CE 7.4, follow the updated exercise steps below.

## Exercise Goals

- Implement the JSP files
- Implement the MVC render command for showing the Assignment list
- Implement the MVC Render command for showing a single Assignment
- Implement the back-end class for the UI management toolbar
- Test the user interface

By convention, the `init.jsp` file is used to centralize imports, taglib declarations, variable initializations, and any common tasks for all the user interface JSP files. The `init.jsp` is then included in the other JSP files, like `view.jsp`.

We'll use taglib declarations for Clay and Liferay Front-End, Liferay Item Selector, as well as imports for the classes we will be using in the front-end implementation.

## Implement the init.jsp

1. **Update** the contents of `src/main/resources/META-INF/resources/init.jsp` with:

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
```

```jsp
<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>
<%@ taglib prefix="liferay-item-selector" uri="http://liferay.com/tld/item-selector" %>
<%@ taglib prefix="liferay-frontend" uri="http://liferay.com/tld/frontend" %>
<%@ taglib prefix="liferay-portlet" uri="http://liferay.com/tld/portlet" %>
<%@ taglib prefix="liferay-theme" uri="http://liferay.com/tld/theme" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<%@ page import="java.util.Date"%>
<%@ page import="javax.portlet.WindowState"%>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil"%>

<%@ page import="com.liferay.training.gradebook.model.Assignment"%>
<%@ page import="com.liferay.training.gradebook.web.constants.MVCCommandNames"%>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />
```

The `view.jsp` implements the Assignments list view. We'll use the Management Toolbar from Clay as well the Search Container from the Liferay UI tag library to implement the view.

## Implement the view.jsp

1. **Update** the contents of portlet *VIEW* mode JSP `src/main/resources/META-INF/resources/view.jsp` with:

```jsp
<%@ include file="init.jsp"%>

<div class="container-fluid-1280">

	<h1><liferay-ui:message key="Assignments" /></h1>
	
	<%-- Clay management toolbar. --%>

	<clay:management-toolbar
		disabled="${assignmentCount eq 0}"
		displayContext="${assignmentsManagementToolbarDisplayContext}"
		itemsTotal="${assignmentCount}"
		searchContainerId="assignmentEntries"
		selectable="false"
	/>
	
	<%-- Search container. --%>

	<liferay-ui:search-container 
		emptyResultsMessage="no-assignments"
		id="assignmentEntries"
		iteratorURL="${portletURL}" 
		total="${assignmentCount}">

		<liferay-ui:search-container-results results="${assignments}" />

		<liferay-ui:search-container-row
			className="com.liferay.training.gradebook.model.Assignment"
			modelVar="entry">

			<%@ include file="/assignment/entry_search_columns.jspf" %>

		</liferay-ui:search-container-row>
		
		<%-- Iterator / Paging --%>

		<liferay-ui:search-iterator 
			displayStyle="${assignmentsManagementToolbarDisplayContext.getDisplayStyle()}"
			markupView="lexicon" 
		/>
	</liferay-ui:search-container>
</div>
```

We need three more JSP files to display a single row on the assignment list, show available actions, and display the _details_ view.

## Implement the Other JSP Files

1. **Create** a subfolder `src/main/resources/META-INF/resources/assignment`.
2. **Implement** the following three files (notice the JSP fragment suffix `.jspf` in `entry_search_columns.jspf`) in the new subfolder:

**entry_search_columns.jspf**

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
					${entry.title}
				</aui:a>
			</h4>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			path="/assignment/entry_actions.jsp" />
	</c:when>

	<%-- Card view  --%>

	<c:when
		test='${assignmentsManagementToolbarDisplayContext.getDisplayStyle().equals("icon")}'>

		<%--
			row.setCssClass("lfr-asset-item");
		--%>

		<liferay-ui:search-container-column-text>

			<%-- Vertical card. --%>

			<liferay-frontend:icon-vertical-card
				actionJsp="/assignment/entry_actions.jsp"
				actionJspServletContext="<%= application %>" 
				icon="cards2" resultRow="${row}"
				title="${entry.title}"
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
			value="<%= entry.getTitle() %>"
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

**entry_actions.jsp**

```jsp
<%@ include file="../init.jsp"%>

<c:set var="assignment" value="${SEARCH_CONTAINER_RESULT_ROW.object}" />

<liferay-ui:icon-menu markupView="lexicon">

	<%-- View action. --%>

	<portlet:renderURL var="viewAssignmentURL">
		<portlet:param name="mvcRenderCommandName"
			value="<%=MVCCommandNames.VIEW_ASSIGNMENT %>" />
		<portlet:param name="redirect" value="${currentURL}" />
		<portlet:param name="assignmentId" value="${assignment.assignmentId}" />
	</portlet:renderURL>

	<liferay-ui:icon message="view" url="${viewAssignmentURL}" />

	<%-- Edit action. --%>
	
	<portlet:renderURL var="editAssignmentURL">
		<portlet:param name="mvcRenderCommandName"
			value="<%=MVCCommandNames.EDIT_ASSIGNMENT %>" />
		<portlet:param name="redirect" value="${currentURL}" />
		<portlet:param name="assignmentId" value="${assignment.assignmentId}" />
	</portlet:renderURL>

	<liferay-ui:icon message="edit" url="${editAssignmentURL}" />	

	<%-- Delete action. --%>
	
	<portlet:actionURL name="<%=MVCCommandNames.DELETE_ASSIGNMENT %>" var="deleteAssignmentURL">
		<portlet:param name="redirect" value="${currentURL}" />
		<portlet:param name="assignmentId" value="${assignment.assignmentId}" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="${deleteAssignmentURL}" />

</liferay-ui:icon-menu>
```

**view_assignment.jsp**

```jsp
<%@ include file="../init.jsp"%>

<div class="container-fluid-1280">

	<h1>${assignment.title}</h1>

	<h2><liferay-ui:message key="assignment-information" /></h2>
	
	<div class="assignment-metadata">

		<dl>
			<dt><liferay-ui:message key="created" /></dt>
			<dd>${createDate}</dd>
	
			<dt><liferay-ui:message key="created-by" /></dt>
			<dd>${assignment.userName}</dd>
	
			<dt><liferay-ui:message key="assignment-duedate" /></dt>
			<dd>${dueDate}</dd>
	
			<dt><liferay-ui:message key="description" /></dt>
			<dd>${assignment.description}</dd>
		</dl>
	</div>
</div>
```

Now we have the JSP files in place and need *MVC Command* components to take care of the portlet lifecycle handling and  interaction between the user interface and back-end. MVC Commands respond to portlet URLs, which in JSP files are generated with the `<portlet>` tag library. 

At this stage, we need two MVC Render Commands: one for displaying the Assignments list and one for displaying a single Assignment.

Before implementing our MVC render commands, implement a constants class to hold the command names. This is a good practice to reduce the risk of typos when referencing the command names.

## Implement MVCCommandNames.java

1. **Create** a class `com.liferay.training.gradebook.web.constants.MVCCommandNames`.
2. **Implement** as follows:

```java
package com.liferay.training.gradebook.web.constants;
/**
 * @author liferay
 *
 */
public class MVCCommandNames {
	public static final String ADD_ASSIGNMENT = "/gradebook/assignment/add";
	public static final String DELETE_ASSIGNMENT = "/gradebook/assignment/delete";
	public static final String EDIT_ASSIGNMENT = "/gradebook/assignment/edit";
	public static final String VIEW_ASSIGNMENT = "/gradebook/assignment/view";
	public static final String VIEW_ASSIGNMENTS = "/gradebook/assignments/view";
	// These are used for the optional exercise "Implement Submissions".
	public static final String ADD_SUBMISSION = "/gradebook/submission/add";
	public static final String DELETE_SUBMISSION = "/gradebook/submission/delete";
	public static final String EDIT_SUBMISSION = "/gradebook/submission/edit";
	public static final String GRADE_SUBMISSION = "/gradebook/submission/grade";
	public static final String VIEW_SUBMISSION = "/gradebook/submission/view";
}
```

## Implement ViewAssignmentsMVCRenderCommand.java

1. **Create** a class `com.liferay.training.gradebook.web.portlet.action.ViewAssignmentsMVCRenderCommand`.
2. **Implement** as follows. Don't worry about the errors with `AssignmentsManagementToolbarDisplayContext`. We'll add that in the next step:

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
		"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK, 
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

		List<Assignment> assignments =
			_assignmentService.getAssignmentsByKeywords(
				themeDisplay.getScopeGroupId(), keywords, start, end,
				comparator);

		// Set request attributes.

		renderRequest.setAttribute("assignments", assignments);
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
	protected AssignmentService _assignmentService;

	@Reference
	private Portal _portal;
}
```

## Implement the MVC Render Command for Showing a Single Assignment

1. **Create** a class `com.liferay.training.gradebook.web.portlet.action.ViewSingleAssignmentMVCRenderCommand`.
2. **Implement** as follows:

**ViewSingleAssignmentMVCRenderCommand.java**

```java
package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;

import java.text.DateFormat;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Command for showing the assignment submissions list view.
 * 
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK,
		"mvc.command.name=" + MVCCommandNames.VIEW_ASSIGNMENT
	},
	service = MVCRenderCommand.class
)
public class ViewSingleAssignmentMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long assignmentId = ParamUtil.getLong(renderRequest, "assignmentId", 0);

		try {

			// Call the service to get the assignment.

			Assignment assignment =
				_assignmentService.getAssignment(assignmentId);

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				"EEEEE, MMMMM dd, yyyy");

			// Set attributes to the request.

			renderRequest.setAttribute("assignment", assignment);
			renderRequest.setAttribute(
				"dueDate", dateFormat.format(assignment.getDueDate()));
			renderRequest.setAttribute(
				"createDate", dateFormat.format(assignment.getCreateDate()));

			// Set back icon visible.

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			String redirect = renderRequest.getParameter("redirect");

			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(redirect);

			return "/assignment/view_assignment.jsp";

		}
		catch (PortalException pe) {
			throw new PortletException(pe);
		}
	}

	@Reference
	private AssignmentService _assignmentService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;
}
```

Our last task at this step is to implement the backing class for the Clay management toolbar. We won't go into implementation details with this class, but you can take a look at the [documentation](https://learn.liferay.com/dxp/latest/en/building-applications/developing-a-java-web-application/using-mvc/tag-libraries/clay-tag-library/clay-management-toolbar.html).

## Implement the Back-End Class for the UI Management Toolbar

1. **Create** a class `com.liferay.training.gradebook.web.display.context.AssignmentsManagementToolbarDisplayContext`.
2. **Implement** as follows:

```java
package com.liferay.training.gradebook.web.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

/**
	* Assigments management toolbar display context.
	*
	* This class passes contextual information to the user interface
	* for the Clay management toolbar.
	*
	* @author liferay
	*/
public class AssignmentsManagementToolbarDisplayContext
	extends BaseManagementToolbarDisplayContext {

	public AssignmentsManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest);

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
				liferayPortletRequest);

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	/**
		* Returns the creation menu for the toolbar
		* (plus sign on the management toolbar).
		*
		* @return creation menu
		*/
	public CreationMenu getCreationMenu() {

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
	
	@Override
	public String getClearResultsURL() {
		return getSearchActionURL();
	}

	/**
		* Returns the assignment list display style. 
		* 
		* Current selection is stored in portal preferences.
		* 
		* @return current display style
		*/
	public String getDisplayStyle() {

		String displayStyle = ParamUtil.getString(request, "displayStyle");

		if (Validator.isNull(displayStyle)) {
			displayStyle = _portalPreferences.getValue(
				GradebookPortletKeys.GRADEBOOK, "assignments-display-style",
				"descriptive");
		}
		else {
			_portalPreferences.setValue(
				GradebookPortletKeys.GRADEBOOK, "assignments-display-style",
				displayStyle);

			request.setAttribute(
				WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
		}

		return displayStyle;
	}

	/**
		* Returns the sort order column.
		* 
		* @return sort column
		*/
	public String getOrderByCol() {

		return ParamUtil.getString(request, "orderByCol", "title");
	}

	/**
		* Returns the sort type (ascending / descending).
		* 
		* @return sort type
		*/
	public String getOrderByType() {

		return ParamUtil.getString(request, "orderByType", "asc");
	}
	
	/**
		* Returns the action URL for the search.
		*
		* @return search action URL
		*/
	@Override
	public String getSearchActionURL() {
		
		PortletURL searchURL = liferayPortletResponse.createRenderURL();

		searchURL.setProperty(
			"mvcRenderCommandName", MVCCommandNames.VIEW_ASSIGNMENTS);
		
		String navigation = ParamUtil.getString(
			request, "navigation", "entries");
		searchURL.setParameter("navigation", navigation);		

		searchURL.setParameter("orderByCol", getOrderByCol());
		searchURL.setParameter("orderByType", getOrderByType());

		return searchURL.toString();
	}


	/**
		* Returns the view type options (card, list, table).
		*
		* @return list of view types
		*/
	@Override
	public List<ViewTypeItem> getViewTypeItems() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
				"mvcRenderCommandName", MVCCommandNames.VIEW_ASSIGNMENTS);

		int delta =
				ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM);

		if (delta > 0) {
			portletURL.setParameter("delta", String.valueOf(delta));
		}

		String orderByCol =
			ParamUtil.getString(request, "orderByCol", "title");
		String orderByType =
			ParamUtil.getString(request, "orderByType", "asc");

		portletURL.setParameter("orderByCol", orderByCol);
		portletURL.setParameter("orderByType", orderByType);

		int cur =
			ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

		if (cur > 0) {
			portletURL.setParameter("cur", String.valueOf(cur));
		}

		return new ViewTypeItemList(portletURL, getDisplayStyle()) {
			{
				addCardViewTypeItem();

				addListViewTypeItem();

				addTableViewTypeItem();
			}
		};
	}
	
	/**
		* Return the option items for the sort column menu.
		*
		* @return options list for the sort column menu
		*/
	@Override
	protected List<DropdownItem> getOrderByDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setActive("title".equals(getOrderByCol()));
						dropdownItem.setHref(
							_getCurrentSortingURL(), "orderByCol", "title");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "title"));
					});

				add(
					dropdownItem -> {
						dropdownItem.setActive(
							"createDate".equals(getOrderByCol()));
						dropdownItem.setHref(
							_getCurrentSortingURL(), "orderByCol",
							"createDate");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "create-date"));
					});
			}
		};
	}
	
	/**
		* Returns the current sorting URL.
		*
		* @return current sorting portlet URL
		*
		* @throws PortletException
		*/
	private PortletURL _getCurrentSortingURL() throws PortletException {
		PortletURL sortingURL = PortletURLUtil.clone(
			currentURLObj, liferayPortletResponse);

		sortingURL.setParameter(
			"mvcRenderCommandName", MVCCommandNames.VIEW_ASSIGNMENTS);

		// Reset current page.

		sortingURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, "0");

		String keywords = ParamUtil.getString(request, "keywords");

		if (Validator.isNotNull(keywords)) {
			sortingURL.setParameter("keywords", keywords);
		}

		return sortingURL;
	}
	
	private final PortalPreferences _portalPreferences;	
	private final ThemeDisplay _themeDisplay;
}
```

## Test the User Interface

1. **Go to** localhost:8080 in your browser.
2. **Refresh** the page to see the changes.
	- Make sure the module has restarted successfully or run `../gradlew deploy` again before refreshing the page.

---

## Next Up

* [Exercise 3: Implement the Assignment Editing View](./exercise-3-implement-the-assignment-editing-view.md)

## Previous Step

* [Introducing Tag Libraries](./introducing-tag-libraries.md)