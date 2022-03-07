# 検索サービス（Elasticsearch）

Elasticsearchサービスは、Liferay DXPアプリケーションのテキスト検索エンジンです。 これは、外部のインターネットではなく、アプリケーションの他のサービスとのみ通信するプライベートサービスです。

![図1：Elasticsearchサービスは、DXP Cloudで利用可能なサービスの1つです。](./search-service/images/01.png)

詳細は、 [Search service limitations](../reference/platform-limitations.md#search-service) のセクションを参照してください。

<a name="configurations" />

## 設定

DXP Cloudのサービスはデフォルトで適切に機能するように微調整されていますが、Elasticsearchをさらに設定する必要がある場合があります。 これを行うには、 適切な`［configs/{ENV}/config/］`フォルダー内にYMLファイルを含めることができます。 変更をデプロイすると、ファイルが自動的にサービスに挿入され、デフォルトの設定が上書きされます。 以下は、 正しいフォルダー内のそのようなファイルのフォルダー構造の例です：

    search
    ├── configs
    │   └── common
    │       └── config
    │           └── elasticsearch.yml
    └── LCP.json

```{important}
Elasticsearchの設定には、［elasticsearch.yml］設定ファイルを使用する必要があります。 UIからElasticsearchを設定すると、デプロイ毎に上書きされます。
```

```{warning}
検索サービスの［LCP.json］ファイルには、デフォルトで次の値が含まれています：［"podManagementPolicy":"Parallel"］ この値を［LCP.json］から削除すると、複数のクラスタインスタンスがある場合に検索サービスの起動に失敗することがあります。なぜなら、Elasticsearch のノードが相互に接続しないと正常に起動しないからです。
```

環境の [［service stack version］](../reference/understanding-service-stack-versions.md) が、まだバージョン `［4.x.x］`にアップグレードされていない場合、設定ファイルをどのフォルダに追加しなければならないかに影響を及ぼします。

| **サービススタックバージョン** | **設定フォルダー** |
| ----------------- | --------------------------------- |
| **3.x.x** | `lcp/search/config /{ENV}/`       |
| **4.x.x** | `search/configs /{ENV}/ config /` |

<a name="scripts" />

## スクリプト

スクリプトを使用して、より広範なカスタマイズを行うことができます。 ただし、これを行う際は注意してください。 なぜなら、これは検索サービスをカスタマイズする最も強力な方法であり、望ましくない副作用を引き起こす可能性があるからです。 `［scripts/configs/{ENV}/scripts/］`フォルダーにある `［.sh］` ファイルは、サービスを開始する前に実行されます。 たとえば、すべてのログファイルを削除するスクリプトを含めるには、次のディレクトリ構造に配置します：

    search
    ├── configs
    │   └── common
    │       └── scripts
    │           └── elasticsearch.yml
    └── LCP.json

環境の [［service stack version］](../reference/understanding-service-stack-versions.md) が、まだバージョン `［4.x.x］`にアップグレードされていない場合、どのフォルダにスクリプトを追加しなければならないかに影響を及ぼします：

| **サービススタックバージョン** | **スクリプトフォルダ** |
| ----------------- | ---------------------------------- |
| **3.x.x** | `lcp/search/script /{ENV}/`        |
| **4.x.x** | `search/configs /{ENV}/ scripts /` |

<a name="deploying-a-license-to-the-search-service" />

## Search Serviceへのライセンスの展開

ライセンスを検索サービスにデプロイするには、パス `［search/configs/{ENV}/license/］` を作成し、そこにライセンスファイルを配置する必要があります。

環境の [［service stack version］](../reference/understanding-service-stack-versions.md) が、まだバージョン `［4.x.x］`にアップグレードされていない場合、どのフォルダにライセンスファイルを追加しなければならないかに影響を及ぼします：

| **サービススタックバージョン** | **ライセンスフォルダ** |
| ----------------- | ---------------------------------- |
| **3.x.x** | `lcp/search/license /{ENV}/`       |
| **4.x.x** | `search/configs /{ENV}/ license /` |

<a name="environment-variables-reference" />

## 環境変数リファレンス

Elastisearchのすべての環境変数とその他の設定形式は、 [［official Elastisearch documentation］](https://www.elastic.co/guide/index.html) にあります。 このような設定と環境変数は、それぞれ `［configs/{ENV}/config/］` ディレクトリと `［LCP.json］`で設定できます。 例は次のとおりです：

| 名前             | Value           | 説明              |
| -------------- | --------------- | --------------- |
| `ES_JAVA_OPTS` | `-Xms4g -Xmx4g` | ESインスタンスのJava設定 |

<a name="additional-information" />

## 追加情報

* [LCP JSONによる設定](../reference/configuration-via-lcp-json.md)
