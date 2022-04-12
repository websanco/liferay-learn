## Optional Exercise: Make the Gradebook Configurable

<div class="ahead">

#### Exercise Goals

- Declare dependencies in build.gradle
- Create a configuration interface in the API module
- Add the configuration interface package to the exported packages in the bnd.bnd
- Add localization resources for the configuration
- Test the configuration user interface
- Implement configuration support to the Assignment validator service
- Show messages in the user interface
- Add new error message localization resources
- Test the Application

<div class="note">
Note: This exercise is optional. It is not written as step-by-step exercises so that you can explore and experiment more.
</div>

</div>
	
#### Declare Dependencies for the API Module

You will need to open the build.gradle of your gradebook-api module to add the "biz.aQuote.bnd.annotation" and "com.liferay.portal.configuration.metatype.api" dependencies.

#### Create the Configuration Interface in the API Module

Add an interface to the gradebook-api module: "com.liferay.training.gradebook.conifiguration.GradebookSystemServiceConfiguration".

<div class="note">
Note: This naming syntax is by convention [Application][Scope][Layer]Configuration. Remember that the configuration ID must be the fully qualified name of the interface.
</div>

The code implementation of this file should look something like this:

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

#### Add Exported Package

To be able to consume the configuration from other bundles, we have to expose it by exporting its package. Open the bnd.bnd file of the gradebook-api module and add the configuration package - `com.liferay.training.gradebook.configuration` - to the exported-packages.

#### Add the Localization Resources

Notice the resource bundle property in our configuration interface:

```java
@Meta.OCD(
	id = "com.liferay.training.gradebook.configuration.GradebookSystemServiceConfiguration",
	localization = "content/Language",
	name = "gradebook-service-configuration-name"
)
```

Let's add the referenced resources to localize the user interface by creating the `Language.properties` file in the gradebook-api module. The implementation should look similar to:

```properties
gradebook-service-configuration-name=Gradebook Service Configuration
title-max-length-description=Maximum length of a title (in characters).
title-max-length-name=Title Maximum Length
title-min-length-description=Minimum length of a title (in characters).
title-min-length-name=Title Minimum Length
```

#### Test the Configuration Interface

Creating a configuration interface automatically generates an user interface. Open a browser at localhost:8080 and go to System Settings in the UI. Click the configuration icon and take a look.

#### Declare Dependencies for the Service Module

We need to add a dependency to the Metatype API. Open build.gradle in the gradebook-service module and add the `com.liferay.portal.configuration.metatype.api` dependency.

#### Implement Configuration Support to the Assignment Validator Service

Open the validator service component `com.liferay.training.gradebook.util.validator.AssignmentValidatorImpl` in the *gradebook-service* module and add the `configurationPid` component property as shown below.

```java
@Component(
	configurationPid = "com.liferay.training.gradebook.configuration.GradebookSystemServiceConfiguration", 
	immediate = true, 
	service = AssignmentValidator.class
)
```

Add a volatile variable to the end of the class for holding the configuration.

```java
private volatile GradebookSystemServiceConfiguration _moduleConfiguration;
```

Add a component activation method to instantiate the configuration similar to the code below.

```java
@Activate
@Modified
private void activate(Map<String, Object> properties) {

	_moduleConfiguration = ConfigurableUtil.createConfigurable(
		GradebookSystemServiceConfiguration.class, properties);
}	 
```

Replace the contents of the `isDescriptionValid()` method with the code below (notice the highlighted code) and organize the missing imports.

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

#### Show Messages in the User Interface

Open the file `src/main/resources/META-INF/resources/assigment/edit_assignment.jsp` in *gradebook-web* module. Add the following messages after you find the error message tags in the beginning of the file:

```html
<liferay-ui:error key="assignmentTitleTooShort" message="error.title-too-short" />
<liferay-ui:error key="assignmentTitleTooLong" message="error.title-too-long" />	
```	

#### Add Error Message Localizations

Open the localization file `src/main/resources/content/Language.properties` in the *gradebook-web* module and add localizations for the new error messages.

```properties
error.title-too-short=Title text too short.
error.title-too-long=Title text too long.
```

#### Test the Application

Test the Gradebook application in your browser by creating new Assignments with either too short or too long descriptions. Make sure you've refreshed your browser before you start testing.
