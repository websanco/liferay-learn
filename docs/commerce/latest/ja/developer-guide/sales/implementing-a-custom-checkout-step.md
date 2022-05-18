# カスタム清算ステップの実装

このチュートリアルでは、 [CommerceCheckoutStep](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/util/CommerceCheckoutStep.java) インターフェースを実装して、カスタム清算ステップを追加する方法を紹介します。

清算ステップは、顧客の清算プロセスの1つの画面を表します。 Liferay Commerceには、 [支払方法ステップ](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-checkout-web/src/main/java/com/liferay/commerce/checkout/web/internal/util/PaymentMethodCommerceCheckoutStep.java) や [注文確認ステップ](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-checkout-web/src/main/java/com/liferay/commerce/checkout/web/internal/util/OrderConfirmationCommerceCheckoutStep.java) などの基本的なステップを含む、いくつかの清算ステップが標準で組み込まれています。

![すぐに使える清算ステップ](./implementing-a-custom-checkout-step/images/01.png "すぐに使えるチェックアウトステップ")

<a name="overview" />

## 概要

1. [**サンプルをデプロイする**](#deploy-an-example)
1. [**例の説明**](#walk-through-the-example)
1. [**追加情報**](#additional-information)

<a name="deploy-an-example" />

## サンプルをデプロイする

このセクションでは、清算ステップをLiferay Commerceのインスタンスで実行する例を示します。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [Acme Commerce Checkout Step](./liferay-n8n6.zip) をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/sales/liferay-n8n6.zip -O
    ```

    ```bash
    unzip liferay-n8n6.zip
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
    STARTED com.acme.n8n6.web_1.0.0
    ```

1. サンプルの清算ステップが追加されたことを確認します。 ブラウザーで `https://localhost:8080` を開き、Liferay Commerceサイトのカタログに移動します。 カートにアイテムを追加し、カートを表示して、「チェックアウト」をクリックします。 新しい「N8N6 Commerce Checkout Step」が清算ステップのリストに表示されます。

![新しい清算ステップ](./implementing-a-custom-checkout-step/images/02.png "新しいチェックアウトステップ")

これで、`CommerceCheckoutStep`を実装する新しい清算ステップのビルドとデプロイが完了しました。

次に、詳細をさらに詳しく見ていきましょう。

<a name="walk-through-the-example" />

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、 `CommerceCheckoutStep`インターフェイスを確認します。 最後に、`CommerceCheckoutStep`の実装を完了します。

```{note}
   `` CommerceCheckoutStep``の実装を簡略化するために、基本機能の `` BaseCommerceCheckoutStep``を拡張します。
```

### OSGi登録用のクラスに注釈を付ける

```java
@Component(
    property = {
    "commerce.checkout.step.name=n8n6",
    "commerce.checkout.step.order:Integer=21"
    },
    service = CommerceCheckoutStep.class
)
public class N8N6CommerceCheckoutStep extends BaseCommerceCheckoutStep {
```

> Liferay Commerceがこの清算ステップを既存の清算ステップと区別できるように、清算ステップ名は一意の値である必要があります。
> 
> `commerce.checkout.step.order` 値は、清算プロセスでこの清算ステップが表示される範囲を示します。 たとえば、 [配送方法清算ステップ](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-checkout-web/src/main/java/com/liferay/commerce/checkout/web/internal/util/ShippingMethodCommerceCheckoutStep.java) の値は20です。 この清算ステップに値21を指定すると、配送方法のステップの直後に表示されます。

### `CommerceCheckoutStep` インターフェイスを確認する

基本クラスを拡張することに加えて、次のメソッドを実装します。

```java
public String getName();
```

> このメソッドは、清算ステップの名前を返します。 この名前は、UIに表示される名前に対応する [言語キー](https://help.liferay.com/hc/en-us/articles/360028746692-Localizing-Your-Application) である可能性があります。

```java
public void processAction(
        ActionRequest actionRequest, ActionResponse actionResponse)
    throws Exception;
```

> バックエンド処理が必要な場合は、 `processAction` メソッドを使用して、 `ActionRequest` 介して渡された情報でビジネスロジックを実装できます。

```java
public void render(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws Exception;
```

> ここで、清算ステップに合わせてカスタマイズされた画面をレンダリングするためのコードを追加します。

### 清算ステップを完了する

清算ステップは、カスタム画面と入力を処理するバックエンドロジックで構成されます。 以下を行います。

* [モジュールに`ServletContext`を構成する。](#configure-the-servletcontext-for-the-module)
* [`render` メソッドを実装します。](#implement-the-render-method)
* [ビジネスロジックを`processAction`に追加する 。](#add-business-logic-to-processaction)
* [JSPを追加して、カスタム画面をレンダリングする。](#add-a-jsp-to-render-the-custom-screen)
* [言語キーを`Language.properties`に追加する。](#add-the-language-key-to-language-properties)

#### モジュールに`ServletContext`を構成する

バンドルのシンボル名を使用して `ServletContext` を定義し、モジュールでJSPを見つけられるようにします。

```java
@Reference(target = "(osgi.web.symbolicname=com.acme.n8n6.web)")
private ServletContext _servletContext;
```

> `osgi.web.symbolicname`に設定した値は、[bnd.bndファイル](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/sales/implementing-a-custom-checkout-step/resources/liferay-n8n6.zip/n8n6-web/bnd.bnd)の`Bundle-SymbolicName`の値と一致します。 これらの値は、JSPを見つけるために`ServletContext`と一致する必要があります。
> 
> また、 `ServletContext` が正しく生成されるように、bnd.bndファイルで `Web-ContextPath` 一意の値を宣言する必要があります。 この例では、 `Web-ContextPath` は `/ n8n6-web`設定されています。 これらの値のリファレンスについては、[bnd.bnd](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/sales/implementing-a-custom-checkout-step/resources/liferay-n8n6.zip/n8n6-web/bnd.bnd)を参照してください。

#### `render`メソッドを実装する

```java
@Override
public void render(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws Exception {

    _jspRenderer.renderJSP(
        _servletContext, httpServletRequest, httpServletResponse,
        "/terms_and_conditions.jsp");
}

@Reference
private JSPRenderer _jspRenderer;

@Reference(target = "(osgi.web.symbolicname=com.acme.n8n6.web)")
private ServletContext _servletContext;
```

> `JSPRenderer`を使用して、チェックアウトステップのJSPをレンダリングします（この場合は、 [terms\_and\_conditions.jsp](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/sales/implementing-a-custom-checkout-step/resources/liferay-n8n6.zip/n8n6-web/src/main/resources/META-INF/resources/terms_and_conditions.jsp)）。 作成したJSPを見つけるためのパラメーターとして`ServletContext`を提供します。

#### ビジネスロジックを`processAction`に追加する 。

今回の例では、カスタム画面にテキストを表示し、 `processAction` の実装ではバックエンド処理を必要としません。

#### JSPを追加して、カスタム画面をレンダリングする

この例では、プレースホルダーテキストを追加しています。 [terms\_and\_conditions.jsp](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/sales/implementing-a-custom-checkout-step/resources/liferay-n8n6.zip/n8n6-web/src/main/resources/META-INF/resources/terms_and_conditions.jsp)で実装を確認できます。

#### 言語キーを`Language.properties`に追加する

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/sales/implementing-a-custom-checkout-step/resources/liferay-n8n6.zip/n8n6-web/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

```properties
n8n6-commerce-checkout-step=N8N6 Commerce Checkout Step
```

> 詳細は、 [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application) を参照してください。

<a name="conclusion" />

## まとめ

　 これで、 `CommerceCheckoutStep`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい清算ステップを追加しました。

<a name="additional-information" />

## 追加情報

* [チェックアウト](../../creating-store-content/commerce-storefront-pages/checkout.md)
* [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
