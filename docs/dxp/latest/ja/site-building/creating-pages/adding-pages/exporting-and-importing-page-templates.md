# ページテンプレートのエクスポートとインポート

コードエディタでテンプレートを編集する方が簡単な場合があります。 [ページテンプレートを作成](./creating-a-page-template.md)したら、それを[エクスポート](../../building-sites/importing-exporting-pages-and-content.md)し、好きなエディターでローカルで編集してから、テンプレートをLiferayにインポートして戻すことができます。 テンプレートは、システム上の任意のサイトにインポートできます。

エクスポート/インポートフレームワークのより一般的な情報については、 [サイトとコンテンツのインポート/エクスポート](../../building-sites/importing-exporting-pages-and-content.md)を参照してください。

```{note}
Liferay DXP 7.4以降の場合、ページテンプレートコレクションは、Liferay UIではページテンプレートセットと呼ばれます。
```

## ページテンプレートのエクスポート

ページテンプレートを作成するには、次の手順に従います。

1. **サイトメニュー**(![Site Menu](../../../images/icon-product-menu.png)) を開き、 ［**Design**］ &rarr; ［**Page Templates**］ に移動します。

1. ［**Page Templates**］ タブをクリックします。

1. 目的のテンプレートを含むページテンプレートセットを選択します。

1. 目的のページテンプレートの **アクション** ボタン (![Actions Button](../../../images/icon-actions.png))をクリックし、 ［**Export**］ を選択します。

   ![［エクスポート］をクリックして、ページテンプレートをZipとしてエクスポートします。](./exporting-and-importing-page-templates/images/01.png)

1. 確認ウィンドウで ［**OK**］ をクリックします。

ページテンプレートは、ZIPファイルとしてローカルマシンにダウンロードされます。

```{tip}
一度に複数のコンテンツテンプレートをエクスポートできます。 ただし、同じZIPファイルにコンテントページとウィジェットテンプレートを含めることはできません。
```

ページテンプレートの最後に公開されたバージョンが常にエクスポートされます。 ページテンプレートが公開されたことがない場合、エクスポートすることはできません。

エクスポートされたZIPファイルには次のファイルが含まれています。

* `page-template-collection.json`：ページテンプレートが保存されているセットの名前とその他のメタデータが含まれています
* オプションのサムネイルファイル
* `page-template.json`：ページテンプレート名とその他のメタデータが含まれています
* `page-definition.json`：ページテンプレートのストラクチャーとコンテンツを指定します。

ZIPファイルには、 `display-page-template.json`, `master-page.json`, `page-template-collection.json` , `page-template.json`というように異なるタイプのページテンプレートも含まれる場合があります。

## ページテンプレートのインポート

ページテンプレートをインポートするには、次の手順に従います。

1. **サイトメニュー**(![Site Menu](../../../images/icon-product-menu.png)) を開き、 ［**デザイン**］ &rarr; ［**ページテンプレート**］ に移動します。

1. アプリケーションバーの **アクション** ボタン（![Actions Button](../../../images/icon-actions.png)）をクリックし、 ［**Import**］ を選択します。

   ![インポート機能は右上のオプションメニューにあります。](./exporting-and-importing-page-templates/images/02.png)

1. 目的のページテンプレートファイルを選択します。

1. ［**Import**］ をクリックします。

   ![ページテンプレートはZIPファイルとしてインポートされます。](./exporting-and-importing-page-templates/images/03.png)

1. ［**インポート**］ ウィンドウを閉じます。

ページテンプレートがサイトにインポートされました。

## 追加情報

* [ページテンプレートの作成](./creating-a-page-template.md)
* [サイトとコンテンツのインポート/エクスポート](../../building-sites/importing-exporting-pages-and-content.md)
* [コレクションの作成](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md)
