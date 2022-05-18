# メッセージングパフォーマンスのチューニング

メッセージングパフォーマンスは、宛先で調整されます。 パフォーマンスは、宛先タイプ、メッセージリスナーが必要とする処理の量、およびメッセージの処理に使用できるスレッドプールによって異なります。

3つの宛先タイプは次のとおりです。

**パラレル宛先**
* ここで送信されたメッセージはキューに入れられます。
* スレッドプールのワーカースレッドは、登録されたメッセージリスナーにメッセージを配信します。1つのメッセージリスナーにつきメッセージごとに1つのワーカースレッドがあります。 スレッドは、同じメッセージを宛先のメッセージリスナーに同時に配信します。

**シリアル宛先**
* ここで送信されたメッセージはキューに入れられます。
* スレッドプールのワーカースレッドは、登録されたメッセージリスナーにメッセージを配信します（メッセージごとに1つのワーカースレッド）。

**同期宛先**
* ここで送信されるメッセージは、メッセージリスナーに直接配信されます。
* メッセージを送信するスレッドは、すべてのメッセージリスナーにもメッセージを配信します。

該当する宛先タイプを使用して、さまざまな方法でメッセージを送信できます。

**宛先タイプの互換性**

以下は、[非同期メッセージング](./using-asynchronous-messaging.md)、[デフォルトの同期メッセージング](./using-default-synchronous-messaging.md)、および[直接同期メッセージング](./using-direct-synchronous-messaging.md)との各宛先タイプの互換性です。

| 宛先タイプ    | 非同期メッセージング | デフォルトの同期メッセージング | 直接同期メッセージング |
|:-------- |:---------- |:--------------- |:----------- |
| **パラレル** | はい         | はい              | いいえ         |
| **シリアル** | はい         | はい              | いいえ         |
| **同期** | いいえ        | いいえ             | はい          |

ここでは、サンプルプロジェクトのメッセージングパフォーマンスを調べることから始めます。 次に、APIを使用して宛先統計を取得し、宛先を設定します。 最後に、サンプルの宛先設定を再構成し、サンプルを再実行して、統計を調べます。

<a name="monitor-messaging-in-an-example-project" />

## サンプルプロジェクトでメッセージングを監視する

サンプルプロジェクトは、宛先を作成し、メッセージリスナーを登録し、Gogoシェルコマンドを介して宛先統計を一覧表示します。

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. サンプルをダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/message-bus/liferay-w3r2.zip -O
    ```

    ```bash
    unzip liferay-w3r2.zip
    ```

1. サンプルのプロジェクトモジュールをビルドしてデプロイします。

    ```bash
    cd liferay-w3r2
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールはモジュールの起動を確認し、宛先の構成を報告します。

    ```
    STARTED com.acme.w3r2.charlie.impl_1.0.0 [1390]
    STARTED com.acme.w3r2.able.impl_1.0.0 [1388]
    [W3R2AbleMessagingConfigurator:27] {_destinationName=acme/w3r2_able,
    _destinationType=serial, _maximumQueueSize=2147483647,
    _rejectedExecutionHandler=null, _workersCoreSize=2, _workersMaxSize=5}
    STARTED com.acme.w3r2.baker.impl_1.0.0 [1389]
    ```

1. ブラウザで`http://localhost:8080`にあるLiferayインスタンスにアクセスし、認証情報を使用してサインインします。

1. [スクリプトコンソール](../../../system-administration/using-the-script-engine/running-scripts-from-the-script-console.md)を開きます。

1. スクリプトフィールドで、次のGroovyコードを実行してメッセージを送信します。

    ```groovy
   import com.liferay.portal.kernel.messaging.*;

    MessageBusUtil.sendMessage(
        "acme/w3r2_able",
        new Message() {
            {
                setPayload("foo");
            }
        });
    ```

1. `W3R2BakerMessageListenerManager`からのメッセージリスナーがメッセージを受信したことを確認します。

    ```
    [acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
    [acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
    [acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
    [acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
    [acme/w3r2_able-2][W3R2BakerMessageListenerManager:30] Received message payload foo
    ```

1. [Gogoシェル](../../../liferay-internals/fundamentals/using-the-gogo-shell.md)を開きます。

1. Gogoシェルコマンドフィールドで`w3r2:listDestinationStats`コマンドを実行して、宛先統計を取得します。

    ```groovy
    w3r2:listDestinationStats
    ```

1. `acme/w3r2_able`宛先の10個のリスナーと送信されたメッセージ数を確認します。

    ```
    [pipe-w3r2:listDestinationStats][W3R2CharlieOSGiCommands:29] acme/w3r2_able
    active thread count 0, current thread count 1, largest thread count 1, max
    thread pool size 1, message listener count 10, min thread pool size 1, pending
    message count 0, sent message count 1
    ```

