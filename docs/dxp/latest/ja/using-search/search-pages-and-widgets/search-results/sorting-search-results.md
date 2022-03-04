# 検索結果の並べ替え

[検索結果](./search-results.md) ウィジェットに表示される検索結果は、デフォルトで [関連性スコア](./search-results.md#search-results-relevance) （ [検索エンジン](https://www.elastic.co/guide/en/elasticsearch/guide/master/scoring-theory.html) によって計算される）の順に並べられます。 並べ替えウィジェットを使用すると、ユーザーは返される結果の順序を制御できます。

ウィジェットを [検索ページ](../working-with-search-pages/search-pages.md) に追加して、結果のソートを開始します。

そのままの状態で、関連性ソートの代わりに次の方法で結果を並べ替えることができます。

- タイトルのアルファベット順
- 更新日（デフォルトでは最初に最新、または最も古いものを最初に選択）
- 作成日（デフォルトでは最初に最新、または古いものを最初に選択）
- 一致する各アセットを作成したユーザーのアルファベット順

並べ替えウィジェットの事前構成された並べ替え戦略のいずれかから選択するか、独自の戦略を構成します。

ウィジェットから不要な並べ替えオプションを削除することもできます。

<a name="adding-the-sort-widget-to-a-page" />

## ページへの並べ替えウィジェットの追加

並べ替えウィジェットを使い始めるには、

1. ページの［追加］メニュー（![Add](../../../images/icon-add-widget.png)）を開き、［ウィジェット］セクションを展開します。

1. ［検索］カテゴリから、並べ替えウィジェットをページにドラッグします。

<a name="configuring-the-sort-widget" />

## 並べ替えウィジェットの構成

並べ替えウィジェットの設定画面から、次のことができます

- 既存の並べ替えオプションを編集する
- オプションを削除する
- 新しいオプションを追加する

![ユーザーは、並べ替えウィジェットを使用して検索結果を並べ替えることができます。](./sorting-search-results/images/01.png)

ウィジェット構成画面にアクセスするには、ウィジェットの［オプション］メニュー（![Options](../../../images/icon-app-options.png)）を開き、［**構成**］をクリックします。

各並べ替えオプションには2つの設定があります： **ラベル** および **フィールド** 。

**ラベル：** 構成するソートのタイプに表示されるラベルを設定します。

**フィールド：** ソートするインデックス付きフィールドの `fieldName` を入力します。 ほとんどの場合、これは [キーワード](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/keyword.html) フィールドです。 その他の受け入れ可能なオプションは、 `日付` および任意の [数値データ型](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/number.html) です。 並べ替えウィジェット（以下を参照）で `テキスト` フィールドを強制的に動作させる方法さえあります。

![並べ替えウィジェットの構成から、並べ替えオプションを追加、編集、または削除します。](./sorting-search-results/images/02.png)

<a name="changing-the-default-sort-behavior" />

## デフォルトのソート動作の変更

初期設定では、関連性オプションがソートウィジェットのリストで最初に表示され、ページに適用されるデフォルトの並べ替えになります。 そのため、検索を実行すると、結果は関連性の高い順に並べられます。 関連性による検索は、ほとんどのユーザーにとって期待されるデフォルトの動作ですが、ソートウィジェット設定の最初のオプションを変更することで、デフォルトの検索に異なるソート戦略を及ぼすことができます。

デフォルトのソートオプションを変更するには、次のようにします。

1. ウィジェットオプションの ![Widget Options](../../../images/icon-widget-options.png) ボタンをクリックして、ソートウィジェットの設定画面を開きます。

1. 関連性オプションの下にある追加![Add](../../../images/icon-duplicate.png) ボタンをクリックします。

1. 現在の関連性オプションの値を複製します。これにより、関連性がリストの2番目のオプションになります。

1. ここで、トップオプションを変更します。 既存のオプションを選択した場合は、削除![Minus](../../../images/icon-minus.png)をクリックして重複したオプションを削除してください。

1. 設定を保存します。 検索を入力すると、新しいソートが適用されたことがわかります。

<a name="finding-sortable-fields" />

## 並べ替え可能なフィールドの検索

適切な権限を持つユーザーは、 **コントロールパネル** &rarr; ［**設定**］ &rarr; ［**検索機能**］ へ行き、ソートウィジェットで使用できるフィールドを見つけることができます。  そこから、［フィールドマッピング］タブを開き、各インデックスのマッピングを参照します。  マッピングの `プロパティ` セクションまでスクロールし、 `キーワード` フィールド、 `日付` フィールド、または数値データタイプのフィールドを見つけます。 `タイプ` フィールドは有益です：

    "type" : "keyword"
    
    "type" : "date"
    
    "type" : "long"

`テキスト` フィールドでソートする必要がある場合は、タイプ `キーワード`して、フィールドの新しいバージョンをインデックスに追加します。 上記のフィールドマッピング画面で、 `liferay-［companyID］`というインデックスの `firstName` フィールドを確認します。

```
"firstName" : {
    "type" : "text",
    "store" : true
},
"firstName_sortable" : {
    "type" : "keyword",
    "store" : true
},
```

サフィックス `_sortable`対応するフィールドがあり、ソートに適したタイプ（`キーワード`）があります。 ソート可能なフィールドは、 [ポータルのプロパティ](https://learn.liferay.com/reference/latest/en/dxp/propertiesdoc/portal.properties.html#Lucene%20Search) を通して得られたものです。

```properties
index.sortable.text.fields=firstName,jobTitle,lastName,name,screenName,title
```

ここにリストされているすべてのテキストフィールドには、インデックスで自動的に作成される `fieldName_sortable` 対応物があります。 さらに追加するには、このプロパティを [`portal-ext.properties`](./../../../installation-and-upgrades/reference/portal-properties.md) ファイルにLiferayホームフォルダーにコピーし、並べ替えに必要な新しいフィールド名を追加して、サーバーを再起動します。

<a name="adding-new-sort-options" />

## 新しい並べ替えオプションの追加

新しいフィールドまたは適切なタイプの既存のフィールドで並べ替えるには、既存のオプションの **フィールド** 構成の下にあるプラス記号を使用して、ウィジェット構成でフィールドの `fieldName_sortable` バージョンを必ず使用してください。

すでに適切なデータ型である新しい並べ替えオプションを追加するには、オプションの **フィールド** 構成の下にあるプラス記号を使用して、フィールドに入力します。 構成画面でのオプションの順序は、ウィジェットを検索用に構成するときの選択リストの順序と一致します。

<a name="editing-and-deleting-sort-options" />

## 並べ替えオプションの編集と削除

既存のオプションを編集するには、設定セクションのテキストを編集します。

既存のオプションを削除するには、 **フィールド** 設定の下にあるマイナス記号を使用します。

<a name="controlling-the-sort-order" />

## ソート順の制御

並べ替えオプションの順序を制御するには、 `fieldName`後にプラス記号またはマイナス記号を追加します。 それがどのように機能するかを理解するために、 **作成** および **作成（最も古い**） とラベル付けされた既存のソートオプションの実行方法を見てください。

**ラベル：****作成日_** フィールド：** `create-date`となります。

フィールド名の次の`-` 記号は順序が **下降** であることを示します 。  この方法で並べ替えると、最近作成された検索結果がリストの一番上に表示されます。

**ラベル：****作成日（古い順**）**フィールド。** `createDate⁺`となります。

フィールド名に続く `+` 記号は、順序が **昇順** ことを示しています。  この方法で並べ替えると、最も古い（作成日順）結果がリストの一番上に表示されます。

<a name="sorting-by-nested-fields" />

## ネストしたフィールドでのソート

> 提供時期： 7.2 FP12, 7.3 FP2

[Accessing Nested DDM Fields](../search-facets/custom-facet.md#accessing-nested-ddm-fields) で説明されているように、Liferay 7.2 SP3 /FP8 (およびLiferay 7.3のすべてのバージョン)では、DDMフィールドは [ネストされたフィールド](../../../liferay-internals/reference/7-3-breaking-changes.md#dynamic-data-mapping-fields-in-elasticsearch-have-changed-to-a-nested-document) になりました。 7.2および7.3の最新のFix PackおよびGAリリースでは、これらのネストされたフィールドを考慮して、 [Elasticsearch Nested query](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-nested-query.html) がサポートされています。

Elasticsearch ドキュメントのルートにある `ddm__keyword__*` という名前のフィールドに依存していたソート構成は引き続き有効です--Search フレームワーク自体がネストしたフィールドタイプを考慮して調整されました。 これらのフィールドは、ソートウィジェットの **フィールド** の設定で、ドキュメントのルートにないにもかかわらず、通常通り使用します。

ソートウィジェットは、キーワード、日付、数値の各フィールドに対応しています。 インデックス内の既存のドキュメントにあるDDMキーワードフィールドを見つけるには

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

これらのフィールドのいずれかをソートの設定で使用するには、 `ddmFieldName` の値（例えば、 `ddm__keyword__40806__Testb5mx_en_US`）を **Field** の設定として入力します。
