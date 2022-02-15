# MVCの使用

```{toctree}
:maxdepth: 3

using-mvc/using-a-jsp-and-mvc-portlet.md
using-mvc/rendering-views-with-mvc-portlet.md
using-mvc/invoking-actions-with-mvc-portlet.md
using-mvc/mvc-action-command.md
using-mvc/mvc-render-command.md
using-mvc/mvc-resource-command.md
using-mvc/portlet-preferences.md
using-mvc/using-localized-messages-in-an-mvc-portlet.md
using-mvc/sharing-localized-messages.md
```

あなたが経験豊富な開発者であれば、Model View Controllerについて聞いたのはこれが初めてではありません。 JavaでMVCフレームワークの実装が非常に多い場合、Liferayがさらに別の実装を作成したのはなぜですか？ Liferay MVCポートレットには次の利点があります。

* 他の多くのJava MVCフレームワークとは対照的に、軽量です。
* コードとの同期を維持する必要のある特別な構成ファイルはありません。
* これは [`GenericPortlet`](https://learn.liferay.com/reference/latest/en/portlet-api/javax/portlet/GenericPortlet.html) の拡張です。
* LiferayのMVCポートレットフレームワークは、`init()`メソッドが呼び出されたときにいくつかの事前定義されたパラメーターのみを検索するため、大量の定型コードの記述を回避できます。
* コントローラーは、MVCコマンドクラスに分類できます。各クラスは、特定のポートレットポートレットフェーズ</a>（レンダリング、アクション、およびリソース提供フェーズ）のコントローラーコードを処理します。
* MVCコマンドクラスは複数のポートレットにサービスを提供できます。
* Liferayのポートレットはそれを使用します。 つまり、Liferayアプリケーションを設計またはトラブルシューティングする必要がある場合に参照する堅牢な実装がたくさんあります。

Liferay MVCポートレットフレームワークは軽量で使いやすいです。

ここでは、次のトピックを取り上げて、MVCポートレットがどのように機能するかを学習します。

* [MVCレイヤーとモジュール性](#mvc-layers-and-modularity)
* [Liferay MVCコマンドクラス](#liferay-mvc-command-classes)
* [Liferay MVCポートレットコンポーネント](#liferay-mvc-portlet-component)
* [シンプルなMVCポートレット](#a-simpler-mvc-portlet)

Liferay MVCポートレットフレームワークの各レイヤーがアプリケーションの懸念事項の分離にどのように役立つかを検討してください。

<a name="mvcレイヤーとモジュール性" />

## MVCレイヤーとモジュール性

MVCには、3つのレイヤーがあります。

**モデル：** モデル層は、アプリケーションデータとそれを操作するためのロジックを保持します。

**ビュー：** ビューレイヤーには、データが表示されます。

**コントローラー：** MVCパターンの中間者であるコントローラーは、ビューレイヤーとモデルレイヤーの間でデータをやり取りします。

Liferay DXPのアプリケーションは、複数の個別の[モジュール](../../liferay-internals/architecture/osgi-and-modularity.md)に分割されています。  [Service Builder](../data-frameworks/service-builder.md)を使用すると、モデルレイヤーが`service`モジュールと`api` [モジュール](../../liferay-internals/fundamentals/module-projects.md)に生成されます。 ビューレイヤーとコントローラーレイヤーは、モジュールである`web`モジュールを共有します。

[Workspace](../tooling/liferay-workspace/creating-code-with-liferay-workspace.md) を使用してマルチモジュールのService Builder駆動型MVCアプリケーション[プロジェクト](../../liferay-internals/fundamentals/module-projects.md)のスケルトンを生成すると、時間を大幅に節約でき、より重要な（そして興味深い）開発作業を開始できます。

<a name="liferay-mvcコマンドクラス" />

## Liferay MVCコマンドクラス

大規模なアプリケーションでは、すべてのコントローラーロジックを保持している場合、 `-Portlet` クラスは巨大で扱いにくいものになります。 Liferayは、コントローラー機能を分割するMVCコマンドクラスを提供します。

* [`MVCActionCommand`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)： `-ActionCommand` クラスを使用して、アクションURLによって呼び出される各ポートレットアクションを保持します。
* [`MVCRenderCommand`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)： `-RenderCommand` クラスを使用して、レンダリングURLに応答することにより、適切なJSPにディスパッチする `render` メソッドを保持します。
* [`MVCResourceCommand`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)： `-ResourceCommand` クラスを使用して、リソースURLに基づいてリソースを提供します。

すべてをつなぎ合わせて適切に機能させるには、混乱を招く構成ファイルが必要です。 いいえ、そうではありません。`-Portlet`クラスの`@Component`アノテーションですべて簡単に管理できます。

<a name="liferay-mvcポートレットコンポーネント" />

## Liferay MVCポートレットコンポーネント

コントローラーをMVCコマンドクラスに分割する予定があるかどうかに関係なく、ポートレットの [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) アノテーションはポートレットを構成します。 例として、簡単なポートレットコンポーネントを次に示します。

```java
@Component(
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "javax.portlet.display-name=Hello World Portlet",
        "javax.portlet.init-param.view-template=/view.jsp",
        "javax.portlet.name=com_acme_hello_world_web_internal_portlet_HelloWorldPortlet"
    },
    service = Portlet.class
)
public class HelloWorldPortlet extends MVCPortlet {
```

`javax.portlet.name`プロパティは必須です。 MVCコマンドを使用する場合、`javax.portlet.name`プロパティ値は、特定のポートレットURL /コマンドの組み合わせを正しいポートレットにリンクします。

```{important}
[Liferay DXPがその名前を使用して[ポートレットのID]を作成する方法(../reference/portlet-descriptor-to-osgi-service-property-map.md#ten)を考慮して、ポートレット名を一意にします。
```

コンポーネントを使用して公開している `Portlet.class` 実装の種類について、混乱が生じる可能性があります。 サービスレジストリは、これが [`javax.portlet.Portlet`](https://learn.liferay.com/reference/latest/en/portlet-api/javax/portlet/Portlet.html) インターフェイスであると想定しています。 たとえば、 `com.liferay.portal.kernel.model.Portlet`ではなく、それをインポートします。

```{note}
DTD [liferay-portlet-app_7_3_0.dtd](https://learn.liferay.com/reference/latest/en/dxp/definitions/liferay-portlet-app_7_3_0.dtd.html) は、ポートレットコンポーネントのプロパティとして指定できるすべてのLiferay固有の属性を定義します。 `javax.portlet.`で名前空間が設定されたプロパティは、 [`portlet.xml` descriptor](https://docs.liferay.com/portlet-api/3.0/portlet-app_3_0.xsd) の要素です。
```

<a name="よりシンプルなmvcポートレット" />

## よりシンプルなMVCポートレット

より単純なアプリケーションでは、MVCコマンドを使用しません。 ポートレットのレンダーURLは`mvcPath`パラメーターでJSPパスを指定し、 [`MVCPortlet`](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html) メソッドは実装制御ロジックをオーバーライドします。 次のJSPコードには、JSPパス`/view_2.jsp`を指定するポートレットレンダーURLが含まれています。

```jsp
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:renderURL var="view2URL">
    <portlet:param name="mvcPath" value="/view_2.jsp" />
</portlet:renderURL>

<a href="<%= view2URL %>">Go to View 2</a>
```

ユーザーがリンクをクリックすると、ポートレットは`mvcPath`リクエストパラメータを受け取り、その`render`メソッドで制御ロジックを処理します。 詳細については、[Rendering Views with MVC Portlet](./using-mvc/rendering-views-with-mvc-portlet.md)を参照してください。

<a name="次のステップ" />

## 次のステップ

これまで見てきたように、LiferayのMVCポートレットフレームワークは、適切に構造化されたコントローラーレイヤーを提供します。 MVCポートレットの基本を学びたい場合は、次のチュートリアルから始めてください。

* [Using a JSP and MVC Portlet](./using-mvc/using-a-jsp-and-mvc-portlet.md)
* [Rendering Views with MVC Portlet](./using-mvc/rendering-views-with-mvc-portlet.md)
* [Invoking Actions with MVC Portlet](./using-mvc/invoking-actions-with-mvc-portlet.md)

個別のMVCコマンドクラスで制御ロジックを開発する場合は、次の記事をお読みください。

* [MVC Action Command](./using-mvc/mvc-action-command.md)
* [MVC Render Command](./using-mvc/mvc-render-command.md)
* [MVC Resource Command](./using-mvc/mvc-resource-command.md)

モデルレイヤーを開発する準備ができている場合は、[Service Builder](../data-frameworks/service-builder.md)をご覧ください。

<a name="追加情報" />

## 追加情報

* [ポートレットのルック&フィール](./using-mvc/portlet-preferences.md)
* [Using Localized Messages in an MVC Portlet](./using-mvc/using-localized-messages-in-an-mvc-portlet.md)
* [モジュールプロジェクト](../../liferay-internals/fundamentals/module-projects.md)
* [ワークスペース](../tooling/liferay-workspace/creating-code-with-liferay-workspace.md)
* [基礎](../../liferay-internals/fundamentals.md)
* [建築](../../liferay-internals/architecture.md)
