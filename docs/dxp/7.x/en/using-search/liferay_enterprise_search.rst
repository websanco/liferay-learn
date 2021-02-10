Liferay Enterprise Search
=========================

.. toctree::
   :maxdepth: 4

   liferay-enterprise-search/cross_cluster_replication.rst
   liferay-enterprise-search/learning-to-rank.md

A Liferay Enterprise Search (LES) subscription gets you additional features beyond what's available out of the box with your Liferay DXP subscription. It includes

-  :doc:`/using-search/liferay-enterprise-search/cross_cluster_replication`
-  :doc:`/using-search/liferay-enterprise-search/learning-to-rank`
-  Monitoring Elasticsearch

.. important::
   Always check the `LES compatibility matrix <https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search>`__ for compatibility information.

X-Pack is an `Elasticsearch extension <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/setup-xpack.html>`__ for securing and monitoring Elasticsearch clusters. If you use Elasticsearch, you should `secure it with X-Pack <./installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md>`__. Elasticsearch 7 bundles these security features, and Liferay has followed suit. Therefore, security is bundled with the Liferay Connector to Elasticsearch 7, and no LES subscription is necessary. Contact `Liferay's Sales department for more information <https://www.liferay.com/contact-us#contact-sales>`__.

To use the LES applications with Liferay,

#. Get an `Enterprise Search subscription <https://help.liferay.com/hc/en-us/articles/360014400932>`__.

#. You'll receive a license for X-Pack monitoring. Install it on your Elasticsearch servers.

   **Note:** If using Elasticsearch 6, you'll also need a LES subscription for X-Pack security.

#. Download and install the LES apps you purchased. Find them in the `Help Center Downloads page <https://customer.liferay.com/downloads>`__, choosing Enterprise Search from the Product drop-down menu.

#. Configure the connectors with the proper credentials, encryption information, and settings.

#. Restart Elasticsearch. These steps require a full cluster restart.

More detailed installation instructions are available in the article for each
LES feature.

.. note::
   Out of the box, X-Pack comes with a `30-day trial <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/start-trial.html>`__.  This can be useful if there's a delay between your subscription and receipt of your production X-Pack license.

Cross Cluster Replication
-------------------------

-  :doc:`/using-search/liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication`
-  :doc:`/using-search/liferay-enterprise-search/cross-cluster-replication/configuring-an-example-ccr-installation-replicating-between-data-centers`
-  :doc:`/using-search/liferay-enterprise-search/cross-cluster-replication/configuring-ccr-in-a-remote-leader-data-center`
-  :doc:`/using-search/liferay-enterprise-search/cross-cluster-replication/configuring-ccr-in-a-local-follower-data-center`
-  :doc:`/using-search/liferay-enterprise-search/cross-cluster-replication/ccr-basic-use-case-config-reference`
-  :doc:`/using-search/liferay-enterprise-search/cross-cluster-replication/troubleshooting-cross-cluster-replication`

Learning to Rank
----------------

-  :doc:`/using-search/liferay-enterprise-search/learning-to-rank`

