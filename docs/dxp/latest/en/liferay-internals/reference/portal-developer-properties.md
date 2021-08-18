# Portal Developer Properties

There are [Portal Properties](../../installation-and-upgrades/reference/portal-properties.md) that facilitate development. Liferay's [`portal-developer.properties`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-impl/src/portal-developer.properties) file includes all of them. The `portal-developer.properties` file is enabled by adding this setting to the top of your `portal-ext.properties` file:

```properties
include-and-override=portal-developer.properties
```

## Developer Settings

| Developer Setting | Description |
| :---------------- | :---------- |
| schema.module.build.auto.upgrade=true | Automatically upgrades the database when a module's build number has been increased since the last deployment. |
| upgrade.database.auto.run=true | Executes the upgrade process when the portal starts and modules are activated. |
| theme.css.fast.load=false | Disables merging the theme's CSS files to facilitate debugging. |
| theme.images.fast.load=false | Disables merging the theme's image files to facilitate debugging. |
| javascript.fast.load=true | Disables loading the packed version of files listed in the properties `Liferay-JS-Resources-Top-Head` and/or `Liferay-JS-Resources-Top-Head-Authenticated` of OSGi bundles' manifest files. |
| javascript.log.enabled=false | Disables the display of JavaScript logging. |
| layout.template.cache.enabled=false | Disables caching layout template content. |
| combo.check.timestamp=true | Facilitates debugging by disabling the combo servlet. See the [`combo.check.timestamp`](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Combo) definition for more information. |
| json.service.serialize.throwable=true | Returns information about server errors in the JSON response. |
| minifier.enabled=false | Enables minification of CSS and JavaScript resources. |
| module.framework.properties.initial.system.check.enabled=true | Checks modules during server startup. |
| module.framework.properties.osgi.console=localhost:11311 | Enables console access for debugging modules. |
| work.dir.override.enabled=true | Allows using the Liferay work directory to override JSP files within a deployed OSGi bundle. |

## Additional Information

* [Portal Properties](../../installation-and-upgrades/reference/portal-properties.md)
