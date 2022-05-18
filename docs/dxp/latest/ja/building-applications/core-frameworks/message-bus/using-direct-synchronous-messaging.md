# ダイレクト同期メッセージングの使用

ダイレクト同期メッセージングは、すべてのリスナーがメッセージを受信するまで処理をブロックする最も簡単な方法です。 `SynchronousMessageSender`の`send(String, Message)`メソッドを呼び出し、宛先名とメッセージインスタンスを渡します。 `SynchronousMessageSender`は、現在のスレッドを使用して、宛先に登録されている各メッセージリスナーで直接メッセージ受信を処理します。 リスナーの処理が完了すると、`send(String, Message)`メソッドを呼び出したクラスで実行が続行されます。 この例は、ダイレクト同期メッセージングの使用をデモしています。

<a name="send-a-direct-synchronous-message" />

## ダイレクト同期メッセージを送信する

サンプルプロジェクトでは、`SynchronousMessageSender`を使用して、2つのリスナーに直接メッセージを送信します。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. サンプルをダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/message-bus/liferay-x6n5.zip -O
    ```

    ```bash
    unzip liferay-x6n5.zip
    ```

1. サンプルのプロジェクトモジュールをビルドしてデプロイします。

    ```bash
    cd liferay-x6n5
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールに、モジュールが起動されたことが示されます。

    ```bash
    STARTED com.acme.x6n5.able.impl_1.0.0
    STARTED com.acme.x6n5.baker.impl_1.0.0
    STARTED com.acme.x6n5.charlie.impl_1.0.0
    STARTED com.acme.x6n5.dog.impl_1.0.0
    ```

1. ブラウザで`http://localhost:8080`にあるLiferayインスタンスにアクセスし、認証情報を使用してサインインします。

1. [Gogo シェル](../../../liferay-internals/fundamentals/using-the-gogo-shell.md)を開きます。

1. Gogoシェルコマンドフィールドに、`x6n5:sendMessage`と入力し、その後にメッセージを入力します。 例:

    ```groovy
    x6n5:sendMessage foo
    ```

1. 出力が次のようになっていることを確認します。

    ```
   INFO  [pipe-x6n5:sendMessage foo][X6N5DogMessageListener:21] Received message payload foo
   INFO  [pipe-x6n5:sendMessage foo][X6N5CharlieMessageListener:21] Received message payload foo
   INFO  [pipe-x6n5:sendMessage foo][X6N5BakerOSGiCommands:28] Response: X6N5CharlieMessageListener
    ```

スレッドは、メッセージを送信するときにメッセージ送信者（つまり、`X6N5BakerOSGiCommands`）でブロックされます。  `X6N5CharlieMessageListener`および`X6N5DogMessageListener`でメッセージを処理した後、スレッドはメッセージ送信者で続行されます。

<a name="project-overview" />

## プロジェクトの概要

4つのサンプルモジュールには1つのクラスがあります。 1つのクラスは宛先を管理し、別のクラスはメッセージを送信し、他の2つは宛先に送信されたメッセージをリッスンします。

クラスの例：

| クラス                             | モジュール               | 説明                                                                       |
|:------------------------------- |:------------------- |:------------------------------------------------------------------------ |
| `X6N5AbleMessagingConfigurator` | `x6n5-able-impl`    | `acme/x6n5_able`という名前のメッセージ宛先を作成し、メッセージバスに登録します。                         |
| `X6N5BakerOSGiCommands`         | `x6n5-baker-impl`   | `acme/x6n5_able`宛先にメッセージを送信し、応答をログに記録します。                                |
| `X6N5CharlieMessageListener`    | `x6n5-charlie-impl` | `acme/x6n5_able`宛先に送信されたメッセージをリッスンします。 メッセージペイロードをログに記録し、メッセージに応答を設定します。 |
| `X6N5DogMessageListener`        | `x6n5-dog-impl`     | `acme/x6n5_able`宛先に送信されたメッセージをリッスンします。 メッセージペイロードをログに記録し、メッセージに応答を設定します。 |

イベントフローは次のとおりです。

1. ユーザーが`x6n5:sendMessage` Gogoシェルコマンドを実行すると、`X6N5BakerOSGiCommands`はメッセージペイロードのコマンド引数を`acme/x6n5_able`宛先に送信します。
1. 現在のスレッドは、各リスナー（つまり、`X6N5CharlieMessageListener`と`X6N5DogMessageListener`）のメッセージ受信を連続して処理します。 リスナーはメッセージペイロードをログに記録し、メッセージに応答を設定します。 処理された最新のリスナーからの応答は、以前の応答に優先します。
1. 処理は`X6N5BakerOSGiCommands`に戻り、メッセージ応答をログに記録します。

これで、宛先コンフィギュレーターから順に、各クラスを調べることができます

<a name="examine-the-destination-configurator" />

## 宛先コンフィグレーターを調べる

