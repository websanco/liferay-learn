# Maintenance Mode and Deprecations in 7.2

With each new DXP release, a feature may not warrant continued enhancements or may become obsolete. Stopping enhancements for a feature puts it in *Maintenance Mode*.

**Maintenance Mode:** The feature is supported but no longer being enhanced.

Obsolete features are eventually removed or replaced with newer, better features. Before dropping support for an obsolete feature, Liferay marks it *Deprecated*.

**Deprecated:** The feature will be unsupported as early as the next minor version release. For example, support for a feature deprecated in 7.2 can be dropped as early as 7.3.

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

If you're upgrading from Liferay 7.0 or earlier, examine the deprecations that occurred in the applicable releases:

* [ 7.1](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
* [ 7.0](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

## Features Deprecated in 7.2

Here are the features deprecated in 7.2:

| Feature |  Availability |  Notes |
| ------- | ------------- | ------ |
| AlloyUI | Bundled | Replaced by [MetalJS](https://metaljs.com/) (temporary) exposed as [ClayUI tag](https://clayui.com/) equivalents. |
| Audience Targeting | Removed | Replaced by [Personalization](../../../site-building/personalizing-site-experience/personalizing-site-experience.html) (see [Migrating from Audience Targeting](https://help.liferay.com/hc/en-us/articles/360028711992-Manually-Migrating-from-Audience-Targeting)). |
| Central Authentication Service (CAS) | Bundled | Migrate to [SAML based authentication](../../securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md). |
| CMIS Store | Removed | Migrate to another [File Store option](../../../system-administration/file-storage/configuring-file-storage.md). Before [upgrading to Liferay 7.2](../upgrade-basics/upgrade-overview.md), [migrate your File Store data](../../../system-administration/file-storage/file-store-migration.md). |
| Google Login | Marketplace | Replaced by [OpenID Connect](../../securing-liferay/configuring-sso/other-ssos/using-openid-connect.md). |
| JCRStore | Removed | Migrate to another [File Store option](../../../system-administration/file-storage/configuring-file-storage.md). Before [upgrading to Liferay 7.2](../upgrade-basics/upgrade-overview.md), [migrate your File Store data](../../../system-administration/file-storage/file-store-migration.md). |
| Liferay Mobile Device Detection Lite | Final | No direct replacement. |
| Liferay Mobile Device Detection Enterprise | Removed | Contact 51Degrees for up-to-date definitions |
| Live Users | Bundled | Enabled through the [`live.users.enabled`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) [portal property](../../reference/portal-properties.md). |
| NTLM | Removed |  Security vulnerabilities persist with NTLM. It is replaced by [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md). If you're using NTLM to authenticate Microsoft Windows™ accounts with , switch to using [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md). |
| OAuth 1.0a | Marketplace | Replaced by OAuth 2.0, which is included in the bundle. |
| OpenAM / OpenSSO | Bundled | Migrate to [SAML based authentication](../../installation-and-upgrades/securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md). |
| OpenID | Marketplace | Replaced by [OpenID Connect](../../securing-liferay/configuring-sso/other-ssos/using-openid-connect.md). |
| Resources Importer | Bundled | Deprecated as of 7.1 with no direct replacement |
| RSS Publisher | Bundled | See [the article](https://help.liferay.com/hc/en-us/articles/360028820672-The-RSS-Publisher-Widget) on enabling and using this widget. |
| Search widget (classic) in *Tools* category | Bundled | Deprecated in 7.1. Replaced by the [Search widgets](../../../using-search/search-pages-and-widgets/search-results/search-results.md) in the *Search* category. |
| Sprite framework | Bundled | Liferay's image sprite framework is deprecated and is disabled by default via the `sprite.enabled` [portal property](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/portal-properties.html). You can still build image sprites using any framework you like and deploy them in your plugins. |
| Theme: Fjord | Final | No direct replacement. |
| Theme: Porygon | Final | No direct replacement. |
| Theme: Westeros | Final | No direct replacement. |
| User Group Pages (Copy Mode) | Bundled | See the [Legacy User Group Sites Behavior](../../../users-and-permissions/user-groups/user-group-sites.md) instructions on how to enable it. |
| Web Form | Removed | Final version released for 7.0. |

## Features Moved to Maintenance Mode in 7.2

Here are the features moved to maintenance mode in 7.2:

* Liferay Connected Services (LCS)
* Liferay Mobile Experience (Liferay Screens, Liferay Mobile SDK, Liferay Push)
* Liferay Sync
* Staging

## Additional Information

* [7.2 Breaking Changes](../../../liferay-internals/reference/7-2-breaking-changes.md)
* [7.2 Default Setting Changes](./default-setting-changes-in-7-2.md)