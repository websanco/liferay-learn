# 注文ワークフローの有効化または無効化

[注文ワークフロー](./introduction-to-order-workflows.md) は、チャネル別に設定できるワークフローで、注文が [処理](../orders/processing-an-order.md)される前に、買い手または売り手が事前
に [承認](./approving-or-rejecting-orders-in-order-workflows.md) をできるようにします。 ワークフローを有効にする方法については、 [ワークフローのアクティブ化](https://learn.liferay.com/dxp/latest/ja/process-automation/workflow/using-workflows/activating-workflow.html) 。を参照してください。

この記事では、注文ワークフローを有効または無効にする方法について説明します。



## ワークフローを有効にする

承認ワークフローを有効にするには：

1. ［**グローバルアプリケーション**］メニュー &rarr; ［**Commerce**］&rarr; ［**チャネル**］に移動します。
1. 目的のチャネル（たとえば、 **Sahara.com**）を選択します。 （アクセラレータを使用してサイトを作成した場合、デフォルトで関連付けられたチャネルが作成されます。）
1. **General** タブをクリックします。
1. ［バイヤー注文承認ワークフロー］ドロップダウンメニューから［**単一の承認者（バージョン１**）］を選択します。
   
   ![チャネルメニューで発注承認ワークフローを有効にします。](./enabling-or-disabling-order-workflows/images/06.png)

1. 販売者注文受付ワークフローを有効にする場合は、ドロップダウンメニューから[**単一承認者（バージョン1**）]を選択します。

1. ［**保存**］ をクリックします。

このチャネルのワークフロープロセスがアクティブ化されました。 両方のワークフローは同時に動作できます。



### Commerce 2.1以前

1. ［**コントロールパネル**］ → ［**コマース**］ → ［**チャネル**］に移動します。
   
   ![Commerceメニューは、 コントロールパネルにあります。](./enabling-or-disabling-order-workflows/images/04.png)

1. 目的のチャネル（たとえば、 **Sahara.com**）をクリックします。 （アクセラレータを使用してサイトを作成した場合、デフォルトで関連付けられたチャネルが作成されます。）

1. **一般** タブをクリックします。
1. ［バイヤー注文承認ワークフロー］ドロップダウンメニューから［**単一の承認者（バージョン１**）］を選択します。
   
   ![チャネルごとに許可ワークフローを有効にすることができます。](./enabling-or-disabling-order-workflows/images/03.png)

1. 販売者注文受付ワークフローを有効にする場合は、ドロップダウンメニューから[**単一承認者（バージョン1**）]を選択します。

1. ［**保存**］ をクリックします。

このチャネルのワークフロープロセスがアクティブ化されました。 両方のワークフローは同時に動作できます。



### Commerce 2.0以前

承認ワークフローを有効にするには：

1. ストアが配置されているサイトに移動します（例： **Sahara.com**）。
1. **サイト管理** メニューを展開し、 ［**コマース**］ → ［**設定**］クリックします。
   
   ![Commerce 2.0のワークフローは、サイト設定にあります](./enabling-or-disabling-order-workflows/images/05.png)

1. ［**注文ワークフロー**］タブをクリックします。

1. ［**許可ワークフロー**］フィールドをクリックし、ドロップダウンで利用可能なオプションから選択します。
   
   ![注文ワークフロータブでバイヤー承認ワークフローを有効にします。](./enabling-or-disabling-order-workflows/images/01.png)

1. **ワークフロー遷移** を有効にするには、 **ワークフロー遷移** フィールドをクリックして、ドロップダウンの使用可能なオプションから選択します。
   
   ![売り手ワークフロー遷移を有効にします。](./enabling-or-disabling-order-workflows/images/02.png)

1. ［**保存**］ をクリックします。



## 注文ワークフローを無効にする

注文ワークフローを無効にするには、［**ワークフロー** なし］オプションを選択し、［**保存**］をクリックします。



## 追加情報

すぐに使える **シングル承認者（バージョン1**） は、利用可能な注文ワークフローです。 [カスタムワークフロー](https://learn.liferay.com/dxp/latest/ja/process-automation/workflow/introduction-to-workflow.html) を作成して、 **注文ワークフロー** タブで選択できます。

* [注文ワークフローの概要](./introduction-to-order-workflows.md)
* [注文ワークフローでの注文の承認または拒否](approving-or-rejecting-orders-in-order-workflows.md)
* [注文の処理](../orders/processing-an-order.md)
* [注文ライフサイクル](../orders/order-life-cycle.md)
* [注文管理ステータスリファレンスガイド](../orders/order-management-statuses-reference-guide.md)
