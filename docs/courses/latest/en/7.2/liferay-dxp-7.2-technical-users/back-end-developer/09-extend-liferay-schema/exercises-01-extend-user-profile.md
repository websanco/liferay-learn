---
description: 9 - Extend Liferay Schema
title: Extend User Profile Using Expandos and Lifecycle Actions
order: 1
---

<h2 class="exercise">Exercises</h2>

## Extend User Profile Using Expandos and Lifecycle Actions

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Liferay Module project using the service template</li>
		<li>Implement the LifecycleAction component</li>
		<li>Deploy and test</li>
	</ul>
</div>

#### Create a Liferay Module Project

**Option 1: Use the Command Line Blade tools**

1. **Open** the _Command Line_ shell in your Liferay Workspace `modules` folder.
1. **Run** the command:
```bash
blade create -t service -p com.liferay.training.expando -s com.liferay.portal.kernel.events.LifecycleAction -c UserExpandoStartupAction expando
```
1. **Run** Gradle refresh on the IDE.

**Option 2: Use the Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__: "expando"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: service
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: "UserExpandoStartupAction"
	* __Package Name__: "com.liferay.training.expando"
1. **Click** on the _browse_ button next to the *Service Name* field.
	* Enter "\*.LifecycleAction" in the *Select Service Name*.
	* Select `com.liferay.portal.kernel.events.LifecycleAction` from the list.
1. **Click** the green plus button to add a property. 
	* Enter "key" for the *Name*.
	* Enter "login.events.post" for the *Value*.
1. **Click** *Finish* to close the wizard.

#### Implement the LifecycleAction Component

1. **Open** the class `com.liferay.training.expando.UserExpandoStartupAction`.
1. **Put** the mouse over the class name (showing an error) and select *Add unimplemented methods* from the menu.	
1. **Implement** the `processLifecycleEvent()` method as follows:
  ```java
	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		System.out.println("Expando exercise executing.");

		long companyId = CompanyThreadLocal.getCompanyId();

		// Try to get the expandoBridge for the User class and add a custom attribute.
		// By setting the secure option to false, we instruct the expandoBridge to
		// use Expando's unchecked local services instead of the permission-checked
		// remote services.

		boolean secure = false;

		try {

			ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil
					.getExpandoBridge(companyId, User.class.getName());
			expandoBridge.addAttribute("linkedin_profile_id", secure);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
  ```
1. **Resolve** missing imports.  
	- Using the `ExpandoBridgeFactoryUtil` saves us the work of dealing with the Expando local services (`ExpandoTableLocalService` and `ExpandoColumnLocalService`) directly. Using Liferay's utility classes guarantees both consistency and maintainability of your code. For an alternative implementation demonstrating the inner workings of the Expando framework, you may want to check out the `09-01-using-expandos-and-lifecycle-actions-with-local-services` branch of the solution workspace repository.

#### Add the Event Key to Component Properties

1. **Add** `"key=application.startup.events"` to the component's property configuration:

```java
@Component(
	immediate = true,
	property = {
		"key=application.startup.events"
	},
	service = LifecycleAction.class
)
```

#### Final Code Review

The complete implementation of the `UserExpandoStartupAction` component will look like this: 

```java
package com.liferay.training.expando;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;

import org.osgi.service.component.annotations.Component;

/**
 * @author berndt
 */
@Component(
	immediate = true,
	property = {
		"key=application.startup.events"
	},
	service = LifecycleAction.class
)
public class UserExpandoStartupAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		System.out.println("Expando exercise executing.");

		long companyId = CompanyThreadLocal.getCompanyId();

		// Try to get the expandoBridge for the User class and add a custom attribute.
		// By setting the secure option to false, we instruct the expandoBridge to
		// use Expando's unchecked local services instead of the permission-checked
		// remote services.

		boolean secure = false;

		try {

			ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil
					.getExpandoBridge(companyId, User.class.getName());
			expandoBridge.addAttribute("linkedin_profile_id", secure);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

#### Deploy and Test
 
1. **Deploy** the module to the Liferay server.
1. **Restart** the server.
1. **Open** your browser to http://localhost:8080 and sign in.
1. **Go to** *Control Panel → Configuration → Custom Fields*.
1. **Click** *User*.
1. **Verify** that the new *linkedin\_profile\_id* field appears in the user custom fields.

<img../images src="../images/expando.png" style="max-width: 100%;"/>

#### Takeaways

In this exercise, we demonstrated how to extend Liferay's user model using Expandos. The user model is one of the most commonly extended ones, and, as you've seen here, it's very easy to do with Expandos. Although Expandos are a great tool to extend or create databases dynamically, they are not without their limitations. If you are doing more than adding simple fields to the database, you may want to consider creating a new database table with the user ID as a foreign key instead.