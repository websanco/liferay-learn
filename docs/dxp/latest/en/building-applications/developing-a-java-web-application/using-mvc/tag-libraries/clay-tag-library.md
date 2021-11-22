# Clay Tag Library

```{toctree}
:maxdepth: 3

clay-tag-library/clay-alerts.md
clay-tag-library/clay-badges.md
clay-tag-library/clay-buttons.md
clay-tag-library/clay-cards.md
clay-tag-library/clay-dropdown-and-action-menus.md
clay-tag-library/clay-form-elements.md
clay-tag-library/clay-icons.md
clay-tag-library/clay-links-and-labels.md
clay-tag-library/clay-management-toolbar.md
clay-tag-library/clay-navigation-bars.md
clay-tag-library/clay-progress-bars.md
clay-tag-library/clay-stickers.md
```

The Liferay Clay tag library provides a set of tags for creating [Clay](https://clayui.com/) UI components in your app.

```{note}
   AUI taglibs are deprecated as of @product-ver@. We recommend that you use Clay taglibs to avoid future compatibility issues.
```

To use the Clay taglib in your apps, add the following declaration to your JSP:

```markup
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>
```
The Liferay Clay taglib is also available via a macro for your FreeMarker theme templates and web content templates. Follow this syntax:

```markup
<@clay["tag-name"] attribute="string value" attribute=10 />
```

Clay taglibs provide the following UI components for your apps:

- [Alerts](clay-tag-library/clay-alerts.md)
- [Badges](clay-tag-library/clay-badges.md)
- [Buttons](clay-tag-library/clay-buttons.md)
- [Cards](clay-tag-library/clay-cards.md)
- [Dropdown Menus and Action Menus](clay-tag-library/clay-dropdown-and-action-menus.md)
- [Form Elements](clay-tag-library/clay-form-elements.md)
- [Icons](clay-tag-library/clay-icons.md)
- [Labels and links](clay-tag-library/clay-links-and-labels.md)
- [Management Toolbar](clay-tag-library/clay-management-toolbar.md)
- [Navigation Bars](clay-tag-library/clay-navigation-bars.md)
- [Progress Bars](clay-tag-library/clay-progress-bars.md)
- [Stickers](clay-tag-library/clay-stickers.md  )

This section covers how to create these components with the Clay taglibs. Each article contains a set of clay component examples along with a screenshot of the resulting UI.