`x6n5-able-impl`モジュールの`X6N5AbleMessagingConfigurator`クラスは、`acme/x6n5_able`という名前の宛先を作成して構成します。 コードは次のとおりです。

```{literalinclude} ./using-direct-synchronous-messaging/resources/liferay-x6n5.zip/x6n5-able-impl/src/main/java/com/acme/x6n5/able/internal/messaging/X6N5AbleMessagingConfigurator.java
   :language: java
   :lines: 15-42
```

このコンフィギュレーターは [`Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) クラスです。 これは、 [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) アノテーションを使用して、`DestinationFactory`インスタンスを挿入します。

`_activate(BundleContext)`メソッドは、 [`DestinationFactory`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationFactory.java) と [`DestinationConfiguration`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationConfiguration.java) を使用して、`acme/x6n5_able`という名前の **同期** 宛先を作成します。 同期宛先は、同期メッセージング用に最適化されています。 最後に、メソッドは`BundleContext`を使用して [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) をOSGiサービスに登録します。

`X6N5AbleMessagingConfigurator`が無効化されると、その`_deactivate()`メソッドは宛先サービスの登録を解除します。

<a name="examine-the-sender" />

## 送信者を調べる

`x6n5-baker-impl`モジュールの`X6N5BakerOSGiCommands`クラスは、メッセージを宛先に送信するOSGiコマンドを提供します。

```{literalinclude} ./using-direct-synchronous-messaging/resources/liferay-x6n5.zip/x6n5-baker-impl/src/main/java/com/acme/x6n5/baker/internal/osgi/commands/X6N5BakerOSGiCommands.java
   :language: java
   :lines: 12-37
```

`X6N5BakerOSGiCommands`は、独自のクラスタイプのサービス`Component`です。 これは、`@Reference`アノテーションを使用して、 **ダイレクト** モード（アノテーションの`target = "(mode=DIRECT)"`属性で指定）に設定された`SynchronousMessageSender`を挿入します。

```{note}
*ダイレクト*モードでは、`SynchronousMessageSender` `send`メソッドは、現在のスレッドがすべてのリスナーにメッセージを配信するまで、呼び出し元のクラスをブロックします。
```

`X6N5BakerOSGiCommands`の`@Component`プロパティは、`sendMessage`と呼ばれるGogoシェルコマンド関数を`x6n5`スコープで定義します。 コマンドは`sendMessage(String)`メソッドにマップされ、入力`String`を受け取ります。

`sendMessage(String)`メソッドは、Gogoシェルコマンドの`String`をペイロードとして使用して [`Message`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Message.java) を作成します。 `SynchronousMessageSender` `send(String, Message)`メソッドは、現在のスレッドを使用して、`acme/x6n5_able` [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) メッセージリスナーにメッセージを配信します。 スレッドがすべての [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) でメッセージを処理するまで、実行は`X6N5BakerOSGiCommands`でブロックされます。 その後、`X6N5BakerOSGiCommands` `sendMessage(String)`メソッドで実行が続行され、メッセージ応答がログに記録されます。

<a name="examine-the-listeners" />

## リスナーを調べる

`x6n5-charlie-impl`モジュールの`X6N5CharlieMessageListener`クラスと`x6n5-dog-impl`モジュールの`X6N5DogMessageListener`クラスは、`acme/x6n5_able` [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) に送信されたメッセージをリッスンします。 [メッセージを聞く](./listening-for-messages.md) に示されている方法と同じ方法で登録されます。

`X6N5CharlieMessageListener`クラス：

```{literalinclude} ./using-direct-synchronous-messaging/resources/liferay-x6n5.zip/x6n5-charlie-impl/src/main/java/com/acme/x6n5/charlie/internal/messaging/X6N5CharlieMessageListener.java
   :language: java
   :lines: 10-30
```

`X6N5DogMessageListener`クラス：

```{literalinclude} ./using-direct-synchronous-messaging/resources/liferay-x6n5.zip/x6n5-dog-impl/src/main/java/com/acme/x6n5/dog/internal/messaging/X6N5DogMessageListener.java
   :language: java
   :lines: 10-30
```

各リスナーの`receive(Message)`メソッドは、メッセージペイロードをログに記録してから、メッセージ応答を独自のクラス名に設定します。

　 ダイレクト同期メッセージングの使用方法が分かりました。

<a name="whats-next" />

## 次のステップ

**デフォルト** モードを使用した同期メッセージングを検討する場合は、 [デフォルトの同期メッセージングの使用](./using-default-synchronous-messaging.md) を参照してください。

メッセージを送信した直後に処理を続行する場合は、 [非同期メッセージングの使用](./using-asynchronous-messaging.md) を参照してください。

<a name="additional-information" />

## 追加情報

* [Message Busメッセージバス](../message-bus.md)
* [メッセージを聞く](./listening-for-messages.md)
* [非同期メッセージングの使用](./using-asynchronous-messaging.md)
* [登録イベントを聞く](./listening-for-registration-events.md)
