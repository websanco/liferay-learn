# 注文メニュー

Liferay Commerceのすべての注文は、管理者が*[Orders]*メニューで表示および管理できます。 ここでは、注文は、[注文ライフサイクル](./order-life-cycle.md)の進捗状況に応じて、*[Open]*、*[Pending]*、および*[Transmitted]*タブに表示されます。

![[Orders]メニューの概要](./orders-menu/images/01.png "[Orders]メニューの概要")

この記事では、*[Open]*、*[Pending]*、および*[Transmitted]*タブの概要について説明します。

## Open

このタブでは、注文アクティビティは購入者側のみで行われます。 購入者が商品をカートに追加すると、*[Open]*タブで新しい注文が作成されます。 購入者が注文するまで、注文はこのタブに残ります。

> **注**：*承認ワークフロー*が有効になっている場合、ワークフロープロセスが完了して注文が行われるまで、注文は*[Open]*タブに残ります。

![[Open]タブ](./orders-menu/images/02.png "[Open]タブ")

## Pending

*[Pending]*タブは注文の保留場所として機能します。これは、すべての注文をすぐに送信するわけではないビジネスコンテキストで役立ちます。 購入者が注文すると、注文は*[Pending]*タブに移動します。 ここで、販売者は注文を変更、キャンセル、または送信できます。

> **注**：*送信ワークフロー*が有効になっている場合、販売者が注文を拒否して購入者に返送した場合でも、注文はワークフロープロセス全体で*[Pending]*タブに残ったままになります。

![[Pending]タブ](./orders-menu/images/03.png "[Pending]タブ")

## Transmitted

このタブでは、注文アクティビティは販売者側のみで行われます。 販売者は、注文ステータスを（手動または自動で）「*To Transmit*」ステータスから他の注文ステータスに変更することにより、*保留中*の注文を送信します。 この時点で、注文は*[Transmitted]*タブに移動し、設定されている場合は、MicrosoftのDynamics GP、OracleのNetSuite、SAPなどの外部システムに送信されます。 その後、販売者は、購入者への注文された商品の配送を進めることができます。 注文ステータスの更新、出荷情報、到着予定時刻などの追加情報を注文書に追加することもできます。

![[Transmitted]タブ](./orders-menu/images/04.png "[Transmitted]タブ")

## 追加情報

*[Orders]*メニューの注文情報は、*[Open Carts]*ウィジェット（*[Open]*タブにある注文の場合 ）および*[Orders]*ウィジェット（*[Pending]*または*[Transmitted]*タブにある注文の場合）を使用して、他のユーザー（特に購入者）に提示することもできます。 詳細については、[Pending Orders](../../creating-store-content/commerce-storefront-pages/pending-orders.md)および[Placed Orders](../../creating-store-content/commerce-storefront-pages/placed-orders.md)を参照してください。

  - [Order Life Cycle](./order-life-cycle.md)
  - [Order Information](./order-information.md)
  - [Order Workflows](./order-workflows.md)
  - [Order Management Statuses](./order-management-statuses.md)
  - [Enabling or Disabling Order Workflows](./enabling-or-disabling-order-workflows.md)
  - [Approving or Rejecting Orders in Order Workflows](./approving-or-rejecting-orders-in-order-workflows.md)
