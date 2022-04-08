---
description: 06 - Real World Application
title: Integrate with Liferay Frameworks
order: 6
---

## Integrate with the Asset Framework

The Asset Framework is a Liferay platform framework that makes it possible to publish and manage any kind of content in a unified way and through a standard API. The framework provides ways of associating and linking content with, for example, other portal assets, tags, and categories, and makes it possible to integrate with portal search, workflows and staging.

The central concepts in integrating with the Asset Framework are an Asset, an Asset Renderer, and an Asset Renderer Factory.

#### Assets

An Asset is an abstract, generic representation of any model entity wrapped in an [AssetEntry](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/asset/kernel/model/AssetEntry.java) class guaranteeing a certain set of metadata (fields) for the consuming applications and APIs. 

#### Asset Renderers

The Asset Renderer Class is responsible for rendering the URLs for viewing and editing an asset, checking view permissions, and providing access to the wrapped entity. The Asset Renderer implements the [AssetRenderer](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/asset/kernel/model/AssetRenderer.java) interface. 

#### Asset Renderer Factories

An Asset Renderer Factory is an OSGi component class that makes an Asset Renderer available to the calling application or API. The factory pattern provides a possibility to have multiple renderers for a single asset type.

#### Asset Framework Diagram

The diagram below illustrates and summarizes the components of the Asset Framework.

On a portal page, there is an __Asset Publisher__ portlet querying the newest assets. The Asset Framework gets a list of assets that contain the required set of metadata and references to the actual content items. When rendering the assets, the Asset Publisher portlet first finds the __Asset Renderer Factory__ service for the model type and then asks for an __Asset Renderer__ from the factory. The Asset Renderer uses the data from the actual content item, wrapped by an asset, to render the item on the type specific __JSP files__ provided. Every Asset Publisher view, like abstracts, full content, and table has a dedicated JSP file:

<img src="../images/asset-framework.png" style="max-height:100%"/>

#### An Asset in the Database

Below is an example of database table structures for BlogsEntry and AssetEntry. You can see that the AssetEntry contains a subset of the data of BlogsEntry:

**BlogsEntry**
```sql

mysql> describe blogsentry;
+-----------------------+--------------+------+-----+---------+-------+
| Field                 | Type         | Null | Key | Default | Extra |
+-----------------------+--------------+------+-----+---------+-------+
| uuid_                 | varchar(75)  | YES  | MUL | NULL    |       |
| entryId               | bigint(20)   | NO   | PRI | NULL    |       |
| groupId               | bigint(20)   | YES  | MUL | NULL    |       |
| companyId             | bigint(20)   | YES  | MUL | NULL    |       |
| userId                | bigint(20)   | YES  |     | NULL    |       |
| userName              | varchar(75)  | YES  |     | NULL    |       |
| createDate            | datetime(6)  | YES  |     | NULL    |       |
| modifiedDate          | datetime(6)  | YES  |     | NULL    |       |
| title                 | varchar(150) | YES  |     | NULL    |       |
| subtitle              | longtext     | YES  |     | NULL    |       |
| urlTitle              | varchar(150) | YES  |     | NULL    |       |
| description           | longtext     | YES  |     | NULL    |       |
| content               | longtext     | YES  |     | NULL    |       |
| displayDate           | datetime(6)  | YES  | MUL | NULL    |       |
| allowPingbacks        | tinyint(4)   | YES  |     | NULL    |       |
| allowTrackbacks       | tinyint(4)   | YES  |     | NULL    |       |
| trackbacks            | longtext     | YES  |     | NULL    |       |
| coverImageCaption     | longtext     | YES  |     | NULL    |       |
| coverImageFileEntryId | bigint(20)   | YES  |     | NULL    |       |
| coverImageURL         | longtext     | YES  |     | NULL    |       |
| smallImage            | tinyint(4)   | YES  |     | NULL    |       |
| smallImageFileEntryId | bigint(20)   | YES  |     | NULL    |       |
| smallImageId          | bigint(20)   | YES  |     | NULL    |       |
| smallImageURL         | longtext     | YES  |     | NULL    |       |
| lastPublishDate       | datetime(6)  | YES  |     | NULL    |       |
| status                | int(11)      | YES  |     | NULL    |       |
| statusByUserId        | bigint(20)   | YES  |     | NULL    |       |
| statusByUserName      | varchar(75)  | YES  |     | NULL    |       |
| statusDate            | datetime(6)  | YES  |     | NULL    |       |
+-----------------------+--------------+------+-----+---------+-------+
29 rows in set (0,01 sec)
```

