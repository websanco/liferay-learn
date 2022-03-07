# データのクエリ

Liferay Analytics Cloudのルートエンドポイントは、 <https://analytics.liferay.com/api/reports>です。 上記のURLにGETをリクエストすることで、すべてのデータタイプのエンドポイントのリストを取得することができます。 curlで試します。

```
curl -L -H "Authorization: Bearer {token}" https://analytics.liferay.com/api/reports
```

以下のようなレスポンスが返ってきます。

```json
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

ここから、さまざまなAPIを呼び出して、アカウント、ユーザー、ページ、セグメントのデータを取得することができます。

アナリティクスデータに対するサーバーの応答は、すべて同じデータ構造に従っています。 応答は、Liferay Analytics Cloudによって計算されたエンティティのページ結果です。 デフォルトでは、各ページには20個の要素が含まれており、ページのクエリパラメーターを置き換えることでナビゲートすることができます。 Totalは、利用可能な要素の総数を表示します。 以下はリスポンスの形式です：

```json
{"results":[],"total":0}
```

<a name="accessing-accounts-data" />

## アカウントデータへのアクセス

アカウントデータは、以下のコマンドで取得できます：

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/accounts
```

<a name="parameters" />

### パラメーター

* `ページ` （整数）：ページ番号の結果

<a name="accessing-individuals-data" />

## ユーザーデータへのアクセス

ユーザーデータは、以下のコマンドで取得できます：

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals
```

<a name="parameters-1" />

### パラメーター

* `ページ` 整数：ページ番号の結果
* `クエリ` 文字列：ユーザーの人口統計情報と一致するキーワード

<a name="individual-activities" />

## ユーザーのアクティビティ

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals/{id}/activities
```

ユーザーによって実行されたアクティビティのリストを返します。

各活動の構成は以下のとおりです：

```json
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

<a name="properties" />

### 詳細設定

* `ownerId` （文字列）：ユーザーIDに相当する、アクティビティの所有者。
* `startTime` （日付）：アクティビティが発生した日付のタイムスタンプ。
* `applicationId` （文字列）：イベントをトリガーしたアプリケーションのID。
* `eventId` （文字列）：ユーザーによって実行されたアナリティクスイベントのID。イベントIDは、ユーザーによって実行されるアクションを表す。
* `eventProperties` （オブジェクト）：イベントに関するメタデータ情報。eventIdによって異なる。

<a name="individual-interests" />

## ユーザーの興味

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals/{id}/interests
```

ユーザーが興味があるもののリストを返します。各興味の構造は以下のようになります。

```json
{
   "score":0.6908830400645879,
   "ownerId":"385450976494153117",
   "dateRecorded":null,
   "id":"389978452012791133",
   "name":"Digital Experience Software Tailored"
}
```

<a name="properties-1" />

### 詳細設定

* ` score ` (数値)：ユーザーがその特定のトピックにどれだけ興味を持っているかを数値で表したもの。
* `ownerId` （文字列）：ユーザーIDに相当する、アクティビティの所有者。
* `dateRecorded` （日付）：興味が計算された日付のタイムスタンプ。
* `applicationId` （文字列）：イベントをトリガーしたアプリケーションのID。
* `id` （文字列）：興味対象の一異な識別情報。
* `name` （文字列）：興味のあるものの名前。

<a name="individual-segments" />

## ユーザーのセグメント

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals/{id}/segments
```

ユーザーが所属するセグメントのリストを返します。各セグメントの構造については、［データ型 > セグメント］で説明しています。

<a name="accessing-segments-data" />

## セグメントデータへのアクセス

セグメンツデータは以下のコマンドで取得できます：

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/segments
```

<a name="properties-2" />

### 詳細設定

`ページ` ：整数、結果ページ番号

<a name="segment-individuals" />

## ユーザーのセグメント化

セグメントに属するユーザーのリストを返します。

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/segments/{id}/individuals
```

<a name="accessing-page-data" />

## ページデータへのアクセス

ページデータは、以下のコマンドで取得できます：

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/pages 
```

<a name="properties-3" />

### 詳細設定

* `ページ` （整数）：ページ番号の結果。
* `キーワード` （文字列）：ページのタイトルやURL情報にマッチさせるキーワード。
* `rangeKey` （整数）：データのグループ化に使用される範囲。 設定可能な値は、0（24時間前のデータ）、1（昨日のデータ）、7（7日前のデータ）、28（28日前のデータ）、30（デフォルト値、30日前のデータ）、90（90日前のデータ）。
* `sortMetric` （文字列）：結果を並べ替えるメトリックを定義。デフォルトはviewsMetrics。可能な値は、ctrMetric、timeOnPageMetric、exitRateMetric、ctpMetric、sessionsMetric、avgTimeOnPageMetric、bounceMetric、maxScrollDepthMetric、visitorsMetric、viewsMetric、bounceRateMetric、indirectAccessMetric、entlinesMetric、directAccessMetric。
* `sortOrder` （文字列）、可能な値は **asc** または **desc** 。 sortMetricに従って、結果を昇順または降順で並べ替える。 デフォルトはdesc。
