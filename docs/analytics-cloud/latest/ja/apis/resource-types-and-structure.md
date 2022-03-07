# リソースの種類とストラクチャー

<a name="accounts" />

## アカウント

アカウントは、サイトと相互作用している企業や組織のことです。 会社の確定情報が記載されており、同じアカウントに所属している全てのユーザーの情報も記載されています。 アカウントリソースについては以下の通りです。

```json
{ 
   "activeIndividualsCount":0,
   "dateCreated":"2019-12-27T19:17:49.924Z",
   "dateModified":"2019-12-27T19:17:49.924Z",
   "id":"386700295379617992",
   "individualsCount":5,
   "properties":{ 
      "shippingCity":"Jasminport",
      "website":"https://www.gino-jacobs.org",
      "accountName":"Lesch, Walsh and Stracke",
      "shippingStreet":"51189 Gina Drive",
      "accountType":"Customer",
      "description":"Open-architected scalable archive",
      "industry":"Semiconductors",
      "billingState":"New York",
      "shippingPostalCode":"53050-7467",
      "yearStarted":1911,
      "numberOfEmployees":65391,
      "accountId":"1e3c7cf2-fde6-465f-8bb6-fa8ca86d80ce",
      "ownership":"Private",
      "phone":"1-842-175-3034",
      "billingStreet":"51087 Vi Fields",
      "billingPostalCode":"11427",
      "shippingCountry":"Guinea-Bissau",
      "billingCountry":"British Indian Ocean Territory (Chagos Archipelago)",
      "shippingState":"Ohio",
      "currencyIsoCode":"GBP",
      "fax":"1-507-906-4658",
      "annualRevenue":83200000,
      "billingCity":"Bayerborough"
   },
   "_links":{ 
      "self":{ 
         "href":"http://192.168.108.90:9090/api/reports/accounts/386700295379617992"
      }
   }
} 
```

**プロパティ**

* `activeIndividualsCount` (Number)：アカウントに所属するアクティブユーザーの数。
* `dateCreated` （Date）：アカウントがシステムで作成された日付。
* `dateModified` （日付）：アカウントプロパティが最後に変更された日付。
* `ID` （文字列）：アカウントのユニークな識別子です。
* `individualsCount` (数値)：アカウントに所属するユーザー数、アクティブまたは非アクティブのユーザーがこのメトリックでカウントされます。
* ` Properties `: アカウントの動的プロパティのキー／バリュー（文字列）マップで、例としては業界、ファックス、電話などがあります。

<a name="individuals" />

## 個人

ユーザーはポータルにアクセスしたすべてのユーザーを表します。 ユーザーは既知でも匿名でも構いません。 既知のユーザーとは、連絡先の同期中にデータが濃縮された人のことです。 充実させた後、既知のユーザーは、メールや人口統計などの追加属性を持つようになります。 個々のリソースとそのプロパティについて、以下の通りです。

```json
{
   "demographics":{
      "gender":"male",
      "givenName":"Joe",
      "familyName":"Bloggs",
      "birthDate":"1970-01-01T00:00:00.000Z",
      "email":"email@domain.com"
   },
   "id":"370982554530167442",
   "_links":{
      "self":{
         "href":"http://localhost:8080/api/reports/individuals/370982554530167442"
      },
      "activities":{
         "href":"http://localhost:8080/api/reports/individuals/370982554530167442/activities"
      },
      "interests":{
         "href":"http://localhost:8080/api/reports/individuals/370982554530167442/interests"
      },
      "segments":{
         "href":"http://localhost:8080/api/reports/individuals/370982554530167442/segments"
      }
   }
}
```

**プロパティ**

* `人口統計` キー/値 (文字列:) ユーザーの人口統計の動的なプロパティのマップ; 例としては、性別、生年月日、電子メールなどがあります。
* `ID` (文字列)：ユーザーの一意の識別子。

<a name="segments" />

## セグメント

