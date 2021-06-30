# Downloading and Uploading Backups

The DXP Cloud backup service creates backups of an environment's database and the full contents of the `LIFERAY_HOME/data` folder. This content is stored as archive files (`.gz` and `.tgz` respectively) and can be downloaded via the DXP Cloud console.

Users can also download or upload environment backups [using the DXP Cloud Console](#uploading-backups-via-the-console), or through [Backup APIs](#backup-service-apis).

```note::
   The Backups page is only available in production environments for backup service versions older than 4.3.5.
```

## Downloading Backups via the Console

Follow these steps to download a backup from the *Backups* page in your chosen environment:

1. Click on the *Actions* button ( ⋮ ) for the backup you want to download.

1. Click on *Download*.

   ![Click on the Actions button, and then click Download.](./downloading-and-uploading-backups/images/01.png)

1. Click on the *Database* (`.gz`) or *Liferay* (`.tgz`) file to start downloading. Together, these zip archives comprise the environment backup.

    ```note::
       If your Backup service is not yet updated to version ``4.2`` or above, then the Database volume is downloaded as a ``.tgz`` archive instead of ``.gz``.
    ```

    ![Click to download the database and Liferay data volume files.](./downloading-and-uploading-backups/images/02.png)

```note::
   Only administrators for the chosen environment can download backups from the Backups page.
```

## Uploading Backups via the Console

You can also upload a backup to your project through the *Backups* page in your chosen environment.

Before you can upload a backup to DXP Cloud, you must compress the database dump and document library in separate archives. See [Preparing the Database and Document Library for Upload](#preparing-the-database-and-document-library-for-upload) for more information on preparing for the upload for an on-premises environment.

```warning::
   Once an upload is initiated, the Backup service is unavailable to generate or restore other backups until the upload is completed.
```

Follow these steps from the *Backups* page:

1. Click *Upload Backup...* near the top of the screen.

1. On the Upload Backup page, expand the appropriate environment, and then click the `+` icons for both the database and document library to upload them.

    ![Click the icons to upload both the database and document library as .gz archives.](./downloading-and-uploading-backups/images/03.png)

1. When both the database dump and document library are uploaded, click *Initiate Upload*.

DXP Cloud begins using the files you uploaded to generate a backup and add it to the list you can restore to your environments. While the backup is being generated, other backups cannot be generated or restored.

A success message appears on the page when the backup is generated and the service resumes normal operation.

![When the backup is finished being added to the list in your environment, a success message appears.](./downloading-and-uploading-backups/images/04.png)

## Backup Service APIs

The backup service has APIs that you can also use to download and upload backups. You can invoke these APIs using a command line tool such as `curl`.

### Getting the Host Name

To invoke the backup APIs, you need the backup service's host name. You can find this on the *Services* page.

![View the backup service's host name from the Services page.](./downloading-and-uploading-backups/images/05.png)

The backup service's host name is a combination of the service, project, and environment names.

Consider this example:

* Service name: `backup`
* Project name: `lfrjoebloggs`
* Environment name: `prd`
* Host name: `backup-lfrjoebloggs-prd.lfr.cloud`

### Authentication

You can authenticate your request with basic authentication or a user access token.

Note that token authentication is required if SSO is enabled. You can retrieve this token from the cookie `access_token` and use it with the `dxpcloud-authorization` header.

Here's an example that uses token authentication with the upload API:

```bash
curl -X POST \
  https://backup-<PROJECT-NAME>-<ENV>.lfr.cloud/backup/upload \
  -H 'Content-Type: multipart/form-data' \
  -H 'dxpcloud-authorization: Bearer <USER_TOKEN>' \
  -F 'database=@/my-folder/database.gz' \
  -F 'volume=@/my-folder/volume.tgz'
```

```note::
   Passing the user token in the header ``dxpcloud-authorization`` only works for versions ``3.2.0`` or greater of the backup service. Previous versions should be upgraded to at least ``3.2.0``. Requests to earlier versions must use the header ``Authorization: Bearer <PROJECT_MASTER_TOKEN>``. You can find the project master token by running the command ``env | grep LCP_PROJECT_MASTER_TOKEN`` in any shell in the Liferay DXP Cloud console.
```

### Download Database API

The API for downloading a database contains an endpoint that returns a `.gz` file. The `id` parameter represents the backup ID, which you can find on the Backups page. This ID is comprised of three strings separated by two dashes (e.g., `dxpcloud-lqgqnewltbexuewymq-201910031723`).

#### Parameters

Name | Type     | Required |
---- | -------- | -------- |
`id` | `String` | Yes      |

#### curl Example

```bash
curl -X GET \
  https://backup-<PROJECT-NAME>-<ENV>.lfr.cloud/backup/download/database/id \
  -u user@domain.com:password \
  --output database.gz
```

```note::
   If your Backup service is not yet updated to version ``4.2`` or above, then the Database volume is downloaded as a ``.tgz`` archive instead of ``.gz``.
```

### Download Data Volume API

The API for downloading a data volume contains an endpoint that returns a `.tgz` file. The `id` parameter represents the backup ID, which you can find on the Backups page. This ID is comprised of three strings separated by two dashes (e.g., `dxpcloud-lqgqnewltbexuewymq-201910031723`).

#### Parameters

Name | Type     | Required |
---- | -------- | -------- |
`id` | `String` | Yes      |

#### curl Example

```bash
curl -X GET \
  https://backup-<PROJECT-NAME>-<ENV>.lfr.cloud/backup/download/volume/id \
  -u user@domain.com:password \
  --output volume.tgz
```

### Upload Backup API

Follow these steps to upload a backup to DXP Cloud with the upload backup API:

1. [Create the database file](#creating-the-database-file).

1. [Create the volume file](#creating-the-volume-file).

1. [Invoke the backup API](#invoking-the-backup-api) with the database and volume files.

Before you can use the upload API, you must compress the database dump and document library in separate archives. See [Preparing the Database and Document Library for Upload](#preparing-the-database-and-document-library-for-upload) for more information on preparing for the upload for an on-premises environment.

#### Invoking the Backup API

**Parameters**

Name       | Type   | Required |
---------- | ------ | -------- |
`database` | `File` | Yes      |
`volume`   | `File` | Yes      |

**curl Example**

```bash
curl -X POST \
  https://backup-<PROJECT-NAME>-<ENV>.lfr.cloud/backup/upload \
  -H 'Content-Type: multipart/form-data' \
  -F 'database=@/my-folder/database.gz' \
  -F 'volume=@/my-folder/volume.tgz' \
  -u user@domain.com:password
```

## Preparing the Database and Document Library for Upload

To upload a backup of your environment to DXP Cloud, you must have the database and document library from that environment prepared in separate archive files.

### Creating the Database File

To create a MySQL dump (as a `.sql` script) and compress it into a `.gz` archive, run the following commands:

```bash
mysqldump -uroot -ppassword --add-drop-database --databases lportal | gzip -c | cat > database.gz
```

```note::
   If your Backup service is not updated to at least version ``4.2``, then you must also run the following command to convert the archive to a ``.tgz`` file: ``tar zcvf database.tgz database.gz``. Then use the resulting ``.tgz`` archive to upload.
```

The `databases` and `add-drop-database` options are necessary for backup restoration to work correctly. You can also use the `/backup/download` API to see how the backup service creates its MySQL dump file.

With these options, the resulting dump file contains the following code just before the create table statements.

```sql
--
-- Current Database: `lportal`
--

/*!40000 DROP DATABASE IF EXISTS `lportal`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lportal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lportal`;
```

### Creating the Volume File

Run this command to compress the data volume:

```bash
cd $LIFERAY_HOME/data && tar -czvf volume.tgz document_library
```

## Additional Information

* [Backup Service Overview](./backup-service-overview.md)
* [Restoring Data from a Backup](./restoring-data-from-a-backup.md)
* [Database Service (MySQL)](../database-service/database-service.md)
