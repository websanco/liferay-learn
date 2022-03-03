# XMLワークフロー定義の作成

Liferay DXPのすべてのワークフロー定義は、XML形式で記述されています。 独自のワークフロー定義を作成するためには、実際の承認プロセスを反映した定義が必要です。

```tip::
   DXPを使用しているサブスクライバーは、グラフィカルデザイナー<../user-guide/workflow-designer/workflow-designer-overview.md>`_を使用してワークフローを作成できます。 すでにXMLでワークフローを作成している場合は、それをアップロードしてGUIで続行できます。 
```

1. グローバルメニュー（![Global Menu](../../../images/icon-applications-menu.png)）を開きます。 ［ワークフロー］で、 ［**プロセスビルダー**］ を選択します。

1. 新しいワークフロー定義を追加するには、 ![add](../../../images/icon-add.png) アイコンをクリックします。

1. ワークフローの定義は、エディターに入力することも、ローカルで作成したものを読み込むこともできます。

ワークフローを公開すると、ワークフローが有効な場所であればどこでも適用できるようになります。

<a name="existing-workflow-definitions" />

## 既存のワークフロー定義

デフォルトでは、唯一の承認者という1つのワークフロー定義のみがインストールされています。 また、Liferayのソースコードにもいくつか組み込まれています。 これらの定義は、ここで説明するすべての機能を理解する良い例となります。

* [カテゴリー別](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/category-specific-definition.xml)
* [リーガルマーケティング](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/legal-marketing-definition.xml)
* [唯一の承認者](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/single-approver-definition.xml)
* [唯一の承認者スクリプトでの割り当て](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/single-approver-definition-scripted-assignment.xml)　以下では最もシンプルなワークフローである油井胃兪の承認者を使って、基本を学習します。 唯一の承認者のワークフローには、［開始］と［終了］の2つの必須ステータスが含まれており、それぞれ ［**作成済み**］ と ［**承認済み**］ という名前が付けられています。 また、 ［**レビュー**］ と ［**アップデート**］ という2つのタスクも含まれています。 これらのタスクは、 **承認** 、 **拒否** 、 **再送信** などの **アクション** を定義します。

ワークフローを構成しているコンポーネントに分解するときは、［ステータス］、［タスク］、［アクション］について考えます。 それらを定義したら、作業を開始する準備が整います。 これで、唯一の承認者のワークフローがどのように機能するかを確認して、すべてをまとめる準備が整いました。

<a name="schema" />

## スキーマ

ワークフロー定義のストラクチャーは、そのXSDファイル [`liferay-workflow-definition-7_3_0.xsd`](https://www.liferay.com/dtd/liferay-workflow-definition_7_3_0.xsd) で定義されています。

ワークフロー定義ファイルの先頭でスキーマを宣言します。

```xml
<?xml version="1.0"?>
<workflow-definition
    xmlns="urn:liferay.com:liferay-workflow_7.3.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="urn:liferay.com:liferay-workflow_7.3.0
        http://www.liferay.com/dtd/liferay-workflow-definition_7_3_0.xsd">
```

<a name="metadata" />

## メタデータ

定義には、名前、説明、バージョンを付けます。

```xml
<name>Single Approver</name>
<description>A single approver can approve a workflow content.</description>
<version>1</version>
```

<a name="start-and-end-nodes" />

## 開始ノードと終了ノード

各ワークフロー定義は、 **ステータスノード** で開始および終了します。 [唯一の承認者](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/single-approver-definition.xml) から次のような **開始** ノードを作成します。

```xml
<state>
    <name>created</name>
    <initial>true</initial>
    <transitions>
        <transition>
            <name>review</name>
            <target>review</target>
        </transition>
    </transitions>
</state>
```

この例では、 **開始** ノードに以下のようなプロパティが設定されています。

* それが初期状態です。
* ノードは、 **レビュー** と呼ばれる[**タスク** ノード](./workflow-task-node-reference.md)に移行します。

**終了** ノードは次のようになります。

```xml
    <state>
        <name>approved</name>
        <actions>
            <action>
                <name>approve</name>
                <script>
                    <!［CDATA［
                        import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
                        import com.liferay.portal.kernel.workflow.WorkflowConstants;

                        WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
                    ］］>
                </script>
                <script-language>groovy</script-language>
                <execution-type>onEntry</execution-type>
            </action>
        </actions>
    </state>
