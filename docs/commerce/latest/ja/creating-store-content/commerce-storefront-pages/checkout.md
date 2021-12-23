# チェックアウト

［チェックアウト］ ページは、 ［_チェックアウト_］ ウィジェットを含む必須ページです。 購入者が ［_チェックアウト_］ をクリックして注文を送信すると、このウィジェットが読み込まれます。 カスタムの清算手順は、拡張ポイントを使用して実装することもできます。 詳細は、[カスタム清算ステップの実装](../../developer-guide/implementing-a-custom-checkout-step.md)を参照してください。

![［チェックアウト］ ウィジェット](./checkout/images/01.png)

清算プロセスでは、購入者の配送先住所と請求先住所を入力し、配送方法を選択するためのいくつかのページがあります。 最後の2ページは、［_注文概要_］ と ［_注文確認_］ です。

## 配送先住所の入力

![配送先住所のステップ](./checkout/images/02.png)

購入者が ［_請求先住所に配送先住所を使用_］ をオンにすると、［請求先住所］ ページがスキップされます。

## 配送方法の選択

![配送方法のステップ](./checkout/images/03.png)

購入者が利用できる配送方法は、［_サイト管理_］の中の［_配送方法_］ から → _Commerce_ → ［_設定_］ -> ［_配送方法_］を変更することにより設定されます。 詳細は、[定額配送方法の使用](../../store-administration/configuring-shipping-methods/using-the-flat-rate-shipping-method.md)を参照してください。

## 請求先住所の入力

上記のように、このページは、購入者の請求先住所が配送先住所と異なる場合にのみ表示されます。

![請求先住所のステップ](./checkout/images/04.png)

## 注文概要の表示

![注文概要のステップ](./checkout/images/05.png)

## 注文の確定

![注文確定のステップ](./checkout/images/06.png)

注文が確定されると、販売者は注文を処理できる状態になります。 詳細は、[注文ライフサイクル](../../orders-and-fulfillment/orders/order-life-cycle.md)を参照してください。

## 追加情報

* [Widget Reference Guide](../liferay-commerce-widgets/widget-reference.md)
* [Creating Pages](https://help.liferay.com/hc/en-us/articles/360018171291-Creating-Pages)
* [カスタム清算ステップの実装](../../developer-guide/implementing-a-custom-checkout-step.md)
* [定額配送方法の使用](../../store-administration/configuring-shipping-methods/using-the-flat-rate-shipping-method.md)
