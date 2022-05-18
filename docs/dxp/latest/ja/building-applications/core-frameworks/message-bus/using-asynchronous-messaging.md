# 非同期メッセージングの使用

メッセージバスの非同期オプションは、「ファイア・アンド・フォーゲット」動作を提供します。メッセージを送信し、応答を待たずに処理を続行します。

非同期メッセージは、 **シリアル** または **パラレル** 宛先に送信されます。

****シリアル** 宛先の場合、メッセージバスはメッセージをキューに入れ、メッセージごとに1つのワーカースレッドを委任します。 スレッドはメッセージリスナーを順番に処理します。

****パラレル** 宛先の場合、メッセージバスはメッセージをキューに入れ、1つのメッセージリスナーにつきメッセージごとに1つのワーカースレッドを委任します。 スレッドはメッセージリスナーを同時に処理します。

別のクラス（メッセージリスナー）がリッスンしているシリアル宛先にメッセージを送信することから始めます。

<a name="send-a-message" />

## メッセージを送る

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. サンプルをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/message-bus/liferay-n8k5.zip -O
   ```

   ```bash
   unzip liferay-n8k5.zip
   ```

1. 宛先モジュール`n8k5-able-impl`をビルドしてデプロイします。

    ```bash
    cd liferay-n8k5/n8k5-able-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```


    The Docker container console shows that the module started.

    ```bash
    STARTED com.acme.n8k5.able.impl_1.0.0
    ```

1. リスナーモジュール`n8k5-charlie-impl`をビルドしてデプロイします。

    ```bash
    cd ../n8k5-charlie-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```


    The Docker container console shows that the module started.

    ```bash
    STARTED com.acme.n8k5.charlie.impl_1.0.0
    ```

1. 送信者モジュール`n8k5-baker-impl`をビルドしてデプロイします。

    ```bash
    cd ../n8k5-baker-impl
    ```

    ```bash
    ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```


    In the Docker container console, confirm `N8K5Baker` sent a message, `N8K5CharlieMessageListener` received a message, and the `n8k5-baker-impl` module started.

   ```bash
   INFO  [pipe-start 2025][N8K5Baker:24] Sent message to acme/n8k5_able
   INFO  [acme/n8k5_able-4][N8K5CharlieMessageListener:21] Received message payload N8K5Baker#_activate
   STARTED com.acme.n8k5.baker.impl_1.0.0 [2025]
   ```

`N8K5Baker`は、宛先`acme/n8k5_able`にメッセージを送信したことを報告しました。 `N8K5CharlieMessageListener`は、宛先`acme/n8k5_able`でペイロード`N8K5Baker#_activate`を含むメッセージを受信しました。 これで、サンプルコードを調べることができます。

<a name="project-overview" />

## プロジェクトの概要

この例の3つのモジュールには、それぞれ1つのクラスがあります。 各クラスは、メッセージングコンポーネントの1つ（宛先、送信者、リスナー）を表します。

クラスの例：

| クラス                                              | 説明                                               |
|:------------------------------------------------ |:------------------------------------------------ |
| n8k5-able-impl の `N8K5AbleMessagingConfigurator` | `acme/n8k5_able`という名前のメッセージ宛先を作成し、メッセージバスに登録します。 |
| n8k5-baker-impl の `N8K5Baker`                    | `acme/n8k5_able`宛先にメッセージを送信します。                  |
| n8k5-charlie-impl の `N8K5CharlieMessageListener` | `acme/n8k5_able`宛先に送信されたメッセージをリッスンします。           |

これらがどのように相互作用するかを以下に示します。

1. `N8K5Baker`が有効になり（たとえば、`n8k5-baker-impl`モジュールが起動したとき）、`acme/n8k5_able`宛先にメッセージを送信します。
1. メッセージバスがメッセージを`N8K5CharlieMessageListener`に送信します。
1. `N8K5CharlieMessageListener`がメッセージを受信します。

宛先構成と送信者クラスを調べます。 リスナークラス`N8K5CharlieMessageListener`は、 [メッセージを聞く](./listening-for-messages.md) に示す方法と同じ方法で登録します。

<a name="examine-the-destination-configuration" />

## 宛先構成を調べる

`n8k5-able-impl`モジュールの`N8K5AbleMessagingConfigurator`クラスは、宛先を作成して構成します。 コードは次のとおりです。

```{literalinclude} ./using-asynchronous-messaging/resources/liferay-n8k5.zip/n8k5-able-impl/src/main/java/com/acme/n8k5/able/internal/messaging/N8K5AbleMessagingConfigurator.java
:language: java
:lines: 15-42
```

