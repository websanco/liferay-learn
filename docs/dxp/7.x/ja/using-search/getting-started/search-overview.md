# 検索の概要

LiferayはElasticsearchのバンドルされたインスタンスを使用して、システム全体の検索を強化します。 テストと開発にはバンドルされたインスタンスを使用しますが、システムと検索のパフォーマンスを最適化するには、スタンドアロンのElasticsearchインスタンスを本番環境にインストールする必要があります。

Liferay DXPを起動して、すぐに使用できる検索機能の探索を開始します。

  - 全文検索機能
  - すべてのコンテンツタイプ（ブログ、ドキュメント、Webコンテンツなど）のインデックス作成
  - 高度に設定可能な検索ページ
  - 各ページのヘッダーに埋め込まれた検索バー
  - 新しいコンテンツが追加、更新、または削除されたときの自動インデックス同期
  - 役割と権限でフィルタリングされた検索結果
  - 検索候補
  - 検索結果の構成（例：フィルターと並べ替え）
  - ファセット検索
  - 高度な検索構文を有効または無効にします（例：AND/OR/NOT、ワイルドカードなど）。

## 検索ページとウィジェット

すぐに使用できる検索ウィジェットを使用して、完全に機能する検索ページをすばやく作成できます。 さらに、グローバル検索ページテンプレートは、必要に応じて使用または変更できます。

デフォルトの検索ページ（`localhost：8080/search`）は検索ページテンプレートに基づいており、適切な検索ウィジェットのセットが含まれていますが、カスタマイズも可能です。

![検索ページテンプレートは便利です。](./search-overview/images/05.png)

検索ウィジェットをページに追加するには、ページの[追加]ボタンをクリックし、[ *ウィジェット*]を選択して、[検索]カテゴリを開きます。

![検索ウィジェットをページに追加します。](./search-overview/images/07.png)

