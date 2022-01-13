# Using a Search Blueprint on a Search Page

By default, a [Search Page](../../search-pages-and-widgets/working-with-search-pages.md) does not have a [Search Blueprint](./understanding-search-blueprints.md) associated with it. To make the Search Page react to your Search Blueprint,

1. [Create a Blueprint](./creating-and-managing-search-blueprints.md).
1. Create or navigate to the [Search Page](../../search-pages-and-widgets/working-with-search-pages.md).
1. Add the [Low Level Search Options](../../search-pages-and-widgets/search-results/understanding-low-level-search-options.md) widget to the page.
1. Click the widget's Options icon (![Options](../../../images/icon-app-options.png)) and click *Configuration*.
1. In the Attributes section, add
   - Key: `search.experiences.blueprint.id`
   - Value: `[the Blueprint ID]`

   You can find the Blueprint ID from the Blueprints application (Global Menu &rarr; Applications &rarr; Blueprints (Search Experiences).

   ![The Blueprint ID is listed in the Blueprints application.](./using-a-search-blueprint-on-a-search-page/images/01.png)
1. Save the configuration.

Now the Blueprint's functionality is applied to the page.

## Applying Additional Search Context Attributes

Blueprints are applied to the page using search context attributes, as described above. However, some Elements require parameters that aren't present in the search request by default, and these must be applied as search context attributes on the search page. If your Blueprint uses a parameter that's not present by default, add it. This example helps demonstrate:

1. [Create a Blueprint](./creating-and-managing-search-blueprints.md) with the following Element:
   - Element Name: 
