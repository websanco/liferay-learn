# ワークフロータスクノードリファレンス

名前が示すように、タスクは*作業*が行われるワークフローの一部です。 タスクはユーザーに割り当てる必要があります。ユーザーは送信されたアセットを確認し、ワークフローのアセットが公開するための条件を満たしているか、またはさらに作業が必要かを判断します。

他のワークフローノードとは異なり、タスクノードには割り当てがあります。これは、ワークフロープロセスがタスクノードに入ったときに、ユーザーが*何かを行う*（多くの場合は、送信されたアセットの承認または拒否）ことが期待されるためです。

通常、タスクノードには、タスクタイマー、割り当て、アクション（通知とスクリプトを含めることができます）、およびトランジションが含まれます。 通知とアクションはタスクノードに限定されませんが、タスクノードとその割り当ては独自の記事（この記事）に値します。

唯一の承認者定義のレビュータスクを見てみましょう。

``` xml
<task>
    <name>review</name>
    <actions>
        <notification>
            <name>Review Notification</name>
            <template>${userName} sent you a ${entryType} for review in the workflow.</template>
            <template-language>freemarker</template-language>
            <notification-type>email</notification-type>
            <notification-type>user-notification</notification-type>
            <execution-type>onAssignment</execution-type>
        </notification>
        <notification>
            <name>Review Completion Notification</name>
            <template><![CDATA[Your submission was reviewed<#if taskComments?has_content> and the reviewer applied the following ${taskComments}</#if>.]]></template>
            <template-language>freemarker</template-language>
        <notification-type>email</notification-type>
            <recipients>
            <user />
            </recipients>
            <execution-type>onExit</execution-type>
        </notification>
    </actions>
    <assignments>
        <roles>
            <role>
                <role-type>organization</role-type>
                <name>Organization Administrator</name>
        </role>
              ...
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

レビュータスクには2つの`actions`があり、両方とも`<notification>`です。 各通知には、名前、テンプレート、通知タイプ、実行タイプ、および受信者を含めることができます。 通知のほかに、`<action>`タグを使用することもできます 。 これらには名前と[スクリプト](./using-the-script-engine-in-workflow.md)があり、タスクよりもステータスノードで使われることが多いです。

## 割り当て

ワークフロータスクはユーザーが完了します。 割り当てを行うことで、適切なユーザーがタスクにアクセスできるようになります。 割り当ての設定方法を選択できます。 割り当ては以下に追加できます

  - 特定のロール
  - あるロールタイプ（組織、サイト、通常のロールタイプ）の複数のロール
  - アセット作成者
  - リソースアクション
  - 特定のユーザー

さらに、割り当てを定義するスクリプトを書くこともできます。 例えば、[single-approver-definition-scripted-assignment.xml](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/single-approver-definition-scripted-assignment.xml)を参照してください。

``` xml
<assignments>
    <roles>
        <role>
            <role-type>organization</role-type>
            <name>Organization Administrator</name>
        </role>
    </roles>
</assignments>
```

上記の割り当ては、組織管理者がタスクを完了する必要があることを指定しています。

``` xml
<assignments>
    <user>
        <user-id>20156</user-id>
    </user>
</assignments>
```

上記の割り当ては、ユーザーIDが20156のユーザーのみがタスクを完了できることを指定しています。 または、ユーザーの `<screen-name>` または `<email-address>` を指定します。

``` xml
<assignments>
    <scripted-assignment>
        <script>
            <![CDATA[
                    import com.liferay.portal.kernel.model.Group;
                    import com.liferay.portal.kernel.model.Role;
                    import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
                    import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
                    import com.liferay.portal.kernel.util.GetterUtil;
                    import com.liferay.portal.kernel.workflow.WorkflowConstants;

                    long companyId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));

                    long groupId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID));

                    Group group = GroupLocalServiceUtil.getGroup(groupId);

                    roles = new ArrayList<Role>();

                    Role adminRole = RoleLocalServiceUtil.getRole(companyId, "Administrator");

                    roles.add(adminRole);

                    if (group.isOrganization()) {
                        Role role = RoleLocalServiceUtil.getRole(companyId, "Organization Content Reviewer");

                        roles.add(role);
                    }
                    else {
                        Role role = RoleLocalServiceUtil.getRole(companyId, "Site Content Reviewer");

                        roles.add(role);
                    }

                    user = null;
                ]]>
            </script>
        <script-language>groovy</script-language>
    </scripted-assignment>
