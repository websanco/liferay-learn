---
description: 11 - Override Controller Actions
title: Override MVC Commands
order: 2
---

## Override MVC Commands

All Liferay platform applications are portlets. MVC Commands are portlet lifecycle handlers that implement the [MVCCommand](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCCommand.java) interface and are used to break up the controller layer into smaller and more manageable code entities. 

There are three types of MVC commands that correspond to portlet lifecycle phases:

* [MVC Action Commands](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.java)
* [MVC Render Commands](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.java)
* [MVC Resource Commands](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.java)

#### MVC Action Commands

MVC Action Commands are components that handle a portlet's *action phase* requests. They are typically used to process form submissions and trigger a service action on the model layer. Here are a few example use cases:

* Add, update, or delete an entity
* Upload a document

MVC commands are typically called from a JSP page. An MVC Action Command responds to an actionURL that has a matching `mvc.command.name` parameter, mapped to the `name` actionURL parameter in the JSP. In the example below, an MVC action command is used to handle the small image upload action in the [Liferay Blogs application](https://github.com/liferay/liferay-portal/tree/master/modules/apps/blogs):

```html
<portlet:actionURL name="/blogs/upload_small_image" var="uploadSmallImageURL" />

<div class="clearfix">
	<label class="control-label"><liferay-ui:message key="small-image" /></label>
</div>

<div class="lfr-blogs-small-image-selector">

	<%
	String smallImageSelectedItemEventName = liferayPortletResponse.getNamespace() + "smallImageSelectedItem";
	%>

	<liferay-item-selector:image-selector
		fileEntryId="<%= smallImageFileEntryId %>"
		itemSelectorEventName="<%= smallImageSelectedItemEventName %>"
		itemSelectorURL="<%= blogsItemSelectorHelper.getItemSelectorURL(requestBackedPortletURLFactory, themeDisplay, smallImageSelectedItemEventName) %>"
		maxFileSize="<%= PropsValues.BLOGS_IMAGE_MAX_SIZE %>"
		paramName="smallImageFileEntry"
		uploadURL="<%= uploadSmallImageURL %>"
		validExtensions='<%= StringUtil.merge(imageExtensions, ", ") %>'
	/>
</div>
```

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

package com.liferay.blogs.web.internal.portlet.action;

import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.web.internal.upload.ImageBlogsUploadResponseHandler;
import com.liferay.blogs.web.internal.upload.TempImageBlogsUploadFileEntryHandler;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.upload.UploadHandler;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
		"mvc.command.name=/blogs/upload_small_image"
	},
	service = MVCActionCommand.class
)
public class UploadSmallImageMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		_uploadHandler.upload(
			_tempImageBlogsUploadFileEntryHandler,
			_imageBlogsUploadResponseHandler, actionRequest, actionResponse);
	}

	@Reference
	private ImageBlogsUploadResponseHandler _imageBlogsUploadResponseHandler;

	@Reference
	private TempImageBlogsUploadFileEntryHandler
		_tempImageBlogsUploadFileEntryHandler;

	@Reference
	private UploadHandler _uploadHandler;

}
```

> Sources:
> * edit_entry.jsp: https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/edit_entry.jsp
> * UploadSmallImageMVCActionCommand.java: https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/portlet/action/UploadSmallImageMVCActionCommand.java

#### MVC Render Commands

MVC render commands are components that handle a portlet's *render phase* requests.

When using JSPs in the front-end, the render() method returns the path to the JSP file. A few example use cases:

* Get a list of assets to show in the user interface
* Fetch an asset entry to show in the user interface

Below is an example of an MVC Render Command in the Blogs application that handles the showing of a single Blogs entry:

```html
...

PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("mvcRenderCommandName", "/blogs/view");

