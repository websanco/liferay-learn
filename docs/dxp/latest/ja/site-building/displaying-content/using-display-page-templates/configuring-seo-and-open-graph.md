# SEOとOpen Graphの設定

> 対応可能：DXP/Portal 7.3以降

表示ページテンプレートは、個別のコンテンツを専用のURLで表示するためのカスタムレイアウトです。 これらのレイアウトは、フラグメントとウィジェットの両方を使用して構築され、Webコンテンツの記事、ドキュメント、ブログエントリーなどのために作成することができます。 各テンプレートには、独自のSEOとOpen Graphの設定があり、手動および動的に入力することができます。

以下の手順で、表示ページテンプレートのSEOとOpen Graphの設定を行います。

1. **サイトメニュー**(![Site Menu](../../../images/icon-product-menu.png)) を開き、 ［**Design**］ &rarr; ［**Page Templates**］ 、 ［**Display Page Templates**］ タブをクリックします。

   ![ [表示ページテンプレート]タブに移動します。](./configuring-seo-and-open-graph/images/01.png)

1. 目的のテンプレートの **アクション** ボタン (![Actions Button](./../../../images/icon-actions.png))をクリックし、 ［**構成**］ を選択します。

   また、テンプレートの編集時に、 **設定** ボタン (![Configure Button](../../../images/icon-cog.png)) をクリックすることで、これらの設定にアクセスすることもできます。

