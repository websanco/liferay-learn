# 登録イベントを聞く

メッセージングAPIは、宛先登録イベントおよびメッセージリスナー登録イベントのリッスンをサポートします。 これらのイベントをリッスンする理由は次のとおりです。

* 興味のあるメッセージを新しい宛先に送信する場合
* 宛先でのメッセージングに調整が必要な場合
* 未登録の宛先に依存する場合
* 登録を解除して再割り当て可能なリソースを解放する場合

サンプルプロジェクトは、これらの登録イベントをリッスンする方法を示しています。 サンプルを実行することから始めます。 次に、イベントリスナーの実装を調べます。 最後に、登録解除イベントをトリガーします。

<a name="trigger-the-events-in-an-example" />

## サンプルでイベントをトリガーする

これらは、宛先の登録リスナー、メッセージリスナーの登録リスナー、そしてそれらがリッスンするイベントをトリガーするクラスをデプロイします。

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. サンプルをダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/message-bus/liferay-s3z9.zip -O
    ```

    ```bash
    unzip liferay-s3z9.zip
    ```

1. `s3z9-able-impl`モジュールをデプロイして、メッセージバスイベントリスナーを起動します。

    ```bash
    cd liferay-s3z9/s3z9-able-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. `s3z9-baker-impl`モジュールをデプロイして、宛先を追加します。

    ```bash
    cd ../s3z9-baker-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Dockerコンテナのコンソールには、新しく追加された宛先に対する`S3Z9AbleMessageBusEventListener`の応答が表示されます。

    ```bash
    [main][S3Z9AbleMessageBusEventListener:17] Destination added acme/s3z9_baker
    ```

1. `s3z9-charlie-impl`モジュールをデプロイして、宛先イベントリスナーを起動します。

    ```bash
    cd ../s3z9-charlie-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. `s3z9-dog-impl`モジュールをデプロイして、メッセージリスナーを宛先に登録します。

    ```bash
    cd ../s3z9-dog-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Dockerコンテナのコンソールには、新しく登録されたメッセージリスナーに対する`S3Z9CharlieDestinationEventListener`の応答が表示されます。

    ```bash
    [S3Z9CharlieDestinationEventListener:23] Registered message listener to acme/s3z9_baker
    ```

モジュールの概要は次のとおりです。

1. `s3z9-able-impl`の [`MessageBusEventListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageBusEventListener.java) 実装は、宛先の追加と削除をリッスンします。
1. `s3z9-baker-impl`のメッセージングコンフィギュレータクラスは宛先を追加します。`s3z9-able-impl`の`MessageBusEventListener`実装は、追加された宛先通知を受信し、イベントをログに記録します。
1. `s3z9-charlie-impl`の [`DestinationEventListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationEventListener.java) 実装は、宛先に登録するメッセージリスナー、または宛先から登録解除するメッセージリスナーをリッスンします。
1. `s3z9-dog-impl`の [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) 実装は宛先に登録されます。 `s3z9-charlie-impl`の`DestinationEventListener`実装は、メッセージリスナー登録通知を受信し、イベントをログに記録します。

<a name="examine-the-messagebuseventlistener" />

## `MessageBusEventListener`を調べる

[`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) が追加または削除されると、メッセージバスは [`MessageBusEventListeners`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageBusEventListener.java) に通知します。 以下に、`MessageBusEventListener`の実装例を示します。

```{literalinclude} ./listening-for-registration-events/resources/liferay-s3z9.zip/s3z9-able-impl/src/main/java/com/acme/s3z9/able/internal/messaging/S3Z9AbleMessageBusEventListener.java
:language: java
:lines: 10-31
```

[`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) アノテーションとその`service = MessageBusEventListener.class` 属性は、`S3Z9AbleMessageBusEventListener`を`MessageBusEventListener`として登録するようにランタイムフレームワークにシグナルを送信します。 実装は、`MessageBusEventListener`の次の2つのメソッドをオーバーライドします。

* `destinationAdded(Destination destination)`は、新しく追加された`Destination`に応答します。
* `destinationRemoved(Destination destination)`は、新しく削除された`Destination`に応答します。

`S3Z9AbleMessageBusEventListener`のメソッド実装は、宛先イベントをログに記録します。

<a name="examine-the-destinationeventlistener" />

## `DestinationEventListener`を調べる

メッセージバスは、 [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) が`DestinationEventListener`の指定された宛先に対し登録または登録解除を行うと、 [`DestinationEventListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationEventListener.java) に通知します。 以下に、`DestinationEventListener`の実装例を示します。

