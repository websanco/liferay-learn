# データのエクスポート

前述したように、すべてのAPIコールはデータのサブセットのみを返し、スコープはページクエリパラメータによって制御されます。 幸いなことに、データセット全体をJSONファイルにエクスポートする方法があります。 最初のリクエストでデータファイルの準備を行い、エクスポート後に同じエンドポイントをフェッチしてデータ結果をストリームすることができます。

ユーザーは、前述の4つのリソースタイプ（アカウント、個人、セグメント、ページ）のデータエクスポートを要求することができます。

## データのエクスポートをリクエストする

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/export/{type}

前述の通り、データ型を定義しています。 可能な値は以下の通りです。 **アカウント**, **個人**, **ページ**, および **セグメント**。 そのため、セグメントデータのエクスポートに興味がある場合は、以下のようなリクエストを送信します。

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/export/segment

以下のようなレスポンスが返ってきます。

``` json
{"message":"The data export file is being created. Please come back later."}
```

最初のリクエストが行われた日から30分間は、同じデータ書き出しファイルが返却されます。 その後、新しいリクエストに応じて新しいデータエクスポートファイルが生成されます。 エクスポートジョブが完了したら、次のコマンドでファイルをダウンロードできます。

    curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/export/segment > segment-data.json
