## Create the Assignment Service

<div class="ahead">

#### Exercise Goals

- Create a Liferay Service Builder Project using the _service-builder_ template
- Define the Assignment entity
- Define service exceptions
- Final code review
- Build the service 

</div>
	
#### Create a Liferay Service Builder Project
1. **Launch** Developer Studio if it's not already running.
	* Make sure you are using the gradebook-workspace
2. **Create** a new _Liferay Module Project_ in the gradebook-workspace.
3. **Type** `gradebook` for _Project Name_.
4. **Choose** _service-builder_ for _Project Template Name_.
5. **Click** *Next*.
6. **Type** `com.liferay.training.gradebook` for _Package name_.
7. **Click** *Finish*.

<br />

<img src="images/service_builder_project_created.png" style="max-width:50%;" />


`service.xml` is the main configuration file of a Service Builder project. It lets you define model entities, data sources, finder methods, and exceptions for your service. You can customize `service.xml` with a graphical designer tool or edit the file's source code directly. 

#### Define the Assignment Entity
1. **Go to** `modules > gradebook > gradebook-service` in the _Project Explorer_.
2. **Open** the `service.xml` file.
3. **Click** on the *Overview* view.
4. **Click** _Service Builder_ in the outline tree.
5. **Enter** "Gradebook" in the *Namespace*.
6. **Right-click** on the `Foo` entity in the outline tree 
7. **Click** _Delete_.

<br />

<img src="images/namespace_defined.png" style="max-width:80%;" />

#### Create the Assignment Entity 
1. **Click** the green plus icon on the right side of the entities list to add a new entity.
2. **Type** `Assignment` for the _Name_ field.
3. **Check** both the *Local Service* and the *Remote Service*.

<br />

<img src="images/assignment_entity_created.png" style="max-width:70%;" />

#### Define Assignment Columns
1. **Double-click** on the _Assignment_ entity in the outline tree to open entity properties.
2. **Click** _Columns_.
3. **Click** _Add Default Columns_ to add a default set of fields.
4. **Click** the green plus sign on the right side of the columns list to add a new column.
5. **Type** `title` for _Name_.
6. **Double-click** the _Type_ field for the title column.
7. **Click** the browse icon on the right side of the field.
8. **Choose** _String_.

<br />

<img src="images/assignment_columns_defined.png" style="max-width:60%;" />

#### Edit the Column Definitions for the Assignment Entity
1. **Click** on the _Source_ tab in the designer.
2. **Add** the rest of the Assignment's columns after the _title_ column:

	```xml
	<column name="description" type="String"></column>
	<column name="dueDate" type="Date"></column>
	```

<br />

<img src="images/columns_defined.png" style="max-width:60%;" />

#### Add Definitions to the `service.xml` File
1. **Add** the following snippet after the _column_ definitions:
	```xml
	<!-- Order -->
	<order by="asc">
		<order-column name="title" />
	</order>
	```

2. **Add** the following snippet after the _order_ definition:
	```xml
	<!-- Finders -->
	<!-- Find by groupId -->
	<finder name="GroupId" return-type="Collection">
		<finder-column name="groupId"></finder-column>
	</finder>
	```

	> NOTE: <br/>
    > References define entity services injected in our service classes. This helps to keep the database calls inside a single transaction. We need the Group services and Liferay Asset services for integrating to the Liferay Asset framework for later exercise steps. 

3. **Add** the following reference definitions after the _finder_ definitions:
	```xml
	<!-- Reference to Group entity service -->
	<reference entity="Group" package-path="com.liferay.portal"></reference>
	<!-- Entity services needed for the integration to Asset framework -->
	<reference entity="AssetEntry"
		package-path="com.liferay.portlet.asset"></reference>
	<reference entity="AssetLink"
		package-path="com.liferay.portlet.asset"></reference>
	<reference entity="AssetTag"
		package-path="com.liferay.portlet.asset"></reference>
	```

4. **Add** the following code snippet after the closing tag of _entity_:
	```xml
	<!-- Exceptions -->
	<exceptions>
		<exception>AssignmentValidation</exception>
	</exceptions>
	```

5. **Save** the _service.xml_ file.

<br />

<img src="images/definitions_added.png" style="max-width:60%;" />

Use Liferay Developer Studio's automatic code formatting to fix indents and spacing. The final `service.xml` should look like this:

#### Final Code Review

```xml
<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.3.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_3_0.dtd">

<service-builder dependency-injector="ds" package-path="com.liferay.training.gradebook">
	<namespace>Gradebook</namespace>
	<!--<entity data-source="sampleDataSource" local-service="true" name="Foo" remote-service="false" session-factory="sampleSessionFactory" table="foo" tx-manager="sampleTransactionManager uuid="true"">-->
	<entity name="Assignment" local-service="true">

		<!-- PK fields -->

		<column name="assignmentId" primary="true" type="long"></column>

		<!-- Group instance -->

		<column name="groupId" type="long"></column>

		<!-- Audit fields -->

		<column name="companyId" type="long"></column>
		<column name="userId" type="long"></column>
		<column name="userName" type="String"></column>
		<column name="createDate" type="Date"></column>
		<column name="modifiedDate" type="Date"></column>
		<column name="title" type="String"></column>
		<column name="description" type="String"></column>
		<column name="dueDate" type="Date"></column>

		<!-- Order -->

		<order by="asc">
			<order-column name="title" />
		</order>

		<!-- Finders -->

		<!-- Find by groupId -->

		<finder name="GroupId" return-type="Collection">
			<finder-column name="groupId"></finder-column>
		</finder>

		<!-- Reference to Group entity service -->

		<reference entity="Group" package-path="com.liferay.portal"></reference>

		<!-- Entity services needed for the integration to Asset framework -->

		<reference entity="AssetEntry"
			package-path="com.liferay.portlet.asset"></reference>
		<reference entity="AssetLink"
			package-path="com.liferay.portlet.asset"></reference>
		<reference entity="AssetTag"
			package-path="com.liferay.portlet.asset"></reference>
	</entity>

	<!-- Exceptions -->

	<exceptions>
		<exception>AssignmentValidation</exception>
	</exceptions>
</service-builder>
```
When you run the `buildService` Gradle task, the following items are generated:

- Database schema for the service (committed at module deploy time)
- Persistence and caching
- Local and remote service APIs

> NOTE: <br/>
    > Remember that you have to rebuild services whenever you edit the `service.xml`.

<div class="page"></div>

#### Build the Service
1. **Expand** the `gradebook-workspace/modules/gradebook/build` in the _Gradle Tasks_ panel.
2. **Run** the `buildService` task.
	* Note that you can run the service generation task several different ways:
 		* The _Gradle Tasks_ panel using the project's `buildService` task 
		* The _Overview_ panel of the `service.xml` designer
 		* The _Context_ menu of the gradebook-service project
 		* The _Command Line_ using the Liferay Workspace `gradlew` script
3. **Configure** and _save_ the dependencies in the gradebook-service module's `build.gradle` file as follows: 

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "release.dxp.api"
	compileOnly project(":modules:gradebook:gradebook-api")
}

buildService {
	apiDir = "../gradebook-api/src/main/java"
}

group = "com.liferay.training.gradebook"

tasks.withType(JavaCompile) {

	// Generated classes using Jodd library are unable to be read when compiled against JDK 11

	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}
```
<br />

<img src="images/dependencies_configured.png" style="max-width:100%;" />

