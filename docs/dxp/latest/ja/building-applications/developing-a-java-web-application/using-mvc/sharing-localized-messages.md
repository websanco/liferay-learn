# ローカライズされたメッセージの共有

アプリケーションで作業するときに、複数のモジュールがあり、それぞれに独自の言語キーが設定されている場合があります。 さまざまな言語プロパティファイルをさまざまな場所に保持するのではなく、それらを1つの場所に統合します。 このサンプルプロジェクトでは、言語キーを異なるモジュール間で共有する方法について示しています。

<a name="run-the-tutorial-code" />

## チュートリアルコードを実行する

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. サンプルをダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/developing-a-java-web-application/using-mvc/liferay-u8t2.zip -O
    ```

    ```bash
    unzip liferay-u8t2.zip
    ```

1. モジュールのルートから、ビルドおよびデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、デプロイされたjarをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.u8t2.impl_1.0.0 [1650]
    STARTED com.acme.u8t2.web_1.0.0 [1651]
    ```

1. サンプルのモジュールが機能していることを確認します。 ブラウザで`https://localhost:8080`を開きます。

1. U8T2ポートレットをページに追加します。 サンプルポートレットは、サンプルウィジェットの下にあります。

    ![U8T2ポートレットをページに追加します。](./sharing-localized-messages/images/01.png)

    ウェルカムメッセージのヘッダと色のリストが表示されます。 色の言語キーは、共有言語キーに由来することに注意してください。 言語キー`colors`は、Liferayのグローバル言語キーに由来します。

1. このサンプルプロジェクトには、ポルトガル語と日本語のロケールも含まれています。 たとえば、言語セレクターを使用してブラジルポルトガル語または日本語を選択すると、ウェルカムメッセージと色のリストが表示されます。

    ![このサンプルでは、ポルトガル語と日本語のロケールが表示されています。](./sharing-localized-messages/images/02.png)

次に、どのように機能するかを見ていきましょう。

<a name="create-the-language-properties-file" />

## 言語プロパティファイルを作成する

すべての共有言語キーを保持するための個別のプロジェクトモジュールを作成します。 サンプルプロジェクトでは、共有キーは`Acme U8T2 Impl`モジュールにあります。

`Language.properties`ファイルを作成し、モジュールの`src/main/resources/content`フォルダに追加します。 ファイルで、他のモジュールと共有するキーを定義します。

サンプルプロジェクトには、`Acme U8T2 Web`モジュールで使用される6色のリストがあります。

```properties
blue=Blue
green=Green
orange=Orange
purple=Purple
red=Red
yellow=Yellow 
```

他のロケールの言語プロパティファイルもフォルダに含めることができます。 たとえば、日本語の言語キーを含めるには、`Language_ja.properties`ファイルをフォルダに追加します。

<a name="add-the-bnd-instruction" />

## bnd命令を追加する

言語キーを共有するモジュールごとに、bndヘッダヘッダでリソースを指定する必要があります。

```properties
Bundle-Name: Acme U8T2 Web
Bundle-SymbolicName: com.acme.u8t2.web
Bundle-Version: 1.0.0
-liferay-aggregate-resource-bundles: com.acme.u8t2.impl
```

サンプルプロジェクトには、`Acme U8T2 Impl`のカラー言語キーを使用するWebポートレットがあります。 `Acme U8T2 Web`モジュールの`bnd.bnd`ファイルでは、リソースバンドルが指定されています。

```properties
-liferay-aggregate-resource-bundles: com.acme.u8t2.impl
```

個々のモジュールにいくつかの言語キーを配置できることに注意してください。 たとえば、サンプルプロジェクトのウェルカムメッセージは、`Acme U8T2 Impl`の共有キーではなく、`Acme U8T2 Web`モジュールの言語キーから送信されます。  個々のモジュールの言語キーは、`-liferay-aggregate-resource-bundles`で指定された共有キーよりも優先されます。

<a name="related-topics" />

## 関連トピック

* [Aggregating Resource Bundles](../../core-frameworks/localization/reference/aggregating-resource-bundles.md)
