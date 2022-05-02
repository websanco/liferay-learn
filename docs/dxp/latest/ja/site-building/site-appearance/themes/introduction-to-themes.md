# テーマの概要

テーマで、サイトのデフォルトのルックアンドフィールをカスタマイズします。 サイト全体でブランドやラベルのスタイルやビジュアルアイデンティティを定義できます。

## テーマとサイトデザイン

テーマは、作成する任意のサイトですぐに使用できます。 サイトのルックアンドフィールを定義する方法は他にもいくつかありますが（[スタイルブック](../style-books/using-a-style-book-to-standardize-site-appearance.md)など）、完全にカスタマイズされたソリューションが必要な場合は、テーマが最も柔軟性があります。

![サイトデザイン階層](./introduction-to-themes/images/01.png)

テーマは、サイトの外観をデザインする方法の階層において、カスタマイズを追加するための最も広範で柔軟な方法の1つです。 テーマは、より具体的なツール（[マスターページ](../../creating-pages/defining-headers-and-footers/master-page-templates.md)や[スタイルブック](../style-books/using-a-style-book-to-standardize-site-appearance.md)など）をその上に構築できる、サイトの最も基本的な基盤を提供するために使用されます。 ただし、テーマを使用して、他の方法では不可能なカスタマイズを追加することもできます。

```{warning}
テーマを介してサイトにカスタマイズを追加すると、Liferayのあるバージョンから別のバージョンにアップグレードする際のメンテナンスが増える可能性があります。 可能であれば、[マスターページテンプレート](../../creating-pages/defining-headers-and-footers/master-page-templates.md)と[スタイルブック](../style-books/using-a-style-book-to-standardize-site-appearance.md)を使用してサイトの外観を管理してください。
```

## テーマの開発

テーマを使用して、ページのスタイル設定、テンプレートの作成、ランタイム時の機能の拡張など、さまざまな方法でサイトをカスタマイズできます。

### サイトのスタイリング

テーマは、サイトのスタイリングの基礎となるものです。 サイトのページのUIの詳細、カスタマイズされたポートレットデコレータを定義したり<!--リンクがあれば追加してください-->、サイトの配色を定義<!--リンクがあれば追加してください-->したりできます。

テーマに基づいてカスタムの[スタイルブックトークン定義](../style-books/developer-guide/style-book-token-definitions.md)を構成することもできます。 <!--Add link when available: See Hooking Style Book Tokens into Your Theme for more information.-->

### ページのデザイン

テーマは、サイトのページのテンプレートをデザインするための[ページテンプレート](../../creating-pages/adding-pages/creating-a-page-template.md)または[マスターページテンプレート](../../creating-pages/defining-headers-and-footers/master-page-templates.md)を補完するものでもあります。 これらの他の方法と組み合わせて使用し、テーマのデザインに追加することができます。

たとえば、[ウィジェットをテーマに埋め込んで](./theme-development/working-with-templates/embedding-widgets-via-templates.md)、サイトのページの永続的なフィクスチャにすることができます。

```{note}
[マスターページ](../../creating-pages/defining-headers-and-footers/master-page-templates.md)または[ページテンプレート](../../creating-pages/adding-pages/creating-a-page-template.md)を使用して、ページのテンプレートを作成することもできます。
```

### リソースのバンドル

リソースをテーマまたは他のソースからバンドルして、一度に多くのコードソースをまとめることができます。 これにより、さまざまな変更をどのようにデプロイするかに応じて戦略を適応させることができます。

* [テーマレット](./theme-development/bundling-resources/bundling-and-installing-resources-into-your-theme-via-themelets.md)を使用すると、多くのテーマに適用できるモジュール方式で、少数のファイルをカスタマイズできます。

* テーマコントリビューター<!--リンクがあれば追加してください-->を使用すると、テーマとは独立してデプロイできるUIリソースとカスタマイズをパッケージ化できます。

* リソースインポーター<!--リンクがあれば追加してください-->は、サイトテンプレートを使用して、テーマの実装に必要なファイルとコンテンツをインポートします。

```{warning}
リソースインポーターは、Liferay DXP 7.1で廃止予定になりました。
```

### 動作の変更

テーマは、サイトのさまざまな部分の動作を変更するための強力なツールでもあります。

* [ページテンプレート](../../creating-pages/adding-pages/creating-a-page-template.md)で使用可能なコンテキスト変数を追加して、これらのテンプレートがテーマの機能にフックできるようにすることができます。

* テーマに変数値を設定して<!--リンクがあれば追加してください-->、DXPの既存の機能の動作に影響を与えることができます。

* [スタイルブック](../style-books/using-a-style-book-to-standardize-site-appearance.md)でスタイリングするためのカテゴリを定義するために使用される[トークン定義](../style-books/developer-guide/style-book-token-definitions.md)を追加できます。

## テーマのアップグレード

テーマをアップグレードするための最初のステップは、Liferayテーマジェネレーターを使用してアップグレードタスクを実行することです。 このタスクは、テーマのバージョンを更新して、新しいバージョンのDXPにデプロイできるようにします。 詳細については、 [テーマのアップグレード](./upgrading-a-theme.md) を参照してください。

<!-- If and when at least one more article is made for the extra work for upgrades, maybe add:

    Using the Liferay Theme Generator allows your theme to be deployed, but more work may be required to accommodate other changes in the new version, such as UI or functionality changes. See this article for more information... -->

## ツール

テーマの開発または拡張には、さまざまなツールが使用されます。 詳細については、次のトピックを参照してください。

* Liferayテーマジェネレーター<!--Add link when available-->
* [Blade](../../../building-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)
* [開発者ツールの概要](../../../building-applications/tooling/developer-tools-overview.md)
<!-- this may just be a single link to all the tooling section. This section is the result of https://issues.liferay.com/browse/IFI-2289 -->
