# Elasticsearchインストールのトラブルシューティング

リモートモードで[LiferayにElasticsearch](./getting-started-with-elasticsearch.md)をセットアップしたが、LiferayがElasticsearchに接続していない場合は、次のことを確認してください。

## クラスター名

`elasticsearch.yml`の`cluster.name`プロパティの値は、Liferay Elasticsearchコネクターで設定された`clusterName`プロパティと一致する必要があります。

## トランスポートアドレス

Elasticsearchコネクター設定の`transportAddresses`プロパティの値には、Elasticsearchノードが実行されている有効なホストとポートが少なくとも1つ含まれている必要があります。 Liferayを組み込みモードで実行していて、スタンドアロンのElasticsearchノードまたはクラスターを起動すると、ポート`9300`が占有されていることが検出され、ポート`9301`に切り替わります。 その後、LiferayのElasticsearchコネクターをリモートモードに設定すると、引き続きデフォルトのポート（`9300`）でElasticsearchを検索します。 クラスターのマスターノードとデータノードのアドレスがすべて記載されていることを確認してください。

[Connecting to Elasticsearch](./connecting-to-elasticsearch.md)では、コネクター設定オプションについて詳しく説明しています。

## ネットワークホストアドレス

Liferay 7.3では、バンドルされているElasticsearchサーバー（サイドカー）はデフォルトでポート`9201`で実行されます。 これは、ElasticsearchのデフォルトのHTTPポート（`9200`）を使用してリモートのElasticsearchインストールの`networkHostAddress`を設定しても、競合が発生しないことを意味します。 トランスポートアドレスと同様に、クラスターのすべてのマスターノードとデータノードのアドレスがすべて記載されていることを確認してください。

## クラスタースニッフィング（追加設定）

Elasticsearchクラスターは複数のノード [タイプ](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-node.html#modules-node)を持つことができます。 Elasticsearchコネクターでデフォルトで有効になっている[クラスタースニッフィング](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html)は、`transportAddresses`プロパティで設定された`data`ノードを検索します。 使用可能なものがない場合、コネクターはコンソールログに`NoNodeAvailableException`をスローする可能性があります。 クラスタースニッフィングを有効のままにする場合は、少なくとも1つの`data`ノードのトランスポートアドレスを常に「スニッフィング可能」に設定して、このエラーを回避します。

クラスタースニッフィングを無効にするには、`clientTransportSniff=false`を`.config`ファイルに追加するか、[システム設定]の[クライアント・トランスポート・スニフ]プロパティの選択を解除します。

## \[Docker\]接続が拒否されました

Liferay DXPコンテナは、接続を確立するためにElasticsearch IPを認識する必要があります。 Elasticsearchコンテナ名をElasticsearchサーバーのホストIPアドレスにマップする`/etc/hosts/`エントリを追加します。 これは、次のような引数を渡すことで、`docker run`フェーズ中に確立できます。

``` bash
--add-host elasticsearch:[IP address]
```

実行中のすべてのコンテナのIPアドレスを取得するには、以下を実行します。

``` bash
docker network inspect bridge
```

## 設定ファイル名

LiferayをElasticsearchに接続する際に問題が発生した場合（おそらくLiferayログに`NoNodeAvailableException`メッセージが表示されます）、最初に実行する手順の1つは、設定ファイルに適切な名前が付けられていることを確認することです。 認識できない設定ファイルは処理されません。 結果として生じるエラーは様々です。

## Elasticsearchの非推奨ログを無効にする

LiferayのElasticsearchコネクターで使用されるElasticsearch APIが廃止予定になる場合があります。 Liferayに必要な機能に影響がない場合でも、以下の警告ログメッセージが表示される可能性があります。

    [2019-07-16T14:47:05,779][WARN ][o.e.d.c.j.Joda           ] [
    ode_name]'y' year should be replaced with 'u'. Use 'y' for year-of-era. Prefix your date format with '8' to use the new specifier.
    [2019-07-16T14:47:06,007][WARN ][o.e.d.c.s.Settings       ] [
    ode_name][xpack.ssl.certificate] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
    [2019-07-16T14:47:06,007][WARN ][o.e.d.c.s.Settings       ] [
    ode_name][xpack.ssl.certificate_authorities] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
    [2019-07-16T14:47:06,008][WARN ][o.e.d.c.s.Settings       ] [
    ode_name][xpack.ssl.key] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
    [2019-07-16T14:47:06,463][WARN ][o.e.d.x.c.s.SSLService   ] [
    ode_name]SSL configuration [xpack.http.ssl] relies upon fallback to another configuration for [key configuration, trust configuration], which is deprecated.
    [2019-07-16T14:47:06,464][WARN ][o.e.d.x.c.s.SSLService   ] [
    ode_name]SSL configuration [xpack.security.transport.ssl.] relies upon fallback to another configuration for [key configuration, trust configuration], which is deprecated.
    1. 07-16T14:47:05,779][WARN ][o.e.d.c.j.Joda           ] [

これらの警告は機能上の問題を示すものではなく、無効にすることができます（方法については、[Deprecation Logging](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/logging.html#deprecation-logging)を参照してください）。
