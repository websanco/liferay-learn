# サイト構築の概要

サイトはLiferay DXPの基本的なコンポーネントです。 基本的に、サイトは、追加機能を提供するコンテンツアプリケーションを含むページのコレクションです。 多くのアプリケーションは、すぐに使用できます。

Liferay DXPインスタンスを最初に起動して設定すると、デフォルトのサイトが含まれています。 デフォルトでは、インスタンス名は **Liferay** となっていますが、この値はセットアップウィザードの設定や、コントロールパネルの[［インスタンス設定］](../system-administration/configuring-liferay/virtual-instances/instance-configuration.md)で変更することができます。

![Liferay DXPで強力なサイトを構築することができます。](./introduction-to-site-building/images/01.png)

## サイトの作成

Liferay DXPには[サイトを構築](./building-sites/adding-a-site.md)し、[ページを作成する ](./creating-pages/adding-pages/adding-a-page-to-a-site.md)のに必要なツールがすべて含まれています。 アプリケーションを含む事前定義されたページのセットを含む既存の[サイトテンプレート](./building-sites/building-sites-with-site-templates.md)からサイトを作成することもできますし、空白のサイトを作成してゼロから構築することもできます。

ページも同様に柔軟です。 複数のページタイプがあります。 既存の[ページタイプ](./creating-pages/understanding-pages/understanding-pages.md)からページを作成するか、デフォルトの[ページテンプレート](./creating-pages/adding-pages/creating-a-page-template.md)、または自分で作成したページテンプレートからページを作成します。

サイトとページは階層的に整理することができます。 親サイトや親ページに対して、ネストしたページ（子ページ）や [ネストしたサイト](./building-sites/site-hierarchies.md) （子サイト）を好きなだけ追加することができます。 Liferay DXPインスタンスでは、様々な組織や従業員などのために複数のサイトを持つことが可能です。 [インポート/エクスポートオプション](./building-sites/importing-exporting-pages-and-content.md)を使用して、コンテンツやページをサイト間で共有することもできます。

ページとサイトの[ナビゲーション](./site-navigation/using-navigation-menus.md)動作の構成は直感的で柔軟性があります。 ページが作成されると、自動的にナビゲーションに追加されます。 ページを非表示にすることもできますし、単純に非公開にすることもできますので、適切な権限を持った [サイトの](./building-sites/site-membership/adding-members-to-sites.md) メンバーだけが閲覧することができます。

また、Liferay DXPは、ユーザーエクスペリエンスを妨げることなく、サイトの変更を可能にするツールを提供しています。 Liferayサイトはステージングすることができ、ユーザーに公開する前にサイト上で変更を加えてテストすることができます。 Liferay DXPの [公開](./publishing-tools/publications.md) 機能がこれを処理してくれます。 ユーザーが見ている本番のサイトに影響を与えることなく、安全な環境でサイトの開発、追跡、更新を行うために、公開を利用することができます。

## コンテンツの表示

Liferay DXPのコンテンツ管理システム（CMS）は、さまざまな種類のコンテンツ（ブログ、画像、Webコンテンツ記事など）を作成、管理、および表示するためのさまざまなツールを提供します。 [Webコンテンツの表示ウィジェット](./displaying-content/additional-content-display-options/using-the-web-content-display-widget.md) を使って、任意のWebコンテンツ記事を表示することができます。

