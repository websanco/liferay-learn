# カタログページの作成

[カタログ](./commerce-storefront-pages/catalog.md) ページは、お客様がチャネル商品を閲覧、検索、そして選択するための主要な手段です。 カタログページには、以下のウィジェットを推奨します。 _検索バー_、 _オプション・ファセット_、 _仕様・ファセット_。 _カテゴリー・ファセット_、 _ソート_、 _検索結果_となっています。 これらのウィジェットやその他のCommerceウィジェットの詳細は、 [Widget Reference](./liferay-commerce-widgets/widget-reference.md)を参照してください。

この記事では、 [アクセラレータ](../starting-a-store/accelerators.md)を使用せずにカタログページを作成する方法の例を説明します。

## 前提条件

サイトやページの作成については、以下のリンクを参照してください：

* [ページフラグメントの作成](https://help.liferay.com/hc/en-us/articles/360018171331-Creating-Page-Fragments)
* [フラグメントからのコンテンツページの構築](https://help.liferay.com/hc/en-us/articles/360018171351-Building-Content-Pages-from-Fragments-)
* [ナビゲーションメニューの作成と管理](https://help.liferay.com/hc/en-us/articles/360018171531-Creating-and-Managing-Navigation-Menus)
* [アプリケーション表示テンプレートの使用](https://help.liferay.com/hc/en-us/articles/360017892632-Styling-Widgets-with-Application-Display-Templates)

## ページの追加と設定

1. _ウィジェット_ のテンプレートを使ってページを作成します。
1. _30-70カラム_レイアウトがデフォルトの選択です。 このレイアウトでは、検索を絞り込むウィジェットの左側の列が狭くなり、_［検索結果］_ ウィジェットの右側の列が広くなります。 ストアデザイナーは、さまざまなスタイルに合わせてさまざまなレイアウトを選択できます。

## ウィジェットをページに追加する

1. ウィジェットを目的の位置にドラッグアンドドロップします。
1. _3ドットアイコン_ をクリックしてから _［ルック＆フィール設定］_ をクリックして、ウィジェットタイトルを追加または削除します。

ウィジェットを含むページを作成および構成すると、基本的なカタログページは次のようになります。

![空白のカタログページ](./creating-a-catalog-page/images/02.png)

## _［検索バー］_ ウィジェットを構成する

_［検索バー］_ ウィジェットは、グローバルサイトスコープからのデータを表示するように構成する必要があります。 これは、すべての商品がサイトレベルではなくグローバルレベルで保存されるためです。

1. 検索バーウィジェットの _Actions_ button (![Actions Button](../images/icon-actions.png))をクリックし、 _Configuration_を選択します。

    ![検索バーウィジェットのActionsボタンをクリックし、Configurationを選択します。](./creating-a-catalog-page/images/03.png)

1. ウィジェットのスコープを _Everything_に設定します。

    ![ウィジェットのスコープを Everythingに設定します。](./creating-a-catalog-page/images/04.png)

1. _［保存］_ をクリックします。

これで_検索バー_ ウィジェットは、すべてのチャネルのコンテンツを表示するように設定されました。

## 検索せずにフルカタログを表示する（オプショナル）

_検索オプション_ ウィジェットを使用すると、  _検索結果_ウィジェットで、検索クエリを必要とせずにすべてのチャネル商品を表示するように設定できます。 これにより、お客様はチャネルに表示されているすべての商品を簡単に閲覧し、フィルタリングすることができます。

1. _［検索オプション］_ ウィジェットで _［Configure additional search options in this page］_ をクリックします。

1. _［空の検索を許可する］_ チェックボックスをオンにします。

1. _［保存］_ をクリックします。

1. _［設定］_ ウィンドウを閉じます。

このオプションを有効にすると、すべてのチャネル商品が _検索結果_ウィジェットに表示されます。

![有効にすると、すべてのチャネル商品が 検索結果ウィジェットに表示されます。](./creating-a-catalog-page/images/01.png)

デフォルトでは、商品はすべてのチャネルで表示されるようになっています。 ただし、表示するかどうかを特定のチャネルに限定することができます。 詳細は、[Configuring Product Visibility Using Channels](../starting-a-store/channels/configuring-product-visibility-using-channels.md)を参照してください。
