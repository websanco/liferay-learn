# Exercise 1: Integrate with the Asset Framework

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/Dt6SP8ZTF9o

> The exercise video above uses DXP 7.3. To complete the exercise using DXP/CE 7.4, follow the updated exercise steps below.

## Exercise Goals

- Add the required fields to the Assignment entity
- Implement Asset Resource Management in the Local Service 
- Implement an `AssetRenderer` for the Assignments
- Implement an `AssetRendererFactory` for the Assignments
- Implement the JSP files 

The Asset Framework requires a certain set of fields (columns) from all the entities. We are currently missing the status fields:

- **status**: used to determine an entity's status in the workflow 
- **statusByUserId**: status audit field
- **statusByUserName**: status audit field
- **statusDate**: status audit field

We'll also add a status finder for listing Assignments by their status.

## Add the Required Fields to the Assignment Entity

1. **Open** the `service.xml` in the `gradebook-service` module.
2. **Add** the status fields. The final `service.xml` looks like this:

```xml
<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.4.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_4_0.dtd">	
<service-builder dependency-injector="ds" package-path="com.liferay.training.gradebook">
	<namespace>Gradebook</namespace>
	<!--<entity data-source="sampleDataSource" local-service="true" name="Foo" 
		remote-service="false" session-factory="sampleSessionFactory" table="foo" 
		tx-manager="sampleTransactionManager uuid="true""> -->	
	<entity local-service="true" name="Assignment" remote-service="true" uuid="true">	
		<!-- PK fields -->
		<column name="assignmentId" primary="true" type="long"></column>
		<!-- Group instance -->
		<column name="groupId" type="long"></column>
		<!-- Audit fields -->
		<column name="companyId" type="long"></column>
		<column name="userId" type="long"></column>
		<column name="userName" type="String"></column>
		<column name="createDate" type="Date"></column>
		<column name="modifiedDate" type="Date"></column>	
		<column name="description" type="String" localized="true" />
		<column name="dueDate" type="Date" />	
		<column name="status" type="int" />
		<column name="statusByUserId" type="long" />
		<column name="statusByUserName" type="String" />
		<column name="statusDate" type="Date" />
		<!-- Localization Fields -->
		<column name="title" type="String" localized="true"></column>	
		<!-- Order -->	
		<order by="asc">
			<order-column name="title" />
		</order>
		<!-- Finders -->
		<!-- Find by groupId -->
		<finder name="GroupId" return-type="Collection">
			<finder-column name="groupId"></finder-column>
		</finder>
		<!-- Reference to Group entity service -->
		<reference entity="Group" package-path="com.liferay.portal"></reference>
		<!-- Entity services needed for the integration to Asset framework -->
		<reference entity="AssetEntry"
			package-path="com.liferay.portlet.asset"></reference>
		<reference entity="AssetLink"
			package-path="com.liferay.portlet.asset"></reference>
		<reference entity="AssetTag"
			package-path="com.liferay.portlet.asset"></reference>
	</entity>
	<!-- Exceptions -->
	<exceptions>
		<exception>AssignmentValidation</exception>
	</exceptions>
</service-builder>
```

3. **Run** the `buildService` task to regenerate the service.
	
Look quickly at the re-generated `com.liferay.training.gradebook.model.AssignmentModel` interface in the `gradebook-api` model. After you added the status fields, the `WorkflowedModel` interface is also implemented, enabling the model to support Workflows, as seen here:

```java
public interface AssignmentModel extends BaseModel<Assignment>, GroupedModel,
LocalizedModel, ShardedModel, StagedAuditedModel, WorkflowedModel {
...
	}
```

As with permissions, the Asset resource life cycle must be kept in sync with your entity: whenever you create, update, or delete an Assignment entity, you must take care of the Asset resource.

## Implement Asset Resource Management in the Local Service

1. **Open** the class `com.liferay.training.gradebook.service.impl.AssignmentLocalServiceImpl`. 
2. **Implement** the new `updateAsset()` method:

```java
private void updateAsset(
	Assignment assignment, ServiceContext serviceContext)
	throws PortalException {
	assetEntryLocalService.updateEntry(
		serviceContext.getUserId(), serviceContext.getScopeGroupId(),
		assignment.getCreateDate(), assignment.getModifiedDate(),
		Assignment.class.getName(), assignment.getAssignmentId(),
		assignment.getUserUuid(), 0, serviceContext.getAssetCategoryIds(),
		serviceContext.getAssetTagNames(), true, true,
		assignment.getCreateDate(), null, null, null,
		ContentTypes.TEXT_HTML,
		assignment.getTitle(serviceContext.getLocale()),
		assignment.getDescription(), null, null, null, 0, 0,
		serviceContext.getAssetPriority());
}
```

