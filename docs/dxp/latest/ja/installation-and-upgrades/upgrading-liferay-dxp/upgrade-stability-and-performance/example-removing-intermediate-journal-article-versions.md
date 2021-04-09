# 例：ジャーナル記事の中間バージョンの削除

ここでは、ジャーナル記事の中間バージョンを削除する手順とコードサンプルを示します。 [スクリプトコンソール](../../../system-administration/using-the-script-engine/running-scripts-from-the-script-console.md)では、JavaまたはGroovyコードを実行して、不要なオブジェクトバージョンを削除できます。

ジャーナル記事の中間バージョンを削除する手順の例を次に示します。

1.  **保持する最新バージョンの数を決定します。**元のバージョンと最新バージョンを保持する必要がありますが、追加の最新バージョンを保持することもできます。 たとえば、2つの最新バージョンを保持することも、最新バージョンのみを保持することもできます。

2.  **エンティティのバージョンを削除する方法を見つけます。**Liferay DXPの[アプリAPI](https://docs.liferay.com/dxp/apps/)と[com.liferay.portal.kernel API](https://docs.liferay.com/dxp/portal/7.2-latest/javadocs/portal-kernel/)が使用可能なオプションです。

    [Service Builder](https://help.liferay.com/hc/en-us/articles/360030958811-Running-Service-Builder)エンティティの場合は、エンティティの`*LocalServiceUtil`クラスの`delete*`メソッドを調べます。

    たとえば、[`JournalArticleLocalServiceUtil`](https://docs.liferay.com/dxp/apps/web-experience/latest/javadocs/com/liferay/journal/service/JournalArticleLocalServiceUtil.html#deleteArticle-long-java.lang.String-double-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-)の次の`deleteArticle`は、ジャーナル記事のバージョンを1つ削除します。

    ``` java
    deleteArticle(long groupId, java.lang.String articleId, double version,
        java.lang.String articleURL,
        com.liferay.portal.kernel.service.ServiceContext serviceContext)
    ```

3.  **削除するエンティティのバージョンとそれらを削除するために必要な情報を集約します。**たとえば、削除基準に一致する範囲内のすべての`JournalArticle`バージョンを取得し、それらのエンティティIDとグループIDをバージョンに関連付けます（上記の`deleteArticle`メソッドにはエンティティIDとグループIDが必要です）。

    エンティティオブジェクト（`JournalArticle`など）には通常、バージョンフィールドがあります。 `JournalArticleResource`には、各`JournalArticle`の記事ID（エンティティのID）とグループIDがあります。 `JournalArticleResource`は各`JournalArticle`を取得するための鍵で、複数のバージョンを持つことができます。 削除する`JournalArticle`バージョンを特定する手順は次のとおりです。

    1.  すべての`JournalArticleResource`オブジェクトを取得します。

        ``` java
        List<JournalArticleResource> journalArticleResources =
            JournalArticleLocalServiceUtil.getJournalArticleResources(start, end);
        ```

    2.  各`JournalArticleResource`に関連付けられた`JournalArticle`オブジェクトを介して、各`JournalArticle`バージョンのワークフローステータスを取得します。 [動的クエリ](https://help.liferay.com/hc/en-us/articles/360030614272-Dynamic-Query)は、各オブジェクトから必要なデータを正確に取得する効率的な方法です。
        
         <!--Add back link for 'Dynamic Query' once dynamic-query article is available-->

        ``` java
        for (JournalArticleResource
            journalArticeResource : journalArticleResources) {

            List<Double> journalArticlesVersionsToDelete =
                new ArrayList<Double>();

            DynamicQuery dq =
                DynamicQueryFactoryUtil.forClass(JournalArticle.class)
                    .setProjection(ProjectionFactoryUtil.projectionList()
                        .add(ProjectionFactoryUtil.property("id"))
                        .add(ProjectionFactoryUtil.property("version"))
                        .add(ProjectionFactoryUtil.property("status")))
                    .add(PropertyFactoryUtil.forName("groupId")
                        .eq(journalArticeResource.getGroupId()))
                    .add(PropertyFactoryUtil.forName("articleId")
                        .eq(journalArticeResource.getArticleId()))
                    .addOrder(OrderFactoryUtil.asc("version"));

            List<Object[]> result =
                JournalArticleLocalServiceUtil.dynamicQuery(dq);

            // See the next step for the sample code that goes here
        }
        ```

    3.  `JournalArticleResource`ごとに（`JournalArticle`エンティティごとに1つあります）、保持する最初のバージョンと最新バージョンの範囲内にあり、かつステータスが削除に適している中間バージョンのリストを作成します。 たとえば、承認済みまたは期限切れの中間記事バージョン（[WorkflowConstants.STATUS\_APPROVEDまたはWorkflowConstants.STATUS\_EXPIRED](https://docs.liferay.com/dxp/portal/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/workflow/WorkflowConstants.html)）を削除することができます。 `MIN_NUMBER_FIRST_VERSIONS_KEPT`および`MIN_NUMBER_LATEST_VERSIONS_KEPT`変数は、保持する最初の（最も古い）バージョンと最新の（最も新しい）バージョンの最小数と最大数をマークします。

        ``` java
        List<Double> journalArticlesVersionsToDelete =
            new ArrayList<Double>();

        for (int i=0; i < result.size(); i++) {
            long id = (long) result.get(i)[0];
            double version = (double) result.get(i)[1];
            int status = (int) result.get(i)[2];

            if ((status == WorkflowConstants.STATUS_APPROVED) || (status == WorkflowConstants.STATUS_EXPIRED) {

                if (i < MIN_NUMBER_FIRST_VERSIONS_KEPT) {
                    continue;
                }

                if (i >= (result.size() -
                    MIN_NUMBER_LATEST_VERSIONS_KEPT)) {
                    continue;
                }

                journalArticlesVersionsToDelete.add(version);
            }
        }

        // See the next step for the sample code that goes here
        ```

4.  最後に、集計したバージョンに一致する各ジャーナル記事を削除します。

    ``` java
    for (double version : journalArticlesVersionsToDelete) {
    {
        JournalArticleLocalServiceUtil.deleteArticle(journalArticeResource.getGroupId(),
            journalArticeResource.getArticleId(),
            journalArticlesVersionsToDelete(i), null, null);
    }
    ```

同様のコードを記述して、他のエンティティの中間バージョンを削除できます。 コードの準備ができたら、サンプルモジュールを使用するか、スクリプトコンソールを使用してスクリプトとして実行します。

``` tip::
   削除する各オブジェクトのバージョン（およびその他の必要な情報）を印刷します。 削除をコミットする前に、オブジェクト削除呼び出しをコメントアウトし、テストとして削除するバージョンのプリントアウトを読み取ることもできます。
```

## 追加情報

  - [Pruning the Database](./database-pruning-for-faster-upgrades.md)
  - [Tuning for the Data Upgrade](./database-tuning-for-upgrades.md)
