# Custom Facet

7.3 FP1+ and the new GA for CE will fix DDM fields so we should document this, how to use DDM fields with the custom facet for Web Content
7.2: By default it's legacy mode so DDM works, only if you switch to the newer nested field style (using the configuration) you will need to use the nested fields as well.
7.2: enable Nested Fields Storage, now custom facet doesn't work FP8+ (unless the fix is backported)

The Custom Facet is unique among the out-of-the-box search facets. Rather than group results by a single static field (like the modified date or the asset type), you use a Custom Facet to choose which field to group results by. You can create an entirely new facet with much more customization.

## Configuring the Custom Facet

1. Click the _Add_ icon (![Add icon](../../../images/icon-add-app.png)) at the top of the page.

1. Locate the Custom Facet and drag it to the collection of facets on the left. The Custom Facet is found under the Search section of widgets.

   ![Find the custom facet under the search widgets.](custom-facet/images/01.png)

1. Click the facet's _Options_ icon (![Options icon](../../../images/icon-app-options.png)) and click *Configuration*.

   ![Click on the Configuration option.](custom-facet/images/02.png)

   The Custom Facet has several configuration options which are described below.

1. When finished setting options, click the *Save* button.

**Display Settings:** Choose between *Default*, *Compact Layout*, and *Label Layout*. The Default layout shows checkboxes next to each term but the Compact layout does not. The Label layout shows small clickable labels for each term.

Advanced Configuration contains additional options: 

**Aggregation Field:** Enter the name of the indexed field to aggregate results by. This must be a non-analyzed keyword field. See below for more information.

**Custom Heading:** Enter the heading to display for this facet. If not set, the aggregated field name is displayed.

**Custom Parameter Name:** Specify a URL parameter name for selected values. If not set, the aggregated field name is used.

**Max Terms:** Set the maximum number of facet terms to display, regardless of how many matching terms are found for the facet.

**Frequency Threshold:** Set the minimum frequency required for terms to appear in the list of facet terms. For example, if the frequency threshold of a facet is set to 3, terms with two matching results don't appear in the term result list.

**Display Frequencies:** Choose whether or not to display the term frequencies.

**Federated Search Key:** Enter the key of an alternate Search this widget is participating on. If not set, this widget participates on the default search. This value is typically the name of an application-defined index.


## Finding Indexed Fields

To use the Custom Facet, you must know which non-analyzed keyword field to use in the configuration. 

```tip::
   Elasticsearch supports indexing fields in multiple ways. Some text fields can be used as keyword fields if they're nested ``raw`` `multi-fields <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/multi-fields.html>`__ in the mapping, or if the field is mapped in an additional separate field mapping as ``fieldName_sortable`` (as a ``keyword``). See the example below on creating facets for Custom Fields, as it leverages the Elasticsearch multi-field concept.
```

To browse the entire list of available fields, inspect the field mappings from *Control Panel* &rarr; *Configuration* &rarr; *Search* (click the *Field Mappings* tab). Here you'll see numerous indexes. The Liferay Assets you're likely interested in are indexed to the [company index](../../liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication.md#liferay-dxp-decide-which-indexes-to-replicate-from-the-remote-cluster), which looks is named similarly to `liferay-20101` (`20101` is the Company ID).

Alternatively, use your search engine's API to browse the mappings. In Elasticsearch you can access the field mappings from your terminal using cURL to call the [Get Mapping API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/indices-get-mapping.html):

```tip::
   `Kibana's <../../liferay-enterprise-search/monitoring-elasticsearch.md>`__ Dev Tools console is more convenient for making Elasticsearch API calls than cURL.
```

 ```bash
curl -X GET "localhost:9200/_mapping/LiferayDocumentType"?pretty
 ```

Solr uses the [ListFields API](https://lucene.apache.org/solr/guide/6_6/schema-api.html#SchemaAPI-ListFields):

```bash
curl http://localhost:8983/solr/liferay/schema/
```

Here's a snippet of output from the Elasticsearch example:

```json
"ddmStructureKey": {
  "store": true,
  "type": "keyword"
},
"ddmTemplateKey": {
  "store": true,
  "type": "keyword"
},
"defaultLanguageId": {
  "store": true,
  "type": "keyword"
},
"description": {
  "store": true,
  "term_vector": "with_positions_offsets",
  "type": "text"
},
"discussion": {
  "store": true,
  "type": "keyword"
},
```

```tip::
   ```

## Example: Creating a Facet for a Custom Field

When you create a [Custom Field](./../../../system-administration/configuring-liferay/adding-custom-fields.md), it's _Searchable as Keyword_ by default. After re-indexing you can see the field. The field itself is a text field, called something like `expando__keyword__custom_fields__Enabled` (if you name the field _Enabled_ in the Custom Fields UI), but it contains a nested field mapping for creating a separate `raw` keyword field. Here's the query you can run in Kibana to inspect the text field's mapping (replace the Company Id in the index name):

```bash 
GET /liferay-20097/_mapping/field/expando__keyword__custom_fields__Enabled
```

The returned JSON looks like 

```json
{
  "liferay-20097" : {
    "mappings" : {
      "expando__keyword__custom_fields__Enabled" : {
        "full_name" : "expando__keyword__custom_fields__Enabled",
        "mapping" : {
          "expando__keyword__custom_fields__Enabled" : {
            "type" : "text",
            "store" : true,
            "fields" : {
              "raw" : {
                "type" : "keyword"
              }
            },
            "analyzer" : "keyword_lowercase"
          }
        }
      }
    }
  }
}
```

To see all the raw fields, query the index for `*.raw` fields:

```bash 
GET /liferay-20097/_mapping/field/*.raw
```


Setting a custom field to searchable means that the value of the field is indexed when the entity (such as User) is modified. Only java.lang.String fields can be made searchable. Note that when an field is newly made searchable, the indexes must be updated before the data is available to search. 


Use Custom Fields to aggregate facet terms by shared non-analyzed keyword field values.
