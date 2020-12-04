# コンテンツの検索

すべてのインデックス付きコンテンツ（別名 Liferay DXPの*アセット* ）を検索結果として返すことができます。 [カスタムコンテンツにもインデックスを付けることができます](https://help.liferay.com/hc/en-us/articles/360032260612-Model-Entity-Indexing-Framework)そのため、ご使用のシステムには、デフォルトで含まれているもの以外の追加のアセットタイプがある場合があります。 次の画像は、Liferay DXPでインデックスが作成されるさまざまなタイプのコンテンツの例を示しています。

![これらのアプリがデプロイされている場合、そのコンテンツはデフォルトで検索可能です。](./searching-for-content/images/08.png)

<!-- move to search results documentation, most likely-->

<!--
**Searching for Users:** When you click an asset in the search results, it's displayed in an Asset Publisher (unless the *View in Context* option is selected in the Search Results portlet). Users are different, though. Think of them as invisible assets, not intended for display in the Asset Publisher application.  While Users appear as search results with other indexed assets, when you click one you're taken to the User's profile page. If public personal pages are disabled, clicking on a User from the list of search results shows you a blank page.
-->

## 検索バーの使用

デフォルトのLiferay DXP設定を使用すると、ユーザーは検索バーに検索語を入力して検索を行うことができます。 検索を実行すると、[検索ページ](../search-pages-and-widgets/working-with-search-pages/search-pages.md)に移動し、さまざまな検索ウィジェットをデプロイします。

![デフォルトの検索設定では、デフォルトのビューに検索バーが表示され、ユーザーに検索コンテキストの入力を求めます。](./searching-for-content/images/01.png)

### 検索用語を入力する

Liferayの検索インフラストラクチャは、サポートされている検索エンジン([Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/current/full-text-queries.html)および[Solr](http://lucene.apache.org/solr/features.html))によって実装されている全文検索に対応しています。
全文検索は、検索クエリに入力されたすべての単語（たとえば、 *space Vacation*）を各インデックス付きドキュメントのすべての単語と比較します。 Elasticsearchなどの検索エンジンは、関連性スコアを計算して、最良の結果が最初に返されるようにします。

全文検索に加えて、高度な検索構文がサポートされています。 基盤となる検索エンジンが正確な動作を決定するため、詳細については [Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.6/query-dsl-simple-query-string-query.html) または [Solr](https://lucene.apache.org/solr/guide/7_0/query-syntax-and-parsing.html) ドキュメントを参照してください。

![Elasticsearchのクエリ文字列構文を使用して、特定のフィールドのテキストを検索します。](./searching-for-content/images/02.png)

### 完全一致のフレーズ：引用検索

ユーザーが検索語（たとえば、 *アジャイルフレームワーク*）に、入力したとおりのフレーズで結果のみを生成させたい場合はどうなりますか？ 通常の全文検索では、 *アジャイルフレームワーク* 検索すると、用語 *アジャイル* および *フレームワーク*のみを含む検索結果と、両方の用語を含むが他のテキストで区切られたヒット、および完全に一致するフレーズの結果が返されます。 完全に一致するフレーズのヒットのみが返されるようにするには、それを引用符で囲みます *"アジャイルフレームワーク"*。

![検索語句を引用符で囲んで、完全に一致するフレーズを検索します。](./searching-for-content/images/04.png)

### プレフィックス検索

プレフィックス検索では、*楽器*という用語を検索すると、完全な単語を含むドキュメントだけでなく、*楽器*をプレフィックスとするバリエーションも返します。 例えば、*楽器*、*楽器の*、*器楽編成*の結果も返されます。

![" leverを検索すると、" また、 " leverage" と " leveraging" を返します。](./searching-for-content/images/03.png)

``` note::
   Prefix searching is available for many fields out of the box, but it's more complicated under the hood. The details of the field mapping, including the analyzer used on the field and any transformations performed, determine the final behavior.
```

ユーザーに確実に結果を表示するもう1つの方法は、 [検索候補](../search-pages-and-widgets/search-results/enabling-search-suggestions.md)を使用することです。

### 検索バーの構成

検索バーの動作は、そのポートレット設定画面で設定できます。

![構成画面で検索バーの動作を構成します。](./searching-for-content/images/05.png)

``` note::
   When you configure the globally embedded Search Bar widget at the top of one page, it configures the page-top Search Bar widget on all pages in the site. It also overrides the `destination Search Page's <../search-pages-and-widgets/working-with-search-pages/search-pages.md>`_ Search Bar portlet, if they're configured differently. However, it does not override Search Bar widgets manually placed on other pages.
```

いくつかのオプションがあります：

**キーワードパラメータ名：** 検索で入力したキーワードのパラメータ名を編集します。 たとえば、キーワード *データ* 検索するときのデフォルトのURLは次のようになります。

    http://localhost:8080/web/guest/search?q=data

キーワードパラメータ名を *キーワード* に変更すると、次のようになります。

    http://localhost:8080/web/guest/search?keyword=data

**スコープ：** このサイト（デフォルト）、すべて、およびユーザーに選択を許可の3つのオプションから選択します。 *このサイト* は、検索が実行されるサイトに関連付けられているアセットのみが検索されることを意味します。 *すべて*を選択して、検索範囲をすべてのサイトに展開します。 ユーザーが検索するスコープを選択できるようにするには、[ *ユーザーに選択させる*]を選択します。

![ユーザーに検索を実行する範囲を選択させます。](./searching-for-content/images/06.png)

**スコープパラメータ名：** 検索が行われているスコープのURLパラメータ名を設定します。 このパラメーターは、スコープ *ユーザーに* を選択させるが選択されている場合にのみURLに表示されます。 デフォルト値は*スコープ*ですので、*データ*という単語を検索するとデフォルトURLが生成されます

    http://localhost:8080/web/guest/search?q=data&scope=this-site

*スコープ*を*ターゲット*に変更するとURLが生成されます：

    http://localhost:8080/web/guest/search?q=data&target=this-site

**リンク先ページ：** [検索ページ](../search-pages-and-widgets/working-with-search-pages/search-pages.md)わかりやすいURLを提供します。 構成されていない場合、または存在しないページを指している場合、ユーザーに表示するには検索バーを構成する必要があるというメッセージが管理者に表示されます。

**高度な検索構文を使用：** Elasticsearchを使用している場合、これを有効にすると、ユーザーは [クエリ文字列構文](https://www.elastic.co/guide/en/elasticsearch/reference/7.6/query-dsl-simple-query-string-query.html) を検索バーに入力できます。 Solrを使用している場合は、 [適切な構文](https://lucene.apache.org/solr/guide/7_0/query-syntax-and-parsing.html)ドキュメントを参照してください。

## 追加情報

  - [検索候補を有効にする](../search-pages-and-widgets/search-results/enabling-search-suggestions.md)
