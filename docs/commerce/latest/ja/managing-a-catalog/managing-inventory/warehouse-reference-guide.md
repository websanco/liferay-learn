# 倉庫リファレンスガイド

倉庫とは、商品在庫が管理され、注文処理のために出荷される物理的な場所を表します。 商品の在庫数量は倉庫ごとに管理できます。 その後、Liferay Commerceによって利用可能な在庫が計算され、倉庫全体で販売可能な在庫の総数が決定されます。 商品在庫を調達するには、チャネルに関連付けられた倉庫が必要です。 複数の倉庫を作成して、特定のチャネルに関連付けることができます。

在庫を管理するには、 ［**グローバルアプリケーション**］ → ［**コマース**］ → ［**設定**］に移動します。 ［**倉庫**］ タブをクリックします。

```{note}
   倉庫の設定は、Commerce 2.0または2.1を使用している場合は、_コントロールパネル_にあります。
```

<a name="warehouse-name" />

## 倉庫名

![倉庫の追加](./warehouse-reference-guide/images/01.png)

| フィールド | 説明               |
| :--- | :--- |
| 名前    | 倉庫の名前            |
| 説明    | 追加情報             |
| 有効    | 倉庫をアクティブに指定するトグル |

<a name="channels" />

## チャネル

![チャネルの選択](./warehouse-reference-guide/images/02.png)

| フィールド | 説明                             |
| :--- | :--- |
| チャネル  | この倉庫が提供するすべてのチャネルのチェックボックスのリスト |

<a name="address-fields" />

## 住所フィールド

![倉庫の住所を追加する](./warehouse-reference-guide/images/03.png)

| フィールド     | 説明                       |
| :--- | :--- |
| 番地        | 住所の最初の行                  |
| 以降の番地     | 住所の2行目                   |
| 建物名／部屋番号等 | 住所の3行目                   |
| 国         | 国を選択するドロップダウンメニュー        |
| 地域(都道府県)  | 州または県を選択するためのドロップダウンメニュー |
| 郵便番号      | 郵便番号を入力するフィールド           |
| 市区町村      | 倉庫がある都市                  |

<a name="geolocation" />

## Geolocation

![倉庫の位置情報の設定](./warehouse-reference-guide/images/04.png)

| フィールド | 説明    |
| :--- | :--- |
| 緯度    | 倉庫の緯度 |
| 経度    | 倉庫の経度 |

倉庫の位置情報は、Fedexの配送方法でチェックアウト時の送料計算に使用されます。 Commerceでは、顧客にとって最もレートの良い倉庫を利用します。

<a name="additional-information" />

## 追加情報

* [発送の概要](../../orders-and-fulfillment/shipments/introduction-to-shipments.md)
* [Commerce倉庫のセットアップ](./setting-up-commerce-warehouses.md)
* [倉庫ごとの在庫設定](./setting-inventory-by-warehouse.md)
