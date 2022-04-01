---
description:  3 - Liferay's OSGi Container
title: Liferay's OSGi Container
order: 2
---

## Liferay's OSGi Container

So far, we have learned that OSGi applications are usually made up of multiple modules that share features with each other. The modules, called *bundles*, need a runtime environment called an *OSGi container*.

The Liferay platform is built on OSGi technology: Liferay applications are OSGi applications, and the platform has an embedded OSGi container. Liferay's OSGi container implementation is standard-compliant, which means that you can run any standard OSGi bundle inside it.

The diagram below illustrates the structure of an OSGi container running standalone in a standard JVM:

<img src="../images/osgi-framework-and-bundle.png" style="max-height:15%;" />

The diagram below shows how the OSGi runtime is embedded into the Liferay platform: 

<img  src="../images/osgi-and-liferay.png" style="max-height:15%;" />

The Liferay platform still runs as a Java EE web application in a servlet container, but it also has an embedded OSGi container where all its applications live.

#### Components and Services in Liferay
 
OSGi components, services, and the service registry are the main ways to achieve modularity within the OSGi framework. Liferay follows these paradigms: functionalities and services in Liferay are mostly implemented as OSGi components, and the platform's core services are exposed through the OSGi service registry.

Below are some Liferay code examples demonstrating how Liferay leverages OSGi patterns and how Liferay functionalities are implemented as OSGi components.

The first example is a Liferay portlet. In traditional portlet development, the portlet declaration and configuration was done in XML descriptor files. Starting with Liferay DXP, the portlet configuration is in a portlet component's properties:

```java
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.application-type=full-page-application",
		"com.liferay.portlet.application-type=widget",
		"com.liferay.portlet.css-class-wrapper=portlet-blogs",
		"com.liferay.portlet.display-category=category.collaboration",
		"com.liferay.portlet.header-portlet-css=/blogs/css/main.css",
		"com.liferay.portlet.icon=/blogs/icons/blogs.png",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"com.liferay.portlet.struts-path=blogs",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Blogs", "javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.always-display-default-configuration-icons=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=categoryId",
		"javax.portlet.supported-public-render-parameter=resetCur",
		"javax.portlet.supported-public-render-parameter=tag",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class BlogsPortlet extends BaseBlogsPortlet {
...
}
```

The second example is a form text field component: 

```java
@Component(
	immediate = true,
	property = {
		"ddm.form.field.type.description=text-field-type-description",
		"ddm.form.field.type.display.order:Integer=2",
		"ddm.form.field.type.group=basic", "ddm.form.field.type.icon=text",
		"ddm.form.field.type.js.class.name=Liferay.DDM.Field.Text",
		"ddm.form.field.type.js.module=liferay-ddm-form-field-text",
		"ddm.form.field.type.label=text-field-type-label",
		"ddm.form.field.type.name=text"
	},
	service = DDMFormFieldType.class
)
public class TextDDMFormFieldType extends BaseDDMFormFieldType {
	@Override
	public Class<? extends DDMFormFieldTypeSettings>
		getDDMFormFieldTypeSettings() {
		return TextDDMFormFieldTypeSettings.class;
	}
	@Override
	public String getName() {
		return "text";
	}

}
```

The third example is an Audience Targeting application rule:

```java
@Component(
	immediate = true, 
	service = Rule.class
)
public class LanguageRule extends BaseJSPRule {
	@Activate
	@Override
	public void activate() {
		super.activate();
	}
	@Deactivate
	@Override
	public void deActivate() {
		super.deActivate();
	}
	@Override
	public boolean evaluate(
			HttpServletRequest request, RuleInstance ruleInstance,
			AnonymousUser anonymousUser)
		throws Exception {
	...
	}
}
```

#### Service Component Development Flow

Some typical Liferay development flows to create a service:

If creating a completely custom service:

1. Define the API.
1. Create service component(s) implementing the API.
1. Expose the API to other bundles as needed (in `bnd.bnd`).
1. Reference the service from a consuming component.

Specifically in creating a Liferay service component or override, like an MVC Render Command or an Indexer Post Processor:

1. Find or create an interface to implement or a superclass to extend. 
1. Create a service component implementing the interface or extending a superclass.
1. Expose the API to other bundles as needed (in `bnd.bnd`).
1. Reference the service from consuming components.

> While every OSGi service is an OSGi *component*, not every component is a *service*. To publish a service in the OSGi service registry, the component has to be declared to be a *service*. Remember also that to reference and consume an OSGi service, a class has to be declared to be a *component*. Using a `@Reference` annotation in a non-component class will not work.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>Liferay has an embedded OSGi ___________________.</li>
	<li>All of the Liferay platform's core applications are ________________ applications.</li>
	<li>Services and functionalities within the Liferay platform leverage the OSGi __________________________________ model.</li>
</ul>
</div>