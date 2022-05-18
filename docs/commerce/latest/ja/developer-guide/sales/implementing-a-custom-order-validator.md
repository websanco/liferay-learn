# カスタム注文バリデーターの実装

このチュートリアルでは、 [CommerceOrderValidator](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/order/CommerceOrderValidator.java) インターフェイスを実装して、カスタムオーダーバリデーターを追加する方法を示します。

注文バリデーターは、清算を進めるときに顧客のカート内のアイテムを検証するクラスです。 Liferay Commerceでは、 [デフォルト](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/internal/order/DefaultCommerceOrderValidatorImpl.java) をはじめ、 [アイテムバージョン](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/internal/order/VersionCommerceOrderValidatorImpl.java) や [定期的なアイテム（サブスクリプション）](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/internal/order/SubscriptionCommerceOrderValidatorImpl.java) を確認するバリデーターなど複数の注文バリデーターをすぐに使うことができます。

<a name="overview" />

## 概要

1. [**サンプルをデプロイする**](#deploy-an-example)
1. [**例の説明**](#walk-through-the-example)
1. [**追加情報**](#additional-information)

<a name="deploy-an-example" />

## サンプルをデプロイする

このセクションでは、注文バリデーターをLiferay Commerceのインスタンスで実行する例を示します。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [Acme Commerce Order Validator](./liferay-n9b2.zip) をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/sales/liferay-n9b2.zip -O
    ```

    ```bash
    unzip liferay-n9b2.zip
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
    STARTED com.acme.n9b2.impl_1.0.0
    ```

1. 失敗メッセージを表示して、サンプルの注文バリデーターが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開き、100ドル以上の価格のアイテムが少なくとも1つあるカタログに移動します。 そのような商品がまだ存在しない場合は、自分で追加してください。詳細は、[シンプル商品の作成](../../product-management/creating-and-managing-products/product-types/creating-a-simple-product.md)を参照してください。

    カタログからこの価格の商品を見つけて、［カートへ追加］をクリックします。 数量を11以上に増やし、矢印をクリックして続行します。 表示されるエラーメッセージは、カスタム注文バリデーターがアイテムの追加を正常に拒否したことを示しています。

    ![新しい注文検証エラーメッセージ](./implementing-a-custom-order-validator/images/01.png "新しい注文検証エラーメッセージ")

これで、`CommerceOrderValidator`を実装する新しい注文バリデーターを正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

<a name="walk-through-the-example" />

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、`CommerceOrderValidator`インターフェイスを確認します。 最後に、`CommerceOrderValidator`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

```java
@Component(
    property = {
    "commerce.order.validator.key=n9b2",
    "commerce.order.validator.priority:Integer=9"
    },
    service = CommerceOrderValidator.class
)
public class N9B2CommerceOrderValidator implements CommerceOrderValidator {
```

> Liferay Commerceが、 [注文バリデーターレジストリ](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/internal/order/CommerceOrderValidatorRegistryImpl.java) で新しい注文バリデーターを他のバリデーターと区別できるように、注文バリデーターに個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられているバリデーターが上書きされます。
> 
> `commerce.order.validator.priority`値は、他のバリデーターとの順序において、この注文バリデーターがいつ検証を実行するかを示します。 たとえば、 [デフォルトの注文バリデーター](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/internal/order/DefaultCommerceOrderValidatorImpl.java) の値は10です。 この注文バリデータに9の値を指定すると、デフォルトのバリデーターの直前に検証が実行されます。

### `CommerceOrderValidator`インターフェイスを確認する

次のメソッドを実装します。

```java
public String getKey();
```

> このメソッドは、注文バリデーターレジストリに注文バリデーター用の一意の識別子を提供します。 このキーを使用して、レジストリからバリデーターを取得できます。 すでに使用されているキーを再利用すると、既存の関連付けられているバリデーターが上書きされます。

```java
public CommerceOrderValidatorResult validate(Locale locale, CommerceOrder commerceOrder, CPInstance cpInstance, int quantity) throws PortalException;
```

> これは、カスタム検証ロジックを追加する2つの検証メソッドの1つです。 このメソッドは、顧客がカートにアイテムを追加するたびに呼び出されます。 これは`CommerceOrderValidatorResult`を返し、booleanを使用して結果が検証に合格したかどうかを示します。 [CommerceOrderValidatorResult.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/order/CommerceOrderValidatorResult.java) を参照してください。

```java
public CommerceOrderValidatorResult validate(Locale locale, CommerceOrderItem commerceOrderItem) throws PortalException;
```
> これは、カスタム検証ロジックを追加できる2番目の検証方法です。 このメソッドは、顧客が清算の新しいステップに進むと、カートにすでにあるアイテムに対して呼び出されます。

### 注文バリデーターを完了する

注文バリデーターは、カートに商品を追加し、新しい清算手順に進むための検証ロジックで構成されています。 以下を行います。

* [商品をカートに追加するための検証ロジックを追加する。](#add-validation-logic-for-adding-a-product-to-cart)
* [清算に進むための検証ロジックを追加する。](#add-validation-logic-for-proceeding-in-checkout)
* [言語キーを `Language.properties`追加します。](#add-the-language-keys-to-languageproperties)

2つの`検証`メソッドでは、注文バリデーター用のカスタム検証ロジックを定義します。 この例では、特定の価格で商品が11個以上ある注文を拒否するロジックを追加します。

#### 商品をカートに追加するための検証ロジックを追加する

```java
@Override
public CommerceOrderValidatorResult validate(
        Locale locale, CommerceOrder commerceOrder, CPInstance cpInstance,
        int quantity)
    throws PortalException {

    if (cpInstance == null) {
        return new CommerceOrderValidatorResult(false);
    }

    BigDecimal price = cpInstance.getPrice();

    if ((price.doubleValue() > _MAX_ITEM_PRICE) &&
        (quantity > _MAX_ITEM_QUANTITY)) {

        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
            "content.Language", locale, getClass());

        return new CommerceOrderValidatorResult(
            false,
            LanguageUtil.format(
                resourceBundle,
                "this-expensive-item-has-a-maximum-quantity-of-x",
                Integer.valueOf(_MAX_ITEM_QUANTITY)));
    }

    return new CommerceOrderValidatorResult(true);
}

private static final double _MAX_ITEM_PRICE = 100.0;

private static final int _MAX_ITEM_QUANTITY = 10;
```

> この例の主な検証チェックは、価格（`BigDecimal`として保存されている）が100ドルを超えていて、数量が10を超えているかどうかをチェックすることです。 `CPInstance`から価格情報を取得します。これには、顧客が追加した注文に関する情報が含まれています。 `CPInstance`で使用できる他のメソッドについては、 [CPInstance](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-api/src/main/java/com/liferay/commerce/product/model/CPInstance.java) と [CPInstanceModel](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-api/src/main/java/com/liferay/commerce/product/model/CPInstanceModel.java) を参照してください。
> 
> メインの検証チェックで検証が失敗した理由を説明するローカライズされたメッセージを含めることをお勧めします。

#### 清算に進むための検証ロジックを追加する

```java
@Override
public CommerceOrderValidatorResult validate(
        Locale locale, CommerceOrderItem commerceOrderItem)
    throws PortalException {

    BigDecimal price = commerceOrderItem.getUnitPrice();

    if ((price.doubleValue() > _MAX_ITEM_PRICE) &&
        (commerceOrderItem.getQuantity() > _MAX_ITEM_QUANTITY)) {

        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
            "content.Language", locale, getClass());

        return new CommerceOrderValidatorResult(
            false,
            LanguageUtil.format(
                resourceBundle,
                "expensive-items-have-a-maximum-order-quantity-of-x",
                Integer.valueOf(_MAX_ITEM_QUANTITY)));
    }

    return new CommerceOrderValidatorResult(true);
}
```

> このメソッドは顧客のカート内のアイテムに対して呼び出されるため、同じ検証ロジックをこのメソッドに追加します。 この場合の主な違いは、`CommerceOrderItem`オブジェクトから情報を取得することです。`CommerceOrderItem`で使用できる他のメソッドについては、 [CommerceOrderItem](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrderItem.java) および [CommerceOrderItemModel](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrderItemModel.java) を参照してください。

#### 言語キーを追加します `Language.properties`

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/sales/implementing-a-custom-order-validator/resources/liferay-n9b2.zip/n9b2-impl/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

```properties
expensive-items-have-a-maximum-order-quantity-of-x=Expensive items have a maximum order quantity of {0}.
this-expensive-item-has-a-maximum-quantity-of-x=This expensive item has a maximum order quantity of {0}.
```

> 詳細は、 [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application) を参照してください。

<a name="conclusion" />

## まとめ

　 `CommerceOrderValidator`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい注文バリデーターを追加しました。

<a name="additional-information" />

## 追加情報

* [シンプル商品を作成する](../../product-management/creating-and-managing-products/product-types/creating-a-simple-product.md)
* [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