この例の3つのモジュールは、宛先を構成し、10個のメッセージリスナーを登録し、宛先の統計を一覧表示するGogoシェルコマンドを提供しました。

`w3r2-able-impl`の`W3R2AbleMessagingConfigurator`が有効になると、`acme/w3r2_able`宛先が構成され、`DestinationConfiguration`の`toString()`値がログに記録されます。

```{literalinclude} ./tuning-messaging-performance/resources/liferay-w3r2.zip/w3r2-able-impl/src/main/java/com/acme/w3r2/able/internal/messaging/W3R2AbleMessagingConfigurator.java
   :dedent: 1
   :language: java
   :lines: 20-37
```

`w3r2-charlie-impl`モジュールの`W3R2CharlieOSGiCommands`は、それが提供する`w3r2:listDestinationStats` Gogoシェルコマンドを使用して宛先統計をログに記録します。 `W3R2CharlieOSGiCommands`の`listDestinationStats()`メソッドが宛先統計を取得する方法を調べます。

```{literalinclude} ./tuning-messaging-performance/resources/liferay-w3r2.zip/w3r2-charlie-impl/src/main/java/com/acme/w3r2/charlie/internal/osgi/commands/W3R2CharlieOSGiCommands.java
   :language: java
   :lines: 13-56
```

`listDestinationStats()`メソッドは、`_messageBus`インスタンスを使用して`Destination`を取得してから、宛先から`DestinationStatistics`インスタンスを取得します。  宛先は、`DestinationStatistics`オブジェクトに最新の統計を入力します。 このメソッドは、次の宛先情報をログに記録します。

* アクティブなスレッド数
* 現在のスレッド数
* 最大スレッド数
* 最大スレッドプールサイズ
* メッセージリスナー数
* 最小（開始）スレッドプールサイズ
* 保留中のメッセージ数
* 送信されたメッセージ数

これと同じAPIを使用してメッセージの宛先を監視できます。

<a name="monitoring-messaging" />

## メッセージングの監視

メッセージングAPIでは、宛先でのメッセージングパフォーマンスをその設定に応じて監視することができます。 次の表に、宛先設定とメッセージング統計にアクセスするためのAPIメソッドを示します。

**宛先設定：**

| 宛先設定         | APIメソッド                                                                                           |
|:------------ |:------------------------------------------------------------------------------------------------- |
| 宛先タイプ        | `Destination#getDestinationType()`                                                                |
| 最大スレッドプールサイズ | `DestinationConfiguration#getWorkersMaxSize()` and `DestinationStatistic#getMaxThreadPoolSize()`  |
| 最小スレッドプールサイズ | `DestinationConfiguration#getWorkersCoreSize()` and `DestinationStatistic#getMinThreadPoolSize()` |
| メッセージキューのサイズ | `DestinationConfiguration#getMaximumQueueSize()`                                                  |

**宛先統計：**

| 宛先統計        | APIメソッド                                          |
|:----------- |:------------------------------------------------ |
| メッセージリスナー数  | `Destination#getMessageListenerCount()`          |
| 保留中のメッセージの数 | `DestinationStatistics#getPendingMessageCount()` |
| 送信されたメッセージ数 | `DestinationStatistics#getSentMessageCount()`    |
| 現在のスレッド数    | `DestinationStatistics#getCurrentThreadCount()`  |
| アクティブなスレッド数 | `DestinationStatistics#getActiveThreadCount()`   |
| 最大スレッド数     | `DestinationStatistics#getLargestThreadCount()`  |

宛先の設定に応じて宛先の統計を把握するようにしてください。

宛先統計を調べた後、宛先を再構成することでパフォーマンスの向上を試みることができます。

<a name="changing-destination-type" />

## 宛先タイプの変更

シリアル宛先を使用していて、メッセージが一部のメッセージリスナーに十分な速度で到達しない場合は、最大スレッドプールサイズを増やすか（以下で説明します）、パラレル宛先タイプに切り替えてみてください。 メッセージバスは、スレッドプールのスレッドを使用して、パラレル宛先メッセージリスナーを同時に処理します。

現在の [`DestinationConfiguration`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationConfiguration.java) を必要なタイプの1つに置き換えることで、宛先タイプを切り替えることができます。 該当する`DestinationConfiguration`メソッドを使用して、新しいパラレルまたはシリアルの`DestinationConfiguration`を作成します。

* `createParallelDestinationConfiguration(String)`
* `createSerialDestinationConfiguration(String)`

