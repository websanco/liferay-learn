---
description: 10 - Customize the Service Layer
title: Override OSGi Service References
order: 3
---

## Override OSGi Service References

[OSGi Declarative Services](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html) allow you to automatically and dynamically inject published services in the OSGi service registry into your components using the `@Reference` annotation. Below is an excerpt of the Liferay Blogs application's [BlogsAdminPortlet](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/portlet/BlogsAdminPortlet.java) portlet component, which has multiple service references:

```java
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-blogs",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/blogs/css/main.css",
		"com.liferay.portlet.icon=/blogs/icons/blogs.png",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Blogs", "javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/blogs/view",
		"javax.portlet.init-param.portlet-title-based-navigation=true",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class BlogsAdminPortlet extends BaseBlogsPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(AssetWebKeys.ASSET_HELPER, _assetHelper);
		renderRequest.setAttribute(TrashWebKeys.TRASH_HELPER, _trashHelper);

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private AssetHelper _assetHelper;

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.blogs.web)(&(release.schema.version>=1.2.0)(!(release.schema.version>=1.3.0))))"
	)
	private Release _release;

	@Reference
	private TrashHelper _trashHelper;

}
```

If an OSGi container allows multiple implementations of a service to co-exist and a consuming component needs just a single implementation, which implementation gets injected? From the perspective of __consuming__ OSGi components, the selection depends on the following factors:

1. The available implementation's `service.ranking` property value
1. `@Reference` annotation attributes

#### The `service.ranking` Property {#ranking}

The `service.ranking` property defines the priority of this implementation among the other components implementing the same service.

```java
@Component(
 immediate = true,
 property = {
	 "context.id=BladeCustomJspBag", 
	 "context.name=Test Custom JSP Bag",
	 "service.ranking:Integer=100"
 }
)
public class BladeCustomJspBag implements CustomJspBag {
...
```

The default value for the `service.ranking` property is 0. An implementation with a higher number has higher priority. This property makes it possible for the consuming components to rank the available implementations. That way, the consuming components can not only pick the implementation with the highest priority but also order the implementations, as in the case of a service reference array, shown in the example below:

```java
@Reference(
	bind = "addProcessor", 
	cardinality = ReferenceCardinality.MULTIPLE, 
	policy = ReferencePolicy.DYNAMIC, 
	service = Processor.class, 
	unbind = "removeProcessor"
)
private volatile List<Processor> _processors = null;
```

#### `@Reference` Attributes {#attributes}

From the perspective of the consuming component, the injection behavior depends on the consuming component's `@Reference` annotation attributes. With default attributes, the first implementation available gets injected and is reluctant to change even if an implementation that takes higher priority becomes available. This is called a *static and reluctant policy*. In this scenario, the OSGi bundle startup order defines the implementation.

Let's take a look at the available attributes.

#### Target {#target}

Target attributes allow you to filter or query the service implementation to be injected, using the LDAP query syntax. 

In the example below, the [ElasticSearchIndexWriter](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search-elasticsearch6/portal-search-elasticsearch6-impl/src/main/java/com/liferay/portal/search/elasticsearch6/internal/ElasticsearchIndexWriter.java) component in Liferay's Elasticsearch search engine adapter module is asking for a reference to a SpellCheckIndexWriter implementation, which has the component property `search.engine.impl=Elasticsearch`. Filtering with the `target` attribute ensures that an implementation for the right physical search engine gets implemented:

```java
@Component(
	immediate = true, property = {"search.engine.impl=Elasticsearch"},
	service = IndexWriter.class
)
public class ElasticsearchIndexWriter extends BaseIndexWriter {

	...

	@Override
	@Reference(
		target = "(search.engine.impl=Elasticsearch)", 
		unbind = "-"
	)
	public void setSpellCheckIndexWriter(
		SpellCheckIndexWriter spellCheckIndexWriter) {

		super.setSpellCheckIndexWriter(spellCheckIndexWriter);
	}
	
	...
```

