# 割引について

割引は、 *コントロールパネル*の *割引* メニューで作成されます。 ユーザーは、次のような複数の割引タイプとインセンティブを作成できます。

  - 特定の商品の価格を下げる
  - 送料無料を提供
  - 特定の [アカウントグループ](../account-management/creating-a-new-account-group.md)、またはより狭く定義された顧客グループへの割引の調整
  - 一定期間プロモーションをスケジュールする

<!-- end list -->

``` tip::
   割引は*価格表*とは異なります。 価格設定の詳細については、 `商品の価格設定方法の概要 <../managing-a-catalog/managing-prices/introduction-to-product-pricing-methods.md>`_ 、 `価格リストの作成 <../managing-a-catalog/managing-prices/creating-a-price-list.md>`_ 、および `Adding Tiered Pricing <../managing-a-catalog/managing-prices/adding-tiered-pricing.md>`_ を参照してください
```

## 割引タイプ

以下のタイプの割引が利用可能です。

| 割引タイプ | 説明                                                                                                                                                    |
| ----- | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| カテゴリ  | 割引は、特定の [カテゴリ](../managing-a-catalog/creating-and-managing-products/products/organizing-your-catalog-with-product-categories.md)タグ付けされたすべての商品に適用されます。 |
| 商品    | 割引は、特定の商品または商品のリストに適用されます。                                                                                                                            |
| 配送    | 割引は、パーセントまたは定額のいずれかで、運賃に適用されます。                                                                                                                       |
| 小計    | 注文の小計は、通常は税金やその他の手数料が適用される前に減額されます。                                                                                                                   |
| 合計    | 割引は全体に適用されます。                                                                                                                                         |

<!-- | Pricing Class | The discount is applied to all products in the same Product Group. | -->

![割引タイプを選択します。](./introduction-to-discounts/images/01.png)

<!--

The devs inadvertently added Pricing Class as a new discount type which adds a third tab called Pricing Classes. Unfortunately, it is not usable because the rest of the feature is not completed. It should be released in the 2.1.2 release.

-->

## クーポンコード

選択した割引タイプに関係なく、割引の対象となるクーポンコードを要求できます。 ユーザーはクーポンコードを指定して、メール、ニュースレター、または広告で共有できます。

![クーポンコード機能をアクティブにし、この割引タイプのクーポンコードを入力します。](./introduction-to-discounts/images/02.png)

クーポンコードを受け入れるには、 *クーポンコードエントリ* ウィジェットをストアの [チェックアウト](../creating-store-content/commerce-storefront-pages/checkout.md) ページにデプロイする必要があります。 （ストアサイトを構築するために [最小アクセラレータ](../starting-a-store/using-the-minium-accelerator-to-jump-start-your-b2b-store.md) を使用した場合、チェックアウトページは自動的に作成されます。） この例では、顧客が注文を確認しながらクーポンコードを入力できるように、 *クーポンコード入力* ウィジェットがページに配置されています。

![クーポンコード入力ウィジェットをチェックアウトページに配置します。](./introduction-to-discounts/images/07.png)

顧客がクーポンコードを入力して割引を適用すると、割引は更新された注文概要に反映されます。

![適用されると、割引は注文の概要に反映されます。](./introduction-to-discounts/images/08.png)

## 段階的割引

ユーザーは、パーセンテージに基づいて、すべての割引タイプに段階的な割引（レベル）を提供できます。 たとえば、顧客が期限前に購入した場合、レベル1として20％割引の基本割引を設定し、レベル2としてさらに5％割引を提供できます。

![割合に基づいて割引の異なるレベルを設定します。](./introduction-to-discounts/images/06.png)

## 有効期限

すべての割引は、プロモーション活動と一致する有効期限を持つように構成できます。

![クーポンコード機能をアクティブにし、この割引タイプのクーポンコードを入力します。](./introduction-to-discounts/images/03.png)

## 割引ルール

割引の対象となる要件を指定するルールを定義できます。 ルールには次の3つのタイプがあります。

| ルール               | 説明                                                    |
| ----------------- | ----------------------------------------------------- |
| カート合計             | カートの小計は、割引を受けるための最小しきい値を満たしている必要があります。                |
| これらのすべての商品を持っています | 割引を受けるには、注文に特定のすべての商品が含まれている必要があります。                  |
| これらの商品の1つを持っている   | 注文には、割引を受けるために、指定された商品のリストから少なくとも1つの商品が含まれている必要があります。 |

割引ルールは、[ *ルール* ]タブに追加されます。 また、開発者は可能 [新しい割引ルール実装](../developer-guide/adding-a-new-discount-rule-type.md)することも可能です。

![割引ルールを追加します。](./introduction-to-discounts/images/04.png)

「*これらのすべての商品を含む* 」または 「*がこれらの商品を1つ含む* 」割引ルールを使用する場合は、カタログ内のどの商品が割引の対象となるかを選択します。

![割引ルールタイプのドロップダウン](./introduction-to-discounts/images/05.png)

顧客が対象商品を選択すると、どのルールを選択したかに応じて商品に割引が適用されます。

## 次のステップ

  - [割引を作成する](./creating-a-discount.md)
