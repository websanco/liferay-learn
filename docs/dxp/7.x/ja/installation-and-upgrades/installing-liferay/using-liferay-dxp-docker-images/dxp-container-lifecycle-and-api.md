# DXPコンテナのライフサイクルとAPI

高レベルでは、コンテナはDXPがデプロイされた状態でTomcatを開始します。 ただし、さらに、コンテナエントリポイントは、以下のユースケースを実行するためのAPIを提供します。

  - スクリプトの呼び出し
  - TomcatおよびDXPの構成
  - アーティファクトのデプロイ
  - パッチのインストール
  - パッチツールの更新

コンテナは、これらのユースケースをトリガーおよび構成するためのAPIを提供します。 ライフサイクルのさまざまなフェーズでユースケースを実行します。

## ライフサイクル

環境でコンテナを作成した後、コンテナエントリポイントはその環境で次のライフサイクルフェーズを実行します。

1.  **事前設定：** [TomcatおよびDXPを構成する前に、ユーザー提供のスクリプトを実行します](./running-scripts-in-containers.md)。
2.  **設定：** TomcatでDXPを実行する準備をします。
    1.  [TomcatのJavaランタイム環境の設定](./configuring-dxp-containers.md#jvm-options)。
    2.  [ユーザが提供したファイル](./configuring-dxp-containers.md) を [Liferay Home](../../reference/liferay-home.md) にコピー。
    3.  [ユーザー提供のスクリプトの実行](./running-scripts-in-containers.md)。
    4.  [ユーザー提供のアーティファクトのデプロイ](./installing-apps-and-other-artifacts-to-containers.md)。
    5.  [パッチツール](./patching-dxp-in-docker.md#updating-the-patching-tool) をユーザー指定のバージョンで更新します。
    6.  [ユーザー提供のパッチをインストールします](./patching-dxp-in-docker.md)。
3.  **起動前：** [Tomcatを起動する前に、ユーザー提供のスクリプトを実行します](./running-scripts-in-containers.md)。
4.  **Tomcat起動：** Catalinaスクリプトを使用してTomcatを起動します。
5.  **シャットダウン後：** [Tomcatの停止後にユーザー指定のスクリプトを実行します](./running-scripts-in-containers.md)。

## API

コンテナエントリポイントは、次のコンテナフォルダでファイルをスキャンし、それらのファイルを使用して、コンテナ、Tomcat、DXPを構成し、DXPで動作します。

  - `/mnt/liferay`
  - `/user/local/liferay/scripts`

<!-- end list -->

``` note::
   `bind mount <https://docs.docker.com/storage/bind-mounts/>`_, `volumes <https://docs.docker.com/storage/volumes/>`_, ``docker cp`` など、これらのコンテナフォルダにファイルを渡すことができます。 詳細については、 `コンテナにファイルを提供する <./providing-files-to-the-container.md>`_ を参照してください。
```

上記の主要なフォルダーには、特定のアクション用に指定されたサブフォルダーがあります。 次のセクションでは、サブフォルダー、それらのファイルで実行されるアクション、および関連するユースケースをライフサイクルの段階順に記述します。

次のライフサイクルの段階は、ユーザーが提供したファイルに作用します。

  - [事前設定段階のAPI](#pre-configure-phase-api)
  - [事前設定段階のAPI](#configure-phase-api)
  - [起動前段階のAPI](#pre-startup-phase-api)
  - [シャットダウン後段階のAPI](#post-shutdown-phase-api)

### 事前設定フェーズAPI

| ファイルの場所                                    | 操作                  | ユースケース                                                |
|:------------------------------------------ |:------------------- |:----------------------------------------------------- |
| `/usr/local/liferay/scripts/pre-configure` | スクリプトをアルファベット順に実行する | [設定段階前のスクリプト](./running-scripts-in-containers.md) の実行 |

### 設定段階のAPI

| ファイルの場所                 | 操作                                                                                                                                                                                                                                                                                             | ユースケース                                                                                                                   |
|:----------------------- |:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |:------------------------------------------------------------------------------------------------------------------------ |
| `/mnt/liferay/files`    | Liferay Home (`/opt/liferay` ) の下にあるフォルダにファイルをコピーする                                                                                                                                                                                                                                            | [DXPコンテナの構成](./configuring-dxp-containers.md)<br><br>[Tomcat構成](./configuring-dxp-containers.md#jvm-options) |
| `/mnt/liferay/scripts`  | スクリプトをアルファベット順に実行する                                                                                                                                                                                                                                                                            | [構成中にスクリプトを実行](./running-scripts-in-containers.md)                                                                       |
| `/mnt/liferay/deploy`   | DXP起動時にアーティファクトを自動展開するために、 `/mnt/liferay/deploy` を `/opt/ liferay/deploy` にシンボリックリンクします。<br><br>実行時、`/mnt/liferay/deploy`、 `/opt/liferay/deploy`、またはいずれかのフォルダに取り付けられたフォルダにコピーされたアーティファクトを自動デプロイします。<br><br>注：自動デプロイされたアーティファクトは、 `/opt/liferay/osgi`下の適切なフォルダーに移動されます。 | [コンテナへのアプリやその他のアーティファクトのインストール](./installing-apps-and-other-artifacts-to-containers.md)                                  |
| `/mnt/liferay/patching` | パッチツールが提供されている場合は、それをインストールします。 提供されているパッチをインストールします。                                                                                                                                                                                                                                          | [DockerでDXPにパッチを適用する](./patching-dxp-in-docker.md)                                                                       |

### 起動前フェーズAPI

| ファイルの場所                                  | 操作                  | ユースケース                                                      |
|:---------------------------------------- |:------------------- |:----------------------------------------------------------- |
| `/usr/local/liferay/scripts/pre-startup` | スクリプトをアルファベット順に実行する | [Tomcatを開始する前にスクリプトを実行](./running-scripts-in-containers.md) |

### シャットダウン後フェーズAPI

| ファイルの場所                                    | 操作                  | ユースケース                                                            |
|:------------------------------------------ |:------------------- |:----------------------------------------------------------------- |
| `/usr/local/liferay/scripts/post-shutdown` | スクリプトをアルファベット順に実行する | [Tomcatをシャットダウンした後の](./running-scripts-in-containers.md) スクリプトの実行 |

## 次のステップ

コンテナのライフサイクルと API いついて学習できました。これで、 [コンテナに](./providing-files-to-the-container.md) ファイルを提供する最良の方法を決定できます。 または、上記の表に記載されているユースケースの実行を開始できます。 それらはあなたの便宜のためにここにリストされています：

  - [DXPコンテナの構成](./configuring-dxp-containers.md)
  - [コンテナへのアプリやその他のアーティファクトのインストール](./installing-apps-and-other-artifacts-to-containers.md)
  - [DockerでDXPにパッチを適用する](./patching-dxp-in-docker.md)
