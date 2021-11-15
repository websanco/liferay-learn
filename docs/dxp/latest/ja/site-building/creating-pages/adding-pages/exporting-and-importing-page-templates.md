# ページテンプレートのエクスポートとインポート

コードエディタでテンプレートを編集する方が簡単な場合があります。 [ページテンプレートを作成](./creating-a-page-template.md)したら、それを[エクスポート](../../building-sites/importing-exporting-pages-and-content.md)し、選択したエディターでローカルで編集してから、テンプレートをLiferayにインポートして戻すことができます。 テンプレートは、システム上の任意のサイトにインポートできます。

```{tip}
ページテンプレートは、より大きなコレクションフレームワークの一部です。 詳細は、 [About Collections and Collection Pages](../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md) を参照してください。
```

エクスポート/インポートフレームワークのより一般的な情報については、 [Importing/ Exporting Sites and Content](../../building-sites/importing-exporting-pages-and-content.md)を参照してください。

## ページテンプレートのエクスポート

1.  ページテンプレートを作成したサイトに移動します。

2.  プロダクトメニュー（![Product Menu](../../../images/icon-product-menu.png)）をクリックし ます。

3.  *[デザイン]* → *[ページテンプレート]* の順にクリックします。

4.  *[ページテンプレート]* タブをクリックします。

5.  ページテンプレートが存在するコレクションを選択します（たとえば、 *コレクション1*）。

6.  目的のページテンプレートの横にあるオプション（![Options](../../../images/icon-options.png)）をクリックします。

7.  *[エクスポート]* をクリックします。

    ![[エクスポート]をクリックして、ページテンプレートをZipとしてエクスポートします。](./exporting-and-importing-page-templates/images/01.png)

8.  確認ウィンドウで*[OK]* をクリックします。

ページテンプレートは、ZIPファイルとしてローカルマシンにダウンロードされます。

```{tip}
一度に複数のコンテンツテンプレートをエクスポートできます。 ただし、同じZIPファイルにコンテンツページを含むウィジェットテンプレートを含めることはできません。
```

ページテンプレートの最後に公開されたバージョンが常にエクスポートされます。 ページテンプレートが公開されたことがない場合、エクスポートすることはできません。

エクスポートされたZIPファイルには次のファイルが含まれています。

  - `page-template-collection.json`：ページテンプレートが保存されているコレクションの名前とその他のメタデータが含まれています
  - オプションのサムネイルファイル
  - `page-template.json`：ページテンプレート名とその他のメタデータが含まれています
  - `page-definition.json`：ページテンプレートのストラクチャーとコンテンツを指定します

[ページ定義JSONスキーマファイル](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/layout/layout-page-template-api/src/main/resources/com/liferay/layout/page/template/validator/dependencies/page_definition_json_schema.json)は、`page-definition.json`ファイルのコンテンツを説明したもので、JSONスキーマ検証をサポートしている任意のエディタにインポートすることができます。

ZIPファイルには、さまざまな種類のページテンプレートが含まれている場合があります。

`display-page-template.json`、`master-page.json`、`page-template-collection.json`および`page-template.json`に使用できる[JSONスキーマ](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/layout/layout-page-template-api/src/main/resources/com/liferay/layout/page/template/validator/dependencies)があります。

## ページテンプレートのインポート

1.  *ページテンプレート*をインポートするサイトに移動します。

2.  プロダクトメニュー（![Product Menu](../../../images/icon-product-menu.png)）をクリックし ます。

3.  *[デザイン]* → *[ページテンプレート]* の順にクリックします。

4.  右上のオプション（![Options](../../../images/icon-options.png) ）→ *[インポート]* の順にクリックします。

    ![インポート機能は右上のオプションメニューにあります。](./exporting-and-importing-page-templates/images/02.png)

5.  *[Browse]* をクリックして、ページテンプレートファイルに移動します。

6.  ブラウザウィンドウで*[Open]* をクリックします。

7.  *[インポート]* をクリックします。

    ![ページテンプレートはZIPファイルとしてインポートされます。](./exporting-and-importing-page-templates/images/03.png)

8.  *[インポート]* ウィンドウを閉じます。

ページテンプレートがサイトにインポートされました。

## 追加情報

  - [Creating a Page Template](./creating-a-page-template.md)
  - [サイトとコンテンツのインポート/エクスポート](../../building-sites/importing-exporting-pages-and-content.md)
  - [コレクションの作成](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md)