...
```

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

package com.liferay.blogs.web.internal.portlet.action;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.exception.NoSuchEntryException;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_AGGREGATOR,
		"mvc.command.name=/blogs/view_entry"
	},
	service = MVCRenderCommand.class
)
public class ViewEntryMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		long assetCategoryId = ParamUtil.getLong(renderRequest, "categoryId");
		String assetCategoryName = ParamUtil.getString(renderRequest, "tag");

		if ((assetCategoryId > 0) || Validator.isNotNull(assetCategoryName)) {
			return "/blogs/view.jsp";
		}

		try {
			boolean redirectToLastFriendlyURL = ParamUtil.getBoolean(
				renderRequest, "redirectToLastFriendlyURL", true);

			BlogsEntry entry = ActionUtil.getEntry(renderRequest);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String assetDisplayPageFriendlyURL =
				_assetDisplayPageFriendlyURLProvider.getFriendlyURL(
					BlogsEntry.class.getName(), entry.getEntryId(),
					themeDisplay);

			if (assetDisplayPageFriendlyURL != null) {
				HttpServletResponse response = _portal.getHttpServletResponse(
					renderResponse);

				response.sendRedirect(assetDisplayPageFriendlyURL);

				return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
			}

			FriendlyURLEntry mainFriendlyURLEntry =
				_friendlyURLEntryLocalService.getMainFriendlyURLEntry(
					BlogsEntry.class, entry.getEntryId());

			String urlTitle = ParamUtil.getString(renderRequest, "urlTitle");

			if (redirectToLastFriendlyURL && Validator.isNotNull(urlTitle) &&
				!urlTitle.equals(mainFriendlyURLEntry.getUrlTitle())) {

				PortletURL portletURL = renderResponse.createRenderURL();

				portletURL.setParameter(
					"mvcRenderCommandName", "/blogs/view_entry");
				portletURL.setParameter(
					"urlTitle", mainFriendlyURLEntry.getUrlTitle());

				HttpServletResponse response = _portal.getHttpServletResponse(
					renderResponse);

				response.sendRedirect(portletURL.toString());

				return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
			}

			HttpServletRequest request = _portal.getHttpServletRequest(
				renderRequest);

			request.setAttribute(WebKeys.BLOGS_ENTRY, entry);

			if (PropsValues.BLOGS_PINGBACK_ENABLED) {
				if ((entry != null) && entry.isAllowPingbacks()) {
					HttpServletResponse response =
						_portal.getHttpServletResponse(renderResponse);

					response.addHeader(
						"X-Pingback",
						_portal.getPortalURL(renderRequest) +
							"/xmlrpc/pingback");
				}
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return "/blogs/error.jsp";
			}

			throw new PortletException(e);
		}

		return "/blogs/view_entry.jsp";
	}

	@Reference
	private AssetDisplayPageFriendlyURLProvider
		_assetDisplayPageFriendlyURLProvider;

	@Reference
	private FriendlyURLEntryLocalService _friendlyURLEntryLocalService;

	@Reference
	private Portal _portal;

}
```

> Sources: 
> * view.jsp: https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/view.jsp
> * ViewEntryMVCRenderCommand.java: https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/portlet/action/ViewEntryMVCRenderCommand.java

#### MVC Resource Commands

MVC resource commands are components that handle a portlet's *resource serving phase* requests.

The resource service phase doesn't trigger the render phase and page refresh. That's why resource commands are useful, for example, for:

* Autocompletion
* Fetching an item into the user interface with an AJAX call
* Captcha checking
* Updating a list without page refresh

