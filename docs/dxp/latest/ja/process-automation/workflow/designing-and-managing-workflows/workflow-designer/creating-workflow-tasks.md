# ワークフロータスクの作成

> サブスクライバー

デフォルトの[唯一の承認者の定義](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/single-approver-definition.xml)では、ワークフローのタスクを簡単に紹介しています。 タスクノードはレビューとアップデートの2つしかありません。 コンテンツ制作者がアセットをレビューのために提出すると、ワークフローはレビューノードに入ります。 レビューでは、アセットを承認または拒否できます。 拒否された場合、プロセスはアップデートタスクに移行します。 その後、送信者はアセットを修正し、レビューのために再送信することができます。

![唯一の承認者の定義には、2つのタスクノードがあります。](./creating-workflow-tasks/images/01.png)

多くの場合、タスクノードはワークフロー定義の最も複雑な部分です。 ユーザーまたはリソースアクションにタスクを割り当てることができる割り当て機能を設定できます（[Using Task Nodes](./assigning-task-nodes.md)を参照）。

タスクノードには、通知やアクションも含まれており、これらに[スクリプト](../../developer-guide/using-the-script-engine-in-workflow.md)で複雑さを加えることができます。 [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)を参照してください。

レビューが終了し、承認されたトランジションがトリガーされると、ワークフローは次のノードに進みます。 唯一の承認者プロセスでは、単に承認済み終了ノードに移動します。

## 唯一の承認者ワークフローの作成

次の4つのステップでワークフローを作成します。

  - ワークフローを作成し、名前をつける
  - レビューノードを作成する
  - アップデートノードを作成する
  - 終了ノードを設定する

### ワークフローの作成

1.  [グローバルメニュー] → [アプリケーション] → [ワークフロー]と進みます。
2.  *[ワークフロー]* タブをクリックします。
3.  *追加*（![Add icon](../../../../images/icon-add.png)）をクリックします。
4.  ワークフローに説明的な名前をつけます（「私の唯一の承認者」など）。

### レビューノードの作成

1.  ワークフローデザイナーのキャンバスで、開始ノードと終了ノードの間の古いトランジション（コネクタライン）をマウスで選択し、キーボードのDeleteキーを押して削除します。

2.  タスクノードをキャンバスにドラッグアンドドロップします。

3.  ノードが選択されていないことを確認してから、ポインタを開始ノードの端に移動して、開始ノードとタスクノードを接続します。 カーソルの形が変わったら、開始ノードからのトランジションをクリックしてタスクノードにドラッグします。

4.  トランジションを選択し、名前を*「レビュー」*に変更します。

5.  タスクノードをクリックすると、そのプロパティの更新が始まります。

6.  [名前]フィールドをダブルクリックして、ノードに*「レビュー」*と名前をつけます。

7.  *[通知]* をダブルクリックします。

8.  以下の情報を入力します。

      - **名前**: レビュー通知
      - **テンプレート言語**: Freemarker
      - **テンプレート**: 次のFreemarkerの通知を入力します： `${userName} sent you a ${entryType} for review in the workflow`
      - **通知タイプ**: Ctrlキーを使用して、*[メール]* と*[ユーザー通知]* を選択します。これは複数選択フィールドです。
      - **実行の種類**: 割り当て時
      - **受信者の種類**: タスク担当者

    ![アセットがレビューの準備ができたことを知らせるメールとユーザー通知を送信するためのタスクノードの通知設定を行います。](./creating-workflow-tasks/images/02.png)

    *[セクションを追加]* をクリックします。

    以下の情報を入力します。

      - **名前:** レビュー完了通知
      - **テンプレート言語:** Freemarker
      - **テンプレート:** 次のFreemarkerの通知を入力します： `Your submission was reviewed<#if taskComments?has_content> and the reviewer applied the following ${taskComments}</#if>.`
      - **通知タイプ:** メール
      - **実行の種類:** 処理終了時
      - **受信者の種類:** ユーザー

9.  完了したら、*[保存]* をクリックします。

