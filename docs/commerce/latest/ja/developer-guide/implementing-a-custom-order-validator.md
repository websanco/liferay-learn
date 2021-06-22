# カスタム注文バリデーターの実装

このチュートリアルでは、[CommerceOrderValidator](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/order/CommerceOrderValidator.java)インターフェイスを実装して、カスタムオーダーバリデーターを追加する方法を示します。

注文バリデーターは、清算を進めるときに顧客のカート内のアイテムを検証するクラスです。 Liferay Commerceは、[デフォルト](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/internal/order/DefaultCommerceOrderValidatorImpl.java)を含む複数のすぐに使用可能な注文バリデーターと、[アイテムのバージョン](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/internal/order/VersionCommerceOrderValidatorImpl.java)と[定期的なアイテム（サブスクリプション）](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/internal/order/SubscriptionCommerceOrderValidatorImpl.java)をチェックするバリデーターを提供します。

## 概要

1.  [**サンプルをデプロイする**](#deploy-an-example)
2.  [**例の説明**](#walk-through-the-example)
3.  [**追加情報**](#additional-information)

## サンプルをデプロイする

このセクションでは、注文バリデーターをLiferay Commerceのインスタンスで実行する例を示します。 次の手順を実行します：

1.  Liferay Commerceを開始します。

    ``` bash
    docker run -it -p 8080:8080 liferay/commerce:2.0.5
    ```

2.  [Acme Commerce Order Validator](./liferay-n9b2.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/liferay-n9b2.zip -O
    ```

    ``` bash
    unzip liferay-n9b2.zip
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
    STARTED com.acme.n9b2.impl_1.0.0
    ```

5.  失敗メッセージを表示して、サンプルの注文バリデーターが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開き、100ドル以上の価格のアイテムが少なくとも1つあるカタログに移動します。 そのような商品がまだ存在しない場合は、自分で追加してください。詳細については、[Creating a Simple Product](../../managing-a-catalog/creating-and-managing-products/product-types/creating-a-simple-product.md)を参照してください。

    カタログからこの価格の商品を見つけて、[Add to Cart]をクリックします。 数量を11以上に増やし、矢印をクリックして続行します。 表示されるエラーメッセージは、カスタム注文バリデーターがアイテムの追加を正常に拒否したことを示しています。

    ![新しい注文検証エラーメッセージ](./implementing-a-custom-order-validator/images/01.png "新しい注文検証エラーメッセージ")

これで、`CommerceOrderValidator`を実装する新しい注文バリデーターを正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、`CommerceOrderValidator`インターフェイスを確認します。 最後に、`CommerceOrderValidator`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

``` java
@Component(
    immediate = true,
    property = {
        "commerce.order.validator.key=" + N9B2CommerceOrderValidator.KEY,
        "commerce.order.validator.priority:Integer=9"
    },
    service = CommerceOrderValidator.class
)
public class N9B2CommerceOrderValidator implements CommerceOrderValidator {

    public static final String KEY = "Example";
```

> Liferay Commerceが[注文バリデーターレジストリ](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/internal/order/CommerceOrderValidatorRegistryImpl.java)で新しい注文バリデーターを他のバリデーターと区別できるように、注文バリデーターに個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられているバリデーターが上書きされます。
> 
> `commerce.order.validator.priority`値は、他のバリデーターとの順序において、この注文バリデーターがいつ検証を実行するかを示します。 たとえば、[デフォルトの注文バリデーター](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-service/src/main/java/com/liferay/commerce/internal/order/DefaultCommerceOrderValidatorImpl.java)の値は10です。 この注文バリデータに9の値を指定すると、デフォルトのバリデーターの直前に検証が実行されます。

### `CommerceOrderValidator`インターフェイスを確認する

次のメソッドを実装します。

``` java
public String getKey();
```

> このメソッドは、注文バリデーターレジストリに注文バリデーター用の一意の識別子を提供します。 このキーを使用して、レジストリからバリデーターを取得できます。 すでに使用されているキーを再利用すると、既存の関連付けられているバリデーターが上書きされます。

``` java
public CommerceOrderValidatorResult validate(Locale locale, CommerceOrder commerceOrder, CPInstance cpInstance, int quantity) throws PortalException;
```

> これは、カスタム検証ロジックを追加する2つの検証メソッドの1つです。 このメソッドは、顧客がカートにアイテムを追加するたびに呼び出されます。 これは`CommerceOrderValidatorResult`を返し、booleanを使用して結果が検証に合格したかどうかを示します。[CommerceOrderValidatorResult.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/order/CommerceOrderValidatorResult.java)を参照してください。

``` java
public CommerceOrderValidatorResult validate(Locale locale, CommerceOrderItem commerceOrderItem) throws PortalException;
```

> これは、カスタム検証ロジックを追加できる2番目の検証方法です。 このメソッドは、顧客が清算の新しいステップに進むと、カートにすでにあるアイテムに対して呼び出されます。

### 注文バリデーターを完了する

注文バリデーターは、カートに商品を追加し、新しい清算手順に進むための検証ロジックで構成されています。 以下を行います。

  - [商品をカートに追加するための検証ロジックを追加する。](#add-validation-logic-for-adding-a-product-to-cart)
  - [清算に進むための検証ロジックを追加する。](#add-validation-logic-for-proceeding-in-checkout)
  - [言語キーを `Language.properties`追加します。](#add-the-language-keys-to-languageproperties)

2つの`検証`メソッドでは、注文バリデーター用のカスタム検証ロジックを定義します。 この例では、特定の価格で商品が11個以上ある注文を拒否するロジックを追加します。

#### 商品をカートに追加するための検証ロジックを追加する

``` java
@Override
public CommerceOrderValidatorResult validate(
        Locale locale, CommerceOrder commerceOrder, CPInstance cpInstance,
        int quantity)
    throws PortalException {

    if (cpInstance == null) {
        return new CommerceOrderValidatorResult(false);
    }

    BigDecimal price = cpInstance.getPrice();

    if ((price.doubleValue() > 100.0) && (quantity > 10)) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
            "content.Language", locale, getClass());

        return new CommerceOrderValidatorResult(
            false,
            LanguageUtil.get(
                resourceBundle, "this-item-has-a-maximum-quantity-of-10"));
    }

    return new CommerceOrderValidatorResult(true);
}
```

> この例の主な検証チェックは、価格（`BigDecimal`として保存されている）が100ドルを超えていて、数量が10を超えているかどうかをチェックすることです。 `CPInstance`から価格情報を取得します。これには、顧客が追加した注文に関する情報が含まれています。 `CPInstance`で使用できる他のメソッドを見つけるには、[CPInstance](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-product-api/src/main/java/com/liferay/commerce/product/model/CPInstance.java)および[CPInstanceModel](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-product-api/src/main/java/com/liferay/commerce/product/model/CPInstanceModel.java)を参照してください。
> 
> メインの検証チェックで検証が失敗した理由を説明するローカライズされたメッセージを含めることをお勧めします。

#### 清算に進むための検証ロジックを追加する

``` java
@Override
public CommerceOrderValidatorResult validate(
        Locale locale, CommerceOrderItem commerceOrderItem)
    throws PortalException {

    BigDecimal price = commerceOrderItem.getUnitPrice();

    if ((price.doubleValue() > 100.0) &&
        (commerceOrderItem.getQuantity() > 10)) {

        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
            "content.Language", locale, getClass());

        return new CommerceOrderValidatorResult(
            false,
            LanguageUtil.get(
                resourceBundle,
                "expensive-items-have-a-maximum-quantity-of-10"));
    }

    return new CommerceOrderValidatorResult(true);
}
```

> このメソッドは顧客のカート内のアイテムに対して呼び出されるため、同じ検証ロジックをこのメソッドに追加します。 この場合の主な違いは、`CommerceOrderItem`オブジェクトから情報を取得することです。`CommerceOrderItem`で使用できる他のメソッドを見つけるには、[CommerceOrderItem](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrderItem.java)および[CommerceOrderItemModel](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrderItemModel.java)を参照してください。

#### 言語キーを追加します `Language.properties`

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/implementing-a-custom-order-validator/resources/liferay-n9b2.zip/n9b2-impl/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

    expensive-items-have-a-maximum-quantity-of-10=Expensive items have a maximum quantity of 10.
    this-item-has-a-maximum-quantity-of-10=This item has a maximum quantity of 10.

> 詳細は、[Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)を参照してください。

## まとめ

`CommerceOrderValidator`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい注文バリデーターを追加しました。

## 追加情報

  - [Creating a Simple Product](../../managing-a-catalog/creating-and-managing-products/product-types/creating-a-simple-product.md)
  - [Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
