# VPNインテグレーションの概要

Liferay DXP Cloudでは、ポートフォワーディングと冗長トンネルをサポートしたクライアントからサイトへのVPN接続を提供しています。 この機能は、通常、DXP Cloud上のサブスクライバーの本番環境を内部ネットワークに接続するために使用されます。 セキュリティと信頼性のために、これらのVPN接続は環境ごと（本番環境、ステージング、または開発）に分離されています。

![トポロジー1 - DXP Cloud VPNクライアントからサイトへのトポロジーについて](./vpn-integration-overview/images/01.png)

加入者は、DXP Cloudサービス間の接続を対応するVPNサーバーのIPアドレスにマッピングすることにより、冗長VPNトンネルを使用できます。 冗長性はさまざまなアベイラビリティーゾーンに配置され、回復力を提供します。 クライアントからサイトへのアプローチには、企業ネットワークで実行されているサービスへの接続が含まれます。 このモデルは、コンテナ化されたアーキテクチャーと、提供されるKubernetesネットワークレイヤーに推奨されます。

VPN接続が設定されると、環境のログページで、ドロップダウンリストから ［**VPN Logs**］ を選択することで、VPNサーバーからのログメッセージを表示することができます。

![［VPN Logs］を選択すると、自分の環境での最近のVPNアクティビティが表示されます。](./vpn-integration-overview/images/02.png)

詳細は、 [VPNサーバーの制限](../../reference/platform-limitations.md#vpn-servers) のセクションを参照してください。

<a name="configuration" />

## 設定

クライアントからサイトへのVPN機能は、次のプロトコルをサポートしています。

* IPsec (IKEv2)
* OpenVPN

サブスクライバーは、プロトコル（IPSecまたはOpenVPN）のいずれかを選択して、目的の環境のDXP Cloudコンソール設定ページから接続を実行できます。 コンソールUIでは、接続に任意の数の転送ポートを設定できます。

```{note}
   IPsecサーバーで ［IKEv2］プロトコルを使用する場合、認証プロトコルとして［MSCHAPv2］または［TLS］を使用することができます。 詳しくは、［Basic Setup for an IPsec Server <./configuring-a-vpn-server.md#basic-setup-for-an-ipsec-server>］__を参照してください。
```

詳しくは [DXP CloudへのVPNサーバーの接続](./connecting-a-vpn-server-to-dxp-cloud.md) をご覧ください。

<a name="connecting-dxp-cloud-to-an-ipsec-vpn-server" />

## DXP CloudとIPSec VPNサーバーの接続

この使用例では、DXP Cloud内で実行されているDXPポータルインスタンスがあり、内部ネットワーク内で実行されているHTTPサービスにアクセスする必要があると想定します。

![トポロジ2-顧客の企業ネットワーク内のHTTPサービスにアクセスするポータルインスタンス](./vpn-integration-overview/images/03.png)

次のことに注意してください。

* お客様の内部ネットワーク内で実行されている `192.168.100.30:8080` のHello Worldサービスは、サーバーアドレス `vpn:33000`介してDXPポータルサービスからアクセスできます。
* クライアントからサーバーへの接続は、 `18.188.145.101:500`実行されている顧客のVPNサーバーを介して行われます。
* ポート転送ルールは、ローカルポート **33000** を公開し、 `192.168.100.30:8080`上で実行されているアプリケーションにマップします。

接続とポート転送ルールが構成された後、Hello Worldサービスへのリクエストは、任意のDXP Cloudサービスから行うことができます。

```bash
curl vpn:33000

<body><h1>Hello world!</h1></body></html>
```

<a name="dxp-cloud-ip-ranges-for-shared-cluster" />

### 共有クラスターのDXP Cloud IP範囲

DXP Cloudは、VPNサーバーにマッピングできる幅広い利用可能なIPアドレスを使用します。 デフォルトでは、DXP Cloudサービスのすべての発信外部IPアドレスは固定されていません。

安定した発信外部IPアドレスを取得する最良の方法は、DXP Cloudプライベートクラスター機能を使用することです。

<a name="dxp-cloud-ip-ranges-for-private-cluster" />

### プライベートクラスタのDXP Cloud IP範囲

Liferay DXP Cloudは、各加入者のサービスを独自の専用クラスターに分離するオプションのプライベートクラスターを提供します。 各クラスターは、サブスクライバーのクラスターからのすべての送信インターネットトラフィック専用のゲートウェイで設定され、静的外部IPが割り当てられます。

<a name="whats-next" />

## 次のステップ

* [Client-to-Site VPNの設定例](./configuring-a-vpn-server.md)
