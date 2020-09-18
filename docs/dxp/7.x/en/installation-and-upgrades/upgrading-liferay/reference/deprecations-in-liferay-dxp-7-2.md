# Deprecations in Liferay DXP 7.2

Liferay DXP will sometimes stop developing or archive features with new versions. In DXP 7.2, Liferay has deprecated several apps and features.

Features in Liferay DXP that have been deprecated may still be present either in the core product, or as a [Marketplace download](https://web.liferay.com/marketplace). Deprecated features may be archived in a future release. Archived apps are no longer maintained or released with new versions of Liferay DXP.

```note::
   All applications deprecated by Liferay are no longer in active development. You should therefore plan to stop using these applications.
```

Features deprecated in earlier versions of Liferay DXP may also need to be considered, if you are upgrading from an earlier version than 7.1. See the following deprecations for any versions between your version pre-upgrade and 7.2:

* [Liferay DXP 7.1](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
* [Liferay DXP 7.0](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

The deprecations in DXP 7.2 are as follows:

## Foundation

| Feature |  Availability |  Notes |
| --- | ------------- | ------ |
| AlloyUI | Bundled | Replaced by [MetalJS](https://metaljs.com/) (temporary) exposed as [ClayUI tag](https://clayui.com/) equivalents. |
| CMIS Store | Archived | Migrate to another [File Store option](../../../system-administration/file-storage/configuring-file-storage.md). Before [upgrading to DXP 7.2](../upgrade-basics/upgrade-overview.md), [migrate your File Store data](../../../system-administration/file-storage/file-store-migration.md). |
| JCRStore | Archived | Migrate to another [File Store option](../../../system-administration/file-storage/configuring-file-storage.md). Before [upgrading to DXP 7.2](../upgrade-basics/upgrade-overview.md), [migrate your File Store data](../../../system-administration/file-storage/file-store-migration.md). |
| Legacy Search Portlet | Bundled | Will be archived in a future release. This will be replaced by the [Search widgets](../../../using-search/search-pages-and-widgets/search-results/search-results.md). |
| Sprite framework | Bundled | Liferay's image sprite framework is deprecated and is disabled by default via the `sprite.enabled` [portal property](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html). You can still build image sprites using any framework you like and deploy them in your plugins. |

## Personalization

| Feature |  Availability |  Notes |
| --- | ------------- | ------ |
| Audience Targeting | Archived | Replaced by [Personalization](../../../site-building/personalizing-site-experience/personalizing-site-experience.html) (see [Migrating from Audience Targeting](https://help.liferay.com/hc/en-us/articles/360028711992-Manually-Migrating-from-Audience-Targeting)). |

## Web Experience

| Feature |  Availability |  Notes |
| --- | ------------- | ------ |
| RSS Publisher | Bundled | See [the article](https://help.liferay.com/hc/en-us/articles/360028820672-The-RSS-Publisher-Widget) on enabling and using this widget. |
| User Group Pages (Copy Mode) | Bundled | See the [Legacy User Group Sites Behavior](../../../users-and-permissions/user-groups/user-group-sites.md) instructions on how to enable it. |
| Resources Importer | Bundled | Deprecated as of 7.1 with no direct replacement |

## Forms

| Feature | Availability | Notes |
| --- | ------------------ | ----------- |
| Web Form | Archived | Final version released for 7.0. |

## Search

| Feature | Availability | Notes |
| --- | ------------- | ------ |
| Search Portlet | Archived | Replaced by multiple Search widgets |

## Security

| Feature |  Availability |  Notes |
| --- | ------------------ | ----------- |
| Central Authentication Service | Bundled | Migrate to [SAML based authentication](../../securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md). |
| Google Login | Marketplace | Replaced by [OpenID Connect](../../securing-liferay/configuring-sso/other-ssos/using-openid-connect.md). |
| NTLM | Marketplace | Replaced by [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md). |
| OAuth 1.0a | Marketplace | Replaced by OAuth 2.0, which is included in the bundle. |
| OpenAM / OpenSSO | Bundled | Migrate to [SAML based authentication](../../installation-and-upgrades/securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md). |
| OpenID | Marketplace | Replaced by [OpenID Connect](../../securing-liferay/configuring-sso/other-ssos/using-openid-connect.md). |

### Switching from NTLM to Kerberos

If you're using NTLM to authenticate Microsoft Windows™ accounts with Liferay DXP, switch to using [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md). Security vulnerabilities persist with NTLM. NTLM has been deprecated and removed from the bundle, but you can still [build and deploy the module](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-security-sso-ntlm).

## User and System Management

| Feature |  Availability |
| --- | ------------------ |
| Live Users | Enabled through the [`live.users.enabled`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) [portal property](../../reference/portal-properties.md). |

## Applications in Maintenance Mode

Although no action is immediately necessary, you may also want to consider applications that are put into maintenance mode in DXP 7.2. These applications are not being deprecated or archived, but will no longer receive updates other than bug fixes. See [Features in Maintenance Mode](./features-in-maintenance-mode.md) for more information.
