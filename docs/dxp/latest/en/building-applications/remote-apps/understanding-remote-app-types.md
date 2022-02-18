# Understanding Remote App Types

Remote Apps uses Liferay's front-end infrastructure to register external JS applications with the Liferay platform and render them as Page widgets. When creating a Remote App entry, you can select between the [IFrame](#using-the-iframe-type) and [Custom Element](#using-the-custom-element-type) types. This determines how the external application is registered with Liferay DXP and rendered in Site Pages.

Both types are incorporated/integrated into a Page during the render Page lifecycle.
When an entry is created, Liferay automatically generates a widget for displaying it on Site Pages. If desired, you can set additional properties at the entry or widget level to configure how the application is rendered.
Everything happens in the browsers.

<!-- Q: Should I note that Liferay automatically generates some OSGi details for each Remote App entry? -->

However...
They are hosted and rendered differently. They also have different levels of access to Liferay services and runtime data.

```{note}
You can review the [`RemoteAppEntryPortlet`](https://github.com/liferay/liferay-portal/blob/7bf2fe6b90af5f50a2fd3d0d77f1148fb71fc339/modules/apps/remote-app/remote-app-web/src/main/java/com/liferay/remote/app/web/internal/portlet/RemoteAppEntryPortlet.java) class to see how each type is rendered as a Page widget.
```

## Using the IFrame Type

The IFrame type renders external applications into a separate `<iframe>` element and allows for limited interaction with the host page using Liferay's client SDK API. The architecture for IFrame applications includes three main parts: the Liferay server, the Liferay host Page, and the external server hosting your application.

![](./understanding-remote-app-types/images/01.png)

Each IFrame Remote App entry stores an IFrame URL that links to an application served from an external server. This entry can also store properties rendered with the application during runtime.

The Remote App widget renders an `<iframe>` pointing to the application's external URL.

The external server On the right side, we have the server where the external application is hosted. This includes the application with its markup, scripts, and styles. __ referenced by the iframe URL.

```{note}
Setting up an IFrame Remote App, the external server must host and serve the application and not just storing code. running on its server.
```

In the center, we have a typical Liferay page in which the Remote App's widget is deployed. This widget is rendered as an`<iframe>` element and is populated with the application content at the target URL. If this includes the client SDK, the widget can make service calls to the DXP server and access state details. Alternatively, the client SDK can be provided directly from the Liferay server.

The contact between the Liferay Platform and IFrame Remote App is limited to the IFrame URL, client SDK API (optional), and `<iframe (+properties)/>` element (which is the mechanism to render it plus some properties)

```{tip}
Due to IFrame limitations, this Remote App type is not recommended for more complex use cases. Instead, use the [Custom Element]() type for more complex scenarios.
```

### How the Client SDK API Works

All __ use the same-origin policy for total isolation between the Liferay host Page and external application page. This is a constraint imposed by the browser to prevent information flow between pages and applications. However, you can use Liferay's client SDK to allow limited communication between the external application and Liferay Page. This SDK provides a simple API that allows the IFrame Remote App to call DXP services and access some Liferay runtime information.

```{note}
This capability distinguishes the IFrame Remote Apps from the IFrame widget.
```

Examples include,

* Allowing the application to open a 'toast' in the host page
* Allowing the application to navigate the host page to a URL
* Allowing the application to access Liferay properties (e.g., siteId, languageId)
* Allowing the application to fetch a portal URL as the current user (e.g., to execute a headless API method)
* Allowing the application to run a graphQL query as the current user.

Using the provided client SDK API, registered applications can access portal runtime information and call DXP services.

This API is meant to be served from a CDN.[???] <!-- * If you want to serve it directly from Liferay, you'd have to manually deployed it to liferay. ([`modules/apps/remote-app/remote-app-client-js`](https://github.com/liferay/liferay-portal/tree/master/modules/apps/remote-app/remote-app-client-js)) * The entry point is `window.__LIFERAY_REMOTE_APP_SDK__`-->`gw clean deploy -Pnodejs.node.env=development`. Once deployed, your Remote App can use it directly from Liferay.

Deploying the Remote App Client SDK

<!-- Q Will users always have to deploy the client SDK to the Liferay server?-->

### Analyzing the `_renderIFrame` Code

The [`RemoteAppEntryPortlet`](https://github.com/liferay/liferay-portal/blob/7bf2fe6b90af5f50a2fd3d0d77f1148fb71fc339/modules/apps/remote-app/remote-app-web/src/main/java/com/liferay/remote/app/web/internal/portlet/RemoteAppEntryPortlet.java) class extends the [`MVCPortlet`]() interface first checks for the Remote App type. If the type is `TYPE_IFRAME`, it calls the [`_renderIFrame`](https://github.com/liferay/liferay-portal/blob/7bf2fe6b90af5f50a2fd3d0d77f1148fb71fc339/modules/apps/remote-app/remote-app-web/src/main/java/com/liferay/remote/app/web/internal/portlet/RemoteAppEntryPortlet.java#L166-L210) method.

## Using the Custom Element Type

The Custom Element type renders external application code into a separate HTML `<custom-element>` and provides full integration with the Liferay host Page. The architecture for Custom Element applications includes three main parts: the Liferay server, the Liferay host Page, and the server storing your application's code.
<!-- The Custom Element type renders the application as an HTML custom element (`<custom-element>`) within a Site Page, without needing an `<iframe>` element. -->

![](./understanding-remote-app-types/images/02.png)

<!-- Rather than having an app hosted in a different server, what we have is code stored in a different server. -->

Unlike IFrame Remote App entries, this type of Remote App widget creates the application. The widget inserts the Remote App entry's JS and CSS files directly into the Site Page using/as `<script>` and `<link>` elements. This code is loaded in the browser and responsible for creating the custom element. The JS files create the custom element, while the css files provide application style. __ is then rendered with any properties defined in the Remote App entry or directly in the deployed widget.  <!--The only responsibility for the widget is to render the custom element with the provided properties.

Sum of the contact between the Platform and the Application:

* Custom Element name & declaration (via IIFE); the app has to declare the custom element and provide its implementation. and it must do so using an [UNCLEAR] invoke function expression--so that once we load the JS function, the function would be immediately executed in the browser.
* JS/CSS URLS; the Remote App can include more JS or CSS URLS that are loaded in the page.
* `<custom-element (+props)/>`; then the portal will render the custom element mark up, passing it some properties.
* Liferay JS API; the custom element JS code then has full access to the Liferay JS API and style classes, which means they have access to the host page's window update, etc. (unless Shadow DOM is used; allows you to isolate the DOM that is created within a custom element, isolating it from the outside world. An isolated element  under the Shadow DOM won't receive any style information from the host page [clarify...]).

The level of integration is much higher.

Other comments:

* Multiple instances of the widget will include URLs once (nice)
* Friendly URL mapping...

The `<custom-element>` __ functions as an extension mechanism for HTML that creates new tags and associates them with custom logic.



## Additional Information
