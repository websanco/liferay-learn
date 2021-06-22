# 在庫不足アクション

この記事では、商品の在庫が指定されたしきい値を下回った場合の自動アクションを設定する方法について説明します。 Liferay Commerceでは、商品が定義された在庫しきい値に達したときに、その商品を自動的に非公開に設できるように事前に設定されています。

在庫不足アクションを設定するには：

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品をクリックします（たとえば、*U-Joint*）。

3.  *[Configurations]* サブタブをクリックします。

4.  次のように入力します：

      - **Inventory Engine**：デフォルト
      - **Availability Estimate**：5～7日
      - **Display Availability**：YES
      - **Display Stock Quantity**：YES
      - **Low Stock Threshold**：5
      - **Low Stock Action**：[Unpublished]として設定
      - **Allow Back Orders**：Yes
      - **Minimum Order Quantity**：1
      - **Maximum Order Quantity**：5
      - **Allowed Order Quantities**：1
      - **Multiple Order Quantity**：1

    ![在庫不足アクションの商品設定](./low-stock-action/images/01.png)

5.  *[Publish]*をクリックします。

この商品の在庫不足アクションが設定されました。 今後、在庫数が*5*を下回ると、「U-Joint」商品は非公開になります。

## 追加情報

  - [Product Inventory Configuration Reference](./product-inventory-configuration-reference.md)
  - [Implementing a Custom Low Stock Action](../../developer-guide/implementing-a-custom-low-stock-activity.md)
