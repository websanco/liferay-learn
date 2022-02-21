# ワークフローコンテクストの利用

[Liferayのワークフロー](../introduction-to-workflow.md) は、承認プロセスとして使用されます。 プロセスの各ステップにおいて、ワークフローは、 `Map<String, Serializable>` オブジェクトで利用可能な特定のデータを持っており、スクリプトやカスタムコードでは `workflowContext`として参照されます。 ワークフローコンテキスト情報は、Liferayのワークフローエンジンが正しく機能するために重要です。 これを理解することで、カスタムコードやワークフロースクリプトでどのように使用するのが最適かを判断することができます。

例えば、ワークフローのコンテキストでは、以下のことができます。

- [ワークフロースクリプト、カスタムコード、または FreeMarker テンプレートで使用するために、既存の属性にアクセスします。](#accessing-workflow-context-attributes-in-workflow-definitions)
- [ワークフロー・スクリプト、カスタム・コード、またはFreeMarkerテンプレートでアクセスできるように、ワークフロー・プロセスの1ステップで新しい属性を設定します。](#setting-workflow-context-attributes-in-a-workflow-process-definition)
- [`ServiceContext` 属性を設定し、ワークフロースクリプトや通知テンプレートでアクセスすることができます。](#setting-service-context-attributes-for-access-in-workflow-definitions)

```{note}
ServiceContext`を使用して、`workflowContext`が利用できないコンテキストの属性を設定します。 例えば、カスタムコードで `BrosEntryLocalService#addEntry` を呼び出す場合には、`ServiceContext` オブジェクトを提供する必要があります。 ServiceContext#setAttribute`を使って、ワークフローの中でアクセスしたいデータを渡すことができます。 
```
`workflowContext`を扱う際には、いくつかの注意点があります。

- 修正可能でなければならないので、スレッドセーフではありません。 並列実行のコンテクストでは注意が必要です。

  例えば、フォークノードを持つワークフローにおいて、ワークフローの両方のフォークで `workflowContext` を更新することは推奨されません。

- その最初の型のパラメータ（属性の `キー` ）は、Stringです。 これは、2番目の属性に格納されている値を検索するために使用されます。
- その2番目の型パラメータ（各属性の `値` ）は、データベースに保存されているため、 `Serializable` となっています。 これにより、ワークフローのすべてのステップでアクセスできるようになります。

任意のワークフローノードのワークフローコンテキストキーと値を印刷するには、次のようなスクリプトアクションを追加します。
```xml
<actions>
    <action>
        <name>print-workflow-context</name>
        <script>
            <![CDATA[
            for (Map.Entry<String, Serializable> mapEntry :
                workflowContext.entrySet()) {
                    out.println(mapEntry.getKey(), mapEntry.getValue());
            }
            ]]>
        </script>
        <script-language>groovy</script-language>
        <execution-type>onEntry</execution-type>
    </action>
</actions>
```

ノードが入力されると、ログに出力されます。

```
entryType, Blogs Entry
companyId, 37401
entryClassPK, 40226
entryClassName, com.liferay.blogs.model.BlogsEntry
groupId, 37441
taskComments, 
userPortraitURL, /image/user_portrait?img_id=0&img_id_token=IpLU58ogLTDf%2FDIfo8Ukg0YxiUE%3D&t=1626283728181
serviceContext, com.liferay.portal.kernel.service.ServiceContext@565b5550
userId, 37448
url, http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_blogs_web_portlet_BlogsAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_mvcRenderCommandName=%2Fblogs%2Fview_entry&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_entryId=40226&p_p_auth=rRDR0ncV
userURL, http://localhost:8080/web/test
```

## ワークフロー定義のワークフローコンテキスト属性へのアクセス

アクセスするには `workflowContext` の属性フォーム[`<script>`](using-the-script-engine-in-workflow.md)にアクセスするには、`Map#get` 方法をで取得します。

```groovy
import com.liferay.portal.kernel.workflow.WorkflowConstants;

String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
```

上記の例では` String` を取得していますが、`workflowContext `の属性の中には、`long` として使用しなければならないものがあります（例えば、メソッドのパラメータとして渡される場合など）。 これには、 `GetterUtil` ユーティリティクラスが役立ちます。

```groovy
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
```

[`WorkflowConstants`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/workflow/WorkflowConstants.java) オブジェクトフィールドを使用することで、エラーになりやすい文字列リテラルを避けることができます。 `workflowContext` フィールドの前には、すべて `CONTEXT` が付けられます（例： `CONTEXT_COMPANY_ID`）。


## ワークフロー定義でのワークフローコンテキスト属性の設定

`workflowContext`に属性を設定する場合は、 `Map#put` 方式を使用します。 この例では、 `assetTitle`を設定しています。

```groovy
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);

String assetTitle = assetRenderer.getAssetObject().getTitle();

workflowContext.put("assetTitle", assetTitle);
```

```{tip}
上記のコードは、アセットが `getTitle` メソッドを持っている場合のみ動作します（例：`JournalArticle`）。
```

## ワークフロー定義でアクセス用のサービスコンテキスト属性を設定する

カスタムのJavaコードで、ワークフロー定義に情報を渡す必要がありますが、`workflowContext`がない場合があります。 例えば、ブログのエントリーを追加するコードを書いている場合、 [`BlogsEntryLocalService#addEntry`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/blogs/blogs-api/src/main/java/com/liferay/blogs/service/BlogsEntryLocalService.java) メソッドのいずれかを呼び出すことができます。 `workflowContext`はこれらのメソッドのパラメータではありませんが、`ServiceContext`はパラメータです。 サービスコンテキストに新しい属性を追加します。

```java
serviceContext.setAttribute("customAttributeKey", "customAttributeValue");
```

ワークフロー定義内の属性を取得するには、 `ServiceContext` を、 `workflowContext`から取得し、そのキーを使って属性を取得します。

```groovy
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

ServiceContext serviceContext = (ServiceContext)workflowContext.get(WorkflowConstants.CONTEXT_SERVICE_CONTEXT);

serviceContext.getAttribute("customAttributeKey");
```

## 関連情報

- [ワークフロー通知テンプレート変数](./workflow-notification-template-variables.md)
- [ワークフローでのスクリプトエンジンの使用](./using-the-script-engine-in-workflow.md)

