# テンプレートへのウィジェットの埋め込み

ウィジェットはWebコンテンツテンプレートに埋め込むことができます。コアアプリ、カスタムアプリ、インスタンス化可能、インスタンス化不可能であるかどうかは関係ありません。 以下は、FreeMarkerにWebコンテンツウィジェットを埋め込む例です。

``` markup
<@liferay_portlet["runtime"] portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
```

Webコンテンツテンプレートへのウィジェットの埋め込みを開始するには、次の手順に従います。

1.  サイト（この例ではデフォルトのDXPサイト）の*サイト管理*に移動します。

2.  *[Content & Data]* → *[Webコンテンツ]* の順にクリックします。

3.  *[テンプレート]* タブをクリックします。

    ![[テンプレート]タブには、サイトのテンプレートが表示されます。](./embedding-widgets-in-templates/images/01.png)

4.  追加（![Add Icon](../../../images/icon-add.png)）アイコンをクリックします。

5.  テンプレートの名前を入力します（たとえば、*テンプレート1*）

6.  *[Properties]* セクションから*[Select]* をクリックして、ストラクチャーを選択します（たとえば、*ストラクチャー1*）。

    ![ストラクチャーを選択します。](./embedding-widgets-in-templates/images/02.png)

7.  マクロ（たとえば`<@liferay_portlet["runtime"] portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />`）をテンプレートエディタに貼り付けます。 ポートレットがインスタンス化可能かどうかによっては、ポートレットIDの入力が必要になる場合があります。

    ![ポートレットマクロを入力します。](./embedding-widgets-in-templates/images/03.png)

8.  完了したら、*[保存]* をクリックします。

<!-- end list -->

```{important}
` theme`変数はFreeMarkerコンテキストに挿入されなくなりました。 DXP 7.0でtheme変数が削除された理由や、コードを更新するための提案については、 [Taglibs Are No Longer Accessible via the theme Variable in FreeMarker](https://help.liferay.com/hc/articles/360017892092-Introduction-to-Breaking-Changes-#taglibs-are-no-longer-accessible-via-the-theme-variable-in-freemarker) の破壊的変更のエントリーを参照してください。
```

## 他のテンプレートの埋め込み

テンプレートにウィジェットを埋め込むことに加えて、別のテンプレート内にテンプレートを埋め込むことができます。 これにより、再利用可能なコード、JavaScriptライブラリのインポート、スクリプト、またはマクロが可能になります。

以下は、FreeMarkerにテンプレートを埋め込む例です。

``` markup
<#include "${templatesPath}/[template-key]" />
```

以前に公開されたテンプレートを編集するときに、*テンプレートキー*を確認できます。

![テンプレートの編集ページを表示すると、テンプレートキーを確認できます。](./embedding-widgets-in-templates/images/04.png)

## 追加情報

  - [Embedding a Portlet by Portlet Name](https://help.liferay.com/hc/articles/360028746512-Embedding-a-Portlet-by-Portlet-Name)
