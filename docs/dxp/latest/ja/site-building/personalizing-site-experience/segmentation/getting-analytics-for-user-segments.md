# ユーザーセグメントの分析の取得

> **サブスクライバー**

ユーザーセグメントでAnalytics Cloudを使用するには、最初にDXPデータソースをAnalytics Cloudに接続し、ユーザーと分析の同期を有効にする必要があります。 DXPとの接続手順を含むAnalytics Cloudの詳細については、公式の[Analytics Cloudドキュメント](https://help.liferay.com/hc/en-us/categories/360000872551)を参照してください。

``` important::
  Analytics Cloudとの同期は即時ではないため、Analytics CloudとLiferay DXPを接続したら、まずユーザーとデータが同期するのを待つ必要があります。 それが完了したら、Analytics Cloudでセグメントを作成して、DXPでデータをキャプチャできます。
```

次の手順に従って、セグメント分析を取得します。

1.  [Analytics Cloudでセグメントを作成](https://help.liferay.com/hc/en-us/articles/360006947671-Creating-Segments)します（まだ作成していない場合）。

    ``` note::
      少なくとも1つのメンバーを含むセグメントのみがLiferay DXPと同期されます。 つまり、Analytics Cloudで作成された空のセグメントは、Liferay DXPでは使用できません。
    ```

2.  セグメントが同期されたら、*[Segments]* ページに移動します。

3.  新しいセグメントをクリックして表示し、カスタマイズします。

![セグメントのリストに表示されるAnalytics Cloudセグメントは、Analytics Cloudアイコンでマークされています。](./getting-analytics-for-user-segments/images/01.png)

分析はAnalytics Cloudで設定した基準に基づいていますが、ここで追加の基準を設定して、DXPでのパーソナライゼーションにこのセグメントを使用できます。 ここでセグメント基準を変更しても、収集された分析データには影響しません。ただし、Analytics Cloudの基準として使用しているコンテンツをメンバーが表示できないように設定されている場合を除きます。

## 関連情報

  - [Assigning Roles to User Segments](../../../users-and-permissions/roles-and-permissions/assigning-roles-to-user-segments.md)
  - [Creating User Segments](./creating-and-managing-user-segments.md)
  - [Content Set Personalization](../experience-personalization/content-set-personalization.md)
