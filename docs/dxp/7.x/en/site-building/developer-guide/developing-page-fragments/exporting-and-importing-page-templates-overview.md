# Exporting and Importing Page Templates Overview

Page Templates can be exported using the Liferay Fragments Toolkit. Once they are exported and saved in a local machine, they can be edited locally. All templates are in JSON format. Page Templates can then be imported into DXP again; they can be uploaded into the same DXP site or into another site.

 The last published version of the Page Template is always exported. Otherwise, if the Page Template does not have a published version it cannot be exported.

The exported ZIP file contains the following:

* A `page-template-collection.json` file which contains the name of the Collection where the Page Template is saved and any other metadata;
* An optional thumbnail file;
* A `page-template.json` file which contains the Page Template name and any other metadata;
* A `page-definition.json` file which specifies the structure and content of the Page Template.

A [Page Definition JSON Schema file](https://github.com/liferay/liferay-portal/blob/master/modules/apps/layout/layout-page-template-api/src/main/resources/com/liferay/layout/page/template/validator/dependencies/page_definition_json_schema.json) describes the content of the `page-definition.json` file and can be imported into any IDE supporting JSON schema validation, as [IntelliJ](https://www.jetbrains.com/help/idea/json.html#ws_json_schema_add_custom) or [Visual Studio Code](https://code.visualstudio.com/docs/languages/json#_json-schemas-and-settings), providing validation and some useful auto-completion capabilities.

The ZIP file may contain different types of page templates and is organized as follows:

```json
display-page-templates/display-page-template-key1/display-page-template.json
display-page-templates/display-page-template-key1/page-definition.json
display-page-templates/display-page-template-key1/thumbnail.png
```

```json
display-page-templates/display-page-template-key2/display-page-template.json
display-page-templates/display-page-template-key2/page-definition.json
display-page-templates/display-page-template-key2/thumbnail.png
```

```json
master-pages/master-page-key1/master-page.json
master-pages/master-page-key1/page-definition.json
master-pages/master-page-key1/thumbnail.png
```

```json
master-pages/master-page-key2/master-page.json
master-pages/master-page-key2/page-definition.json
master-pages/master-page-key2/thumbnail.png
page-templates/page-template-collection-key1/page-template-collection.json
page-templates/page-template-collection-key1/page-template-key1/page-template.json
page-templates/page-template-collection-key1/page-template-key1/page-definition.json
page-templates/page-template-collection-key1/page-template-key1/thumbnail.png
```

```json
page-templates/page-template-collection-key1/page-template-key2/page-template.json
page-templates/page-template-collection-key1/page-template-key2/page-definition.json
page-templates/page-template-collection-key1/page-template-key2/thumbnail.png
```

There are [JSON schemas](https://github.com/liferay/liferay-portal/tree/master/modules/apps/layout/layout-page-template-api/src/main/resources/com/liferay/layout/page/template/validator/dependencies) available for `display-page-template.json`, `master-page.json`, `page-template-collection.json` and `page-template.json`.

## Additional Information

* [Developing Fragments Intro](./developing-fragments-intro.md)
* [Using the Fragments Toolkit](./using-the-fragments-toolkit.md)
