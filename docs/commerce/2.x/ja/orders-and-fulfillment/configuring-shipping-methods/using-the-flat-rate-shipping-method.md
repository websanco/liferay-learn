# 定額配送方法の使用

購入者は、任意の商品を配送するための定額配送オプションを選択できます。 複数の定額オプションを作成することにより、顧客に費用、速さ、またはセキュリティの選択肢を提供できます。

定額配送では、カタログ内のすべての商品に同じ費用がかかります。 ただし、商品の[[Configuration]](https://help.liferay.com/hc/articles/360017870032-Configuration-#shipping)タブの*[Shipping Extra Price]*フィールドから個々の商品について例外を作成できます。

デフォルトでは、定額配送方法が有効になっています。

新しい定額配送オプションを追加するには：

1.  *[Site Administration]* → *[Commerce]* → *[Settings]*に移動します。
2.  *[Shipping Methods]*タブをクリックします。
3.  *[Flat Rate]*をクリックします。
4.  *[Shipping Options]*タブをクリックします。 （Miniumアクセラレータを使用している場合、*[Standard Delivery]*および*[Expedited Delivery]*がデフォルトで作成されます。）
5.  （+）ボタンをクリックして、新しい配送オプションを追加します。
6.  次のように入力します：
      - **Name**：7日発送
      - **Amount**：3.95
      - **Priority**：0.0
7.  *[Save]*をクリックします。

新しい配送オプションが追加され、精算プロセスで利用可能になりました。

![定額配送オプションの追加](./using-the-flat-rate-shipping-method/images/01.png)

## 追加情報

  - [Using the Variable Rate Shipping Method](./using-the-variable-rate-shipping-method.md)
  - [Using FedEx as a Carrier Method](./using-fedex-as-a-carrier-method.md)
  - [Applying Shipping Method Restrictions](./applying-shipping-method-restrictions.md)
  - [Creating New Shipping Methods](https://help.liferay.com/hc/en-us/articles/360020751831)
