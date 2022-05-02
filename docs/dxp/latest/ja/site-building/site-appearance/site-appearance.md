# サイトの外観

Liferayサイトのデザインや機能は、様々な補完的なメカニズムによって調整・カスタマイズすることができます。 この表は、一般的のタスクや目標を達成するために、Liferayのどの機能やツールを使用できるかをまとめ、対比させたものです。

| 機能                             | スタイルブック | マスターページテンプレート | テーマ |
| ------------------------------ |:-------:|:-------------:|:---:|
| 一般的なフラグメント/ウィジェットを埋め込む         |         |       ✓       |  ✓  |
| ページの一般的な通レイアウトの定義              |         |       ✓       |  ✓  |
| UIで管理                          |    ✓    |       ✓       |     |
| スタイル、スペーシング、カラーなどのカスタマイズが可能です。 |    ✓    |               |  ✓  |
| 機能の追加                          |         |               |  ✓  |

<a name="master-page-templates" />

## マスターページテンプレート

[マスターページテンプレート](../creating-pages/defining-headers-and-footers/master-page-templates.md) では、フラグメントを使ってページの共通要素、特にヘッダーやフッターを定義することができます。 これらを利用して、本サイトの任意の数のページをデザインしたルック＆フィールに適合させることができます。

![マスターページテンプレートは、ヘッダーやフッターなど、すべてのページに共通する要素を定義する簡単な方法です。](./site-appearance/images/01.png)

<a name="style-books" />

## スタイルブック

[スタイルブック](./style-books/using-a-style-book-to-standardize-site-appearance.md) は、標準的な色や間隔など、サイトに適用される視覚的な標準のセットです。 これにより、各ページのレイアウトを独立してデザインしながら、各ページ間の一貫した操作性を維持することができます。

![スタイルブックは、テキストの色など、サイトの外観に関するルールを定義するものです。](./site-appearance/images/02.png)

<!--
TODO:

### Style Book Token Definitions

Note how they tie into themes as well (depending on them for definitions), possibly? And then maybe link to developer guide articles? (Unless maybe developer guide material is not good to go into for this overview... then perhaps it'd be better to just briefly reference their reliance on the theme and leave it as that, not even with an H3)
-->

<a name="widgetfragment-appearance" />

## ウィジェット/フラグメントの外観

フラグメントとウィジェットは、サイトの構成要素です。 サイトの外観に合わせて、様々なカスタマイズが可能です。

* [フラグメントのカスタマイズ](../developer-guide/developing-page-fragments/developing-fragments-intro.md) は、サイトのフラグメントページのルック＆フィールを調整するために使用されます。
* アプリケーション表示テンプレート <!--利用可能な場合はリンクを追加--> は、サイト上のウィジェットの外観や動作をカスタマイズすることができます。

<a name="themes" />

## テーマ

[テーマ](./themes/introduction-to-themes.md) は、サイトのデフォルトのルック＆フィールをカスタマイズするための非常に柔軟なツールです。 これらは、 [スタイルブック](#style-books) や [ページテンプレート](../creating-pages/adding-pages/creating-a-page-template.md) がその上に構築される基盤となります。 また、テーマ自体にウィジェットを埋め込むことで、こだわりのアレンジが可能です。

![サイトのページのテーマを選択することで、サイトのルック＆フィールを大きく変えることができます。](./site-appearance/images/03.png)
