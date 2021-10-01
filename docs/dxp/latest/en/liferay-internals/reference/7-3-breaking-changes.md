# 7.3 Breaking Changes

This document presents a chronological list of changes that break existing functionality, APIs, or contracts with third party Liferay developers or users. We try our best to minimize these disruptions, but sometimes they are unavoidable.

Here are some of the types of changes documented in this file:

* Functionality that is removed or replaced
* API incompatibilities: Changes to public Java or JavaScript APIs
* Changes to context variables available to templates
* Changes in CSS classes available to Liferay themes and portlets
* Configuration changes: Changes in configuration files, like `portal.properties`, `system.properties`, etc.
* Execution requirements: Java version, Java EE Version, browser versions, etc.
* Deprecations or end of support: For example, warning that a certain feature or API will be dropped in an upcoming version.

## Breaking Changes List

### Liferay FontAwesome Is No Longer Included by Default
- **Date:** 2019-Aug-21
- **JIRA Ticket:** [LPS-100021](https://issues.liferay.com/browse/LPS-100021)

#### What changed?

Liferay FontAwesome, which included icon fonts for Font Awesome, Glyphicon, and custom Liferay icons, is no longer included by default.

#### Who is affected?

This affects pages or sites that have a Theme applied that does not include these icon fonts itself. Any content or code on such pages or sites that uses these icon fonts will no longer work.

#### How should I update my code?

Depending on how you're using icon fonts, there's a few approaches you can take.

##### For liferay-ui:icon usage

Replace `<liferay-ui:icon iconCssClass="icon-user">` with `<liferay-ui:icon icon="user" markupView="lexicon" />`

##### For JavaScript-generated icons

Those manually generating FontAwesome icon html can use the `Liferay.Util.getLexiconIconTpl('user')` API. For example, the previous call would return the html code for a user svg icon.

##### For direct HTML within JSPs

Developers directly using icons in jsps can either use the `liferay-ui:icon` tag as explained above or the `clay:icon` one to generate svg-based icons instead.

##### For non-controlled code

If you don't have access to the content that uses the icon fonts or you don't want to update the code or content, you can include the fonts in your Themes.

During the 7.2 upgrade process, the theme upgrade assistant prompts developers to keep FontAwesome as part of the Theme. Themes that already include the icon fonts won't be affected and will continue to work in 7.3.

#### Why was this change made?

This change was made to save bandwidth and increase performance of your sites by not serving unnecessary files.

---------------------------------------

### Removed liferay.frontend.ProgressBar
- **Date:** 2019-Aug-28
- **JIRA Ticket:** [LPS-100122](https://issues.liferay.com/browse/LPS-100122)

#### What changed?

The legacy metal+soy `liferay.frontend.ProgressBar` component, used as a temporary bridge for legacy behaviour, was removed.

#### Who is affected?

This affects any code that relies on `liferay.frontend.ProgressBar`; this is usually done via `soy` as `{call liferay.frontend.ProgressBar /}`.

#### How should I update my code?

There's no direct replacement for the `liferay.frontend.ProgressBar` component. If you have a component that relies on it, you can co-locate a copy of the old implementation and use it locally within your module.

#### Why was this change made?

The `liferay.frontend.ProgressBar` component was deprecated in 7.2 and is no longer used.

---------------------------------------

### AssetCategory's Tree Path Replaces Left/Right Category IDs
- **Date:** 2019-Oct-08
- **JIRA Ticket:** [LPS-102671](https://issues.liferay.com/browse/LPS-102671)

#### What changed?

Left and right category IDs in an `AssetCategory` have been removed and replaced with a single tree path.

#### Who is affected?

This affects anyone who uses left and right category IDs in `AssetCategory` and associated APIs.

Left and right category IDs were primarily used for `AssetCategory`'s internal hierarchical tree.

Existing `AssetCategory` service APIs remain the same, except `AssetCategoryLocalService::rebuildTree(long groupId, boolean force)`, which has been removed.

These methods have been removed from `AssetCategoryUtil`:

- `countAncestors`
- `countDescendants`
- `getAncestors`
- `getDescendants`

Methods related to left and right category IDs have been removed from `AssetEntryQuery`.

Finder methods ending in `G_P_N_V` have been replaced with methods ending in `P_N_V`.

#### How should I update my code?

##### For left and right category IDs

If you're using left and right category IDs, consider these options:

- Adapt your code to use the new tree path
- Explore whether a service API can be used to accomplish the same goal

For example, instead of working with the category IDs via `category.getLeftCategoryId()` and `category.getRightCategoryId()`, you can get the tree path via `category.getTreePath()`. Then use the tree path.

As a reference, this snippet `AssetCategoryLocalService` sets the tree path when adding a category:

```
if (parentCategory == null) {
    category.setTreePath("/" + categoryId + "/");
}
else {
    category.setTreePath(
        parentCategory.getTreePath() + categoryId + "/");
}
```

See [7.3.0-ga1 - AssetCategoryLocalServiceImpl.java#L122-L128](https://github.com/liferay/liferay-portal/blob/7.3.0-ga1/portal-impl/src/com/liferay/portlet/asset/service/impl/AssetCategoryLocalServiceImpl.java#L122-L128).

##### For AssetCategoryLocalService#rebuildTree(long, boolean)

Calls to `AssetCategoryLocalService#rebuildTree(long, boolean)` may be unnecessary. This method was mainly used to help maintain the internal hierarchical tree implmentation that has now been replaced.

Consider re-evaluating your existing code to confirm whether your call to the `rebuildTree` method is still needed.

##### For AssetCategoryUtil and AssetEntryQuery

If you use methods removed from `AssetCategoryUtil` and `AssetEntryQuery`, consider these suggestions:

- Re-evaluate your existing code
- Explore whether an existing service API can accomplish the same goal

##### For Finder Methods Involving G_P_N_V

If you use `AssetCategory` finder methods that end in `G_P_N_V`, use the methods ending in `P_N_V` instead.

#### Why was this change made?

This change was made to improve the hierarchical tree implementation for AssetCategory.

---------------------------------------

### Removed liferay.frontend.Slider
- **Date:** 2019-Oct-10
- **JIRA Ticket:** [LPS-100124](https://issues.liferay.com/browse/LPS-100124)

#### What changed?

The legacy metal+soy `liferay.frontend.Slider` component, used as a temporary bridge for legacy behaviour, was removed.

#### Who is affected?

This affects any code that relies on `liferay.frontend.Slider`; this is usually done via `soy` as `{call liferay.frontend.Slider /}`.

#### How should I update my code?

There's no direct replacement for the `liferay.frontend.Slider` component. If you have a component that relies on it, you can co-locate a copy of the old implementation and use it locally within your module.

#### Why was this change made?

The `liferay.frontend.Slider` component was deprecated in 7.2 and is no longer used.

---------------------------------------

### Removed com.liferay.asset.taglib.servlet.taglib.soy.AssetTagsSelectorTag
- **Date:** 2019-Oct-15
- **JIRA Ticket:** [LPS-100144](https://issues.liferay.com/browse/LPS-100144)

#### What changed?

The Java class `com.liferay.asset.taglib.servlet.taglib.soy.AssetTagsSelectorTag` was removed.

#### Who is affected?

This affects any code that directly instantiates or extends this class.

#### How should I update my code?

There's no direct replacement for the removed class. If you have code that depends on it, you must copy over the old implementation to your own project and change the dependency to rely on your local version.

#### Why was this change made?

The `asset:asset-tags-selector` and its components have been migrated to React, making the old tag and its soy infrastructure unnecessary.

---------------------------------------

### Removed Portal Property user.groups.copy.layouts.to.user.personal.site
- **Date:** 2019-Dec-26
- **JIRA Ticket:** [LPS-106339](https://issues.liferay.com/browse/LPS-106339)

#### What changed?

The portal property `user.groups.copy.layouts.to.user.personal.site` and the behavior associated with it were removed.

#### Who is affected?

This affects anyone who set the `user.groups.copy.layouts.to.user.personal.site` property to `true` to copy User Group pages to User Personal Sites.

#### How should I update my code?

There's no direct replacement for this property. If you depend on the behavior, you can copy the old implementations of `UserGroupLocalServiceImpl#copyUserGroupLayouts` to your own project.

#### Why was this change made?

The behavior associated with this property has been deprecated since 6.2.

---------------------------------------

### Removed Support for Auto Deploying EXT Plugins
- **Date:** 2019-Dec-31
- **JIRA Ticket:** [LPS-106008](https://issues.liferay.com/browse/LPS-106008)

#### What changed?

The support for deploying EXT plugins using Auto Deployer (via `liferay-home/deploy folder`) was removed. EXT plugins copied to the deploy folder are no longer recognized.

#### Who is affected?

This affects anyone deploying EXT plugins via the Auto Deployer.

#### How should I update my code?

There's no direct replacement for the removed feature. If you have an EXT plugin, you must deploy it manually or use [`ant direct-deploy`](https://github.com/liferay/liferay-plugins-ee/blob/7.0.x/ext/build-common-ext.xml#L211).

#### Why was this change made?

This feature has been deprecated since 7.1.

---------------------------------------

### Replaced OSGi configuration Property autoUpgrade
- **Date:** 2020-Jan-03
- **JIRA Ticket:** [LPS-102842](https://issues.liferay.com/browse/LPS-102842)

#### What changed?

The OSGi property `autoUpgrade` defined in `com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.config` was replaced with the portal property `upgrade.database.auto.run`.

Unlike the old property, which only controlled the upgrade processes in modules, the new one also affects the Core upgrade processes. The default value is `false`, so upgrade processes won't run on startup or module deployment. You can execute module upgrade processes anytime via the Gogo Shell console or via Database Upgrade Tool when the server is down.

This property is set to `true` in the `portal-developer.properties`

#### Who is affected?

This change affects any environment where you're expecting to run upgrades automatically on server startup or on module deployment. Setting `upgrade.database.auto.run` to `true` is not recommended in production environments. If you must, however, upgrade on server startup, first back up your Liferay database and File Store (Document Library).

If you set `upgrade.database.auto.run` to `false` (default value) but database upgrade is required, Liferay prints information about the required upgrade and halts startup. Database upgrade is typically required by major/minor Liferay releases and may be required by early CE Portal GA releases and certain Service Packs (in exceptional cases)--Fix Packs never require database upgrade. On startup, Liferay prints information about any pending micro changes. You can always use the Gogo Shell console and release notes to check such changes and then decide whether to execute them.

#### How should I update my code?

This change doesn't affect your code.

#### Why was this change made?

This change was made to unify the auto-upgrade feature between the Core and modules. The default value was also changed to avoid executing new upgrade processes on startup in production environments.

---------------------------------------

### Removed Cache Bootstrap Feature
- **Date:** 2020-Jan-8
- **JIRA Ticket:** [LPS-96563](https://issues.liferay.com/browse/LPS-96563)

#### What changed?

The cache bootstrap feature has been removed. These properties can no longer be used to enable/configure cache bootstrap:

- `ehcache.bootstrap.cache.loader.enabled`
- `ehcache.bootstrap.cache.loader.properties.default`
- `ehcache.bootstrap.cache.loader.properties.${specific.cache.name}`

#### Who is affected?

This affects anyone using the properties listed above.

#### How should I update my code?

There's no direct replacement for the removed feature. If you have code that depends on it, you must implement it yourself.

#### Why was this change made?

This change was made to avoid security issues.

---------------------------------------

### Removed liferay-frontend:cards-treeview Tag
- **Date:** 2020-Jan-10
- **JIRA Ticket:** [LPS-106899](https://issues.liferay.com/browse/LPS-106899)

#### What changed?

The `liferay-frontend:cards-treeview` tag was removed.

#### Who is affected?

This affects anyone using the tag from a jsp, or some of its components inside a SOY (Closure Templates) template.

#### How should I update my code?

There's no direct replacement for the removed feature. If you have code that depends on it, you must implement it yourself.

#### Why was this change made?

This change was made because the tag was primarily used internally.

---------------------------------------

### Removed liferay-frontend:contextual-sidebar Tag
- **Date:** 2020-Jan-10
- **JIRA Ticket:** [LPS-100146](https://issues.liferay.com/browse/LPS-100146)

#### What changed?

The `liferay-frontend:contextual-sidebar` tag was removed.

#### Who is affected?

This affects anyone using the tag from a jsp or some of its components inside a SOY (Closure Templates) template.

#### How should I update my code?

There's no direct replacement for the removed feature. If you have code that depends on it, you must implement it yourself.

#### Why was this change made?

This change was made because the tag was primarily used internally.

---------------------------------------

### Removed Add Action methods in Portal Vulcan API
- **Date:** 2020-Jan-22
- **JIRA Ticket:** [LPS-98387](https://issues.liferay.com/browse/LPS-98387)

#### What changed?

The `addAction` methods with signatures `String, Class, GroupedModel, String, UriInfo` and `String, Class, Long, String, String, Long, UriInfo` were removed.

#### Who is affected?

This affects anyone using the removed `addAction` methods or anyone that has dependencies like `compileOnly group: "com.liferay", name: "com.liferay.portal.vulcan.api", version: "[1.0.0, 2.0.0)"`.

#### How should I update my code?

Use `addAction` methods with the signature `String, Class, GroupedModel, String, Object, UriInfo` or `String, Class, Long, String, String, Object, Long, UriInfo`.

#### Why was this change made?

These methods were removed as part of a cleanup refactor.

---------------------------------------

### Changed Control Menu and Product Menu Positioning
- **Date:** 2020-Feb-04
- **JIRA Ticket:** [LPS-107487](https://issues.liferay.com/browse/LPS-107487)

#### What changed?

The placement and structure of the Control and Product Menus has changed to address several accessibility concerns and common visual glitches.

These changes have been applied to the Control and Product menus:

- The Product Menu has been moved outside of the Control Menu
- The Control Menu now uses `position:sticky` to control its behaviour
- Styles of the menus inside the Control Menu have been updated to account for the new sticky behaviour

#### Who is affected?

This could affect developers that have a custom Control Panel Theme with a customized `portlet.ftl` template or those that have developed a custom menu that behaves as a sticky bar and is included using the `*ControlMenuEntry` API.

#### How should I update my code?

##### Control Panel Themes

Developers with custom Control Panel themes should move the call (if any) to the `@liferay.control_menu` macro above the portlet section in their `portlet.ftl`.

**Before:**

```jsp
<section class="portlet" id="portlet_${htmlUtil.escapeAttribute(portletDisplay.getId())}">
	${portletDisplay.writeContent(writer)}
</section>

<#if portletDisplay.isStateMax()>
	<@liferay.control_menu />
</#if>
```

**After:**

```jsp
<#if portletDisplay.isStateMax()>
	<@liferay.control_menu />
</#if>

<section class="portlet" id="portlet_${htmlUtil.escapeAttribute(portletDisplay.getId())}">
	${portletDisplay.writeContent(writer)}
</section>
```

##### Custom Sticky Bars

Developers with custom Sticky Bars included using the `*ControlMenuEntry` API can use the newly included extension points in the Control Menu to inject their components.

Move the code that injects the menu to a `DynamicInclude` component and register it for the proper position:

- Before the Control Menu: Use `com.liferay.product.navigation.taglib#/page.jsp#pre`
- After the Control Menu: Use `com.liferay.product.navigation.taglib#/page.jsp#post`

#### Why was this change made?

This change was made to improve accessibility and simplify the required logic for positioning and controlling top-positioned menus. It provides a more correct and expected markup that avoids common visual glitches.

---------------------------------------

### jQuery Is No Longer Included by Default
- **Date:** 2020-Feb-04
- **JIRA Ticket:** [LPS-95726](https://issues.liferay.com/browse/LPS-95726)

#### What changed?

Previously, `jQuery` was being included on every page by default and made available through the global `window.$` and the scoped `AUI.$` variables. After this change, `jQuery` is no longer included by default and those variables are `undefined`.

#### Who is affected?

This affects any developer who used `AUI.$` or `window.$` in their custom scripts.

#### How should I update my code?

Use any of the strategies to add third party libraries to provide your own version of JQuery to use in your code.

Additionally, as a temporary measure, you can bring back the old behaviour by setting the `Enable jQuery` property in *System Settings* &rarr; *Third Party* &rarr; *jQuery* to `true`.

#### Why was this change made?

This change was made to avoid bundling and serving additional library code on every page that was mostly unused and redundant.

---------------------------------------

### Server-side Parallel Rendering Is No Longer Supported
- **Date:** 2020-Mar-16
- **JIRA Ticket:** [LPS-110359](https://issues.liferay.com/browse/LPS-110359)

#### What changed?

Properties with the prefix `layout.parallel.render` were removed, which means parallel rendering is only supported when AJAX rendering is enabled.

#### Who is affected?

This affects anyone using the removed properties.

#### How should I update my code?

Remove any properties prefixed with `layout.parallel.render` from your properties file.

#### Why was this change made?

This feature has been deprecated.

---------------------------------------

### The ContentField value Property Name Was Changed to contentFieldValue
- **Date:** 2020-Mar-18
- **JIRA Ticket:** [LPS-106886](https://issues.liferay.com/browse/LPS-106886)

#### What changed?

In Headless Delivery API, the property name `value` inside the ContentField schema was changed to `contentFieldValue`.

#### Who is affected?

This affects REST clients depending in the ContentField `value` property name.

#### How should I update my code?

Change the property name to `contentFieldValue` in the REST client.

#### Why was this change made?

This change restores consistency with all value property names in the Headless APIs, called `{schemaName}+Value`.

---------------------------------------

### Removed liferay-editor-image-uploader Plugin
- **Date:** 2020-Mar-27
- **JIRA Ticket:** [LPS-110734](https://issues.liferay.com/browse/LPS-110734)

### What changed?

`liferay-editor-image-uploader` AUI plugin was removed. Its code was merged into `addimages` CKEditor plugin, used by Alloy Editor and CKEditor.

### Who is affected

This affects custom solutions that use the plugin directly.

### How should I update my code?

There's no direct replacement for the `liferay-editor-image-uploader` plugin. If you have a component that relies on it, you can co-locate a copy of the old implementation and use it locally within your module.

#### Why was this change made?

This change enables image drag and drop handling in CKEditor and provides a common image uploader for both Alloy Editor and CKEditor.

---------------------------------------

### TinyMCE Editor Is No Longer Bundled by Default
- **Date:** 2020-Mar-27
- **JIRA Ticket:** [LPS-110733](https://issues.liferay.com/browse/LPS-110733)

### What changed?

As of 7.3, CKEditor is the default and only supported WYSIWYG editor.

### Who is affected

This affects anyone who uses TinyMCE.

### How should I update my code?

If you've configured Liferay Portal to use the TinyMCE, you can remove these configurations. If you still want to use TinyMCE, you must take these steps:

- Keep your configurations.
- Open https://repository.liferay.com/nexus/index.html in your browser.
- Search for `com.liferay.frontend.editor.tinymce.web`.
- Download a .jar file for the `com.liferay.frontend.editor.tinymce.web` module.
- Deploy the .jar file you downloaded in your liferay-portal instalation.

#### Why was this change made?

This change was made to consolidate all our UX for writing Rich Text Content around a single Editor to provide a more cohesive and comprehensive experience.

---------------------------------------

### Simple Editor Is No Longer Bundled by Default
- **Date:** 2020-Mar-27
- **JIRA Ticket:** [LPS-110734](https://issues.liferay.com/browse/LPS-110734)

### What changed?

As of 7.3, CKEditor is the default and only supported WYSIWYG editor.

### Who is affected

This affects anyone who uses the Liferay Front-end Editor Simple Web module.

### How should I update my code?

If you've configured Liferay Portal to use the Simple Editor, you can remove these configurations. If you still want to use the Simple Editor, you must take these steps:

- Keep your configurations.
- Open https://repository.liferay.com/nexus/index.html in your browser.
- Search for `com.liferay.frontend.editor.simple.web`.
- Download a .jar file for the `com.liferay.frontend.editor.simple.web` module.
- Deploy the .jar file you downloaded in your liferay-portal installation.

#### Why was this change made?

This change was made to consolidate all our UX for writing Rich Text Content around a single Editor to provide a more cohesive and comprehensive experience.

---------------------------------------

### asset.vocabulary.default Now Holds a Language Key
- **Date:** 2020-Apr-28
- **JIRA Ticket:** [LPS-112334](https://issues.liferay.com/browse/LPS-112334)

### What changed?

`asset.vocabulary.default` is now a language key and no longer has a fixed value of `Topic`.

### Who is affected

This affects anyone who overwrites the property.

### How should I update my code?

There is no need to change the code if the property is not overwritten. If the property was overwritten and the specified key is not found, the provided text will be taken as the name of the default vocabulary.

#### Why was this change made?

The change was made so users don't have to change the name for the default vocabulary in all languages.

---------------------------------------

### Liferay.Poller Is No Longer Initialized by Default
- **Date:** 2020-May-19
- **JIRA Ticket:** [LPS-112942](https://issues.liferay.com/browse/LPS-112942)

#### What changed?

The global AUI `Liferay.Poller` utility is now deprecated and is no longer initialized by default.

#### Who is affected?

This affects any code that relies on `Liferay.Poller`; this is usually done via a call to `Liferay.Poller.init()` in a JSP.

#### How should I update my code?

There's no direct replacement for the `Liferay.Poller` utility. If you must initialize `Liferay.Poller`, update your JSP to use the code below:

```jsp
<%@ page import="com.liferay.petra.encryptor.Encryptor" %>

<%-- For access to `company` and `themeDisplay`. --%>
<liferay-theme:defineObjects>

<aui:script use="liferay-poller">
	<c:if test="<%= themeDisplay.isSignedIn() %>">
		Liferay.Poller.init({
			encryptedUserId:
				'<%= Encryptor.encrypt(company.getKeyObj(), String.valueOf(themeDisplay.getUserId())) %>',
		});
	</c:if>
</aui:script>
```

#### Why was this change made?

The `Liferay.Poller` component was only used in the Chat application, which is archived. Skipping initialization by default streamlines page loads for the common case.

---------------------------------------

### ContentTransformerListener Is Disabled By Default
- **Date:** 2020-May-25
- **JIRA Ticket:** [LPS-114239](https://issues.liferay.com/browse/LPS-114239)

#### What changed?

`ContentTransformerListener` is now disabled by default.

#### Who is affected?

This affects Liferay Portal installations using legacy web content features provided by the `ContentTransformerListener`, such as embedding web content inside another web content, a legacy edit in place infrastructure, token replacements (`@article_group_id@`, `@articleId;elementName@`), etc.

#### How should I update my code?

There's no need to update your code. If you still want to use `ContentTransformerListener`, you can enable it in System Settings via the *Enable ContentTransformerListener* property under *Content & Data* &rarr; *Web Content* &rarr; *Virtual Instance Scope* &rarr; *Web Content*.

#### Why was this change made?

`ContentTransformerListener` was disabled to improve performance, due to its expensive string processing on article elements (calling `HtmlUtil.stripComments` and `HtmlUtil.stripHtml` on article fields).

---------------------------------------

### Liferay.BrowserSelectors.run Is No Longer Called
- **Date:** 2020-May-26
- **JIRA Ticket:** [LPS-112983](https://issues.liferay.com/browse/LPS-112983)

#### What changed?

The `Liferay.BrowserSelectors.run()` function is no longer called on pages, which as a result removes some CSS classes from the opening `<html>` tag. Many of these are now added to the `<body>` element instead.

#### Who is affected?

This affects any code that relies on these CSS classes in the `<html>` element:

- `aol`
- `camino`
- `edgeHTML` or `edge`
- `firefox`
- `flock`
- `gecko`
- `icab`
- `ie`, `ie6`, `ie7`, `ie9`, or `ie11`
- `js`
- `konqueror`
- `mac`
- `mozilla`
- `netscape`
- `opera`
- `presto`
- `safari`
- `secure`
- `touch`
- `trident`
- `webkit`
- `win`

#### How should I update my code?

There's no direct replacement for the `Liferay.BrowserSelectors.run()` function, but you can adapt your CSS and JavaScript to target new classes on the `<body>` element instead. These classes are added to the `<body>` element to reflect the browser you're currently using:

- `chrome`
- `edge`
- `firefox`
- `ie`
- `mobile`
- `other`

Alternatively, you can still invoke `Liferay.BrowserSelectors.run()` to apply the old classes to the `<html>` element with the code below:

```
<aui:script use="liferay-browser-selectors">
	Liferay.BrowserSelectors.run();
</aui:script>
```

#### Why was this change made?

The classes, some of which referred to outdated browsers, were being added to the top `<html>` element via legacy JavaScript that depended on Alloy UI. This change, which removes the outdated browser references, is now done on the server side, improving page loading times.

---------------------------------------

### Remove Support for Blocking Cache
- **Date:** 2020-Jun-17
- **JIRA Ticket:** [LPS-115687](https://issues.liferay.com/browse/LPS-115687)

#### What changed?

Blocking cache support was removed. These properties can no longer be used to enable blocking cache:

- `ehcache.blocking.cache.allowed`
- `permissions.object.blocking.cache`
- `value.object.entity.blocking.cache`

#### Who is affected?

This affects anyone using the properties listed above.

#### How should I update my code?

There's no direct replacement for the removed feature. If you have code that depends on it, you must implement it yourself.

#### Why was this change made?

This change was made to improve performance because blocking caches should never be enabled.

---------------------------------------

### Remove Support for Setting Cache Properties for Each Entity Model
- **Date:** 2020-Jun-24
- **JIRA Ticket:** [LPS-116049](https://issues.liferay.com/browse/LPS-116049)

#### What changed?

Support was removed for setting these cache properties for an entity:

- `value.object.entity.cache.enabled*`
- `value.object.finder.cache.enabled*`
- `value.object.column.bitmask.enabled*`

For example, these properties are for entity `com.liferay.portal.kernel.model.User`:

- `value.object.entity.cache.enabled.com.liferay.portal.kernel.model.User`
- `value.object.finder.cache.enabled.com.liferay.portal.kernel.model.User`
- `value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.User`

#### Who is affected?

This affects anyone using the properties listed above for an entity.

#### How should I update my code?

There's no direct replacement for the removed feature. You must remove these properties from your entities.

#### Why was this change made?

This change was made because these properties are not useful for an entity.

---------------------------------------

### Renamed Portal Properties "module.framework.properties.felix.fileinstall.\*" to "module.framework.properties.file.install.\*"
- **Date:** 2020-Jul-13
- **JIRA Ticket:** [LPS-115016](https://issues.liferay.com/browse/LPS-115016)

#### What changed?

Portal properties beginning with "module.framework.properties.felix.fileinstall" have been renamed to begin with "module.framework.properties.file.install".

#### Who is affected?

This affects anyone who has overridden `module.framework.properties.felix.fileinstall.*` portal property settings.

#### How should I update my code?

Rename properties starting with `module.framework.properties.felix.fileinstall.*` to start with `module.framework.properties.file.install.*`

#### Why was this change made?

This change was made to reflect the inlining of Apache Felix File Install. Liferay is now managing and maintaining this functionality.

---------------------------------------

### Dynamic Data Mapping fields in Elasticsearch have changed to a nested document
- **Date:** 2020-Jul-27
- **JIRA Ticket:** [LPS-103224](https://issues.liferay.com/browse/LPS-103224)

#### What changed?

Dynamic Data Mapping fields in Elasticsearch that start with `ddm__keyword__` and `ddm__text__` have been moved to a new nested document `ddmFieldArray`.

The `ddmFieldArray` has several entries with following fields:

- `ddmFieldName`: Contains the Dynamic Data Mapping structure field name. This name is generated using `DDMIndexer.encodeName` methods.
- `ddmFieldValue*`: Contains the indexed data. The name of this field is generated using `DDMIndexer.getValueFieldName` and depends on the field's data type and language.
- `ddmValueFieldName`: Contains the index field name where the indexed data is stored.

 This change is not applied if you are using the Solr search engine.

#### Who is affected?

This affects anyone with custom developments that execute queries in the Elasticsearch index using `ddm__keyword__*` and `ddm__text__*` fields.

#### How should I update my code?

You have to use the new nested document `ddmFieldArray` in your Elasticsearch queries.

There are some examples in Liferay code. For example, [DDMIndexerImpl](https://github.com/liferay/liferay-portal/blob/7.3.x/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/util/DDMIndexerImpl.java) and [AssetHelperImpl](https://github.com/liferay/liferay-portal/blob/master/modules/apps/asset/asset-service/src/main/java/com/liferay/asset/internal/util/AssetHelperImpl.java) use the `DDM_FIELD_ARRAY` constant.

You can also restore the legacy behavior from System Settings and continue using `ddm__keyword__*` and `ddm__text__*` fields.

1. Go to *System Settings* &rarr; *Dynamic Data Mapping* &rarr; *Dynamic Data Mapping Indexer*.
1. Select *Enable Legacy Dynamic Data Mapping Index Fields*.
1. Execute a full reindex.

#### Why was this change made?

This change was made to avoid the *Limit of total fields has been exceeded* Elasticsearch error that occurs if you have too many Dynamic Data Mapping structures.

---------------------------------------

### Moving Lexicon icons path
- **Date:** 2020-Aug-17
- **JIRA Ticket:** [LPS-115812](https://issues.liferay.com/browse/LPS-115812)

### What changed?

The path for the Lexicon icons has been changed from `themeDisplay.getPathThemeImages() + "/lexicon/icons.svg` to `themeDisplay.getPathThemeImages() + "/clay/icons.svg`

### Who is affected

This affects custom solutions that use the Lexicon icons path directly. The Gradle task for building the icons on the `lexicon` path will be removed.

### How should I update my code?

Update the path to reference `clay` instead of `lexicon`

#### Why was this change made?

This change was made to unify references to the icon sprite map.

---------------------------------------

### Removed classNameId related methods from DDM Persistence classes
- **Date:** 2020-Aug-18
- **JIRA Ticket:** [LPS-108525](https://issues.liferay.com/browse/LPS-108525)

### What changed?

The `countByClassNameId`, `findByClassNameId`, and `removeByClassNameId` methods were removed from the following classes:

- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructurePersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateLinkPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateLinkUtil`

### Who is affected

This affects anyone who uses any of these methods.

### How should I update my code?

You can use the other finder and counter methods.

#### Why was this change made?

These methods were removed as part of the solution for [LPS-108525](https://issues.liferay.com/browse/LPS-108525).

---------------------------------------

### Removed com.liferay.dynamic.data.mapping.util.BaseDDMDisplay Method
- **Date:** 2020-Aug-18
- **JIRA Ticket:** [LPS-103549](https://issues.liferay.com/browse/LPS-103549)

### What changed?

The `isShowAddStructureButton` method was removed.

### Who is affected

This affects anyone who uses this method.

### How should I update my code?

You can use `isShowAddButton(Group scopeGroup)` method instead of this method.

#### Why was this change made?

This method was removed as part of a clean up refactor.

---------------------------------------

### Replaced portal properties: view.count.enabled and buffered.increment.enabled
- **Date:** 2020-Oct-01
- **JIRA Ticket:** [LPS-120626](https://issues.liferay.com/browse/LPS-120626) and [LPS-121145](https://issues.liferay.com/browse/LPS-121145)

#### What changed?

Enabling and disabling view counts globally and specifically for entities has been removed from portal properties and is now configured as system settings. View counts can be configured in the UI at *System Settings* &rarr; *Infrastructure* &rarr; *View Count* or using a configuration file named `com.liferay.view.count.configuration.ViewCountConfiguration.config`.

Here are the portal property changes:

The `buffered.increment.enabled` portal property has been removed. Enabling and disabling view counts globally is now done using the `enabled` property on the View Count page.

Disabling view count behavior for a specific entity is no longer done in portal properties, for example, by setting `view.count.enabled[SomeEntity]=false` in 7.3 or `buffered.increment.enabled[SomeEntity]=false` in 7.2, but is now done by adding the entity class name to the `Disabled Class Name` value list on the View Count page.

#### Who is affected?

This affects anyone who has the portal property setting `view.count.enabled=false` or `buffered.increment.enabled=false`.

This affects anyone who has disabled view counts for some entity (e.g., `SomeEntity`) using portal property settings `view.count.enabled[SomeEntity]=false` in early 7.3 versions or `buffered.increment.enabled[SomeEntity]=false` in 7.2 portal.

#### How should I update my code?

Remove `view.count.enabled` or `buffered.increment.enabled` portal properties and entity-specific properties such as `view.count.enabled[SomeEntity]=false` or `buffered.increment.enabled[SomeEntity]=false`.

Configure view count behavior in System Settings or using a configuration file:

In *System Settings* &rarr; *Infrastructure* &rarr; *View Count*, set `enabled` to `false` to disable view counts globally, or set `enabled` to `true` to enable view counts globally and disable view counts for specific entities by adding the entity class names to the `Disabled Class Name` value list.

To use a configuration file, configure view counts in System Settings, save the settings, and export them to a `com.liferay.view.count.configuration.ViewCountConfiguration.config` file. Then deploy the configuration by placing the file in your `[Liferay Home]/osgi/configs` folder.

#### Why was this change made?

This change was made to facilitate managing view count behavior.

---------------------------------------

### Removed Portal Property "module.framework.properties.file.install.optionalImportRefreshScope"
- **Date:** 2020-Oct-11
- **JIRA Ticket:** [LPS-122008](https://issues.liferay.com/browse/LPS-122008)

#### What changed?

Portal property `module.framework.properties.file.install.optionalImportRefreshScope` has been removed. File Install will now only checks managed bundles when scanning for bundles with optional packages that need to be refreshed.

#### Who is affected?

This affects anyone who has the portal property settings `module.framework.properties.file.install.optionalImportRefreshScope`.

#### How should I update my code?

Remove property `module.framework.properties.file.install.optionalImportRefreshScope`. File Install cannot be configured to use other behavior.

#### Why was this change made?

There are very few cases where alternate behavior was desirable. File Install is the primary way bundles are installed into Liferay, so it is now the bundle management default. Removing the old feature and its branching logic improves code maintainability and readability.

---------------------------------------