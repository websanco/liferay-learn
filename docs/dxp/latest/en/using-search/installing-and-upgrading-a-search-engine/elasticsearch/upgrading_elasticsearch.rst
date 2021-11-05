Upgrading Elasticsearch
=======================

.. toctree::
   :maxdepth: 1

   upgrading-elasticsearch/upgrading-search-with-liferay.md
   upgrading-elasticsearch/upgrading-to-elasticsearch-7.md
   upgrading-elasticsearch/backing-up-elasticsearch.md

Liferay supports Elasticsearch 7. The `compatibility matrix <https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix>`__ provides the latest support details.

..important::
   Solr integration is deprecated as of Liferay 7.3, replaced by Elasticsearch integration. Migrating to Elasticsearch requires `setting up Elasticsearch <./getting-started-with-elasticsearch.md>`_ and `connecting Liferay <./connecting-to-elasticsearch.md>`_ to it.

.. important::
   Elasticsearch 6.x is not supported on Liferay 7.3+.

Before planning your upgrade, read `Upgrading Search with Liferay <./upgrading-elasticsearch/upgrading-search-with-liferay.md>`_. It provides an overview of the steps required to upgrade older Liferay/Elasticsearch systems to the latest supported search stack. Always `back up your search indexes <./upgrading-elasticsearch/backing-up-elasticsearch.md>`__ before `upgrading Elasticsearch <./upgrading-elasticsearch/upgrading-to-elasticsearch-7.md>`__.

-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-search-with-liferay`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-to-elasticsearch-7`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/backing-up-elasticsearch`
