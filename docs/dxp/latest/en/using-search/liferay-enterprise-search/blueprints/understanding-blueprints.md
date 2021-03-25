# Understanding Blueprints

Liferay Enterprise Search Blueprints is a search experience configuration framework. Blueprints lets you provide a search page experience that is tailored to your site needs. Context-aware, configure the Liferay search experience 

In the first iteration of Blueprints, it's important to define the things that can be done, and the things that you might expect from a feature like this, but that can't currently be done:

| Feature | Available in Blueprints? | 
|---------------------|----------|
| Feature X           | &#10004; |
| Feature Y           | &#10008; |


The configurability of the default Liferay search experience has evolved over time. As of the last Liferay DXP release, out of the box you can tune the search results (Synonym Sets and Result Rankings), add Custom Facets, and use Custom Filters to perform several complex search customizations. These configurations have limits, though, and most search-heavy sites will require customizations of the search backend by deploying Java-based modules to Liferay's OSGi runtime.

Instead of building on this feature set, Blueprints takes a different approach, for the user who needs near-complete control over the search experience, offering a UI-based configuration of the search experience that can satisfy almost every use case (if not in the earliest iteration of the feature, in subsequent versions where the feature set is even more robust).

Blueprints: A blueprint is a design plan. It's needed when you're building something big. With LES Blueprints you're building out a search experience, which is hugely important for your site's visitors, who are hugely important for your site's success. With Search Blueprints you can define XYZ.

Elements: Elements are individual JSON fragments that combine to build an entire Blueprint. Each Element usually defines a concrete search behavior that gets combined with other Elements, additively comprising the Blueprint. System elements are provided out of the box with LES Blueprints, and an Element editor lets you create your own, or duplicate the system-defined elements to use as starting points for your own needs.
