# 検索サービス（Elasticsearch）

Elasticsearchサービスは、Liferay DXPアプリケーションのテキスト検索エンジンです。 これは、外部のインターネットではなく、アプリケーションの他のサービスとのみ通信するプライベートサービスです。

![図1：Elasticsearchサービスは、DXP Cloudで利用可能なサービスの1つです。](./search-service/images/01.png)

## 設定

DXP Cloudのサービスはデフォルトで適切に機能するように微調整されていますが、Elasticsearchをさらに設定する必要がある場合があります。 これを行うには、 `config` フォルダー内にYMLファイルを含めることができます。 変更をデプロイすると、ファイルが自動的にサービスに挿入され、デフォルトの設定が上書きされます。 以下は、 `config` フォルダー内のそのようなファイルのフォルダー構造の例です。

    search
    ├── config
    │ └── elasticsearch.yml
    └── LCP.json

## スクリプト

スクリプトを使用して、より広範なカスタマイズを行うことができます。 ただし、その際は注意してください。 これは、検索サービスをカスタマイズする最も強力な方法であり、望ましくない副作用を引き起こす可能性があります。

`スクリプト` フォルダーにある `.sh` ファイルは、サービスを開始する前に実行されます。 たとえば、すべてのログファイルを削除するスクリプトを含めるには、次のディレクトリ構造に配置します。

    search
    ├──script
    │ └──remove-log-files.sh
    └──LCP.json

## Search Serviceへのライセンスの展開

ライセンスを検索サービスにデプロイするには、パス `lcp/search/license/common` を作成し、そこにライセンスファイルを配置する必要があります。

## 環境変数リファレンス

Elastisearchのすべての環境変数とその他の設定形式は、 [公式Elastisearchドキュメント](https://www.elastic.co/guide/index.html)ます。 このような設定と環境変数は、それぞれ `config` ディレクトリと `LCP.json`で設定できます。 例は次のとおりです。

| 名前             | Value           | 説明              |
| -------------- | --------------- | --------------- |
| `ES_JAVA_OPTS` | `-Xms4g -Xmx4g` | ESインスタンスのJava設定 |

## 追加情報

  - [Configuration via LCP JSON](../reference/configuration-via-lcp-json.md)
