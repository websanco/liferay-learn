# データのエクスポート

前述のように、すべてのAPIコールはデータのサブセットを返すだけで、そのスコープはページのクエリパラメータによって制御されます。 幸い、データセット全体をJSONファイルにエクスポートする方法があります。 最初のリクエストでデータファイルを用意し、エクスポート後に同じエンドポイントをフェッチしてデータ結果をストリームすることができます。

ユーザーは、前述の4つのリソースタイプ（アカウント、個人、セグメント、ページ）のデータエクスポートをリクエストできます。

<a name="requesting-a-data-export" />

## データのエクスポートをリクエストする

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/export/{type}
```

前述の通り、タイプはデータ型を定義します。 設定可能な値は次のとおりです： **アカウント** 、 **個人** 、 **ページ** 、 **セグメント** 。  したがって、セグメントデータのエクスポートに興味がある場合は、次のリクエストを送信します：

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/export/segment
```

以下のようなレスポンスが返ってきます：

```json
{"message":"The data export file is being created. Please come back later."}
```

最初のリクエストが行われた日から30分間は、同じデータエクスポートファイルが返されます。 その後、新しいリクエストに応じて、新しいデータエクスポートファイルが生成されます。 エクスポートジョブが完了すると、以下のコマンドでファイルをダウンロードできます：

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/export/segment > segment-data.json
```
