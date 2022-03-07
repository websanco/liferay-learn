# Webアプリケーションファイアウォール

DXP Cloudは、組み込みのWebアプリケーションファイアウォール（WAF）機能を提供し、機密データの損失、攻撃者によるシステムのハイジャック、およびダウンタイムにつながる可能性のある高度な Layer 7攻撃からアプリケーションを保護します。

ここでは、DXP Cloudの機能が一般的な攻撃から保護するWAFをどのように形成するかを学びます。

![図1：Webアプリケーションファイアウォールは一般的な攻撃から保護します。](./web-application-firewall/images/01.png)

<a name="private-network" />

## プライベートネットワーク

DXP Cloudのサービスはインターネットに公開されていません。 DXP Cloudのすべての環境には独自のプライベートネットワークがあり、同じ環境のサービスが、パブリックインターネットとやり取りすることなく、安全な通信プロトコルを介して通信できます。 このプライベートネットワークの設定については、 [プライベートネットワーク](../networking/private-network.md) を参照してください。

<a name="public-load-balancer" />

## パブリックロードバランサー

DXP Cloud Public Load Balancer（ [［Layer 7］](https://www.nginx.com/resources/glossary/layer-7-load-balancing/) ）は、TLS（1.0〜1.2）プロトコルを使用したプロキシされたHTTP（S）接続を介して環境のサービスへのインターネットアクセスを提供します。 各ロードバランサーには、カスタムドメインの設定に使用できる静的IPがあります。 HTTP（S）負荷分散は、IPスプーフィングと大規模なSYNフラッド攻撃を吸収して保護できます。 この機能はDXP Cloudに組み込まれており、ユーザー設定は必要ありません。

<a name="cdn-offload" />

## CDNオフロード

DXP Cloudの [CDN](../networking/load-balancer.md#cdn) は、クライアントとオリジンサーバー間のプロキシとして機能します。 CDNは、キャッシュ可能なコンテンツを、バックエンドサーバー（インスタンス）に送信するのではなく、ユーザーに近いPOP（Points-of-Presence）からキャッシュして提供します。

キャッシュ可能なコンテンツに対するDDoS攻撃が発生した場合、要求はオリジンサーバーではなく世界中のPOPに送信されるため、攻撃を吸収するためのより多くの場所が提供されます。

<a name="ip-allow-and-deny-lists" />

## IP許可および拒否リスト

許可リストと拒否リストを使用してIPアドレスまたは範囲に基づいて着信トラフィックを許可またはブロックする機能は、DXP Cloudが提供する [Webserver (Nginx) service](../../platform-services/web-server-service.md) を介して利用できます。

ユーザーは `nginx.conf` ファイルの `ストリーム` コンテキストまたは `サーバー` ブロック内の `allow` および `deny` ディレクティブを利用できます。

```
stream {
    ...
    server {
        listen 12345;
        deny   192.168.1.2;
        allow  192.168.1.1/24;
        allow  2001:0db8::/32;
        deny   all;
    }
}
```
