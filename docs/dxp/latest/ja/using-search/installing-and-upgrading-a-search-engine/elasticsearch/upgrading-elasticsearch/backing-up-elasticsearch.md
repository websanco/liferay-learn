# Elasticsearchのバックアップ

[Elasticsearchレプリカ](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index-modules.html#index-modules-settings)は 、ノードがダウンするのを防ぎますが、壊滅的な障害が発生した場合には役立ちません。 その時に役立つのは、適切なバックアップ習慣だけです。

Elasticsearchインデックスをバックアップして復元のテストを行う適切な機会は、[アップグレード](./upgrading-search-for-liferay-73.md)前です。 実際、[検索の調整インデックス](#backing-up-and-restoring-search-tuning-indexes)のスナップショットを使用して、新しいElasticsearchサーバーをセットアップするときに、以前の同義語セットと結果ランキングのインデックスを再作成できます。 このアプローチを試みる前に、[スナップショットとバージョンの復元の互換性](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshot-restore.html#snapshot-restore-version-compatibility)に関するElasticsearchのドキュメントを読んでください。

```{tip}
It's convenient to create and manage snapshots via the [Kibana 7.x UI](https://www.elastic.co/guide/en/kibana/7.x/snapshot-repositories.html)_.
```

次の3つの手順でElasticsearchクラスターをバックアップし、バックアップの復元をテストします。

1.  リポジトリを作成します

2.  Elasticsearchクラスターのスナップショットを作成します

3.  スナップショットから復元します

<!-- end list -->

```{note}
For more detailed information, refer to Elastic's [Elasticsearch administration guide](https://www.elastic.co/guide/en/elasticsearch/guide/master/administration.html), and in particular to the [Snapshot and Restore module](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshot-restore.html).
```

## リポジトリの作成

まず、スナップショットを保存する[リポジトリを作成](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshots-register-repository.html)します。 サポートされているリポジトリタイプは次のとおりです。

  - ネットワークファイルシステムやNASなどの共有ファイルシステム
  - Amazon S3
  - HDFS（Hadoop Distributed File System）
  - Azureクラウド

スナップショットを共有ファイルシステムに保存する場合は、最初に、[`path.repo`設定](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshots-register-repository.html#snapshots-filesystem-repository)を使用して、各ノードの`elasticsearch.yml`に共有ファイルシステムへのパスを登録します。 例:

``` yaml
path.repo: ["path/to/shared/file/system/"]
```

リポジトリをホストするフォルダへのパスを登録した後（フォルダが存在していることを確認してください）、`PUT`コマンドでリポジトリを作成します。 例:

``` bash
PUT /_snapshot/test_backup
{
  "type": "fs",
  "settings": {
    "location": "/path/to/shared/file/system/"
  }
}'
```

`localhost:9200`をシステムの`hostname:port`に置き換え、`test_backup`を作成するリポジトリの名前に置き換え、`location`の設定値を共有ファイルシステムへの絶対パスに置き換えます。

リポジトリが正しく作成されていれば、コマンドは次のような結果を返します。

``` json
{"acknowledged":true}
```

リポジトリが存在しているので、スナップショットを作成します。

## クラスターのスナップショットを取得する

最も簡単なスナップショットのアプローチは、[クラスター内のすべてのインデックスのスナップショット](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshots-take-snapshot.html)を作成することです。 例:

``` bash
PUT /_snapshot/test_backup/snapshot_1
```

スナップショットコマンドが成功すると、次の結果が返されます。

``` json
{"accepted":true}
```

スナップショットを特定のインデックスに制限することもできます。 たとえば、Liferay Enterprise Search Monitoringを使用しているが、スナップショットからモニタリングインデックスを除外したい場合があります。 スナップショットに含めるインデックスを明示的に宣言できます。 例:

``` bash
PUT /_snapshot/test_backup/snapshot_2
{ "indices": "liferay-0,liferay-20116" }
```

すべてのインデックスとインデックスメトリクスを一覧表示するには、次のコマンドを実行します。

``` bash
GET /_cat/indices?v
```

インデックスメトリクスの例：

``` bash
health status index                                              uuid                   pri rep docs.count docs.deleted store.size pri.store.size
green  open   liferay-20101-search-tuning-rankings               ykbNqPjkRkq7aCYnc7G20w   1   0          7            0      7.7kb          7.7kb
green  open   liferay-20101-workflow-metrics-tokens              DF-1vq8IRDmFAqUy4MHHPQ   1   0          4            0       26kb           26kb
green  open   liferay-20101                                      QKXQZeV5RHKfCsZ-TYU-iA   1   0     253015          392     43.1mb         43.1mb
green  open   liferay-20101-workflow-metrics-sla-task-results    SrWzmeLuSKGaIvKrv4WmuA   1   0          4           72     30.6kb         30.6kb
green  open   liferay-20101-workflow-metrics-processes           Ras8CH0PSDGgWSyO3zEBhg   1   0          1            0     49.3kb         49.3kb
green  open   liferay-20101-workflow-metrics-nodes               bcdKKgDySeWf4BJnmMzk6A   1   0          4            0     10.5kb         10.5kb
green  open   liferay-20101-workflow-metrics-sla-process-results VJrNOpJWRoeTaJ-sBGs_vA   1   0          3           91     47.4kb         47.4kb
green  open   liferay-20101-workflow-metrics-instances           OgJMyD5ZQIi2h0xUTSjezg   1   0          3            0     62.4kb         62.4kb
green  open   liferay-0                                          jPIEOfZhSCKZSWnY0L65RQ   1   0     253114          491     50.1mb         50.1mb
green  open   liferay-20101-search-tuning-synonyms               pAUN8st1RmaV1NxXtj-Sig   1   0          1            0      4.1kb          4.1kb
```

```{note}
Elasticsearch uses a *smart snapshots* approach. To understand what that means, consider a single index. The first snapshot includes a copy of the entire index, while subsequent snapshots only include the delta between the first, complete index snapshot and the current state of the index.
```

最終的には、リポジトリに多数のスナップショットが作成されることになります。スナップショットに名前を付けたとしても、スナップショットに含まれているものを忘れてしまう可能性があります。 Elasticsearch APIを使用して説明を取得できます。 例:

``` bash
GET /_snapshot/test_backup/snapshot_1
```

以下が返されます

``` json
{"snapshots":[
    {"snapshot":"snapshot_1",
    "uuid":"WlSjvJwHRh-xlAny7zeW3w",
    "version_id":6.80399,
    "version":"6.8.2",
    "indices":["liferay-20099","liferay-0","liferay-47206"],
    "state":"SUCCESS",
    "start_time":"2018-08-15T21:40:17.261Z",
    "start_time_in_millis":1534369217261,
    "end_time":"2018-08-15T21:40:17.482Z",
    "end_time_in_millis":1534369217482,
    "duration_in_millis":221,
    "failures":[],
    "shards":{
        "total":3,
        "failed":0,
        "successful":3

        }
    }
]}
```

スナップショット情報には、インデックスの時間範囲が含まれています。

スナップショットを破棄したい場合は、`DELETE`コマンドを使用します。

``` bash
DELETE /_snapshot/test_backup/snapshot_1
```

スナップショットにすべてのインデックスを含めると、多くの時間とストレージを消費する可能性があります。 誤ってスナップショットの作成を開始した場合（例えば、特定のインデックスにフィルターをかけようとしたが、すべてのインデックスを含めてしまったなど）、`DELETE`コマンドを使用してスナップショットの処理をキャンセルすることができます。 名前でスナップショットを削除すると、スナップショットプロセスが終了し、部分的なスナップショットがリポジトリから削除されます。

## スナップショットからの復元のテスト

壊滅的な障害が発生した場合、スナップショットから[検索インデックスを復元](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshots-restore-snapshot.html)することができなければ、スナップショットは何の役にも立ちません。 `_restore` APIを使用して、すべてのスナップショットのインデックスを復元します。

``` bash
POST /_snapshot/test_backup/snapshot_1/_restore
```

スナップショットインデックスから既存のインデックスにデータを復元する必要がある場合は、別の名前でインデックスを復元してから、データを既存のインデックスに再インデックスします。 復元コマンドを特定のインデックスに制限するには、`indices` オプションを使用します。 `rename_pattern` および `rename_replacement` オプションを使用して、復元されたインデックスの名前を変更します。

``` bash
POST /_snapshot/test_backup/snapshot_1/_restore
{
    "indices": "liferay-20116",
    "rename_pattern": "(.+)",
    "rename_replacement": "restored-$1"
}
```

これにより、スナップショットから`liferay-20116`という名前のインデックスのみが復元され、名前が`restored_liferay-20116`に変更されます。 クラスターに復元すると、バックアップされたデータを既存の`liferay-20116`インデックスに復元する`_reindex` API呼び出しを実行するために使用できます。

``` bash
POST _reindex/
{
    "dest": {
      "index": "liferay-20116"
    },
    "source": {
      "index": "restored_liferay-201116"
    }
}
```

スナップショットプロセスのキャンセルと同様に、`DELETE` コマンドを使用して、誤った復元プロセスをキャンセルすることができます。

``` bash
DELETE /restored_liferay-20116index_3
```

本番環境システムでの壊滅的な障害は誰もが望みませんが、スナップショットを取得してインデックスを復元するためのElasticsearchのAPIを使用すれば、障害が発生した場合に検索クラスターを復元できるので安心です。 詳細とオプションについては、Elasticの[スナップショットと復元のドキュメンテーション](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshot-restore.html)を参照してください。

## 検索の調整インデックスのバックアップと復元

Elasticsearchインデックスのスナップショットを作成することを強くお勧めします。プライマリストレージ形式として機能するインデックス（[同義語セット](../../../search-administration-and-tuning/synonym-sets.md)や[結果ランキング](../../../search-administration-and-tuning/result-rankings.md)など）の場合は特にお勧めします。 データベースには、これらのアプリケーションのレコードはありません。

Elasticsearchの[スナップショットおよび復元](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshot-restore.html)機能を使用して、検索の調整インデックスをバックアップおよび復元できます。

1.  システムのどこかに`elasticsearch_local_backup`というフォルダを作成します。 Elasticsearchがフォルダへの読み取りおよび書き込みアクセス権を持っていることを確認します（例： `/path/to/elasticsearch_local_backup`）。

2.  以下を

    ``` yaml
    path.repo: [ "/path/to/elasticsearch_local_backup" ]
    ```

    Elasticsearchクラスター内の[すべてのマスターノードとデータノード](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshots-register-repository.html#snapshots-filesystem-repository)の`elasticsearch.yml`に追加します。 Elasticsearchをアップグレードする場合は、スナップショットリポジトリへのパスがアップグレード前とアップグレード後のElasticsearch構成で同じであることを確認してください。

3.  すべてのElasticsearchノードを再起動します。

4.  [スナップショットリポジトリを登録します](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshots-register-repository.html)。 次の`snapshot` APIリクエストを実行できます（たとえば、Kibanaの開発ツールコンソールから）。

    ``` json
    PUT /_snapshot/elasticsearch_local_backup
    {
      "type": "fs",
      "settings": {
        "location": "/path/to/elasticsearch_local_backup"
      }
    }

    ```

    新しいElasticsearchバージョンにアップグレードする場合は、アップグレード後のElasticsearchでこれと同じコマンドを使用して、スナップショットリポジトリを登録できます。

5.  [スナップショットを作成します](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshots-take-snapshot.html)：

    ``` json
    PUT /_snapshot/elasticsearch_local_backup/snapshot1?wait_for_completion=true
    {
      "indices": "liferay-20101-search-tuning*",
      "ignore_unavailable": true,
      "include_global_state": false
    }
    ```

    すべてのLiferayインデックスのスナップショットを作成する場合は、代わりに`"indices": "liferay*,workflow-metrics*"`を使用できます。 アップグレードシナリオを使用している場合は、同義語セットインデックスや結果ランキングインデックスなど、データベースから再作成できないインデックスのみのスナップショットを作成するほうが合理的な場合があります。

6.  別の名前を使用してスナップショットから特定のインデックスを[復元](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshots-restore-snapshot.html)するには、次のような`restore` API呼び出しを実行します。

    ``` json
    POST /_snapshot/elasticsearch_local_backup/snapshot1/_restore
    {
      "indices": "liferay-20101-search-tuning-synonyms,liferay-20101-search-tuning-rankings",
      "ignore_unavailable": true,
      "include_global_state": false,
      "rename_pattern": "(.+)",
      "rename_replacement": "restored_$1",
      "include_aliases": false
    }
    ```

    ここで、`indices`には、復元元のスナップショットされているインデックス名を設定します。 上記の呼び出しによるインデックスは、`rename_pattern` および `rename_replacement` 正規表現に従って、`restored_liferay-20101-search-tuning-rankings` および `restored_liferay-20101-search-tuning-synonyms` として復元されます。

Sidecar/Embeddedモードで実行中に同義語セットまたは結果ランキングを追加した場合、Elasticsearch 7へのリモートモード接続を構成して完全なインデックス再作成を実行すると、これらの検索の調整が消えます。

既存の*検索の調整*インデックスドキュメントを復元するには、以下のようにElasticsearchの[インデックスの再構築 API](https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-reindex.html#docs-reindex)を使用できます。

``` json
POST _reindex/
{
 "dest": {
   "index": "liferay-20101-search-tuning-synonyms"
 },
 "source": {
   "index": "restored_liferay-20101-search-tuning-synonyms"
 }
}
```

`liferay-20101-search-tuning-rankings`インデックスに同じコマンドを実行します。 アップグレード後のElasticsearchインストールで両方のリクエストを実行すると、アップグレード前のシステムから同義語セットと結果ランキングのデータが復元されるようになりました。

### 検索の調整インデックス名

すぐに使用できる検索の調整インデックス名は、Liferayのバージョンとパッチレベルによって異なります。

| Liferayのバージョンとパッチ         | 検索の調整インデックス                                                                                                      |
| ------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| Liferay DXP 7.2 SP2/FP5以下 | `liferay-search-tuning-rankings`<br />`liferay-search-tuning-synonyms-liferay-<companyId>`           |
| Liferay DXP 7.2 SP3/FP8以降 | `liferay-<companyId>-search-tuning-rankings`<br />`liferay-<companyId>-search-tuning-synonyms` |
| Liferay DXP 7.3、すべてのパッチ   | `liferay-<companyId>-search-tuning-rankings`<br />`liferay-<companyId>-search-tuning-synonyms` |

`<companyId>`（例えば`20101`）は、データベース内の指定された`Company`レコードに属しています。 UIでは*インスタンスID*として表示され、[仮想インスタンス](../../../../system-administration/configuring-liferay/virtual-instances/understanding-virtual-instances.md)を表します。

## 次のステップ

[Elasticsearchをアップグレード](./upgrading-to-elasticsearch-7.md)する場合は、今すぐアップグレードできます。

## 追加情報

[Search Administration and Tuning](../../../search_administration_and_tuning.rst)
