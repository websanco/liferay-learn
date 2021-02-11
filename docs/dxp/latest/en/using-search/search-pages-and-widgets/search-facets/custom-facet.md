# Custom Facet

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

Use Custom Fields to aggregate facet terms by shared non-analyzed keyword field values.
