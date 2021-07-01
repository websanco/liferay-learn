# Resource Types and Structure

## Accounts

Account represents a company or organization that is interacting with your websites. It contains firmographic information about the company, and it also contains all of the individuals who belong in the same account. The account resource is described below.

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

**Properties**

* `activeIndividualsCount` (Number): Number of active individuals belonging to the account.
* `dateCreated` (Date): Date the account was created in the system.
* `dateModified` (Date): Date of the last modification of the account properties.
* `ID` (String): Unique identifier of the account;
* `individualsCount` (Number): Number of individuals belonging to the account, active or inactive individuals are considered in this metric. 
* `Properties`: Key/Value (String) map of the account dynamic properties, examples might include industry, fax, phone, etc.

## Individuals

Individual represents any user who accessed your portal. The individual can be known or anonymous. Known individuals are those who have had their data enriched during the contacts synchronization. After enrichment, a known individual will have additional attributes, such as email, demographics, etc. The individual resource and its properties are described below.

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

**Properties**

* `Demographics` Key/Value (String:) Map of the individuals demographics dynamic properties; Examples might include gender, birthDate, email, etc.
* `ID` (String): Unique identifier of the individual.

## Segments

Segments are a group of individuals that share similar characteristics. Segments can either be *static* or *dynamic*. Static segments are a static group of selected individuals. Dynamic segments are based on criteria (for example, group all individuals browsing from the United States). The defined criteria determine the individuals included in the dynamic segment in the Liferay Analytics Cloud UI. The segment resource is described below:

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

**Properties**

* `dateCreated` (Date): Date the segment was created in the system.
* `ID` (String): Unique identifier of the segment.
* `individualCount` (Number): Number of individuals belonging to the segment; This metric considers both anonymous or known individuals.
* `knownIndividualsCount` (Number): Number of known individuals belonging to the segment.
* `name` (String): Name of the segment;
* `segmentType` (String): Whether the segments are static or dynamic.
* `includeAnonymousUsers` (Boolean): Whether the segment includes anonymous users or not. When false, individualCount and knownIndividualCount are always equal;

## Pages

Pages information represents aggregated interaction data with any tracked page. Each page URL will contain properties such as time on page, number of views for a page, etc. All the properties can be seen below.

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

**Properties**

* `title` (String): The page title.
* `metrics` (Metric): The list of metric values of the page; Each page is uniquely identified by the title, url pair.
  * `Metric` (Object): Object that represents the value of a metric; the metric properties are described below:
    * Trend
      * `percentage` (Number) - relative variation of the previous and the current metric value.
      * `trendClassfication` (String), it can assume the POSITIVE, NEUTRAL, NEGATIVE value. It takes into account how well the metric is performing when compared to its previous value.
    * `Value` (Number): metric value, depends on the rangeKey, if the selected rangeKey is 30, value will represent the aggregated data from the last 30 days.
    * `previousValue` (Number): previous metric value. It also depends on the requested rangeKey, if the selected rangeKey is 30, previous value will aggregate data from today - 60days until today - 30days.
* `url` (String): The page URL

## Assets 

Asset information represents aggregated interaction data with any tracked asset page. Any page URL could contain multiple assets. Asset metrics is a complement to the page report, and it offers a more in-depth view of page interactions. There are four assets available: blogs, documents and media, forms, and web content. Each of these assets has a specific endpoint:

* Blogs - [https://analytics.liferay.com/api/reports/blogs](https://analytics.liferay.com/api/reports/blogs)
* Documents And Media - [https://analytics.liferay.com/api/reports/documents-and-media](https://analytics.liferay.com/api/reports/documents-and-media)
* Forms - [https://analytics.liferay.com/api/reports/forms](https://analytics.liferay.com/api/reports/forms)
* Web Content - [https://analytics.liferay.com/api/reports/web-contents](https://analytics.liferay.com/api/reports/web-contents)

Each asset contains its own set of properties.

### Blogs

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

**Properties**

* `ID` (String): Unique identifier of the blog.
* `Title` (String) : Title used for the blog.
* `Reading Time Metric` (Double): Average user time spent reading the blog.
* `Clicks Metric` (Double): Sum of user clicks on the blog.
* `Views Metric` (Double): Sum of user views of the blog.
* `Ratings Metric` (Double): Average rating (1-5 range) of the blog over that selection period.
* `Comments Metric` (Double): Sum of comments added to the blog.

### Documents and Media

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

**Properties**

* `ID` (String): Unique identifier of the document or media.
* `Title` (String) : Title used for the document or media.
* `Downloads Metric` (Double): Sum of user downloads of the document or media.
* `Ratings Metric` (Double): Average rating (1-5 range) of the document or media over that selection period.
* `Comments Metric` (Double): Sum of comments added to the document or media.
* `Previews Metric` (Double): Sum of user previews of the document or media.

### Forms

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

**Properties**

* `ID` (String): Unique identifier of the form.
* `Title` (String): Title used for the form.
* `Views Metric` (Double): Sum of user views of the form.
* `Abandonments Metric` (Double): Rate of abandonment of form (0-1 range).
* `Submissions Metric` (Double): Sum of submissions of the form.
* `Completion Time Metric` (Double): Average user time for completion of the form.

You can also follow the pages link and see the form page metrics information. It shows form metrics by page and form fields:

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

**Properties**

* `Form ID` (String): Unique identifier of the form.
* `Form Title` (String): Title used for the form.
* `Field Interactions Metric` (Double): Sum of user interaction of the form field.
* `Field Interactions Duration Metric` (Double): Average interaction time of the form field.
* `Field Abandonments Metric` (Double): Sum of user abandonment of the form at this field.
* `Page Views Metric` (Double): Sum of user views of the form page.
* `Page Abandonments Metric` (Double): Sum of user abandonment of the form at this page.

### Web Content

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

**Properties**

* `ID` (String): Unique identifier of the web content.
* `Title` (String): Title used for the web content.
* `Views Metric` (Double): Sum of user views of the web content.
