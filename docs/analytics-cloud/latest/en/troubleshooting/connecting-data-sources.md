# Connecting Data Sources

Misconfigured environments or data sources can prevent or disrupt access to Liferay DXP data sources. Here's how to troubleshoot DXP data source issues.

## No Network Access to Analytics Cloud

Ensure that the DXP installation has internet access to our Analytic Cloud server by adding the following URLs to an allow list:

* `https://analytics.liferay.com`
* `https://osbasahpublisher-{regionKey}.lfr.cloud`
* `https://osbasahbackend-{regionKey}.lfr.cloud`
* `https://analytics-js-cdn.liferay.com`

```{note}
Note, the {regionKey} is based on the initial selection during workspace setup (i.e. ac-southamericaeast1, ac-europewest2, ac-europewest3, or ac-uswest1).
```

```{important}
For some use cases, such as analyzing corporate intranet usage, your visitors’ browsers are also behind a firewall. In this scenario, you must ensure that the corporate office network also allows outbound access for the above URLs.
```

## Validating the Connection to Analytics Cloud

Here are some useful tips to help you validate that data is being sent to Analytics Cloud.

### Analytics Events

Analytics events are sent directly from the client's browser. To validate data is being sent to Analytics Cloud, perform the following steps:

1. Visit one of the pages of your DXP website that is being tracked.
1. Open the browser inspector and go to the Network tab.
1. Filter the network tab by XHR.
1. Refresh the page.
1. Verify that you see a request starting with `osbasahpublisher`. The request should look something similar to the screenshot below:

    ![Validating the connection to Analytics Cloud.](connecting-data-sources/images/01.png)

    If you are able to see this request that means your website is sending analytics data to your Analytics Cloud workspace. Check the request payload and verify that there is a variable called `channelId`. 

### Contacts Data

DXP will sends contact information of your logged in users to Analytics Cloud as individual profile data. This data is sent directly from the DXP server.

To verify that contacts data are being sent, check the DXP server logs for the messages similar to the following:

```
INFO  [liferay/analytics_messages_processor-1][AddAnalyticsMessagesMessageListener:70] Added 500 analytics messages

INFO  [liferay/analytics_messages_processor-1][AddAnalyticsMessagesMessageListener:70] Added 500 analytics messages

INFO  [liferay/scheduler_dispatch-3][SendAnalyticsMessagesMessageListener:149] Sent 100 analytics messages

INFO  [liferay/scheduler_dispatch-3][SendAnalyticsMessagesMessageListener:164] Deleted 100 analytics messages
```

If you see these server logs, then your contact data are being sent successfully to AC.

## Data Processing Time

Once the data arrives at Analytics Cloud, it takes additional time to process, before appearing in the workspace dashboard.

For Analytics events, you should be able to see visitors metric in the 24 hours filter in the Site Dashboard within 10 to 15 minutes.

![Analytics Data coming in over a period of time.](connecting-data-sources/images/02.png)

Other session-related data such as session duration, and bounce rate etc, will have to wait until the visitor session ends. Visitor sessions are considered over when there are 30 minutes of inactivity, or at 00:00:00 UTC -- whichever comes first.

Individual Profiles take longer to process and become available over time.

## Unsupported Version

**Error Message:** `Unsupported version. This method of connection does not support the data source Liferay version. Make sure you are connecting to Liferay 7.0/7.1 instance or try a different method of connection.`

```{important}
Your Liferay DXP installation must meet the following fix pack minimum requirements:

  * 7.4+
  * 7.3 Fix Pack 1
  * 7.2 Fix Pack 11
  * 7.1 Fix Pack 22
  * 7.0 Fix Pack 98
```

**Resolution:**

1. Make sure to [connect with a Liferay DXP 7.0 or 7.1 instance].

1. Follow the steps for [adding a Liferay DXP data source](../getting-started/connecting-data-sources/connecting-liferay-dxp-using-oauth.md).

1. If the error persists, make sure JSON web services are enabled on your DXP instance. They're enabled by default. If you disabled them using a [portal property](https://docs.liferay.com/dxp/portal/7.1-latest/propertiesdoc/portal.properties.html#JSON) setting json.web.service.enabled=false (e.g., set in a [portal-ext.properties file](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/portal-properties.html)), delete the setting or set the property value to true.