</assignments>
```

上記の割り当てでは、タスクを*管理者*ロールに割り当て、アセットの*グループ*が組織であるかどうかをチェックします。 組織の場合は、*組織コンテンツレビュア*ロールが割り当てられます。 組織でない場合、タスクは*サイトコンテンツレビュア*のロールに割り当てられます。

上記の`roles = new ArrayList<Role>();`の行に注目してください。 スクリプトでの割り当てでは、`roles` 変数で、タスクが割り当てられるロールを指定します。 例えば、`roles.add(adminRole);`が呼び出されると、管理者ロールが割り当てに追加されます。

## リソースアクションの割り当て

ユーザーは、*アップデート*アクションなどのリソースアクションにタスクを割り当てることができます。 ワークフロー定義で割り当てにUPDATEアクションが指定されている場合、ワークフローで処理されているアセットタイプを更新する権限を持つユーザーがタスクに割り当てられます。  1つのタスクに複数の割り当てを設定することができます。

*リソースアクション*は、ユーザーがアプリケーションやエンティティに対して行う操作です。 例えば、ユーザーが掲示板のメッセージを更新する権限を持っているとします。 これは、ユーザーがリソースを更新できることから、UPDATEリソースアクションと呼ばれます。 リソースアクションの詳細は、[権限システム](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)の開発者チュートリアルで詳細な説明を参照してください。

作成されたすべてのリソースアクションを見つけるには、コントロールパネルのロールの管理アプリケーションへのアクセスが必要です（すなわち、ロールリソースのVIEWアクションの権限が必要です）。

1.  *[コントロールパネル]* → *[ユーザー]* → *[ロール]* へ移動します。
2.  新しい標準ロールを追加します。 詳細は、[ロールの管理](../../../users-and-permissions/roles-and-permissions/creating-and-managing-roles.md)を参照してください。
3.  ロールが追加されたら、ロールの[権限の定義]インターフェイスに移動します。
4.  ワークフローの割り当てを定義するアクションを持つリソースを探します。

割り当てのXMLは次のようになります。

``` xml
<assignments>
    <resource-actions>
        <resource-action>UPDATE</resource-action>
    </resource-actions>
</assignments>
```

これで、ワークフローがリソースアクションの割り当てがあるタスクに進むと、リソース（たとえば、掲示板のメッセージ）に対する`UPDATE`権限を持つユーザーにタスクが通知され、タスクを自分に割り当てることができます（通知が[タスク担当者]に設定されている場合）。 具体的には、ユーザーには、*[自分のロールに割り当て済み]* タブに*[マイワークフロータスク]* アプリケーションのタスクが表示されます。

リソースのアクション名には、すべて大文字を使用してください。 一般的なリソースアクションは次のとおりです。

  - UPDATE
  - ADD
  - DELETE
  - VIEW
  - PERMISSIONS
  - SUBSCRIBE
  - ADD\_DISCUSSION

考えられるリソースアクション名を、そのリソースの権限画面から決定します。 例えば、掲示板では、その画面に表示される権限の1つに*Add Discussion*があります。 これをすべて大文字に変換し、スペースをアンダースコアに置き換えると、アクション名になります。

## タスクタイマー

タスクタイマーは、指定された期間が経過した後にアクションをトリガーします。 タイマーは、タスクが長時間放置されないようにするために便利です。 使用可能なタイマーアクションには、追加の通知の送信、アセットの再割り当て、タイマーアクションの作成などがあります。

``` xml
<task-timers>
    <task-timer>
        <name></name>
        <delay>
            <duration>1</duration>
            <scale>hour</scale>
        </delay>
        <blocking>false</blocking>
        <recurrence>
            <duration>10</duration>
            <scale>minute</scale>
        </recurrence>
        <timer-actions>
            <timer-notification>
                <name></name>
                <template></template>
                <template-language>text</template-language>
                <notification-type>user-notification</notification-type>
            </timer-notification>
        </timer-actions>
    </task-timer>
</task-timers>
```

上記のタスクタイマーは通知を作成します。 `<delay>` タグで期間を指定し、 `<timer-actions>` ブロックで期間が過ぎたときのアクションを指定します。 `<blocking>` 要素は、タイマーアクションを繰り返すかどうかを指定します。 ブロッキングが`false`に設定されている場合、タイマーアクションを繰り返すことができます。 `recurrence`要素では、上記で示したように`duration`と`scale`を使って繰り返し間隔を指定します。 上記のrecurrence要素は、タイマーアクションが最初の発生後10分ごとに再度実行されることを指定しています。 ブロッキングをtrueに設定すると、タイマーアクションが繰り返されなくなります。

``` xml
<timer-actions>
    <reassignments>
       <assignments>
         <roles>
          <role>
              <role-type></role-type>
              <name></name>
          </role>
          ...
         </roles>
       </assignments>
    </reassignments>
</timer-actions>
```

上記のスニペットは、再割り当てアクションを設定する方法を示しています。

`<action>` 要素と同様に、 `<timer-action>` 要素にはスクリプトを含めることができます。 詳細は、[ワークフローでのスクリプトエンジンの使用](./using-the-script-engine-in-workflow.md)を参照してください。

```{note}
`timer-action`には、` execution-type`という1つの例外を除いて、`action`と同じタグをすべて含めることができます。 タイマーアクションは、時間切れになると常にトリガーされるため、たとえば、 実行タイプに`onEntry`を指定しても、タイマーの中では意味がありません。
```

## 追加情報

  - [Crafting XML Workflow Definitions](./crafting-xml-workflow-definitions.md)
  - [Workflow Definition Node Reference](./workflow-definition-node-reference.md)
