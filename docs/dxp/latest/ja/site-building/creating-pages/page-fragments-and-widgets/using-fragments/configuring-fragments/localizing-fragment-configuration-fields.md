# フラグメント設定フィールドのローカライズ

> 対応可能：Liferay DXP/Portal 7.4以降

Liferayフラグメントを使用する場合、ターゲットオーディエンスの言語に一致するように次のフラグメント設定フィールドをローカライズできます。

* [画像の説明（*alt text*)](#localizing-alternative-text-for-images)
* [編集可能なURL](#localizing-editable-urls)<!-- * \[Collection Filter Label Text\](#localizing-collection-filter-label-text) -->フラグメントの開発中に、`localizable`属性を使用して、フラグメントの構成フィールドをローカライズ可能にすることができます。 詳しくは、 [Fragment Specific Tags and Attributes Reference](../../../../developer-guide/reference/fragments/fragment-specific-tags-reference.md)をお読みください。

## 画像の説明のローカライズ

組み込みフラグメントおよびカスタムフラグメントの画像の説明（*代替テキスト*または*alt text*とも呼ばれます）のローカライズをさまざまな言語で追加できます。 エディタツールバー(A)の言語セレクタを使ってコンテントページのターゲット言語を変更すると、画像の説明を編集して、ターゲット言語用のテキストを適応させることができます（B）。

![コンテントページエディタで画像の説明をローカライズすることができます。](./localizing-fragment-configuration-fields/images/01.png)

```{tip}
ローカライズ可能なフィールドには、その画像の説明の横にフラグのアイコンが表示されます。 
```

画像の説明文に翻訳がない場合、説明文にはデフォルトのコンテントページの言語が表示されます。

## 編集可能なURLのローカライズ

コンテントページ内のURLやリンクをローカライズすると、ターゲット言語に応じて、異なるURLやコンテンツにリダイレクトすることができます。 エディターツールバー（A）の言語セレクターを使ってコンテントページのターゲット言語を変更すると、URLを任意のリンクに更新することができます（B）。

![コンテントページエディタでリンクをローカライズすることができます。](./localizing-fragment-configuration-fields/images/02.png)

手動で入力したリンクや、コンテンツ項目からのリンクをローカライズすることができます。 リンクに翻訳がない場合、アクティブなURLは、デフォルトのコンテントページの言語のものになります。<!--TASK: explain.
## Localizing Collection Filter Label Text -->## 追加情報

- [コンテントページの使用](../../../using-content-pages.md)
- [コンテントページエディタUIリファレンス](../../../using-content-pages/content-page-editor-ui-reference.md)
- [フラグメントの使用](../../using-fragments.md)