1. [［SEO］](#seo-settings-reference) または [［Open Graph］](#open-graph-settings-reference) タブに移動し、目的の設定を入力してください。

   ![[SEO]タブと [Open Graph]タブに目的の構成を入力します。](./configuring-seo-and-open-graph/images/02.png)

1. ［**保存**］ をクリックします。

   ```{important}
   ［SEO］タブと［Open Graph］タブを行き来すると、保存されていない変更は失われます。
   ```

SEOとOpen Graphの設定を行う際、値を直接入力したり、フィールドマッピングを使用して動的に値を追加したりすることが可能です。 使用可能なマッピングオプションは、テンプレートのコンテンツタイプとサブタイプによって異なります。 フィールドをマッピングするには、 ［**フィールドセレクタ**］ ボタン (![Mapping Button](../../../images/icon-map.png)) をクリックし、利用できるフィールドを選択するだけです。 Liferay DXP 7.4 U1およびPortal 7.4 GA5では、この方法で追加されたフィールドは `${}` のプレースホルダーとして表現され、プレースホルダーはフィールド参照IDとフィールドラベルの2つの部分から成ります(`${fieldReferenceID:Field Label}`)。

```{note}
以前のバージョンでは、マッピングプレースホルダーはフィールド参照IDのみを含んでいました（例：`${title}`、`${authorName}`、`${Text84981642}`）。
```

カスタムWebコンテンツとドキュメント構造におけるフィールド参照IDは人間が読めるものではないため、Liferayは、管理側で明確にするためにフィールドラベル値を提供します（例： `${Text84981642:Contributors}`）。 このラベルはエンドユーザーには表示されず、マッピングにも影響しないため、ユーザーが自由に編集することができます。 マッピングは、プレースホルダーのフィールド参照IDにのみ依存します。

```{note}
Liferay は可能な限り、マッピングされたフィールドの翻訳を提供します。 ただし、マッピングされたフィールド以外で提供されたテキストは翻訳されません。
```

<a name="seo-settings-reference" />

## SEO設定の参考

検索エンジン最適化 (SEO) とは、検索エンジン結果ページ (SERP) でのページのランキングを改善するために使用される方法を指します。 表示ページテンプレートを使用すると、コンテンツの表示ページをSEO用に設定することができます。

<a name="html-title" />

### HTMLタイトル

[**HTML タイトル**] フィールドは、表示ページの `<title>` タグを定義します。 このタイトルは、検索エンジンによってページのランク付けに使用され、検索エンジンの結果でページの見出しとして機能します。 デフォルトでは、表示ページテンプレートはこのフィールドを `${title}`にマップしています。 必要に応じて、この値を直接編集し、フィールドセレクタを使用して追加のマッピングを含めることができます（例： `${title} ${authorName:Author Name}`）。

**HTMLタイトル** の推奨される長さは 60 文字未満です。

```{tip}
SEOとOpenGraphのタイトルを一緒に更新することをお勧めします。
```

![マッピングされた複数のフィールドを組み合わせて、独自のHTMLタイトルを構築することができます。](./configuring-seo-and-open-graph/images/03.png)

<a name="description" />

### 説明

［**Description**］ フィールドは、表示ページの説明 `<meta>` タグを定義します。 この説明は、ページをランク付けするために検索エンジンによって使用され、ページのプレビューとして検索エンジンの結果に表示されます。 デフォルトでは、表示ページテンプレートはこのフィールドを `${description}`にマップしています。 必要に応じて、この値を直接編集し、フィールドセレクタを使用して追加のマッピングを含めることができます（例： `${description} ${authorName:Author Name}`）。

ページの説明の推奨される長さは 155 文字未満です。

```{tip}
SEOとOpenGraphの説明を一緒に更新することをお勧めします。
```

![複数のマッピングされたフィールドを組み合わせて、カスタム HTML の説明を作成します。](./configuring-seo-and-open-graph/images/04.png)

<a name="robots" />

### ロボット

［**Robots**］ フィールドは、表示ページの `robots.txt` ルールを構成します。 これらのルールは、サイトをクロールしてインデックスを作成する検索エンジンやその他のツールに指示を与え、クロールするパスとクロールしないパスを定義します。 Webクローラーは、`robots.txt` の指示に従う場合もあれば、従わない場合もあることに注意してください。

**言語フラグ** を使用して、このフィールドをローカライズすることもできます。

![ [ロボット] フィールドは、表示ページの robots.txt ルールを構成します。](./configuring-seo-and-open-graph/images/05.png)

<a name="sitemap" />

### サイトマップ

表示ページを `sitemap.xml` ファイルに含めるかどうかを決定し、 ［**Page Priority**］ と ［**Change Frequency**］ を設定します。 これらのフィールドは、表示ページをクロールしてインデックスを作成するかどうか、他のサイト ページと比較してそのページにどのように優先順位を付けるか、および更新頻度を検索エンジンに通知します。

![sitemap.xml ファイルに表示ページを含めるかどうかを決定し、その重要度と定期更新を設定します。](./configuring-seo-and-open-graph/images/06.png)

<a name="open-graph-settings-reference" />

## Open Graph 設定の参考

> 対応可能：Liferay DXP/Portal 7.3以降

[Open Graph](https://ogp.me/) は、Facebook、Slack、Twitterなどのアプリケーションコンテキストで共有する際に、サイトコンテンツのプレビューを標準化するインターネットプロトコルです。 これは、 `<meta>` タグとしてページヘッダーに構造化データを埋め込むことで、 [RDFa](https://en.wikipedia.org/wiki/RDFa) と同じように実現します。

Liferay DXP を使用すると、ページの Open Graph `<meta>` タグを動的に構成する表示ページ テンプレートを作成できます。 ここで定義された値は、[サイト](../../site-settings/configuring-open-graph.md)レベルで定義されたデフォルト値をオーバーライドします。

```{note}
Open Graph [](meta) タグは、認証されていないユーザーへの公開ページヘッダーにのみ含まれます。 非公開ページや、ユーザーがログインしているときの公開ページには含まれません。
```

<a name="title" />

### タイトル

［**タイトル**］ フィールドは、表示ページの `og:title` プロパティを定義します。これは、リッチプレビューでコンテンツに表示されるタイトルを定義します。 デフォルトでは、このフィールドは `${title}`にマッピングされます。 必要に応じて、この値を直接編集し、フィールドセレクタを使用して追加のマッピングを含めることができます（例： `${title} ${authorName:Author Name}`）。

```{tip}
SEOとOpenGraphのタイトルを一緒に更新することをお勧めします。
```

![複数のマッピングされたフィールドを組み合わせて、カスタムの og:title 値を作成します。](./configuring-seo-and-open-graph/images/07.png)

<a name="description-1" />

### 説明

［**Description**］ フィールドは、表示ページの `og:description` プロパティを定義します。これは、リッチ プレビューでコンテンツに表示される説明を決定します。 デフォルトでは、このフィールドは `${description}`にマッピングされます。 必要に応じて、この値を編集し、フィールドセレクタを使用して追加のマッピングを含めることができます（例： `${description} ${authorName:Author Name}`）。

```{tip}
SEOとOpenGraphの説明を一緒に更新することをお勧めします。
```

![複数のマッピングされたフィールドを組み合わせて、カスタムの og:description 値を作成します。](./configuring-seo-and-open-graph/images/08.png)

<a name="image" />

### 画像

［**Image**］ フィールドは、表示ページの `og:image` プロパティを定義します。これは、リッチ プレビューでコンテンツに表示される画像を構成します。 基本的な画像タグに加えて、DXP は、選択した画像の表示方法を決定する多くの構造化プロパティを自動的に追加します。 ［画像］フィールドは、次の `<meta>` タグを定義します。

```html
<meta property="og:image" content="http://example.com/ogp.jpg" />
<meta property="og:image:secure_url" content="https://secure.example.com/ogp.jpg" />
<meta property="og:image:type" content="image/jpeg" />
<meta property="og:image:width" content="400" />
<meta property="og:image:height" content="300" />
```

デフォルトでは、このフィールドは表示ページ テンプレートにマップされていません。 つまり、別の画像フィールドを選択しない限り、テンプレートはデフォルトでサイト レベルで設定された画像になります。

![ [画像] フィールドは、表示ページの og:image プロパティを定義します。](./configuring-seo-and-open-graph/images/09.png)

<a name="image-alt-description" />

### 画像の別の説明

［**Image Alt Description**］ フィールドは、表示ページの `og:image:alt` プロパティを定義します。これは、表示されたコンテンツの `og:image` プロパティに対してスクリーン リーダーが読み取る代替テキストを決定します。

```html
<meta property="og:image:alt" content="This is an example." />
```

デフォルトでは、このフィールドは表示ページ テンプレートにマップされていません。 つまり、別のテキスト フィールドを選択しない限り、テンプレートはデフォルトでサイト レベルで設定された代替テキストになります。

![ [画像の別の説明] フィールドは、表示ページの og:image:alt プロパティを定義します](./configuring-seo-and-open-graph/images/10.png)

<a name="additional-information" />

## 追加情報

- [ページのフレンドリURLの設定](../../creating-pages/page-settings/configuring-your-pages-friendly-url.md)
- [Open Graphの設定](./../../site-settings/configuring-open-graph.md)
