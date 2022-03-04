# 検索インサイト

> **この機能はテストおよび開発のみを目的としています****Elasticsearchでのみ動作します** 。

検索インサイトウィジェットは、検索ページに追加されると、2つのことを検査するためのものです。

- ユーザーがキーワードを入力した際に、バックエンドの検索コードによって構築されるクエリ文字列

- サーチエンジンから返されるレスポンス文字列

```{note}
   リクエスト文字列として返されたJSONは、明確にするためにいくつかのElasticsearchのクエリのデフォルトから刈り取られています。 Elasticsearchが処理した完全なリクエストJSONを見るには、 [Elasticsearchサーバのロギング](https://www.elastic.co/guide/en/elasticsearch/reference/6.x/logging.html) を調整してください。
```

**スコアの説明を有効にする** オプション（デフォルトで有効）を指定すると、「洞察」ウィジェットは、返された各結果に対して関連性スコアの説明も表示します。

Elasticsearchは、検索クエリを処理すると結果を返します。 **関連性** の概念は、結果がどれだけクエリにマッチしているかを判断します。 返された検索ドキュメントのスコアの説明は、一見奇妙な結果を明確にするのに役立ち、 **が** の結果を後押しすることで、関連性のスコアリングプロセスを調整することができます（特定のフィールドでのマッチをより多くカウントする）。

<a name="inspecting-the-search-query-string" />

## 検索クエリ文字列の検査

Search Insights」ウィジェットの動作を確認するには、テストサーバーの検索ページに移動し、「Add」メニューからウィジェットを追加してください（![Add](../../images/icon-add-widget.png)）。

![テストや開発の際には、検索インサイトウィジェットが役立ちます。](./search-insights/images/01.png)

検索結果を返すキーワードを検索すると、検索インサイトポートレットには、返されたクエリ文字列がすべて表示されます。

![このクエリストリングの全文は、お勧めできません。 この例は、読者のために切り取ってあります。](./search-insights/images/02.png)

<a name="explaining-search-results" />

## 検索結果の説明

Explainオプションを有効または無効にするには、次のようにします。

1. 検索インサイトウィジェットの設定画面を開きます。

1. デフォルトで有効になっているブール型フィールドは1つだけです：スコアの説明を有効にする。 選択を外すと、各結果の関連性スコアの説明を無効にすることができます。

フードの下では、検索インサイト ウィジェットの Explain オプションが Elasticsearch API を公開しています： [Explain](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/search-explain.html) 。 詳細はElasticsearchのドキュメントを参照してください。

ここでは、テストテストユーザーのドキュメントの検索で、検索キーワードが **test** の場合の採点説明の一部を紹介します。

```json
_explanation":{  
   "value":9.461341,
   "description":"sum of:",
   "details":[  
      {  
         "value":9.461341,
         "description":"sum of:",
         "details":[  
            {  
               "value":1.0,
               "description":"emailAddress:*test*",
               "details":[  

               ]
            },
            {  
               "value":5.0,
               "description":"userName:*test*^5.0",
               "details":[  

               ]
            },
            {  
               "value":0.72928625,
               "description":"sum of:",
               "details":[  
                  ... 

            { 
               "value":1.0027686,
               "description":"sum of:",
               "details":[  
                  ...
                  {  
            {  
               "value":0.72928625,
               "description":"sum of:",
               "details":[  
                  ...
            {  
               "value":1.0,
               "description":"screenName:*test*",
               "details":[  

               ]
            }
         ]
      },
      ...
   ]
}}]}
```

これで、クエリストリング全体、レスポンスストリング、そして返された各ドキュメントの検索のスコアを見ることができます。
