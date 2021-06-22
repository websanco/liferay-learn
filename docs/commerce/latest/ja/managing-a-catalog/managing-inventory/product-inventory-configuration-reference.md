# 商品在庫設定リファレンス

カタログ内の各商品は、ストアフロントに在庫データを表示するように設定できます。 管理者は、在庫が少ない場合に、各アイテムの在庫を「在庫あり」または「在庫なし」として表示し、在庫の合計を表示できます。

在庫切れしきい値は、商品をいつ再発注する必要があるかを示し、在庫の販売可能数量から差し引かれ、取り寄せ注文をサポートするように設定できます。 管理者は取り寄せ注文を許可し、注文の最大量を設定できます。

各商品の在庫を管理するには、[商品の *構成* ]タブに移動します。

![在庫の商品構成](./product-inventory-configuration-reference/images/02.png)

| フィールド                    | 説明                                                    |
| ------------------------ | ----------------------------------------------------- |
| Inventory Engine         | 必要なインベントリエンジンを選択するドロップダウンメニュー。拡張ポイントを使用してカスタマイズできます   |
| Availability Estimate    | 在庫が枯渇した場合に商品を再供給するのに必要な時間の長さを選択するドロップダウンメニュー          |
| Display Availability     | 商品の販売可否を表示するかどうかを選択するトグル                              |
| Display Stock Quantity   | 販売可能な在庫の残量を表示するかどうかを選択するトグル                           |
| Low Stock Threshold      | 在庫がないために注文を処理できなくなるまでの残りの商品の数                         |
| Low Stock Action         | カタログに商品を表示し続けるか、非公開にするかを選択するドロップダウンメニュー               |
| Allow Back Orders        | 商品が在庫切れの場合でも注文を許可するかどうかを選択するトグル                       |
| Minimum Order Quantity   | 購入者に求められる最低購入数量                                       |
| Maximum Order Quantity   | 購入者が購入できる最大アイテム数                                      |
| Allowed Order Quantities | 注文数量は入力された数字に制限されます。 2、4、6を入力すると、購入者はそれらの数量のみを購入できます。 |
| Multiple Order Quantity  | 入力した数の倍数の注文数量のみを許可します。                                |

## Commerce 2.0以前

各商品の在庫を管理するには、商品の*[Configuration]*タブに移動し、左側のメニューで*[Inventory]*をクリックします。

![在庫不足アクションの商品設定](./product-inventory-configuration-reference/images/01.png "在庫不足アクションの商品設定")

## 追加情報

  - [Implementing a Custom Low Stock Activity](../../developer-guide/implementing-a-custom-low-stock-activity.md)
  - [Low Stock Action](./low-stock-action.md)
