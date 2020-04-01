# 商品ごとの割引の追加

割引は、商品の基本価格に修飾子を適用します。 絶対数またはパーセンテージにすることができ、限られた数の商品またはカタログ全体に適用できます。 すべての購入者、定義済みのアカウントグループ、または特定の資格を満たすより狭く定義された顧客グループが利用できます。 価格表とは異なり、割引があると必ず基本価格が*下がります*。

割引には、カテゴリ、商品、出荷、小計、合計の5種類があります。 この記事では、商品ごとに割引を追加する方法について説明します。

## 商品ごとに割引を作成する

商品ごとに割引を作成するには：

1.  *[Control Panel]* → *[Commerce]* → *[Discounts]*に移動します。

2.  （+）ボタンをクリックして、新しい割引を追加します。

3.  名前を入力します（*クリスマスセール*）。

4.  *[Target]*ドロップダウンから割引タイプを選択します。 *[Apply to Product]*を選択します。

5.  この割引が適用されるチャネルのボックスをオンにします。

6.  この割引が適用される[アカウントグループ](../account-management/creating-a-new-account-group.md)を選択します。 この例では、*US East Coast*。

7.  *[Use Percentage]* トグルを *[Yes]*に切り替えます。

8.  *[Maximum Discount Amount]*を入力します（20％）。

9.  *[Level]*を入力します（1.00）。

10. *[Active]* トグルを *[YES]*に切り替えます。

    ![新しい割引](./adding-discounts-by-product/images/01.png)

11. *[Publish]*をクリックします。

ストアでは、指定されたアカウントグループの商品を対象に、選択した商品に20%割引が適用されるようになりました。

## ルールを割引に追加する

購入者の割引条件を制限するルールを作成できます。 ルールには次の3つのタイプがあります。

**Cart Total**：割引を受け取るには、カートの小計が最小しきい値を満たしている必要があります。

**Has all of these products**：割引を受けるには、指定されているすべての商品が注文に含まれている必要があります。

**Has one of these products**：割引を受けるには、指定された商品リストから少なくとも1つの商品が注文に含まれている必要があります。

これらのルールは、販売およびマーケティングの目標達成のために、必要に応じて組み合わせることができます。

1.  割引が作成されると、*[Detail]*、*[Rules]*、および*[Products]*の3つのタブが用意されます。 *[Rules]*タブをクリックします。

    ![割引ルールタブ](./adding-discounts-by-product/images/02.png)

2.  （+）ボタンをクリックして、新しい割引ルールを追加します。

3.  *[Type]* ドロップダウンメニューから *[Cart Total]*を選択します。

4.  *[Cart Total Minimum Amount]*フィールドに25.00と入力します。

5.  *[Save]*をクリックします。

この例では、この特定の割引に割引ルールが適用されました。 顧客は、設定された金額を超えるすべての購入に対してこの割引を受けます。

他の2つのオプション（**[Has all of these products]**および**[Has one of these products]**）は、販売者がカタログのどの商品を割引の対象にするかを選択できるようにすることで機能します。

![割引ルールタイプのドロップダウン](./adding-discounts-by-product/images/03.png)

含める商品を選択します。

![割引の商品選択](./adding-discounts-by-product/images/04.png)

顧客が対象商品を選択すると、どのルールを選択したかに応じて商品に割引が適用されます。

## 追加情報

  - [Adding Discounts by Subtotal](../promoting-products/adding-discounts-to-the-subtotal.md)
  - [Creating a New Account Group](../account-management/creating-a-new-account-group.md)
