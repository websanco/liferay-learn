# 仮想商品の作成

Liferay Commerceでは、仮想商品とは、顧客がダウンロードできる非有形の商品です。 例としては、ビデオ、電子出版物、保証、サービス契約などがあります。 他の商品タイプと同様に、在庫が割り当てられ、個別に販売することも、 ［[Product bundle](../products/creating-product-bundles.md) ］または ［[Grouped Product](./creating-a-grouped-product.md)］の一部として販売することもできます。 仮想商品の場合は、ダウンロードの利用規約、利用期間、顧客のダウンロード数の上限、ダウンロードサンプルの有無なども確認できます。

<a name="creating-virtual-products" />

## 仮想商品の作成

以下の手順に従って、仮想商品を作成します：

1. ［**グローバルメニュー**］(![Global Menu](../../../images/icon-applications-menu.png))を開き、［**Commerce**］タブをクリックして、［**商品管理**］&rarr; ［**商品**］に移動します。

1. ［**追加**］ボタン（![Add icon](../../../images/icon-add.png)）をクリックし、 [**仮想**] を選択します。

1. ［**name**］ を入力し、目的の［**カタログ**］を選択します。

1. 完了したら、 [**送信**] をクリックします。

仮想商品が作成されたら、顧客が購入できるようにする前に、希望するデジタルアセットと関連付けさせ、SKUの［[基本価格](./../../managing-prices/setting-a-products-base-price.md)］を設定し、［[在庫を割り当てる](../../managing-inventory/setting-inventory-by-warehouse.md) ］必要があります。

<a name="configuring-virtual-products" />

## 仮想商品の設定

一般的な商品の詳細（例えば、 ［[仕様](../products/specifications.md)］、［[ オプション ](../products/using-product-options.md)］、 ［[SKUs](../products/creating-skus-for-product-variants.md)］）に加えて、仮想商品には独自の設定があります。 次の手順に従って、独自の仮想商品設定を行います：

1. ［**グローバルメニュー**］(![Global Menu](../../../images/icon-applications-menu.png))を開き、［**Commerce**］タブをクリックし、 **商品管理** &rarr; **商品** に移動します。

1. 既存の［**仮想商品**］をクリックします。

1. **仮想** サブタブをクリックします。

   ![仮想サブタブで仮想商品の独自の設定を行います。](./creating-a-virtual-product/images/01.png)

1. ［**Details**］の下にURLを入力するか、ファイルを選択して、本商品のダウンロードに使用するデジタルアセットを決定します。

1. ［**Basic Information**］に、以下の設定を行います：

   * ［**ライセンス認証ステータス**］: ダウンロードのライセンス認証ステータスを設定します。 これにより、アセットがダウンロードできるようになる注文段階（つまり完了、返答待ち、または処理中）が決まります。

   * ［**継続期間**］：顧客がファイルにアクセスできる日数を設定します。このフィールドを［ `0`］にすると、ユーザーに無制限のアクセス権利が与えられます。

   * ［**最大ダウンロード数**］: 顧客がデジタルアセットをダウンロードできる最大回数を設定します。

1. ［**サンプル**］で、商品に関連するサンプルのダウンロードがあるかどうかを決めます。 あるとした場合、サンプルのダウンロードに使用するURLを入力するか、ファイルを選択します。

1. ［**利用規約**］で、商品に利用規約があるかどうかを決めます。 あるとした場合、既存のものをWebコンテンツから選択するか、提供されているテキストエディタを使用して独自のテキストを入力します。

   ![仮想商品の詳細を入力します。](./creating-a-virtual-product/images/02.png)

1. 完了したら、 ［**保存**］ をクリックします。

各フィールドの詳細は、記事 [仮想商品リファレンス](./virtual-product-reference.md) を参照してください。 また、その他の設定については、一般的な ［ [商品](../products.html) ］の資料を参照してください。

<a name="commerce-21-and-below" />

## Commerce 2.1以前

1. ［**コントロールパネル**］ → ［**コマース**］ → ［**商品**］に移動します。
1. 追加（![Add icon](../../../images/icon-add.png)）ボタンをクリックしてから、 ［**仮想**］をクリックします。
1. 次のように入力します：
    ***Catalog** ：Sahara.com
    ***Name** : 3年保証
    ***Short Description** : 3年保証
    ***Full Description** : Sahara.comで購入したすべての商品の3年保証
    ***Friendly URL** ：（自動生成）
    ***Meta Title** : 3年保証
    ***Meta Description** : すべての商品の3年保証
    ***Meta Keywords** : 保証、商品、年
1. ［**公開**］をクリックします。

この仮想商品を作成したら、ストア管理者はデジタルアセットと利用規約を商品に関連付ける必要があります。

### 仮想商品の詳細を設定する

1. ［**Virtual**］サブタブをクリックして続行します。

   ![仮想サブタブでは、仮想商品の設定を行います。](./creating-a-virtual-product/images/01.png)

1. 次のように入力します：
    ***Insert the URL or select a file of your virtual product.** : warranty.pdfをアップロード
    ***Activation Status** : 完了
    ***Duration** : 0
    ***Max Number of Downloads** : 0
    ***Enable Sample** ：オフ
    ***Enable Terms of Use** ：No

   ![仮想商品の詳細を入力します。](./creating-a-virtual-product/images/02.png)

1. ［**保存**］ をクリックします。

各フィールドの詳細は、記事 [仮想商品リファレンス](./virtual-product-reference.md) を参照してください。

### 価格と数量を設定する

商品の価格と数量を設定するには：

1. ［**SKU**］タブをクリックします。
1. ［**default**］SKUの［**3-dot**］ アイコン &rarr; ［**Edit**］をクリックします。
1. 左側のメニューで ［**Pricing**］ をクリックします。
1. 次のように入力します：
    ***Price** : 12.00
    ***Promo Price** : 0.00
    ***Cost** : 0.00
1. ［**保存**］ をクリックします。 （［Promo Price］および［Cost］フィールドが0.00のままの場合、この時点では割引やプロモーションは適用されません。）
1. ［**Price List**］をクリックして、この商品を既存の[［価格表］](../../managing-prices/adding-products-to-a-price-list.md)に適用します。
1. 在庫を設定するには、 ［**在庫**］ サブタブをクリックし、各倉庫に適切な数量を設定します。 更新されたすべての倉庫の ［**保存**]ボタンをクリックします。

価格設定の詳細は、 [プライシングの概要](../../managing-prices/introduction-to-pricing.md) を参照してください。

商品の在庫を設定する方法については、[倉庫ごとの在庫設定](../../managing-inventory/setting-inventory-by-warehouse.md)を参照してください。

商品の価格とSKUを設定したら、上記の、商品オプションや仕様の追加、または商品画像をアップロードする方法を参照してください。

<a name="additional-information" />

## 追加情報

* [商品タイプについて](./introduction-to-product-types.md)
* [グループ商品の作成](./creating-a-grouped-product.md)
* [シンプル商品の作成](./creating-a-simple-product.md)
* [仮想商品リファレンス](./virtual-product-reference.md)
