# 新しい配送エンジンの実装

このチュートリアルでは、 [CommerceShippingEngine](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/model/CommerceShippingEngine.java) インターフェイスを実装して、カスタムの配送エンジンを追加する方法を示します。

配送エンジンは配送オプションを処理して、ユーザーに表示されるオプション（価格など）を決定します。 Liferay Commerceには標準で次の3つの配送エンジンが組み込まれています： [flat rate engine](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-shipping-engine-fixed-web/src/main/java/com/liferay/commerce/shipping/engine/fixed/web/internal/FixedCommerceShippingEngine.java) 、 [variable rate engine](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-shipping-engine-fixed-web/src/main/java/com/liferay/commerce/shipping/engine/fixed/web/internal/ByWeightCommerceShippingEngine.java) 、 [FedEx engine](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-shipping-engine-fedex/src/main/java/com/liferay/commerce/shipping/engine/fedex/internal/FedExCommerceShippingEngine.java)
> FedEx配送エンジンは、Commerce Enterpriseの加入者のみが利用できます。

![すぐに使える配送方法](./implementing-a-new-shipping-engine/images/01.png "すぐに使える配送方法")

<a name="overview" />

## 概要

1. [**サンプルをデプロイする**](#deploy-an-example)
1. [**例の説明**](#walk-through-the-example)
1. [**追加情報**](#additional-information)

<a name="deploy-an-example" />

## サンプルをデプロイする

このセクションでは、配送エンジンをLiferay Commerceのインスタンスで実行する例を示します。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [Acme Commerce Shipping Engine](./liferay-j6x8.zip) をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/sales/liferay-j6x8.zip -O
    ```

    ```bash
    unzip liferay-j6x8.zip
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
    STARTED com.acme.j6x8.impl_1.0.0
    ```

1. サンプルの配送エンジンが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開きます。 次に、アプリケーションメニュー（![Applications Menu](../images/icon-applications-menu.png)）をクリックし、［**コマース**］→［**チャネル**］に移動します。 配送方法のセクションには、当社の配送エンジンを代表する新しい配送方法（「割引レート」）が表示されます。

```{note}
   Commerce 2.1以前のバージョンでは、*サイト管理* → *Commerce* → *設定* → *配送方法*に移動して、配送方法を確認します。
```

![新しい配送方法](./implementing-a-new-shipping-engine/images/02.png "新しい配送方法")

これで、`CommerceShippingEngine`を実装する新しい配送エンジンを正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

<a name="walk-through-the-example" />

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、 `CommerceShippingEngine`インターフェイスを確認します。 最後に、`CommerceShippingEngine`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

```java
@Component(
    property = "commerce.shipping.engine.key=j6x8",
    service = CommerceShippingEngine.class
)
public class J6X8CommerceShippingEngine implements CommerceShippingEngine {
```

> Liferay Commerceが、 [配送エンジンレジストリ](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/internal/util/CommerceShippingEngineRegistryImpl.java) で新しいエンジンを他のエンジンと区別できるように、配送エンジンに個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられているエンジンが上書きされます。

### `CommerceShippingEngine`インターフェイスを確認する

次のメソッドを実装します。

```java
public String getCommerceShippingOptionLabel(String name, Locale locale);
```

> このメソッドは、配送オプションに使用されるテキストラベルを返します。 言語キーで説明を取得する際のリファレンスについては、[J6X8CommerceShippingEngine.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/sales/implementing-a-new-shipping-engine/resources/liferay-j6x8.zip/j6x8-impl/src/main/java/com/acme/j6x8/internal/commerce/model/J6X8CommerceShippingEngine.java)の実装を参照してください。
> 
> 詳細は、 [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application) を参照してください。

```java
public List<CommerceShippingOption> getCommerceShippingOptions(
        CommerceContext commerceContext, CommerceOrder commerceOrder,
        Locale locale)
    throws CommerceShippingEngineException;
```

> ここで、カスタム配送エンジンのビジネスロジックを追加します。 利用可能なオプションのリストを取得し、それらを顧客に提示するために必要な処理を実行する必要があります。

```java
public String getDescription(Locale locale);
```

> これは、配送エンジンの簡単な説明を返します。 このメソッドは、`getCommerceShippingOptionLabel`メソッドと同様に動作します。

```java
public String getName(Locale locale);
```

> これは、UIに表示する配送エンジンの名前を返します。 また、`getCommerceShippingOptionLabel`および`getDescription`メソッドと同様に動作します。

### 配送エンジンを完成させる

配送エンジンのメソッド `getCommerceShippingOptions` は、顧客に表示する配送オプションのリストを返します。

```java
@Override
public List<CommerceShippingOption> getCommerceShippingOptions(
        CommerceContext commerceContext, CommerceOrder commerceOrder,
        Locale locale)
    throws CommerceShippingEngineException {

    try {
        CommerceShippingMethod commerceShippingMethod =
            _commerceShippingMethodLocalService.fetchCommerceShippingMethod(
                commerceOrder.getScopeGroupId(), "j6x8");

        if (commerceShippingMethod == null) {
            return Collections.emptyList();
        }

        List<CommerceShippingOption> commerceShippingOptions =
            new ArrayList<>();

        List<CommerceShippingFixedOption> commerceShippingFixedOptions =
            _commerceShippingFixedOptionLocalService.
                getCommerceShippingFixedOptions(
                    commerceShippingMethod.getCommerceShippingMethodId(),
                    QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        for (CommerceShippingFixedOption commerceShippingFixedOption :
                commerceShippingFixedOptions) {

            CommerceAddress commerceAddress =
                commerceOrder.getShippingAddress();

            if (_commerceAddressRestrictionLocalService.
                    isCommerceShippingMethodRestricted(
                        commerceShippingFixedOption.
                            getCommerceShippingMethodId(),
                        commerceAddress.getCommerceCountryId())) {

                continue;
            }

            String name = commerceShippingFixedOption.getName(locale);

            if (_commerceShippingHelper.isFreeShipping(commerceOrder)) {
                commerceShippingOptions.add(
                    new CommerceShippingOption(
                        name, name, BigDecimal.ZERO));
            }

            BigDecimal amount = commerceShippingFixedOption.getAmount();

            amount = amount.multiply(BigDecimal.valueOf(0.75));

            commerceShippingOptions.add(
                new CommerceShippingOption(name, name, amount));
        }

        return commerceShippingOptions;
    }
    catch (Exception exception) {
        throw new CommerceShippingEngineException(exception);
    }
}
```

この方法では、まず利用可能な配送オプションを取得し、それらをループさせて各配送オプションを処理します。

```java
CommerceShippingMethod commerceShippingMethod =
    _commerceShippingMethodLocalService.fetchCommerceShippingMethod(
        commerceOrder.getScopeGroupId(), "j6x8");

if (commerceShippingMethod == null) {
    return Collections.emptyList();
}

List<CommerceShippingOption> commerceShippingOptions =
    new ArrayList<>();

List<CommerceShippingFixedOption> commerceShippingFixedOptions =
    _commerceShippingFixedOptionLocalService.
        getCommerceShippingFixedOptions(
            commerceShippingMethod.getCommerceShippingMethodId(),
            QueryUtil.ALL_POS, QueryUtil.ALL_POS);

for (CommerceShippingFixedOption commerceShippingFixedOption :
        commerceShippingFixedOptions) {

    //The shipping option processing logic goes here.
```

> まず、 [CommerceShippingMethodLocalService](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/service/impl/CommerceShippingMethodLocalServiceImpl.java) を使用して「配送方法」（配送エンジンを表す）を取得し、次に [CommerceShippingFixedOptionLocalService](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-shipping-engine-fixed-service/src/main/java/com/liferay/commerce/shipping/engine/fixed/service/impl/CommerceShippingFixedOptionLocalServiceImpl.java) を使用して利用可能なオプションを取得します。

> その後、配送方法をループさせて処理します。

配送オプションの処理手順は次のとおりです。

* [アドレス制限チェックを実装する。](#implement-address-restriction-checking)
* [送料無料の有無を確認する。](#check-for-free-shipping)
* [カスタム配送オプションロジックを追加する。](#add-custom-shipping-option-logic)
* [処理済みの配送オプションを追加する。](#add-the-processed-shipping-option)

Liferay Commerceの [固定料金配送エンジン](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-shipping-engine-fixed-web/src/main/java/com/liferay/commerce/shipping/engine/fixed/web/internal/FixedCommerceShippingEngine.java) は、開始するのに適したベースラインとなる処理ステップを確認するのに適したリファレンスです。 この例では、同様の手順を採用しています。

#### アドレス制限チェックを実装する

```java
private boolean _shippingOptionIsAddressRestricted(
        CommerceOrder commerceOrder,
        CommerceShippingFixedOption commerceShippingFixedOption)
    throws PortalException {

    CommerceAddress commerceAddress = commerceOrder.getShippingAddress();

    return _commerceAddressRestrictionLocalService.
        isCommerceShippingMethodRestricted(
            commerceShippingFixedOption.getCommerceShippingMethodId(),
            commerceAddress.getCommerceCountryId());
}
```

> 次のステップでは、特定の配送オプションが注文の配送先住所に対して制限されているかどうかを判断します。 制限されたオプションは、選択するオプションとして表示されません。
> 
> [CommerceAddressRestrictionLocalService](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/service/impl/CommerceAddressRestrictionLocalServiceImpl.java) を使用して、オプションが注文の住所に制限されているかどうかを判断します。 アドレス情報を取得するには、`CommerceOrder`を使用します。`CommerceOrder`オブジェクトは、出荷される注文に関するすべての種類の情報を表します。 `CommerceOrder`で使用できる他のメソッドについては、 [CommerceOrder.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrder.java) と [CommerceOrderModel.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrderModel.java) を参照してください。

#### 送料無料の有無を確認する。

```java
String name = commerceShippingFixedOption.getName(locale);

if (_commerceShippingHelper.isFreeShipping(commerceOrder)) {
    commerceShippingOptions.add(
        new CommerceShippingOption(
            name, name, BigDecimal.ZERO));
}
```

> [CommerceShippingHelper](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/internal/util/CommerceShippingHelperImpl.java) を使用して、注文を無料にする必要があるかどうかをより簡単に判断できます。

#### カスタム配送オプションロジックを追加する。

```java
BigDecimal amount = commerceShippingFixedOption.getAmount();

amount = amount.multiply(BigDecimal.valueOf(0.75));
```

> ここでは、配送オプションのロジックを追加することができます。 この例の配送オプションでは、標準の固定配送オプションの金額が25％減額されます。

#### 処理済みの配送オプションを追加する。

```java
commerceShippingOptions.add(
    new CommerceShippingOption(name, name, amount));
} // end of shipping option processing loop

return commerceShippingOptions;
```

> その金額を配送オプションに適用し、配送オプションをリストに追加します。 その後、forループを閉じて、配送オプションのリストを返します。

### 言語キーを `Language.properties`に追加します。

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/sales/implementing-a-new-shipping-engine/resources/liferay-j6x8.zip/j6x8-impl/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

```properties
discounted-rate=Discounted Rate
ship-for-a-discounted-price=Ship for a discounted price.
```

> 詳細は、 [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application) を参照してください。

<a name="conclusion" />

## まとめ

　 `CommerceShippingEngine`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい配送エンジンを追加しました。

<a name="additional-information" />

## 追加情報

* [配送方法の制限の適用](../../store-management/configuring-shipping-methods/applying-shipping-method-restrictions.md)
* [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