10. *[Assignments]* をダブルクリックします。 レビュータスクは、ロール、ロールタイプ、特定のユーザー、またはリソースアクションに割り当てることができます。 この例では、レビュータスクをロールタイプに割り当てます。 これらのロールを選択し、新しいロールを追加する必要があるたびに、*[セクションを追加]* をクリックします。

      - アセットライブラリ管理者
      - アセットライブラリコンテンツレビュア
      - アセットライブラリ所有者
      - 組織管理者
      - 組織コンテンツレビュア
      - 組織所有者
      - 管理者
      - ポータルコンテンツレビュア
      - サイト管理者
      - サイトコンテンツレビュア
      - サイトオーナー

    ロールと権限の詳細は、[ロールと権限について](../../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)と[Assigning Users to Roles](../../../../users-and-permissions/roles-and-permissions/assigning-users-to-roles.md)を参照してください。

    ![アセットがレビューの準備ができたことを知らせるメールとユーザー通知を送信するためのタスクノードの通知設定を行います。](./creating-workflow-tasks/images/03.png)

11. 完了したら、*[保存]* をクリックします。

12. 終了ノードをクリックし、名前を*「承認済み」*に変更します。

13. レビューノードを承認済み終了ノードに接続します。 コネクターの名前を*「承認」*とします。

タスクノードが設定され、提出物のレビューの準備が整ったことを知らせる通知が特定のロールに割り当てられたユーザーに送信されます。

また、他のユーザーやロールタイプではなく、リソースアクションにタスクノードを割り当てることもできます。 詳しくは、[Using Task Nodes](./assigning-task-nodes.md)を参照してください。

### アップデートノードの作成

1.  別のタスクノードをキャンバスにドラッグアンドドロップします。

2.  ノードが選択されていないことを確認してから、ポインタを開始ノードの端に移動して、レビューノードと新しいタスクノードを接続します。 カーソルの形が変わったら、レビューノードからのコネクタをクリックして新しいタスクノードにドラッグします。

3.  コネクタを選択して、名前を*「拒否」*に変更します。

4.  タスクノードをクリックすると、そのプロパティの更新が始まります。

5.  *[名前]* フィールドをダブルクリックして、ノードに*「アップデート」*と名前を付けます。

6.  *[通知]* をダブルクリックします。

7.  以下の情報を入力します。

      - **名前:** 作成者への修正通知
      - **テンプレート言語:** Freemarker
      - **テンプレート:** 次のFreemarkerの通知を入力します：`Your submission was rejected by ${userName}; please modify and resubmit.`
      - **通知タイプ:** Ctrlキーを使用して、*[メール]* と*[ユーザー通知]* を選択します。これは複数選択フィールドです。
      - **実行の種類:** 割り当て時
      - **受信者の種類:** タスク担当者

8.  *[Assignments]* をダブルクリックします。 *[ユーザー]* を選択し、*[保存]* をクリックします。

9.  *[アクション]* をダブルクリックします。 以下の情報を入力します。

      - **名前:** 拒否
      - **スクリプト:**
        ``` groovy
         import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
         import com.liferay.portal.kernel.workflow.WorkflowConstants;

         WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("denied"), workflowContext);
         WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("pending"), workflowContext);
        ```
      - 残りはデフォルトのままにします(言語: *groovy*、実行の種類: *割り当て時*)。

10. *[保存]* をクリックします。

11. 何も選択されていないことを確認し、マウスポインタを*アップデート*ノードの端に移動させます。 コネクタを*アップデート*ノードから*レビュー*ノードにドラッグして戻します。

12. 新しいコネクタに*「再送信」*と名前をつけます。

![ワークフローが具体化してきました。 あとは、終了ノードを接続するだけです。](./creating-workflow-tasks/images/04.png)

### 終了ノードの設定

あとは、終了ノードの名前を変更し、ワークフローのステータスを「承認」に設定するように構成するだけです。

1.  コネクタをレビューノードから終了ノードにドラッグします。
2.  コネクタの名前を*「承認」*に変更します。
3.  *終了ノード*をダブルクリックし、名前を*「承認済み」*に変更します。
4.  下部にある*[Publish]* ボタンをクリックして、ワークフローを公開します。

ワークフローの作成とワークフローデザイナの使用方法を学習したところで、ワークフロープロセスを次のレベルに進めることができるノードのタイプ（[フォークと結合](./using-forks-and-joins.md)や[条件](./using-condition-nodes.md)など）を追加する方法について見ていきます。

## 追加情報

  - [ワークフローのアクティブ化](../../using-workflows/activating-workflow.md)
  - [Workflow Nodes](./workflow-nodes.md)
  - [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)
  - [Using Task Nodes](./assigning-task-nodes.md)
