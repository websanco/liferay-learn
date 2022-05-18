# デフォルトの同期メッセージングの使用

デフォルトの同期メッセージングでは、メッセージバススレッドが登録されたメッセージリスナーにメッセージをディスパッチしている間、送信者はブロックします。 応答メッセージが受信されるか、送信者スレッドがタイムアウトすると、送信者はブロックを解除します。

```{note}
送信者は、受信した*最初の*応答メッセージのブロックを解除します。
```

サンプルプロジェクトを使用して、デフォルトの同期メッセージを送信します。 次に、例を変更してメッセージをタイムアウトにします。

<a name="send-a-default-synchronous-message" />

## デフォルトの同期メッセージを送信する

サンプルプロジェクトでは、デフォルトモードで`SynchronousMessageSender`を使用してメッセージを送信し、応答を待ちます。

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. サンプルをダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/message-bus/liferay-m4q7.zip -O
    ```

    ```bash
    unzip liferay-m4q7.zip
    ```

1. サンプルのプロジェクトモジュールをビルドしてデプロイします。

    ```bash
    cd liferay-m4q7
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールにモジュールの起動が表示されます。

    ```bash
    STARTED com.acme.m4q7.able.impl_1.0.0
    STARTED com.acme.m4q7.baker.impl_1.0.0
    STARTED com.acme.m4q7.charlie.impl_1.0.0
    ```

1. ブラウザで`http://localhost:8080`にあるLiferayインスタンスにアクセスし、認証情報を使用してサインインします。

1. [Gogoシェル](../../../liferay-internals/fundamentals/using-the-gogo-shell.md)を開きます。

1. Gogoシェルコマンドフィールドに、`m4q7:sendMessage`と入力し、その後にメッセージを入力します。 例:

    ```groovy
    m4q7:sendMessage foo
    ```

1. 出力が次のようになっていることを確認します。

    ```
    INFO  [acme/m4q7_able-2][M4Q7CharlieMessageListener:23] Received message payload foo
    INFO  [acme/m4q7_baker-2][M4Q7BakerMessageListener:21] Received message payload M4Q7CharlieMessageListener
    INFO  [pipe-m4q7:sendMessage foo][M4Q7BakerOSGiCommands:28] Response: M4Q7CharlieMessageListener
    ```

`acme/m4q7_able`宛先で、`M4Q7CharlieMessageListener`がGogoシェルメッセージを受信しました。 `acme/m4q7_baker`宛先で、`M4Q7BakerMessageListener`が`M4Q7CharlieMessageListener`から応答メッセージを受信しました。 最後に、`M4Q7BakerOSGiCommands`の`sendMessage`メソッドが、メッセージ送信者から返された応答オブジェクトをログに記録しました。

<a name="project-overview" />

## プロジェクトの概要

3つのサンプルモジュールクラスは、宛先を管理し、メッセージをリッスンし、メッセージを送信します。

**`m4q7-able-impl`モジュール：** `M4Q7AbleMessagingConfigurator`は、`acme/m4q7_able`という名前のメッセージ宛先を作成し、それをメッセージバスに登録します。

**`m4q7-baker-impl`モジュール：**

* `M4Q7BakerOSGiCommands`は、`acme/m4q7_able`宛先にメッセージを送信し、応答をログに記録します。
* `M4Q7BakerMessagingConfigurator`は、`acme/m4q7_baker`という名前のメッセージ宛先を作成し、それをメッセージバスに登録します。
* `M4Q7BakerMessageListener`は、`acme/m4q7_baker`宛先に送信されたメッセージをリッスンし、メッセージペイロードをログに記録します。

**`m4q7-charlie-impl`モジュール：** `M4Q7CharlieMessageListener`は、`acme/m4q7_able`宛先に送信されたメッセージをリッスンし、メッセージペイロードをログに記録し、元のメッセージの応答先に応答メッセージを送信します。

イベントフローは次のとおりです。

1. `m4q7:sendMessage` Gogoシェルコマンドを呼び出して、メッセージを渡します。
1. `M4Q7BakerOSGiCommands`の`sendMessage(String)`メソッドは、Gogoシェルコマンドでトリガーし、メッセージ内のコマンド引数を`acme/m4q7_able`宛先に送信します。
1. メッセージバススレッドは、メッセージを`M4Q7CharlieMessageListener`に配信します。
1. `M4Q7CharlieMessageListener`は、メッセージペイロードをログに記録し、応答メッセージ内の独自のクラス名を元のメッセージの応答先`acme/m4q7_baker`に送信します。
1. `M4Q7BakerMessageListener`は応答メッセージを受信し、そのペイロードをログに記録します。
1. 処理は`M4Q7BakerOSGiCommands`に戻り、元のメッセージへの応答をログに記録します。

