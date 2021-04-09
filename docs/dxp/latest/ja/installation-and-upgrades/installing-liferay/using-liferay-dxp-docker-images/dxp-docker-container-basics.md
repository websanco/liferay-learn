# DXP Dockerコンテナの基本

Docker Hub ホスト [Liferay DXP](https://hub.docker.com/r/liferay/dxp) と [Liferay Portal Community Edition (CE)](https://hub.docker.com/r/liferay/portal) Linux 上の Tomcat にバンドルされたDockerイメージ。 Liferay Docker Hubページには、さまざまなリリースのイメージの詳細とタグが表示されます。

  - [Liferay DXPイメージ](https://hub.docker.com/r/liferay/dxp)
  - [LiferayポータルCE画像](https://hub.docker.com/r/liferay/portal)

コンテナを使用するための基本は次のとおりです。

  - [コンテナを初めて起動する](#starting-a-container-for-the-first-time)
  - [ログファイルの表示](#viewing-log-files)
  - [コンテナの停止](#stopping-a-container)
  - [コンテナの再起動](#restarting-a-container)

これらのコンテナは、標準のDockerコンテナであり、そのまま起動および停止できます。 次の例では、 [Docker CLI（`docker`）](https://docs.docker.com/engine/reference/commandline/docker/)を使用していますが、任意のDockerコンテナツールを使用できます。

## コンテナを初めて起動する

コンテナはポート `8080` リッスンし、すべてのDockerコンテナと同様に開始します。

1.  [ホストポート（例： `8080`）をコンテナの `8080` ポートにマップするコンテナ](https://docs.docker.com/engine/reference/commandline/run/) を実行します。

    ``` bash
    docker run -it --name [some name] -p 8080:8080 liferay/portal:7.3.2-ga3
    ```

    ``` note::
       Naming your container is optional but can facilitate managing the container.
    ```


    The container runs and prints log messages, including this Tomcat startup completion message:
    
        INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [xx,xxx] milliseconds

2.  DXP UIを`https://localhost:8080` でブラウザで開きます。

    ![Liferay DXPの最初のランディングページ。](./dxp-docker-container-basics/images/01.png)

DXPを使用する準備が整いました。

## ログの表示

DXPログメッセージとログファイルは、ライブで表示したり、ホストにコピーしたりできます。

``` tip::
   The ``[container]`` value is the name you entered via ``--name [some name]`` in your ``run`` command.
```

### `Dockerログ` コマンド

[`docker logs`](https://docs.docker.com/engine/reference/commandline/logs/) コマンドは、コンテナログメッセージを出力します。

| コマンド                         | 結果                                         |
|:---------------------------- |:------------------------------------------ |
| `docker logs [container]`    | 現在のすべてのログメッセージを出力します                       |
| `docker logs -f [container]` | `tail -f [file]` のように、新しいログメッセージをストリーミングする |
| `docker logs -t [container]` | 各ログメッセージにタイムスタンプを追加します                     |

### `docker cp` コマンド

以下のような [`docker cp`](https://docs.docker.com/engine/reference/commandline/cp/) コマンドを使用して、ホストマシンにログファイルをコピーできます。

``` bash
docker cp [container]:/opt/liferay/logs/liferay.[timestamp].log .
```

## コンテナの停止

コンテナを停止するには、2つの方法があります。

| 方法                                                                                                                                                                                                        | 利点:                                                                                                                                           | 欠点:                                                                                                                                                                     |
|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |:--------------------------------------------------------------------------------------------------------------------------------------------- |:----------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `docker exec [container] /opt/liferay/tomcat/bin/shutdown.sh`                                                                                                                                             | DXP、Tomcat、およびその他のアプリがリソースを解放できるようにします。 コンテナエントリポイントは、 [シャットダウン後のスクリプト](./dxp-container-lifecycle-and-api.md#post-shutdown-phase-api) を実行します。 |                                                                                                                                                                         |
| `i `引数を指定して実行しているターミナルセッションで `Ctrl-C` を実行します。<br><br>注意：これは SIGINT または [`SIGKILL` シグナルをアタッチされたコンテナに送信します。](https://docs.docker.com/engine/reference/commandline/attach/#extended-description) | コンテナを停止する最速の方法。                                                                                                                               | DXP、Tomcat、およびコンテナエントリポイントは、リソースを解放せずにすぐに停止します。 エントリポイントの [シャットダウン後フェーズ](./dxp-container-lifecycle-and-api.md#post-shutdown-phase-api) はスキップされます。 本番環境ではこの方法を使用しないでください |

## コンテナの再起動

コンテナは、すべてのDockerコンテナと同様に再起動できます。

``` bash
docker start [container]
```

``` warning::
   コンテナが再起動すると、そのエントリーポイントが再度実行されます（「 `DXPコンテナライフサイクルとAPI <./dxp-container-lifecycle-and-api.md#lifecycle>`_ 」を参照してください）。 あなたが実行していることを確認任意の `スクリプト作成します <./running-scripts-in-containers.md>` _ は、エントリー・ポイントを経由して安全に再実行することができます。
```

これで、DXPコンテナの開始、停止、監視の基本を理解できました。

## 次のステップ

コンテナのエントリーポイントが何をするのか知りたい、コンテナのAPIを知りたい場合は、[DXP Container Lifecycle and API](./dxp-container-lifecycle-and-api.md)を参照してください。 コンテナの使用を開始する場合は、次のいずれかの使用例を実行してください。

  - [DXPコンテナの構成](./configuring-dxp-containers.md)
  - [コンテナへのアプリやその他のアーティファクトのインストール](./installing-apps-and-other-artifacts-to-containers.md)
  - [DockerでDXPにパッチを適用する](./patching-dxp-in-docker.md)
  - [コンテナへのファイルの提供](./providing-files-to-the-container.md)
