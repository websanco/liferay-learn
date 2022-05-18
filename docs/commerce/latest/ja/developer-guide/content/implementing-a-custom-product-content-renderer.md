# カスタム商品コンテンツレンダラーの実装

このチュートリアルでは、 [CPContentRenderer](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-content-api/src/main/java/com/liferay/commerce/product/content/render/CPContentRenderer.java) インターフェイスを実装して、カスタム商品コンテンツレンダラーを追加する方法を示します。

商品コンテンツレンダラーは、商品を表示できるさまざまなウィジェットで、特定の 商品タイプ の商品詳細を表示するスタイルを提供します。 Liferay Commerceは、シンプル商品用の [SimpleCPContentRenderer](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-type-simple/src/main/java/com/liferay/commerce/product/type/simple/internal/SimpleCPContentRenderer.java) など、すぐに使用できる商品タイプごとに商品コンテンツレンダラーを提供しています。

![すぐに使える商品コンテンツレンダラー](./implementing-a-custom-product-content-renderer/images/01.png "すぐに使える商品コンテンツレンダラー")

<a name="overview" />

## 概要

1. [**サンプルをデプロイする**](#deploy-an-example)
1. [**例の説明**](#walk-through-the-example)
1. [**追加情報**](#additional-information)

<a name="deploy-an-example" />

## サンプルをデプロイする

このセクションでは、商品コンテンツレンダラーをLiferay Commerceのインスタンスで実行する例を示します。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [Acme Commerce Product Content Renderer](./liferay-q4f7.zip) をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/content/liferay-q4f7.zip -O
    ```

    ```bash
    unzip liferay-q4f7.zip
    ```

1. サンプルをビルドしてデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
       このコマンドは、デプロイされたjarをDockerコンテナの ``/opt/liferay/osgi/modules``にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.q4f7.web_1.0.0
    ```

1. サンプルの商品コンテンツレンダラーが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開き、Liferay Commerceで商品を含む任意のカタログに移動します。 商品をクリックして［商品の詳細］ウィジェットを表示し、ウィジェットの ［**設定**］をクリックします。

    ［**カスタムレンダラー**］セクションで、［**Simple**］を選択します。 ［**シンプルなコマース商品タイプのレンダラーのキー**］ドロップダウンで、新しいレンダラー（"Q4F7 Commerce Product Content Renderer"）を選択します。

    ![新しい商品コンテンツレンダラー](./implementing-a-custom-product-content-renderer/images/02.png "新しい商品コンテンツレンダラー")

1. 商品を選択すると、新しいレンダラーでは、商品のSKU、価格、在庫状況、在庫数のみが表示されます。

    ![新しいレンダラーで表示された商品の詳細は以下の通りです。](./implementing-a-custom-product-content-renderer/images/03.png "新しいレンダラーによる詳細表示")

これで、`CPContentRenderer`を実装する新しいカスタム商品コンテンツレンダラーを正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

<a name="walk-through-the-example" />

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、 `CPContentRenderer`インターフェイスを確認します。 最後に、`CPContentRenderer`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

```java
@Component(
    immediate = true,
    property = {
        "commerce.product.content.renderer.key=q4f7",
        "commerce.product.content.renderer.order=1",
        "commerce.product.content.renderer.type=" + SimpleCPTypeConstants.NAME
    },
    service = CPContentRenderer.class
)
public class Q4F7CPContentRenderer implements CPContentRenderer {
```

> Liferay Commerceが商品コンテンツレンダラーレジストリ</a>で新しいレンダラーを他のレンダラーと区別できるように、商品コンテンツレンダラーに個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられているレンダラーが上書きされます。
> 
> `commerce.product.content.renderer.order`プロパティは、CommerceがUIにレンダラをリストアップするために使用する整数値です。 レンダラーは昇順で表示されます。 例えば、 [SimpleCPContentRenderer](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-type-simple/src/main/java/com/liferay/commerce/product/type/simple/internal/SimpleCPContentRenderer.java) では、このプロパティが最小整数値に設定されているため、Simpleタイプの商品の他のレンダラーはリスト内でこの後に表示されます。
> 
> `commerce.product.content.renderer.type`プロパティは、このレンダラーを使用できる商品のタイプを決定します。 この例では、Simpleタイプを使用しているため、レンダラーはUIの［Simple］カテゴリの下に表示されます。

### `CPContentRenderer`インターフェイスを確認する

次のメソッドを実装します。

```java
public String getKey();
```

> このメソッドは、商品コンテンツレンダラーレジストリ内の商品コンテンツレンダラーに一意の識別子を提供します。 このキーを使用して、レジストリからレンダラーを取得できます。 すでに使用されているキーを再利用すると、既存の関連付けられているレンダラーが上書きされます。

```java
public String getLabel(Locale locale);
```

> これは、商品コンテンツレンダラーを説明するテキストラベルを返します。 言語キーでラベルを取得する際のリファレンスについては、[Q4F7CPContentRenderer.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/content/implementing-a-custom-product-content-renderer/resources/liferay-q4f7.zip/q4f7-web/src/main/java/com/acme/q4f7/web/internal/commerce/product/content/renderer/Q4F7CPContentRenderer.java)の実装を参照してください。

```java
public void render(
        CPCatalogEntry cpCatalogEntry,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws Exception;
```

> ここで、商品コンテンツレンダラーのカスタマイズされたビューをレンダリングするコードを追加します。
> 
> ビューを定義するには、JSP、Freemarkerテンプレート、Soyテンプレートなど、いくつかの方法があります。 この例では、JSPを使用します。

### 商品コンテンツレンダラーを完成させる

商品コンテンツレンダラーは、定義およびレンダリングするカスタムビューで構成されます。 以下を行います。

* [モジュールに`ServletContext`を構成する。](#configure-the-servletcontext-for-the-module)
* [`render` メソッドを実装します。](#implement-the-render-method)
* [カスタムビューのJSPを追加する。](#add-a-jsp-for-the-custom-view)
* [言語キーを`Language.properties`に追加する。](#add-the-language-key-to-languageproperties)

#### モジュールに`ServletContext`を構成する

バンドルのシンボル名を使用して `ServletContext` を定義し、モジュールでJSPを見つけられるようにします。

```java
@Reference(target = "(osgi.web.symbolicname=com.acme.q4f7.web)")
private ServletContext _servletContext;
```

> `osgi.web.symbolicname`に設定した値は、[bnd.bndファイル](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/content/implementing-a-custom-product-content-renderer/resources/liferay-q4f7.zip/q4f7-web/bnd.bnd)の`Bundle-SymbolicName`の値と一致します。 これらの値は、JSPを見つけるために`ServletContext`と一致する必要があります。
> 
> また、 `ServletContext` が正しく生成されるように、bnd.bndファイルで `Web-ContextPath` 一意の値を宣言する必要があります。 この例では、`Web-ContextPath`を`/q4f7-web`に設定しています。 これらの値のリファレンスについては、[bnd.bnd](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/content/implementing-a-custom-product-content-renderer/resources/liferay-q4f7.zip/q4f7-web/bnd.bnd)を参照してください。

#### `render`メソッドを実装する

```java
@Override
public void render(
        CPCatalogEntry cpCatalogEntry,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws Exception {

    _jspRenderer.renderJSP(
        _servletContext, httpServletRequest, httpServletResponse,
        "/view.jsp");
}
```

> `JSPRenderer`を使用して、商品コンテンツレンダラーのJSPをレンダリングします（この場合は、 [view.jsp](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/content/implementing-a-custom-product-content-renderer/resources/liferay-q4f7.zip/q4f7-web/src/main/resources/META-INF/resources/view.jsp)）。 作成したJSPを見つけるためのパラメーターとして`ServletContext`を提供します。

#### カスタムビューのJSPを追加する

```jsp
<%
CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPSku cpSku = cpContentHelper.getDefaultCPSku(cpContentHelper.getCPCatalogEntry(request));
%>
```

> [CPContentHelper](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-content-web/src/main/java/com/liferay/commerce/product/content/web/internal/util/CPContentHelperImpl.java) は、特定の商品に関する情報を取得するクラスです。
> 
> [CPCatalogEntry](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-api/src/main/java/com/liferay/commerce/product/catalog/CPCatalogEntry.java) は、表示された商品自体を表します。 [CPSku](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-service/src/main/java/com/liferay/commerce/product/internal/catalog/CPSkuImpl.java) オブジェクトに含まれるデフォルトのSKUを持つ商品に関する詳細情報を取得します。

```jsp
<c:if test="<%= cpSku != null %>">
    SKU: <%= cpSku.getSku() %><br />
    Price: <%= cpSku.getPrice() %><br />
    Availability: <%= cpContentHelper.getAvailabilityLabel(request) %><br />
    Stock Quantity: <%= cpContentHelper.getStockQuantityLabel(request) %>
</c:if>

<liferay-util:dynamic-include key="com.liferay.commerce.product.content.web#/add_to_cart#" />
```

> Liferay Commerceの [add_to_cart_button.jsp](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-cart-content-web/src/main/resources/META-INF/resources/dynamic **include/add** to **cart** button.jsp) を使用して、「カートへ追加t」機能をビューに挿入します。

#### 言語キーを`Language.properties`に追加する

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/content/implementing-a-custom-product-content-renderer/resources/liferay-q4f7.zip/q4f7-web/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

```properties
q4f7-commerce-product-content-renderer=Q4F7 Commerce Product Content Renderer
```

> 詳細は、 [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application) を参照してください。

<a name="conclusion" />

## まとめ

　 `CPContentRenderer`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい商品コンテンツレンダラーを追加しました。

<a name="additional-information" />

## 追加情報

  - [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
