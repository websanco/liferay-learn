# 検索設定のリファレンス

Liferay DXPの検索は様々な方法で設定できます。 [設定範囲](../system-administration/configuring-liferay/understanding-configuration-scope.md)の概念を理解すれば、ある設定画面がどこにあるのかがわかるようになります。

* 検索設定の多くはシステムスコープに影響を与えます（アクションの再インデックス化や検索エンジンコネクタの設定など）。
* 検索チューニングの設定は、仮想インスタンスに影響を与えます。
* 新しい検索ページの作成は、サイトスコープでの検索に影響します。
* 検索ウィジェットの設定は、ウィジェットスコープの設定ですが、一部はページスコープの設定と考えることもできます。

そのため、 `Configuring Search` という用語は、利用可能なすべてのスコープに対応しているため、非常に幅広いものとなっています。 ここでは、どのような検索動作が設定可能か、そして重要なこととして、どこに検索設定オプションがあるのかについて、高いレベルで説明します。

<a name="widget-scoped-searchの設定" />

## Widget Scoped Searchの設定

ページ上に設置できる検索ウィジェットをご用意しました。 それぞれに設定オプションがあります。

![各ウィジェットの設定はそれぞれ異なります。](./search-configuration-reference/images/03.png)

### 検索ウィジェット

