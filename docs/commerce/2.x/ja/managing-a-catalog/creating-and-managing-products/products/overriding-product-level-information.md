# 商品レベルの情報を上書きする

カタログ内の商品SKUは、通常、親商品から仕様と構成を継承します。 特定の商品構成または仕様をSKUごとにオーバーライドできます。

## 商品出荷値の上書き

SKUは、商品用に構成されたものとは異なる出荷重量と寸法を持つように構成できます。 SKUの重量と寸法を変更すると、商品とは関係なく、その特定のSKUの送料計算が更新されます。

配送オーバーライドを作成するには：

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品（たとえば、 *Generic Coffee*）をクリックします。

3.  [ *SKUs* ]タブをクリックします。

    ![[SKU]タブで上書きを構成する](./overriding-product-level-information/images/01.png)

4.  目的のSKU（VANILLA24）をクリックします。

5.  [ *Details* ]タブで、[ *Shipping Override* セクションまでスクロールします。

6.  SKUの寸法を入力します。

    ![寸法を入力してください](./overriding-product-level-information/images/02.png)

7.  完了したら、*[Publish]*をクリックします。

このSKUの配送オーバーライドが作成されました。

## 商品サブスクリプション設定の上書き

個々の商品SKUは、商品自体がサブスクリプション用に構成されていない場合でも、サブスクリプションベースで使用できるように構成できます。

特定のSKUの商品のサブスクリプション設定を上書きするには：

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品（たとえば、 *Generic Coffee*）をクリックします。

3.  [ *SKU* ]タブをクリックします。

4.  目的のSKU（COLD BREW6-PACK）をクリックします。

5.  [ *Subscriptions* ]タブをクリックします。

6.  *サブスクリプション設定のオーバーライド* トグルを *YES*スライドします。

7.  「 *支払いサブスクリプションを有効にする*」 トグルを *YES*スライドします。

8.  サブスクリプションに必要な値を入力します。

    ![寸法を入力してください](./overriding-product-level-information/images/03.png)

9.  [ *配信サブスクリプションを有効にする* 」トグルを *YES*スライドします。

10. サブスクリプションに必要な値を入力します。

    ![寸法を入力してください](./overriding-product-level-information/images/04.png)

11. 完了したら、*[Save]*をクリックします。

SKUがサブスクリプションベースで利用可能になりました。

## 追加情報

  - [仕様](./specifications.md)
  - [SKUを商品に追加する](./adding-skus-to-your-products.md)
