# 固定税率の設定

Liferay Commerceは、固定レートと住所による2種類の税計算をサポートしています。 固定方式では、購入するたびに同じレートが商品に適用されますが、住所別方式は、特定の地理的地域内の購入者にのみ適用されます。

固定税方式では、税カテゴリごとに個別に税率が設定されます。 徴収される税は、商品に割り当てられた税カテゴリによって異なります。

最初に、 *固定税率*機能を有効にします。

1.  *[Site Administration]* → *[Commerce]* → *[Settings]* に進みます。

2.  *[Taxes]*タブをクリックしてから、*[Tax Calculations]*サブタブをクリックします。

3.  *[Fixed Tax Rate]* をクリックします。

4.  税を購入価格の割合として定義する場合は、*[Percentage]*トグルを *[YES]* に切り替えます。 税を固定金額として定義するには無効にします。

5.  トグルを *[YES]* に切り替えます。

    ![固定税率を有効にする](./setting-tax-rate-by-fixed-rate/images/01.png)

6.  *[Save]*をクリックします。

次に、税カテゴリの税率を設定します。

1.  *[Tax Rates]* サブタブをクリックします。

2.  各税カテゴリの *[Rate]* フィールドに税率を入力します。

    ![税率を設定する](./setting-tax-rate-by-fixed-rate/images/02.png)

3.  *[Save]*をクリックします。

ストアは、この税カテゴリに該当するすべての注文に対して固定レートを徴収します。

## 追加情報

  - [Setting Tax Rates by Address](../configuring-taxes/setting-tax-rate-by-address.md)
  - [Creating Tax Categories](../configuring-taxes/creating-tax-categories.md)
