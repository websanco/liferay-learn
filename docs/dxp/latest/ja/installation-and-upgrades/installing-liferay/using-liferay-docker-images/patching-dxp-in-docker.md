# DockerでDXPにパッチを適用する

LiferayはDXP問題を修正する [パッチ](../../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md) とパッチを適用するためのパッチツールを提供しています。 移行先の新しい DXPイメージ( [Docker Hub](https://hub.docker.com/r/liferay/dxp))として、フィックスパックとサービスパックを提供します。 Liferayは、コンテナにインストールするセキュリティフィックスパック、ホットフィックス、および新しいパッチツールバージョンも提供します。

|エンタープライズサブスクリプション

DXPコンテナのパッチに関するトピックは次のとおりです。

  - [ホットフィックスまたはセキュリティフィックスパックのインストール](#installing-a-hotfix-or-security-fix-pack)
  - [フィックスパックおよびサービスパックのイメージの使用](#using-fix-pack-and-service-pack-images)
  - [パッチツールの更新](#updating-the-patching-tool)
  - [パッチのためのデータベースのアップグレード](#upgrading-the-database-for-a-patch)

## ホットフィックスまたはセキュリティフィックスパックのインストール

ホットフィックスまたはセキュリティフィックスパックをインストールするには、ボリューム内のフォルダまたはバインドマウントが必要で、コンテナの `/mnt/liferay/patching` フォルダにマップされます。

``` note::
   Please see `Providing Files to the Container <./providing-files-to-the-container.md>`_ for more information.
```

これらのパッチをインストールするには、次の2つの方法があります。

1.  [ `/ mnt/liferay/patching` フォルダーにマップする既存の修飾ボリュームまたはバインドマウントがある場合は、既存のコンテナー](#install-to-your-existing-container)インストールします。

2.  [ <1>/mnt/liferay/patching</1> フォルダーにマップする既存の修飾ボリュームまたはバインドマウントがある場合は、既存のコンテナ](#install-to-a-new-container)インストールします。

### 既存のコンテナにインストール

既存のコンテナにパッチをインストールする手順は次のとおりです。

1.  現在のコンテナを停止します。

2.  コンテナの `/mnt/liferay/patching` フォルダーにマップするボリュームまたはバインドマウントのフォルダーにパッチをコピーします。

3.  コンテナを再起動します。

### 新しいコンテナにインストール

新しいコンテナにパッチをインストールする手順は次のとおりです。

1.  ホストフォルダーと `patching`というサブフォルダーを作成します。

    ``` bash
    mkdir -p [host folder]/patching
    ```

2.  パッチファイルを `[host folder]/patching` フォルダにコピーします。 例:

    ``` bash
    cp ~/[patch file] [host folder]/patching
    ```

3.  実行中の場合は、現在のDXPコンテナを停止します。

4.  コンテナを作成し、パッチファイルのフォルダをコンテナの`/mnt/liferay/patching`フォルダにマップするバインドマウントを含むコンテナを作成します。 この例のパッチファイルは `patching`という名前のフォルダーにあるため、 [コンテナの `/mnt /liferay` フォルダーにバインドマウントできます](./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay)。

    ``` bash
    docker run ... -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

    ``` note::
       Please see `Providing Files to the Container <./providing-files-to-the-container.md>`_ for more information on specifying bind mounts.
    ```

パッチツールがパッチをインストールし、DXPが起動します。

``` important::
   If the Patching Tool reports this message: ``[patch file] is incompatible with Patching Tool version [x.y.z]``, install the latest Patching Tool. See `Installing the Patching Tool <#installing-the-patching-tool>`_ for details.
```

## フィックスパックおよびサービスパックのイメージの使用

新しいフィックスパックまたは新しいサービスパックを使用するには、そのフィックスパックまたはサービスパックに基づくイメージから作成されたコンテナに移行する必要があります。 このようなコンテナを作成する手順は次のとおりです。

1.  現在のDXPコンテナを停止します。

2.  ソース管理リポジトリまたはその他の手段を使用して、コンテナのアーティファクトと構成ファイルをバックアップします。

    ``` bash
    git commit -a
    ```

3.  [Docker Hub](https://hub.docker.com/r/liferay/dxp)からフィックスパックイメージまたはサービスパックイメージをダウンロードします。

    ``` bash
    docker pull liferay/dxp:[tag]
    ```

4.  修飾ボリュームまたはバインドマウントを使用して、フィックスパックイメージまたはサービスパックイメージに基づく新しいコンテナを作成し、アーティファクトと構成ファイルをコンテナに提供します。

    たとえば、ファイルが...のホストフォルダーにある場合
   
        [host folder]
        ├───deploy
        ├───files
        ├───patching
        └───scripts

    [コンテナの `/mnt/liferay` フォルダー](./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay) にマウントする ことができます。

    ``` bash
    docker run ... -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

DXPは新しいフィックスパックまたはサービスパックで起動します。

## パッチツールの更新

現在のパッチツールがインストールするパッチと互換性がない場合、パッチツールは次のメッセージを報告します `[パッチファイル]は、パッチツールバージョン [x.y.z]`と互換性がありません。

新しいパッチツールバージョンをインストールする手順は次のとおりです。

1.  [カスタマーポータル](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191019&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066)から最新のパッチツールをダウンロードします。

2.  パッチツールのZIPファイル名にこの形式が含まれていない場合 `patching-tool-xyzzip`、ここで `xyz` はツールのメジャー、マイナー、およびマイクロのバージョン番号であり、名前を変更してその形式を使用します。 例:

    ``` bash
    mv patching-tool.zip patching-tool-2.0.15.zip
    ```

3.  [ホットフィックスとセキュリティフィックスパックがインストールされるのと同じ方法で、コンテナの `/mnt/liferay/patching` フォルダを介してパッチツールのZIPファイルをコンテナにインストールします](#installing-a-hotfix-or-security-fix-pack)。

コンテナを再起動するか新しいコンテナを実行すると、コンテナのエントリポイントによって新しいパッチツールがインストールされます。

## パッチのためのデータベースのアップグレード

パッチでデータベースのアップグレードが必要な場合は、コンテナ化されていない環境（ [Liferay Tomcat Bundleインストール](../installing-a-liferay-tomcat-bundle.md) または [Liferay on Application Server](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/installing-liferay/installing_liferay_on_an_application_server.html)など）でデータベースアップグレードツールを使用してアップグレードする必要があります。 詳細については、 [データベースアップグレードツールの使用](../../upgrading-liferay-dxp/upgrade-basics/using-the-database-upgrade-tool.md) を参照してください。

データベースがアップグレードされたら、そのデータベースを使用するコンテナを実行します。

## 追加情報

  - [コンテナへのアプリやその他のアーティファクトのインストール](./installing-apps-and-other-artifacts-to-containers.md)
  - [DXP Dockerコンテナの基本](./docker-container-basics.md)
  - [コンテナへのファイルの提供](./providing-files-to-the-container.md)
  - [DXPコンテナのライフサイクルとAPI](./container-lifecycle-and-api.md)
  - [データベースアップグレードツールの使用](../../upgrading-liferay-dxp/upgrade-basics/using-the-database-upgrade-tool.md)
