# Configuring Hreflang Meta Data

Hreflang tags are HTML attributes used to identify the language and geographic region targeted by alternative versions of a Site Page. In some cases, these attributes can help search engines serve the correct version of a Page to users and contribute to SEO. By default, Liferay DXP populates Page hreflang meta data with all available languages in the Site.

Beginning with Liferay 7.4, you can configure this behavior to only generate hreflang meta data for languages with translated content in a Page. This can be configured at both the instance and Site levels.

When hreflangs are limited in this way, Liferay's behavior can vary depending on Page type.

* **Widget Pages**: Languages used to translate a Widget Page's name are added to the hreflang tags.

* **Content Pages**/**Display Page Templates**: Languages used to translate a Page Fragment are added to the hreflang tags.

## Instance Settings

Follow these steps to configure hreflang settings for all Pages in an Instance:

1. Open the *Global Menu* (![Global Menu](../../images/icon-applications-menu.png)), click the *Control Panel* tab, and go to *Instance Settings* &rarr; *Pages* &rarr; *SEO* (Virtual Instance Scope).

1. Check/Uncheck *Enable Only Translated Hreflangs*.

   ![Use the check Box to enable or disable hreflang for all instance languages or only languages with translated content.](./configuring-hreflang-meta-data/images/01.png)

1. Click *Save*.

When enabled, hreflang meta data is only generated for languages with translated content in a Page. When disabled, Page hreflang tags are populated with all available languages in the Site.

## Site Settings

Follow these steps to configure hreflang settings for individual Sites:

1. Navigate to the desired *Site*.

1. Open the *Site Menu* (![Site Menu](../../images/icon-product-menu.png)), expand *Configuration*, and go to *Site Setting* &rarr; *Pages* &rarr; *SEO*.

1. Check/Uncheck *Enable Only Translated Hreflangs*.

   This configuration only affects Pages in the current Site.

   ![Use the check Box to enable or disable hreflang for all Site languages or only languages with translated content.](./configuring-hreflang-meta-data/images/02.png)

1. Click *Save*.

When enabled, hreflang meta data is only generated for languages with translated content in a Page. This configuration affects all Pages of a virtual Instance.

## Additional Information

* [Site Localization](./site-localization.md)
* [Exporting and Importing Translations](../../content-authoring-and-management/web-content/translating-web-content/exporting-and-importing-translations.md)
