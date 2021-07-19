# Defining Document Types

In Liferay, Document Types are templates used for uploading files and categorizing them in Documents and Media. These templates are created using metadata fields and provide additional, searchable information for uploaded files. They also can help when integrating files with [Workflow](../../../../process-automation/workflow/introduction-to-workflow.md).

Follow these steps to create a custom Document Type.

1. Navigate to the Documents and Media application in a Site or Asset Library.

   To access Documents and Media in a Site, open the *Site Menu* (![Site Menu](../../../../images/icon-product-menu.png)) and go to *Content & Data* &rarr; *Documents and Media*.

   ![Click on Documents and Media under Content & Data in the Site Menu.](./defining-document-types/images/01.png)

   To access Documents and Media in an Asset Library, simply navigate to any Library where it's enabled, and click on *Documents and Media*.

   ![Click on Documents and Media in the Asset Library.](./defining-document-types/images/02.png)

1. Click on the *Document Types* tab.

1. Click the *Add* button (![Add](../../../../images/icon-add.png)) to access the New Document Type form.

1. Enter a *name* for the Document Type.

1. In the *Builder* tab, add the desired fields.

   ![Add fields in the Builder tab.](./defining-document-types/images/03.png)

   Each field is highly configurable, with Basic and Advanced options.

   ![Configure each field.](./defining-document-types/images/04.png)

   If desired, you can create field groups by dragging and dropping fields on top of one another. Each group can also be configured.

   ![Create and configure field groups.](./defining-document-types/images/05.png)

   ```note::
      All custom Document Types have the following fields: Upload File, Title, File Name, and Description. Fields added to a custom Document Type are listed in addition to these default fields.
   ```

1. (Optional) Click on the *Details* tab, and enter a *description*.

   ![Enter a description in the Details tab.](./defining-document-types/images/06.png)

1. (Optional) Click on the *Additional Metadata Fields* tab, and select any existing [Metadata Sets](./defining-metadata-sets.md) you want to add to the new Document Type.

   ![Add Metadata Sets in the Additional Metadata Fields tab.](./defining-document-types/images/07.png)

1. (Optional) Click on the *Permissions* tab, and configure who can view, edit, and manage the new Document Type.

   ![Configure permission for the Document Type in the Permissions tab](./defining-document-types/images/08.png)

1. Click on *Save*.

Once saved, the new Document Type can now be accessed in the Documents and Media tab, where you can use it to start a new upload.

![Use the Document Type to start a new upload.](./defining-document-types/images/09.png)

When the Document Type is selected, users are presented with your configured metadata fields. See [Uploading Files](../uploading-files.md) for more information.

## Additional Information

* [Documents and Media Overview]()
* [Using Metadata Sets](./using-metadata-sets.md)