詳細については、 [Reconfigure the Example Destination](#reconfigure-the-example-destination) を参照してください。

<a name="configuring-the-message-queue-and-thread-pool" />

## メッセージキューとスレッドプールの構成

各シリアルおよびパラレル宛先には、メッセージキューと専用スレッドプールがあります。

キューがいっぱいになったときにメッセージが到着した場合、宛先の`RejectedExecutionHandler`がメッセージを処理します。 デフォルトのハンドラーはメッセージを破棄し、警告をログに記録します。 デフォルトのメッセージキューの最大サイズはJavaの最大整数値ですが、この数は必要に応じて減らすことができます。

メッセージバスは、宛先のスレッドプールからメッセージリスナー処理スレッドを引き出します。 プールには、開始サイズと最大サイズがあります。

次の [`DestinationConfiguration`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationConfiguration.java) メソッドを使用して、メッセージキューの最大サイズ、拒否された実行ハンドラー、スレッドプールの開始サイズ（コアサイズ）、およびスレッドプールの最大サイズを変更できます。

* `setMaximumQueueSize(int maximumQueueSize)`
* `setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler)`
* `setWorkersCoreSize(int workersCoreSize)`
* `setWorkersMaxSize(int workersMaxSize)`

次に、サンプルの宛先を再構成します。

<a name="reconfigure-the-example-destination" />

## サンプルの宛先を再構成します

ここでは、次の設定を使用して、サンプルの`acme/w3r2_able`宛先を再構成します。

* 宛先タイプ：`parallel`
* 開始スレッドプールサイズ：`10`
* 最大スレッドプールサイズ：`20`

手順は次のとおりです。

1. `W3R2AbleMessagingConfigurator`の`_activate(BundleContext)`メソッドを次のコードに置き換えて、別の`DestinationConfiguration`を使用します。

    ```java
    @Activate
    private void _activate(BundleContext bundleContext) {
        DestinationConfiguration destinationConfiguration =
            DestinationConfiguration.createParallelDestinationConfiguration(
                "acme/w3r2_able");

        destinationConfiguration.setWorkersCoreSize(10);
        destinationConfiguration.setWorkersMaxSize(20);

        if (_log.isInfoEnabled()) {
            _log.info(destinationConfiguration.toString());
        }

        Destination destination = _destinationFactory.createDestination(
            destinationConfiguration);

        _serviceRegistration = bundleContext.registerService(
            Destination.class, destination,
            MapUtil.singletonDictionary(
                "destination.name", destination.getName()));
    }
    ```

1. モジュールを再デプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Dockerコンテナコンソールは、`w3r2-able-impl`モジュールの起動を確認し、宛先構成を報告します。

    ```
    STARTED com.acme.w3r2.able.impl_1.0.0 [1388]
    [W3R2AbleMessagingConfigurator:27] {_destinationName=acme/w3r2_able,
    _destinationType=parallel, _maximumQueueSize=2147483647,
    _rejectedExecutionHandler=null, _workersCoreSize=10, _workersMaxSize=20}
    ```

1. 次のGogoシェルコマンドを実行して、メッセージリスナーモジュール（Acme W3R2 Baker実装）のIDを取得します。

    ```bash
    lb | grep W3R2
    ```

    各行は、対応するモジュールのID番号で始まります。

    ```bash
    1388|Active     |   10|Acme W3R2 Able Implementation (1.0.0)|1.0.0
    1389|Active     |   10|Acme W3R2 Baker Implementation (1.0.0)|1.0.0
    1390|Active     |   10|Acme W3R2 Charlie Implementation (1.0.0)|1.0.0
    ```

1. 次のGogoシェルコマンドを使用してメッセージリスナーモジュールを再起動することにより、メッセージリスナーを宛先置換にバインドします。 番号をモジュールのIDに置き換えます。

    ```bash
    stop 1389
    ```

    ```bash
    start 1389
    ```

1. スクリプトコンソールで次のGroovyコードを再度実行して、別のメッセージを送信します。

    ```groovy
    import com.liferay.portal.kernel.messaging.*;

    MessageBusUtil.sendMessage(
        "acme/w3r2_able",
        new Message() {
            {
                setPayload("foo");
            }
        });
    ```

1. Gogoシェルで`w3r2:listDestinationStats`コマンドを実行して、宛先統計を取得します。

    ```bash
    w3r2:listDestinationStats
    ```

次ようなログメッセージは、新しい設定を確認するためのものです。

```bash
[pipe-w3r2:listDestinationStats][W3R2CharlieOSGiCommands:29] acme/w3r2_able
active thread count 0, current thread count 10, largest thread count 10, max
thread pool size 20, message listener count 10, min thread pool size 10,
pending message count 0, sent message count 2
```

これで、宛先でメッセージングを監視し、宛先設定を調整する方法がわかりました。 さまざまな設定をテストして、パフォーマンスを最適化できます。

<a name="additional-information" />

## 追加情報

* [非同期メッセージングの使用](./using-asynchronous-messaging.md)
* [デフォルトの同期メッセージングの使用](./using-default-synchronous-messaging.md)
* [ダイレクト同期メッセージングの使用](./using-direct-synchronous-messaging.md)
