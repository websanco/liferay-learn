# Liferay Dockerイメージの使用

```{toctree}
:maxdepth: 3

using-liferay-docker-images/container-lifecycle-and-api.md
using-liferay-docker-images/configuring-containers.md
using-liferay-docker-images/installing-apps-and-other-artifacts-to-containers.md
using-liferay-docker-images/patching-dxp-in-docker.md
using-liferay-docker-images/running-scripts-in-containers.md
using-liferay-docker-images/providing-files-to-the-container.md
using-liferay-docker-images/upgrading-to-a-new-docker-image.md
```

Docker Hub ホスト [Liferay DXP](https://hub.docker.com/r/liferay/dxp) と [Liferay Portal Community Edition (CE)](https://hub.docker.com/r/liferay/portal) Linux 上の Tomcat にバンドルされたDockerイメージ。 Liferay Docker Hubページには、さまざまなリリースのイメージの詳細とタグが表示されます。

* [Liferay DXPイメージ](https://hub.docker.com/r/liferay/dxp)
* [LiferayポータルCE画像](https://hub.docker.com/r/liferay/portal)

コンテナを使用するための基本は次のとおりです。

* [コンテナを初めて起動する](#starting-a-container-for-the-first-time)
* [ログファイルの表示](#viewing-log-files)
* [コンテナの停止](#stopping-a-container)
* [コンテナの再起動](#restarting-a-container)

これらのコンテナは、標準のDockerコンテナであり、そのまま起動および停止できます。 次の例では、 [Docker CLI（`docker`）](https://docs.docker.com/engine/reference/commandline/docker/)を使用していますが、任意のDockerコンテナツールを使用できます。

## コンテナを初めて起動する

コンテナはポート `8080` リッスンし、すべてのDockerコンテナと同様に開始します。

1.  [ホストポート（例： `8080`）をコンテナの `8080` ポートにマップするコンテナ](https://docs.docker.com/engine/reference/commandline/run/) を実行します。

    ``` bash
    docker run -it --name [some name] -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

    ```{note}
    コンテナの命名はオプションですが、コンテナの管理が容易になります。
    ```

    コンテナが実行され、次のTomcatの起動完了メッセージを含むログメッセージが記録されます。
    
        INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [xx,xxx] milliseconds

2.  Liferay UIを`https://localhost:8080` でブラウザで開きます。

    ![Liferayのランディングページです。](./using-liferay-docker-images/images/01.png)

Liferayを使用する準備が整いました。

## ログの表示

Liferayログメッセージとログファイルは、ライブで表示したり、ホストにコピーしたりできます。

```{tip}
`[container]`値は、 ` run`コマンドで ` --name [some name] `を介して入力した名前です。
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
| `docker exec [container] /opt/liferay/tomcat/bin/shutdown.sh`                                                                                                                                             | Liferay、Tomcat、およびその他のアプリがリソースを解放できるようにします。 コンテナエントリポイントは、 [シャットダウン後のスクリプト](./using-liferay-docker-images/container-lifecycle-and-api.md#post-shutdown-phase-api) を実行します。 |                                                                                                                                                                         |
| `i `引数を指定して実行しているターミナルセッションで `Ctrl-C` を実行します。<br><br>注意：これは SIGINT または [`SIGKILL` シグナルをアタッチされたコンテナに送信します。](https://docs.docker.com/engine/reference/commandline/attach/#extended-description) | コンテナを停止する最速の方法。                                                                                                                               | Liferay、Tomcat、およびコンテナエントリポイントは、リソースを解放せずにすぐに停止します。 エントリポイントの [シャットダウン後フェーズ](./using-liferay-docker-images/container-lifecycle-and-api.md#post-shutdown-phase-api) はスキップされます。 本番環境ではこの方法を使用しないでください |

## コンテナの再起動

コンテナは、すべてのDockerコンテナと同様に再起動できます。

``` bash
docker start [container]
```

```{warning}
コンテナが再起動すると、そのエントリーポイントが再度実行されます（「 [Container Lifecycle and API](./container-lifecycle-and-api.md#lifecycle) 」を参照してください）。 [エントリーポイントを介して実行している](./using-liferay-docker-images/running-scripts-in-containers.md) スクリプトが安全に再実行できることを確認してください。
```

これで、Liferayコンテナの開始、停止、監視の基本を理解できました。

## 次のステップ

コンテナのエントリーポイントが何をするのか知りたい、コンテナのAPIを知りたい場合は、[Container Lifecycle and API](./using-liferay-docker-images/container-lifecycle-and-api.md)を参照してください。 コンテナの使用を開始する場合は、次のいずれかの使用例を実行してください。

* [Configuring Containers](./using-liferay-docker-images/configuring-containers.md)
* [Installing Apps and Other Artifacts to Containers](./using-liferay-docker-images/installing-apps-and-other-artifacts-to-containers.md)
* [Patching DXP in Docker](./using-liferay-docker-images/patching-dxp-in-docker.md)
* [コンテナへのファイルの提供](./using-liferay-docker-images/providing-files-to-the-container.md)
* [Upgrading to a New Docker Image](./using-liferay-docker-images/upgrading-to-a-new-docker-image.md)