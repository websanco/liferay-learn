# サブスクリプションの管理

定期購入は特別な種類の定期的な注文であり、[通常の注文](../orders/processing-an-order.md)とは別に管理され。 サブスクリプションを含むすべての注文が受け付けられ、 [Orders Menu](../orders/orders-menu-reference-guide.md)処理されます。 ただし、 *サブスクリプション* 注文が受理されると、 [サブスクリプションメニュー](./subscription-administration-reference-guide.md)表示されます。

## サブスクリプション注文の履行

行われたサブスクリプション注文の履行を開始するには：

1.  *コントロールパネル* → *Commerce* → *Orders*へ行きます。

2.  *オープン* タブをクリックします。

    ![未処理の注文タブ](./managing-subscriptions/images/01.png)

3.  *Order ID* をクリックして開始します。

4.  *保留中* ボタンをクリックします。 これにより、注文が保留中ステータスに移行します。

    ![注文オープンタブ](./managing-subscriptions/images/07.png)

5.  *注文を承認* ボタンをクリックします。 これにより、注文が処理中ステータスに移行します。

    ![注文を受け付ける](./managing-subscriptions/images/02.png)

6.  サブスクリプションの注文が受け入れられたら、手順に従って出荷を作成します。 詳細については、 [シップメントの作成](../shipments/creating-a-shipment.md) を参照してください。

注文が受理されると、 [サブスクリプションメニュー](./subscription-administration-reference-guide.md)表示されます。

## サブスクリプション注文の管理

1.  *コントロールパネル* → *Commerce* → *サブスクリプション*ます。

    ![サブスクリプションメニュー](./managing-subscriptions/images/03.png)

2.  *ID* （43811）をクリックします。 このIDはサブスクリプションに固有です。 （ [通常の注文](../orders/processing-an-order.md) または [出荷](../shipments/introduction-to-shipments.md) IDとは異なります。）

3.  *全般* タブで、支払いまたは配送オプションのいずれかに変更を加えます。 たとえば、[配送サブスクリプション]の下の *次の反復日* を以前の日付に変更します。

    ![次の配達日を変更できます。](./managing-subscriptions/images/04.png)

4.  *[Save]*ボタンをクリックします。

### サブスクリプション注文の一時停止またはキャンセル

サブスクリプション注文を一時停止またはキャンセルするには：

1.  [ *全般* ]タブで、[ *支払いサブスクリプション* セクションの[ *ステータス* ]ドロップダウンメニューから[ *停止* または[ *キャンセル* ]を選択します。

2.  *Nver Ends * トグルを *NO*切り替えます。

3.  *Ends After* フィールドに **1** を入力します。

4.  次に、「 *配送サブスクリプション* セクションまでスクロールします。

5.  \ _Statusドロップダウンメニューから *Suspended* または *Canceled* を選択します。

    ![注文の一時停止またはキャンセル](./managing-subscriptions/images/08.png)

6.  *Never Ends* トグルを *NO*切り替えます。

7.  *Ends After* フィールドに **1** を入力します。

8.  *[Save]*をクリックして変更を適用します。

### 発送ステータスの追跡

1.  *Shipments* タブをクリックします。 ここから、店長は出荷ステータスを追跡できます。

    ![配送状況の追跡を変更できます。](managing-subscriptions/images/05.png)

### 支払い履歴の追跡

1.  *支払い* タブをクリックします。 ここから、店舗管理者は支払い履歴を追跡できます。

    ![支払い履歴を追跡できます。](managing-subscriptions/images/06.png)

## サブスクリプションの構成

サブスクリプションを構成するには：

1.  *[Control Panel]* → *[Configuration]* → *[System Settings]*に移動します。

    ![コントロールパネル-システム設定](managing-subscriptions/images/09.png)

2.  *Commerce* までスクロールし、 *カタログ*をクリックします。

3.  左側のメニューで[ *サブスクリプション* ]をクリックします。

    ![コントロールパネル-システム設定](managing-subscriptions/images/10.png)

    次の使用可能な構成を確認します。

      - **更新チェック間隔** システムが更新チェックする頻度を決定します。 ->。
      - **サブスクリプションのキャンセルを許可** チェックした場合、ユーザーはサブスクリプションをキャンセルできます。
      - **サブスクリプションの一時停止を許可** チェックした場合、ユーザーはサブスクリプションを一時停止できます。

4.  *Save* ボタンをクリックして変更を適用します。

## 追加情報

  - [サブスクリプション管理リファレンスガイド](./subscription-administration-reference-guide.md)
  - [商品のサブスクリプションを有効にする](../../managing-a-catalog/creating-and-managing-products/products/enabling-subscriptions-for-a-product.md)
  - [注文の処理](../orders/processing-an-order.md)
  - [注文メニューリファレンスガイド](../orders/orders-menu-reference-guide.md)
  - [Introduction to Shipments](../shipments/introduction-to-shipments.md)
