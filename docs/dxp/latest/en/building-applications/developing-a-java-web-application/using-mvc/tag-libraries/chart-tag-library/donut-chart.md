# Donut Charts

Donut charts are percentage-based. A donut chart is similar to a pie chart, but it has a hole in the center. Each data set must be defined as a new instance of the `SingleValueColumn` object <!--[`SingleValueColumn` object](https://docs.liferay.com/dxp/apps/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html)-->. Follow these steps to configure your portlet to use donut charts. 

1. Import the chart taglib along with the `DonutChartConfig` and `SingleValueColumn` classes into your bundle's `init.jsp` file:

    ```markup
    <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
    <%@ page import="com.liferay.frontend.taglib.chart.model.percentage.donut.DonutChartConfig" %>
    <%@ page import="com.liferay.frontend.taglib.chart.model.SingleValueColumn" %>
    ```

1. Add the following Java scriptlet to the top of your `view.jsp`:

    ```java
    <%
    DonutChartConfig _donutChartConfig = new DonutChartConfig();

    _donutChartConfig.addColumns(
      new SingleValueColumn("data1", 30),
      new SingleValueColumn("data2", 70)
    );

    %>
    ```

1. Add the `<chart>` taglib to the `view.jsp`, passing the `_donutChartConfig` as the `config` attribute's value:

    ```markup
    <chart:donut
      config="<%= _donutChartConfig %>"
    />
    ```

![A donut chart is similar to a pie chart, but it has a hole in the center.](./donut-chart/images/01.png)

Awesome! Now you know how to create donut charts for your apps. 

## Related Topics

* [Donut Charts](./donut-chart.md)
* [Gauge Charts](./line-chart.md)
* [Pie Charts](./scatter-chart.md)