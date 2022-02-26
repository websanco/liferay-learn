# カスタムファセット設定

カスタムファセットは、すぐに使用できる検索ファセットの中でもユニークなものです。 結果を単一の静的フィールド（更新日やアセットタイプなど）でグループ化するのではなく、カスタムファセットを使用して結果をグループ化するフィールドを選択します。 さらにカスタマイズして、まったく新しいファセットを作成できます。

<a name="カスタムファセットの設定" />

## カスタムファセットの設定

1. ページ上部の **追加** アイコン（![Add icon](../../../images/icon-add-app.png)）をクリックします。

1. カスタムファセットを見つけて、左側のファセットのコレクションにドラッグします。 カスタムファセットは、ウィジェットの［検索機能］セクションにあります。

   ![検索ウィジェットの下でカスタムファセットを見つけます。](custom-facet/images/01.png)

1. ファセットの **オプション** アイコン（![Options icon](../../../images/icon-app-options.png)）をクリックし、 ［**設定**］ をクリックします。

   ![［設定］オプションをクリックします。](custom-facet/images/02.png)

   カスタムファセットには、以下で説明するいくつかの設定オプションがあります。

1. オプションの設定が終了したら、 ［**保存**］ ボタンをクリックします。

**ディスプレイの設定：****デフォルト** 、 **コンパクトレイアウト** 、 **ラベルレイアウト** の中から選択します。 デフォルトのレイアウトでは、各用語の横にチェックボックスが表示されますが、コンパクトレイアウトでは表示されません。 ラベルレイアウトでは、用語ごとにクリック可能な小さなラベルが表示されます。

詳細設定には、追加のオプションが含まれています。

**集約フィールド：** 結果を集計するインデックス付きフィールドの名前を入力します。 これは、分析されていないキーワードフィールドでなければなりません。 詳細は、以下を参照してください。

**カスタム見出し：** このファセットに表示する見出しを入力します。 設定されていない場合、集約されたフィールド名が表示されます。

**カスタムパラメータ名：** 選択した値のURLパラメーター名を指定します。 設定されていない場合、集約されたフィールド名が使用されます。

**最大ターム数：** ファセットに一致する用語がいくつ見つかったかに関係なく、表示するファセット用語の最大数を設定します。

**頻度の閾値：** 用語がファセット用語のリストに表示されるために必要な最小頻度を設定します。 たとえば、ファセットの頻度の閾値が3に設定されている場合、一致する結果が2つの用語は用語結果リストに表示されません。

**表示頻度：** 用語頻度を表示するかどうかを選択します。

**統合検索キー：** このウィジェットが参加している代替検索のキーを入力します。 設定されていない場合、このウィジェットはデフォルトの検索に参加します。 この値は通常、アプリケーション定義のインデックスの名前です。


<a name="インデックス付きフィールドの検索" />

## インデックス付きフィールドの検索

カスタムファセットを使用するには、構成で使用する非分析キーワードフィールドを知っておく必要があります。

```{tip}
   Elasticsearchは複数の方法でフィールドのインデックス化をサポートしています。 いくつかのテキストフィールドは、マッピングの中で ` [multi-fields](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/multi-fields.html)  のようにネストされている場合や、フィールドが `fieldName_sortable` として追加の別のフィールドマッピングでマッピングされている場合（`キーワード` として）、キーワードフィールドとして使用することができます。 以下の例では、カスタムフィールドのファセットを作成し、Elasticsearchのマルチフィールドのコンセプトを活用しています。
```

使用可能なフィールドのリスト全体を参照するには、［**コントロールパネル**］→［**構成**］→［**検索**］（［**フィールドマッピング**］タブをクリック）からフィールドマッピングを調べます。 ここでは、数多くのインデックスを見ることができます。 興味のあるLiferayアセットは、 [company index](../../search-administration-and-tuning/elasticsearch-indexes-reference.md)にインデックスされており、 `liferay-20101` のような名前になっています（`20101` はカンパニーID）。

あるいは、検索エンジンのAPIを使ってマッピングを閲覧することもできます。 Elasticsearchでは、端末からcURLを使ってフィールドマッピングにアクセスし、 [Get Mapping API](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/indices-get-mapping.html) を呼び出すことができます。

```{tip}
   [Kibanaの](../../liferay-enterprise-search/monitoring-elasticsearch.md)  Dev Toolsのコンソールは、ElasticsearchのAPIコールを行うのに、cURLよりも便利です。
```

 ```bash