次に、宛先コンフィギュレーターから順に、各クラスを調べます。

<a name="examine-the-destination-configurators" />

## 宛先コンフィグレーターを調べる

`m4q7-able-impl`モジュールと`m4q7-baker-impl`モジュールには、それぞれ宛先コンフィギュレータークラス`M4Q7AbleMessagingConfigurator`と`M4Q7BakerMessagingConfigurator`があります。 それぞれが宛先を作成して構成します。

`M4Q7AbleMessagingConfigurator`クラスは、`acme/m4q7_able`宛先を構成します。

```{literalinclude} ./using-default-synchronous-messaging/resources/liferay-m4q7.zip/m4q7-able-impl/src/main/java/com/acme/m4q7/able/internal/messaging/M4Q7AbleMessagingConfigurator.java
   :language: java
   :lines: 15-45
```

`M4Q7BakerMessagingConfigurator`クラスは、`acme/m4q7_baker`宛先を構成します。

```{literalinclude} ./using-default-synchronous-messaging/resources/liferay-m4q7.zip/m4q7-baker-impl/src/main/java/com/acme/m4q7/baker/internal/messaging/M4Q7BakerMessagingConfigurator.java
   :language: java
   :lines: 15-45
```

どちらのコンフィギュレータも [`Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) クラスです。 これらは [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) アノテーションを使用して、`DestinationFactory`インスタンスを挿入します。

`_activate(BundleContext)`メソッドは、 [`DestinationFactory`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationFactory.java) と [`DestinationConfiguration`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationConfiguration.java) を使用して **シリアル** 宛先を作成します。 最後に、`_activate(BundleContext)`メソッドは、`BundleContext`を使用して [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) をOSGiサービスに登録します。

```{warning}
デフォルトの同期メッセージングでは、シリアルまたはパラレルの宛先のみを使用してください。 それらは、`DestinationConfiguration`の`createSerialDestinationConfiguration(String)`および `createParallelDestinationConfiguration(String)`メソッドを呼び出すことで作成できます。

メッセージ送信者のタイムアウトが無効になるため、デフォルトの同期メッセージングでは同期宛先を使用しないでください。
```

コンフィギュレーターが無効になると、それらの`_deactivate()`メソッドは宛先サービスの登録を解除します。

<a name="examine-the-listeners" />

## リスナーを調べる

`m4q7-charlie-impl`モジュールの`M4Q7CharlieMessageListener`クラスは、`acme/m4q7_able` [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) に送信されたメッセージをリッスンします。 [メッセージを聞く](./listening-for-messages.md) に示されている方法と同じ方法で登録されます。

`M4Q7CharlieMessageListener`クラス：

```{literalinclude} ./using-default-synchronous-messaging/resources/liferay-m4q7.zip/m4q7-charlie-impl/src/main/java/com/acme/m4q7/charlie/internal/messaging/M4Q7CharlieMessageListener.java
   :language: java
   :lines: 12-42
```

`M4Q7CharlieMessageListener`がメッセージを受信すると、その`receive(Message)`メソッドはメッセージペイロードをログに記録し、元のメッセージの応答先に応答メッセージを送信します。 このメソッドは、応答メッセージのペイロードをリスナークラス名に設定し、応答メッセージIDを元のメッセージの応答IDに設定します。

```{important}
デフォルトの同期メッセージングでは、応答メッセージは元のメッセージの応答IDを使用する必要があり、*かつ*、応答先に送信される必要があります。
```

`m4q7-baker-impl`モジュールの`M4Q7BakerMessageListener`クラスは、`M4Q7BakerOSGiCommands`のメッセージの応答先である`acme/m4q7_baker`に送信されたメッセージをリッスンします。

`M4Q7BakerMessageListener`クラス：

```{literalinclude} ./using-default-synchronous-messaging/resources/liferay-m4q7.zip/m4q7-baker-impl/src/main/java/com/acme/m4q7/baker/internal/messaging/M4Q7BakerMessageListener.java
   :language: java
   :lines: 10-28
```

`M4Q7BakerMessageListener`がメッセージを受信すると、その`receive(Message)`メソッドはメッセージペイロードをログに記録します。

<a name="examine-the-sender" />

## 送信者を調べる

`m4q7-baker-impl`モジュールの`M4Q7BakerOSGiCommands`クラスは、メッセージ内のコマンド引数を`"acme/m4q7_able"`宛先に送信するトリガーとなるOSGiコマンドを提供します。

```{literalinclude} ./using-default-synchronous-messaging/resources/liferay-m4q7.zip/m4q7-baker-impl/src/main/java/com/acme/m4q7/baker/internal/osgi/commands/M4Q7BakerOSGiCommands.java
   :language: java
   :lines: 12-38
```

`M4Q7BakerOSGiCommands`は、独自のクラスタイプのサービス`Component`です。 これは、`@Reference`アノテーションを使用して、 **デフォルト** モード（アノテーションの`target = "(mode=DEFAULT)"`属性で指定）に設定された`SynchronousMessageSender`を挿入します。

```{note}
*デフォルト*モードでは、`SynchronousMessageSender`の`send`メソッドは、応答メッセージが受信されるまで、または送信者がタイムアウトするまで、呼び出し元のクラスをブロックします。
```

`M4Q7BakerOSGiCommands`の`@Component`プロパティは、`m4q7`スコープで`sendMessage`と呼ばれるGogoシェルコマンド関数を定義します。 このコマンドは入力`String`を受け取り、`M4Q7BakerOSGiCommands`の`sendMessage(String)`メソッドにマップします。

`sendMessage(String)`メソッドは、Gogoシェルコマンドの`String`をペイロードとして、`"acme/m4q7_baker"`を応答先として [`Message`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Message.java) を作成します。

`sendMessage(String)`メソッドは、`SynchronousMessageSender`の`send(String, Message, long)`メソッドを呼び出してメッセージを送信し、`"acme/m4q7_able"`宛先名、メッセージインスタンス、および`10000`ミリ秒のタイムアウトを渡します。 デフォルトモードでは、`SynchronousMessageSender`はメッセージバススレッドを使用してメッセージをメッセージリスナーに配信します。 元のメッセージの応答IDを持つメッセージが`"acme/m4q7_baker"`応答先で受信されるまで、実行が`M4Q7BakerOSGiCommands`クラスでブロックされます。 応答を受信すると、`M4Q7BakerOSGiCommands`メソッドで実行が続行され、メッセージ応答がログに記録されます。 一致する応答メッセージを受信する前にタイムアウトが期限切れになると、`SynchronousMessageSender`の`send(String, Message, long)`メソッドは`MessageBusException`をスローします。

```{important}
デフォルトの同期メッセージングでは、応答メッセージは元のメッセージの応答IDを使用する必要があり、*かつ*、応答先に送信される必要があります。
```

メッセージリスナーが応答メッセージを返すのを確認したので、応答のタイムアウトをテストできます。

<a name="demonstrate-the-response-timeout" />

## 応答タイムアウトのデモを実行する

メッセージ応答ロジックをオフにしてタイムアウトを強制する方法は次のとおりです。

1. `M4Q7CharlieMessageListener`の`receive(Message)`メソッドで、`_messageBus.sendMessage(...)`呼び出しをコメントアウトします。

    ```java
    @Override
    public void receive(Message message) {
        if (_log.isInfoEnabled()) {
            Object payload = message.getPayload();

            _log.info("Received message payload " + payload.toString());
        }

        // _messageBus.sendMessage(
        //  message.getResponseDestinationName(),
        //  new Message() {
        //      {
        //          setPayload("M4Q7CharlieMessageListener");
        //          setResponseId(message.getResponseId());
        //      }
        //  });
    }
    ```

1. サンプルプロジェクトを再デプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Gogoシェルコマンドフィールドに、`m4q7:sendMessage`と入力し、その後にメッセージを入力します。 例:

    ```groovy
    m4q7:sendMessage foo
    ```

1. Gogoシェルページが次のようになっていることを確認します。

    ![エラー：メッセージに対する返信がありません。](./using-default-synchronous-messaging/images/01.png)

1. Dockerコンソールのメッセージが次のようになっていることを確認します。

    ```bash
    INFO  [acme/m4q7_able-2][M4Q7CharlieMessageListener:23] Received message payload foo
    ```

`M4Q7CharlieMessageListener`はメッセージを受信しましたが、応答しませんでした。 `SynchronousMessageSender`は、Gogoシェルページに出力された`MessageBusException`をスローしました。

タイムアウトと同期してメッセージを送信しました。

<a name="whats-next" />

## 次のステップ

**ダイレクト** モードを使用した同期メッセージングを検討する場合は、 [ダイレクト同期メッセージングの使用](./using-direct-synchronous-messaging.md) を参照してください。

メッセージを送信した直後に処理を続行する場合は、 [非同期メッセージングの使用](./using-asynchronous-messaging.md) を参照してください。

<a name="additional-information" />

## 追加情報

* [Message Busメッセージバス](../message-bus.md)
* [メッセージを聞く](./listening-for-messages.md)
* [非同期メッセージングの使用](./using-asynchronous-messaging.md)
* [登録イベントを聞く](./listening-for-registration-events.md)