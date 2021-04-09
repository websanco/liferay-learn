# ページフラグメントの使用

ページフラグメントは、HTML、CSS、およびJavaScriptのチャンクであり、これらを組み合わせると [コンテンツページ](../../creating-pages/building-and-managing-content-pages/content-pages-overview.md)構築します。 各ページフラグメントは、ページに機能を追加します。 Liferay DXPにはいくつかのデフォルトのページフラグメントが付属しており、それらを使用してカルーセルやカードなどの基本的なページ要素を作成できます。

![フラグメントを使用してページをすばやく作成できます。](./using-page-fragments/images/01.png)

ただし、カスタムフラグメントが必要な場合があります。 組み込みの [ページフラグメントエディター](../../developer-guide/developing-page-fragments/reference/page-fragment-editor-interface-reference.md) か、デスクトップからローカルに [フラグメントツールキット](../../developer-guide/developing-page-fragments/developing-page-fragments-with-the-fragments-toolkit.md)を使用して、独自のページフラグメントを作成できます。 カスタムページフラグメントの作成の詳細については、 [ページフラグメントの開発](../../developer-guide/developing-page-fragments/developing-fragments-intro.md)参照してください。

[Liferayはウィジェット埋め込み](../../developer-guide/developing-page-fragments/reference/fragment-specific-tags-reference.md#including-widgets-within-a-fragment)、ページ・フラグメントにテキストや画像を含むフィールドを追加できます。 エンドユーザーは、コンテンツページの最終公開プロセス中に、これらのページ要素にコンテンツを追加できます。

![独自のコンテンツをフラグメントで提供できます。](./using-page-fragments/images/02.png)

ページフラグメントはコレクションに編成されます。 コレクションは関連するフラグメントをグループ化するため、簡単に管理できます。 [コンテンツページの構築](../../creating-pages/building-and-managing-content-pages/building-content-pages.md)するときフラグメントは、これらのコレクションを介してアクセスできます 。 リソースをページフラグメントコレクションに含め、可用性を気にすることなくページフラグメントのコードからそれらを参照できます。 これは、ページフラグメントを移動するときに役立ちます。 詳細については、「 [ページフラグメント](../../developer-guide/developing-page-fragments/including-default-resources-with-fragments.md) デフォルトリソースを含める」を参照してください。

![コレクションは、フラグメントを整理、管理、共有する簡単な方法を提供します。](./using-page-fragments/images/03.png)
