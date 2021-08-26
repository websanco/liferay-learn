# 永続的なファイルストレージボリュームの設定

管理者は、デプロイタイプ（`Deployment` または `StatefulSet`）に応じて、DXP Cloudでサービスのボリュームを設定できます。 ボリュームは、デプロイタイプに応じて、永続共有ストレージ（NFS）または専用ストレージ（SSD）のいずれかで保存できます。 この記事では、サービスの `LCP.json` ファイルを介してボリュームを設定する方法について説明します。 デプロイタイプの詳細は、[デプロイメントの種類について](./understanding-deployment-types.md)を参照してください。

1.  永続化するデータを含むフォルダを選択します（例： `/liferay/opt/data`）。
2.  特定の環境のリポジトリ内の `LCP.json` ファイル（例えば、 `lcp/liferay`）に移動します。
3.  `volumes` 設定を `LCP.json` ファイルに追加します。 この設定には、各ボリュームのキーが含まれている必要があります。 たとえば、次の設定には `/liferay/opt/data` の `data` キーが含まれています。

<!-- end list -->

``` json
{
    "id": "lfr",
    "memory": 6144,
    "cpu": 4,
    "volumes": {
        "data": "/liferay/opt/data"
    }
}
```

## 異なるサービス間でボリュームを共有する

ボリュームは、単一のサービス内のすべてのインスタンス間で共有されますが、 `Deployment` タイプのサービス内のボリュームのみが、NFSを使用して同じ環境内の他のサービスと共有できます。 この方法で共有されたボリュームは、NFSに保存されるため、サービスが削除されても保持されます。

ボリュームを共有するには：

1.  サービスのGithubリポジトリの `LCP.json` ファイル（`[ProjectID]/lcp/liferay/LCP.json`）に移動します。
2.  次のように入力します：
      - サービスのID
      - 共有するコンテンツの場所（絶対パス）
      - 異なるサービス用の同じボリュームキー

次の例では、`service1` および `service2` は、NFSの共有ボリュームを使用して `/documents/images` からのファイルを共有します。 これにより、両方のサービスがキーおよび宣言されたファイルパスを介してボリューム内のファイルにアクセスできます。

最初のサービス（`service1`）は `/documents/images`から写真を共有します。

``` json
{
  "id"."service1",
  "volume".{
    "photos"."/documents/images"
  } }
}。
```

2番目のサービス（`service2`）は、同じ場所でボリュームを宣言し、NFSを介して共有できるようにします。

``` json
{
  "id"."service2",
  "volume".{
    "photos"."/documents/images"
  } }
}。
```

両方のサービスは、サービスの再起動後、次のデプロイ時にNFSの指定されたボリュームにアクセスできます。

``` note::
   サービスボリュームを削除するには、サービスが所属する環境を削除します。 しかし、``StatefulSet``型のサービスのボリュームは、単一のサービスを削除することで削除することができます。
```

## 追加情報

  - [Githubリポジトリの設定](../getting-started/configuring-your-github-repository.md)
  - [LCP.json 経由での設定](../reference/configuration-via-lcp-json.md)
  - [デプロイタイプを理解する](./understanding-deployment-types.md)
