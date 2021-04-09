# 倉庫リファレンスガイド

倉庫とは、商品在庫が管理され、注文処理のために出荷される物理的な場所を表します。 商品の在庫数量は倉庫ごとに管理できます。 その後、Liferay Commerceによって利用可能な在庫が計算され、倉庫全体で販売可能な在庫の総数が決定されます。 商品在庫を調達するには、チャネルに関連付けられた倉庫が必要です。 複数の倉庫を作成して、特定のチャネルに関連付けることができます。

倉庫を管理するには、*[Control Panel]* → *[Commerce]* → *[Settings]*に移動します。 *[Warehouses]* タブをクリックします。

## 倉庫名

![倉庫の追加](./warehouse-reference-guide/images/01.png)

| フィールド       | 説明               |
| ----------- | ---------------- |
| Name        | 倉庫の名前            |
| Description | 追加情報             |
| Active      | 倉庫をアクティブに指定するトグル |

## チャネル

![チャンネルの選択](./warehouse-reference-guide/images/02.png)

| フィールド | 説明                             |
| ----- | ------------------------------ |
| チャネル  | この倉庫が提供するすべてのチャネルのチェックボックスのリスト |

## 住所フィールド

![倉庫の住所を追加する](./warehouse-reference-guide/images/03.png)

| フィールド    | 説明                       |
| -------- | ------------------------ |
| Street 1 | 住所の最初の行                  |
| Street 2 | 住所の2行目                   |
| Street 3 | 住所の3行目                   |
| Country  | 国を選択するドロップダウンメニュー        |
| Region   | 州または県を選択するためのドロップダウンメニュー |
| 郵便番号     | 郵便番号を入力するフィールド           |
| City     | 倉庫がある都市                  |

## Geolocation

![倉庫の地理位置情報の設定](./warehouse-reference-guide/images/04.png)

| フィールド    | 説明    |
| -------- | ----- |
| Latitude | 倉庫の緯度 |
| 経度       | 倉庫の経度 |

倉庫の地理位置情報は、距離に基づいて送料を計算するために使用されます。 詳細については、[Using the Variable Rate Shipping Method](../../store-administration/configuring-shipping-methods/using-the-variable-rate-shipping-method.md)を参照してください。

## 追加情報

  - [Introduction to Shipments](../../orders-and-fulfillment/shipments/introduction-to-shipments.md)
  - [Adding a New Warehouse](./adding-a-new-warehouse.md)
  - [Setting Inventory by Warehouse](./setting-inventory-by-warehouse.md)
