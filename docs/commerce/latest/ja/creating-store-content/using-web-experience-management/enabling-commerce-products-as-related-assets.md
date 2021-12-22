# コマース商品を関連するアセットとして利用可能に

コマース商品は、 [関連するアセット](https://help.liferay.com/hc/articles/360028820532-Defining-Content-Relationships)の関係を使用して、Liferay Commerceの他のアセットに接続できます。

```{note}
   コンテンツの編集時に、関連するアセットのオプションとしてコマース商品を表示するには、検索のインデックスの再構築が必要な場合があります。
```

## データベースの検索のためにアセットブラウザーを設定する

1. ``［Liferay Home］\osgi\marketplace\`に移動します。 ``Liferay Home`フォルダーについての詳細は、[Liferay Home](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/liferay-home.html)を参照してください。
1. `Liferay Web Experience - Liferay Asset - Impl.lpkg`を検索し、アーカイブマネージャーで開きます。
1. アーカイブマネージャーウインドウで、`com.liferay.asset.browser.web.jar`を開きます。
1. テキストエディターを使用して、`portlet.properties`ファイルを開きます。
1. `search.with.database=true`を設定し、jarを更新します。
1. アプリケーションサーバーを再起動します。

## インデックスの再構築

インデックスの再構築を行い、関連するアセットがフィルターに入力されていることを確認します。

1. Navigate to the _Control Panel_ &rarr; _Configuration_ &rarr; _Search_.
1. ［すべての検索インデックスを再構築］の横にある_［実行］_をクリックします。

## コマース商品の在庫状況の確認

コマース商品はグローバルに保管されるため（つまり、すべてのLiferay DXPサイトで利用可能）、ユーザーはフィルターを_グローバル_に変更する必要があります。

1. _［サイト管理］_ &rarr; _［コンテンツ］_ &rarr; _［Webコンテンツ］_をクリックします。
1. （![Add icon](../../images/icon-add.png)）アイコンをクリックし、次に_［基本Webコンテンツ］_をクリックします。
1. _関連するアセット_ のセクションまでスクロールします。
1. _クリック_選択します。
1. _［コマース製品］_をクリックします。

     ![［関連するアセット］セクションでコマース商品を選択します。](./enabling-commerce-products-as-related-assets/images/01.png)

1. _［フィルターと並び替え ］_ そして_［グローバル］_をクリックします。

     ![グローバルフィルターを選択すると、コマース商品が表示されます。](./enabling-commerce-products-as-related-assets/images/02.png)

1. 商品をクリックします。

これで、コマース商品を関連するアセットとして利用できるようになります。

## 追加情報

* [コンテンツ関連の定義](https://help.liferay.com/hc/articles/360028820532-Defining-Content-Relationships)
* [基本的なWebコンテンツの記事の追加](https://learn.liferay.com/dxp/latest/en/content-authoring-and-management/web-content/user-guide/web-content-articles/adding-a-basic-web-content-article.html)