3.  **Add** the call to the `updateAsset()` to the very end of `addAssignment()` before the `return` statement: 

```java
	// Update asset resources.
	updateAsset(assignment, serviceContext);
	return assignment;
}
```

4. **Implement** the updating Asset resource in the `updateAssignment()` method:

```java
	// Update Asset resources.	
	updateAsset(assignment, serviceContext);
	return assignment;
}
```

5. **Implement** deleting the Asset resource at the very end of the `deleteAssignment()` method before the return statement:

```java
	// Delete the Asset resource.
	assetEntryLocalService.deleteEntry(
		Assignment.class.getName(), assignment.getAssignmentId());
	return super.deleteAssignment(assignment);
}
```

6. **Resolve** missing imports.
7. **Rebuild** the service.

The service layer is now ready for the Asset Framework. Next, we'll create the `AssetRenderer` and `AssetRendererFactory` components in the `gradebook-web` to take care of displaying the assets in a standard way, for example, in the Asset Publisher portlet.

First, we'll add the required dependencies to the `gradebook-web`.

## Implement an Asset Renderer Factory for the Assignments

1. **Open** the `build.gradle` of the `gradebook-web` bundle.
2. **Add** dependency for the `AssetHelper` utility, located in the `com.liferay.asset.api` bundle, and Asset Display Page Api:

```groovy
compileOnly group: "com.liferay", name: "com.liferay.asset.api"
compileOnly group: "com.liferay", name: "com.liferay.asset.display.page.api"
```	

3. **Run** Gradle refresh to refresh the dependencies.
4. **Create** a class `com.liferay.training.gradebook.web.asset.model.AssignmentAssetRendererFactory` and implement as follows:

```java
package com.liferay.training.gradebook.web.asset.model;
	
import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.training.gradebook.constants.GradebookConstants;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentLocalService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;
	
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.ServletContext;
	
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
	
/**
 * Asset renderer factory component for assignments.
 *
 * @author liferay
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK,
	service = AssetRendererFactory.class
)
public class AssignmentAssetRendererFactory
	extends BaseAssetRendererFactory<Assignment> {
	
	public static final String CLASS_NAME = Assignment.class.getName();
	
	public static final String TYPE = "assignment";
	
	public AssignmentAssetRendererFactory() {
		setClassName(CLASS_NAME);
		setLinkable(true);
		setPortletId(GradebookPortletKeys.GRADEBOOK);
		setSearchable(true);
	}
	
	@Override
	public AssetRenderer<Assignment> getAssetRenderer(long classPK, int type)
		throws PortalException {
	
		Assignment assignment = _assignmentLocalService.getAssignment(classPK);
	
		AssignmentAssetRenderer assignmentAssetRenderer =
			new AssignmentAssetRenderer(
					assignment);
	
		assignmentAssetRenderer.setAssetDisplayPageFriendlyURLProvider( 
			_assetDisplayPageFriendlyURLProvider);
		assignmentAssetRenderer.setAssetRendererType(type);
		assignmentAssetRenderer.setServletContext(_servletContext);
	
		return assignmentAssetRenderer;
	}
		
	@Override
	public String getType() {
		return TYPE;
	}
		
	@Override
	public PortletURL getURLAdd(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, long classTypeId) {
	
		PortletURL portletURL = _portal.getControlPanelPortletURL(
			liferayPortletRequest, getGroup(liferayPortletRequest),
			GradebookPortletKeys.GRADEBOOK, 0, 0, PortletRequest.RENDER_PHASE);
	
		portletURL.setParameter("mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);
	
		return portletURL;
	}
		
	@Override
	public PortletURL getURLView(
		LiferayPortletResponse liferayPortletResponse,
		WindowState windowState) {
	
		LiferayPortletURL liferayPortletURL =
			liferayPortletResponse.createLiferayPortletURL(
				GradebookPortletKeys.GRADEBOOK, PortletRequest.RENDER_PHASE);
	
		try {
			liferayPortletURL.setWindowState(windowState);
		}
		catch (WindowStateException wse) {
		}
	
		return liferayPortletURL;
	}	
	
	@Override
	public boolean hasAddPermission(
			PermissionChecker permissionChecker, long groupId, long classTypeId)
		throws Exception {
	
		return _portletResourcePermission.contains(
			permissionChecker, groupId, ActionKeys.ADD_ENTRY);
	}
	
	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {
	
		return _assignmentModelResourcePermission.contains(
			permissionChecker, classPK, actionId);
	}
	
	@Reference
	private AssetDisplayPageFriendlyURLProvider
		_assetDisplayPageFriendlyURLProvider;
		
	@Reference
	private AssignmentLocalService _assignmentLocalService;
	
	@Reference(
		target = "(model.class.name=com.liferay.training.gradebook.model.Assignment)"
	)
	private ModelResourcePermission<Assignment>
		_assignmentModelResourcePermission;
	
	@Reference
	private Portal _portal;
	
	@Reference(
		target = "(resource.name=" + GradebookConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;
	
	@Reference
	private PortletURLFactory _portletURLFactory;
	
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.training.gradebook.web)"
	)
	private ServletContext _servletContext;
	
}
```

