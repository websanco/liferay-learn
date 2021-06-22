# 新しい支払い方法の実装

このチュートリアルでは、[CommercePaymentMethod](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/payment/method/CommercePaymentMethod.java)インターフェイスを実装して、新しい支払い方法を追加する方法を示します。

支払方法とは、顧客が注文に対して支払う様々な方法を表します。 Liferay Commerceでは、 [Authorize.Net](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-payment-method-authorize-net/src/main/java/com/liferay/commerce/payment/method/authorize/net/internal/AuthorizeNetCommercePaymentMethod.java)、[Mercanet](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-payment-method-mercanet/src/main/java/com/liferay/commerce/payment/method/mercanet/internal/MercanetCommercePaymentMethod.java)、[Money Order](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-payment-method-money-order/src/main/java/com/liferay/commerce/payment/method/money/order/internal/MoneyOrderCommercePaymentMethod.java)、および[PayPal](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-payment-method-paypal/src/main/java/com/liferay/commerce/payment/method/paypal/internal/PayPalCommercePaymentMethod.java)を含むすぐに使える支払方法をいくつか提供しています。

![すぐに使える支払方法](./implementing-a-new-payment-method/images/01.png "すぐに使える支払方法")

## 概要

1.  [**サンプルをデプロイする**](#deploy-an-example)
2.  [**例の説明**](#walk-through-the-example)
3.  [**追加情報**](#additional-information)

## サンプルをデプロイする

このセクションでは、支払方法をLiferay Commerceのインスタンスで実行する例を示します。 次の手順を実行します：

1.  Liferay Commerceを開始します。

    ``` bash
    docker run -it -p 8080:8080 liferay/commerce:2.0.5
    ```

2.  [Acme Commerce Payment Method](./liferay-b1c3.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/liferay-b1c3.zip -O
    ```

    ``` bash
    unzip liferay-b1c3.zip
    ```

3.  サンプルをビルドしてデプロイします。

    ``` bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ``` note::
       このコマンドは、デプロイされたjarをDockerコンテナの ``/opt/liferay/osgi/modules``にコピーするのと同じです。
    ```

4.  Dockerコンテナコンソールでデプロイを確認します。

    ``` bash
    STARTED com.acme.b1c3.impl_1.0.0
    ```

5.  サンプルの支払方法が追加されたことを確認します。 ブラウザで`https://localhost:8080`を開き、*[Site Administration]* → *[Commerce]* → *[Settings]* → *[Payment Methods]*に移動します。

    ![新しい支払方法](./implementing-a-new-payment-method/images/02.png "新しい支払方法")

これで、`CommercePaymentMethod`を実装する新しい支払方法を正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、 `CommercePaymentMethod`インターフェイスを確認します。 最後に、`CommercePaymentMethod`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

``` java
@Component(
    immediate = true,
    property = "commerce.payment.engine.method.key=" + B1C3CommercePaymentMethod.KEY,
    service = CommercePaymentMethod.class
)
public class B1C3CommercePaymentMethod implements CommercePaymentMethod {

    public static final String KEY = "Example";
```

> Liferay Commerceが[支払方法レジストリ](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-payment-service/src/main/java/com/liferay/commerce/payment/internal/method/CommercePaymentMethodRegistryImpl.java)で新しい支払方法を他の支払方法と区別できるように、支払方法に個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられている支払方法が上書きされます。

### `CommercePaymentMethod`インターフェイスを確認する

次のメソッドを実装します。

``` java
    public String getDescription(Locale locale);
```

> これにより、*[Payment Methods]*管理ページの「Description」列にデータが入力されます。 言語キーで説明を取得する際のリファレンスについては、[B1C3CommercePaymentMethod.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/implementing-a-new-payment-method/resources/liferay-b1c3.zip/b1c3-impl/src/main/java/com/acme/b1c3/internal/commerce/payment/method/B1C3CommercePaymentMethod.java)の実装を参照してください。

``` java
    public String getKey();
```

> これにより、支払方法レジストリ内の支払方法に一意の識別子が提供されます。 このキーを使用して、レジストリから支払方法を取得できます。 すでに使用されているキーを再利用すると、既存の関連付けられている支払方法が上書きされます。

``` java
    public String getName(Locale locale);
```

> これにより、*[Payment Methods]*管理ページの「Name」列にデータが入力されます。 このメソッドは、`getDescription`メソッドと同様に動作します。

``` java
    public int getPaymentType();
```

> これにより、支払エンジンが特定の支払方法をどのように使用するかが識別されます。
> 
> 値`COMMERCE_PAYMENT_METHOD_TYPE_OFFLINE`を使用して、この支払方法にオンライン処理要件がないことを支払エンジンに通知します。 すぐに使用できる支払いタイプ定数は他に2つあります：`COMMERCE_PAYMENT_METHOD_TYPE_ONLINE_STANDARD`と`COMMERCE_PAYMENT_METHOD_TYPE_ONLINE_REDIRECT`です。

``` java
    public String getServletPath();
```

> オンラインの支払方法では、外部の支払いプロバイダーとの適切なインターフェイスを有効にするために、サーブレットが必要になる場合があります。 オフラインの支払方法にはサーブレットパスは必要ないため、`getServletPath`は`null`を返します。

### 支払い方法を完了する

支払方法は、支払いを処理および完了するためのバックエンドロジックと、さらに多くのオプションのカスタム動作で構成されています。 以下を行います。

  - [支払処理ロジックを実装する。](#implement-payment-processing-logic)
  - [支払完了ロジックを実装する。](#implement-payment-completion-logic)
  - [オプションのメソッドを実装する。](#implement-optional-methods)
  - [言語キーを `Language.properties`追加します。](#add-the-language-keys-to-languageproperties)

#### 支払処理ロジックを実装する

``` java
@Override
public boolean isProcessPaymentEnabled() {
    return true;
}
```

> このメソッドは、支払いを処理する支払方法に対してtrueを返す必要があります。 これにより、支払方法でサポートされる機能が支払エンジンに通知されます。

``` java
@Override
public CommercePaymentResult processPayment(
        CommercePaymentRequest commercePaymentRequest)
    throws Exception {

    return new CommercePaymentResult(
        null, commercePaymentRequest.getCommerceOrderId(),
        CommerceOrderConstants.PAYMENT_STATUS_AUTHORIZED, false, null, null,
        Collections.emptyList(), true);
}
```

> このメソッドにカスタム支払いロジックを実装します。 `CommercePaymentResult`には、支払いの処理に関連する情報を格納する必要があります。 詳細については、[CommercePaymentResult.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/payment/result/CommercePaymentResult.java)を参照してください。

#### 支払完了ロジックを実装する

``` java
@Override
public boolean isCompleteEnabled() {
    return true;
}
```

> これは、支払いを完了する支払方法に対してtrueを返す必要があります。 これにより、支払方法でサポートされる機能が支払エンジンに通知されます。

``` java
@Override
public CommercePaymentResult completePayment(
        CommercePaymentRequest commercePaymentRequest)
    throws Exception {

    return new CommercePaymentResult(
        null, commercePaymentRequest.getCommerceOrderId(),
        CommerceOrderConstants.PAYMENT_STATUS_PAID, false, null, null,
        Collections.emptyList(), true);
}
```

> このメソッドにカスタム支払完了ロジックを実装します。 `CommercePaymentResult`は、支払いプロセスの完了に関連する情報を格納するコンテナです。 詳細については、[CommercePaymentResult.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/payment/result/CommercePaymentResult.java)を参照してください。

#### オプションのメソッドを実装する

購読、定期的な支払い、払い戻しなど、追加の機能を提供するために実装できる追加のメソッドがあります。 これらは、[CommercePaymentMethod.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/payment/method/CommercePaymentMethod.java)で確認できます。 これらのメソッドはペアで提供されます。1つは有効にするメソッドで、もう1つは特定の機能を実装するメソッドです。

これらのメソッドの多くは、オンラインAPIを使用した支払方法にとって重要です。 オンライン支払方法の例については、[PayPalCommercePaymentMethod](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-payment-method-paypal/src/main/java/com/liferay/commerce/payment/method/paypal/internal/PayPalCommercePaymentMethod.java)を参照してください。

この例では、これらのオプションのメソッドをオーバーライドしていません。

#### 言語キーを追加します `Language.properties`

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/implementing-a-new-payment-method/resources/liferay-b1c3.zip/b1c3-impl/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

    example=Example
    this-is-the-example-payment-method=This is the Example Payment Method.

> 詳細は、[Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)を参照してください。

## まとめ

　 `CommercePaymentMethod`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい支払方法を追加しました。

## 追加情報

  - [Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
