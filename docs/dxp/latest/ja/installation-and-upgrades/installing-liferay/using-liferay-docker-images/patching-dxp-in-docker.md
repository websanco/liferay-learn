# DockerでDXPにパッチを適用する

Liferayの[パッチ](../../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md)はDXPの問題を修正し、パッチツールがパッチを適用します。 Liferayは、 [Docker Hub](https://hub.docker.com/r/liferay/dxp) 上で、新しい [フィックスパック](../../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md#fix-packs) 、 [セキュリティフィックスパック](../../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md#scurity-fix-packs) 、 [サービスパック](../../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md#service-packs) がそれぞれあらかじめ組み込まれたイメージを提供しています。 また、LiferayはDXPコンテナにインストールする [セキュリティフィックスパック](../../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md#scurity-fix-packs) 、 [ホットフィックス](../../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md#hotfixes) 、および新しい[パッチツール](../../maintaining-a-liferay-dxp-installation/patching-liferay/installing-the-patching-tool.md)のバージョンも提供しています。

> エンタープライズサブスクリプション

```{important}
   **Always** `back up <../../maintaining-a-liferay-dxp-installation/backing-up.md>`_ your database and installation before patching.
```

DXPコンテナのパッチに関するトピックは次のとおりです。

* [フィックスパック、セキュリティフィックスパック、およびサービスパックイメージの使用](#using-fix-pack-security-fix-pack-and-service-pack-images)
* [パッチのインストール](#installing-a-patch)
* [パッチを元に戻す](#reverting-a-patch)
* [パッチツールの更新](#updating-the-patching-tool)
* [パッチのためのデータベースのアップグレード](#upgrading-the-database-for-a-patch)

<a name="フィックスパックセキュリティフィックスパックおよびサービスパックイメージの使用" />

## フィックスパック、セキュリティフィックスパック、およびサービスパックイメージの使用

フィックスパック、セキュリティフィックスパック、およびサービスパックのイメージは、[スリムバンドル](../../maintaining-a-liferay-dxp-installation/patching-liferay/advanced-patching-for-dxp-7-2/using-slim-bundles.md)基づいています。 スリムバンドルは、通常の[Liferay Tomcatバンドル](../installing-a-liferay-tomcat-bundle.md)よりも起動が速く、フットプリントが小さくなります。 ただし、スリムバンドルに適用できるパッチは1つだけです。 したがって、これらのイメージには、次のパッチ適用の制限があります。

* フィックスパックおよびサービスパックイメージは、ホットフィックスやセキュリティフィックスパックなどの追加パッチを1つだけ受け取ることができます。

* セキュリティフィックスパックイメージにパッチを適用することはできません。 これらは、セキュリティフィックスパックですでにパッチが適用されたフィックスパックで構成されています。

新しいフィックスパック、セキュリティフィックスパック、またはサービスパックのイメージを使用するには、そのイメージに基づいてコンテナに移行する必要があります。 DXPコンテナから新しいパッチイメージに移行する方法は次のとおりです。

1. [現在のDXPコンテナを停止します](./docker-container-basics.md#stopping-a-container) 。

1. DXPコンテナのカスタマイズに使用したファイルを[バックアップ](../../maintaining-a-liferay-dxp-installation/backing-up.md)します。

    ```bash
    git commit -a
    ```

1. [Docker Hub](https://hub.docker.com/r/liferay/dxp) から新しいイメージをダウンロードします。

    ```bash
    docker pull liferay/dxp:[tag]
    ```

1. イメージに基づいて、バックアップのアーティファクトと設定ファイルを使用する新しいコンテナを作成します。

   たとえば、アーティファクトとファイルを次のようなローカルフォルダ構造に配置できます。

    ```
    [host folder]
    ├───deploy
    ├───files
    ├───patching
    └───scripts
    ```

    次に、`run`コマンドでホストフォルダをコンテナの `/mnt/liferay` フォルダ</a>にバインドマウントします。

    ```bash
    docker run ... -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

DXPは、アーティファクトとファイルを使用して新しいコンテナで起動します。

<a name="パッチのインストール" />

## パッチのインストール

フィックスパックイメージとサービスパックイメージは、追加のパッチの受け取りが1回に制限されています。 パッチは、ホットフィックス、セキュリティフィックスパック、またはその両方を組み合わせたパッチにすることができます。 この最後のタイプのパッチを入手するには、 [ヘルプセンターのチケット](https://help.liferay.com/hc) を作成し、現在のフィックスパックレベルに加えて最新のセキュリティ修正と製品修正を含むパッチを要求します。

パッチ要件：

1. コンテナに既存のパッチがない。 新しいパッチを適用する前に、既存のパッチを [元に戻す](#reverting-a-patch) 必要があります。

1. イメージはセキュリティフィックスパックイメージであってはなりません。これらのイメージには、すでにパッチ（セキュリティフィックスパック）が含まれています。

1. コンテナの `/mnt/liferay/patching` フォルダにマップするボリュームまたはバインドマウントのフォルダ。 詳細は、 [コンテナへのファイルの提供](./providing-files-to-the-container.md) を参照してください。

コンテナにパッチをインストールする方法は2つあります。

1. [既存のコンテナへのパッチのインストール](#installing-to-an-existing-container) 。

1. [新しいコンテナへのパッチのインストール](#installing-to-a-new-container) 。

### 既存のコンテナへのインストール

既存のコンテナにパッチをインストールする手順は次のとおりです。

1. [現在のコンテナを停止します](./docker-container-basics.md#stopping-a-container) 。

1. パッチを [ダウンロード](https://customer.liferay.com/downloads) して、コンテナの`/mnt/liferay/patching`フォルダにマップするボリュームまたは[バインドマウント](./providing-files-to-the-container.md)内のフォルダにコピーします。

1. [コンテナを再起動します](./docker-container-basics.md#restartings-a-container) 。

### 新しいコンテナへのインストール

新しいコンテナにパッチをインストールする手順は次のとおりです。

1. ホストフォルダと `patching`というサブフォルダを作成します。

    ```bash
    mkdir -p [host folder]/patching
    ```

1. パッチを [ダウンロード](https://customer.liferay.com/downloads) して、 `［host folder］/patching`フォルダにコピーします。 例:

   ```bash
   cp ~/[patch file] [host folder]/patching
   ```

1. [現在のDXPコンテナを停止します](./docker-container-basics.md#stopping-a-container) （実行されている場合）。

1. パッチファイルのフォルダをコンテナの`/mnt/liferay/patching`フォルダにマップするバインドマウントを含むコンテナを作成します。 この例のパッチファイルは `patching`という名前のフォルダにあるため、親フォルダ（`［host folder］`） をコンテナの `/mnt /liferay` フォルダに [バインドマウント](./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay) できます。 これにより、DXPに適用するためのパッチにアクセスできるようになります。

    ```bash
    docker run ... -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

    ```{note}
       バインドマウントを指定する方法については、`Providing Files to the Container <./providing-files-to-the-container.md>`_を参照してください。
    ```

パッチツールがパッチをインストールし、DXPが起動します。

```{important}
   パッチツールでメッセージ``［patch file］ is incompatible with Patching Tool version ［x.y.z］``が報告された場合は、最新のパッチツールをインストールしてください。 詳細は、`Installing the Patching Tool <#installing-the-patching-tool>`_を参照してください。
```

<a name="パッチを元に戻す" />

## パッチを元に戻す

コンテナからパッチを元に戻したり、パッチを適用したコンテナに別のパッチをインストールしたりする場合は、コンテナを削除して新しいコンテナを作成する必要があります。

1. [コンテナを停止します](./docker-container-basics.md#stopping-a-container) 。

    ```bash
    docker stop [container]
    ```

1. コンテナのアーティファクトとファイルを[バックアップ](../../maintaining-a-liferay-dxp-installation/backing-up.md)します。

1. コンテナを削除します。

    ```bash
    docker rm [container]
    ```

1. 以前に使用した`docker run`引数を使用して、同じイメージまたは互換性のあるフィックスパックレベルを持つイメージから新しいコンテナを作成します。 ボリュームまたは [バインドマウント](./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay) を介して、必要なパッチを適用します。

<a name="パッチツールの更新" />

## パッチツールの更新

現在のパッチツールがインストールするパッチと互換性がない場合、パッチツールには次のメッセージが報告されます：  `［patch file］ is incompatible with Patching Tool version ［x.y.z］`（`x.y.z`はツールのメジャー、マイナー、およびマクロのバージョン番号です）。

ここでは、新しい[パッチツール](../../maintaining-a-liferay-dxp-installation/patching-liferay/installing-the-patching-tool.md)のバージョンをインストールする方法を説明します。

1. [カスタマーポータル](https://customer.liferay.com/downloads?p **p** id=com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet&** com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet** productAssetCategoryId=118191019& **com** liferay **osb** customer **downloads** display **web** DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066) から最新のパッチツールをダウンロードします。

1. パッチツールのZIPファイル名の形式が`patching-tool-x.y.z.zip`でない場合は、その形式を使用するよう名前を変更します。 例:

    ```bash
    mv patching-tool.zip patching-tool-2.0.15.zip
    ```

1. [ホットフィックスとセキュリティフィックスパックがインストールされる](#installing-a-hotfix-or-security-fix-pack) のと同じ方法で、コンテナの `/mnt/liferay/patching` フォルダを介してパッチツールのZIPファイルをコンテナにインストールします。

コンテナを再起動するか新しいコンテナを実行すると、コンテナのエントリポイントによって新しいパッチツールがインストールされます。

<a name="パッチのためのデータベースのアップグレード" />

## パッチのためのデータベースのアップグレード

パッチでデータベースのアップグレードが必要な場合は、コンテナ化されていない環境でデータベースアップグレードツールを使用してアップグレードする必要があります。

1. 使用しているLiferayのバージョンの[Liferay Tomcat Bundleのインストール](../installing-a-liferay-tomcat-bundle.md)をインストールします。

1. インストールにパッチを適用します。 詳細は、[Patching Liferay](../../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md)を参照してください。

1. データベースアップグレードツールを使用してデータベースをアップグレードします。 詳細は、 [データベースアップグレードツールの使用](../../upgrading-liferay/upgrade-basics/using-the-database-upgrade-tool.md) を参照してください。

データベースがアップグレードされたら、そのデータベースを使用するコンテナを実行します。

<a name="追加情報" />

## 追加情報

* [Installing Apps and Other Artifacts to Containers](./installing-apps-and-other-artifacts-to-containers.md)
* [Docker Container Basics](./docker-container-basics.md)
* [コンテナへのファイルの提供](./providing-files-to-the-container.md)
* [コンテナのライフサイクルとAPI](./container-lifecycle-and-api.md)
* [データベースアップグレードツールの使用](../../upgrading-liferay/upgrade-basics/using-the-database-upgrade-tool.md)
