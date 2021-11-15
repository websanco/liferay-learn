# ワークフローデザイナーの概要

> サブスクライバー

Process Builderのワークフローデザイナーは、ワークフローのプロセス定義を作成するためのグラフィカルなインターフェイスです。 便利なドラッグアンドドロップのインターフェイスにより、XML定義を手で書くよりも簡単にワークフロー設計ができます。 [コントロールパネル] → [ワークフロー] → [Process Builder]からアクセスできます。

![デザイナーのキャンバスにノードをドラッグアンドドロップします。](./workflow-designer-overview/images/01.png)

```{tip}
また、[Process Builder]の[ソース]タブからXML定義を書き込んだり、アップロードしたりすることもできます。 [Managing Workflows](.../managing-workflows.md#uploading-a-new-workflow-definition) を参照してください。
```

ワークフローデザイナーは、すべての[ワークフローノード](./workflow-nodes.md)のタイプをサポートしています。

  - [開始ノードと終了ノード](./workflow-nodes.md#start-and-end-nodes)
  - [フォークノードと結合ノード](./using-forks-and-joins.md)
  - [条件ノード](./using-condition-nodes.md)
  - [ステータスノード](./workflow-nodes.md#state-nodes)
  - [タスクノード](./creating-workflow-tasks.md)

ドラッグアンドドロップインターフェイスで提供される機能に加えて、Groovy（Java ベースのスクリプト言語）をフル活用して、ワークフローで移動するアセットに対して[プログラム操作](./../../developer-guide/using-the-script-engine-in-workflow.md)を実行することができます。

デフォルトでは、1つのワークフロー定義（唯一の承認者ワークフローの定義）のみがインストールされています。 その他の定義はこちらからダウンロードできます。

  - [Category-Specific Definition](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/category-specific-definition.xml)
  - [Legal Marketing Definition](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/legal-marketing-definition.xml)
  - [Single Approver Definition with Scripted Assignment](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/single-approver-definition-scripted-assignment.xml)
  - [Single Approver Definition](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/single-approver-definition.xml)

## ワークフローの構築

ワークフローを構築するには、グローバルメニュー（![Global Menu](../../../../images/icon-applications-menu.png)）→ [コントロールパネル] → [Process Builder]と進みます。

（![Add icon](../../../../images/icon-add.png)）をクリックして開始します。

各ワークフローノードは、レビュープロセスの開始、アセットの承認または拒否、タスクの再割り当てなど、承認プロセスにおける特定のポイントを表します。

ワークフロートランジションは、各ノードをリンクさせ、レビュープロセスに必要なフローを作成します。 最初のノードの処理が終了すると、トランジションで示された次のノードに処理が進みます。

ワークフローに取り組む準備はできましたか？ 次のステップは、[ワークフローのタスクを作成](./creating-workflow-tasks.md)することです。

## 追加情報

  - [Managing Workflows](../managing-workflows.md)
  - [Workflow Nodes](./workflow-nodes.md)
  - [Using Forks and Joins](./using-forks-and-joins.md)
  - [Using Condition Nodes](./using-condition-nodes.md)
  - [Creating Workflow Tasks](./creating-workflow-tasks.md)
  - [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)
