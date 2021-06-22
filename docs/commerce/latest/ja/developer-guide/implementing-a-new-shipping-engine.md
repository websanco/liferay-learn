# 新しい配送エンジンの実装

このチュートリアルでは、[CommerceShippingEngine](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/model/CommerceShippingEngine.java)インターフェイスを実装して、カスタムの配送エンジンを追加する方法を示します。

配送エンジンは配送オプションを処理して、ユーザーに表示されるオプション（価格など）を決定します。 Liferay Commerceでは、[均一料金エンジン](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-shipping-engine-fixed-web/src/main/java/com/liferay/commerce/shipping/engine/fixed/web/internal/FixedCommerceShippingEngine.java)、[変動レートエンジン](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-shipping-engine-fixed-web/src/main/java/com/liferay/commerce/shipping/engine/fixed/web/internal/ByWeightCommerceShippingEngine.java)、および[FedExエンジン](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-shipping-engine-fedex/src/main/java/com/liferay/commerce/shipping/engine/fedex/internal/FedExCommerceShippingEngine.java)の3つのすぐに使える配送エンジンを提供しています。

> FedEx配送エンジンは、Commerce Enterpriseの加入者のみが利用できます。

![すぐに使える配送方法](./implementing-a-new-shipping-engine/images/01.png "すぐに使える配送方法")

## 概要

