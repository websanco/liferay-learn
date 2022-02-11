# ドキュメントとメディアでのワークフローの使用

ユーザーは、_ドキュメントとメディア_ファイルの[ワークフローを実装](../../../process-automation/workflow/using-workflows/activating-workflow.md)できます。  （一般的なワークフローの詳細については、[Introduction to Workflow](../../../process-automation/workflow/introduction-to-workflow.md)を参照してください。）

_ドキュメントとメディア_アプリケーションには、独自の個別のワークフロー設定があります。 たとえば、特定の[フォルダ](../uploading-and-managing/creating-folders.md)のワークフローを有効にして、そのフォルダにアップロードされたすべてのファイルが最初に確認されるようにすることができます。

次に、複数の[ドキュメントタイプ](../uploading-and-managing/managing-metadata/defining-document-types.md)を作成し、レビュープロセスの対象となるドキュメントタイプを指定できます。 たとえば、作成された2つのドキュメントタイプ（法律文書ドキュメントタイプとトレーニングビデオドキュメントタイプ）がある場合、法律文書ドキュメントタイプに対してのみワークフローを有効にできます。 これは、_フォルダ_レベルで設定されます。

## すべてのドキュメントタイプのワークフローを有効にする

1. 右上の（![Actions icon](../../../images/icon-actions.png) ）をクリックし、_［編集］_をクリックします。

    ![［編集］をクリックして、オプション設定にアクセスします。](./using-workflow-with-documents-and-media/images/05.png)

1. ドロップダウンメニューから目的のワークフローの定義を選択します。

    ![ワークフローの定義を選択します。](./using-workflow-with-documents-and-media/images/06.png)

1. 完了したら、_［保存］_をクリックします。

ワークフローを有効にすると、ユーザーがファイルをアップロードするたびに（ドキュメントタイプに関係なく）、_［公開］_ボタンが_［公開申請］_ボタンに置き換えられます。

![展開して、［Document Type Restrictions and Workflow］ラジオボタンを表示します。](./using-workflow-with-documents-and-media/images/04.png)

## フォルダのワークフローを有効にする

1. （ご自身のサイト）&rarr; _［Content & Data］_ &rarr; _［ドキュメントとメディア］_に移動します。
1. 目的のフォルダの横にある（![Actions icon](../../../images/icon-actions.png)）&rarr; _［編集］_の順にクリックします。

    ![［編集］をクリックして、フォルダのワークフロー設定にアクセスします。](./using-workflow-with-documents-and-media/images/01.png)

1. _［Document Type Restrictions and Workflow］_セクションを展開します。

    ![展開して、［Document Type Restrictions and Workflow］ラジオボタンを表示します。](./using-workflow-with-documents-and-media/images/02.png)

1. 目的の設定を選択します。 フォルダに親フォルダ（ここでは_ドキュメントとメディアホーム_フォルダ）を継承させるか、特定のドキュメントタイプを指定するか、この指定されたフォルダ内のすべてのドキュメントタイプのワークフローを有効にすることができます。
1. _［Default Workflow for this Folder］_が選択されている場合は、ワークフローの定義を選択します。

    ![展開して、［Document Type Restrictions and Workflow］ラジオボタンを表示します。](./using-workflow-with-documents-and-media/images/03.png)

1. 完了したら、_［保存］_をクリックします。

このフォルダのワークフローが有効になっています。

親（_ホーム_など）フォルダでワークフローが有効になっている場合でも、ユーザーは特定のフォルダ（またはサブフォルダ）のワークフローを_無効_にすることもできます。  これを行うには、3番目のオプションである_［Default Workflow for this Folder (Folder Name)］_を選択してから、_［No Workflow］_を選択します。 無効にすると、このフォルダにアップロードされたファイルはレビュープロセスを必要としません。

## 追加情報

* [Activating Workflow](../../../process-automation/workflow/using-workflows/activating-workflow.md)
* [ドキュメントとメディアUIリファレンス](../documents-and-media-ui-reference.md)
