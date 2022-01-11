# 価格範囲のファセットウィジェットを使う

Liferay Commerceには、商品の検索結果を価格によって素早く絞り込むための *Price Range Facet* ウィジェットが含まれています。 このウィジェットを使用するには、目的のページにCommerce検索結果ウィジェットもデプロイされていることを確認してください。

```{note}
   Liferay DXP 7.3 SP2をはじめとし、価格範囲のファセットウィジェットは、商品の最低価格を使用して結果を絞り込みます。

   以前のバージョンのCommerceでは、ウィジェットは商品の基本価格を使用し、単一のSKUを持つ商品でのみ機能します。 ファセットが有効な場合、複数のSKUを持つ商品は表示されません。
```

![チャネル商品を価格を使用して絞り込みたい場合、価格範囲のファセットウィジェットを使います。](using-the-price-range-facet-widget/images/01.png)

価格範囲のファセットウィジェットをページに追加したら、そのフィールドを設定することができます。

1. ウィジェットの上にカーソルを置き、 *アクション*ボタン(![Actions Button](../../images/icon-actions.png))をクリックし、 *設定*を選択してください。

   ![ウィジェットの設定を行います。](./using-the-price-range-facet-widget/images/02.png)

1. *Setup* タブで、以下のフィールドを設定します：

   **Show Input Range**: ウィジェットで顧客が価格範囲をカスタム入力できるかどうかを定めます。

   **Ranges JSON Array**: ウィジェットに規定値の価格範囲を設定します。

   ![Setupタブでウィジェットのフィールドを設定します。](using-the-price-range-facet-widget/images/03.png)

1. *［保存］*をクリックします。

他のCommerceウィジェットについては、 [ウィジェットリファレンス](../liferay-commerce-widgets/widget-reference.md) を参照してください。
