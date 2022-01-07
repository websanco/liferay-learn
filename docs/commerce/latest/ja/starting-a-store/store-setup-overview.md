# ストア設定の概要

## はじめに

この記事は、Liferay Commerceでストアを構築するのが初めてのユーザー向けにウォークスルーを提供することを目的としています。 Liferay Commerce（およびLiferay DXP）が適切にインストールされ、設定されていることを前提としています。 インストールの詳細は、[インストールの概要](../../installation-and-upgrades/installation-overview.md)を参照してください。

## ロードマップ

* [ストア設定](#store-setup)
* [ストアフロントの作成](#creating-the-storefront)
* [顧客アカウントの管理](#managing-customer-accounts)
* [注文と出荷の管理](#managing-orders-and-shipments)

## ストア設定

```note::
   `Accelerator <../starting-a-store/accelerators.md>`_ は店のサイトを作成するために使用することができます。 アクセラレータを使用してストアサイトを作成すると、このセクションで説明されている多くの設定が構成されます。詳細は、`MiniumAcceleratorを使用してB2Bストアをジャンプスタートする <../starting-a-store/using-the-minium-accelerator-to-jump-start-your-b2b-store.md>`_ を参照してください。
```

最初のストア設定は、[管理者アカウント](./introduction-to-the-admin-account.md)を使用して行います 。 Liferay Commerceの設定レイアウトと使用可能なオプションについては、[Liferay Commerceの設定の概要](../store-administration/liferay-commerce-configuration-overview.md)をご覧ください。

### グローバルCommerce設定の構成

Liferay Commerceのグローバル設定には、ストアのタイムゾーンと使用可能な言語の設定、地域や地域固有の測定単位の追加などがあります。 詳細は、次の記事をご覧ください。

* [ロケールオプションの設定](../store-administration/locale-options.md)
* [リージョンの追加](../store-administration/adding-regions.md)
* [倉庫の設定](../managing-a-catalog/managing-inventory/warehouse-reference-guide.md)
* [チャネルの作成](./channels/introduction-to-channels.md)

### ストアサイトを作成する

Liferay Commerceは、B2B、B2C、およびB2C-B2Bストアをサポートしています。 [サイトタイプ](../starting-a-store/sites-and-site-types.md)によって、ストアのビジネスモデルが指定され、ストアフロントがアカウントとどのように連携するかが決まります。

Liferay CommerceはLiferay DXP上に構築されています。 Liferay DXPサイトの機能の詳細は、[Building a Site](https://learn.liferay.com/dxp/latest/en/site-building/building-sites/adding-a-site.html)を参照してください。

### ストアサイトのCommerce設定を構成する

次のセクションでは、ストアサイトに固有の構成について説明します。

#### 支払いの受け入れ

Liferay Commerceは、複数のサードパーティの支払い処理業者に対応しており、郵便為替を使用した支払いにも対応しています。 以下を参照してください。

* [支払い](../store-administration/configuring-payment-methods/payments.md)
* [支払い方法の管理](../store-administration/configuring-payment-methods/managing-payment-methods.md)
* [Authorize.net](../store-administration/configuring-payment-methods/authorize.net.md)
* [PayPal](../store-administration/configuring-payment-methods/mercanet.md)
* [Mercanet](../store-administration/configuring-payment-methods/mercanet.md)
* [マネーオーダー](../store-administration/configuring-payment-methods/mercanet.md)

#### 適用税の徴収

適用税を徴収するために、Liferay Commerceには、税カテゴリを作成し、住所または固定レートごとに税率を設定する機能があります。

* [税区分の作成](../store-administration/configuring-taxes/creating-tax-categories.md)
* [税計算のためのレート設定](../store-administration/configuring-taxes/setting-rates-for-tax-calculations.md)
* [税率の適用](../store-administration/configuring-taxes/applying-tax-rates.md)

#### 商品の出荷

Liferay Commerceには、送料を計算するためのいくつかのオプションがあります。 Commerce Enterpriseの加入者は、FedExのキャリア統合をすぐに利用できます。

* [配送方法](../store-administration/configuring-shipping-methods/shipping-methods.md)
* [可変レート](../store-administration/configuring-shipping-methods/using-the-variable-rate-shipping-method.md)
* [一律料金](../store-administration/configuring-shipping-methods/using-the-flat-rate-shipping-method.md)
* [FedExを運送業者として使用する](../store-administration/configuring-shipping-methods/using-the-fedex-shipping-method.md)

## カタログの作成

ストアをセットアップした後、[新しいカタログの作成](../managing-a-catalog/catalogs/creating-a-new-catalog.md) を開始します。

### カタログに商品を追加する

商品を追加するときは、**シンプル**、**グループ**、**仮想**の3つの商品タイプがあります。 詳細は、[Introduction to Product Types](../managing-a-catalog/creating-and-managing-products/product-types/introduction-to-product-types.md)を参照してください。

#### 商品情報の追加

Liferay Commerceカタログは、さまざまな商品情報の保存と管理をサポートしています。 次の記事では、利用可能なオプションの一部を説明しています。

* [商品オプション](../managing-a-catalog/creating-and-managing-products/products/using-product-options.md)
* [商品仕様](../managing-a-catalog/creating-and-managing-products/products/specifications.md)
* [商品画像](../managing-a-catalog/creating-and-managing-products/products/product-images.md)
* [商品関連](../managing-a-catalog/creating-and-managing-products/products/related-products-up-sells-and-cross-sells.md)
* [商品の分類](../managing-a-catalog/creating-and-managing-products/products/organizing-your-catalog-with-product-categories.md)
* [在庫数の見積もり](../managing-a-catalog/managing-inventory/availability-estimates.md)
* [在庫数低下時のアクション](../managing-a-catalog/managing-inventory/low-stock-action.md)

#### 商品価格

商品の価格設定にはいくつかの方法があり、これらの方法は価格設定階層（基本価格、価格表、段階型価格、プロモーション価格、割引）で互いに関連しています。 価格はSKUごとに管理されます。

* [価格](../managing-a-catalog/managing-prices/introduction-to-pricing.md)
* [価格表](../managing-a-catalog/managing-prices/creating-a-price-list.md)
* [割引](../promoting-products/introduction-to-discounts.md)

#### 在庫管理

* [在庫管理の概要](../managing-a-catalog/managing-inventory/introduction-to-managing-inventory.md)

## ストアフロントの作成

Liferay Commerceでストアを構築するには、完全なカタログが1つ以上必要です。 ストアフロントをゼロから構築するストア管理者は、商品を表示および販売するための一連のページを追加する必要があります。

詳細は、[Creating Your Storefront](../creating-store-content/creating-your-storefront.md)を参照してください。

## 顧客アカウントの管理

アカウント、ユーザーアカウントの招待、アカウントの役割などの詳細は、次の記事をご覧ください。

* [アカウントの概要](../users-and-accounts/account-management.md)
* [新規アカウントの作成](../users-and-accounts/account-management/creating-a-new-account.md)
* [アカウントへのユーザーの招待](../users-and-accounts/account-management/inviting-users-to-an-account.md)
* [アカウントへのアドレスの追加](../users-and-accounts/account-management/adding-addresses-to-an-account.md)
* [アカウントロール](../users-and-accounts/account-management/account-roles.md)
* [新規アカウントグループの作成](../users-and-accounts/account-management/creating-a-new-account-group.md)

## 注文と出荷の管理

注文が受信されると、注文はERPに送信され、オプションでCRMに送信されます。 要求された商品が出荷可能になると、ストアの在庫スペシャリストが出荷を追跡できます。 注文のライフサイクルと出荷プロセスについては、次の記事を参照してください。

* [注文ライフサイクル](../orders-and-fulfillment/orders/order-life-cycle.md)
* [発送の概要](../orders-and-fulfillment/shipments/introduction-to-shipments.md)
* [発送の作成](../orders-and-fulfillment/shipments/creating-a-shipment.md)
* [発送のキャンセル](../orders-and-fulfillment/shipments/cancelling-a-shipment.md)
