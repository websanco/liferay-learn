# DXP CloudへのVPNサーバーの接続

DXP CloudのVPN機能を使って、DXP Cloudのサービスをプライベートネットワーク上の外部サービスに接続することができます。 これにより、外部システムとクラウド環境を、あたかも同じネットワーク内に存在するかのように運用することができます。

たとえば、企業のVPNでのみアクセス可能なディレクトリまたはアプリケーションにDXP Cloudサービスを接続する必要がある場合があります。 その方法はこちらでご紹介しています。

<a name="creating-a-vpn-configuration" />

## VPN設定の作成

1. あなたの環境の **設定** タブに移動します。

1. ページのVPNセクションにスクロールダウンし、 **Create New VPN** をクリックします。

    ![お使いの環境の設定ページから［新規VPNの作成］をクリックします。](./connecting-a-vpn-server-to-dxp-cloud/images/01.png)

1. 使用する接続プロトコルを選択します。 対応するプロトコルは以下の通りです。

    * [OpenVPN](https://openvpn.net)

    * [IPSec](https://www.cloudflare.com/learning/network-layer/what-is-ipsec/)

    ![VPNへの接続に使用するプロトコルを選択します。](./connecting-a-vpn-server-to-dxp-cloud/images/02.png)

    プロトコルを選択すると、そのプロトコルに接続するための必須項目が以下のように表示されます。

1. VPNの必須項目を入力してください。

    ***サーバー** ：サーバーのIPアドレス。

    ***Port** ：VPNのローカルポート番号です。

    ***Account name** ：管理者のメールアドレスです。

    ***Password** ：管理者のパスワードです。

    ***IKE Version** ：VPNのインターネット鍵交換のバージョンです。 このフィールドは、プロトコルとして **IPSec** を選択した場合にのみ表示されます。 **IKEv2** のみサポートしています。

    ***Certificate** : 証明書のコードです。

1. VPN接続のための1つまたは複数のポートフォワーディングルートを入力します。 各ルートの必須項目に入力します。

    ***Forwarded Port** ：転送するDXP Cloud環境内のポート番号です。

    ***Destination IP** ：VPNとインターフェイスするカスタマーネットワークのIPアドレスです。

    ***Destination Port** ：VPNに接続するお客様のネットワークのポートです。

    ![VPN接続を行う前に、1つ以上のポートフォワーディングルートを追加してください。](./connecting-a-vpn-server-to-dxp-cloud/images/03.png)

    ```{tip}
    右側のアイコンをクリックして、さらにポートフォワーディングのルートを追加します。 追加されたルートを削除するには、既存のルートの横にあるゴミ箱のアイコンをクリックします。
    ```

1. ［**VPNの作成**］ をクリックします。

VPN設定が作成されました。 ただし、VPNの詳細ページから手動で接続するまでは、VPNは接続されていません。

<a name="managing-your-vpn-configuration" />

## VPN設定の管理

VPN設定を作成した後は、詳細ページで接続状況や設定の詳細を確認したり、設定の編集や接続・切断を行うことができます。

![VPN詳細ページでは、VPNの状態、設定の詳細、VPNネットワークのアクティビティが表示されます。](./connecting-a-vpn-server-to-dxp-cloud/images/04.png)

お使いの環境の **設定** ページに移動し、設定されているVPN接続をクリックして詳細ページを表示します。

![設定されたVPN接続をクリックすると、VPNの詳細ページが表示されます。](./connecting-a-vpn-server-to-dxp-cloud/images/05.png)

```{note}
VPNの状態（接続されているか、されていないか）は、VPNの詳細ページと、あなたの環境の`設定`ページのVPNセクションの両方から見ることができます。
```

<a name="connecting-and-disconnecting-the-vpn" />

### VPNの接続と切断

VPNの詳細ページでは、右上にVPNがすでに接続されているかどうかが表示されます。 VPNが接続されていない場合は、 **接続** ボタンをクリックして、接続を確立します。

![詳細ページの右上には、接続状態が表示され、［接続］または［切断］のオプションが表示されます。](./connecting-a-vpn-server-to-dxp-cloud/images/06.png)

ボタンをクリックすると、VPNが接続を試みます。 接続に失敗した場合は、詳細ページの **関連アクティビティ** セクションに失敗した試みが表示されます。

```{tip}
サービスの[shell](../../troubleshooting/shell-access.md)を使って、以下のようなコマンドを実行することで、VPNを介したIPアドレスへの接続性を手動でテストすることができます。`curl -v [address]`
```

接続が確立されている間は、「VPN接続の試行が開始されました」というメッセージが表示され、完了するまではVPNの他の管理操作を行うことができません。 接続をキャンセルする必要がある場合（たとえば、エラーで接続が止まっているなど）、ポップアップで［Cancel］をクリックします。

![接続が確立される前に停止する必要がある場合は、メッセージポップアップの［キャンセル］ボタンをクリックしてください。](./connecting-a-vpn-server-to-dxp-cloud/images/07.png)

接続が確立された後にVPNを切断するには、右上の［アクション］メニューから ［**切断**］ をクリックします。 これにより、 **Disconnect VPN** のページが表示されます。

```{warning}
VPNを切断すると、DXPクラウドとの外部サービスとの通信が遮断されます。
```

![「VPNの切断」ページでは、先に進む前に切断の影響を確認するよう求められます。](./connecting-a-vpn-server-to-dxp-cloud/images/08.png)

VPNを切断したときの影響を確認するボックスにチェックを入れ、 ［**Disconnect VPN**］ をクリックすると、すぐにVPNを切断することができます。 一度VPNを切断すると、再び設定を変更することができます。

<a name="editing-the-configuration" />

### 設定の編集

それが作成された後は、 **VPNは、現在接続されていない限り、** VPN構成（転送ポートを含む）のいずれかの内容を変更することができます 。 VPNが接続されている場合は、設定を編集する前に [切断](#connecting-and-disconnecting-the-vpn) してください。

設定を編集するには、その環境の詳細ページに行き、右上のアクションメニューから ［**Edit...**］ を選択します。 初めてVPN設定を作成したときと同じ画面が表示されます。

![VPNが接続されていない状態で、アクションメニューの「編集」ボタンをクリックすると、設定を変更することができます。](./connecting-a-vpn-server-to-dxp-cloud/images/09.png)

<a name="deleting-the-configuration" />

### 設定内容の削除

**Delete VPN** オプションを使用すると、VPN構成を完全に削除することができます。

1. 環境の設定ページで、VPNの［アクション］メニューをクリックし、［VPNの削除］をクリックします。

1. Delete VPNページでは、チェックボックスにチェックを入れて削除を確認します。 その時にVPNが接続されていれば、さらにチェックボックスが表示され、効果を確認することができます。

    ![ [VPNの削除]のページ。](./connecting-a-vpn-server-to-dxp-cloud/images/10.png)

1. ページの下部にある［Delete VPN］をクリックします。

これでVPNは削除され、お客様の環境では使用できなくなります。 代わりに、新しいVPN構成を追加して、ご使用の環境に接続してください。

<a name="additional-information" />

## 追加情報

* [VPNインテグレーションの概要](./vpn-integration-overview.md)
* [VPNサーバーの設定](./configuring-a-vpn-server.md)
* [シェルアクセス](../../troubleshooting/shell-access.md)