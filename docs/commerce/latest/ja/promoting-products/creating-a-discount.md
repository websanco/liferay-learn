# 割引を作成する

以下の手順に従って、割引タイプ、対象顧客を選択し、割引率を設定して割引を作成します。 割引が作成されたら、割引の対象となる追加の条件を指定します。

新しい割引を作成するには：

1.  *[Control Panel]* → *[Commerce]* → *[Discounts]*に移動します。

2.  （![Add](../images/icon-add.png)）ボタンをクリックして、新しい割引を追加します。

3.  名前を入力します（*スプリングセール*）。

4.  *ターゲット* ドロップダウンから割引タイプ（たとえば、 *小計*適用）を選択します。 （さまざまな割引タイプの詳細については、 [割引タイプ](./introduction-to-discounts.md#types-of-discounts) を参照してください。）

5.  この割引が適用されるチャネルのボックスをオンにします。

6.  この割引が適用される[アカウントグループ](../account-management/creating-a-new-account-group.md)を選択します。 この例では、*US East Coast*。

7.  クーポンコードを使用する場合は、[ *クーポンコードを使用* を *はい*に切り替えます。 次に、「 *カップルコード* 」フィールドにコードを入力します。 それ以外の場合は、 *NO*ままにします 。

8.  パーセンテージを使用する場合は、 *Use Percentage* トグルを *Yes*に切り替えます。

9.  *最大割引額*入力：$ 20.00

10. [*Level*](./introduction-to-discounts.md#tiered-discounts)：20.00と入力し
 。 これにより、小計から20％オフになります。</p></li> 
    
    11 *[Active]* トグルを *[YES]*に切り替えます。
  
  ![新しい割引](./creating-a-discount/images/01.png)

12 *[Publish]*をクリックします。</ol> 

ストアでは、指定されたアカウントグループの商品を対象に、選択した商品に20%割引が適用されるようになりました。



## ルールを割引に追加する

割引の対象となる要件を指定するルールを定義できます。 詳細については、 [割引ルール](./introduction-to-discounts.md#discount-rules) を参照してください。

1.  割引が作成されると、*[Detail]*および*[Rules]*の2つのタブが用意されます。 *[Rules]*タブをクリックします。
   
   ![割引ルールタブ](./creating-a-discount/images/02.png)

2.  （+）ボタンをクリックして、新しい割引ルールを追加します。

3.  *[Type]* ドロップダウンメニューから *[Cart Total]*を選択します。

4.  *[Cart Total Minimum Amount]*フィールドに15.00と入力します。

5.  *[保存]*をクリックします。

この例では、この特定の割引に割引ルールが適用されました。 顧客は、設定された金額を超えるすべての購入に対してこの割引を受けます。



## 追加情報

  - [割引について](./introduction-to-discounts.md)
  - [新規アカウントグループの作成](../account-management/creating-a-new-account-group.md)
  - [価格表の作成](../managing-a-catalog/managing-prices/creating-a-price-list.md)
  - [階層型価格設定の追加](../managing-a-catalog/managing-prices/adding-tiered-pricing.md)
