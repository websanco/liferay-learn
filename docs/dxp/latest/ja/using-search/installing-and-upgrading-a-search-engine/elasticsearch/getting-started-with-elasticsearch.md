# Elasticsearchを開始する

> サポートされている最新のElasticsearchバージョン：7.9   
> 利用可能：Liferay DXP/CE 7.3およびLiferay DXP 7.2 SP1以降

一般的なLiferayシステムの検索インフラは以下のように構成されています。

  - クラスターまたは単一ノード（オンプレミスまたはDockerコンテナ）で構成されたLiferayサーバー
  - Elasticsearchサーバー（オンプレミスまたはDockerコンテナ）
  - Liferayにデプロイされ、デプロイメントに適合するように構成されたElasticsearchコネクタアプリケーション。

<!-- end list -->

```{tip}
Additional features and intergrations are available with a [Liferay Enterprise Search](../../liferay_enterprise_search.rst)_ subscription.
```

Liferayサーバーがすでに設置されていると仮定すると、残りの手順は次のとおりです。

1.  使用するElasticsearchサーバーとコネクタのバージョンを決定する

2.  Elasticsearchサーバーをセットアップする

3.  LiferayをElasticsearchに接続する

## 互換性のあるElasticsearchとコネクタのバージョンを見つける

Liferay CE/DXPの各バージョンには、特定のElasticsearchのメジャーバージョン（6.xまたは7.xなど）と互換性のあるElasticsearchへのコネクタがバンドルされています。 Elasticsearchの新しいメジャーバージョンへのサポートを追加するために、Liferayは[マーケットプレイス](https://web.liferay.com/marketplace)を通じて追加のコネクタをリリースします。 これらは、すぐに使用できる（バンドルされた）コネクタのドロップイン代替品として使用できます。

[Search Engine Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360016511651)には、Liferayの各バージョンでサポートされている最新のElasticsearchコンポーネントのバージョンが表示されています。 ElasticsearchサーバーとElasticsearchへのコネクタは必須コンポーネントです。

```{warning}
The Liferay Connector to Elasticsearch that your Liferay installation includes may not be the latest connector. Make sure to use the latest connector for the Elasticsearch version you're using. The connectors are available on [Liferay Marketplace](https://web.liferay.com/marketplace).
```

## Elasticsearchのインストール

Elasticsearchは、ElasticsearchアーカイブまたはDockerイメージを使用してインストールできます。 サーバーのクラスターでも構成できます。 以下に、Elasticsearchホスティングのさまざまな例を示します。

  - [アーカイブからElasticsearchをインストール](./installing-elasticsearch.md)
  - [Dockerを使用してElasticsearchをインストール](./exercise-installing-elasticsearch.md)

また、[Liferayクラスタリングの例](../../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md)では、ElasticsearchでLiferayクラスターを使用する方法を示しています。

## LiferayをElasticsearchに接続する

ElasticsearchへのコネクタはLiferayにバンドルされています。 Elasticsearchアプリケーションへの最新のLiferayコネクタはLiferay マーケットプレイスでも入手できます。 詳細については、[Connecting to Elasticsearch](./connecting-to-elasticsearch.md)を参照してください。

## Elasticsearchの保護

本番環境では、LiferayとElasticsearchの通信を保護する必要があります。 [Securing Elasticsearch](./securing-elasticsearch.md)では、認証と暗号化を設定する方法を説明し、Liferay Enterprise Search Monitoringに必要なPEM証明書の使用方法を示しています。

## 次のステップ

[Elasticsearchのインストール](./installing-elasticsearch.md)では、手動によるインストールと構成の各手順について説明しています。 これらの手順は、Dockerで実行している場合でも理解するのに役立ちます。 DockerでElasticsearchとLiferayを実行することから始めたい場合は、[Exercise Installing Elasticsearch](./exercise-installing-elasticsearch.md)を参照してください。
