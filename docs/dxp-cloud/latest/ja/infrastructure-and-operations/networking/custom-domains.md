# カスタムドメイン

Liferay DXP Cloudでは、DNSプロバイダーを使ってカスタムドメインを環境サービスに接続することができます。

これを行うには、まずカスタムドメインを環境のロードバランサーのIPアドレスに登録します。 次に、DXP CloudのコンソールまたはサービスのLCP.jsonファイルを介してドメインを目的のサービスに追加します。

* [環境IPを利用したカスタムドメインの登録](#registering-a-custom-domain-with-an-environment-ip)
* [DXP Cloudサービスへのカスタムドメインの追加](#adding-a-custom-domain-to-a-dxp-cloud-service)
* [カスタムドメインのステータスの確認](#verifying-the-status-of-a-custom-domain)

<a name="registering-a-custom-domain-with-an-environment-ip" />

## 環境IPを利用したカスタムドメインの登録

各プロジェクト環境には独自のIngress Load Balancer IPがあり、カスタムドメインを環境サービスに接続するために使用することができます。

このIPは、各環境の **Network** のページと、各サービスの **カスタムドメイン** の専用ページに記載されています。

![図1：環境のIngress Load BalancerのIPは、環境のNetworkページで確認できます。](./custom-domains/images/01.png)

環境サービスにドメインを追加する前に、環境の専用IPを持つカスタムドメインをタイプ `A` レコードとして登録します。 これは、選択したドメイン名レジストラを使用して行います。

以下の例では、Cloudflareを使用してDNSレコードを作成しています。

![図2：この例では、Cloudflareをドメイン名レジストラとして使用してDNSレコードを作成しています。](./custom-domains/images/02.png)

DNSの伝播が有効になるまでに24〜48時間かかることがありますが、場合によっては数分しかかからないこともあります。

この伝播プロセス中に、あるデバイスは更新されたアドレスのドメインに到達できる可能性がありますが、別のデバイスは到達できない可能性があります。 これは、デバイスがどのDNSサーバーに到達するかによって異なります。

準備が整うと、ドメインはどのデバイスからでもアクセス可能となり、標準の `default backend - 404` エラーを返します。

<a name="adding-a-custom-domain-to-a-dxp-cloud-service" />

## DXP Cloudサービスへのカスタムドメインの追加

ドメインの準備ができたら、DXP Cloudのコンソールや `LCP.json` ファイルを使って、ドメインを環境のサービスに追加することができます。

```{important}
   最大50のカスタムドメインを環境のサービスに追加することができます。
```

以下の手順で、DXP Cloudのコンソールから環境サービスにカスタムドメインを追加します。

1. 目的の環境に移動します。

1. カスタムドメインを追加するサービスを選択します。

1. ［**Custom Domains**］ タブをクリックします。

1. ご使用の環境に登録されているカスタムドメインを[**Domain Names** フィールドに入力します。

    ![図3：サービスの［カスタムドメイン］タブを使用してドメインを追加します。](./custom-domains/images/03.png)

1. ［**Update Custom Domains**］ をクリックして追加を確定します。

```{note}
   DXP Cloudのコンソールでカスタムドメインを追加すると、自動的に「Let's Encrypt <https://letsencrypt.org/>」__が提供する証明書がすべてのドメインに使用されます。 カスタムドメインに［custom SSL certificates <./load-balancer.md#custom-ssl>］__ を使用したい場合は、代わりにWebサーバーの［LCP.json］ファイルを介して追加する必要があります。
```

<a name="adding-a-custom-domain-via-lcpjson" />

### LCP.jsonによるカスタムドメインの追加

`customDomains` プロパティをその環境の `LCP.json` ファイルに追加することで、その環境のサービスが使用するドメインを置き換えることができます。 特定の環境の `environments` 属性内にプロパティを追加します。

```json
{
    "id": "webserver",
    "environments":
    {
        "uat":
        {
            "loadBalancer":
            {
                "customDomains": ["acme.com", "www.acme.com"]
            }
        }
    } 
}
```

```{important}
   追加されたカスタムドメインごとに特定の環境を定義する必要があり、複数の環境に同じカスタムドメインを使用することはできません（異なる地域の［ディザスタリカバリ環境 <../../troubleshooting/configuring-cross-region-disaster-recovery.md>］__を除く )。 これは、DXP Cloudが適切に証明書を生成し、ユーザーを正しいドメインに導くために必要です。
```

お客様のサービスにカスタムドメインが追加され、変更がデプロイされると、DXP Cloudがルーティングを行います。

```{note}
   カスタムドメインの数は、プロビジョニングプロセス中に設定されたクォータによって制限できます。 DXP Cloudは、独自のロードバランサーを50個のカスタムドメインに制限しています。
```

<a name="verifying-the-status-of-a-custom-domain" />

## カスタムドメインのステータスの確認

カスタムドメインのステータスは、以下の2つの方法で確認できます：

* ブラウザを開き、カスタムドメインを入力します。 エンドポイントの準備が整うと、 `default backend - 404` エラーやセキュリティ警告を返さなくなります。
* DXP Cloudコンソールから環境の **Network** ページに移動して、サービスのドメインのステータスを確認します。

![図4：すべてのエンドポイントとカスタムドメインを［ネットワーク］ページに表示します。](./custom-domains/images/04.png)

バックエンドの処理により、設定後にカスタムドメインを確認できるようになるまでに時間がかかる場合があります。 カスタムドメインの検証時間に影響を与えるバックエンドのプロセスには、DXP Cloudのロードバランサーへのルートの追加、 [Let's Encrypt](https://letsencrypt.org/) によるSSLサーバー証明書の要求、Let's Encryptからのチャレンジの受信、チャレンジに成功した後に証明書によるロードバランサーの更新などがあります。

```{important}
   チャレンジプロセス中にユーザーがドメインにアクセスしようとすると、ブラウザにセキュリティ警告が表示されますが、これは無視しても問題ありません。
```

バックエンドの処理が完了すると、DXP CloudのロードバランサーのSSLサーバー証明書が更新され、サービスを安全に使用することができます。

1つ以上のカスタムSSL証明書の設定方法など、ロードバランサーLiferay DXP CloudのSSL証明書の詳細については、 [ロードバランサー](./load-balancer.md) を参照してください。

<a name="additional-information" />

## 追加情報

* [ロードバランサー](./load-balancer.md)
* [LCP.jsonによる設定](../../reference/configuration-via-lcp-json.md)
