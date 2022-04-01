---
description: 10 - Customize the Service Layer
title: Service Wrappers
order: 2
---

## Customize the Service Layer

All Liferay core services have been designed and generated with the Liferay *Service Builder* pattern, which automatically generates service wrapper APIs for both remote and local service variants. 

The wrapper API is a service façade that provides a transactionally safe way to customize the underlying service. In a Service Builder project, the wrapper API will be created in the API module:

<img src="../images/service-wrappers-service-builder.png" style="max-height:100%;" />

If you take a look at any platform core application service, like the Blogs service, you'll see the same design:

<img src="../images/blogs-service-wrappers.png" style="max-height:100%;" />

These generated service wrapper stub classes contain façade methods for all the methods of the underlying service class. Below is an excerpt of the [BlogsEntryLocalServiceWrapper](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/blogs/blogs-api/src/main/java/com/liferay/blogs/service/BlogsEntryServiceWrapper.java). In `addBlogsEntry()`, we see that this wrapper is just calling the underlying service:

```java
@Override
public com.liferay.blogs.model.BlogsEntry addEntry(
		String title, String subtitle, String description, String content,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, boolean allowPingbacks,
		boolean allowTrackbacks, String[] trackbacks,
		String coverImageCaption,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
			coverImageImageSelector,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
			smallImageImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
	throws com.liferay.portal.kernel.exception.PortalException {

	return _blogsEntryService.addEntry(
		title, subtitle, description, content, displayDateMonth,
		displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
		allowPingbacks, allowTrackbacks, trackbacks, coverImageCaption,
		coverImageImageSelector, smallImageImageSelector, serviceContext);
}
```

The important thing to notice here is that when you do a service call, like in this case with `BlogsEntryLocalService.addBlogsEntry()`, it's actually the service wrapper façade method that gets first executed. By default, the call is just redirected to the underlying service.

By creating your custom Service Wrapper, you can override the default wrappers and, in doing so, override the default behavior.
