# グループ商品の作成

Liferay Commerceのグループ商品とは、カタログから2つ以上の商品をまとめてパッケージ化し、単一のアイテムとして販売することです。 グループ商品は在庫で追跡されます。 カタログは、グループに含まれるすべての商品を個別に追跡します。 グループ商品には、シンプル商品、他のグループ化された商品、または仮想商品を含めることができます。

## 前提条件

グループ商品を作成する前に、次の条件が満たされていることを確認してください。

1.  グループ商品として販売される個々の商品がカタログに作成されている。
      - すべてのSKUが生成されている。
      - すべての価格が設定されている。
      - 商品がシンプルまたは仮想のいずれかである。
2.  個々の商品の在庫が十分にある。

## グループ商品の作成

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。
2.  [追加(![Add icon](../../../images/icon-add.png))]ボタンをクリックし、[*グループ化*]をクリックします。
3.  次のように入力します：
      - **Catalog**：Sahara.com
      - **名前**：ブレーキセットコンボキット
      - **短い説明**：ブレーキセットコンボキット
      - **Friendly URL**：（自動生成）
      - **メタタイトル**：ブレーキセットコンボキット
4.  *[Publish]*をクリックします。

グループ商品エンティティが最初に作成されたら、ストア管理者は個々の商品をグループ商品エントリに関連付ける必要があります。

## グループで商品を関連付ける

1.  *[Grouped]*サブタブをクリックします。

    ![[Grouped]サブタブ](./creating-a-grouped-product/images/01.png)

2.  （![Add icon](../../../images/icon-add.png)）ボタンをクリックして、グループ化されたエントリを追加します。

3.  次の商品を選択： *ブレーキフルード*、 *ブレーキパッド*、及び *ブレーキローター*。

4.  *[Add]*をクリックします。

    ![[Grouped]サブタブ](./creating-a-grouped-product/images/02.png)

次に、優先度（表示される順序）と各パッケージで販売される数量を指定します。 *3ドット*アイコンをクリックして、いずれかの商品の隣にある*[Edit]*をクリックします。

![グループ商品への商品の追加](./creating-a-grouped-product/images/03.png)

上の画像では、商品に優先度1.0が与えられているため、最初にリストされます。 他の商品に増分値を割り当てます。

![グループ商品の優先度](./creating-a-grouped-product/images/04.png)

## 商品オプションの適用

[商品オプション](../products/customizing-your-product-with-product-options.md)は、サイズ、数量、色などのさまざまなオプションを持つ商品を提供するための簡単で柔軟な方法を提供します。 *[Options]*サブタブをクリックして、商品にさまざまなオプションを追加します。

## SKUを生成する

商品に商品オプションを適用したら、SKUを生成する必要があります。 SKUの生成方法の詳細については、記事[Adding SKUs to Your Products](../products/adding-skus-to-your-products.md)を参照してください。 *[SKUs]*サブタブをクリックして、SKUを生成します。

## 在庫の価格と数量を設定する

SKUが生成されたら、在庫で各アイテムの基本価格と数量を設定します。 さまざまな商品オプションに基づいて生成されたSKUの数に応じて、各SKUに独自の価格を設定できます。 バルク数量で販売する商品もあれば、個別に販売する商品もあります。

1.  *3ドット*アイコンをクリックして、*default*の隣にある*[Edit]*をクリックします。 （この時点で、SKUの名前を「 *詳細* メニューの「 *ブレーキセットコンボキット* わかりやすい名前に変更できます。）

    ![価格を追加](./creating-a-grouped-product/images/07.png)

2.  *[Publish]*をクリックします。

価格設定の詳細については、[Introduction to Product Pricing Methods](../../managing-prices/introduction-to-product-pricing-methods.md)を参照してください。

商品の在庫を設定する方法については、[Setting Inventory by Warehouse](../../managing-inventory/setting-inventory-by-warehouse.md)を参照してください。

### Liferay Commerce 2.0以前の価格と数量の設定

グループ化された商品の商品価格と数量を設定するには：

1.  *3ドット*アイコンをクリックして、*default*の隣にある*[Edit]*をクリックします。 （この時点で、SKUの名前を「 *詳細* メニューの「 *ブレーキセットコンボキット* わかりやすい名前に変更できます。）

2.  左側のメニューで *[Pricing]* をクリックします。

3.  次のように入力します：

      - **Price**: 89.99

      - **Promo Price**: 0.00

      - **Cost**: 0.00

        ![グループ商品の価格設定](./creating-a-grouped-product/images/06.png)

4.  *[Save]*をクリックします。 （[Promo Price]および[Cost]フィールドが0.00のままの場合、この時点では割引やプロモーションは適用されません。）

5.  *[Price List]*をクリックして、この商品を既存の[[Price Lists]](../../managing-prices/adding-products-to-a-price-list.md)に適用します。

## 商品の仕様を設定する

[商品仕様](../products/specifications.md)には、寸法、色、重量、容量、またはその他の属性など、商品に関する有用な商品情報が含まれています。 仕様を追加するには、*[Specifications]*サブタブをクリックします。

## 商品の画像と添付ファイルをアップロードする

[商品画像](../products/product-images.md)があると、お客様は購入しているものについて把握しやすくなります。 各商品バリアント（SKU）には、独自の商品画像を関連付けることができます。 すべての商品画像を追加するには、*[Images]*サブタブをクリックします。

ストア管理者は、特定の商品に関連付けられた添付ファイルを追加することもできます。 ドキュメントを追加するには、*[Attachments]*サブタブをクリックします。

## カテゴリによる分類

[商品カテゴリ](../products/creating-a-new-product-category.md)は、商品を整理するために使用されます。 商品カテゴリを使用して、商品のセットに割引やその他のオファーを適用したり、購入者が商品を見つけやすくしたり、特定の商品を特定のアカウントまたはアカウントグループにプロモートしたりできます。

## 関連商品と関連付ける

[商品関係](../products/related-products-up-sells-and-cross-sells.md)は、商品を結びつけるのに使用できます。 関連付けると、商品には他の商品へのリンクが表示されます。 すべての関連商品を商品関係タイプに割り当てる必要があります。

## グループ商品の販売

新しいグループ商品がカタログに表示されます。 個々の商品の在庫が十分にある限り、購入者は購入するグループ商品を選択できます。

![ドッググルーミングキット](./creating-a-grouped-product/images/05.png)

## 追加情報

  - [商品タイプについて](./introduction-to-product-types.md)
  - [Creating a Simple Product](./creating-a-simple-product.md)
  - [Creating a Virtual Product](./creating-a-virtual-product.md)
