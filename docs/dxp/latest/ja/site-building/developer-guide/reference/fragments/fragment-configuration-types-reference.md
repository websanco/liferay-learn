# フラグメント設定タイプのリファレンス

このリファレンスには、フラグメントに使用可能な構成タイプがリストされています。 フラグメントを構成可能にする方法の詳細は、[Adding Configuration Options to Fragments](../../developing-page-fragments/adding-configuration-options-to-fragments.md)を参照してください。

実装できる 5 つの構成可能なフラグメント タイプがあります。

  - `checkbox`
  - `colorPalette`
  - `itemSelector` (Liferay DXP 7.3 で使用可能)
  - `select`
  - `text`

<!-- end list -->

```{note}
FreeMarker コンテキストに挿入された設定値は、JSON ファイルで指定された定義済みの `datatype` 値に従います。 たとえば、`dataType` が String の場合、`configuration.[name-value]?is_string` は `true` となります。
```

## チェックボックスの設定

次の JSON 構成は、ブール値の選択が必要な場合に実装できるチェックボックスを作成します。

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "name": "hideBody",
                    "label": "Hide Body",
                    "description": "hide-body",
                    "type": "checkbox",
                    "defaultValue": false
                }
                ...
            ]
        }
    ]
}
```

![チェックボックス設定は、ブール値の選択が必要な場合に役立ちます。](./fragment-configuration-types-reference/images/01.png)

## カラーパレットの設定

次の JSON 構成は、色を選択する必要がある場合に実装できるカラー セレクターを作成します。

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "name": "textColor",
                    "label": "Text color",
                    "type": "colorPalette",
                    "dataType": "object",
                    "defaultValue": {
                        "cssClass": "white",
                        "rgbValue": "rgb(255,255,255)"
                    }
                }
                ...
            ]
        }
    ]
}
```

`colorPalette` タイプは、`cssClass` および `rgbValue` の 2 つの値を持つオブジェクトを格納します。

たとえば、上記のスニペットを実装すると、次のように FreeMarker コンテキストで使用できます。

``` html
<h3 class="text-${configuration.textColor.cssClass}">Example</h3>
```

白を選択した場合、 `h3` タグの見出しはクラス `text-white'` を持ちます。

![カラー パレットの設定は、色の選択が必要な場合に役立ちます。](./fragment-configuration-types-reference/images/02.png)

## アイテムセレクターの設定

次の構成は、フラグメントに含める既存のコンテンツ (デフォルトでは、Web コンテンツの記事、ブログエントリー、またはドキュメント) を 1 つ選択できるセレクターを作成します。

``` json
{
    "fieldSets": [{
        "fields": [{
            "label": "select-content",
            "name": "itemSelector1",
            "type": "itemSelector",
            "typeOptions": {
                "enableSelectTemplate": true
            }
        }]
    }]
}
```

作成者が特定の種類のコンテンツのみを選択できるようにする、より高度な構成を提供できます。 以下の構成では、Web コンテンツの記事のみを選択できることを指定しています。 オプションの `itemSubtype` プロパティは、選択された Web コンテンツの記事が `article-structure-key-15` のストラクチャーを使用している必要があることを指定します。

``` json
{
    "fieldSets": [{
        "fields": [{
            "label": "select-content",
            "name": "itemSelector1",
            "type": "itemSelector",
            "typeOptions": {
        "itemType" : "com.liferay.journal.model.JournalArticle",
        "itemSubtype": "article-structure-key-15"
            }
        }]
    }]
}
```

次の例では、ストラクチャー `metadataset-structure-key-2` を使用している `img` または `jpg` MIME タイプのドキュメントのみを選択できることを指定しています。

``` json
{
    "fieldSets": [{
        "fields": [{
            "label": "select-content",
            "name": "itemSelector1",
            "type": "itemSelector",
            "typeOptions": {
        "itemType" : "com.liferay.portal.kernel.repository.model.FileEntry",
        "itemSubtype": "metadataset-structure-key-2",
        "mimeTypes": ["img/jpg"]
            }
        }]
    }]
}
```

次の例では、ブログエントリーのみを選択できるように指定しています。

``` json
{
    "fieldSets": [{
        "fields": [{
            "label": "select-content",
            "name": "itemSelector1",
            "type": "itemSelector",
            "typeOptions": {
        "itemType" : "com.liferay.blogs.model.BlogsEntry",
            }
        }]
    }]
}
```

その後、Web コンテンツ記事に次の HTML スニペットを使用して、フラグメント内のコンテンツをレンダリングできます。

``` markup
<div class="fragment_name">
  [#if configuration.itemSelector1.content??]
       ${configuration.itemSelector1.content}
  [/#if]
</div>
```

また、コンテンツの特定の部分にアクセスする必要がある場合には、`[name-of-field]Object` ( 下の例では `itemSelector1Object` ) というキーの下で、フラグメント内の Java オブジェクトにアクセスすることができます。 次の例は、Web コンテンツの記事のタイトル、説明、および本文をレンダリングします。

``` markup
<div class="fragment_name">
  [#if configuration.itemSelector1.content??]
    ${itemSelector1Object.getTitle()}
    ${itemSelector1Object.getDescription()}
    ${itemSelector1Object.getContent()}
  [/#if]
</div>
```

![アイテム セレクターの設定は、既存のコンテンツを表示するオプションの選択が必要な場合に役立ちます。](./fragment-configuration-types-reference/images/03.png)

## 選択設定

次の JSON 構成は、事前定義されたオプションを選択する必要がある場合に実装できるセレクターを作成します。

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "name": "numberOfFeatures",
                    "label": "Number Of Features",
                    "description": "number-of-features",
                    "type": "select",
                    "dataType": "int",
                    "typeOptions": {
                        "validValues": [
                            {"value": "1"},
                            {"value": "2"},
                            {"value": "3"}
                        ]
                    },
                    "defaultValue": "3"
                }
                ...
            ]
        }
    ]
}
```

![選択設定は、オプションの選択が必要な場合に役立ちます。](./fragment-configuration-types-reference/images/04.png)

## テキスト設定

次の JSON 構成は、テキスト オプションを手動で入力する必要がある場合に実装できる入力テキスト フィールドを作成します。

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "name": "buttonText",
                    "label": "Button Text",
                    "description": "button-text",
                    "type": "text",
                    "typeOptions": {
                        "placeholder": "Placeholder"
                    },
                    "dataType": "string",
                    "defaultValue": "Go Somewhere"
                }
                ...
            ]
        }
    ]
}
```

![テキスト設定は、入力テキスト オプションが必要な場合に役立ちます。](./fragment-configuration-types-reference/images/05.png)
