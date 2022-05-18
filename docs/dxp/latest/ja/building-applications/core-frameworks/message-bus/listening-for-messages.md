# メッセージを聞く

登録済みのメッセージバスの宛先に送信されたメッセージは、DXP/Portalに組み込まれているもの、サードパーティによって定義されているもの、自分で作成したものに関係なく、リッスンすることができます。 同じ宛先に送信されるメッセージには、通常、類似したイベントタイプやトピックなど、共通点があります。 ここでは、`DestinationNames.DOCUMENT_LIBRARY_PDF_PROCESSOR`という宛先で受信したメッセージをリッスンするクラスをデプロイします。 ドキュメントとメディアは、アップロードされたすべてのPDFファイルを処理した後、この宛先にメッセージを送信します。

<a name="run-the-example-message-listener" />

## サンプルのメッセージリスナーを実行する

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. [サンプルのプロジェクト](dxp/latest/en/building-applications/core-frameworks/message-bus/liferay-w3a4.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/message-bus/liferay-w3a4.zip -O
    ```

    ```bash
    unzip liferay-w3a4.zip
    ```

1. プロジェクトモジュールをビルドしてデプロイします。

    ```bash
    cd liferay-w3a4
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、コンパイルされたモジュールJARをDockerコンテナの `/opt/liferay/osgi/modules` にコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.w3a4.impl_1.0.0 [2177]
    ```

1. UIで、ドキュメントとメディアに[PDFファイルをアップロード](../../../content-authoring-and-management/documents-and-media/uploading-and-managing/uploading-files.md)します。

ドキュメントとメディアは、PDFファイルプレビューの生成を完了すると、サンプルプロジェクトの`MessageListener`がリッスンしている宛先にメッセージを送信します。 メッセージバスは、宛先に登録されているすべての`MessageListener`にメッセージを中継します。 中継されたメッセージを受信すると、プロジェクトの`MessageListener`はメッセージのペイロードと宛先をログに記録します。

```bash
[liferay/document_library_pdf_processor-2][W3A4MessageListener:22] Received message payload [Ljava.lang.Object;@6df886c1 at destination liferay/document_library_pdf_processor
```

仕組みは次のとおりです。

<a name="determine-the-destination" />

## 宛先を決定する

メッセージの宛先は、その名前で参照されます。 APIは宛先名を指定します。 たとえば、 [`DestinationNames`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/DestinationNames.java) クラスは、Liferayの組み込みの宛先を一覧表示します。 サンプルの`MessageListener`は、次の`String`定数で指定されたLiferayの宛先に送信されたメッセージをリッスンします。

```java
DestinationNames.DOCUMENT_LIBRARY_PDF_PROCESSOR
```

[ソースコード](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]) でLiferayの`*DestinationNames`クラスを検索するか、他のAPIでリスナーを追加できる宛先を検索します。 メッセージリスナーで宛先名を指定します。

<a name="implement-the-messagelistener-interface" />

## `MessageListener`インターフェイスを実装する

メッセージを受信するクラスで、 [`MessageListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) インターフェイスを実装します。

```{literalinclude} ./listening-for-messages/resources/liferay-w3a4.zip/w3a4-impl/src/main/java/com/acme/w3a4/internal/messaging/W3A4MessageListener.java
   :language: java
   :lines: 15
```

`receive`メソッドをメッセージを処理するためのロジックでオーバーライドします。 以下に、`receive`メソッドの実装例を示します。

```{literalinclude} ./listening-for-messages/resources/liferay-w3a4.zip/w3a4-impl/src/main/java/com/acme/w3a4/internal/messaging/W3A4MessageListener.java
   :dedent: 1
   :language: java
   :lines: 17-27
```

上記の実装は、メッセージのペイロードと宛先名をログに記録します。 他のメソッドの詳細については、 [`Message`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/messaging/MessageListener.java) クラスを参照してください。

<a name="register-your-messagelistener-with-the-destination" />

## MessageListenerを宛先に登録する

`Component`アノテーションを使用してクラスを登録し、目的の宛先でメッセージをリッスンします。 例:

```{literalinclude} ./listening-for-messages/resources/liferay-w3a4.zip/w3a4-impl/src/main/java/com/acme/w3a4/internal/messaging/W3A4MessageListener.java
   :language: java
   :lines: 11-15
```

上記のアノテーションは、`DestinationNames.DOCUMENT_LIBRARY_PDF_PROCESSOR`という名前の宛先でメッセージを受信するための`MessageListener`サービスコンポーネントとしてクラスを登録します。

コンポーネントの`destination.name`プロパティ値を宛先の名前に設定します。

プロジェクトをデプロイすると、OSGiランタイムは`MessageListener`を宛先に登録します。 これで、`MessageListener`は宛先に送信されたメッセージを受信します。

<a name="additional-information" />

## 追加情報

* [Message Busメッセージバス](../message-bus.md)
