# Liferay Elasticsearch コネクタの高度な設定

[Elasticsearchの接続](./connecting-to-elasticsearch.md) は、設定ファイル [またはシステム設定](./elasticsearch-connector-configuration-reference.md#configuration-files-and-system-settings-entries) を使って設定します。

Elasticsearchコネクタには多くの設定オプションが用意されています。ほとんどのElasticsearchの設定は、Liferayの似たような、あるいは同じ名前の設定によって構成することができます（例えば、 `httpSSLEnabled`）。 特別な設定が必要な場合は、 [詳細設定](./../elasticsearch/elasticsearch-connector-configuration-reference.md)を使用して、必要な設定オプションを追加してください。 これらの特別な設定のほとんどは、設定やマッピングを追加したり、オーバーライドしたりするものです。

* [インデックス構成の追加](#adding-index-configurations)
* [タイプマッピングの追加](#adding-type-mappings)
* [型マッピングのオーバーライド](#overriding-type-mappings)
* [開発モードのElasticsearchに設定を追加する](#adding-configurations-to-the-development-mode-elasticsearch)

Elasticsearchで設定可能なものは、Elasticsearchコネクターを使って設定することができます。

<a name="adding-settings-and-mappings-to-the-liferay-elasticsearch-connector" />

## Liferay Elasticsearch コネクタへの設定とマッピングの追加

利用可能な設定オプションは、簡単に設定できる一般的なものと、YAMLやJSONの入力が必要な複雑な設定の2つのグループに分かれていると考えてください。

![システム設定で現在用意されている設定に、Elasticsearchの設定を追加することができます。](./advanced-configuration-of-the-liferay-elasticsearch-connector/images/01.png)

<a name="adding-index-configurations" />

### インデックス構成の追加

`additionalIndexConfigurations` 設定は、 [会社インデックス](../../search-administration-and-tuning/elasticsearch-indexes-reference.md) （例：それぞれのLiferayの仮想インスタンスのインデックス）が作成されるときに、それぞれに適用される追加の設定（JSONやYAML）を定義します。 たとえば、この設定を使って、カスタムのアナライザーやフィルターを作成することができます。 使用可能な設定の完全なリストは、 [Elasticsearchのリファレンス](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index-modules.html) を参照してください。

ここでは、フィールドやダイナミックテンプレートに適用できる [の解析](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index-modules-analysis.html) の設定例を紹介します（ダイナミックテンプレートへの適用例は、</a> 下記

を参照してください）。</p> 



```json
{  
    "analysis": {
        "analyzer": {
            "kuromoji_liferay_custom": {
                "filter": [
                    "cjk_width",
                    "kuromoji_baseform",
                    "pos_filter"
                ],
                "tokenizer": "kuromoji_tokenizer"
            }
        },
        "filter": {
            "pos_filter": {
                "type": "kuromoji_part_of_speech"
            }
        }
    }
}
```




<a name="adding-type-mappings" />

### タイプマッピングの追加

`additionalTypeMappings` は、各 [会社やシステムのインデックス](../../search-administration-and-tuning/elasticsearch-indexes-reference.md) （つまり、各Liferay仮想インスタンスのインデックス）にデータをインデックスするための追加マッピングを定義します。 これらは、インデックスの作成時に適用されます。 JSONの構文を使ってマッピングを追加します。 詳しくは、 [こちら](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/mapping.html) と [こちら](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/indices-put-mapping.html) をご覧ください。 新しいフィールド（`プロパティ`）のマッピングや新しいダイナミックテンプレートには `additionalTypeMappings` を使用し、既存のマッピングを上書きしないようにしてください。 ここで設定したマッピングが既存のマッピングと重なる場合は、インデックス作成に失敗します。 デフォルトのマッピングを置き換えるには、 `overrideTypeMappings` を使用します。

ダイナミックテンプレートと同様に、Liferayのタイプマッピングにサブフィールドマッピングを追加することができます。 これらは、Elasticsearchでは [プロパティ](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/properties.html) と呼ばれています。

プロパティを追加するには、次のJSON構文を使用します。



```json
{ 
    "LiferayDocumentType": {  
        "properties": {   
            "fooName": {
                "index": "true",
                "store": "true",
                "type": "keyword"
            }
        }   
    }
}
```


追加したマッピングがLiferayのマッピングに追加されたことを確認するには、追加内容を保存してインデックスを再作成した後に、 `curl` を使ってこのURLにアクセスします。



```bash
curl http://[HOST]:[ES_PORT]/liferay-[COMPANY_ID]/_mapping?pretty
```


以下は、 `localhost:9200`で動作するElasticsearchインスタンスで、Liferayの企業IDが `20116`の場合の例です。



```bash
curl http://localhost:9200/liferay-20116/_mapping?pretty
```


上記のURLでは、 `liferay-20116` がインデックス名です。 これを含めると、その名前のインデックスを作成するために使用されたマッピングを確認したいことになります。

Elasticsearchのフィールドデータタイプの詳細については、 [こちら](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/mapping-types.html) を参照してください。

上の例では、Liferayのタイプマッピングに、 `fooName` フィールドを追加する方法を示しています。 `fooName` はマッピングの既存のプロパティではないため、正常に機能します。 既存のプロパティマッピングを上書きしようとすると、インデックス作成に失敗します。 代わりに、 `overrideTypeMappings` の設定を使用して、マッピングの `プロパティ` をオーバーライドします。



<a name="overriding-type-mappings" />

### 型マッピングのオーバーライド

`overrideTypeMappings` を使用して、Liferayのデフォルトのタイプマッピングをオーバーライドし、データが [会社およびシステムインデックス](../../search-administration-and-tuning/elasticsearch-indexes-reference.md)インデックス付けされる方法を制御します。 これは高度な機能であり、厳密に必要な場合にのみ使用する必要があります。 この値を設定すると、LiferayソースコードでLiferayドキュメントタイプを定義するために使用されるデフォルトマッピング（例えば、 `liferay-type-mappings.json`）は完全に無視されますので、修正するセグメントだけでなく、マッピング定義全体をこのプロパティに含めてください。

変更を加えるには、インデックス作成に使用されている現在のマッピングの全リストを、URL



```
http://[HOST]:[ES_PORT]/liferay-[COMPANY_ID]/_mapping?pretty
```


その内容を、このプロパティの値として（「システム設定」またはOSGiの設定ファイルに）コピーします。 冒頭の中括弧 `{`は残しますが、2行目と3行目は完全に削除します（インデックス名を書いた行と `マッピングを書いた行`）。



```json
"liferay-[COMPANY_ID]": {
    "mappings" : {
```


次に、マッピングの最後から、最後の2つの中括弧を削除します。



```json
    }
}
```


ここで、好きなマッピングを変更します。 変更を保存し、 [サーバ管理](../../../system-administration/using-the-server-administration-panel.md)から再インデックスをトリガーすると、変更が有効になります。 

ここでは、 [ダイナミック・テンプレート](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/dynamic-templates.html) が、 `additionalIndexConfigurations` の解析設定を使用して、 `_ja`で終わるすべての文字列フィールドを解析している例を示しています。 これを他のすべてのデフォルトマッピングと一緒にインクルードし、提供されている `template_ja` をこのカスタムマッピングで置き換えるのです。



```json
{
    "LiferayDocumentType": {
        "dynamic_templates": [
            {
                "template_ja": {
                    "mapping": {
                        "analyzer": "kuromoji_liferay_custom",
                        "index": "analyzed",
                        "store": "true",
                        "term_vector": "with_positions_offsets",
                        "type": "string"
                    },
                    "match": "\\w+_ja\\b|\\w+_ja_[A-Z]{2}\\b",
                    "match_mapping_type": "string",
                    "match_pattern": "regex"
                }
                ...
            }
        ]
    }
}
```




<a name="adding-configurations-to-the-development-mode-elasticsearch" />

### 開発モードのElasticsearchに設定を追加する

追加設定(`additionalConfigurations`) フィールドを使用して、埋め込みまたはサイドカーのElasticsearchインスタンスの追加設定を（YAMLで）定義します。 これはテスト環境でのみ有効です。 通常、 `elasticsearch.yml` で設定するノードの設定は、すべてここで宣言することができます。 可能なすべてのノード設定の説明については、 [Elasticsearch のドキュメンテーション](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index.html)  を参照してください。



<a name="multi-line-yaml-configurations" />

## 複数行のYAML設定

OSGiの設定ファイルを使って前節の設定を行う場合、複数行にわたるYAMLスニペットを書く必要があるかもしれません。 そのための構文は簡単で、次のように各行に 「 \n 」を追加するだけです。



```yaml
additionalConfigurations=\
                    cluster.routing.allocation.disk.threshold_enabled: false\n\
                    cluster.service.slow_task_logging_threshold: 600s\n\
                    index.indexing.slowlog.threshold.index.warn: 600s\n\
                    index.search.slowlog.threshold.fetch.warn: 600s\n\
                    index.search.slowlog.threshold.query.warn: 600s\n\
                    monitor.jvm.gc.old.warn: 600s\n\
                    monitor.jvm.gc.young.warn: 600s
```


簡単な設定から既存の型マッピングの上書きまで、ElasticsearchとLiferayのElasticsearchへのコネクタは設定が可能です。
