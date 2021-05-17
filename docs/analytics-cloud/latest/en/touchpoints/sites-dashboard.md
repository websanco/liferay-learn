# Sites Dashboard

Liferay Analytics Cloud provides a Sites dashboard for each Property in your Workspace. For example, when [Creating a Property](../connecting-data-sources/scoping-sites-and-individuals-using-properties.md#creating-a-property) each Property can be associated with one site or can be set to aggregate data from multiple sub-sites.

To view the dashboard,

1. Click *Sites* in the Touchpoints section of the menu.

1. Click the *Overview* tab (this tab is selected by default when you click Sites). 

The dashboard contains the following data panels:

* [Site Activities](#site-activities)
* [Top Pages](#top-pages)
* [Acquisitions](#acquisitions)
* [Visitors by Day and Time](#visitors-by-day-and-time)
* [Search Terms and Interests](#search-terms-and-interests)
* [Sessions by Location](#sessions-by-location)
* [Session Technology](#session-technology)
* [Cohort Analysis](#cohort-analysis)

Each panel in the dashboard has a time period selector that recalculates the metrics for the selected time period. The following time periods are available:

```note::
   Some metrics such as bounce rate, time on page, exits, etc. rely on a visitor’s session end (30 minutes of inactivity or signing out) to be calculated. Therefore analytics data within the last 24 hours is displayed after the end of a session.
```

**Last 24 hours:** Displays data generated over the last 24 hours. 

**Last 7 days:** Displays data generated for the previous seven full days (the current day is excluded), in daily increments.

**Last 30 days (default):** Displays data generated for the previous 30 full days (the current day is excluded), in weekly increments.

**Last 90 days:** Displays data generated for the previous 90 full days (the current day is excluded), in 15 day increments.

**More Preset Periods:** Shows additional time periods: Yesterday, Last 28 days, Last 180 days, Last Year.

**Custom Range:** Select a start date and end date of your choice.

## Site Activities

The Site Activities panel presents a summary of how visitors interact with your Site. This panel contains the following metrics:

**Unique Visitors:** Total unique visitors.

**Sessions per Visitor:** An average of the number of sessions for each unique visitor. A single visitor can open multiple sessions. A session ends after 30 minutes of inactivity, or at midnight.

**Session Duration:** The length of time an average session lasts.

**Bounce Rate:** The percentage of visitors who view your Site's first page, but do nothing else before the session ends.

![Site Activities include a visualization of data points over a period of time.](./sites-dashboard/images/01.png)

Clicking each metric changes the visualization in the panel to display the selected metric. 

Note the legend at the bottom right shows a breakdown of the unique visitors between known visitors and anonymous visitors.

## Top Pages

The Top Pages panel shows visited pages, entrance pages, and exit pages:

![The Top Pages screen provides a list of pages in order of most visited.](./sites-dashboard/images/02.png)

**Visited Pages:** Your Site's most visited pages and the number of visitors for each.

**Entrance Pages:** The most common pages that visitors view first upon entering your Site and the number of entrances for each.

**Exit Pages:** The most common pages that visitors view when leaving your Site and the exit percentage for each. The exit percentage is the percentage of visitors for which the page is the last page in their session.

To view more detailed page analytics, click *View Pages* at the bottom of the panel. Alternatively, click the *Pages* tab at the top of the screen. Both take you to the page analytics discussed in [Viewing Page Data](../touchpoints.md#viewing-page-data).

## Acquisitions

The Acquisitions panel gives a comprehensive overview of how visitors arrive to your Site. The data is presented from a more high level view (i.e. Channels) down to a more granular level (i.e. Referrers). Having an understanding at these different levels leads to more informed business decisions. 

![See the different sources of traffic to your site with this Acquisitions panel.](./sites-dashboard/images/03.png)

**Channels:** A channel is a broad grouping of sources where users come from. For example, traffic coming from different search engines would all be grouped as organic search. Other possible channels are: direct, social, email, affiliates, referral, paid search, display, and other advertising. After identifying these different channels AC users can apply the data in different ways. For example, channel data on organic search could be applied to SEO efforts to improve search rankings.

**Source | Medium:** The source is the starting point of the visitor (e.g. search engine, website, etc.). The medium is a general category for the type of link the traffic came from (e.g. organic, ppc, referral, email, social, etc.). Having this data breakdown is very useful. For example seeing what traffic comes from paid advertising could validate your return on ad spend.

**Referrers:** For traffic that comes from another webpage, the webpage URL is passed along as a referrer from the user's web browser. 

The reporting works best if your marketing campaign uses UTM parameters. UTM parameters allow Analytics Cloud to determine where visitors arrive from (e.g., the specific referrer or ad campaign).

## Visitors by Day and Time

The Visitors by Day and Time panel visualizes the days and times when visitors come to your Site. This helps you understand when your Site is most active. You can use this information, for example, to know when to release important information or launch an advertising campaign.

![A specialized chart identifies when your site is most heavily visited.](./sites-dashboard/images/04.png)

The panel contains a grid with the days of the week on one axis and the time of day on the other axis. Darker cells in the grid indicate heavier Site traffic at the corresponding day and time. Tooltips for each cell show the number of visitors for that day and time.

```note::
   This panel is not affected by the timezone setting but is based on the Analytics Cloud user's timezone.
```

## Search Terms and Interests

The Search Terms and Interests panels show your visitors' most common search terms and the topics they're interested in, respectively. Search terms are collected from the search query parameter in your Site's URL. Interest topics are derived from the keyword metadata of the pages that visitors view. 

![Commonly used search terms are presented over a period time.](./sites-dashboard/images/05.png)

Note that the Search Terms panel is based on the search query parameters that can be customized in settings.

To view the full list of interest topics, click *All Interests* at the bottom of the panel. Alternatively, click the *Interests* tab at the top of the screen.

## Sessions by Location

The Sessions by Location panel shows the countries from which visitors access your Site. Countries with more visitors are shaded darker on the map. The country names appear below the map along with the number and percentage of visitors for each.

![Colored shading on a map diagram quickly identifies your users' country of origin.](./sites-dashboard/images/06.png)

## Session Technology

The Session Technology panel shows the devices, operating systems, and browsers visiting your Site. Tooltips for each graph element display more detailed data for that element. On the Devices tab, for example, mouse over each bar on the bar graph to see the operating system data for that device.

![You can use this screen to identify the distribution of users across operating systems.](./sites-dashboard/images/07.png)

## Cohort Analysis

The Cohort Analysis panel shows a [cohort analysis](https://en.wikipedia.org/wiki/Cohort_analysis) based on visitors from a specific acquisition date (the cohort) and whether they return to your Site over a given time period. 

![Identify usage patterns for groups of users over a period of time using Cohort Analysis.](./sites-dashboard/images/08.png)

Use the selector menus at the top left of the panel to select the visitor type (All, Anonymous, or Known) and time period (Day, Week, or Month).

For example, if you select All Visitors and Day, the percentage of visitors from a given acquisition date that return to your Site appear in the chart for each following day.
