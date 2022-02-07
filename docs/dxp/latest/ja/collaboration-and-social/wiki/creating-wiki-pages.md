# Wikiページの作成

_［Wiki］_ウィジェットは、_FrontPage_と呼ばれるデフォルトのWikiページから始まります。 ユーザーはこのページを編集して、最初のWikiページを作成できます。

デフォルトでは、認証されたユーザーのみが_Wiki_の記事を作成できます。ゲストは、最初にサインインする必要があります。

## _FrontPage_でコンテンツを作成する

1. _［Wiki］_ウィジェットがデプロイされているサイトページに移動します。

    ![コンテンツのない［Wiki］ウィジェット](./creating-wiki-pages/images/01.png)

1. _FrontPage_を変更するには、ボックス内の_［This page is empty. Edit it to add some text.］_リンクをクリックします。
1. デフォルトのテキストエディターはCreoleを使用します。 形式を切り替えるには、_［設定］_セクションを展開します。

    ![テキスト形式の変更](./creating-wiki-pages/images/02.png)

1. 新しい形式（HTML）を選択し、_［OK］_をクリックして変更を受け入れます。
1. 記事の内容を入力します。

### 添付ファイルをアップロードする

ユーザーはファイルをWikiページに添付できます。

1. 添付ファイルを追加するには、_［Attachments］_セクションを展開します。
1. ファイルをドラッグアンドドロップしてアップロードするか、_［Select Files］_ボタンを使用してファイルの場所に移動します。

    ![添付ファイルのアップロード](./creating-wiki-pages/images/03.png)

```{important}
You can enable automatic antivirus scanning to scan files on upload. For details, please see [Enabling Antivirus Scanning for Uploaded Files](../../system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.md).
```

### タグと関連アセットを使用してWikiページを整理する

ユーザーはページにタグを追加できます。 このタグを使用してコンテンツを検索すると（たとえば、_Toyota_）、_Toyota_を含むすべての投稿がより速く返されます。

1. _［カテゴリー設定］_セクションを展開します。
1. _［選択］_ボタンをクリックして、既存のタグを選択します。 または、_［Tags］_フィールドにタグ名を入力して_［追加］_をクリックし、新しいタグを作成します。 詳細は、[タグに関するドキュメント](https://help.liferay.com/hc/articles/360028820472-Tagging-Content)を参照してください。

1. 完了したら、_［公開］_をクリックします。

![FrontPageページの公開](./creating-wiki-pages/images/05.png)

_FrontPage_の記事が作成されました。

## 子ページを作成する

_FrontPage_ページが作成されると、ユーザーは_子ページ_を作成できます。 これにより、Wikiページの単純な親子階層が作成されます。 _Wikiの子ページ_は親ページに属している場合でも、独自の子記事を持つことができます。 _子ページ_を作成するには、_［Add Child Page］_をクリックします。

![子ページを追加する](./creating-wiki-pages/images/06.png)

同じ_Wiki_ページエディターが開きます。

![Wikiページエディターは、すべてのレベルのすべてのページで同じです。](./creating-wiki-pages/images/07.png)

## 他のトップレベルページを作成する

_「子ページを作成する」_で前述したように、今後のWikiページは、組織化の目的で子ページとして作成されます。 別のトップレベルページを作成するには：

1. _［All Pages］_タブをクリックします。
1. _［Wiki］_ウィジェットのメニューの上にマウスを置き、_［Add Page］_をクリックします。

   ![別のWikiトップレベルページを追加する](./creating-wiki-pages/images/04.png)

同じ_Wiki_ページエディターが開きます。

_［Wiki］_ウィジェットは複数のトップレベルページを持つことができます。メインの_［Wiki］_ウィジェットには_FrontPage_ Wikiページしか表示されませんが、サイトコンテンツの作成者は[［Wiki Display］ウィジェット](./using-the-wiki-display-widget.md)を使用して他のトップレベルページを表示できます。

![［Wiki Display］ウィジェットを使用して他のトップレベルページを表示する](./creating-wiki-pages/images/08.png)

## 次のステップ

* [ノードの作成](./creating-a-node.md)
* [Wiki表示ウィジェットの使用](./using-the-wiki-display-widget.md)