コンテンツの種類を組み合わせて公開する場合は、[アセットパブリッシャー](./displaying-content/using-the-asset-publisher-widget/displaying-assets-using-the-asset-publisher-widget.md)または[コレクションの表示](../site-building/displaying-content/additional-content-display-options/displaying-collections.md)を使用できます。 アセットパブリッシャーが表示するコンテンツを手動で選択することも、特定の条件に基づいてアセットを動的に表示することもでき、ユーザーの体験を大幅にコントロールできます。 [IFrameウィジェット ](./displaying-content/additional-content-display-options/using-the-iframe-widget.md)を使用するか、または [ 埋め込みページ](./creating-pages/understanding-pages/other-page-types.md#embedded) を作成して、他のWebサイトのコンテンツを埋め込むこともできます。

[フラグメント](./creating-pages/page-fragments-and-widgets/using-fragments.md)を使用してインラインでコンテンツを作成・編集することができます。 フラグメントとは、コード（CSS、HTML、JavaScript）の個々の断片のことで、これを並べたり組み合わせたりして、ページを構築することができます。 いくつかのフラグメントが用意されており、それらを変更してコンテンツを作成することができます。 よりカスタムなソリューションが必要な場合は、 [独自に作成](./developer-guide/developing-page-fragments/developing-fragments-intro.md)することができます。

各コンテンツには、表示時に表示されるデフォルトページがありますが、 [表示ページテンプレート](./displaying-content/using-display-page-templates/about-display-page-templates-and-display-pages.md)を作成することにより、カスタマイズされたランディングページを提供できます。 表示ページテンプレートは、Webコンテンツの一部（タイトル、本文、画像など）をフラグメントにマッピングすることで、思い通りの表示を実現します。

![表示ページテンプレートでは、Webコンテンツをページフラグメントにマッピングして、コンテンツを思い通りのデザインやレイアウトで表示することができます。](./introduction-to-site-building/images/02.png)

## ユーザーエクスペリエンスのカスタマイズ

Liferay DXPの [パーソナライズとセグメンテーション](./personalizing-site-experience/personalizing-site-experience.md) フレームワークにより、ユーザーの興味やニーズに対応したサイト体験を構築することができます。 所属する組織やサイト内での役割など、特定の条件に基づいて[ユーザーセグメントを作成](./personalizing-site-experience/segmentation/creating-and-managing-user-segments.md)し、それを使ってユーザー層に合わせて [パーソナライズされたページレイアウトやコンテンツ](./personalizing-site-experience/experience-personalization/content-page-personalization.md) を表示することができます。または、 [Analytics Cloudと連携](https://learn.liferay.com/analytics-cloud/latest/ja/connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.html) して、セグメント内のユーザーがどのようにサイトを利用しているのか、その行動を分析することができます。 [ユーザーの行動に基づいてコンテンツを推奨](./personalizing-site-experience/experience-personalization/understanding-content-recommendations.md)することもできます。

<!-- Screenshot -->

## サイトのカスタマイズと設定

サイトの外観、動作、機能を変更するための[設定](./site-settings/site-settings-ui-reference.md)が多数用意されています。 必要に応じて、[サイトとそのコンテンツを複数の言語にローカライズ](./site-settings/site-localization.md)できます。

サイトのルック＆フィールを[異なるテーマを使用することで変更](../getting-started/changing-your-sites-appearance.md)することができます。 テーマは、[Liferay マーケットプレイス](../system-administration/installing-and-managing-apps/using-marketplace.md)からダウンロードしてデプロイするか、[自分でテーマを作成する](../setting-up-the-site-building/site-appearance/themes/theme-development/getting-started/setting-up-an-environment-and-creating-a-theme.md)ことも可能です。 テーマは、サイトの全体的なルック＆フィールを決定し、ページのCSS、JavaScript、HTMLを定義します（FreeMarkerテンプレートを使用）。  FreeMarkerは、標準的なHTML要素を組み合わせ、変数、条件文、ループなどの機能を追加しています。 詳細は、[Developing Themes](./developer_guide.md)を参照してください。

## サイトの最適化

Liferay DXPは、サイトの [検索エンジン最適化(SEO)ランキング](../site-building/displaying-content/using-display-page-templates/configuring-seo-and-open-graph.md)の向上から、 各デバイスに最適化された[レスポンシブ・ページの作成](./optimizing-sites/building-a-responsive-site/building-a-responsive-site.md)、 [A/Bテスト](../site-building/optimizing-sites/ab-testing/ab-testing.md)によるメッセージン グ・キャンペーンの改善まで、サイトを最適化するための複数のツールと機能を提供します。 A/Bテストでは、現在のデフォルトバリエーションのページと、ページバリアント（複数可）を比較し、特定の目標（直帰率、クリック数など）に対して、どのページのパフォーマンスが良いかを確認します。 これにより、データに基づいてサイトに関するより良い判断ができるようになり、ユーザーや顧客にこれまで以上に迅速にサービスを提供できるようになります。
