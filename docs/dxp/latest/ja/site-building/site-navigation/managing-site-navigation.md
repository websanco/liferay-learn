# サイトナビゲーションの管理

Liferayには、ページを作成・整理するための強力なツールが用意されています。 シンプルでフラットなサイトナビゲーションから、多くのレベルにネストされたサブページの複雑な階層ツリーまで、あらゆるものを構築できます。

デフォルトでは、ページを整理するための定義済みのページ階層があります。 しかし、ページ階層とは別の*ナビゲーションメニュー*を作成することもできます。 このナビゲーションメニューを使えば、単発のランディングページはもちろん、メインメニュー、セカンダリメニュー、フッタメニュー、カスタムメニューといった複数のナビゲーションメニューを作成することができます。

```{note}
In DXP 7.3+, if you want to create a Navigation Menu to use across multiple Sites, you can use Navigation Menus created in the Global Site. Note that Global Navigation Menus cannot include menu items that require Site-specific information (such as the "page" type).
```

メニューはページごとに変えることができます。ランディングページには、頻繁にアクセスするページの簡単なリストを表示でき、残りのページにはセカンダリナビゲーションに表示できます。 さまざまなランディングページに特定のメニューを作成して、訪問者を関連するコンテンツに誘導することもできます。

サイトビルダーメニューでは、ページとナビゲーションメニューを作成および整理できます。

## ページの管理

*[サイトビルダー]* → *[Pages]* メニューに表示されている階層が、そのサイトのページ構成の主なリファレンスです。 ナビゲーションメニューは一部のページを表示するようにカスタマイズされていますが、このメニューは常にサイト上のページの主要なリファレンスです。

![サイトビルダーのページメニューは、サイト上のすべてのページの主要なリファレンスとして機能します。](./managing-site-navigation/images/01.png)

新しいページは、*[サイトビルダー]* → *[Pages]* メニューで作成します。 ページは、誰でも閲覧できる公開ページにすることも、サイトメンバーだけが閲覧できる非公開ページにすることもできます。 ページを追加する方法については、[Adding a Page to a Site](../creating-pages/adding-pages/adding-a-page-to-a-site.md)を参照してください。

## ナビゲーションメニューの使用

ナビゲーションメニューを作成してページに配置する方法については、[Using Navigation Menus](./using-navigation-menus.md)を参照してください。 ナビゲーションメニューウィジェットの設定方法の詳細については、[Configuring Navigation Menus](./configuring-navigation-menus.md)を参照してください。

## ページツリーの使用

サイトのページ階層を管理するもう一つの方法は、*ページツリー*を使用することです。 ここでは、現在のサイトのページを表示および設定できます。

ページツリーにアクセスするには、

1.  *[プロダクトメニュー]* をクリックします。

2.  *[ページツリー]* をクリックします。

    ![ページツリー機能は、プロダクトメニューの上部にあります。](./managing-site-navigation/images/02.png)

3.  左側のメニューには、サイトのページ階層が表示されます。

    ![各サイトのページツリーを表示します。](./managing-site-navigation/images/03.png)

ページツリービューでは、階層にページを追加したり、ページを設定したり、公開ページと非公開ページを切り替えたりすることができます。

## 追加情報

  - [Adding a Page to a Site](../creating-pages/adding-pages/adding-a-page-to-a-site.md)
  - [Using Navigation Menus](./using-navigation-menus.md)
  - [Managing Page Hierarchies](./managing-page-hierarchies.md)