The [ElasticsearchSpellCheckIndexWriter](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search-elasticsearch6/portal-search-elasticsearch6-impl/src/main/java/com/liferay/portal/search/elasticsearch6/internal/ElasticsearchSpellCheckIndexWriter.java) component has (uniquely) the matching property value and gets injected:

```java
@Component(
	immediate = true, 
	property = {
		"search.engine.impl=Elasticsearch"
	},
	service = SpellCheckIndexWriter.class
)
public class ElasticsearchSpellCheckIndexWriter
	extends BaseGenericSpellCheckIndexWriter {
...
```

#### Reference Cardinality {#cardinal}

Reference cardinality defines whether a reference has to be satisfied for a component to be able to activate. Cardinality has the following possible values:

* __MANDATORY:__ the reference has to be satisfied (available and injected) before the component will start (default)
* __OPTIONAL:__ the reference is not required for the component to start and work
* __MULTIPLE:__ the reference is not required, but multiple implementations may satisfy the reference and the component will take all of them
* __AT\_LEAST\_ONE:__ like multiple, but at least one reference is required for the component to start

> When using the multi options, a setter method has to be defined.

#### Reference Policy {#policy}

The reference policy defines whether an injected service reference can be replaced dynamically. The following options are available:

* __STATIC:__ the reference is required and the component will not be notified of alternative services as they become available (default)
* __DYNAMIC:__ the reference is not required and the component accepts new references as they become available

#### The Reference PolicyOption {#poloption}

The policy option provides more granularity for the dynamic injection policy:

* __RELUCTANT:__ For a single reference cardinality, new available references will be ignored. For multiple reference cardinality, new reference potentials will be bound.
* __GREEDY:__  As new reference potentials become available, the component will bind to them.

#### Other @Reference Attributes {#other}

* __bind:__ the name of the bind method for this reference
* __field:__ the name of the field for this reference
* __fieldOption:__ the field option for this reference:
	* REPLACE: replace the field value with a new value when there are changes to the bound services
	* UPDATE:  update the collection referenced by the field when there are changes to the bound services
* __name:__ name of this reference
* __scope:__ scope for the reference
* __service:__ the service class name for this reference
* __unbind:__ unbind the event method for the reference
* __updated:__ updated the event method for the reference

#### @Reference Attributes Example {#example}

Let's interpret the reference example below, with multiple attibutes:

```java
@Reference(
	bind = "setPermissionFilterQueryBuilder", 
	cardinality = ReferenceCardinality.MANDATORY,
	policy = ReferencePolicy.DYNAMIC, 
	policyOption = ReferencePolicyOption.GREEDY, 
	service = PermissionFilterQueryBuilder.class, 
	unbind = "removePermissionFilterQueryBuilder"
)
private PermissionFilterQueryBuilder _permissionFilterQueryBuilder;
```	

* The reference has to be satisfied (`cardinality`).
* The component can accept new references as they become available (`policy`).
* The component will bind an implementation with a higher service ranking if one becomes available (`policyOption`).
* The reference calls for a setPermissionFilterQueryBuilder(PermissionFilterQueryBuilder) method to initialize the reference (`bind`).
* The reference calls for a removePermissionFilterQueryBuilder(PermissionFilterQueryBuilder) method when unsetting the reference (`unbind`). 
* The implementation has the service type `PermissionFilterQueryBuilder`(`service`).

#### Overriding Static and Reluctant References {#override}

Even when the consuming component's reference has a static and reluctant policy, meaning that it's not allowing new candidates after being satisfied, it's possible to manually override the used implementation by configuring the OSGi container itself. This approach is explained on Liferay Developer Network (https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/reconfiguring-components-to-use-your-service).

<div class="summary-chapter">
<h3>Knowledge Check</h3>
<ul>
	<li>The ___________________________ is an OSGi component property that defines the priority of service components among the components implementing the same service.</li>
	<li>___________________________ allow you to filter the service implementation to be injected.</li>
	<li>Reference cardinality defines whether a reference has to be _____________________ for a component to be able to activate.</li>
	<li>The ___________________________ defines whether an injected service reference can be replaced dynamically.</li>
	<li>The policy option provides more granularity for the ___________________________ policy.</li>
</ul>
</div>