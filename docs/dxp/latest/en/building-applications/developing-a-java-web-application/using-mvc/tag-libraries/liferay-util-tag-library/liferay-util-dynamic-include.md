# Using Liferay Util Dynamic Include

The dynamic include tag lets you specify a point or points in a JSP or theme where a developer can inject additional HTML, resources, or functionality, using the `DynamicIncludeRegistry`. You can read more about the OSGi Service Registry [here](http://docs.spring.io/osgi/docs/current/reference/html/service-registry.html). The `key` attribute identifies the extension point. See [Dynamic Includes](https://help.liferay.com/hc/en-us/articles/360018165711-Dynamic-Includes) for example configurations that use dynamic include extension points to inject additional functionality.

The example configuration below uses the `<liferay-util:dynamic-include>` tag to include an extension point before the primary code and an extension point after the primary code:

```markup
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:dynamic-include key="/path/to/jsp#pre" />

<div>
        <p>And here we have our content</p>
</div>

<liferay-util:dynamic-include key="/path/to/jsp#post" />
```

Now you know how to use the `<liferay-util:dynamic-include>` tag to add extension points to your app.
    
## Related Topics

- [Dynamic Includes](https://help.liferay.com/hc/en-us/articles/360018165711-Dynamic-Includes)
- [Using the Liferay Util Body Top Tag](./liferay-util-body-top.md)
- [Using the Chart Taglib](https://help.liferay.com/hc/en-us/articles/360028832592-Using-the-Chart-Taglib-in-Your-Portlets)