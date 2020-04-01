# チャネルを使用したサイトごとの商品の可視性の構成

Liferay Commerceのデフォルトでは、カタログに追加された商品はグローバルに表示されます。商品は、Liferay Commerceインストールに追加されたすべてのサイトで表示されます。 ストア管理者は、基準に基づいて商品の可視性を特定のチャネルに制限することができます。

この例の目的として、マルチリージョンの販売者が単一のカタログから商品を提供するシナリオを考えます。

![カタログ編成](./configuring-product-visibility-by-site-using-channels/images/01.png)

Liferay Commerceでのチャネルの使用の詳細については、[Introduction to Channels](./introduction-to-channels.md)を参照してください。

## 前提条件

1.  複数のサイトが作成されている。
2.  各サイトにチャネルが関連付けられている。
3.  商品がカタログに追加されている。

## 商品フィルターチャネルの構成

特定のチャネルで利用できるように商品を構成するには、次の手順に従います。

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品をクリックします（この例では*Torque Converters*）。

3.  *[Configuration]* サブタブをクリックします。

4.  *[Channels]*サイドバーオプションをクリックします。

5.  *[Enable filter channels]*トグルを*[Yes]*に切り替えます。

6.  商品を表示する各チャネルのボックスをオンにします（この場合は*NA Minium Store* ）。

    ![商品構成](./configuring-product-visibility-by-site-using-channels/images/02.png)

7.  *[Save]* をクリックします。

商品は、選択したチャネルでのみ表示されます。

## 追加情報

  - [Introduction to Channels](./introduction-to-channels.md)
  - [Managing Channels](./managing-channels.md)
