# コンテナへのファイルの提供

Liferayコンテナは、提供されたファイルを使用して、次のユースケースを実行します。

* `.properties` ファイルと `.config` ファイルでLiferayを構成する
* Tomcatを構成する
* アプリとその他のアーティファクトをデプロイする
* パッチツールを更新する
* DXPをパッチする
* スクリプトを実行する

コンテナが主要なキーコンテナフォルダー内の特定のフォルダーでファイルを見つけた場合、すべてのユースケースはコンテナの作成時にトリガーできます。

**主要なコンテナフォルダ：**

* `/mnt/liferay`
* `/user/local/liferay/scripts`

[コンテナライフサイクルおよびAPI](./container-lifecycle-and-api.md) は、スキャンされたサブフォルダ、コンテナがそれらをスキャンするフェーズ、およびそれらのファイルに対して実行されるアクションを指定します。

いくつかの方法でコンテナにファイルを提供できます。

**ファイルを提供する方法：**

* [バインドマウント](https://docs.docker.com/storage/bind-mounts/)
* [ボリューム](https://docs.docker.com/storage/volumes/)
* [`docker cp`を使用する](https://docs.docker.com/engine/reference/commandline/cp/)

アーティファクトのデプロイと `.config` ファイルの使用を除いて、すべての使用例では、コンテナの作成時にファイルを使用できるようにする必要があります。 バインドマウントとボリュームはこれを実現します。 アーティファクトのデプロイと `.config` ファイルの適用は、バインドマウントとボリュームを使用してコンテナを作成するか、実行時に `docker cp`を使用して実行できます。

バインドマウントは、ファイルを提供するためのボリュームよりも単純であるため、ここでの例で使用されています。 コンテナにマウントするためのファイルを準備するときは、管理しやすい方法でファイルを整理すると便利です。 ここでは、Liferayコンテナへのバインドマウント、ファイルの整理、および `docker cp`の使用について説明します。

<a name="bind-mount-format" />

## バインドマウント形式

`docker run` コマンドにバインドマウントをいくつでも指定できます。 各バインドマウントは次の形式に従います。

```
-v [source path in host]:[destination path in container]
```

バインドマウントソースは、ホスト内の任意のフォルダパスまたはファイルパスにすることができます。 バインドマウント先は、コンテナ内の任意のフォルダパスまたはファイルパスにすることができます。

<a name="scanned-container-folders" />

## スキャンされたコンテナフォルダ

コンテナはこれらのフォルダをスキャンします。

* `/mnt/liferay/deploy`
* `/mnt/liferay/files` (すべてのファイルとサブフォルダはスキャンされます)
* `/mnt/liferay/patching`
* `/mnt/liferay/scripts`
* `/usr/local/liferay/scripts/post-shutdown`
* `/usr/local/liferay/scripts/pre-configure`
* `/usr/local/liferay/scripts/pre-startup`

各フォルダに関連付けられているアクションと使用例については、 [API](./container-lifecycle-and-api.md#api) を参照してください。

<a name="organizing-files-for-bind-mounting" />

## バインドマウント用のファイルの整理

Liferayコンテナのバインドマウントは、いくつかの方法で整理できます。

* キーフォルダの 1 つまたは両方にバインドします: `/mnt/liferay` 、 `/usr/local/liferay/scripts`
* サブフォルダーにバインドする
* サブフォルダーとファイルの組み合わせにバインドする

次の表に、バインドマウントメソッドの例をいくつか示し、それらの長所と短所を説明します。

### バインドマウントの例

| 方式                             | 例                                                                                                                                                               | 長所                                    | 短所                                                                                               |
|:------------------------------ |:--------------------------------------------------------------------------------------------------------------------------------------------------------------- |:------------------------------------- |:------------------------------------------------------------------------------------------------ |
| `/mnt/liferay`にマウント            | `-v［host-path］:/mnt/liferay`                                                                                                                                    | 入力ファイルを一元化します。                        | 入力ファイルは、コンテナが予期するサブフォルダーに編成する必要があります（ ロケーションがリストされている [上記](#scanned-container-folders) を参照してください）。 |
| `/mnt/liferay` サブフォルダーに個別にマウント | `-v ［host-path］/［folder-1］:/mnt/liferay/deploy -v ［host-path］/［folder-2］:/mnt/liferay/files`<br><br><br>注: `［host-path］` は同じパスまたは異なるパスでも可能です。 | ホスト上のさまざまな場所で入力ファイルグループを使用する柔軟性。      | 管理するホストファイルの場所が増えました。                                                                            |
| 個々のファイルにマウント                   | `-v ［host path］/setenv.sh:/mnt/liferay/files/tomcat/bin/setenv.sh`                                                                                              | 入力ファイルは、 `docker run` コマンドで明確に表示されます。 | 長いdocker実行コマンド。 管理するホストファイルのロケーションがさらに増えます。                                                      |

コンテナの [構成フェーズ](./container-lifecycle-and-api.md#lifecycle) ファイルを提供する最も一般的な方法は、ホストフォルダーをコンテナの `/mnt/liferay` フォルダーにバインドマウントすることです。

## ホストフォルダを `/mnt/liferay`にバインドマウントする

Liferayへの構成、パッチ適用、およびデプロイのためのファイルを集中管理する場合は、ホストフォルダをコンテナの `/mnt/liferay` フォルダにバインドマウントすることを検討してください。

```{note}
   ユースケース記事のほとんどの例では、このバインドマウント戦略を使用しています。
```

手順は次のとおりです。

1. ホスト上のフォルダーをベースフォルダーとして機能するように指定します。

1. ベースホストフォルダーで、コンテナが作用するすべての `/mnt/liferay` サブフォルダーに対応するサブフォルダーを作成します。 コンテナフォルダの詳細は、[コンテナのライフサイクルとAPI](./container-lifecycle-and-api.md)を参照してください。

    ```bash
    cd [host folder]
    mkdir deploy files patching scripts
    ```

    結果：

    ```
    [host folder]
    ├───deploy
    ├───files
    ├───patching
    └───scripts
    ```

    ```{note}
    すべてのサブフォルダを作成する必要はありません。入力するサブフォルダだけを作成します。
    ```
1. コンテナが動作するファイルをサブフォルダに入れます。

    例えば、 [`portal-ext.properties` ファイルを追加して](./configuring-containers.md#portal-properties) 、DXPを構成し、 [セキュリティフィックスパック](./patching-dxp-in-docker.md) を追加してインストールできます。

    結果：

    ```
    [host folder]
    ├───deploy
    ├───files/portal-ext.properties
    ├───patching/[Security Fix Pack file name].zip
    └───scripts
    ```

1. `docker run` コマンドで、ベースホストフォルダーをコンテナの `/mnt/liferay` フォルダーにバインドマウントします。

    ```bash
    docker run -v [host folder path]:/mnt/liferay ...
    ```

[コンテナライフサイクル](./container-lifecycle-and-api.md#liferay-phases) に従って、新しいコンテナはマウントされたホストフォルダ内のファイル（およびネストされたフォルダ）に作用し、Tomcatを起動します

## `docker cp`を使用する

[`docker cp`](https://docs.docker.com/engine/reference/commandline/cp/) コマンドは、アプリケーション、モジュール、および構成をDockerコンテナにデプロイするための便利な代替手段です。

```bash
docker cp ［file］ ［container］:［folder path］
```

アプリケーションのデプロイ：

```bash
docker cp some_app.lpkg my_container:/opt/liferay/deploy
```

[設定ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)のデプロイ：

```bash
docker cp com.liferay.journal.configuration.JournalServiceConfiguration.config my_container:/opt/liferay/osgi/configs
```

macOSで `docker cp` を使用するとuser/group `liferay`に変更するのではなく、ファイルの所有権が保持されます。 いくつかの回避策です:

* `tar` を、 `docker cp` コマンドの入力として使用し、ファイルの所有権とパーミッションを設定します。 以下に例を示します。

    ```bash
    tar -cf - command.sh --mode u=+rwx,g=-wx,o=-wx --owner liferay --group liferay | docker cp - my_container:/usr/local/liferay/scripts/pre-startup
    ```

* `docker cp` を使用した後、コンテナ内でBashセッションを開き、ファイルの所有権を変更します。

    ```bash
    docker exec -it my_container bash
    ```

    ```bash
    chown -R liferay:liferay /usr/local/liferay/scripts/pre-startup/command.sh
    ```

<a name="conclusion" />

## まとめ

バインドマウントと `docker cp` コマンドを使用してコンテナにファイルを提供する方法をマスターしました。 詳細は、 [コンテナのライフサイクルとAPI](./container-lifecycle-and-api.md) を参照してください。 ユースケースの詳細は、次の記事を参照してください。

* [コンテナの設定](./configuring-containers.md)
* [コンテナへのアプリやその他のアーティファクトのインストール](./installing-apps-and-other-artifacts-to-containers.md)
* [DockerでDXPにパッチを適用する](./patching-dxp-in-docker.md)