```{literalinclude} ./listening-for-registration-events/resources/liferay-s3z9.zip/s3z9-charlie-impl/src/main/java/com/acme/s3z9/charlie/internal/messaging/S3Z9CharlieDestinationEventListener.java
:language: java
:lines: 10-38
```

`@Component`アノテーションの `property = "destination.name=acme/s3z9_baker"`および`service = MessageBusEventListener.class`属性は、`S3Z9CharlieDestinationEventListener`を`acme/s3z9_baker`宛先の`DestinationEventListener`として登録するようにランタイムフレームワークにシグナルを送信します。 実装は、`DestinationEventListener`の次の2つのメソッドをオーバーライドします。

* `messageListenerRegistered(String destinationName, MessageListener messageListener)`は、宛先に登録された新しいメッセージリスナーに応答します。
* `messageListenerUnregistered(String destinationName, MessageListener messageListener)`は、宛先から登録解除された新しいメッセージリスナーに応答します。

`S3Z9CharlieDestinationEventListener`のメソッド実装は、メッセージリスナー登録イベントをログに記録します。

メッセージバスリスナーの登録解除と宛先の削除に応答する`MessageBusEventListener`と`DestinationEventListener`のサンプルを確認してください。

<a name="trigger-the-other-events" />

## 他のイベントをトリガーする

モジュールを停止することにより、サンプルのメッセージリスナーの登録を解除し、サンプルの宛先を削除できます。 `s3z9-dog-impl`がメッセージリスナーをデプロイし、`s3z9-able-impl`が宛先をデプロイしたことを思い出してください。 これらのモジュールを停止すると、それらのクラスはそれぞれメッセージリスナーと宛先の登録を解除します。

1. ブラウザで`http://localhost:8080`にあるLiferayインスタンスにアクセスし、認証情報を使用してサインインします。

1. [Gogoシェル](../../../liferay-internals/fundamentals/using-the-gogo-shell.md)を開きます。

1. Gogoシェルコマンドフィールドに次のコマンドを入力して、サンプルモジュールを一覧表示します。

    ```bash
    lb | grep S3Z9
    ```

    各行の先頭には、対応するモジュールのID番号が含まれています。

    ```bash
    1839|Active     |   10|Acme S3Z9 Able Implementation (1.0.0)|1.0.0
    1840|Active     |   10|Acme S3Z9 Baker Implementation (1.0.0)|1.0.0
    1841|Active     |   10|Acme S3Z9 Charlie Implementation (1.0.0)|1.0.0
    1842|Active     |   10|Acme S3Z9 Dog Implementation (1.0.0)|1.0.0
    ```

1. 次のGogoシェルコマンドを入力し、番号をモジュールのIDに置き換えて、メッセージリスナーのモジュールを停止します。

    ```bash
    stop 1842
    ```

1. メッセージリスナーの登録解除に対する宛先イベントリスナーのログに記録された応答を確認します。

    ```bash
    [S3Z9CharlieDestinationEventListener:33] Unregistered message listener from acme/s3z9_baker
    ```

1. 次のGogoシェルコマンドを入力し、番号をモジュールのIDに置き換えて、宛先のモジュールを停止します。

    ```bash
    stop 1840
    ```

　 すべてのメッセージバスイベントリスナーのイベントと宛先イベントリスナーのイベントをトリガーしました。

<a name="whats-next" />

## 次のステップ

これらのメッセージバスイベントをリッスンする方法がわかったので、新しい宛先でメッセージをリッスンしたり、新しい登録関連のアクティビティに応じて[メッセージング環境を調整](./tuning-messaging-performance.md)したりできます。

<a name="additional-information" />

## 追加情報

* [メッセージングパフォーマンスのチューニング](./tuning-messaging-performance.md)
* [メッセージを聞く](./listening-for-messages.md)
* [非同期メッセージングの使用](./using-asynchronous-messaging.md)
