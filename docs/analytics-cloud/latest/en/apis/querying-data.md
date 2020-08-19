# Querying Data

The root endpoint of Liferay Analytics Cloud is <https://analytics.liferay.com/api/reports>. You can get the list of all data types endpoints by requesting a GET to the above URL. Try with curl

```
curl -L -H "Authorization: Bearer {token}" https://analytics.liferay.com/api/reports
```

The following response should be returned:

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

From here you can call different APIs to get data for accounts, individuals, pages, and segments.

All the server responses to the analytics data follow the same data structure. The response is a paged results of entities computed by Liferay Analytics Cloud. By default, each page will contain 20 elements and it can be navigated by replacing the page query parameter. Total shows the total number of available elements. Below you can see  response format:

```json
{"results":[],"total":0}
```

## Accessing Accounts Data

Accounts data can be retrieved with the following command:

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/accounts
```

### Parameters

* `page` (Integer): results page number

## Accessing Individuals Data

Individuals data can be retrieved by the following command

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals
```

### Parameters

* `page` Integer: results page number
* `query` String: keywords to be matched with the individuals demographics information  

## Individual Activities

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals/{id}/activities
```

Returns the list of activities performed by the individual

The structure of each activity is described below

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

### Properties

* `ownerId` (String): Owner of the activity, equivalent to the individual ID.
* `startTime` (Date): Date timestamp of when the activity occurred.
* `applicationId` (String): Id of the application that triggered the event.
* `eventId` (String): ID of the analytics events performed by the individual; event ID represent an action performed by the individual.
* `eventProperties` (Object): Metadata information about the event, varies according to the eventId;

## Individual Interests

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals/{id}/interests
```

Returns the list of individuals' interests, the structure of each interest is described below

```json
{
   "score":0.6908830400645879,
   "ownerId":"385450976494153117",
   "dateRecorded":null,
   "id":"389978452012791133",
   "name":"Digital Experience Software Tailored"
}
```

### Properties

* `score` (Number): Numerical representation of how interest the individual is in that particular topic; ranges from 0 to 1.
* `ownerId` (String): Owner of the activity, equivalent to the individual ID.
* `dateRecorded` (Date): Date timestamp of when the interest was calculated.
* `applicationId` (String): Id of the application that triggered the event.
* `id` (String): Unique identifier of the interest.
* `name` (String): Name of the interest.

## Individual Segments

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/individuals/{id}/segments
```

Returns the list of segments the individual belongs to, the structure of each segment is described in the section Data Types > Segments.

## Accessing Segments Data

Segments data can be retrieved by the following command:

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/segments
```

### Properties

`page` : Integer, results page number

## Segment individuals

Returns the list of individuals that belong to the segment.

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/segments/{id}/individuals
```

## Accessing Page Data

Page data can be retrieved by the following command:

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/pages 
```

### Properties

* `page` (Integer): results page number.
* `keywords` (String): keywords to be matched with the pages title or url information.
* `rangeKey` (Integer): range to be used to group data. Possible values are 0 ( data from the last 24 hours), 1 (data from yesterday), 7 (data from the last 7 days), 28 (data from the last 28 days), 30 (default value, data from the last 30 days), 90 (data from the last 90 days.
* `sortMetric` (String): defined what metric to sort the results, default to viewsMetrics; possible values are ctrMetric, engagementMetric, timeOnPageMetric, exitRateMetric, ctpMetric, sessionsMetric, avgTimeOnPageMetric, bounceMetric, maxScrollDepthMetric, visitorsMetric, viewsMetric, bounceRateMetric, indirectAccessMetric, entrancesMetric, directAccessMetric
* `sortOrder` (String), possible values are *asc* or *desc*. Orders the results according to the sortMetric in ascending or descending order. Default is desc.
