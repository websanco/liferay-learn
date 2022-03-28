# 商品のサブスクリプションを有効にする

Liferay Commerceでは、［[ Simple ](../product-types/creating-a-simple-product.md)］、 ［[グループ化済み](../product-types/creating-a-grouped-product.md)］、 ［[仮想](../product-types/creating-a-virtual-product.md)］ 商品のサブスクリプションを作成、管理することができます。 サブスクリションの例としては、雑誌、更新可能なサービス契約、そして定期的に消費される商品の自動注文などがあります。 Commerceでは、 ［**支払**］ と ［**配送**］のサブスクリプションを提供しています。

<a name="payment-and-delivery-subscriptions" />

## 支払と配達のサブスクリプション

［**支払のサブスクリプション**］は、電気代やNetflixのサブスクリプションに類似しています。 ここでは、サービスを継続するために、定期的に銀行口座から特定の金額が引き落とされます。

```{important}
支払のサブスクリプションは、PayPalまたは定期的な支払をサポートするその他のカスタム支払方法を使用する場合にのみ有効にできます。
```

［**配送のサブスクリプション**］は、雑誌のサブスクリプションに類似しています。 ここでは、一括払が前払され、定期的に出荷が行われる仕組みになっています。

支払のサブスクリプションと配送のサブスクリプションの両方を組み合わせることで、商品またはいくつかの商品を発送する必要があり、そのために顧客に定期的に支払いを請求するシナリオに対応できます。

<a name="prerequisites" />

## 前提条件

商品のサブスクリプションを有効にするには、ストア管理者は定期的な支払いをサポートする支払い方法をアクティブにする必要があります。 デフォルトでは、Liferay Commerceは定期的な支払い方法として [Paypal](../../../store-management/configuring-payment-methods/paypal.md) をサポートしています。

<a name="enabling-product-subscriptions" />

## 商品のサブスクリプションを有効にする

以下の手順で、商品のサブスクリプションを有効にします：

1. ［**グローバルメニュー**］(![Global Menu](../../../images/icon-applications-menu.png))を開き、［**Commerce**］タブをクリックして、［**商品管理**］ &rarr; ［**商品**］に移動します。

1. [**サブスクリプション**]タブをクリックします。

    ![商品のサブスクリプションタブをクリックします。](./enabling-subscriptions-for-a-product/images/02.png)

1. トグルスイッチを使用して、［**支払のサブスクリプション**］および/または ［**配送のサブスクリプション**］を有効にします。

1. ［**サブスクリプションタイプ**］のフィールドを使用して、サブスクリプションの時間単位を選択します。

   * 日
   * 週
   * 月
   * 年

   「週」、「月」、または「年」を選択した場合は、各ユニットが開始する曜日も決定する必要があります。

1. サブスクリプション期間の長さを設定するには、 **サブスクリプション期間** フィールドを使用します。

1. サブスクリプションを自動的に終了させるかどうかは、トグルスイッチで設定します。

    ![支払のサブスクリプションを構成します。](./enabling-subscriptions-for-a-product/images/03.png)

1. 完了したら、 ［**公開**］ をクリックします。

これで、選択した商品のサブスクリプションが有効になります。

```{tip}
定期的な支払いをサポートするために、他の支払い方法を実装できます。 詳細は[Implementing a New Payment Method](../../../developer-guide/implementing-a-new-payment-method.md) を参照してください。
```

<a name="viewing-subscriptions-in-product-details" />

## 商品の詳細でのサブスクリプションの表示

サブスクリプションの詳細は、商品の詳細ウィジェットを介して商品の表示ページに表示されます。

![支払いと配信のサブスクリプションの詳細は、商品詳細ウィジェットに表示されます。](./enabling-subscriptions-for-a-product/images/05.png)

<a name="additional-information" />

## 追加情報

* [サブスクリプションの管理](../../../order-management/subscriptions/managing-subscriptions.md)
* [サブスクリプション管理リファレンスガイド](../../../order-management/subscriptions/subscription-administration-reference-guide.md)
