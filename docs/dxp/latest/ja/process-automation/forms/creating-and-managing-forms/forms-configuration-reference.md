# フォーム構成リファレンス

ここでは、フォームの全構成の概要を説明していますので、必要な構成をすぐに見つけることができます。

<a name="forms-options" />

## フォームオプション

サイト間でフォームをエクスポートおよびインポートすることができます。 **エクスポート/インポート** にメニューにアクセスするには、 ［**サイト管理**］ &rarr; ［**コンテンツおよびデータ**］ &rarr; ［**フォーム**］ 右上の![Options](../../../images/icon-options.png)アイコンをクリックし、 ［**エクスポート/インポート**］ をクリックします。

![フォームをエクスポートおよびインポートできます。](./forms-configuration-reference/images/01.png)

詳しくは、 [フォームのエクスポートとインポート](./exporting-and-importing-forms.md) および [ページとコンテンツのインポートとエクスポート](../../../site-building/building-sites/importing-exporting-pages-and-content.md) を参照してください。

<a name="form-settings" />

## フォームの設定

各フォームにはそれぞれ設定メニューがあります。

設定メニューを表示するには、目的のフォームを選択します（例： **ゲスト調査のフィードバック**）。 右上の![Options](../../../images/icon-options.png)アイコンをクリックし、 ［**Settings**］ をクリックします。

### フォームオプション

![フォームオプションをクリックすると、これらの設定が含まれています。](./forms-configuration-reference/images/02.png)

| フィールド                                                              | 説明                                                     |
| :--- | :--- |
| 認証を求める                                                             | フォームを送信する前にサインインが必要になります                               |
| CAPTCHA                                                            | フォームを送信する際にCAPTCHAの質問に回答する必要があります                      |
| 回答を自動保存                                                            | フォームへの回答を自動的に保存します                                     |
| フォーム送信完了時のリダイレクト先URL                                               | フォームの送信が成功した場合のリダイレクトURLを指定します。                        |
| ストレージの種類                                                           | デフォルトのストレージタイプとしてJSONを設定します（編集不可）。                     |
| [ワークフロー](./advanced-forms-usage/using-forms-with-a-workflow.md)の選択 | フォームの送信を確認するためのワークフロー定義を選択します。ワークフローはデフォルトでは無効になっています。 |

### メール通知設定

ここでは、フォームエントリーが送信されるたびに通知メールを送信するようにフォームアプリを設定できます。 まずはメールサーバーを設定する必要があります。詳しくは[Connecting to a Mail Server](../../../installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md)を参照してください。

![フォームに通知機能を追加することができます。](./forms-configuration-reference/images/03.png)

| フィールド      | 説明                                                             |
| :--- | :--- |
| 送信者名       | 送信者の名前。これは、サイト名、フォーム名、または受信者に有益なその他の情報にできます。                   |
| 送信者メールアドレス | 送信者のメールアドレス。`noreply@example.com`を使用して、受信者が返信できないようにすることができます。 |
| 送信先アドレス    | 受信者のメールアドレス（例：`test@example.com`)                              |
| 件名         | 有益な件名にすることで、受信者はメールの内容を把握しやすくなります。                             |

詳しくは、 [フォーム通知の設定](../sharing-forms-and-managing-submissions/configuring-form-notifications.md) を参照してください。

<a name="form-widget-configuration" />

## フォームウィジェットの設定

サイトページに配置されている **フォーム** ウィジェットを設定することができます。 ［**設定**］ メニューにアクセスするには、ウィジェットのタイトルの横にある![Options](../../../images/icon-app-options.png)アイコン → ［**設定**]］ をクリックします。

### 設定

ここでは、このウィジェットで使用する目的のフォームを選択できます。

![このウィジェットで使用するフォームを選択します。](./forms-configuration-reference/images/04.png)

### 共有

ここでは、このアプリケーションをDXP以外のプラットフォームに埋め込むことができます。

![フォームアプリを組み込むプラットフォームを選択します。](./forms-configuration-reference/images/05.png)

### 範囲

ここでは、ウィジェットのスコープを［グローバル］［サイト］［ページ］から変更できます。

![フォームウィジェットのスコープを選択します。](./forms-configuration-reference/images/06.png)

<a name="instance-settings" />

## インスタンス設定

インスタンス設定の Forms 設定エントリは、システム設定の Forms エントリと同じです (下記参照)。 詳細については、 [設定スコープ](../../../system-administration/configuring-liferay/understanding-configuration-scope.md) についてを参照してください。

各構成プロパティの説明については、 [システム設定](#system-settings) を参照してください。

<a name="system-settings" />

## システム設定

現在、システムレベルのFormsの設定項目は1つしかありません。 Liferay UIでプロパティを設定するには、以下の方法があります。

1. **グローバルメニュー**（![global icon](../../../images/icon-applications-menu.png)）→ [**コントロールパネル**] に移動します。
1. ［**システム設定**］ をクリックします。
1. ［**コンテンツとデータ**］ セクションで ［**フォーム**］ をクリックします。

![［システム設定］メニューでフォームを構成します。](./forms-configuration-reference/images/07.png)

また、バッキング `DDMFormWebConfiguration` サービスを、 `.config` という名前のファイルで設定することもできます。

```
com.liferay.dynamic.data.mapping.form.web.internal.configuration.DDMFormWebConfiguration.config
```

`Liferay Home/osgi/configs`に配置してください。 詳細は、 [構成ファイルの使用](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) を参照してください。

| フィールド                                                   | 説明                                                                                                                                                                                          |
| :--- | :--- |
| 自動保存間隔 (`autosaveInterval`)                             | フォームの自動保存を行う時間を分単位で設定します。0に設定すると自動保存は行われません。                                                                                                                                                |
| CSVエクスポート(`csvExport`)                                  | 管理者がフォームエントリーをCSVとしてダウンロードできるかどうかを決定します。                                                                                                                                                    |
| 既定の表示モード (`defaultDisplayView`)                         | 検索コンテナでのフォームの表示方法を設定します。                                                                                                                                                                    |
| ゲストアップロードファイル拡張子（`guestUploadFileExtensions`）           | 受け入れられるファイル拡張子のコンマ区切りリストを入力します。 デフォルトでは以下のリストがサポートされています：`doc`、`docx`、`jpeg`、`jpg`、`pdf`、`png`、`ppt`、`pptx`、`tiff`、`txt`、`xls`、および`xlsx`。                                                   |
| ゲストアップロードの最大ファイルサイズ（`guestUploadMaximumFileSize`）       | ゲストユーザーがアップロード欄からアップロードできる最大ファイルサイズを設定します。 これは、ドキュメントとメディアアプリケーションの設定で、認証されたユーザーに対して設定され、システム全体の制限は、アップロードサーブレットリクエストシステム設定エントリーの［合計最大アップロードリクエストサイズ］というフィールドで定義されます。 デフォルトでは25MBに設定されています。 |
| アップロードフィールドの最大繰り返し（`maximumRepetitionsForUploadFields`） | フィールドが反復可能に設定されている場合、ユーザーがフォームにフィールドを追加できる最大回数を設定します（ゲストユーザーとログインユーザーに同じように適用されます）。 デフォルトでは5に設定されています。                                                                                      |
| ゲストアップロードフィールドの最大送信数``）                                 | ゲストユーザーがゲスト対応のアップロードフィールドを含むフォームを送信できる最大回数を設定します。 ゲストユーザーのIPアドレスは、送信数の記録のために使用されます。 デフォルトでは5に設定されています。                                                                                      |

<a name="additional-information" />

## 追加情報

* [フォーム権限リファレンス](./forms-permissions-reference.md)
