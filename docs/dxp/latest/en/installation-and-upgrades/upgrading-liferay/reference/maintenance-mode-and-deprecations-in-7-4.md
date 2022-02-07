# Maintenance Mode and Deprecations in 7.4

With each new Liferay DXP and Liferay Portal release, a feature may not warrant continued enhancements or may become obsolete. Stopping enhancements for a feature puts it in *Maintenance Mode*.

**Maintenance Mode:** The feature is supported but no longer being enhanced.

Obsolete features are eventually removed or replaced with newer, better features. Before dropping support for an obsolete feature, Liferay marks it *Deprecated*.

**Deprecated:** The feature will be unsupported as early as the next minor version release. For example, support for a feature deprecated in 7.4 can be dropped as early as 7.5.

```{important}
Plan to stop using deprecated features because their support may cease as early as the next minor release.
```

```{important}
Please see [Maintenance Mode and Deprecation Policies](https://help.liferay.com/hc/en-us/articles/360015767952-Maintenance-Mode-and-Deprecation) for details.
```

## Deprecated Feature Availability

Deprecated features have different availability:

**Bundled:** The feature is included in the Liferay software.

**Marketplace:** The feature is included in a Marketplace app final version.

**Final:** The final version of the Marketplace app is released for this Liferay version.

**Archived:** The feature is unavailable, but its code is available in a sources artifact on Liferay's repository at <https://repository.liferay.com/nexus/index.html#welcome>.

**Removed:** The feature and its code are unavailable.

## Features Deprecated in 7.4

Here are the features deprecated in 7.4:

| Feature | Availability | Notes |
| :------ | :----------- | :---- |
| App Builder, App Builder Workflow | Removed | Replaced by [Liferay Objects](../../../building-applications/objects.md). |
| Bootstrap 3 & Bootstrap 4 compatibility layer | Removed | Opt in to adding a compatibility layer during [Theme upgrade](../../../site-building/site-appearance/themes/upgrading-a-theme.md) or manually add the layer.  |
| Commerce Account Widget | Removed | Replaced by the DXP/Portal Accounts Widget |
| Central Authentication Service (CAS) | Archived | Deprecated in 7.2. It is replaced by other solutions, including SAML, OpenID Connect, and Token-based SSO. |
| Commerce Categories Navigation widget | Bundled | Replaced by [Display Page Templates](../../../site-building/displaying-content/using-display-page-templates/about-display-page-templates-and-display-pages.md) [Menu Display](../../../site-building/site-navigation/configuring-navigation-menus.md) support. |
| Commerce Bill of Materials (BOM) | Removed | To be replaced by Shop By Diagram. |
| Commerce Category Content widget | Bundled | Replaced by [Display Page Templates](../../../site-building/displaying-content/using-display-page-templates/about-display-page-templates-and-display-pages.md). |
| Digest Authentication | Bundled | Digest Authentication  requires weak password hash storage, which is a vulnerability. |
| Dynamic Data Lists | Bundled | Deprecated in 7.3. It is replaced by [Liferay Objects](../../../building-applications/objects.md). |
| EXT Plugins support | Removed | Replaced by configuration settings and standard extension points. |
| Image Editor (Soy-based) | Removed | Replaced by a React-based image editor. |
| Google Login/SSO | Archived | Deprecated in 7.2. It is replaced by other solutions, including SAML, OpenID Connect, and Token-based SSO. |
| JAAS support code | Bundled | No replacement. |
| Liferay Mobile Device Detection Enterprise | Archived | Deprecated in 7.2. Contact 51Degrees for up-to-date definitions. |
| Liferay Screens | Removed. Final version released in 7.3. | No direct replacement; however, the Mobile SDK and Headless API support mobile application development. |
| Liferay Sync Connector and Sync Client | Archived | Deprecated in 7.3. It has no direct replacement. |
| Live Users | Bundled | Deprecated in 7.2. It is replaced by [Analytics Cloud](https://learn.liferay.com/analytics-cloud/latest/en/index.html) |
| Mobile SDK | Removed | Replaced by [Headless Delivery](../../../headless-delivery/using-liferay-as-a-headless-platform.md). |
| NTLM | Archived | Deprecated in 7.2. It is replaced by [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md). If you're using NTLM to authenticate Microsoft Windows™ accounts with , switch to using [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md). |
| OAuth 1.0a | Archived | Deprecated in 7.2. It is replaced by Liferay Connector to OAuth 2.0 |
| Polls | Removed | Replaced by [Forms/Forms Reports](../../../process-automation/forms/sharing-forms-and-managing-submissions/form-reports.md). |
| Search widget (classic) | Bundled | Deprecated in 7.1. It is replaced by the [Search widgets](../../../using-search/search-pages-and-widgets/search-results/search-results.md). |
| SOAP Web Services | Bundled (disabled by default) | Replaced by JSON web services, REST services, and GraphQL services. |
| Solr Connector | Removed | Deprecated in 7.3. It is replaced by Elasticsearch. |
| Theme: Hello World | Archived | Deprecated in 7.3. There is no direct replacement. |
| Web Content Search widget | Archived |  |

Features deprecated in earlier Liferay versions may also need to be considered, if you are upgrading from an earlier version.

* [7.3 Deprecations and Features Moved to Maintenance Mode](./maintenance-mode-and-deprecations-in-7-3.md)
* [7.2 Deprecations and Features Moved to Maintenance Mode](./maintenance-mode-and-deprecations-in-7-2.md)
* [7.1 Deprecations](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
* [7.0 Deprecations](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

## Features Moved to Maintenance Mode in 7.4

Here are the features moved to Maintenance Mode in 7.4:

| Feature | Notes |
| :------ | :---- |
| Export/Import | To be replaced replaced by [Publications](../../../site-building/publishing-tools/publications.md). |
| Kaleo Forms | To be replaced replaced by business processes for [Liferay Objects](../../../building-applications/objects.md). |
| Staging | To be replaced replaced by [Publications](../../../site-building/publishing-tools/publications.md). |

## Additional Information

* [7.4 Breaking Changes](../../../liferay-internals/reference/7-4-breaking-changes.md)
* [7.4 Default Setting Changes](./default-setting-changes-in-7-4.md)
