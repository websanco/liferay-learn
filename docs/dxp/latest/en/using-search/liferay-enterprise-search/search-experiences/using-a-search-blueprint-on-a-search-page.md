# Using a Search Blueprint on a Search Page

<!--
If this section is all that's needed in this article (steps for setting the blueprint ID in the LLSO widget), we could maybe add them to Creating and Managing Blueprints, and extract one of the other long sections into its own article. 
If we need the other sections  (Applying Search Context Attributes) they'll need fleshing out, but I need to know what we expect these use cases to look like.
If there's additional information we'd like to include here, let me know. -->

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

<!-- SME Question: A search Page can only use one Blueprint at a time, correct? -->

## Applying Additional Search Context Attributes

Blueprints are applied to the page using search context attributes, as described above. However, some Elements require parameters that aren't present in the search request by default; these must be applied as search context attributes on the search page or programmatically. See [the Elements reference for more information](./search-blueprints-elements-reference.md). Follow this example to set search context attributes into a Blueprint-driven search page:

1. [Create a Blueprint](./creating-and-managing-search-blueprints.md) with the following Element:
   - Element Name: 

## Applying Search Context Attributes Programmatically

<!-- Should we say something about this? -->