**AssetEntry**
```sql
mysql> describe assetentry;
+----------------+-------------+------+-----+---------+-------+
| Field          | Type        | Null | Key | Default | Extra |
+----------------+-------------+------+-----+---------+-------+
| entryId        | bigint(20)  | NO   | PRI | NULL    |       |
| groupId        | bigint(20)  | YES  | MUL | NULL    |       |
| companyId      | bigint(20)  | YES  | MUL | NULL    |       |
| userId         | bigint(20)  | YES  |     | NULL    |       |
| userName       | varchar(75) | YES  |     | NULL    |       |
| createDate     | datetime(6) | YES  |     | NULL    |       |
| modifiedDate   | datetime(6) | YES  |     | NULL    |       |
| classNameId    | bigint(20)  | YES  | MUL | NULL    |       |
| classPK        | bigint(20)  | YES  |     | NULL    |       |
| classUuid      | varchar(75) | YES  |     | NULL    |       |
| classTypeId    | bigint(20)  | YES  |     | NULL    |       |
| listable       | tinyint(4)  | YES  |     | NULL    |       |
| visible        | tinyint(4)  | YES  | MUL | NULL    |       |
| startDate      | datetime(6) | YES  |     | NULL    |       |
| endDate        | datetime(6) | YES  |     | NULL    |       |
| publishDate    | datetime(6) | YES  | MUL | NULL    |       |
| expirationDate | datetime(6) | YES  | MUL | NULL    |       |
| mimeType       | varchar(75) | YES  |     | NULL    |       |
| title          | longtext    | YES  |     | NULL    |       |
| description    | longtext    | YES  |     | NULL    |       |
| summary        | longtext    | YES  |     | NULL    |       |
| url            | longtext    | YES  |     | NULL    |       |
| layoutUuid     | varchar(75) | YES  | MUL | NULL    |       |
| height         | int(11)     | YES  |     | NULL    |       |
| width          | int(11)     | YES  |     | NULL    |       |
| priority       | double      | YES  |     | NULL    |       |
| viewCount      | int(11)     | YES  |     | NULL    |       |
+----------------+-------------+------+-----+---------+-------+
27 rows in set (0,00 sec)
```

The wrapped model entity is referenced by its ID in the AssetEntry's `classPK` (Class Primary Key) field. In the example below, the `classPK` contains the `entryId` for a BlogsEntry:

**AssetEntry**
```sql
mysql> select entryId, classPK,  groupId, companyId, userId, userName, title from assetentry where classPK=63341;
+---------+---------+---------+-----------+--------+--------------+----------------------+
| entryId | classPK | groupId | companyId | userId | userName     | title                |
+---------+---------+---------+-----------+--------+--------------+----------------------+
|   63342 |   63341 |   47971 |     20115 |  20155 | Liferay Demo | New Great Blog Entry |
+---------+---------+---------+-----------+--------+--------------+----------------------+
1 row in set (0,00 sec)
```

**BlogsEntry**
```sql
mysql> select entryId, groupId, companyId, userId, userName, title from blogsentry where entryId=63341;
+--------------------------------------+---------+---------+-----------+--------+--------------+----------------------+
| entryId | groupId | companyId | userId | userName     | title                |
+--------------------------------------+---------+---------+-----------+--------+--------------+----------------------+
|   63341 |   47971 |     20115 |  20155 | Liferay Demo | New Great Blog Entry |
+--------------------------------------+---------+---------+-----------+--------+--------------+----------------------+
1 row in set (0,00 sec)
```

Asset entry fields and their descriptions:

* __userId:__ the user updating the content
* __groupId:__ the scope group of the created content
* __createDate:__ the date the entity was created
* __modifiedDate:__ the date the entity was last modified
* __className:__ identifies the entity’s class
* __classPK:__ the primary key of the model entity
* __classUuid:__ a secondary identifier that’s guaranteed to be universally unique
* __classTypeId:__ identifies the particular variation of this class (if any, default 0)
* __categoryIds:__ the asset category ids for the entity
* __tagNames:__ tag names for the entity
* __listable:__ specifies whether the entity can be shown in dynamic lists of content (Asset Publisher)
* __visible:__ specifies whether the entity is approved
* __startDate:__ when the entity should be visible
* __endDate:__ when the entity should stop being visible
* __publishDate:__ the date the entity will be published (visible)
* __expirationDate:__ the date the entity will be archived (not visible)
* __mimetype:__ the Mime type, such as ContentTypes.TEXT_HTML of the content
* __title:__ the entity’s name
* __description:__ a String-based textual description of the entity
* __summary:__ a shortened or truncated sample of the entity’s content
* __url:__ a URL to optionally associate with the entity
* __layoutUuid:__ the universally unique ID of the layout of the entry’s default display page
* __height:__ this can be set to 0
* __width:__ this can be set to 0
* __priority:__ specifies how the entity is ranked among peer entity instances; the lower numbers take priority

