# シンプル商品の作成

シンプル商品とは、在庫が追跡される物理的な商品です。 この記事では、ストアカタログに商品を追加する方法について詳しく説明します。 この手順には、基本的な商品設定（名前と説明）、商品オプションの適用、SKUの生成、および商品イメージ、仕様、分類などの他の重要な考慮事項に関する包括的な説明が含まれています。

## シンプル商品の作成

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  追加（![Add icon](../../../images/icon-add.png)）ボタンをクリックしてから、 *シンプル* をクリックします。

3.  次のように入力します：

      - **Catalog**：Sahara.com

      - **Name**: 水ボトル

      - **Short Description**: ステンレス製の水ボトル20オンス。

      - **Friendly URL**: （自動生成）

        ![商品の追加](./creating-a-simple-product/images/01.png)

4.  *[Publish]*をクリックします。

最初の商品エントリが作成されました。 引き続き商品の設定について学習します。

## 商品オプションの適用

[商品オプション](../products/customizing-your-product-with-product-options.md)は、サイズ、数量、色などのさまざまなオプションを持つ商品を提供するための簡単で柔軟な方法を提供します。 *[Options]*サブタブをクリックして、商品にさまざまなオプションを追加します。

## SKUを生成する

商品に商品オプションを適用したら、SKUを生成する必要があります。 SKUの生成方法の詳細については、記事[「Adding SKUs to Your Products」](../products/adding-skus-to-your-products.md)を参照してください。 *[SKUs]*サブタブをクリックして、SKUを生成します。

## 在庫の価格と数量を設定する

SKUが生成されたら、在庫で各アイテムの基本価格と数量を設定します。 さまざまな商品オプションに基づいて生成されたSKUの数に応じて、各SKUに独自の価格を設定できます。 バルク数量で販売する商品もあれば、個別に販売する商品もあります。

![商品SKU](./creating-a-simple-product/images/02.png)

1.  *3ドット* アイコン（![Actions icon](../../../images/icon-actions.png)）をクリックし、次に *6*横にある *編集* をクリックします。 （この時点で、*[Details]*メニューでSKUの名前を*「6パック」*などのよりわかりやすい名前に変更できます。）

2.  次のように入力します：

      - **Price**: 19.99
      - **Promo Price**: 0.00
      - **Cost**: 0.00

    ![SKUの価格の編集](./creating-a-simple-product/images/03.png)

3.  *[Publish]*をクリックします。

4.  設定を閉じます

価格設定の詳細については、[Introduction to Product Pricing Methods](../../managing-prices/introduction-to-product-pricing-methods.md)を参照してください。

商品の在庫を設定する方法については、[Setting Inventory by Warehouse](../../managing-inventory/setting-inventory-by-warehouse.md)を参照してください。

### Liferay Commerce 2.0以前の価格と数量の設定

商品の価格と数量を設定するには：

1.  *3ドット*アイコンをクリックして、*[6]*の隣にある*[Edit]*をクリックします。 （この時点で、*[Details]*メニューでSKUの名前を*「6パック」*などのよりわかりやすい名前に変更できます。）
2.  左側のメニューで *[Pricing]* をクリックします。
3.  次のように入力します：
      - **Price**: 12.00
      - **Promo Price**: 0.00
      - **Cost**: 0.00
4.  *[Save]*をクリックします。 （[Promo Price]および[Cost]フィールドが00のままの場合、この時点では割引やプロモーションは適用されません。）
5.  *[Price List]*をクリックして、この商品を既存の[価格リスト](../../managing-prices/adding-products-to-a-price-list.md)に適用します。

## 商品の仕様を設定する

[商品仕様](../products/specifications.md)には、寸法、色、重量、容量、またはその他の属性など、商品に関する有用な商品情報が含まれています。 仕様を追加するには、*[Specifications]*サブタブをクリックします。

## 商品の画像と添付ファイルをアップロードする

[商品画像](../products/product-images.md)があると、お客様は購入しているものについて把握しやすくなります。 各商品バリアント（SKU）には、独自の商品画像を関連付けることができます。 すべての商品画像を追加するには、*[Images]*サブタブをクリックします。

ストア管理者は、特定の商品に関連付けられた添付ファイルを追加することもできます。 ドキュメントを追加するには、*[Attachments]*サブタブをクリックします。

## カテゴリによる分類

[商品カテゴリ](../products/creating-a-new-product-category.md)は、商品を整理するために使用されます。 商品カテゴリを使用して、商品のセットに割引やその他のオファーを適用したり、購入者が商品を見つけやすくしたり、特定の商品を特定のアカウントまたはアカウントグループにプロモートしたりできます。

## 関連商品と関連付ける

[商品関係](../products/related-products-up-sells-and-cross-sells.md)は、商品を結びつけるのに使用できます。 関連付けると、商品には他の商品へのリンクが表示されます。 すべての関連商品を商品関係タイプに割り当てる必要があります。

## 追加情報

  - [商品タイプについて](./introduction-to-product-types.md)
  - [グループ商品の作成](./creating-a-grouped-product.md)
  - [Creating a Virtual Product](./creating-a-virtual-product.md)
  - [Setting Inventory by Warehouse](../../managing-inventory/setting-inventory-by-warehouse.md)
