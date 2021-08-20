# Selecting Assets in the Asset Publisher Widget

You can configure the Asset Publisher Widget to select Assets dynamically based on specific criteria, or manually, specifying exactly what assets to display. You can also display Assets from [Collections](../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md).

```note::
   On Liferay 7.2 and below, Collections are referred to as `Content Sets <../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md#liferay-dxp-7-2>`_.
```

To select Assets to publish in the *Asset Publisher* widget,

1. Click the *Actions* menu (![Click on the Options button at the top-right corner of the widget for configuration.](../../../images/icon-options.png)) at the top-right corner of the Asset Publisher Widget.

    ![Click the Actions menu to access the Asset Publisher Configuration options.](./selecting-assets-in-the-asset-publisher-widget/images/01.png)

1. Select *Configuration* to display the Configuration dialog.

    Under Asset Selection, select how you want to filter your Assets. For a description of these options, see [Asset Selection Options](#asset-selection-options).

    ![The configuration window has different Asset Selection options under the Setup tab.](selecting-assets-in-the-asset-publisher-widget/images/02.png)

1. Optionally, click *Create a collection from this configuration* to save the configuration as a new [Collection](../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md). This option is available for the [Dynamic](#dynamic) and [Manual](#manual) Asset selection options.

1. Once you've selected your Assets and configured the widget, click *Save*.

## Asset Selection Options

You can use different options to select the Assets in the Asset Publisher dialog's Asset Selection section:

- [Dynamic](#dynamic)
- [Manual](#manual)
- [Collection](#collection)
- [Collection Provider](#collection-provider)

### Dynamic

Using the Dynamic asset selection, you can include Assets in the Asset Publisher Widget automatically, based on different criteria.

  1. In the Source section, use the *Asset Type* drop-down menu to select your Assets. Select a specific Asset Type or *Select More Than One* to choose from different types.
  1. In the Scope section, choose to display assets from an individual Site or other Sites.
  1. In the Filter section, define the *Rules* for the Assets in your Asset Publisher Widget. You can define rules based on [Categories](../../../content-authoring-and-management/tags-and-categories/defining-categories-and-vocabularies-for-content.md), [Tags](../../../content-authoring-and-management/tags-and-categories/tagging-content-and-managing-tags), or Keywords.
  1. Include a *Custom User Attribute* if you have defined one.
  1. In the Ordering section, choose the presentation order of Assets in the Asset Publisher Widget.

 ![Define the options for the Dynamic Asset collection, including Source, Scope, Filter, Custom User Attribute, and Ordering.](selecting-assets-in-the-asset-publisher-widget/images/05.png)

### Manual

Here you can set parameters and manually filter specific Assets:

 1. In the Scope section, choose to display assets from an individual Site or other Sites.
 1. In the Asset Entries section, use the *Select* button to filter for specific types of assets.
 1. For the type of asset you select, check the assets you want to include.
 1. Click *Add*.

 ![Here is an example of filtering for Basic Web Content and manually selecting items](selecting-assets-in-the-asset-publisher-widget/images/03.png)

 ```note::
    A new Collection is created based on your selection.
 ```

### Collection

Select this option to choose from your previously saved Collections. To learn more about Collections, read [About Collections and Collection Pages](../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md).

 1. Under the Select Collection section, click *Select*.
 1. Choose one of your exiting Collections.
 1. Click *Save*.

 ![Click Select and choose from your saved Collections.](selecting-assets-in-the-asset-publisher-widget/images/04.png)

### Collection Provider

This is a new feature in Liferay DXP 7.3 that allows developers to create specific collections with more advanced criteria. To learn more, read the information about [Creating an Information List Provider](https://help.liferay.com/hc/en-us/articles/360029067271-Creating-an-Information-List-Provider) in the [Info Framework](https://help.liferay.com/hc/en-us/articles/360029067251-Introduction-to-The-Info-Framework) developer documentation.

## Related Information

- [Displaying Assets Using the Asset Publisher Widget](./displaying-assets-using-the-asset-publisher-widget.md)
- [Configuring the Asset Publisher Display Settings](./configuring-asset-publisher-display-settings.md)
- [About Collections and Collection Pages](../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md)
