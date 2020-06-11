# Writing a Form Storage Adapter

By default, forms are stored in Liferay DXP's database. You can store form records in alternate storage formats or inject custom logic into the lifecycle of a form record persistence event by implementing a `com.liferay.dynamic.data.mapping.storage.DDMStorageAdapter`.

![Use a DDM Storage Adapter to add a Storage Type to the Forms application.](./writing-a-form-storage-adapter/images/01.png)

This example storage adapter starts by duplicating the [default storage adapter's](https://github.com/liferay/liferay-portal/blob/7.3.1-ga2/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/storage/DDMJSONStorageAdapter.java) logic, to save form records in the Liferay DXP database as JSON content. Later you'll add add logic to store each Form Record on the file system.

## Examine a Running DDM Storage Adapter

To get the example storage adapter up and running,

1. Start Liferay DXP. If you don't already have a docker container, use

    ```bash
    docker run -it -p 8080:8080 --name lrdev liferay/portal:7.3.2-ga3
    ```

    If you already have a docker container, use

    ```bash 
    docker start -i [container_name] 
    ```

1. Download and unzip [the DDM File System Storage Adapter project](./writing-a-form-storage-adapter/liferay-r2f1.zip).

    ```bash
    curl https://learn.liferay.com/dxp-7.x/process-automation/forms/developer-guide/writing-a-form-storage-adapter/liferay-r2f1.zip -O
    ```

    ```bash
    unzip liferay-r2f1.zip
    ```

1. From the module root, build and deploy.

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```
    ```note::
    This command is the same as copying the deployed jars to /opt/liferay/osgi/modules on the Docker container.
    ```

1. Confirm the deployment in the Liferay Docker container console.

    ```bash
    STARTED com.liferay.learn.r2f1.impl_1.0.0 [1009]
    ```

1. Verify that the example storage adapter is working. Begin by opening your browser to `https://localhost:8080`

1. Go to the Forms application in _Site Menu_ &rarr; _Content_ &rarr; _Forms_.

1. Click the *Add* button ![Add](./../../../images/icon-add.png) to open the Form Builder.

1. In the Form Builder view, click the *Options* button (![Options](./../../../images/icon-options.png)) and open the *Settings* window. 

1. From the select list field called *Select a Storage Type*, choose the File System type and click _Done_.

1. Add a [Text Field](./../user-guide/creating-forms.md) to the form, publish the form, and submit it a couple times.

1. To verify the form records were persisted, go to the Form's Records:

   From _Site Menu_ &rarr; _Content_ &rarr; _Forms_, click the Form's *Actions* button (![Actions](./../../../images/icon-actions.png)), then _View Entries_.

   ![Verify that the form entries were added.](./writing-a-form-storage-adapter/images/02.png)

## Understanding the Extension Point

The deployed example contains just one class: `DDMFileSystemStorageAdapter`, a service implementing a `DDMStorageAdapter` to provide logic for storing Form Entries. The current example holds logic identical to the default implementation used for all forms by default: `DDMJSONStorageAdapter`. Adding logic to this will extend the existing example to do something in addition to it. Here's how it works in its current state:

* [The Adapter Class is Annotated for OSGi Registration](#annotate-the-adapter-class-for-osgi-registration)
* [The `DDMStorageAdapter` Interface is Implemented](#review-the-ddm-storage-adapter-interface)
* [Understand the Storage Adapter Calling Context](#understand-the-storage-adapter-calling-context)

### The Adapter Class is Annotated for OSGi Registration

The `DDMFileSystemStorageAdapter` implements the `DDMStorageAdapter` interface:

```java
@Component(
	immediate = true, property = "ddm.storage.adapter.type=file-system",
	service = DDMStorageAdapter.class
)
public class DDMFileSystemStorageAdapter implements DDMStorageAdapter {
```

The `service` component property registers your implementation as a `DDMStorageAdapter` service.

The `property = "ddm.storage.adapter.type=file"` provides an identifier so that your service can be dynamically retrieved from a Service Tracker in the Forms service layer or specifically referenced as a unique `DDMStorageAdapter` implementation: 

```java
@Reference(target = "(ddm.storage.adapter.type=file)")
private DDMStorageAdapter fileSystemDDMStorageAdapter;
```

### Understand the DDMStorageAdapter Interface

#### Storage Adapter Calling Context

The Forms application's service code looks up the appropriate storage adapter and calls its storage logic against the `DDMFormInstanceRecord` being handled.

From `DDMFormInstanceRecordLocalServiceImpl`:

```java
DDMStorageAdapter ddmStorageAdapter = getDDMStorageAdapter();

DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
    ddmStorageAdapter.save(ddmStorageAdapterSaveRequest);
```

#### Review the DDM Storage Adapter Interface

To create your own storage adapter you must implement three methods to handle CRUD operations on form records: `delete`, `get`, and `save` (which also handles update logic).

```java
public DDMStorageAdapterDeleteResponse delete(
        DDMStorageAdapterDeleteRequest ddmStorageAdapterDeleteRequest)
    throws StorageException;
```

```java
public DDMStorageAdapterGetResponse get(
        DDMStorageAdapterGetRequest ddmStorageAdapterGetRequest)
    throws StorageException;
```

```java
public DDMStorageAdapterSaveResponse save(
        DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
    throws StorageException;
```

Each method must return a
_DDMStorageAdapter[[Save](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/storage/DDMStorageAdapterSaveResponse.java)/[Get](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/storage/DDMStorageAdapterGetResponse.java)/[Delete](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/storage/DDMStorageAdapterDeleteSaveResponse.java)]Response_
object, each constructed using a static inner `Builder` class's `newBuilder`
method. 

Each method of the storage adapter is passed a `DDMStorageAdapter[Save/Delete/Get]Request`. The request objects contain getter methods that return useful contextual information. 

### Declare the Service Dependencies

This code relies on several services deployed to an OSGi container:

`DDMContentLocalService`, a `DDMFormValuesSerializer` implementation, and a `DDMFormValuesDeserializer`implementation. Declare your need for them using the Declarative Services `@Reference` annotation, provided by `org.osgi.service.component.annotations.Reference`. Set them into private fields.

```java
@Reference
private DDMContentLocalService _ddmContentLocalService;

@Reference
private DDMFormValuesDeserializerTracker _ddmFormValuesDeserializerTracker;

@Reference
private DDMFormValuesSerializerTracker _ddmFormValuesSerializerTracker;
```


## Implementing File System Storage

The default storage adapter already overrides the three methods of the interface, and the file system storage is in addition to that logic.

### Implement File Deletion

Add a line to the beginning of the `delete` method that sets the `fileId` as the primary key returned by the delete request object:

```java
long fileId = ddmStorageAdapterDeleteRequest.getPrimaryKey();
```

Then outsource the file deletion logic to a utility method called `_deleteFile`, and pass it the file ID:

```java
_deleteFile(fileId);
```

Give `_deleteFile` these contents:

```java
private void _deleteFile(long fileId) {
    File file = new File(_PATHNAME + "/" + fileId);

    file.delete();
}
```

Set a private variable `_PATHNAME` so you can control where the files are stored. Recall that the example is deployed to the docker container, so that's where the files will be stored.

```java
private static final String _PATHNAME = "/opt/liferay/form-records";
```

The full delete method now looks like this:

```java
@Override
public DDMStorageAdapterDeleteResponse delete(
        DDMStorageAdapterDeleteRequest ddmStorageAdapterDeleteRequest)
    throws StorageException {

    long fileId = ddmStorageAdapterDeleteRequest.getPrimaryKey();

    _deleteFile(fileId);

    try {
        _ddmContentLocalService.deleteDDMContent(
            ddmStorageAdapterDeleteRequest.getPrimaryKey());

        DDMStorageAdapterDeleteResponse.Builder builder =
            DDMStorageAdapterDeleteResponse.Builder.newBuilder();

        return builder.build();
    }
    catch (Exception e) {
        throw new StorageException(e);
    }
}
```

After dealing with the file storage scenario, this code does the same thing the default JSON storage adapter does: delete the `DDMContent` with the ID returned by the delete request's `getPrimaryKey` method. After that a delete response object is built and returned.

Since there's no serialization or deserialization involved with deletion you can use the default storage adapter's basic deletion code in most cases, unless you require some custom deletion logic for your use case.

### Implement File Retrieval

At the very beginning of the `get` method, set the `storageId` (retrieved by `ddmStorageAdapterGetRequest.getPrimaryKey()`) as the `fileId` and call a `_getFile` utility method which prints the retrieved content to the Liferay log.

```java 
long fileId = ddmStorageAdapterGetRequest.getPrimaryKey();

_getFile(fileId);
```

Here's the `_getFile` utility method:

```java
private void _getFile(long fileId) throws IOException {
    try {
        System.out.println(
            "Reading the file named:" + fileId + "\n" +
                "The file contents: " +
                    FileUtil.read(_PATHNAME + "/" + fileId));
    }
    catch (IOException e) {
        throw new IOException(e);
    }
}
```

After that, we keep the default JSON implementation: get the DDM content using the storage ID of the form record, retrieved from the request object, then construct the `DDMFormValues` object from the serialized data you've already stored, by calling the deserialization code. Set the now-deserialized `DDMFormValues` into the response object. Of course, if you need to add custom logic in the meantime, do that too.

Here's the new method with the file retrieval call:

```java
public DDMStorageAdapterGetResponse get(
        DDMStorageAdapterGetRequest ddmStorageAdapterGetRequest)
    throws StorageException {

    try {
        long fileId = ddmStorageAdapterGetRequest.getPrimaryKey();

        _getFile(fileId);

        DDMContent ddmContent = _ddmContentLocalService.getContent(
            ddmStorageAdapterGetRequest.getPrimaryKey());

        DDMFormValues ddmFormValues = _deserialize(
            ddmContent.getData(), ddmStorageAdapterGetRequest.getDDMForm());

        DDMStorageAdapterGetResponse.Builder builder =
            DDMStorageAdapterGetResponse.Builder.newBuilder(ddmFormValues);

        return builder.build();
    }
    catch (Exception e) {
        throw new StorageException(e);
    }
}
```

### Implement File Creation Logic

The Forms application autosaves partially created forms by calling the `save` method. In addition, `save` is called when the user submits the form. Each save request can be one of two types: a new record is being added or an existing record is being updated.

For a File-backed form record, the `update` method can just overwrite an existing file each time a save is made, using the current `ddmFormValues` as the file contents.

Whether inserting a new form (see the `insert` method), you must retrieve the primary key from the `DDMStorageAdapterSaveResponse` object this time. The request object would return `0`, as the record and its primary key is not yet created in the database. The end of the `insert` method will now look like this:

```java
DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
    builder.build();

long fileId = ddmStorageAdapterSaveResponse.getPrimaryKey();

_saveFile(fileId, ddmFormValues);

return ddmStorageAdapterSaveResponse;
```

The same call to `_saveFile` is needed near the end of the `update` method, but the code has two small differences:

1. You must retrieve the `ddmFormValues` from the save request. This is already done in the existing logic of the `insert` method.
2. You don't need anything from the response object, so don't declare it explicitly.

```java
DDMFormValues ddmFormValues =
    ddmStorageAdapterSaveRequest.getDDMFormValues();

long fileId = ddmStorageAdapterSaveRequest.getPrimaryKey();

_saveFile(fileId, ddmFormValues);

return builder.build();
```

The `_saveFile` method looks like this:

```java
private void __saveFile(long fileId, DDMFormValues formValues)
    throws IOException {

    try {
        String serializedDDMFormValues = _serialize(formValues);

        File abstractFile = new File(String.valueOf(fileId));

        FileUtil.write(
            _PATHNAME, abstractFile.getName(), serializedDDMFormValues);
    }
    catch (IOException e) {
        throw new IOException(e);
    }
}
```
Here's the entirety of the save logic after adapting to the file system example:

```java
@Override
public DDMStorageAdapterSaveResponse save(
        DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
    throws StorageException {

    if (ddmStorageAdapterSaveRequest.isInsert()) {
        return insert(ddmStorageAdapterSaveRequest);
    }

    return update(ddmStorageAdapterSaveRequest);
}

private DDMStorageAdapterSaveResponse _insert(
        DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
    throws PortalException, StorageException {

    DDMFormValues ddmFormValues =
        ddmStorageAdapterSaveRequest.getDDMFormValues();

    try {
        ServiceContext serviceContext = new ServiceContext();

        serviceContext.setScopeGroupId(
            ddmStorageAdapterSaveRequest.getScopeGroupId());
        serviceContext.setUserId(ddmStorageAdapterSaveRequest.getUserId());
        serviceContext.setUuid(ddmStorageAdapterSaveRequest.getUuid());

        DDMContent ddmContent = _ddmContentLocalService.addContent(
            ddmStorageAdapterSaveRequest.getUserId(),
            ddmStorageAdapterSaveRequest.getScopeGroupId(),
            ddmStorageAdapterSaveRequest.getClassName(), null,
            _serialize(ddmFormValues), serviceContext);

        DDMStorageAdapterSaveResponse.Builder builder =
            DDMStorageAdapterSaveResponse.Builder.newBuilder(
                ddmContent.getPrimaryKey());

        DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
            builder.build();

        long fileId = ddmStorageAdapterSaveResponse.getPrimaryKey();

        _saveFile(fileId, ddmFormValues);

        return ddmStorageAdapterSaveResponse;
    }
    catch (Exception e) {
        throw new StorageException(e);
    }
}

private DDMStorageAdapterSaveResponse update(
        DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
    throws StorageException {

    DDMFormValues ddmFormValues =
        ddmStorageAdapterSaveRequest.getDDMFormValues();

    try {
        DDMContent ddmContent = _ddmContentLocalService.getContent(
            ddmStorageAdapterSaveRequest.getPrimaryKey());

        ddmContent.setModifiedDate(new Date());

        ddmContent.setData(
            _serialize(ddmStorageAdapterSaveRequest.getDDMFormValues()));

        _ddmContentLocalService.updateContent(
            ddmContent.getPrimaryKey(), ddmContent.getName(),
            ddmContent.getDescription(), ddmContent.getData(),
            new ServiceContext());

        DDMStorageAdapterSaveResponse.Builder builder =
            DDMStorageAdapterSaveResponse.Builder.newBuilder(
                ddmContent.getPrimaryKey());

        DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
            builder.build();

        DDMFormValues ddmFormValues =
            ddmStorageAdapterSaveRequest.getDDMFormValues();

        long fileId = ddmStorageAdapterSaveRequest.getPrimaryKey();

        _saveFile(fileId, ddmFormValues);

        return builder.build();
    }
    catch (Exception e) {
        throw new StorageException(e);
    }
}
```

The `_serialize` and `_deserialize` methods didn't change for this example. If you wanted to write a format other than JSON you would need to add custom serialization logic.

## Deploy and Test the Storage Adapter

Use the same `deploy` command as earlier to deploy the Storage Adapter. From the module root run

```bash
./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
```

Now verify that it's working:

1. Go to the Forms application in _Site Menu_ &rarr; _Content_ &rarr; _Forms_.

1. Click the *Add* button ![Add](./../../../images/icon-add.png) to open the Form Builder.

1. In the Form Builder view, click the *Options* button (![Options](./../../../images/icon-options.png)) and open the *Settings* window. 

1. From the select list field called *Select a Storage Type*, choose the File System type and click _Done_.

1. Add a [Text Field](./../user-guide/creating-forms.md) to the form, publish the form, and submit it a couple times.

1. To verify the form record files were written in the container, execute a `find` and `cat` via `docker exec` to print the file contents to the command line:

   ```bash
   docker exec -it lrdev find /opt/liferay/form-records -type f -exec cat {} +
   ```

   The JSON for each form record file is printed to the command line:

    ```json
    {"availableLanguageIds":["en_US"],"defaultLanguageId":"en_US","fieldValues":[{"instanceId":"d0bVzHXG","name":"PetsFavoriteWine","value":{"en_US":"white zinfandel"}}]}
    ```

## Conclusion

By implementing a `DDMStorageAdapter`, you can save forms records in any storage format you want.

