# Understanding and Extending Generated Classes

Service Builder generates both [tables for your entity](./generating-model-persistence-and-service-code.md) and model, persistence, and service classes for it. Here you'll examine generated classes for an entity called `W9B7Entry`. Then you'll extend the local service with a new method and invoke it.

Get started by downloading and unzipping the example project:

```bash
curl https://learn.liferay.com/dxp/latest/en/building-applications/data-frameworks/service-builder/service-builder-basics/liferay-w9b7.zip -O
```

```bash
unzip liferay-w9b7.zip
```

Examine the `w9b7-service/service.xml` file:

```{literalinclude} ./understanding-service-builder-generated-classes/resources/liferay-w9b7.zip/w9b7-service/service.xml
:language: xml
```

The `package-path="com.acme.w9b7"` setting specifies that classes are generated to the `com/acme/w9b7` package path in the `-api` module and `-service` module. The only entity is called `W9B7Entry`. It has local services (services accessible from the same JVM as DXP/Portal) but no remote services.

Check out the generated classes.

## Generated Classes Listing

Each module's `com/acme/w9b7` package folder contains the Service Builder-generated Java classes. Here are the module classes as they appear in the file structure:

```
w9b7-api/src/main/java/com/acme/w9b7
├── exception
│   └── NoSuchW9B7EntryException.java
├── model
│   ├── W9B7Entry.java
│   ├── W9B7EntryModel.java
│   ├── W9B7EntrySoap.java
│   ├── W9B7EntryTable.java
│   └── W9B7EntryWrapper.java
└── service
    ├── persistence
    │   ├── W9B7EntryPersistence.java
    │   └── W9B7EntryUtil.java
    ├── W9B7EntryLocalService.java
    ├── W9B7EntryLocalServiceUtil.java
    └── W9B7EntryLocalServiceWrapper.java

w9b7-service/src/main/java/com/acme/w9b7
├── model
│   └── impl
│       ├── W9B7EntryBaseImpl.java
│       ├── W9B7EntryCacheModel.java
│       ├── W9B7EntryImpl.java // MODIFIABLE
│       └── W9B7EntryModelImpl.java
└── service
    ├── base
    │   └── W9B7EntryLocalServiceBaseImpl.java
    ├── impl
    │   └── W9B7EntryLocalServiceImpl.java // MODIFIABLE
    └── persistence
        └── impl
            ├── constants
            │   └── W9B7EntryPersistenceConstants.java
            ├── W9B7EntryModelArgumentsResolver.java
            └── W9B7EntryPersistenceImpl.java
```

The `W9B7EntryImpl.java` and `W9B7EntryLocalServiceImpl.java` classes are the only classes you can modify. Don't modify the other generated classes---Service Builder regenerates their content every time Service Builder runs.

```{note}
If you run Service Builder with remote services enabled (i.e., `remote-service="true"`) for an entity, Service Builder generates remote service classes, including a modifiable remote service implementation class (e.g., `W9B7EntryServiceImpl.java`). See *Remote Services* (coming soon).
```

All the classes are described, starting with the API classes.

## API Classes

The API classes define the public interface, utilities, and constants.

