# ワークフローのアクションと通知の設定

> サブスクライバー

ワークフローデザイナーを使用して、ユーザーは各ノードのワークフローのアクションと通知を設定できます。

アクションは非常に適応性が高く、アクションが発生するタイミングを、ノードに入る前、ノードから出た後、タスクノードが割り当てられた後などに指定することができます。 例えば、レビュアがWebコンテンツの記事を拒否した場合、ワークフローアクションはアセットのステータスを*[保留中]* に設定し、元の作成者に記事を自動的に再割り当てします。

![タスクノードにアクションを追加することができます。](./configuring-workflow-actions-and-notifications/images/01.png)

ワークフロー通知は、タスク担当者にワークフローに注意が必要であることを通知したり、プロセスのステータスについてアセット作成者を更新したりするために送信されます。 通知は、ワークフロー内のタスクやその他のタイプのノードに対して送信することができます。

![タスクノードに通知を追加することができます。](./configuring-workflow-actions-and-notifications/images/02.png)

ただし、すべてのノードでワークフローのアクションや通知が必要なわけではありません。 通常、レビュープロセスが開始または終了したことを元の作成者に通知する場合を除き、開始ノードと終了ノードにはアクションや通知はありません。

唯一の承認者の定義では、2つの[タスクノード（レビューとアップデート）](./creating-workflow-tasks.md)にアクションと通知があります。

## アクションの追加

提出物にさらに作業が必要な場合は、タスクの*アップデート*ノードに拒否*アクション*を追加します。 拒否アクションにはGroovyスクリプトが含まれており、実行されると、アセットのステータスが*「拒否」*に設定され、その次に*「保留中」*に設定されます。

以下の手順に従ってください。

1.  *[グローバルメニュー]* → *[アプリケーション]* → *[Process Builder]* に移動します。

2.  *[ワークフロー]* タブをクリックします。

3.  ワークフローの定義をクリックします（例：*唯一の承認者*）。

4.  *[アップデート]* ノードをクリックします。

    ![アップデートノードを修正します。](./configuring-workflow-actions-and-notifications/images/04.png)

5.  *[アクション]* をダブルクリックして、アクションを定義します。

6.  名前フィールドに*「拒否」*と入力します。

7.  *[スクリプト言語]* ドロップダウンメニューから*[Groovy]* を選択します。

8.  *[実行の種類]* ドロップダウンメニューから*[割り当て時]* を選択します。

9.  *[スクリプト]* フィールドにスクリプトを入力します。 唯一の承認者ワークフローには、アセットのステータスを*「拒否」*に設定し、その次に *「保留中」*に設定するGroovyで書かれたアクションを含むアップデートタスクが含まれています。

    ``` java
    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;

    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("denied"), workflowContext);
    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("pending"), workflowContext);
    ```

    ![拒否アクションを追加します。](./configuring-workflow-actions-and-notifications/images/05.png)

10. 完了したら、*[保存]* をクリックします。

*アップデート*ノードを設定したら、提出物が現時点で拒否され、さらに作業が必要であることをアセット作成者に知らせる通知を追加します。

## 通知の追加

アセット作成者に提出物にさらなる作業が必要なことや、再割り当てされたことを知らせる通知を*アップデート*タスクノードに追加します。

以下の手順に従ってください。

1.  *アップデート*ノードの [プロパティ] タブにある*[通知]* をダブルクリックします。

![通知は[プロパティ]タブにあります。](./configuring-workflow-actions-and-notifications/images/03.png)

1.  次のように入力します：

      - **名前:** 作成者への修正通知
      - **説明**: この通知の説明を入力します

2.  *[テンプレート言語]* ドロップダウンメニューから*[FreeMarker]* を選択します。

3.  *[テンプレート]* フィールドにメッセージを入力します。

      - `Your submission was rejected by ${userName}, please modify and resubmit.`

4.  通知タイプを選択します。このフィールドは複数選択可能なので、複数のユーザーを設定することができます。

      - メールアドレス
      - ユーザー通知

5.  *[実行の種類]* ドロップダウンメニューから *[割り当て時]* を選択します。 ユーザーがこのタスクに割り当てられたときに通知が送られます。

6.  受信者の種類（*アセット作成者*）を選択します。

    ![提出物が拒否されたことを作成者に知らせる通知をアップデートノードに追加します。](./configuring-workflow-actions-and-notifications/images/06.png)

7.  完了したら、*[保存]* をクリックします。

アップデートノードの通知が追加されました。

## 追加情報

  - [Creating Workflow Tasks](./creating-workflow-tasks.md)
  - [Using Forks and Joins](./using-forks-and-joins.md)
  - [Using Condition Nodes](./using-condition-nodes.md)
  - [Workflow Nodes](./assigning-task-nodes.md)
