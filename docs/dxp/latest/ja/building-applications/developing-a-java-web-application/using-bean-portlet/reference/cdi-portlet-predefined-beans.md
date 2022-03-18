# CDIポートレットの事前定義されたBean

Liferay DXPは、[JSR 362](https://jcp.org/en/jsr/detail?id=362)で指定されているように、ポートレットの事前定義されたBeanと呼ばれるCDI用の注入可能なポートレットアーティファクトを提供します。  事前定義されたBeanには次の2つのタイプがあります。

* ポートレットリクエストスコープのBean（[`@PortletRequestScoped`](https://learn.liferay.com/reference/latest/en/portlet-api/javax/portlet/annotations/PortletRequestScoped.html)

* 依存スコープのBean（[`@Dependent` scoped](https://docs.oracle.com/javaee/7/api/javax/enterprise/context/Dependent.html)）

次の表は、各Beanのこれらの属性を示しています。

**アーティファクト：** Beanのタイプ。

**Bean EL名：** JSPまたはJSFページでBeanにアクセスするための式言語（EL）名。

**修飾子：**Bean実装を定義および選択するためにBeanに適用されるアノテーション。

**有効なフェーズ：**Beanが有効な[ポートレットフェーズ](../../reference/portlets.md)。

## ポートレットリクエストスコープのBean

これらのBeanには`@PortletRequestScoped`アノテーションがあります。 これらのアーティファクトタイプ、Bean EL名、アノテーション修飾子、および有効なポートレットフェーズは次のとおりです。

表1：ポートレットリクエストスコープのBean[^1]

| アーティファクト                      | Bean EL名              | 修飾子 | 有効期間           |
| ----------------------------- | --------------------- | --- | -------------- |
| `PortletConfig`               | `portletConfig`       | -   | すべて            |
| `PortletRequest`              | `portletRequest`      | -   | すべて            |
| `PortletResponse`             | `portletResponse`     | -   | すべて            |
| `ActionRequest`               | `actionRequest`       | -   | 操作             |
| `ActionResponse`              | `actionResponse`      | -   | 操作             |
| `HeaderRequest`               | `headerRequest`       | -   | ヘッダ            |
| `HeaderResponse`              | `headerResponse`      | -   | ヘッダ            |
| `RenderRequest`               | `renderRequest`       | -   | レンダー           |
| `RenderResponse`              | `renderResponse`      | -   | レンダー           |
| `EventRequest`                | `eventRequest`        | -   | イベント           |
| `EventResponse`               | `eventResponse`       | -   | イベント           |
| `ResourceRequest`             | `resourceRequest`     | -   | リソース           |
| `ResourceResponse`            | `resourceResponse`    | -   | リソース           |
| `StateAwareResponse`          | `stateAwareResponse`  | -   | アクション、イベント     |
| `MimeResponse`                | `mimeResponse`        | -   | ヘッダー、レンダー、リソース |
| `ClientDataRequest`           | `clientDataRequest`   | -   | アクション、リソース     |
| `RenderParameters`            | `renderParams`        | -   | すべて            |
| `MutableRenderParameters`     | `mutableRenderParams` | -   | アクション、イベント     |
| `ActionParameters`            | `actionParams`        | -   | 操作             |
| `ResourceParameters`          | `resourceParams`      | -   | リソース           |
| `PortletContext`              | `portletContext`      | -   | すべて            |
| `PortletMode`                 | `portletMode`         | -   | すべて            |
| `WindowState`                 | `windowState`         | -   | すべて            |
| `PortletPreferences`          | `portletPreferences`  | -   | すべて            |
| `Cookies(List<Cookie>)` | `cookie`              | -   | すべて            |
| `PortletSession`              | `portletSession`      | -   | すべて            |
| `Locales(List<Locale>)` | `locales`             | -   | すべて            |

## 依存スコープのBean

これらのBeanは`@Dependent`スコープを使用します。 それらは`java.lang.String`型で、 `final`です。 これにより、プロキシされなくなります。  元のスコープよりも広いスコープで依存スコープのBeanを使用しないようにするには、それらを`@PortletRequestScoped` Beanにのみ注入する必要があります。

表2：依存スコープのBean[^2]

| アーティファクト                | Bean EL名      | 修飾子            | 有効期間 |
| ----------------------- | ------------- | -------------- | ---- |
| `Namespace` (String)    | `namespace`   | `@Namespace`   | すべて  |
| `ContextPath` (String)  | `contextPath` | `@ContextPath` | すべて  |
| `WindowID` (String)     | `windowId`    | `@WindowId`    | すべて  |
| `Portlet name` (String) | `portletName` | `@PortletName` | すべて  |

## 追加情報

[CDI Dependency Injection](../../../core-frameworks/dependency-injection.md)

[^1]: Martin Scott Nicklous、Java&trade; Portlet Specification 3.0、122ページ。

[^2]: Martin Scott Nicklous、Java&trade; Portlet Specification 3.0、123ページ。