# 発送の作成

シップメントの作成は[［_注文_］ メニュー](../orders/orders-menu-reference-guide.md)と統合されています。 注文が[_処理_ 段階](../orders/processing-an-order.md)になったら、［_発送_］メニューで[すべての発送を管理](./introduction-to-shipments.md)することもできます。

## 新規出荷の作成

新しい発送を作成するには：

1. ［_コントロールパネル_］ → ［_コマース_］ → ［_注文_］に移動します。
1. _保留中_ タブをクリックし、注文を選択します。

    ![注文メニュー](./creating-a-shipment/images/11.png)

1. _受注_ ボタンをクリックします。

    ![注文メニュー-注文を受け付ける](./creating-a-shipment/images/12.png)

1. 注文が受理されたら、［_発送を作成_］ボタンをクリックします。

    ![注文メニュー](./creating-a-shipment/images/10.png)

1. これにより、［発送］メニューにリダイレクトされます。

1. 追加（![Add Icon](../../images/icon-add.png)）をクリックします。
1. この注文に関連付けられているすべてのアイテムを選択します。

    ![注文メニュー](./creating-a-shipment/images/07.png)

1. ［_送信_］をクリックします 。
1. アイテムの横にある［_編集_］クリックして、数量とソーシング [倉庫を選択します](../../managing-a-catalog/managing-inventory/warehouse-reference-guide.md)。
1. ［_発送数量_］フィールドに数量を入力します。

    ![注文メニュー](./creating-a-shipment/images/08.png)

1. ［_保存_］ をクリックします。
1. ［_処理を終了_］クリックします 。

注文の発送準備が整ったら：

1. ［_編集_］をクリックして、発送予定日と配達予定を入力します。

    ![注文メニュー](./creating-a-shipment/images/09.png)

1. 準備ができたら _発送_ クリックし ます。

## Liferay Commerce 2.0以前での新規出荷の作成

Liferay Commerceの以前のバージョンで発送を作成するには：

1. ［_コントロールパネル_］ → ［_コマース_］ → ［_出荷_］に移動します。
1. 追加（![add-icon](../../images/icon-add.png)）ボタンをクリックして、新しい発送を追加します。
1. ドロップダウンメニューから注文を選択します（たとえば、 _41241_）。

    ![注文を選択](./creating-a-shipment/images/02.png)

1. ［_保存_］ をクリックします。
1. 対応する [倉庫](../../managing-a-catalog/managing-inventory/warehouse-reference-guide.md)からの数量を入力します。

    ![US NE倉庫から数量を入力](./creating-a-shipment/images/03.png)

1. ［_保存_］ をクリックします。
1. 発送先住所を確認します。

    ![発送先住所を確認](./creating-a-shipment/images/04.png)

1. ［_保存_］ をクリックします。

新しい発送が作成されました。

発送が作成されると、オーダーマネージャーが発送に対して実行できるアクションがさらにいくつかあります。

### アイテムを確認する

1. ［_アイテム_］タブをクリックして、アイテムが正しいことを確認します。

    ![アイテムの確認](./creating-a-shipment/images/05.png)

### 発送状況を更新する

1. ［_詳細_］ タブをクリックします。
1. ［_配送業者_］フィールドに運送業者の名前を入力します（例：USPS、FedEx、UPS）
1. _追跡番号_ を入力します 。
1. ［_ステータス_］ドロップダウンメニューからステータスを選択します（例： _出荷準備完了_）。
1. ［_保存_］ をクリックします。

オーダーマネージャーは、注文が完了するまで、必要に応じて発送のステータスを更新できます。

![発送商品](./creating-a-shipment/images/06.png)

## 追加情報

* [Commerce倉庫のセットアップ](../../managing-a-catalog/managing-inventory/setting-up-commerce-warehouses.md)
* [倉庫ごとの在庫設定](../../managing-a-catalog/managing-inventory/setting-inventory-by-warehouse.md)
* [注文情報](../orders/order-information.md)
* [注文の処理](../orders/processing-an-order.md)
* [倉庫リファレンスガイド](../../managing-a-catalog/managing-inventory/warehouse-reference-guide.md)
