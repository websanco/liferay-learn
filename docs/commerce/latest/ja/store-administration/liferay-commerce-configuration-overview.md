# Liferay Commerce設定の概要

Liferay Commerce 3.0の設定は、 ［**グローバルアプリケーション**］ メニューから行えます。

![グローバルアプリケーションメニューからCommerce 3.0にアクセスします。](./liferay-commerce-configuration-overview/images/06.png)

［**グローバルアプリケーション**］ メニュー &rarr; ［**コマース**］ に移動します。 管理者は以下を構成できます：

* [チャネル](../starting-a-store/channels/managing-channels.md)
* [価格表](../managing-a-catalog/managing-prices/creating-a-price-list.md)
* [割引](../promoting-products/introduction-to-discounts.md)
* プロモーション
* 商品グループ
* [カタログ](../managing-a-catalog/catalogs/creating-a-new-catalog.md)
* [商品](../managing-a-catalog/creating-and-managing-products/products/products-overview.md)
* [オプション](../managing-a-catalog/creating-and-managing-products/products/using-product-options.md)
* [注文](../orders-and-fulfillment/orders/orders-menu-reference-guide.md)
* [出荷](../orders-and-fulfillment/shipments/introduction-to-shipments.md)
* [素材リスト](../managing-a-catalog/creating-and-managing-products/products/managing-boms.md)
* [サブスクリプション](../orders-and-fulfillment/subscriptions/managing-subscriptions.md)
* [通貨](../store-administration/currencies/adding-a-new-currency.md)
* [在庫](../managing-a-catalog/managing-inventory/introduction-to-managing-inventory.md)
* [倉庫](../managing-a-catalog/managing-inventory/warehouse-reference-guide.md)
* [在庫数の見積もり](../managing-a-catalog/managing-inventory/availability-estimates.md)

<a name="liferay-commerce-21-and-below" />

## Liferay Commerce 2.1以前

Liferay Commerce 2.1の設定は、Liferay ［**商品メニュー**］ のいくつかの場所に分かれています。 Commerceの ［**コントロールパネル**］ ドロップダウンには、Liferay Commerceのインストールに **グローバル** に適用される構成と設定が含まれています。 Commerceの ［**サイトメニュー**］ ドロップダウンには、特定のストアサイトを対象とする構成と設定が含まれています。

### Commerceのコントロールパネル

［**コントロールパネル**］ → ［**コマース**］ に移動します。 以下の設定のいずれかに変更を加えると、ストアサイト全体に適用されます。

![グローバルCommerce設定](./liferay-commerce-configuration-overview/images/01.png)

* [チャネル](../starting-a-store/channels/managing-channels.md)
* [価格表](../managing-a-catalog/managing-prices/creating-a-price-list.md)
* [注文](../orders-and-fulfillment/orders/orders-menu-reference-guide.md)

［コマース Global Settings］メニューには、次のタブが含まれています。

![Commerceのグローバル設定タブ](./liferay-commerce-configuration-overview/images/02.png)

* [在庫数の見積もり](../managing-a-catalog/managing-inventory/availability-estimates.md)
* [新しい通貨を追加する為替レート](../store-administration/currencies/adding-a-new-currency.md)
* [計量単位](../store-administration/configuring-shipping-methods/measurement-units.md)
* [地域](../store-administration/adding-regions.md)
* [倉庫](../managing-a-catalog/managing-inventory/warehouse-reference-guide.md)

### Commerceのサイト設定

> Commerce 2.0以前

次の設定は、選択したストアサイトに限定されます。 ここで行った変更は、同じLiferay Commerceインスタンスでホストされている他のストアには適用されません。

［**コントロールパネル**］ → ［**Store Site**］ → ［**コマース**］ に移動します。

![サイトの最小フル設定](./liferay-commerce-configuration-overview/images/03.png)

ここには、以下のサイト設定があります。

* [お知らせ](./sending-emails/using-notification-templates.md)
* [注文ワークフローの概要](../orders-and-fulfillment/order-workflows/introduction-to-order-workflows.md)
* [支払方法](../store-administration/configuring-payment-methods/payments.md)
* 商品表示ページ
* [配送方法](../store-administration/configuring-shipping-methods/shipping-method-reference.md)
* [サイトのタイプ](../starting-a-store/sites-and-site-types.md)
