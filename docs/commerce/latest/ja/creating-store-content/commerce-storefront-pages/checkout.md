# 清算

[Checkout] ページは、 *[Checkout]* ウィジェットを含む必須ページです。 購入者が *[Checkout]* をクリックして注文を送信すると、このウィジェットが読み込まれます。 カスタムの清算手順は、拡張ポイントを使用して実装することもできます。 詳細については、[Implementing a Custom Checkout Step](../../developer-guide/implementing-a-custom-checkout-step.md)を参照してください。

![[Checkout] ウィジェット](./checkout/images/01.png)

清算プロセスでは、購入者の配送先住所と請求先住所を入力し、配送方法を選択するためのいくつかのページがあります。 最後の2ページは、*[Order Summary]* と *[Order Confirmation]* です。

## 配送先住所の入力

![配送先住所のステップ](./checkout/images/02.png)

購入者が *[Use shipping address as billing address]* をオンにすると、[Billing Address] ページがスキップされます。

## 配送方法の選択

![配送方法のステップ](./checkout/images/03.png)

購入者が利用できる配送方法は、 *[Site Administration]* → *[Commerce]* → *[Settings]* -\> *[Shipping Methods]* で *[Shipping Methods]* を変更することにより設定されます。 詳細については、[Using the Flat Rate Shipping Method](../../store-administration/configuring-shipping-methods/using-the-flat-rate-shipping-method.md)を参照してください。

## 請求先住所の入力

上記のように、このページは、購入者の請求先住所が配送先住所と異なる場合にのみ表示されます。

![請求先住所のステップ](./checkout/images/04.png)

## 注文概要の表示

![注文概要のステップ](./checkout/images/05.png)

## 注文の確定

![注文確定のステップ](./checkout/images/06.png)

注文が確定されると、販売者は注文を処理できる状態になります。 詳細については、[Order Life Cycle](../../orders-and-fulfillment/orders/order-life-cycle.md)を参照してください。

## 追加情報

  - [Widget Reference Guide](../widget-reference.md)
  - [Creating Pages](https://help.liferay.com/hc/en-us/articles/360018171291-Creating-Pages)
  - [Implementing a Custom Checkout Step](../../developer-guide/implementing-a-custom-checkout-step.md)
  - [Using the Flat Rate Shipping Method](../../store-administration/configuring-shipping-methods/using-the-flat-rate-shipping-method.md)