| API Class | Description |
| :-------- | :---------- |
| `W9B7Entry` | `W9B7Entry` model interface; extends `W9B7EntryModel`. |
| `W9B7EntryModel` | Base model interface. This interface and its `W9B7EntryModelImpl` implementation serve only as containers for the default property accessors Service Builder generates. Any helper methods and all application logic should be added to `W9B7EntryImpl`. |
| `W9B7EntrySoap` | SOAP model, similar to `W9B7EntryModelImpl`. `W9B7EntrySoap` is serializable; it does not implement `W9B7Entry`. |
| `W9B7EntryTable` | Represents the entity's table. |
| `W9B7EntryWrapper` | Wrapper, wraps `W9B7Entry`. This class is there to be extended to [customize the entity](../../../../liferay-internals/extending-liferay/creating-service-wrappers.md). |
| `W9B7EntryPersistence` | Persistence interface that defines CRUD methods for the entity such as `create`, `remove`, `countAll`, `find`, `findAll`, etc. |
| `W9B7EntryUtil` | Persistence utility class that wraps `W9B7EntryPersistenceImpl` and provides direct access to the database for CRUD operations. This utility should only be used by the service layer; in your portlet classes, use the `W9B7Entry` class by injecting it with the [`@Reference` annotation](../../../../liferay-internals/fundamentals/using-an-osgi-service.md). |
| `W9B7EntryLocalService` | Local service interface. |
| `W9B7EntryLocalServiceUtil` | Local service utility class which wraps `W9B7EntryLocalServiceImpl`. |
| `W9B7EntryLocalServiceWrapper` | Local service wrapper which implements `W9B7EntryLocalService`. This class is there to be extended to [customize the entity's local services](../../../../liferay-internals/extending-liferay/creating-service-wrappers.md). |

## Implementation Classes

These classes implement the model, persistence, and service layer.

| Implementation Class | Description |
| :------------------- | :---------- |
| `W9B7EntryBaseImpl` | Extends the `W9B7EntryModelImpl` to represents a row in the `W9B7_W9B7Entry` database table, with each column mapped to a `W9B7EntryModel` property. |
| `W9B7EntryCacheModel` | Represents the `W9B7Entry` entity in cache. |
| `W9B7EntryImpl` (**MODIFIABLE**) | Model implementation. You can use this class to add helper methods and application logic to your model. If you don't add any helper methods or application logic, only the auto-generated field getters and setters are available. Whenever you add or modify methods in this class, Service Builder propagates the changes to the `W9B7Entry` interface the next time you run it. |
| `W9B7EntryLocalServiceBaseImpl` | Local service base implementation. This is an abstract class. Service Builder injects a number of instances of various service and persistence classes into this class. |
| `W9B7EntryLocalServiceImpl` (**MODIFIABLE**) | Local service implementation. This is the only class in the local service that you should modify. It's where you add your business logic. For any methods added or modified here, Service Builder propagates the changes to the `W9B7EntryLocalService` interface the next time you run it. |
| `W9B7EntryModelArgumentsResolver` | Processes parameters for retrieving entity values. |
| `W9B7EntryModelImpl` | Base model implementation. |
| `W9B7EntryPersistenceImpl` | Persistence implementation class that implements `W9B7EntryPersistence`. |

The `*BaseImpl` abstract classes are rich with implementation. The `W9B7EntryImpl` and `W9B7EntryLocalServiceImpl` classes extend them and provide a way to add functionality.

## Adding a Local Service Method

Extend the local service by adding a convenience method for creating a `W9B7Entry` instance from given a name and description.

1. Add the following convenience method to your `W9B7EntryLocalServiceImpl` class.

    ```java
	public W9B7Entry addW9B7Entry(String description, String name)
		throws PortalException {

		W9B7Entry w9b7Entry = w9b7EntryPersistence.create(
			counterLocalService.increment());

		w9b7Entry.setDescription(description);
		w9b7Entry.setName(name);

		return w9b7EntryPersistence.update(w9b7Entry);
	}
    ```

    It uses the base class' `w9b7EntryPersistence` field and `counterLocalService` field to create a `W9B7Entry` instance with an ID generated by a counter service. The description and name are assigned to the entry and then the entry is persisted via the `w9b7EntryPersistence.update` method call.

    ```{note}
    Service Builder-generated base classes, such as `W9B7EntryLocalServiceBaseImpl.java`, provide helpful fields and methods for using in an application.
    ```

1. Run Service Builder.

    ```bash
    cd w9b7-service
    ```

    ```bash
    ../gradlew buildService
    ```

    Output:

    ```
    > Task :w9b7-service:buildService
    Building W9B7Entry
    Writing ../w9b7-api/src/main/java/com/acme/w9b7/model/W9B7EntryModel.java
    Writing ../w9b7-api/src/main/java/com/acme/w9b7/service/W9B7EntryLocalService.java
    Writing ../w9b7-api/src/main/java/com/acme/w9b7/service/W9B7EntryLocalServiceUtil.java
    Writing ../w9b7-api/src/main/java/com/acme/w9b7/service/W9B7EntryLocalServiceWrapper.java
    Writing src/main/resources/service.properties
    ```

   Service Builder updated the local service API to support the new local service method implementation.

1. Check for the new method signature in the `w9b7-api` module's `W9B7EntryLocalService` class. 

    ```java
    public W9B7Entry addW9B7Entry(String description, String name) throws PortalException;
    ```

The new method is available to publish with your modules.

## Testing the New Service Method

It's time to deploy your modules and test your new service.

```{include} /_snippets/run-liferay-portal.md
```

Then, follow these steps:

1. Build and deploy the example.

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    This command is the same as copying module JARs to `/opt/liferay/osgi/modules` on the Docker container.
    ```

1. Confirm the deployment in the Docker container console.

    ```bash
    STARTED com.acme.w9b7.api_1.0.0
    STARTED com.acme.w9b7.service_1.0.0
    ```

1. Navigate to the Script console at Control Panel → Server Administration → Script.

1. Add an entry via your new method by executing the following script.

    ```groovy
    import com.acme.w9b7.service.W9B7EntryLocalServiceUtil;

    W9B7EntryLocalServiceUtil.addW9B7Entry("Remove clutter from your desk.", "Straighten Desk");

    w9b7Entries = W9B7EntryLocalServiceUtil.getW9B7Entries(-1, -1);

    for (w9b7Entry in w9b7Entries){
        out.println(w9b7Entry);
    }
    ```

    Output:

    ```
    {w9b7EntryId=1234, description=Remove clutter from your desk., name=Straighten Desk}
    ```

    The entry is printed in JSON format.

Congratulations! You've successfully added a new service method.

## What's Next

Now that you understand the Service Builder generated classes and how to add a local service method, you can learn how to [invoke a service from a portlet](./invoking-a-service-locally.md).
