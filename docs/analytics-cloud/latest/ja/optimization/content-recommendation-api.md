# コンテンツレコメンデーションAPI

Liferay Analytics CloudのコンテンツレコメンデーションAPIは、Liferay DXPインスタンス内のコンテンツとユーザーのインタラクションに基づいてコンテンツを提案します。 これは、Liferay DXPをAnalytics Cloudに接続すると、ユーザーがページを訪問するたびにインタラクションイベントが送信されるためです。 これらのイベントのそれぞれには、ユーザーが消費したコンテンツに関する情報が含まれています。 Analytics Cloudはこの情報を収集し、処理します。

コンテンツレコメンデーションAPIには、以下を有効にするサービスが含まれています。

-   ユーザーが閲覧している現在のタグに基づいて、類似したタグ（コンテンツ）を発見します。
-   ユーザーの経年的な興味に基づいてタグを発見する。

## 類似のタグを発見する

このサービスは、パラメータとして渡されたものと同様のタグ（用語）のリストを返します。 類似語のリストは重みでソートされ、JSONとして返されます。

ここがこのAPIのエンドポイントです。

**GET：** `{url}/api/1.0/interests/terms/related{?}`

ここにパラメータがあります。

`int page (defaultValue = 0)`: 結果のページ。 例えば、デフォルト値0は結果の最初のページを指定します。

`int size (defaultValue = 5)`: 各ページに含める結果の数。 デフォルト値 `5` は、各ページに5つの結果を指定します。

`List<String> terms`: 類似タグのリストを決定する際に使用するタグ。 これが唯一の必須パラメータです。

`double termWeightThreshold (defaultValue = 0.01)`: 関連語を決定するための関連度（重み）。 `0.01` のデフォルト値は、1%以上のウェイトを持つすべてのタグを返します。

一緒に、 `page` と `size` パラメータは、応答に含める類似の用語の数を制御します。 このAPIは最大100項まで返すことができることに注意してください。 条件数がその上限を超えた場合、API はエラーを返します。

例えば、関連する用語を含むJSONレスポンスは次のようになります。

``` json
{
  "_embedded": {
    "interest-terms": [
      "jquery",
      "html",
      "sql",
      "mysql",
      "java"
    ]
  },
  "page": {
    "number": 0,
    "size": 5,
    "totalPages": 7,
    "totalElements": 35
  }
}
```

## 興味に基づいたタグの発見

このサービスは、特定のユーザーに関連するタグ（用語）のリストを返します。 そのユーザーのIDをパラメータとして渡す必要があります。 類似語のリストは重みでソートされ、JSONとして返されます。

ここがこのAPIのエンドポイントです。

**GET：** `{url}/api/1.0/interests/terms/{userId}`

また、オプションで使用できるパラメータは3つあります。 これらのパラメータを使用して、API が返す用語を微調整することができます。 トピックは用語のグループであることに注意してください。

`int termsPerTopic (defaultValue = 3)`: トピック/サブジェクトごとに考慮する用語の数。

`double termWeightThreshold (defaultValue = 0.01)`: 関心のある用語を決定するための関連性レベル。 `0.01` のデフォルト値は、1%以上の重みを持つすべての項を返します。

`int topicsLength (defaultValue = 3)`: 検討するトピックの数。

`termsPerTopic` を減らし、 `topicsLength` を増やすことで、主題のバリエーション（異なるトピックの用語がユーザーに推奨されること）が増える可能性があります。

ここでは、ユーザIDのみを含むリクエストの例を示します。

    {url}/api/1.0/interests/terms/953be104-5540-abf8-59b8-55f895200acc

そして、JSONでのレスポンス例です。

``` json
{
  "_embedded": {
    "interest-topics": [
      {
        "terms": [
          {
            "weight": 0.0945945945945946,
            "keyword": "javascript"
          },
          {
            "weight": 0.08648648648648649,
            "keyword": "jquery"
          },
          {
            "weight": 0.07027027027027027,
            "keyword": "html"
          }
        ],
        "weight": 0.08653350323695352,
        "id": 7
      },
      {
        "terms": [
          {
            "weight": 0.1322314049586777,
            "keyword": "php"
          },
          {
            "weight": 0.06060606060606061,
            "keyword": "sql"
          },
          {
            "weight": 0.05509641873278237,
            "keyword": "mysql"
          }
        ],
        "weight": 0.08027610626914822,
        "id": 1
      },
      {
        "terms": [
          {
            "weight": 0.15204678362573099,
            "keyword": "java"
          },
          {
            "weight": 0.10526315789473684,
            "keyword": "android"
          },
          {
            "weight": 0.023391812865497075,
            "keyword": "multithreading"
          }
        ],
        "weight": 0.07511374008317741,
        "id": 9
      }
    ]
  }
}
```