curl -X GET "localhost:9200/_mapping/LiferayDocumentType"?pretty
 ```

Solrは [ListFields API](https://lucene.apache.org/solr/guide/6_6/schema-api.html#SchemaAPI-ListFields) を使用します。

```bash
curl http://localhost:8983/solr/liferay/schema/
```

以下は、Elasticsearchの例からの出力の一部です。

```json
"ddmStructureKey": {
  "store": true,
  "type": "keyword"
},
"ddmTemplateKey": {
  "store": true,
  "type": "keyword"
},
"defaultLanguageId": {
  "store": true,
  "type": "keyword"
},
"description": {
  "store": true,
  "term_vector": "with_positions_offsets",
  "type": "text"
},
"discussion": {
  "store": true,
  "type": "keyword"
},
```

<a name="カスタムフィールドへのアクセス" />

## カスタムフィールドへのアクセス

[カスタムフィールド](./../../../system-administration/configuring-liferay/adding-custom-fields.md) を作成し、 **キーワードとして検索可能** という設定を有効にすると、カスタムフィールドはバックアセット（Blogs Entriesなど）と一緒にインデックス化されます。 再インデックス化後は、既存のエントリーにも適用されます。 フィールド自体はテキストフィールドで、`expando__keyword__custom_fields__Enabled`のような名前ですが（カスタムフィールドUIで **Enabled** というフィールド名をつけた場合）、別の`raw`のキーワードフィールドを作成するためのネストしたフィールドマッピングが含まれています。

カスタム・ファセットで生のフィールドを使用するには、 `.raw` を、 **集約フィールド** .raw のカスタム・フィールド名に追加します。

`expando__keyword__custom_fields__Enabled.raw`

以下は、Kibanaで実行してテキストフィールドのマッピングを検査するためのクエリです（インデックス名のパラメータで、Company Id---`20097`---を置き換えます）。

```bash 
GET /liferay-20097/_mapping/field/expando__keyword__custom_fields__Enabled
```

JSONが返されます。

```json
{
  "liferay-20097" : {
    "mappings" : {
      "expando__keyword__custom_fields__Enabled" : {
        "full_name" : "expando__keyword__custom_fields__Enabled",
        "mapping" : {
          "expando__keyword__custom_fields__Enabled" : {
            "type" : "text",
            "store" : true,
            "fields" : {
              "raw" : {
                "type" : "keyword"
              }
            },
            "analyzer" : "keyword_lowercase"
          }
        }
      }
    }
  }
}
```

すべての生のフィールドを見るには、インデックスに `*.raw` フィールドを問い合わせます。

```bash 
GET /liferay-20097/_mapping/field/*.raw
```

カスタムフィールドを検索可能に設定することは、エンティティが変更されたとき、または再インデックスがトリガーされたときに、フィールドの値がインデックスされることを意味します。 `java.lang.String` フィールドのみを検索可能にすることができます。

<a name="ddmのネストされたフィールドへのアクセス" />

## DDMのネストされたフィールドへのアクセス

[7.3 Breaking Changes ドキュメント](../../../liferay-internals/reference/7-3-breaking-changes.md#dynamic-data-mapping-fields-in-elasticsearch-have-changed-to-a-nested-document) で説明されているように、Liferay Dynamic Data Mapping フレームワークがいくつかのフィールドをインデックスする方法が変更されました。 以前は検索エンジンのドキュメントのルートにありましたが、現在はネストされたフィールドになっています。  この変更は、Liferay 7.3 と Liferay 7.2 SP3/FP8+ に影響します（ただし、システム設定 &rarr; Dynamic Data Mapping Indexer で **Enable Legacy Dynamic Data Mapping Index Fields** 設定が無効になっている場合のみです）。 最新のFix PackとGAリリースの7.3では、この変更はLiferayのSearch APIで説明されており、設定の更新は必要ありません。 したがって、Elasticsearch ドキュメントのルートにあった `ddm__text__*` または `ddm__keyword__*` という名前のフィールドに依存している Custom Facet ウィジェットがある場合、これらのフィールドは通常どおり Custom Facet の **Aggregation Field** 設定で使用し続けます。これらのフィールドがドキュメントのルートにない場合でも、Custom Facet の **Aggregation Field** 設定でこれらのフィールドを通常通り使用し続けます。

既存のドキュメントのDDMフィールドをインデックスで探すには

```json
GET liferay-20097/_search
{
  "query": {
    "nested": {
      "path": "ddmFieldArray",
      "query": {
        "wildcard":  { "ddmFieldArray.ddmFieldName": "ddm__keyword*" }
      }
    }
  }
}
```

インデックス名パラメータの企業ID---`20097`---を、インスタンスの値に合わせて置き換えます。

返されたドキュメントには、ネストされたコンテンツを持つ `ddmFieldArray` オブジェクトがあります。

```json
 "ddmFieldArray" : [
    {
      "ddmFieldName" : "ddm__keyword__40806__Textb5mx_en_US",
      "ddmValueFieldName" : "ddmFieldValueKeyword_en_US",
      "ddmFieldValueKeyword_en_US_String_sortable" : "some text has been entered",
      "ddmFieldValueKeyword_en_US" : "some text has been entered"
    },
    {
      "ddmFieldName" : "ddm__keyword__40806__Selectjdw0_en_US",
      "ddmValueFieldName" : "ddmFieldValueKeyword_en_US",
      "ddmFieldValueKeyword_en_US_String_sortable" : "option 3",
      "ddmFieldValueKeyword_en_US" : "value 3"
    },
    {
      "ddmFieldName" : "ddm__keyword__40806__Boolean15cg_en_US",
      "ddmValueFieldName" : "ddmFieldValueKeyword_en_US",
      "ddmFieldValueKeyword_en_US" : "true",
      "ddmFieldValueKeyword_en_US_String_sortable" : "true"
    }
  ],
```

これらのフィールドの1つをカスタムファセットで使用するには、カスタムファセット構成の `ddmFieldName` の値（例えば、 `ddm__keyword__40806__Testb5mx_en_US`）を **集約フィールド** に入力します。
