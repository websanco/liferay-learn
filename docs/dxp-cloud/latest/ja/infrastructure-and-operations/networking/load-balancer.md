# ロードバランサー

Ingress Load Balancerは、TLS（1.0から1.2）プロトコルを使用したプロキシされたHTTP（S）接続を介して、環境のサービスへのインターネットアクセスを提供します。 各ロードバランサーには、カスタムドメインの設定に使用できる静的IPがあります。

![カスタムドメインを使用して環境のロードバランサーを構成できます。](./load-balancer/images/01.png)

専用のロードバランサーを使用すると、ポート設定、カスタムSSL証明書、CDNなどの拡張機能が無数に提供されます。 これらの機能は、サービスの `LCP.json` ファイルで設定できます：

```json
{
  "id": "webserver",
  "loadBalancer": {
    "cdn": true,
    "targetPort": 80,
    "customDomains": ["acme.liferay.cloud"],
    "ssl": {
      "key": "...",
      "crt": "..."
    }
  }
}
```

<a name="cdn" />

## CDN

Liferayのコンテンツ配信ネットワーク（CDN）は、DXP Cloudで提供される組み込み機能です。 このCDNは静的コンテンツをグローバルにキャッシュし、配信速度を大幅に向上させます。 このCDNはデフォルトでは無効になっていますが、 `LCP.json` ファイルの中の、 `loadbalancer` オブジェクトの中で、サービスに対して有効にすることができます：

```json
{
    "loadBalancer": {
        "cdn": true
    }
}
```

![CDNのステータスは [ネットワーク]ページに表示されます。](./load-balancer/images/02.png)

```{note}
現在、ドバイ/アラブ首長国連邦北部地域では、CDNはサポートされていません。
```

<a name="clearing-the-cdn-cache" />

### CDNキャッシュのクリア

CDNは、静的コンテンツをユーザーに配信する際のレイテンシを減らすことでパフォーマンスを向上させます。 しかし、キャッシュが更新される前に、コンテンツの有効性が失われた状態でユーザーに配信されてしまうことがあります。

CDNキャッシュをクリアしてコンテンツを強制的に再取得する必要がある場合は、DXP Cloudコンソールから手動でクリアすることができます：

1. DXP Cloudコンソールにログインし、目的の環境に移動します。

1. 左側のメニューから ［**Network**］ をクリックします。

1. ［**CDN**］ セクションの下の、 ［**Clear CDN Cache...**］ をクリックします。

    ![ご使用の環境の[ネットワーク]ページで [CDNキャッシュのクリア]ボタンをクリックします。](./load-balancer/images/03.png)

1. ［Clear CDN cache］ページで、キャッシュのクリアにともなう影響を理解し、CDNを有効にしたすべてのサービスに適用されるということを理解したら、全てのチェックボックスを選択します。

    ![CDNキャッシュのクリアページ。](./load-balancer/images/04.png)

1. ［**Request Cache Clearance**］ をクリックします。

ボタンをクリックすると、キャッシュをクリアするためのリクエストが送信されます。 キャッシュがクリアされるまで最大30分かかります。

```{warning}
CDNキャッシュを頻繁にクリアすると、サーバーのパフォーマンスに悪影響を与える可能性があります。これは、キャッシュが他の方法で提供していたはずのサービスへのリクエストが短期的に急増する可能性があるためです。 この影響を軽減するために、キャッシュのクリアは例外的な状況にのみ行うようにしてください。
```

<a name="port" />

## ポート

ロードバランサーのサービスエンドポイントがどの内部ポート(`［targetPort］`)を経由するかを設定できます。 DXP Cloudは、提供するサービスに適したポートを自動的に設定します。

```json
"targetPort": 3000
```

![ロードバランサーは、ポート構成を示しています。](./load-balancer/images/05.png)

<a name="custom-ssl" />

## カスタムSSL

サービスにロードバランサー属性を指定すると、次のようなネーミングパターンでサービスのエンドポイントが追加されます：

- `<SERVICE-NAME>-<PROJECT-NAME>-<ENVIRONMENT-NAME>.lfr.cloud`

`.lfr.cloud` でDXP Cloudのインフラストラクチャによって作成されたドメインは、ネットワークページのSSL証明書セクションに表示されないワイルドカード証明書によってカバーされています。

