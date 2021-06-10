# リソースの種類とストラクチャー

## アカウント

アカウントは、サイトと相互作用している企業や組織のことです。 会社の確定情報が記載されており、同じアカウントに所属している全てのユーザーの情報も記載されています。 アカウントリソースの説明は以下の通りです。

``` json
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

### 詳細設定

-   `activeIndividualsCount` (Number)：アカウントに所属するアクティブユーザーの数。
-   `dateCreated` (Date)：システムでアカウントが作成された日付。
-   `dateModified` (Date)：アカウントのプロパティを最終的に変更した日付。
-   `ID` （文字列）：アカウントのユニークな識別子です。
-   `individualsCount` (数値)：アカウントに所属するユーザー数、アクティブまたは非アクティブのユーザーがこのメトリックでカウントされます。
-   ` Properties `: アカウントの動的プロパティのキー／バリュー（文字列）マップで、例としては業界、ファックス、電話などがあります。

## ユーザー

ユーザーはポータルにアクセスしたすべてのユーザーを表します。 個人が知られていても、匿名でも構いません。 既知のユーザーとは、連絡先の同期中にデータが濃縮された人のことです。 充実させた後、既知のユーザーは、メールや人口統計などの追加属性を持つようになります。 個々のリソースとその特性を以下に説明します。

``` json
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

### 詳細設定

-   `人口統計` キー/値 (文字列:) ユーザーの人口統計の動的なプロパティのマップ; 例としては、性別、生年月日、電子メールなどがあります。
-   `ID` (文字列)：ユーザーの一意の識別子。

## セグメント

セグメントとは、似たような特徴を持つユーザーのグループのことです。 セグメントは、 *静的* または *動的*のいずれかになります。 静的セグメントは、選択されたユーザーの静的なグループです。 動的セグメントは、基準に基づいています（例えば、米国から閲覧しているすべてのユーザーをグループ化する）。 定義された基準は、Liferay Analytics Cloud UIの動的セグメントに含まれるユーザーを決定します。 セグメントリソースについては、以下のように説明します。

``` json
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

### 詳細設定

-   `dateCreated` (Date)：システムでセグメントが作成された日付。
-   `ID` (String)：セグメントの一意の識別子。
-   `individualCount` (Number)：このメトリックは匿名または既知のユーザーの両方を考慮します。
-   `knownIndividualsCount` (Number)：セグメントに属する既知のユーザーの数。
-   `name` (String)：セグメントの名前。
-   `segmentType` (String)：セグメントが静的か動的か。
-   `includeAnonymousUsers` (ブール値)：セグメントに匿名ユーザーが含まれるかどうか。 false の場合、 individualCount と knownIndividualCount は常に等しくなります。

## ページ

ページ情報は、追跡されたページとの相互作用データを集約したものです。 各ページのURLには、ページ滞在時間やページの閲覧数などのプロパティが含まれます。 すべてのプロパティは以下の通りです。

``` json
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

### 詳細設定

-   `title` (文字列)：ページタイトル。
-   `メトリクス` (メトリック)：各ページはタイトル、URLペアによって一意に識別されます。
    -   `メトリクス` (メトリック)：各ページはタイトル、Urlペアによって一意に識別されます。
        -   トレンド
            -   `パーセンテージ` (数値) - 前のメトリック値と現在のメトリック値の相対的な変動。
            -   `trendClassfication` (String)で、POSITIVE, NEUTRAL, NEGATIVEの値を想定することができます。 これは、以前の値と比較したときに、メトリックがどの程度のパフォーマンスを発揮しているかを考慮に入れています。
        -   `値` (Number)：メトリック値、rangeKeyに依存し、選択されたrangeKeyが30の場合、値は過去30日間の集計データを表します。
        -   `previousValue` (Number)： 前のメトリック値。 また、要求されたrangeKeyにも依存しますが、選択されたrangeKeyが30の場合、前の値は、今日から60日前までのデータを集約します。
-   `url` (文字列)：ページのURL
