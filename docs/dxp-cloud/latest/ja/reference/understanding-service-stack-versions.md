# サービススタックのバージョンについて

DXP Cloudのサービススタックは、お客様のDXP Cloud環境のすべてのサービスのメジャーバージョンを表しています。 個々のサービスのマイナーバージョンは異なるかもしれませんが、すべてのサービスはサービススタックのバージョンを共有しています（例：バージョン4.x.x）。 サービススタックの新バージョンでは、一般的にさまざまな新機能が導入されています（たとえば、 [バージョン4.x.xの](./dxp-cloud-project-changes-in-version-4.md)では、デフォルトのJenkinsfileが追加され、新しいリポジトリ構造が採用されています）。

DXP Cloudのサービススタック自体のバージョンは、お客様のサービスのDockerイメージのメジャーバージョンで示されます。 このバージョンは、Dockerイメージ名に表示される最後の数字です。

例えば、 `liferay` サービスのDockerイメージ名をご覧ください。

    liferaycloud/liferay-dxp:7.2.10-sp1-fp4-3.0.21

このイメージのバージョンは `3.0.21`です。 これは、この環境のサービススタックのバージョンが3.x.xであることを示しています。

どのDXP Cloud環境でも、そのサービスのDockerイメージのバージョンが様々な場所に表示されます。 環境の *概要* または *サービス* ページや各サービスのページなど、これらの場所で表示されるイメージ名の最後にある数字を調べることで、環境のサービス スタック バージョンを確認することができます。

![環境にナビゲートして最初に表示されるのは、サービスのバージョンを示す1ページです。](./understanding-service-stack-versions/images/01.png)

## リポジトリからサービススタックのバージョンを確認する

Dockerイメージのバージョンは、サービススタックが4.x.xにアップグレードされているかどうかに応じて、異なる場所に定義されています。 そのため、各バージョンごとに異なる場所を確認する必要があります。 4.x.xにアップグレードする前と後での違いについては、 [バージョン4におけるDXP Cloudプロジェクトの変更](./dxp-cloud-project-changes-in-version-4.md)を参照してください。

### 4.x.xへのアップグレード前の確認事項

リポジトリのバージョンが3.x.x以下の場合、リポジトリのルートには、各サービスのバージョンを定義した `gradle.properties` という記事があります。 このファイルを開き、Dockerイメージのバージョンを特定するために、これらのプロパティを確認します。

``` properties
liferay.workspace.lcp.backup.image=liferaycloud/backup:3.2.1
liferay.workspace.lcp.database.image=liferaycloud/database:3.2.1
liferay.workspace.lcp.search.image=liferaycloud/elasticsearch:6.1.4-3.0.3
liferay.workspace.lcp.liferay.image=liferaycloud/liferay-dxp:7.2.10-ga1-3.0.10
liferay.workspace.lcp.webserver.image=liferaycloud/nginx:1.14.2-3.1.1
liferay.workspace.lcp.jenkins.image=liferaycloud/jenkins:2.176.1-3.1.1
```

上記の例では、各サービスの名前の末尾が `3.x.x`となっており、サービススタックのバージョンが3.x.xであることを示しています。

### 4.x.xへのアップグレード後の確認事項

リポジトリがバージョン 4.x.x にアップグレードされている場合、各サービスのバージョンは、各サービスの `image` プロパティ、各サービスの `LCP.json` ファイル内で定義されます。 それぞれのファイルは、サービス名のついたフォルダ内に格納されています。

例えば、`LCP.json` のファイル `データベース` サービスのセクションを参照：

``` json
{
   "kind": "Deployment",
   "id": "database",
   "image": "liferaycloud/database:4.0.0",
}
```

この例では、 `image` プロパティの値は、 `4.0.0`で終わる名前を持っており、サービススタックのバージョンが 4.x.x であることを示しています。

``` tip::
   最新の機能を利用するには、お使いのサービススタックをバージョン ``4.x.x`` にアップグレードすることをお勧めします。 See `Upgrading Your DXP Cloud Stack <./upgrading-your-dxp-cloud-stack.md>`__ for steps on performing the upgrade.
```

## 追加情報

  - [バージョン4におけるDXP Cloudプロジェクトの変更](./dxp-cloud-project-changes-in-version-4.md)
  - [DXP Cloudスタックのアップグレード](./upgrading-your-dxp-cloud-stack.md)
