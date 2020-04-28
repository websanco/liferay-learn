# Site Hierarchies

Sites can be organized hierarchically, just like Organizations, with child Sites. Organizing Sites hierarchically lets you share content between them easily. The difference between Sites and Organizations is that Sites organize pages, content, application data, and Users (via site memberships), whereas Organizations only group Users. Site hierarchy is discussed in more detail below.

* [Content Sharing between Sites](#content-sharing-between-sites)
* [Site Hierarchy Roles and Permissions](#site-hierarchy-roles-and-permissions)
* [Site Hierarchy Applications](#site-hierarchy-applications)
* [The Sites Directory Application](#the-sites-directory-application)
* [The Site Map Application](#the-site-map-application)

## Content Sharing Between Sites

Content sharing is available for Sites within the same hierarchy. For instance, if a parent Site has a document type called *Devcon Presentation* and all its child Sites should have a copy, the parent Site's administrator can enable content sharing to share the document type automatically with its child Sites. Also, content sharing privileges can be set to let every Site Administrator share content across Sites they manage. You can share these content types across Sites:

* Web Content Structures
* Web Content Templates
* Document Types
* Vocabularies and Categories
* Widget Templates
* Data Definitions (Dynamic Data Lists)

See the [Sites Admin Portlet](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html#Sites%20Admin%20Portlet) section of Liferay's `portal.properties` file for a list of relevant configurable properties. For example, the `Sites.content.sharing.with.children.enabled` property can disable/enable content sharing between Sites and child Sites, while letting Site Administrators configure it per Site.

## Site Hierarchy Roles and Permissions

These rules apply to Site hierarchy:

* Each child site in the hierarchy has its own Administrator
* The Site Administrator Role permissions don't flow down to child Sites in the hierarchy
* If a Site Administrator creates a child Site, he or she has the same permissions in that child Site. This is because creating a Site makes you the owner of that Site (not inheritance).
* A Site Administrator has no default Role in any child Sites created by other Site Administrators.

```note::
  If you want a User to have administrative access to all Sites in a Site/child Site hierarchy, you must create a Role based on the Site Administrator Role that has the permission *Manage Subsites*.
```

## Navigating Site Hierarchy with the Sites Directory Application

The Sites Directory application is a configurable app that shows a hierarchy of Sites and child Sites. It enables Users to navigate to any of the displayed Sites. To use this app to display Site hierarchies, follow these steps:

1. Add the Sites Directory application to a page.
1. Open its *Configuration* window.
1. Select *List Hierarchy* under Display Style.
