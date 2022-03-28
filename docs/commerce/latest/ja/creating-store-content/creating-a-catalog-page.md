# カタログページの作成

[カタログ](./commerce-storefront-pages/catalog.md) ページは、お客様がチャネル商品を閲覧、検索、そして選択するための主要な手段です。 カタログページには、以下のウィジェットを推奨します。 **検索バー** 、 **オプション・ファセット** 、 **仕様・ファセット** 。 **カテゴリー・ファセット** 、 **ソート** 、 **検索結果** となっています。 これらのウィジェットやその他のCommerceウィジェットの詳細は、 [ウィジェットリファレンス](./liferay-commerce-widgets/widget-reference.md) を参照してください。

この記事では、 [アクセラレータ](../starting-a-store/accelerators.md)を使用せずにカタログページを作成する方法の例を説明します。

<a name="prerequisites" />

## 前提条件

サイトやページの作成については、以下のリンクを参照してください：

* [ページフラグメントの作成](https://help.liferay.com/hc/en-us/articles/360018171331-Creating-Page-Fragments)
* [フラグメントからのコンテンツページの構築](https://help.liferay.com/hc/en-us/articles/360018171351-Building-Content-Pages-from-Fragments-)
* [ナビゲーションメニューの作成と管理](https://help.liferay.com/hc/en-us/articles/360018171531-Creating-and-Managing-Navigation-Menus)
* [アプリケーション表示テンプレートの使用](https://help.liferay.com/hc/en-us/articles/360017892632-Styling-Widgets-with-Application-Display-Templates)

<a name="add-and-configure-the-page" />

## ページの追加と設定

1. **ウィジェット** のテンプレートを使ってページを作成します。
1. **30-70カラム** レイアウトがデフォルトの選択です。 このレイアウトでは、検索を絞り込むウィジェットの左側の列が狭くなり、［**検索結果**］ ウィジェットの右側の列が広くなります。 ストアデザイナーは、さまざまなスタイルに合わせてさまざまなレイアウトを選択できます。

<a name="add-the-widgets-to-the-page" />

## ウィジェットをページに追加する

1. ウィジェットを目的の位置にドラッグアンドドロップします。
1. **3ドットアイコン** をクリックしてから ［**ルック＆フィール設定**］ をクリックして、ウィジェットタイトルを追加または削除します。

ウィジェットを含むページを作成および構成すると、基本的なカタログページは次のようになります。

![空白のカタログページ](./creating-a-catalog-page/images/02.png)

<a name="configure-the-search-bar-widget" />

## ［**検索バー**］ ウィジェットを構成する

［**検索バー**］ ウィジェットは、グローバルサイトスコープからのデータを表示するように構成する必要があります。 これは、すべての商品がサイトレベルではなくグローバルレベルで保存されるためです。

1. 検索バーウィジェットの **Actions** button (![Actions Button](../images/icon-actions.png))をクリックし、 **Configuration** を選択します。

    ![検索バーウィジェットのActionsボタンをクリックし、Configurationを選択します。](./creating-a-catalog-page/images/03.png)

1. ウィジェットのスコープを **Everything** に設定します。

    ![ウィジェットのスコープを Everythingに設定します。](./creating-a-catalog-page/images/04.png)

1. ［**保存**］ をクリックします。

これで **検索バー** ウィジェットは、すべてのチャネルのコンテンツを表示するように設定されました。

<a name="displaying-the-full-catalog-without-a-search-optional" />

## 検索せずにフルカタログを表示する（オプショナル）

**検索オプション** ウィジェットを使用すると、 **検索結果** ウィジェットで、検索クエリを必要とせずにすべてのチャネル商品を表示するように設定できます。 これにより、お客様はチャネルに表示されているすべての商品を簡単に閲覧し、フィルタリングすることができます。

1. ［**検索オプション**］ ウィジェットで ［**Configure additional search options in this page**］ をクリックします。

1. ［**空の検索を許可する**］ チェックボックスをオンにします。

1. ［**保存**］ をクリックします。

1. ［**設定**］ ウィンドウを閉じます。

このオプションを有効にすると、すべてのチャネル商品が **検索結果** ウィジェットに表示されます。

![有効にすると、すべてのチャネル商品が 検索結果ウィジェットに表示されます。](./creating-a-catalog-page/images/01.png)

デフォルトでは、商品はすべてのチャネルで表示されるようになっています。 ただし、表示するかどうかを特定のチャネルに限定することができます。 詳細は、 [チャネルを使用した商品の可視性の構成](../store-management/channels/configuring-product-visibility-using-channels.md) を参照してください。
