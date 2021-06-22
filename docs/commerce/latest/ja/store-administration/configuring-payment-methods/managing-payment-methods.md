# 支払方法の管理

Liferay Commerceの支払い方法は、[チャネル](../../starting-a-store/channels/introduction-to-channels.md)およびストア[サイト](../../starting-a-store/sites-and-site-types.md)に限定されます。 Liferay Commerceインスタンスの同じインスタンスで複数のストアフロントがホストされている場合、管理者は各サイトで異なる支払い方法を使用できます。

支払い方法を管理するには、 *コントロールパネル* → *Commerce* → *チャネル*へ行きます。

![Commerce 2.1チャネル](./managing-payment-methods/images/06.png)

目的のチャネルを選択し、このストアサイトの支払い方法の[ *編集* ]をクリックします。

![支払方法ページ](./managing-payment-methods/images/04.png)

選択した支払い方法がアクティブになったら、値で支払い方法を構成します。

## 支払方法の設定

![支払方法の設定](./managing-payment-methods/images/05.png)

### [Details]タブ

各詳細画面には、精算ウィジェットで支払い方法をレンダリングする方法を決定するフィールドが含まれています。

| フィールド    | 説明                                           |
| -------- | -------------------------------------------- |
| Name     | 支払い方法の名前                                     |
| 説明       | 支払方法の簡単な説明                                   |
| Icon     | 支払い方法を表す小さな画像またはロゴをアップロードします                 |
| Priority | 精算プロセスで支払い方法が表示される順序を決定します。数値が小さいほど高く表示されます。 |
| Active   | トグルを切り替えて、支払い方法を有効または無効にします                  |

### [Configuration]タブ

支払い方法の*[Configuration]*タブでは、ストア管理者が支払い方法をストアに統合します。

各支払い方法を有効にする方法については、対応する記事を参照してください。

  - [Authorize.net](./authorize.net.md)
  - [Mercanet](./mercanet.md)
  - [郵便為替](./money-orders.md)
  - [PayPal](./paypal.md)

## Commerce 2.0以前

Liferay Commerceの以前のバージョンでは、支払い方法はストア [サイト](../../starting-a-store/sites-and-site-types.md)スコープとしています。

Commerce 2.0以下で支払い方法を管理するには、 *サイト管理* → *Commerce* → *設定*ます。

![支払方法ページ](./managing-payment-methods/images/07.png)

[ *支払い方法* ]タブを選択します。

![支払方法ページ](./managing-payment-methods/images/01.png)

### 詳細タブ（2.0）

![支払方法の設定](./managing-payment-methods/images/02.png)

### [制限]タブ（2.0）

制限は、指定された国の購入者の支払い方法を無効にします。

![支払い方法の制限を設定する](./managing-payment-methods/images/03.png)
