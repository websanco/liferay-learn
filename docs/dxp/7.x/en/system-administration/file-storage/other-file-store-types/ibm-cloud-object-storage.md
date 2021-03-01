# IBM Cloud Object Storage

Liferay DXP implements IBM’s [Cloud Object Storage](https://cloud.ibm.com/docs/cloud-object-storage?topic=cloud-object-storage-getting-started-cloud-object-storage) store with the S3 compatibility layer so that it uses the same configuration as the AWS S3 store. Once you've set up your IBM account and created a bucket, you're ready to configure your instance's S3 Store.

## Configuring the Store

1. Open the *Control Panel* tab in the Global Menu and go to *System Settings* &rarr; *File Storage* &rarr; *S3 Store*.

1. At minimum, fill in the following configuration values:

   * **Bucket Name**: Set the bucket name used by S3 to store files.
   * **Access Key**: Set the account access key.
   * **Secret Key**: Set the account secret key.
   * **S3 Endpoint**: Set the default endpoint to use to connect to S3.
   * **S3 Region**: Set the S3 Region, which represents a geographical region where S3 stores the user-created buckets.

   ```important::
      Currently, the store only supports *HMAC credentials*. See official `IBM Cloud Object Storage documentation <https://cloud.ibm.com/docs/cloud-object-storage?topic=cloud-object-storage-uhc-hmac-credentials-main>`_ for more information.
   ```

1. Click *Save* when finished.

Once you have the System Settings configuration in place, you must set the IBM Cloud Object Storage store as default. To do this, set the following property in the `portal-ext.properties` file:

```properties
dl.store.impl=com.liferay.portal.store.s3.IBMS3Store
```

## Using the Store in a Clustered Environment

To use the IBM Cloud Object Storage store in a clustered environment, the configuration on all nodes must be identical. In particular, ensure the `portal-ext.properties` file and the S3 Store settings in System Settings are the same.

## Database Rollback Limitation

If a database transaction rollback occurs in a Document Library, the transaction's file system changes are not reversed. Inconsistencies between Document Library files and those in the file system store can occur and may require manual synchronization. All DXP stores except DBStore are vulnerable to this limitation.

Please consult official IBM Cloud Object Storage documentation for additional details on using IBM's service.

## Additional Information

* [Configuring File Storage](../configuring-file-storage.md)
* [Amazon S3 Store](./amazon-s3-store.md)
* [DBStore](./dbstore.md)
