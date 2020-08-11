# チャネルを使用した商品の可視性の構成

デフォルトでは、商品はすべてのチャネルで表示されますが、表示は特定のチャネルに制限することができます。

この例では、同じ商品が複数のブランドで共有されているマルチブランドのシナリオを検討します。 ここでは、サイトごとに異なるブランド体験を持っています。

![同じカタログ内の複数の商品をフィルタリングして、複数のチャネルで販売することができます。](./configuring-product-visibility-using-channels/images/01.png)

Liferay Commerceでのチャネルの使用の詳細については、[Introduction to Channels](./introduction-to-channels.md)を参照してください。

## 前提条件

1.  1つ以上のサイトが作成されました。
2.  各サイトにチャネルが関連付けられている。
3.  商品がカタログに追加されている。

## 商品フィルターチャネルの構成

特定のチャネルで利用できるように商品を構成するには、次の手順に従います。

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品をクリックします。

3.  *Visibility* タブをクリックします。

    ![2.1での商品の可視性](./configuring-product-visibility-using-channels/images/03.png)

4.  追加ボタンをクリックします。

5.  目的のチャネル（たとえば、 *Test Channel One*）をクリックします。

6.  *[Add]*をクリックします。

これで、チャネルが商品に関連付けられました。

### Commerce 2.0以前

特定のチャネルで利用できるように商品を構成するには、次の手順に従います。

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品をクリックします（この例では*Torque Converters*）。

3.  *[Configuration]* サブタブをクリックします。

4.  *[Channels]*サイドバーオプションをクリックします。

5.  *[Enable filter channels]*トグルを*[Yes]*に切り替えます。

6.  商品を表示する各チャネルのボックスをオンにします（この場合は*NA Minium Store* ）。

    ![商品構成](./configuring-product-visibility-using-channels/images/02.png)

7.  *[Save]*をクリックします。

商品は、選択したチャネルでのみ表示されます。

## 追加情報

  - [Introduction to Channels](./introduction-to-channels.md)
  - [Managing Channels](./managing-channels.md)
