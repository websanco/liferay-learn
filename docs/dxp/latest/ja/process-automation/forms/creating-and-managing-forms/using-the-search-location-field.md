# ロケーションの検索フィールドを使う

［場所の検索］フィールドを使用して、ユーザーがGoogleのMaps JavaScriptAPIとGooglePlaces APIを操作し、場所を選択して、これらのフィールドをフォームに自動入力できるようにします。

- 住所(例：1400 Montefino Ave.)
- 市（例：Diamond Bar）
- 州（例：カリフォルニア州）
- 郵便番号（例：91765）
- 国名（例：米国）

フォームのすべてのフィールドを表示する必要はありません。

## 検索場所フィールドの追加と構成

検索場所］フィールドをフォームに追加するには、次のようにします。

1. JavaScriptのGoogle Maps APIとGoogle Places APIを有効にします。

   - ［検索場所］フィールドをフォームに追加します。 Google Places APIキーがサイトに設定されていない場合、フィールドには警告メッセージが表示されます。

       ![Search Locationフィールドは、APIキーが設定されていないことを検出します。](./using-the-search-location-field/images/01.png)

   - Google Places APIとMaps JavaScript APIの両方を使用するための1つのAPI Keyを取得します。
   - フィールド警告メッセージのリンクをクリックするか、［サイト設定（プラットフォーム）］→［Google Places］に移動してください。 または、同一のインスタンス設定の構成エントリを使用します。

       ![APIキーは、Google Places APIとMaps JavaScript APIを有効にする必要があります。](./using-the-search-location-field/images/02.png)

   - Google Places API Keyを入力し、 _［保存］_をクリックします。

1. ここで、フォームのフィールドを設定します。 フィールド設定のフィールドラベル、表示フィールド、およびレイアウトに特に注意してください。

    - **フィールドラベル** は、フォームのフィールドのラベルを変更するためのものです。 例えば、［Search Location］の代わりに［Location Lookup］を選択します。
    - **表示フィールド** は、フォームに表示および自動入力するサブフィールドを構成します。 _Search Location_ テキストフィールドは常に表示されます（ただし、ラベルはカスタマイズできます）。 住所、市、州、郵便番号、および/または国のサブフィールドを含めることを選択します。
    - **レイアウト** は、検索場所とそのサブフィールドを1列に表示するか2列に表示するかを決定します。

## ロケーションの検索フィールドへのデータ入力

フォームが公開されると、ユーザーは［Search Location］フィールドに場所に関する何かを入力して操作します。フィールド設定で表示するように設定されたフィールドは、提示されたオプションから場所が選択されると、すべて自動入力されます。

![位置情報の入力を開始すると、Google APIのオートコンプリートにより、有効な位置情報が選択されます。](./using-the-search-location-field/images/03.png)

## 関連する内容

- [サイト設定UIリファレンス](../../../site-building/site-settings/site-settings-ui-reference.md)
- [フォームの作成](creating-forms.md)
- [RESTデータプロバイダーを使用してフォームオプションを入力する](../data-providers/using-the-rest-data-provider-to-populate-form-options.md)
