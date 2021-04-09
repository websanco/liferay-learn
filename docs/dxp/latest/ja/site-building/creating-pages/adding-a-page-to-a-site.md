# サイトにページを追加する

以下の手順では、サイトに新しいページを追加し、一般的なオプションを構成する方法について説明します。 残りのページ設定を構成する方法については、[Configuring Individual Pages](./06-configuring-individual-pages.md)を参照するか、または[ページセット](./understanding-pages.md#page-sets)のオプションを設定する方法について[Configuring Page Sets](./05-configuring-page-sets.md)を参照してください。

## 新しいページを追加する

次の手順に従って、ページを追加します。

1.  [サイトメニュー](../../getting-started/navigating-dxp.md#site-menu)を使用して、目的のサイトに移動します。

2.  サイトメニューの*[Site Builder]* → *[Pages]* に移動します。

3.  [Pages]ページで、*追加*ボタン（![Add](../../images/icon-add.png)）をクリックしてトップレベルのページを作成します。

    ![[Pages]画面では、サイトページ全体を編集できます。](./adding-a-page-to-a-site/images/01.png)

    ``` tip::
       または、Liferay DXP 7.3以降では、サイト名の横にあるページツリーアイコンをクリックして、ページツリーメニューを開くことができます。 ページツリーメニューを使用すると、検索バーでキーワードによってページをすばやく検索したり、ページを選択してそのページに移動したり、アクションメニューからページを追加したりすることもできます。
    ```

4.  *[Blank]* をクリックして[コンテンツページ](./understanding-pages.md#page-types)を作成するか、*[Other]* 見出しの下の[別のページタイプ](./other-page-types.md)を選択するか、あるは*[Global Template]* メニューの下に表示されるページテンプレートを選択します。 Liferay DXP 7.3より前のバージョンでは、ページタイプは*[Basic Pages]* にリストされています。

    ![ページを追加するときは、ページタイプを選択する必要があります。](./adding-a-page-to-a-site/images/04.png)

5.  名前を入力し、ページの残りの設定を構成します。

6.  *[保存]* をクリックしてページを作成します。

    ``` tip::
       デフォルトでページを作成すると、新しいページが追加されるように設定されているナビゲーションメニューにもそのページが追加されます。 詳細については、`Configuring Site Navigation <../04-site-navigation/README.md>`を参照してください。
    ```

## 子ページを追加する

ページツリーメニューから子ページを追加するには、次の手順に従います。

1.  製品メニューを開き、サイト名の横にある（![icon-page-tree](../../images/icon-page-tree.png)）アイコンをクリックして、ページツリーメニューを開きます。

2.  既存のページの横にあるアクションメニューから*[Add Child Page]* を選択して、ネストされた子ページを作成します。

    ![ページツリーメニューを使用して子ページを追加する。](adding-a-page-to-a-site/images/05.png)

3.  上記のページを作成するための標準的な手順に従います。

[Pages]画面から子ページを追加するには、次の手順に従います。

1.  製品メニューを開き、サイトのメニューから*[Site Builder]* → *[Pages]* に移動します。
2.  既存のページの横にある*追加*ボタン（![Add](../../images/icon-add-app.png)）をクリックします。 [Pages]ページの右上にある*追加*ボタン（![Add](../../images/icon-add.png)）をクリックして、トップレベルのページを作成することもできます。
3.  上記のページを作成するための標準的な手順に従います。

## 追加情報

  - [Understanding Pages](./understanding-pages.md)
  - [Content Page Overview](./content-pages-overview.md)
