# データのクエリ

Liferay Analytics Cloudのルートエンドポイントは、 <https://analytics.liferay.com/api/reports>です。 上記URLにGETリクエストすることで、全データ型エンドポイントの一覧を取得することができます。 curlで試します。

    curl -L -H "Authorization: Bearer {token}" https://analytics.liferay.com/api/reports

以下のようなレスポンスが返ってきます。

``` json
{
   "_links":{
      "accounts":{
         "href": "https://analytics.liferay.com/api/reports/accounts"
      },
      "individuals":{
         "href": "https://analytics.liferay.com/api/reports/individuals"
      },
      "pages":{
         "href": "https://analytics.liferay.com/api/reports/pages"
      },
      "segments":{
         "href": "https://analytics.liferay.com/api/reports/segments"
      }
   }
}
```

ここから、アカウント、個人、ページ、セグメントのデータを取得するために異なるAPIを呼び出すことができます。

アナリティクスデータに対するサーバーの応答はすべて同じデータ構造に従っています。 レスポンスは、Liferay Analytics Cloudによって計算されたエンティティのページ化された結果です。 デフォルトでは、各ページには20の要素が含まれており、ページクエリパラメータを置き換えることでナビゲートすることができます。 Totalは利用可能な要素の総数を示します。 以下に回答形式を示します。

``` json
{"results":[],"total":0}
```

## アカウントデータへのアクセス

アカウントデータは、以下のコマンドで取得できます。

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/accounts

### パラメーター

-   `ページ` (整数)：結果ページ番号

## ユーザーデータへのアクセス

個人データは以下のコマンドで取得できます。

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals

### パラメーター

-   `ページ` 整数：結果ページ番号
-   `クエリ` 文字列: 個人の人口統計情報と一致させるキーワード

## ユーザーのアクティビティ

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals/{id}/activities

個人が行ったアクティビティのリストを返します

各活動の構成は以下の通りです。

``` json
{
   "ownerId": "371000621354447876",
   "startTime": "2019-10-28T21:49:05.674Z",,
   "applicationId":"WebContent",
   "eventId":"webContentViewed",
   "eventProperties":{
      "numberOfWords":"13",
      "articleId":"232001430",
      "title":"Navigation Content - Partner Resources Basic (for launch)",
      "pageViewActivityId":"8ed2e0d9-ed41-4b3d-bbe1-e1219448e9eb"
   }
}
```

### 詳細設定

-   `ownerId` (String)：アクティビティの所有者、個人のIDに相当します。
-   `startTime` (Date)：アクティビティが発生した日付のタイムスタンプ。
-   `applicationId` (String)：イベントをトリガしたアプリケーションの Id。
-   `eventId` (String)：個人によって実行されたアナリティクスイベントのID; イベントIDは個人によって実行されたアクションを表します。
-   `eventProperties` (Object)：イベントに関するメタデータ情報で、eventIdによって異なります。

## 個人の関心トピック

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals/{id}/interests

個人の関心トピックのリストを返します。

``` json
{
   "score":0.6908830400645879,
   "ownerId":"385450976494153117",
   "dateRecorded":null,
   "id":"389978452012791133",
   "name":"Digital Experience Software Tailored"
}
```

### 詳細設定

-   ` score ` (数値)：個人がその特定のトピックにどれだけ興味を持っているかを数値で表したもの。
-   `ownerId` (String)：アクティビティの所有者、個人のIDに相当します。
-   `dateRecorded` (Date)：関心トピックの数値が計算された日付のタイムスタンプ。
-   `applicationId` (String)：イベントをトリガしたアプリケーションの Id。
-   `applicationId` (String)：イベントをトリガしたアプリケーションの Id。
-   `name` (String)：関心トピックの名前

## ユーザーのセグメント

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals/{id}/segments

個人が所属するセグメントのリストを返します。各セグメントの構造については、［データ型 &gt; セグメント］で説明しています。

## セグメントデータへのアクセス

セグメントデータは以下のコマンドで取得できます。

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/segments

### 詳細設定

`ページ` : 整数、結果ページ番号

## ユーザーのセグメント化

セグメントに属する個人のリストを返します。

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/segments/{id}/individuals

## ページデータへのアクセス

ページデータは以下のコマンドで取得できます。

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/pages

### 詳細設定

-   `ページ` (整数)：結果ページ番号。
-   `キーワード` (文字列): ページのタイトルまたはURL情報と一致させるキーワード。
-   `rangeKey` (Integer): データをグループ化するために使用する範囲。 可能な値は、0 (過去24時間のデータ)、1 (昨日のデータ)、7 (過去7日間のデータ)、28 (過去28日間のデータ)、30 (デフォルト値、過去30日間のデータ)、90 (過去90日間のデータ)です。
-   `sortMetric` (String): defined what metric to sort the results, default to viewsMetrics; possible values are ctrMetric, timeOnPageMetric, exitRateMetric, ctpMetric, sessionsMetric, avgTimeOnPageMetric, bounceMetric, maxScrollDepthMetric, visitorsMetric, viewsMetric, bounceRateMetric, indirectAccessMetric, entrancesMetric, directAccessMetric
-   `sortOrder` (String), 可能な値は *asc* または *desc*です. sortMetricに従って結果を昇順または降順に並べ替えます。 デフォルトは desc です。
