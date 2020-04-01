# 支払方法の管理

Liferay Commerceの支払い方法は、[チャネル](../../managing-a-catalog/creating-and-managing-products/introduction-to-channels.md)およびストア[サイト](../../sites-and-site-types.md)に限定されます。 Liferay Commerceインスタンスの同じインスタンスで複数のストアフロントがホストされている場合、管理者は各サイトで異なる支払い方法を使用できます。

支払い方法を管理するには、*[Site Administration]* → *[Commerce]* → *[Settings]*に移動し、[Payment Methods]タブを選択します。

![支払方法ページ](./managing-payment-methods/images/01.png)

## 支払方法の設定

![支払方法の設定](./managing-payment-methods/images/02.png)

### [Details]タブ

各詳細画面には、精算ウィジェットで支払い方法をレンダリングする方法を決定するフィールドが含まれています。

| フィールド       | 説明                                           |
| ----------- | -------------------------------------------- |
| Name        | 支払い方法の名前                                     |
| Description | 支払方法の簡単な説明                                   |
| Icon        | 支払い方法を表す小さな画像またはロゴをアップロードします                 |
| Priority    | 精算プロセスで支払い方法が表示される順序を決定します。数値が小さいほど高く表示されます。 |
| Active      | トグルを切り替えて、支払い方法を有効または無効にします                  |

### [Configuration]タブ

支払い方法の*[Configuration]*タブでは、ストア管理者が支払い方法をストアに統合します。

各支払い方法を有効にする方法については、対応する記事を参照してください。

  - [Authorize.net](../../orders-and-fulfillment/payment-methods/authorize.net.md)
  - [Mercanet](../../orders-and-fulfillment/payment-methods/mercanet.md)
  - [Money Orders](../../orders-and-fulfillment/payment-methods/money-orders.md)
  - [PayPal](../../orders-and-fulfillment/payment-methods/paypal.md)

### [Restrictions]タブ

制限は、指定された国の購入者の支払い方法を無効にします。

![支払い方法の制限を設定する](./managing-payment-methods/images/03.png)
