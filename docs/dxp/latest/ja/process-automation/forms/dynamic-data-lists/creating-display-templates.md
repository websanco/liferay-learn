# 表示テンプレートの作成

データ定義ごとに、必要な数のディスプレイを作成することができます。 データリストのフォームビューに特定のデータ定義のすべてのフィールドを表示しないフォームテンプレートを作成した場合は、それらのフィールドをリストビューにも表示したくない場合があります。 表示テンプレートを使用してリストビューを変更します。

注： [ウェブコンテンツテンプレート](../../../content-authoring-and-management/web-content/web-content-templates/creating-web-content-templates.md)に慣れている方は、ディスプレイテンプレートも同じようにリストの表示をカスタマイズします。 ディスプレイテンプレートはFreeMarkerやVelocityで書かれており、Webコンテンツテンプレートが構造体からデータを引き出すのと同じように、データ定義からデータを引き出します。 また、Webコンテンツのテンプレートと同様に、ディスプレイテンプレートを他のディスプレイテンプレートに埋め込むことができます。 これにより、再利用可能なコード、JavaScriptライブラリのインポート、またはVelocityやFreeMarkerのテンプレートでインポートされたマクロをシステムに組み込むことができます。 表示テンプレートを埋め込むことで、似たようなデータ定義が多数ある場合に、より効率的な処理が可能になります。 埋め込まれた表示テンプレートをインポートして、それを元に新しい表示テンプレートを作成します。

[フォームテンプレート](./creating-form-templates.md)と同様に、サイト管理または動的データリスト表示ウィジェットから表示テンプレートを作成できます。

サイト管理から新しい表示テンプレートを作成するには

1. ［**コンテンツとデータ**］ &rarr; ［**動的データリスト**］ へ行きます。

1. 右上のオプションアイコン（![Options](../../../images/icon-options.png)）をクリックし、 ［**データ定義の管理**］ をクリックします。

1. データ定義のリストで、定義の［アクション］アイコン（![Actions](../../../images/icon-actions.png)）をクリックし、 ［**テンプレートの管理**］ をクリックします。

1. **追加** アイコンをクリックし（![Add](../../../images/icon-add.png)）、 **ディスプレイテンプレートの追加** をクリックします。

1. テンプレートに名前をつけ、フォームの［詳細］セクションを展開して、説明をつけます。 フォームの詳細セクションで、使用するテンプレート言語を言語セレクタから選択します。 [FreeMarker](https://freemarker.apache.org/index.html) または [Velocity](https://velocity.apache.org/) を選択できます。

1. フォームのスクリプトセクションで、前のステップで選択したテンプレート言語を使って、エディタでテンプレートを作成します。　 エディターの左にあるパレットには、共通の変数が入っています。 変数をクリックすると、エディターに挿入されます。 エディターもオートコンプリートしています。 FreeMarkerのテンプレートでは、 `${`と入力すると、一般的な変数のオートコンプリートリストが表示されます。 エディターに挿入する変数を選択します。 また、エディターの下にある **ファイルを選択** ボタンをクリックして、完全なスクリプトファイルをアップロードすることもできます。

    ![スクリプトセクションを使用してテンプレートを作成します。](./creating-display-templates/images/01.png)

    テンプレートエディター用のヘルパー変数については、以下を参照してください。

1. テンプレートの作成が完了したら、 **保存** をクリックします。

また、［動的データリスト表示］ウィジェットから表示テンプレートを作成することもできます。

1. サイトページでウィジェットを追加および構成するには、[［データリストの作成］](./creating-data-lists.md) 記事の手順に従ってください。 テンプレートを作成するリストを表示するようにウィジェットを構成してください。

1. ウィジェットの **表示テンプレートの追加** リンクをクリックします。 これにより、リスト定義のフォームテンプレートを作成するための上記と同じフォームが開きます。

<a name="display-template-editor" />

## 表示テンプレート

ヘルパー変数は、テンプレートエディターで使用できます。 これらのデータは、ディスプレイテンプレートを作成する際に使用するほとんどのデータにアクセスできます。 ［データリスト変数］の見出しの下にある変数は、テンプレートの作成対象となるデータ定義に関する特定の情報を注入することができます。

**データ定義ID：** `reserved_ddm_structure_id`となります。

**データリストの説明：** `reserved_record_set_description`となります。

**データリストID：** `reserved_record_set_id`となります。

**データリスト名：** `reserved_record_set_name`となります。

**テンプレートID：** `reserved_ddm_template_id`となります。

テンプレート内では、これらの変数によってレコードセットのID、名前、説明、データ定義が与えられます。

`レコード` 変数に割り当てることにより、レコードのリストを表示します。 リストのレコードを `DDLDisplayTemplateHelper`から取得しますが、この中には以下の関数が含まれています。

```
getDocumentLibraryPreviewURL

getHTMLContent

getLayoutFriendlyURL

getRecords

renderRecordFieldValue
```

`DDLDisplayTemplateHelper` は一般的なタスクを実行します。 `getRecords` メソッドを使用して、データ定義のエントリにアクセスし、 `records` 変数に割り当てます。

```
<#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>
```

これは、関連するデータリストのレコードを取得します。 まだ何もしていないので、ディスプレイは空のままです。 すべてのレコードをリストアップするには、テンプレートエディターのサイドバーにある［データリストレコード］ヘルパーを使います。 テンプレートエディターウィンドウの適切な場所にカーソルを置き、［データリスト・レコード］をクリックしてください。 このコードはカーソルに表示されます。

```
<#if records?has_content>
    <#list records as cur_record>
        ${cur_record}
    </#list>
</#if>
```

このデフォルトのコードスニペットは、与えられたデータ定義に対するデータベース内のすべての情報を吐き出しますが、これはあまり人間が読めるものではありません。

```
{uuid=52c4ac1c-afe7-963c-49c6-5279b7030a99, recordId=35926, groupId=20126, companyId=20099, userId=20139, userName=Test Test, versionUserId=20139, versionUserName=Test Test, createDate=2018-07-26 14:31:51.056, modifiedDate=2018-07-26 14:31:51.058, DDMStorageId=35927, recordSetId=35922, recordSetVersion=1.0, version=1.0, displayIndex=0, lastPublishDate=null}
```

ここでは、埋め込まれた連絡先データ定義に基づくリストを使用し、［会社名］と［電子メール］フィールドのみを箇条書きで表示するシンプルなテンプレート例をご紹介します。

```
    <#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>

    <h1>Here are contacts by company name and email address.</h1>

    <#if records?has_content>
        <#list records as cur_record>
            <ul>
                <li>
                    <#-- The below gets the Company field and wraps it in an <em> tag -->
                    Company Name: <em>${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("company")?first, locale)}</em><br /> 
                    <#-- The below gets the Email field  and wraps it in an <em> tag --> 
                    Email: ${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("email")?first, locale)} 
                </li> 
            </ul> 
        </#list> 
    </#if>
```

次のようになります。

![会社名とメールアドレスを表示する表示テンプレートの例。](./creating-display-templates/images/02.png)
