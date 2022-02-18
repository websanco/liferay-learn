# サブスクリプション管理リファレンスガイド

サブスクリプションは **グローバルアプリケーション** の **サブスクリプション** メニューで[管理](./managing-subscriptions.md)されています。 この記事では、 **サブスクリプション** メニューで使用できるさまざまな機能とフィールドについて説明します。

5つのフィールドがあります。

| フィールド   | 説明                                    |
| :--- | :--- |
| ID      | これはサブスクリプションのIDです。                    |
| 状態      | ステータスは注文のステータスを表示します                  |
| 注文ID    | これは、サブスクリプションに基づく注文IDです。              |
| アカウントID | アカウントIDは、サブスクリプションを作成したアカウントに対応しています。 |
| アカウント名  | アカウント名はアカウント名に対応します。                  |

<a name="commerce-21-and-below" />

## Commerce 2.1以前

サブスクリプションは **コントロールパネル** の **サブスクリプションメニュー** で[管理](./managing-subscriptions.md)されています。

［**コントロールパネル**］ → ［**コマース**］ → ［**サブスクリプション**］に移動します。

![サブスクリプション管理](./subscription-administration-reference-guide/images/01.png)

5つのフィールドがあります。

| フィールド   | 説明                                    |
| :--- | :--- |
| ID      | これはサブスクリプションのIDです。                    |
| 状態      | ステータスは注文のステータスを表示します                  |
| オーダーID  | これは、サブスクリプションに基づく注文IDです。              |
| アカウントID | アカウントIDは、サブスクリプションを作成したアカウントに対応しています。 |
| アカウント名  | アカウント名はアカウント名に対応します。                  |

サブスクリプションの **ID** をクリックして、サブスクリプション注文の詳細を表示します。

<!--Need to finish this for Commerce 3.0; blocked by https://issues.liferay.com/browse/COMMERCE-4813 -->

**全般** 、 **発送** 、 **支払い** の3つのタブがあります 。

<a name="general-tab" />

## ［一般］タブ

**全般** タブには、サブスクリプション [注文](../orders/processing-an-order.md)基本的な詳細が含まれています。

### 参照順序

**Reference Order** セクションには、</a>注文に関する
情報が表示されます。</p> 

![支払いセクション](./subscription-administration-reference-guide/images/02.png)



### 支払いサブスクリプション

Payment Subscriptionセクションでは、次の情報を追跡します。

![参照](./subscription-administration-reference-guide/images/03.png)

| フィールド        | 説明                                                                   |
| :--- | :--- |
| 状態           | これはサブスクリプションのワークフローステータスです。                                          |
| 次の反復日        | これは翌日の支払い期限です。                                                       |
| サブスクリプションタイプ | これは、サブスクリプションのタイプです。                                                 |
| サブスクリプション期間  | これは、出荷の間隔を指します。                                                      |
| モード          | これは、間隔がマークされた日付を指します。 ユーザーは、オプションとして月の正確な日または **月の最後の日** を選択することもできます。 |




### 配送サブスクリプション

**配送サブスクリプション** セクションは、次の情報を追跡します。

![配送](./subscription-administration-reference-guide/images/04.png)

| フィールド        | 説明                                                                   |
| :--- | :--- |
| 状態           | これはサブスクリプションのワークフローステータスです。                                          |
| 次の繰り返し日      | これは、 [発送](./shipments/introduction-to-shipments.md) が発送された翌日です。      |
| サブスクリプションタイプ | これは、サブスクリプションのタイプです。                                                 |
| サブスクリプション期間  | これは、出荷の間隔を指します。                                                      |
| モード          | これは、間隔がマークされた日付を指します。 ユーザーは、オプションとして月の正確な日または **月の最後の日** を選択することもできます。 |




### アイテム

**アイテム** セクションには、サブスクリプション内の [商品](../../managing-a-catalog/creating-and-managing-products/products/products-overview.md) がすべてリストされます。

![アイテム](./subscription-administration-reference-guide/images/05.png)



<a name="shipments-tab" />

## 発送タブ

［**発送**］タブには、次の情報が表示されます。

![出荷](./subscription-administration-reference-guide/images/06.png)

| フィールド    | 説明                                                               |
| :--- | :--- |
| 日付を作成します | これは、注文が[作成された日です ](../orders/processing-an-order.md)。            |
| 出荷ID     | この番号は [発送](./shipments/introduction-to-shipments.md) IDとして生成されます |
| 状態       | これは [注文](../orders/orders-menu-reference-guide.md)のステータスです。      |
| オーダーID   | これは、サブスクリプションの基になる注文を指します。                                       |
| 送信先      | これはアカウントの配達先住所です。                                                |
| 追跡       | これは、運送業者の追跡番号です。                                                 |




<a name="payments-tab" />

## 支払いタブ

**支払い** タブは、注文の支払い履歴を追跡します。

![アイテム](./subscription-administration-reference-guide/images/07.png)

| フィールド  | 説明                                                    |
| :--- | :--- |
| タイプ    | これは、支払いが正常に行われたかどうかを示します。                             |
| Date   | これは、注文が[作成された日です ](../orders/processing-an-order.md)。 |
| TXN ID | これはトランザクションIDです。                                      |
| 量      | これは注文の合計金額を指します。                                      |




<a name="additional-information" />

## 追加情報

* [サブスクリプションを有効にする](../../managing-a-catalog/creating-and-managing-products/products/enabling-subscriptions-for-a-product.md)
* [サブスクリプションの管理](./managing-subscriptions.md)
* [注文の処理](../orders/processing-an-order.md)
* [出荷の概要](../shipments/introduction-to-shipments.md)
* [注文情報](../orders/order-information.md)
