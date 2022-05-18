# カスタム為替レートプロバイダーの実装

このチュートリアルでは、 [ExchangeRateProvider](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-api/src/main/java/com/liferay/commerce/currency/util/ExchangeRateProvider.java) インターフェイスを実装して、カスタム為替レートプロバイダーを追加する方法を示します。

為替レートプロバイダーは、データソースを使用して通貨間の為替計算を実行します。 Liferay Commerceでは、すぐに使える為替レートプロバイダー、 [ECBExchangeRateProvider](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-service/src/main/java/com/liferay/commerce/currency/internal/util/ECBExchangeRateProvider.java) を提供しています。

![すぐに使える為替レートプロバイダー](./implementing-an-exchange-rate-provider/images/01.png "すぐに使える為替レートプロバイダー")

<a name="overview" />

## 概要

1. [**サンプルをデプロイする**](#deploy-an-example)
1. [**例の説明**](#walk-through-the-example)
1. [**追加情報**](#additional-information)

<a name="deploy-an-example" />

## サンプルをデプロイする

このセクションでは、為替レート プロバイダーをLiferay Commerceのインスタンスで実行する例を示します。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [Acme Commerce Exchange Rate Provider](./liferay-f2y1.zip) をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/sales/liferay-f2y1.zip -O
    ```

    ```bash
    unzip liferay-f2y1.zip
    ```

1. `liferay-f2y1`に移動します。

    ```bash
    cd liferay-f2y1
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
    STARTED com.acme.f2y1.impl_1.0.0
    ```

1. サンプルの為替レートプロバイダーが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開きます。 次に、アプリケーションメニュー（![Applications Menu](../../images/icon-applications-menu.png)）をクリックし、［**コマース**］→［**通貨**］に移動します。 新しい為替レートプロバイダー（"f2y1"）が、［**通貨レートプロバイダー**］ドロップダウンの下に表示されます。

```{note}
   Liferay Commerce 2.1以前のバージョンでは、*コントロールパネル* → *Commerce* → *設定* → *Currencies* → *Exchange Rate*に移動して為替レートを検索します。 新しい為替レートプロバイダー（"f2y1"）が、［通貨レートプロバイダー］ドロップダウンの下に表示されます。
```

![新しい為替レートプロバイダー](./implementing-an-exchange-rate-provider/images/02.png "新しい為替レートプロバイダー")

これで、`ExchangeRateProvider`を実装する新しい為替レートプロバイダーを正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

<a name="walk-through-the-example" />

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、`ExchangeRateProvider`インターフェイスを確認します。 最後に、`ExchangeRateProvider`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

```java
@Component(
    immediate = true, property = "commerce.exchange.provider.key=f2y1",
    service = ExchangeRateProvider.class
)
public class F2Y1ExchangeRateProvider implements ExchangeRateProvider {
```

> Liferay Commerceが [為替レートプロバイダーレジストリ](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-service/src/main/java/com/liferay/commerce/currency/internal/util/ExchangeRateProviderRegistryImpl.java) で新しい為替レートプロバイダーを他のプロバイダーと区別できるように、為替レートプロバイダーに個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられている為替レートプロバイダーが上書きされます。

### `ExchangeRateProvider`インターフェイスを確認する

次のメソッドを実装します。

```java
@Override
public BigDecimal getExchangeRate(
        CommerceCurrency primaryCommerceCurrency,
        CommerceCurrency secondaryCommerceCurrency)
    throws Exception;
```

> このメソッドは、通貨間の為替レートを計算するために呼び出されます。 レートに選択するデータソースは、Liferay Commerceのインスタンスで使用される可能性のある通貨を処理できる必要があります。

### 為替レートプロバイダーを完成させる

為替レートプロバイダーは、2つの通貨間の為替レートを計算するロジックで構成されています。 以下を行います。

* [為替レート計算ロジックを実装する。](#implement-the-exchange-rate-calculation-logic)

#### 為替レート計算ロジックを実装する

```java
@Override
public BigDecimal getExchangeRate(
        CommerceCurrency primaryCommerceCurrency,
        CommerceCurrency secondaryCommerceCurrency)
    throws Exception {

    return new BigDecimal(
        _getExchangeRate(secondaryCommerceCurrency) /
            _getExchangeRate(primaryCommerceCurrency));
}

private Double _getExchangeRate(CommerceCurrency commerceCurrency) {
    String code = StringUtil.toUpperCase(commerceCurrency.getCode());

    return _exchangeRates.get(code);
}

private Map<String, Double> _exchangeRates = new HashMap<String, Double>() {
    {
        put("AUD", 1.9454);
        put("BRL", 5.15262);
        put("CAD", 1.84981);
        put("CHF", 1.36562);
        put("CLP", 947.813);
        put("CNY", 9.49073);
        put("CZK", 31.0599);
        put("DKK", 9.04642);
        put("EUR", 1.21177);
        put("GBP", 1.09733);
        put("HKD", 10.9628);
        put("HUF", 390.23);
        put("IDR", 19698.8);
        put("ILS", 5.12143);
        put("INR", 98.562);
        put("JPY", 150.862);
        put("KRW", 1567.74);
        put("MXN", 26.7972);
        put("MYR", 5.72459);
        put("NOK", 11.8138);
        put("NZD", 2.05827);
        put("PHP", 73.2097);
        put("PKR", 194.073);
        put("PLN", 5.22207);
        put("RUB", 93.4562);
        put("SEK", 12.4178);
        put("SGD", 1.88797);
        put("THB", 44.6128);
        put("USD", 1.39777);
        put("ZAR", 19.3996);
    }
};
```

> この例では、為替レートのマップを使用しています。 より実用的な使用事例については、[ECBExchangeRateProvider](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-service/src/main/java/com/liferay/commerce/currency/internal/util/ECBExchangeRateProvider.java)を参照してください。 [F2Y1ExchangeRateProvider.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/sales/implementing-an-exchange-rate-provider/resources/liferay-f2y1.zip/f2y1-impl/src/main/java/com/acme/f2y1/internal/commerce/currency/util/F2Y1ExchangeRateProvider.java)にアクセスして、`_getStaticExchangeRates`および`_getRateForCode`を参照してください。
> 
> 2つの通貨に対し`CommerceCurrency`オブジェクトを使用して、通貨コードなどの必要な情報を取得します。  `CommerceCurrency`オブジェクトで使用できる他のメソッドについては、 [CommerceCurrency.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-api/src/main/java/com/liferay/commerce/currency/model/CommerceCurrency.java) と [CommerceCurrencyModel.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-api/src/main/java/com/liferay/commerce/currency/model/CommerceCurrencyModel.java) を参照してください。

<a name="conclusion" />

## まとめ

　 `ExchangeRateProvider`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい為替レートプロバイダーを追加しました。

<a name="additional-information" />

## 追加情報

* [新しい通貨の追加](../../store-management/currencies/adding-a-new-currency.md)
* [為替レートの管理](../../store-management/currencies/managing-exchange-rates.md)