1.  [**サンプルをデプロイする**](#deploy-an-example)
2.  [**例の説明**](#walk-through-the-example)
3.  [**追加情報**](#additional-information)

## サンプルをデプロイする

このセクションでは、配送エンジンをLiferay Commerceのインスタンスで実行する例を示します。 次の手順を実行します：

1.  Liferay Commerceを開始します。

    ``` bash
    docker run -it -p 8080:8080 liferay/commerce:2.0.5
    ```

2.  [Acme Commerce Shipping Engine](./liferay-j6x8.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/liferay-j6x8.zip -O
    ```

    ``` bash
    unzip liferay-j6x8.zip
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
    STARTED com.acme.j6x8.impl_1.0.0
    ```

5.  サンプルの配送エンジンが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開き、*[Site Administration]* → *[Commerce]* → *[Settings]* → *[Shipping Methods]*に移動します。 この画面には、この配送エンジンを表す新しい配送方法（"Discounted Rate"）が表示されます。

![新しい配送方法](./implementing-a-new-shipping-engine/images/02.png "新しい配送方法")

これで、`CommerceShippingEngine`を実装する新しい配送エンジンを正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、 `CommerceShippingEngine`インターフェイスを確認します。 最後に、`CommerceShippingEngine`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

``` java
@Component(
    immediate = true,
    property = "commerce.shipping.engine.key=" + J6X8CommerceShippingEngine.KEY,
    service = CommerceShippingEngine.class
)
public class J6X8CommerceShippingEngine implements CommerceShippingEngine {

    public static final String KEY = "Example";
```

> Liferay Commerceが[配送エンジンレジストリ](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/internal/util/CommerceShippingEngineRegistryImpl.java)で新しいエンジンを他のエンジンと区別できるように、配送エンジンに個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられているエンジンが上書きされます。

### `CommerceShippingEngine`インターフェイスを確認する

次のメソッドを実装します。

``` java
public String getCommerceShippingOptionLabel(String name, Locale locale);
```

> このメソッドは、配送オプションに使用されるテキストラベルを返します。 言語キーで説明を取得する際のリファレンスについては、[J6X8CommerceShippingEngine.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/implementing-a-new-shipping-engine/resources/liferay-j6x8.zip/j6x8-impl/src/main/java/com/acme/j6x8/internal/commerce/model/J6X8CommerceShippingEngine.java)の実装を参照してください。
> 
> 詳細は、[Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)を参照してください。

``` java
public List<CommerceShippingOption> getCommerceShippingOptions(
        CommerceContext commerceContext, CommerceOrder commerceOrder,
        Locale locale)
    throws CommerceShippingEngineException;
```

> ここで、カスタム配送エンジンのビジネスロジックを追加します。 利用可能なオプションのリストを取得し、それらを顧客に提示するために必要な処理を実行する必要があります。

``` java
public String getDescription(Locale locale);
```

> これは、配送エンジンの簡単な説明を返します。 このメソッドは、`getCommerceShippingOptionLabel`メソッドと同様に動作します。

``` java
public String getName(Locale locale);
```

> これは、UIに表示する配送エンジンの名前を返します。 また、`getCommerceShippingOptionLabel`および`getDescription`メソッドと同様に動作します。

### 配送エンジンを完成させる

配送エンジンは、顧客に表示される配送オプションのリストを準備するバックエンドロジックで構成されています。 このロジックは、複数のステップに分割して実装するのが最適です。 以下を行います。

  - [利用可能な配送オプションの取得を実装する。](#implement-getting-the-available-shipping-options)
  - [アドレス制限チェックを実装する。](#implement-address-restriction-checking)
  - [オプションを処理するループを実装する。](#implement-a-loop-to-process-the-options)
  - [`getCommerceShippingOptions`から処理ロジックを呼び出す。](#call-processing-logic-from-getcommerceshippingoptions)
  - [言語キーを`Language.properties`に追加する。](#add-the-language-keys-to-languageproperties)

Liferay Commerceの[固定料金配送エンジン](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-shipping-engine-fixed-web/src/main/java/com/liferay/commerce/shipping/engine/fixed/web/internal/FixedCommerceShippingEngine.java)は、開始するのに適したベースラインとなる処理ステップを確認するのに適したリファレンスです。 このサンプルでは、同じ手順に従います。

#### 利用可能な配送オプションの取得を実装する

``` java
private List<CommerceShippingFixedOption> _getCommerceShippingFixedOptions(
    long groupId) {

    CommerceShippingMethod commerceShippingMethod =
        _commerceShippingMethodLocalService.fetchCommerceShippingMethod(
            groupId, KEY);

    if (commerceShippingMethod == null) {
        return Collections.emptyList();
    }

    return _commerceShippingFixedOptionLocalService.
        getCommerceShippingFixedOptions(
            commerceShippingMethod.getCommerceShippingMethodId(),
            QueryUtil.ALL_POS, QueryUtil.ALL_POS);
}
```

> まず、[CommerceShippingMethodLocalService](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/service/impl/CommerceShippingMethodLocalServiceImpl.java)を使用して「配送方法」（配送エンジンを表す）を取得し、次に[CommerceShippingFixedOptionLocalService](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-shipping-engine-fixed-service/src/main/java/com/liferay/commerce/shipping/engine/fixed/service/impl/CommerceShippingFixedOptionLocalServiceImpl.java)を使用して使用可能なオプションを取得します。

#### アドレス制限チェックを実装する

``` java
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
> [CommerceAddressRestrictionLocalService](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/service/impl/CommerceAddressRestrictionLocalServiceImpl.java)を使用して、オプションが注文の住所に制限されているかどうかを判断します。 アドレス情報を取得するには、`CommerceOrder`を使用します。`CommerceOrder`オブジェクトは、出荷される注文に関するすべての種類の情報を表します。 `CommerceOrder`で使用できる他のメソッドについては、[CommerceOrder.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrder.java)および[CommerceOrderModel.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrderModel.java)を参照してください。

#### オプションを処理するループを実装する

``` java
private List<CommerceShippingOption> _getCommerceShippingOptions(
        long groupId, CommerceOrder commerceOrder, Locale locale)
    throws PortalException {

    List<CommerceShippingOption> commerceShippingOptions =
        new ArrayList<>();

    List<CommerceShippingFixedOption> commerceShippingFixedOptions =
        _getCommerceShippingFixedOptions(groupId);

    for (CommerceShippingFixedOption commerceShippingFixedOption :
            commerceShippingFixedOptions) {

        if (_shippingOptionIsAddressRestricted(
            commerceOrder, commerceShippingFixedOption)) {

            continue;
        }

        String name = commerceShippingFixedOption.getName(locale);

        if (_commerceShippingHelper.isFreeShipping(commerceOrder)) {
            commerceShippingOptions.add(
                new CommerceShippingOption(name, name, BigDecimal.ZERO));
        }

        BigDecimal amount = commerceShippingFixedOption.getAmount();

        amount = amount.multiply(new BigDecimal(DISCOUNT_RATE));

        commerceShippingOptions.add(
            new CommerceShippingOption(name, name, amount));
    }

    return commerceShippingOptions;
}
```

> 次のステップでは処理中のほとんどの作業を行う（以前に定義したヘルパーメソッドを使用）ため、`getCommerceShippingOptions`メソッドからより簡単に呼び出すことができます。 この例では、処理ループの最後に追加のステップを追加して、通常請求される配送オプションの金額に割引率を掛けます。
> 
> [CommerceShippingHelper](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/internal/util/CommerceShippingHelperImpl.java)を使用して、注文を無料にする必要があるかどうかをより簡単に判断できます。

#### `getCommerceShippingOptions`から処理ロジックを呼び出す

``` java
@Override
public List<CommerceShippingOption> getCommerceShippingOptions(
        CommerceContext commerceContext, CommerceOrder commerceOrder,
        Locale locale)
    throws CommerceShippingEngineException {

    List<CommerceShippingOption> commerceShippingOptions =
        new ArrayList<>();

    try {
        commerceShippingOptions = _getCommerceShippingOptions(
            commerceContext.getSiteGroupId(), commerceOrder, locale);
    }
    catch (PortalException pe) {
        if (_log.isDebugEnabled()) {
            _log.debug(pe, pe);
        }
    }

    return commerceShippingOptions;
}
```

> 前に定義した処理ロジックを`getCommerceShippingOptions`から呼び出し 、考えられるエラーを処理します。

#### 言語キーを`Language.properties`に追加する

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/implementing-a-new-shipping-engine/resources/liferay-j6x8.zip/j6x8-impl/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

    discounted-rate=Discounted Rate
    ship-for-a-discounted-price=Ship for a discounted price.

> 詳細は、[Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)を参照してください。

## まとめ

　 `CommerceShippingEngine`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい配送エンジンを追加しました。

## 追加情報

  - [Applying Shipping Method Restrictions](../../store-administration/configuring-shipping-methods/applying-shipping-method-restrictions.md)
  - [Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
