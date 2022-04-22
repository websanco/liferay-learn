---
description: 06 - Real World Application
title: Make the Application Configurable
order: 7
---

## Introducing Liferay's Configuration API

Liferay's configuration API is a configuration management framework for both the Liferay platform and applications on the platform. The configuration API is based on the [OSGi Configuration Admin service](https://osgi.org/specification/osgi.cmpn/7.0.0/service.cm.html), which allows you to dynamically set and manage configuration data for OSGi bundles and components. The configuration API is part of the OSGi Compendium specification.

While standard portlet preferences can still be used in portlet applications, the configuration API is more flexible and feature-rich, superseding standard preferences as the preferred portlet application configuration framework.

As with portlet preferences, data in the configuration API is stored in key-value pairs. With the configuration API, however, the values have strong Java typing. Also, similar to portlet preferences on the Liferay platform before, the configuration can be scoped. The available scopes are: 

* __System:__ unique for the complete system
* __Virtual Instance:__ instance-wide
* __Site:__ configuration can vary per site
* __Portlet Instance:__ single application (portlet) instance

When an application configuration is implemented using the configuration API, a management user interface is generated automatically. The generated management user interface can be accessed in *Control Panel → Configuration → System Settings -> CONFIGURATION_NAME*:

<img src="../images/system-settings.png" style="max-height:25%"/>

<img src="../images/configuration-captcha.png" style="max-height:25%"/>

Configuration data can be exported and imported. This is useful, for example, in transporting or replicating settings between environments. Importing configuration data can be done by copying the exported configuration file to the `LIFERAY_HOME/osgi/configs` folder.

On a high level, the minimum steps required for making a Liferay application configurable with the configuration API are as follows:

* If necessary, add required dependencies to the `build.gradle`.
* Create the configuration interface.
* If using other than system scope, create a Configuration Provider
* Make the configuration data available in an OSGi component.

> As the Liferay configuration API is relying on the standard OSGi configuration Admin service, the component runtime configuration data can be read with standard OSGi management tools like the Gogo Shell and Felix Web Console.

#### Example: Creating a System-Wide Portlet Configuration

#### Step 1 - Add Metatype API Dependency

```groovy
compileOnly group: "com.liferay", name: "com.liferay.portal.configuration.metatype.api"
```

#### Step 2 - Create the Configuration Interface

Creating the configuration interface automatically creates the configuration user interface in *Control Panel → System Settings*.  The configuration `id` property has to match the interface fully qualified name.

```java
package com.liferay.training.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(
	id = "com.liferay.training.configuration.MóduleConfiguration",
	localization = "content/Language",
	name = "configuration-api-example-portlet",
)
public interface ModuleConfiguration {

	@Meta.AD(
		deflt = "false", 
		description = "show-hello-description",
	    name = "show-hello-name",
		required = false
	)
	public boolean showHello();	

}
```

#### Step 3 - Make the Configuration Data Available in an OSGi Component

Reference the configuration by its id using the component property `configurationPid`. The configuration variable has to be `volatile`. After that, the configuration can be consumed, for example, by putting values into the request:

```java
@Component(
	configurationPid = "com.liferay.training.configuration.MóduleConfiguration", 
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Make-application-configurable Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ConfigurationExamplePortletKeys.CONFIGURATION_EXAMPLE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ConfigurationExamplePortlet extends MVCPortlet {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {

		_moduleConfiguration = ConfigurableUtil.createConfigurable(
			ModuleConfiguration.class, properties);
	}

	@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
	
		renderRequest.setAttribute("showHello", _moduleConfiguration.showHello());
	
		super.render(renderRequest, renderResponse);
	}	

	private volatile ModuleConfiguration _moduleConfiguration;
}
```

> A note on the annotations: `@Meta.OCD` and `@Meta.AD` are part of the bnd library, while `@ObjectClassDefinition` and `@AttributeDefinition` are OSGi core equivalents. Both can be used, but only bnd annotations are available at runtime.

#### Further Reading

* Configurable Applications: https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/configurable-applications

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>Liferay's _____________ is a _____________  for both the Liferay platform and applications.</li>
	<li>The_____________  is based on OSGi _____________.</li>
	<li>Configuration data is stored in _____________  and can be _____________.</li>
</ul>
</div>