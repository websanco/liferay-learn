# Webサーバーサービス（Nginx）

Nginx Webサーバーは、オープンインターネットからDXP クラウドサービスへのゲートウェイ として機能します。 ユーザーからのすべてのトラフィックを処理し、高性能なWebサーバーとして機能します。

![図1：Webサーバーは、DXP Cloudで利用可能ないくつかのサービスの1つです。](./web-server-service/images/01.png)

詳細は、 [Web server service limitations](../reference/platform-limitations.md#web-server-service) のセクションを参照してください。

<a name="configurations" />

## 設定

DXP Cloudのサービスはデフォルトでうまく機能するように微調整されていますが、さらにNginxを設定する必要がある場合があります。 これを行うには、 `configs/{ENV}/conf.d/` フォルダー内に任意のCONFファイルを含めることができます。 変更をデプロイすると、ファイルが自動的にサービスに挿入され、デフォルトの設定が上書きされます。 以下は、 正しいディレクトリ内のそのようなファイルのフォルダー構造の例です： 

    webserver
    ├── configs
    │   └── common
    │       └── conf.d
    │           └── nginx.conf
    └── LCP.json

`/webserver/configs/{ENV}/`内のファイルは、DXP Cloudのウェブサーバコンテナ内の /etc/nginx/にオーバーライドとしてコピーされます。 `/webserver/configs/{ENV}/public/`のファイルはオーバーライドとしてvar/www/html/にコピーされます。

```{note}
バージョン3.x.xのサービスを使用している場合は、Nginxの設定は適切な［lcp/webserver/config/{ENV}/］ディレクトリに属しています。 バージョンの確認方法については， [サービススタックのバージョンについて](../reference/understanding-service-stack-versions.md) を参照してください．
```

<a name="environment-variables" />

## 環境変数

これらの環境変数は、Webサーバーサービスで利用できます：

| 名前                                        | デフォルト値 | 説明                                                                                                                                                                      |
| ----------------------------------------- | ------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `LCP_HAPROXY_RESOLVER_HOLD_TIME`          | `10`   | HAProxyロードバランサーの [`ホールド` 構成](https://cbonte.github.io/haproxy-dconv/2.0/configuration.html#5.3.2-hold) の設定を行います。 この構成は、 `有効な` ステータス用です。                                 |
| `LCP_HAPROXY_RESOLVER_RETRIES`            | `3`    | HAProxyロードバランサーの [`resolve_retries` の構成](https://cbonte.github.io/haproxy-dconv/2.0/configuration.html#5.3.2-resolve_retries) を行います（再試行回数は、セッションがあきらめる前に、サーバーに接続しようとします）。 |
| `LCP_HAPROXY_RESOLVER_TIMEOUT_RESOLVE`    | `1`    | HAProxyロードバランサーの [`タイムアウト` 構成](https://cbonte.github.io/haproxy-dconv/2.0/configuration.html#5.3.2-timeout) の設定を行います（イベントタイムアウトの秒数）。 この構成は、 `解決する` イベント用です。             |
| `LCP_HAPROXY_RESOLVER_TIMEOUT_RETRY`      | `1`    | HAProxyロードバランサーの [`タイムアウト` 構成](https://cbonte.github.io/haproxy-dconv/2.0/configuration.html#5.3.2-timeout) の設定を行います（イベントタイムアウトの秒数）。 この構成は、 `再試行` のイベント用です。             |
| `LCP_HAPROXY_SERVER_TEMPLATE_BACKEND_NUM` | `10`   | 任意のサービスの最大インスタンス数を上書きします。 [オートスケーリング](../manage-and-optimize/auto-scaling.md)を使用する場合は、必要な最大値を設定してください。                                                                  |
| `LCP_WEBSERVER_LOG_FORMAT`                |        | Nginxログの形式をカスタマイズします。 [official Nginx documentation](https://docs.nginx.com/nginx/admin-guide/monitoring/logging/#setting-up-the-access-log) を参照してください。                  |

[のIngress ロードバランサー](../infrastructure-and-operations/networking/load-balancer.md) もWebサーバーサービスで設定されます。 このサービスに環境変数を追加して、ロードバランサーやカスタムドメインを設定することができます。 詳細については、 [the Load Balancer environment variables reference](../infrastructure-and-operations/networking/load-balancer.md#environment-variables-reference) を参照してください。

Nginx の環境変数やその他の設定はすべて [official Nginx documentation](https://docs.nginx.com/) にあります。 サービスの`LCP.json`ファイルで、`configs/{ENV}/`ディレクトリと環境変数を設定することができます。

<a name="scripts" />

## スクリプト

スクリプトを使用して、より広範なカスタマイズを行うことができます。 ただし、これを行う際は注意してください。 これは、Webサーバーサービスをカスタマイズする最も強力な方法であり、望ましくない副作用を引き起こす可能性があります。

`configs/{ENV}/scripts/`フォルダーにある `.sh` ファイルは、サービスを開始する前に実行されます。 たとえば、すべてのログファイルを削除するスクリプトを含めるには、次のディレクトリ構造に配置します： 

    webserver
    ├── configs
    │   └── common
    │       └── scripts
    │           └── remove-log-files.sh
    └── LCP.json

```{note}
バージョン3.x.xのサービスを使用している場合、ウェブサースクリプトは適切な［lcp/webserver/script/{ENV}/］ディレクトリに置かれます。 バージョン確認の詳細については、[Understanding Service Stack Versions](*../reference/understanding-service-stack-versions.md)を参照してください。
```