## Implement an AssetRenderer for the Assignments

1. **Create** a class `com.liferay.training.gradebook.web.asset.model.AssignmentAssetRenderer` and implement as follows:

```java
package com.liferay.training.gradebook.web.asset.model;
	
import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.asset.util.AssetHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;
import com.liferay.training.gradebook.web.internal.security.permission.resource.AssignmentPermission;
	
import java.util.Locale;
	
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	
/**
 * Asset renderer for assignments.
 *
 * @author liferay
 */
public class AssignmentAssetRenderer extends BaseJSPAssetRenderer<Assignment> {
	 
	public AssignmentAssetRenderer(
		Assignment assignment) {
	
		_assignment = assignment;
	}
	
	@Override
	public Assignment getAssetObject() {
		return _assignment;
	}
	
	@Override
	public String getClassName() {
		return Assignment.class.getName();
	}
	
	@Override
	public long getClassPK() {
		return _assignment.getAssignmentId();
	}
	
	@Override
	public long getGroupId() {
		return _assignment.getGroupId();
	}
	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
	
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {
	
			return "/asset/" + template + ".jsp";
		}
	
		return null;
	}
	
	@Override
	public int getStatus() {
		return _assignment.getStatus();
	}
	
	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {
	
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	
		int abstractLength = AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH;
	
		String summary = HtmlUtil.stripHtml(
			StringUtil.shorten(
				_assignment.getDescription(),
				abstractLength));
	
		return summary;
	}
	
	@Override
	public String getTitle(Locale locale) {
		return _assignment.getTitle(locale);
	}
		
	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {
	
		Group group = GroupLocalServiceUtil.fetchGroup(_assignment.getGroupId());
	
		if (group.isCompany()) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)liferayPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);
	
			group = themeDisplay.getScopeGroup();
		}
	
		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			liferayPortletRequest, group, GradebookPortletKeys.GRADEBOOK, 0, 0,
			PortletRequest.RENDER_PHASE);
	
		portletURL.setParameter(
			"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);
		portletURL.setParameter(
			"assignmentId", String.valueOf(_assignment.getAssignmentId()));
		portletURL.setParameter("showback", Boolean.FALSE.toString());
	
		return portletURL;
	}
		
	@Override
	public String getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws Exception {
	
		return super.getURLView(liferayPortletResponse, windowState);
	}
	
	@Override
	public String getURLViewInContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			String noSuchEntryRedirect)
		throws Exception {
	
		if (_assetDisplayPageFriendlyURLProvider != null) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)liferayPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);
	 
			String friendlyURL =
				_assetDisplayPageFriendlyURLProvider.getFriendlyURL(
					getClassName(), getClassPK(), themeDisplay);
	
			if (Validator.isNotNull(friendlyURL)) {
				return friendlyURL;
			}
		}
			
		try {
			long plid = PortalUtil.getPlidFromPortletId(
				_assignment.getGroupId(), GradebookPortletKeys.GRADEBOOK
			);
	
			PortletURL portletURL;
	
			if (plid == LayoutConstants.DEFAULT_PLID) {
				portletURL = liferayPortletResponse.createLiferayPortletURL(
					getControlPanelPlid(liferayPortletRequest),
					GradebookPortletKeys.GRADEBOOK,
					PortletRequest.RENDER_PHASE);
			}
			else {
				portletURL =
					PortletURLFactoryUtil.getPortletURLFactory(
					).create(
						liferayPortletRequest, GradebookPortletKeys.GRADEBOOK,
						plid, PortletRequest.RENDER_PHASE
					);
			}
	
			portletURL.setParameter(
				"mvcRenderCommandName", MVCCommandNames.VIEW_ASSIGNMENT);
			portletURL.setParameter(
				"assignmentId", String.valueOf(_assignment.getAssignmentId()));
	
			String currentUrl = PortalUtil.getCurrentURL(
				liferayPortletRequest 
			);
	
			portletURL.setParameter("redirect", currentUrl);
	
			return portletURL.toString();
		}
		catch (PortalException pe) {
		}
		catch (SystemException se) {
		}
	
		return null;
	}
	
	@Override
	public long getUserId() {
		return _assignment.getUserId();
	}
	
	@Override
	public String getUserName() {
		return _assignment.getUserName();
	}
	
	@Override
	public String getUuid() {
		return _assignment.getUserUuid();
	}
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker)
		throws PortalException {
	
		return AssignmentPermission.contains(
			permissionChecker, _assignment, ActionKeys.UPDATE);
	}
	
	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker)
		throws PortalException {
	
		return AssignmentPermission.contains(
			permissionChecker, _assignment, ActionKeys.VIEW);
	}
	
	@Override
	public boolean include(
			HttpServletRequest request, HttpServletResponse response,
			String template)
		throws Exception {
	
		request.setAttribute("assignment", _assignment);
	
		return super.include(request, response, template);
	}
		
	public void setAssetDisplayPageFriendlyURLProvider(
		AssetDisplayPageFriendlyURLProvider
			assetDisplayPageFriendlyURLProvider) {
	
		_assetDisplayPageFriendlyURLProvider =
			assetDisplayPageFriendlyURLProvider;
	}
		
	private AssetDisplayPageFriendlyURLProvider
	_assetDisplayPageFriendlyURLProvider;
	
	private Assignment _assignment;
}
```

