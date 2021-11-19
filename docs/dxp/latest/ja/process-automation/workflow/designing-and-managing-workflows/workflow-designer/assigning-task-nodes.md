# タスクノードの割り当て

> サブスクライバー

タスクノードでは、人によるインタラクションが必要です。 適切な人に警告を発したり、タスクを適切な人に関連付けるには、それぞれ「[通知](./configuring-workflow-actions-and-notifications.md#adding-notifications)」と「割り当て」を使用します。 タスクノードは、特定のロール、1つのロールタイプ（組織、サイト、または標準ロールのタイプ）の[複数のロール](../../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)、アセット作成者、リソースアクション、または特定のユーザーに割り当てることができます。 さらに、割り当てを定義するスクリプトを書くこともできます（下記参照）。 以下の例では、タスクがポータルコンテンツレビュアロールに割り当てられています。

![タスクノードに割り当てを追加することができます。](./assigning-task-nodes/images/01.png)

## リソースアクションの割り当て

ワークフロータスクを*リソースアクション*に割り当てることができます。 リソースアクションは、ユーザーがアプリケーションやエンティティに対して行う操作です。 例えば、ユーザーが掲示板のメッセージを更新する権限を持っているとします。 これは、ユーザーがリソースを更新できることから、UPDATEリソースアクションと呼ばれます。 ワークフロー定義で割り当てにUPDATEアクションが指定されている場合、ワークフローで処理されているアセットタイプを更新する権限を持つユーザーがタスクに割り当てられます。  1つのタスクに複数の割り当てを設定することができます。

リソースアクションの詳細は、[権限システム](../../../../building-applications/permissions.md)の開発者チュートリアルで詳細な説明を参照してください。

[ソース（ワークフローXML）]タブでの割り当ては次のように表示されます。

``` xml
<assignments>
    <resource-actions>
        <resource-action>UPDATE</resource-action>
    </resource-actions>
</assignments>
```

ワークフローを適切なワークフロー対応のアセットに割り当てることができます。

これで、ワークフローがリソースアクションの割り当てがあるタスクに進むと、リソース（たとえば、掲示板のメッセージ）に対する`UPDATE`権限を持つユーザーにタスクが通知され、タスクを自分に割り当てることができます（通知が[タスク担当者]に設定されている場合）。 具体的には、ユーザーには、*[自分のロールに割り当て済み]* タブに*[マイワークフロータスク]* アプリケーションのタスクが表示されます。

リソースのアクション名には、すべて大文字を使用してください。 一般的なリソースアクションは次のとおりです。

  - UPDATE
  - ADD
  - DELETE
  - VIEW
  - PERMISSIONS
  - SUBSCRIBE
  - ADD\_DISCUSSION

考えられるリソースアクション名は、そのリソースの権限画面から確認できます。 例えば、掲示板では、その画面に表示される権限の1つに*Add Discussion*があります。 これをすべて大文字に変換し、スペースをアンダースコアに置き換えると、アクション名になります。

### スクリプトでの割り当て

また、スクリプトを使って割り当てを管理することもできます。 以下は、スクリプト化された唯一の承認者ワークフロー定義（`single-approver-definition-scripted-assignment.xml`）におけるレビュータスク割り当てのスクリプトです。

``` groovy
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
```

このスクリプトは、タスクを*管理者*ロールに割り当て、アセットの*グループ*が組織であるかどうかを確認します。 組織の場合、それを*組織コンテンツレビュア*のロールに割り当てます。 組織でない場合、*サイトコンテンツレビュア*のロールにタスクを割り当てます。

上記の`roles = new ArrayList<Role>();`の行に注目してください。 スクリプトでの割り当てでは、`roles` 変数で、タスクが割り当てられるロールを指定します。 例えば、`roles.add(adminRole);`が呼び出されると、管理者ロールが割り当てに追加されます。

## 追加情報

  - [Creating Workflow Tasks](./creating-workflow-tasks.md)
  - [Using Task Nodes](./workflow-nodes.md)
