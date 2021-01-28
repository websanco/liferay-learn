# ワークフローの管理

*ワークフロー*アプリケーションを使用すると、ユーザーはワークフロープロセスを管理できます。新しいワークフロー定義をアップロードして、定義を変更できます。 最後に、ワークフローを削除できます。

## ワークフロー定義の公開権限

ワークフロー定義を編集または公開する権限を持つユーザーは、ワークフローに [Groovyスクリプト](../../../system-administration/using-the-script-engine/using-the-script-engine.md) を追加できます。 スクリプトエンジンへのアクセスとは、サーバーのJava仮想マシン（JVM）へのアクセスを意味します。 したがって、公開（または編集）の権限を持つユーザーは、Liferay DXPの個別の [仮想インスタンス](../../../system-administration/virtual-instances.md) に含まれているデータなど、JVMの到達範囲内の任意のデータにアクセスできます。

この広範囲にわたるアクセスのため、ワークフロー定義を作成または編集する権限は、デフォルトの仮想インスタンスの通常の管理者に制限されています。 Liferay DXPのお客様は、Kaleo DesignerおよびKaleo Forms Adminアプリケーションにアクセスできるサイト管理者もワークフローを編集および公開できます。

これらのロールを持つユーザーに、追加の仮想インスタンスでのワークフローパブリケーションアクセスを許可するには、

1.  [これらの管理者にアクセスを付与する ](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)を理解していることを確認してください。
2.  コントロールパネル→システム設定→ワークフロー→ワークフロー定義に移動します。
3.  *管理者が公開を許可し、編集ワークフロー*。を設定するにはチェックボックスにチェックを入れます。

これは、システムに追加された仮想インスタンスにのみ適用されます。 デフォルトの仮想インスタンスは、コントロールパネル→ワークフロー→プロセスビルダーを介して通常の管理者に、Kaleo Forms Adminアプリケーションを介してLiferay DXPサブスクライバーに、そのままのワークフローパブリケーションアクセスを提供します。

## 新しいワークフロー定義のアップロード

ワークフロープロセスの定義は、[Liferayで作成](./building-workflows.md)するか、`XML`形式でアップロードできます。 DXPワークフローの定義は`XML`形式で記述されています。 詳細については、[Introduction to Crafting XML Workflow Definitions](https://help.liferay.com/hc/articles/360029147791-Introduction-to-Crafting-XML-Workflow-Definitions)を参照してください。

新しい定義をアップロードするには：

1.  *[Control Panel]* → *[Workflow]* → *[Process Builder]* に移動します。

2.  *[New Workflow]* ボタン（![Add](../../../images/icon-add.png)）をクリックします。

3.  埋め込みワークフローに基づくローカルのXML定義ファイルがある場合は、DXPにアップロードできます。 *[Source]* タブをクリックします。

4.  新しい定義の名前を入力します。

5.  XMLファイルの内容をコピーしてサンプルコードを上書きするか、*[import a file]* をクリックしてファイルをアップロードします。

    ![新しい定義をアップロードして追加する](./managing-workflows/images/01.png)

6.  *[保存]* ボタンをクリックします。

7.  *[Publish]* ボタンをクリックします。

新しく追加された定義をワークフロープロセスとして使用する準備ができました。

## ワークフロー定義の編集

ワークフローを編集するには：

1.  *[Control Panel]* → *[Workflow]* → *[Process Builder]* に移動します。

2.  ワークフローの*アクション*ボタン（![Actions](../../../images/icon-actions.png)）をクリックし、*[Edit]* をクリックします。

    ![定義の編集](./managing-workflows/images/03.png)

ワークフロー定義への変更は、*[Details]* メニューと*[Revision History]* メニューで追跡されます。

### ワークフローリビジョンの表示と復元

ワークフロー定義の詳細とリビジョン履歴を表示するには：

1.  *情報*ボタン（![Information](../../../images/icon-information.png)）をクリックします。

2.  *[Revision History]* タブの前のバージョンの横にある*アクション*ボタン（![Actions](../../../images/icon-actions.png)）をクリックし、*[Preview]* または*[Restore]* を選択します。

    ![ワークフローの以前のバージョンを表示および復元します。](./managing-workflows/images/02.png)

3.  *[Restore]* をクリックして成功メッセージが表示された場合、以前のバージョンがワークフローの現在のバージョンです。 これで、復元されたバージョンのワークフローを編集できます。

4.  編集が必要な場合は、編集して*[Update]* をクリックします。 これにより、ワークフローの別のバージョンが作成されます。

## ワークフロー定義の削除

ワークフローには2つのステータスがあります。

  - **Published：**検証が完了し、ワークフローをアセットに割り当てることができます。
  - **Unpublished：**検証は非公開ワークフローでは実行されず、公開されるまでアセットに割り当てることはできません。

*[Unpublished]* ワークフローのみ削除可能です。

*[Published]* ワークフローの定義は、現在アセットタイプに関連付けられている場合は削除できません。 ユーザーは、最初にワークフロープロセスを非アクティブ化してから、ワークフローを*[Unpublished]* ステータスにする必要があります。

1.  アセットタイプのワークフローを非アクティブ化するには、各アセットタイプの[Activating Workflow](./activating-workflow.md)の記事にある手順を逆にして、*[No Workflow]* を選択します。

2.  *[Control Panel]* → *[Workflow]* → *[Process Builder]* に移動します。

3.  ワークフローの*アクション*ボタン（![Actions](../../../images/icon-actions.png)）をクリックし、*[Unpublish]* をクリックします。

    ![ワークフローを非公開にする](./managing-workflows/images/04.png)

4.  選択した定義が*[Unpublished]* セクションに表示されます。 ワークフローの*アクション*ボタン（![Actions](../../../images/icon-actions.png)）をクリックし、*[Delete]* をクリックします。

    ![ワークフローの削除](./managing-workflows/images/05.png)

## ワークフロー定義権限の設定

*システム設定*では、通常の管理者がワークフローとスクリプトを公開するためのアクセス許可を有効または無効にできます。

![管理者がワークフロー定義を公開および編集するには、事前に明示的なアクセス権を付与する必要があります。](./managing-workflows/images/06.png)

## 追加情報

  - [Introduction to Crafting XML Workflow Definitions](https://help.liferay.com/hc/articles/360029147791-Introduction-to-Crafting-XML-Workflow-Definitions)
  - [Workflow Designer（サブスクリプション）](https://help.liferay.com/hc/articles/360028821892-Workflow-Designer)
