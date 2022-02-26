# コンテンツの検索

すべてのインデックス付きコンテンツ（別名 Liferay DXPの **アセット**）を検索結果として返すことができます。 [カスタムコンテンツにもインデックスを付けることができます](https://help.liferay.com/hc/en-us/articles/360032260612-Model-Entity-Indexing-Framework) そのため、ご使用のシステムには、デフォルトで含まれているもの以外の追加のアセットタイプがある場合があります。 次の画像は、Liferay DXPでインデックスが作成されるさまざまなタイプのコンテンツの例を示しています。

![これらのアプリがデプロイされている場合、そのコンテンツはデフォルトで検索可能です。](./searching-for-content/images/08.png)

<a name="検索バーの使用" />

## 検索バーの使用

デフォルトのLiferay DXP設定を使用すると、ユーザーは検索バーに検索語を入力して検索を行うことができます。 検索を実行すると、 [検索ページ](../search-pages-and-widgets/working-with-search-pages/search-pages.md) に移動し、さまざまな検索ウィジェットをデプロイします。

![デフォルトの検索設定では、デフォルトのビューに検索バーが表示され、ユーザーに検索コンテキストの入力を求めます。](./searching-for-content/images/01.png)

### 検索用語を入力する

Liferayの検索インフラストラクチャは、サポートされている検索エンジン( [Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/current/full-text-queries.html) および [Solr](http://lucene.apache.org/solr/features.html) )によって実装されている全文検索に対応しています。

全文検索では、検索クエリに入力されたすべての単語（例えば、 **stock market**）と、インデックスされた各文書に含まれるすべての単語を比較します。 Elasticsearchのような検索エンジンは、関連性スコアを計算して、最適な結果が最初に返されるようにしています（最近の強気の市場についてのBlog Entryなど）。 **stock** や **market** のような単語を含むものが返されます。

全文検索に加えて、高度な検索構文がサポートされています。 基盤となる検索エンジンが正確な動作を決定するため、詳細は [Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.6/query-dsl-simple-query-string-query.html) または [Solr](https://lucene.apache.org/solr/guide/7_0/query-syntax-and-parsing.html) ドキュメントを参照してください。

![Elasticsearchのクエリ文字列構文を使用して、特定のフィールドのテキストを検索します。](./searching-for-content/images/02.png)

### 完全一致のフレーズ：引用検索

ユーザーが検索語（たとえば、 **アジャイルフレームワーク**）に、入力したとおりのフレーズで結果のみを生成させたい場合はどうなりますか？ 通常の全文検索では、 **アジャイルフレームワーク** 検索すると、用語 **アジャイル** および **フレームワーク** のみを含む検索結果と、両方の用語を含むが他のテキストで区切られたヒット、および完全に一致するフレーズの結果が返されます。 完全に一致するフレーズのヒットのみが返されるようにするには、それを引用符で囲みます **"アジャイルフレームワーク"** 。

![検索語句を引用符で囲んで、完全に一致するフレーズを検索します。](./searching-for-content/images/04.png)

### プレフィックス検索

プレフィックス検索では、 **楽器** という用語を検索すると、完全な単語を含むドキュメントだけでなく、 **楽器** をプレフィックスとするバリエーションも返します。 例えば、 **楽器** 、 **楽器の** 、 **器楽編成** の結果も返されます。

![" leverを検索すると、" また、 " leverage" と " leveraging" を返します。](./searching-for-content/images/03.png)

```{note}
   プレフィックス検索は、多くのフィールドですぐに利用できますが、ボンネットの中はもっと複雑です。 フィールドで使用されている分析器や実行された変換など、フィールドマッピングの詳細が最終的な動作を決定します。
```

ユーザーに確実に結果を表示するもう1つの方法は、 [検索候補](../search-pages-and-widgets/search-results/enabling-search-suggestions.md)を使用することです。

### 検索バーの構成

検索バーの動作は、そのポートレット設定画面で設定できます。

![構成画面で検索バーの動作を構成します。](./searching-for-content/images/05.png)

```{note}
   あるページの上部にグローバルに埋め込まれた検索バーウィジェットを設定すると、サイト内のすべてのページでページトップの検索バーウィジェットが設定されます。 また `destination Search Page's <../search-pages-and-widgets/working-with-search-pages/search-pages.md>`_ Search Bar Portlet' が異なる設定になっている場合は、そちらも上書きされます。 ただし、他のページに手動で設置した検索バーウィジェットを上書きすることはできません。
```

いくつかのオプションがあります：

**キーワードパラメータ名：** 検索で入力したキーワードのパラメータ名を編集します。 たとえば、キーワード **データ** 検索するときのデフォルトのURLは次のようになります。

```
http://localhost:8080/web/guest/search?q=data
```

キーワードパラメータ名を **キーワード** に変更すると、次のようになります。

```
http://localhost:8080/web/guest/search?keyword=data
```

**スコープ：** このサイト（デフォルト）、すべて、およびユーザーに選択を許可の3つのオプションから選択します。 **このサイト** は、検索が実行されるサイトに関連付けられているアセットのみが検索されることを意味します。 **すべて** を選択して、検索範囲をすべてのサイトに展開します。 ユーザーが検索するスコープを選択できるようにするには、［**ユーザーに選択させる**］を選択します。

![ユーザーに検索を実行する範囲を選択させます。](./searching-for-content/images/06.png)

**スコープパラメータ名：** 検索が行われているスコープのURLパラメータ名を設定します。 このパラメーターは、スコープ **ユーザーに** を選択させるが選択されている場合にのみURLに表示されます。 デフォルト値は **スコープ** ですので、 **データ** という単語を検索するとデフォルトURLが生成されます

```
http://localhost:8080/web/guest/search?q=data&scope=this-site
```

**スコープ** を **ターゲット** に変更するとURLが生成されます：

```
http://localhost:8080/web/guest/search?q=data&target=this-site
```

**リンク先ページ：** [検索ページ](../search-pages-and-widgets/working-with-search-pages/search-pages.md) わかりやすいURLを提供します。 構成されていない場合、または存在しないページを指している場合、ユーザーに表示するには検索バーを構成する必要があるというメッセージが管理者に表示されます。

**高度な検索構文を使用：** Elasticsearchを使用している場合、これを有効にすると、ユーザーは [クエリ文字列構文](https://www.elastic.co/guide/en/elasticsearch/reference/7.6/query-dsl-simple-query-string-query.html) を検索バーに入力できます。 Solrを使用している場合は、 [適切な構文](https://lucene.apache.org/solr/guide/7_0/query-syntax-and-parsing.html) ドキュメントを参照してください。

<a name="追加情報" />

## 追加情報

* [検索結果の見方](../search-pages-and-widgets/search-results/search-results-behavior.md)
* [検索候補を有効にする](../search-pages-and-widgets/search-results/enabling-search-suggestions.md)
