# ワークフローでのスクリプトエンジンの使用

Liferayの[ワークフローエンジン](../introduction-to-workflow.md)は、[XMLワークフロー定義](./crafting-xml-workflow-definitions.md)に埋め込まれたGroovyスクリプトを使って、ワークフロー実行時にスクリプトを実行することができます。

ワークフロースクリプトのトピックは次のとおりです。

* [ワークフローノードへのスクリプトの追加](#adding-scripts-to-workflow-nodes)
* [定義済み変数の使用](#predefined-variables)
* [スクリプトの例](#script-example)
* [OSGiサービスの呼び出し](#calling-osgi-services)

<a name="adding-scripts-to-workflow-nodes" />

## ワークフローノードへのスクリプトの追加

ワークフロースクリプトは、次のワークフローノードタイプのアクションから呼び出すことができます。

* `<fork>`
* `<join>`
* `<state>`
* `<task>`

スクリプトを呼び出すアクションの形式は次のとおりです。

```xml
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

一般的な操作として、ワークフローの状態を設定することがあります。 例えば、このスクリプトでは、ワークフローの状態を **approved** に設定しています。

```xml
<script>
<![CDATA[
    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;

    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
]]>
</script>
<script-language>groovy</script-language>
```

<a name="predefined-variables" />

## 定義済み変数

すべてのノードタイプに共通する変数もあれば、 `タスク` ノードだけが利用できる変数もあります。

### すべてのノードタイプに共通する変数

次の変数は、ワークフロースクリプトを実行できる場所であればどこからでも利用できます。

| 変数                                                                                                                                                                                                                                                                  | 説明                                                                                 | 使用法                                                                                                                                                                                                                                                                                                     |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `kaleoInstanceToken` ( [`KaleoInstanceToken`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-api/src/main/java/com/liferay/portal/workflow/kaleo/model/KaleoInstanceToken.java) ) | ユーザーが **公開申請** をクリックするたびに、ワークフローインスタンスとそれに対応するインスタンストークン（`KaleoInstanceToken`）が作成されます。 | `kaleoInstanceToken.getKaleoInstanceTokenId()`を呼び出して、注入されたトークンを使用してそのIDを取得します。 これは多くの場合、スクリプトのメソッドパラメータとして渡されます。                                                                                                                                                                                        |
| `userId`                                                                                                                                                                                                                                                            | `userId` が返されるかどうかは、コンテキストに依存します。                                                  | 技術的には以下のようなロジックになります。 `KaleoTaskInstanceToken.getcompletionUserId()` がnullの場合、 `KaleoTaskInstanceToken.getUserId()`をチェックします。 それもnullの場合は、`KaleoInstanceToken.getUserId()`を呼び出します。 これは、スクリプトの実行時にワークフローに介入する最後のユーザーのIDです。 `created`ノードでは、これは **公開申請** をクリックしたユーザーですが、単一の承認者定義の`review`ノードの終了時はレビューアのIDになります。 |
| `workflowContext` (`Map<String, Serializable>`)                                                                                                                                                                                                               | ワークフローのコンテキストには、スクリプトで使用できる情報が含まれています。                                             | 通常、コンテキストはパラメーターとして渡されますが、`WorkflowContext`のすべての属性はスクリプトでも使用できます。 スクリプトのワークフローコンテキストは、コンテキストに依存します。 `ExecutionContext.getWorkflowContext()`の呼び出しがnullに戻ると、ワークフローコンテキストは`KaleoInstanceModel.getWorkflowContext()`によって取得されます。                                                                             |

### タスクノードに挿入される変数

これらの変数は、タスクノードに注入されます。

| 変数                                                                                                                                                                                                                                                                                 | 説明                                                                                                                           | 使用法                                                    |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------ |
| `kaleoTaskInstanceToken` ( [`KaleoTaskInstanceToken`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-api/src/main/java/com/liferay/portal/workflow/kaleo/model/KaleoTaskInstanceToken.java) )    | タスクのトークン自体は、ワークフロースクリプトで利用できます。                                                                                              | これを使用してIDを取得し、プログラムによる割り当てなど、他の有用なプログラムワークフロー活動で使用します。 |
| `taskName`（`String`）：タスク自体の名前にアクセスできます（`KaleoTak.getName()`と同じ値を返します）。                                                                                                                                                                                                             |                                                                                                                              |                                                        |
| `workflowTaskAssignees` (`List<` [`WorkflowTaskAssignee`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/workflow/WorkflowTaskAssignee.java) `>`)                                                 | タスクの担当者をリストアップします。                                                                                                           |                                                        |
| `kaleoTimerInstanceToken` ( [`KaleoTimerInstanceToken`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-workflow/portal-workflow-kaleo-api/src/main/java/com/liferay/portal/workflow/kaleo/model/KaleoTimerInstanceToken.java) ) | [タスクタイマー](./workflow-task-node-reference.md)が存在する場合は、`kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId()`を呼び出してIDを取得します。 | |                                                      |

<a name="script-example" />

## スクリプトの例

ワークフローのほぼすべての時点で、Liferayのスクリプトエンジンを使用して、ワークフローAPIまたは他のLiferay APIにアクセスできます。 ワークフロースクリプトを使用できる実用的な方法をいくつか次に示します。

* 特定の役割を持つユーザーのリストを取得する
* 指定されたコンテンツ承認者に、コンテンツをレビューできない場合に連絡する人のリストを記載したメールを送信する
* コンテンツを承認する権限を持つユーザーに対して、アラートポートレットに表示するアラートの作成

以下のワークフロースクリプトはGroovyを使って書かれており、 `Condition` Nodeと共に使用されます。 スクリプトはLiferayの[アセットフレームワーク](../../../developing-applications/data-frameworks/assets.md)を使用してアセットのカテゴリを決定し、カテゴリを使用して正しい承認プロセスを自動的に決定します。 アセットが `法的な` カテゴリーにある場合は、提出時に `法的審査` タスクに送られます。 それ以外の場合、アセットは`Default Review`タスクに送信されます。

```xml
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

```{note}
   スクリプトの戻り値は、次のタスクまたは状態を決定します。
```

ダウンロード可能なワークフローのスクリプト例へのリンクは、 [Crafting Workflow Definitions](./crafting-xml-workflow-definitions.md) を参照してください。

<a name="calling-osgi-services" />

## OSGiサービスの呼び出し

[Service Tracker](../../../liferay-internals/service-trackers.md)は、使用可能なOSGiサービスを取得します。 Service Trackerがサービスに対してnullを返した場合、そのサービスは利用できず、応答時に適切な処理を行うことができます。

以下は、`JournalArticleLocalService`を使用して記事数を取得するGroovyで記述されたワークフロースクリプトです。

```groovy
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

```groovy
Bundle bundle = FrameworkUtil.getBundle(GroovyExecutor.class);
```

LiferayのKaleoワークフローエンジンとLiferayのスクリプトエンジンの組み合わせは強力です。 権限を設定するときは、ワークフロー定義内のスクリプトが不適切だったり悪意を持って記述された場合の潜在的な影響に注意してください。

<a name="additional-information" />

## 追加情報

* [ワークフローの概要](../introduction-to-workflow.md)
* [Running Scripts From the Script Console](../../../system-administration/using-the-script-engine/running-scripts-from-the-script-console)
* [スクリプトの例](../../../system-administration/using-the-script-engine/script-examples.md)