コンソールまたは `LCP.json`を介して追加されたすべてのカスタムドメインの場合、Liferay DXP Cloudは [Let's Encrypt](https://letsencrypt.org/) に到達して、自動的に更新され、かつ、作成したすべてのカスタムドメインをカバーする証明書を取得します。

<a name="adding-custom-ssl-certificates" />

### カスタムSSL証明書の追加

独自のSSL証明書を追加して、作成したカスタムドメインをカバーすることもできます。 Let's Encryptが提供するSSL証明書（DXPクラウドのコンソールで追加されたカスタムドメイン用）を使用するか、 `webserver` サービスの `LCP.json` ファイルで1つまたは複数のカスタム証明書を定義することができます。 両方の場所に証明書が存在する場合は、 `LCP.json` ファイルで定義されたカスタム証明書が優先されます。

カスタム証明書を作成する時、DXP Cloudはカプセル化境界を含む [Base64](https://tools.ietf.org/html/rfc4648#section-4) エンコーディングを使用した適切なPEM形式での鍵と証明書のみを受け入れるということに注意してください。

単一のSSL証明書を `LCP.json`ファイルに追加するには、`ssl` オブジェクトを追加し、証明書 `key`と`crt` の値を [`loadbalancer`オブジェクト](./custom-domains.md#adding-a-custom-domain-via-lcp-json) の中に入れます：

```json
{
    "loadbalancer": {
        "ssl": {
            "key": "...",
            "crt": "..."
        }
    }
}
```

`ssl` オブジェクトを `LCP.json` で使用すると、この環境で使用されるすべてのカスタムドメインにマッピングされる単一のカスタムSSL証明書が作成されます。

<a name="mapping-multiple-ssl-certificates-to-custom-domains" />

### 複数のSSL証明書をカスタムドメインにマッピングする

`ssl`オブジェクトの代わりに`certs` プロパティを使用して、 異なるSSL証明書を複数のカスタムドメインにマッピングすることもできます。

ウェブサーバーの`LCP.json`ファイルの`certs`プロパティを使って、使用できる証明書のリストを作成します。 各証明書の `key`と`crt` の値と、それらがマッピングされるカスタムドメインをグループ化します。

```json
{
    "loadbalancer": {
        "certs": [
            {
                "customDomains": ["acme.liferay.cloud"],
                "key": "...",
                "crt": "..."
            },
            {
                "customDomains": ["acme2.liferay.cloud"],
                "key": "...",
                "crt": "..."
            }
        ]
    }
}
```

```{note}
複数のSSL証明書をカスタムドメインにマッピングするには、［webserver］サービスの［LCP.json］ファイルに［certs］プロパティを追加する必要があります。 DXP Cloudコンソールでカスタムドメインを追加すると、すべてのカスタムドメインが1つの証明書にマッピングされます。
```

<a name="generating-an-ssl-certificate" />

### SSL証明書の生成

キーを生成するときは、RSA-2048またはECDSA P-256暗号化アルゴリズムのいずれかを使用し、パスフレーズで保護されたキーの使用を避ける必要があります。

カスタム証明書を作成した後は、ユーザーがその管理（新しいカスタムドメインが追加された際の更新や、期限切れの際の更新など）を行うことになります。

まだエンコードされていない場合、証明書ファイルとキー ファイルには、以下のようなテキストが含まれています（開始/終了タグに`CERTIFICATE` または `KEY` ）。

```xml
-----BEGIN CERTIFICATE-----
base64encodedcertificate
-----END CERTIFICATE-----
```

これらのファイルの内容をエンコードして使用するには、以下の手順を行います：

1. `key` と `cert` のコンテンツの両方のファイルを新規に作成します：

    ```bash
    touch originalkeyfile.key
    ```

    ```bash
    touch originalcertfile.crt
    ```

1. `key` ファイルを開き、 **開始キータグと終了キータグの間とそれを含む内容** をすべてコピーして、新しく作成したファイル（この例では、 `originalkeyfile.key`）にコピーします。 ファイルを保存します。

1. `cert` ファイルを開き、 **開始certタグと終了certタグの間とそれを含む内容** をすべてコピーして、新しく作成したファイル（この例では、 `originalcertfile.crt`）にコピーします。 ファイルを保存します。

1. 以下のコマンドを実行して（または他の望ましいエンコーディング方式を使用して）、ファイルをbase64エンコーディングの新しいファイルに変換します：

    ```bash
    openssl base64 -in originalkeyfile.key -out base64keyfile.key
    ```

    ```bash
    openssl base64 -in originalcertfile.crt -out base64certfile.crt
    ```

1. 新しくエンコーディングされた`key`ファイル（この例では、`base64keyfile.key`）から全てのコンテンツをコピーし、それを`webserver`サービスの`LCP.json`ファイルの`key`変数に貼り付けます。

1. 新しくエンコーディングされた`cert`ファイル（この例では、`base64certfile.crt`）から全てのコンテンツをコピーし、それを`webserver`サービスの`LCP.json`ファイルの`crt`変数に貼り付けます。

これで、 `key` および `cert` 値がエンコードされ、Webサーバー構成で使用できるようになりました。

```{tip}
証明書を1つの文字列に連結し、その結果をbase-64でエンコードして[crt]フィールドに入力することで、[cert]に複数の値を含めることができます。
```

[ネットワーク]ページには、サービスごとに最大1つのカスタム証明書が表示されます。 詳細は、 [カスタムドメイン](./custom-domains.md)参照してください。

![DXP Cloudは、カスタムドメインをカバーするSSL証明書のステータスを示しています。](./load-balancer/images/06.png)

<a name="environment-variables-reference" />

## 環境変数リファレンス

| 名前              | Value                              | 説明                                                                                                                                                                            |
| --------------- | ---------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `cdn`           | false                              | CDNはデフォルトで無効になっています。 設定を`true`にすることで有効にできます。                                                                                                                                  |
| `customDomains` | ["example.com", "www.example.com"] | カスタムドメインの名前。 複数をリストできます。                                                                                                                                                      |
| `targetPort`    | 3000                               | ロードバランサーのポート番号                                                                                                                                                                |
| `key`           |                                    | Base64形式のSSL証明書のキー。 これを [`ssl`](#adding-custom-ssl-certificates) オブジェクト、または [`certs`](#mapping-multiple-ssl-certificates-to-custom-domains) オブジェクトにグループ化します （複数の証明書をリストするために）。  |
| `crt`           |                                    | Base64形式のSSL証明書のcrt。 これを [`ssl`](#adding-custom-ssl-certificates) オブジェクト、または [`certs`](#mapping-multiple-ssl-certificates-to-custom-domains) オブジェクトにグループ化します （複数の証明書をリストするために）。 |

<a name="additional-information" />

## 追加情報

* [プライベートネットワーク](./private-network.md)
* [VPNインテグレーションの概要](./vpn-integration-overview.md)
* [カスタムドメイン](./custom-domains.md)
