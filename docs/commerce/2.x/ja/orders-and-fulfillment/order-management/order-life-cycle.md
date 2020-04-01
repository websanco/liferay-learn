# 注文ライフサイクル

注文には、見込みのある取引または過去の取引に関するデータが保存されます。 この記事では、カートのオープンから注文の発送までの注文ライフサイクルの概要を説明します。

![注文ライフサイクル](./order-life-cycle/images/01.png)

> **図1**. 注文ライフサイクル

## ステップ1：商品がカートに追加される

購入者が商品をカートに入れると、新しい注文が作成されます。 この開始点で、注文に保存されるデータには、商品のIDと数量、および注文を作成したアカウントが含まれます。

## ステップ2：発注

配送先住所や支払い方法などの情報を販売者に提供し、注文の概要を確認した後、購入者が注文します。

## ステップ3：注文の送信

販売者が保留中から送信済みに注文を進めると、注文書がMicrosoftのDynamics GP、OracleのNetSuite、SAPなどの外部システムに送信されます。

## ステップ4：受注処理

販売者は残りの手順を完了して、注文された商品を購入者に配送します。 注文ステータスの更新、出荷情報、到着予定時刻などの追加情報を注文書に追加できます。

## 追加情報

**注1 ** ：上記のワークフローを変更したり追加のステップを追加する[注文ワークフロー](./order-workflows.md)を実装できます。

  - [Orders Menu](./orders-menu.md)
  - [Order Information](./order-information.md)
  - [Order Workflows](./order-workflows.md)
  - [Order Management Statuses](./order-management-statuses.md)
  - [Enabling or Disabling Order Workflows](./enabling-or-disabling-order-workflows.md)
  - [Approving/Rejecting Orders in Order Workflows](./approving-or-rejecting-orders-in-order-workflows.md)
