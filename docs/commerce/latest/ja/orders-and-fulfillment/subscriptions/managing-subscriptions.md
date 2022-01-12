# サブスクリプションの管理

定期購入は特別な種類の定期的な注文であり、[通常の注文](../orders/processing-an-order.md)とは別に管理され。 サブスクリプションを含むすべての注文が受け付けられ、 [Orders Menu](../orders/orders-menu-reference-guide.md)処理されます。 ただし、 **サブスクリプション** 注文が受理されると、 [サブスクリプションメニュー](./subscription-administration-reference-guide.md)表示されます。

<a name="fulfilling-a-subscription-order" />

## サブスクリプション注文の履行

Commerce 3.0では、 **注文** および **サブスクリプション** は **グローバルアプリケーション** メニューで管理されています。

行われたサブスクリプション注文の履行を開始するには：

1. ［**グローバルアプリケーション**］メニュー &rarr; ［**Commerce**］&rarr; ［**注文**］に移動します。
1. **Order ID** をクリックして開始します。
1. **保留中** ボタンをクリックします。 これにより、注文が保留中ステータスに移行します。

    ![未処理の注文タブ](./managing-subscriptions/images/07.png)

1. **受注** ボタンをクリックします。 これにより、注文が処理中ステータスに移行します。

    ![注文を受け付ける](./managing-subscriptions/images/02.png)

1. サブスクリプションの注文が受け入れられたら、手順に従って出荷を作成します。 詳細は、 [シップメントの作成](../shipments/creating-a-shipment.md) を参照してください。

注文が受理されると、 [サブスクリプションメニュー](./subscription-administration-reference-guide.md)表示されます。

<a name="managing-a-subscription-order" />

## サブスクリプション注文の管理

1. ［**コントロールパネル**］ → ［**コマース**］ → ［**サブスクリプション**］に移動します。

    ![サブスクリプションメニュー](./managing-subscriptions/images/03.png)

2. **ID**（43811）をクリックします。 このIDはサブスクリプションに固有です。 （ [通常の注文](../orders/processing-an-order.md) または [出荷](../shipments/introduction-to-shipments.md) IDとは異なります。）
3. **全般** タブで、支払いまたは配送オプションのいずれかに変更を加えます。 たとえば、［配送サブスクリプション］の下の **次の反復日** を以前の日付に変更します。

    ![次の配達日を変更できます。](./managing-subscriptions/images/04.png)

4. ［**保存**］ボタンをクリックします。

### サブスクリプション注文の一時停止またはキャンセル

サブスクリプション注文を一時停止またはキャンセルするには：

1. ［**全般**］タブで、［**支払いサブスクリプション** セクションの［**ステータス**］ドロップダウンメニューから［**停止** または［**キャンセル**］を選択します。
1. **Nver Ends** トグルを **NO** 切り替えます。
1. **Ends After** フィールドに **1** を入力します。
1. 次に、「**配送サブスクリプション** セクションまでスクロールします。
1. **Status** のドロップダウンメニューから、 ［**中断済み**］ または ［**キャンセル済み**］ を選択します。

    ![注文の一時停止またはキャンセル](./managing-subscriptions/images/08.png)

1. **Nver Ends** トグルを **NO** 切り替えます。
1. **Ends After** フィールドに **1** を入力します。
1. ［**保存**］をクリックして変更を適用します。

### 発送ステータスの追跡

1. **Shipments** タブをクリックします。 ここから、店長は出荷ステータスを追跡できます。

    ![配送状況の追跡を変更できます。](managing-subscriptions/images/05.png)

### 支払い履歴の追跡

1. **支払い** タブをクリックします。 ここから、店舗管理者は支払い履歴を追跡できます。

    ![支払い履歴を追跡できます。](managing-subscriptions/images/06.png)

<a name="configuring-subscriptions" />

## サブスクリプションの構成

サブスクリプションを構成するには：

1. ［**コントロールパネル**］ → ［**設定**］ → ［**システム設定**］に移動します。

    ![コントロールパネル-システム設定](managing-subscriptions/images/09.png)

1. **Commerce** までスクロールし、 **カタログ** をクリックします。
1. 左側のメニューで［**サブスクリプション**］をクリックします。

    ![コントロールパネル-システム設定](managing-subscriptions/images/10.png)

    次の使用可能な構成を確認します。
      ***更新チェック間隔** システムが更新チェックする頻度を決定します。 --> 。
      ***サブスクリプションのキャンセルを許可** チェックした場合、ユーザーはサブスクリプションをキャンセルできます。
      ***サブスクリプションの一時停止を許可** チェックした場合、ユーザーはサブスクリプションを一時停止できます。

1. **保存** ボタンをクリックして変更を適用します。

<a name="commerce-21-and-below" />

## Commerce 2.1以前

コマース注文とサブスクリプションは、 ［**コントロールパネル**］で管理されています。

1. ［**コントロールパネル**］ → ［**コマース**］ → ［**注文**］に移動します。
1. **オープン** タブをクリックします。

    ![未処理の注文タブ](./managing-subscriptions/images/01.png)

1. **Order ID** をクリックして開始します。
1. **保留中** ボタンをクリックします。 これにより、注文が保留中ステータスに移行します。

    ![未処理の注文タブ](./managing-subscriptions/images/07.png)

1. **受注** ボタンをクリックします。 これにより、注文が処理中ステータスに移行します。

    ![注文を受け付ける](./managing-subscriptions/images/02.png)

1. サブスクリプションの注文が受け入れられたら、手順に従って出荷を作成します。 詳細は、 [シップメントの作成](../shipments/creating-a-shipment.md) を参照してください。

注文が受理されると、 [サブスクリプションメニュー](./subscription-administration-reference-guide.md)表示されます。

<a name="additional-information" />

## 追加情報

* [サブスクリプション管理リファレンスガイド](./subscription-administration-reference-guide.md)
* [商品のサブスクリプションを有効にする](../../managing-a-catalog/creating-and-managing-products/products/enabling-subscriptions-for-a-product.md)
* [注文の処理](../orders/processing-an-order.md)
* [注文メニューリファレンスガイド](../orders/orders-menu-reference-guide.md)
* [発想の概要](../shipments/introduction-to-shipments.md)
