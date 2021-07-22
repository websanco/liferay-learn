# アプリやその他のアーティファクトをコンテナにインストールする

アプリケーションやその他のアーティファクト( [DXPアクティベーションキー](../../setting-up-liferay-dxp/activating-liferay-dxp.md)など)は、コンテナの `/mnt/liferay/deploy` フォルダを介してDXPコンテナにインストールされます。 コンテナエントリポイントは、 `/mnt/liferay/deploy` フォルダをコンテナの `[Liferay Home]/deploy` フォルダ (すなわち、 `/opt/liferay/deploy` ) にシンボリックリンクします。 `/mnt/liferay/deploy` フォルダに指定したアーティファクトは、DXPに自動デプロイされます。

アーティファクトをインストールするには、次の2つの方法があります。

  - [バインドマウントを使用したアーティファクトのインストール](#installing-artifacts-using-a-bind-mount)
  - [`docker cp`を使用したアーティファクトのインストール](#installing-artifacts-using-docker-cp)

<!-- end list -->

``` note::
   A `Docker volume <https://docs.docker.com/storage/volumes/>`_ can also be used to install artifacts to a container.
```

## バインドマウントを使用したアーティファクトのインストール

手順は次のとおりです。

1.  ホストフォルダーと `deploy`というサブフォルダーを作成します。

    ``` bash
    mkdir -p [host folder]/deploy
    ```

2.  アーティファクトを `[host folder]/deploy` フォルダにコピーします。 例:

    ``` bash
    cp my-app.lpkg [host folder]/deploy
    ```

3.  アーティファクトのフォルダをコンテナの `/mnt/liferay/deploy` フォルダにマッピングするバインドマウントを含むコンテナを作成します。 この例のアーティファクトは`deploy`というフォルダにあるので、[コンテナの`/mnt/liferay`フォルダ](./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay)にマウントをバインドすることができます。

    ``` bash
    docker run -it --name [container] -p 8080:8080 -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

DXPが起動してアーティファクトをインストールします。 コンテナは次のようなメッセージを表示します：

``` message
[LIFERAY] The directory /mnt/liferay/deploy is ready. Copy files to [host folder]/deploy on the host operating system to deploy modules to Liferay Portal at runtime.
```

``` note::
   After DXP launches, you can install additional artifacts to DXP by copying them to your ``[host folder]/deploy`` folder.
```

## `docker cp`を使用したアーティファクトのインストール

このような `docker cp` コマンドを使用して、実行中のコンテナの `/mnt/liferay/deploy` フォルダにアーティファクトをコピーします。

``` bash
docker cp ~/my-apps/some-app.lpkg [container]:/mnt/liferay/deploy
```

アプリやその他のアーティファクトをDXPにインストールする方法をマスターしました。

## 追加情報

  - [DXP Dockerコンテナの基本](./docker-container-basics.md)
  - [コンテナへのファイルの提供](./providing-files-to-the-container.md)
  - [DXPコンテナのライフサイクルとAPI](./container-lifecycle-and-api.md)
  - [DXPコンテナの構成](./configuring-containers.md)
  - [DockerでDXPにパッチを適用する](./patching-dxp-in-docker.md)
