---
description: 11 - Override Controller Actions
title: Override Struts Actions
order: 3
---

## Override Struts Actions

Although the [Apache Struts MVC framework](https://struts.apache.org/) is being replaced with the MVC Commands in Liferay, the platform is still using the framework for some of the native functionalities like portal login and logout. That's why there might be use cases where you'd still need to customize them. Generally, however, because the feature is deprecated, you should try to avoid customization scenarios with the Apache Struts MVC framework if you have other applicable approaches.

Let's take a very brief look at basic Struts concepts and implementation in Liferay.

#### Struts Basic Concepts

#### The Action Servlet

The action servlet is a servlet that forwards Struts requests to Struts action controllers.

The servlet mapping is defined in the `web.xml` and the path to action mapping in `struts-config.xml`.

#### The Action Controller

An action controller is a class that extends the `org.apache.struts.action.Action` and is responsible for performing actions based on the action paths like:

* */view_entry*
* */edit_entry*

#### The Action Form (Bean)

The Action Form represents the model layer. Practically, the Action Form is a data transfer object that transports model objects from the back-end to the user interface and vice versa. The action controller takes care of syncing the action form values with the persistence layer. 

The user interface action form name to Java bean mapping is defined in the `struts-config.xml` file.

#### The View Layer

The view layer of the Struts framework is implemented with the JSP. Process-wise in the back-end, the action controllers first set an *action forward* name. The name for the JSP file mapping is defined in `struts-config.xml` or, if you're using Apache Tiles, in `tiles-defs.xml`.

#### The Struts Process Flow

The following diagram roughly summarizes the Struts framework process flow when using Apache Tiles, like in Liferay:

<img src="../images/struts.png" style="max-height:50%;" />

#### Struts in Liferay 

Let's look at an example of how Struts works in Liferay. The example is an action for updating user language and is missing an ActionForward, meaning, effectively, that a user stays on the same page after the action has been processed.

#### 1 - Action URL in the JSP

We call the Struts Action from a portal JSP file:

```html
<c:if test="<%= LanguageUtil.isAvailableLocale(userLocale) %>">

	<%
	String displayPreferredLanguageURLString = themeDisplay.getPathMain() + "/portal/update_language?p_l_id=" + themeDisplay.getPlid() + "&redirect=" + URLCodec.encodeURL(currentURL) + "&languageId=" + user.getLanguageId() + "&persistState=false&showUserLocaleOptionsMessage=false";
	%>

	<aui:a cssClass="d-block" href="<%= displayPreferredLanguageURLString %>">
		<%= LanguageUtil.format(userLocale, "display-the-page-in-x", userLocale.getDisplayName(userLocale)) %>
	</aui:a>
</c:if>
```
#### 2- Action Mapping in `struts-config.xml`

The action mapping is defined in the `struts-config.xml`:

```xml
<action-mappings>
	...
	<action path="/portal/update_language" type="com.liferay.portal.action.UpdateLanguageAction" />
	...
</action-mappings>
```

#### 3 - An Action Controller

The mapped action controller implements the Struts Action interface and processes the request. It returns a `null`, meaning that we won't leave the JSP where we were. That's why there's also no need for a Tiles JSP mapping.

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

package com.liferay.portal.action;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.struts.Action;
import com.liferay.portal.struts.model.ActionForward;
import com.liferay.portal.struts.model.ActionMapping;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.admin.util.AdminUtil;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class UpdateLanguageAction implements Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String languageId = ParamUtil.getString(request, "languageId");

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		if (LanguageUtil.isAvailableLocale(
				themeDisplay.getSiteGroupId(), locale)) {

			boolean persistState = ParamUtil.getBoolean(
				request, "persistState", true);

			if (themeDisplay.isSignedIn() && persistState) {
				User user = themeDisplay.getUser();

				Contact contact = user.getContact();

				AdminUtil.updateUser(
					request, user.getUserId(), user.getScreenName(),
					user.getEmailAddress(), user.getFacebookId(),
					user.getOpenId(), languageId, user.getTimeZoneId(),
					user.getGreeting(), user.getComments(), contact.getSmsSn(),
					contact.getFacebookSn(), contact.getJabberSn(),
					contact.getSkypeSn(), contact.getTwitterSn());
			}

			HttpSession session = request.getSession();

			session.setAttribute(WebKeys.LOCALE, locale);

			LanguageUtil.updateCookie(request, response, locale);
		}

		// Send redirect

		String redirect = PortalUtil.escapeRedirect(
			ParamUtil.getString(request, "redirect"));

		String layoutURL = StringPool.BLANK;
		String queryString = StringPool.BLANK;

		int pos = redirect.indexOf(Portal.FRIENDLY_URL_SEPARATOR);

		if (pos == -1) {
			pos = redirect.indexOf(StringPool.QUESTION);
		}

		if (pos != -1) {
			layoutURL = redirect.substring(0, pos);
			queryString = redirect.substring(pos);
		}
		else {
			layoutURL = redirect;
		}

		Layout layout = themeDisplay.getLayout();

		if (isGroupFriendlyURL(layout.getGroup(), layout, layoutURL, locale)) {
			if (PropsValues.LOCALE_PREPEND_FRIENDLY_URL_STYLE == 0) {
				redirect = layoutURL;

				if (themeDisplay.isI18n()) {
					String i18nPath = themeDisplay.getI18nPath();

					redirect = redirect.substring(i18nPath.length());
				}
			}
			else {
				redirect = PortalUtil.getGroupFriendlyURL(
					layout.getLayoutSet(), themeDisplay, locale);
			}
		}
		else if (layout.isTypeControlPanel() && themeDisplay.isI18n()) {
			String i18nPath = themeDisplay.getI18nPath();

			redirect = redirect.substring(i18nPath.length());
		}
		else {
			if (PropsValues.LOCALE_PREPEND_FRIENDLY_URL_STYLE == 0) {
				redirect = PortalUtil.getLayoutURL(
					layout, themeDisplay, locale);
			}
			else {
				redirect = PortalUtil.getLayoutFriendlyURL(
					layout, themeDisplay, locale);
			}
		}

		if (Validator.isNotNull(queryString)) {
			redirect = redirect + queryString;
		}

		response.sendRedirect(redirect);

		return null;
	}

	protected boolean isGroupFriendlyURL(
		Group group, Layout layout, String layoutURL, Locale locale) {

		if (Validator.isNull(layoutURL)) {
			return true;
		}

		int pos = layoutURL.lastIndexOf(CharPool.SLASH);

		String layoutURLLanguageId = layoutURL.substring(pos + 1);

		Locale layoutURLLocale = LocaleUtil.fromLanguageId(
			layoutURLLanguageId, true, false);

		if (layoutURLLocale != null) {
			return true;
		}

		if (PortalUtil.isGroupFriendlyURL(
				layoutURL, group.getFriendlyURL(),
				layout.getFriendlyURL(locale))) {

			return true;
		}

		return false;
	}

}
```

> Sources:
> * user\_locale\_options.jsp: https://github.com/liferay/liferay-portal/blob/7.2.x/portal-web/docroot/html/common/themes/user_locale_options.jsp
> * Liferay's struts-config.xml: https://github.com/liferay/liferay-portal/blob/7.2.x/portal-web/docroot/WEB-INF/struts-config.xml
> * UpdateLanguageAction.java: https://github.com/liferay/liferay-portal/blob/7.2.x/portal-impl/src/com/liferay/portal/action/UpdateLanguageAction.java

#### Overriding Liferay Struts Actions

Overriding Struts actions in the context of Liferay means overriding action controller classes. Generally, the steps for overriding Liferay Struts actions are as follows:

1. Find the action path you are overriding (`struts-config.xml`)
1. Create a Liferay Module Project
1. Create a new Struts Action Component
1. Override and implement methods as needed

#### Example of Catching Portal Logout Struts Action

Below is an example of a component listening to a `/portal/logout` Struts action, defined in the `struts-config.xml`:

```java
package com.liferay.training.strutsactionoverride;

import com.liferay.portal.kernel.struts.StrutsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(
  immediate = true, 
  property = { 
    "path=/portal/logout",
  }, 
  service = StrutsAction.class
)
public class LogoutActionOverride implements StrutsAction {

@Override
  public String execute(HttpServletRequest request,
    HttpServletResponse response)
      throws Exception {
	  
		// Implementation...
   }
}
```

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>The action servlet forwards Struts requests to Struts _______________________.</li>
	<li>An action controller is a class that extends the *org.apache.struts.action.Action* and is responsible for performing actions based on the _______________________.</li>
	<li>The _______________________ represents the model layer.</li>
	<li>Overriding Struts _______________________ in the context of Liferay means overriding action controller classes.</li>
</ul>
</div>