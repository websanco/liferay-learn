# フラグメントの設定

```{toctree}
:maxdepth: 3

configuring-fragments/general-settings-reference.md
configuring-fragments/styles-reference.md
configuring-fragments/fragment-sub-elements-reference.md
configuring-fragments/localizing-fragment-configuration-fields.md
```

Liferay DXPは、フラグメントとそのサブ要素の構成オプションを提供します。 これらのオプションは異なる場合がありますが、一部はすべてに共通です。 使用可能な構成オプションを表示するには、ページまたはテンプレートの編集を開始し、サイドバーメニューの*ブラウザ*パネル（![Browser](../../../../images/icon-browser.png)）を開きます。 次に、フラグメントまたはサブ要素をクリックし、使用可能なタブから設定するオプションを選択します。

```{note}
一部のフラグメント設定フィールドはローカライズできます。 詳細については、[フラグメント設定フィールドのローカライズ](./configuring-fragments/localizing-fragment-configuration-fields.md)を参照してください。
```

## フラグメント設定オプション

使用可能なフラグメント構成オプションは、［一般］または［Styles］の2つのタブにグループ化されています。 ほぼすべてのフラグメントに両方のタブが含まれています。 ただし、いくつかの例外があります。

* HTML、段落、区切り文字、およびソーシャルには、一般設定はありません。
* コレクション表示にはスタイル設定がありません。

DXPのレスポンシブレイアウトエディターを使用すると、さまざまなビューポイントに対してこれらの設定を構成することもできます。

### 一般

［一般］タブには、標準の構成オプション（閲覧性やフレームなど）と、各フラグメントに固有のオプションの両方が含まれています。 詳しくは、 [一般設定リファレンス](./configuring-fragments/general-settings-reference.md) を参照してください。

### スタイル

［Styles］タブには、フラグメントのスタイルを構成するための標準オプションが含まれています。 フラグメントの寸法、背景、枠線などが含まれます。 詳しくは、[スタイルリファレンス](./configuring-fragments/styles-reference.md)を参照してください。

## フラグメントサブ要素設定

多くのフラグメントには、独自の構成オプションを持つサブ要素が含まれています。 使用可能なオプションは、サブ要素のタイプによって異なり、［マッピング］、［画像ソース］、および［リンク］のタブに編成されています。 詳しくは、 [フラグメントサブ要素リファレンス](./configuring-fragments/fragment-sub-elements-reference.md) を参照してください。

### マッピング

［マッピング］タブでは、目的のアイテム（Webコンテンツの記事、ドキュメント、ブログなど）を選択し、要素に表示するフィールド（タイトル、作成者、名前など）を指定することで、ページ要素を使用可能なアセットにマップできます。

### 画像ソース

［画像ソース］タブでは、要素の画像を選択し、その解像度を表示し、そのaltテキストを提供できます。

### リンク

［リンク］タブでは、ページ要素にURLを手動で追加するか、使用可能なコンテンツ項目から選択することができます。 目的のターゲット（例：自身、親）を指定することもできます。

::::{grid} 2 :gutter: 3 3 3 3

:::{grid-item-card} 一般設定リファレンス :link: ./configuring-fragments/general-settings-reference.md :::

:::{grid-item-card} スタイルリファレンス :link: ./configuring-fragments/styles-reference.md :::

:::{grid-item-card} フラグメントサブ要素リファレンス :link: ./configuring-fragments/fragment-sub-elements-reference.md :::

:::{grid-item-card} フラグメント設定フィールドのローカライズ :link: ./configuring-fragments/localizing-fragment-configuration-fields.md ::: ::::
