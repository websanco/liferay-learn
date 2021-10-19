# Creating Objects

Liferay Objects provides low-code development capabilities in the Liferay UI. Using the Objects portlet, you can build fully integrated Liferay applications without having to develop any code or manually deploy modules. This process includes [creating](#creating-object-drafts) an initial draft of the Object, [editing](#editing-object-drafts) the Object draft, and finally [publishing](#publishing-object-drafts) the draft to create the new application. This process creates a fully integrated Liferay application that can be managed and extended at any time via the Objects portlet.

## Creating Object Drafts

Object drafts are application templates stored in the Objects portlet. The data stored in each template is used to create the Object application when it's published. <!--SME INPUT-->

Follow these steps to create an Object draft:

1. Open the *Global Menu* (![Global Menu]()), go to the *Control Panel* tab, and click on *Objects*.

1. Click on the *Add* button (![Add Button]()).

1. Provide the following details for your new Object.

   **Label**: This value is used to identify the Object in the Objects portlet and supported application contexts (e.g., Workflow, Display Page Templates, Forms).

   **Plural Label**: This value determines the display name for the Object application in the Portal UI.

   **Object Name**: This value determines the Object's `definition.name` and cannot be changed once the Object is [published](#publishing-object-drafts).

1. Click on *Save*.

Once saved, a new Object draft is created without any fields, relationships, layouts, or defined scope. To finish the creation process, you must [edit](#editing-object-drafts) the Object and then [publish](#publishing-object-drafts) it to your Liferay instance.

## Editing Object Drafts

Before an Object is published, you can configure any of its settings and elements. This includes the scope of its data, its name, panel category key, fields, relationships, and layouts. Once published, some of these configuration options are removed. See [Publishing Object Drafts](#publishing-object-drafts) for more information.

Follow these steps to edit the Object draft.

1. From the Objects application, click on the newly created Object draft. This redirects you to the *Details* tab.

1. If desired, modify the Object's Name, Label, and Plural Label.

   ```{important}
   Once published, the Name field cannot be changed.
   ```

1. Determine the scope of the Object's data and where it can be accessed in the Liferay UI.

   **Company** (default): When scoped by Company, the application is made available in the Global Menu and its data can be accessed globally.

   **Site**: When scoped by Site, the application is deployed to all Sites and is made available in the Site Menu; any generated data is scoped to the Site in which it's created.

1. Use the *Panel Category Key* dropdown menu to determine where the Object is listed in either the Site Menu or Global Menu.

   ```{important}
   You can only edit an Object's scope before publishing. After publishing, the scope setting is disabled, but you can edit the Panel Category Key at any time.
   ```

1. Click on *Save* before moving on to the next steps. Any unsaved changes in the Details tab will be lost when you navigate to the other tabs.

1. (Optional) Go to the *Fields* tab and add fields to the Object. Fields are Object data definitions for storing specific types of values and represent database columns. See [Adding Fields to Objects]() for more information.

1. (Optional) Go to the *Relationships* tab and add relationships to the Object. Relationships are defined connections between Objects that allow you to relate Object entries. See [Defining Object Relationships]() for more information.

1. (Optional) Go to the *Layouts* tab and add a custom layout to the Object if desired. Object layouts define how its fields and relationships are displayed when creating or editing an Object entry. See [Designing Object Layouts](./designing-object-layouts.md) for more information.

   ```{note}
   If you do not design a custom layout and set it as default, the Object will use an automatically generated layout for entries. This automatically generated layout has a single tab that displays all Object fields in alphabetical order and does not display relationships. To display relationships, you must create a custom layout with a dedicated relationships tab.
   ```
<!--TASK: Determine how to explain the entry title field.-->
Whenever you add a field, relationship, or layout, the Object draft is automatically saved. Once you've finished configuring and editing the draft, you can [publish](#publishing-object-drafts) it in the *Details* tab to create the application.

## Publishing Object Drafts
<!--REFINE SECTION-->

Publishing an Object creates and activates your new application. When activated, users can access it in the Portal UI according to its scope and panel category key. The publishing process includes the following operations:

* A database table is created for the Object with the draft's data definitions.

* A new Headless API is automatically created for CRUD operations.

* A Collection Provider is created for displaying the Object's entries.

* The Object is integrated with Info framework, so you can select the Object as a content type for a Display Page Template

* The Object is integrated with the Permissions framework, so you can manage permissions for the new application and its resources. The Object's location within the permissions is determined by the Object's scope and panel category key. (Permissions Framework)

* The Object is integrated with the Workflow framework, so you can define/configure a custom workflow for the Object. (Workflow Framework)

* The Object is integrated with Forms, so you can select the Object as a data storage option.

In order to prevent data loss and system conflicts,<!--SME INPUT--> some configuration options are removed for Objects, fields, and relationships after they are published.

* Published Objects cannot be removed.

* The name and scope of a published Object cannot be changed.

* Any fields or relationships included in an Object at the time of publishing cannot be removed.

* Most configuration options for published fields and relationships cannot be changed.

While you can still edit and localize labels for Objects, fields, and relationships after publishing, most configuration options are removed. For Objects, you can also change the Panel Category Key, as well as add new fields, relationships, and layouts. For relationships, you can also configure its *Deletion Type*.

## Additional Information

* [Introduction to Objects](../introduction-to-objects.md)
* [Adding Fields to Objects](./adding-fields-to-objects.md)
* [Defining Object Relationships](./defining-object-relationships.md)
* [Designing Object Layouts](./designing-object-layouts.md)
* [Managing Objects](./managing-objects.md)
