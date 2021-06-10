# データソースの接続

環境やデータ・ソースが誤って設定されていると、Liferay DXPのデータ・ソースへのアクセスを妨げたり、中断させたりすることがあります。 ここでは、DXP データソースの問題をトラブルシューティングする方法をご紹介します。

## アナリティクスクラウドへのネットワークアクセスがない

次のURLを許可リストに追加して、DXPのインストールで弊社のAnalytics Cloudサーバーへのインターネットアクセスが可能であることを確認してください。

-   `https://analytics.liferay.com`
-   `https://osbasahpublisher-{weDeployKey}.lfr.cloud`
-   `https://osbasahbackend-{weDeployKey}.lfr.cloud`
-   `https://analytics-js-cdn.liferay.com`

``` note::
   Please contact Liferay Analytics Cloud Support at help.liferay.com to obtain your ``{weDeployKey}`` value.
```

``` important::
   企業のイントラネットの利用状況を分析するなど、いくつかのユースケースでは、訪問者のブラウザもファイアウォールの背後にあります。 このシナリオでは、企業のオフィスネットワークが上記のURLのアウトバウンドアクセスも許可していることを確認する必要があります。
```

## アナリティクスクラウドへの接続を検証する

データがAnalytics Cloudに送信されているかどうかを検証するのに役立つヒントをご紹介します。

### アナリティクスのイベント

アナリティクスのイベントは、クライアントのブラウザから直接送信されます。 データがAnalytics Cloudに送信されていることを確認するには、次の手順を実行します。

1.  追跡されているDXPウェブサイトのページをご覧ください。

2.  ブラウザのインスペクタを開き、［ネットワーク］タブに移動します。

3.  ネットワーク タブを XHR でフィルタリングします。

4.  ページを更新してください。

5.  `osbasahpublisher`から始まるリクエストが出ていることを確認します。 リクエストは以下のスクリーンショットのようなものになります。

    ![Analytics Cloudへの接続を検証します。](connecting-data-sources/images/01.png)

    この要求が表示されている場合は、お客様のウェブサイトがアナリティクス データをアナリティクス クラウド ワークスペースに送信していることを意味します。 リクエストペイロードをチェックして、 `channelId`という変数があることを確認してください。

### 連絡先データ

DXPは、ログインユーザーの連絡先情報を個別のプロファイルデータとしてAnalytics Cloudに送信します。 このデータはDXPサーバーから直接送信されます。

連絡先データが送信されていることを確認するには、DXPサーバーのログに以下のようなメッセージがないか確認してください。

    INFO  [liferay/analytics_messages_processor-1][AddAnalyticsMessagesMessageListener:70] Added 500 analytics messages
    
    INFO  [liferay/analytics_messages_processor-1][AddAnalyticsMessagesMessageListener:70] Added 500 analytics messages
    
    INFO  [liferay/scheduler_dispatch-3][SendAnalyticsMessagesMessageListener:149] Sent 100 analytics messages
    
    INFO  [liferay/scheduler_dispatch-3][SendAnalyticsMessagesMessageListener:164] Deleted 100 analytics messages

これらのサーバーログが表示されている場合は、連絡先データが正常にACに送信されていることを示しています。

## データ処理時間

データが Analytics Cloud に到着すると、ワークスペース ダッシュボードに表示される前に、処理にさらに時間がかかります。

アナリティクス イベントの場合は、サイト ダッシュボードの 24 時間フィルターの訪問者メトリクスを 10 分から 15 分以内に表示できるようにする必要があります。

![アナリティクス データが一定期間で入ってくる。](connecting-data-sources/images/02.png)

セッション期間やバウンス率などの他のセッション関連データは、訪問者のセッションが終了するまで待つ必要があります。 ビジターセッションは、30分間の非活動時間が経過した時点、またはUTC 00:00:00:00のいずれか早い時点で終了したとみなされます。

訪問者プロフィールは、処理に時間がかかり、時間の経過とともに利用可能になります。

## サポートされていないバージョン

**エラーメッセージ**： `サポートされていないバージョンです。 この接続方法は、データソースのLiferayバージョンをサポートしていません。 Liferay 7.0/7.1インスタンスに接続していることを確認するか、別の接続方法を試してみてください。`

**解決策：**

1.  ［connect with a Liferay DXP 7.0 or 7.1 instance］を確認してください。

2.  [Liferay DXPデータソースの追加](../getting-started/connecting-data-sources/connecting-liferay-dxp-using-oauth.md)の手順に従ってください。

3.  エラーが続く場合は、DXPインスタンスでJSONウェブサービスが有効になっていることを確認してください。 デフォルトで有効になっています。 [ポータルプロパティ](https://docs.liferay.com/dxp/portal/7.1-latest/propertiesdoc/portal.properties.html#JSON) でjson.web.service.enabled=falseを設定して無効にしていた場合（例えば、 [のportal-ext.propertiesファイル](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html)で設定）、設定を削除するか、プロパティ値をtrueにしてください。
