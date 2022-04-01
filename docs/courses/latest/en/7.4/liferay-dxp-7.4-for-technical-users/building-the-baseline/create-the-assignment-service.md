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
1. **Create** a new service builder project in the gradebook-workspace with the following parameters:
	* Use `gradebook` for the _Project Name_.
	* Use _service-builder_ for the _Project Template Name_.
	* Use  `com.liferay.training.gradebook` for the _Package Name_.

Feel free to use whatever method you are most comfortable with to generate this project. If you are using Blade CLI, use the command `blade create -t service-builder -p com.liferay.training.gradebook gradebook` in the root folder of your workspace. 


`service.xml` is the main configuration file of a Service Builder project. It lets you define model entities, data sources, finder methods, and exceptions for your service. You can customize `service.xml` with a graphical designer tool or edit the file's source code directly. 

#### Define the Assignment Entity
1. **Go to** `modules > gradebook > gradebook-service` in your preferred IDE.
2. **Open** the `service.xml` file.
3. **Replace** the contents of the file with the following:
	```xml
	<?xml version="1.0"?>
	<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.4.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_4_0.dtd">
	<service-builder dependency-injector="ds" package-path="com.liferay.training.gradebook">
		<namespace>Gradebook</namespace>
		<!--<entity data-source="sampleDataSource" local-service="true" name="Foo" remote-service="false" session-factory="sampleSessionFactory" table="foo" tx-manager="sampleTransactionManager uuid="true"">-->
	</service-builder>
	```


#### Create the Assignment Entity 
1. **Add** the following entity just before the closing `service-builder` tag:
	```xml
	<entity name="Assignment" local-service="true"></entity>
	```

This creates an entity named _Assignment_ and enables both the local and remote services.

#### Define Assignment Columns
1. **Add** a column called _title_ to the _Assignment_ entity. Your entity will look like this:
	```xml
	<entity name="Assignment" local-service="true">
        <column name="title" type="String"></column>
    </entity>
    ```

2. **Add** columns for _description_ and _dueDate_ to the _Assignment_ entity. Your entity will now look like this:

	```xml
	<entity name="Assignment" local-service="true">
        <column name="title" type="String"></column>
        <column name="description" type="String"></column>
		<column name="dueDate" type="Date"></column>
    </entity>
    ```


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

The final `service.xml` should look like this:

#### Final Code Review

```xml
<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.4.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_4_0.dtd">

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
1. **Run** the `buildService` task in the `gradebook-workspace/modules/gradebook/build` directory.
	* Note that you can run the service generation task several different ways:
 		* The _Gradle Tasks_ panel using the project's `buildService` task 
		* The _Overview_ panel of the `service.xml` designer
 		* The _Context_ menu of the gradebook-service project
 		* The _Command Line_ using the Liferay Workspace `gradlew` script
 	* If you encounter issues with `buildService`, you may need to run it from `gradebook-workspace/modules/gradebook/gradebook-service/build`.
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


