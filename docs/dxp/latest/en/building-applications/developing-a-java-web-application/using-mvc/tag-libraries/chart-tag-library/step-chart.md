# Step Charts

Step charts contain multiple sets of data. A step chart steps between the points of data, resembling steps. Each data series (created with the `addColumns()` method) is defined with a new instance of the [`MultiValueColumn` object](https://docs.liferay.com/ce/apps/frontend-taglib/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), which takes an ID and a set of values. Follow these steps to configure your portlet to use step charts. 

1. Import the chart taglib along with the `StepChartConfig` and `MultiValueColumn` classes into your bundle's `init.jsp` file:

    ```markup
    <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
    <%@ page import="com.liferay.frontend.taglib.chart.model.point.step.StepChartConfig" %>
    <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>
    ```

1. Add the following Java scriptlet to the top of your `view.jsp`:

    ```java
    <%
    StepChartConfig _stepChartConfig = new StepChartConfig();

    _stepChartConfig.addColumns(
      new MultiValueColumn("data1", 100, 20, 30),
      new MultiValueColumn("data2", 20, 70, 100));
    }
    %>
    ```

1. Add the `<chart>` taglib to the `view.jsp`, passing the `_stepChartConfig` as the `config` attribute's value:

    ```markup
    <chart:step
      config="<%= _stepChartConfig %>"
    />
    ```

![A step chart steps between the points of data, resembling steps.](./step-chart/images/01.png)

You can also use an area step chart if you prefer. An area step chart highlights the area covered by a step graph. 

```markup
<chart:area-step 
  config="<%= _stepChartConfig %>" 
/>
```

![An area step chart highlights the area covered by a step graph.](./step-chart/images/02.png)

Awesome! Now you know how to create step charts for your apps. 

## Related Topics

* [Line Charts](./line-chart.md)
* [Scatter Charts](./scatter-chart.md)
* [Spline Charts](./spline-chart.md)