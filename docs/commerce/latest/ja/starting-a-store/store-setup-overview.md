# ストア設定の概要

## はじめに

この記事は、Liferay Commerceでストアを構築するのが初めてのユーザー向けにウォークスルーを提供することを目的としています。 Liferay Commerce（およびLiferay DXP）が適切にインストールされ、設定されていることを前提としています。 インストールの詳細については、[Installation Overview](../../installation-and-upgrades/installation-overview.md)を参照してください。

## ロードマップ

  - [ストア設定](#store-setup)
  - [ストアフロントの作成](#creating-the-storefront)
  - [顧客アカウントの管理](#managing-customer-accounts)
  - [注文と出荷の管理](#managing-orders-and-shipments)

## ストア設定

``` note::
   `Accelerator <../starting-a-store/accelerators.md>`_ は店のサイトを作成するために使用することができます。 アクセラレータを使用してストアサイトを作成すると、このセクションで説明されている多くの設定が構成されます。詳細については、`MiniumAcceleratorを使用してB2Bストアをジャンプスタートする <../starting-a-store/using-the-minium-accelerator-to-jump-start-your-b2b-store.md>`_ を参照してください。
```

最初のストア設定は、[管理者アカウント](./introduction-to-the-admin-account.md)を使用して行います 。 Liferay Commerceの設定レイアウトと使用可能なオプションについては、[Liferay Commerce Configuration Overview](../store-administration/liferay-commerce-configuration-overview.md)をご覧ください。

### グローバルCommerce設定の構成

Liferay Commerceのグローバル設定には、ストアのタイムゾーンと使用可能な言語の設定、地域や地域固有の測定単位の追加などがあります。 詳細については、次の記事をご覧ください。

  - [Setting Locale Options](../store-administration/locale-options.md)
  - [Adding Regions](../store-administration/adding-regions.md)
  - [Setting up Warehouses](../managing-a-catalog/managing-inventory/warehouse-reference-guide.md)
  - [Creating Channels](./channels/introduction-to-channels.md)

### ストアサイトを作成する

Liferay Commerceは、B2B、B2C、およびB2C-B2Bストアをサポートしています。 [サイトタイプ](../starting-a-store/sites-and-site-types.md)によって、ストアのビジネスモデルが指定され、ストアフロントがアカウントとどのように連携するかが決まります。

Liferay CommerceはLiferay DXP上に構築されています。 Liferay DXPサイトの機能の詳細については、[Building a Site](https://learn.liferay.com/dxp/7.x/en/site-building/building-sites/adding-a-site.html)を参照してください。

### ストアサイトのCommerce設定を構成する

次のセクションでは、ストアサイトに固有の構成について説明します。

#### 支払いの受け入れ

Liferay Commerceは、複数のサードパーティの支払い処理業者に対応しており、郵便為替を使用した支払いにも対応しています。 以下を参照してください。

  - [Payments](../store-administration/configuring-payment-methods/payments.md)
  - [Managing Payment Methods](../store-administration/configuring-payment-methods/managing-payment-methods.md)
  - [Authorize.net](../store-administration/configuring-payment-methods/authorize.net.md)
  - [PayPal](../store-administration/configuring-payment-methods/mercanet.md)
  - [Mercanet](../store-administration/configuring-payment-methods/mercanet.md)
  - [Money Orders](../store-administration/configuring-payment-methods/mercanet.md)

#### 適用税の徴収

適用税を徴収するために、Liferay Commerceには、税カテゴリを作成し、住所または固定レートごとに税率を設定する機能があります。

  - [Creating Tax Categories](../store-administration/configuring-taxes/creating-tax-categories.md)
  - [Setting Tax Rate by Address](../store-administration/configuring-taxes/setting-tax-rate-by-address.md)
  - [Setting Tax Rate by Fixed Rate](../store-administration/configuring-taxes/setting-tax-rate-by-fixed-rate.md)

#### 商品の出荷

Liferay Commerceには、送料を計算するためのいくつかのオプションがあります。 Commerce Enterpriseの加入者は、FedExのキャリア統合をすぐに利用できます。

  - [Shipping Methods](../store-administration/configuring-shipping-methods/shipping-methods.md)
  - [Variable Rate](../store-administration/configuring-shipping-methods/using-the-variable-rate-shipping-method.md)
  - [Flat Rate](../store-administration/configuring-shipping-methods/using-the-flat-rate-shipping-method.md)
  - [Using FedEx as a Carrier](../store-administration/configuring-shipping-methods/using-the-fedex-shipping-method.md)

## カタログの作成

ストアをセットアップした後、[新しいカタログの作成](../managing-a-catalog/catalogs/creating-a-new-catalog.md) を開始します。

### カタログに商品を追加する

商品を追加するときは、**シンプル**、**グループ**、**仮想**の3つの商品タイプがあります。 詳細については、[Introduction to Product Types](../managing-a-catalog/creating-and-managing-products/product-types/introduction-to-product-types.md)を参照してください。

#### 商品情報の追加

Liferay Commerceカタログは、さまざまな商品情報の保存と管理をサポートしています。 次の記事では、利用可能なオプションの一部を説明しています。

  - [Product Options](../managing-a-catalog/creating-and-managing-products/products/customizing-your-product-with-product-options.md)
  - [Product Specifications](../managing-a-catalog/creating-and-managing-products/products/specifications.md)
  - [Product Images](../managing-a-catalog/creating-and-managing-products/products/product-images.md)
  - [Product Relations](../managing-a-catalog/creating-and-managing-products/products/related-products-up-sells-and-cross-sells.md)
  - [Product Categorization](../managing-a-catalog/creating-and-managing-products/products/organizing-your-catalog-with-product-categories.md)
  - [Availability Estimates](../managing-a-catalog/managing-inventory/availability-estimates.md)
  - [Low Stock Action](../managing-a-catalog/managing-inventory/low-stock-action.md)

#### 商品価格

商品の価格設定にはいくつかの方法があり、これらの方法は価格設定階層（基本価格、価格表、段階型価格、プロモーション価格、割引）で互いに関連しています。 価格はSKUごとに管理されます。

  - [Pricing](../managing-a-catalog/managing-prices/introduction-to-product-pricing-methods.md)
  - [Price Lists](../managing-a-catalog/managing-prices/creating-a-price-list.md)
  - [Discounts](../promoting-products/introduction-to-discounts.md)

#### 在庫管理

  - [Introduction to Managing Inventory](../managing-a-catalog/managing-inventory/introduction-to-managing-inventory.md)

## ストアフロントの作成

Liferay Commerceでストアを構築するには、完全なカタログが1つ以上必要です。 ストアフロントをゼロから構築するストア管理者は、商品を表示および販売するための一連のページを追加する必要があります。

詳細については、[Creating Your Storefront](../creating-store-content/creating-your-storefront.md)を参照してください。

## 顧客アカウントの管理

アカウント、ユーザーアカウントの招待、アカウントの役割などの詳細については、次の記事をご覧ください。

  - [Introduction to Accounts](../account-management/introduction-to-accounts.md)
  - [Creating a New Account](../account-management/creating-a-new-account.md)
  - [Inviting Users to an Account](../account-management/inviting-users-to-an-account.md)
  - [Adding Addresses to an Account](../account-management/adding-addresses-to-an-account.md)
  - [Account Roles](../account-management/account-roles.md)
  - [Assigning Account Roles](../account-management/assigning-account-roles.md)
  - [Creating a New Account Group](../account-management/creating-a-new-account-group.md)

## 注文と出荷の管理

注文が受信されると、注文はERPに送信され、オプションでCRMに送信されます。 要求された商品が出荷可能になると、ストアの在庫スペシャリストが出荷を追跡できます。 注文のライフサイクルと出荷プロセスについては、次の記事を参照してください。

  - [Order Life Cycle](../orders-and-fulfillment/orders/order-life-cycle.md)
  - [Introduction to Shipments](../orders-and-fulfillment/shipments/introduction-to-shipments.md)
  - [Creating a shipment](../orders-and-fulfillment/shipments/creating-a-shipment.md)
  - [Cancelling a Shipment](../orders-and-fulfillment/shipments/cancelling-a-shipment.md)
