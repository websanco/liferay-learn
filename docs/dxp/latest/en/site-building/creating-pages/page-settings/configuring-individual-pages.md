# Configuring Individual Pages

You can configure individual Pages to meet your needs. All changes made in this way are scoped to the selected Page.

To configure a Page, open the *Site Menu* and go to *Site Builder* &rarr; *Pages*. Then click the *Actions* button (![Actions button](../../../images/icon-staging-bar-options.png)) for the Page you want to customize and select *Configure*.

![Click the Actions button for the Page you want to customize, and select Configure](./configuring-individual-pages/images/01.png)

Alternatively, you can access individual Page settings by navigating to the Page you want to customize and clicking the *Configure Page* button ( ![Configure Page Button](./../../../images/icon-cog.png) ) at the top right of the *Application Bar*.

These settings are organized into the following tabs:

* [General](#general)
* [SEO](#seo)
* [Open Graph](#open-graph)
* [Custom Meta Tags](#custom-meta-tags)

```{note}
Many of these settings can be localized to provide translations based on a User's locale. See [Introduction to Localization](../../../installation-and-upgrades/setting-up-liferay/initial-instance-localization.md) and [Modifying Localizable Site Fields](../../site-settings/site-localization.md#modifying-localizable-site-fields) for more information.
```

## General

Here you can configure the basic information and design of a Page, including its name, friendly URL, layout, and more.

### Name and Friendly URL

Use the Name field to set a Page's title used for Site navigation and displayed in a browser's title bar. Use the Friendly URL field to set a new URL for a Page, or click the *Restore* icon ( ![Friendly URL Restore icon](./../../../images/icon-restore.png) ) to see and restore a previously used URL. See [Configuring Your Page's Friendly URL](./configuring-your-pages-friendly-url.md) for more information.

![Use the Name field to set a Page's title.](./configuring-individual-pages/images/02.png)
![Use the Friendly URL field to set a Page's custom URL.](./configuring-individual-pages/images/03.png)

```{tip}
While a Page's name and friendly URL are initially defined together at Page creation, they are configured independently afterwards. It is best practice to update them together so that they match.
```

### Menu Displays

You can hide a Page from Menu Display widgets and fragments that show the Public Pages Hierarchy. When enabled, a hidden Page can still appear in other navigation menus if added to them. See [Configuring Site Navigation](./../../site-navigation/managing-site-navigation.md) for more information.

### Page Layout

For basic Widget Pages created using a global template (e.g., blog, wiki, search), determine whether the Page *Inherits Changes* made to the global template. If the Page does not inherit changes, select a custom layout to define row and column containers for its widgets.

![Select a layout template to define row and column containers for widgets.](./configuring-individual-pages/images/05.png)

### Categorization

Use *Topics* and *Tags* to categorize a Page's content so users can more easily find it. For more information on using tags and categories, see [Organizing Content with Tags and Categories](../../../content-authoring-and-management/tags_and_categories.html).

### Look and Feel

Here you can use the Page set's theme settings or define a unique look and feel for your Page. When you choose to customize the Page's theme, you can toggle various theme settings.

You can also access a CSS editor where you can add custom CSS code to the Page. Any CSS code added to a Page in this way is loaded after the theme.

![Determine whether to use the Page set's theme settings or define a unique look and feel for your Page.](./configuring-individual-pages/images/07.png)

```{note}
While Widget Pages include the *Look and Feel* tab here, Content Pages include these settings in the *Edit* sidebar menu.
```

### Advanced Settings

Access additional settings to customize a Page's content and features, including query strings, [mobile device rules](../../optimizing-sites/building-a-responsive-site/creating-mobile-device-rules.md), custom fields, and embedded widgets.

```{note}
While Widget Pages include the Advanced tab here, Content Pages include these settings in the Edit sidebar menu under *Look and Feel*.
```

**Query String**: Use this to provide default parameters for a Page when no others exist. Query strings are useful for web content templates.

**Target**: Use this field to determine Page link behavior using specific target attributes, such as opening the link in a new window, tab, or frameset.

**Icon**: Choose an icon to display along with a Page's name in the Site's navigation menus.

**JavaScript**: Add JavaScript code to a Page. Any added code is executed at the bottom of the Page. You can also access this editor in a Page Set's *Configuration* Page.

```{tip}
If your Site's theme uses JavaScript, it's best to add custom JavaScript code to the theme rather than an individual Page or Page Set. This ensures your Site's JavaScript code remains in one place. If your theme does not use JavaScript, place all of your Site's JavaScript code here.
```

**Mobile Device Rules**: Determine whether a Page uses its Page Set's rules for mobile devices or is uniquely configured. These rules determine how a Page's content renders for different types of mobile devices. 

**Customization Settings**: Determine whether a Widget Page's columns are *Customizable* for users. If customization is enabled, specify the columns users can customize. See [Personalizing Pages](../using-widget-pages/enabling-user-personalization-of-widget-pages.md) for more information.

![Determine whether users can customize a Widget Page.](./configuring-individual-pages/images/12.png)

**Custom Fields**: View and configure the *Custom Fields* you've defined for Site Pages. See [Custom Fields](../../../system-administration/configuring-liferay/adding-custom-fields.md) for more information.

**Embedded Widgets**: Access widgets you've embedded in a Page using [Web Content Templates](./../../../content-authoring-and-management/web-content/web-content-templates/embedding-widgets-in-templates.md), [Page Fragments](../../developer-guide/developing-page-fragments/developing-fragments-intro.md), or [Themes](../../site-appearance/themes/introduction-to-themes.md).

```{note}
The *Custom Fields* and *Embedded Widget* sections only appear once you've created custom fields and embedded widgets in a Page.
```

## SEO

Search engine optimization (SEO) refers to the methods used to improve your Page's ranking in search engine results Pages (SERP). The SEO tab provides easy access to settings you can use to optimize your Page's content. See [Configuring SEO and Open Graph](../../displaying-content/using-display-page-templates/configuring-seo-and-open-graph.md) for more information about the following settings.

### HTML Title

Use the HTML Title field to define a Page's `<title>` tag. This title is used by search engines to rank your Page and serves as the Page's heading in search engine results, though without replacing a Page's [Name](#name-and-friendly-url). The recommended length for an HTML title is under 60 characters.

### Description

Use the Description field to define a Page's `<description>` tag. This description is used by search engines to rank your Page and also appears in search result previews of your Page. The recommended length for a description is under 155 characters.

### Custom Canonical URL

Set a custom canonical URL for a Page. Custom URLs set in this way take precedence over the global and instance level settings.

### Keywords

List words that people are likely to use when searching for your Page's content. These keywords contribute to your Page's ranking.

```{tip}
Use keywords often throughout your Page's content, including titles, headings, descriptions, paragraphs, and image alt text.
```

### Preview SERP

Preview how a Page is displayed in search engine results Pages (SERP) based on your configuration of its HTML title, description, and custom URL.

![Preview how a Page is displayed in search engine results.](./configuring-individual-pages/images/13.png)

### Robots

Configure `robots.txt` rules for a Page. These rules provide instructions to search engines and other tools crawling and indexing your Site, either blocking or granting a crawler access to a specified path.

### Sitemap

Determine whether a Page is included in the sitemap used by site crawlers. By excluding a Page, you instruct site crawlers to skip it when crawling and indexing your Site.

### Page Priority

Set a Page's priority from 0.0 to 1.0. This informs tools crawling and indexing your Site how a Page should be prioritized relative to other Pages in your Site.

### Change Frequency

Use the drop-down menu to inform tools crawling and indexing your Site how frequently a Page is updated.

## Open Graph

> Available: Liferay DXP/Portal 7.3+

[Open Graph](https://ogp.me/) is an Internet protocol that standardizes previews of Site content when shared in application contexts that support it, such as Facebook, Slack, and Twitter. It does this by embedding structured data in page headers as `<meta>` tags, similar to [RDFa](https://en.wikipedia.org/wiki/RDFa).

In the *Open Graph* tab, you can use Open Graph `<meta>` tags to define a Page's metadata and create engaging representations of your content. Values defined here override default values defined at the [Site](../../site-settings/configuring-open-graph.md) level.

```{note}
Open Graph `<meta>` tags are only included in public page headers to unauthenticated users. They are not included in private pages, or in public pages when the user is logged in.
```

### Image

Use the Image field to define the following Open Graph `<meta>` properties for a Page:

   ```html
   <meta property="og:image" content="http://example.com/ogp.jpg" />
   <meta property="og:image:secure_url" content="https://secure.example.com/ogp.jpg" />
   <meta property="og:image:type" content="image/jpeg" />
   <meta property="og:image:width" content="400" />
   <meta property="og:image:height" content="300" />
   ```

### Image Alt Description

Use the Image Alt Description field to define the `og:image:alt` property for a Page. You can also localize an image's alt description via the *Language Flag* button.

### Custom Title and Description

Use the Title and Description fields to define the `og:title` and `og:description` properties. These properties create custom text used for rich previews in place of a Page's HTML title and description. You can also localize these values via their *Language Flag* buttons.

### Preview Open Graph Page Representation

Preview your Page's Open Graph configuration. This section displays how your Page's content appears when its URL is shared in contexts supporting the protocol. Though an image's ratio may change depending on where the URL is posted.

![Preview your Open Graph configuration.](./configuring-individual-pages/images/23.png)

## Custom Meta Tags

Here you can add custom `<meta>` tags to the `<head>` of a Page. These tags are only visible in a Page's HTML source code and are used to describe a Page's data and determine how that data is displayed in different contexts, such as search engine results and social media posts. Each tag is defined using property and content attributes.

![Add custom meta tags to a Page's head using the Property and Content fields.](./configuring-individual-pages/images/24.png)

## Additional Information

* [Adding Pages to a Site](./../adding-pages/adding-a-page-to-a-site.md)
* [Using Content Pages](../using-content-pages.md)
* [Configuring Page Sets](./configuring-page-sets.md)
