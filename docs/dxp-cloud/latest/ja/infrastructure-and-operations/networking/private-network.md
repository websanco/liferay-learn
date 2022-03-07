# プライベートネットワーク

すべての環境には独自のプライベートネットワークがあります。 これにより、同じ環境のサービスは、公共のインターネットとやり取りすることなく、複数の安全な通信プロトコルを介して通信できます。

たとえば、デフォルトでは、プロジェクトはWebサーバーサービスをパブリック接続にのみ公開します。 他のサービス（Liferay DXP、データベースなど）間の接続は、プライベートネットワークを通じてルーティングされます。

このプライベートネットワークで設定されているすべての接続に対して、次の変数を指定する必要があります：

`targetPort`：公開するサービスの内部ポート。

`port`：接続するサービスの外部ポート。

`protocol`：作成する接続のタイプ（TCPおよびUDPがサポートされています）。

`external`：接続が外部接続で使用可能かどうか。 デフォルト値 `false` は、接続を内部DXP Cloud接続に制限します。

```tip::
   接続を外部接続に公開している場合は、サービスのシェルを使用して接続のトラブルシューティングを行う必要がある場合があります。 詳しくは、Shell Access <../../troubleshooting/shell-access.md>__ を参照してください。
```

構成例を次に示します。

```json
{
  "id": "db",
  "ports": [
    {
      "port": 3400,
      "targetPort": 7000,
      "protocol": "TCP"
    },
    {
      "port": 9000,
      "targetPort": 8000,
      "protocol": "TCP",
      "external": true
    }
  ]
}
```

<a name="environment-variables-reference" />

## 環境変数リファレンス

| 名前           | 値 | 説明                                                         |
| ------------ | ----- | ---------------------------------------------------------- |
| `port`          | 3400  | 接続するサービスの外部ポート。                                            |
| `targetPort` | 7000  | 公開するサービスの内部ポート                                             |
| `protocol`      | TCP   | 作成する接続のタイプ（TCPおよびUDPがサポートされています）                           |
| `external`   | true  | 接続が外部接続で使用できるかどうか。 デフォルト値 `false` は、接続を内部DXP Cloud接続に制限します |

<a name="additional-information" />

## 追加情報

* [LCP JSONによる設定](../../reference/configuration-via-lcp-json.md)
* [シェルアクセス](../../troubleshooting/shell-access.md)
