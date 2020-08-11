# 発送の概要

[注文の処理](../orders/processing-an-order.md)一環として、最初に 注文</em> メニューで発送を作成します。 注文が処理されると、注文処理を完了するために*発送* メニューにリダイレクトされます。

``` note::
   1つの発送に複数の注文が含まれる場合がありますが、すべての注文は*同じ*アカウントで作成する必要があります。
```

*コントロールパネル* → *コマース* →* 注文*へ行きます。 発送の準備ができている注文を選択し、[ *発送を作成* ]ボタンをクリックします。

![[発送]メニュー](./introduction-to-shipments/images/04.png)

これにより、ユーザーは自動的に[発送]メニューに移動して、注文の処理を完了します。 処理、発送準備、発送、発送の4つの段階があります。

## 処理

すべてのアイテムと調達倉庫が選択されると、注文の発送準備が整います。 ここでは、発送業者、追跡番号、配達予定日などの詳細を表示できます。

![発送メニュー-処理を完了](./introduction-to-shipments/images/05.png)

## 発送準備完了

*Ship* ボタンをクリックすると、注文の処理が進みます。

![発送メニュー-発送準備](./introduction-to-shipments/images/06.png)

## Shipped

注文が発送され、配達の確認を待つと、注文は発送フェーズになります。 配信が完了したら、[ *発送*] クリックします。

![発送メニュー-発送済み](./introduction-to-shipments/images/08.png)

## 配達済み

配信が確認されると、「 *配達済み* 」タブは各フェーズを緑色で完了としてマークします。

![発送メニュー-発送済み](./introduction-to-shipments/images/09.png)

## Completed

配達が確認されると、配送プロセスは配達済みとしてマークされ、注文は完了としてマークされます。

![発送メニュー-発送準備完了](./introduction-to-shipments/images/07.png)

特定の機能とフィールドの詳細については、 [発送管理リファレンスガイド ](./shipments-management-reference-guide.md) を参照してください。

## Liferay Commerce 2.0以前

[ *発送* ]メニューでは、 [が注文を受け取った後、店長が発送を作成および追跡できます](../orders/processing-an-order.md#commerce-2-0-and-below)。

[コントロールパネル]</em> → [Commerce]</em> → [発送]</em>に移動します。

![[発送]メニュー](./introduction-to-shipments/images/01.png)

[発送]</em>メニューには、すべてのステータスの作成済みのすべての発送が表示されます。 追加（![add-icon](../../images/icon-add.png)）ボタンをクリックして、新しい発送を追加します。

特定の発送番号をクリックすると（たとえば、41250</em> ）、その発送の[Details]</em>タブと[Items]</em>タブが表示されます。 （注文番号</em>（41241</em>）をクリックすると[注文]</em>メニューにリダイレクトされます。）

### Details

[Shipment Details]</em>タブには次の情報が含まれます。

![[Shipment Details]メニュー](./introduction-to-shipments/images/02.png)

| フィールド                  | 説明                                                                                              |
| ---------------------- | ----------------------------------------------------------------------------------------------- |
| Name                   | 購入者の名前                                                                                          |
| 説明                     | 説明フィールド                                                                                         |
| Street 1               | 発送先住所の最初の行                                                                                      |
| Street 2               | 発送先住所の2行目                                                                                       |
| Street 3               | 発送先住所の3行目                                                                                       |
| City                   | 発送先住所の都市                                                                                        |
| 郵便番号                   | 発送先住所の郵便番号                                                                                      |
| Country                | 発送先住所の国                                                                                         |
| Region                 | 発送先住所の州または県                                                                                     |
| Carrier                | 運送業者の名前                                                                                         |
| Tracking Number        | **運送業者**によって生成された発送の追跡番号                                                                        |
| Shipment Status        | オプション[Processing]</em>、[Ready to be Shipped]</em>、[Shipped]</em>、[Delivered]</em>を含むドロップダウンメニュー |
| Shipping Date          | 荷物が運送業者に引き渡された日付                                                                                |
| Expected Delivery Date | 荷物の到着予定日                                                                                        |

### アイテム

[Shipment Items]</em>タブには次の情報が含まれます。

![[Shipment Items]タブ](./introduction-to-shipments/images/03.png)

| フィールド     | 説明         |
| --------- | ---------- |
| SKU       | SKU番号      |
| 名前        | アイテムの名前    |
| Quantity  | 発送するアイテムの数 |
| Warehouse | 発送元の倉庫の名前  |

さらに、[(+) Add Shipment]ボタンをクリックすると、注文にさらにアイテムを追加するためのウィンドウが開きます。

## 追加情報

  - [Creating a Shipment](./creating-a-shipment.md)
  - [Cancelling a Shipment](./cancelling-a-shipment.md)
  - [注文の処理](../orders/processing-an-order.md)
  - [発送管理リファレンスガイド](./shipments-management-reference-guide.md)
