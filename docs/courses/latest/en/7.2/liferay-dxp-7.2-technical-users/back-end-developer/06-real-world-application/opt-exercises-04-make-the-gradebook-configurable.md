---
description: 6 - Real World Application
title: Make the Gradebook Configurable
order: 4
---

<h2 class="exercise">Optional Exercise</h2>

## Make the Gradebook Configurable

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Declare dependencies in the <code>build.gradle</code></li>
		<li>Create a configuration interface in the API module</li>
		<li>Add the configuration interface package to the exported packages in the <code>bnd.bnd</code></li>
		<li>Add localization resources for the configuration</li>
		<li>Test the configuration user interface</li>
		<li>Implement configuration support to the Assignment validator service</li>
		<li>Show messages in the user interface</li>
		<li>Add new error message localization resources</li>
		<li>Test the Application</li>
	</ul>
</div>

#### Declare Dependencies

We need to add dependencies for the BND annotations and Metatype API:

1. **Open** the `build.gradle` in the *gradebook-api* module.
1. **Add** the following dependencies:
```groovy
compileOnly group: "biz.aQute.bnd", name: "biz.aQute.bnd.annotation", version: "3.1.0"
compileOnly group: "com.liferay", name: "com.liferay.portal.configuration.metatype.api"
```

#### Create the Configuration Interface in the API Module

1. **Create** a new interface `com.liferay.training.gradebook.configuration.GradebookSystemServiceConfiguration` in the *gradebook-api* module.
1. **Implement** the interface as follows:

```java
package com.liferay.training.gradebook.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
	* Configuration interface for Gradebook service. 
	* 
	* An user interface for this interface is automatically created 
	* in Control Panel -> System settings.
	* 
	* @see <a
	*      href="https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-2/making-applications-configurable">Tutorial
	*      on making configurable applications at Liferay Developer Network</a>
	* @author liferay
	*/
@ExtendedObjectClassDefinition(
	category = "Gradebook", 
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.training.gradebook.configuration.GradebookSystemServiceConfiguration",
	localization = "content/Language",
	name = "gradebook-service-configuration-name"
)
public interface GradebookSystemServiceConfiguration {

	@Meta.AD(
		deflt = "10", 
		description = "description-min-length-description", 
		name = "description-min-length-name", 
		required = false
	)
	public int descriptionMinLength();

	@Meta.AD(
		deflt = "200", 
		description = "description-max-length-description", 
		name = "description-max-length-name", 
		required = false
	)
	public int descriptionMaxLength();
}
```

> The naming syntax of the interface is by convention \[Application\]\[Scope\]\[Layer\]Configuration.

> Note that the configuration ID __has to be__ the fully qualified name of the interface.

#### Add Exported Package

To be able to consume the configuration from other bundles, we have to expose it by exporting its package:

1. **Open** the `bnd.bnd` file of the *gradebook-api* module.
1. **Implement** the export as follows (highlighted code):
```groovy
Bundle-Name: gradebook-api
Bundle-SymbolicName: com.liferay.training.gradebook.api
Bundle-Version: 1.0.0
Export-Package:\
	com.liferay.training.gradebook.configuration,\
	com.liferay.training.gradebook.constants,\
	com.liferay.training.gradebook.exception,\
	com.liferay.training.gradebook.model,\
	com.liferay.training.gradebook.service,\
	com.liferay.training.gradebook.service.persistence
-check: EXPORTS
-includeresource: META-INF/service.xml=../gradebook-service/service.xml
```
	
#### Add the Localization Resources

Notice the resource bundle property in our configuration interface:
```java
@Meta.OCD(
	id = "com.liferay.training.gradebook.configuration.GradebookSystemServiceConfiguration",
	localization = "content/Language",
	name = "gradebook-service-configuration-name"
)
```

Let's add the referenced resources to localize the user interface:
1. **Create** a file `src/main/resources/content/Language.properties` in the *gradebook-api* module
1. **Implement** the file as follows:
```properties
gradebook-service-configuration-name=Gradebook Service Configuration
description-max-length-description=Maximum length of a description (in characters).
description-max-length-name=Description Maximum Length
description-min-length-description=Minimum length of a description (in characters).
description-min-length-name=Description Minimum Length
```		