```

ワークフローが最終ステータスに移行すると、送信が承認されます。 例をシンプルにするために、通知機能は含まれていませんが、追加することも可能でした。 終了ノードには、ワークフローステータスを`approved`に設定する[スクリプト](./using-the-script-engine-in-workflow.md)があります

詳しくは、[ワークフローの定義ノードリファレンス](./workflow-definition-node-reference.md) を参照してください。

<a name="task-nodes" />

## タスクノード

タスクノードは、ユーザーがワークフロープロセスで行うべきことを定義します。 他のワークフローノードとは異なり、タスクノードには割り当てがあり、タスクをユーザーや[ロール](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)に割り当てることができます。

タスクノードには、タスクタイマー、アクション（通知やスクリプトを含む）、トランジションを含めることができます。 詳しくは、[ワークフロー タスクノードリファレンス](./workflow-task-node-reference.md)を参照してください。 唯一の承認者のワークフローには、 **レビュー** と **アップデート** の2つのタスクが含まれています。

### レビュータスクノードの作成

レビュータスクには、［拒否］または［承認］の2つの結果があります。 これには、提出物のレビューの準備が整ったことをレビュアに通知する`<notification>`要素が含まれています。 ユーザーに割り当てられた場合、そのユーザーはアセットをレビューする必要があります。 ロールに割り当てられている場合、そのロールを持つ人は誰でもアセットをレビューできます。

可能なトランジションには、 **承認** と **拒否** の2つがあります。 承認されると、ワークフローは終了ステータスに移行します。このステータスは、以前は`approved`と呼ばれていました。 拒否された場合、ワークフローはアップデートタスクに移行します。

```xml
    <task>
        <name>review</name>
        <actions>
            <notification>
                <name>Review Notification</name>
                <template>${userName} sent you a ${entryType} for review in the workflow.</template>
                <template-language>freemarker</template-language>
                <notification-type>email</notification-type>
                <notification-type>user-notification</notification-type>
                <recipients receptionType="to">
                    <assignees />
                </recipients>
                <execution-type>onAssignment</execution-type>
            </notification>
            <notification>
                <name>Review Completion Notification</name>
                <template><![CDATA[Your submission was reviewed<#if taskComments?has_content> and the reviewer applied the following ${taskComments}</#if>.]]></template>
                <template-language>freemarker</template-language>
                <notification-type>email</notification-type>
                <recipients receptionType="to">
                    <user />
                </recipients>
                <execution-type>onExit</execution-type>
            </notification>
        </actions>
        <assignments>
            <roles>
                <role>
                    <role-type>depot</role-type>
                    <name>Asset Library Administrator</name>
                </role>
                <role>
                    <role-type>depot</role-type>
                    <name>Asset Library Content Reviewer</name>
                </role>
                <role>
                    <role-type>depot</role-type>
                    <name>Asset Library Owner</name>
                </role>
                <role>
                    <role-type>organization</role-type>
                    <name>Organization Administrator</name>
                </role>
                <role>
                    <role-type>organization</role-type>
                    <name>Organization Content Reviewer</name>
                </role>
                <role>
                    <role-type>organization</role-type>
                    <name>Organization Owner</name>
                </role>
                <role>
                    <role-type>regular</role-type>
                    <name>Administrator</name>
                </role>
                <role>
                    <role-type>regular</role-type>
                    <name>Portal Content Reviewer</name>
                </role>
                <role>
                    <role-type>site</role-type>
                    <name>Site Administrator</name>
                </role>
                <role>
                    <role-type>site</role-type>
                    <name>Site Content Reviewer</name>
                </role>
                <role>
                    <role-type>site</role-type>
                    <name>Site Owner</name>
                </role>
            </roles>
        </assignments>
        <transitions>
            <transition>
                <name>approve</name>
                <target>approved</target>
            </transition>
            <transition>
                <name>reject</name>
                <target>update</target>
                <default>false</default>
            </transition>
        </transitions>
    </task>
```

**レビュー** タスクノードが追加され、設定されました。 Excellent! あとは、アップデートタスクを残すのみです。

### アップデートタスクノードの作成

提出物がレビュータスクで **拒否** トランジションに入ると、アップデートタスクに送られ、レビューのために再提出できるようになります。 アセットがアップデートタスクに到着すると、似たような名前の **拒否** アクションが実行され、ワークフローのステータスが`denied`に割り当てられ、次に`pending`に割り当てられます。 元の作成者には通知が送られます。 また、そのアセットは元の作成者に再度割り当てられます。 ここで、元の作成者は、アセットを編集して拒否される原因となった問題を解決した後、アセットを再送信できます。

```xml
    <task>
        <name>update</name>
        <actions>
            <action>
                <name>reject</name>
                <script>
                    <![CDATA[
                        import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
                        import com.liferay.portal.kernel.workflow.WorkflowConstants;

                        WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("denied"), workflowContext);
                        WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("pending"), workflowContext);
                    ]]>
                </script>
                <script-language>groovy</script-language>
                <execution-type>onAssignment</execution-type>
            </action>
            <notification>
                <name>Creator Modification Notification</name>
                <template>Your submission was rejected by ${userName}, please modify and resubmit.</template>
                <template-language>freemarker</template-language>
                <notification-type>email</notification-type>
                <notification-type>user-notification</notification-type>
                <recipients receptionType="to">
                    <user />
                </recipients>
                <execution-type>onAssignment</execution-type>
            </notification>
        </actions>
        <assignments>
            <user />
        </assignments>
        <transitions>
            <transition>
                <name>resubmit</name>
                <target>review</target>
            </transition>
        </transitions>
     </task>
```

<a name="conclusion" />

## まとめ

ここで、終了タグを追加します。

```xml
</workflow-definition>
```

これで唯一の承認者のワークフローが完成しました。 ワークフローがどのように作成されるかがわかったところで、フォーク、結合、条件など、その他の可能なオプションについて学びましょう。 Liferayのワークフローシステムには、必要なプロセスを実装することができます。

<a name="additional-information" />

## 追加情報

* [ワークフロー定義ノードリファレンス](./workflow-definition-node-reference.md)
* [ワークフロータスクノードリファレンス](./workflow-task-node-reference.md)
* [ワークフローでのスクリプトエンジンの使用](./using-the-script-engine-in-workflow.md)
* [ワークフローの管理](../designing-and-managing-workflows/managing-workflows.md)
* [ワークフローデザイナーの概要](../designing-and-managing-workflows/workflow-designer/workflow-designer-overview.md)
* [ロールと権限について](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)
