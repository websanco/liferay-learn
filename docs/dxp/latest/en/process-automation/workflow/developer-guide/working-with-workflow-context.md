# Working with Workflow Context

Workflows in Liferay are used as approval processes. At each step of the process, the workflow has certain data that's available in a `Map<String, Serializable>` object referred to in your scripts and custom code as `workflowContext`. The workflow context information is important to the proper functionality of Liferay's workflow engine. Understanding it can help you determine how best to use it in your custom code and workflow scripts.

For example, with workflow context you can

- Access any of the existing attributes for use in workflow scripts, custom code, or Freemarker templates.
- Set new attributes at one step of a workflow process for access in workflow scripts, custom code, or Freemarker templates.
- Set new `workflowContext` attributes in the service layer of custom entities, to be accessed in workflow definition nodes.

<!-- For Rafael: are these the main ways workflow context is used? Should we add, edit, or remove anything? -->
<!-- For Russ and Rafael: probably good to add concrete examples of each use case we identify -->

There are some important things to be aware of when working with `workflowContext`:

- It must be modifiable, therefore it isn't threadsafe.
- Its first type parameter (the `key` for the attribute) is a String.
- Its second type parameter (the `value`for each attribute) is a `Serializable`, so it can be accessed at each step of the workflow.

<!-- For Rafael: the third bullet came from Olaf's community blog, which I included below). I don't understand why the serializable parameter implies that workflow context is available at each workflow step. -->

<!-- For Rafael: are there things that we want to tell customers to avoid doing with workflow context?  -->

<!-- For Rafael: are there things customers might expect they can do, but really can't? -->

To print the workflow context keys and values in any workflow node, you can add a script action like this one:

<!-- Tested on the created node of single approver -->
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

When the node is entered, output will be printed to the log:

```bash
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

--------
Everything below is from Olaf's Blog post
https://liferay.dev/blogs/-/blogs/context-is-everything

If you've ever looked at a Liferay workflow implementation and its scripts, you might have seen workflowContext being referenced in the scripts that are executed in the individual tasks and states.

I've recently had my first scripting contact with Workflow, and wanted to look at this context, and what it can do for me. Digging a bit, you’ll find out that workflowContext is a Map<String, Serializable> - interesting: Serializable hints at it being available in later steps of the workflow again, when filled in the beginning. And indeed, that's the case.

Here's a very simple example for how it can be useful:

First of all: The dynamic portion of a workflow is a mixture of Freemarker (for notifications) and Groovy (for scripts).

The default Single Approver Workflow sends mail without any subject. That's ugly, but can be changed easily: Notifications have a Description and a Template. Their description turns into the email subject - and by default it's empty. So you'll just need to fill it: Static text is fine ("Please review a workflow submission"), but you can do better with workflowContext:

Before making the email more personal, we'll have to go into scripting: Look at Single Approver's initial state, "created": It doesn't have any action, but you can add one. Let's make it extremely simple and just cater for JournalArticle (that's a Web Content Article on the UI - other types are left as an API exercise for the reader): onExit, enter this script:

```groovy
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;

long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);
AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);
String assetTitle = "none";
try {
  assetTitle = assetRenderer.getAssetObject().getTitle();
} catch ( java.lang.Exception e) {
  // ignore. Note: Above code works for JournalArticle, but
  // not every asset has a getTitle method. Those will fail, 
  // but we ignore this in the quick sample here.
}
workflowContext.put("assetTitle", assetTitle);
WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("pending"), workflowContext);
```

The magic words are the last two lines: From now on, any time in the workflow, we can reference workflowContext.get("assetTitle") in scripts, or ${assetTitle} in Freemarker-enabled fields.

Go ahead and change the description of this workflow's "Review Notification" to "please review ${assetTitle}" and provide your reviewers with more meaningful notifications.

Extend this with anything you want to store in the workflowContext. Well, not anything - don't overdo it. But it can simplify your other workflow scripts tremendously, and provide personalized and meaningful notifications to your customers.

Need some icing on the cake?

Description is a single line and turns into the subject of an E-Mail, while Template can be multi-line and will be the body of the email (e.g. insert <br/> or other HTML markup). However, if you're creating a User Notification, Template will be single line, with HTML tags escaped, and the only content shown (no Description) - it will be the title of the UI Notification. You might want to split up the current single notification into two, to cater for each of the channels individually.

