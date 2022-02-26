Elasticsearch
=============

.. toctree:: :maxdepth: 2

   elasticsearch/getting-started-with-elasticsearch.md 
   elasticsearch/installing-elasticsearch.md elasticsearch/connecting-to-elasticsearch.md 
   elasticsearch/securing-elasticsearch.md 
   elasticsearch/exercise-run-liferay-and-elasticsearch-using-docker.md 
   elasticsearch/troubleshooting-elasticsearch-installation.md 
   elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md 
   elasticsearch/upgrading_elasticsearch.rst 
   elasticsearch/elasticsearch-connector-configuration-reference.md 
   elasticsearch/advanced-configuration-of-the-liferay-elasticsearch-connector.md

Elasticsearchは、Liferayがデフォルトで使用する、拡張性の高いフルテキスト検索エンジンです。 ElasticsearchはLiferayにバンドルされており、本番環境以外の目的で使用されます。 本番環境では、Liferayは別のリモートサーバーで動作するElasticsearchを必要とします。

.. 重要:: 常に `互換性マトリックス <https://help.liferay.com/hc/en-us/sections/360002103292-Compatibility-Matrix>`_   でLiferayのバージョンと互換性のあるElasticsearchのバージョンとサーバー構成を確認してください。


.. {important} Liferay 7.2および7.3では、Elasticsearch 7をサポートし、認証および暗号化されたElasticsearch接続を保護します。 Elasticsearch 6コネクタ（Liferay 7.2で利用可能）のセキュリティを確保するには、 `Liferay Enterprise Searchサブスクリプションでのみ利用可能な追加モジュールが必要です <.../liferay_enterprise_search.md>`_ 。

インストール
~~~~~~~~~~

-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/getting-started-with-elasticsearch`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/installing-elasticsearch`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/connecting-to-elasticsearch`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/exercise-run-liferay-and-elasticsearch-using-docker`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/troubleshooting-elasticsearch-installation`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/using-the-sidecar-or-embedded-elasticsearch`

- `[Clustering Liferay]` `Add a Search Engine to a Liferay Cluster <../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md#start-a-search-engine-server>`_  

Upgrading
~~~~~~~~~

-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading_elasticsearch`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-search-for-liferay-73`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/backing-up-elasticsearch`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/upgrading-elasticsearch/upgrading-to-elasticsearch-7`

保護する 
~~~~~~~~
- :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch`
旧バージョンのElasticsearchをインストールする ***_*_*****_*_*****_*_*****_*_*****_*_*****_*_*****_*_*****_*_*****_*_****_**
- Elasticsearch 6 for Liferay 7.2 (近日公開)
設定
~~~~~~~~~~~

-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/advanced-configuration-of-the-liferay-elasticsearch-connector`
-  :doc:`/using-search/installing-and-upgrading-a-search-engine/elasticsearch/elasticsearch-connector-configuration-reference`