検索ウィジェットの詳細については、 [ページとウィジェットの検索](./getting-started/search-overview.md#search-pages-and-widgets) をご覧ください。

**検索結果** : 検索結果の表示方法を設定します。 詳しくは、 [検索結果の表示](./search-pages-and-widgets/search-results/configuring-the-search-results-widget.md#displaying-search-results) をご覧ください。

**検索バー** : 検索キーワードをどのように処理するかの動作を設定します。 詳しくは、 [検索バーを設定する](./getting-started/searching-for-content.md#configuring-the-search-bar) をご覧ください。

**検索ファセットFacets** : 各ファセットの動作とURLパラメータを設定します。 詳しくは、 [ファセット](./search-pages-and-widgets/search-facets/facets.md) をご覧ください。

**検索オプション** : これは特殊なケースで、このウィジェットを構成することで、ページスコープでの動作を定義します。 ［検索オプション］ウィジェットをページに追加し、「検索ページ」に2つのブーリアンを定義します。

* 空の検索を許可する。デフォルトでは、キーワードを入力しなかった場合、検索結果は表示されません。 これを有効にすると、検索バーにキーワードが入力されていない場合、 **すべての** の結果が返されるようになります。
* 基本的なファセット選択。デフォルトでは、ファセット数はファセットを選択するたびに再計算されます。 これを有効にすると、ファセットリカウンティングがオフになります。

**検索候補** ：より良いクエリやスペルチェックのクエリを提案します。 詳しくは、 [サジェスチョン・コンフィギュレーション・リファレンス](./search-pages-and-widgets/search-results/enabling-search-suggestions.md#suggestions-configuration-reference) を参照してください。

**検索インサイト** ：これを検索ページに追加すると、ユーザーがキーワードを入力したときに、バックエンドの検索コードによって構築される完全なクエリ文字列を検査することができます。 テストや開発にしか使えません。 詳しくは、 [検索インサイト](./search-pages-and-widgets/search-insights.md) をご覧ください。

**カスタムフィルター** ：検索結果に適用させたいフィルターごとに、ページにウィジェットを追加します。 検索ページのユーザがフィルタを見たり操作したりできるようにするか、フィルタを不可視にしたり、不変にしたりする。 詳細については、 [検索結果のフィルタリング](./search-pages-and-widgets/search-results/filtering-search-results.md) を参照してください。

**ソート** : インデックス内の特定の `キーワード` フィールドの値に基づいて、ユーザーが検索結果を並べ替えることができます。 例えば、「タイトル」フィールドのアルファベット順に結果を表示します。 デフォルトの順序は、検索エンジンの 「**関連性**」 の計算によって決定されます。 詳しくは、 [検索結果の並べ替え](./search-pages-and-widgets/search-results/sorting-search-results.md) をご覧ください。

**低レベルの検索オプション：****会社インデックス** 以外のインデックスを対象とした検索に参加するように検索ウィジェットを設定します。 会社インデックスはLiferay DXPアセットがデータのインデックスを作成する場所なので、多くのインストールではこのウィジェットは必要ありません。 詳しくは、 [低レベル検索オプションを理解する](./search-pages-and-widgets/search-results/understanding-low-level-search-options.md) をご覧ください。

**類似した検索結果：** ページに表示されているアセットに類似した検索結果を表示します。 詳細は、 [類似結果](./search-pages-and-widgets/similar-results.md) を参照してください。

[Liferay Enterprise Search]**Elasticsearchのモニタリング：** Liferay Enterprise Searchの契約者は、Liferay DXPページに配置されたウィジェット内で、 [Elastic社のKibana](https://www.elastic.co/kibana) モニタリングツールにアクセスすることができます。 詳しくは、 [Elasticsearchをモニタリング](./liferay-enterprise-search/monitoring-elasticsearch.md) をご覧ください。

<a name="サイトスコープ検索の設定" />

## サイトスコープ検索の設定

[サイトスコープの構成](../../system-administration/configuring-liferay/understanding-configuration-scope.md)の厳密な定義では、検索には何もありません。 しかし、 [検索ページ](./search-pages-and-widgets/working-with-search-pages/search-pages.md) は、サイト固有の検索動作に影響を与えます。 通常、検索ページには、特定のサイト内のすべてのコンテンツを検索するように設定された検索ウィジェットが含まれています。

![検索の範囲を設定します。](./search-configuration-reference/images/04.png)

検索ウィジェットを使用する際には、いくつかの重要な設定上の注意点があります。

ヘッダー検索バー（デフォルトテーマに埋め込まれた検索バー）が検索バーウィジェットを使用する場合、その構成では常に **宛先ページ** を設定する必要があります。 [検索先ページ](./search-pages-and-widgets/working-with-search-pages/creating-a-search-page.md) は、検索ウィジェットを搭載した通常のページです。 検索ウィジェットを持つページは、サイト全体でいくつでも持つことができます。

検索バーウィジェットはインスタンス化が可能なので、1つのページに複数の検索バーウィジェットを設定することができます。 すべての検索バーのインスタンスは、サイト内の検索ページを指していないと効果がありません。

```{important}
    Liferayのデフォルトテーマのように、検索先のページがテーマ埋め込み型のヘッダー検索バーに加えて、検索バーのウィジェットインスタンスを持っている場合、ヘッダー検索バーの設定がページのウィジェットインスタンスよりも優先されます。

    逆に、他のページのSearch Barウィジェットインスタンスからの検索では、ヘッダのSearch Barの設定と異なっていても、その設定が尊重されます。
```

詳しくは、 [検索バーを設定する](../getting-started/searching-for-content.md#configuring-the-search-bar) をご覧ください。

<a name="インスタンススコープの検索構成" />

## インスタンススコープの検索構成

Liferay 7.3以降では、検索は [院タンス設定](../../system-administration/configuring-liferay/understanding-configuration-scope.md#system-settings-and-instance-settings) パネルにインスタンススコープエントリを持ちません。 しかし、多くの検索ウィジェットのインスタンスワイドなデフォルトの [ウィジェットテンプレート](../../site-building/displaying-content/additional-content-display-options/styling-widgets-with-widget-templates.md) を構成するためのエントリがあります。 テンプレートが定義されているサイトID（多くの場合、サイト設定パネルにあるグローバルサイトのID）と、サイトメニューの &rarr; デザイン &rarr; ウィジェットテンプレートにあるウィジェットテンプレートのIDを入力します。

検索に関するインスタンス・スコープの設定は、Global Menuの &rarr; Applications &rarr; Search Tuningのエントリーのみです。

**検索結果のランキング。** 特定のエイリアスの検索結果を隠したり、固定したり、追加したりして、検索結果を手動でカスタマイズできます。 詳しくは、 [結果ランキング](./search-administration-and-tuning/result-rankings.md) をご覧ください。

**同義語。** 同義語の検索語がマッチし、検索語との完全一致のようにスコアリングされるように、同義語セットを作成します。 詳細は、 [シノニムセット](./search-administration-and-tuning/synonym-sets.md) を参照してください。

<a name="システムスコープ検索の設定" />

## システムスコープ検索の設定

システムスコープの検索設定は、主に [システム設定](../../system-administration/configuring-liferay/system-settings.md) にあります。

1. グローバルメニューでは、 **コントロールパネル** &rarr; **設定** &rarr; **システム設定** へ行きます。

1. ［プラットフォーム］セクションの下にある ［**検索**］ カテゴリーをクリックします。

    または、 **検索** を検索します。

![システム設定には、検索のための多数のシステム・スコープ・エントリがあります。](./search-configuration-reference/images/01.png)

[OSGi設定ファイル](../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)を経由してシステム設定を設定できます。 ファイル名はここに記載されています。

### デフォルト・キーワードクエリ

**設定ファイル：** `com.liferay.portal.search.configuration.DeltKeywordQueryConfiguration.config`・・・。

デフォルト・キーワードクエリエントリには1つの設定があります。

**`disabledEntryClassNames`** となります。 `DefaultKeywordQueryContributor` のコードは、キーワード検索クエリに `description`、 `userName`、 `title` フィールドを自動的に追加します。 エントリークラス名 `DefaultKeywordQueryContributor` を無視するように指定します。

### デフォルト検索結果パーミッションフィルター

**設定ファイル：** `com.liferay.portal.search.configuration.DeltsercheantReversPermissionFilterConfiguration.config`。

デフォルト検索結果パーミッションフィルターエントリでは、 **ポストフィルタリングパーミッションチェック**（検索インデックスから結果が返された後に行われるデータベースパーミッションチェック）の設定が可能です。 これらの設定の詳細については、 [最終権限設定](../search-pages-and-widgets/search-results/search-results-behavior.md#final-permissions-checking) を参照してください。

***`permissionFilteredSearchResultAccurateCountThreshold`**

***`searchQueryResultWindowLimit`**

### インデックス・ステータス・マネージャー

**設定ファイル：** `com.liferay.portal.search.configuration.config`です。

ステータス マネージャーをインデックスするエントリーの設定は1つです。

**`indexReadOnly`** ：これを有効にすると、すべてのインデックス操作と検索エンジンへの書き込みが中断されます。 検索では、すでにインデックスされている文書のみが返されます。 この機能は、大規模なデータのインポートを高速化するのに便利ですが、インポートが終了したら、無効にして完全な再インデックスを実行する必要があります。

### インデックス書き込みヘルパー

**設定ファイル：** `com.liferay.portal.search.configuration.config`IndexWriterHelperConfiguration.config

インデックスライターヘルパーエントリには、1つのエントリが含まれています。

**`indexCommitImmediately`** ： **true**(デフォルト)の場合、書き込みリクエストのたびにサーチエンジンがインデックスリーダーを更新し、トランザクションをディスクに流す可能性があります。 これは、検索エンジンのパフォーマンスに悪影響を与える可能性があります。 デフォルトの動作は、個々のアセットへのインデックス書き込み（例：blog の追加、blog の更新）については直ちにコミットしますが、一括したインデックス書き込み操作（例：すべてのユーザーのインデックス化、すべてのフォームエントリのインデックス化）については、すべてのエントリが検索エンジンに送信されるまでコミットを遅らせます。 この設定をfalseにすると、個々のインデックス操作の動作が変化し、Asset Publisherなどのアプリケーションで、新しく追加されたコンテンツを表示する際にレスポンスが遅れることがあります。 詳しくは、 [Elasticsearchのドキュメント](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/near-real-time.html) をご覧ください。

### インデクサー登録

**設定ファイル：** `com.liferay.portal.search.configuration.indexerRegistryConfiguration.config`です。

インデックス要求のバッファリングを設定する。

**`buffered`** : インデックス要求のバッファリングを有効（デフォルト）にするか、無効にするかを設定します。

**`bufferedExecutionMode`** : 管理者が `IndexerRequestBufferExecutor`を選択し、 `IndexerRequest`の実行に使用できるようにします。 エクゼキュータの実装は1つだけです（**DEFAULT**）。 新しい `IndexerRequestBufferExecutor` の実装がデプロイされると、必要なプロパティの1つとして、 `buffered.execution.mode`が指定されます。 この値は、 **DEFAULT** の代わりに使用することができます。

**`maximumBufferSize`** ：バッファリングが有効になっている場合は、追加のインデックス要求がすぐに実行されるように最大バッファサイズを設定します。

**`minimumBufferAvailabilityPercentage`** : バッファの容量が指定されたパーセント分しか残っていない場合、バッファ内の既存のリクエストが一括して実行され、バッファから削除されます。

### インデックス・クエリ・プロセッサー

**設定ファイル：** `com.liferay.portal.search.configuration.config`

このエントリは、1つの繰り返し可能なプロパティを持っています（ [OSGi構成ファイル](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files) で定義している場合は、配列構文を使用します）。

**`fieldNamePatterns`** ：ここで設定したパターンに一致する名前のフィールドは、分析されていないキーワードフィールドとして扱われます。 スコアリングされたフルテキストクエリの代わりに、スコアリングされていないワイルドカードクエリによってマッチングが行われます。 これはリソースを消費する操作で、インデックスが大きくなると検索エンジンのパフォーマンスが低下します。 部分文字列の照合では、 [ngram tokenizer](https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-ngram-tokenizer.html) に頼る方が、通常は良い結果が得られます。

### インデックスの再構築

**設定ファイル：** `com.liferay.portal.search.configuration.config`です。

このエントリーには、1つのプロパティしか含まれていません。

**`indexingBatchSizes`** ：バッチインデックスをサポートするモデルタイプのバッチごとにインデックスを作成するドキュメントの数（デフォルト値は1000）を設定します。 大規模なドキュメントを持つモデルでは、この値を小さくすることで、完全な再インデックスを実行する際の安定性が向上する場合があります。

### リインデックサー

**設定ファイル：** `com.liferay.portal.search.configuration.config`です。

これらのプロパティは、Search フレームワークの Reindexer サービスを構成します。 これらは実験的なものであり、Liferayのサポート担当者の指示のもと、特定の特定のシナリオをトラブルシューティングする場合にのみ有効です。 これらの設定は本番環境では使用しないでください。

**`nonbulkIndexingOverride`** ：これをtrueに設定すると、変更されたエンティティがバッチではなく1つずつ再インデックス付けされます。 パフォーマンス上の理由から、本番環境ではこの設定を有効にしないでください。

**`SynchronousExecutionOverride`** ：これをtrueに設定すると、変更されたエンティティのインデックスの再作成が完了するまで他のポータルプロセスをブロックします。 パフォーマンス上の理由から、本番環境ではこの設定を有効にしないでください。

### エンジンヘルパー

**設定ファイル：** `com.liferay.portal.search.configuration.config`。

このエントリは、1つの反復可能なプロパティを持っています（ [OSGi構成ファイル](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)で定義する場合は、配列構文を使用します）。

**`excludedEntryClassNames`** ：検索アプリケーション用に構築されたキャッチオールクエリで、アセットタイプを検索対象から除外します。 例えば、ユーザーと組織 アプリケーションから検索できるように、組織アセットのフィールドにインデックスを付ける必要がありますが、検索アプリケーションでは検索できません。 したがって、組織は、 `excludedEntryClassNames`に追加されます。

### 権限チェッカー

**設定ファイル：** `com.liferay.portal.search.configuration.config`。

このエントリーは、Liferay DXP 7.3では1つの設定オプションがあります。

**`permissionTermsLimit`** : このレベルの許可チェックが中止されるまでに、検索クエリに追加される許可検索句の数を制限します。 パーミッションチェックは、 [デフォルト検索結果パーミッションフィルター](#default-search-result-permission-filter) セクションで説明されている最終的なパーミッションフィルタリングにのみ依存します。

### タイトルフィールドクエリビルダー

**設定ファイル：** `com.liferay.portal.search.configuration.config`TitleFieldQueryBuilderConfiguration.config

ドキュメントの「タイトル」フィールドにマッチした場合の検索の応答方法を設定します。

**`exactMatchBoost`** : 検索されたキーワードが、文書の `タイトル` フィールドと完全に一致した場合に、さらにブーストをかけます。

**`maxExpansions`** : 検索されたキーワードに、 `title` フィールドをフレーズのプレフィックスとしてマッチさせたときに、返すドキュメントの数を制限します。 詳しくはElasticsearchの [Match Phrase Queryのドキュメント](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query-phrase.html) を参照してください。

### クラスター横断レプリケーション

このエントリは、Liferay Enterprise Searchのサブスクリプションで利用可能なクラスター横断レプリケーションモジュールを導入した場合にのみ表示されます。 これらの設定プロパティについては、専用の [クラスター横断レプリケーション](./liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication.md) のドキュメントで説明されています。

### Elasticsearchのモニタリング

**設定ファイル：** `com.liferay.portal.search.elasticsearch.monitoring.web.internal.configuration.config`。

Liferay Enterprise Searchのサブスクリプションで利用可能なLiferay Enterprise Search Monitoringモジュールを導入している場合、Elasticsearch Monitoringの構成を使用できます。 これらの設定プロパティについては、専用の [Elasticsearchをモニタリング](./liferay-enterprise-search/monitoring-elasticsearch.md) のドキュメントで説明しています。

### Elasticsearch 7

**設定ファイル：** `com.liferay.portal.search.elasticsearch7.config.ElasticsearchConfiguration.config`

LiferayとElasticsearchの接続を設定する 7. これらのプロパティの詳細については、 [コネクターを構成する](../installing-and-upgrading-a-search-engine/elasticsearch/connecting-to-elasticsearch.md#configuring-the-connector) を参照してください。

### Elasticsearch接続

**設定ファイル：** `com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-[connectionId].config`。

Liferay 7.3は、複数のElasticsearchクラスタに接続することができます。 接続はElasticsearch接続エントリで定義されます。 これにより、低レベル検索オプションおよび検索結果ウィジェットと組み合わせて使用することで、Elasticsearchクラスターを使用しているサードパーティシステムからの結果を表示することができます。 また、 [クラスター横断レプリケーション](./liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication.md) のドキュメントでも紹介されています。

### Learning to Rank

**設定ファイル：** `com.liferay.portal.search.learning.to.rank.configuration.LearningToRankConfiguration.config`となっています。

Liferay Enterprise Searchのサブスクリプションで利用可能なLiferay Enterprise Search Learning to Rankモジュールを導入している場合、Learning to Rank構成を使用できます。 これらの設定プロパティについては、専用の [Learning to Rank](./liferay-enterprise-search/learning-to-rank.md) ドキュメントで説明しています。

### ウェブ検索

**設定ファイル：** `com.liferay.portal.search.web.internal.configuration.searchWebConfiguration.config`です。

このエントリーには1つのプロパティが含まれています。

**`classicSearchPortletInFrontPage`** : 新しい検索ウィジェットを使用していたデフォルトの検索エクスペリエンスを、過去のリリースで標準装備されていた非推奨のクラシック検索ポートレットに戻します。

### 同義語

**設定ファイル：** `com.liferay.portal.search.tuning.synonyms.web.internal.configuration.config`です。

このエントリは、1つの繰り返し可能なプロパティを持っています（ [OSGi構成ファイル](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files) で定義している場合は、配列構文を使用します）。

**`synyonymFilterNames`** : 適用するシノニムフィルター名を設定します。 これらのフィルターはElasticsearch Index Settingsで定義する必要があります。 [Creating New Synonym Language Filters](./search-administration-and-tuning/synonym-sets.md#creating-new-synonym-language-filters) を参照してください。

### 検索機能の管理

**コントロールパネル** &rarr; **設定** &rarr; **検索** には、つながり、アクションをインデックスする、フィールドマッピングの3つの管理用UIがあります。

![検索管理パネルには、3つの管理画面があります。](./search-configuration-reference/images/02.png)

#### つながり

検索エンジンへの接続情報が表示されます。 例：

```bash
Search Engine Vendor: Elasticsearch (Embedded), Client Version: 6.5.0, Nodes: A0D6GlN (6.5.0)
```

#### アクションをインデックスする

アクションをインデックスするでは、これらのレベルのいずれかでインデックスを再作成します。

   * すべてのインデックス可能アセット
   * 個別の指標となる資産
   * すべてのスペルチェック用インデックス

#### フィールドマッピング

フィールドマッピングタブには、システムで有効なすべてのフィールドマッピングがインデックスごとに表示されます。 現在、マッピングの表示、コピー、拡大・縮小、ダークテーマでの表示が可能です。

### ポータルプロパティ

ポータルプロパティは、システムにも適用されます。 [Lucene Search](https://learn.liferay.com/reference/latest/en/dxp/propertiesdoc/portal.properties.html#Lucene%20Search) ポータル・プロパティは、低レベルの検索動作を構成します。 物件とその説明を見て、自分の検索条件に当てはまるかどうかを判断します。 -->
