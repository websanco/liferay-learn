# アセットの確認

アセットのワークフローが[アクティブ化](./activating-workflow.md)されると、1人以上のユーザーが公開前にそれを確認する必要があります。 ワークフローレビュープロセスは、特定のユーザーまたは特定のロール（たとえば、ポータルまたはサイトのコンテンツレビュー担当者）に割り当てることができます。 後者の場合、そのロールに割り当てられた人であれば提出物を承認または拒否できます。 たとえば、初期設定の*Single Approver*プロセスを使用している場合、ワークフロータスクは、ポータルまたはサイトのコンテンツレビュー担当者、または管理者権限を持つユーザーに割り当てられます。

アセットが提出されると、*ワークフロー*アプリケーションは可能性のあるすべてのレビュー担当者に通知を送信します。 通知にアクセスするには、アバターをクリックして、*[Notifications]* を選択します。

![アセットをレビューする準備ができたという通知がワークフローから送信されます。](./reviewing-assets/images/01.png)

## アセットの確認

アセットの確認を開始するには、タスクを自分自身に割り当てる必要があります。 Single Approverワークフローを使用する場合、タスクはデフォルトでロールに割り当てられることに注意してください。 （ロールではなく、特定のユーザーにレビュータスクを割り当てる[カスタムワークフローを構築](./building-workflows.md)することもできます。）

### レビュータスクの割り当て

1.  アバターをクリックして、*[My Workflow Tasks]* を選択します。

2.  ユーザーに直接割り当てられたすべてのワークフロータスクは、[My Workflow Task]ウィジェットの*[Assigned to Me]* タブに一覧表示されます。

    ![ユーザーに割り当てられたアセットは、[Assigned to Me]にリストされます。](./reviewing-assets/images/02.png)

3.  タスクを要求するには、*[Assigned to My Roles]* タブをクリックします。

    ![ロールに割り当てられたアセットは、関連する各ユーザーの[Assigned to My Roles]タブにリストされます。](./reviewing-assets/images/03.png)

4.  アセットの*アクション*ボタン（![Actions](../../../images/icon-actions.png)）をクリックし、*[Assign to Me]* を選択します。

    ![タスクを自分に割り当てます。](./reviewing-assets/images/04.png)

5.  *[Comment]* テキストフィールドにオプションのコメントを入力します。

6.  *[Done]* をクリックします。

### タスクの承認または却下

タスクを自分に割り当てたら、提出物を承認または拒否できます。

1.  アセットを確認するには、アセットの名前をクリックします。 ここには、アセットのプレビュー（*Wikiページ*）とレビューステータスがあります。

    ![アセットを確認します。](./reviewing-assets/images/05.png)

2.  *アクション*ボタン（![Actions](../../../images/icon-actions.png)）をクリックし、*[Approve]* または*[Reject]* をクリックします。

    ![アセットを承認または拒否します。](./reviewing-assets/images/06.png)

3.  オプションでコメントを入力して、*[Done]* をクリックします。

タスクが完了すると、次のいずれかの処理が行われます。

  - 提出物が拒否された場合、アセットは公開されません。 アセットはワークフロープロセスを終了します。 元の作成者には、ユーザーの*[Notifications]* で、提出物が拒否され再提出する前に修正する必要があることが通知されます。 *[My Workflow Tasks]* で*[Update]* とマークされます。
  - 複数のレビュー担当者がいる場合、タスクはチェーン内の次のレビュー担当者に進みます。
  - 提出物が承認され、レビュー担当者が1人のみの場合、タスクは*[Assigned to Me]* タブの*[Completed]* セクションに移動します。 アセットが公開されます。

![完了したタスクは[Assigned to Me]タブにあります。](./reviewing-assets/images/07.png)

承認されたアセット（Wikiページ）がウィジェットに公開されます。

## 追加情報

  - [Managing Notifications and Requests](../../../collaboration-and-social/notifications-and-requests/user-guide/managing-notifications-and-requests.md)
  - [Workflow Task Nodes](https://help.liferay.com/hc/articles/360028834732-Workflow-Task-Nodes#assignments)
  - [Workflow Notifications](https://help.liferay.com/hc/articles/360028834772-Workflow-Notifications)
