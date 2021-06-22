# Liferay Commerceの概要

Liferay Commerce \へようこそ！ Liferay Commerceは、あらゆるB2B組織の困難で複雑なニーズを満たすためにゼロから構築されたデジタルコマースプラットフォームです。 Liferay DXPのエンタープライズ基盤を使用して、Liferay Commerceは、完全に統合されたWebコンテンツ管理と業界をリードするポータル機能の能力と柔軟性を備えています。 LiferayのDXPの詳細については、この [記事](https://help.liferay.com/hc/en-us/articles/360028818552-Introduction-to-The-Liferay-Distinction)を参照してください。

## 概要

  - [最新のストアフロントページとコンテンツ管理](#modern-storefront-pages-and-content-management)
  - [マルチチャネルカタログと商品情報管理](#multi-channel-catalog-and-product-information-management)
  - [B2Bに焦点を当てたアカウント管理と購入ワークフロー](#b2b-focused-account-management-and-purchasing-workflow)
  - [注文管理と販売](#order-management-and-sales)
  - [MLを利用した推奨事項とアラート](#ml-powered-recommendations-and-alerts)
  - [APIと統合](#apis-and-integrations)
  - [次のステップ](#next-steps)

開始する準備は整いましたか？ [Liferay Commerceのドッカー画像使用](../installation-and-upgrades/using-the-liferay-commerce-docker-image.md)して、Liferayコマースを *素早く* 起動させます。

## 最新のストアフロントページとコンテンツ管理

Liferay Commerceには、ストアフロントをセットアップするための幅広いツールが含まれています。 たとえば、新しい検索ツールは商品カタログ専用に設計されていますが、カスタマイズ可能なウィジェットにより、チェックアウトやカートの表示などの一般的なUI要素をページに簡単に追加できます。 [ストアフロントの構築](../creating-store-content/creating-your-storefront.md)についての詳細はこちら。

![Liferay Commerceダッシュボード](./introduction-to-liferay-commerce/images/01.png)

## マルチチャネルカタログと商品情報管理

Liferay Commerceには、さまざまな [商品タイプ](../managing-a-catalog/creating-and-managing-products/product-types/introduction-to-product-types.md) をサポートし、幅広い商品データを格納するカタログ管理システムが含まれています。 [商品オプション](../managing-a-catalog/creating-and-managing-products/products/customizing-your-product-with-product-options.md)、 [価格](../managing-a-catalog/managing-prices/introduction-to-product-pricing-methods.md)、 [画像](../managing-a-catalog/creating-and-managing-products/products/product-images.md)、及び [仕様](../managing-a-catalog/creating-and-managing-products/products/specifications.md) いくつかの例です。 その後、商品は選択した流通チャネルですぐに利用できます。

![商品カタログ](./introduction-to-liferay-commerce/images/02.png)

[チャネル](./channels/introduction-to-channels.md) は、カタログおよび商品を、商品が販売される任意の場所に接続します。LiferayCommerceベースのストアフロント、サードパーティのマーケットプレイス、またはバックオフィスの電話販売です。

![商品に対して有効なチャネル](./introduction-to-liferay-commerce/images/03.png)

[カテゴリー](../managing-a-catalog/creating-and-managing-products/products/organizing-your-catalog-with-product-categories.md) および [商品の関係](../managing-a-catalog/creating-and-managing-products/products/related-products-up-sells-and-cross-sells.md) 店舗のナビゲーションを簡素化し、収益を生み出す機会を増やします。

![商品詳細と関連商品](./introduction-to-liferay-commerce/images/04.png)

[価格表](../managing-a-catalog/managing-prices/creating-a-price-list.md) と [段階的価格設定](../managing-a-catalog/managing-prices/adding-tiered-pricing.md) 使用することにより、商品の価格設定を柔軟に管理できます。これらはすべて、固有の顧客のニーズに合わせて調整できます。

## B2Bに焦点を当てたアカウント管理と購入ワークフロー

Liferay Commerceは、B2Bニーズに対応するように設計された機能により、企業間取引向けに最適化されています。 [アカウント](../account-management/introduction-to-accounts.md)、 [アカウントグループ](../account-management/creating-a-new-account-group.md)、および [アカウントロール](../account-management/account-roles.md)が連携し、顧客のニーズに合ったエクスペリエンス、セルフサービス、および購入ワークフローを提供します。

![ユーザーロールの選択](./introduction-to-liferay-commerce/images/05.png)

## 注文管理と販売

Liferay Commerceは、買い手と売り手が注文の準備、発注、承認、送信、および実行を可能にする注文管理を提供します。

注文の支払いは、初期設定から利用可能ないくつかの異なる [支払い方法](../store-administration/configuring-payment-methods/managing-payment-methods.md)で完了できます。 追加のカスタマイズにより、より多くの決済処理業者との統合を実現できます。

![支払方法ページ](./introduction-to-liferay-commerce/images/06.png)

注文は、いくつかの異なる使用して満たすことができる [配送方法](../store-administration/configuring-shipping-methods/shipping-method-reference.md)売り手ができ： [定額課す](../store-administration/configuring-shipping-methods/using-the-flat-rate-shipping-method.md)、適用 [配送方法の制限](../store-administration/configuring-shipping-methods/applying-shipping-method-restrictions.md)、またはと統合 [フェデックスのようなキャリアレート](../store-administration/configuring-shipping-methods/using-the-fedex-shipping-method.md)。

## MLを利用した推奨事項とアラート

Liferay Commerceは、機械学習と予測分析を適用して、提案された商品グループ、商品レベルまでの注文予測、スマートロイヤルティスコアアラート、パーソナライズされた商品推奨を可能にします。

## APIと統合

Mulesoftコネクタ、Talendコンポーネント、またはSalesforce、SAP ERP（*Coming Soon*）、またはMS Dynamics NAV（*Coming Soon*）のビルド済みコネクタを使用して、他のビジネスシステムと統合します。

## 次のステップ

  - [Installation Overview](../installation-and-upgrades/installation-overview.md)
  - [Liferay Commerce Dockerイメージの使用](../installation-and-upgrades/using-the-liferay-commerce-docker-image.md)
  - [Minium Acceleratorを使用して完全なB2Bサイトをセットアップ](../starting-a-store/using-the-minium-accelerator-to-jump-start-your-b2b-store.md)
