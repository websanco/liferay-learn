# フォームエントリーの管理

ユーザーがフォームエントリーの送信を開始すると、UIを介してフォームエントリーデータにアクセスしたり、さらなる分析や確認のためにデータを別のファイル形式にエクスポートしたりできます。

## フォームエントリーの表示

1.  *製品メニュー*（![Product Menu](../../../images/icon-product-menu.png)）を開き、*サイト管理*メニューのコンパスアイコン（![Compass](../../../images/icon-compass.png)）をクリックします。 フォームが作成されたサイトを選択します。

2.  *[Content & Data]* → *[Forms]* に移動します。

3.  フォームの*アクション*（![Actions](../../../images/icon-actions.png)）ボタンをクリックし、*[View Entries]* を選択します。

    ![エントリーは、フォームアプリケーションで直接表示できます。 ](./managing-form-entries/images/01.png)

4.  フォームにフィールドが複数ありウィンドウに収まらない場合は、エントリの横にある*アクション*（![Actions](../../../images/icon-actions.png)）ボタンをクリックして、*[View]* を選択します。

    ![フォームアプリケーションで1つのエントリを表示できます。](./managing-form-entries/images/02.png)

5.  *[View Form]* ページには、各フォームページからの回答が表示されます。 *[Next]* をクリックして次に進みます。

    ![フォームアプリケーションで個々の回答を表示する。](./managing-form-entries/images/03.png)

6.  *[Back]* をクリックして、フォームエントリーをもう一度表示します。

## フォームエントリーのエクスポート

フォームエントリーをエクスポートするには：

1.  サイトの*[Content & Data]* セクションでフォームアプリケーションに移動します。

2.  フォームの横にある*アクション*（![Actions](../../../images/icon-actions.png)）ボタンをクリックして、*[Export]* を選択します。

    ![フォームエントリーのエクスポート](./managing-form-entries/images/04.png)

3.  ファイル拡張子を選択します。 エントリーは`CSV`、`JSON`、`XLS`、または`XML`でエクスポートできます。

    ![ファイルタイプを選択](./managing-form-entries/images/05.png)

4.  *[OK]* をクリックして、ファイルを開くか、ローカルに保存します。

### CSVエクスポートの無効化

管理者がCSV形式でエントリーをエクスポートできるかどうかを決定するシステムレベルの設定があります。

1.  *[Control Panel]* → *[Configuration]* → *[System Settings]* に移動します。

2.  *[Content & Data]* の*[Forms]* カテゴリをクリックします。

3.  *[SITE SCOPE]* メニューの*[Forms]* エントリをクリックします。

4.  CSVエクスポートプロパティには3つのオプションがあります。

      - **Enabled**：警告なしでCSVエクスポートを有効にします

      - **Enabled (Show Warning)**：CSVエクスポートを有効にし、管理者に次の警告を表示します。

        `This CSV file contains user supplied inputs. Opening a CSV file in a spreadsheet program may be dangerous.`

      - **Disabled**：CSVエクスポートをオフにします。

    ![エクスポート機能の設定](./managing-form-entries/images/06.png)

5.  エントリーをエクスポートする機能を有効または無効にするオプションを選択します。

6.  完了したら、*[保存]* をクリックします。

## フォームエントリーの削除

1.  *[Site Adminstration]* → *[Content & Data]* → *[Forms]* に移動します。

2.  選択したフォームの横にある*アクション*（![Actions](../../../images/icon-actions.png)）ボタンをクリックし、*[View Entries]* を選択します。

3.  **[Filter and Order]**の横のボックスをオンにして、すべてのエントリーを選択します。 *[X]* が、[Form Entries]画面の右上隅に表示されます。

    ![すべてのフォームエントリーを一度に削除します。](./managing-form-entries/images/07.png)

4.  *[X]* ボタンをクリックして、すべてのエントリーを削除します。

または、ユーザーは1つのエントリーのみを削除することもできます。 選択したエントリーの横にあるボックスをオンにして、右上隅の*[X]* をクリックします。 *[OK]* をクリックしてコマンドを確認します。


<!-- Removed this information because it really doesn't belong in an article titled, "Managing Form Entries". Export/Import does more than just manage entries - it handles Forms and Forms Application Configurations.

## Additional Information

```note::
   The Forms application itself has an Import/Export window accessible from the application's Configuration menu. This is how you import and export the application configuration and its data (forms and form entries). The file format for this type of import and export is a LAR file.
```

![Exporting Form contents as a LAR](./managing-form-entries/images/08.png)

For more information, see the article on [importing and exporting application content](https://help.liferay.com/hc/articles/360029132551-Importing-Exporting-Sites-and-Content). -->
