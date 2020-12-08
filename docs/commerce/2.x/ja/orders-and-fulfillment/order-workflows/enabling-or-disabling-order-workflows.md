# 注文ワークフローの有効化または無効化

[注文ワークフロー](./introduction-to-order-workflows.md) は、チャネル別に設定できるワークフローで、注文が [処理](../orders/processing-an-order.md)される前に、買い手または売り手が事前
に承</1>認をできるようにします。 ワークフローを有効にする方法については、 [ワークフローのアクティブ化](https://learn.liferay.com/dxp/7.x/en/process-automation/workflow/using-workflows/activating-workflow.html)。を参照してください。</p> 

この記事では、注文ワークフローを有効または無効にする方法について説明します。



## ワークフローを有効にする

承認ワークフローを有効にするには：

1.  *[Control Panel]* → *[Commerce]* → *[Channels]*に移動します。
   
   ![Commerceチャネル](./enabling-or-disabling-order-workflows/images/04.png)

2.  目的のチャネル（たとえば、 *Sahara.com*）をクリックします。 （アクセラレータを使用してサイトを作成した場合、デフォルトで関連付けられたチャネルが作成されます。）

3.  *General* タブをクリックします。

4.  [バイヤー注文承認ワークフロー]ドロップダウンメニューから[ *Single Approver（Version 1）* ]を選択します。
   
   ![承認ワークフロー](./enabling-or-disabling-order-workflows/images/03.png)

5.  販売者注文受付ワークフローを有効にする場合は、ドロップダウンメニューから[ *単一承認者（バージョン1）* ]を選択します。

6.  *[Save]*をクリックします。

このチャネルのワークフロープロセスがアクティブ化されました。 両方のワークフローは同時に動作できます。



### Commerce 2.0以前

承認ワークフローを有効にするには：

1.  ストアが配置されているサイトに移動します（例： *Sahara.com*）。

2.  *Site Administration* メニューを展開し、 *Commerce* → *Settings*クリックします。
   
   ![サハラサイト設定](./enabling-or-disabling-order-workflows/images/05.png)

3.  *[Order Workflows]*タブをクリックします。

4.  *[Approval Workflow]*フィールドをクリックし、ドロップダウンで利用可能なオプションから選択します。
   
   ![承認ワークフロー](./enabling-or-disabling-order-workflows/images/01.png)

5.  *Transmission Workflow*を有効にするには、 *Transmission Workflow* フィールドをクリックして、ドロップダウンの使用可能なオプションから選択します。
   
   ![送信ワークフロー](./enabling-or-disabling-order-workflows/images/02.png)

6.  *[Save]*をクリックします。



## 注文ワークフローを無効にする

注文ワークフローを無効にするには、[ *ワークフロー* なし]オプションを選択し、[ *保存*]をクリックします。



## 追加情報

すぐに使える *シングル承認者（バージョン1）* は、利用可能な注文ワークフローです。 [カスタムワークフロー](https://learn.liferay.com/dxp/7.x/en/process-automation/workflow/introduction-to-workflow.html)を作成して、*[Order Workflows]*タブで選択できます。

  - [注文ワークフローの概要](./introduction-to-order-workflows.md)
  - [注文ワークフローでの注文の承認または拒否](approving-or-rejecting-orders-in-order-workflows.md)
  - [注文の処理](../orders/processing-an-order.md)
  - [注文ライフサイクル](../orders/order-life-cycle.md)
  - [注文管理ステータスリファレンスガイド](../orders/order-management-statuses-reference-guide.md)
