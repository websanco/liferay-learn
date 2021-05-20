# Categorizing a Configuration

When you register a configuration interface, the UI for your app is generated in *System Settings* &rarr; *Platform* &rarr; *Third Party*. This default categorization can easily be changed.

## Specifying an Existing Category

Here are the available system settings sections. Categories are nested beneath these sections:

* Commerce
* Platform
* Security
* Content and Data
* Other

Specify the category for your UI by placing an `@ExtendedObjectClassDefinition` annotation in your configuration interface. For example, if you wanted to place the UI under blogs, add the following line above your `@Meta.OCD` annotation:

```java
@ExtendedObjectClassDefinition(category = "blogs")
```

Import the `@ExtendedObjectClassDefinition` class with:

```java
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition
```

Redeploy your application and the configuration UI will not be located under *Content and Data* &rarr; *Blogs*.

## Creating New Sections and Categories

If you prefer your own section and category, you can create them by implementing the `ConfigurationCategory` interface.

If you followed the example project in [Setting and Accessing Configurations](./setting-and-accessing-configurations), this code can be added to the `configuration` folder of the application. This is the same folder that holds the configuration interface Java file. The code below creates a foobar section and a foobar category:

```java
package com.acme.e3q3.web.internal.configuration;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationCategory.class)
public class E3Q3WebConfigurationCategory implements ConfigurationCategory {

	@Override
	public String getCategoryIcon() {
		return _CATEGORY_ICON;
	}

	@Override
	public String getCategoryKey() {
		return _CATEGORY_KEY;
	}

	@Override
	public String getCategorySection() {
		return _CATEGORY_SECTION;
	}

	private static final String _CATEGORY_ICON = "foobar";

	private static final String _CATEGORY_KEY = "foobar";

	private static final String _CATEGORY_SECTION = "foobar";

}
```

The language keys for the custom section and category must also be specified in the localization file. For the example above, add the following keys to the `Language.properties` file in the portlet's `src/main/resources/content/` folder.

```properties
category.foobar=Foobar
category-section.foobar=Foobar
```

Redeploy the application to see the new settings section and category.

![The configuration UI is now in the custom category](./categorizing-a-configuration/images/01.png)

The application's configuration UI is now located in the custom section and custom category.