セグメントとは、似たような特徴を持つユーザーのグループのことです。 セグメントは **静的** または **動的** のいずれかです。 静的セグメントは、選択されたユーザーの静的なグループです。 動的セグメントは、基準に基づいています（例えば、米国から閲覧しているすべてのユーザーをグループ化する）。 定義された基準は、Liferay Analytics Cloud UIの動的セグメントに含まれるユーザーを決定します。 セグメントのリソースについては以下の通りです：

```json
{ 
   "dateCreated":"2019-12-27T19:17:49.924Z",
   "id":"386700296216137268",
   "individualCount":5,
   "knownIndividualCount":5,
   "name":"Account: 386700295379617992",
   "segmentType":"DYNAMIC",
   "includeAnonymousUsers":false,
   "_links":{ 
      "self":{ 
         "href":"http://192.168.108.90:9090/api/reports/segments/386700296216137268"
      },
      "individuals":{ 
         "href":"http://192.168.108.90:9090/api/reports/segments/386700296216137268/individuals?page=0"
      }
   }
}
```

**プロパティ**

* `dateCreated` （日付）：セグメントがシステムで作成された日付。
* `ID` （文字列）：セグメントのユニークな識別子。
* `individualCount` (Number)：このメトリックは匿名または既知のユーザーの両方を考慮します。
* `knownIndividualsCount` (Number)：セグメントに属する既知のユーザーの数。
* `name` （文字列）：セグメントの名前。
* `segmentType` （文字列）：セグメントが静的か動的か。
* `includeAnonymousUsers` （ブール値）：セグメントに匿名ユーザーが含まれているかどうか。 falseの場合、individualCountとknownIndividualCountは常に等しくなります。

<a name="pages" />

## ページ

ページ情報は、追跡されたページでのインタラクションデータを集約したものです。 各ページのURLには、ページ滞在時間やページの閲覧数などのプロパティが含まれます。 すべてのプロパティは以下の通りです。

```json
{ 
   "title":"Home - Liferay DXP",
   "metrics":{ 
      "ctrMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
          },
          "value":0.0
      },
      "timeOnPageMetric":{ 
         "previousValue":0.0,
         "trend":{ 
             "percentage":null,
             "trendClassification":"NEUTRAL"
         },
         "value":264283.0
      },
      "exitRateMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":0.0
      },
      "ctpMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":0.0
      },
      "sessionsMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":1.0
      },
      "bounceMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":0.0
      },
      "avgTimeOnPageMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":264283.0
      },
      "maxScrollDepthMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":0.0
      },
      "visitorsMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":1.0
      },
      "viewsMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":5.0
      },
      "bounceRateMetric":{ 
        "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":0.0
      },
      "indirectAccessMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":5.0
      },
      "entrancesMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":0.0
      },
      "directAccessMetric":{ 
         "previousValue":0.0,
         "trend":{ 
            "percentage":null,
            "trendClassification":"NEUTRAL"
         },
         "value":0.0
      }
   },
   "url":"http://172.16.22.127:8080/web/guest",
   "_links":{ 
      "self":{ 
         "href":"http://192.168.108.90:9090/api/reports/pages/http%253A%252F%252F172.16.22.127%253A8080%252Fweb%252Fguest?rangeKey=30"
      }
   }
}
```

**プロパティ**

* `title` (文字列)：ページタイトル。
* `metrics` （メトリック）：ページのメトリック値のリスト。各ページはタイトル、URLのペアで一意に識別されます。
  * `Metric` （オブジェクト）：メトリック値を表すオブジェクト。メトリックプロパティについては、以下に説明されています：
    * トレンド
      * `パーセント` （Number）-以前と現在のメトリック値の相対的なバリエーション。
      * `TrendClassfication` （文字列）、POSITIVE、NEUTRAL、NEGATIVEの値をとることができます。 この指標は、以前の値と比較して、どれだけパフォーマンスが向上しているかを考慮しています。
    * `Value/0> （Number）：メトリック値。rangeKeyによって異なります。選択したrangeKeyが30の場合、値は過去30日間の集計データを表します。</li>
<li><code>previousValue` （Number）：前のメトリック値。 また、要求されたレンジキーにもよりますが、選択されたレンジキーが30の場合、前の値は今日-60日目から今日-30日目までのデータを集約します。
* `url` （文字列）：ページのURL

