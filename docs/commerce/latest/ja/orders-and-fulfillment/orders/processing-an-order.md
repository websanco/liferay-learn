# 注文の処理

Liferay Commerceでの注文の処理は、注文の受け入れと注文の処理の2つの段階で構成されます。 注文の処理中に出荷が作成されます。 [サブスクリプション](../subscriptions/managing-subscriptions.md) （定期的な注文）を含むすべての注文の処理を開始するには、 *コントロールパネル*の [*Orders* メニュー](./orders-menu-reference-guide.md) に移動します。

## 注文を受け付ける

ユーザーは、チェックアウトプロセスが完了すると、注文を受け入れることができます。 注場合は、その [注文ワークフロー](../order-workflows/enabling-or-disabling-order-workflows.md) 有効になっている、順番が最初に検討する必要があり、その後、 [承認（または拒否）](../order-workflows/approving-or-rejecting-orders-in-order-workflows.md)。 注文が確認されるまで、注文はチェックアウトプロセスを完了していないため、この時点では受け付けられません。

注文を受け付けるには：

1.  *コントロールパネル* → *Commerce* → *Orders*へ行きます。

    ![[注文]メニューはコントロールパネルにあります](./processing-an-order/images/01.png)

2.  *Open* タブをクリックします。

3.  注文IDをクリックします（例： *42838*）。

4.  *Accept Order* ボタンをクリックします。

    ![Accept Orderボタンをクリックして処理を開始します。](./processing-an-order/images/02.png)

注文が受け付けられ、処理できます。

## 受け付けた注文の処理

注文が受け付けられた後、3つのオプションがあります。

  - **キャンセル**：注文はキャンセルされ、[注文]メニューの[ *保留中* ]から[ *完了* ]タブに自動的に移動します。 注文を再度編集することはできず、顧客は新しい注文を送信する必要があります。
  - **保留**：追加のアクションが実行されるまで、注文の処理は一時的に停止されます（たとえば、ストアは在庫が不足している場合や、支払いの問題を解決する必要がある場合）。
  - **出荷の作成**：オーダーは [出荷の準備ができています](../shipments/introduction-to-shipments.md)。

![配送のキャンセル、保留、または作成](./processing-an-order/images/04.png)

### サブスクリプション注文の処理

*コントロールパネル*の*サブスクリプション*メニューで、すべてのサブスクリプション注文を管理できます。

![サブスクリプションメニュー](./processing-an-order/images/03.png)

詳細については、 [サブスクリプションの管理](../subscriptions/managing-subscriptions.md) 記事を参照してください。

## 出荷の作成

出荷の作成は、受け付けられた注文の処理の続きとして、[ *Orders* ]メニューから始まります。 詳細については、 [シップメントの作成](../shipments/creating-a-shipment.md) 記事を参照してください。

注文に出荷可能なアイテム（サービス契約やオンライン商品など）がない場合、ユーザーは出荷を作成できません。

## 完了した注文

お客様が購入したすべてのアイテムを受け取ったときに、注文は完了としてマークされます。 （物理的な在庫の場合、これは通常、商品が顧客に配達されたときです。） 注文がキャンセルされると、注文は完了としてマークされます。 この時点では、完了した注文を以前のステータスに戻すことはできません。

## Commerce 2.0以前

注文処理は、注文の送信と出荷の作成の2つの段階で行われます。

注文を処理するには：

1.  *コントロールパネル* → *Commerce* → *Orders*へ行きます。

2.  *保留中* タブをクリックします。

3.  注文（*40926*）をクリックします。

    ![保留中のタブをクリックして注文を処理します。](./processing-an-order/images/05.png)

4.  *概要*タブをクリックします。

5.  *Edit Order Status*クリックします 。

    ![保留中のタブをクリックして注文を処理します。](./processing-an-order/images/06.png)

6.  [Order Status] ドロップダウンメニューから [0>送信済み</em>] を選択します。

7.  完了したら、*[Save]*をクリックします。

その後、注文は[ *送信済み* ]タブに移動します。 注文が送信されると、発送の準備が整います。

詳細については、「 [Liferay Commerce 2.0および](../shipments/creating-a-shipment.md#creating-a-new-shipment-on-liferay-commerce-2-0-and-below) 未満の記事で新しい出荷を作成する」セクションを参照してください。

## 追加情報

  - [注文ワークフローの概要](../order-workflows/introduction-to-order-workflows.md)
  - [注文メニューリファレンスガイド](./orders-menu-reference-guide.md)
  - [サブスクリプションの管理](../subscriptions/managing-subscriptions.md)
  - [Introduction to Shipments](../shipments/introduction-to-shipments.md)
  - [在庫管理リファレンスガイド](../../managing-a-catalog/managing-inventory/inventory-management-reference-guide.md)
