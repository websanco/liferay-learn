# Microsoft Office 365を使用したドキュメントの作成と編集

Office 365&trade;を使用して、ドキュメントとメディアライブラリに保存するためのテキストドキュメント、スプレッドシート、またはプレゼンテーションを作成および編集できます。 Office 365&trade;編集セッションが終了したら、ドキュメントをチェックインして、ドキュメントとメディアライブラリに変更を保存する必要があります。

開始する前に、まずDXPインストールを[有効](../../devops/enabling-document-creation-and-editing-with-microsoft-office-365.md)にしてMicrosoft Office 365&trade;に接続する必要があります。

<a name="認証" />

## 認証

Office 365&trade;を介してドキュメントとメディアファイルを初めて作成または編集するときは、Microsoftアカウントで認証する必要があります。 これにより、OneDrive&trade;がDXPアカウントにリンクされるため、これを行う必要があるのは1回だけです。

アカウントのリンクを解除するには、次の3つの手順を実行します。

1. **ご自身のプロファイル** &rarr; ［**アカウント設定**］ をクリックします。
1. ［**一般**］ タブで、 ［**Apps**］ をクリックします。
1. OneDrive&trade;の横にある ［**取り消す**］ をクリックします。

    ![ポータルからアカウントのリンクを解除できます。](./creating-and-editing-documents-with-microsoft-office-365/images/01.png)

OneDrive&trade;のリンクが解除されました。

<a name="ファイルの作成" />

## ファイルの作成

1. **メニュー**（![Product Menu](../../../../images/icon-menu.png)）を開き、サイト名をクリックして、 ［**Content & Data**］ &rarr; ［**ドキュメントとメディア**］ に移動します。
1. **追加** アイコン（![Add](../../../../images/icon-add.png)）をクリックし、ドキュメントライブラリに追加するOffice 365&trade;ドキュメントのタイプを選択します。

    * Word&trade;
    * Excel&trade;
    * PowerPoint&trade;

    これらのオプションのいずれかを選択すると、ドキュメントの名前を入力するための新しいウィンドウが開きます。

    ![作成するドキュメントのタイプを選択します。](./creating-and-editing-documents-with-microsoft-office-365/images/02.png)

1. ［**Title**］ フィールドにドキュメントの名前を入力し、 ［**保存**］ をクリックします。 ［**保存**］ をクリックすると、DXPは一時的なドキュメントとメディアファイルを作成し、それを新しいOffice 365&trade;ファイルにリンクします。 次に、ブラウザでそのOffice 365&trade;ファイルを含む新しいウィンドウが開くので、そのコンテンツを作成できます。。

    ![ドキュメントを作成するときは、名前を付ける必要があります。](./creating-and-editing-documents-with-microsoft-office-365/images/03.png)

1. Office 365&trade;エディタを使用して、ドキュメントのコンテンツを作成します。

1. DXPへの変更を保存または破棄します。

    **Check in：** Office 365&trade;ファイルをドキュメントとメディアに保存してから、Office 365&trade;からファイルを削除します。 保存されるファイルの形式は、上記の手順2で選択したドキュメントタイプによって異なります。

    * Word: Microsoft Word&trade;（`.docx`）
    * PowerPoint: Microsoft PowerPoint&trade;（`.pptx`）
    * Excel: Microsoft Excel&trade;（`.xlsx`）

    **チェックアウトのキャンセル：** Office 365&trade;ファイルを削除し、変更を破棄します。

<a name="ファイルの編集" />

## ファイルの編集

Office 365&trade;を使用して、次のタイプのドキュメントとメディアファイルを編集できます。

* テキストファイル（`.doc`、`.docx`、`.docm`、`.dot`、`.dotx`、`.dotm`、`.html`、`.txt`、`.rtf`、`.odt`）
* プレゼンテーションファイル（`.ppt`、`.pptx`、`.pptm`、`.pps`、`.ppsx`、`.ppsm`、`.pot`、`.potx`、`.potm`）
* スプレッドシートファイル（`.xls`、`.xlsx`、`.xlsm`、`.xlt`、`.xltx`、`.xltm`、`.ods`、`.csv`、`.tsv`、`.txt`、`.tab`）

次の手順に従って、Office 365&trade;でドキュメントとメディアファイルを編集します。

1. ドキュメントとメディアライブラリのファイルに移動します。

1. ファイルのアクションアイコン（![Actions](../../../../images/icon-actions.png)）をクリックし、 ［**Office 365で編集**］ を選択します。 これにより、ファイルが自動的に[チェックアウト](../../publishing-and-sharing/managing-document-access/managing-document-changes-with-checkout.md)され、そのコンテンツが新しいOffice 365&trade;ファイルに転送され、そのファイルのOffice 365&trade;にリダイレクトされます。

1. Office 365&trade;でファイルを編集します。 編集プロセスは、ファイルを作成するための上記のプロセスとまったく同じです。

    ![ドキュメントを変更するには、ファイルのアクションメニューから［Office 365で編集］を選択します。](./creating-and-editing-documents-with-microsoft-office-365/images/04.png)

<a name="追加情報" />

## 追加情報

* [Microsoft Office 365でドキュメントの作成と編集を有効にする](../../devops/enabling-document-creation-and-editing-with-microsoft-office-365.md)
* [Googleドライブでのドキュメントの作成と編集](./creating-and-editing-documents-with-google-drive.md)
