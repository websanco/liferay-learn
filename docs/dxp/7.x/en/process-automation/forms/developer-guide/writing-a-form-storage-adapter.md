# Writing a Form Storage Adapter

You can store form records in alternate storage formats, or inject custom logic into the lifecycle of a form record persistence event, by implementing a

```java
com.liferay.dynamic.data.mapping.storage.DDMStorageAdapter
```

![Use a DDM Storage Adapter to add a Storage Type to the Forms application.](./writing-a-form-storage-adapter/images/01.png)

The example storage adapter in this tutorial provides the [default storage adapter's](https://github.com/liferay/liferay-portal/blob/7.3.1-ga2/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/storage/DDMJSONStorageAdapter.java) logic, to save form records in the Liferay DXP database as JSON content, but also adds logic to store each Form Record in a simple file on the file system.

## Examine a Running DDM Storage Adapter

To get the example storage adapter up and running,

1. Start Liferay DXP. If you don't already have a docker container, use

    ```bash
    docker run -it -p 8080:8080 --name lrdev liferay/portal:7.3.1-ga2
    ```

    If you already have a docker container, use

    ```bash 
    docker start -a [container_name] 
    ```

1. Download and unzip [the DDM File System Storage Adapter project](./liferay-r2f1.zip).

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

    >**Note:** This command is the same as copying the deployed jars to /opt/liferay/osgi/modules on the Docker container.

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

1. To verify the form record files were written in the container, execute a `find` and `cat` via `docker exec` to print the file contents to the command line:

   ```bash
   docker exec -it lrdev find /opt/liferay/form-records -type f -exec cat {} +
   ```

    The JSON for each form record file is printed to the command line:

    ```json
    {"availableLanguageIds":["en_US"],"defaultLanguageId":"en_US","fieldValues":[{"instanceId":"d0bVzHXG","name":"PetsFavoriteWine","value":{"en_US":"white zinfandel"}}]}
    ```

## Understanding the DDM Storage Adapter

The deployed example contains just one class: `DDMFileSystemStorageAdapter`, a service implementing a `DDMStorageAdapter` to provide logic for storing Form Entries on the File System.

* [Annotate the Adapter Class for OSGi Registration](#annotate-the-adapter-class-for-osgi-registration)
* [Review the `DDMStorageAdapter` Interface](#review-the-ddm-storage-adapter-interface)
* [Understand the Storage Adapter Calling Context](#understand-the-storage-adapter-calling-context)

### Annotate the Contributor Class for OSGi Registration

The `DDMFileSystemStorageAdapter` implements the `DDMStorageAdapter` interface:

```java
@Component(
	immediate = true, property = "ddm.storage.adapter.type=file",
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

### Understand the Storage Adapter Calling Context

The Forms application's service code looks up the appropriate storage adapter, and calls its storage logic against the `DDMFormInstanceRecord` being handled.

From `DDMFormInstanceRecordLocalServiceImpl`:

```java
DDMStorageAdapter ddmStorageAdapter = getDDMStorageAdapter();

DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
    ddmStorageAdapter.save(ddmStorageAdapterSaveRequest);
```

### Review the DDM Storage Adapter Interface

To create your own storage adapter you must implement three methods: `delete`, `get`, and `save`.

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

Each method of the storage adapter is passed a very useful `DDMStorageAdapter[Save/Delete/Get]Request`. The request objects contain getter methods that return useful contextual information. 

<!-- link to javadoc when available -->

## Exploring the File System DDM Storage Adapter Implementation

### Implement the `delete` Method

Put form record deletion logic in the `delete` method.

The `delete` method takes a `DDMStorageAdapterDeleteRequest`. The interface demands that you return a `DDMStorageAdapterDeleteResponse` and handle `StorageException`s.

The default storage adapter's implementation:

```java
@Override
public DDMStorageAdapterDeleteResponse delete(
        DDMStorageAdapterDeleteRequest ddmStorageAdapterDeleteRequest)
    throws StorageException {

    long fileId = ddmStorageAdapterDeleteRequest.getPrimaryKey();

    deleteFile(fileId);

    try {
        ddmContentLocalService.deleteDDMContent(
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

The first thing to note is the call to the `deleteFile` utility method:

```java
private void deleteFile(long fileId) {
    File file = new File(_PATHNAME + "/" + fileId);

    file.delete();
}
```

After dealing with the file storage scenario, this code does the same thing the default JSON storage adapter does: delete the `DDMContent` with the ID returned by the delete request's `getPrimaryKey` method. After that a delete response object is built and returned.

Since there's no serialization or deserialization involved with deletion you can use the default storage adapter's basic deletion code in most cases, unless you require some custom deletion logic for your use case.

### Implement the `get` Method

Put form record retrieval logic in the `get` method.

The `get` method takes a `DDMStorageAdapterGetRequest`. The interface demands that you return a `DDMStorageAdapterGetResponse` and handle `StorageException`s.

Here we set the `storageId` (retrieved by `ddmStorageAdapterGetRequest.getPrimaryKey()`) as the `fileId` and call a `getFile` utility method which prints the retrieved content to the Liferay log. After that, we keep the default JSON implementation: get the DDM content using the storage ID of the form record, retrieved from the request object, then construct the `DDMFormValues` object from the serialized data you've already stored, by calling the deserialization code. Set the now-deserialized `DDMFormValues` into the response object. Of course, if you need to add custom logic in the meantime, do that too.

```java
public DDMStorageAdapterGetResponse get(
        DDMStorageAdapterGetRequest ddmStorageAdapterGetRequest)
    throws StorageException {

    try {
        long fileId = ddmStorageAdapterGetRequest.getPrimaryKey();

        getFile(fileId);

        DDMContent ddmContent = ddmContentLocalService.getContent(
            ddmStorageAdapterGetRequest.getPrimaryKey());

        DDMFormValues ddmFormValues = deserialize(
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

Here's the `getFile` utility method:

```java
private void getFile(long fileId) throws IOException {
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

To do the deserialization of the `DDMContent` you can use a `deserialize` utility method. This is what it looks like for the default storage adapter.

```java
private DDMFormValues deserialize(String content, DDMForm ddmForm) {
    DDMFormValuesDeserializerDeserializeRequest.Builder builder =
        DDMFormValuesDeserializerDeserializeRequest.Builder.newBuilder(
            content, ddmForm);

    DDMFormValuesDeserializerDeserializeResponse
        ddmFormValuesDeserializerDeserializeResponse =
            jsonDDMFormValuesDeserializer.deserialize(builder.build());

    return ddmFormValuesDeserializerDeserializeResponse.getDDMFormValues();
}
```

It calls the `DDMFormValuesDeserializer` for this. Liferay DXP provides a [JSON deserializer](https://github.com/liferay/liferay-portal/blob/7.3.1-ga2/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONDeserializer.java). If you need different deserialization logic for your storage adapter, you'll need to provide your own deserialization code. As you might imagine, you'll also need corresponding serialization logic for saving a Form Record into your preferred storage format. There's a [JSON implementation](https://github.com/liferay/liferay-portal/blob/7.3.1-ga2/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONSerializer.java) of the `DDMFormValuesSerializer` included in Liferay DXP.

### Implement the `save` Method

Because Form Records are autosaved to avoid data loss, each save request will be one of two types: a new record is being added or an existing record is being updated.

The `save` method takes a `DDMStorageAdapterSaveRequest`. The interface demands that you return a `DDMStorageAdapterSaveResponse` and handle `StorageException`s.

The default JSON implementation responds differently depending on the value of a boolean value stored in the save request, `isInsert`. If true, logic for adding a new form record is invoked, and if false, an update is precipitated instead. This logic is contained in two the methods `insert` and `update`. The File System example preserves this logic and adds calls to its `saveFile` utility method for writing a file with the `storageId` as the filename and the `DDMContent` (in JSON) as the file contents.

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

private DDMStorageAdapterSaveResponse insert(
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

        DDMContent ddmContent = ddmContentLocalService.addContent(
            ddmStorageAdapterSaveRequest.getUserId(),
            ddmStorageAdapterSaveRequest.getScopeGroupId(),
            ddmStorageAdapterSaveRequest.getClassName(), null,
            serialize(ddmFormValues), serviceContext);

        DDMStorageAdapterSaveResponse.Builder builder =
            DDMStorageAdapterSaveResponse.Builder.newBuilder(
                ddmContent.getPrimaryKey());

        DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
            builder.build();


        long fileId = ddmStorageAdapterSaveResponse.getPrimaryKey();

        saveFile(fileId, ddmFormValues);

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
        DDMContent ddmContent = ddmContentLocalService.getContent(
            ddmStorageAdapterSaveRequest.getPrimaryKey());

        ddmContent.setModifiedDate(new Date());

        ddmContent.setData(
            serialize(ddmStorageAdapterSaveRequest.getDDMFormValues()));

        ddmContentLocalService.updateContent(
            ddmContent.getPrimaryKey(), ddmContent.getName(),
            ddmContent.getDescription(), ddmContent.getData(),
            new ServiceContext());

        DDMStorageAdapterSaveResponse.Builder builder =
            DDMStorageAdapterSaveResponse.Builder.newBuilder(
                ddmContent.getPrimaryKey());

        DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
            builder.build();

        long fileId = ddmStorageAdapterSaveResponse.getPrimaryKey();

        saveFile(fileId, ddmFormValues);

        return ddmStorageAdapterSaveResponse;
    }
    catch (Exception e) {
        throw new StorageException(e);
    }
}

private void saveFile(long fileId, DDMFormValues formValues)
    throws IOException {

    try {
        String serializedDDMFormValues = serialize(formValues);

        File abstractFile = new File(String.valueOf(fileId));

        FileUtil.write(
            _PATHNAME, abstractFile.getName(), serializedDDMFormValues);
    }
    catch (IOException e) {
        throw new IOException(e);
    }
}

private String serialize(DDMFormValues ddmFormValues) {
    DDMFormValuesSerializerSerializeRequest.Builder builder =
        DDMFormValuesSerializerSerializeRequest.Builder.newBuilder(
            ddmFormValues);

    DDMFormValuesSerializerSerializeResponse
        ddmFormValuesSerializerSerializeResponse =
            jsonDDMFormValuesSerializer.serialize(builder.build());

    return ddmFormValuesSerializerSerializeResponse.getContent();
}
```

The `serialize` method returns a String of the serialized form record content (JSON, for this case).

### Declare the Service Dependencies

This code relies on several services deployed to an OSGi container:

`DDMContentLocalService`, a `DDMFormValuesSerializer` implementation, and a `DDMFormValuesDeserializer`implementation. Declare your need for them using the Declarative Services `@Reference` annotation, provided by `org.osgi.service.component.annotations.Reference`. Set them into private fields.

```java
@Reference
private DDMContentLocalService ddmContentLocalService;

@Reference
private DDMFormValuesDeserializerTracker ddmFormValuesDeserializerTracker;

@Reference
private DDMFormValuesSerializerTracker ddmFormValuesSerializerTracker;
```

## Additional Information

When a User adds a form record, the Forms API routes the processing of the request through a storage adapter API. The same is true for the other *CRUD* operations performed on form entries (read, update, and delete operations). The default implementation of the storage service is called `DDMJSONStorageAdapter`, and as its name implies, it implements the `DDMStorageAdapter` interface to provide JSON storage of form entry data.

The Dynamic Data Mapping (DDM) backend can *adapt* to other data storage formats for form records. Want to store your data in XML? YAML? No problem. Because the storage API is separated from the regular service calls used to populate the database table for form entries, you can even choose to store form data outside the Liferay database.

A newly added storage adapter can only be used with new Forms. All existing Forms continue to use the adapter selected (JSON by default) at the time of their creation, and a different storage adapter cannot be selected.

### Serializing and Deserializing Form Records

The example here uses the logic for serializing and deserializing the form record's content to and from JSON, and shows storing the Form Records' content in an additional location: on the file system. Implementing your own serialization code to store `DDMContent` in a different format requires implementing some additional interfaces and more complete documentation for these extension points will be provided in the future to help round out this usage.

**Serialization:** When you're saving `DDMcontent`, you need to transform the `DDMFormValues` object and transform it into the target storage format.

**Deserialization:** When reading `DDMContent` (in the `get` method if the storage adapter), it must be transformed from the storage format back into a `DDMFormValues` object that the `DDMStorageAdapterGetResponse`'s `Builder` constructor requires.

There's a `DDMFormValuesSerializer` interface to implement for this serialization logic, and a `DDMFormValuesDeserializer` interface to implement for the deserialization logic. Liferay's default implementations, which support transforming the `DDMFormValues` object to JSON and vice versa, can be found [here](https://github.com/liferay/liferay-portal/blob/7.3.1-ga2/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONSerializer.java) and [here](https://github.com/liferay/liferay-portal/blob/7.3.1-ga2/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONDeserializer.java), respectively.

## Conclusion

By implementing a `DDMStorageAdapter`, you can save forms records in any storage format you want.

