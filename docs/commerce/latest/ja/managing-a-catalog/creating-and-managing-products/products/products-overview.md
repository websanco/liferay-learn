# 商品の概要

商品は、ストアを動かす資産であり、ストアの [カタログ](../../catalogs/creating-a-new-catalog.md)リストされています。 ユーザーは、商品の3つのタイプを追加することができます： [シンプル](../product-types/creating-a-simple-product.md)、 [グループ化](../product-types/creating-a-grouped-product.md)、および [仮想](../product-types/creating-a-virtual-product.md)。 詳細については、 [商品タイプの概要](../product-types/introduction-to-product-types.md) を参照してください。 商品とその関連SKUは、さまざまな方法で構成できます。

## Product Options

商品オプションを使用して、サイズ、数量、色などのさまざまなオプションを持つ商品を提供できます。

詳細については、「 [商品オプションを使用した商品のカスタマイズ](./customizing-your-product-with-product-options.md) 」を参照してください。

![商品オプションを作成します。](./products-overview/images/02.png)

## SKU

SKUは商品の各バリエーションを表し、商品の色、サイズ、数量に基づくことができます（前の商品オプションのセクションを参照）。

SKUを追加するには、「 [商品へのSKUの追加](./adding-skus-to-your-products.md)」を参照してください。

![複数の商品バリエーションの複数のSKUを生成します。](./products-overview/images/09.png)

デフォルトでは、商品のSKUは、親商品によって定義された仕様と構成を継承します。 ユーザーは、SKUごとに一部の商品構成をオーバーライドすることを選択できます。 これには、SKUの個別のディメンションと重みの定義、またはSKUをサブスクリプションベースで利用できるように構成すること（親商品が利用できない場合でも）が含まれます。 詳細については、「 [商品レベル情報の上書き](./overriding-product-level-information.md) 」を参照してください。

## メディア

ユーザーは複数の商品画像と添付ファイルをアップロードできます。 詳細については、 [商品イメージ](./product-images.md) および [商品添付](./product-attachments.md) を参照してください。

![画像と添付ファイルをアップロードします。](./products-overview/images/07.png)

## 商品の関係とカテゴリー

多くの場合、ブレーキローターやブレーキパッドなどの関連商品が一緒に販売されます。 商品の関係タイプテンプレートを使用して、類似の商品をグループ化できます。 [関連商品、アップセル、クロスセルを見る](./related-products-up-sells-and-cross-sells.md)

![プロダクトリレーションシップを設定します。](./products-overview/images/03.png)

タグやカテゴリを使用して、商品をプレゼンテーション用にグループ化することもできます。 [商品カテゴリでカタログを整理](./organizing-your-catalog-with-product-categories.md)を参照してください。

![カテゴリーを使用して商品をグループ化します。](./products-overview/images/04.png)

## サブスクリプション契約

定期購入に分類される商品は定期的な注文です。一部の例には、雑誌、更新オプションのあるサービス契約、定期的に消費されるアイテムが含まれます（これらに限定されません）。

![サブスクリプションを有効にします。](./products-overview/images/05.png)

詳細については、 [商品](./enabling-subscriptions-for-a-product.md) サブスクリプションの有効化を参照してください。

## 在庫

商品が作成されると、ユーザーは在庫ルールを構成できます。 在庫が設定されたしきい値を下回った場合の商品の販売方法、ユーザーが1つの注文で購入できる最大または最小数量、およびストアが注文を取り戻すかどうかを制御するルールを定義できます。

![在庫ルールを設定します。](./products-overview/images/01.png)

## 可視性

カタログ内の商品を表示するには、カタログをチャネルに関連付ける必要があります。 カタログは、複数の [チャネルに関連付けることができます](../../../starting-a-store/channels/introduction-to-channels.md)。 さらに、ユーザーは特定の [アカウントグループ](../../../account-management/creating-a-new-account-group.md)商品を宣伝できます。

![商品の可視性の管理](./products-overview/images/08.png)

## 部品表図

部品表（BOM）図は、商品に属する構成部品を識別します。 ユーザーは、ストア内の商品を参照するLiferay CommerceでBOM図を作成して注釈を付けることができます。 その後、BOMダイアグラムをサイトページに表示できます。

![BOMダイアグラムを作成します。](./products-overview/images/06.png)

BOMを作成するには、 [BOMの管理](./managing-boms.md)を参照してください

## 追加情報

  - [Creating a Simple Product](../product-types/creating-a-simple-product.md)
  - [グループ商品の作成](../product-types/creating-a-grouped-product.md)
  - [Creating a Virtual Product](../product-types/creating-a-virtual-product.md)
  - [在庫不足アクション](../../managing-inventory/low-stock-action.md)
