# ドキュメントとメディアでのワークフローの使用

ユーザーは、 **ドキュメントとメディア** ファイルの[ワークフローを実装](../../../process-automation/workflow/using-workflows/activating-workflow.md)できます。  （一般的なワークフローの詳細については、 [ワークフローの概要](../../../process-automation/workflow/introduction-to-workflow.md) を参照してください。）

**ドキュメントとメディア** アプリケーションには、独自の個別のワークフロー設定があります。 たとえば、特定の[フォルダ](../uploading-and-managing/creating-folders.md)のワークフローを有効にして、そのフォルダにアップロードされたすべてのファイルが最初に確認されるようにすることができます。

次に、複数の[ドキュメントタイプ](../uploading-and-managing/managing-metadata/defining-document-types.md)を作成し、レビュープロセスの対象となるドキュメントタイプを指定できます。 たとえば、作成された2つのドキュメントタイプ（法律文書ドキュメントタイプとトレーニングビデオドキュメントタイプ）がある場合、法律文書ドキュメントタイプに対してのみワークフローを有効にできます。 これは、 **フォルダ** レベルで設定されます。

<a name="すべてのドキュメントタイプのワークフローを有効にする" />

## すべてのドキュメントタイプのワークフローを有効にする

1. 右上の（![Actions icon](../../../images/icon-actions.png) ）をクリックし、 ［**編集**］ をクリックします。

    ![［編集］をクリックして、オプション設定にアクセスします。](./using-workflow-with-documents-and-media/images/05.png)

1. ドロップダウンメニューから目的のワークフローの定義を選択します。

    ![ワークフローの定義を選択します。](./using-workflow-with-documents-and-media/images/06.png)

1. 完了したら、 ［**保存**］ をクリックします。

ワークフローを有効にすると、ユーザーがファイルをアップロードするたびに（ドキュメントタイプに関係なく）、 ［**公開**］ ボタンが ［**公開申請**］ ボタンに置き換えられます。

![展開して、［Document Type Restrictions and Workflow］ラジオボタンを表示します。](./using-workflow-with-documents-and-media/images/04.png)

<a name="フォルダのワークフローを有効にする" />

## フォルダのワークフローを有効にする

1. （ご自身のサイト）&rarr; ［**Content & Data**］ &rarr; ［**ドキュメントとメディア**］ に移動します。
1. 目的のフォルダの横にある（![Actions icon](../../../images/icon-actions.png)）&rarr; ［**編集**］ の順にクリックします。

    ![［編集］をクリックして、フォルダのワークフロー設定にアクセスします。](./using-workflow-with-documents-and-media/images/01.png)

1. ［**Document Type Restrictions and Workflow**］ セクションを展開します。

    ![展開して、［Document Type Restrictions and Workflow］ラジオボタンを表示します。](./using-workflow-with-documents-and-media/images/02.png)

1. 目的の設定を選択します。 フォルダに親フォルダ（ここでは **ドキュメントとメディアホーム** フォルダ）を継承させるか、特定のドキュメントタイプを指定するか、この指定されたフォルダ内のすべてのドキュメントタイプのワークフローを有効にすることができます。
1. ［**Default Workflow for this Folder**］ が選択されている場合は、ワークフローの定義を選択します。

    ![展開して、［Document Type Restrictions and Workflow］ラジオボタンを表示します。](./using-workflow-with-documents-and-media/images/03.png)

1. 完了したら、 ［**保存**］ をクリックします。

このフォルダのワークフローが有効になっています。

親（**ホーム** など）フォルダでワークフローが有効になっている場合でも、ユーザーは特定のフォルダ（またはサブフォルダ）のワークフローを **無効** にすることもできます。  これを行うには、3番目のオプションである ［**Default Workflow for this Folder (Folder Name**)］ を選択してから、 ［**No Workflow**］ を選択します。 無効にすると、このフォルダにアップロードされたファイルはレビュープロセスを必要としません。

<a name="追加情報" />

## 追加情報

* [ワークフローのアクティブ化](../../../process-automation/workflow/using-workflows/activating-workflow.md)
* [ドキュメントとメディアUIリファレンス](../documents-and-media-ui-reference.md)
