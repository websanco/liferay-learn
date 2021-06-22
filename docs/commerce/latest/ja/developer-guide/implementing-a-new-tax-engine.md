# 新しい税エンジンの実装

このチュートリアルでは、[CommerceTaxEngine](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/tax/CommerceTaxEngine.java)インターフェイスを実装して、新しい税エンジンを追加する方法を示します。

税エンジンは、取引が行われるときに税金の計算を実行します。 Liferay Commerceでは、デフォルトで2つの税エンジン、[FixedCommerceTaxEngine](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-tax-engine-fixed-service/src/main/java/com/liferay/commerce/tax/engine/fixed/internal/engine/FixedCommerceTaxEngine.java)（固定料金用）と[ByAddressCommerceTaxEngine](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-tax-engine-fixed-service/src/main/java/com/liferay/commerce/tax/engine/fixed/internal/engine/ByAddressCommerceTaxEngine.java)（住所別に税金を計算する用）を提供しています。

![すぐに使える税エンジン](./implementing-a-new-tax-engine/images/01.png "すぐに使える税エンジン")

## 概要

1.  [**サンプルをデプロイする**](#deploy-an-example)
2.  [**例の説明**](#walk-through-the-example)
3.  [**追加情報**](#additional-information)

## サンプルをデプロイする

このセクションでは、税エンジンをLiferay Commerceのインスタンスで実行する例を示します。 次の手順を実行します：

1.  Liferay Commerceを開始します。

    ``` bash
    docker run -it -p 8080:8080 liferay/commerce:2.0.5
    ```

2.  [Acme Commerce Tax Engine](./liferay-q4b9.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/liferay-q4b9.zip -O
    ```

    ``` bash
    unzip liferay-q4b9.zip
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
    STARTED com.acme.q4b9.impl_1.0.0
    ```

5.  サンプルの税エンジンが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開き、*[Site Administration]* → *[Commerce]* → *[Settings]* → *[Taxes]* → *[Tax Calculations]*に移動します。 新しい税エンジン（"Flat Tax"）がリストに表示されます。

![新しい税エンジン](./implementing-a-new-tax-engine/images/02.png "新しい税エンジン")

これで、`CommerceTaxEngine`を実装する新しい税エンジンを正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、 `CommerceTaxEngine`インターフェイスを確認します。 最後に、`CommerceTaxEngine`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

``` java
@Component(
    immediate = true,
    property = "commerce.tax.engine.key=" + Q4B9CommerceTaxEngine.KEY,
    service = CommerceTaxEngine.class
)
public class Q4B9CommerceTaxEngine implements CommerceTaxEngine {

    public static final String KEY = "Example";
```

> Liferay Commerceが[税エンジンレジストリ](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/internal/util/CommerceTaxEngineRegistryImpl.java)で新しいエンジンを他のエンジンと区別できるように、税エンジンに個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられている税エンジンが上書きされます。

### `CommerceTaxEngine`インターフェイスを確認する

次のメソッドを実装します。

``` java
public CommerceTaxValue getCommerceTaxValue(
        CommerceTaxCalculateRequest commerceTaxCalculateRequest)
    throws CommerceTaxEngineException;
```

> このメソッドでは、税エンジンのビジネスロジックが実装されます。 詳細については、[CommerceTaxValue](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/tax/CommerceTaxValue.java)を参照してください。

``` java
public String getDescription(Locale locale);
```

> これは、税エンジンの簡単な説明を返します。 言語キーで説明を取得する際のリファレンスについては、[Q4B9CommerceTaxEngine.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/implementing-a-new-tax-engine/resources/liferay-q4b9.zip/q4b9-impl/src/main/java/com/acme/q4b9/internal/commerce/tax/Q4B9CommerceTaxEngine.java)の実装を参照してください。

``` java
public String getName(Locale locale);
```

> これは、税エンジンの名前を返します。 このメソッドは、`getDescription`メソッドと同様に動作します。

### 税エンジンを完成させる

税エンジンは、税計算を実行するロジックで構成されています。 以下を行います。

  - [ビジネスロジックを`getCommerceTaxValue`に追加する 。](#add-business-logic-to-getcommercetaxvalue)
  - [言語キーを `Language.properties`追加します。](#add-the-language-keys-to-languageproperties)

#### ビジネスロジックを`getCommerceTaxValue`に追加する

``` java
@Override
public CommerceTaxValue getCommerceTaxValue(
        CommerceTaxCalculateRequest commerceTaxCalculateRequest)
    throws CommerceTaxEngineException {

    BigDecimal flatTaxValue = new BigDecimal("1.50");

    if (commerceTaxCalculateRequest.isPercentage()) {
        flatTaxValue = flatTaxValue.divide(new BigDecimal(100.0));

        flatTaxValue = flatTaxValue.multiply(
            commerceTaxCalculateRequest.getPrice());
    }

    return new CommerceTaxValue("flat-tax", KEY, flatTaxValue);
}
```

> `CommerceTaxCalculateRequest`パラメータには、計算に必要な情報が含まれています。 この例では、`CommerceTaxCalculateRequest`からの価格と、レートをパーセンテージとして適用するかどうかを示す値を使用します。 `CommerceTaxCalculateRequest`で使用できる他のメソッドを見つけるには、[CommerceTaxCalculateRequest.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/tax/CommerceTaxCalculateRequest.java)を参照してください。

#### 言語キーを追加します `Language.properties`

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/implementing-a-new-tax-engine/resources/liferay-q4b9.zip/q4b9-impl/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

    a-flat-tax-rate-that-does-not-adjust=A flat tax rate that does not adjust.
    flat-tax=Flat Tax

> 詳細は、[Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)を参照してください。

## まとめ

`CommerceTaxEngine`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい税エンジンを追加しました。

## 追加情報

  - [Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
  - [Setting Tax Rate by Fixed Rate](../../store-administration/configuring-taxes/setting-tax-rate-by-fixed-rate.md)
