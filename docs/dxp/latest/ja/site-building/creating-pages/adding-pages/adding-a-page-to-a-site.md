# サイトにページを追加する

以下の手順では、サイトに新しいページを追加し、一般的なオプションを構成する方法について説明します。 残りのページ設定を構成する方法については、[Configuring Individual Pages](../page-settings/configuring-individual-pages.md)を参照するか、または[ページセット](../understanding-pages/understanding-pages.md#page-sets)のオプションを設定する方法について[Configuring Page Sets](../page-settings/configuring-page-sets.md)を参照してください。

## 新しいページを追加する

次の手順に従って、ページを追加します。

1.  [サイトメニュー](../../../getting-started/navigating-dxp.md#site-menu)を使用して、目的のサイトに移動します。

2.  サイトメニューの*[Site Builder]* → *[Pages]* に移動します。

3.  [Pages]ページで、*追加*ボタン（![Add](../../../images/icon-add.png)）をクリックしてトップレベルのページを作成します。

    ![[Pages]画面では、サイトページ全体を編集できます。](./adding-a-page-to-a-site/images/01.png)

    ``` tip::
       または、Liferay DXP 7.3以降では、サイト名の横にあるページツリーアイコンをクリックして、ページツリーメニューを開くことができます。 ページツリーメニューを使用すると、検索バーでキーワードによってページをすばやく検索したり、ページを選択してそのページに移動したり、アクションメニューからページを追加したりすることもできます。
    ```

4.  *ブランク* [マスターページテンプレート](../defining-headers-and-footers/master-page-templates.md)をクリックして、[コンテンツページ](../understanding-pages/understanding-pages.md#page-types)をゼロから開始し、[カスタムマスターページテンプレート](../defining-headers-and-footers/creating-a-master-page-template.md)を選択して、テンプレートに基づいてコンテンツページを作成します。または、*その他* の見出しで [別のページタイプ](../understanding-pages/other-page-types.md) を選択するか、[ウィジェットページ](../understanding-pages/understanding-pages.md#widget-pages) や *グローバルテンプレート* メニューにリストされているページテンプレートなどを選択します。

    ![ページを追加するときは、ページタイプを選択する必要があります。](./adding-a-page-to-a-site/images/04.png)

5.  名前を入力し、ページの残りの設定を構成します。

6.  *[保存]* をクリックしてページを作成します。

    ``` tip::
       デフォルトでページを作成すると、新しいページが追加されるように設定されているナビゲーションメニューにもそのページが追加されます。 See `Configuring Site Navigation <../../04-site-navigation/README.md>`_ for more information.
    ```

## 子ページを追加する

ページツリーメニューから子ページを追加するには、次の手順に従います。

1.  製品メニューを開き、サイト名の横にある（![icon-page-tree](../../../images/icon-page-tree.png)）アイコンをクリックして、ページツリーメニューを開きます。

2.  既存のページの横にあるアクションメニューから*[Add Child Page]* を選択して、ネストされた子ページを作成します。

    ![ページツリーメニューを使用して子ページを追加する。](adding-a-page-to-a-site/images/05.png)

3.  [上記](#adding-a-new-page)のページ作成の標準的な手順に従ってください。

[Pages]画面から子ページを追加するには、次の手順に従います。

1.  製品メニューを開き、サイトのメニューから*[Site Builder]* → *[Pages]* に移動します。
2.  既存のページの横にある *Add* ボタン（![Add](../../../images/icon-add-app.png)）をクリックします。 [ページ]ページの右上にある[ *追加* ]ボタン（![Add](../../../images/icon-add.png)）をクリックして、トップレベルのページを作成することもできます。
3.  上記のページを作成するための標準的な手順に従います。

## 関連情報

  - [Understanding Pages](../understanding-pages/understanding-pages.md)
  - [Content Page Overview](../building-and-managing-content-pages/content-pages-overview.md)