#### Test the Configuration Interface

Creating a configuration interface automatically generates an user interface.

1. **Open** the browser and go to the *Control Panel → Configuration → System Settings*. 
	> You should see the Gradebook configuration in the *Other* category. You can also search for *Gradebook*.
1. **Click** the configuration icon.

<img src="../images/gradebook-configuration.png" style="max-height: 100%"/>

#### Declare Dependencies

We need to add a dependency to the Metatype API:

1. **Open** the `build.gradle` in the *gradebook-service* module.
1. **Add** the following dependency:
```groovy
compileOnly group: "com.liferay", name: "com.liferay.portal.configuration.metatype.api"
```

#### Implement Configuration Support to the Assignment Validator Service

1. **Open** the validator service component `com.liferay.training.gradebook.util.validator.AssignmentValidatorImpl` in the *gradebook-service* module.
1. **Add** the `configurationPid` component property as follows:
	```java
	@Component(
		configurationPid = "com.liferay.training.gradebook.configuration.GradebookSystemServiceConfiguration", 
		immediate = true, 
		service = AssignmentValidator.class
	)
	```
1. **Add** a volatile variable to the end of the class for holding the configuration:
	```java
	private volatile GradebookSystemServiceConfiguration _moduleConfiguration;
	```
1. **Add** a component activation  method to instantiate the configuration:
	```java
	@Activate
	@Modified
	private void activate(Map<String, Object> properties) {

		_moduleConfiguration = ConfigurableUtil.createConfigurable(
			GradebookSystemServiceConfiguration.class, properties);
	}	 
	```
1. **Replace** the contents of `isDescriptionValid()` method with the following (notice the highlighted code):
	```java
	private boolean isDescriptionValid(
		final Map<Locale, String> descriptionMap, final List<String> errors) {

		boolean result = true;

		// Verify the map has something

		if (MapUtil.isEmpty(descriptionMap)) {
			errors.add("assignmentDescriptionEmpty");
			result = false;
		}
		else {

			// Get the default locale

			Locale defaultLocale = LocaleUtil.getSiteDefault();

			String descriptionHTML = descriptionMap.get(defaultLocale);
			
			if ((Validator.isBlank(descriptionHTML))) {
				errors.add("assignmentDescriptionEmpty");
				result = false;
			}

			// Strip HTML tags from text.

			String descriptionText = HtmlUtil.stripHtml(descriptionHTML);

			if (Validator.isBlank(descriptionText)) {
				errors.add("assignmentDescriptionEmpty");
				result = false;
			}

			if (descriptionText.length() < _moduleConfiguration.descriptionMinLength()) {
				errors.add("assignmentDescriptionTooShort");
				result = false;
			}

			else if (descriptionText.length() > _moduleConfiguration.descriptionMaxLength()) {
				errors.add("assignmentDescriptionTooLong");
				result = false;
			}
		}

		return result;
	}
	```	
1. **Organize** missing imports.	

<br /><br />

#### Show Messages in the User Interface

1. **Open** the file `src/main/resources/META-INF/resources/assigment/edit_assignment.jsp` in *gradebook-web* module.
1. **Add** the following messages after you find the error message tags in the beginning of the file: 
```html
<liferay-ui:error key="assignmentDescriptionTooShort" message="error.description-too-short" />
<liferay-ui:error key="assignmentDescriptionTooLong" message="error.description-too-long" />	
```	

#### Add Error Message Localizations

1. **Open** the localization file `src/main/resources/content/Language.properties` in the *gradebook-web* module
1. **Add** localizations for the new error messages:
```properties
error.description-too-short=Description text too short.
error.description-too-long=Description text too long.
```

#### Test the Application

1. **Test** creating new Assignments with either too short or too long descriptions, after refreshing.

<img src="../images/configured-validation-error.png" style="max-height: 100%"/>