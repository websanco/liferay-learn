# 注文ライフサイクル

> Liferay Commerce 2.0

注文には、見込みのある取引または過去の取引に関するデータが保存されます。 この記事では、オープンカートから [フルフィルメント注文](../shipments/introduction-to-shipments.md)までの注文ライフサイクルの概要を説明します。

![注文ライフサイクル](./order-life-cycle/images/01.png)

## ステップ1：商品がカートに追加される

購入者が商品をカートに入れると、新しい注文が作成されます。 この開始点で、注文に保存されるデータには、商品のIDと数量、および注文を作成したアカウントが含まれます。

## ステップ2：発注

売り手に情報を提供し、注文の概要を確認した後、買い手は注文を出します。 注文ワークフローが有効になっている場合、送信前に注文が確認されます。 注文が処理されている間、バイヤーは注文のステータスを追跡する [通知](../../store-administration/sending-emails/store-emails.md) を受け取ることができます。

## ステップ3：注文の送信

販売者が保留中から送信済みに注文を進めると、注文書がMicrosoftのDynamics GP、OracleのNetSuite、SAPなどの外部システムに送信されます。 売り手注文受付ワークフローが有効になっている場合、売り手は購入を許可する前に承認を受ける必要があります。

## ステップ4：受注処理

販売者は残りの手順を完了して、注文された商品を購入者に配送します。 注文ステータスの更新、出荷情報、到着予定時刻などの追加情報を注文書に追加できます。 購入者と販売者は、注文が完了したことを通知されます。

## 追加情報

  - [注文ワークフローの概要](../order-workflows/introduction-to-order-workflows.md)
  - [注文の処理](./processing-an-order.md)
  - [注文メニュー](./orders-menu-reference-guide.md)
  - [出荷の概要](../shipments/introduction-to-shipments.md)
  - [メールを保存](../../store-administration/sending-emails/store-emails.md)
