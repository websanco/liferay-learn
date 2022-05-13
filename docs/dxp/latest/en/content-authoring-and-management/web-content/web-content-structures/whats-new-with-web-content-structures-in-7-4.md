# What's New with Web Content Structures in 7.4?
# Migrating Liferay Applications from Dynamic Data Mapping to Data Engine: The New Form-Building Framework for Liferay

As of Liferay 7.4 <!-- Is this true or was it 7.3? -->, these product areas have been migrated from using Dynamic Data Mapping (DDM) to Data Engine (DE) as the back-end framework for building their respective forms: 

- Web Content Structures
- Documents and Media Metadata Sets
- Liferay Forms

<!-- What about objects?-->

Neither the name of these frameworks or their inner workings is important, but understanding the changes and enhancements due to the change is definitely in your interests. 

- The drag-and-drop experience for placing fields into the structure is improved. <!-- How is it improved? -->

## New and Improved Field Types

Fields have been added and improved as part of the move to Data Engine:

- The Select from List field's options are ordered alphabetically.
- Configure a Numeric field as an integer or decimal field.
- The Grid field is a new field type in Web Content and Documents and Media. See the [Forms Field Type Reference](../../../process-automation/forms/creating-and-managing-forms/forms-field-types-reference.md).
- The HTML field was replaced by the Rich Text field, which includes a convenient tool bar.

```{warning}
**Boolean field versus Multiple Selection field:** Data Engine did not initially include a Boolean field. Instead, Boolean fields in upgraded Structures were migrated to use the Data Engine's Multiple Selection field, using the same label and just one option. Some issues can arise from this, for example if templates were built on structures with Boolean fields. In Liferay 7.4 Update/GA 23, the Boolean field type is added to Data Engine. Upgraded Structures after this point successfully convert 7.3 DDM Boolean fields to 7.4 DE Boolean fields in Web Content Structures. If your installation was upgraded before this change, you must manually change these single-option Multiple Selection fields to Boolean fields by editing the affected Structures. <!-- Should we provide a Groovy script? -->
```

<!-- See Confluence: https://liferay.atlassian.net/wiki/spaces/ENGFORMS/blog/2022/05/05/2029453611/Update+Process+Corrections -->

## Parent Structures are Replaced by Structure Fieldsets

In Liferay 7.2 you could create child structures that inherited all the parent's fields and settings. This relationship no longer exists in Liferay 7.4, because you can now create fieldsets, which similarly allow you to create resuable fieldsets for your structures. Fieldsets improve the editing experience since they allow you to see what the structure will look like. 

To work with fieldsets,

1. In the Web Content Structure form builder view, click the _Fieldsets_ tab.
1. Click _Create New Fieldset_. Name the fieldset.
1. Add and configure its fields. Click _Save_.

<!-- Screenshot of fieldset editor or the available fieldsets list -->

When you begin creating a new structure, any existing fieldsets will be available to select in the Fieldsets tab. The Basic Web Content structure is included as a fieldset out-of-the-box.

<!--If we can we should be more specific--what improved? -->
* Field Sets replace parent Structures (benefits documented in [LRDOCS-10455](https://issues.liferay.com/browse/LRDOCS-10455)

<!-- Should I compare these with Forms' Element Sets, https://learn.liferay.com/dxp/latest/en/process-automation/forms/creating-and-managing-forms/reusing-sets-of-form-elements.html? What additional sources of information are there? The LRDOCS-10455 ticket says
-->

## Structure Definition Source Changes

In Data Engine the form is represented by JSON (it was XML in DDM). Instead of allowing source editing in the Web Content Structures UI, you can now import and export the Structure definition to work with the structure's source JSON locally.

```{note}
Forms and Document and Media do not allow import and export of the form JSON.
```

To export a Structure's JSON, 

1. Go to the Site Menu &rarr; Content and Data &rarr; Web Content.
1. Click the _Structures_ tab.
1. If there's no existing Structure, create one and save it.
1. In the main Structures list view, Open the Actions menu and click _Export as JSON_.

   ![](./whats-new-with-web-content-structures-in-7-4/images/01.png)

To import a Structure, 

1. Go to the Site Menu &rarr; Content and Data &rarr; Web Content.
1. Click the _Structures_ tab.
1. Open the Options menu for Web Content in the upper right corner of the screen.
1. Click _Import Structure_.

   ![](./whats-new-with-web-content-structures-in-7-4/images/02.png)
