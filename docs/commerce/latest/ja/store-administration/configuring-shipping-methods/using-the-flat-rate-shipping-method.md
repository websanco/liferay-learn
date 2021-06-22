# 定額配送方法の使用

購入者は、任意の商品を配送するための定額配送オプションを選択できます。 複数の定額オプションを作成することにより、顧客に費用、速さ、またはセキュリティの選択肢を提供できます。

定額配送では、カタログ内のすべての商品に同じ費用がかかります。 ただし、商品の[[Configuration]](../../managing-a-catalog/managing-inventory/product-inventory-configuration-reference.md)タブの*[Shipping Extra Price]*フィールドから個々の商品について例外を作成できます。

デフォルトでは、定額配送方法が有効になっています。

新しい定額配送オプションを追加するには：

1.  *[Control Panel]* → *[Commerce]* → *[Channels]*に移動します。

    ![コントロールパネルのチャネルに移動します。](./using-the-flat-rate-shipping-method/images/02.png)

2.  目的のチャネル（たとえば、Sahara.com）をクリックします。 （Miniumなどのアクセラレータを使用してサイトを作成した場合は、対応するチャネルがデフォルトですでに作成されています。）

3.  [ *Shipment Methods*]までスクロールします。

    ![配送方法はチャネルで設定されます。](./using-the-flat-rate-shipping-method/images/03.png)

4.  *[Edit]*をクリックします。

5.  *[Active]* トグルを *[YES]*に切り替えます。

6.  *[Shipping Options]*タブをクリックします。

7.  追加（![Add Icon](../../images/icon-add.png)）ボタンをクリックして、新しい配送オプションを追加します。

8.  次のように入力します：

      - **Name**：7日発送
      - **Amount**：3.95
      - **Priority**：0.0

    ![新しい配送方法オプションを追加します。](./using-the-flat-rate-shipping-method/images/04.png)

9.  *[Save]*をクリックします。

10. ウィンドウを閉じます。

![新しい配送方法オプションが作成されていることを確認します。](./using-the-flat-rate-shipping-method/images/05.png)

新しい配送方法オプションが追加されました。

![新しい配送方法オプションが利用可能であることを確認します。](./using-the-flat-rate-shipping-method/images/06.png)

## Commerce 2.0以前

新しい定額配送オプションを追加するには：

1.  *[Site Administration]* → *[Commerce]* → *[Settings]*に移動します。
2.  *[Shipping Methods]*タブをクリックします。
3.  *[Flat Rate]*をクリックします。
4.  *[Shipping Options]*タブをクリックします。 （Miniumアクセラレータを使用している場合、*[Standard Delivery]*および*[Expedited Delivery]*がデフォルトで作成されます。）
5.  追加（![Add Icon](../../images/icon-add.png)）ボタンをクリックして、新しい配送オプションを追加します。
6.  次のように入力します：
      - **Name**：7日発送
      - **Amount**：3.95
      - **Priority**：0.0
7.  *[Save]*をクリックします。

新しい配送オプションが追加され、精算プロセスで利用可能になりました。

## 追加情報

  - [Using the Variable Rate Shipping Method](./using-the-variable-rate-shipping-method.md)
  - [Using FedEx as a Carrier Method](./using-the-fedex-shipping-method.md)
  - [配送方法の制限の適用](./applying-shipping-method-restrictions.md)
  - [新しい配送エンジンの実装](../../developer-guide/implementing-a-new-shipping-engine.md)
