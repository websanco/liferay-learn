Elasticsearch
=============

Elasticsearch is the highly scalable, full-text search engine Liferay uses by default. Elasticsearch is bundled with Liferay for non-production purposes. In production, Liferay requires Elasticsearch running on separate remote server.

```important::
   Always check the `compatibility matrix <https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix>`_ for the Elasticsearch version and server configuration compatible with your Liferay version.
```

```important:
   Liferay 7.2 and 7.3 include support for Elasticsearch 7 and securing authenticated, encrypted Elasticsearch connections. Securing the Elasticsearch 6 connector (available on Liferay 7.2) requires an additional module only available with a `Liferay Enterprise Search subscription <./../liferay_enterprise_search.rst>`_.
```

Installing
~~~~~~~~~~

-  `Getting Started <./elasticsearch/getting-started-with-elasticsearch.md>`__

-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/installing-elasticsearch`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/connecting-to-elasticsearch`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/exercise-installing-elasticsearch`

- `[Clustering Liferay]` `Add a Search Engine to a Liferay Cluster <../../installation-and-upgrades/setting-up-liferay-dxp/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md#prepare-a-search-engine>`__

-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/troubleshooting-elasticsearch-installation`

Install an Older Elasticsearch Version
______________________________________

- Elasticsearch 6 (Coming Soon!)

Securing
~~~~~~~~

Coming Soon!

Upgrading
~~~~~~~~~

Coming Soon!

Configuring
~~~~~~~~~~~

Coming Soon!

Liferay Enterprise Search
~~~~~~~~~~~~~~~~~~~~~~~~~

Coming Soon!

.. toctree::
   :maxdepth: 2

   elasticsearch/getting-started-with-elasticsearch.md
   elasticsearch/installing-elasticsearch.md
   elasticsearch/connecting-to-elasticsearch.md
   elasticsearch/securiing-elasticsearch.md
   elasticsearch/troubleshooting-elasticsearch-installation.md
   elasticsearch/exercise-installing-elasticsearch.md
   elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md
