# コンテナへのファイルの提供

DXPコンテナは、提供されたファイルを使用して、次のユースケースを実行します。

  - `.properties` ファイルと `.config` ファイルでDXPを構成する
  - Tomcatを構成する
  - アプリとその他のアーティファクトをデプロイする
  - パッチツールを更新する
  - パッチDXP
  - スクリプトを実行する

コンテナが主要なコンテナフォルダー内の特定のフォルダーでファイルを見つけた場合、すべてのユースケースはコンテナの作成時にトリガーできます。

**主要なコンテナフォルダ：**

  - `/mnt/liferay`
  - `/user/local/liferay/scripts`

[DXPコンテナライフサイクルおよびAPI](./dxp-container-lifecycle-and-api.md) は、スキャンされたサブフォルダー、コンテナがそれらをスキャンするフェーズ、およびそれらのファイルに対して実行されるアクションを指定します。

いくつかの方法でコンテナにファイルを提供できます。

**ファイルを提供する方法：**

  - [バインドをバインド](https://docs.docker.com/storage/bind-mounts/)
  - [ボリューム](https://docs.docker.com/storage/volumes/)
  - [`docker cp`を使用する](https://docs.docker.com/engine/reference/commandline/cp/)

アーティファクトのデプロイと `.config` ファイルの使用を除いて、すべての使用例では、コンテナの作成時にファイルを使用できるようにする必要があります。 バインドマウントとボリュームはこれを実現します。 アーティファクトのデプロイと `.config` ファイルの適用は、バインドマウントとボリュームを使用してコンテナを作成するか、実行時に `docker cp`を使用して実行できます。

バインドマウントは、ファイルを提供するためのボリュームよりも単純であるため、ここでの例で使用されています。 コンテナにマウントするためのファイルを準備するときは、管理しやすい方法でファイルを整理すると便利です。 ここでは、DXPコンテナへのバインドマウント、ファイルの整理、および `docker cp` について説明します。

## バインドマウント形式

`docker run` コマンドにバインドマウントをいくつでも指定できます。 各バインドマウントは次の形式に従います。

    -v [source path in host]:[destination path in container]

バインドマウントソースは、ホスト内の任意のフォルダパスまたはファイルパスにすることができます。 バインドマウント先は、コンテナ内の任意のフォルダパスまたはファイルパスにすることができます。

## スキャンされたコンテナフォルダ

コンテナはこれらのフォルダをスキャンします。

  - `/mnt/liferay/deploy`
  - `/mnt/liferay/files` (すべてのファイルとサブフォルダはスキャンされます)
  - `/mnt/liferay/patching`
  - `/mnt/liferay/scripts`
  - `/usr/local/liferay/scripts/post-shutdown`
  - `/usr/local/liferay/scripts/pre-configure`
  - `/usr/local/liferay/scripts/pre-startup`

各フォルダに関連付けられているアクションと使用例については、 [API](./dxp-container-lifecycle-and-api.md#api) を参照してください。

## バインドマウント用のファイルの整理

DXPコンテナのバインドマウントは、いくつかの方法で整理できます。

  - キーフォルダの 1 つまたは両方にバインドします: `/mnt/liferay` 、 `/usr/local/liferay/scripts`
  - サブフォルダーにバインドする
  - サブフォルダーとファイルの組み合わせにバインドする

次の表に、バインドマウントメソッドの例をいくつか示し、それらの長所と短所を説明します。

### バインドマウントの例

| 方法                             | 例                                                                                                                                                               | 利点:                                   | 欠点:                                                                                          |
|:------------------------------ |:--------------------------------------------------------------------------------------------------------------------------------------------------------------- |:------------------------------------- |:-------------------------------------------------------------------------------------------- |
| `/mnt/liferay`にマウント            | `-v[host-path]:/mnt/liferay`                                                                                                                                    | 入力ファイルを一元化します。                        | 入力ファイルは、コンテナが予期するサブフォルダーに編成する必要があります（上記の [リストされている場所を参照してください](#scanned-container-folders)）。 |
| `/mnt/liferay` サブフォルダーに個別にマウント | `-v [host-path]/[folder-1]:/mnt/liferay/deploy -v [host-path]/[folder-2]:/mnt/liferay/files`<br><br><br>注: `[host-path]` は同じパスまたは異なるパスでも可能です。 | ホスト上のさまざまな場所で入力ファイルグループを使用する柔軟性。      | 管理するホストファイルの場所が増えました。                                                                        |
| 個々のファイルにマウント                   | `-v [host path]/setenv.sh:/mnt/liferay/files/tomcat/bin/setenv.sh`                                                                                              | 入力ファイルは、 `docker run` コマンドで明確に表示されます。 | 長いドッカーランコマンド。 管理するホストファイルの場所がさらに増えます。                                                        |

コンテナの [構成フェーズ](./dxp-container-lifecycle-and-api.md#lifecycle) ファイルを提供する最も一般的な方法は、ホストフォルダーをコンテナの `/mnt/liferay` フォルダーにバインドマウントすることです。

## バインド ホストフォルダを `/mnt/liferay`にマウントする

DXPへの構成、パッチ適用、および展開のためにファイルを集中管理する場合は、ホストフォルダーをコンテナの `/mnt/liferay` フォルダーにバインドマウントすることを検討してください。

``` note::
   Most of the examples in the use case articles use this bind mount strategy.
```

手順は次のとおりです。

1.  ホスト上のフォルダーをベースフォルダーとして機能するように指定します。

2.  ベースホストフォルダーで、コンテナが作用するすべての `/mnt/liferay` サブフォルダーに対応するサブフォルダーを作成します。 コンテナフォルダーの詳細については、 [DXP Container LifecycleおよびAPI](./dxp-container-lifecycle) を参照してください。

    ``` bash
    cd [host folder]
    mkdir deploy files patching scripts
    ```

    結果：

     [host folder]
     ├───deploy
     ├───files
     ├───patching
     └───scripts

    ``` note::
       You don't have to create all of the subfolders, just the ones you're populating.
    ```

3.  コンテナが作用するファイルをサブフォルダに入力します。

    例えば、 [で `portal-ext.properties` ファイルを追加して](./configuring-dxp-containers.md#portal-properties) 、DXPを構成し、 [でSecurity Fix Pack](./patching-dxp-in-docker.md) を追加してインストールできます。

    結果：
   
        [host folder]
        ├───deploy
        ├───files/portal-ext.properties
        ├───patching/[Security Fix Pack file name].zip
        └───scripts

4.  `ドッカーラン` コマンドで、ベースホストフォルダーをコンテナの `/mnt/liferay` フォルダーにバインドマウントします。

    ``` bash
    docker run -v [host folder path]:/mnt/liferay ...
    ```

[DXPコンテナライフサイクル](./dxp-container-lifecycle-and-api.md#liferay-phases)に従って、新しいコンテナはマウントされたホストフォルダー内のファイル（およびネストされたフォルダー）に作用し、Tomcatを起動します

## `docker cp`を使用する

実行中のコンテナにデプロイする新しいアプリ、アーティファクト、または `.config` ファイルがある場合は、 `docker cp`使用します。 次に、アプリをコンテナにコピーする例を示します。

``` bash
docker cp ~/my-apps/some-app.lpkg [container]:/mnt/liferay/deploy
```

`.config` ファイルを適用するには、それをコンテナの `/mnt/liferay/files/osgi/configs` フォルダーにコピーする必要があることに注意してください。

## まとめ

バインドマウントと `docker cp` コマンドを使用してコンテナにファイルを提供する方法をマスターしました。 詳細については、 [DXP Container Lifecycle and API](./dxp-container-lifecycle-and-api.md) を参照してください。 ユースケースの詳細については、次の記事を参照してください。

  - [DXPコンテナの構成](./configuring-dxp-containers.md)
  - [コンテナへのアプリやその他のアーティファクトのインストール](./installing-apps-and-other-artifacts-to-containers.md)
  - [DockerでDXPにパッチを適用する](./patching-dxp-in-docker.md)
