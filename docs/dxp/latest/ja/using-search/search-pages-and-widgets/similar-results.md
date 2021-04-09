# 同様の結果

> **サブスクライバー**

> **可用性：** [Liferayマーケットプレイス](https://web.liferay.com/marketplace/-/mp/application/172465398)

<!-- please use a more generic example for screenshots (instead of lunar resort) -->

類似結果ウィジェットには、ページで選択された *メインアセット* 類似した検索結果が表示されます。

主な資産の概念は重要です。 DXPの特定のウィジェットには、アセットのリストが表示されます。アセットパブリッシャー、ブログ、Wikiなどです。 ユーザーが表示されたアセットの1つをクリックし、ウィジェットがそのコンテンツをページ上に表示する場合、それはページの *メインアセット*です。 類似結果ウィジェットは、同じページに配置されている場合、 [*More Like This* クエリ](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-mlt-query.html)によって返されるのに十分に類似しているアセットのリストを表示します。 メインアセットの概念は、Elasticsearchの [*入力ドキュメント*](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-mlt-query.html#_how_it_works)と同義です。

類似結果は、入力ドキュメント/メインアセットを使用して、クエリに最も一致するものとして自分自身を返すクエリを作成し、この論理積クエリ（または `OR`）を検索エンジンに送信して、一致する結果ドキュメントを返します。 このプロセスは構成可能です。入力ドキュメントを処理する方法、処理されたコンテンツから用語を選択する方法、およびクエリ自体を形成する方法です。 [詳細については、Elasticsearchのドキュメントを参照してください](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-mlt-query.html#_parameters_4)。

## 類似結果ウィジェットの構成

1.  「追加」メニュー（![Add](../../images/icon-add-widget.png)）→ウィジェット→検索し、類似結果ウィジェットをページにドラッグします。

2.  構成するには、ウィジェットの[オプション]メニュー（![Options](../../images/icon-app-options.png)）を開き、[ *構成*]をクリックします。

![類似結果ウィジェットの表示設定を構成します。](./similar-results/images/01.png)

使用可能なプロパティの完全なリストは、</a>下の

ます。</p> 



## 類似結果ウィジェットの使用

類似結果ウィジェットがページに配置されるとどうなるかは、ページに現在表示されているコンテキストとアセットによって異なります。 ページでメインアセットが選択されていない場合、類似結果には何も表示されません。ページのスペースは空白のままです。 サイト管理者には次の情報メッセージが表示されます。

*利用可能な同様の結果はありません。*

メインアセットのドキュメントが検出されると、ウィジェットは同じアセットタイプの同様の結果を表示し、設定された [ウィジェットテンプレート](#similar-results-widget-templates)指定された形式で表示されます。

2つの使用例を検討して詳細を確認してください。



## ユースケース1：Asset Publisherページでの同様の結果

Asset Publisherでアセットを選択すると、同じアセットタイプの同様の結果（一致する検索ヒットとして返される結果）が表示されます。

この例を構成するには、

<!-- One or two screenshots here would be beneficial. -->

1.  ウィジェットページを作成します。 Asset Publisherウィジェットと同様の結果ウィジェットを追加します。

2.  Asset Publisherの構成の[表示設定]に移動し、Asset Link Behaviorを[すべて表示する] に*設定します*。
   
   これにより、選択したアセットがAsset Publisher内でコンテンツ全体を表示します。 *View in Context*を選択すると、アセットがネイティブに存在するページにリダイレクトされ、類似結果ウィジェットが役に立たなくなります。

3.  以下にリストされているタイプの類似のアセットを複数作成します。 それらが十分に類似していることを確認して、類似結果ウィジェットに結果が表示されるようにします。
   
   「More Like This Query」によって返されるアセットを作成するには、作成するアセットのコンテンツと [類似結果ウィジェットの構成](#similar-results-configurations)注意してください。 テストの場合、 *最小項頻度* と *最小ドキュメント頻度* 値を両方とも `1`に設定することをお勧めします。
   
         - ブログのエントリ
      - ドキュメントとメディアファイル
      - ドキュメントとメディアフォルダー
      - Webコンテンツの記事
      - Webコンテンツフォルダー
      - Wikiページ
      - 掲示板スレッド
      - 掲示板メッセージ
      - 掲示板のカテゴリ

Asset Publisherウィジェットで表示されたアセットをクリックすると、同様の結果が類似結果ウィジェットに表示されます。

<!-- ![Similar Results are displayed for the Asset Publisher's main asset, if the Asset Publisher is configured to display full content.](./similar-results/images/02.png) -->

類似結果の1つをクリックします。 Asset Publisherはメインアセットを更新し、新しいメインアセットについて類似結果が再計算されます。



## ユースケース2：アセット表示ページでの類似結果

これらのウィジェットと付随するアセットは、それらのアセットのリストを表示し、類似結果ウィジェットのメインアセットとして使用するものを選択できます。

| ウィジェット名     | アセットタイプ                |
| ----------- | ---------------------- |
| ブログ         | ブログのエントリ               |
| ドキュメントとメディア | ドキュメントとメディアファイル、フォルダ   |
| Wiki        | Wikiページ                |
| メッセージボード    | 掲示板のスレッド、メッセージ、およびカテゴリ |


ブログで同様の結果を使用する例を構成するには、

1.  ウィジェットページを作成します。 ブログウィジェットと類似結果ウィジェットを追加します。

2.  同様のブログエントリを複数作成します。 それらが類似していることを確認し、類似結果ウィジェットがそれらを結果として返すようにします。
   
   More Like This Queryによって返されるアセットを作成するには、作成するアセットのコンテンツと [類似結果ウィジェットの構成](#similar-results-configurations)注意してください。 テストの場合、 *最小項頻度* と *最小ドキュメント頻度* 値を両方とも `1`に設定することをお勧めします。

3.  ブログウィジェットのブログ投稿をクリックします。 ブログエントリが表示され、類似結果ウィジェットに類似エントリへのリンクが表示されます。

4.  同様の結果の1つをクリックします。 現在、そのブログコンテンツは現在のページのブログウィジェットにレンダリングされています。

<!-- ![The Similar Results widget must accompany widgets that display a main asset on the page.](./similar-results/images/03.png) -->

## 同様の結果ウィジェットテンプレート

デフォルトでは、類似の結果は *コンパクトレイアウト* [ウィジェットテンプレート](https://help.liferay.com/hc/en-us/articles/360029132571-Styling-Widgets-with-Widget-Templates)を使用してレンダリングされ、ハイパーリンクされたタイトルのリストが表示されます。

追加の2つのウィジェットテンプレートは、すぐに使用できます *リストレイアウト* と *カードレイアウト*です。

<!--The Compact Layout looks like this:
![The compact layout is a list of hyperlinked titles.](./similar-results/images/02.png) -->

<!--The List Layout looks like this:
![The list layout shows a summary of the asset, and includes the title, the User, modified date, asset type, and a snippet of content.](./similar-results/images/04.png) -->

<!--The Card Layout looks like this:
![Similar to the list, the card layout puts the asset's summary fields into a card container.](./similar-results/images/05.png) -->

### 同様の結果のカスタムウィジェットテンプレートを追加する

類似結果表示用の独自のウィジェットテンプレートを作成するには、

1.  類似結果ウィジェットの[オプション]メニュー（![Options](../../images/icon-app-options.png)）を開き、[ *構成*]をクリックします。

2.  [ *テンプレートの管理*] をクリックして、[ウィジェットテンプレート]画面を表示します。

3.  [追加]ボタン（![Add](../../images/icon-add.png)）をクリックして、テンプレートクリエーターパレットを開きます。
   
   標準の [ウィジェットテンプレート](https://help.liferay.com/hc/en-us/articles/360029132571-Styling-Widgets-with-Widget-Templates) 変数に加えて、テンプレートエディターで提供される検索固有の変数を活用します。

4.  好みに合わせてテンプレートをデザインしてください。 詳細については、 [ウィジェットテンプレート](https://help.liferay.com/hc/en-us/articles/360029132571-Styling-Widgets-with-Widget-Templates) ドキュメントをご覧ください。

同じテンプレートエディターには、[サイトメニュー]の[ *サイトビルダー* セクションの[ウィジェットテンプレート]エントリからアクセスできます。



## 同様の結果の構成

最初の構成オプションは *Display Settings*というセクションに表示されます。

<!-- Please consider reworking this list of configurations into a table for a better skimming experience along with a screenshot or two for a visual reference. -->

**表示テンプレート：** ウィジェットテンプレートを選択して、同様の結果の表示方法を構成します。

**最大アイテム表示：** ウィジェットに表示する結果の最大数を設定します。

*Advanced Configuration* セクションは、ウィジェットの動作を調整するための設定を収集します。 これらの設定の多くは、Elasticsearch</a>More Like this Queryを構成するために使用されます。</p> 

**フィールド：** カンマ区切りのリストを使用して、別のアセットがメインアセットと一致するかどうかを判別するために使用されるコンテンツのキーワードまたはテキストフィールドを指定します。

**最大クエリ用語：** メインアセットから抽出するクエリ用語の最大数を設定します。 これらは、検索結果をメインアセットに一致させるために使用される用語です。 この値を大きくすると、実行速度を犠牲にして、返される結果の関連性が高まります。 空白のままにすると、デフォルトで `25`ます。

**最小用語頻度：** 類似用語の一致に使用するために用語が索引に現れる必要がある時間の最小しきい値を設定します。 空白のままにすると、デフォルトで `2`ます。

**最小ドキュメント頻度：** More Like Thisクエリの作成に用語を使用するために、用語を含むドキュメント数の最小しきい値を設定します。 空白のままにすると、デフォルトで `5`ます。

**最大ドキュメント頻度：** セット類似の結果を照合するために用語がそれを使用するように見えるインデックス内のドキュメント数の最大しきい値。 これを使用して、ストップワードなどの頻繁に使用される単語を無視します。 空白のままにすると、上限は設定されません。

**最小語長：** 最小語長を設定します。これを下回ると、「類似」クエリから用語が除外されます。 空白のままにすると、デフォルトで `0`ます。

**最大語長：** 最大語長を設定します。これを超えると、More Like Thisクエリから用語が省略されます。 空白のままにすると、上限は設定されません。

**ストップワード：** 同様の結果を見つけるために無視する必要のある、興味のないストップワードの配列（コンマ区切りのリスト）。 設定されたアナライザでストップワードが許可されている場合、これらは完全にMore Like This クエリへの送信を避けることができます。

**アナライザー：** 入力ドキュメントのフィールドで使用するアナライザーを指定します。 空白のままにすると、デフォルトでフィールド構成の最初のエントリに関連付けられたアナライザーになります。

**最小一致数：** 選言クエリが形成された後、このパラメーターは一致する必要のある用語の数を制御します（デフォルトは `30％`）。 使用できる構文については、 [Elasticsearchのドキュメント](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-minimum-should-match.html#query-dsl-minimum-should-match)参照してください。

**Term Boost:**用語をブーストする場合に使用するブースト係数を[tf-idf](https://en.wikipedia.org/wiki/Tf%E2%80%93idf)スコアで指定します。 空白の場合、このデフォルトは無効になります (`0`)。 その他の正の値は、指定されたブースト係数で項ブーストをアクティブにします。

**統合検索キー：** このウィジェットが参加している代替検索のキーを入力します。
