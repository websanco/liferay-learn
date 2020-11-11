# Exporting and Importing Page Templates Overview

You can export Page Templates to the Liferay Fragments Toolkit. Once they are exported locally, you can use your favorite editor to modify them. All templates are in JSON format. Page Templates can then be re-imported into the same Site or into another Site.

The last published version of the Page Template is always exported. If the Page Template has never been published, it cannot be exported.

The exported ZIP file contains these files: 

* `page-template-collection.json`: Contains the name of the Collection where the Page Template is saved and any other metadata
* An optional thumbnail file
* `page-template.json`: Contains the Page Template name and any other metadata
* `page-definition.json`: specifies the structure and content of the Page Template

A [Page Definition JSON Schema file](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/layout/layout-page-template-api/src/main/resources/com/liferay/layout/page/template/validator/dependencies/page_definition_json_schema.json) describes the content of the `page-definition.json` file and can be imported into any editor supporting JSON schema validation.

The ZIP file may contain different types of page templates. 

There are [JSON schemas](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/layout/layout-page-template-api/src/main/resources/com/liferay/layout/page/template/validator/dependencies) available for `display-page-template.json`, `master-page.json`, `page-template-collection.json` and `page-template.json`.

To learn how to Export and Import Page Templates using the Liferay Fragment Toolkit, see [Exporting and Importing Page Templates](./exporting-and-importing-page-templates.md).

## Additional Information

* [Developing Fragments](./developing-fragments-intro.md)
* [Using the Fragments Toolkit](./using-the-fragments-toolkit.md)
* [Exporting and Importing Page Templates](./exporting-and-importing-page-templates.md)
