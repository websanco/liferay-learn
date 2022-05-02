# コンテナへのアプリやその他のアーティファクトのインストール

アプリケーションやその他のアーティファクト( [DXPアクティベーションキー](../../setting-up-liferay/activating-liferay-dxp.md)など)は、コンテナの `/mnt/liferay/deploy` フォルダを介してDXPコンテナにインストールされます。 コンテナエントリポイントは、 `/mnt/liferay/deploy` フォルダをコンテナの `［Liferay Home］/deploy` フォルダ (すなわち、 `/opt/liferay/deploy` ) にシンボリックリンクします。 `/mnt/liferay/deploy` フォルダに指定したアーティファクトは、Liferayに自動デプロイされます。

アーティファクトをインストールするには、次の2つの方法があります。

* [バインドマウントを使用したアーティファクトのインストール](#installing-artifacts-using-a-bind-mount)
* [`docker cp`を使用したアーティファクトのインストール](#installing-artifacts-using-docker-cp)

```{note}
また、 [Docker volume](https://docs.docker.com/storage/volumes/) を使用し、コンテナにアーティファクトをインストールすることもできます。
```

<a name="installing-artifacts-using-a-bind-mount" />

## バインドマウントを使用したアーティファクトのインストール

手順は次のとおりです。

1. ホストフォルダーと `deploy`というサブフォルダーを作成します。

    ```bash
    mkdir -p [host folder]/deploy
    ```

1. アーティファクトを `[host folder]/deploy` フォルダにコピーします。 例:

    ```bash
    cp my-app.lpkg [host folder]/deploy
    ```

1. アーティファクトのフォルダをコンテナの `/mnt/liferay/deploy` フォルダにマッピングするバインドマウントを含むコンテナを作成します。 この例のアーティファクトは`deploy`というフォルダにあるので、 [コンテナの`/mnt/liferay`フォルダにバインドマウントする](./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay) ことができます。

    ```bash
    docker run -it -m 8g -p 8080:8080 -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

Liferayが起動してアーティファクトをインストールします。 コンテナは次のようなメッセージを表示します：

```message
［LIFERAY］ ディレクトリ/mnt/liferay/deployの準備ができました。 ファイルをホストオペレーティングシステムの［host folder］/deployにコピーして、ランタイム時にモジュールをLiferayポータルにデプロイします。
```

```{note}
Liferayの起動後、追加のアーティファクトを`[host folder]/deploy`フォルダにコピーすることで、Liferayにインストールできます。
```

<a name="installing-artifacts-using-docker-cp" />

## `docker cp`を使用したアーティファクトのインストール

`docker cp` コマンドを使用して、実行中のコンテナの `/mnt/liferay/deploy` フォルダにアーティファクトをコピーします。

```bash
docker cp ~/my-apps/some-app.lpkg ［container］:/opt/liferay/deploy
```

詳細は、[コンテナへのファイルの提供](./providing-files-to-the-container.md)を参照してください。

アプリやその他のアーティファクトをLiferayにインストールする方法をマスターしました。

<a name="additional-information" />

## 追加情報

* [Docker Container Basics](./docker-container-basics.md)
* [コンテナへのファイルの提供](./providing-files-to-the-container.md)
* [コンテナのライフサイクルとAPI](./container-lifecycle-and-api.md)
* [コンテナの設定](./configuring-containers.md)
* [DockerでDXPにパッチを適用する](./patching-dxp-in-docker.md)