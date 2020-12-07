# Documents and Media UI Reference

Navigate to the _Site Administration_ &rarr; _Content & Data_ &rarr; _Documents and Media_.

![The Documents and Media menu is found in the Site Administration.](./documents-and-media-ui-reference/images/01.png)

There are three tabs: _Documents and Media_, _Document Types_, and _Metadata Sets_.

## Documents and Media Tab

The _Documents and Media_ Tab's Home screen displays the selected Site's folders and documents. This is the Root folder. Here, users can upload a single document or multiple documents, add a new folder, add a new Repository (for example, Sharepoint), or a new shortcut.

Click the (![Add icon](../../images/icon-add.png)) to begin using the _Documents and Media_ application.

![Click the add icon then a selection to begin populating the Documents and Media app.](./documents-and-media-ui-reference/images/04.png)

## Document Types Tab

_Document Types_ are made of metadata fields that help define the purpose of Document Library files. They provide additional, searchable information about uploaded documents.

![The Document Types Tab contains all Document Types which contain metadata sets.](./documents-and-media-ui-reference/images/02.png)

Click the (![Add icon](../../images/icon-add.png)) to create a new Document Type.

To learn more about using Document Types, see [Defining Document Types](./uploading-and-managing/managing-metadata/defining-document-types.md).

## Metadata Set Tab

Metadata Sets are groups of fields that can be added to Document Types.

Metadata Sets can be grouped in a hierarchy and can become the foundation of another metadata set (an *extension*). A child metadata set is displayed on the same level as the parent.

![The Metadata Set Tab contains all Metadata Sets.](./documents-and-media-ui-reference/images/03.png)

Click the (![Add icon](../../images/icon-add.png)) to create a new Metadata Set.

To learn more about Metadata Sets, see [Using Metadata Sets](./uploading-and-managing/managing-metadata/using-metadata-sets.md).

## Documents and Media Settings

You can access the application's settings from any of the three tabs by clicking the (![Options icon](../../images/icon-options.png)) to access the _Options_ menu.

![Click the Options menu in the top right to access the application settings.](./documents-and-media-ui-reference/images/05.png)

### Access from Desktop

Users can access their _Documents and Media_ repository in their file explorer on their desktop using WebDAV. Click on _Access from Desktop_ to generate the WebDAV URL.

![Generate the WebDAV URL.](./documents-and-media-ui-reference/images/06.png)

### Edit

Users can enable or disable [Workflow](../../process-automation/workflow/using-workflows/activating-workflow.md) for all Document Types. To learn more about Workflows in general, see [Introduction to Workflow](../../process-automation/workflow/introduction-to-workflow.md).

![Select a Workflow definition.](./documents-and-media-ui-reference/images/07.png)

Workflows for Documents and Media are managed only here.

### Home Folder Permissions

Users can manage the permissions for the _Documents and Media_ Home folder.

![Configure the permissions.](./documents-and-media-ui-reference/images/08.png)

See [Understanding Roles and Permissions](../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md) to learn more.

### Export/ Import

You can [Export or Import](../../site-building/building-sites/importing-exporting-pages-and-content.md) Documents and Media `LAR` (Liferay Archive) files. There are two primary places Export/Import is used: Sites and apps. Here, you can export the contents of your DM application or import an existing one.

![Export or Import a DM LAR.](./documents-and-media-ui-reference/images/12.png)

### Configuration

Users can configure their DM email notifications settings on the _Configuration_ menu. See [Connecting to a Mail Server](../../installation-and-upgrades/setting-up-liferay-dxp-configuring-mail/connecting-to-a-mail-server.md) to set up a mail server first.

Users can use the _Definition of Terms_: placeholders that parse information such as the system administrator, the recipient, and the name of the document.

#### Email From Tab

Users can enter a name and email address.

![Enter the name and email address.](./documents-and-media-ui-reference/images/09.png)

#### Document Added Email

If a new document has been added, this is the email notification sent to the desired persons.

![Enter the name and contents of the email notification.](./documents-and-media-ui-reference/images/10.png)

#### Document Updated Email

If an existing document has been updated, this is the email notification sent to the desired persons.

![Enter the name and contents of the email notification.](./documents-and-media-ui-reference/images/11.png)

## Additional Information

* [Sharing Documents and Media](./sharing-documents-and-media.md)
* [Importing/Exporting Sites and Contents](../../site-building/building-sites/importing-exporting-pages-and-content.md)
