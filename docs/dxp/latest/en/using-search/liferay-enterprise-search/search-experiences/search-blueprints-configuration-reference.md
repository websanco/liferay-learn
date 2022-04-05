# Search Blueprints Configuration Reference

The Search Blueprints user interface has a Query Builder for creating Blueprints. Beyond adding Elements and configuring the query settings, there are additional configurations that are important for many Blueprints use cases. To work with these configurations go to the Global Menu &rarr; Applications &rarr; Blueprints. Add a new Blueprint or open an existing one, then click the _Configuration_ tab.

![Configure Search Blueprints using JSON.](./search-blueprints-configuration-reference/7.x/01.png)

Each configuration field consists of a single text area that accepts a JSON string.

## Aggregation Configuration

Configure [Aggregations](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/search-aggregations.html) in the Search Blueprint. Aggregations take the results of a query and group the data into logical sets. Aggregations can be composed to provide complex data summaries.

Aggregations added in Search Blueprints are applied in addition to those already present in the search request (e.g., from facets).

```{note}
Aggregations can be configured and will be returned in the search response, but there is currently no way to display or otherwise process the aggregation on a search page.
<!-- should i say something brief about developing a custom UI for this? or about our planned suopport for getting the aggregation in a widget template? -->
```

To add an Aggregation to a Blueprint, 

1. From the Blueprint editor, click the _Configuration_ tab.
1. Find the Aggregation Configuration text area and enter valid JSON. Use the Elasticsearch DSL to craft the JSON.

   ```json
   {
      "aggs": {
         "date_histogram-test": {
            "date_histogram": {
               "date_histogram_interval": "minute",
               "field": "modified",
               "keyed": true,
               "min_doc_count": 1,
               "order": {
                  "_count": "asc"
               }
            }
         }
      }
   }
   ```

The above example blah blah

Some questions aggregations can answer in Liferay:
- How many contents were created each month, by user
- What was the content average content length, by month and translated language
- What's the average number of tags in a Web Content
- Who were the most active content creates per month

## Highlight Configuration

Configure [Highlights](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/highlighting.html) in Search Blueprints. If you add a highlight configuration in Search Blueprints, it overrides the default search highlight configuration.

To add a Highlight to a Blueprint, 

1. From the Blueprint editor, click the _Configuration_ tab.
1. Find the Highlight Configuration text area and enter valid JSON. The properties and their possible values are detailed in the [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/highlighting.html).

```json
{
   "fields": {
      "content${context.language_id}": {},
      "title${context.language_id}": {}
   },
   "fragment_size": 100,
   "number_of_fragments": 10,
   "post_tags": [
      "</liferay-hl>"
   ],
   "pre_tags": [
      "<liferay-hl>"
   ],
   "require_field_match": true
}
```

## Sort Configuration

Configure [Sorts](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/sort-search-results.html) in Search Blueprints. Sorts added via Search Blueprints are applied in addition to those already in the search request (e.g., from the Sort widget).

To add a Sort to a Blueprint, 

1. From the Blueprint editor, click the _Configuration_ tab.
1. Find the Sort Configuration text area and enter valid JSON.

```json
{
	"sorts": [
		{
			"title_sortable": "desc"
		}
	]
}
```

<!-- Maybe our example sort could enhance the sort by title option in the Sort widget: use localized titles, use names, labels. Find a limitation of the Sort widget that can be solved with a Blueprint sort configuration. -->

## Parameter Configuration

<!-- mention how to code up new search context attributes that will be available in a blueprint? -->
Declare new template variables from search context attributes. For example, a Custom Filter widget could be combined with a Widget Template and a Blueprint to allow the end user to toggle the search results between unpublished and draft content. 
<!-- is status already available in the search context or would this use case need to also add it somehow? -->

To add a Parameter configuration to a Blueprint, 

1. From the Blueprint editor, click the _Configuration_ tab.
1. Find the Parameter Configuration text area and enter valid JSON.

<!--I need an example -->

## Advanced Configuration

The Advanced Configuration adds [source includes and excludes](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/mapping-source-field.html#include-exclude) as search request parameters. The `_source` field contains the stored document body that was passed to the index request. This field is not itself indexed. The Advanced Configuration in Blueprints lets you prune the `_source` field by specifying what fields to include or exclude from the field. This is an advanced feature that will be rarely needed. To add an Advanced configuration to a Blueprint, 

1. From the Blueprint editor, click the _Configuration_ tab.
1. Find the Advanced Configuration text area and enter the `excludes` and `includes` you need. Wildcards are permitted. <!-- maybe we should use wildcards to illustrate--these are the only cases that to me make sense for why you'd want to specify both includes and excludes--exclude with wildcards, explicitly include some stuff you still want to keep--or vice versa -->

```json
{
	"excludes":[
      "<fieldName1>","<fieldName2>"
	],
	"fetchSource":true,
	"includes":[
      "<fieldName3>","<fieldName4>"
	]
}
```
