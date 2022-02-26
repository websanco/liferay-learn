# Elasticsearchを開始する

> **互換性があります。** 互換性のあるElasticsearchのバージョン、利用可能なLiferayコネクタ、必要なLiferayのパッチレベルの詳細については、 [検索エンジン互換性マトリクス](https://help.liferay.com/hc/en-us/articles/360016511651) を参照してください。

一般的なLiferayシステムの検索インフラは以下のように構成されています。

* クラスターまたは単一ノード（オンプレミスまたはDockerコンテナ）で構成されたLiferayサーバー
* Elasticsearchサーバー（オンプレミスまたはDockerコンテナ）
* Liferayにデプロイされ、デプロイメントに適合するように構成されたElasticsearchコネクタアプリケーション。

```{tip}
   Additional features and intergrations are available with a [Liferay Enterprise Search](../../liferay-enterprise-search.md) subscription.
```

Liferayサーバーがすでに設置されていると仮定すると、残りの手順は次のとおりです。

1. 使用するElasticsearchサーバーとコネクタのバージョンを決定する

1. Elasticsearchサーバーをセットアップする

1. LiferayをElasticsearchに接続する

<a name="互換性のあるelasticsearchとコネクタのバージョンを見つける" />

## 互換性のあるElasticsearchとコネクタのバージョンを見つける

Liferay CE/DXPの各バージョンには、特定のElasticsearchのメジャーバージョン（6.xまたは7.xなど）と互換性のあるElasticsearchへのコネクタがバンドルされています。 Elasticsearchの新しいメジャーバージョンへのサポートを追加するために、Liferayは [マーケットプレイス](https://web.liferay.com/marketplace) を通じて追加のコネクタをリリースします。 これらは、すぐに使用できる（バンドルされた）コネクタのドロップイン代替品として使用できます。

[検索エンジンの互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360016511651) には、Liferayの各バージョンでサポートされている最新のElasticsearchコンポーネントのバージョンが表示されています。 ElasticsearchサーバーとElasticsearchへのコネクタは必須コンポーネントです。

```{warning}
   Liferayのインストールに含まれているLiferay Connector to Elasticsearchは、最新のコネクタではない可能性があります。 使用しているElasticsearchのバージョンに合わせて最新のコネクタを使用するようにしてください。 コネクタは、 [Liferay Marketplace](https://web.liferay.com/marketplace) で入手できます。
```

<a name="elasticsearchのインストール" />

## Elasticsearchのインストール

Elasticsearchは、ElasticsearchアーカイブまたはDockerイメージを使用してインストールできます。 サーバーのクラスターでも構成できます。 以下に、Elasticsearchホスティングのさまざまな例を示します。

* [アーカイブからElasticsearchをインストール](./installing-elasticsearch.md)
* [Dockerを使用してElasticsearchをインストール](./exercise-run-liferay-and-elasticsearch-using-docker.md)

また、[Liferayクラスタリングの例](../../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md)では、ElasticsearchでLiferayクラスターを使用する方法を示しています。

<a name="liferayをelasticsearchに接続する" />

## LiferayをElasticsearchに接続する

ElasticsearchへのコネクタはLiferayにバンドルされています。 Elasticsearchアプリケーションへの最新のLiferayコネクタはLiferay マーケットプレイスでも入手できます。 詳細は、 [Elasticsearchへの接続](./connecting-to-elasticsearch.md) を参照してください。

<a name="elasticsearchの保護" />

## Elasticsearchの保護

本番環境では、LiferayとElasticsearchの通信を保護する必要があります。 [Elasticsearchの保護](./securing-elasticsearch.md)では、認証と暗号化を設定する方法を説明し、Liferay Enterprise Search Monitoringに必要なPEM証明書の使用方法を示しています。

<a name="次のステップ" />

## 次のステップ

[Elasticsearchのインストール](./installing-elasticsearch.md) では、手動によるインストールと構成の各手順について説明しています。 これらの手順は、Dockerで実行している場合でも理解するのに役立ちます。 Docker上でElasticsearchとLiferayを動かすことから始めたいという方は、 [Exerciseをご覧ください。Dockerを使ってLiferayとElasticsearchを実行する](./exercise-run-liferay-and-elasticsearch-using-docker.md).
