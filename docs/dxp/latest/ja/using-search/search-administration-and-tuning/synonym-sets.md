# 同義語セット

> **サブスクライバー**

同義語セットとは、同じ意味を持つ単語やフレーズを集めたものです。 管理者であるユーザーが同義語セットを作成し、検索ページのエンドユーザーがキーワードやフレーズを検索すると、セット内の同義語も検索されます。

たとえば、ユーザーが「US」という単語を検索するとします。 ほとんどの場合、ユーザーは **America** 、 **U.S.A** 、 **United States** などの同義語も含む検索結果を求めています。 同義語セットを作成することで、ユーザーが検索を最大限に活用できるようになります。

<a name="要求事項と制限事項" />

## 要求事項と制限事項

同義語セットは、Elasticsearchを検索エンジンとして使用している場合にのみサポートされます。 Elasticsearchのインストールについては、 [Elasticsearchを開始する](../installing-and-upgrading-a-search-engine/elasticsearch/getting-started-with-elasticsearch.md) を参照してください。

同義語セットは現在、すぐに使用可能なロケール（英語またはスペイン語）のいずれかでインデックス付けされたフィールドで機能します。 これら2つの言語のいずれかでローカライズ可能なフィールドを持つLiferayアセットは、同義語セットで機能します。

Elasticsearchでサポートされている [`=>`形式](https://www.elastic.co/guide/en/elasticsearch/guide/current/synonyms.html) は同義語セットUIではサポートされていません。

<a name="同義語セットの作成と管理" />

## 同義語セットの作成と管理

必要な数の同義語キーワードをセットに追加して、同義語セットを作成します。 同義語セットが保存されると、同じ会社スコープ（同義語が設定された[仮想インスタンス](../../system-administration/configuring-liferay/virtual-instances/understanding-virtual-instances.md)の任意のサイト）での検索が有効になります。

新しい同義語セットを作成するには、

1. グローバルメニュー（![Global Menu](../../images/icon-applications-menu.png)）から、 ［**アプリケーション**］ → ［**検索の調整**］ → ［**同義語**］ に移動します。

   ![グローバルメニューの［同義語］セクションに移動します](synonym-sets/images/01.png)

1. **追加** アイコン（![Click on the add icon](../../images/icon-add.png)）をクリックして、新規同義語セットを追加します。

1. セット内の同義語のリストを入力します。 同義語の入力は、 **Enter** をクリックするか、カンマを入力することで行います。

   ![異なる同義語をセットに入力します。](synonym-sets/images/02.png)

1. 同義語の横にある ［**X**］ をクリックすると、同義語を削除できます。 セットが終わったら、 ［**公開**］ をクリックします。

1. セットを編集または削除するには、 **オプション** アイコン（![Click on the options icon.](../../images/icon-options.png)）をクリックし、 ［**Edit**］ または ［**Delete**］ をクリックします。

   ![編集または削除をクリックして変更を加えます。](synonym-sets/images/03.png)

   同義語セットが公開されると、使用できるようになります。

<a name="同義語セットの使用" />

## 同義語セットの使用

セットに保存した同義語キーワードの1つを検索することで、同義語セットをテストできます。 キーワードと同義語に一致する結果が検索結果ウィジェットに返されます。

![セットから同義語を検索してみてください。](synonym-sets/images/04.png)

上記の例では、月面車に関するこのブログ記事には「LRV」という単語は含まれていませんが、検索結果の一致として返されるようになりました。 同義語が強調表示されていることにも注目してください。

<a name="新しいシノニム言語フィルタの作成" />

## 新しいシノニム言語フィルタの作成

> **入手方法Liferay DXP 7.3 FP2およびLiferay DXP 7.2 FP13**

Synonyms Setsは、すぐに [英語とスペイン語のみ](#requirements-and-limitations) の同義語をサポートします。 他の言語に対応するためには、以下のような設定が必要です。

- [French](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/analysis-lang-analyzer.html#french-analyzer) アナライザーに必要な変更（Synonym graph token filter内のパイプラインを含む）

を加え、デフォルトの再実装をすることで [カスタムアナライザー](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/analysis-custom-analyzer.html) を作成します。</li> 
  
  - コネクタ構成の追加インデックス設定を使用して、カスタム・アナライザー定義をインデックス設定に追加します。
- コネクタ構成の［タイプマッピングを上書き］設定を使用して、Liferay DXPのデフォルトのタイプマッピングをオーバーライドして、目的のフィールドにカスタム・アナライザーを適用します。
- 新しい設定を適用するためにインデックスを再作成します。</ul> 



### 前提条件デフォルトマッピングの取得

カスタマイズする前に、Elasticsearch コネクタのデフォルトの JSON マッピングを取得する必要があります。

Liferay DXP バンドルからマッピングを取得するには。

1. `[Liferay Home]/osgi/marketplace`に移動します。
1. LPKGを探す `Liferay Foundation - Liferay Connector to Elasticsearch 6/7 - Impl.lpkg`。
1. アーカイブマネージャで開き、名前のパターンが `com.liferay.portal.search.elasticsearch6/7-x.y.z.jar`となっているJARファイルを展開します。
1. 解凍したJARファイルをアーカイブマネージャーで開き、 `META-INF/mappings`に移動します。
1. `liferay-type-mappings.json` というファイルが必要なリソースになります。 お使いのファイルシステムに解凍してください。

ソースコードからマッピングを取得するには、 [Liferay DXP ソースコードアクセス](https://help.liferay.com/hc/en-us/articles/360045389291) ,

1. ソースコードのリポジトリにアクセスできたら、上記の記事の手順に従って、修正パックレベルのタグを探します。
1. `modules/apps/portal-search-elasticsearch(6 or 7)/portal-search-elasticsearch(6 or 7)-impl/src/main/resources/META-INF/mappings`に移動します。
1. ここには、前述のJSONファイル（`liferay-type-mappings.json`）があります。



```{warning}
   インデックスのマッピングや設定は、バージョン間で変更されることがあり、場合によっては（Fix PackやService Packによって）マイナーバージョンの中でも変更されることがあります。 マッピングや設定のカスタマイズは、アップグレードや新しいパッチレベルへの移行時に見直し、必要に応じて対応する必要があります。 さらに、Liferayのサーチチームは、将来のバージョンでは、より多くの言語をサポートすることを計画しており、カスタマイズが不要になります。
```




### 言語の追加

これでデフォルトのマッピングファイルができたので、必要な変更を加えてフランス語の同義語に必要な設定を追加します。

1. Elasticsearch接続の「システム設定」エントリ---Elasticsearch 6/7にアクセスしてください。

1. 追加インデックス設定フィールドに、 `の解析` ブロックを追加します。 
   
   

   ```json
   {
       "analysis": {
           "analyzer": {
               "custom **liferay** analyzer_fr": {
                   "filter": [
                       "french_elision",
                       "lowercase",
                       "french_stop",
                       "my-synonym-filter-fr",
                       "french_stemmer"
                   ],
                   "tokenizer": "standard"
               }
           },
           "filter": {
               "my-synonym-filter-fr": {
                   "lenient": true, 
                   "synonyms": [],
                   "type": "synonym_graph"
               },
               "french_stop":{
                   "type":"stop",
                   "stopwords":" **french** "
               },
               "french_stemmer":{
                   "type":"stemmer",
                   "language":"light_french"
               },
               "french_elision": {
                   "type": "elision",
                   "articles_case": true,
                   "articles": [
                       "l", "m", "t", "qu", "n", "s",
                       "j", "d", "c", "jusqu", "quoiqu",
                       "lorsqu", "puisqu"
                   ]
               }
           }
       }
   }
   ```


この構成で設定を追加すると、初期設定のインデックス設定で利用できる項目が増えます。 デフォルトのJSON設定は、ソースコードの `index-settings.json` ファイルの中にあります。 ここでは、 `custom_liferay_analyzer_fr` という名前の新しいアナライザーを作成し、新しいフィルター `my-synonym-filter-fr`を使用しています。 `synonyms` の配列は今のところ空です。UIで作成したシノニムセットはここに表示されます。

1. ［タイプマッピングの上書き］フィールドを使用して、 `template_fr` ダイナミックフィールドのアナライザーを変更して、カスタムアナライザー（`custom_liferay_analyzer_fr`）を使用します。 
   
   

   ```{important}
      この例は、簡潔にするために切り取られています。 Override Type Mappingsは、Liferayのデフォルトの型マッピングを完全にオーバーライドして無視しますので、オーバーライドされた部分だけでなく、完全なマッピングファイルを提供する必要があります。   
   ```




   ```json
    {
    "LiferayDocumentType": {
        "date_detection": false,
        "dynamic_templates": [
            // (...)
            {
                "template_fr": {
                    "mapping": {
                        "analyzer": "custom **liferay** analyzer_fr",
                        "store": true,
                        "term **vector": "with** positions_offsets",
                        "type": "text"
                    },
                    "match": "\\w+ **fr\\b|\\w+** fr_[A-Z]{2}\\b",
                    "match **mapping** type": "string",
                    "match_pattern": "regex"
                }
            },
            // (...)
   ```


ここでの重要な変更点は、デフォルトで割り当てられているアナライザー（`french`）が、カスタムアナライザー `custom_liferay_analyzer_fr`に置き換えられていることです。

1. 設定の変更を保存します。 
   
   

   ```{tip}
      Sidecar Elasticsearch サーバーを使用している場合、コンソールにエラーが表示されることがあります。 Liferay DXPを再起動すると問題が解決します。
   ```


1. ここで、「システム設定」の &rarr; 「検索」 &rarr; 「同義語」を選択します。

1. カスタムフィルター名（例： `custom-synonym-filter-fr`）をフィルター名の設定に追加し、設定を保存します。

1. 完全な再インデックスを実行します。コントロールパネルの &rarr; 検索 &rarr; インデックスアクションで、 **すべての検索インデックスの再インデックスをクリックします。** をクリックします。
   
   カスタムマッピングが正常に適用されたことを確認するには、「フィールドマッピング」タブに移動し、インデックスを選択し（例えば、 `liferay-20101`）、右パネルで `template_fr` を探します。
   
   カスタムアナライザーを含む追加インデックス設定も追加されていることを確認するために、Elasticsearchに対して以下のAPIコールを行います。 `http://<host>:<port>/liferay-[company-id]/_settings` そして、レスポンスの中にあなたのアナライザー名を探します。 例えば、 `localhost`で稼働しているLiferay DXPサーバーのサイドカーElasticsearchサーバーのインデックス設定を見るには、 `20101`で、 <http://localhost:9201/liferay-20101/_settings>にアクセスします。

新しいフィルターが動作していることを確認するため。 

1. Synonymsアプリケーションに移動します。グローバルメニューのアプリケーションタブから、 **Synonyms**（「検索チューニング」の下）をクリックします。

1. 新しいシノニムセットを作成します。 `maison, logement`.

1. 英語とフランス語の翻訳付きのウェブコンテンツ記事を作成します。 フランス語のタイトルに **maison** を追加します。

1. 英語とフランス語の翻訳付きで、別のWebコンテンツ記事を作成します。 フランス語のタイトルに **logement** を追加します。

1. フランス語のロケールに切り替えて、 **maison** を検索します。 両方の記事が返されます。

