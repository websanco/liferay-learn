---
description: 12 - Catch Portal Events
title: Intercept Events with Model Listeners
order: 3
---

## Intercept Events with Model Listeners

Model Listeners listen to persistence events on the models and their associations, allowing you to create actions on them. They are available for both the core model entities as well as custom, Service Builder-generated entities.

Model listeners are OSGi components that implement the [ModelListener](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/model/ModelListener.java) interface and usually extend the [BaseModelListener](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/model/BaseModelListener.java).

Events available for the Model Listeners are:

* `onBeforeAddAssociation()	`
* `onBeforeCreate()`
* `onBeforeRemove()`
* `onBeforeRemoveAssociation()`
* `onBeforeUpdate()	`
* `onAfterAddAssociation()`
* `onAfterCreate()`
* `onAfterRemove()`
* `onAfterRemoveAssociation()`
* `onAfterUpdate()`

The association-named events are triggered on model associations with other models. For example, a user joining a group triggers `onBeforeAddAssociation()` before and `onAfterAddAssociation()` after the joining.

Model Listeners are easy and fast to implement, but there are also some restrictions. First, model listeners are called before a database transaction is complete. This means that you might end up triggering something in the model listener and the transaction could still fail. For the same reason, modifying an entity in the model listener could produce an inconsistent model state. 

Another restriction is that the order in which model listeners are invoked can't be controlled. That's why model listeners should always be independent of each other.

Although you should keep in mind their restrictions, model listeners are usually good, for example, for:

* Notifying or auditing of model persistence events
* Clearing a custom cache on entity update

Below is an example of a model listener component sending an email when any user is associated with a role:

```java
package com.liferay.training.events.model.listener;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 *
 * @author liferay
 */
@Component(
	immediate = true,
	service = ModelListener.class
)
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
		Object classPK, String associationClassName, Object associationClassPK)
		throws ModelListenerException {

		try {

			User user =
				_userService.getUserById(Long.parseLong(classPK.toString()));

			if (associationClassName.equalsIgnoreCase(
				"com.liferay.portal.kernel.model.Role")) {

				Role role = _roleService.getRole(
					Long.parseLong(associationClassPK.toString()));

				MailMessage message = new MailMessage();

				StringBundler sb = new StringBundler();
				sb.append("User ").append(user.getScreenName()).append(
					" was added to role ").append(role.getName());

				message.setSubject(sb.toString());
				message.setBody(sb.toString());

				message.setTo(getToAddress());
				message.setFrom(getFromAddress());

				_mailService.sendEmail(message);

			}
		}
		catch (PortalException e) {

			e.printStackTrace();
		}
		catch (AddressException e) {

			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
	}

	private InternetAddress getToAddress()
		throws UnsupportedEncodingException, AddressException {

		InternetAddress toAddress = new InternetAddress("something@example.com");
		toAddress.setPersonal("Somebody");

		return toAddress;
	}

	private InternetAddress getFromAddress()
		throws UnsupportedEncodingException, AddressException{

		InternetAddress fromAddress = new InternetAddress(no-reply@liferay.com);
		fromAddress.setPersonal("Liferay Portal");

		return fromAddress;

	}

	@Reference
	private MailService _mailService;

	@Reference
	private RoleService _roleService;

	@Reference
	private UserService _userService;
}
```

Generally, the steps for creating a model listener are as follows:

1. **Create** a new Liferay module using the *api* template
1. **Create** a model listener component using the Liferay Component Class wizard
1. **Create** a component extending the [BaseModelListener](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/model/BaseModelListener.java) base class
1. **Implement** the handler method(s) for the desired events

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>_______________________ are OSGi components that implement the <code>com.liferay.portal.kernel.model.ModelListener</code> interface and extend the <code>com.liferay.portal.kernel.model.BaseModelListener</code>.</li>
	<li>The association-named events are triggered on model _______________________.</li>
</ul>
</div>