# カスタム為替レートプロバイダーの実装

このチュートリアルでは、[ExchangeRateProvider](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-currency-api/src/main/java/com/liferay/commerce/currency/util/ExchangeRateProvider.java)インターフェイスを実装して、カスタム為替レートプロバイダーを追加する方法を示します。

為替レートプロバイダーは、データソースを使用して通貨間の為替計算を実行します。 Liferay Commerceでは、すぐに使える為替レートプロバイダー、[ECBExchangeRateProvider](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-currency-service/src/main/java/com/liferay/commerce/currency/internal/util/ECBExchangeRateProvider.java)を提供しています。

![すぐに使える為替レートプロバイダー](./implementing-an-exchange-rate-provider/images/01.png "すぐに使える為替レートプロバイダー")

## 概要

1.  [**サンプルをデプロイする**](#deploy-an-example)
2.  [**例の説明**](#walk-through-the-example)
3.  [**追加情報**](#additional-information)

## サンプルをデプロイする

このセクションでは、為替レート プロバイダーをLiferay Commerceのインスタンスで実行する例を示します。 次の手順を実行します：

1.  Liferay Commerceを開始します。

    ``` bash
    docker run -it -p 8080:8080 liferay/commerce:2.0.5
    ```

2.  [Acme Commerce Exchange Rate Provider](./liferay-f2y1.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/liferay-f2y1.zip -O
    ```

    ``` bash
    unzip liferay-f2y1.zip
    ```

3.  `liferay-f2y1`に移動します。

    ``` bash
    cd liferay-f2y1
    ```

4.  サンプルをビルドしてデプロイします。

    ``` bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ``` note::
       このコマンドは、デプロイされたjarをDockerコンテナの ``/opt/liferay/osgi/modules``にコピーするのと同じです。
    ```

5.  Dockerコンテナコンソールでデプロイを確認します。

    ``` bash
    STARTED com.acme.f2y1.impl_1.0.0
    ```

6.  サンプルの為替レートプロバイダーが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開き、*[Control Panel]* → *[Commerce]* → *[Settings]* → *[Currencies]* → *[Exchange Rate]*に移動します。 新しい為替レートプロバイダー（"Example"）が、*[Exchange Rate Provider]*ドロップダウンの下に表示されます。

![新しい為替レートプロバイダー](./implementing-an-exchange-rate-provider/images/02.png "新しい為替レートプロバイダー")

これで、`ExchangeRateProvider`を実装する新しい為替レートプロバイダーを正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、`ExchangeRateProvider`インターフェイスを確認します。 最後に、`ExchangeRateProvider`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

``` java
@Component(
    immediate = true,
    property = "commerce.exchange.provider.key=" + F2Y1ExchangeRateProvider.KEY,
    service = ExchangeRateProvider.class
)
public class F2Y1ExchangeRateProvider implements ExchangeRateProvider {

    public static final String KEY = "Example";
```

> Liferay Commerceが[為替レートプロバイダーレジストリ](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-currency-service/src/main/java/com/liferay/commerce/currency/internal/util/ExchangeRateProviderRegistryImpl.java)で新しい為替レートプロバイダーを他のプロバイダーと区別できるように、為替レートプロバイダーに個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられている為替レートプロバイダーが上書きされます。

### `ExchangeRateProvider`インターフェイスを確認する

次のメソッドを実装します。

``` java
@Override
public BigDecimal getExchangeRate(
        CommerceCurrency primaryCommerceCurrency,
        CommerceCurrency secondaryCommerceCurrency)
    throws Exception;
```

> このメソッドは、通貨間の為替レートを計算するために呼び出されます。 レートに選択するデータソースは、Liferay Commerceのインスタンスで使用される可能性のある通貨を処理できる必要があります。

### 為替レートプロバイダーを完成させる

為替レートプロバイダーは、2つの通貨間の為替レートを計算するロジックで構成されています。 以下を行います。

  - [為替レート計算ロジックを実装する。](#implement-the-exchange-rate-calculation-logic)

#### 為替レート計算ロジックを実装する

``` java
@Override
public BigDecimal getExchangeRate(
        CommerceCurrency primaryCommerceCurrency,
        CommerceCurrency secondaryCommerceCurrency)
    throws Exception {

    String primaryCurrencyCode = primaryCommerceCurrency.getCode();
    String secondaryCurrencyCode = secondaryCommerceCurrency.getCode();

    primaryCurrencyCode = StringUtil.toUpperCase(primaryCurrencyCode);
    secondaryCurrencyCode = StringUtil.toUpperCase(secondaryCurrencyCode);

    JSONArray exchangeRatesArray = _getStaticExchangeRates();

    List<String> codesList = JSONUtil.toStringList(
        exchangeRatesArray, "code");

    double primaryRate = _getRateForCode(
        exchangeRatesArray, codesList, primaryCurrencyCode);
    double secondaryRate = _getRateForCode(
        exchangeRatesArray, codesList, secondaryCurrencyCode);

    return new BigDecimal(secondaryRate/primaryRate);
}
```

> この例では、データソースとして静的な為替レートのリストを持つデータファイル、[f2y1-exchange-rates.json](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/2.x/en/developer-guide/implementing-an-exchange-rate-provider/liferay-f2y1.zip/f2y1-impl/src/main/resources/com/acme/f2y1/internal/commerce/exchange/rates/f2y1-exchange-rates.json)を使用しています。 より実用的な使用事例については、[ECBExchangeRateProvider](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-currency-service/src/main/java/com/liferay/commerce/currency/internal/util/ECBExchangeRateProvider.java)を参照してください。 [F2Y1ExchangeRateProvider.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/implementing-an-exchange-rate-provider/resources/liferay-f2y1.zip/f2y1-impl/src/main/java/com/acme/f2y1/internal/commerce/currency/util/F2Y1ExchangeRateProvider.java)にアクセスして、`_getStaticExchangeRates`および`_getRateForCode`を参照してください。
> 
> 2つの通貨に対し`CommerceCurrency`オブジェクトを使用して、通貨コードなどの必要な情報を取得します。 `CommerceCurrency`オブジェクトで使用できる他のメソッドについては、[CommerceCurrency.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-currency-api/src/main/java/com/liferay/commerce/currency/model/CommerceCurrency.java)および[CommerceCurrencyModel.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-currency-api/src/main/java/com/liferay/commerce/currency/model/CommerceCurrencyModel.java)を参照してください。

## まとめ

　 `ExchangeRateProvider`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい為替レートプロバイダーを追加しました。

## 追加情報

  - [Adding a New Currency](../../store-administration/currencies/adding-a-new-currency.md)
  - [Managing Exchange Rates](../../store-administration/currencies/managing-exchange-rates.md)
