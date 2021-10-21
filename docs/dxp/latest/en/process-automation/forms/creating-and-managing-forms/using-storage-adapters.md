# Using Storage Adapters

When a User adds a form record, Liferay routes the processing of the request through a [storage adapter](../developer-guide/understanding-form-storage-adapters.md) API. The same is true for the other *CRUD* operations performed on form entries (read, update, and delete). The default implementation of the storage service translates the forms into JSON for storage in the Liferay database.

![The storage adapter for Liferay Forms is configurable.](./using-storage-adapters/images/01.png)

In addition to the default JSON storage adapter, there's an Object storage adapter, beginning in Liferay 7.4. If your system contains any [custom storage adapters](../developer-guide/writing-a-form-storage-adapter.md) there may be even more.

To see the storage adapters in your system,

1. Add a new form.
1. Click the ![Settings](../../../images/icon-settings.png) icon in the upper right.
1. In the General tab, expand the list options in the Select a Storage Type setting.

The Dynamic Data Mapping (DDM) back-end can *adapt* to other storage formats for form records. This makes it easy to serialize form data as XML or YAML (or whatever format you like) instead of the default JSON. You can then choose to store form data anywhere, including the Liferay database.

```{important}
You cannot change the storage adapter after a form has been saved. All existing forms continue to use the adapter configured at the time of their creation, and a different storage adapter cannot be selected.
```

| Storage Type | Description |
| ------------ | ----------- |
| Default      | Use the [`DefaultDDMStorageAdapter`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/storage/DefaultDDMStorageAdapter.java) to store the form's records in JSON in the Liferay Database. |
| JSON | (Deprecated) The [`JSONDDMStorageAdapter`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/storage/JSONDDMStorageAdapter.java)<br /> should not be used as it is for backwards compatibility only. It stores the form record data in JSON in the Liferay database. |
| Object       | Use the [`ObjectDDMStorageAdapter`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/object/object-dynamic-data-mapping/src/main/java/com/liferay/object/dynamic/data/mapping/internal/storage/ObjectDDMStorageAdapter.java) to map the form's records to a Liferay Object. After choosing this option you must select an existing Liferay Object. |
