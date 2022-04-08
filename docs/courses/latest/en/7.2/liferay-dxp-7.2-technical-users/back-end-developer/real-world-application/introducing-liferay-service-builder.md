---
description: 06 - Real World Application
title: Introducing Liferay Service Builder
order: 3
---

## Introduction

Liferay Service Builder is a code generation tool that takes an XML configuration file as an input and generates the complete service layer as an output. The generated code includes the database schema definition, persistence and caching code, service classes with CRUD methods, and a remote service layer supporting JSON and SOAP web services. The generated code can be complemented and overridden by service implementation classes, which are created for every entity defined in the service schema.

The diagram below illustrates a Service Builder project file structure before and after code generation:

<img src="../images/service-builder-process.png" style="max-height:100%"/>

A service created by the Service Builder defines a zone where all the operations are run within the same transaction. Service Builder can also be used just to create a web service, without defining any persistence entities.

Service Builder is one of the central development patterns in Liferay and is used in its core services. The persistence functionalities rely on [Hibernate](http://hibernate.org/), while in the implementation classes, you can choose whether to use [Spring](https://spring.io/) or, as of 7.2, OSGi Declarative Services. While generated service classes are Spring beans, they are wired to the OSGi service container and exposed through the OSGi service registry.

The generated database schema can be fine-tuned on a field-mapping level, and you can choose to use external datasources for any entity. Although Service Builder-generated code abstracts the database layer, providing basic CRUD methods, you can completely customize the service implementation classes and use dynamic and custom SQL queries in the code.

> Note: You can only have a single datasource for a single Service Builder module. See the [Developer Network article for more information: https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/connecting-service-builder-to-external-databases

Let's walk through the basic concepts of Liferay Service Builder.

#### service.xml

`service.xml` is the main Service Builder configuration file containing:

* Global information like the database namespace and package path for the service
* Entity definitions
* Default order of entity retrieval
* Entity finder methods
* Datasource configuration
* Custom service exception definitions
* Service references available in the generated service classes
* Caching information

Service class generation is done with the `buildService` Gradle task. Every time `service.xml` or signatures in the generated implementation classes are modified, the `buildService` task has to be re-run.

Below is an excerpt of the `service.xml` of [Liferay's Blogs application](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/blogs/blogs-service/service.xml):

```xml
<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.2.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_2_0.dtd">

<service-builder auto-import-default-references="false" auto-namespace-tables="false" dependency-injector="ds" package-path="com.liferay.blogs">
	<namespace>Blogs</namespace>
	<entity local-service="true" name="BlogsEntry" remote-service="true" trash-enabled="true" uuid="true">

		<!-- PK fields -->

		<column name="entryId" primary="true" type="long" />

		<!-- Group instance -->

		<column name="groupId" type="long" />

		<!-- Audit fields -->

		<column name="companyId" type="long" />
		<column name="userId" type="long" />
		<column name="userName" type="String" uad-anonymize-field-name="fullName" />
		<column name="createDate" type="Date" />
		<column name="modifiedDate" type="Date" />
```

While we'll walk through the basic `service.xml` configuration in our Gradebook exercise, a lot of available options fall out of scope. See the [Service Builder DTD file](https://docs.liferay.com/portal/7.2-latest/definitions/liferay-service-builder_7_2_0.dtd.html) for all the options and documentation which you can later use in your own Service Builder projects. 

> When upgrading Service Builder projects to a newer portal versions, it's important to update the DTD to get all the improvements in the generated code within.

#### portlet-model-hints.xml

The `portlet-model-hints.xml` file is generated when the `buildService` task is run in `resources/META-INF`. It lets you customize the entity to SQL column mapping, like field types, column sizes, and validation.

Below is the `portlet-model-hints.xml` of  [Liferay's Blogs application](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-service/src/main/resources/META-INF/portlet-model-hints.xml):

```xml
<?xml version="1.0"?>

<model-hints>
	<model name="com.liferay.blogs.model.BlogsEntry">
		<field name="uuid" type="String" />
		<field name="entryId" type="long" />
		<field name="groupId" type="long" />
		<field name="companyId" type="long" />
		<field name="userId" type="long" />
		<field name="userName" type="String" />
		<field name="createDate" type="Date" />
		<field name="modifiedDate" type="Date" />
		<field name="title" type="String">
			<hint name="max-length">150</hint>
			<sanitize content-type="text/plain" modes="ALL" />
			<validator name="required" />
		</field>
		<field name="subtitle" type="String">
			<hint-collection name="TEXTAREA" />
		</field>
		<field name="urlTitle" type="String">
			<hint name="max-length">255</hint>
		</field>
		<field name="description" type="String">
			<hint-collection name="TEXTAREA" />
			<hint name="display-width">350</hint>
		</field>
		<field name="content" type="String">
			<hint-collection name="CLOB" />
			<sanitize content-type="text/html" modes="ALL" />
		</field>
		<field name="displayDate" type="Date" />
		<field name="allowPingbacks" type="boolean" />
		<field name="allowTrackbacks" type="boolean" />
		<field name="trackbacks" type="String">
			<hint-collection name="CLOB" />
		</field>
		<field name="coverImageCaption" type="String">
			<hint-collection name="TEXTAREA" />
			<sanitize content-type="text/html" modes="ALL" />
		</field>
		<field name="coverImageFileEntryId" type="long" />
		<field name="coverImageURL" type="String">
			<hint-collection name="URL" />
		</field>
		<field name="smallImage" type="boolean" />
		<field name="smallImageFileEntryId" type="long" />
		<field name="smallImageId" type="long" />
		<field name="smallImageURL" type="String">
			<hint-collection name="URL" />
			<hint name="display-width">210</hint>
		</field>
		<field name="lastPublishDate" type="Date" />
		<field name="status" type="int" />
		<field name="statusByUserId" type="long" />
		<field name="statusByUserName" type="String" />
		<field name="statusDate" type="Date" />
	</model>
	<model name="com.liferay.blogs.model.BlogsStatsUser">
		<field name="statsUserId" type="long" />
		<field name="groupId" type="long" />
		<field name="companyId" type="long" />
		<field name="userId" type="long" />
		<field name="entryCount" type="int" />
		<field name="lastPostDate" type="Date" />
		<field name="ratingsTotalEntries" type="int" />
		<field name="ratingsTotalScore" type="double" />
		<field name="ratingsAverageScore" type="double" />
	</model>
</model-hints>
```

#### Local Service

When configuring the service, you can define which kind of services are generated in the `service.xml`. There are two kinds of services available: the Local service and the Remote service.

The Local service is meant to be a service access-point within the same JVM and without any permission checks. The Local service is the layer where you call the persistence layer to retrieve and store data entities.

The Local service generation is enabled in `service.xml`:

```xml
<entity name="Assignment" local-service="true" ... >
```

#### Remote Service

The remote service serves two purposes. First, it's meant to be a façade layer for the local service, a place where you can implement permission checking. The other purpose is to provide JSON and SOAP APIs for the service.

If you are providing user-level access to the service, you should implement the remote service layer with permission checks and access the service through it. When you don't need permission checks, accessing the service through the local service should be preferred because of better performance.

Remote service generation for an entity is enabled in the entity definition in `service.xml`:

```xml
<entity name="Assignment" remote-service="true" ... >
```

#### Service Implementation Classes

 Service implementation classes are for overriding and customizing the generated service methods and for implementing your own business logic. They are the __only generated classes to be modified manually__. When you build and generate the service, the implementation classes are, depending on the entity configuration, created in the service module for:

* Local service
* Remote service
* Entity model class
* Entity finders

When implementing custom CRUD logic in the local or remote service implementation classes, the following naming and signature pattern is recommended:

* `{entity}ServiceImpl.add{entity}(userId, groupId, {all entity fields}, {serviceContext});`
	* returns created `{entity}`
* `{entity}ServiceImpl.update{entity}({primaryKey}, {all entity fields}, {serviceContext})`
	* returns updated `{entity}`
* `{entity}ServiceImpl.delete{entity}({primaryKey});`
	* returns deleted `{entity}` 

As an example, un the Gradebook exercise, we'll use this pattern for example in the `AssignmentLocalServiceImpl.updateAssignment()`:

```java
public Assignment updateAssignment(
	long assignmentId, Map<Locale, String> titleMap, String description,
	Date dueDate, ServiceContext serviceContext)
	throws PortalException {

	Assignment assignment = getAssignment(assignmentId);

	assignment.setModifiedDate(new Date());
	assignment.setTitleMap(titleMap);
	assignment.setDueDate(dueDate);
	assignment.setDescription(description);

	assignment = super.updateAssignment(assignment);

	return assignment;
}
```

> Having the entity as a return value in the CRUD methods is mandatory when using the `@Indexable` annotations in integrating with the search framework. 
	
Remember that the `buildService` task must be run whenever `service.xml` is modified or any of the method signatures in the model, service, or finder implementation classes have been changed. Thus, a typical workflow in creating the service layer is:

1. Define the model entities and finder queries in `service.xml`.
1. Run the `buildService` task.
1. Modify implementation classes.
1. Run the `buildService` task.
1. Modify the implementation classes.
1. Run the `buildService` task.
1. ...

#### "Silencing" Generated Service Methods

Sometimes it's useful to silence generated methods or method signatures to ensure the correct usage of the API. Below is an example of overriding a generated signature in a service implementation class:

```java
@Override
public class AssignmentLocalServiceImpl extends AssignmentLocalServiceBaseImpl {

	public Assignment addAssignment(Assignment assignment) {
		throw new UnsupportedOperationException(
			"please use instead addAssignment(long groupId,  long userId, Map<Locale, String> titleMap," +
			" String description, Date dueDate, ServiceContext)");		
	}
}
```

#### Finders

Finders are methods for querying the database and are defined in `service.xml`. They are automatically cached and can be customized in the entity-specific finder implementation classes.

There are two kind of finders:

* __Finders:__ work without permission-checking
* __Filtered finders:__ provide permission-checking

The actual finder methods are created in the persistence classes. To access those via your service, you have to implement facade methods for them. Also note that entity __permissions have to be defined__ in order to have filtered finders automatically created.

Finders in Liferay core services are using a naming pattern using the first letters of the finder parameters, separated by underscores. The following finder in the [Blogs application](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/service/persistence/impl/BlogsEntryPersistenceImpl.java) is returning a collection of Blogs entries and accepting `groupId` and `status` as parameters:

```xml{1}
<finder name="G_S" return-type="Collection">
	<finder-column name="groupId" />
	<finder-column name="status" />
</finder>
```

Finder methods are created in the persistence classes. To access those via your service, you have to implement façade methods for them. Here is an example from the [BlogsEntryPersistenceImpl](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/service/persistence/impl/BlogsEntryPersistenceImpl.java):

```java{2}
@Override
public List<BlogsEntry> findByG_S(long groupId, int status) {
	return findByG_S(
		groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
}
```

#### Service Wrappers

When the `buildService` task is run, it not only generates implementation classes for all the model entities, it also generates so-called service wrapper APIs, which allow you to override your services in an external module. With this approach, you can also override Liferay core services. 

The diagram below illustrates Service Builder design and architecture as well as the positioning of service wrappers:

<img src="../images/service-builder-overview.png" style="max-height: 45%"/>

> Service Wrappers will be discussed in detail in *Module 10 - Customize the Service Layer*.

#### Service Context

When you work with Liferay services you'll often see a `ServiceContext` object in the method parameters. The `ServiceContext` is a wrapper object for contextual information, implementing the [Parameter Object](http://principles-wiki.net/patterns:parameter_object) design pattern. It aggregates information necessary for features used throughout Liferay’s portlets and services, such as:

* Actions
* Request parameters
* Classifications (tags and categories)
* Exceptions
* Scoping (company and group)
* Locale
* Request object
* Permission-related information

> All the request parameters can be accessed with the  `ServiceContext.getAttribute()` method.

> See [Liferay Developer Network](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-2/understanding-servicecontext) for more information about the `ServiceContext`.

#### Service Builder Caching

Service Builder provides built-in caching support for all entities and finders defined in the service configuration. Cache implementation relies on [EHCache](https://www.ehcache.org/).

Caching is implemented on three levels:

* Hibernate
* Entity
* Finder

Hibernate cache has two layers: level 1 (L1) and level 2 (L2). __Level 1__ is used to cache objects retrieved from the database within the current database session, which is usually tied to the invocation of Liferay's service layer within a single request.

__Level 2__ stores both database objects (Entity Cache) and results of queries (Query Cache) and is able to span across database sessions. Liferay provides custom Service Builder Entity and Query (Finder) caches, making the Hibernate L2 cache redundant. L2 cache is disabled by default, and unless you are accessing Hibernate code directly, there shouldn't be a reason to enable it.

#### Service Builder and OSGi

An important thing to notice is that, currently, the Service Builder-generated service classes are Spring beans and not OSGi service components. Although they are wired to the OSGi service registry, you can't use the OSGi `@Reference` annotation inside the Service Builder-generated classes. Making other Liferay services available in your custom service class should be done by referencing them in the `service.xml` configuration file or using the Spring `@ServiceReference` and `@BeanReference` annotations.

With 7.2, the Service Builder is relying on OSGi and Declarative Services but you can still choose to use the Spring approach. The dependency injector attribute is defined in the `service.xml` and can have either the `ds` value for Declarative Services or the `spring` value for Spring AOP. For new OSGi modules, the default value is `ds` and for the WARs, it's `spring`.

```xml
<service-builder dependency-injector="ds" package-path="com.liferay.training.gradebook">
```

For further reading on the Service Builder resources in Liferay Developer Network: https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/service-builder

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>_____________________________ is a code generation tool that takes an _____________________________ as an input and generates a _____________________________ as an output.</li>
	<li>There are two kinds of services available:</li>
	<ul>
		<li>_____________________________</li>
		<li>_____________________________</li>
	</ul>
	<li>_____________________________ are database-querying methods with caching support.</li>
	<li>Service _____________________________ are the only classes meant to be _____________________________.</li>
	<li>_____________________________ allow you to override Service Builder-generated services. They can also be used to override core Liferay services.</li>   
</ul>
</div>