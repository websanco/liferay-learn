# コンテナへのファイルの提供

Liferayコンテナは、提供されたファイルを使用して、次のユースケースを実行します。

* `.properties` ファイルと `.config` ファイルでLiferayを構成する
* Tomcatを構成する
* アプリとその他のアーティファクトをデプロイする
* パッチツールを更新する
* パッチDXP
* スクリプトを実行する

コンテナが主要なコンテナフォルダー内の特定のフォルダーでファイルを見つけた場合、すべてのユースケースはコンテナの作成時にトリガーできます。

**主要なコンテナフォルダ：**

* `/mnt/liferay`
* `/user/local/liferay/scripts`

[コンテナライフサイクルおよびAPI](./container-lifecycle-and-api.md) は、スキャンされたサブフォルダ、コンテナがそれらをスキャンするフェーズ、およびそれらのファイルに対して実行されるアクションを指定します。

いくつかの方法でコンテナにファイルを提供できます。

**ファイルを提供する方法：**

* [バインドをバインド](https://docs.docker.com/storage/bind-mounts/)
* [ボリューム](https://docs.docker.com/storage/volumes/)
* [`docker cp`を使用する](https://docs.docker.com/engine/reference/commandline/cp/)

アーティファクトのデプロイと `.config` ファイルの使用を除いて、すべての使用例では、コンテナの作成時にファイルを使用できるようにする必要があります。 バインドマウントとボリュームはこれを実現します。 アーティファクトのデプロイと `.config` ファイルの適用は、バインドマウントとボリュームを使用してコンテナを作成するか、実行時に `docker cp`を使用して実行できます。

バインドマウントは、ファイルを提供するためのボリュームよりも単純であるため、ここでの例で使用されています。 コンテナにマウントするためのファイルを準備するときは、管理しやすい方法でファイルを整理すると便利です。 ここでは、Liferayコンテナへのバインドマウント、ファイルの整理、および `docker cp`の使用について説明します。

## バインドマウント形式

`docker run` コマンドにバインドマウントをいくつでも指定できます。 各バインドマウントは次の形式に従います。

  *v [source path in host]:[destination path in container]

バインドマウントソースは、ホスト内の任意のフォルダパスまたはファイルパスにすることができます。 バインドマウント先は、コンテナ内の任意のフォルダパスまたはファイルパスにすることができます。

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

## バインドマウント用のファイルの整理

Liferayコンテナのバインドマウントは、いくつかの方法で整理できます。

* キーフォルダの 1 つまたは両方にバインドします: `/mnt/liferay` 、 `/usr/local/liferay/scripts`
* サブフォルダーにバインドする
* サブフォルダーとファイルの組み合わせにバインドする

次の表に、バインドマウントメソッドの例をいくつか示し、それらの長所と短所を説明します。

### バインドマウントの例

| 方式                             | 例                                                                                                                                                               | 長所                                    | 短所                                                                                           |
|:------------------------------ |:--------------------------------------------------------------------------------------------------------------------------------------------------------------- |:------------------------------------- |:-------------------------------------------------------------------------------------------- |
| `/mnt/liferay`にマウント            | `-v [host-path]:/mnt/liferay`                                                                                                                                   | 入力ファイルを一元化します。                        | 入力ファイルは、コンテナが予期するサブフォルダーに編成する必要があります（上記の [リストされている場所を参照してください](#scanned-container-folders)）。 |
| `/mnt/liferay` サブフォルダーに個別にマウント | `-v [host-path]/[folder-1]:/mnt/liferay/deploy -v [host-path]/[folder-2]:/mnt/liferay/files`<br><br><br>注: `[host-path]` は同じパスまたは異なるパスでも可能です。 | ホスト上のさまざまな場所で入力ファイルグループを使用する柔軟性。      | 管理するホストファイルの場所が増えました。                                                                        |
| 個々のファイルにマウント                   | `-v [host path]/setenv.sh:/mnt/liferay/files/tomcat/bin/setenv.sh`                                                                                              | 入力ファイルは、 `docker run` コマンドで明確に表示されます。 | 長いドッカーランコマンド。 管理するホストファイルの場所がさらに増えます。                                                        |

コンテナの [構成フェーズ](./container-lifecycle-and-api.md#lifecycle) ファイルを提供する最も一般的な方法は、ホストフォルダーをコンテナの `/mnt/liferay` フォルダーにバインドマウントすることです。

## バインド ホストフォルダを `/mnt/liferay`にマウントする

Liferayへの構成、パッチ適用、および展開のためにファイルを集中管理する場合は、ホストフォルダをコンテナの `/mnt/liferay` フォルダにバインドマウントすることを検討してください。

```{note}
ユースケース記事のほとんどの例では、このバインドマウント戦略を使用しています。
```

手順は次のとおりです。

1.  ホスト上のフォルダーをベースフォルダーとして機能するように指定します。

2.  ベースホストフォルダーで、コンテナが作用するすべての `/mnt/liferay` サブフォルダーに対応するサブフォルダーを作成します。 コンテナフォルダの詳細は、[コンテナのライフサイクルとAPI](./container-lifecycle-and-api.md)を参照してください。

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

    ```{note}
    すべてのサブフォルダを作成する必要はありません。入力するサブフォルダだけを作成します。
    ```

3.  コンテナが作用するファイルをサブフォルダに入力します。

    例えば、 [`portal-ext.properties` ファイルを追加して](./configuring-containers.md#portal-properties) 、DXPを構成し、 [セキュリティフィックスパック](./patching-dxp-in-docker.md) を追加してインストールできます。

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

[コンテナライフサイクル](./container-lifecycle-and-api.md#liferay-phases)に従って、新しいコンテナはマウントされたホストフォルダ内のファイル（およびネストされたフォルダ）に作用し、Tomcatを起動します

## `docker cp`を使用する

[`docker cp`](https://docs.docker.com/engine/reference/commandline/cp/)コマンドは、アプリケーション、モジュール、および構成をDockerコンテナにデプロイするための便利な代替手段です。

``` bash
docker cp [file] [container]:[folder path]
```

アプリケーションのデプロイ：

``` bash
docker cp some_app.lpkg my_container:/opt/liferay/deploy
```

[設定ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)のデプロイ：

``` bash
docker cp com.liferay.journal.configuration.JournalServiceConfiguration.config my_container:/opt/liferay/osgi/configs
```

しかし、macOSなどのオペレーティングシステムでは、`docker cp`を使用すると、ファイル権限の問題が発生する場合があります。 このような場合は、バインドマウント（このセクションで説明）またはボリュームを使用してください。

## まとめ

バインドマウントと `docker cp` コマンドを使用してコンテナにファイルを提供する方法をマスターしました。 詳細は、 [コンテナのライフサイクルとAPI](./container-lifecycle-and-api.md) を参照してください。 ユースケースの詳細は、次の記事を参照してください。

* [コンテナの設定](./configuring-containers.md)
* [アプリやその他のアーティファクトをコンテナにインストールする](./installing-apps-and-other-artifacts-to-containers.md)
* [DockerでDXPにパッチを適用する](./patching-dxp-in-docker.md)