# Creating and Managing Elements

Elements are one of the fundamental building blocks of [Search Blueprints](understanding-search-blueprints.md) and are managed within the Blueprints application. Open the Global menu's Applications section and find the Search Experiences category to open Search Blueprints. Click the _Elements_ tab to add a new Element and manage existing Elements.

![Create and Manage Elements from the Blueprints application.](./creating-and-managing-elements/images/01.png)

## Managing Elements

To create new Elements, click the Add (![Add](../../../images/icon-add.png)) button. See [Creating Elements](#creating-elements) for more details.

To delete existing custom Elements, select the Element(s) using the checkbox selector and click the Delete (![Delete](../../../images/icon-trash.png)) button.

```{warning} System (i.e., out of the box) Elements cannot be edited or deleted. Duplicate an existing Element if you would like to create a custom Element base don an existing Element.
```

To duplicate an existing Element, click the Actions (![Actions](../../../images/icon-actions.png)) button and select _Copy_. The duplicated Element can be deleted or edited just like any other custom Element.

To export an Element, click the Actions (![Actions](../../../images/icon-actions.png)) button and select _Export_.

To preview what the Element's configuration window will look like in the Query Builder, click the _Preview_ link (next to the Cancel button).

![Preview the Element Configuration window.](./creating-and-managing-elements/images/03.png)

## Creating Elements

There are a lot of [system Elements that ship with Search Blueprints](search-blueprints-elements-reference.md). If none of the system Elements can meet your need, there are a variety of ways to create your own Search Blueprints Elements:

* [Use the Custom JSON Element](#using-the-custom-json-elements) to create Elements from the Search Blueprints [Query Builder](creating-and-managing-search-blueprints.md#using-the-query-builder).
* [Use the Add Element source editor](#using-the-add-element-source-editor) to write Elements using a more robust Element editor, including handy preview functionality and a listing of the predefined template variables in the system. 
* [Duplicate a system Element](#managing-elements) to get a head start on developing your own custom Element.
* Use the [Paste Any Elasticsearch Query system Element](search-blueprints-elements-reference.md#paste-any-elasticsearch-query) if you're familiar with using the [Elasticsearch query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl.html) and need to cook up an Element quickly based on an Elasticsearch query.

### Using the Custom JSON Element

Elements can be added in the [Query Builder](creating-and-managing-search-blueprints.md#using-the-query-builder) while working on a Blueprint. Add the Custom JSON Element to the builder and begin editing the boilerplate JSON:

```json
{
   "description_i18n": {
      "en_US": "Editable JSON Text Area"
   },
   "elementDefinition": {
      "category": "custom",
      "configuration": {},
      "icon": "custom-field"
   },
   "title_i18n": {
      "en_US": "Custom JSON Element"
   }
}
```

While any Element can be written in the Custom JSON Element, usually this approach is best reserved for simple Elements that add a query clause and perhaps a condition. Often these will not need a `uiConfiguration` section and will not make extensive use of the predefined template variables that can be used in an Element. For more complex cases, use the [Element source editor](#using-the-add-element-source-editor).

For example, the below Exclude Journal Articles Element is based on the Custom JSON Element. It adds a condition so that the Element's query is only applied if a parameter called `exclude.journal_articles` is `true`. The query adds a `must_not` term query clause to make sure that results do not match a Web Content article's `entryClassName` field.

```json
{
   "description_i18n": {
      "en_US": "Exclude journal articles from the search."
   },
   "elementDefinition": {
      "category": "hide",
      "configuration": {
         "queryConfiguration": {
            "queryEntries": [
               {
                  "clauses": [
                     {
                        "context": "query",
                        "occur": "filter",
                        "query": {
                           "bool": {
                              "should": [
                                 {
                                    "bool": {
                                       "must_not": [
                                          {
                                             "term": {
                                                "entryClassName": {
                                                "value": "com.liferay.journal.model.JournalArticle"}
                                             }
                                          }
                                       ]
                                    }
                                 },
                                 {
                                    "bool": {
                                       "must": [
                                          {
                                             "term": {
                                                "discussion": false
                                             }
                                          }
                                       ]
                                    }
                                 }
                              ]
                           }
                        }
                     }           
                  ],
                  "condition": {
                     "contains": {
                        "parameterName": "exclude.journal_articles",
                        "value": "true"
                     }
                  }
               }
            ]
         }
      },
      "icon": "hidden"
   },
   "title_i18n": {
      "en_US": "Exclude Journal Articles"
   }
}
```

This Element depends on a custom template variable that can be added using the Parameter Configuration of a Blueprint. See the [Search Blueprints Configuration Reference](./search-blueprints-configuration-reference.md#parameter-configuration) to learn about adding custom template variables to the Blueprint.

### Using the Add Element Source Editor

A more robust editing experience is available for building your Elements. From the Elements section of Search Blueprints, click the Add (![Add](../../../images/icon-add.png)) button. 

Name the Element and click _Create_. The Element Source editor is displayed, and the Predefined Variables are shown in the pane to the left of the editor.

![Create Elements in the Element source editor.](./creating-and-managing-elements/images/02.png)

The boilerplate JSON populating the Element is nearly identical to the [Text Match over Multiple Field](search-blueprints-elements-reference.md#text-match-over-multiple-fields) system Element:

```json
{
   "description_i18n": {},
   "elementDefinition": {
      "category": "match",
      "configuration": {
         "queryConfiguration": {
            "queryEntries": [
               {
                  "clauses": [
                     {
                        "context": "query",
                        "occur": "must",
                        "query": {
                           "multi_match": {
                              "fuzziness": "${configuration.fuzziness}",
                              "query": "${configuration.keywords}",
                              "minimum_should_match": "${configuration.minimum_should_match}",
                              "boost": "${configuration.boost}",
                              "fields": "${configuration.fields}",
                              "type": "${configuration.type}",
                              "operator": "${configuration.operator}",
                              "slop": "${configuration.slop}"
                           }
                        }
                     }
                  ]
               }
            ]
         }
      },
      "icon": "picture",
      "uiConfiguration": {
         "fieldSets": [
            {
               "fields": [
                  {
                     "defaultValue": [
                        {
                           "field": "localized_title",
                           "locale": "${context.language_id}",
                           "boost": "2"
                        },
                        {
                           "field": "content",
                           "locale": "${context.language_id}",
                           "boost": "1"
                        }
                     ],
                     "label": "Fields",
                     "name": "fields",
                     "type": "fieldMappingList",
                     "typeOptions": {
                        "boost": true
                     }
                  },
                  {
                     "defaultValue": "or",
                     "label": "Operator",
                     "name": "operator",
                     "type": "select",
                     "typeOptions": {
                        "options": [
                           {
                              "label": "OR",
                              "value": "or"
                           },
                           {
                              "label": "AND",
                              "value": "and"
                           }
                        ]
                     }
                  },
                  {
                     "defaultValue": "best_fields",
                     "label": "Match Type",
                     "name": "type",
                     "type": "select",
                     "typeOptions": {
                        "options": [
                           {
                              "label": "Best Fields",
                              "value": "best_fields"
                           },
                           {
                              "label": "Most Fields",
                              "value": "most_fields"
                           },
                           {
                              "label": "Cross Fields",
                              "value": "cross_fields"
                           },
                           {
                              "label": "Phrase",
                              "value": "phrase"
                           },
                           {
                              "label": "Phrase Prefix",
                              "value": "phrase_prefix"
                           },
                           {
                              "label": "Boolean Prefix",
                              "value": "bool_prefix"
                           }
                        ]
                     }
                  },
                  {
                     "defaultValue": "AUTO",
                     "helpText": "Only use fuzziness with the following match types: most fields, best fields, bool prefix.",
                     "label": "Fuzziness",
                     "name": "fuzziness",
                     "type": "select",
                     "typeOptions": {
                        "nullable": true,
                        "options": [
                           {
                              "label": "Auto",
                              "value": "AUTO"
                           },
                           {
                              "label": "0",
                              "value": "0"
                           },
                           {
                              "label": "1",
                              "value": "1"
                           },
                           {
                              "label": "2",
                              "value": "2"
                           }
                        ]
                     }
                  },
                  {
                     "defaultValue": "0",
                     "label": "Minimum Should Match",
                     "name": "minimum_should_match",
                     "type": "text",
                     "typeOptions": {
                        "nullable": true
                     }
                  },
                  {
                     "defaultValue": "",
                     "helpText": "Only use slop with the following match types: phrase, phrase prefix.",
                     "label": "Slop",
                     "name": "slop",
                     "type": "number",
                     "typeOptions": {
                        "min": 0,
                        "nullable": true,
                        "step": 1
                     }
                  },
                  {
                     "defaultValue": 1,
                     "label": "Boost",
                     "name": "boost",
                     "type": "number",
                     "typeOptions": {
                        "min": 0
                     }
                  },
                  {
                     "helpText": "If this is set, the search terms entered in the search bar will be replaced by this value.",
                     "label": "Text to Match",
                     "name": "keywords",
                     "type": "keywords",
                     "typeOptions": {
                        "required": false
                     }
                  }
               ]
            }
         ]
      }
   },
   "title_i18n": {
      "en_US": "My Element"
   },
   "type": 0
}
```

## Understanding the Element Schema

The schema for Search Blueprint Elements is defined in the [sxp-query-element.schema.json](https://github.com/liferay/liferay-portal/blob/master/modules/dxp/apps/search-experiences/search-experiences-web/src/main/resources/META-INF/resources/sxp_blueprint_admin/schemas/sxp-query-element.schema.json) file.

Each Element has these three top-level properties: `description_i18n`, `elementDefinition`, and `title_i18n`.

```json
{
   "description_i18n": {
      "en_US": "Search for a text match over multiple text fields."
   },
   "elementDefinition": {},
   "title_i18n": {
      "en_US": "Text Match Over Multiple Fields"
   }
}
```

The title and description fields are straightforward: set the title and description text for the Element, in as many languages as you need.

### Creating the Element `elementDefinition`

The `elementDefinition` is where you'll do the bulk of the work. Its properties include `category`, `configuration`, `icon`, and `uiConfiguration`. 

- `category` provides a string that classifies the behavior of the Element. Specify `match`, `boost`, `conditional`, `filter`, `hide`, or `custom`.
- `configuration` provides the `queryConfiguration`, which holds the query clauses you're contributing, via the `queryEntries` property.
- `icon` sets a string that sets which available icon to use for the Element. Any image available in the [Lexicon Icon Library](https://github.com/liferay/lexiconcss/tree/master/images/icons) can be used (e.g., `thumb-up`). <!-- need clarification on this: for example custom-field is not in Lexicon -->
- `uiConfiguration` sets the configuration elements that you'll show in the UI and then pass into your custom Element with the configured values. 

For example, an Element that boosts a term query match on the `entryClassName` field can configure the query and the UI like this:

```json
"configuration": {
   "queryConfiguration": {
      "queryEntries": [
         {
            "clauses": [
               {
                  "query": {
                     "term": {
                        "entryClassName": {
                           "boost": "${configuration.boost}",
                           "value": "${configuration.entry_class_name}"
                        }
                     }
                  }
               }
            ]
         }
      ]
   }
},
"uiConfiguration": {
   "fieldSets": [
      {
         "fields": [
            {
               "defaultValue": 1,
               "label": "Boost",
               "name": "boost",
               "type": "number",
               "typeOptions": {
                  "min": 0
               }
            }
         ]
      }
   }
```

### Defining the Element `configuration`

The `configuration` property holds the nested properties `queryConfiguration` &rarr; `queryEntries`.

Diving into the `queryEntries` JSON, it can contain the properties `clauses`, `conditions`, `enabled`, `postFilterClauses`, and `rescores`. 

- The snippet above shows how `clauses` (an array of elements) is used to add a query clause the Element contributes to the Blueprint-driven search. In addition to `query`, you can add `additive`, `boost`, `content`, `disabled`, `field`, `name`, `occur`, `parent`, `type`, or `value` properties.
- A `condition` provides a boolean check. If true the provided clauses are included in the search query, if false they are left out.
- Set `enabled` to false (it's true by default) if <!--Does this do what it sounds like? Disable the Element? -->
- Add `postFilterClauses` (as an array of `clause` elements). You can add `additive`, `boost`, `content`, `disabled`, `field`, `name`, `occur`, `parent`, `query`, `type`, or `value` properties.

### Defining the Element's `uiConfiguration`

The `uiConfiguration` property holds the nested properties `fieldSet` and `field`.

There are a number of configurations you can add for each field in the UI Configuration:

- `fieldMappings` <!--not sure what this does -->
- Enter `helpText` to display help text for the field in the Blueprints UI.
- Enter a human readable `label`.
- Enter a `name` that can be used to reference the field elsewhere in the Element, like when passing the field value into the query clause: `${configuration.[name]}`.
- `step`
- Set the `type` of the field. Choose from `date`, `fieldMapping`, `fieldMappingList` (a list of the available fields, with their locale and a field boost), `itemSelector`, `json`, `keywords`, `multiselect`, `number`, `select`, `slider`, and `text`
- Use the `typeOptions` property to configure the options available for each type of field. For example, set the `options` displayed for a select field.


The  Element Definition for the Text Match over Multiple Fields Element is instructive for learning about these properties:

```json
"elementDefinition": {
      "category": "match",
      "configuration": {
         "queryConfiguration": {
            "queryEntries": [
               {
                  "clauses": [
                     {
                        "context": "query",
                        "occur": "must",
                        "query": {
                           "multi_match": {
                              "fuzziness": "${configuration.fuzziness}",
                              "query": "${configuration.keywords}",
                              "minimum_should_match": "${configuration.minimum_should_match}",
                              "boost": "${configuration.boost}",
                              "fields": "${configuration.fields}",
                              "type": "${configuration.type}",
                              "operator": "${configuration.operator}",
                              "slop": "${configuration.slop}"
                           }
                        }
                     }
                  ]
               }
            ]
         }
      },
      "icon": "picture",
      "uiConfiguration": {
         "fieldSets": [
            {
               "fields": [
                  {
                     "defaultValue": [
                        {
                           "field": "localized_title",
                           "locale": "${context.language_id}",
                           "boost": "2"
                        },
                        {
                           "field": "content",
                           "locale": "${context.language_id}",
                           "boost": "1"
                        }
                     ],
                     "label": "Fields",
                     "name": "fields",
                     "type": "fieldMappingList",
                     "typeOptions": {
                        "boost": true
                     }
                  },
                  {
                     "defaultValue": "or",
                     "label": "Operator",
                     "name": "operator",
                     "type": "select",
                     "typeOptions": {
                        "options": [
                           {
                              "label": "OR",
                              "value": "or"
                           },
                           {
                              "label": "AND",
                              "value": "and"
                           }
                        ]
                     }
                  },
                  {
                     "defaultValue": "best_fields",
                     "label": "Match Type",
                     "name": "type",
                     "type": "select",
                     "typeOptions": {
                        "options": [
                           {
                              "label": "Best Fields",
                              "value": "best_fields"
                           },
                           {
                              "label": "Most Fields",
                              "value": "most_fields"
                           },
                           {
                              "label": "Cross Fields",
                              "value": "cross_fields"
                           },
                           {
                              "label": "Phrase",
                              "value": "phrase"
                           },
                           {
                              "label": "Phrase Prefix",
                              "value": "phrase_prefix"
                           },
                           {
                              "label": "Boolean Prefix",
                              "value": "bool_prefix"
                           }
                        ]
                     }
                  },
                  {
                     "defaultValue": "AUTO",
                     "helpText": "Only use fuzziness with the following match types: most fields, best fields, bool prefix.",
                     "label": "Fuzziness",
                     "name": "fuzziness",
                     "type": "select",
                     "typeOptions": {
                        "nullable": true,
                        "options": [
                           {
                              "label": "Auto",
                              "value": "AUTO"
                           },
                           {
                              "label": "0",
                              "value": "0"
                           },
                           {
                              "label": "1",
                              "value": "1"
                           },
                           {
                              "label": "2",
                              "value": "2"
                           }
                        ]
                     }
                  },
                  {
                     "defaultValue": "0",
                     "label": "Minimum Should Match",
                     "name": "minimum_should_match",
                     "type": "text",
                     "typeOptions": {
                        "nullable": true
                     }
                  },
                  {
                     "defaultValue": "",
                     "helpText": "Only use slop with the following match types: phrase, phrase prefix.",
                     "label": "Slop",
                     "name": "slop",
                     "type": "number",
                     "typeOptions": {
                        "min": 0,
                        "nullable": true,
                        "step": 1
                     }
                  },
                  {
                     "defaultValue": 1,
                     "label": "Boost",
                     "name": "boost",
                     "type": "number",
                     "typeOptions": {
                        "min": 0
                     }
                  },
                  {
                     "helpText": "If this is set, the search terms entered in the search bar will be replaced by this value.",
                     "label": "Text to Match",
                     "name": "keywords",
                     "type": "keywords",
                     "typeOptions": {
                        "required": false
                     }
                  }
               ]
            }
         ]
      }
```
