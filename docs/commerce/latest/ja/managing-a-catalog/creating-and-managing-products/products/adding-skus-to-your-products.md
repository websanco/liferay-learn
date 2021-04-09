# SKUを商品に追加する

SKUは、複数の商品バリアントの在庫と価格を設定する便利な方法を提供します。 この記事では、カタログで商品と商品オプションが作成された後にSKUを自動的に生成する方法と、SKUを手動で追加する方法について説明します。

## 前提条件

SKUを自動生成するには、以下を完了する必要があります。

  - 商品オプションテンプレートを作成している。
  - 商品オプションを商品に適用している。

詳細については、[Customizing Your Product with Product Options](./customizing-your-product-with-product-options.md)を参照してください。

## すべてのSKUの組み合わせを生成（自動）

商品にSKUを追加する最も早くて簡単な方法は、「*すべてのSKUの組み合わせを生成する*」機能を使用することです。

「*すべてのSKUの組み合わせを生成する*」機能を使用する前に、商品のすべての商品オプション（テンプレートと値）を確定することを強くお勧めします。

商品オプションを作成後、すべての商品バリアントのSKUをすばやく簡単に生成する方法は次のとおりです。

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品をクリックします（この例では*Torque Converters*）。

3.  *[SKUs]*サブタブをクリックします。

4.  （+）ボタンをクリックします。

5.  [Generate All SKU Combinations]をクリックします。

    ![[SKU]タブ](./adding-skus-to-your-products/images/01.png)

BlackとGrayの商品オプションにSKUが生成されました。 必要に応じて、他の商品に対して手順を繰り返します。

## SKUの変更（手動）

商品を作成すると、単一のSKU（名前は*default* ）が自動的に作成されます。 この生成されたSKUを手動で変更できます。

このSKUを変更するには：

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品をクリックします（この例では*Torque Converters*）。

3.  *[SKUs]*タブをクリックします。

4.  *SKU*をクリックします（この例では[*default*]）。

5.  SKUの名前を*GRAY*に変更します。

6.  [Purchasable]トグルは[YES]のままにしておきます。

    ![SKUの編集](./adding-skus-to-your-products/images/02.png)

7.  *[Publish]*をクリックします。

SKUの名前が正常に変更されました。

## 追加情報

  - [商品レベルの情報を上書きする](./overriding-product-level-information.md)
