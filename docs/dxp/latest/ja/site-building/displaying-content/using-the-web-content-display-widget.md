# Webコンテンツの表示ウィジェットの使用

[Webコンテンツの記事](../../content-authoring-and-management/web-content/web-content-articles/adding-a-basic-web-content-article.md)を作成したら、それをページに表示する最も簡単な方法はWebコンテンツの表示ウィジェットです。 Webコンテンツの記事が表示されると、[ワークフローの](../../process-automation/workflow/introduction-to-workflow.md)が有効になっている場合を除き、そのコンテンツの更新情報がすぐに表示されます。

## Webコンテンツの表示をページに追加する

Webコンテンツの表示ウィジェットをページに追加して構成するには、次の手順に従います。

1.  編集 ( ![Edit icon](../../images/icon-edit.png) ) モード (コンテンツ ページを使用している場合) に入り、 [ページにWebコンテンツの表示ウィジェットを追加します](../creating-pages/using-widget-pages/adding-widgets-to-a-page.md)。

    ![Webコンテンツの表示アプリをページに追加して、新しいWebコンテンツ記事の表示を開始します。](./additional-content-display-options/using-the-web-content-display-widget/images/01.png)

2.  [Webコンテンツの表示ウィジェットの構成メニューを開きます](TODO:adding-widgets#configuration-menu)。

3.  *[選択]* ボタンをクリックして、Webコンテンツの一部を選択します。

4.  表示したい記事を検索してクリックします。

5.  ウィジェットで有効にしたい[オプションを設定します](#using-the-web-content-display-widget-configuration-options)。

6.  *[保存]* をクリックして変更を適用し、構成ウィンドウを閉じます。 コンテンツページを使用している場合は、*[Publish]* をクリックして、ウィジェットを含むページを公開します。

## Webコンテンツ表示設定オプション

すべての機能はシンプルなセレクター ボタンとして実装されているため、必要に応じて有効または無効にすることができます。 次の機能を使用できます。

  - **ユーザーツール**

      - *翻訳:* コンテンツで利用可能なロケールを表示します。 特定の言語のページで作業している場合は、ロケールに合ったコンテンツの翻訳を選択できます。

      - *印刷する:* コンテンツの印刷に適したバージョンで印刷ダイアログを開きます。

      - *PDF | DOC | ODT | TXTでダウンロード:* 選択した形式でWebコンテンツをダウンロードします。 これらのオプションは、[Open Officeの統合が有効になっている](../../content-authoring-and-management/documents-and-media/devops/enabling-openoffice-libreoffice-integration.md)場合にのみ使用できます。

  - **メタデータコンテンツ**

      - *関連するアセット*
      - *評価*
      - *コメント*
      - *コメントの評価*

![Webコンテンツの公開は簡単です。 最低限、公開したいコンテンツを選択すればよいだけです。 また、多くのオプション機能を有効にして、ユーザーがコンテンツを操作できるようにすることもできます。](./additional-content-display-options/using-the-web-content-display-widget/images/02.png)

### ゲストのコメントを有効にする

デフォルトでは、ゲストはWebコンテンツにコメントを残すことはできません。 Webコンテンツの記事にゲストがコメントできるようにするには、次の手順を実行します。

1.  [グローバルメニュー](../../getting-started/navigating-dxp.md) ( ![Global Menu icon](../../images/icon-applications-menu.png) ) を開き、 *[コントロールパネル]* → *[ロール]* に移動します。

2.  *[ゲスト]* → *[権限の定義]* を選択します。

3.  左のメニューから、*[サイトとアセットライブラリの管理]* → *[Content & Data]* → *[Webコンテンツ]* を選択します。

4.  Webコンテンツ記事の見出しに移動し、*[Add Discussion]* チェックボックスをオンにします。

5.  *[保存]* をクリックします。

ゲストはWebコンテンツの記事にコメントを投稿できるようになりました。

### Webコンテンツの表示ウィジェットからコンテンツを編集する

公開されたコンテンツは、Webコンテンツの表示ウィジェットから直接編集できます。

1.  Webコンテンツの表示ウィジェットにカーソルを合わせます。
2.  ウィジェットコンテナ内のアクションメニュー (![Options](../../images/icon-app-options.png)) を開きます。
3.  *[Webコンテンツの編集]* を選択してエディターを起動するか、 *[テンプレートの編集]* を選択してWebコンテンツ記事のテンプレート用のテンプレートエディターを起動します (ある場合)。

### Open OfficeとWebコンテンツの表示の統合

Liferay Portalのインスタンスで[OpenOffice/LibreOfficeの統合を有効](../../content-authoring-and-management/documents-and-media/devops/enabling-openoffice-libreoffice-integration.md)にしている場合は、コンテンツのドキュメントの変換を有効にできます。 その後、ユーザーは選択した形式でコンテンツをダウンロードできます。 *ユーザーツール*のリストで、Webコンテンツ表示の設定ページに希望する変換オプションを有効にします。

![アセットの変換オプションが一覧表示されます。](./additional-content-display-options/using-the-web-content-display-widget/images/03.png)

## 関連情報

  - [Content Authoring and Management](../../content_authoring_and_management.html)
  - [Displaying Content on Display Pages Templates](./using-display-page-templates/displaying-content-with-display-page-templates.md)
  - [Displaying Assets](./using-the-asset-publisher-widget/displaying-assets-intro.md)
