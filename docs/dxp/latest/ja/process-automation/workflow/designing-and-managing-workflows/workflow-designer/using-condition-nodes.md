# 条件ノードの使用

> サブスクライバー

*条件*ノードは、ワークフローの中で条件付きで行動（移行、割り当てなど）する場合に役立ちます。 複数のレビュアがいて、どのレビュアにタスクを割り当てるべきかを決定しなければならない場合があります。 例えば、法務部によるレビューが必要な文書もあれば、マーケティングチームによるレビューが必要な文書もあります。

条件は、指定された条件が満たされているかどうかをチェックするゲートキーパーの役割を果たし、その条件に基づいて、適切なレビュアを割り当てたり、特定のタスクに移行したりするなど、アセットに対してアクションを起こします。

[Category Specific Definition](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/category-specific-definition.xml)には、条件ノードの例が含まれています。

1.  *[グローバルメニュー]* → *[アプリケーション]* → *[ワークフロー]* → *[Process Builder]* に移動します。

2.  （![Add icon](../../../../images/icon-add.png)）をクリックして新規ワークフローを追加します。

3.  ワークフローデザイナーのキャンバスで、ワークフローの名前を入力します。

4.  *[ソース]* タブをクリックします。

5.  *[ファイルをインポート]* をクリックします。

6.  [Category Specific Definition](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-runtime-impl/src/main/resources/META-INF/definitions/category-specific-definition.xml)をアップロードします。

7.  *[図]* タブをクリックします。

8.  *条件*ノードをダブルクリックして、ノードのプロパティを設定します。

9.  *[スクリプト]* の隣にある*[Value]* をクリックします。

10. 次のようにスクリプトが表示されます。

    ``` groovy
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

    ```

    ![レビューパスを決定するGroovyスクリプトを追加します。](./using-condition-nodes/images/01.png)

    スクリプトは、アセットのカテゴリをループして、文字列`legal`を探します。 それが見つかった場合、ワークフローは法務レビューパスに沿って続行されます。 それ以外の場合は、コンテンツレビューパスに沿って進みます。

11. キャンバスに戻るには、*[保存]* または*[キャンセル]* をクリックします。

![Category Specific Approvalの定義は、条件ノードから始まります。](./using-condition-nodes/images/02.png)

*条件*ノードが3つの異なるタスクノードにどのように接続されているかに注目してください。

  - ドキュメントが`legal`カテゴリにある場合、アセットは法務部に送られます。
  - それ以外の場合、アセットはマーケティングチームに送信されます。
  - *アップデート*ノードは、アセットが拒否された場合に変更を加えるためにアセットを元の作成者に再度割り当てます。

タスクノードの設定方法については、[Creating Workflow Tasks](./creating-workflow-tasks.md)と[Assigning Task Nodes](./assigning-task-nodes.md)を参照してください。

## 追加情報

  - [Managing Workflows](../managing-workflows.md)
  - [Workflow Nodes](./workflow-nodes.md)
  - [Using Forks and Joins](./using-forks-and-joins.md)
  - [Configuring Workflow Actions and Notifications](./configuring-workflow-actions-and-notifications.md)
