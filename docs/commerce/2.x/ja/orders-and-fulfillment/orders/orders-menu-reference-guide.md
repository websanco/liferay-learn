# 注文メニューリファレンスガイド

注文は*注文*メニューの管理者によって[閲覧、管理](./processing-an-order.md)できます。 *注文* メニューにアクセスするには、 *コントロールパネル/0> → *Commerce* → *注文*へ行きます。</p>

上部には5つのタブがあります: *すべて*、*オープン*、*保留中*、*処理中*、*完了*。

![注文メニュー](./orders-menu-reference-guide/images/05.png)

## すべて

*全て* のタブには、注文状況に関係なくすべての注文が表示されます。

## Open

*オープン* タブには、チェックアウトプロセスを完了していないすべての注文が表示されます。

![注文メニュー-タブを開く](./orders-menu-reference-guide/images/07.png)

| フィールド    | 説明                              |
| -------- | ------------------------------- |
| 注文ID     | これは注文IDです。                      |
| アカウント    | これはアカウントの名前です。                  |
| アカウント番号  | 生成された口座番号です。                    |
| チャネル     | これは、注文が行われたチャネルまたはストアフロントの名前です。 |
| 量        | 注文金額です。                         |
| 日付を作成します | 注文が作成された日付が表示されます。              |
| 注文のステータス | これは注文のステータスです。                  |

## 保留中

*保留中* タブには、チェックアウトプロセスを完了したすべての注文が表示されます。

![[注文]メニュー-[保留中]タブ](./orders-menu-reference-guide/images/06.png)

注意すべき3つの特定のフィールドがあります。

| フィールド          | 説明                                                                                                 |
| -------------- | -------------------------------------------------------------------------------------------------- |
| 注文日            | これは、注文が作成された日付です。                                                                                  |
| 注文のステータス       | これは注文のステータスです。                                                                                     |
| 承認ワークフローのステータス | これにより、注文 [購入者の承認ワークフロー](../order-workflows/enabling-or-disabling-order-workflows.md) ステータスが表示されます。 |

## 処理

注文を *保留* から *処理*に進めるには、[ *注文を受け入れる* ]ボタンをクリックします。

![[注文]メニュー-[保留中]タブ](./orders-menu-reference-guide/images/10.png)

ストアが注文の処理を開始すると、注文は *Processing* タブに表示されます。

![[注文]メニュー-[保留中]タブ](./orders-menu-reference-guide/images/09.png)

## 完了

[配送が確認された場合](../shipments/introduction-to-shipments.md)、注文は *完了* タブに移動します。

![[注文]メニュー-[保留中]タブ](./orders-menu-reference-guide/images/08.png)

## Liferay Commerce 2.0以下

注文は上で管理されている *オープン*、 *保留*、及び *透過* を介して注文の進行に応じてタブ [オーダーのライフサイクル](./order-life-cycle.md)。

![[Orders]メニューの概要](./orders-menu-reference-guide/images/01.png "[Orders]メニューの概要")

*[Orders]*メニューの注文情報は、*[Open Carts]*ウィジェット（*[Open]*タブにある注文の場合 ）および*[Orders]*ウィジェット（*[Pending]*または*[Transmitted]*タブにある注文の場合）を使用して、他のユーザー（特に購入者）に提示することもできます。 詳細については、[Pending Orders](../../creating-store-content/commerce-storefront-pages/pending-orders.md)および[Placed Orders](../../creating-store-content/commerce-storefront-pages/placed-orders.md)を参照してください。

### Open

このタブでは、注文アクティビティは購入者側のみで行われます。 購入者が商品をカートに追加すると、*[Open]*タブで新しい注文が作成されます。 購入者が注文するまで、注文はこのタブに残ります。

``` note::
   *承認ワークフロー*が有効になっている場合、ワークフロープロセスが完了して注文が行われるまで、注文は[開く]タブに残ります。
```

![[Open]タブ](./orders-menu-reference-guide/images/02.png "[Open]タブ")

### 保留中

*[Pending]*タブは注文の保留場所として機能します。これは、すべての注文をすぐに送信するわけではないビジネスコンテキストで役立ちます。 購入者が注文すると、注文は*[Pending]*タブに移動します。 ここで、売り手は、 [変更、キャンセル、または注文を送信することができます](./processing-an-order.md#commerce-20-and-below)。

``` note::
   * Transmission Workflow *が有効になっている場合、売り手が注文を拒否して買い手に送り返す場合でも、注文はワークフロープロセス全体で* Pending *タブに残ります。
```

![[Pending]タブ](./orders-menu-reference-guide/images/03.png "[Pending]タブ")

### 送信済み

このタブでは、注文アクティビティは販売者側のみで行われます。 売り手は、 *保留中の* 注文を、 [注文ステータス](./processing-an-order.md#commerce-20-and-below) を（手動または自動で）"*To Transmit*"ステータスから他の注文ステータスに変更することで送信します。 この時点で、注文は*[Transmitted]*タブに移動し、設定されている場合は、MicrosoftのDynamics GP、OracleのNetSuite、SAPなどの外部システムに送信されます。 その後、販売者は、購入者への注文された商品の配送を進めることができます。 Additional information may also be added to the order, such as an updated [order status](./order-management-statuses-reference-guide.md), [shipping information](../shipments/introduction-to-shipments.md) and estimated arrival time.

![[Transmitted]タブ](./orders-menu-reference-guide/images/04.png "[Transmitted]タブ")

## 追加情報

  - [注文ワークフローの概要](../order-workflows/introduction-to-order-workflows.md)
  - [Enabling or Disabling Order Workflows](../order-workflows/enabling-or-disabling-order-workflows.md)
  - [注文の処理](./processing-an-order.md)
  - [注文管理ステータスリファレンスガイド](./order-management-statuses-reference-guide.md)
  - [Introduction to Shipments](../shipments/introduction-to-shipments.md)
