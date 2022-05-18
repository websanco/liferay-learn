# ビデオショートカットプロバイダーの作成

> Liferay DXP 7.4以降で利用可能

デフォルトでは、Liferayの外部ビデオショートカットは、 [YouTube](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-video/src/main/java/com/liferay/document/library/video/internal/video/external/shortcut/provider/YouTubeDLVideoExternalShortcutProvider.java) 、 [Vimeo](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-video/src/main/java/com/liferay/document/library/video/internal/video/external/shortcut/provider/VimeoDLVideoExternalShortcutProvider.java) 、 [Facebook](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-video/src/main/java/com/liferay/document/library/video/internal/video/external/shortcut/provider/FacebookDLVideoExternalShortcutProvider.java) 、および [Twitch](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-video/src/main/java/com/liferay/document/library/video/internal/video/external/shortcut/provider/TwitchDLVideoExternalShortcutProvider.java) をサポートしています。 ただし、この機能を拡張して、他のビデオソースをサポートすることができます。

次の手順に従って、独自のビデオショートカットプロバイダーを作成します。

1. **OSGIコンポーネントアノテーション** ：`@Component`アノテーションを使用して、OSGiフレームワーク内でプロバイダーを`DLVideoExternalShortcutProvider.class`サービスとして宣言します。

1. [**`DLVideoExternalShortcutProvider`**](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-api/src/main/java/com/liferay/document/library/video/external/shortcut/provider/DLVideoExternalShortcutProvider.java) ：`DLVideoExternalShortcutProvider`インターフェースを実装します。

1. **インターフェイスメソッドのオーバーライド** ：インターフェイスの`getDLVideoExternalShortcut()`メソッドをオーバーライドします。 このメソッドは、 [`DLVideoExternalShortcut`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-api/src/main/java/com/liferay/document/library/video/external/shortcut/DLVideoExternalShortcut.java) インターフェイスのインスタンスを作成し、URL文字列を受け取ります。 実装において次のことが実行されるか確認してください。

   * 受信したURLが定義済みのURLパターンと一致するかどうかを確認します。
   * URLがパターンに一致しない場合、プログラムは`null`を返す必要があります。 Liferayは、一致するものを探して他の利用可能なプロバイダーをチェックします。
   * URLがパターンと一致する場合は、URLを解析し、外部ソースから追加情報をフェッチして、収集した情報を含む`DLVideoExternalShortcut`インスタンスを返します。

1. **`DLVideoExternalShortcut`メソッドのオーバーライド** ：`getDLVideoExternalShortcut()`メソッドによって返される`DLVideoExternalShortcut`インスタンスに必要なメソッドをオーバーライドします。

   * `getURL()`：元のビデオURLを取得します。
   * `renderHTML()`：ユーザーインターフェイスにビデオを埋め込みます。 これは通常、`iframe`をレンダリングしますが、ユーザー向けにビデオをレンダリングするHTMLビデオタグにすることもできます。

1. 次のオプションのメソッドをオーバーライドします。

   * `getDescription()`：このメソッドを使用して、元のビデオの説明を取得します。デフォルト値は`null`です。
   * `getThumbnailURL()`：このメソッドを使用して、ビデオのサムネイルを取得します。デフォルト値は`null`です。
   * `getTitle()`：このメソッドを使用して、元のビデオのタイトルを取得します。デフォルト値は`null`です。

以下は、 [外部のビデオショートカットプロバイダーのサンプル](liferay-g9b6.zip) で、独自に実装するための最低限の要件を示しています。 より複雑な例については、 [既存のプロバイダー](https://github.com/liferay/liferay-portal/tree/master/modules/apps/document-library/document-library-video/src/main/java/com/liferay/document/library/video/internal/video/external/shortcut/provider) を参照してください。

<a name="deploying-the-sample-video-provider" />

## サンプルビデオプロバイダーのデプロイ

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. サンプルモジュールをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/docs/dxp/latest/en/content-authoring-and-management/documents-and-media/developer-guide/liferay-g9b6.zip -O
   ```

   ```bash
   unzip liferay-g9b6.zip -d liferay-g9b6
   ```

1. `gradlew deploy`コマンドを実行してJARファイルをビルドし、それを新しいDockerコンテナにデプロイします。

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   JARは、`build/libs`フォルダ（つまり、`g9b6-impl/build/libs/com.acme.G9B6.impl-1.0.0`）に生成されます。

1. プロバイダーが正常にデプロイされ、コンテナコンソールを介して開始されたことを確認します。

   ```log
   Processing com.acme.G9B6.impl-1.0.0.jar
   STARTED com.acme.G9B6.impl-1.0.0 [1356]
   ```

1. 短いDailymotion URL（https://dai.ly/x7szh28など）を使用して、新しい外部ビデオショートカットを作成 <!--タスク: 記事がマージされたらリンクを追加 --> し、モジュールが機能していることを確認します。

   成功した場合、LiferayはDailymotionをサポートされているプラットフォームとして認識します。

   ![LiferayはDailymotionをサポートされているプラットフォームとして認識します。](./creating-custom-video-shortcut-providers/images/01.png)

<a name="code-for-the-sample-video-provider" />

## サンプルビデオプロバイダーのコード

```{literalinclude} ./creating-custom-video-shortcut-providers/resources/liferay-g9b6.zip/g9b6-impl/src/main/java/com/acme/g9b6/internal/document/library/video/external/shortcut/provider/G9B6DLVideoExternalShortcutProvider.java
   :dedent: 1
   :language: java
   :lines: 14-51
```

### OSGiコンポーネントアノテーション

プロバイダーは、OSGiフレームワーク内でコンポーネントとして宣言され、`DLVideoExternalShortcutProvider.class`サービスとして識別されます。

### `DLVideoExternalShortcutProvider`実装

プロバイダーは、`DLVideoExternalShortcutProvider`インターフェイスを実装します。 このインターフェイスには、有効なURLを受信した場合に`DLVideoExternalShortcut`を返す単一のメソッド`getDLVideoExternalShortcut`が含まれています。

### `getDLVideoExternalShortcut`のオーバーライド

プロバイダーは、インターフェイスの`getDLVideoExternalShortcut`メソッドをオーバーライドします。このメソッドには、プロバイダーの基本的なロジックがすべて含まれています。  URLが定義された正規表現パターンと一致するかどうかをチェックします。 一致するものが見つからない場合は `null` を返し、Liferayは一致するものを探して他の利用可能なプロバイダーを呼び出します。 一致する場合は、ビデオをLiferayのページまたはアセットに埋め込むための新しい`DLVideoExternalShortcut`オブジェクトを返します。

### `DLVideoExternalShortcut`のメソッドのオーバーライド

プロバイダーが`DLVideoExternalShortcut`オブジェクトを返すと、オブジェクトの`getURL()`メソッドと`renderHTML()`メソッドがオーバーライドされます。 `getURL()`は、ユーザーが入力したURLを返します。 `renderHTML()`は`HttpServletRequest`パラメーターを受け取り、Liferayのページまたはアセットに埋め込まれる`iframe`文字列を返します。  次の例を考えてみましょう。

`getDescription()`、`getThumbnailURL()`、および`getTitle()`がオーバーライドされていないので、`null`が返されます。

<a name="additional-information" />

## 追加情報

* [外部ビデオショートカットの作成](../videos/creating-external-video-shortcuts.md)
