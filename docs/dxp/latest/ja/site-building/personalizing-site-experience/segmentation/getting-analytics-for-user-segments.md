# ユーザーセグメントの分析を取得する

> **サブスクライバー**

ユーザーセグメントでAnalytics Cloudを使用するには、最初にDXPデータソースをAnalytics Cloudに接続し、ユーザーと分析の同期を有効にする必要があります。 DXPとの接続手順を含むAnalytics Cloudの詳細については、公式の [Analytics Cloudドキュメント](https://learn.liferay.com/analytics-cloud/latest/ja/index.html) を参照してください。

```{important}
Analytics Cloudとの同期は即時ではないため、Analytics CloudとLiferay DXPを接続したら、まずユーザーとデータが同期するのを待つ必要があります。 それが完了したら、Analytics Cloudでセグメントを作成して、DXPでデータをキャプチャできます。
```

次の手順に従って、セグメント分析を取得します。

1. [Analytics Cloudでセグメントを作成](https://learn.liferay.com/analytics-cloud/latest/ja/people/segments/creating-segments.html) します（まだ作成していない場合）。

    ```{note}
    少なくとも1つのメンバーを含むセグメントのみがLiferay DXPと同期されます。 つまり、Analytics Cloudで作成された空のセグメントは、Liferay DXPでは使用できません。
    ```

1. セグメントが同期されたら、 ［**Segments**］ ページに移動します。
1. 新しいセグメントをクリックして表示し、カスタマイズします。

> Liferay DXPのバージョンによって、製品の画像が若干異なる場合があります。

![セグメントのリストに表示されるAnalytics Cloudセグメントは、Analytics Cloudアイコンでマークされています。](./getting-analytics-for-user-segments/images/02.png)

Analytics Cloudセグメントをクリックすると、Analytics Cloudに移動して、セグメントを編集し、条件を変更できます。 Liferay DXPでAnalytics Cloudセグメントを編集することはできません。

## 関連情報

* [ユーザーセグメントへのロールの割り当て](../../../users-and-permissions/roles-and-permissions/assigning-roles-to-user-segments.md)
* [ユーザーセグメントの作成](./creating-and-managing-user-segments.md)
* [コレクションのパーソナライズ](../experience-personalization/personalizing-collections.md)
