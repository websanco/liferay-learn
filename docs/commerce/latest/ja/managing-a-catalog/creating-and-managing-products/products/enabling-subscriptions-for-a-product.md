# 商品のサブスクリプションを有効にする

Liferay Commerceでは、ストアユーザーが商品のサブスクリプションを作成および管理できます。 定期購入の例には、雑誌、更新オプション付きのサービス契約、定期的に消費されるアイテムが含まれます（これらに限定されません）。

## 前提条件

商品のサブスクリプションを有効にするには、ストア管理者は定期的な支払いをサポートする支払い方法をアクティブにする必要があります。 デフォルトでは、Liferay Commerceは定期的な支払い方法として [Paypal](../../../store-administration/configuring-payment-methods/paypal.md) をサポートしています。

## 支払いサブスクリプションを有効にする

サブスクリプションは、 [シンプルな](../product-types/creating-a-simple-product.md)、 [グループ化された](../product-types/creating-a-grouped-product.md)、または [仮想](../product-types/creating-a-virtual-product.md) 商品のために有効にすることができます。 この例では、シンプル商品を使用しています。

1.  まだ作成していない場合は、 [シンプル商品](../product-types/creating-a-simple-product.md)作成します。

2.  次のように入力します：

      - **カタログ**：Sahara.com
      - **名前**：マルチビタミン

    ![シンプル商品の作成](./enabling-subscriptions-for-a-product/images/01.png)

3.  商品の作成が完了したら、[ *サブスクリプション* ]タブをクリックします。

    ![シンプル商品の作成](./enabling-subscriptions-for-a-product/images/02.png)

4.  支払いサブスクリプションにはトグルスイッチを *有効* にする。

5.  *Subscription Type* ドロップダウンメニューから *Month* を選択します。

6.  *Mode* ドロップダウンメニューから *Order Date* を選択します。」

7.  *Subscription Length* ドロップダウンメニューから *1* を入力します。

8.  *Never Ends* から *YES*に切り替えます。

    ![支払いサブスクリプションを構成する](./enabling-subscriptions-for-a-product/images/03.png)

9.  *[Publish]*ボタンをクリックします。

この商品で支払いサブスクリプションが有効になりました。

支払いサブスクリプションを有効にしたら、引き続き配信サブスクリプションを有効にできます。

## 配信サブスクリプションを有効にする

配信サブスクリプションを有効にするには：

1.  配信サブスクリプションでトグルを *有効* に切り替えます。

2.  *Subscription Type* ドロップダウンメニューから *Month* を選択します。

3.  *Mode* ドロップダウンメニューから *Order Date* 選択します。

4.  *Subscription Length* ドロップダウンメニューから *1* を入力します。

5.  *Never Ends* から *YES*に切り替えます。

    ![配信サブスクリプションを構成する](./enabling-subscriptions-for-a-product/images/04.png)

6.  *[Publish]*ボタンをクリックします。

この商品の配信サブスクリプションが有効になりました。

``` tip::
   定期的な支払いをサポートするために、他の支払い方法を実装できます。 詳細については、 `新しい支払い方法の実装 <../../../developer-guide/implementing-a-new-payment-method.md>`_ を参照してください。
```

## 商品の詳細でのサブスクリプションの表示

商品でサブスクリプションが有効になっている場合、ユーザーはカタログの商品詳細ウィジェットで支払いとサブスクリプションの詳細を表示できます。

![支払いと配信のサブスクリプションの詳細は、商品詳細ウィジェットに表示されます。](./enabling-subscriptions-for-a-product/images/05.png)

## 追加情報

  - [サブスクリプションの管理](../../../orders-and-fulfillment/subscriptions/managing-subscriptions.md)
  - [サブスクリプション管理リファレンスガイド](../../../orders-and-fulfillment/subscriptions/subscription-administration-reference-guide.md)
