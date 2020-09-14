# Maintenance Mode and Deprecations in 7.3

In some new Liferay versions, Liferay stops enhancing specific items or makes plans to remove specific apps and capabilities.

1. **Moving an Item to Maintenance Mode:** Support continues for the item but enhancements cease. Future deprecation is not necessarily implied.

2. **Deprecation:** Support for a feature may stop in the next minor release or later. In that future release, Liferay removes the feature from the Liferay bundle. The removed feature may published as a [Marketplace app](https://web.liferay.com/marketplace) or its source code may be preserved as an archive. Liferay puts many archives in the `modules/apps/archived` source code folder. During deprecation, the feature may be disabled.

```important::
   Please see the `maintenance mode and deprecation policies <https://help.liferay.com/hc/en-us/articles/360015767952-Maintenance-Mode-and-Deprecation>`_ for details.
```

```note::
   Plan to stop using deprecated apps/capabilities because they are not being developed and will be removed.
```

## Items Moved to Maintenance Mode

* Export/Import
* Site Templates
* Liferay Reports
* Liferay Drools
* Asset Publisher (deprecation is planned in 7.4)
* Liferay Mobile Experience: Mobile SDK, Screens, Push

## Deprecations

| Feature | Availability | Notes |
| :------ | :----------- | :---- |
| DDM, DDL, and Polls | Bundled | Replaced by App Builder and Forms Reports. |
| Elasticsearch 6 Connector | Archived | Replaced by Elasticsearch 7 Connector. |
| Flash widget | Removed | No direct replacement. [FlashPlayer end-of-life is Dec 31, 2020](https://www.adobe.com/products/flashplayer/end-of-life.html#:~:text=As%20previously%20announced%20in%20July,(%E2%80%9CEOL%20Date%E2%80%9D)). |
| Internet Explorer 11 (IE11) browser support | Bundled | Switch to Microsoft Edge with IE11 support enabled. |
| JQuery default global Liferay variables | Disabled | ou can bring back the old behaviour by setting the `Enable jQuery` property in *System Settings* &rarr; *Third Party* &rarr; *jQuery* to `true`. |
| Liferay Bookmarks | Archived | No direct replacement. |
| Liferay Sync Connector and Sync Client | Marketplace and [downloads](https://web.liferay.com/downloads/liferay-sync) | No direct replacement. |
| SOAP Web Services | disabled | The Axis Servlet (e.g., publishes services to `http://localhost:8080/api/axis/`) can be enabled with this Portal Property setting `axis.servlet.enabled`. Liferay `*SOAP` classes are deprecated. |
| Solr | Marketplace | Replaced by Elasticsearch. |
| Theme: Fjord | Archived | No direct replacement. |
| Theme: Porygon | Archived | No direct replacement. |
| Theme: Westeros | Archived | No direct replacement. |
| Virtual Instance: Per-virtual instance portal property files | Archived | Replaced by virtual instance configuration in the Control Panel. |
| Xuggler | Removed | No direct replacement. |

Features deprecated in earlier Liferay versions may also need to be considered, if you are upgrading from an earlier version.

* [7.2 Deprecations and Features Moved to Maintenance Mode](./maintenance-mode-and-deprecations-in-7-2.md)
* [7.1 Deprecations](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
* [7.0 Deprecations](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

## Addtional Information

* [7.3 Breaking Changes](../../../liferay-internals/reference/7-3-breaking-changes.md)
* [7.3 Default Setting Changes](./7-3-efault-setting-changes.md)