# Understanding Form Storage Adapters

When a User adds a form record, the Forms API routes the processing of the request through a storage adapter API. The same is true for the other *CRUD* operations performed on form entries (read, update, and delete operations). The default implementation of the storage service is called `DDMJSONStorageAdapter`, and as its name implies, it implements the `DDMStorageAdapter` interface to provide JSON storage of form entry data.

The Dynamic Data Mapping (DDM) backend can *adapt* to other data storage formats for form records. Want to store your data in XML? YAML? No problem. Because the storage API is separated from the regular service calls used to populate the database table for form entries, you can even choose to store form data outside the Liferay database.

```important::
   A newly added storage adapter can only be used with new Forms. All existing Forms continue to use the adapter selected (JSON by default) at the time of their creation, and a different storage adapter cannot be selected.
```

## Saving Form Records

The default JSON implementation responds differently depending on the value of a boolean value stored in the save request, `isInsert`. If true, logic for adding a new form record is invoked, and if false, an update is precipitated instead. This logic is contained in two the methods `insert` and `update`. Make sure your implementation of the `DDMJSONStorageAdapter` accounts for this paradigm as well.

## Serializing and Deserializing Form Records

The default logic for serializing and deserializing a form record's content transforms the Java Object's to and from JSON. If the JSON serialization logic works for your use case, use it, as demonstrated in the [example project](./writing-a-form-storage-adapter.md). Implementing your own serialization code to store `DDMContent` in a different format requires implementing some additional interfaces and more complete documentation for these extension points will be provided in the future to help round out this usage.

**Serialization:** When you're saving `DDMcontent`, you need to transform the `DDMFormValues` object and transform it into the target storage format.

**Deserialization:** When reading `DDMContent` (in the `get` method if the storage adapter), it must be transformed from the storage format back into a `DDMFormValues` object that the `DDMStorageAdapterGetResponse`'s `Builder` constructor requires.

There's a `DDMFormValuesSerializer` interface to implement for this serialization logic, and a `DDMFormValuesDeserializer` interface to implement for the deserialization logic. Liferay's default implementations, which support transforming the `DDMFormValues` object to JSON and vice versa, can be found [here](https://github.com/liferay/liferay-portal/blob/7.3.1-ga2/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONSerializer.java) and [here](https://github.com/liferay/liferay-portal/blob/7.3.1-ga2/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONDeserializer.java), respectively.

For a complete example, see [Writing a Form Storage Adapter](./writing-a-form-storage-adapter.md).
