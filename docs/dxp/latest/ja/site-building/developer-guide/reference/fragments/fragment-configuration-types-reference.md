# フラグメント設定タイプのリファレンス

このリファレンスには、フラグメントに使用可能な構成タイプがリストされています。 フラグメントを構成可能にする方法の詳細は、 [フラグメントへの構成オプションの追加](../../developing-page-fragments/adding-configuration-options-to-fragments.md) を参照してください。

以下は実装できる 5 つの構成可能なフラグメントタイプです。

- `checkbox`
- `colorPalette`
- `itemSelector` (Liferay DXP 7.3+ で使用可能)
- `select`
- `text`
- `videoSelector` (Liferay DXP 7.4+ で使用可能+)
- `collectionSelector` (Liferay DXP 7.3+ で使用可能)

```{note}
FreeMarker コンテキストに挿入された設定値は、JSON ファイルで指定された定義済みの `datatype` 値に従います。 たとえば、`dataType`が String の場合、`configuration.[name-value]?is_string` は `true`となります。
```

## チェックボックスの設定

次の JSON 構成は、ブール値の選択が必要な場合に実装できるチェックボックスを作成します。

```json
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

```json
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

```html
<h3 class="text-${configuration.textColor.cssClass}">Example</h3>
```

白を選択した場合、 `h3` タグの見出しはクラス `text-white'` を持ちます。

![カラー パレットの設定は、色の選択が必要な場合に役立ちます。](./fragment-configuration-types-reference/images/02.png)

## アイテムセレクターの設定

次の構成は、フラグメントに含める既存のコンテンツ (デフォルトでは、Web コンテンツの記事、ブログエントリー、またはドキュメント) を 1 つ選択できるセレクターを作成します。

```json
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

```json
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

```json
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

```json
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

```markup
<div class="fragment_name">
  [#if configuration.itemSelector1.content??]
       ${configuration.itemSelector1.content}
  [/#if]
</div>
```

また、コンテンツの特定の部分にアクセスする必要がある場合には、`[name-of-field]Object` ( 下の例では `itemSelector1Object` ) というキーの下で、フラグメント内の Java オブジェクトにアクセスすることができます。 次の例は、Web コンテンツの記事のタイトル、説明、および本文をレンダリングします。

```markup
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

```json
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

```json
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

## ビデオセレクター

> 対応可能：Liferay DXP/Portal 7.4以降

`videoSelector` 型を使用すると、 [External Video](../../../creating-pages/page-fragments-and-widgets/using-fragments/default-fragments-reference.md) フラグメントを別のフラグメントに取り込むためのビデオセレクタを作成することができます。

```json
{
  "fieldSets": [
    {
      "fields": [
        {
          "label": "My Video Selector",
          "name": "myVideoConfig",
          "type": "videoSelector"
        }
      ]
    }
  ]
}
```

これは、デフォルトでビデオが埋め込まれているフラグメントが必要な場合に便利です。 以下のJSON設定サンプルは、カードフラグメントに外部ビデオセレクターを組み込む方法を示しています。

```json
{
"fieldSets": [
    {
        "fields": [
            {
                "label": "Video",
                "name": "video",
                "type": "videoSelector"
            }
        ]
    },
    {
        "configurationRole": "style",
        "fields": [
            {
                "dataType": "string",
                "defaultValue": "w-100",
                "label": "image-size",
                "name": "imageSize",
                "type": "select",
                "typeOptions": {
                    "validValues": [
                        {
                            "label": "fit",
                            "value": "w-100"
                        },
                        {
                            "label": "original-size",
                            "value": "w-0"
                        }
                    ]
                }
            }
        ]
    }
]
}
```

![ビデオセレクターで、フラグメントに外部ビデオを含めることができます。](./fragment-configuration-types-reference/images/06.png)

```{note}
`videoSelector`型は、 [外部ビデオ](../../../creating-pages/page-fragments-and-widgets/using-fragments/default-fragments-reference.md#external-video) フラグメントと互換性がありますが、[ビデオURL](../../../creating-pages/page-fragments-and-widgets/using-fragments/default-fragments-reference.md)フラグメントとは互換性がありません。
```

## コレクションセレクター

> 対応可能：Liferay DXP/Portal 7.3以降

`collectionSelector` の設定タイプを使用すると、 [Collection](../../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md) またはコレクションプロバイダーを含むフラグメントを開発することが可能です。 `collectionSelector` は、手動コレクションと動的コレクションの両方で使用することができます。

```{note}
コレクションプロバイダーは、開発者がより高度な基準で特定のコレクションを作成することを可能にします。 詳しくは、開発者向けドキュメント [Info Framework](https://help.liferay.com/hc/ja/articles/360029067251-Introduction-to-The-Info-Framework) の [Creating an Information List Provider](https://help.liferay.com/hc/ja/articles/360029067271-Creating-an-Information-List-Provider) をご覧ください。
```

次のJSON設定は、 `collectionSelector`を使用する方法を示しています。

```json
{ 
    "fieldSets": [   
        {            
        "label": "Collection",            
        "fields": [                
            {                    
                "name": "collection",                    
                "type": "collectionSelector"                
            }            
        ]        
        } 
    ]
}
```

このフラグメント設定を、以下のHTMLコードサンプルで使用することで、コレクション項目を一覧表示することができます。 `collectionObjectList`は[コンテントページエディタ](../../../creating-pages/using-content-pages/content-page-editor-ui-reference.md)で選択されたコレクションを表します。

このコレクションをHTMLから参照するには、JSONの設定でコレクションの`名前` と `ObjectList`サフィックスを使用します。 先のJSONコードの抜粋では、コレクションの`名前` は `コレクション` なので、HTMLでは `collectionObjectList`を使ってコレクションを参照しています。

```html
<div class="fragment_310">
 <h1>
  List of Items:
 </h1>
  <ul>
  [#if collectionObjectList??]
   [#list collectionObjectList as item]
    <li>${item.title}</li>
   [/#list]
  [/#if]
  </ul>
</div>
```

![コレクションの設定により、フラグメントをコレクションセレクターで開発することができます。](./fragment-configuration-types-reference/images/07.png)

また、 `collectionSelector` の設定で、 `itemType` を使ってコレクションセレクターをフィルタリングすることができます。 例えば、Webコンテンツとブログを含む異なるコレクションがある場合、コレクションセレクターを制限して、ブログコレクションのみを表示することができます。 このJSONサンプルは、この設定を説明するものです。

```json
{ 
 "fieldSets": [   
  {            
   "label": "Collection",            
   "fields": [                
    {                    
     "name": "collection",                    
     "type": "collectionSelector",
     "typeOptions": {
      "itemType": "com.liferay.blogs.model.BlogsEntry"
               }
    }            
   ]        
  } 
 ]
}
```

このサンプル構成では、Web コンテンツとブログの両方を含むコレクションは、コレクションタイプが アセット であるため、コレクションセレクターからフィルタリングされます。

![Webコンテンツやブログエントリーを含むコレクションは、アセットタイプに対応します。](./fragment-configuration-types-reference/images/08.png)

```{tip}
設定には `itemTime` の他に、`itemSubtype` を指定することができます。 `itemSubtype`はアセット`classPK`に対応します。
```

## 追加情報

- [フラグメントの開発](../../developing-page-fragments/developing-fragments-intro.md)
- [フラグメント固有のタグリファレンス](./fragment-specific-tags-reference.md)
- [ページ フラグメントエディタのインターフェイスリファレンス](./page-fragment-editor-interface-reference.md)
