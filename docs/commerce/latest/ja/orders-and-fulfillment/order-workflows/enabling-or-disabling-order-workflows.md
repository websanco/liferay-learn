# 注文ワークフローの有効化または無効化

[注文ワークフロー](./introduction-to-order-workflows.md) は、チャネル別に設定できるワークフローで、注文が [処理](../orders/processing-an-order.md)される前に、買い手または売り手が事前
に承</1>認をできるようにします。 ワークフローを有効にする方法については、 [ワークフローのアクティブ化](https://learn.liferay.com/dxp/latest/en/process-automation/workflow/using-workflows/activating-workflow.html)。を参照してください。</p> 

この記事では、注文ワークフローを有効または無効にする方法について説明します。



## ワークフローを有効にする

承認ワークフローを有効にするには：

1. ［_グローバルアプリケーション_］メニュー &rarr; ［_Commerce_ ］&rarr; ［_ チャネル_］に移動します。
1. 目的のチャネル（たとえば、 _Sahara.com_）を選択します。 （アクセラレータを使用してサイトを作成した場合、デフォルトで関連付けられたチャネルが作成されます。）
1. _General_ タブをクリックします。
1. ［バイヤー注文承認ワークフロー］ドロップダウンメニューから［ _単一の承認者（バージョン１）_ ］を選択します。
   
   ![チャネルメニューで発注承認ワークフローを有効にします。](./enabling-or-disabling-order-workflows/images/06.png)

1. 販売者注文受付ワークフローを有効にする場合は、ドロップダウンメニューから[ _単一承認者（バージョン1）_ ]を選択します。

1. _［保存］_ をクリックします。

このチャネルのワークフロープロセスがアクティブ化されました。 両方のワークフローは同時に動作できます。



### Commerce 2.1以前

1. _［コントロールパネル］_ → _［コマース］_ → _［チャネル］_に移動します。
   
   ![Commerceメニューは、 コントロールパネルにあります。](./enabling-or-disabling-order-workflows/images/04.png)

1. 目的のチャネル（たとえば、 _Sahara.com_）をクリックします。 （アクセラレータを使用してサイトを作成した場合、デフォルトで関連付けられたチャネルが作成されます。）

1. _一般_ タブをクリックします。
1. ［バイヤー注文承認ワークフロー］ドロップダウンメニューから［ _単一の承認者（バージョン１）_ ］を選択します。
   
   ![チャネルごとに許可ワークフローを有効にすることができます。](./enabling-or-disabling-order-workflows/images/03.png)

1. 販売者注文受付ワークフローを有効にする場合は、ドロップダウンメニューから[ _単一承認者（バージョン1）_ ]を選択します。

1. _［保存］_ をクリックします。

このチャネルのワークフロープロセスがアクティブ化されました。 両方のワークフローは同時に動作できます。



### Commerce 2.0以前

承認ワークフローを有効にするには：

1. ストアが配置されているサイトに移動します（例： _Sahara.com_）。
1. _サイト管理_ メニューを展開し、 _［コマース］_ → _［設定］_クリックします。
   
   ![Commerce 2.0のワークフローは、サイト設定にあります](./enabling-or-disabling-order-workflows/images/05.png)

1. _［注文ワークフロー］_タブをクリックします。

1. _［許可ワークフロー］_フィールドをクリックし、ドロップダウンで利用可能なオプションから選択します。
   
   ![注文ワークフロータブでバイヤー承認ワークフローを有効にします。](./enabling-or-disabling-order-workflows/images/01.png)

1. _ワークフロー遷移_を有効にするには、 _ワークフロー遷移_ フィールドをクリックして、ドロップダウンの使用可能なオプションから選択します。
   
   ![売り手ワークフロー遷移を有効にします。](./enabling-or-disabling-order-workflows/images/02.png)

1. _［保存］_ をクリックします。



## 注文ワークフローを無効にする

注文ワークフローを無効にするには、［ _ワークフロー_ なし］オプションを選択し、［ _保存_］をクリックします。



## 追加情報

すぐに使える _シングル承認者（バージョン1）_ は、利用可能な注文ワークフローです。 [カスタムワークフロー](https://learn.liferay.com/dxp/latest/en/process-automation/workflow/introduction-to-workflow.html)を作成して、_注文ワークフロー_タブで選択できます。

* [注文ワークフローの概要](./introduction-to-order-workflows.md)
* [注文ワークフローでの注文の承認または拒否](approving-or-rejecting-orders-in-order-workflows.md)
* [注文の処理](../orders/processing-an-order.md)
* [注文ライフサイクル](../orders/order-life-cycle.md)
* [注文管理ステータスリファレンスガイド](../orders/order-management-statuses-reference-guide.md)
