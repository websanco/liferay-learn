# Working with Workflow Context

[Workflows](../introduction-to-workflow.md) in Liferay are used as approval processes. At each step of the process, the workflow has certain data that's available in a `Map<String, Serializable>` object referred to in your scripts and custom code as `workflowContext`. The workflow context information is important to the proper functionality of Liferay's workflow engine. Understanding it can help you determine how best to use it in your custom code and workflow scripts.

For example, with workflow context you can

- [Access any of the existing attributes for use in workflow scripts, custom code, or FreeMarker templates.](#accessing-workflow-context-attributes-in-workflow-definitions)
- [Set new attributes at one step of a workflow process for access in workflow scripts, custom code, or FreeMarker templates.](#setting-workflow-context-attributes-in-a-workflow-process-definition)
- [Set `ServiceContext` attributes, access them in workflow scripts and notification templates.](#setting-service-context-attributes-for-access-in-workflow-definitions)

```{note}
Use `ServiceContext` to set attributes in contexts where a `workflowContext` is not available. For example, if your custom code calls `BlogsEntryLocalService#addEntry`, you must provide a `ServiceContext` object to it. You can use `ServiceContext#setAttribute` to pass in data that you want to access in the workflow. 
```
There are some important things to be aware of when working with `workflowContext`:

- It must be modifiable, therefore it isn't thread safe. Caution is advised in parallel execution contexts.

  For example, in a workflow with a fork node, updating the `workflowContext` in both forks of the workflow is not recommended.

- Its first type parameter (the `key` for the attribute) is a String. This is used to look up the value stored in the second attribute.
- Its second type parameter (the `value` for each attribute) is a `Serializable` because it's stored in the database. This ensures that it's accessible at every step of the workflow.

To print the workflow context keys and values in any workflow node, you can add a script action like this one:
```xml
<actions>
    <action>
        <name>print-workflow-context</name>
        <script>
            <![CDATA[
            for (Map.Entry<String, Serializable> mapEntry :
                workflowContext.entrySet()) {
                    out.println(mapEntry.getKey(), mapEntry.getValue());
            }
            ]]>
        </script>
        <script-language>groovy</script-language>
        <execution-type>onEntry</execution-type>
    </action>
</actions>
```

When the node is entered, output is printed to the log:

```
entryType, Blogs Entry
companyId, 37401
entryClassPK, 40226
entryClassName, com.liferay.blogs.model.BlogsEntry
groupId, 37441
taskComments, 
userPortraitURL, /image/user_portrait?img_id=0&img_id_token=IpLU58ogLTDf%2FDIfo8Ukg0YxiUE%3D&t=1626283728181
serviceContext, com.liferay.portal.kernel.service.ServiceContext@565b5550
userId, 37448
url, http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_blogs_web_portlet_BlogsAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_mvcRenderCommandName=%2Fblogs%2Fview_entry&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_entryId=40226&p_p_auth=rRDR0ncV
userURL, http://localhost:8080/web/test
```

## Accessing Workflow Context Attributes in Workflow Definitions

To access `workflowContext` attributes form a [`<script>`](using-the-script-engine-in-workflow.md), retrieve them with the `Map#get` method:

```groovy
import com.liferay.portal.kernel.workflow.WorkflowConstants;

String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
```

The above example retrieves a `String`, but some of the `workflowContext` attributes must be used as `long`s (for example, when passed as method parameters). The `GetterUtil` utility class helps with this:

```groovy
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
```

Using the [`WorkflowConstants`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/workflow/WorkflowConstants.java) object fields helps avoid error-prone String literals. The `workflowContext` fields are all prefixed with `CONTEXT` (e.g., `CONTEXT_COMPANY_ID`).


## Setting Workflow Context Attributes in Workflow Definitions

To set attributes into the `workflowContext`, use the `Map#put` method. This example sets the `assetTitle`:

```groovy
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);

String assetTitle = assetRenderer.getAssetObject().getTitle();

workflowContext.put("assetTitle", assetTitle);
```

```{tip}
The above code works only if the asset has a `getTitle` method (for example, `JournalArticle`).
```

## Setting Service Context Attributes for Access in Workflow Definitions

Sometimes in your custom Java code, you must pass information to the workflow definition but there's no `workflowContext` to pass through. For example, if you're writing code that adds Blogs Entries, you can call one of the [`BlogsEntryLocalService#addEntry`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/blogs/blogs-api/src/main/java/com/liferay/blogs/service/BlogsEntryLocalService.java) methods. Even though `workflowContext` isn't a parameter in these methods, `ServiceContext` is. Add a new attribute to the service context:

```java
serviceContext.setAttribute("customAttributeKey", "customAttributeValue");
```

To get the attribute in the workflow definition, retrieve the `ServiceContext` from the `workflowContext`, the get the attribute using its key:

```groovy
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

ServiceContext serviceContext = (ServiceContext)workflowContext.get(WorkflowConstants.CONTEXT_SERVICE_CONTEXT);

serviceContext.getAttribute("customAttributeKey");
```

## Related Information

- [Workflow Notification Template Variables](./workflow-notification-template-variables.md)
- [Using the Script Engine in Workflow](./using-the-script-engine-in-workflow.md)