どのクラスでも宛先を作成および構成できますが、 [`Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) には`DestinationFactory`のように依存関係を挿入できます。 `_destinationFactory`フィールドの [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) アノテーションは、LiferayのOSGiフレームワークに`DestinationFactory`インスタンスを挿入するようにシグナルを送信します。

`_activate`メソッドでは、`N8K5AbleMessagingConfigurator`は [`DestinationFactory`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationFactory.java) と [`DestinationConfiguration`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationConfiguration.java) を使用して、`acme/n8k5_able`という名前の **シリアル** 宛先を作成します。 次に、OSGiフレームワーク`BundleContext`を使用して、`Destination`に対するサービスを登録します。 `N8K5AbleMessagingConfigurator`が無効化されると、`_deactivate`メソッドはサービスの登録を解除します。

<a name="examine-the-sender" />

## 送信者を調べる

以下の`N8K5Baker`クラスは、ペイロード`"N8K5Baker#_activate"`を含むメッセージを`acme/n8k5_able`という名前の宛先に送信します。

```{literalinclude} ./using-asynchronous-messaging/resources/liferay-n8k5.zip/n8k5-baker-impl/src/main/java/com/acme/n8k5/baker/internal/N8K5Baker.java
:language: java
:lines: 12-23
```

コンポーネントとして、`N8K5Baker`は`@Reference`アノテーションを使用して`MessageBus`インスタンスを挿入します。

コンポーネントのアクティブ化時に、`N8K5Baker`は、アクティブ化メソッド`_activate()`を介してメッセージを作成して送信します。 [`Message`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Message.java) インスタンスを構築し、それにペイロードを追加します。 ペイロードは、`Message`に入力できるもののうちの1つです。

主なメッセージ入力方法は次のとおりです。

| メソッド                                  | 説明                             |
|:------------------------------------- |:------------------------------ |
| `setPayload(Object)`                  | `Message`のメインコンテンツを追加します。      |
| `setResponseDestinationName(String)`  | 応答を受信するための`Destination`を参照します。 |
| `setValues(Map<String,Object>)` | `Map`から追加データを提供します。            |

`N8K5Baker`は、 [`MessageBus`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageBus.java) の`sendMessage(String, Message)`メソッドを呼び出して、`acme/n8k5_able`という名前の [`Destination`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/Destination.java) にメッセージを送信します。 `MessageBus`は新しいスレッドを開始し、`acme/n8k5_able` `Destination`に登録されている [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) インスタンスに`Message`を送信します。 `N8K5Baker`のスレッドが継続します。

```{note}
`Message`への応答を受信したい場合は、` Message`に応答先を設定し、 `N8K5Baker`などのクラスを` MessageListener`としてその宛先に登録します。 詳細については、 [メッセージを聞く](./listening-for-messages.md) を参照してください。
```

<a name="add-response-handling" />

## 応答処理の追加

メッセージ受信者からの応答が必要な場合は、返信の応答先を設定します。

1. メッセージ応答用に別の宛先を登録します。
1. クラス（例えば、元の送信者）を`MessageListener`として応答先に登録します。
1. メッセージで応答先を渡します。
1. `MessageListener`に応答ロジックを追加します。

<a name="step-1-register-a-destination-for-responses" />

### ステップ1：応答の宛先を登録する

`N8K5AbleDestinationConfigurator`が宛先を管理するのと同じ方法で、応答先を管理するように`N8K5Baker`を変更できます。 `_activate()`メソッドのシグネチャを`_activate(BundleContext bundleContext)`に置き換え、`acme/n8k5_baker`応答先のサービスを作成、構成、および登録するコードを追加します。 サービスの登録を解除する`_deactivate()`メソッドを追加します。 `_activate(BundleContext bundleContext)`メソッドと`_deactivate()`メソッドは次のようになります。

```java
@Activate
private void _activate(BundleContext bundleContext) {
   Destination destination = _destinationFactory.createDestination(
      DestinationConfiguration.createSerialDestinationConfiguration(
         "acme/n8k5_baker"));

   _serviceRegistration = bundleContext.registerService(
      Destination.class, destination,
      MapUtil.singletonDictionary(
         "destination.name", destination.getName()));

   Message message = new Message();

   message.setPayload("N8K5Baker#_activate");

   _messageBus.sendMessage("acme/n8k5_able", message);
}

@Deactivate
private void _deactivate() {
   if (_serviceRegistration != null) {
      _serviceRegistration.unregister();
   }
}

@Reference
private DestinationFactory _destinationFactory;

private ServiceRegistration<Destination> _serviceRegistration;
```

<a name="step-2-register-n8k5baker-as-a-listener-on-the-response-destination" />

### ステップ2：`N8K5Baker`を応答先のリスナーとして登録する

送信者`N8K5Baker`の変更点は次のとおりです。

1. `@Component`アノテーションを更新し、`N8K5Baker`を`MessageListener.class`タイプのサービスとして宣言し、プロパティ`"destination.name=acme/n8k5_baker"`を介して`N8K5Baker`を応答先にマッピングします。
1. [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) インターフェイスを実装します。
1. `receive(Message)`メソッドをメッセージ処理ロジックでオーバーライドします。

送信者の変更は次のようになります。

```java
@Component(
    property = "destination.name=acme/n8k5_baker",
    service = MessageListener.class
)
public class N8K5Baker implements MessageListener {

    @Override
    public void receive(Message message) {
        Object payload = message.getPayload();

        _log.info("Received message payload " + payload.toString());
    }

   // Existing methods and fields

   private static final Log _log = LogFactoryUtil.getLog(N8K5Baker.class);
}
```

<a name="step-3-pass-the-response-destination-in-the-message" />

### ステップ3：メッセージの応答先を渡す

`N8K5Baker`が送信するメッセージの応答先として`acme/n8k5_baker`を設定します。  次のようになります。

```java
@Activate
private void _activate(BundleContext bundleContext) {
   // Destination setup

   Message message = new Message();

   message.setPayload("N8K5Baker#_activate");
   message.setResponseDestinationName("acme/n8k5_baker");

   _messageBus.sendMessage("acme/n8k5_able", message);
}
```

<a name="step-4-add-response-logic-to-the-messagelisteners" />

### ステップ4：`MessageListener`に応答ロジックを追加する

`MessageListener`の`receive(Message)`メソッドで、応答を設定し、メッセージから応答先を取得し、`MessageBus`インスタンスを使用して応答メッセージを応答先に送信します。 次のようになります。

```java
public void receive(Message message) {
   // Message processing

   message.setResponse("N8K5CharlieMessageListener");

   Message responseMessage = new Message();

   responseMessage.setDestinationName(
      message.getResponseDestinationName());
   responseMessage.setPayload("N8K5CharlieMessageListener");
   responseMessage.setResponseId(message.getResponseId());

   _messageBus.sendMessage(
      message.getResponseDestinationName(), responseMessage);
}

// Existing methods and fields

@Reference
private MessageBus _messageBus;
```

<a name="test-your-changes" />

### 変更をテストする

サンプルプロジェクトを再デプロイして、変更をテストします。

```bash
cd ../../liferay-n8k5.zip
```

```bash
./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
```

出力は次のようになります。

```bash
STARTED com.acme.n8k5.charlie.impl_1.0.0 [2020]
STARTED com.acme.n8k5.baker.impl_1.0.0 [2025]
INFO  [acme/n8k5_able-2][N8K5CharlieMessageListener:23] Received message payload N8K5Baker#_activate
INFO  [acme/n8k5_baker-2][N8K5Baker:30] Received message payload N8K5CharlieMessageListener
```

`N8K5CharlieMessageListener`は、`N8K5Baker`のメッセージを受信してから、応答メッセージを応答先に送信します。 `N8K5Baker`は応答メッセージを受信し、メッセージペイロードを出力します。

```{note}
クラスでメッセージを再度交換する場合は、[Gogo シェル](../../../liferay-internals/fundamentals/using-the-gogo-shell.md)でモジュール（OSGiバンドル）を再起動できます。  バンドルを一覧表示して（`lb`）バンドルIDを取得し、バンドルを停止して（ [stop](id) ）、バンドルを再起動します（ [start](id) ）。
```

```{note}
OSGiコンポーネントではないクラスでは、 [MessageBusUtil](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageBusUtil.java) と、`Destination`、`DestinationConfiguration`、`Message`、および`MessageListener`インスタンスを使用してメッセージを送信できます。

示されているように`Destination`サービスを登録できますが、`BundleContext`を別の方法で取得する必要があります（たとえば、`Bundle bundle = FrameworkUtil.getBundle(YourClass.class); BundleContext bundleContext = bundle.getBundleContext()`を呼び出しを行うことによって）。
```

　 2つのクラス間で非同期的にメッセージを交換しました。

<a name="whats-next" />

## 次のステップ

非同期メッセージングに慣れてきたので、最適なパフォーマンスになるように調整できます。 [メッセージングパフォーマンスのチューニング](./tuning-messaging-performance.md) でその方法を学びましょう。

**デフォルト** モードと **ダイレクト** モードを使用した同期メッセージングを検討する場合は、詳細について [ダイレクト同期メッセージングの使用](./using-direct-synchronous-messaging.md) および [デフォルトの同期メッセージングの使用](./using-default-synchronous-messaging.md) を参照してください。

<a name="additional-information" />

## 追加情報

* [Message Busメッセージバス](../message-bus.md)
* [メッセージを聞く](./listening-for-messages.md)
* [登録イベントを聞く](./listening-for-registration-events.md)