#### The Benefits of Integrating the Asset Framework

To leverage most of Liferay's native features, a custom entity must be integrated into the Asset Framework. Integration allows you to: 

* Show custom entities in the Asset Publisher portlet
* Associate tags and categories
* Associate comments and ratings
* Integrate with portal search
* Integrate with portal workflows
* Enable staging on the entities
* Link assets to each other
* Assign social bookmarks like Facebook likes to the entity
* Add custom fields (Liferay Expando API)
* Track the number of times an asset is viewed
* Implement Recycle Bin support

#### Steps for Integrating the Asset Framework

Generally, the steps to integrate with the Asset Framework are:

1. Add the required fields, Asset Framework references, and finders to the model entity.
1. Manage the asset lifecycle (usually in the CRUD methods on the service layer).
	* Whenever you modify the custom model entity, the corresponding asset entry has to be updated as well.
1. Create an asset renderer factory for providing the renderer the model entity.
1. Create an asset renderer for displaying the model entity.

Additionally, if you want to show your assets in the Asset Publisher:

1. Implement the JSP files to support the different display modes of the Asset Publisher.
1. Integrate with the Liferay Search framework (required).

> See the Developer Network article: https://dev.liferay.com/fi/develop/tutorials/-/knowledge_base/7-2/assets-integrating-with-liferays-framework for more information.

#### Integrate with the Search Framework

Before version 7.1, there used to be a single indexer component for taking care of everything search indexer related for a model entity. In 7.1 the approach was modularized to provide a clean approach for controlling different aspects of search framework integration. You can still use the old approach, however.

Generally, the steps to integrate with the Search framework are: 

1. Implement a Model Registrar class to register with the search framework
1. Implement a Model Document Contributor to control which fields are indexed
1. Implement a Model Indexer Writer Contributor to configure reindexing
1. Implement a Keyword Query Contributor to control which fields of the model are being queried
1. Implement a Model Summary Contributor to control the summaries returned
1. Add the `@Indexable` annotations to the service methods that should trigger indexing.
1. Integrate with the Asset Framework to be able to show the entities in the Asset Publisher

> See details about all the available contributors and integrating to Search Framework in the Developer Network: https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/search-and-indexing. For a real world example, see https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search.

#### Keeping the Search Index Updated on Entity Modification Events

When you use Service Builder, the Liferay-provided method level `@Indexable` annotation automates the task for you. Just annotate those methods, which should trigger an index update. The only requirement for annotated  methods is that they __have to return the target entity__. 

The @Indexable annotation has two action types:

* __REINDEX:__ on add or update 
* __DELETE:__ on entity delete

Below is an example from a Service Builder project implementation class, where an index document gets created on entity add and deleted on deletion:

**addAssignment()**
```java
@Indexable(
	type = IndexableType.REINDEX
)
public Assignment addAssignment(
	long groupId, Map<Locale, String> titleMap, String description,
	Date dueDate, ServiceContext serviceContext)
	throws PortalException {
	...
	return assignment;
}
```

**deleteAssignment()**
```java
@Indexable(
	type = IndexableType.DELETE
)
public Assignment deleteAssignment(Assignment assignment)
	throws PortalException {
	...
	return assignment;
}
```

> Notice that by default, the CRUD methods of Service Builder generated service __base classes__ are annotated with @Indexable automatically. If you call the annotated base class methods from your implementation class, you don't have to annotate them.

#### Creating a Custom Search Interface

To get your custom entities to show up in standard portal search, you  have to integrate with the Liferay Asset Framework. If, however, you would like to create your own custom search user interface, you can call the extensive portal search API directly.

Generally, the steps to create a custom Liferay search interface are:

1. Send the query parameters from the user interface to the back-end.
1. Catch the parameters and build a *SearchContext* object that transports all the required information to the search engine adapter.
1. Call the *IndexSearcherHelper* service and execute the search.
1. Format the *Hits* objects for the user interface

Below is an example of creating the SearchContext object and executing the search:

```java
public Hits doSearch(ThemeDisplay, BooleanClause booleanClause, int start, int end, Sort[] sorts) {
	
	SearchContext searchContext = new SearchContext();
	searchContext.setCompanyId(themeDisplay.getCompanyId());
	searchContext.setStart(start);
	searchContext.setEnd(end);
	searchContext.setSorts(sorts);
	
	searchContext.setBooleanClauses(new BooleanClause[] {
		booleanClause
	});
	
	Hits hits = _indexSearcherHelper.search(searchContext, query);
	
	return hits;
}

@Reference
private IndexSearcherHelper _indexSearcherHelper;

```