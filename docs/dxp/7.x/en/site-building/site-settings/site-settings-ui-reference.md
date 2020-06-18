# Site Settings UI Reference

Liferay DXP is built with flexibility in mind, and beyond just creating content and pages, Liferay DXP has a wealth of configuration options and tools available to help you create the Site that meets your needs and the needs of your users. The Site Settings UI is organized into four tabs:

* [General](#general)
* [Social](#social)
* [Languages](#language)
* [Advanced](#advanced)

To view Site Settings, open the Product Menu and go to *Configuration* &rarr; *Settings*. Each tab is described below in the order in which it appears in the UI.

## General

General settings range from core configuration, like your Site's Membership Type, to finer details like Documents and Media indexing options. 

### Details

**Name:** The Site's name. You can translate the name into multiple languages. See [Modifying Localizable Site Fields](./modifying-localizable-site-fields.md) for more information.

**Description:** Describes the Site's intended function. The description can also be translated into other languages. See [Modifying Localizable Site Fields](./modifying-localizable-site-fields.md) for more information.

**Active:** Whether a Site is active or inactive. Inactive Sites are inaccessible but can be activated later if needed.

**Membership Type:** Specifies how restrictive a Site is. See [Changing Site Membership Types](./user-settings/changing-site-membership-types.md) for more information.

**Allow Manual Membership Management:** Whether Users can be added and removed manually from the Site. By default, manual Site membership management is enabled. This lets Users join the Site or request membership to the Site if it's restricted using the My Sites app. If your Site's membership is handled automatically by a membership policy, you can disable this setting. See [Managing Membership Policies for Sites](./user-settings/managing-membership-policies-for-sites.md) for more information.

```note::
  Site memberships can be handled automatically by a membership policy. The membership policy can check various pieces of information from each User, such as their first names, last names, birthdays, job titles, Organizations, and User Groups. Using this information, the Site membership policy can automatically assign members to the Site. If your Site implements a membership policy, you can disable the *Allow Manual Membership Management* option; when the option is disabled, the *Members* section of Site Administration (Site Memberships and Site Teams) is hidden, even from Administrators.
```

**Parent Site:** lets you select a parent Site for the Site that's being created. Organizing Sites hierarchically like this provides many benefits. See [Site Hierarchies](../building-sites/site-hierarchies.md) for more information.

**Limit membership to members of the parent site**: This option only appears for Child Sites. If enabled, Users can only be members of the Site if they're members of the parent Site.

### Pages

Under Pages you can view your Site's Public or Private Pages, if any exist. If they don't exist, a *Site Templates* selector appears for creating pages with a Site Template.

![You can select a Site Template for your Public and Private Pages if they're not already configured.](./site-settings-ui-reference/images/01.png)

### Categorization

*Categorization* helps Administrators organize the Site and makes it easier for Users to find your Site and its content through search and navigation. For more information on using tags and categories, see [Organizing Content with Tags and Categories](TODO).

### Site URL

**Friendly URL:** Specifies your Site's URL paths (i.e. `/my-site`). See [Configuring Your Site's Friendly URL](./configuring-your-site-s-friendly-url.md) for more information.

**Public/Private Pages:** Specifies the virtual host to map to the Public and Private Pages of the Site. See [Configuring Virtual Hosts Site URLs](./configuring-virtual-hosts-site-urls.md) for more information.

![You can configure virtual hosts for your Sites.](./site-settings-ui-reference/images/02.png)

### Documents and Media

**Enable Directory Indexing:** Whether Site Administrators can browse your Site's Documents and Media files and folders. For example, a Site Administrator of a Site called *My Site* can browse documents at `http://localhost:8080/documents/my-site` if this option is enabled.

### Site Template

If you created your Site using a Site Template, this section appears and displays information about the link between the Site Template and the Site. Specifically, you can see which Site Template was used and whether or not it allows modifications to the pages inherited from it by Site Administrators. To learn more about Site Templates and how to create your own, see [Building Sites from Templates](../building-sites/building-sites-with-site-templates.md).

### Open Graph

```note::
  Available in Liferay DXP 7.3+
```

The Open Graph section provides configuration settings for the [Open Graph protocol](https://ogp.me/) for your Site.

**Enable Open Graph:** Whether to embed Open Graph meta tags on every page to share content on applications supporting Open Graph, such as Facebook, Twitter, Slack, etc.

**Open Graph Image:** The default image to use for Open Graph tags. You can override the default image set here by setting a different image for the individual page's configuration.

### Asset Auto Tagging

**Enable Auto Tagging of Assets on This Site:** Whether to use Asset Auto Tagging rules on your Site. See [Asset Auto Tagging](TODO) for more information.

### Sharing

**Enabled:** Whether to enable person to person document sharing for a Site. The default value is Yes. This can be configured at the instance level (instance settings) and globally (System Settings)

### Custom Fields

*Custom Fields* only appears if you've created them in Control Panel &rarr; *Configuration* &rarr; *Custom Fields*. For more information on Custom Fields, see [Custom Fields](TODO).

## Advanced

Advanced Settings relate to security (like User Roles) or require external configuration (like creating a Google Analytics account) to use.

### Default User Associations

**Site Roles:** Specifies the Site Roles that newly assigned Site members have by default

**Teams:** Specifies the Teams to assign newly assigned Site members to by default.

 See [Configuring Role and Team Defaults for Site Members](./user-settings/configuring-role-and-team-defaults-for-site-members.md) for more information.

### Analytics

**Google Analytics ID:** Specifies the Google Analytics ID

**Google Analytics Custom Configuration:** Sets the custom options for Google Analytics

**Piwik:** Specifies the tracking code for Piwik Analytics

![To set up Google Analytics: sign up, receive an ID, and then enter it into the Google Analytics ID field.](./site-settings-ui-reference/images/03.png)

If you require a different analytics service, you can add it. See [Adding a New Analytics Service](./configuring-analytics-for-sites/adding-a-new-analytics-service.md) for more information.

### Maps

The *Maps* option configures the maps API provider used by your Liferay Portal instance when displaying geolocalized assets. Geolocalized assets can be displayed for documents, web content articles, DDL records, etc. See [Geolocating Assets](TODO) for more information.

### Recycle Bin

The *Recycle Bin* panel provides options for enabling/disabling the Recycle Bin and specifying how long to retain assets in the Recycle Bin before deleting them. See [Configuring the Asset Recycle Bin for Sites](./content-settings/configuring-the-asset-recycle-bin-for-sites.md) for more information.

### Content Sharing

**Allow subsites to display content from this site:** Specifies whether sub-sites can display content from this Site. Administrators of this Site's sub-Sites can use all structures, templates, categories, widget templates, and more from this parent Site. Even if you initially allowed content sharing between the parent Site and its sub-sites, you can disable this option and immediately revoke content sharing from all sub-Sites.

You can also manage content sharing at the global level. See [Managing Content Sharing Globally](./content-settings/managing-content-sharing-globally.md) for more information.

## Social

The Social tab provides options for managing the social interactions on your Site.

### Ratings

The *Ratings* section lets you select the ratings types (Stacked Stars, Stars, Likes, and Thumbs) for applications. See [Configuring Content Ratings Types](./content-settings/configuring-content-ratings-types) for more information.

### Mentions

**Allow Users to Mention Other Users:** Specifies whether Users can mention (notify and/or draw attention to) friends and colleagues by entering the "@" character followed by their User names. See [Mentioning Users](./user-settings/configuring-user-mentions.md) for more information.

## Language

The *Languages* tab lets you configure language options for your Site. You can use the default language or define another supported language as the default for your Site. 

![You can update language options through the Languages tab of Site Settings.](./site-settings-ui-reference/images/04.png)