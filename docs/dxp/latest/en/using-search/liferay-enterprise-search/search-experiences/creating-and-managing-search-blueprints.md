# Creating and Managing Search Blueprints

[Search Blueprints](./understanding-search-blueprints.md) are created, updated, and deleted using an administrative application in the Global Menu. Open the Global menu's Applications section and find the Search Experiences category.

To create Search Blueprints,

1. Open the Blueprints application by clicking _Blueprints_ from Global Menu &rarr; Applications (Search Experiences).

1. Add a Blueprint by clicking the Add (![Add](../../../images/icon-add.png)) button.

   ![Start creating a Blueprint from the Add Blueprint modal window.](./creating-and-managing-search-blueprints/images/02.png)

1. In the New Search Blueprint window, give the Blueprint a name (required) and a description (optional).

1. Decide whether to start by including all the clauses from Liferay's search framework, or to start with a minimal baseline of clauses to use as the foundation for a more fully customized search experience. Click _Create_.

   See [Configuring Asset Types and Query Clauses](#configuring-asset-types-and-query-clauses) to learn more about the impacts of excluding Liferay's Indexers or other certain Clause Contributors from the search query.

1. Depending on your [blueprint plan](./planning-a-blueprint.md), next you'll use these menus to continue building the [Blueprint](./understanding-search-blueprints.md#what-is-a-blueprint):

   - [Query Builder](#using-the-query-builder): add new query clauses to the search by choosing or composing Elements for the Blueprint.

        ```{note}
        Out of the box, elements are available to use in creating your Blueprints. However, if you've installed the Search Experiences application into an existing Liferay installation, you must re-index the `SXPElement` entity for the elements to appear in the Blueprints UI. Navigate to Control Panel &rarr; Search &rarr; Index Actions, then find the `com.liferay.search.experiences.model.SXPElement` entry and click _Execute_.

        ![Reindex the SXPElement model to see all the out of the box elements.](./creating-and-managing-search-blueprints/images/09.png)
        ```

   - [Clause Contributors](#configuring-default-clauses): Further refine which clauses are included from Liferay's backend contributors. 
   - [Settings](#adding-settings): Configure advanced settings (e.g., Sorts or Aggregations) in the search.

1. Test the Blueprint. Click _Preview_ and enter a search keyword.

   ![Preview a Blueprint before putting it in action.](./creating-and-managing-search-blueprints/images/01.png)

   See [Testing a Blueprint](#testing-a-blueprint) for more details.

1. Once you're finished with the initial Blueprint creation, Click _Save_.

The Blueprint creation process can be fast and simple, but sometimes involves more iterating and testing. Make sure you save the Blueprint often to ensure your work is preserved.

Edit or delete a Blueprint from its Actions menu (![Actions](../../../images/icon-actions.png)).

![Edit or delete a Blueprint from its Actions menu.](./creating-and-managing-search-blueprints/images/03.png)

<!-- SME Question: what about editing or deleting a blueprint while in use? do we have any safety mechanism for this? maybe a warning to the user? should we say something about it here? -->

In addition to the CRUD options, Search Blueprints can be [imported and exported](#importing-and-exporting-blueprints).

## Using the Query Builder

Many use cases for Blueprints will require using its Query Builder. Use the Query Builder to

1. [Add Elements to the Blueprint](#adding-elements-to-the-blueprint).
1. [Choose which Liferay assets to search](#choosing-which-liferay-assets-to-search).

### Adding Elements to the Blueprint

Add Elements to begin adding query clauses to the Blueprint:

1. In the Query Builder's sidebar, Add Query Elements, expand the Element category you'd like to explore.
1. Hover over the Element, then click the _Add_ button.
1. The Element is added to the Query Builder, ready to configure:
   ![This Element gives a boost of ten to content found on sites the searching user is a member of.](./creating-and-managing-search-blueprints/images/04.png)
1. Add as many Elements as needed to configure the search query as desired.

   See [Search Blueprints Elements Reference](./search-blueprints-elements-reference.md) (not yet written) for a description of each out of the box Element.

1. If any custom Elements are required, add the Custom JSON Element to the Query Builder and write the query clause you need.

   See [Creating Elements](./creating-elements.md) (not yet written) to learn about building Custom JSON Elements.

<!-- TODO: Remove not yet written statements when written -->

After adding Elements, make sure you save the Blueprint.

### Choosing which Liferay Assets to Search

<!-- this UX will change based on https://issues.liferay.com/browse/LPS-143640 -->
Decide which Liferay Asset Types to include in the Blueprint's query. Use the Query Settings &rarr; Searchable Types dropdown:

![Expand the Searchable Types dropdown to begin removing assets from the Search Blueprint.](./creating-and-managing-search-blueprints/images/05.png)

- All assets are selected by default.
- To remove specific assets, click each one's ![Times](./../../../images/icon-times.png).
- To manage the types in bulk (e.g., deselect all assets) open the Select Types modal by clicking the Select Types button.
    ![The Select Types modal is used for bulk management of the assets to be searched.](./creating-and-managing-search-blueprints/images/06.png)

```{note}
De-selecting all assets in the Searchble Types modal is identical to selecting all types: all the asset types will be searched. See [Configuring Query Clause Contributors](#configuring-query-clause-contributors) to learn about disabling most of Liferay's search clauses.
```

Always save the Blueprint after editing its Searchable Types configuration.

Disabling an asset type in the Searchable Types configuration means that the query clauses usually contributed by its indexing code are excluded. Therefore, the type will not be searchable and the end user will not see results of the excluded type when the Blueprint is applied to a search.

More information is included in the next section, as the Searchable Types configuration has important implications for the Clause Contributors configuration options.

## Configuring Query Clause Contributors
<!-- possibly a confusing heading, particularly as it follows ### Choosing which Liferay Assets to Search -->
<!-- Andre suggests it is not a good idea for most blueprints users to edit the clause contributors. we could move this content into a separate article and link to it from this article -->
<!-- this must be reworked pending the result of https://issues.liferay.com/browse/LPS-143640 -->

Query clauses are contributed to the ongoing search by Liferay's backend code (and potentially any custom applications deployed in your Liferay instance).

Search Blueprints provides configurability for these backend-contributed query clauses. However, most Users should never touch the settings in the Clause Contributors tab. The initial setting you chose when creating the Blueprint, choosing to start with All Clauses or with just the Baseline Clauses, is usually enough. Further tweaking is safely accomplished with the Searchable Types setting, where you can turn off individual indexers entirely, ensuring their default clauses aren't contributed to the search query. If you're sure you must tweak this behavior further, you must understand the way the backend contributors work:

1. Use **Searchable Types** (from the Query builder tab) to disable individual indexers from participating in the search. If you disable a type's indexer, no clauses for the type will be added to the search query, even if its Clause Contributors are selected. The search end user will not see results for these types.

1. Use **Liferay Indexer Clauses** (in the Clause Contributors tab) to disable all Liferay's indexers from contributing clauses to the search. The only reason to disable all indexers is to build a search query from scratch, disabling all Clause Contributors and Searchable Assets as well.

1. Use the **Clause Contributors** section to remove certain contributors from participating in the search. Disable certain clause contributors if you want to override them using your own Blueprints configuration, or all clauses to completely override Liferay's search behavior, disabling Liferay's Indexers and Searchable Types as well.

```{important}
* Even when you disable all Indexers and Clause Contributors, certain mandatory clauses are always added by Liferay's search framework. Therefore, you're never truly building a query from scratch with Blueprints.

* Liferay's Indexer framework was refactored in Liferay 7.2. Some of Lifery's core assets, like Web Content Articles and Folders, have not been updated to the new pattern. This has an impact on Search Blueprints because there are no Clause Contributors for these assets. Therefore, the standard clauses for the assets will always be added to the search query when Liferay Indexer Clauses is enabled. Therefore, a complete override of the Web Content Article's clauses is not possible. You can, hoswever, tweak the search behavior of these assets by layering more clauses on top (boosting certain clause matches, for example).
```

![Disable certain clause contributors or all indexers from contributing clauses to the search query.](./creating-and-managing-search-blueprints/images/07.png)

```{note}
Due to internal limitations, you must choose to enable or disable all of Liferay's `Indexer`s. The other clause contributors can be managed more flexibly: choose to include all, none, or any subset of contributors you wish.
```

When you edit the clause contributors or indexer behavior, make sure to save the Blueprint.

<!-- TODO: Read and incorporate Andre's GDoc content:  https://docs.google.com/document/d/1i3TI3F2ieswmyukKduDLPsYtaTUfbLAOBYQ-ru1yFDI/edit -->

## Adding Settings

In addition to micromanaging the search query, add Search Blueprint settings add JSON configurations for

- Aggregations
- Highlights
- Sorts
- Parameters
- Advanced Configurations

![Additional settings can be configured using JSON.](./creating-and-managing-search-blueprints/images/08.png)

To add these, click the Settings tab, then find the text entry box for the desired configuration. Enter your JSON, then save the Blueprint.

Here's an example Sort that sorts the search results by the `name` field, in descending (reverse alphabetical--Z-A) order:

```json
{
	"sorts": [
		{
			"name": "desc"
		}
	]
}
```

For more details see [Using Blueprint Settings](./using-blueprint-settings.md)

## Importing and Exporting Blueprints

<!-- As of 01/07, the option to export is there, but clicking Export results in a java.lang.SecurityException --> 
A Blueprint is a JSON object. Export the JSON of a Blueprint from one environment and import it into the other. This can be useful when bringing the Blueprint from a staging and testing environment to production.

To export the Blueprint JSON, 

1. Open the Blueprints application from the Global Menu &rarr; Applications &rarr; Blueprints (in the Search Experiences section).

1. From the list of Blueprints, open a Blueprint's Actions (![Actions](../../../images/icon-actions.png)) menu and click _Export_.

To import a Blueprint's JSON definition,

1. Open the Blueprints application from the Global Menu &rarr; Applications &rarr; Blueprints (in the Search Experiences section).

1. Open the main Blueprints Actions (![Actions](../../../images/icon-actions.png)) menu and click _Import_.

1. Use the Import modal to choose a valid Blueprint JSON file. Valid Element JSON files can also be imported from this screen.

   ![Import Blueprints and Elements.](./creating-and-managing-search-blueprints/images/10.png)

1. Click _Import_.

## Testing a Blueprint

There's a preview sidebar that's handy for examining the results of a search backed by the Blueprint in progress. Access the preview by clicking the _Preview_ button from the Edit Blueprint screen.

![Preview a Blueprint before putting it in action.](./creating-and-managing-search-blueprints/images/01.png)

There's more functionality in this screen than first meets the eye:

- Click _View Raw Response_ to see the entire search response string. This is the same string you can see in the [Search Insights](../../search-pages-and-widgets/search-insights.md) widget on the Search page. The response is opened in a Raw Response modal and can be copied to the clipboard or downloaded as a JSON file.

   ![View the raw response string returned from Elasticsearch.](./creating-and-managing-search-blueprints/images/11.png)

- The score of each result is displayed to the left of the result title. Click the score to see the Score Explanation modal.

   ![View the score explanation for a result.](./creating-and-managing-search-blueprints/images/12.png)

- To expand a result and see all the fields of its returned document, click the right facing angle bracket to the right of the result title.

   ![Inspect the document's fields.](./creating-and-managing-search-blueprints/images/13.png)

<!-- TODO: cover the search context attributes accessible in the preview sidebar, gear icon -->
<!-- SME Question: how much should we say about the search context attributes accessible in the preview sidebar, gear icon. If possible, please provide a list of things we should talk about. -->
