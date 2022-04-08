---
description: 8 - Customize the User Interface
title: Liferay User Interface Technologies Overview
order: 2
---

## Liferay User Interface Technologies Overview

Because of comprehensive web service APIs, the Liferay platform can be accessed from many kinds of user interfaces. In addition to the portal web user interface, Liferay makes it possible for desktop and mobile Sync clients to access the document repository, providing tools like [Liferay Screens](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/android-apps-with-liferay-screens) and [Mobile SDK](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/mobile-sdk) for creating native mobile applications. In the following sections, we will discuss the basic concepts and means of customizing the portal web user interface. 

#### Web User Interface Technologies Overview {#web}

Liferay's web user interface is built mainly on JSP technology. Styling and user interface responsiveness is accomplished with Liferay Lexicon and Clay frameworks. Three main JavaScript frameworks are used and provided: Alloy UI, [jQuery](https://jquery.com), and Metal.js, but Liferay doesn't restrict you from using your own framework. Additionally, JSP user interfaces largely rely on both standard and Liferay-custom tag libraries.

#### Liferay Lexicon {#lex}

[Liferay Lexicon](https://lexicondesign.io) is an abstract user interface design language. Lexicon doesn't dictate the implementation, but provides principles, patterns, and tools to produce a common design framework and a consistent style and user experience.

See an example of Lexicon's definition of form here: https://lexicondesign.io/docs/patterns/Forms/forms.html

#### Liferay Clay {#clay}

[Liferay Clay](https://clayui.com) is an __implementation__ of the Lexicon Experience Design Language. Liferay Clay is an extension of the Twitter Bootstrap framework and is built with HTML, CSS, and JavaScript. The dependency of Twitter Bootstrap means that jQuery is always available in Liferay by default.

Below is an example of a drop-down menu created with Clay CSS classes:

<img src="../images/clay-example.png" style="max-width: 100%;" />

#### AlloyUI {#alloy}

[Alloy UI](http://alloyui.com) is an extensive UI framework incorporating HTML, CSS, and JavaScript built on [Yahoo YUI3](https://yuilibrary.com). AlloyUI has over 350 YUI and 150 modules. Because Yahoo decided to stop maintaining the YUI, AlloyUI has been deprecated since Liferay DXP 7.0. Although legacy, AlloyUI is still commonly used in the portal native user interface and will be supported throughout the whole Liferay 7.x product lifecycle.

Below is an example of a drop-down menu created with AlloyUI:

<img src="../images/alloyui-example.png" style="max-width: 100%;" />

#### Metal.js {#metal}

Development of Liferay's own lightweight JavaScript framework [Metal.js](https://metaljs.com) started after Yahoo YUI deprecation in 2014. Metal.js integrates with Google Closures (SOY) and Facebook (JSX) templating languages, supporting ECMAScript 2015 and 6 (ES 2015, ES6). Metal.js also has isomorphic, server-side rendering (SSR) and AMD loader support.

Below is an example of a Metal.js component with a SOY template. When compiled and transpiled, Metal.js components are merged into a single JavaScript file:

```html
{namespace View}

/**
 * Prints the Hello Soy portlet main view
 *
 * @param id
 * @param layouts
 * @param navigationURL
 */
{template .render}
	<div id="{$id}">
		{call Header.render data="all"}{/call}

		<p>{msg desc=""}here-you-will-find-how-easy-it-is-to-do-things-like{/msg}</p>

		<h3>{msg desc=""}listing-pages{/msg}</h3>

		<div class="list-group">
			<div class="list-group-heading">{msg desc=""}navigate-to{/msg}</div>

			{foreach $layout in $layouts}
				<a class="list-group-item" href="{$layout.friendlyURL}">{$layout.nameCurrentValue}</a>
			{/foreach}
		</div>

		<h3>{msg desc=""}navigating-between-views{/msg}</h3>

		<a href="{$navigationURL}">{msg desc=""}click-here-to-navigate-to-another-view{/msg}</a>

		{call Footer.render data="all"}{/call}
	</div>
{/template}
```

```javascript
import Component from 'metal-component/src/Component';
import Footer from './Footer.soy';
import Header from './Header.soy';
import Soy from 'metal-soy/src/Soy';
import templates from './View.soy';

class View extends Component {}

// Register component
Soy.register(View, templates);

export default View;
```

> See a hello SOY example here: https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/hello-soy.

#### Tag Libraries {#tag}

Liferay offers a set of fully-integrated taglibs for use in JSP files. In addition to reducing boilerplate code, taglibs provide other advantages, too, like a consistent and responsive user interface. The documentation for most of the Liferay taglibs can be accessed on the Liferay docs site (https://docs.liferay.com/ce/portal/7.2-latest/taglibs/util-taglib/).

#### User Interface Customization Overview {#custom}

The following mindmap summarizes the typical approaches for user interface customization in Liferay:

<img src="../images/ui-customization-overview.png" style="max-width: 100%;"/>

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
    <li> Liferay __________ is the UI design language, while __________ is its implementation.</li>
    <li>The Liferay web user interface still relies on ___________, but Metal.js is increasingly being introduced in the new native applications.</li>
    <li>As a development platform, Liferay is framework-agnostic. Developers can use any preferred ___________ libraries.</li>
</ul>
</div>