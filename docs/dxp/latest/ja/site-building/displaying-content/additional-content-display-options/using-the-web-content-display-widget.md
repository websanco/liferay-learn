# Webコンテンツの表示ウィジェットの使用

[［Webコンテンツの記事］](../../../content-authoring-and-management/web-content/web-content-articles/adding-a-basic-web-content-article.md)を作成したら、それをページに表示する最も簡単な方法はWebコンテンツの表示ウィジェットです。 Webコンテンツの記事が表示されると、 [ワークフロー](../../../process-automation/workflow/introduction-to-workflow.md) を設定して更新を確認および承認しない限り、コンテンツの更新がすぐに表示されます。

## Webコンテンツの表示をページに追加する

1. サイトメニューの下のから ［**サイトビルダー**］ &rarr; ［**ページ**］ に移動します。
1. 編集したいページの横にある **アクション**(![Actions](../../../images/icon-actions.png)) ボタンをクリックし、 ［**編集**］(または、[［新しいコンテントページの作成］](../../creating-pages/adding-pages/adding-a-page-to-a-site.md)) を選択して下さい。
1. コンテントページサイドバーから、 ［**Fragments and Widgets**］ ボタンを選択します。
1. ［**Fragments**］ タブを選択し、 ［**Web Content Display**］ ウィジェットをページ上にドラッグ＆ドロップしてください。

    ![Webコンテンツの表示ウィジェットをページ上にドラッグ＆ドロップする](./using-the-web-content-display-widget/images/04.png)

1. Webコンテンツの表示ウィジェットにカーソルを合わせ、ウィジェットの **アクション**(![Actions](../../../images/icon-widget-options.png)) メニューから ［**Configuration**］ を選択します。

    ![Webコンテンツの表示ウィジェットの設定オプションを開く](./using-the-web-content-display-widget/images/05.gif)

