# チャンネルリファレンスガイド

> Commerce 2.1+

この記事では、チャネル管理インターフェースの概要について説明します。 チャネルを作成するには、 [チャネルの管理](./managing-channels.md)参照してください。

## 一般

*General* タブには、通貨、注文ワークフロー、支払い、配送、税金など、多くの重要なチャネル設定があります。

### Details

*詳細* セクションでは、チャネルの名前、通貨、および [コマースサイトタイプ](../../../starting-a-store/sites-and-site-types.md) が設定されます。

![[詳細]タブでは、名前、通貨、サイトタイプを設定します。](./channels-reference-guide/images/01.png)

### [Orders]

ユーザーは、買い手 [と売り手](../../../orders-and-fulfillment/order-workflows/introduction-to-order-workflows.md)ワークフローを有効にできます。 ユーザーは、ゲストチェックアウトを有効または無効にしたり、注文番号が表示されるかどうか（該当する場合）もできます。

![[注文]タブでは、ワークフローが有効になります。](./channels-reference-guide/images/02.png)

### 価格

ユーザーは [配送税カテゴリー](../../../store-administration/configuring-taxes/creating-tax-categories.md)、価格タイプ、および割引目標価格タイプを設定できます。

*価格タイプ*場合、ユーザーは商品価格を税込み（総額）または税抜き（正味価格）のどちらで表示するかを決定できます。

*割引ターゲット*場合、ユーザーは、適用される割引が商品の総額または正味価格に基づいて計算されるかどうかを決定できます。

![[価格]タブでは、税のカテゴリと価格を設定します。](./channels-reference-guide/images/03.png)

### 健康診断

ヘルスチェックにより、ユーザーは、ストアが機能するために必要なウィジェットの不足など、ストアサイトに関する一般的なセットアップの問題をすばやく修正できます。

![ヘルスチェックを使用すると、コマースポートレットをすばやく追加したり、手動でサイトを構築した場合は問題を修正したりできます。](./channels-reference-guide/images/04.png)

### 支払い方法

ユーザーは、チャネルに使用する [支払方法](../../../store-administration/configuring-payment-methods/managing-payment-methods.md) を構成できます。 さらに、ユーザーは [カスタム支払いエンジン](../../../developer-guide/implementing-a-new-payment-method.md)使用できます。

![[お支払い方法]セクションでは、使用するお支払い方法を選択できます。](./channels-reference-guide/images/05.png)

### 配送方法

ユーザーはチャネルの配送方法を設定できます。 デフォルトでは、 [フラットレート](../../../store-administration/configuring-shipping-methods/using-the-flat-rate-shipping-method.md) が有効になっています。 ユーザーは [可変レート](../../../store-administration/configuring-shipping-methods/using-the-variable-rate-shipping-method.md) 配送方法を有効にすることもできます。 加入者は [FedEx](../../../store-administration/configuring-shipping-methods/using-the-fedex-shipping-method.md)有効にすることもでき
 。</p> 

最後に、ユーザーは [カスタム配送エンジン](../../../developer-guide/implementing-a-new-shipping-engine.md)使用できます。

![[配送方法]セクションでは、使用する配送方法を選択できます。](./channels-reference-guide/images/06.png)



### 税計算

ユーザーはチャネルの税計算を構成できます。 税率の設定の詳細については、 [住所による税率の設定](../../../store-administration/configuring-taxes/setting-tax-rate-by-address.md) または [固定税による税率の設定](../../../store-administration/configuring-taxes/setting-tax-rate-by-fixed-rate.md)を参照してください</p> 

![ユーザーはチャネルの税計算を構成できます。](./channels-reference-guide/images/07.png)



## タイプ

チャネルはDXPサイトに接続できます。 または、チャネルは、Amazonマーケットプレイスなどの非DXPサイトに接続できます。 ここで、ユーザーはチャネルを関連付けるDXPサイトを選択できます。

![チャンネルに関連付けられているサイトを選択します。](./channels-reference-guide/images/10.png)



## 通知キュー

Liferay Commerceは、さまざまなイベントによってトリガーされる電子メール通知を送信するように構成できます。 イベントによって通知がトリガーされると（注文など）、通知はチャネルの[ *通知キュー* ]タブに記録されます。 デフォルトでは、Liferay Commerceインスタンスは、通知が15分ごとに送信されたかどうかを確認し、43200分（30日）後に未送信の通知を削除します。

詳細については、 [コマース通知キュー](../../../store-administration/sending-emails/configuring-the-commerce-notification-queue.md) 設定の記事を参照してください。



## 通知テンプレート

通知テンプレートを使用すると、ユーザーはメール通知をカスタマイズして、トリガーイベントを定義できます。

メール通知の送信について詳しくは、 [ストアメール](../../../store-administration/sending-emails/store-emails.md)ご覧ください。

通知テンプレートの作成と使用の詳細については、 [通知テンプレートの使用](../../../store-administration/sending-emails/using-notification-templates.md)を参照してください。

![通知テンプレートを作成します。](./channels-reference-guide/images/11.png)



## カテゴリ表示ページ

[カテゴリ表示ページ]タブには、ストアサイトで作成されたすべてのカテゴリ表示ページのリストが表示されます。 *カテゴリー表示ページ* 使用すると、ユーザーは、特定のカテゴリーでタグ付けされたすべての商品を表示する特定のDXPサイトページを置き換えることができます。 ユーザーは最初に [商品カテゴリ](../../../store-administration/configuring-payment-methods/managing-payment-methods.md)を作成し、それらを目的の商品に関連付けてから、ページを作成する必要があります。

DXPサイトページの作成の詳細については、 [ページの作成](https://learn.liferay.com/dxp/7.x/en/site-building/creating_pages.html)を参照してください。 タグとカテゴリの作成の詳細については、 [タグとカテゴリ](https://learn.liferay.com/dxp/7.x/en/content-authoring-and-management/tags_and_categories.html)を参照してください。



## 商品表示ページ

商品表示ページは、カテゴリ表示ページと同様の前提で動作します。ユーザーは特定のサイトページを作成して、デフォルトのカタログページの代わりに特定の商品を強調表示できます。 *商品表示ページ* タブで、ユーザーはサイトで作成されたすべての商品表示ページのリストを表示できます。



## 支払い制限

ユーザーは、 購入者の国に対して、*制限された*支払方法を設定することができます。 （請求先住所がリストされていない場合、チャネルは配送先住所を確認します。） 少なくとも一つ、これを行うには [支払方法](../../../store-administration/configuring-payment-methods/managing-payment-methods.md) を有効にする必要があります。 各国のチェックボックスをオンにして、その国のアカウントがその支払い方法を使用できないようにします。

![支払い制限](./channels-reference-guide/images/08.png)



## 配送制限

ユーザーは、購入者の国で出荷方法を *制限する* ように構成できます。

![発送制限](./channels-reference-guide/images/09.png)



## 追加情報

  - [チャネルを使用した商品の可視性の構成](./configuring-product-visibility-using-channels.md)
  - [Enabling or Disabling Order Workflows](../../../orders-and-fulfillment/order-workflows/enabling-or-disabling-order-workflows.md)
