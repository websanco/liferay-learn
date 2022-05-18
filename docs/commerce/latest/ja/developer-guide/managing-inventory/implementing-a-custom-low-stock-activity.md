# カスタムの在庫不足アクティビティの実装

このチュートリアルでは、 [CommerceLowStockActivity](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-api/src/main/java/com/liferay/commerce/stock/activity/CommerceLowStockActivity.java) インターフェイスを実装して、カスタムの在庫不足アクティビティを追加する方法を示します。

在庫不足アクティビティは、商品が設定された最小在庫数量を下回った場合に自動的に実行されるアクションです。 Liferay Commerceは、1つの [デフォルトの在庫不足アクティビティ](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/internal/stock/activity/CommerceLowStockActivityImpl.java) を提供しています。これは、商品を非公開にすることです。

![すぐに使える在庫不足アクティビティ](./implementing-a-custom-low-stock-activity/images/01.png "すぐに使える在庫不足アクティビティ")

<a name="overview" />

## 概要

1. [**サンプルをデプロイする**](#deploy-an-example)
1. [**例の説明**](#walk-through-the-example)
1. [**追加情報**](#additional-information)

<a name="deploy-an-example" />

## サンプルをデプロイする

このセクションでは、在庫不足アクティビティをLiferay Commerceのインスタンスで実行する例を示します。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [Acme Commerce Low Stock Activity](./liferay-j1e4.zip) をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/managing-inventory/liferay-j1e4.zip -O
    ```

    ```bash
    unzip liferay-j1e4.zip
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
    STARTED com.acme.j1e4.impl_1.0.0
    ```

1. サンプルの在庫不足アクティビティが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開きます。 アプリケーションメニュー（![Applications Menu](../../images/icon-applications-menu.png)）をクリックし、［**コマース**］→［**商品**］に移動します。 次に、任意の商品のメニュー内の［**編集**］をクリックします。 必要に応じて、 [商品](../../product-management/creating-and-managing-products/product-types/creating-a-simple-product.md)追加できます。

   そこから、［**設定**］に移動します。 新しいアクティビティ（"Log a warning message"）が［**在庫数低下時のアクション**］ドロップダウンの下に表示されます。

```{note}
  Liferay Commerce 2.1以前のバージョンでは、*コントロールパネル* → *Commerce* → *商品*に移動して商品ページを検索します。 
```

![新しい在庫不足アクティビティ](./implementing-a-custom-low-stock-activity/images/02.png "新しい在庫不足アクティビティ")

これで、`CommerceLowStockActivity`を実装する新しい在庫不足アクティビティを正常に構築およびデプロイできました。

次に、詳細をさらに詳しく見ていきましょう。

<a name="walk-through-the-example" />

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、`CommerceLowStockActivity`インターフェイスを確認します。 最後に、`CommerceLowStockActivity`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

```java
@Component(
    property = {
        "commerce.low.stock.activity.key=j1e4",
        "commerce.low.stock.activity.priority:Integer=9"
    },
    service = CommerceLowStockActivity.class
)
public class J1E4CommerceLowStockActivity implements CommerceLowStockActivity {
```

> Liferay Commerceが在庫不足アクティビティレジストリ</a>で新しいアクティビティを他のアクティビティと区別できるように、在庫数低下アクティビティ個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられているアクティビティが上書きされます。
> 
> `commerce.low.stock.activity.priority`値は、在庫不足アクティビティのリストでこのアクティビティがUIに表示される範囲を示します。 例えば、 ["set as unpublished" アクティビティ](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-service/src/main/java/com/liferay/commerce/internal/stock/activity/CommerceLowStockActivityImpl.java) の値は10です。 作成した在庫不足アクティビティの値を9にすると、"set as unpublished"アクティビティの直前に表示されます。

### `CommerceLowStockActivity`インターフェイスを確認する

次のメソッドを実装します。

```java
public void execute(CPInstance cpInstance) throws PortalException;
```

> このメソッドでは、カスタムアクティビティのビジネスロジックが実装されます。

```java
public String getKey();
```

> これにより、在庫不足アクティビティレジストリの在庫不足アクティビティに一意の識別子が提供されます。 このキーを使用して、レジストリから在庫不足アクティビティを取得できます。

```java
public String getLabel(Locale locale);
```

> これは、在庫不足アクティビティを説明するテキストラベルを返します。 言語キーでラベルを取得する際のリファレンスについては、[J1E4CommerceLowStockActivity.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/managing-inventory/implementing-a-custom-low-stock-activity/resources/liferay-j1e4.zip/j1e4-impl/src/main/java/com/acme/j1e4/internal/commerce/stock/activity/J1E4CommerceLowStockActivity.java)の実装を参照してください。

### 在庫不足アクティビティを完了する

在庫不足アクティビティは、アクティビティ自体を実行するバックエンドロジックで構成されます。 以下を行います。

* [ビジネスロジックを`execute`に追加する 。](#add-business-logic-to-execute)
* [言語キーを`Language.properties`に追加する。](#add-the-language-key-to-languageproperties)

#### ビジネスロジックを`execute`に追加する

```java
@Override
public void execute(CPInstance cpInstance) throws PortalException {
    if (_log.isWarnEnabled()) {
        _log.warn("SKU " + cpInstance.getSku());
    }
}
```

> この例では、Liferayのログに追加される警告メッセージを追加します。
> 
> `cpInstance`オブジェクトには、在庫の少ないアイテムについて使用できる情報が含まれています。 この例では、警告メッセージに追加するアイテムのSKUを取得するために使用します。 `CPInstance`で使用できる他のメソッドについては、 [CPInstance](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-api/src/main/java/com/liferay/commerce/product/model/CPInstance.java) と [CPInstanceModel](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-api/src/main/java/com/liferay/commerce/product/model/CPInstanceModel.java) を参照してください。

#### 言語キーを`Language.properties`に追加する

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/managing-inventory/implementing-a-custom-low-stock-activity/resources/liferay-j1e4.zip/j1e4-impl/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

```properties
j1e4-commerce-low-stock-activity=J1E4 Commerce Low Stock Activity
```

> 詳細は、 [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application) を参照してください。

<a name="conclusion" />

## まとめ

　 `CommerceLowStockActivity`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい在庫不足アクティビティを追加しました。

<a name="additional-information" />

## 追加情報

* [シンプル商品の作成](../../product-management/creating-and-managing-products/product-types/creating-a-simple-product.md)
* [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
* [在庫数低下時のアクション](../../inventory-management/low-stock-action.md)
