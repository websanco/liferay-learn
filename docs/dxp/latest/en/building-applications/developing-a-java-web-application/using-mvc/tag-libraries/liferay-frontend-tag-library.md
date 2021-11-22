# Liferay Frontend Tag Library

```{toctree}
:maxdepth: 3

liferay-frontend-tag-library/liferay-frontend-add-menu.md
liferay-frontend-tag-library/liferay-frontend-cards.md
liferay-frontend-tag-library/liferay-frontend-info-bar.md
liferay-frontend-tag-library/liferay-frontend-management-bar.md
liferay-frontend-tag-library/including-actions-in-the-management-bar.md
liferay-frontend-tag-library/disabling-the-management-bar.md
```

The Liferay Front-end tag library provides a set of tags for creating common front-end UI components in your app. 

To use the Front-end taglib in you apps, add the following declaration to your JSP:

```markup
<%@ taglib prefix="liferay-frontend" uri="http://liferay.com/tld/frontend" %>
```

The Liferay Front-end taglib is also available via a macro for your FreeMarker theme templates and web content templates. Follow this syntax:

```markup
<@liferay_frontend["tag-name"] attribute="string value" attribute=10 />
```

The following Front-end UI components are covered in this section:

- [Add Menu](./liferay-front-end-add-menu)
- [Cards](./liferay-front-end-cards)
- [Info Bar](./liferay-front-end-info-bar)
- [Management Bar](./liferay-front-end-management-bar)

Each article contains a set of examples along with a screenshot of the resulting UI. 