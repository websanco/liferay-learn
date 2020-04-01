# 注文管理ステータス

Liferay Commerceのすべての注文には、注文管理ステータスを含む重要な[注文情報](./order-information.md)が含まれています 。

この記事では、[*[Orders]* メニュー](./orders-menu.md)にある3つの注文管理ステータスのタイプ、*[Order Status]*、*[Payment Status]*、および*[Workflow Status]*の概要について説明します。

![注文管理ステータス](./order-management-statuses/images/01.png)

## 注文のステータス

*[Order Status]*では、注文が[注文ライフサイクル](./order-life-cycle.md)のどこにあるかについて、より正確な情報が提供されます。 各注文ステータスは、 [*[Orders]*メニュー](./orders-menu.md)の[*[Open]*](../orders-and-fulfillment/orders-menu.md#open)、[*[Pending]*](../orders-and-fulfillment/orders-menu.md#pending)、または[*[Transmitted]*](../orders-and-fulfillment/orders-menu.md#transmitted)タブのいずれか1つにのみ表示されます。

### [Open]タブ

*[Open]*タブで使用できる注文ステータスには、*[Open]*および*[In Progress]*の2つがあります。

![注文ステータスの[Open]タブ](./order-management-statuses/images/02.png)

| 注文のステータス    | 説明                                                                    |
| ----------- | --------------------------------------------------------------------- |
| Open        | 1つ以上の商品がカートに追加されました。 注文が精算プロセスの途中である可能性があります（*[Order Summary]*ページまで）。 |
| In Progress | 注文は、精算プロセスで*[Order Summary]* ページを越えて支払いステップに進みました。                    |

### [Pending]タブ

*[Pending]*タブでは、*[To Transmit]*という1つの注文ステータスが使用可能です。

![注文ステータスの[Pending]タブ](./order-management-statuses/images/03.png)

| 注文のステータス    | 説明                                    |
| ----------- | ------------------------------------- |
| To Transmit | 注文が購入者によって行われましたが、販売者によってまだ送信されていません。 |

### [Transmitted]タブ

*[Transmitted]*タブでは、以下にリストされている12個の注文ステータスが使用可能です。

![注文ステータスの[Transmitted]タブ](./order-management-statuses/images/04.png)

| 注文のステータス             | 説明                                                             |
| -------------------- | -------------------------------------------------------------- |
| Transmitted          | 注文は送信され、追加のステータス更新はありません。 （これは、送信された注文のデフォルトの注文ステータスです。）       |
| Awaiting Fulfillment | 注文は送信され、支払いが確認されましたが、運送業者による出荷の準備はまだできていません。                   |
| Awaiting Pickup      | 注文は準備済み（おそらく発送済み）で、販売者が指定した場所から購入者が受け取るのを待っています。               |
| Awaiting Shipment    | 注文の発送準備が整い、運送業者による集荷と出荷を待っています。                                |
| Partially Refunded   | 販売者が、注文の合計金額未満の金額を購入者に払い戻しました。                                 |
| Partially Shipped    | 注文した商品の一部のみが出荷されました。                                           |
| Refunded             | 販売者が、注文の合計金額を購入者に払い戻しました。                                      |
| Shipped              | 注文は発送されましたが、まだ受領確認はされていません。                                    |
| Completed            | 注文が発送済み（または受け取り済み）で、受領が確認されました。または、仮想商品の支払いが行われ、ダウンロード可能な状態です。 |
| Cancelled            | 注文が販売者によってキャンセルされました。                                          |
| Declined             | 注文は発送されましたが、購入者が受け取りを拒否しました。                                   |
| Disputed             | 購入者が、支払い取引の紛争解決を開始しました（PayPalなどで）。                             |

## 支払い状況

*[Payment Status]*には、注文に対して支払いが行われ、受領されたかどうかに関する情報が表示されます。 [*[Orders]*メニュー](./orders-menu.md)の*[Pending]*および*[Transmitted]*タブでは、*[Authorized]*、*[Paid]*、および*[Pending]*の3つの支払いステータスが使用可能です。 （*[Open]*タブでは、注文の支払いステータスは常に*[Pending]*です。 ）

![支払い状況](./order-management-statuses/images/05.png)

| 支払い状況      | 説明                                                                                                                                                                   |
| ---------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Authorized | 支払いは関係する金融機関によって承認されており、販売者の口座に入金するためのステップが取られます。 （これは、PayPal、クレジットカード、デビットカードなどのオンラインの支払い方法の場合が考えられます。）                                                             |
| Paid       | 注文に対する支払いが販売者によって受領されました。                                                                                                                                            |
| Pending    | 注文に対する支払いが受領または承認されていません。 （これは、支払いがまだ受領または確認されていない場合、または支払いステータスが更新されていない場合に、郵便為替や発注書などのオフラインの支払い方法の場合が考えられます。 また、購入者が支払いプロセスを停止した場合は、PayPalなどのオンラインの支払い方法の場合もあります。） |

## ワークフローステータス

*[Workflow Status]*には、2つの[注文ワークフロー](./order-workflows.md)のいずれかで注文がどこにあるかに関する情報が提供されます。 （./orders-menu.md）見つかりました。）

### [Open]タブ

*[Open]*タブの*[Workflow Status]*は、[*承認ワークフロー*](../order-workflows/README.md#approval-workflow-buyer-side-cart-approval-only)での注文の進行状況を示します。 この注文ワークフローでは、*[Draft]*、*[Pending]*、および*[Approved]*の3つのワークフローステータスが使用可能です。

![ワークフローステータスの[Open]タブ](./order-management-statuses/images/06.png)

| ワークフローステータス | 説明                                              |
| ----------- | ----------------------------------------------- |
| Draft       | 商品がカートに追加されましたが、購入者による確認と承認のためにカートがまだ送信されていません。 |
| Pending     | カート（注文）は確認と承認のために送信されましたが、購入者によってまだ承認されていません。   |
| Approved    | 注文は購入者によって確認および承認され、注文が精算に進むことができます。            |

### [Pending]タブ

*[Pending]*タブの*[Workflow Status]*は、[*送信ワークフロー*](../order-workflows/README.md#transmission-workflow-seller-side-order-approval-only)での注文の進行状況を示します。 この注文ワークフローでは、*[Pending]*および*[Approved]*の2つのワークフローステータスが使用可能です。

![ワークフローステータスの[Pending]および[Transmitted]タブ](./order-management-statuses/images/07.png)

| ワークフローステータス | 説明                                                                           |
| ----------- | ---------------------------------------------------------------------------- |
| Pending     | 注文は行われましたが、販売者によってまだ承認されていません。                                               |
| Approved    | 注文は販売者によって確認および承認され、注文は送信されます。 （注：このワークフローステータスは、*[Transmitted]*タブにも表示されます。） |

## 追加情報

  - [Orders Menu](./orders-menu.md)
  - [Order Information](./order-information.md)
  - [Order Life Cycle](./order-life-cycle.md)
  - [Order Workflows](./order-workflows.md)
  - [Enabling or Disabling Order Workflows](./enabling-or-disabling-order-workflows.md)
  - [Approving or Rejecting Orders in Order Workflows](./approving-or-rejecting-orders-in-order-workflows.md)
