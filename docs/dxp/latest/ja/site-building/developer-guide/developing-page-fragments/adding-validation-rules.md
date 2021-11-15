# 検証ルールの追加

フラグメントの[設定オプション](./adding-configuration-options-to-fragments.md)を定義する際に、`text` タイプのフィールドに有効なエントリーのルールを決める `validation` プロパティを追加することができます。 ルールのタイプ（`text`、`number`、`email`、`url`、または `pattern`）を指定することで、各フィールドが受け入れるデータのタイプを決定します。  また、カスタムのエラーメッセージを追加して、無効なエントリーをユーザーに通知することもできます。

## テキスト検証ルール

次のJSONコードは、 `text`タイプフィールドの有効なエントリー（つまり`minLength`および`maxLength`）で使用される最小および最大文字数を設定する`text`検証ルールを追加します。

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "label": "validate text length",
                    "name": "text1",
                    "type": "text",
                    "typeOptions": {
                      "validation": {
                        "errorMessage": "Enter a minimum of 10 characters. Entries cannot exceed 30 characters.",
                        "type": "text",
                        "minLength": 10,
                        "maxLength": 30
                      }
                    }
                }
            ]
        }
    ]
}
```

![テキスト検証ルールを使用して、有効なエントリーで使用される最小および最大の文字数を設定します。](./adding-validation-rules/images/01.png)

## 数値検証ルール

次のJSONコードは、 `text`タイプフィールドの有効なエントリーの数値範囲（つまり`min`および`max`）を定義する`number`検証ルールを追加します。

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "dataType": "int",
                    "label": "validate number",
                    "name": "text2",
                    "type": "text",
                    "typeOptions": {
                      "validation": {
                        "errorMessage": "Enter a number between 5 and 10.",
                        "type": "number",
                        "min": 5,
                        "max": 10
                      }
                    }
                }
            ]
        }
    ]
}
```

![数値検証ルールを使用して、有効なエントリーの数値範囲を定義します。](./adding-validation-rules/images/02.png)

## メール検証ルール

次のJSONコードは、メール構文を要求し、`text`タイプフィールドの有効なエントリーの文字長（つまり`minLength`および`maxLength`）を定義する`email`検証ルールを追加します。

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "label": "validate email",
                    "name": "text3",
                    "type": "text",
                    "typeOptions": {
                      "validation": {
                        "errorMessage": "Enter a valid email address.",
                        "type": "email",
                        "minLength": 1,
                        "maxLength": 30
                      }
                    }
                }
            ]
        }
    ]
}
```

![メール検証ルールを使用して、メールの構文を要求し、有効なエントリーの文字長を定義します。](./adding-validation-rules/images/03.png)

## URL検証ルール

次のJSONコードは、適切なURLプロトコルを要求し、`text`タイプフィールドの有効なURLの文字長（つまり`minLength`および`maxLength`）を定義する`url`検証ルールを追加します。

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "label": "validate url",
                    "name": "text4",
                    "type": "text",
                    "typeOptions": {
                      "validation": {
                        "errorMessage": "Enter a valid URL.",
                        "type": "url",
                        "minLength": 1,
                        "maxLength": 100
                      }
                    }
                }
            ]
        }
    ]
}
```

![URL検証ルールを使用して、適切なURLプロトコルを要求し、有効なURLの文字長を定義します。](./adding-validation-rules/images/04.png)

## パターン検証ルール

次のJSONコードは、正規表現を使用して`pattern`検証ルールを`text`タイプフィールドに追加して、有効なエントリーを定義します。

```{note}
正規表現値の文字をエスケープするためにバックスラッシュを使用する場合（例：`\d`）、最初にバックスラッシュをエスケープするために`JSON`ファイル内で2つのバックスラッシュを使用する必要があります (例：`\\d`)。 JSONの文法の詳細は、 [IETF](./https://www.ietf.org/rfc/rfc4627.txt) ドキュメンテーションを参照してください。
```

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "label": "validate pattern",
                    "name": "text5",
                    "type": "text",
                    "typeOptions": {
                      "validation": {
                        "errorMessage": "Enter a valid 10 digit phone number.",
                        "type": "pattern",
                        "regexp": "([0-9]{3})[.-]?([0-9]{3})[.-]?([0-9]{4})"
                      }
                    }
                }
            ]
        }
    ]
}
```

![正規表現を使ったパターン検証ルールを使用して、有効なエントリーを定義します。](./adding-validation-rules/images/05.png)

## 追加情報

  - [フラグメントの開発](./developing-fragments-intro.md)
  - [フラグメントエディターの使用](./using-the-fragments-editor.md)
  - [Adding Configuration Options to Fragments](./adding-configuration-options-to-fragments.md)
