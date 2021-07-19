# Uploading Files

With Documents and Media, you can upload any type of file to your Liferay instance's [file store](../../../system-administration/file-storage/configuring-file-storage.md) or connected repositories. Once uploaded, Users with the requisite [permissions](../publishing-and-sharing/managing-document-access/documents-and-media-permissions-reference.md) can view, edit, download, or share uploaded files.

```note::
   Users can configure Liferay to tag supported assets automatically when uploaded. See `Configuring Asset Auto Tagging <../../tags-and-categories/auto-tagging/configuring-asset-auto-tagging.md>`_ to learn more. 
```

```important::
   You can enable automatic antivirus scanning to scan files on upload. For details, please see `Enabling Antivirus Scanning for Uploaded Files <../../../system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.md>`_.
```

In order to upload files, first navigate to the Documents and Media application in a Site or Asset Library.

To access Documents and Media in a Site, open the *Site Menu* (![Site Menu](../../../images/icon-product-menu.png)) and go to *Content & Data* &rarr; *Documents and Media*.

![Click on Documents and Media under Content & Data in the Site Menu.](./uploading-files/images/01.png)

To access Documents and Media in an Asset Library, simply navigate to any Library where it's enabled, and click on *Documents and Media*.

![Click on Documents and Media in the Asset Library.](./uploading-files/images/02.png)

Once you've opened the application, the quickest way to upload files is to drag and drop them into the desired folder. This immediately begins uploading your files without prompting you to configure your upload before they're published. Once a file finishes uploading, you can edit its details by clicking its *Actions* button and selecting *Edit*.

![Drag and drop any number of files into the desired folder.](./uploading-files/images/03.png)

However, if you would like to define file details before uploading them, follow these steps:

1. Click the *Add* button (![Add Button](../../../images/icon-add.png)) and select either *File Upload*, or *Multiple Files Upload*.

   ![Select either File Upload, or Multiple Files Upload.](./uploading-files/images/04.png)

1. Drag and drop files into the designated drop-zone or use the file selector to browse for your files.

1. Configure file details before upload.

   If you've selected *File Upload*, you can set the file's Title, File Name, Description, Display Page Template, Public Categories, Related Assets, and Permissions used for your selected file.

   ![Select a file to upload and configure its details.](./uploading-files/images/05.png)

   If you've selected *Multiple Files Upload*, you can set the following fields: Description, Document Type, Display Page Template, Public Categories, and Permissions. for all of the selected files.

   ![Select multiple to upload, and configure their details.](./uploading-files/images/06.png)

1. When finished, click *Publish* to begin the upload process.

## Additional Information

* [Creating Folders](./creating-folders.md)
* [Using the Media Gallery](../publishing-and-sharing/publishing-documents.md#using-the-media-gallery-widget)
* [Enabling Xuggler and ImageMagick for Previews](../../../system-administration/using-the-server-administration-panel/configuring-external-services.md#enabling-document-previews)
