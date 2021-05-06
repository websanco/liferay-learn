# Embedding Widgets via Templates

You can embed a widget in your theme to make it available on all pages using the chosen template file. When you are embedding a widget, you will need to reference the widget's portlet definition <!-- Add link to portlet definitions article when available --> to provide some of the component's values.

When you are ready, choose a FreeMarker template (`.ftl`) file in your theme (in the `src/templates/` directory) to embed the widget in. For example, choose `portal_normal.ftl` to embed the widget in most pages you add.

Add the `liferay_portlet["runtime"]` macro to the template file where you want to embed the widget, using the chosen widget's portlet name and instance ID (if the widget may be instanced):

```
<@liferay_portlet["runtime"]
    instanceID="INSTANCE_ID"
    portletName="PORTLET_NAME"
/>
```

Provide the portlet name by using the value of the widget's `javax.portlet.name` in the portlet definition. You must also provide an instance ID unless the `com.liferay.portlet.instanceable` property is set to `false`.

For example, this macro embeds a navigation menu into the chosen place in your template file:

```
<@liferay_portlet["runtime"]
    portletName="com_liferay_product_navigation_applications_menu_web_internal_portlet_ProductNavigationApplicationsMenuPortlet"
/>
```

Once you have added the embedded widget to your template, run `gulp deploy` to build your theme with the changes. The deployed theme now contains the embedded widget in the location you chose.

<!-- When available, add more information referencing article as to embedding widgets by function (developer tutorial) -->
