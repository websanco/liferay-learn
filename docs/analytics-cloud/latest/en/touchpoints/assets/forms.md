# Forms

[Forms](https://learn.liferay.com/dxp/latest/en/process-automation/forms/introduction-to-forms.html) are important direct data gathering tools for enterprises. Are your web forms providing you invaluable information or turning users away? Analytics Cloud gives you important insights.

In Analytics Cloud, find the Touchpoints section of the menu and click Assets. There are four tabs, each displaying a paginated list of the Asset Type indicated by the tab title. Click Forms.

![Forms metrics are presented in a table with submission, view, abandonment, and completion time statistics.](forms/images/01.png)

View the summary metrics for an Asset directly in the list, and click on an Asset to view its detail page.

## Visitor Behavior

As with all Asset types, the Visitors Behavior chart is at the top of its page and provides a line graph with several trend lines. Choose from four important metrics:

* Total Submissions
* Total Views
* Abandonment rate
* Average Completion Time

![Behavior is charted using a line graph to identify trends across a variety of metrics.](forms/images/02.png)

### Submissions

Submissions counts the number of times the Submit button was clicked on a Form. It's the gold standard metric for a form, because that's why the form was created in the first place: to collect data entered into the form. If the Submit button isn't clicked, you don't get the data you wanted.

The Submissions trend line shows the number of times the Submit button was clicked each day (or hour, if Yesterday or Last 24 Hours are selected) over the selected time period.

### Views

Views is a common metric among all Assets (and Pages).

Useful with the time period filter, Views is the number of views for a Form in a given period of time. It's not the same as the number of visitors, because it doesn't try to count only unique IP addresses. So over the last 30 days, one visitor (IP address) could come back to the Page 100 times. That means there are 100 Page Views, but only one visitor (assuming the same device was used to access the Page each time). However, a unique view isn't logged for a single user unless at least 30 minutes of inactivity on the Page passes before the user interacts with the Page again.

### Abandonment

Abandonment is the daily (or hourly if Yesterday or Last 24 Hours is selected) percentage of users that interacted with the form but stopped short of submitting an entry over the selected time period.

### Completion Time

Completion time is a daily average (or hourly if Yesterday or Last 24 Hours is selected) of the time it took for form users to go from their first interaction with the form until they hit the Submit button.

The time series metrics displayed in the Visitors Behavior chart are paramount to understanding Asset performance over time. But there's more to Asset Analytics.

## Audience

The Audience report uses charts to present information about the audience interaction with the asset. It answers these questions:

* How many users are interacting with my content?
* Of these users, how many are known or anonymous?
* Of the known users who interacted with my content, how many belong to Analytics Cloud Segments?
* Of the users in Segments, what are the top 15 segments?

Here are the charts in the Audience report:

**Submissions:** A donut chart that presents the percentage of total unique visitors who are known or anonymous. The center shows the total number of unique visitors. Tooltips show the number of users for that section (e.g, the number of known or anonymous users).

**Segmented Submissions:** A donut chart that presents the percentage of known individuals who belong or do not belong to one or more Analytics Cloud Segment. The center shows the total number of known individuals. Note that the total number may include some anonymous individuals depending on the segmentation criteria. Tooltips show the number of users for that section (e.g, the number of users belonging to one or more Segments). 

**Viewer Segments:** A bar chart that shows the percentage of known individuals that comprise each Segment. The chart shows a bar for each of the top five Segments, and then aggregates the remaining Segments into the last bar. A tooltip on the last bar shows the values for each of the remaining Segments.

![Forms audience metrics are visually presented with a variety of charts identifying unique visitors, known individuals, and known individual segments.](forms/images/03.png)

Note that the segmentation data presented about your audience is based on an individual belonging to a segment at the time of their visit.

## Submissions by Location

The map in this panel shows the number of submissions by country in the selected time period.

![A map chart identifies forms submissions by geography.](forms/images/04.png)

## Submissions by Technology

View a stacked bar graph of the Page's submissions by operating system (grouped by device type) in the default tab.

![Forms users can also be viewed by technology.](forms/images/05.png)

Click Web Browser to see a donut chart displaying up to the top eight web browsers over the selected time period. If applicable, remaining web browsers are aggregated in the ninth donut segment.

## Form Abandonment

Form Abandonment chart displays a bar graph. For the first row Step 1, the blue bar represents the portion of your visitors that abandoned the form without any interaction. Note that if your form has multiple steps there is a new row for each step followed by the input fields in that step.

![Form Abandonment charts help to breakdown user interactions with specific elements on a form.](forms/images/06.png)

For each input field, the blue bar represents the portion of visitors that abandoned the form at that particular field. This is useful information to have to diagnose potential issues with your forms. For example, in the image above, no visitor abandoned the form when entering their first and last name. But some abandoned the form when entering their email. With your own forms, use this analytics data to remove any input fields that seem to cause frequent form abandonment.

## Asset Appears On

The Asset Appears On table includes a Page Name and URL. Like any Page data in Analytics Cloud, only Pages that were interacted with in some way by site visitors are tracked and reported. It's important to note that the link doesn't take you to the actual Page with the Asset on it. Instead, it leads to the Page Analytics view of the Page. From there you can click the URL to the actual Page.

![A table lists where the assets appear.](forms/images/07.png)
