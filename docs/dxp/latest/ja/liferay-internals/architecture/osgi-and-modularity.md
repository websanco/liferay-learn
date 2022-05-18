# OSGiとモジュール性

モジュール性があることで、特にチームとしてソフトウェアを作成することが楽しくなります。 Liferayでのモジュール開発の利点は次のとおりです。

* Liferayのランタイムフレームワークは、軽量、高速、安全です。
* このフレームワークは [OSGi](https://www.osgi.org/resources/what-is-osgi/) 規格を使用します。 他のプロジェクトでOSGiを使用した経験がある場合は、既存の知識を生かすことができます。
* モジュールは、サービスレジストリにサービスを公開し、サービスレジストリからサービスを利用します。 サービス契約はサービス・プロバイダーとコンシューマから疎結合されており、レジストリが契約を自動的に管理します。
* モジュールの依存関係は、コンテナによって自動的に、かつ動的に管理されます（再起動は必要ありません）。
* コンテナは [モジュールのライフサイクル](./module-lifecycle.md) を動的に管理します。 Liferayの実行中にモジュールのインストール、開始、更新、停止、およびアンインストールができるため、デプロイメントを簡単に行うことができます。
* パッケージが明示的に[エクスポート](../fundamentals/exporting-packages.md)されているモジュールのクラスのみが公開されます。 OSGiは、デフォルトで他のすべてのクラスを非表示にします。
* モジュールとパッケージは[セマンティックにバージョン管理](../fundamentals/semantic-versioning.md)され、他のパッケージの特定のバージョンへの依存関係を宣言します。 これにより、同じパッケージの異なるバージョンに依存する2つのアプリケーションが、それぞれ独自のバージョンのパッケージに依存することができます。
* チームメンバーは、モジュールを並行して開発、テスト、および改善できます。
* 既存の開発者ツールと環境を使用してモジュールを開発できます。

OSGiによるモジュール型ソフトウェア開発には多くのメリットがありますが、ここではその一部をご紹介します。 一度モジュール開発を始めると、他の方法での開発には戻れなくなるかもしれません。

Liferayでは、一般的に3種類のモジュールを使用します。

1. **API** モジュールはインターフェイスを定義します。

1. **実装** モジュールは、インターフェイスを実装する具象クラスを提供します。

1. **クライアント** モジュールはAPIを消費します。

[Gogo シェル](../fundamentals/using-the-gogo-shell.md)でユーザーが名前を入力したときにあいさつ文を表示する簡単なコマンドを開発することで、それぞれを作成する方法を学習します。

![ユーザーに挨拶をするGogoシェルコマンド。](./osgi-and-modularity/images/01.png)

モジュールプロジェクトがどのように見えるかを確認し、Liferayのモジュール開発機能が実際に動作しているのを見てみましょう。

<a name="gogo-シェルコマンドの例をデプロイする" />

## Gogo シェルコマンドの例をデプロイする

サンプルの使用を開始します。

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. `liferay-r9u2.zip`をダウンロードして解凍します。

    ```bash
    curl hhttps://learn.liferay.com/dxp/latest/ja/liferay-internals/architecture/liferay-r9u2.zip -O
    ```

    ```bash
    unzip liferay-r9u2.zip
    ```

1. サンプルモジュールをデプロイします。

    ```bash
    cd liferay-r9u2.zip
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```
    STARTED com.acme.r9u2.api_1.0.0
    STARTED com.acme.r9u2.impl_1.0.0
    STARTED com.acme.r9u2.osgi.commands_1.0.0
    ```

1. [Gogo シェル](../fundamentals/using-the-gogo-shell.md)を開きます。

1. Gogo シェルコマンドフィールドに、`r9u2:greet`コマンドを入力して、挨拶を生成します。

    ```groovy
    r9u2:greet "Captain Kirk"
    ```

1. 出力を確認します。

    ```
    Hello Captain Kirk!
    ```

この例のクライアントモジュールは、APIおよび実装モジュールを利用して、`r9u2:greet` Gogo シェルコマンドから返されるコンテンツを生成します。 次に、各モジュールを調べます。

<a name="api" />

## API

APIモジュールが最初です。 これは、プロバイダーが実装し、コンシューマが使用する契約を定義します。 その構造は次のとおりです。

```
[project root]
 └── r9u2-api
 │   ├── bnd.bnd
 │   ├── build.gradle
 │   └── src
 │       └── main
 │           └── java
 │               └── com/acme/r9u2
 │                   └── Greeter.java
 │
 └── [Gradle files]
```

`r9u2-api`モジュールフォルダには、`bnd.bnd`メタデータファイル、 `build.gradle` スクリプト、およびJavaコードが含まれています。

非常にシンプルです。 Javaソースファイル以外には、Gradleビルドスクリプト（任意のビルドシステムを使用できます）と`bnd.bnd`という構成ファイルの2つのファイルしかありません。 `bnd.bnd`ファイルは、モジュールの説明と設定を行います。
```{literalinclude} ./osgi-and-modularity/resources/liferay-r9u2.zip/r9u2-api/bnd.bnd
```

The `build.gradle` file specifies the module's dependencies.

```{literalinclude} ./osgi-and-modularity/resources/liferay-r9u2.zip/r9u2-api/build.gradle
   :language: groovy
```

これは、LiferayリリースのAPI JARという1つのアーティファクトに依存しています。 これは、Liferay製品のリリースに関連するLiferay、Bnd、OSGiのアーティファクトを詰め込んだ大きなJARです。

モジュールの名前は **Acme R9U2 API** です。 そのシンボリック名（一意性を確保するための名前）は `com.acme.r9u2.api` です。 次にそのセマンティックバージョンが宣言され、そのパッケージが **エクスポート** されます。つまり、他のモジュールで使用できるようになります。 このモジュールのパッケージは、他のモジュールが実装できるAPIに過ぎません。

最後に、Javaクラスがあります。この場合はインターフェイスです。

```{literalinclude} ./osgi-and-modularity/resources/liferay-r9u2.zip/r9u2-api/src/main/java/com/acme/r9u2/Greeter.java
   :language: java
   :lines: 5-10
```

インターフェイスの`@ProviderType`アノテーションは、インターフェイスを実装しているものがプロバイダーであることをサービスレジストリに通知します。 インターフェイスの1つのメソッドは`String`を要求し、何も返しません。

これで完了です。 ご覧のとおり、モジュールの作成は他のJavaプロジェクトの作成と大差ありません。

<a name="実装" />

## 実装

インターフェイスはAPIを定義しているだけです。何かをするためには、それを実装する必要があります。 これが、実装（またはプロバイダー）モジュールの目的です。 Greeter APIの実装モジュールは次のようになります。

```
[project root]
 └── r9u2-impl
 │   ├── bnd.bnd
 │   ├── build.gradle
 │   └── src
 │       └── main
 │           └── java
 │               └── com/acme/r9u2/internal
 │                   └── R9U2Greeter.java
 │
 └── [Gradle files]
```

APIモジュールと同じ構造、つまりビルドスクリプト、`bnd.bnd`構成ファイル、および実装クラスがあります。 唯一の違いはファイルのコンテンツです。 `bnd.bnd`ファイルが少し異なります。
```{literalinclude} ./osgi-and-modularity/resources/liferay-r9u2.zip/r9u2-impl/bnd.bnd
```

The bundle name, symbolic name, and version are all set similarly to the API. 

Finally, there's no `Export-Package` declaration. A client (which is the project's third module) just wants to use the API: it doesn't care how its implementation works as long as the API returns what it's supposed to return. The client, then, only needs to declare a dependency on the API; the service registry injects the appropriate implementation at run time. 

Pretty cool, eh? 

All that's left, then, is the class that provides the implementation:

```{literalinclude} ./osgi-and-modularity/resources/liferay-r9u2.zip/r9u2-impl/src/main/java/com/acme/r9u2/internal/R9U2Greeter.java
   :language: java
   :lines: 7-15
```

`greet`メソッドの例では、指定された名前を使用して熱烈な挨拶文を出力します。

実装モジュールの`build.gradle`ファイルは以下のとおりです。

```{literalinclude} ./osgi-and-modularity/resources/liferay-r9u2.zip/r9u2-impl/build.gradle
   :language: groovy
```

このファイルには、モジュールの`Greeter`クラスが必要なため、`r9u2-api`モジュールプロジェクトへのコンパイル時の依存関係が含まれています。

実装モジュールについてはこれですべてです。

<a name="クライアント" />

## クライアント

コンシューマまたはクライアントは、APIモジュールが定義し実装モジュールが実装するAPIを使用します。 Liferayにはさまざまな種類のコンシューマモジュールがあります。 [ポートレット](../../developing-applications/developing-a-java-web-application/reference/portlets.md)は最も一般的なコンシューマモジュールタイプですが、それ自体がトピックであるため、この例ではApache Felix Gogoシェルのコマンドを作成することでシンプルなままにしています。  もちろん、コンシューマはさまざまなAPIを利用して機能を提供することができます。

コンシューマモジュールは、他のモジュールタイプと同じ構造を持っています。

```
[project root]
 └── r9u2-osgi-commands
 │   ├── bnd.bnd
 │   ├── build.gradle
 │   └── src
 │       └── main
 │           └── java
 │               └── com/acme/r9u2/internal/osgi/commands
 │                   └── R9U2OSGiCommands.java
 │
 └── [Gradle files]
```

ここでも、ビルドスクリプト、`bnd.bnd`ファイル、およびJavaクラスがあります。 このモジュールの`bnd.bnd`ファイルは、プロバイダーのものとほとんど同じです。
```{literalinclude} ./osgi-and-modularity/resources/liferay-r9u2.zip/r9u2-osgi-commands/bnd.bnd
```

There's nothing new here: you declare the same things you declared for the provider. 

The client module depends on the API module and the `release.portal.api` artifact. Here's the `r9u2-osgi-commands` module's `build.gradle` file:

```{literalinclude} ./osgi-and-modularity/resources/liferay-r9u2.zip/r9u2-osgi-commands/build.gradle
   :language: groovy
```

Javaクラスでは、もう少し続きがあります。

```{literalinclude} ./osgi-and-modularity/resources/liferay-r9u2.zip/r9u2-osgi-commands/src/main/java/com/acme/r9u2/internal/osgi/commands/R9U2OSGiCommands.java
   :language: java
   :lines: 8-21
```

上記のメソッドは、`Greeter`の`greet`メソッドを呼び出します。 `com.acme.r9u2.Greeter`は、実装モジュールが登録するOSGiサービスタイプです。 レジストリから`Greeter`サービスを取得するには、`Greeter`フィールド`_greeter`に [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) アノテーションを追加する必要があります。

`R9U2OSGiCommands`クラスは、独自のタイプのOSGiサービスを提供します。 2つのプロパティは、`r9u2`というスコープで`greet` というコマンド関数を使用してGogoシェルコマンドを定義します。 デプロイされた`R9U2OSGiCommands`コンポーネントは、`String`を入力として受け取るGogo シェルコマンド`r9u2:greet`を提供します。

この最も基本的な例を見れば、モジュールベースの開発が簡単で分かりやすいことがわかるでしょう。 API-Provider-Consumer契約によって疎結合が促進され、ソフトウェアの管理、拡張、およびサポートが容易になります。

<a name="典型的なliferayアプリケーション" />

## 典型的なLiferayアプリケーション

Liferayのソースから典型的なアプリケーションを見てみると、通常は少なくとも4つのモジュールがあります。

* APIモジュール
* サービス（プロバイダー）モジュール
* テストモジュール
* Web（コンシューマ）モジュール

これは、ユーザーがコメント、ブログ、または他のアプリケーションで`@username`の命名法を使用して他のユーザーに言及できるようにする、Mentionsアプリケーションなど、一部の小さなアプリケーションで見られるものとまったく同じです。 ドキュメントとメディアライブラリなどの大規模なアプリケーションには、さらに多くのモジュールがあります。 ドキュメントとメディアライブラリの場合、ドキュメントストレージバックエンドごとに個別のモジュールがあります。 Wikiの場合、Wikiエンジンごとに個別のモジュールがあります。

モジュールとして機能のバリエーションをカプセル化すると、拡張性が向上します。 Liferayがまだサポートしていないドキュメントストレージバックエンドがある場合は、モジュールを開発することでLiferayのドキュメントストレージAPIをソリューション用に実装し、Liferayのドキュメントとメディアライブラリを拡張できます。 Liferayのwikiが提供する言語よりも気に入ったWiki言語がある場合は、そのモジュールを作成してLiferayのwikiを拡張できます。

興味が湧いてきましたか。 開発を始める準備はできましたか。 さらに学習するためのリソースのいくつかを以下に紹介します。

<a name="追加情報" />

## 追加情報

* [OSGi Alliance](https://www.osgi.org/)
* [Getting started with OSGi at OSGi EnRoute](https://enroute.osgi.org/)
* [アプリケーションの開発](../../developing_applications.html)
* [開発者ツールの概要](../../developing-applications/tooling/developer-tools-overview.md)
* [Dockerイメージから始める](../../getting-started/starting-with-a-docker-image.md)
