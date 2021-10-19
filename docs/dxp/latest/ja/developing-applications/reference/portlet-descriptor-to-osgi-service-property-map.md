# OSGiサービスのプロパティマップへのポートレット記述子

ここでは、OSGiポートレットを発行するためのOSGiサービスプロパティへのポートレットXML記述子の値のマップを示します。 プロパティは、ポートレット設定を一元化および簡素化します。 これらは通常、キーと値のペアとして、またはより一般的にはマップのようなオブジェクトとして表されます。

プロパティキーは、基本的にXML記述子をフラットにしたものですが、記述子の名前に似ています。

マッピングは、記述子タイプごとに編成されています。

  - [標準ポートレット記述子](#portlet-descriptor-mappings)。 プロパティキーは、プリフィックス`javax.portlet`を使用します。

  - [Liferay記述子の記述子](#liferay-descriptor-mappings)。 プロパティキーは、プリフィックス`com.liferay.portlet`を使用します。Liferay の記述子は、記述子ファイル名によってさらにグループ化されます。

      - `liferay-display.xml`
      - `liferay-portlet.xml`

標準のポートレット記述子マッピングが最初です。

## ポートレット記述子のマッピング

**注:** 簡単にするために、**ポートレットXSD** [4](#four)から派生したXPath記法を使用しています。

| portlet.xml XPath                                                                                             | OSGiポートレットサービスプロパティ                                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `/portlet-app/container-runtime-option`                                                                       | portlet-appスコープではサポートされていません                                                                                                                                       |
| `/portlet-app/custom-portlet-mode`                                                                            | サポートされていません                                                                                                                                                        |
| `/portlet-app/custom-window-state`                                                                            | サポートされていません                                                                                                                                                        |
| `/portlet-app/default-namespace`                                                                              | `javax.portlet.default-namespace=<String>`                                                                                                                   |
| `/portlet-app/event-definition`                                                                               | `javax.portlet.event-definition=<QNameLocalPart>;<QNameURI>[;<PayloadType>][,<AliasQNameLocalPart>;<AliasQNameURI>]` [2](#two)       |
| `/portlet-app/filter`<br/>`/portlet-app/filter/init-param/name`<br/>`/portlet-app/filter-mapping` | [3](#three)<br/>`javax.portlet.init-param.<name>=<value>` [3](#three), [9](#nine)<br/>[3](#three)                                          |
| `/portlet-app/public-render-parameter`                                                                        | サポートされていません                                                                                                                                                        |
| `/portlet-app/resource-bundle`                                                                                | portlet-appスコープではサポートされていません                                                                                                                                       |
| `/portlet-app/security-constraint`                                                                            | サポートされていません                                                                                                                                                        |
| `/portlet-app/user-attribute`                                                                                 | サポートされていません                                                                                                                                                        |
| `/portlet-app/version`                                                                                        | `javax.portlet.version=<value>`                                                                                                                              |
| `/portlet-app/portlet/async-supported`                                                                        | `javax.portlet.async-supported=<boolean>`                                                                                                                    |
| `/portlet-app/portlet/cache-scope`                                                                            | サポートされていません                                                                                                                                                        |
| `/portlet-app/portlet/container-runtime-option`                                                               | `javax.portlet.container-runtime-option.<name>=<value>` [2](#two)                                                                                      |
| `/portlet-app/portlet/dependency`                                                                             | `javax.portlet.dependency=<name>;<scope>;<version>` [2](#two), [6](#six)                                                                         |
| `/portlet-app/portlet/description`                                                                            | `javax.portlet.description=<String>`                                                                                                                         |
| `/portlet-app/portlet/display-name`                                                                           | `javax.portlet.display-name=<String>`                                                                                                                        |
| `/portlet-app/portlet/expiration-cache`                                                                       | `javax.portlet.expiration-cache=<int>`                                                                                                                       |
| `/portlet-app/portlet/init-param/name`                                                                        | `javax.portlet.init-param.<name>=<value>`                                                                                                              |
| `/portlet-app/portlet/listener`                                                                               | `javax.portlet.listener=<listener-class>;<ordinal>` [2](#two),[8](#eight)                                                                              |
| `/portlet-app/portlet/multipart-config/file-size-threshold`                                                   | `javax.portlet.multipart.file-size-threshold=<Integer>`                                                                                                      |
| `/portlet-app/portlet/multipart-config/location`                                                              | `javax.portlet.multipart.location=<String>`                                                                                                                  |
| `/portlet-app/portlet/multipart-config/max-file-size`                                                         | `javax.portlet.multipart.max-file-size=<Long>`                                                                                                               |
| `/portlet-app/portlet/multipart-config/max-request-size`                                                      | `javax.portlet.multipart.max-request-size=<Long>`                                                                                                            |
| `/portlet-app/portlet/portlet-class`                                                                          | [1](#one)                                                                                                                                                          |
| `/portlet-app/portlet/portlet-info/keywords`                                                                  | `javax.portlet.info.keywords=<String>`                                                                                                                       |
| `/portlet-app/portlet/portlet-info/short-title`                                                               | `javax.portlet.info.short-title=<String>`                                                                                                                    |
| `/portlet-app/portlet/portlet-info/title`                                                                     | `javax.portlet.info.title=<String>`                                                                                                                          |
| `/portlet-app/portlet/portlet-name` [10](#ten)                                                                | `javax.portlet.name=<String>` [10](#ten)                                                                                                                     |
| `/portlet-app/portlet/portlet-preferences`                                                                    | `javax.portlet.preferences=<String>`<br/>または<br/>`javax.portlet.preferences=classpath:<path_to_file_in_jar>`                               |
| `/portlet-app/portlet/portlet-preferences/preferences-validator`                                              | `javax.portlet.preferences-validator=<String>` [1](#one)                                                                                                     |
| `/portlet-app/portlet/resource-bundle`                                                                        | `javax.portlet.resource-bundle=<String>`                                                                                                                     |
| `/portlet-app/portlet/security-role-ref`                                                                      | `javax.portlet.security-role-ref=<String>[,<String>]`[2](#two)                                                                                         |
| `/portlet-app/portlet/supported-locale`                                                                       | `javax.portlet.supported-locale=<String>` [2](#two)                                                                                                          |
| `/portlet-app/portlet/supported-processing-event`                                                             | `javax.portlet.supported-processing-event=<QNameLocalPart>` または `javax.portlet.supported-processing-event=<QNameLocalPart>;<QNameURI>` [2](#two) |
| `/portlet-app/portlet/supported-public-render-parameter`                                                      | `javax.portlet.supported-public-render-parameter=<String>`[2](#two)                                                                                          |
| `/portlet-app/portlet/supported-publishing-event`                                                             | `javax.portlet.supported-publishing-event=<QNameLocalPart>` または `javax.portlet.supported-publishing-event=<QNameLocalPart>;<QNameURI>` [2](#two) |
| `/portlet-app/portlet/supports/mime-type`                                                                     | サポートされていません                                                                                                                                                        |
| `/portlet-app/portlet/supports/portlet-mode`                                                                  | `javax.portlet.portlet-mode=<mime-type>;<portlet-mode>[,<portlet-mode>]*`                                                                        |
| `/portlet-app/portlet/supports/window-state`                                                                  | `javax.portlet.window-state=<mime-type>;<window-state>[,<window-state>]*`                                                                        |

## Liferay記述子のマッピング

### Liferayディスプレイ

| liferay-display.xml XPath  | OSGiポートレットサービスプロパティ                                  |
| -------------------------- | ---------------------------------------------------- |
| `/display/category[@name]` | `com.liferay.portlet.display-category=<value>` |

### Liferayポートレット

**注:** 簡単にするために、**Liferayポートレット** [5](#five)から派生したXPath記法を使用しています。

| liferay-portlet.xml XPath                                            | OSGi Liferayポートレットサービスプロパティ                                                                                             |
| -------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| `/liferay-portlet-app/portlet/action-timeout`                        | `com.liferay.portlet.action-timeout=<int>`                                                                        |
| `/liferay-portlet-app/portlet/action-url-redirect`                   | `com.liferay.portlet.action-url-redirect=<boolean>`                                                               |
| `/liferay-portlet-app/portlet/active`                                | `com.liferay.portlet.active=<boolean>`                                                                            |
| `/liferay-portlet-app/portlet/add-default-resource`                  | `com.liferay.portlet.add-default-resource=<boolean>`                                                              |
| `/liferay-portlet-app/portlet/ajaxable`                              | `com.liferay.portlet.ajaxable=<boolean>`                                                                          |
| `/liferay-portlet-app/portlet/application-type`                      | `com.liferay.portlet.application-type=full-page-application` または `com.liferay.portlet.application-type=widget`[2](#two) |
| `/liferay-portlet-app/portlet/asset-renderer-factory`                | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/atom-collection-adapter`               | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/autopropagated-parameters`             | `com.liferay.portlet.autopropagated-parameters=<String>`[2](#two)                                                 |
| `/liferay-portlet-app/portlet/configuration-action-class`            | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/configuration-path`                    | サポートされていません                                                                                                             |
| `/liferay-portlet-app/portlet/control-panel-entry-category`          | `com.liferay.portlet.control-panel-entry-category=<String>`                                                       |
| `/liferay-portlet-app/portlet/control-panel-entry-class`             | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/control-panel-entry-weight`            | `com.liferay.portlet.control-panel-entry-weight=<double>`                                                         |
| `/liferay-portlet-app/portlet/css-class-wrapper`                     | `com.liferay.portlet.css-class-wrapper=<String>`                                                                  |
| `/liferay-portlet-app/portlet/custom-attributes-display`             | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/ddm-display`                           | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/facebook-integration`                  | サポートされていません                                                                                                             |
| `/liferay-portlet-app/portlet/footer-portal-css`                     | `com.liferay.portlet.footer-portal-css=<String>`[2](#two)                                                         |
| `/liferay-portlet-app/portlet/footer-portal-javascript`              | `com.liferay.portlet.footer-portal-javascript=<String>`[2](#two)                                                  |
| `/liferay-portlet-app/portlet/footer-portlet-css`                    | `com.liferay.portlet.footer-portlet-css=<String>`[2](#two)                                                        |
| `/liferay-portlet-app/portlet/footer-portlet-javascript`             | `com.liferay.portlet.footer-portlet-javascript=<String>`[2](#two)                                                 |
| `/liferay-portlet-app/portlet/friendly-url-mapper-class`             | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/friendly-url-mapping`                  | `com.liferay.portlet.friendly-url-mapping=<String>
`                                                              |
| `/liferay-portlet-app/portlet/friendly-url-routes`                   | `com.liferay.portlet.friendly-url-routes=<String>`                                                                |
| `/liferay-portlet-app/portlet/header-portal-css`                     | `com.liferay.portlet.header-portal-css=<String>`[2](#two)                                                         |
| `/liferay-portlet-app/portlet/header-portal-javascript`              | `com.liferay.portlet.header-portal-javascript=<String>`[2](#two)                                                  |
| `/liferay-portlet-app/portlet/header-portlet-css`                    | `com.liferay.portlet.header-portlet-css=<String>`[2](#two)                                                        |
| `/liferay-portlet-app/portlet/header-portlet-javascript`             | `com.liferay.portlet.header-portlet-javascript=<String>`[2](#two)                                                 |
| `/liferay-portlet-app/portlet/header-request-attribute-prefix`       | `com.liferay.portlet.header-request-attribute-prefix=<String>` [7](#seven)                                        |
| `/liferay-portlet-app/portlet/header-timeout`                        | `header-timeout=<int>`                                                                                            |
| `/liferay-portlet-app/portlet/icon`                                  | `com.liferay.portlet.icon=<String>`                                                                               |
| `/liferay-portlet-app/portlet/include`                               | `com.liferay.portlet.include=<boolean>`                                                                           |
| `/liferay-portlet-app/portlet/indexer-class`                         | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/instanceable`                          | `com.liferay.portlet.instanceable=<boolean>`                                                                      |
| `/liferay-portlet-app/portlet/layout-cacheable`                      | `com.liferay.portlet.layout-cacheable=<boolean>`                                                                  |
| `/liferay-portlet-app/portlet/maximize-edit`                         | `com.liferay.portlet.maximize-edit=<boolean>`                                                                     |
| `/liferay-portlet-app/portlet/maximize-help`                         | `com.liferay.portlet.maximize-help=<boolean>`                                                                     |
| `/liferay-portlet-app/portlet/open-search-class`                     | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/parent-struts-path`                    | `com.liferay.portlet.parent-struts-path=<String>`                                                                 |
| `/liferay-portlet-app/portlet/partial-action-serve-resource`         | `com.liferay.portlet.partial-action-serve-resource=<boolean>`                                                     |
| `/liferay-portlet-app/portlet/permission-propagator`                 | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/poller-processor-class`                | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/pop-message-listener-class`            | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/pop-up-print`                          | `com.liferay.portlet.pop-up-print=<boolean>`                                                                      |
| `/liferay-portlet-app/portlet/portlet-data-handler-class`            | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/portlet-dependency-css-enabled`        | `com.liferay.portlet.portlet-dependency-css-enabled=<boolean>`                                                    |
| `/liferay-portlet-app/portlet/portlet-dependency-javascript-enabled` | `com.liferay.portlet.dependency-javascript-enabled=<boolean>`                                                     |
| `/liferay-portlet-app/portlet/portlet-layout-listener-class`         | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/portlet-name`                          | サポートされていません                                                                                                             |
| `/liferay-portlet-app/portlet/portlet-url-class`                     | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/preferences-company-wide`              | `com.liferay.portlet.preferences-company-wide=<boolean>`                                                          |
| `/liferay-portlet-app/portlet/preferences-owned-by-group`            | `com.liferay.portlet.preferences-owned-by-group=<boolean>`                                                        |
| `/liferay-portlet-app/portlet/preferences-unique-per-layout`         | `com.liferay.portlet.preferences-unique-per-layout=<boolean>`                                                     |
| `/liferay-portlet-app/portlet/private-request-attributes`            | `com.liferay.portlet.private-request-attributes=<boolean>`                                                        |
| `/liferay-portlet-app/portlet/private-session-attributes`            | `com.liferay.portlet.private-session-attributes=<boolean>`                                                        |
| `/liferay-portlet-app/portlet/remoteable`                            | サポートされていません                                                                                                             |
| `/liferay-portlet-app/portlet/render-timeout`                        | `com.liferay.portlet.render-timeout=<int>`                                                                        |
| `/liferay-portlet-app/portlet/render-weight`                         | `com.liferay.portlet.render-weight=<int>`                                                                         |
| `/liferay-portlet-app/portlet/requires-namespaced-parameters`        | `com.liferay.portlet.requires-namespaced-parameters=<boolean>`                                                    |
| `/liferay-portlet-app/portlet/restore-current-view`                  | `com.liferay.portlet.restore-current-view=<boolean>`                                                              |
| `/liferay-portlet-app/portlet/scheduler-entry`                       | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/scopeable`                             | `com.liferay.portlet.scopeable=<boolean>`                                                                         |
| `/liferay-portlet-app/portlet/show-portlet-access-denied`            | `com.liferay.portlet.show-portlet-access-denied=<boolean>`                                                        |
| `/liferay-portlet-app/portlet/show-portlet-inactive`                 | `com.liferay.portlet.show-portlet-inactive=<boolean>`                                                             |
| `/liferay-portlet-app/portlet/single-page-application`               | `com.liferay.portlet.single-page-application=<boolean>`                                                           |
| `/liferay-portlet-app/portlet/social-activity-interpreter-class`     | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/social-request-interpreter-class`      | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/social-interactions-configuration`     | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/staged-model-data-handler-class`       | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/struts-path`                           | `com.liferay.portlet.struts-path=<String>`                                                                        |
| `/liferay-portlet-app/portlet/system`                                | `com.liferay.portlet.system=<boolean>`                                                                            |
| `/liferay-portlet-app/portlet/template-handler`                      | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/trash-handler`                         | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/url-encoder-class`                     | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/use-default-template`                  | `com.liferay.portlet.use-default-template=<boolean>`                                                              |
| `/liferay-portlet-app/portlet/user-notification-definitions`         | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/user-notification-handler-class`       | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/user-principal-strategy`               | `com.liferay.portlet.user-principal-strategy=<String>`                                                            |
| `/liferay-portlet-app/portlet/virtual-path`                          | `com.liferay.portlet.virtual-path=<String>`                                                                       |
| `/liferay-portlet-app/portlet/webdav-storage-class`                  | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/webdav-storage-token`                  | `webdav-storage-token=<string>` WebDavStorage OSGiサービスプロパティで宣言されています                                              |
| `/liferay-portlet-app/portlet/workflow-handler`                      | [3](#three)                                                                                                             |
| `/liferay-portlet-app/portlet/xml-rpc-method-class`                  | [3](#three)                                                                                                             |

  - \[<a name="one">1</a>\] ポートレットは具象オブジェクトとして登録されます。

  - \[<a name="two">2</a>\] これらのプロパティの複数を使用できます。 これにより、値の配列が生成されます。

  - \[<a name="three">3</a>\] このタイプはOSGiサービスとして登録されています。

  - \[<a name="four">4</a>\] [https://xmlns.jcp.org/xml/ns/portlet/portlet-app_3_0.xsd](https://xmlns.jcp.org/xml/ns/portlet/portlet-app_3_0.xsd)

  - \[<a name="five">5</a>\] [http://www.liferay.com/dtd/liferay-portlet-app\_7\_3\_0.dtd](https://docs.liferay.com/dxp/portal/7.3-latest/definitions/liferay-portlet-app_7_3_0.dtd.html)

  - \[<a name="six">6</a>\] 複数の`javax.portlet.dependency`プロパティを使用した例を以下に示します。

    *旧:*

    ``` xml
    <portlet>
        ...
        <dependency>
            <name>jquery</name>
            <scope>com.jquery</scope>
            <version>2.1.1</version>
        </dependency>
        <dependency>
            <name>jsutil</name>
            <scope>com.mycompany</scope>
            <version>1.0.0</version>
        </dependency>
        ...
    </portlet>
    ```

    *新:*

    ``` java
    @Component(
        immediate = true, property = {
            "javax.portlet.name=my_portlet",
            "javax.portlet.display-name=my-portlet",
            "javax.portlet.dependency=jquery;com.jquery;2.1.1",
            "javax.portlet.dependency=jsutil;com.mycompany;1.0.0"
        }, service = Portlet.class
    )
    public class MyPortlet extends GenericPortlet {
        ...
    } 
    ```

  - \[<a name="seven">7</a>\] `com.liferay.portlet.header-request-attribute-prefix`プロパティの例を以下に示します。

    *旧:*

    ``` xml
    <portlet>
        ...
        <header-request-attribute-prefix>com.mycompany</header-request-attribute-prefix>
        ...
    </portlet>
    ```

    *新:*

    ``` java
    @Component(
        immediate = true, property = {
            "javax.portlet.name=my_portlet",
            "javax.portlet.display-name=my-portlet",
            "javax.portlet.dependency=jquery;com.jquery;2.1.1",
            "javax.portlet.dependency=jsutil;com.mycompany;1.0.0",
            "com.liferay.portlet.header-request-attribute-prefix=com.mycompany"
        }, service = Portlet.class
    )
    public class MyPortlet extends GenericPortlet {
        ...
    } 
    ```

  - \[<a name="eight">8</a>\] `javax.portlet.listener`プロパティの例を以下に示します。

    *旧:*

    ``` xml
    <portlet>
        ...
        <listener>
            <listener-class>com.mycompany.MyPortletURLGenerationListener</listener-class>
            <ordinal>1</ordinal>
        </listener>
        ...
    </portlet>
    ```

    *新:*

    ``` java
    @Component(
        immediate = true,
        property = {"javax.portlet.name=myPortlet",
            "javax.portlet.listener=com.mycompany.MyPortletURLGenerationListener;1"
        }, service = Portlet.class
    )
    public class MyPortlet extends GenericPortlet {
        ...
    } 
    ```

  - \[<a name="nine">9</a>\] `javax.portlet.init-param`プロパティは次のように宣言できます。

    ``` java
    @Component(
        immediate = true,
        property = {"javax.portlet.name=myPortlet", 
            "javax.portlet.init-param.myInitParam=1234"},
        service = PortletFilter.class
    )
    public class MyFilter implements RenderFilter {
        ...
    }
    ```

  - \[<a name="ten">10</a>\] Liferayはポートレットの名前に基づいて、各ポートレットのIDを作成します（すなわち、`liferay-portlet.xml`の`portlet-name`記述子または`javax.portlet.name` OSGiサービスのプロパティ）。 ポートレット名にはダッシュ、ピリオド、スペースを使用できますが、これらの文字やその他のJavaScriptの安全でない文字は、ポートレットIDに使用される名前の値から取り除かれます。 したがって、削除される文字を考慮して、ポートレット名を一意なものにしてください。 そうしないと、既にデプロイ済みのポートレットと同じIDのポートレットをデプロイしようとすると、ポートレットのデプロイが失敗し、Liferayは次のメッセージを記録します。
    
        ポートレットID [portletId] は既に使用されています
