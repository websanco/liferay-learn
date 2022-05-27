# Site Settings UI Reference

> The Site Settings application changes significantly in Liferay DXP 7.4+. See [Site Settings UI Reference in Liferay DXP 7.2 and 7.3](#site-setting-ui-reference-in-liferay-dxp-7-2-and-7-3) below for information on previous versions.

Starting with Liferay DXP 7.4, the Site Settings application layout is consistent with other configuration areas, such as Instance Settings or System Settings.

## Accessing the Site Settings Application

1. Go to Site Administration &rarr; *Configuration* &rarr; *Site Settings*.
1. Click on a setting to open its configuration.

You can find the following configuration areas:

- [Commerce](#commerce)
- [Platform](#platform)
- [Content and Data](#content-and-data)
- [Other](#other)

![Site Settings are organized into four areas.](./site-settings-ui-reference/images/20.png)

## Commerce

By default, the Commerce area includes the following settings:

- Catalog
- Orders
- Payment
- Shipping

Here you can configure the Commerce settings at the Site scope. For more information about Commerce settings, see [Liferay Commerce Configuration Overview](https://learn.liferay.com/commerce/latest/en/store-management/liferay-commerce-configuration-overview.html).

![Default Site Settings for Commerce](./site-settings-ui-reference/images/21.png)

## Platform

By default, the Platform area includes the following settings:

- [Analytics](#analytics)
- [Localization](#localization)
- [Site Configuration](#site-configuration)
- [Third Party](#third-party)
- [Third Party Applications](#third-party-applications)
- [Users](#users)

### Analytics

Use these settings to configure the Google Analytics options for your Site. You can also set the Piwik Analytics tracking code.

```{note}
In the Piwik configuration, enter the full script code including the script start and end tags.
```

If you require a different analytics service, you can add it. See [Adding a New Analytics Service](./adding-a-new-analytics-service.md) for more information and how to add additional fields for different analytics services.

### Localization

Here you can choose whether to use the installation's default language options or define your own for the Site. For more information on setting your Site's language options, read [Site Localization](../site-settings/site-localization.md).

![Use the Localization settings to set your Site language options.](./site-settings-ui-reference/images/22.png)

### Site Configuration

By default, Site Configuration includes the Details and Site URL sections.

#### Details

| Setting | Description |
| :--- | :--- |
| Site ID | A unique number automatically generated for a Site at its creation. This ID is permanent and cannot be changed. |
| Description | Use the this field to explain a Site's purpose.|
| Parent Site | Designate a Site as a child Site by selecting its parent Site. See [Site Hierarchies](../building-sites/site-hierarchies.md) for more information. |
| Membership Type | Determines how restrictive a Site's membership is. See [Changing Site Membership Types](../site-settings/site-users/changing-site-membership-type.md) for more information.|
| Allow Manual Membership Management | Determine whether to allow members to be manually added or removed from a Site. See [Changing Site Membership Type](../site-settings/site-users/changing-site-membership-type.md) for more information. |

#### Site URL

| Setting | Description |
| :--- | :--- |
| Friendly URL | Set a custom URL for Site pages. See [Configuring Your Site's Friendly URL](../site-settings/managing-site-urls/configuring-your-sites-friendly-url.md) for more information. |
| Pages | Set Virtual Hosts that map to friendly URL for the Site's pages. See [Configuring Virtual Hosts Site URLs](../site-settings/managing-site-urls/configuring-virtual-hosts-site-urls.md) for more information. |

### Third Party

Configure the PunchOut2Go Site setting for Liferay Commerce. For more information, see [Liferay Commerce Connector to PunchOut2Go Reference Guide](https://learn.liferay.com/commerce/latest/en/add-ons-and-connectors/liferay-commerce-connector-to-punchout2go-reference-guide.html).

### Third Party Applications

By default, Third Party Application includes the Maps and Google Places sections.

| Section | Description |
| :--- | :--- |
| Asset Auto Tagging | When enabled, assets are auto tagged by the providers configured at the Instance scope. See [Configuring Asset Auto Tagging](../../content-authoring-and-management/tags-and-categories/auto-tagging/configuring-asset-auto-tagging.md) for more information. |
| Categorization | Use this section to categorize your Site. This setting does not affect the Categories and Tags available for the Site. For more information on using Categories and Tags, see [Organizing Content with Categories and Tags](../../content-authoring-and-management/tags-and-categories/organizing-content-with-categories-and-tags.md). |

### Users

By default, the Users section includes the Default User Associations setting, where you can configure the default Roles and Teams for new Site members. For more information, see [Configuring Role and Team Defaults for Site Members](../site-settings/site-users/configuring-role-and-team-defaults-for-site-members.md).

## Content and Data

By default, the Content and Data area includes the following settings:

- [Assets](#assets)
- [Click to Chat](#click-to-chat)
- [Community Tools](#community-tools)
- [Digital Signature](#digital-signature)
- [Documents and Media](#documents-and-media)
- [Message Boards](#message-boards)
- [Pages](#pages)
- [Recycle Bin](#recycle-bin)
- [Reports](#reports)
- [Sharing](#sharing)

### Assets

| Section | Description |
| :--- | :--- |
| Maps | Select which maps API provider to use when displaying geo-localized assets in your Site. Geo-localized assets can be displayed for documents, web content articles, DDL records, and more. See [Geo-locating Assets](../../content-authoring-and-management/tags-and-categories/geolocating-assets.md) for more information. |
| Google Places API Key | Set the Google Places API Key. |

### Click to Chat

> Available: Liferay 7.4 GA2+

You can integrate Liferay DXP with a number of live support chat platforms. The configuration at Site scope depends on the Click to Chat configuration at Instance scope. For more information, read [Enabling Automated Live Chat Systems](../personalizing-site-experience/enabling-automated-live-chat-systems/enabling-automated-live-chat-systems.md).

You can see the type of configuration for this setting at the Instance scope under the Site Settings Strategy text:

![Find the type of configuration at the Instance scope under Site Settings Strategy.](./site-settings-ui-reference/images/23.png)

```{note}
Depending on your configuration at the Instance scope, you may not be able to update the configuration at Site scope. To verify your Instance Configuation, got to the Site Menu → Control Panel → Instance Settings → Content and Data → Click to Chat.
```

### Community Tools

| Section | Description |
| :--- | :--- |
| Mentions | Determines whether to allow Users to mention other Users in Site applications. See [Configuring Mentions](../../collaboration-and-social/notifications-and-requests/user-guide/configuring-mentions.md) for more information. |
| Ratings | Here you can select the type of ratings used for the different Site applications. See [Configuring Content Rating Types](../site-settings/site-content-configurations/configuring-content-rating-type.md) for more information. |

### Digital Signature

You can integrate Liferay DXP with DocuSign to manage electronic signatures. The configuration at Site scope depends on the Digital Signature configuration at Instance scope. You can check the Instance configuration  under the Site Settings Strategy text:

![Find the type of configuration at the Instance scope under Site Settings Strategy.](./site-settings-ui-reference/images/24.png)

```{note}
Depending on your Digital Signature configuration at the Instance scope, you may not be able to update the configuration at Site scope. To verify your Instance Configuation, go to Site Menu → Control Panel → Instance Settings → Content and Data → Digital Signature. 
```

### Documents and Media

When enabled, a User with view permission can browse the Site's document library files and folders. For more information, see [Securing Site Documents Content](../site-settings/site-content-configurations/securing-site-documents-content.md).

### Message Boards

Using this option, you can enable automatic moderation of user messages on Message Boards after a predefined number of messages.

![Configure automatic moderation of Message Boards.](./site-settings-ui-reference/images/25.png)

### Pages

| Section | Description |
| :--- | :--- |
| Google PageSpeed Insights (Liferay DXP 7.4+) | Enable the Google PageSpeed Insight integration in Liferay DXP. Using this option, you can audit your pages for accessibility issues and check suggestions for fixing them. See [Analyzing SEO and Accessibility on Pages](../../content-authoring-and-management/content-performance-tool/analyzing-seo-and-accessibility-on-pages.md) for more information. |
| Open Graph | These tags define page metadata to create engaging representations of your Site's content when shared in applications that support the Open Graph protocol, such as Facebook, Slack, and Twitter. When enabled Open Graph `<meta>` tags are embedded in the `head` of the Site's pages. For more information, read [Configuring Open Graph](./configuring-open-graph.md). |
| Pages | View the Site's pages. On this section, you can also configure the propagation of changes from a Site template, if one exists. For more information, read [Building Sites with Site Templates](../../../en/site-building/building-sites/building-sites-with-site-templates.md). |

### Recycle Bin

Configure the Recycle Bin for a Site. You can specify the number of minutes an asset remains in the Recycle Bin before automatic deletion. By default, the max age for recycled items is 43200 minutes (30 days). See [Configuring the Asset Recycle Bin for Sites](../site-settings/site-content-configurations/configuring-the-asset-recycle-bin-for-sites.md) for more information.

### Reports

Settings for the Reports Group Service functionality.

### Sharing

| Section | Description |
| :--- | :--- |
| Content Sharing | Enable this option to allow child Sites to display content from this Site, including structures, templates, categories, or widget templates. When you disable this option, you immediately revoke content sharing from all child Sites. See [Managing Content Sharing Across Sites](../site-settings/site-content-configurations/managing-content-sharing-across-sites.md) for more information. |
| Sharing | When enabled, Users can share items with one another. See [Enabling and Configuring Sharing](../../content-authoring-and-management/documents-and-media/publishing-and-sharing/managing-document-access/enabling-and-configuring-sharing.md) for more information. |

## Other

By default, the Other area includes the following settings:

| Section | Description |
| :--- | :--- |
| Other | Includes custom Site settings you have defined for your Site, if any. For more information, read [Setting and Accessing Configurations](../../building-applications/core-frameworks/configuration-framework/setting-and-accessing-configurations.md). |
| Tax | Configure the settings for the Remote Commerce Tax Configuration in Liferay Commerce. For more information, see the [Liferay Commerce](https://learn.liferay.com/commerce/latest/en/index.html) documentation. |
| category.tax-rate | Configure the settings for the Liferay Commerce Avalara integration. For more information, see the [Liferay Commerce](https://learn.liferay.com/commerce/latest/en/index.html) documentation. |

## Site Setting UI Reference in Liferay DXP 7.2 and 7.3

To view a Site's settings, open the Site Menu (![Site Menu](../../images/icon-product-menu.png)) and go to *Configuration* &rarr; *Settings*, where you'll see the following tabs:

* [General](#general)
* [Social](#social)
* [Language](#language)
* [Advanced](#advanced)

![Site Settings are organized into four tabs.](./site-settings-ui-reference/images/01.png)

```{note}
Many of these settings can be localized to provide translations based on a user's locale. See [Introduction to Localization](https://help.liferay.com/hc/en-us/articles/360028746672-Introduction-to-Localization) and [Modifying Localizable Site Fields](./site-localization.md#modifying-localizable-site-fields) for more information.
```

### General

General settings range from core configuration, like a Site's Membership Type, to finer details like Documents and Media indexing options. The tab's content is organized into the following subsections: Details, Pages, Categorization, Site URL, Documents and Media, Open Graph, Asset Auto Tagging, Sharing, and Custom Fields.

#### Details

**Site ID**: A unique number automatically generated for a Site at its creation. This ID is permanent and cannot be changed.

**Name**: Set a Site's title using the *Name* field. This title is displayed in the browser's title bar as well as the header for each Site page. You can also localize a Site's name via the *Language Flag* button.

**Description**: Use the *Description* field to explain a Site's purpose. You can also localize a Site's description via the *Language Flag* button.

**Active**: Determine whether a Site is *Active* or *Inactive*. While inactive, a site is inaccessible to users, though it can be reactivated if desired.

**Membership Type**: A *Membership Type* determines how restrictive a Site's membership is. See [Changing Site Membership Types](./site-users/changing-site-membership-type.md) for more information.

**Allow Manual Membership Management**: Determine whether to allow members to be manually added or removed from a Site. If your Site's membership is handled automatically by a membership policy, you can disable this setting. See [Managing Membership Policies for Sites](./site-users/changing-site-membership-type.md) for more information.

**Parent Site**: Designate a Site as a child Site by selecting its parent Site. See [Site Hierarchies](../building-sites/site-hierarchies.md) for more information.

**Limit Membership to Parent Site Members**: Determine whether a child Site's membership is limited to members of its parent Site. This option only appears for child Sites.

#### Pages

View a Site's Public and Private Pages, if any exist, and enable or disable the propagation of changes from the selected Site template. If they don't exist, a Site Templates selector appears for creating pages with a template.

![In the Pages section, you can view a Site's Public and Private Pages.](./site-settings-ui-reference/images/03.png)

### Custom Fields

View and configure any custom fields you've defined for your pages. With these fields, you can set page metadata, such as author, date of creation, and geolocation. This section only appears once you've configured custom fields for your Site. See [Custom Fields](../../system-administration/configuring-liferay/adding-custom-fields.md) for more information.

#### Categorization

Use Categories and Tags to categorize a Site's content so users can more easily find it. For more information on using tags and categories, see [Organizing Content with Tags and Categories](../../content-authoring-and-management/tags-and-categories/organizing-content-with-categories-and-tags.md).

#### Site URL

**Friendly URL**: Set a custom URL for both public and private Site pages. See [Configuring Your Site's Friendly URL](./managing-site-urls/configuring-your-sites-friendly-url.md) for more information.

**Public and Private Virtual Host**: Set public and private Virtual Hosts to map to a Site's Public and Private Pages. See [Configuring Virtual Hosts Site URLs](./managing-site-urls/configuring-virtual-hosts-site-urls.md) for more information.

![In the Site URL section, you can configure your Site's Friendly URL, as well as Virtual Hosts for your Public and Private Pages.](./site-settings-ui-reference/images/05.png)

#### Documents and Media

Determine whether to enable Directory Indexing for a Site. When enabled, a User with view permission can browse the Site's document library files and folders.

#### Site Template

If you created your Site using a *Site Template*, it's displayed here, along with whether the template allows Users to modify pages that inherit it. See [Building Sites from Templates](../building-sites/building-sites-with-site-templates.md) for more information about Site templates and how to create your own.

![View your page's selected template.](./site-settings-ui-reference/images/07.png)

#### Open Graph

**Enable Open Graph**: Determine whether to embed [Open Graph](https://ogp.me/) `<meta>` tags in the `<head>` of your Site's pages. These tags define page metadata to create engaging representations of your Site's content when shared in applications that support the Open Graph protocol, such as Facebook, Slack, and Twitter.

**Image**: Use the Image field to define the following Open Graph `<meta>` properties for a page:

   ```html
      <meta property="og:image" content="http://example.com/ogp.jpg" />
      <meta property="og:image:secure_url" content="https://secure.example.com/ogp.jpg" />
      <meta property="og:image:type" content="image/jpeg" />
      <meta property="og:image:width" content="400" />
      <meta property="og:image:height" content="300" />
   ```

**Image Alt Description**: Use the Image Alt Description field to define the `og:image:alt` property for a page. You can also localize an image's alt description via the *Language Flag* button.

See [Configuring Open Graph](./configuring-open-graph.md) to learn more.

![You can enable or disable Open Graph, define image meta tags, and see an image preview.](./site-settings-ui-reference/images/08.png)

#### Sharing

Determine whether to enable document sharing between Site Users. When enabled, Users can share items with one another. See [Sharing Documents with Other Users](../../content-authoring-and-management/documents-and-media/publishing-and-sharing/managing-document-access/sharing-documents-with-other-users.md) for more information. 

#### Asset Auto Tagging

Determine whether to enable asset auto tagging for a Site. When enabled, assets are auto tagged by the providers configured at Instance scope. See [Configuring Asset Auto Tagging](../../content-authoring-and-management/tags-and-categories/auto-tagging/configuring-asset-auto-tagging.md) for more information. 

### Social

Here you can manage the social interactions between Users on a Site.

#### Ratings

Here you can select the type of ratings used for the following Site applications: Comments, Knowledge Base, Blogs, Wiki, Message Boards, Web Content, and Documents and Media. See [Configuring Content Ratings Types](./site-content-configurations/configuring-content-rating-type.md) for more information.

![You can select the type of ratings used for Site applications.](./site-settings-ui-reference/images/11.png)

#### Mentions

Determine whether to allow Users to mention other Users in Site applications. See [Mentioning Users](../../collaboration-and-social/notifications-and-requests/user-guide/configuring-mentions.md) for more information.

### Language

Here you can choose whether to use the installation's default language options, or define your own for a Site.

![You can update language options through the Languages tab of Site Settings.](./site-settings-ui-reference/images/13.png)

### Advanced

Here you can configure additional settings, including analytics services, content sharing, and more.

#### Default User Associations

Select the Roles and Teams that new Site members are assigned to by default. See [Configuring Role and Team Defaults for Site Members](./site-users/configuring-role-and-team-defaults-for-site-members.md) for more information.

![You can select the Roles and Teams that new Site members are assigned to by default.](./site-settings-ui-reference/images/14.png)

#### Analytics

Use the provided fields to set a Site's Google Analytics ID and configure additional Google Analytics options. You can also set the Piwik Analytics tracking code for a Site by entering the full script code, including script start and end tags.

If you require a different analytics service, you can add it. See [Adding a New Analytics Service](./adding-a-new-analytics-service.md) for more information and how to add additional fields for different analytics services.

![In the Analytics section, you can configure a Site's analytic services.](./site-settings-ui-reference/images/15.png)

#### Maps

Select which maps API provider to use when displaying geolocalized assets in your Site. Geolocalized assets can be displayed for documents, web content articles, DDL records, and more. See [Geolocating Assets](./site-content-configurations/configuring-geolocation-for-assets.md) for more information.

![You can select the maps API provider used for Site geolocation.](./site-settings-ui-reference/images/16.png)

#### Recycle Bin

Determine whether to enable the Recycle Bin for a Site. You can specify the number of minutes an asset remains in the Recycle Bin before being automatically deleted. By default, the max age for recycled items is 43200 minutes (i.e., 30 days). See [Configuring the Asset Recycle Bin for Sites](./site-content-configurations/configuring-the-asset-recycle-bin-for-sites.md) for more information.

![You can configure a Site's Recycle Bin.](./site-settings-ui-reference/images/17.png)

#### Content Sharing

Determine whether child Sites can display content from this Site, including structures, templates, categories, widget templates, and more. Disabling this option immediately revokes content sharing from all child Sites. See [Managing Content Sharing Across Sites](./site-content-configurations/managing-content-sharing-across-sites.md) for more information. 

![You can determine whether child Sites can display content from this Site.](./site-settings-ui-reference/images/18.png)

## Additional Information

- [Page Configuration UI Reference](../creating-pages/page-settings/configuring-individual-pages.md)
- [Configuring Page Sets](../creating-pages/page-settings/configuring-page-sets.md)
