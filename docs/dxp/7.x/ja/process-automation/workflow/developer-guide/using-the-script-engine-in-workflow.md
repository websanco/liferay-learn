# ワークフローでのスクリプトエンジンの使用

Liferayの[ワークフローエンジン](../user-guide/introduction-to-workflow.md)は、エンタープライズ環境でコンテンツを確認および承認するための堅牢なシステムです。 スクリプトを利用しなくても、強力で堅牢なワークフローソリューションです。 スクリプトを追加すると、次のレベルに進みます。 これらのスクリプトは[スクリプトコンソール](../../../system-administration/using-the-script-engine/running-scripts-from-the-script-console.md)からは実行されませんが、[XMLワークフロー定義](https://help.liferay.com/hc/en-us/articles/360029147791-Introduction-to-Crafting-XML-Workflow-Definitions)に埋め込まれ、ワークフローの実行中に実行されます。

ワークフロースクリプトのトピックは次のとおりです。

  - [ワークフローノードへのスクリプトの追加](#adding-scripts-to-workflow-nodes)
  - [定義済み変数の使用](#predefined-variables)
  - [スクリプトの例](#script-examples)
  - [OSGiサービスの呼び出し](#calling-osgi-services)

## ワークフローノードへのスクリプトの追加

ワークフロースクリプトは、次のワークフローノードタイプのアクションから呼び出すことができます。

  - `<fork>`
  - `<join>`
  - `<state>`
  - `<task>`

スクリプトを呼び出すアクションの形式は次のとおりです。

``` xml
<actions>
<action>
    <script>
        <![CDATA[script code goes here]]>
    </script>
    <script-language>scripting language name goes here</script-language>
</action>
...
</actions>
```

たとえば、デフォルトのワークフロー定義の状態ノードから呼び出したこれらのスクリプト要素は、ワークフローのステータスを*承認済み*に設定します。

``` xml
<script>
<![CDATA[
    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;

    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
]]>
</script>
<script-language>groovy</script-language>
```

## 定義済み変数

上記のノードタイプで使用できる変数もあれば、`task`ノードでのみ使用できる変数もあります。

### 常に利用可能な変数

次の変数は、ワークフロースクリプトを実行できる場所であればどこからでも利用できます。

`kaleoInstanceToken`（[`KaleoInstanceToken`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-workflow/portal-workflow-kaleo-api/src/main/java/com/liferay/portal/workflow/kaleo/model/KaleoInstanceToken.java)）：ユーザーが*[Submit for Publication]* をクリックするたびに、ワークフローインスタンスと対応するインスタンストークン（`KaleoInstanceToken`）が作成されます。 挿入されたトークンを使用してIDを取得するには、`kaleoInstanceToken.getKaleoInstanceTokenId()`を呼び出します。 これは多くの場合、スクリプトのメソッドパラメータとして渡されます。

`userId`：返される`userId`はコンテキストに依存します。 技術的には、ロジックは次のように機能します：`KaleoTaskInstanceToke.getcompletionUserId()`がnullの場合、`KaloeTaskInstanceToken.getUserId()`を確認します。 それもnullの場合は、`KaleoInstanceToken.getUserId()`を呼び出します。 これは、スクリプトの実行時にワークフローに介入する最後のユーザーのIDです。 `created`ノードでは、これは*Submit for Publication*をクリックしたユーザーですが、単一の承認者定義の`review`ノードの終了時はレビューアのIDになります。

`workflowContext`（`Map<String, Serializable>`）：ワークフローコンテキストには、スクリプトで使用できる情報が含まれています。 通常、コンテキストはパラメーターとして渡されますが、`WorkflowContext`のすべての属性はスクリプトでも使用できます。 スクリプトのワークフローコンテキストは、コンテキストに依存します。 `ExecutionContext.getWorkflowContext()`の呼び出しがnullに戻ると、ワークフローコンテキストは`KaleoInstanceModel.getWorkflowContext()`によって取得されます。

### タスクノードに挿入される変数

`kaleoTaskInstanceToken`（[`KaleoTaskInstanceToken`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-workflow/portal-workflow-kaleo-api/src/main/java/com/liferay/portal/workflow/kaleo/model/KaleoTaskInstanceToken.java)）：タスクのトークン自体はワークフロースクリプトで使用できます。 これを使用してIDを取得し、プログラムによる割り当てなど、他の有用なプログラムによるワークフローアクティビティで使用します。

`taskName`（`String`）：タスク自体の名前にアクセスできます（`KaleoTak.getName()`と同じ値を返します）。

`workflowTaskAssignees`（`List<`[`WorkflowTaskAssignee`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/portal-kernel/src/com/liferay/portal/kernel/workflow/WorkflowTaskAssignee.java)`>`）：タスクの担当者を一覧表示します。

`kaleoTimerInstanceToken`（[`KaleoTimerInstanceToken`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-workflow/portal-workflow-kaleo-api/src/main/java/com/liferay/portal/workflow/kaleo/model/KaleoTimerInstanceToken.java)）：[タスクタイマー](https://help.liferay.com/hc/en-us/articles/360028834732-Workflow-Task-Nodes#task-timers)が存在する場合は、`kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId()`を呼び出してそのIDを取得します。

## スクリプトの例

ワークフローのほぼすべての時点で、Liferayのスクリプトエンジンを使用して、ワークフローAPIまたは他のLiferay APIにアクセスできます。 ワークフロースクリプトを使用できる実用的な方法をいくつか次に示します。

  - 特定のワークフロー関連の役割を持つユーザーのリストを取得する
  - 指定されたコンテンツ承認者に、コンテンツをレビューできない場合に連絡する人のリストを記載したメールを送信する
  - コンテンツの承認に割り当てられたユーザーのアラートポートレットに表示されるアラートを作成する

次のワークフロースクリプトはGroovyを使用して記述され、`Condition`ステートメントで使用されます。 スクリプトはLiferayの[アセットフレームワーク](https://help.liferay.com/hc/en-us/articles/360028725412-Introduction-to-Asset-Framework)を使用してアセットのカテゴリを決定し、カテゴリを使用して正しい承認プロセスを自動的に決定します。 アセットが`legal`カテゴリに含まれている場合は、送信時に`Legal Review`タスクに送信されます。 それ以外の場合、アセットは`Default Review`タスクに送信されます。

``` xml
<script>
    <![CDATA[
        import com.liferay.portal.kernel.util.GetterUtil;
        import com.liferay.portal.kernel.workflow.WorkflowConstants;
        import com.liferay.portal.kernel.workflow.WorkflowHandler;
        import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
        import com.liferay.asset.kernel.model.AssetCategory;
        import com.liferay.asset.kernel.model.AssetEntry;
        import com.liferay.asset.kernel.model.AssetRenderer;
        import com.liferay.asset.kernel.model.AssetRendererFactory;
        import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;

        import java.util.List;

        String className = (String)workflowContext.get(
            WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

        WorkflowHandler workflowHandler =
            WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

        AssetRendererFactory assetRendererFactory =
            workflowHandler.getAssetRendererFactory();

        long classPK =
            GetterUtil.getLong((String)workflowContext.get
            (WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

        AssetRenderer assetRenderer =
            workflowHandler.getAssetRenderer(classPK);

        AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
            assetRendererFactory.getClassName(), assetRenderer.getClassPK());

        List<AssetCategory> assetCategories = assetEntry.getCategories();

        returnValue = "Default Review";

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
```

``` note::
   スクリプトの戻り値は、次のタスクまたは状態を決定します。
```

その他のワークフロースクリプトの例については、[Embedded Workflows](https://help.liferay.com/hc/en-us/articles/360028721732-Introduction-to-Workflow#embedded-workflows)を参照してください。

## OSGiサービスの呼び出し

[Service Tracker](https://help.liferay.com/hc/en-us/articles/360028846472-Service-Trackers-for-OSGi-Services)は、使用可能なOSGiサービスを取得します。 Service Trackerがサービスに対してnullを返した場合、そのサービスは利用できず、応答時に適切な処理を行うことができます。

以下は、`JournalArticleLocalService`を使用して記事数を取得するGroovyで記述されたワークフロースクリプトです。

``` groovy
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.scripting.groovy.internal.GroovyExecutor;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

ServiceTracker<JournalArticleLocalService, JournalArticleLocalService> st;

try {
    Bundle bundle = FrameworkUtil.getBundle(GroovyExecutor.class);

    st = new ServiceTracker(bundle.getBundleContext(), JournalArticleLocalService.class, null);
    st.open();

    JournalArticleLocalService jaService = st.waitForService(500);

    if (jaService == null) {
        _log.warn("The required service 'JournalArticleLocalService' is not available.");
    }
    else {
        java.util.List<JournalArticle>articles = jaService.getArticles();
        if (articles != null) {
            _log.info("Article count: " + articles.size());
        } else {
            _log.info("no articles");
        }
    }
}
catch(Exception e) {
    //Handle error appropriately
}
finally {
    if (st != null) {
        st.close();
    }
}
```

このスクリプトは、スクリプトを実行するクラスのOSGiバンドルを使用してサービスを追跡します。 `com.liferay.portal.scripting.groovy.internal.GroovyExecutor`インスタンスがスクリプトを実行するため、インスタンスのバンドルを使用してサービスを追跡します。

``` groovy
Bundle bundle = FrameworkUtil.getBundle(GroovyExecutor.class);
```

LiferayのKaleoワークフローエンジンとLiferayのスクリプトエンジンの組み合わせは強力です。 権限を設定するときは、ワークフロー定義内のスクリプトが不適切だったり悪意を持って記述された場合の潜在的な影響に注意してください。

## 追加情報

  - [Introduction to Workflow](../user-guide/introduction-to-workflow.md)
  - [Running Scripts From the Script Console](../../../system-administration/using-the-script-engine/running-scripts-from-the-script-console)
  - [Script Examples](../../../system-administration/using-the-script-engine/script-examples.md)
