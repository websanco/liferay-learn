# Understanding Blueprints

Search Blueprints is part of Liferay's Search Experiences search configuration framework. Use Blueprints to tailor the search page experience to your users' needs, without deploying any custom code. With Blueprints you can

- Create context-aware search pages. For example, boost Web Content articles by proximity to the search user.
- Exclude fields from being searched. For example, you can choose not to search the `userName` field.
- Boost assets with a certain category for a specific period of time. For example, create a seasonal campaign to promote Liferay Commerce products by category.
- Most use cases for the [Custom Filter widget](../../search-pages-and-widgets/search-results/custom-filter-examples.md) can be satisfied with Blueprints. For example, boost certain fields that match the searched keyword.

The configurability of the default Liferay search experience has evolved over time. In the latest Liferay DXP release, out of the box you can tune the search results (Synonym Sets and Result Rankings), add Custom Facets, and use Custom Filters to perform complex search customizations. These configurations have limits, though, and many search-heavy sites will require customizations of the search infrastructure's backend code. This requires deploying Java-based modules to Liferay's OSGi runtime.

Instead of building on this feature set, Blueprints takes a different approach, for the user who needs near-complete control over the search experience without deploying custom code. Blueprints offers a UI-based configuration of the search experience that can satisfy almost every use case (if not in the earliest iteration of the feature, in subsequent versions where the feature set is even more robust).

## What is a Blueprint?

Simply put, Blueprints are comprised of Elements, with some additional configuration sprinkled on top.

**Blueprints**: A blueprint is a design plan. It's needed when you're building something big. With LES Blueprints you're building out a search experience, which is hugely important for your site's visitors, who are hugely important for your site's success. With Search Blueprints you can tweak Liferay's search page behavior, or completely overhaul it.

**Elements**: Elements are individual JSON fragments that combine to build an entire Blueprint. Each Element usually defines a concrete search behavior that gets combined with other Elements, additively comprising the Blueprint. System elements are provided out of the box with LES Blueprints, and an Element editor lets you create your own, or duplicate the system-defined elements to use as starting points for your own needs.

To create a Blueprint, start with the Elements. Create new Elements, leverage existing Elements, or write plain Elasticsearch queries that are translated to Liferay Elements on the fly.

For more details see [Creating and Managing Blueprints](./creating-and-managing-blueprints.md).

## What can I do in the Blueprints UI?

To produce a search solution using Blueprints, you'll need to create and compose Elements into a Blueprint and apply it to the page. The features available for doing so include:

| Feature | Available in Blueprints? | 
|---------------------|----------|
| Use the Query Builder to build Blueprints visually | &#10004; |
| Compose Blueprints with JSON in the Liferay UI | &#10004; |
| Leverage out-of-the-box Elements to simplify Blueprint creation | &#10004; |
| Create custom Elements from Elasticsearch queries | &#10004; |
| Create custom JSON Elements for my Blueprints | &#10004; |
| Create advanced Blueprints settings by editing the JSON directly | &#10004; |
| Choose which Liferay Entities are searchable when the Blueprint is applied | &#10004; |
| Choose whether to include clauses from Liferay's Indexers and Keyword Query Contributors | &#10004; |
| Using JSON, configure advanced settings: Aggregations, Facets, Sorts, Highlights, etc. | &#10004; |
| Apply Blueprints to the search page | &#10004; |
Yes: &#10004;
No: &#10008;

For more details see [Creating and Managing Blueprints](./creating-and-managing-blueprints.md).
