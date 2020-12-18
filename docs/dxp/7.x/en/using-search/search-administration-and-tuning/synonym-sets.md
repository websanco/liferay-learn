# Synonym Sets

> **Subscribers**

Synonym Sets are administrator-created groups of words or phrases with the same meaning. If a User searches for a certain keyword or phrase, the synonymous terms in your set are also searched. 

For example, a user might search for the word "US". Most likely, the user would want search results that also included synonyms such as *America*, *U.S.A*, *United States*, etc. By creating Synonym Sets, you can ensure your users get the most out of their searches.

## Requirement and Limitations

Synonym Sets are only supported when using Elasticsearch as your search engine. Learn about installing Elasticsearch in [Getting Started with Elasticsearch](../installing-and-upgrading-a-search-engine/elasticsearch/getting-started-with-elasticsearch.md).

Synonym Sets currently work with fields indexed in either of the following out-of-the-box locales: English or Spanish. Liferay assets with localizable fields in either of these two languages work with Synonym Sets.

The [`=>` format](https://www.elastic.co/guide/en/elasticsearch/guide/current/synonyms.html) supported in Elasticsearch is not supported through the Synonyms Set UI.

## Creating and Managing Synonym Sets

Create a synonym set by adding as many synonymous keywords to a set as you like. Once the synonym set is saved, searches in the same company scope (any site from the [Virtual Instance](../../system-administration/configuring-liferay/virtual-instances/understanding-virtual-instances.md) where the synonyms were configured) take effect.

To create a new synonym set,

1. From the Global Menu (![Global Menu](../../images/icon-applications-menu.png)) navigate to *Applications* &rarr; *Search Tuning* &rarr; *Synonyms*.

   ![Navigate to the Synonyms section in the Applications menu](synonym-sets/images/01.png)

1. Click the _Add_ icon (![Click on the add icon](../../images/icon-add.png)) to add a new Synonym Set.

1. Enter the list of synonyms in the set. The input of a synonym is accomplished by clicking *Enter* or by entering a comma.

   ![Type your different synonym words into the set.](synonym-sets/images/02.png)

1. You can delete synonym by clicking on the *X* next to it. When you finish your set, click *Publish*.

1. To edit or delete a set, click the _Options_ icon (![Click on the options icon.](../../images/icon-options.png)) and click on *Edit* or *Delete*.

   ![Click on edit or delete to make changes.](synonym-sets/images/03.png)

   Once your synonym set is published, it is ready to be used.

## Using Synonym Sets

You can test your synonym set by searching for one of the synonym keywords you saved in the set. Results matching the keyword and any synonyms are returned in the Search Results widget.

![Try searching for a synonym from your set.](synonym-sets/images/04.png)

In the example above, this blog article about a lunar rover does not contain the word "LRV" but is now returned as a search result match. Note that the synonym is also highlighted.
