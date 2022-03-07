# トラブルシューティングサービス

サービスはDXP Cloud環境のコアです。 この記事では、DXP Cloudサービスで発生する可能性のある問題を調査し、対処するために使用できるトラブルシューティング手法について説明します。

<a name="a-service-is-continually-restarting" />

## サービスが継続的に再起動する

サービスが何度も再起動するのは、そのサービスの [活性または準備プローブ](./self-healing.md) に問題があることを示している可能性があります。 この現象が発生すると、ある環境の概要の ［**アクティビティ**］ パネルの ［**一般**］ タブに表示される場合があります。 <!-- Add screenshot if a way to easily reproduce this is available.-->

定期的に起こる **活性プローブの失敗** によって継続的に再起動されているサービスは、活性プローブが再起動をトリガーする前に、起動を完了していないことがあります。 **準備プローグの失敗** が起きた場合は、準備プローブが適切なレスポンスの取得に何度も連続して失敗したことを意味します（サービスが完全に開始された後の場合もあります）。

継続的に再起動するサービスを診断するには、以下の手順をお勧めします。

1. [サービスログをチェックして、活性または準備プローブの失敗を確認する](#check-service-logs-for-liveness-or-readiness-probe-failures)
1. [プローブ構成の確認](#review-probe-configurations)
1. [スタートアップエラーがないかサービスログを確認する](#review-service-logs-for-startup-errors)
1. [プローブ構成の調整](#adjust-probe-configurations)

<a name="check-service-logs-for-liveness-or-readiness-probe-failures" />

### サービスログをチェックして、活性または準備プローブの失敗を確認する

サービスのページに移動すると、最初のタブでログが表示されます。 最近のログをスキャンして、プローブの不具合を示すものを探します。

たとえば、活性プローブの失敗メッセージは次のようなものです：

```
Liveness probe failed: HTTP probe failed with statuscode: 500
```

<a name="review-probe-configurations" />

### プローブ構成の確認

お客様の環境に合わせてプローブの設定をカスタマイズしている場合、誤ったプローブの設定が原因でプローブの不具合が発生している可能性があります。 これをプローブの失敗の原因として除外するには、活性プローブと準備プローブの設定を確認し、環境に合っているかどうかを確認してください。

問題が発生しているサービスに移動し、ステータスアイコン（省略記号または「準備完了」のアイコン）にカーソルを合わせます。

![サービスステータスインジケータにカーソルを合わせると、プローブ構成にアクセスできるポップアップが表示されます。](./troubleshooting-services/images/01.png)

該当するプローブをクリックすると、その構成が表示されます：

```
timeoutSeconds: 10
httpGet: path: /c/portal/layout
         port: 8080

initialDelaySeconds: 120
periodSeconds: 15
failureThreshold: 3
successThreshold: 3
```

プローブの構成値（`パス` または `ポート`の 値）がご使用の環境にあっていない場合、プロジェクトのリポジトリのサービスの`LCP.json` ファイルで正しい値に調整してください。 このファイルをサービスに対応するディレクトリ（例： `liferay/`）で見つけ、 [テスト環境に変更をデプロイします](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md)。

サービスの `LCP.json` ファイルにプローブの設定がまだない場合は、コンソールに表示されている設定をコピーして、変更を適用しながらJSON形式に変換することができます。 例えば、 `liferay` サービスの準備プローブと活性プローブの構成は次のようになります：

```json
{
"readinessProbe": {
    "httpGet": {
      "path": "/c/portal/layout",
      "port": 8080
    },
    "initialDelaySeconds": 120,
    "periodSeconds": 15,
    "timeoutSeconds": 10,
    "failureThreshold": 3,
    "successThreshold": 3
  },
  "livenessProbe": {
    "tcpSocket": {
      "port": 8080
    },
    "initialDelaySeconds": 300,
    "periodSeconds": 60,
    "timeoutSeconds": 10,
    "failureThreshold": 3,
    "successThreshold": 1
  }
}
```

ほとんどの環境では、デフォルトのプローブ設定で問題ないはずです。 プローブの設定を全く変更していない場合は、設定が正しくされていないことが原因ではないと思われます。

<a name="review-service-logs-for-startup-errors" />

### スタートアップエラーがないかサービスログを確認する

プローブの設定が正しい場合、プローブが正しく機能しないのはサービス自体に問題がある可能性があります。

スタートアップの失敗を示すエラーメッセージが出ていないか、ログを確認してください。 起動時のログにエラーや問題が確認できれば、それが原因で1つまたは複数のプローブが失敗している可能性があるため、サービスの再起動が必要になります。

```tip::
   Liferayサービスでは、再起動後の最初のログに次のようなメッセージが表示されます。``[LIFERAY] To SSH into this container, run: "docker exec -it liferay-<node-id> /bin/bash".``
```

Liferayのスタートアップエラーの処理にサポートが必要な場合は、 [サポートに連絡してください](https://help.liferay.com/hc/en-us) 。

<a name="adjust-probe-configurations" />

### プローブ構成の調整

プローブの失敗の原因となるサービスの起動ログに明らかなエラーや問題がないにもかかわらず、サービスが再起動してしまう場合は、プローブの設定を調整する必要があるかもしれません。

サービスの起動に時間がかかると、活性プローブが早期にタイムアウトし、サービスの再起動が必要になることがあります。 これは、プローブが故障して再起動するまでに、通常の起動ログがすべて表示されていない場合に起こります。 これは、サービスがすでに完全に開始された後に、 **準備** プローブが失敗した場合のケースでは通常ありません。

このような現象が発生する場合は、以下のいずれかをお試しください。

* サービスを確認するまでのプローブの待機時間を長くするために、 `initialDelaySeconds` の値を大きくします。
* `failureThreshold` の値を大きくして、サービスの再起動を行う前にプローブの試行回数を増やすようにします。
* サービスからの応答が遅い場合、プローブが失敗する可能性を減らすために、 `timeoutSeconds` の値を大きくします。

プローブの設定（サービスの `LCP.json` ファイル）でこれらの値を1つまたは複数増やし、速度低下によりサービスの再起動が発生している場合は、サービスに応じて適切に変更をデプロイします。 ただし、これらの値を恣意的に高く設定しないようにしてください。今後、サービスを再起動するたびに、変更内容がプローブに適用されるからです。

詳細は、 [Using and Configuring Probes](./self-healing.md#using-and-configuring-probes) を参照してください。

<a name="contact-cloud-support" />

### クラウドサポートへのお問い合わせ

活性や準備プローブの失敗がサービスの再起動の原因となっていない場合、またはこれらの戦略がそれらの問題を解決するために役立っていない場合は [DXP Cloudサポート](https://help.liferay.com/hc/en-us) にご連絡ください。