> See [Enabling Assets](https://learn.liferay.com/dxp/latest/en/building-applications/data-frameworks/asset-framework/enabling-assets.html#create-an-asset-renderer) for more information about Asset renderers.

As the final step, we'll implement the JSP files for _abstract_ and _full content_ Asset views. If you look at the `getJspPath()` method in the `AssetRenderer` just created, you'll see how the file path is built:
```java
@Override
public String getJspPath(HttpServletRequest request, String template) {

	return "/asset/" + template + ".jsp";
}
```

## Implement the JSP files

1. **Add** imports for the [`AssetRenderer`](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-kernel/src/com/liferay/asset/kernel/model/AssetRenderer.java) and [`WebKeys`](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-kernel/src/com/liferay/portal/kernel/util/WebKeys.java) in the `src/main/resources/META-INF/resources/init.jsp`.

```html
<%@ page import="com.liferay.asset.kernel.model.AssetRenderer"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
```

2. **Create** a folder `src/main/resources/META-INF/resources/asset` in the _gradebook-web_ module.
3. **Implement** the two files in the folder:

**abstract.jsp**

```jsp
<%@ include file="/init.jsp"%>

<p>
	<%
		AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute(WebKeys.ASSET_RENDERER);
	%>

	<%= HtmlUtil.escape(assetRenderer.getSummary(renderRequest, renderResponse)) %>
</p>
```

**full_content.jsp**

```jsp   
<%@ include file="/init.jsp"%>

<%
	AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute(WebKeys.ASSET_RENDERER);
					
	String viewEntryURL = assetRenderer.getURLView(liferayPortletResponse, WindowState.MAXIMIZED);
					
	Assignment assignment = (Assignment)request.getAttribute("assignment");					
%>

<aui:a cssClass="title-link" href="<%= viewEntryURL %>">
	<h3 class="title"><%= HtmlUtil.escape(assignment.getTitle(locale)) %></h3>
</aui:a>

<div class="autofit-col autofit-col-expand">
	<%= HtmlUtil.escape(assignment.getDescription()) %>
</div>
```

## Deploy and Test

1. **Check** that the `gradebook-web` module redeploys successfully and that you are able to access the Gradebook application in your web browser.

Remember that to be able to show Assets in the Asset Publisher portlet, we also have to integrate to the Search Framework. We'll do that in the next exercise.

---

## Next Up

* [Integrate with the Search Framework](./integrate-with-the-search-framework.md)

## Previous Step

* [Integrate with the Asset Framework](./integrate-with-the-asset-framework.md)

