# Googleドライブでのドキュメントの作成と編集

Google Docs&trade;使用して、ドキュメントとメディアライブラリに保存するテキストドキュメント、スプレッドシート、またはプレゼンテーションを作成および編集できます。  Google Docs&trade;の編集セッションが終了すると、変更内容はドキュメントとメディアライブラリに自動的に保存されます。

<a name="認証" />

## 認証

Google Docs&trade;を使用してドキュメントとメディアファイルを初めて作成または編集するときは、Googleアカウントに対して認証する必要があります。 これにより、Google Drive&trade;がポータルアカウントにリンクされるため、これを行う必要があるのは1回だけです。

アカウントのリンクを解除するには、

1. **ご自身のプロファイル** &rarr; ［**アカウント設定**］ をクリックします。
1. ［**一般**］ タブで、 ［**Apps**］ をクリックします。
1. Google Drive&trade;の横にある ［**取り消す**］ をクリックします。

![ポータルからGoogleアカウントのリンクを解除できます。](./creating-and-editing-documents-with-google-drive/images/01.png)

Google Drive&trade;へのアクセスはリンク解除されました。

<a name="ファイルの作成" />

## ファイルの作成

次の手順に従って、Google Docs&trade;を介して新しいドキュメントとメディアファイルを作成します。

1. **メニュー**（![Product Menu](../../../../images/icon-menu.png)）を開き、サイト名をクリックして、 ［**Content & Data**］ &rarr; ［**ドキュメントとメディア**］ に移動します。
1. **追加** アイコン（![Add](../../../../images/icon-add.png)）をクリックし、ドキュメントライブラリに追加するGoogleドキュメントのタイプを選択します。

    * Google Docs&trade;
    * Google Slides&trade;
    * Google Sheets&trade;

1. これらのオプションのいずれかを選択すると、DXPは一時的なドキュメントとメディアファイルを作成し、それを新しいGoogleファイルにリンクします。 次に、ブラウザはそのGoogleファイルにリダイレクトして、そのコンテンツを作成できるようにします。 一時的なドキュメントとメディアファイルに対する一部のアクションは、リンクされたGoogleファイルに影響を与える可能性があることに注意してください。 詳細については、 [複数の編集セッション](#multiple-editing-sessions) を参照してください。

    ![作成するGoogleドキュメントのタイプを選択します。](./creating-and-editing-documents-with-google-drive/images/02.png)

1. Google Docs&trade;エディタを使用して、ドキュメントのコンテンツを作成します。 共有を除いて、すべてのGoogle Docs&trade;機能を利用できます。

1. Google Docs&trade;エディタで次のツールバーボタンのいずれかをクリックして、変更を保存または破棄します。

    **Save and Return to Liferay：** ドキュメントを新しいファイルとしてドキュメントとメディアライブラリに保存し、Googleファイルを削除して、ポータルに戻ります。 保存されるファイルの形式は、上記の手順2で選択したGoogleドキュメントのタイプによって異なります。

    * Google Docs&trade;：Microsoft Word (`.docx`)
    * Google Slides&trade;：Microsoft PowerPoint (`.pptx`)
    * Google Sheets&trade;：Microsoft Excel (`.xlsx`)

    **変更を破棄：** ドキュメントとメディアライブラリまたはGoogle Docs&trade;でファイルを保存せずにポータルに戻ります。

    どちらのボタンもクリックせずにGoogle Docs&trade;ウィンドウを閉じることも可能です。 この場合、編集セッションは、それを表示していたウィンドウが閉じていても開いたままになります。 詳細については、以下の [複数の編集セッション](#multiple-editing-sessions) を参照してください。

    ![エディタのツールバーを使用して、変更を保存または破棄します。](./creating-and-editing-documents-with-google-drive/images/03.png)

<a name="ファイルの編集" />

## ファイルの編集

Google Docs&trade;を使用して、次のタイプのドキュメントとメディアファイルを編集できます。

* テキストファイル（`.docx`、`.html`、`.txt`、`.rtf`、`.odt`）
* プレゼンテーションファイル（`.pptx`、`.odp`）
* スプレッドシートファイル（`.xlsx`、`.ods`、`.csv`、`.tsv`）
* PDFファイル

| **注：** Google Docs&trade;は、古い、非XMLベースのMicrosoft Officeファイルの形式（`.doc`、`.ppt`、`.xls`）をサポートしていません。

次の手順に従って、Google Docs&trade;でドキュメントとメディアファイルを編集します。

1. ドキュメントとメディアライブラリのファイルに移動します。
1. ファイルのアクションアイコン（![Actions](../../../../images/icon-actions.png)）をクリックし、 ［**Google ドキュメントで編集**］ を選択します。 これにより、ファイルが自動的に[チェックアウト](../../publishing-and-sharing/managing-document-access/managing-document-changes-with-checkout.md)され、そのコンテンツが新しいGoogle Docs&trade;ファイルに転送され、そのGoogle Docs&trade;ファイルにリダイレクトされます。

    ![ファイルのアクションメニューから［Google ドキュメントで編集］を選択します。](./creating-and-editing-documents-with-google-drive/images/04.png)

1. Google Docs&trade;でファイルを編集します。 編集プロセスは、ファイルを作成するための上記のプロセスとまったく同じです。

<a name="複数の編集セッション" />

## 複数の編集セッション

Google Docs&trade;でドキュメントとメディアファイルを作成または編集する場合、 ［**Save and Return to Liferay**］ または ［**変更を破棄**］ をクリックすると、変更を保存または破棄できます。 代わりに、どちらもクリックせずにウィンドウを閉じると、編集セッションは引き続き存在します。 セッションには、ドキュメントとメディアの元のファイルからアクセスできます。 ファイルが以前に存在していなかった場合（たとえば、新しいファイルを作成していた場合）、ドキュメントとメディアに一時ファイルとして表示されます。

ドキュメントとメディアファイルの編集セッションがすでに存在する場合、そのファイルのアクションアイコン（![Actions](../../../../images/icon-actions.png)）を介して次のアクションを実行できます。

**Google ドキュメントで編集：** Google Docs&trade;でファイルの編集を再開します。

**Check in：** Googleファイル（変更を含む）をドキュメントとメディアファイルに保存してから、Googleファイルを削除します。 これは、Google Docs&trade;の編集ウィンドウで ［**Save and Return to Liferay**］ をクリックするのと同じです。

**チェックアウトのキャンセル：** Googleファイルを削除し、変更を破棄します。 これは、Google Docs&trade;の編集ウィンドウで ［**変更を破棄**］ をクリックするのと同じです。

<a name="追加情報" />

## 追加情報

* [Microsoft Office 365を使用したドキュメントの作成と編集](./creating-and-editing-documents-with-microsoft-office-365.md)
* [Googleドライブ上のファイルへのリンク](../linking-to-files-on-google-drive.md)
