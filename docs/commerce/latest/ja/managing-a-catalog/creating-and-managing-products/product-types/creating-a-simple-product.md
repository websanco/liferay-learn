# シンプル商品の作成

シンプル商品とは、在庫が追跡される物理的な商品です。 商品はストアのカタログに追加され、名前と説明が表示されます。 商品オプションを適用し、SKU、画像、仕様、そしてカテゴリー設定を生成することで、商品をさらに定義することができます。

<a name="create-a-simple-product" />

## シンプル商品の作成

新しいシンプル商品を追加するには：

1. ［**グローバルアプリケーション**］メニュー &rarr; ［**Commerce**］&rarr; ［**商品**］に移動します。
1. 追加（![Add icon](../../../images/icon-add.png)）ボタンをクリックしてから、 **シンプル** をクリックします。
1. 次のように入力します：
   ***Catalog** ：Sahara.com
   ***Name** : 水ボトル
1. **Submit** クリックし* 。</li> </ol>

最初の商品エントリが作成されました。 引き続き商品の設定について学習します。

<a name="apply-product-options" />

## 商品オプションの適用

[商品オプション](../products/using-product-options.md)は、サイズ、数量、色などのさまざまなオプションを持つ商品を提供するための簡単で柔軟な方法を提供します。 ［**オプション**］サブタブをクリックして、商品にさまざまなオプションを追加します。

<a name="generate-skus" />

## SKUを生成する

商品に商品オプションを適用したら、SKUを生成する必要があります。 SKUの生成方法の詳細は、 [商品バリアントのSKU作成](../products/creating-skus-for-product-variants.md) を参照してください。 ［**SKU**］サブタブをクリックして、SKUを生成します。

<a name="set-prices-and-quantities-in-the-inventory" />

## 在庫の価格と数量を設定する

SKUが生成されたら、在庫で各アイテムの基本価格と数量を設定します。 さまざまな商品オプションに基づいて生成されたSKUの数に応じて、各SKUに独自の価格を設定できます。 バルク数量で販売する商品もあれば、個別に販売する商品もあります。

![商品SKU](./creating-a-simple-product/images/02.png)

1. **3ドット** アイコン（![Actions icon](../../../images/icon-actions.png)）をクリックし、次に **6** 横にある **編集** をクリックします。 （この時点で、［**詳細**］メニューでSKUの名前を 「**6パック**」 などのよりわかりやすい名前に変更できます。）
1. 次のように入力します：
    ***Price** : 19.99
    ***Promo Price** : 0.00
    ***Cost** : 0.00

    ![SKUの価格の編集](./creating-a-simple-product/images/03.png)

1. ［**公開**］をクリックします。
1. 設定を閉じます

価格設定の詳細は、 [プライシングの概要](../../managing-prices/introduction-to-pricing.md) を参照してください。

商品の在庫を設定する方法については、[倉庫ごとの在庫設定](../../managing-inventory/setting-inventory-by-warehouse.md)を参照してください。

<a name="configure-product-specifications" />

## 商品の仕様を設定する

[商品仕様](../products/specifications.md)には、寸法、色、重量、容量、またはその他の属性など、商品に関する有用な商品情報が含まれています。 仕様を追加するには、［**仕様**］サブタブをクリックします。

<a name="upload-product-images-and-attachments" />

## 商品の画像と添付ファイルをアップロードする

[商品画像](../products/product-images.md)があると、お客様は購入しているものについて把握しやすくなります。 各商品バリアント（SKU）には、独自の商品画像を関連付けることができます。 すべての商品画像を追加するには、［**Images**］サブタブをクリックします。

ストア管理者は、特定の商品に関連付けられた添付ファイルを追加することもできます。 ドキュメントを追加するには、［**Attachments**］サブタブをクリックします。

<a name="organize-with-categories" />

## カテゴリによる分類

[商品カテゴリ](../products/creating-a-new-product-category.md)は、商品を整理するために使用されます。 商品カテゴリを使用して、商品のセットに割引やその他のオファーを適用したり、購入者が商品を見つけやすくしたり、特定の商品を特定のアカウントまたはアカウントグループにプロモートしたりできます。

<a name="associate-with-related-products" />

## 関連商品と関連付ける

[商品関係](../products/related-products-up-sells-and-cross-sells.md)は、商品を結びつけるのに使用できます。 関連付けると、商品には他の商品へのリンクが表示されます。 すべての関連商品を商品関係タイプに割り当てる必要があります。

<a name="commerce-21-and-earlier-versions" />

## Commerce 2.1およびそれ以前のバージョン

### シンプル商品の作成

1. ［**コントロールパネル**］ → ［**コマース**］ → ［**商品**］に移動します。
1. 追加（![Add icon](../../../images/icon-add.png)）ボタンをクリックしてから、 **シンプル** をクリックします。
1. 次のように入力します：
   ***Catalog** ：Sahara.com
   ***Name** : 水ボトル
   ***Short Description** : ステンレス製の水ボトル20オンス。
   ***Friendly URL** : （自動生成）

      ![商品の追加](./creating-a-simple-product/images/01.png)

1. ［**公開**］をクリックします。

最初の商品エントリが作成されました。 引き続き商品の設定について学習します。

### 価格と数量の設定

商品の価格と数量を設定するには：

1. **3ドット** アイコンをクリックして、［**6**］の隣にある［**編集**］をクリックします。 （この時点で、［**詳細**］メニューでSKUの名前を 「**6パック**」 などのよりわかりやすい名前に変更できます。）
1. 左側のメニューで ［**価格**］ をクリックします。
1. 次のように入力します：
    ***Price** : 12.00
    ***Promo Price** : 0.00
    ***Cost** : 0.00
1. ［**保存**］ をクリックします。 （［Promo Price］および［Cost］フィールドが00のままの場合、この時点では割引やプロモーションは適用されません。）
1. ［**Price List**］をクリックして、この商品を既存の[価格リスト](../../managing-prices/adding-products-to-a-price-list.md)に適用します。

商品の価格とSKUを設定したら、商品オプションや仕様を追加する方法、または商品画像をアップロードする方法について、上記のセクションを参照してください。

<a name="additional-information" />

## 追加情報

* [商品タイプについて](./introduction-to-product-types.md)
* [グループ商品の作成](./creating-a-grouped-product.md)
* [仮想商品の作成](./creating-a-virtual-product.md)
* [倉庫ごとの在庫設定](../../managing-inventory/setting-inventory-by-warehouse.md)
