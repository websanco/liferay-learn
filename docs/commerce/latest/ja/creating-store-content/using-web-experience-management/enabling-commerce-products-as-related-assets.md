# コマース商品を関連するアセットとして利用可能に

コマース商品は、 [関連するアセット](https://help.liferay.com/hc/articles/360028820532-Defining-Content-Relationships)の関係を使用して、Liferay Commerceの他のアセットに接続できます。

```{note}
コンテンツの編集時に、関連するアセットのオプションとしてコマース商品を表示するには、検索のインデックスの再構築が必要な場合があります。
```

## データベースの検索のためにアセットブラウザーを設定する

1.  ``[Liferay Home]\osgi\marketplace\`に移動します。 ``Liferay Home`フォルダーについての詳細は、[Liferay Home](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/liferay-home.html)を参照してください。
2.  `Liferay Web Experience - Liferay Asset - Impl.lpkg`を検索し、アーカイブマネージャーで開きます。
3.  アーカイブマネージャーウインドウで、`com.liferay.asset.browser.web.jar`を開きます。
4.  テキストエディターを使用して、`portlet.properties`ファイルを開きます。
5.  `search.with.database=true`を設定し、jarを更新します。
6.  アプリケーションサーバーを再起動します。

## インデックスの再構築

インデックスの再構築を行い、関連するアセットがフィルターに入力されていることを確認します。

1.  *[コントロールパネル]* → *[設定]* → *[検索]* に移動します。
2.  [すべての検索インデックスを再構築]の横にある*[実行]* をクリックします。

## コマース商品の在庫状況の確認

コマース商品はグローバルに保管されるため（つまり、すべてのLiferay DXPサイトで利用可能）、ユーザーはフィルターを*グローバル*に変更する必要があります。

1.  *[サイト管理]* → *[コンテンツ]* → *[Webコンテンツ]* をクリックします。

2.  （![Add icon](../../images/icon-add.png)）アイコンをクリックし、次に*[基本Webコンテンツ]* をクリックします。

3.  *関連するアセット* のセクションまでスクロールします。

4.  *クリック*選択します。

5.  *[コマース商品]* をクリックします。

    ![[関連するアセット]セクションでコマース商品を選択します。](./enabling-commerce-products-as-related-assets/images/01.png)

6.  *[フィルターと並び替え]*、次に[グローバル]をクリックします。

    ![グローバルフィルターを選択すると、コマース商品が表示されます。](./enabling-commerce-products-as-related-assets/images/02.png)

7.  商品をクリックします。

これで、コマース商品を関連するアセットとして利用できるようになります。

## 追加情報

  - [コンテンツ関連の定義](https://help.liferay.com/hc/articles/360028820532-Defining-Content-Relationships)
  - [基本的なWebコンテンツの記事の追加](https://learn.liferay.com/dxp/7.x/en/content-authoring-and-management/web-content/user-guide/web-content-articles/adding-a-basic-web-content-article.html)
