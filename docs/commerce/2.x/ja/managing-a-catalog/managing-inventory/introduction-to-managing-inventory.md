# 在庫管理の概要

Liferay Commerceは、複数の倉庫とストアを持つ組織に、ストアが1つの販売業者向けの商品在庫管理ツールを提供します。 ストア管理者はこれらの機能を使用して、販売数量を管理し、出荷を処理して注文を完了することができます。

在庫管理機能には、倉庫、入荷見積り、在庫不足アクション、倉庫ごとに在庫を設定する機能など、いくつかの重要な機能があります。

## 倉庫

倉庫とは、商品在庫が管理され、注文処理のために調達される物理的な場所を表します。

倉庫の管理方法の詳細については、[Warehouse Reference Guide](./warehouse-reference-guide.md)および記事[「Adding a New Warehouse」](./adding-a-new-warehouse.md)を参照してください。

倉庫は、少なくとも1つのチャネルに関連付けられている必要があります。 チャネルの仕組みについては、記事[「Introduction to Channels」](../creating-and-managing-products/introduction-to-channels.md)を参照してください。

## 入荷見積り

ストアの所有者は、在庫切れ商品が再び入荷したときに顧客に通知されるように入荷見積りを設定できます。

詳細については、[Availability Estimates](./availability-estimates.md)を参照してください。

## 在庫不足アクション

在庫不足アクションは、販売可能な商品在庫が指定されたしきい値に達したときに自動アクションを実行するように設定できます。 在庫が最小しきい値に達したときに実行されるアクションを設定する方法については、[Low Stock Action](./low-stock-action.md)を参照してください。

独自のカスタムの在庫不足アクションの作成に興味のある開発者は、[Implementing a Custom Low Stock Activity](../../developer-guide/tutorials/implementing-a-custom-low-stock-activity.md)を参照してください。

## 倉庫ごとの在庫の設定

Liferay Commerceでは、在庫管理は商品SKUごとに行われます。 詳細については、記事[「Setting Inventory by Warehouse」](./setting-inventory-by-warehouse.md)を参照してください。

## 追加情報

  - [Creating a New Shipment](../../orders-and-fulfillment/managing-shipments/creating-a-shipment.md)
  - [Product Inventory Configuration Reference](./product-inventory-configuration-reference.md)
  - [Order Life Cycle](../../orders-and-fulfillment/order-management/order-life-cycle.md)
