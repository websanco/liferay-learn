# 永続的なファイルストレージボリュームの構成

管理者は、デプロイタイプ（`Deployment` または `StatefulSet`）に応じて、DXP Cloudでサービスのボリュームを設定できます。 ボリュームは、展開タイプに応じて、永続共有ストレージ（NFS）または専用ストレージ（SSD）のいずれかで保存できます。 この記事では、サービスの `LCP.json` ファイルを使用してボリュームを構成する方法について説明します。 デプロイメントのタイプの詳細については[デプロイメントのタイプを理解する](./understanding-deployment-types.md)を参照してください。

1. 永続化するデータを含むフォルダーを選択します（例： `/ liferay / opt / data`）。

1. 特定の環境のリポジトリ内の `LCP.json`ファイル（例えば、`liferay/`）に移動します。

1. `volumes` 設定を `LCP.json` ファイルに追加します。 この構成には、各ボリュームのキーが含まれている必要があります。 たとえば、次の構成には、 `/ liferay / opt / data``data` キーが含まれています。

```json
{
    "id": "lfr",
    "memory": 6144,
    "cpu": 4,
    "volumes": {
        "data": "/liferay/opt/data"
    }
}
```

```{important}
`StatefulSet`タイプのサービスの場合、ボリューム構成の変更を有効にするには、サービスを削除してから再デプロイする必要があります。
```

```{note}
バージョン3.x.xのサービスを使用している場合、`liferay` サービスの`LCP.json` ファイルは、`lcp/liferay/` directoryディレクトリにあります。 バージョン確認の詳細については、[Understanding Service Stack Versions]（../ reference / understanding-service-stack-versions.md）を参照してください。
```

<a name="sharing-volumes-between-different-services" />

## 異なるサービス間でのボリュームの共有

`Deployment` タイプのサービスのボリュームのみ、NFSを使用して同じ環境の他のサービスと共有できます。 `StatefulSet` タイプのサービスにはそれぞれ、共有できない独自のボリュームがあります。

ボリュームを共有する方法：

1. サービスのGithubリポジトリの `LCP.json` ファイル（`[ProjectID]/liferay/LCP.json`）に移動します。

1. 次のように入力します：
     * サービスID
     * 共有するコンテンツの場所（絶対パス）
     * 異なるサービスに同じボリュームキー

次の例では、`service1` および `service2` は、NFSの共有ボリュームを使用して `/documents/images` からのファイルを共有します。 これにより、両方のサービスがキーおよび宣言されたファイルパスを介してボリューム内のファイルにアクセスできます。

最初のサービス（`service1`）は `/documents/images`から写真を共有します。

```json
{
  "id": "service1",
  "volumes": {
    "photos": "/documents/images"
  }
}
```

2番目のサービス（`service2`）は、同じ場所でボリュームを宣言し、NFSを介して共有できるようにします。

```json
{
  "id": "service2",
  "volumes": {
    "photos": "/documents/images"
  }
}
```

両方のサービスは、サービスの再起動後、次のデプロイ時にNFSの指定されたボリュームにアクセスできます。

<a name="removing-contents-of-a-volume" />

## ボリュームのコンテンツの削除

サービスが削除されても、ボリュームは環境内に残ります。 サービスで使用しているボリュームの名前を変更して（または既存のボリュームの名前を変更して）新しいボリュームを使用することができますが、古いボリュームのコンテンツは（NFSまたはサービスのSSDのいずれかに）存在します。 また、ボリュームの内容を保持したくない場合は、内容を自分で削除する必要があります。

<a name="additional-information" />

## 追加情報

* [Githubリポジトリの構成](../getting-started/configuring-your-github-repository.md)
* [LCP.json 経由での設定](../reference/configuration-via-lcp-json.md)
* [デプロイタイプを理解する](./understanding-deployment-types.md)
