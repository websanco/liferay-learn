# スタイルブックトークンの定義

スタイルブックには、テーマごとに定義されたさまざまなカテゴリにグループ化されたオプションがあります。 各オプションは*トークン*で定義されます。 1つのテーマに対して定義されたすべてのトークンのコレクションが*トークン定義*です。

サイトの公開ページにテーマを割り当てると、そのテーマに含まれているトークン定義が、サイトのスタイルブックを作成するときに使用されます。

## スタイルブックのトークン定義

トークン定義はテーマに関連付けられているため、トークン定義はテーマモジュールに含まれているCSS変数に対応している必要があります。 トークン定義自体は、テーマモジュールのフォルダ内にある`.json`ファイルに、 `frontend-token-definition.json`という名前で指定します。

### トークンカテゴリ

スタイルブックを構成するためのオプションを定義するトークンは、カテゴリにグループ化されます。 スタイルブックを編集しているときに、各カテゴリがドロップダウンメニューのオプションの1つとして表示されます。

![ドロップダウンメニューの各オプションは、スタイルブックのトークンの1つのカテゴリに対応しています。](../style-books/developer-guide/style-book-token-definitions/images/01.png)

`frontend-token-definition.json`ファイル内の`frontendTokenCategories`フィールド内でこれらの各カテゴリを定義します。

``` json
{
    "frontendTokenCategories": [
        {
            "frontendTokenSets": [],
            "label": "buttons",
            "name": "buttons"
        }
    ]
}
```

トークンのカテゴリごとに、`label`と`name`を定義します。 `label`値は言語キーとして解釈され、カテゴリのドロップダウンメニューのオプションとして表示されます。 <!-- Add link to article explaining localization when it is available. -->

### トークンセット

各カテゴリはさらにトークンセットに整理されます。 トークンセットは、スタイルブックを編集しているときに表示される折りたたみ可能なオプションのグループに対応しています。

たとえば、デフォルトの標準テーマを使用すると、*ボタンプライマリ*トークンセット（*ボタン*カテゴリ内）には、標準のボタンの色オプションのすべてのトークンが含まれています。

![ボタンプライマリトークンセットには、標準テーマにおけるメインボタンのカスタマイズ可能な色がすべて含まれています。](../style-books/developer-guide/style-book-token-definitions/images/02.png)

カテゴリの`frontendTokenSets`フィールド内に各トークンセットを定義します。

``` json
{
    "frontendTokenCategories": [
        {
            "frontendTokenSets": [
                {
                    "frontendTokens": [],
                    "label": "primary-buttons",
                    "name": "primaryButtons"
                }
            ],
            "label": "buttons",
            "name": "buttons"
        }
    ]
}
```

各カテゴリと同様に、トークンセットごとに`label`と`name`を定義します。

### トークンの定義

最後に、各トークンセットのすべてのトークンには、各オプションを構成するためのプロパティが含まれています。

各トークンセットの`frontendTokens`フィールド内のすべてのトークンを定義します。 トークンに使用できるすべてのプロパティのリストは次のとおりです。

`defaultValue`：オプションに表示されるデフォルト値。 このフィールドは、CSSで使用されるデフォルト値と一致する必要があります。

`editorType`：フィールドにカラーピッカーエディターを使用する場合は、このフィールドを使用します。 サポートされている値は`"ColorPicker"`のみです。 値が設定されていない場合は、テキスト入力が使用されます。 選択入力が必要な場合は、代わりに`validValues`プロパティを使用してください（2つのプロパティを一緒に使用することはできません）。

`mappings`：トークン定義名とそれに対応するCSS変数名とのマッピング（ネストされたフィールドとして`type`と`value`を含む必要があります）。 `"cssVariable"`を `type`として使用し、`value`をCSS変数名として定義します。

`label`：スタイルブックを編集するときにオプションに表示される言語キー。

`name`：トークンの名前。

`type`：トークンが表示するデータのタイプ。 `"Integer"`、`"Float"`、または`"String"`を使用して、これらのタイプの値を保持するテキストフィールドを表示します。 チェックボックスを表示するには、`"Boolean"`を使用します。

`validValues`：UIでユーザーが使用できるオプションを一覧表示するオプションのプロパティ。 このフィールドには、`label`と`value`のペアのネストされたリストが含まれている必要があります（`value`は、CSSでのフィールドの値です）。 このプロパティは、`editorType`と一緒に使用することはできません。 `validValues`に値を定義すると、入力タイプが自動的に選択入力になります。

トークンセット内のトークンの一覧の例を次に示します。

``` json
"frontendTokens": [
    {
        "defaultValue": "#0B5FFF",
        "editorType": "ColorPicker",
        "label": "primary",
        "mappings": [
            {
                "type": "cssVariable",
                "value": "primary"
            }
        ],
        "name": "primaryColor",
        "type": "String"
    },
    {
        "defaultValue": "sans-serif",
        "label": "font-family",
        "mappings": [
            {
                "type": "cssVariable",
                "value": "fontFamily"
            }
        ],
        "name": "fontFamily",
        "type": "String",
        "validValues": [
            {
                "label": "sans-serif",
                "value": "sans-serif"
            },
            {
                "label": "monospace",
                "value": "Courier New"
            }
        ]
    }
]
```

## CSS変数をスタイルブックトークンに一致させる

トークンの定義を含む`frontend-token-definition.json`ファイルは、テーマモジュールフォルダのルートレベルになければなりません。 トークン定義で定義されたすべてのトークンは、CSSでテーマのスタイル（色、間隔、フォントなど）を表している必要があります。

トークンが表すすべてのスタイルは、CSS変数としてコーディングする必要があります。 たとえば、次のトークンの定義（フォントを設定するオプションを提供）を見てみましょう。

``` json
{
    "defaultValue": "sans-serif",
    "label": "font-family-base",
    "mappings": [
        {
            "type": "cssVariable",
            "value": "font-family-base"
        }
    ],
    "name": "fontFamilyBase",
    "type": "String"
}
```

このトークンは、次のようにCSSでスタイルを表すことができます。

``` css
:root {
    --font-family-base: 'sans-serif'
}

body {
    font-family: var(--font-family-base);
}
```

トークン定義の`mappings`（`font-family-base`）の値は、CSSの変数名と一致します。 CSS変数名の前に2つのハイフンを使用します（この例では、`--font-family-base`）。

```{important}
If a value for `defaultValue` is included in your token definition, then this must match the default value defined in the matching CSS variable definition.
```

## 追加情報

  - [Using a Style Book to Standardize Site Appearance](../using-a-style-book-to-standardize-site-appearance.md)

<!-- Add link to token definition tutorial when available -->
