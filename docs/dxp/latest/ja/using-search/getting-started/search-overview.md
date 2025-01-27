# 検索の概要

検索は、Liferayの基本的なコンポーネントです。 Elasticsearchは、テストや開発の目的でLiferayにバンドルされています。 本番環境では、リモートサーバ上でElasticsearchを動作させる必要があります。 [Elasticsearchを開始する](../installing-and-upgrading-a-search-engine/elasticsearch/getting-started-with-elasticsearch.md) では、リモートサーバーの設定について説明しています。 しかし、バンドルされているElasticsearchエンジンを使えば、すぐに検索機能を利用することができます。

ここでは、検索機能を紹介します。

* フルテキスト検索
* すべてのコンテンツタイプ（ブログ、ドキュメント、Webコンテンツなど）のインデックス作成
* 高度に設定可能な検索ページ
* 各ページのヘッダーに埋め込まれた検索バー
* 追加、更新、削除されたコンテンツの自動インデックス同期
* ロールやパーミッションによる検索結果のフィルタリング
* 検索候補
* 検索結果の設定（フィルターやソートなど）
* ファセット検索
* 高度な検索構文（AND/OR/NOT、ワイルドカードなど）の有効化・無効化

<a name="search-pages-and-widgets" />

## 検索ページとウィジェット

デフォルトの検索ページ(`localhost:8080/search`)には、実用的な検索ウィジェットのセットがあります。 このページはカスタマイズ可能で、グローバル検索ページのテンプレート（こちらもカスタマイズ可能）をベースにしています。

![検索ページテンプレートは便利です。](./search-overview/images/05.png)

検索ウィジェットをページに追加するには、ページの［追加］ボタンをクリックし、［**ウィジェット**］を選択して、［検索］カテゴリを開きます。

![検索ウィジェットはたくさんあります。](./search-overview/images/07.png)

詳細は、 [検索ページの構成](../search-pages-and-widgets/working-with-search-pages/search-pages.md) または [検索ページとウィジェット](../search-pages-and-widgets/working_with_search_pages.md)を参照してください。

<a name="search-configuration-and-administration" />

## 検索の構成と管理

コントロールパネルでは、検索機能の設定、接続とフィールドのマッピングの表示、検索インデックスアクションの実行が可能です。

検索の設定は、コントロールパネルのシステムスコープで行うことができます。 設定のカテゴリでは、 **システム設定** &rarr; **検索** を選択してください。 検索設定画面が表示されます。

![検索は高度な設定が可能です。](./search-overview/images/06.png)

検索の管理画面では、検索の接続やフィールドのマッピングを調べたり、インデックスを実行したりすることができます。 コントロールパネルの「設定」カテゴリにある 「**検索**」 を選択して、これらを表示します。

![検索管理画面は有益で便利です。](./search-overview/images/08.png)

詳細は、 [検索の管理とチューニング](../search_administration_and_tuning.md)をご覧ください。

<a name="custom-development-in-search" />

## 検索におけるカスタム開発

検索のカスタマイズには、通常、これらの検索フェーズのうち少なくとも1つが含まれます。

**インデックス** は、1つ以上のドキュメントを検索エンジンに送信することです。 ドキュメントには、さまざまなタイプのフィールド（テキスト、キーワードなど）が含まれています。 検索エンジンは各フィールドを処理し、フィールドを保存するか分析するかを決定します。

**検索** は、検索クエリを送信して結果を取得します（別名 ヒット）検索エンジンから。 クエリとフィルタは検索リクエストの一部にすることができ、どちらも検索対象のフィールドと照合する値を指定します。 サーチエンジンは、ネストされたクエリとフィルタ内の各フィールドを繰り返し実行し、オプションとして、クエリを実行する前に特別な分析を行います（検索時間分析）。 検索時間分析は、マッピング定義を介してフィールドごとに構成できます。

検索機能は、サービス・プロバイダー・インターフェースとAPIを使って、それぞれ拡張したり呼び出したりすることができます。

* サービスプロバイダーインターフェイス（SPI）は実装することを目的としています。 ソースコードでは、 `-spi` で終わるモジュールに含まれています（例えば、 [`portal-search-spi` module](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-search/portal-search-spi) など）。

* APIには、独自のコードで呼び出すことができるメソッドが含まれています。 ソースコードでは、 `-api` で終わるモジュールの中にあります（例えば、 [`portal-search-api` モジュール](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-search/portal-search-api) など）。

詳しくは、 [開発者ガイド](../developer_guide.md) をご覧ください。

<a name="whats-next" />

## 次のステップ

[コンテンツの検索](./searching-for-content.md) による検索機能をご紹介します。 本番環境用に検索を設定する準備ができたら、 [Elasticsearchを開始する](../installing-and-upgrading-a-search-engine/elasticsearch/getting-started-with-elasticsearch.md) を参照してください。
