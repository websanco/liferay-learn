# 在庫管理の概要

Liferay Commerceは、単一の店先と倉庫を持つユーザーから、複数の販売チャネルと地理的に分散した倉庫を持つ大規模な組織まで、商品在庫管理のためのツールを提供します。

在庫管理機能には、在庫の管理、倉庫、入荷見積り、在庫数低下時のアクション、倉庫ごとに在庫を設定する機能など、いくつかの重要な機能があります。

## 在庫管理

> 可用性：Commerce 2.1以降

在庫管理システムでは、ユーザーはすべての在庫を一箇所で追跡することができます。 在庫管理では、SKUごとに在庫を追跡します。同じSKUを持つすべてのアイテムは、商品カタログでどのように作成されたかにかかわらず、同じ在庫を共有します。

詳細は、[Using Inventory Management](./using-the-inventory-management-system.md)を参照してください。

## 倉庫

倉庫とは、商品在庫が管理され、注文処理のために調達される物理的な場所を表します。 商品の在庫数量は、倉庫ごとに管理されます。

在庫管理についての詳細は、[Setting Up Commerce Warehouses](./setting-up-commerce-warehouses.md)と[Warehouse Reference Guide](./warehouse-reference-guide.md)を参照してください。

ストアが商品の注文を受け付けるには、関連するチャネルに関連付けられた倉庫が必要です。 チャネルの仕組みについては、記事[「Introduction to Channels」](../../starting-a-store/channels/introduction-to-channels.md)を参照してください。

## 在庫数の見積もり

ストアの所有者は、在庫切れ商品が再び入荷したときに顧客に通知されるように入荷見積りを設定できます。

詳細は、[Availability Estimates](./availability-estimates.md)を参照してください。

## 在庫数低下時のアクション

在庫数低下時のアクションは、販売可能な商品在庫が指定されたしきい値に達したときに自動アクションを実行するように設定できます。 在庫が最小しきい値に達したときに実行されるアクションを設定する方法については、[在庫数低下時のアクション](./low-stock-action.md)を参照してください。

独自のカスタムの在庫数低下時のアクションの作成に興味のある開発者は、[カスタムの在庫不足アクティビティの実装](../../developer-guide/implementing-a-custom-low-stock-activity.md)を参照してください。

## 倉庫ごとの在庫設定

Liferay Commerceでは、在庫管理は商品SKUごとに行われます。 詳細は、記事[倉庫ごとに在庫を設定する](./setting-inventory-by-warehouse.md)を参照してください。

![在庫ライフサイクル](./introduction-to-managing-inventory/images/01.png)

## 追加情報

* [新規出荷の作成](../../orders-and-fulfillment/shipments/creating-a-shipment.md)
* [在庫管理リファレンスガイド](./inventory-management-reference-guide.md)
* [商品の在庫設定のリファレンス](./product-inventory-configuration-reference.md)
* [注文ライフサイクル](../../orders-and-fulfillment/orders/order-life-cycle.md)
* [在庫管理システムの活用](./using-the-inventory-management-system.md)
