---
description: 10 - Customize the Service Layer
title: Customize Liferay UserLocalService with a Service Wrapper
order: 1
---

<h2 class="exercise">Exercises</h2>

## Customize the Service Layer

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Liferay Module project using the service-wrapper template.</li>
		<li>Override the addUserWithWorkflow() method.</li>
		<li>Do a final code review.</li>
		<li>Deploy and test.</li>
	</ul>
</div>

#### Create a Liferay Module Project

**Option 1: Use the Command Line Blade Tools**

1. **Open** the _Command Line_ shell in your Liferay Workspace `modules` folder.
1. **Run** the command:
```bash
blade create -t service-wrapper -p com.liferay.training.user.service -c UserLocalServiceOverride -s com.liferay.portal.kernel.service.UserLocalServiceWrapper user-local-service-override
```
1. **Run** Gradle refresh on the IDE.

**Option 2: Use Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__:  "user-local-service-override"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__:  service-wrapper
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: "UserLocalServiceOverride"
	* __Package Name__: "com.liferay.training.user.service"
1. **Click** on the browse button next to the *Service Name* field.
	* Enter "\*.UserLocalServiceWrapper" in the *Select Service Name*.
	* Choose `com.liferay.portal.kernel.service.UserLocalServiceWrapper`.
1. **Click** *Finish* to close the wizard.

#### Override the `addUserWithWorkflow()` Method

1. **Open** the class `com.liferay.training.user.service.UserLocalServiceOverride`.
1. **Right-click** on the `UserLocalServiceOverride()` constructor.
1. **Choose** the *Source → Override/Implement Methods* option.
1. **Select** the `addUserWithWorkflow()` method.
1. **Implement** the method as follows:
```java
@Override
public User addUserWithWorkflow(
	long creatorUserId, long companyId, boolean autoPassword,
	String password1, String password2, boolean autoScreenName,
	String screenName, String emailAddress, long facebookId, String openId,
	Locale locale, String firstName, String middleName, String lastName,
	long prefixId, long suffixId, boolean male, int birthdayMonth,
	int birthdayDay, int birthdayYear, String jobTitle, long[] groupIds,
	long[] organizationIds, long[] roleIds, long[] userGroupIds,
	boolean sendEmail, ServiceContext serviceContext)
		throws PortalException {

	if (emailAddress.contains("@gmail.com") ||
			  emailAddress.contains("@yahoo.com") ||
			  emailAddress.contains("@aol.com") ||
			  emailAddress.contains("@hotmail.com")) {

		System.out.println(
			"You must enter a work email address. User will not be added.");

		throw new PortalException("You must enter a work email address.");
	}

	return super.addUserWithWorkflow(creatorUserId,companyId,autoPassword,
			password1,password2,autoScreenName,screenName,emailAddress,
			facebookId,openId,locale,firstName,middleName,lastName,prefixId,
			suffixId,male,birthdayMonth,birthdayDay,birthdayYear,jobTitle,
			groupIds,organizationIds,roleIds,userGroupIds,sendEmail,serviceContext);
}
```

> Note the personal email domains that will be rejected as the user is added.

#### Final Code Review

The complete implementation of the class will look like this: 

```java
package com.liferay.training.user.service;

import com.liferay.portal.kernel.service.UserLocalServiceWrapper;

import java.util.Locale;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author liferay
 */
@Component(
	immediate = true, 
	property = {
	}, 
	service = ServiceWrapper.class
)
public class UserLocalServiceOverride extends UserLocalServiceWrapper {

	public UserLocalServiceOverride() {
		super(null);
	}

	@Override
	  public User addUserWithWorkflow(
	    long creatorUserId, long companyId, boolean autoPassword,
	    String password1, String password2, boolean autoScreenName,
	    String screenName, String emailAddress, long facebookId, String openId,
	    Locale locale, String firstName, String middleName, String lastName,
	    long prefixId, long suffixId, boolean male, int birthdayMonth,
	    int birthdayDay, int birthdayYear, String jobTitle, long[] groupIds,
	    long[] organizationIds, long[] roleIds, long[] userGroupIds,
	    boolean sendEmail, ServiceContext serviceContext)
	      throws PortalException {

		if (emailAddress.contains("@gmail.com") ||
				  emailAddress.contains("@yahoo.com") ||
				  emailAddress.contains("@aol.com") ||
				  emailAddress.contains("@hotmail.com")) {


			System.out.println(
				"You must enter a work email address. User will not be added.");

			throw new PortalException("You must enter a work email address.");
		}

		return super.addUserWithWorkflow(creatorUserId,companyId,autoPassword,
				password1,password2,autoScreenName,screenName,emailAddress,
				facebookId,openId,locale,firstName,middleName,lastName,prefixId,
				suffixId,male,birthdayMonth,birthdayDay,birthdayYear,jobTitle,
				groupIds,organizationIds,roleIds,userGroupIds,sendEmail,serviceContext);
	}
}
```

#### Deploy and Test

1. **Deploy** the module to the Liferay server.
1. **Open** your browser to http://localhost:8080 and sign in.
1. **Open** *Control Panel → Users → Users and Organizations*.
1. **Click** the plus button on the top right to add a user.
1. **Try** to create a user with one of the rejected email domains in the `addUserWithWorkflow()` method.

You will see an error message on the page stating that the user was not added. You'll also see the ouput from the `System.out.println()` statement in the console view:

```bash
[BundleStartStopLogger:35] STARTED com.liferay.training.user.service_1.0.0 [963]
[2019-03-21T08:34:32,999][INFO ][o.e.m.j.JvmGcMonitorService] [Ymb-PoP] [gc][1326] overhead, spent [1.5s] collecting in the last [1.7s]
You must enter a work email address. User will not be added.
```

#### Takeaways

Using the pattern above, you will be able to override any Liferay Service with a service wrapper.
