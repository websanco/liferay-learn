# Understanding Form Storage Adapters

> Available: Liferay DXP 7.3 and Liferay DXP 7.2 versions that include the fix for [LPS-97208](https://issues.liferay.com/browse/LPS-97208) (planned for Liferay DXP 7.2 SP3).

When a User adds a form record, the Forms API routes the processing of the request through a storage adapter API. The same is true for the other *CRUD* operations performed on form entries (read, update, and delete). The default implementation of the storage service is called `JSONDDMStorageAdapter`, and as its name implies, it implements the `DDMStorageAdapter` interface to store form entry data in JSON format.

The Dynamic Data Mapping (DDM) back-end can *adapt* to other storage formats for form records. This makes it easy to serialize form data as XML or YAML (or whatever format you like) instead of the default JSON. You can then choose to store form data anywhere, including the Liferay database.

```important::
   A newly added storage adapter can only be used with new Forms. All existing Forms continue to use the adapter selected (JSON by default) at the time of their creation, and a different storage adapter cannot be selected.
```

## Saving Form Records

The default JSON implementation responds differently depending on the value of a boolean stored in the save request, `isInsert`. If true, logic for adding a new form record is invoked, and if false, an update happens instead. This logic is contained in the methods `insert` and `update`. Make sure your implementation of the `DDMStorageAdapter` accounts for this paradigm as well.

## Serializing and Deserializing Form Records

The default format for serializing and deserializing form records is JSON. The [example project](./writing-a-form-storage-adapter.md) demonstrates the use of this default format. Storing `DDMContent` in a different format requires implementing additional interfaces. 

**Serialization:** When you're saving `DDMcontent`, you must transform the `DDMFormValues` object it into the target storage format.

**Deserialization:** When reading `DDMContent` (in the `get` method of the storage adapter), it must be transformed from the storage format back into a `DDMFormValues` object that the `DDMStorageAdapterGetResponse`'s `Builder` constructor requires.

There's a `DDMFormValuesSerializer` interface to implement for this serialization logic and a `DDMFormValuesDeserializer` interface to implement for the deserialization logic. Liferay's default implementations, which support transforming the `DDMFormValues` object to JSON and vice versa, can be found [here](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONSerializer.java) and [here](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONDeserializer.java), respectively.

For a complete example, see [Writing a Form Storage Adapter](./writing-a-form-storage-adapter.md).
