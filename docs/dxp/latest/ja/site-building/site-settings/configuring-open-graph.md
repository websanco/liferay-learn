# Open Graphの設定

> 対応可能：Liferay DXP/Portal 7.3以降

[Open Graph](https://ogp.me/) は、Facebook、Slack、Twitterなど、サイトコンテンツをサポートしているアプリケーションコンテキストで共有する際に、サイトコンテンツのプレビューを標準化するインターネットプロトコルです。 これは、 `<meta>` タグとしてページヘッダーに構造化データを埋め込むことで、 [RDFa](https://en.wikipedia.org/wiki/RDFa) と同じように実現します。

デフォルトでは、Open Graph `<meta>`タグはすべてのLiferay公開ページに埋め込まれていますが、必要に応じてサイトレベルでこの動作を無効にすることができます。 サイト設定では、デフォルトの`og:image`プロパティを設定することもできます。 これらの値は、 [ページ](../creating-pages/page-settings/configuring-individual-pages.md#open-graph) または[表示ページ](../displaying-content/using-display-page-templates/configuring-seo-and-open-graph.md)レベルで上書きされない限り、すべてのページに使用されます。

```{note}
Open Graph [](meta) タグは、認証されていないユーザーへの公開ページヘッダーにのみ含まれます。 非公開ページや、ユーザーがログインしているときの公開ページには含まれません。
```

次の手順に従って、サイトにOpen Graphを構成します。

1. **サイトメニュー**（![Site Menu](../../images/icon-product-menu.png)）を開き、 ［**設定**］ &rarr; ［**Settings**］（以前は ［**サイト設定**］）に移動します。

1. ［**Open Graph**］ 小見出しまで下にスクロールして、展開します。

1. ［**Enable Open Graph**］ 設定をオンまたはオフにし、サイトのOpen Graphを有効または無効にします。

   ![サイトのページに埋め込まれたOpen Graphタグを有効または無効にします。](./configuring-open-graph/images/01.png)

1. （オプション）［**Image**］ フィールドと ［**画像の別の説明**］ フィールドを使用して、サイトのページのデフォルトの`og:image`プロパティを設定します。

   これらの値は、 [ページ](../creating-pages/page-settings/configuring-individual-pages.md#open-graph) および[表示ページ](../displaying-content/using-display-page-templates/configuring-seo-and-open-graph.md)の構成によって上書きされます。

   ![サイトで使用されるデフォルトのog:imageおよびog:image:altプロパティを定義します。](./configuring-open-graph/images/02.png)

1. デフォルトの`og:image`構成をプレビューします。

   画像の比率は、共有する場所によって変わる可能性があることに注意してください。

   ![デフォルトのOpen Graph設定をプレビューします。](./configuring-open-graph/images/03.png)

1. ［**保存**］ をクリックします。

<a name="analyzing-the-ogimage-properties" />

## `og:image`プロパティの分析

画像を選択すると、DXPは選択した画像の表示方法を決定する多くの構造化プロパティを自動的に追加します。 ［画像］ フィールドは、次の `<meta>` タグを定義します。

```html
<meta property="og:image" content="http://example.com/ogp.jpg" />
<meta property="og:image:secure_url" content="https://secure.example.com/ogp.jpg" />
<meta property="og:image:type" content="image/jpeg" />
<meta property="og:image:width" content="400" />
<meta property="og:image:height" content="300" />
```

［画像の別の説明］フィールドにテキストを入力すると、選択した画像のスクリーンリーダーで読み取られるデフォルトのテキストが決まります。

Open Graph画像のローカライズされた別の説明を作成するには、 ［**言語フラグ**］ ボタンをクリックして、設定する言語を選択します。 ここで入力された値によって、デフォルトの`og:image:alt`タグが定義されます。

```html
<meta property="og:image:alt" content="This is an example." />
```

<a name="additional-information" />

## 追加情報

* [検索エンジンを最適化](../../optimizing_sites.html)
* [サイト設定UIリファレンス](./site-settings-ui-reference.md)
* [ページ単位での設定](./../creating-pages/page-settings/configuring-individual-pages.md)
