# ページについて

Liferay DXPのページは、主にサイトのユーザーにコンテンツとアプリケーションを表示するために使用されます。

## ページセット

ページをサイトに追加すると、ページはサイトの[ページセット](./05-configuring-page-sets.md)（**パブリックページ** または **プライベートページ**）の一部として作成されます。 パブリックページセットとプライベートページセットを以下で比較します。

| 動作                     | パブリックページ | プライベートページ |
| :--- | :--- | :--- |
| 認証されていないユーザーへの表示       | ✔        |           |
| 閲覧にはログインとサイトメンバーシップが必要 |          | ✔         |
| 独自のURLパターン             | ✔        | ✔         |

## ページタイプ

[ページを追加](./adding-a-page-to-a-site.md)するときに使用できるページタイプはいくつかあります。 デフォルトのページタイプは **コンテンツページ** です。 [ページテンプレート](./07-creating-a-page-template.md)に基づいてページを作成することもできます。 ページタイプとページテンプレートについては、以下で詳しく説明します。

![ページを追加するときは、ページタイプを選択する必要があります。](./understanding-pages/understanding-pages/images/01.png)

```{tip}
現在表示されているページのページタイプがページの上部に表示され、使用できる管理オプションや、ページを構成するために移動する場所などを把握できます。
```

### コンテンツページ

コンテンツページを使用すると、ユーザーは簡単に管理できるプロフェッショナルページを作成し、コンテンツをその場でインライン編集できます。 コンテンツページは、主に [ページフラグメント](../README.md#using-fragments) を使用して構築されていますが、ウィジェットも使用できます。

![コンテンツページは、複数のフラグメントで構成できます。](./understanding-pages/understanding-pages/images/04.png)

コンテンツページを使用すると、追加の強力な機能と利点にアクセスできます。

  - [パーソナライズされたアダプティブサイトエクスペリエンス](../personalizing-site-experience/README.rst)
  - [A/Bテスト](../optimizing-sites/02-ab-testing/README.rst)
  - 親しみやすいコンテンツとサイト構築のエクスペリエンス

コンテンツページの詳細については、[Content Page Overview](./content-pages-overview.md)を参照してください。 すぐにページを作成するには、[Building Content Pages](./building-content-pages.md)を参照してください。

### ウィジェットページ

<!-- Should there be an article that covers layouts and layout templates? -->

ウィジェットページは、 **ウィジェット**（アプリケーション）を表示できるようにレイアウトが設定されたページです。 ウィジェットは、コンテンツを表示したり、インタラクティブで動的な機能をページに追加したりできます。 Liferay Portalを初めて起動したとき、最初のホームページがウィジェットページです。 詳細については、[Adding Widgets to a Page](./adding-widgets-to-a-page.md)を参照してください。

![ウィジェットページは、専用のWikiページソリューションなど、いくつかの機能を提供できます。](./understanding-pages/understanding-pages/images/05.png)

### コンテンツページとウィジェットページの比較

次の表では、コンテンツページとウィジェットページの違いをいくつか比較しています。

| 特徴                    | コンテンツページ | ウィジェットページ |
| :--- | :--- | :--- |
| ウィジェットを追加する機能         | ✔        | ✔         |
| パーソナライズされたサイトエクスペリエンス | ✔        |           |
| A/Bテストへのアクセス          | ✔        |           |
| 簡単なレイアウトとコンテンツ編集      | ✔        |           |
| 高度なカスタムレイアウト          |          | ✔         |
| ユーザーがカスタマイズ可能な列       |          | ✔         |
| ステージングページのバリエーション     |          | ✔         |

### その他のページタイプ

使用できるページタイプは他にもいくつかあります。特定の使用例に合わせて使用できます。 詳細については、[Other Page Types](./other-page-types.md)を参照してください。

## グローバルページテンプレート

ページを追加するときに、ページテンプレートを選択することもできます。 ページテンプレートは、アプリケーションが既にページにデプロイされている事前設定されたページです。 デフォルトでは、 **グローバルテンプレート** のみが使用可能ですが、作成した追加のコレクションもオプションとして表示されます。

| グローバルページテンプレート | 説明                                                                                     |
| :--- | :--- |
| Blog           | [Blogs]ウィジェット、[Tag Cloud]ウィジェット、[Recent Bloggers]ウィジェットを含むウィジェットページを作成します。             |
| Search         | [Search Bar]ウィジェット、[Search Results]ウィジェット、および[Search Options]ウィジェットを含むウィジェットページを作成します。 |
| Wiki           | [Wiki]ウィジェット、[Category Filter]ウィジェット、[Tag Filter]ウィジェットを含むウィジェットページを作成します。  |

ページテンプレートの詳細：

  - Adding a Page Using a Page Template
  - [Creating a Page Template](./07-creating-a-page-template.md)

## 子ページとページ階層

子ページを作成することにより、ページを階層的に作成することもできます。 子ページは、カテゴリに整理できる複数のページがある場合に役立ちます。

![子ページをトップレベルページに追加して、ページを階層的に整理できます。](./understanding-pages/understanding-pages/images/06.png)

## 次のステップ

  - [Adding a Page](./adding-a-page-to-a-site.md)
  - [Configuring Individual Pages](./06-configuring-individual-pages.md)
  - [Personalizing Pages](./09-personalizing-pages.md)
