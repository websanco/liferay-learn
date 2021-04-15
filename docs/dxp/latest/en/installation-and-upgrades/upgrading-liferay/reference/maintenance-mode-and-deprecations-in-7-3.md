# Maintenance Mode and Deprecations in 7.3

With each new DXP release, a feature may not warrant continued enhancements or may become obsolete. Stopping enhancements for a feature puts it in *Maintenance Mode*.

**Maintenance Mode:** The feature is supported but no longer being enhanced.

Obsolete features are eventually removed or replaced with newer, better features. Before dropping support for an obsolete feature, Liferay marks it *Deprecated*.

**Deprecated:** The feature will be unsupported as early as the next minor version release. For example, support for a feature deprecated in 7.3 can be dropped as early as 7.4.

```important::
   Plan to stop using deprecated features because their support may cease as early as the next minor release.
```

```important::
   Please see `Maintenance Mode and Deprecation Policies <https://help.liferay.com/hc/en-us/articles/360015767952-Maintenance-Mode-and-Deprecation>`_ for details.
```

## Deprecated Feature Availability

Deprecated features have different availability:

**Bundled:** The feature is included in the Liferay product.

**Marketplace:** The feature is included in a Marketplace app final version.

**Final:** The final version of the Marketplace app is released for this Liferay version.

**Archived:** The feature is unavailable, but its code is available in a sources artifact on Liferay's repository at <https://repository.liferay.com/nexus/index.html#welcome>.

**Removed:** The feature and its code are unavailable.

## Features Deprecated in 7.3

Here are the features deprecated in 7.3:

| Feature | Availability | Notes |
| :------ | :----------- | :---- |
| Central Authentication Service (CAS) | Bundled | Deprecated in 7.2. Migrate to [SAML based authentication](../../securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md). |
| DDM Form Builder, DDL, and Polls | Bundled | Replaced by App Builder and Forms Reports. |
| Elasticsearch 6 Connector | Removed | Replaced by Elasticsearch 7 Connector. |
| Flash widget | Removed | No direct replacement. [FlashPlayer end-of-life](https://www.adobe.com/products/flashplayer/end-of-life.html) is Dec 31, 2020. |
| Internet Explorer 11 (IE11) browser support | Bundled | Switch to Microsoft Edge with IE11 support enabled. |
| JAAS support | Bundled | No direct replacement. |
| JQuery default global Liferay variables | Bundled (disabled) | You can bring back the old behaviour by setting the `Enable jQuery` property in *System Settings* &rarr; *Third Party* &rarr; *jQuery* to `true`. |
| Liferay Bookmarks | Archived | No direct replacement. |
| Liferay Mobile Device Detection Lite | Archived | No direct replacement. |
| Liferay Sync Connector and Sync Client | Marketplace and [downloads](https://web.liferay.com/downloads/liferay-sync) | No direct replacement. |
| OpenAM / OpenSSO | Bundled | Deprecated in 7.2. Migrate to [SAML based authentication](../../installation-and-upgrades/securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md). |
| OpenID | Marketplace | Replaced by [OpenID Connect](../../securing-liferay/configuring-sso/other-ssos/using-openid-connect.md). Deprecated in 7.2. |
| Project Template: project-templates-activator | Archived [in GitHub](https://github.com/liferay/liferay-blade-cli/tree/master/extensions) | No replacement. |
| Project Template: project-templates-freemarker-portlet | Archived [in GitHub](https://github.com/liferay/liferay-blade-cli/tree/master/extensions) | No replacement. |
| Screens | Github - iOS: [5.2.0](https://github.com/liferay/liferay-screens/releases/tag/5.2.0), Android: [5.1.0](https://github.com/liferay/liferay-screens/releases/tag/5.1.0-android) |  Use the [Mobile SDK](../../../developing-applications/tooling/other-tools/mobile-sdk.md) and the [Headless APIs](../../..//headless-delivery/consuming_apis.html). |
| Search widget (classic) in *Tools* category | Bundled | Deprecated in 7.1. Replaced by the [Search widgets](../../../using-search/search-pages-and-widgets/search-results/search-results.md) in the *Search* category. |
| SOAP Web Services | Bundled (disabled) | The Axis Servlet (e.g., publishes services to `http://localhost:8080/api/axis/`) can be enabled with this Portal Property setting `axis.servlet.enabled`. Liferay `*SOAP` classes are deprecated. |
| Solr | Marketplace | Replaced by Elasticsearch. |
| Theme: Fjord | Archived | No direct replacement. |
| Theme: Hello World | Bundled | No direct replacement. |
| Theme: Porygon | Archived | No direct replacement. |
| Theme: Westeros | Archived | No direct replacement. |
| Virtual Instance: Per-virtual instance portal property files | Archived | Replaced by virtual instance configuration in the Control Panel. |
| Xuggler | Removed | No direct replacement. |

Features deprecated in earlier Liferay versions may also need to be considered, if you are upgrading from an earlier version.

* [7.2 Deprecations and Features Moved to Maintenance Mode](./maintenance-mode-and-deprecations-in-7-2.md)
* [7.1 Deprecations](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
* [7.0 Deprecations](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

## Features Moved to Maintenance Mode in 7.3

Here are the features moved to Maintenance Mode in 7.3:

* Export/Import
* Liferay Drools
* Liferay Mobile Experience: Mobile SDK, Push
* Liferay Reports
* Site Templates

## Additional Information

* [7.3 Breaking Changes](../../../liferay-internals/reference/7-3-breaking-changes.md)
* [7.3 Default Setting Changes](./default-setting-changes-in-7-3.md)