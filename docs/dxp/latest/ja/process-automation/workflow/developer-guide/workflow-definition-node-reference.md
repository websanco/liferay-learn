# ワークフロー定義ノードリファレンス

*ノード*要素とそのサブ要素は、ワークフロー定義を構成する基本的な構成要素です。 多くの場合、ノードはレビュープロセスの実際の段階を反映しています。 このガイドでは、さまざまなタイプのノードとその使用方法について説明します。

  - [ステータスノード](#state-nodes)
  - [条件ノード](#conditions)
  - [フォークと結合](#forks-and-joins)

## ステータスノード

ステータスノードはユーザー入力を必要としません。 通常、ステータスノードはワークフロー定義を開始または終了します。 ワークフローは、ステータスノードの`actions`タグで指定されたもの（通知やカスタムスクリプト）を実行してから、指定されたトランジションに移行します。

初期のステータスノードには、多くの場合、1つのトランジションしか含まれていません。

``` xml
<state>
    <name>created</name>
    <initial>true</initial>
    <transitions>
        <transition>
            <name>Determine Branch</name>
            <target>determine-branch</target>
            <default>true</default>
        </transition>
    </transitions>
</state>
```

ステータスノードで通知またはスクリプトが必要な場合は、`actions`タグを使用できます。 以下は、Groovyスクリプトを含む `action`要素です。 これは、多くの末端ステータスノードに見られるもので、ワークフローでアセットを承認済みとマークします。

``` xml
<actions>
    <action>
        <name>Approve</name>
        <description>Approve</description>
        <script>
            <![CDATA[
            com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(com.liferay.portal.kernel.workflow.WorkflowConstants.getLabelStatus("approved"), workflowContext);]]>
        </script>
        <script-language>groovy</script-language>
        <execution-type>onEntry</execution-type>
    </action>
</actions>
```

## 条件

*条件ノード*は、条件が満たされているかどうかを判別し、満たされている場合は、ワークフローを適切なノードに移行します。 たとえば、提出されたドキュメントが契約書である場合、それは法務チームに送られる必要があります。それ以外の場合は、マーケティングチームに送られます。

以下は、[Category Specific Approval](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/category-specific-definition.xml)ワークフロー定義の`determine-branch`条件を示しています。

``` xml
<condition>
    <name>determine-branch</name>
    <script>
        <![CDATA[
            import com.liferay.asset.kernel.model.AssetCategory;
            import com.liferay.asset.kernel.model.AssetEntry;
            import com.liferay.asset.kernel.model.AssetRenderer;
            import com.liferay.asset.kernel.model.AssetRendererFactory;
            import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
            import com.liferay.portal.kernel.util.GetterUtil;
            import com.liferay.portal.kernel.workflow.WorkflowConstants;
            import com.liferay.portal.kernel.workflow.WorkflowHandler;
            import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

            import java.util.List;

            String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

            WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

            AssetRendererFactory assetRendererFactory = workflowHandler.getAssetRendererFactory();

            long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

            AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);

            AssetEntry assetEntry = assetRendererFactory.getAssetEntry(assetRendererFactory.getClassName(), assetRenderer.getClassPK());

            List<AssetCategory> assetCategories = assetEntry.getCategories();

            returnValue = "Content Review";

            for (AssetCategory assetCategory : assetCategories) {
                String categoryName = assetCategory.getName();

                if (categoryName.equals("legal")) {
                    returnValue = "Legal Review";

                    return;
                }
            }
        ]]>
    </script>
    <script-language>groovy</script-language>
    <transitions>
        <transition>
            <name>Legal Review</name>
            <target>legal-review</target>
            <default>false</default>
        </transition>
        <transition>
            <name>Content Review</name>
            <target>content-review</target>
            <default>false</default>
        </transition>
    </transitions>
</condition>
```

この例では、アセットカテゴリをチェックして、*法務レビュー*タスクに移行するか、または*コンテンツレビュー*タスクに移行するかを選択します。

`returnValue`変数は、条件からトランジションを指し、その値は有効なトランジション名と一致する必要があります。 このスクリプトでは、対象となるアセットを調べ、そのアセットカテゴリーを取得し、初期値として`returnValue`を設定します。 その後、そのアセットが*legal*カテゴリでマークされているかどうかを確認します。 マークされていない場合は、*コンテンツレビュー*（ワークフローのcontent-reviewタスク）を通過し、マークされている場合は、*法務レビュー*（ワークフローのlegal-reviewタスク）を通過します。

## フォークと結合

フォークはワークフロープロセスを分割し、結合はプロセスを統合されたブランチに戻します。 処理は常に結合（またはXOR 結合）を使用して戻す必要があり、ワークフロー定義内のフォークと結合の数は同じである必要があります。

``` xml
<fork>
    <name>fork-1</name>
    <transitions>
        <transition>
            <name>transition-1</name>
            <target>task-1</target>
            <default>true</default>
        </transition>
        <transition>
            <name>transition-2</name>
            <target>task-2</target>
            <default>false</default>
        </transition>
    </transitions>
</fork>
<join>
    <name>join-1</name>
    <transitions>
        <transition>
            <name>transition-4</name>
            <target>EndNode</target>
            <default>true</default>
        </transition>
    </transitions>
</join>
```

アセットが両方のフォークから結合に移行するまで、ワークフローは結合の先に進みません。 ワークフロープロセスをフォークし、フォークが1つだけ完了したときに処理を続行できるようにするには、XOR 結合を使用します。

XOR 結合は、1つの重要な点で結合とは異なります。それは、処理を続行する前に両方のフォークを完了する必要があるという制約がないことです。 フォークのいずれを完了すれば、アセットの処理を続行できます。

``` xml
<join-xor>
    <name>join-xor</name>
    <transitions>
        <transition>
            <name>transition3</name>
            <target>EndNode</target>
            <default>true</default>
        </transition>
    </transitions>
</join-xor>
```

## タスクノード

[タスクノード](./workflow-task-node-reference.md)は、ワークフロー定義の中核となるものです。 これらは、誰かが何らかの方法でアセットと対話する部分です。 タスクには、通知、割り当て、タスクタイマーなどのサブ要素を含めることもできます。

以下は、Category Specific Approval ワークフローの`content-review`タスクで、簡潔にするために`role`割り当てタグの一部を切り取ったものです。

``` xml
<task>
    <name>content-review</name>
    <actions>
        <notification>
            <name>Review Notification</name>
            <template>You have a new submission waiting for your review in the workflow.</template>
            <template-language>text</template-language>
            <notification-type>email</notification-type>
            <notification-type>user-notification</notification-type>
            <execution-type>onAssignment</execution-type>
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
    <task-timers>
        <task-timer>
            <name></name>
            <delay>
                <duration>1</duration>
                <scale>hour</scale>
            </delay>
            <blocking>false</blocking>
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
    <transitions>
        <transition>
            <name>approve</name>
            <target>approved</target>
            <default>true</default>
        </transition>
        <transition>
            <name>reject</name>
            <target>update</target>
            <default>false</default>
        </transition>
    </transitions>
</task>
```

## 追加情報

  - [Crafting XML Workflow Definitions](./crafting-xml-workflow-definitions.md)
  - [Workflow Task Node Reference](./workflow-task-node-reference.md)
