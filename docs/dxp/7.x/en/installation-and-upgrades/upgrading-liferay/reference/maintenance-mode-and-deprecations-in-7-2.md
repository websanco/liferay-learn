# Maintenance Mode and Deprecations in 7.2

With each new DXP release, a feature may not warrant continued enhancements or may become obsolete. Stopping enhancements for a feature puts it in *Maintenance Mode*.

**Maintenance Mode:** The feature is supported but no longer being enhanced.

Obsolete features are eventually either removed or replaced with newer, better features. Before dropping support for an obsolete feature, Liferay marks it *Deprecated*.

**Deprecated:** The feature will be unsupported as early as the next minor version release. For example, support for a feature deprecated in 7.2 can be dropped as early as 7.3.

```important::
   Plan to stop using deprecated features because their support may cease as early as the next minor release.
```

```important::
   Please see the `maintenance mode and deprecation policies <https://help.liferay.com/hc/en-us/articles/360015767952-Maintenance-Mode-and-Deprecation>`_ for details.
```

## Deprecated Feature Availability

Deprecated features have different availability:

**Bundled:** The feature is included in the Liferay product.

**Marketplace:** The feature is included in a Marketplace app final version.

**Archived:** The feature is unavailable, but its code is available.

**Removed:** The feature and its code are unavailable.

If you're upgrading from Liferay 7.0 or earlier, examine the deprecations that occurred in the applicable releases:

* [ 7.1](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
* [ 7.0](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

## Features Deprecate in 7.2

Here are the categories of features deprecated in 7.2:

* [Foundation](#foundation)
* [Forms](#forms)
* [Personalization](#personalization)
* [Search](#search)
* [Security](#secturiy)
* [User and System Management](#user-and-system-management)
* [Web Experience](#web-experience)

### Foundation

| Feature |  Availability |  Notes |
| --- | ------------- | ------ |
| AlloyUI | Bundled | Replaced by [MetalJS](https://metaljs.com/) (temporary) exposed as [ClayUI tag](https://clayui.com/) equivalents. |
| CMIS Store | Archived | Migrate to another [File Store option](../../../system-administration/file-storage/configuring-file-storage.md). Before [upgrading to Liferay 7.2](../upgrade-basics/upgrade-overview.md), [migrate your File Store data](../../../system-administration/file-storage/file-store-migration.md). |
| JCRStore | Archived | Migrate to another [File Store option](../../../system-administration/file-storage/configuring-file-storage.md). Before [upgrading to Liferay 7.2](../upgrade-basics/upgrade-overview.md), [migrate your File Store data](../../../system-administration/file-storage/file-store-migration.md). |
| Legacy Search Portlet | Bundled | Will be archived in a future release. This will be replaced by the [Search widgets](../../../using-search/search-pages-and-widgets/search-results/search-results.md). |
| Sprite framework | Bundled | Liferay's image sprite framework is deprecated and is disabled by default via the `sprite.enabled` [portal property](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html). You can still build image sprites using any framework you like and deploy them in your plugins. |

### Forms

| Feature | Availability | Notes |
| --- | ------------------ | ----------- |
| Web Form | Archived | Final version released for 7.0. |

### Personalization

| Feature |  Availability |  Notes |
| --- | ------------- | ------ |
| Audience Targeting | Archived | Replaced by [Personalization](../../../site-building/personalizing-site-experience/personalizing-site-experience.html) (see [Migrating from Audience Targeting](https://help.liferay.com/hc/en-us/articles/360028711992-Manually-Migrating-from-Audience-Targeting)). |

### Search

| Feature | Availability | Notes |
| --- | ------------- | ------ |
| Search Portlet | Archived | Replaced by multiple Search widgets |

### Security

| Feature |  Availability |  Notes |
| --- | ------------------ | ----------- |
| Central Authentication Service (CAS) | Bundled | Migrate to [SAML based authentication](../../securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md). |
| Google Login | Marketplace | Replaced by [OpenID Connect](../../securing-liferay/configuring-sso/other-ssos/using-openid-connect.md). |
| NTLM | Marketplace | Replaced by [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md). |
| OAuth 1.0a | Marketplace | Replaced by OAuth 2.0, which is included in the bundle. |
| OpenAM / OpenSSO | Bundled | Migrate to [SAML based authentication](../../installation-and-upgrades/securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md). |
| OpenID | Marketplace | Replaced by [OpenID Connect](../../securing-liferay/configuring-sso/other-ssos/using-openid-connect.md). |

#### Switching from NTLM to Kerberos

If you're using NTLM to authenticate Microsoft Windows™ accounts with , switch to using [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md). Security vulnerabilities persist with NTLM. NTLM has been deprecated and removed from the bundle, but you can still [build and deploy the module](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-security-sso-ntlm).

### User and System Management

| Feature |  Availability | Notes |
| --- | ------------------ | :---- |
| Live Users | Bundled | Enabled through the [`live.users.enabled`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) [portal property](../../reference/portal-properties.md). |

### Web Experience

| Feature |  Availability |  Notes |
| --- | ------------- | ------ |
| Resources Importer | Bundled | Deprecated as of 7.1 with no direct replacement |
| RSS Publisher | Bundled | See [the article](https://help.liferay.com/hc/en-us/articles/360028820672-The-RSS-Publisher-Widget) on enabling and using this widget. |
| User Group Pages (Copy Mode) | Bundled | See the [Legacy User Group Sites Behavior](../../../users-and-permissions/user-groups/user-group-sites.md) instructions on how to enable it. |

## Features Moved to Maintenance Mode in 7.2

Here are the features moved to maintenance mode in 7.2:

* Liferay Connected Services (LCS)
* Liferay Mobile Experience (Liferay Screens, Liferay Mobile SDK, Liferay Push)
* Liferay Sync
* Staging

## Additional Information

* [7.2 Breaking Changes](../../../liferay-internals/reference/7-2-breaking-changes.md)
* [7.2 Default Setting Changes](./default-setting-changes-in-7-2.md)