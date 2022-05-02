# 表示ページテンプレートと表示ページについて

> 対応可能：Liferay DXP/Portal 7.3以降

表示ページは、表示ページテンプレートと組み合わせて、あらかじめ定義されたレイアウトでコンテンツを表示します。 [コンテントページ](../../creating-pages/using-content-pages.md)と同様に、表示ページのコンテンツは [フラグメント](../../creating-pages/page-fragments-and-widgets/using-fragments.md) と [ウィジェット](../../creating-pages/using-content-pages/using-widgets-on-a-content-page.md) で整理し、表示ページテンプレートを使用してコンテンツの表示方法を編成することになります。 単一の表示ページテンプレートを使用して、同じタイプの異なるコンテンツに同じレイアウトを複数回適用できます。

```{tip}
コンテンツページでは、ページレイアウトや構成を作成することはできますが、テンプレートとして保存して再利用することはできません。
```

例えば、Webコンテンツの記事ごとに表示ページテンプレートを作成し、それぞれ同じレイアウトで独自のフレンドリURLを持つ異なる表示ページにコンテンツを表示することができます。 さらに、 [SEO設定](./configuring-seo-and-open-graph.md) を定義することで、ページの検索順位を向上させることも可能です。 表示ページは、ビジネスケーススタディ、製品・サービスオプション、アパート・マンション、求人情報など、さまざまな要素のカタログを一貫した方法で表示する場合に最適です。

```{note}
Liferay DXP でコンテンツを表示するさまざまな方法についての詳細は、 [コンテンツの表示](../displaying-content-intro.md) を参照してください。
```

## 表示ページテンプレートと表示ページについて

表示ページテンプレートは、表示ページにコンテンツを表示およびマップする方法を決定します。 表示ページテンプレートの作成は、[コンテントページに要素を追加する方法](../../creating-pages/using-content-pages/adding-elements-to-content-pages.md)と似ています。 ただし、表示ページは常に特定のタイプのコンテンツに関連付けられていますが、コンテンツページは関連付けられていません。

表示ページを作成するには、まず表示ページ用テンプレートを作成し、表示ページのユニークなURLを用いてコンテンツを表示させます。

![表示ページテンプレートを使用して、表示ページにコンテンツを表示および整理します。](./about-display-page-templates-and-display-pages/images/03.png)

表示ページテンプレートを作成する際に、関連付けるコンテンツの種類を以下の中から選択します。

- [Webコンテンツの記事](../../../content-authoring-and-management/web-content/web-content-articles/adding-a-basic-web-content-article.md)
- [ドキュメント](../../../content-authoring-and-management/documents-and-media/publishing-and-sharing/publishing-documents.md)
- [カテゴリ](../../../content-authoring-and-management/tags-and-categories/defining-categories-and-vocabularies-for-content.md) (Liferay DXP 7.4 以降)
- [ブログエントリー](../../../content-authoring-and-management/blogs/getting-started-with-blogs.md)

表示ページテンプレートを作成および管理するときは、次の項目を考慮してください。

- 表示ページテンプレートは、同じコンテンツタイプに対して複数作成することができますが、デフォルトでは1つしか設定できません。
- デフォルトの表示ページテンプレートまたは特定の表示ページテンプレートを使用してコンテンツを公開できます（詳細については、 [表示ページによるコンテンツの公開](./publishing-content-with-display-pages.md) を参照してください）。
- 表示ページでは、ユニークな [フレンドリURL](../../site-settings/managing-site-urls/configuring-your-sites-friendly-url.md)を使用してコンテンツをレンダリングします。
- 表示ページのレイアウトとマッピングを更新するには、［サイト管理］ &rarr; ［**デザイン**］ &rarr; ［**ページテンプレート**］ で表示ページテンプレートを更新してください。

    ![表示ページの設定は、ページテンプレートアプリケーションの下にあります。](./about-display-page-templates-and-display-pages/images/04.png)

## 表示ページテンプレートの使用と表示ページ例

この例では、表示ページテンプレートと表示ページをサイトに実装する方法について、よりよく理解することができます。

あなたは保険ビジネスのウェブサイトに、見込み客にさまざまな保険の選択肢を紹介したいと思っています。 各オプションを説明するウェブコンテンツの記事がすでにあります。 まず、訪問者がすべてのオプションを一目で確認できるランディングページを作成します。 4つのオプションのいずれかをクリックすると、対応する保険の詳細が記載された新しいページが表示されます。

![単一の表示ページテンプレートを使用して、同じタイプの異なるコンテンツに同じレイアウトを適用できます。](./about-display-page-templates-and-display-pages/images/02.png)

```{tip}
[コンテンツページ](../../creating-pages/using-content-pages.md)は、魅力的なランディングページを作成するためのツールを提供します。
```

異なる保険オプションを説明する4つの詳細ページでそれぞれ同じレイアウトを使用し、各フィールドをWebコンテンツの記事の保険情報に動的にマッピングしたいと思っています。

これをするには、 **Insurance Details** という1つの表示ページテンプレートを作成し、Webコンテンツ記事のフィールドをテンプレート構成内のフラグメントに対応させます。 次に、この新しい表示ページテンプレートを、ウェブコンテンツの記事プロパティを使用して、ウェブコンテンツに定義します。 このWebコンテンツをフレンドリURLで表示する場合、表示ページには表示ページテンプレートのレイアウトとマッピングが使用されます。

![Webコンテンツ記事に表示ページテンプレートを構成します。](./about-display-page-templates-and-display-pages/images/01.png)

## 関連情報

- [表示ページテンプレートの作成と管理](./creating-and-managing-display-page-templates.md)
- [表示ページによるコンテンツの公開](./publishing-content-with-display-pages.md)
- [コンテンツの表示](../displaying-content-intro.md)
