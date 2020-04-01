# 倉庫リファレンスガイド

倉庫とは、商品在庫が管理され、注文処理のために出荷される物理的な場所を表します。 ストア管理者は、倉庫ごとに在庫数を設定します。 その後、Liferay Commerceによって利用可能な在庫が計算され、倉庫全体で販売可能な在庫の総数が決定されます。

Liferay Commerceでは、地理的に異なる場所に複数の倉庫を置くことができます。

倉庫を管理するには、*[Control Panel]* → *[Commerce]* → *[Settings]*に移動します。 *[Warehouses]* タブをクリックします。

## 倉庫名と範囲

![倉庫の追加](./warehouse-reference-guide/images/01.png)

| フィールド       | 説明                             |
| ----------- | ------------------------------ |
| Name        | 倉庫の名前                          |
| Description | 追加情報                           |
| Active      | 倉庫をアクティブに指定するトグル               |
| Channels    | この倉庫が提供するすべてのチャネルのチェックボックスのリスト |

## 倉庫住所フィールド

![倉庫の住所を追加する](./warehouse-reference-guide/images/02.png)

| フィールド       | 説明                       |
| ----------- | ------------------------ |
| Street 1    | 住所の最初の行                  |
| Street 2    | 住所の2行目                   |
| Street 3    | 住所の3行目                   |
| Country     | 国を選択するドロップダウンメニュー        |
| Region      | 州または県を選択するためのドロップダウンメニュー |
| Postal Code | 郵便番号を入力するフィールド           |
| City        | 倉庫がある都市                  |

## 倉庫の位置情報フィールド

![倉庫の地理位置情報の設定](./warehouse-reference-guide/images/03.png)

| フィールド     | 説明    |
| --------- | ----- |
| Latitude  | 倉庫の緯度 |
| Longitude | 倉庫の経度 |

倉庫の地理位置情報は、距離に基づいて送料を計算するために使用されます。 詳細については、[Using the Variable Rate Shipping Method](../../orders-and-fulfillment/configuring-shipping-methods/using-the-variable-rate-shipping-method.md)を参照してください。

## 追加情報

  - [Introduction to Shipments](../../orders-and-fulfillment/managing-shipments/introduction-to-shipments.md)
  - [Adding a New Warehouse](./adding-a-new-warehouse.md)
  - [Setting Inventory by Warehouse](./setting-inventory-by-warehouse.md)
