# カタログページの作成

[[Catalog]](./commerce-storefront-pages/catalog.md) ページは、ユーザーがストアで提供されている商品を閲覧するための最初のエントリーポイントです。 このページには、次のウィジェットが含まれています：*[Search Bar]*、*[Options Facets]*、*[Specification Facet]*、*[Category Facet]* および *[Search Results]*。 Commerceウィジェットの詳細は、[Widget Reference](./widget-reference.md)を参照してください。

この記事では、 [アクセラレータ](../starting-a-store/accelerators.md)を使用せずにカタログページを作成する方法の例を説明します。

## 前提条件

サイトおよびページ作成の詳細は、次のリンクを参照してください。

  - [Creating Page Fragments](https://help.liferay.com/hc/en-us/articles/360018171331-Creating-Page-Fragments)
  - [Building Content Pages from Fragments](https://help.liferay.com/hc/en-us/articles/360018171351-Building-Content-Pages-from-Fragments-)
  - [Creating and Managing Navigation Menus](https://help.liferay.com/hc/en-us/articles/360018171531-Creating-and-Managing-Navigation-Menus)
  - [Using Application Display Templates](https://help.liferay.com/hc/en-us/articles/360017892632-Styling-Widgets-with-Application-Display-Templates)

## ページを追加して構成する

1.  *ウィジェット* テンプレートを使用してページを作成します。
2.  *30-70カラム*レイアウトがデフォルトの選択です。 このレイアウトでは、検索を絞り込むウィジェットの左側の列が狭くなり、*[Search Results]* ウィジェットの右側の列が広くなります。 ストアデザイナーは、さまざまなスタイルに合わせてさまざまなレイアウトを選択できます。

## ウィジェットをページに追加する

1.  ウィジェットを目的の位置にドラッグアンドドロップします。
2.  *3ドットアイコン* をクリックしてから *[Look and Feel Configurations]* をクリックして、ウィジェットタイトルを追加または削除します。

ウィジェットを含むページを作成および構成すると、基本的なカタログページは次のようになります。

![空白のカタログページ](./creating-a-catalog-page/images/02.png)

## *[Search Bar]* ウィジェットを構成する

*[Search Bar]* ウィジェットは、グローバルサイトスコープからのデータを表示するように構成する必要があります。 これは、すべての商品がサイトレベルではなくグローバルレベルで保存されるためです。

1.  *[Search Bar]* ウィジェットで*3ドットアイコン*をクリックしてから *[Configuration]* をクリックします。

    ![検索バーの構成](./creating-a-catalog-page/images/03.png)

2.  *[Scope]*ドロップダウンメニューから *[Everything]*を選択します。

    ![すべてのスコープ](./creating-a-catalog-page/images/04.png)

3.  *[Save]* をクリックします。

4.  *[Configuration]* ウィンドウを閉じます。

*[Search Bar]* ウィジェットが、インスタンス全体のコンテンツを表示するように構成されました。

## 検索なしで完全なカタログを表示する（オプション）

Liferay Commerceでは、ストアマネージャーは、購入者に最初に検索クエリを入力してもらわなくても、カタログ内のすべての商品を *[Search Results]* ウィジェットに表示できます。 これを行うには、 *[Search Options]* ウィジェットを構成します。

1.  *[Search Options]* ウィジェットで *[Configure additional search options in this page]* をクリックします。
2.  *[Allow Empty Searches]* チェックボックスをオンにします。
3.  *[Save]*をクリックします。
4.  *[Configuration]* ウィンドウを閉じます。

このオプションを有効にすると、このカタログ内のすべての商品が *[Search Results]* ウィジェットに表示されます。

![空の検索](./creating-a-catalog-page/images/01.png)

## 商品チャネルフィルターの構成（オプション）

Liferay Commerceインスタンスに格納されているストアサイトが複数ある場合、カタログ全体がすべてのストアサイトで検索可能になります。

商品の範囲を特定のサイトに限定するには、 [チャネルを使用した商品の表示設定](../starting-a-store/channels/configuring-product-visibility-using-channels.md)を参照してください。
