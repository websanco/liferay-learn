# Selecting Assets for the Asset Publisher

You can configure the Asset Publisher to select assets dynamically based on specific criteria, or you can select assets manually, specifying exactly what assets to display. You can also display assets from [Content Sets](TODO).

## Selecting Assets Dynamically

By default, the Asset Publisher selects assets dynamically, according to a set of customizable rules. These rules can be combined to compliment each other to create a nice, refined query for your content. Assets are filtered by permissions automatically, no matter how complicated your asset selection rules are. To enable dynamic asset selection, follow these steps:

1. Hover over the Asset Publisher and click the Options icon (![Options](../../../images/icon-app-options.png)) in the widget's menu and select *Configuration*.

    ![Hover over the Asset Publisher widget to access the Configuration Menu.](./selecting-assets-for-the-asset-publisher/images/01.png)

1. In the Asset Publisher configuration, click the *Dynamic* radio button beneath *Asset Selection* if it's not already selected.
1. Expand the *Source* panel and select the type of Assets to display. By default, all (any) assets are displayed.
1. Select a *Scope*. A list of configured scopes appears under the Scope heading. You can configure multiple scopes, including the global scope, from which to select assets. Remove a scope with the *X* button at the right. Add a scope with the *Select* button.

    ![You can add scopes to expand the list of available assets to display.](./selecting-assets-for-the-asset-publisher/images/02.png)

1. Optionally specify any filters for tags, categories, or keywords that you need under the *Filter* heading.

    ![You can filter by tags and categories, and you can set up as many filter rules as you need.](./selecting-assets-for-the-asset-publisher/images/03.png)

1. Optionally expand the *Custom User Attributes* heading and specify categories for the assets to match. These categories must be from the global context. See [Defining Categories for Content](TODO) for more information.
1. Expand the *Ordering* panel and specify how to order and group the assets. See [below](#configuring-asset-display-ordering) for more information.
1. Click *Save* to apply the dynamic configuration.

```note::
  These actions are applied immediately to the Asset Publisher and don't require saving:

  * Changing the value of the *Asset Selection* option
  * Changing the value of the *Scope* option
  * Selecting, adding, sorting or deleting asset entries (only when selecting assets manually)
```

## Selecting Assets Manually

To enable manual asset selection, follow these steps:

1. Hover over the widget and click the *Options* icon (![Options](../../../images/icon-app-options.png)) in the widget's menu and select *Configuration*.
1. In the Asset Publisher configuration, select *Manual* from the select box beneath *Asset Selection*.
1. Select a *Scope*. A list of configured scopes appears under the Scope heading. You can configure multiple scopes, including the global scope, from which to select assets. Remove a scope with the *X* button at the right. Add a scope with the *Select* button.

    ![You can add scopes to expand the list of available assets to display.](./selecting-assets-for-the-asset-publisher/images/02.png)

1. Open the *Asset Entries* panel and click the *Select* button for each scope to select the assets to display for it. A list of the selected assets appears in the Asset Entries section. See [Configuring Display Settings](./03-configuring-display-settings.md) for more information.
1. click *Save* to apply the manual configuration.

## Selecting a Content Set

Since Liferay DXP 7.2, you can select Content Sets: predefined lists of content to display in the Asset Publisher. To use a Content Set, follow these steps:

1. Hover over the widget and click the *Options* icon (![Options](../../../images/icon-app-options.png)) in the widget's menu and select *Configuration*.
1. In the Asset Publisher configuration, select *Content Set* or *Content Set Provider* beneath *Asset Selection*.
1. Expand the *Select Content Set* panel that appears and choose the Content Set that you want to use.

![Select a Content Set beneath the Select Content Set heading to display assets from it.](./selecting-assets-for-the-asset-publisher/images/04.png)

For more information on using Content Sets, see [Creating Content Sets](TODO).

## Configuring Asset Display Ordering

Ordering controls how assets are arranged in the Asset Publisher. For example, you can display a series of "How To" articles in descending order based on whether the article has a certain tag. The second ordering is applied to any assets for which the first ordering isn't sufficient. For example, if you order assets by title and there are multiple assets with the same title, the second ordering takes effect. You can display the assets in ascending or descending order by these attributes:

* Title
* Create Date
* Modified Date
* Publish Date
* Expiration Date
* Priority

Ordering rules are one way to control how your content appears. You can combine ordering with [grouping](./configuring-display-settings.md#grouping) to organize your assets further. See [Asset Publisher Display Settings](./configuring-display-settings.md) for other display options.