<a name="assets" />

## アセット

アセット情報は、追跡されたアセットページでのインタラクションデータを集約したものです。 どのページURLにも複数のアセットを含めることができます。 アセットメトリクスは、ページレポートを補完するもので、ページのインタラクションをより詳細に見ることができます。 ブログ、ドキュメントとメディア、フォーム、そしてWebコンテンツの4つのアセットが用意されています。 これらの各アセットには、特定のエンドポイントがあります：

* ブログ - [https://analytics.liferay.com/api/reports/blogs](https://analytics.liferay.com/api/reports/blogs)
* ドキュメントとメディア - [https://analytics.liferay.com/api/reports/documents-and-media](https://analytics.liferay.com/api/reports/documents-and-media)
* フォーム- [https://analytics.liferay.com/api/reports/forms](https://analytics.liferay.com/api/reports/forms)
* Webコンテンツ- [https://analytics.liferay.com/api/reports/web-contents](https://analytics.liferay.com/api/reports/web-contents)

各アセットには、それぞれのプロパティが含まれています。

<a name="blogs" />

### ブログ

```json
 {
      "id": "107694635",
      "title": "Awesome Web Portal Examples",
      "metrics": {
        "readingTimeMetric": {
          "value": 71788.99821937321
        },
        "clicksMetric": {
          "value": 1969.0
        },
        "viewsMetric": {
          "value": 3253.0
        },
        "ratingsMetric": {
          "value": 0.0
        },
        "commentsMetric": {
          "value": 0.0
        }
      },
      "_links": {
        "self": {
          "href": "https://analytics.liferay.com/api/reports/blogs/107694635?blogTitle=16%20Awesome%20Web%20Portal%20Examples&rangeKey=30"
        }
      }
```

**プロパティ**

* `ID` （文字列）：ブログのユニークな識別子。
* ` Title ` （文字列）：ブログに使用されるタイトル。
* `Reading Time Metric` （Double）：ブログの閲覧に費やしたユーザーの平均時間。
* `Clicks Metric` （Double）：ブログ上でユーザーがクリックした回数の合計。
* `Views Metric` （Double）：ブログのユーザー閲覧数の合計。
* `Ratings Metric` （Double）：その選択期間中のブログの平均評価（1〜5の範囲）。
* `Comments Metric` （Double）：ブログに追加されたコメントの合計。

<a name="documents-and-media" />

### ドキュメントとメディア

```json
{
      "id": "320981007",
      "title": "My Awesome Document",
      "metrics": {
        "downloadsMetric": {
          "value": 6.0
        },
        "ratingsMetric": {
          "value": 0.0
        },
        "commentsMetric": {
          "value": 0.0
        },
        "previewsMetric": {
          "value": 286.0
        }
      },
      "_links": {
        "self": {
          "href": "https://analytics.liferay.com/api/reports/documents-and-media/320981007?documentTitle=My%20Awesome%20Document&rangeKey=30"
      }
}
```

**プロパティ**

* `ID` （文字列）：ドキュメントまたはメディアのユニークな識別子。
* `Title` （文字列）：ドキュメントまたはメディアに使用されるタイトル。
* `Downloads Metric` （Double）：ドキュメントまたはメディアのユーザーダウンロード数の合計。
* `Ratings Metric` （Double）：その選択期間中のドキュメントまたはメディアの平均評価（1〜5の範囲）。
* `Comments Metric` （Double）：ドキュメントまたはメディアに追加されたコメントの合計。
* `Previews Metric` （Double）：ドキュメントまたはメディアのユーザープレビュー数の合計。

<a name="forms" />

### フォーム

```json
{
      "id": "872a3ca0-324d-438a-9d82-e4cad68c3a20",
      "title": "Blog Subscription",
      "metrics": {
        "viewsMetric": {
          "value": 20409.0
        },
        "abandonmentsMetric": {
          "value": 0.9989710421872703
        },
        "submissionsMetric": {
          "value": 22.0
        },
        "completionTimeMetric": {
          "value": 33645.77272727273
        }
      },
      "_links": {
        "self": {
          "href": "https://analytics.liferay.com/api/reports/forms/872a3ca0-324d-438a-9d82-e4cad68c3a20?formTitle=Blog%20Subscription&rangeKey=30"
        },
        "pages": {
          "href": "https://analytics.liferay.com/api/reports/forms/872a3ca0-324d-438a-9d82-e4cad68c3a20/pages?formTitle=Blog%20Subscription&rangeKey=30"
        }
      }
```

**プロパティ**

* `ID` （文字列）：フォームのユニークな識別子。
* ` Title ` （文字列）：フォームに使用されるタイトル。
* `Views Metric` （Double）：フォームのユーザー閲覧数の合計。
* `Abandonments Metric` (Double)：フォームの放棄率（0～1の範囲）。
* `Submissions Metric` （Double）：フォームの送信数の合計。
* `Completion Time Metric` (Double)：フォームを完了するためのユーザーの平均時間。

また、ページのリンクに移動すると、フォームのページメトリクス情報を見ることができます。 ページおよびフォームフィールドごとにフォームメトリックが表示されます。

```json
{
  "formId": "872a3ca0-324d-438a-9d82-e4cad68c3a20",
  "formTitle": "Blog Subscription",
  "formPages": [
    {
      "id": "0",
      "title": "",
      "fields": {
        "name_field": {
          "metrics": {
            "fieldInteractionsMetric": {
              "value": 6.0
            },
            "fieldInteractionsDurationMetric": {
              "value": 0.0
            },
            "fieldRefilledMetric": {
              "value": 3.0
            },
            "fieldAbandonmentsMetric": {
              "value": 3.0
            }
          }
        },
        "email_field": {
          "metrics": {
            "fieldInteractionsMetric": {
              "value": 86.0
            },
            "fieldInteractionsDurationMetric": {
              "value": 223983.0
            },
            "fieldRefilledMetric": {
              "value": 34.0
            },
            "fieldAbandonmentsMetric": {
              "value": 10.0
            }
          }
        }       
      },
      "metrics": {
        "pageViewsMetric": {
          "value": 20409.0
        },
        "pageAbandonmentsMetric": {
          "value": 20388.0
        }
      }
    }
  ],
  "_links": {
    "self": {
      "href": "https://analytics.liferay.com/api/reports/forms/872a3ca0-324d-438a-9d82-e4cad68c3a20/pages?formTitle=Blog%20Subscription&rangeKey=30"
    },
    "parent": {
      "href": "https://analytics.liferay.com/api/reports/forms/872a3ca0-324d-438a-9d82-e4cad68c3a20?formTitle=Blog%20Subscription&rangeKey=30"
    }
  }
}
```

**プロパティ**

* `Form ID` （文字列）：フォームのユニークな識別子。
* `Form Title` （文字列）：フォームに使用されるタイトル。
* `Field Interactions Metric` (Double)：フォーム項目のユーザーインタラクションの合計。
* `Field Interactions Duration Metric` (Double)：フォーム項目の平均インタラクション時間。
* `Field Abandonments Metric` (Double)：このフィールドでフォームを放棄したユーザーの合計。
* `Page Views Metric` (Double)：フォームページのユーザービューの合計。
* `Page Abandonments Metric` (Double)：このページでユーザーがフォームを放棄した数の合計。

<a name="web-content" />

### Web コンテンツ

```json
 {
      "id": "231976097",
      "title": "My Web Content",
      "metrics": {
        "viewsMetric": {
          "value": 280756.0
        }
      },
      "_links": {
        "self": {
          "href": "https://analytics.liferay.com/api/reports/web-contents/231976097?webContentTitle=My%20Web%20Content&rangeKey=30"
        }
      }
    }
```

**プロパティ**

* `ID` （文字列）：Webコンテンツのユニークな識別子。
* ` Title ` （文字列）：Webコンテンツに使用されるタイトル。
* `Views Metric` （Double）：Webコンテンツのユーザー閲覧数の合計。
