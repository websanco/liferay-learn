# コンテントページの使用

```{toctree}
:maxdepth: 3

using-content-pages/adding-elements-to-content-pages.md
using-content-pages/managing-content-in-content-pages.md
using-content-pages/using-widgets-on-a-content-page.md
using-content-pages/using-page-comments.md
using-content-pages/manually-translating-content-pages.md
using-content-pages/content-page-editor-ui-reference.md
```

デフォルトでは、Liferay DXPはコンテントページタイプを使用し、ページのコンテンツとデザインを管理・編集するための便利なユーザーインターフェイスを提供します。 コンテンツページでは、設定可能なドラッグ＆ドロップ要素をすぐに使用でき、独自の要素を開発して、希望するユーザーエクスペリエンスを実現することができます。

他のページタイプと同様に、 [ウィジェット](./using-content-pages/using-widgets-on-a-content-page.md) を使用してコンテンツページに動的な機能を追加し、ブログ、Wiki、掲示板などを統合することができます。 コンテントページは、主に[ページフラグメント](./page-fragments-and-widgets/using-fragments.md)を使って構築されます。 ページフラグメントは、テキスト、画像、リンクなどの編集可能なコンポーネントを含む、拡張可能で再利用可能なページ要素であり、利用可能なコンテンツにマップすることもできます。 DXPのすぐに使えるフラグメントの詳細については [デフォルトのフラグメントリファレンス](./page-fragments-and-widgets/using-fragments/default-fragments-reference.md) を、独自のフラグメントを作成する方法については [フラグメントの開発](../developer-guide/developing-page-fragments/developing-fragments-intro.md) を参照してください。

コンテントページを編集する場合、 **サイトビルダー** を介して変更が行われ、下書きとして保存されます。 本番環境に影響を与えることなく、現在の編集セッションで行った変更を確認したり、元に戻したり、やり直したりすることができます。 準備ができたら、変更をすぐに公開するか、カスタム [ワークフロー](../../process-automation/workflow/introduction-to-workflow.md) を有効にして、レビューと公開のプロセスを指示します。 ページ要素に[コメント](./using-content-pages/using-page-comments.md)を追加して、チームメンバーと共同作業することもできます。

![コンテントページを編集する場合、すべての変更はサイトビルダーを介して行われます。](./using-content-pages/images/01.png)

コンテントページのフラグメントとウィジェットを使って、Webコンテンツやドキュメントなどを表示することができます。 また、ページフィールドにマッピングされたコンテンツも含めて、閲覧、編集、管理することができます。 詳細については、 [コンテントページのコンテンツの管理](./using-content-pages/managing-content-in-content-pages.md) を参照してください。

パーソナライズされたページ の[エクスペリエンス](../personalizing-site-experience/experience-personalization/content-page-personalization.md)を作成し、ローカライズされた翻訳を管理して、ターゲットオーディエンスを引き付けます。 また、レスポンシブレイアウトエディターを使えば、ユーザーエクスペリエンスをさらに最適化することができます。 このツールは、ページの内容を適応させるので、コンテンツページがデスクトップ、タブレット、スマートフォンでどのように表示されるかを確認し、設定することができます。

最後に、 [A/Bテスト](../optimizing-sites/ab-testing/ab-testing.md)では、コンテントページの代替バージョンを作成し、訪問者のデータ（例：直帰率、クリック数など）を追跡することが可能です。 アルゴリズムを使ってこのデータを分析し、どのページのバージョンが最も目的を達成できるかを判断し、それに応じてメッセージを磨くことができます。

はじめるには、 [コンテンツページへの要素の追加](./using-content-pages/adding-elements-to-content-pages.md) を参照してください。

::::{grid} 2 :gutter: 3 3 3 3

:::{grid-item-card} Adding Elements to Content Pages :link: ./using-content-pages/adding-elements-to-content-pages.md :::

:::{grid-item-card} Managing Content in Content Pages :link: ./using-content-pages/managing-content-in-content-pages.md :::

:::{grid-item-card} Using Fragments

* [デフォルトのフラグメントリファレンス](./page-fragments-and-widgets/using-fragments/default-fragments-reference.md)
* [レイアウト要素の使用](./page-fragments-and-widgets/using-fragments/using-layout-elements.md)
* [フラグメントの設定](./page-fragments-and-widgets/using-fragments/configuring-fragments.md) :::

:::{grid-item-card} Using Widgets on a Content Page :link: ./using-content-pages/using-widgets-on-a-content-page.md :::

:::{grid-item-card} Using Page Comments :link: ./using-content-pages/using-page-comments.md :::

:::{grid-item-card} Manually Translating Content Pages :link: ./using-content-pages/manually-translating-content-pages.md :::

:::{grid-item-card} Content Page Editor UI Reference :link: ./using-content-pages/content-page-editor-ui-reference.md ::: ::::
