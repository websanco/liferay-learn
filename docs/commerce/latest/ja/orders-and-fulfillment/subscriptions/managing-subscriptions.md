# サブスクリプションの管理

定期購入は特別な種類の定期的な注文であり、[通常の注文](../orders/processing-an-order.md)とは別に管理され。 サブスクリプションを含むすべての注文が受け付けられ、 [Orders Menu](../orders/orders-menu-reference-guide.md)処理されます。 ただし、 *サブスクリプション* 注文が受理されると、 [サブスクリプションメニュー](./subscription-administration-reference-guide.md)表示されます。

## サブスクリプション注文の履行

Commerce 3.0では、 _注文_ および _サブスクリプション_は_グローバルアプリケーション_ メニューで管理されています。

行われたサブスクリプション注文の履行を開始するには：

1. ［_グローバルアプリケーション_］メニュー &rarr; ［_Commerce_ ］&rarr; ［_注文_］に移動します。
1. _Order ID_ をクリックして開始します。
1. _保留中_ ボタンをクリックします。 これにより、注文が保留中ステータスに移行します。

    ![未処理の注文タブ](./managing-subscriptions/images/07.png)

1. _受注_ ボタンをクリックします。 これにより、注文が処理中ステータスに移行します。

    ![注文を受け付ける](./managing-subscriptions/images/02.png)

1. サブスクリプションの注文が受け入れられたら、手順に従って出荷を作成します。 詳細は、 [シップメントの作成](../shipments/creating-a-shipment.md) を参照してください。

注文が受理されると、 [サブスクリプションメニュー](./subscription-administration-reference-guide.md)表示されます。

## サブスクリプション注文の管理

1. _［コントロールパネル］_ → _［コマース］_ → _［サブスクリプション］_に移動します。

    ![サブスクリプションメニュー](./managing-subscriptions/images/03.png)

2. _ID_ （43811）をクリックします。 このIDはサブスクリプションに固有です。 （ [通常の注文](../orders/processing-an-order.md) または [出荷](../shipments/introduction-to-shipments.md) IDとは異なります。）
3. _全般_ タブで、支払いまたは配送オプションのいずれかに変更を加えます。 たとえば、［配送サブスクリプション］の下の _次の反復日_ を以前の日付に変更します。

    ![次の配達日を変更できます。](./managing-subscriptions/images/04.png)

4. _［保存］_ボタンをクリックします。

### サブスクリプション注文の一時停止またはキャンセル

サブスクリプション注文を一時停止またはキャンセルするには：

1. ［ _全般_ ］タブで、［ _支払いサブスクリプション_ セクションの［ _ステータス_ ］ドロップダウンメニューから［ _停止_ または［ _キャンセル_ ］を選択します。
1. _Nver Ends _ トグルを _NO_切り替えます。
1. _Ends After_ フィールドに **1** を入力します。
1. 次に、「 _配送サブスクリプション_ セクションまでスクロールします。
1. _Statusのドロップダウンメニューから、 _［中断済み］_ または _［キャンセル済み］_ を選択します。

    ![注文の一時停止またはキャンセル](./managing-subscriptions/images/08.png)

1. _Nver Ends _ トグルを _NO_切り替えます。
1. _Ends After_ フィールドに **1** を入力します。
1. _［保存］_をクリックして変更を適用します。

### 発送ステータスの追跡

1. _Shipments_ タブをクリックします。 ここから、店長は出荷ステータスを追跡できます。

    ![配送状況の追跡を変更できます。](managing-subscriptions/images/05.png)

### 支払い履歴の追跡

1. _支払い_ タブをクリックします。 ここから、店舗管理者は支払い履歴を追跡できます。

    ![支払い履歴を追跡できます。](managing-subscriptions/images/06.png)

## サブスクリプションの構成

サブスクリプションを構成するには：

1. _［コントロールパネル］_ → _［設定］_ → _［システム設定］_に移動します。

    ![コントロールパネル-システム設定](managing-subscriptions/images/09.png)

1. _Commerce_ までスクロールし、 _カタログ_をクリックします。
1. 左側のメニューで［ _サブスクリプション_ ］をクリックします。

    ![コントロールパネル-システム設定](managing-subscriptions/images/10.png)

    次の使用可能な構成を確認します。
      * **更新チェック間隔** システムが更新チェックする頻度を決定します。 -->。
      * **サブスクリプションのキャンセルを許可** チェックした場合、ユーザーはサブスクリプションをキャンセルできます。
      * **サブスクリプションの一時停止を許可** チェックした場合、ユーザーはサブスクリプションを一時停止できます。

1. _保存_ ボタンをクリックして変更を適用します。

## Commerce 2.1以前

コマース注文とサブスクリプションは、 _［コントロールパネル］_で管理されています。

1. _［コントロールパネル］_ → _［コマース］_ → _［注文］_に移動します。
1. _オープン_ タブをクリックします。

    ![未処理の注文タブ](./managing-subscriptions/images/01.png)

1. _Order ID_ をクリックして開始します。
1. _保留中_ ボタンをクリックします。 これにより、注文が保留中ステータスに移行します。

    ![未処理の注文タブ](./managing-subscriptions/images/07.png)

1. _受注_ ボタンをクリックします。 これにより、注文が処理中ステータスに移行します。

    ![注文を受け付ける](./managing-subscriptions/images/02.png)

1. サブスクリプションの注文が受け入れられたら、手順に従って出荷を作成します。 詳細は、 [シップメントの作成](../shipments/creating-a-shipment.md) を参照してください。

注文が受理されると、 [サブスクリプションメニュー](./subscription-administration-reference-guide.md)表示されます。

## 追加情報

* [サブスクリプション管理リファレンスガイド](./subscription-administration-reference-guide.md)
* [商品のサブスクリプションを有効にする](../../managing-a-catalog/creating-and-managing-products/products/enabling-subscriptions-for-a-product.md)
* [注文の処理](../orders/processing-an-order.md)
* [注文メニューリファレンスガイド](../orders/orders-menu-reference-guide.md)
* [発想の概要](../shipments/introduction-to-shipments.md)