検索ページの作成の詳細については、こちら [読みください](https://help.liferay.com/hc/en-us/articles/360028821052-Configuring-Search-Pages)。

各ウィジェットの機能の詳細については、[こちら](../search-pages-and-widgets/README.md)を読みください。

## 検索の構成と管理

検索の仕組みとコンテンツのインデックス作成方法を構成するには、いくつかの方法があります。

システムスコープの検索構成の多くは、[システム設定]→[検索]にあります。

![検索システム設定は多数あります。](./search-overview/images/06.png)

追加の管理画面は、[コントロールパネル]→[構成]→[検索]セクションにあります。

![検索管理画面は有益で便利です。](./search-overview/images/08.png)

検索の構成について詳しくは、こちらの [読みください](../search-administration-and-tuning/README.md)。

## 検索におけるカスタム開発

検索用のカスタムコードは通常、これらの検索フェーズの少なくとも1つに介入する必要があります。

**インデックス** は、1つ以上のドキュメントを検索エンジンに送信することです。 ドキュメントには、さまざまなタイプのフィールド（テキスト、キーワードなど）が含まれています。 検索エンジンは各フィールドを処理し、フィールドを保存するか分析するかを決定します。

**検索** は、検索クエリを送信して結果を取得します（別名 ヒット）検索エンジンから。 クエリとフィルタは検索リクエストの一部にすることができ、どちらも検索対象のフィールドと照合する値を指定します。 検索エンジンは、ネストされたクエリとフィルター内の各フィールドを反復処理し、クエリを実行する前に特別な分析を実行する場合があります（検索時間分析）。 検索時間分析は、マッピング定義を介してフィールドごとに構成できます。

検索に関する開発機能は、次の2つのカテゴリに分類できます。

  - サービスプロバイダーインターフェイス（SPI）は実装することを目的としています。 ソースコードでは、これらは `-spi` 終わるモジュールにあります（たとえば、 [`portal-search-spi` module](https://github.com/liferay/liferay-portal/tree/master/modules/apps/portal-search/portal-search-spi)）。

  - APIには、独自のコードで呼び出すことができるメソッドが含まれています。 ソースコードでは、これらは `-api` 終わるモジュールにあります（たとえば、 [`portal-search-api` モジュール](https://github.com/liferay/liferay-portal/tree/master/modules/apps/portal-search/portal-search-api)）。

これらのAPIと拡張ポイントの使用に関する詳細は、開発者ガイドに記載されています。


<!--

Search is a fundamental component of Liferay DXP. If you're testing out the built-in search functionality or developing, there's a [search engine bundled](#elasticsearch) precisely for these purposes. Just start the portal and begin searching. In production environments, you must first install a search engine and configure Liferay DXP to begin using search.

Once a search engine holding your indexed data is freely communicating with Liferay DXP, you're ready to configure or customize the search experience.

Sites often feature lots of content split over lots of asset types. Web content articles, documents and media files, and blogs entries are just a few examples. Most content types are *assets*.Under the hood, assets use the [Asset API](https://help.liferay.com/hc/en-us/sections/360004656831-Asset-Framework) and [indexing code](#custom-development-in-search). Any content that has these features can be searched in Liferay DXP's out-of-the-box search widgets. 

![The Type Facet configuration lists the searchable out-of-the-box asset types.](./search-overview/images/01.png)

## Bundled Search Features

A bunch of search widgets are bundled with Liferay DXP:

- Search Bar
- Search Results
- Search Facets
- Custom Filter
- Search Insights
- Low Level Search Options
- Similar Results (bundled in 7.3+)
- Sort
- Search Options
- Suggestions
- X-Pack Monitoring (LES)

![Compose the search widgets to build your search page any way you see fit.](./search-overview/images/05.png)

Administrative search functionality is also included:

- Search Tuning
- Search Engine Connection Configuration
- System Level Search Configurations
- Adding and editing Search pages
- Viewing Indexes and Field Mappings
- Indexing Actions

![Much of the administrative configuration is done via System Settings.](./search-overview/images/06.png)

The behavior and configuration of these features is described in the User Guide section.

## Elasticsearch

The default search engine is Elasticsearch, which is backed by the Lucene search library. There's an Elasticsearch server embedded in all bundles, which is handy for testing and development purposes. Production environments must install a separate, remote Elasticsearch server (or even better, cluster of servers).  For information on how to install Elasticsearch, read the [deployment guide](https://help.liferay.com/hc/en-us/sections/360004655831-Installing-a-Search-Engine).

Actually the rest of this stuff is probably unnecessary here. Preserving for now in a commented out section -->


<!--
## Searching

Find a search bar (there's one embedded in every page by default), enter a term, and click *Enter*.

![There's a search bar embedded on all pages by default.](./search-overview/images/02.png)

After search is triggered, a results page appears. If there are hits to search engine documents, they appear as search results in the right hand column. In the left hand column are search facets.

![Results are displayed in the Search Results portlet.](./search-overview/images/03.png)

The search bar, search results, and search facets make up three powerful features in the search UI.

### Search Bar

The search bar is where you enter *search terms*. Search terms are the text you send to the search engine to match against the documents in the index.

### Search Results and Relevance

The search term is processed by an algorithm in the search engine, and search results are returned to users in order of relevance. Relevance is determined by a document's *score*, generated against the search query. The higher the score, the more relevant a document is considered. The particular relevance algorithm used is dependent on [algorithms provided by the search engine (Elasticsearch by default)](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html#relevance-intro).

### Search Facets

Facets allow users of the Search application to filter search results. Think of facets as buckets that hold similar search results. You might want to see the results in all the buckets, but after scanning the results, you might decide that the results of just one bucket better represent what you want. So what facets are included out of the box?

- Category
- Folder
- Site
- Tag
- Type
- User
- Modified
- Custom

![Site and Type are two of the facet sets you'll encounter. They let you drill down to results that contain the search terms you entered.](./search-overview/images/04.png)

You've probably used something similar on any number of sites. You search for an item, are presented with a list of results alongside a list of buckets you can click to further drill down into the search results, without entering additional search terms. Search facets work the same way.
-->
