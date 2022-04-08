---
description: 06 - Real World Application
title: Implement Workflow Support
order: 8
---

## Implement Workflow Support Introduction

A workflow is an orchestrated and repeatable pattern or sequence of operations. Typically, workflows are used in review processes, but they're often used in integrating with other systems as well.

In Liferay, any registered asset can be assigned to a workflow. For handling the workflows, Liferay is using its own Kaleo workflow engine, but integrations to other workflow engines are available from third parties.

For designing the workflows, there is a workflow editor available in the *Control Panel*.

<img src="../images/workflow-editor.png" style="max-height:100%"/>

#### Implementing Support for Workflows

Here are the general steps for enabling workflows for a custom entity:

1. Ensure that the model entity has status fields.
1. Add workflow instance creation and deletion handling to the service layer.
1. Make getter methods on the service layer status-aware.
1. Add a method on the service layer for updating the status. 
1. Create a workflow handler component.
1. Implement the status on the user interface as needed.

#### Implementation Example

As an example, let's take a look at the workflow support implementation in the Liferay Blogs application: https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/blogs.

#### 1. Status Field in service.xml

```xml
<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.1.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_1_0.dtd">

<service-builder auto-import-default-references="false" auto-namespace-tables="false" package-path="com.liferay.blogs">
	<namespace>Blogs</namespace>
	<entity local-service="true" name="BlogsEntry" remote-service="true" trash-enabled="true" uuid="true">
		
		...

		<column name="status" type="int" />
		<column name="statusByUserId" type="long" />
		<column name="statusByUserName" type="String" uad-anonymize-field-name="fullName" />
		<column name="statusDate" type="Date" />
		
		...
```

#### 2. Workflow Instance Creation and Deletion Handling on the Service Layer

Workflow instance creation and deletion is handled in `com.liferay.blogs.service.impl.BlogsEntryLocalServiceImpl`:

**Create workflow instance**
```java
@Indexable(type = IndexableType.REINDEX)
@Override
public BlogsEntry addEntry(
		long userId, String title, String subtitle, String urlTitle,
		String description, String content, Date displayDate,
		boolean allowPingbacks, boolean allowTrackbacks,
		String[] trackbacks, String coverImageCaption,
		ImageSelector coverImageImageSelector,
		ImageSelector smallImageImageSelector,
		ServiceContext serviceContext)
	throws PortalException {

	...

	return startWorkflowInstance(userId, entry, serviceContext);
}
```

**Delete workflow instance**
```java
	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public BlogsEntry deleteEntry(BlogsEntry entry) throws PortalException {

		...
	
		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			entry.getCompanyId(), entry.getGroupId(),
			BlogsEntry.class.getName(), entry.getEntryId());
	
		return entry;
	}
```

#### 3. Status-Aware Getter Methods

Status in a parameter in the getter methods in `com.liferay.blogs.service.impl.BlogsEntryLocalServiceImpl`: 

```java
	@Override
	public List<BlogsEntry> getCompanyEntries(
		long companyId, Date displayDate,
		QueryDefinition<BlogsEntry> queryDefinition) {

		if (queryDefinition.isExcludeStatus()) {
			return blogsEntryPersistence.findByC_LtD_NotS(
				companyId, displayDate, queryDefinition.getStatus(),
				queryDefinition.getStart(), queryDefinition.getEnd(),
				queryDefinition.getOrderByComparator());
		}
		else {
			return blogsEntryPersistence.findByC_LtD_S(
				companyId, displayDate, queryDefinition.getStatus(),
				queryDefinition.getStart(), queryDefinition.getEnd(),
				queryDefinition.getOrderByComparator());
		}
	}
	...
```

#### 4. Method for Updating Status

The status updating method is in the `com.liferay.blogs.service.impl.BlogsEntryLocalServiceImpl`:

```java
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BlogsEntry updateStatus(
			long userId, long entryId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {
		// Entry
		User user = userLocalService.getUser(userId);
		Date now = new Date();
	
		BlogsEntry entry = blogsEntryPersistence.findByPrimaryKey(entryId);
	
		validate(
			entry.getTitle(), entry.getUrlTitle(), entry.getContent(), status);
	
		int oldStatus = entry.getStatus();
	
		if ((status == WorkflowConstants.STATUS_APPROVED) &&
			now.before(entry.getDisplayDate())) {
	
			status = WorkflowConstants.STATUS_SCHEDULED;
		}

		...

		return entry;
	}
```

### 5. Workflow Handler Component

Workflow handler component `com.liferay.blogs.internal.workflow.BlogsEntryWorkflowHandler`:

```java
@Component(
	property = "model.class.name=com.liferay.blogs.model.BlogsEntry",
	service = WorkflowHandler.class
)
public class BlogsEntryWorkflowHandler extends BaseWorkflowHandler<BlogsEntry> {

	...
	
	@Override
	public BlogsEntry updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException {
	
		long userId = GetterUtil.getLong(
			(String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long classPK = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	
		ServiceContext serviceContext = (ServiceContext)workflowContext.get(
			"serviceContext");
	
		return _blogsEntryLocalService.updateStatus(
			userId, classPK, status, serviceContext, workflowContext);
	}		
	
	...
```

### 6. Implementation on the User Interface

Status field is displayed, for example, in the Blogs search page:

```html
<c:choose>
	<c:when test='<%= displayStyle.equals("descriptive") %>'>
		<liferay-ui:search-container-column-user
			showDetails="<%= false %>"
			userId="<%= entry.getUserId() %>"
		/>

		<liferay-ui:search-container-column-text
			colspan="<%= 2 %>"
		>

			<%
			Date modifiedDate = entry.getModifiedDate();

			String modifiedDateDescription = LanguageUtil.getTimeDescription(request, 
			System.currentTimeMillis() - modifiedDate.getTime(), true);
			%>

			<h5 class="text-default">
				<liferay-ui:message arguments="<%= new String[] {entry.getUserName(), modifiedDateDescription} %>" key="x-modified-x-ago" />
			</h5>

			<h4>
				<aui:a href="<%= rowURL.toString() %>">
					<%= BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) %>
				</aui:a>
			</h4>

			<h5 class="text-default">
				<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" 
				showLabel="<%= false %>" status="<%= entry.getStatus() %>" />
			</h5>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			path="/blogs_admin/entry_action.jsp"
		/>
	</c:when>
</c:choose>
```