1. ［**Webコンテンツ表示構成**］ で、 ［**Setup**］ タブの下の ［**選択**］ をクリックします。
1. ［**Webコンテンツの選択**］ ダイアログで、追加したいWebコンテンツをクリックします。
1. オプショナルの [Webコンテンツの表示オプション](#web-content-display-setup-options) をセットアップします。
1. ［**保存**］ をクリックし、 ［**Webコンテンツ表示設定**］ ダイアログを閉じます。
1. コンテンツページを公開する場合は、 ［**公開**］ をクリックします。

## Webコンテンツの表示設定オプション

Webコンテンツの表示ウィジェットでは、その他のオプションを設定することができます。 ウィジェットの構成にアクセスするには、［Web Content Display］ウィジェットにカーソルを合わせ、ウィジェットの **アクション**(![Actions](../../../images/icon-widget-options.png)) メニューから ［**Configuration**］ を選択します。 設定オプションは、 ［**Setup**］ タブで確認することができます。

![Webコンテンツの表示の設定オプション](./using-the-web-content-display-widget/images/07.png)

### テンプレート

| オプション             | Description                                                        |
|:----------------- |:------------------------------------------------------------------ |
| デフォルトのテンプレートを使用する | ウィジェットの表示にデフォルトのテンプレートを使用します。 ここで使用されているデフォルトのテンプレートの名前を見ることができます。 |
| 特定のテンプレートを使用する    | 別のテンプレートでウィジェットを表示する場合は、このオプションを選択します。 新しいテンプレートを選択する必要があります。      |

### ユーザーツール

| オプション                    | 説明                                                                                                                                                                       |
|:------------------------ |:------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| 翻訳                       | コンテンツの追加翻訳を表示します。 異なる言語に翻訳されたコンテンツをお持ちの場合、このオプションを使用して希望の翻訳を表示します。                                                                                                       |
| 印刷                       | 印刷に適したバージョンのコンテンツを含む印刷ダイアログを提供します。                                                                                                                                       |
| PDF、DOC、ODT、TXTとしてダウンロード | 選択されたウィジェットのコンテンツを任意のフォーマットでダウンロードします。 このオプションは、 [OpenOffice または LibreOffice の統合](#integrating-open-office-or-libre-office-with-the-web-content-display) を設定した後でのみ利用可能です。 |

### メタデータコンテンツ

| オプション    | 説明                                                                                                                                                                                                                                             |
|:-------- |:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 関連するアセット | 関連する他のコンテンツへのリンクを表示する場合に設定します。 コンテンツの ［**Related Assets**］ プロパティで関連コンテンツを設定します（基本Webコンテンツアセットでの例は、以下の画像を参照してください）。                                                                                                                               |
| 評価       | ユーザーがさまざまな方法（いいね、スター、親指をたててグーなど）でコンテンツを評価できるクリック可能なオプションを表示します。 この評価オプションを異なるコンテンツの種類に設定するには、 [コンテンツの評価タイプの設定](../../../site-building/site-settings/site-content-configurations/configuring-content-rating-type.md) を参照してください。 |
| コメント     | Webコンテンツの表示ウィジェットのコンテンツに対して、ユーザーがコメントを入力できるようにします。 デフォルトでは、ゲストはコメントを残すことはできません。 デフォルトのオプションを変更するには、 [Enabling Comments for Guest](#enabling-comments-for-guests) を参照してください。                                                                    |
| コメントの評価  | コメントの評価オプションを表示します。                                                                                                                                                                                                                            |

![基本的なWebコンテンツエントリーの関連するアセットを設定する](./using-the-web-content-display-widget/images/06.png)

### カウンター増加を確認する

［**カウンター増加を確認する**］ オプションを有効にすると、このWebコンテンツの表示ウィジェットにビューカウンターを追加することができます。

## Webコンテンツの表示ウィジェットの追加オプション

[設定オプション](#web-content-display-setup-options) に加えて、Webコンテンツ表示ウィジェットで追加設定を行うことができます。

### ゲストのコメントを有効にする

デフォルトでは、ゲストはWebコンテンツにコメントを残すことはできません。 Webコンテンツの記事にゲストがコメントできるようにするには、次の手順を実行します。

1. [グローバルメニュー](../../../getting-started/navigating-dxp.md) (![Global Menu icon](../../../images/icon-applications-menu.png)) を開き、 ［**コントロールパネル**］ &rarr; ［**ユーザー**］ &rarr; ［**ロール**］ へと移動します。
1. ［**Guest**］ Rol.をクリックします。
1. ［**権限の定義**］ の列をクリックします。
1. 左のメニューから、 ［**サイトとアセットライブラリの管理**］ &rarr; ［**コンテンツ & データ**］ &rarr; ［**Webコンテンツ**］ を選択します。
1. ［**Webコンテンツの記事**］ のセクションで、 ［**Add Discussion**］ にチェックを入れます。
1. ［**保存**］ をクリックします。

### Webコンテンツの表示ウィジェットからコンテンツを編集する

編集モードのコンテントページで作業する場合、Webコンテンツの表示ウィジェットから公開コンテンツを直接編集することができます。

1. Webコンテンツ表示ウィジェットをクリックします。
1. サイドバーパネルで、 **コンテンツ**(![Contents](../../../images/icon-list-ul.png)) ボタンをクリックします。
1. ［**Contents**］ の下にある、編集したいコンテンツの **アクション**(![Actions](../../../images/icon-actions.png)) ボタンをクリックします。
1. ［**編集**］ を選択します。

    ![Webコンテンツの表示ウィジェットの編集オプションを開く](./using-the-web-content-display-widget/images/08.png)

### OpenOfficeまたはLibreOfficeとWebコンテンツの表示の統合

Liferay Portalのインスタンスで[OpenOfficeまたはLibreOfficeの統合を有効](../../../content-authoring-and-management/documents-and-media/devops/enabling-openoffice-libreoffice-integration.md)にしている場合は、コンテンツのドキュメントの変換を有効にできます。 この統合により、ユーザーはさまざまなフォーマットでコンテンツをダウンロードできるようになります。 Webコンテンツ表示ウィジェットの [**ユーザーツール**](#user-tools) 設定で、利用できる形式を選択する必要があります。

## 関連情報

- [基本的なWebコンテンツの記事の追加](../../../content-authoring-and-management/web-content/web-content-articles/adding-a-basic-web-content-article.md)
- [コンテントページの使用](../../../site-building/creating-pages/using-content-pages.md)
- [表示ページテンプレートと表示ページについて](../using-display-page-templates/about-display-page-templates-and-display-pages.md)
- [アセットの表示](../using-the-asset-publisher-widget/displaying-assets-using-the-asset-publisher-widget.md)
