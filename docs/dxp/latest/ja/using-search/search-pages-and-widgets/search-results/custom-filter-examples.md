# カスタムフィルターの例


カスタムフィルターウィジェットは、検索チューニングを強力にサポートします。 カスタムコードを導入することなく、検索エンジンに送信されるクエリをコントロールすることができます。 ここでは、カスタムフィルターウィジェットへのアプローチ方法を理解するために、いくつかの一般的な使用例を紹介します。

- [検索結果からコンテンツを除外する](#excluding-certain-content)
- [検索結果のコンテンツを増やす](#boosting-fields)
- [複数のサイトIDによる絞り込み](#filtering-by-site-id)
- [Elasticsearchのクエリストリングクエリを使う](#complex-filter-with-query-string)
- [ネストしたフィールドへのブーストマッチ](#boosting-matches-to-nested-fields)

カスタムフィルターウィジェットの詳細な説明については、 [フィルター検索結果](./filtering-search-results.md) を参照してください。

<a name="一部のコンテンツを除く" />

## 一部のコンテンツを除く

カスタムフィルタは、 `must_not` Occur の設定と一緒に使用すると、検索結果からドキュメントをキャッチして除外することができます。

### 一部のドキュメントおよびメディアコンテンツを除く

場合によっては、特定のタイプのコンテンツを検索結果に表示させないようにしたいことがあります。 Webコンテンツに追加するシステムにしか存在しないドキュメントやメディアファイルのエントリを除外するには、まず、除外する特定のファイルを、検索インデックスで識別できるように区別する必要があります。 目的を明示したタグ（`wconly` など）を付けたり、専用の [ドキュメントとメディアフォルダ](./../../../content-authoring-and-management/documents-and-media/uploading-and-managing/creating-folders.md)に入れたりすることができます。 カスタムフィルタでドキュメントとメディアフォルダを除外するには、以下の設定を行います。

**フィルタクエリタイプ:** `Match`

**フィルタフィールド:** `folderId`

**フィルタ値:** `41103`

**発生:** `must_not`

この構成により、値`41103`の`folderId`フィールドを含む検索ドキュメントが検索結果に返されないようになります。

### 特定の拡張子を持つコンテンツの除外

おそらく、GIFファイルを検索結果から除外しなければなりません。 カスタムフィルターを以下のように設定します。

**フィルタクエリタイプ:** `Match`

**フィルタフィールド:** `extension`

**フィルタ値:** `gif`

**発生:** `gif`

この構成は、ドキュメントとメディア [`DLFileEntry`モデル](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/spi/model/index/contributor/DLFileEntryModelDocumentContributor.java) からインデックス付けされた`extension`フィールドの存在を利用します。 これにより、値`gif`の`extention`フィールドを含む検索文書が検索結果に返されないようになります。

<a name="ブーストフィールド" />

## ブーストフィールド

特定のフィールドに基づいて特定のドキュメントを強化したいというニーズはよくありますが、「カスタムフィルタ」ウィジェットを使えば、簡単に実現できます。 ブースト値は、お客様のニーズに合わせてチューニングが必要な場合があります。 **スコアの説明を有効にする** を有効にして 検索インサイト ウィジェットを使用し、ドキュメントがどのようにスコアリングされているかを調べ、ブースト値を微調整します。

### 指定フィールドへのブーストマッチ

検索したキーワードに一致する特定のフィールドを持つドキュメントをブーストするには、次のようにカスタムフィルターを設定します。

**フィルター・クエリ・タイプ:** `マルチマッチ`

**フィルターフィールド:** `title_en_US, content_en_US`

**発生:** `Should`

**ブースト:** `100`

**カスタムパラメーター名:** `q`

この設定では、英語（米国）のタイトルとコンテンツに、ユーザーが「検索バー」ウィジェットに入力したキーワードが含まれている場合、ドキュメントのマッチングが強化されます。 カスタムパラメーター名を検索バーのキーワードパラメーター名の設定と同じ値で入力すると、検索バーに渡された値がカスタムフィルターでブーストされた値になります（検索インデックスのドキュメントにマッチした場合）。

マルチマッチクエリは、複数のフィールドを一度にマッチさせることができます。 そうしないと、他の設定値が同じであっても、個別のフィールドごとにカスタムフィルタを設定する必要があります。

### フィールドの存在による後押し

タグの値に関わらず、タグ付けされたコンテンツをブーストするには、次のようにカスタムフィルターを設定します。

**フィルタクエリタイプ** `Exists`

**フィルタフィールド** `assetTagNames`となります。

**発生:** `should`

**ブースト:** `100`

クエリにマッチするドキュメントがタグ付けされている場合、 `assetTagNames` フィールドが含まれます。 Existsクエリは、フィールドのあらゆる値にマッチします。

<a name="サイトidによるフィルタリング" />

## サイトIDによるフィルタリング

1つの検索ページで複数のサイトを検索する際に、すべてのサイトを検索しない設定はありません。 現在のサイトとすべての [子サイト](../../../site-building/building-sites/site-hierarchies.md) からの結果を含めるには、検索バーのスコープを設定し、 **すべて** に設定する必要があります。 その後、検索結果に含める Site の `groupId` をマッチさせるための term クエリを持つ カスタムフィルター ウィジェットがそれぞれの子クエリ節を集めることができる Bool クエリを持つ 1 つの親 カスタムフィルター を使用します。 サイトのIDは、検索ドキュメントの `groupId` フィールドです。

1. 3つのサイトを作る：
   - 子サイトを持つ親サイトを1つ以上作成してください。
   - 検索にかからない追加のSiteを1つ以上作成する。

   ```{tip}
      サイトのグループIDを確認するには、サイトメニューのConfiguration > Settingsに移動します。 表示されている`Site ID`は、サイトのフィルタリングに使用できる`groupId``です。
   ```

1. 各Siteに少なくとも1つのコンテンツ（Blog Entry）を作成し、それぞれに **Liferay** という単語を入れてください。

1. 検索バーのスコープを **Everything** に設定します。

   > **チェックポイント：** 検索して、すべてのサイトのコンテンツが返されることを確認する

   ![この3つのBlogエントリーは、それぞれ別のサイトからのものです。](./custom-filter-examples/images/01.png)

1. 親のカスタムフィルターを設定します。

   **フィルタクエリタイプ：** `Bool`

   **発生：** `Filter`

   **クエリ名：** `SiteBoolQuery`です。

1. 検索対象とするサイトごとにカスタムフィルターを設定します。

   **フィルタクエリタイプ：** `Term`

   **フィルターフィールド：** `groupId`です。

   **発生：** `should`です。

   **親クエリ：** `SiteBoolQuery`です。

   **フィルターの値：** `38109`となります。

   上のスクリーンショットのコンテンツの例では、もうひとつカスタムフィルタが必要です。 フィルターの値を `38105`とすること以外は、上記と同じように設定します。

   > **チェックポイント：** 再度検索を行い、指定したサイトのみのコンテンツが表示されることを確認します。 これは、検索結果が **Display Results in Document Form** に設定されている場合、詳細ビューを見てさらに確認することができます。

   ![含まれるサイトのコンテンツのみが表示されます。](./custom-filter-examples/images/02.png)

重要なのは、 `groupId` によるフィルタは、 `SiteBoolQuery` を親クエリとして宣言していることです。 各サイトの子Termクエリの **should** Occur句は、OR演算子として機能し、 `groupId`のいずれかがマッチした場合、そのコンテンツが［検索結果］ウィジェットに表示されるようになっています。

<a name="クエリ文字列による複合フィルタ" />

## クエリ文字列による複合フィルタ

[サイトIDによるフィルタリング](#filtering-by-site-id) の例のように、 [クエリ文字列クエリ](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-query-string-query.html) を使用することで、複数のクエリを必要としない場合があります。 以下の設定では、1つのカスタム・フィルター・ウィジェットのみを使用して、検索対象を制限する方法を示しています。

* ドキュメントとメディアファイルを` pdf`または、`jpg`拡張子に一致させる。
* ウェブコンテンツの記事に合わせる

カスタムフィルターウィジェットを以下のように設定します。

**フィルタクエリタイプ：** `クエリ文字列`

**発生：** `Filter`

**フィルターの値：** `((extension:pdf OR extension:jpg) AND entryClassName:com.liferay.document.library.kernel.model.DLFileEntry) OR entryClassName:com.liferay.journal.model.JournalArticle`

意図した優先順位を確実に実行するためには、括弧の使用を推奨します。

検索ページの構成を簡素化すること（複雑なケースを1つのカスタムフィルタウィジェットだけで処理することが多い）は良いことですが、Query Stringクエリはカスタムフィルタウィジェットの複雑さをすべて解決するものではありません。 クエリ文字列型では真似できないクエリもあります。 たとえば、ネストされたドキュメントを検索するためのネストされたクエリ、分析を回避するための用語クエリ、またはプレフィックスに基づいて検索するためのプレフィックスクエリを処理することはできません。

```{warning}
   クエリ文字列クエリは、渡される値が検索バーからのものである場合には使用しないでください（`Boosting Matches to Designated Fields`_で説明しています）。 検索バーのユーザーが無効な構文を含むキーワードを入力した場合、エラーが返されます。
```

<a name="ネストしたフィールドへのブーストマッチ" />

## ネストしたフィールドへのブーストマッチ

> 提供時期：7.2 FP10 , 7.3 FP1/SP1

[Accessing Nested DDM Fields](../search-facets/custom-facet.md#accessing-nested-ddm-fields) で説明されているように、Liferay 7.2 SP3 /FP8 (およびLiferay 7.3のすべてのバージョン)では、DDMフィールドは [ネストされたフィールド](../../../liferay-internals/reference/7-3-breaking-changes.md#dynamic-data-mapping-fields-in-elasticsearch-have-changed-to-a-nested-document) になりました。 7.2および7.3の最新のFix PackおよびGAリリースでは、これらのネストされたフィールドを考慮して、 [Elasticsearch Nested query](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-nested-query.html) がサポートされています。

カスタムフィルタの設定で [のネストされたフィールド](../../../liferay-internals/reference/7-3-breaking-changes.md#dynamic-data-mapping-fields-in-elasticsearch-have-changed-to-a-nested-document) を使用すると、検索ページに3つのカスタムフィルタウィジェットが必要になります。 必要な子クエリをラップする [ネストされたクエリ](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-nested-query.html) がウィジェットの1つに追加されます。1つの子クエリはフィールドの名前と一致し、もう1つは値と一致します。

この例では、特定のDDM構造のフィールドにマッチした場合のブーストを追加しています。

1. [ストラクチャー](../../../content-authoring-and-management/web-content/web-content-structures/creating-structures.md)を作成します。
    - サイトメニューの［コンテンツ］ & ［データ］ &rarr; ［ウェブコンテンツ］に移動します。
    - **ストラクチャー** タブをクリックし、追加ボタン ![Add](../../../images/icon-add.png)をクリックします。
    - ストラクチャーにタイトル（例： **Boosted Content**）とこれらのフィールドを与えます。
        - フィールド 1:
            - **タイプ：** `Boolean`
            - **フィールドラベル：** `Boost?`
        - フィールド 2:
            - **タイプ：** `テキストボックス`
            - **フィールドラベル：** `コンテンツ`
    - ストラクチャーを保存する。

    ストラクチャーのフィールドは、デフォルトではインデックス化されています。

1. 新しいストラクチャーを使用する Web コンテンツを 1 つ追加します。

    - **タイトル：** `ブースト`
    - **Boost?** `True`
    - **コンテンツ：** `テスト`

1. 新しいストラクチャーを使用する2つ目のWebコンテンツを追加します。

    - **タイトル：** `Not Boosted`
    - **Boost?** `False`
    - **コンテンツ：** `テストコンテンツ`

1. 検索ページに移動し、 **test content** と検索します。

    **チェックポイント：** コンテンツフィールドが完全に一致しているため、BoostedされていないWebコンテンツがBoostedされたWebコンテンツよりも先に表示されます。

1. Kibana Dev Toolsコンソール、またはcURL経由のCLIから、フィールド名に `Boost` が含まれるDDMフィールドを検索するGETリクエストを実行します。

    ```json
    GET liferay-20097/_search
    {
      "query": {
        "nested": {
          "path": "ddmFieldArray",
          "query": {
            "wildcard":  { "ddmFieldArray.ddmFieldName": "ddm **_***Boost** " }
          }
        }
      }
    }
    ```

    `20097` を、仮想インスタンスの `companyId` [に置き換えます](../../../system-administration/configuring-liferay/virtual-instances/understanding-virtual-instances.md)。

1. Elasticsearchのレスポンスの中で、ネストされたBoostフィールドを持つ `ddmFieldArray` を見つけてコピーします。

    ```json
    "ddmFieldArray" : [
                {
                  "ddmFieldName" : "ddm **_** keyword **_** 39707 **_** Boost **en** US",
                  "ddmValueFieldName" : "ddmFieldValueKeyword **en** US",
                  "ddmFieldValueKeyword **en** US" : "true",
                  "ddmFieldValueKeyword **en** US **String** sortable" : "true"
                }
    ```

1. 検索ページに行き、Elasticsearchのレスポンスデータを使って3つのカスタムフィルタを追加します。

    - フィルタ1、親の入れ子のクエリ：
        - **フィルターフィールド：** `ddmFieldArray`となります。
        - **フィルタクエリタイプ：** `Nested`
        - **発生：** `should`
        - **クエリ名：** `parent_query`
        - **Boost：** `500`
    - フィルター2、フィールド名の子マッチクエリ。
        - **フィルターフィールド：** `ddmFieldArray.ddmFieldName`
        - **フィルタクエリタイプ：** `Match`
        - **発生：** `should`
        - **値：** `ddm__keyword__39707__Boost_ja_US`
        - **親クエリ名：** `parent_query`
    - フィルター3、Boostフィールドの `true` の値に対する子マッチクエリです。
        - **フィルターフィールド：** `ddmFieldArray.ddmFieldValueKeyword_en_US`
        - **フィルターの値：** `true`
        - **フィルタクエリタイプ：** `Match`
        - **発生：** `should`
        - **親クエリ名：** `parent_query`

1. ここで、 **test content** の検索を繰り返し、BoostedされたウェブコンテンツがNot Boostedされていないウェブコンテンツの上に表示されることを確認します。

ブースト値は、お客様のニーズに合わせてチューニングが必要な場合があります。 **Score Explanation** を有効にして Search Insights ウィジェットを使用し、ドキュメントがどのようにスコアリングされているかを調べ、ブースト値を微調整します。

<a name="関連する内容" />

## 関連する内容

- [検索結果のフィルタリング](./filtering-search-results.md)
- [結果ランキング](../../search-administration-and-tuning/result-rankings.md)
- [同義語セット](../../search-administration-and-tuning/synonym-sets.md)

