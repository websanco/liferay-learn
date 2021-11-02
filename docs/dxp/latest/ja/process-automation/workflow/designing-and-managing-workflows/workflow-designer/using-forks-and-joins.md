# フォークと結合の使用

> サブスクライバー

ここでは、 *フォーク*、*結合*および*XOR 結合*ノードの使用方法を学びます。 これらのノードは、[Legal-Marketing Definition](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/legal-marketing-definition.xml)サンプルのような複数のレビュー担当者がいるワークフロープロセスに役立ちます。

レビュータスクを分割することで、2つのレビューを同時に行うことができます。 Legal-Marketingのワークフロー例では、プロセスは線形です。ワークフローは公開前にMarketingからLegalに移行しますが、*フォーク*および*結合*（またはXOR 結合）を使用して並行ワークフロープロセスを構築できます。

## フォークノードと結合ノードの使用

1.  *[グローバルメニュー]* → *[アプリケーション]* → *[Process Builder]* に移動します。

2.  *[ワークフロー]* タブをクリックします。

3.  （![Add icon](../../../../images/icon-add.png)）をクリックして新規ワークフローを追加します。

4.  ワークフロー デザイナーのキャンバスで、*開始*ノードと*終了*ノードの間の古いコネクタを削除します。

5.  *フォーク*ノードをキャンバスにドラッグアンドドロップしてから、*開始*ノードを*フォーク*ノードに接続します。 ノードやコネクタの名前は後で変更することができます。

6.  2つの*タスク*ノードをドラッグアンドドロップします。 *フォーク*ノードを2つの*タスク*ノードに接続します。

    ![フォークノードを追加して、次にタスクノードを追加します。](./using-forks-and-joins/images/02.png)

7.  *結合*ノードをドラッグアンドドロップします。 2つの*タスクノード*を*結合*ノードに接続します。

8.  *結合*ノードを*終了*ノードに接続します。

9.  ノードやコネクターの名前を変更し、[アクションや通知](./configuring-workflow-actions-and-notifications.md)を追加します。

    ![ワークフローが完了したら公開します。](./using-forks-and-joins/images/01.png)

10. 完了したら、*[Publish]* をクリックします。

### XOR 結合ノードの使用

通常の結合ノードでは、ワークフローを結合を超えて進めるには、両方の並列実行からのトランジションが呼び出される必要があります。 しかし、代わりにXOR 結合ノードを使用する場合は、いずれかの並列実行からのトランジションが起動している限り、ワークフローは進行します。

## 追加情報

  - [ワークフローのアクティブ化](../../using-workflows/activating-workflow.md)
  - [Workflow Nodes](./workflow-nodes.md)
  - [Creating Workflow Tasks](./creating-workflow-tasks.md)
  - [Using Condition Nodes](./using-condition-nodes.md)
  - [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)
