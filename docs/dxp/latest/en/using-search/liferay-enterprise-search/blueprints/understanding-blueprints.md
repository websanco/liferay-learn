# Understanding Blueprints

Blueprints is a search experience configuration framework. Use Blueprints to tailor the search page experience to your users' needs, without deploying any custom code. If you need context-aware search pages, and other feature (fill this in), use Blueprints.

Blueprints can already do a lot, and more development is planned. It's important to define the things that currently can be done, and the things that you might expect from a feature like this, but that currently can't be done:

| Feature | Available in Blueprints? | 
|---------------------|----------|
| Compose Blueprints with JSON Elements in the Liferay UI | &#10004; |
| Leverage out-of-the-box Elements for common use cases | &#10004; |
| Create custom Elements for my specific use case | &#10004; |
| Create advanced Blueprints settings by editing the JSON directlyC | &#10004; |
<!--to add and understand the scope of blueprints v1, survey 
https://issues.liferay.com/secure/StructureBoard.jspa?s=1621# -->
Yes: &#10004;
No: &#10008;

The configurability of the default Liferay search experience has evolved over time. As of the last Liferay DXP release, out of the box you can tune the search results (Synonym Sets and Result Rankings), add Custom Facets, and use Custom Filters to perform several complex search customizations. These configurations have limits, though, and many search-heavy sites will require customizations of the search infrastructure's backend code. This requires deploying Java-based modules to Liferay's OSGi runtime.

Instead of building on this feature set, Blueprints takes a different approach, for the user who needs near-complete control over the search experience without deploying custom code. Blueprints offers a UI-based configuration of the search experience that can satisfy almost every use case (if not in the earliest iteration of the feature, in subsequent versions where the feature set is even more robust).

## What are the Essential Components of the Search Experience?

Blueprints: A blueprint is a design plan. It's needed when you're building something big. With LES Blueprints you're building out a search experience, which is hugely important for your site's visitors, who are hugely important for your site's success. With Search Blueprints you can define XYZ.

Elements: Elements are individual JSON fragments that combine to build an entire Blueprint. Each Element usually defines a concrete search behavior that gets combined with other Elements, additively comprising the Blueprint. System elements are provided out of the box with LES Blueprints, and an Element editor lets you create your own, or duplicate the system-defined elements to use as starting points for your own needs.