The example below is from the [Liferay Microblogs application](https://github.com/liferay/liferay-portal/tree/master/modules/apps/microblogs). In the JSP file, there's an AJAX call doing the auto completion for user mentions. An MVC Resource Command handles the call by the `mvc.command.name` parameter, mapped to the `id` parameter in the front-end resourceURL:

```html
<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/microblogs/autocomplete_user_mentions" var="userIdURL">
	<portlet:param name="userId" value="<%= String.valueOf(user.getUserId()) %>" />
</liferay-portlet:resourceURL>

var createAutocomplete = function(contentTextarea) {
	fetch(
		'<%= HtmlUtil.escapeJS(userIdURL.toString()) %>',
		{
			credentials: 'include'
		}
	).then(
		function(response) {
			return response.json();
		}
	).then(
		function(response) {
			autocompleteDiv = new A.AutoComplete(
				{
					inputNode: contentTextarea,
					maxResults: 5,
						on: {
							clear: function() {
							var highlighterContent = A.one('#<portlet:namespace />highlighterContent<%= formId %>');

							highlighterContent.html('');
						},
						query: updateHighlightDivContent,
						select: updateContentTextbox
						},
					resultFilters: 'phraseMatch',
					resultFormatter: resultFormatter,
					resultTextLocator: 'fullName',
					source: response
				}
			).render();
		}
	);
};

```

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

package com.liferay.microblogs.web.internal.portlet.action;

import com.liferay.microblogs.constants.MicroblogsPortletKeys;
import com.liferay.microblogs.web.internal.util.MicroblogsWebUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author István András Dézsi
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + MicroblogsPortletKeys.MICROBLOGS,
		"mvc.command.name=/microblogs/autocomplete_user_mentions"
	},
	service = MVCResourceCommand.class
)
public class AutocompleteUserMentionsMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	public void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			HttpServletRequest request = _portal.getOriginalServletRequest(
				_portal.getHttpServletRequest(resourceRequest));

			long userId = ParamUtil.getLong(request, "userId");

			ThemeDisplay themeDisplay =
				(ThemeDisplay)resourceRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			JSONArray jsonArray = MicroblogsWebUtil.getJSONRecipients(
				userId, themeDisplay);

			HttpServletResponse response = _portal.getHttpServletResponse(
				resourceResponse);

			response.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(response, jsonArray.toString());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AutocompleteUserMentionsMVCResourceCommand.class);

	@Reference
	private Portal _portal;

}
```

> Sources: 
> * edit\_microblogs\_entry.jsp: https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/microblogs/microblogs-web/src/main/resources/META-INF/resources/microblogs/edit_microblogs_entry.jsp
> * AutocompleteUserMentionsMVCResourceCommand.java: https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/microblogs/microblogs-web/src/main/java/com/liferay/microblogs/web/internal/portlet/action/AutocompleteUserMentionsMVCResourceCommand.java

#### Overriding MVC Commands

The same pattern applies to overriding any portal MVC Commands. Generally, the steps for overriding MVC Commands are as follows:

1. Locate the target portlet bundle and its bundle name.
1. Find the MVC Command class to override.
1. Locate the `mvc.command.name` parameter.
1. Implement an overriding OSGi MVC Command component with a higher service ranking.

As an example, we'll demonstrate overriding an MVC Action Command.

<br />

#### Overriding the Blogs Admin Portlet Edit Entry Action Command

In the example below, we will customize the MVC Action Command responsible for editing a Blogs entry. In the customization, we'll create a notification when somebody is trying to delete an entry from the *Control Panel*. After the notification, the original action is executed. 

#### Step 1 - Find out the Blogs Web Bundle Name

Use the Gogo Shell to find the web bundle for blogs:

```bash
g! lb | grep "Blogs" 
  359|Active     |   10|Liferay CE Collaboration - Liferay CE Blogs - API (2.0.0)|2.0.0
  360|Active     |   10|Liferay Blogs API (5.0.0)|5.0.0
  361|Active     |   10|Liferay Blogs Item Selector API (3.0.0)|3.0.0
  362|Active     |   10|Liferay Blogs Recent Bloggers API (3.0.0)|3.0.0
  369|Active     |   10|Liferay Adaptive Media Blogs Editor Configuration (3.0.0)|3.0.0
  370|Active     |   10|Liferay Adaptive Media Blogs Item Selector Web (3.0.0)|3.0.0
  371|Active     |   10|Liferay Adaptive Media Blogs Web (3.0.0)|3.0.0
  372|Resolved   |   10|Liferay Adaptive Media Blogs Web Fragment (3.0.0)|3.0.0
  560|Active     |   10|Liferay CE Collaboration - Liferay CE Blogs - Impl (2.0.0)|2.0.0
  561|Active     |   10|Liferay Blogs Editor Configuration (3.0.0)|3.0.0
  562|Active     |   10|Liferay Blogs Item Selector Web (4.0.0)|4.0.0
  563|Active     |   10|Liferay Blogs Layout Prototype (4.0.0)|4.0.0
  564|Active     |   10|Liferay Blogs Reading Time (2.0.0)|2.0.0
  565|Active     |   10|Liferay Blogs Recent Bloggers Web (4.0.0)|4.0.0
  566|Active     |   10|Liferay Blogs Service (3.0.0)|3.0.0
  567|Active     |   10|Liferay Blogs UAD (4.0.0)|4.0.0
  568|Active     |   10|Liferay Blogs Web (4.0.0)|4.0.0
true
g! 
```

#### Step 2 - Find the MVC Command Class to Override

<img src="../images/blogs-example-2.png" style="max-height:32%;" />

#### Step 3 - Locate the `mvc.command.name` Parameter to Override

```java
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_AGGREGATOR,
		"mvc.command.name=/blogs/edit_entry"
	},
	service = MVCActionCommand.class
)
public class EditEntryMVCActionCommand extends BaseMVCActionCommand {
	...
```

#### Step 4 - Implement an Overriding OSGi MVC Command Component with a Higher Service Ranking

```java

package com.liferay.training.mvc.command.override;

import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate=true,
    property = { 
        "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN, 
        "mvc.command.name=/blogs/edit_entry",
        "service.ranking:Integer=100" 
    }, 
    service = MVCActionCommand.class
)
public class CustomEditEntryMVCActionCommand extends BaseMVCActionCommand {


	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		System.out.println("Module activated.");
	}
	
    @Override
    protected void doProcessAction
        (ActionRequest actionRequest, ActionResponse actionResponse) 
        throws Exception {

        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        if (cmd.equals(Constants.DELETE)) {
            _log.info("Deleting a Blogs entry.");
        }

        mvcActionCommand.processAction(actionRequest, actionResponse);
    }

    @Reference(
        target = "(component.name=com.liferay.blogs.web.internal.portlet.action.EditEntryMVCActionCommand)")
    protected MVCActionCommand mvcActionCommand;
    
	private static final Log _log = LogFactoryUtil.getLog(
	CustomEditEntryMVCActionCommand.class);
}
```

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>MVC _______________ commands are OSGi components that handle a portlet's <b>action phase</b> requests.</li>
	<li>MVC _______________ commands are OSGi components that handle a portlet's <b>render phase</b> requests.</li>
	<li>MVC _______________ commands are OSGi components that handle a portlet's <b>resource serving phase</b> requests.</li>
</ul>
</div>