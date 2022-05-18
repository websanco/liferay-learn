# モジュールプロジェクト

Liferayのアプリケーションやカスタマイズは、 [OSGi モジュール](https://www.osgi.org/resources/what-is-osgi/) : `.jar`ファイルで、Javaコードと、APIを公開・消費するための追加設定が含まれています。

モジュールプロジェクトは、次の3つから構成されています。

1. **コード：** Javaクラスと、画像、テンプレート、追加の記述子などのリソース。 Javaパッケージはデフォルトではプライベートですが、他のモジュールが使用できるように[エクスポート](./exporting-packages.md)することができます。

1. **ビルドスクリプト：** モジュールをビルドしてデプロイするための [Gradle](https://gradle.org/) ファイル。

1. **メタデータ：** [Bnd](https://bnd.bndtools.org/) ファイルは、モジュールのアーティファクトを定義し、モジュールが提供するまたは必要とするパッケージや機能を指定します。

モジュールプロジェクトの構造は次のとおりです。

```
[project root]
 └── [module 1]
 │    ├── bnd.bnd // Defines the module artifact, provided/required capabilities, and more
 │    ├── build.gradle // Declares dependencies
 │    └── src
 │        └── main
 │            ├── java
 │            │   └── [Java packages]
 │            └── resources
 │                └── [Images, templates, descriptors, etc.]
 │
 └── [module 2]
 │
 └── [module n]
 │
 ├── gradle
 │   └── [Gradle wrapper files]
 ├── gradlew // Invokes the Gradle wrapper to execute tasks
 ├── gradlew.bat
 ├── gradle.properties // Specifies the Liferay product version
 └── settings.gradle // Applies Gradle plugins
```

Liferayでは、一般的に3種類のモジュールを使用します。

1. **API** モジュールはインターフェイスを定義します。

1. **実装** モジュールは、インターフェイスを実装する具象クラスを提供します。

1. **クライアント** モジュールはAPIを消費します。

[Gogo シェル](./using-the-gogo-shell.md)でユーザーが名前を入力したときにあいさつ文を表示する簡単なコマンドを開発することで、それぞれを作成する方法を学習します。

![ユーザーに挨拶をするGogoシェルコマンド。](./module-projects/images/01.png)

ここでは、APIを作成し、モジュールプロジェクトの各部分について学習し、モジュールをデプロイして、ランタイム時にモジュールを検査します。 次の2つのチュートリアルでは、実装モジュールとクライアントモジュールを作成します。

まず、サンプルのAPIモジュールプロジェクトをデプロイします。

<a name="シンプルなモジュールのデプロイ" />

## シンプルなモジュールのデプロイ

サンプルモジュールでは、あいさつ文を生成するためのAPIを定義します。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. サンプルをダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/liferay-internals/fundamentals/liferay-k8s2.zip -O
    ```

    ```bash
    unzip liferay-k8s2.zip
    ```

1. モジュールのJARをビルドします。

    ```bash
    cd liferay-k8s2
    ```

    ```bash
    ./gradlew jar
    ```


    JARファイルはモジュールの `build/libs` フォルダに生成されます。

    ```
    k8s2-api/build/libs/com.acme.k8s2.api-1.0.0.jar
    ```

1. モジュールのJARをデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ログメッセージには、LiferayがJARを処理してモジュールを起動していることが示されます。

    ```
    Processing com.acme.k8s2.api-1.0.0.jar
    STARTED com.acme.k8s2.api_1.0.0 [1152]
    ```

    `STARTED` のメッセージには、モジュールのID `1152`が含まれています。

1. [Gogo シェル](./using-the-gogo-shell.md)を開きます。

1. Gogo シェルのコマンド欄に `lb` と入力すると、モジュールのIDなどの情報が表示されます。 最後に追加されたモジュールが最後に表示されます。 モジュール名のキーワードがわかっていれば、 `grep` で探すことができます。

    ```bash
    lb | grep -i "k8s2"
    ```

    出力:

    ```
    1152|Active     |   15|Acme K8S2 API (1.0.0)|1.0.0
    ```

    このモジュールのIDは `1152`です。

1. モジュールの詳細情報を表示するには、 `b` コマンドとモジュールIDを使用します。

    ```bash
    b 1152
    ```

    出力:

    ```
    com.acme.k8s2.api_1.0.0 [1152]
    Id=1152, Status=ACTIVE      Data Root=[Liferay Home]/osgi/state/org.eclipse.osgi/1152/data
      "No registered services."
      No services in use.
      Exported packages
        com.acme.k8s2; version="1.0.0"[exported]
      No imported packages
      No fragment bundles
      No required bundles
    ```

このモジュールはアクティブで、`com.acme.k8s2`というパッケージをエクスポートしています。

モジュールのインストールとアクティベーションが完了したので、その機能について見ていきます。

<a name="モジュールの設定方法" />

## モジュールの設定方法

* [ビルドインフラストラクチャーの構築](#set-up-the-build-infrastructure)
* [コードを書く](#write-code)
* [依存関係の指定](#specify-dependencies)
* [メタデータの指定](#specify-metadata)

### ビルドインフラストラクチャーの構築

Liferayのモジュールは、Gradleのビルドインフラストラクチャーで開発されています。 プロジェクトのルートフォルダには、以下のGradleファイルが入っています。

| ファイル                | 説明                                                                                        |
|:------------------- |:----------------------------------------------------------------------------------------- |
| `gradle/`           | Gradleのラッパーが含まれています。                                                                      |
| `gradlew［.bat］`     | タスクを実行するためにGradleラッパーを呼び出します                                                              |
| `gradle.properties` | Liferayの製品バージョンを指定します                                                                     |
| `settings.gradle`   | [Liferay Workspace](../../developing-applications/tooling.md) のプラグインを含むGradleプラグインを適用します。 |

サンプルプロジェクトの `k8s2-api` フォルダのように、新しいサブフォルダにモジュールを追加したり、新しい [Liferay Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md) にモジュールを作成することができます。

以下は、`k8s2-api` モジュールの構造を、プロジェクトルートのコンテキストで示したものです。

```
［project root］
 └── k8s2-api
 │   ├── bnd.bnd
 │   ├── build.gradle
 │   └── src
 │       └── main
 │           └── java
 │               └── com/acme/k8s2
 │                   └── Greeter.java
 │
 └── ［Gradle files］
```

`k8s2-api` モジュールフォルダには、`bnd.bnd` メタデータファイル、 `build.gradle` スクリプト、およびJavaコードが含まれています。

### コードを書く

サンプルモジュールには、`Greeter`という名前のJavaクラスが1つしかありません。

```{literalinclude} ./module-projects/resources/liferay-k8s2.zip/k8s2-api/src/main/java/com/acme/k8s2/Greeter.java
:language: java
:lines: 5-10
```

[`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) アノテーションは、そのインターフェイスを実装しているものがそれを提供することをサービスレジストリに伝えます（すなわち`Greeter`）。 インターフェイスの `greet` という1つのメソッドは、 `String` を要求し、何も返しません。

モジュールの `src/main/java` フォルダと `src/main/resources` フォルダに、それぞれ独自のJavaコードとリソースを追加します。

### 依存関係の指定

`build.gradle` ファイルでは、モジュールの依存関係を指定します。

```{literalinclude} ./module-projects/resources/liferay-k8s2.zip/k8s2-api/build.gradle
:language: groovy
```

これは、LiferayリリースのAPI JARという1つのアーティファクトに依存しています。 これは、Liferay製品のリリースに関連するLiferay、Bnd、OSGiのアーティファクトを詰め込んだ大きなJARです。

`［project root］/gradle.properties` ファイルでは、 `liferay.workspace.product` プロパティで製品のリリースを指定しています。

```properties
liferay.workspace.product=portal-7.3-ga3
```

最後に、依存性のあるバージョンはありません。 これは、ワークスペースがリリースに関連するLiferay製品のAPIバージョンを適用するためです。

```{note}
詳細については、[依存関係の構成](./configuring-dependencies.md)を参照してください。
```

### メタデータの指定

モジュールJARの `META-INF/MANIFEST.MF` ファイルには、モジュールの説明が書かれています。 マニフェストには、マニフェストヘッダーと呼ばれるプロパティが含まれ、モジュールがエクスポート/インポートするパッケージや、モジュールが提供/要求する機能を指定します。 ビルドインフラストラクチャーがBndを提供しているので、モジュールの `bnd.bnd` ファイルにいくつかの初期ヘッダーを指定するだけで済みます 。 Bndは、モジュールの検査に基づいて他のほとんどの値を生成します。

#### 初期のメタデータ

`bnd.bnd` ファイルは、モジュールの説明と設定を行います。
```{literalinclude} ./module-projects/resources/liferay-k8s2.zip/k8s2-api/bnd.bnd
```

The module's name is **Acme K8S2 API** . Its symbolic name---a name that ensures uniqueness---is `com.acme.k8s2.api`. Its [semantic version](./semantic-versioning.md) is declared next. Lastly, the module [**exports**](./exporting-packages.md) the Java package `com.acme.k8s2`, making the package available to other modules. You confirmed the package export above when you executed the `b [bundle ID]` Gogo Shell command.

#### Generated Metadata

At build time, Bnd propagates metadata from the `bnd.bnd` file to the JAR file's `META-INF/MANIFEST.MF` and adds metadata based on its inspection.

Here's a `META-INF/MANIFEST.MF` file generated for the example module:

```properties
Manifest-Version: 1.0
Bnd-LastModified: 1598968383025
Bundle-ManifestVersion: 2
Bundle-Name: Acme K8S2 API
Bundle-SymbolicName: com.acme.k8s2.api
Bundle-Version: 1.0.0
Created-By: 1.8.0_252 (Oracle Corporation)
Export-Package: com.acme.k8s2;version="1.0.0"
Javac-Debug: on
Javac-Deprecation: off
Javac-Encoding: UTF-8
Require-Capability: osgi.ee;filter:="(&(osgi.ee=JavaSE)(version=1.8))"
Tool: Bnd-4.3.0.201909301554
```

Bndは`bnd.bnd`ファイルからすべてのヘッダーをプロパゲートし、さらにヘッダーと詳細を追加しました。 例えば、エクスポートされた`com.acme.k8s2`パッケージには、デフォルトのパッケージバージョン`1.0.0`があります。

<a name="まとめ" />

## まとめ

これで完了です。 ご覧の通り、モジュールプロジェクトは他のJavaプロジェクトと同じですが、いくつかの設定が追加されています。

これで、モジュールプロジェクトがどのようなものか、ビルドしてデプロイする方法、そしてランタイム時にモジュールを検査する方法をマスターしました。

モジュールは、`Greeter` APIのようなAPIを介して、互いの機能を活用します。 LiferayはOSGiサービスを使用して、APISを定義、実装、消費します。 次に、[依存関係の構成](./apis-as-osgi-services.md)で、OSGiサービスを使用した`Greeter` APIの **実装** について説明します。

```{note}
モジュールのライフサイクルの詳細については、[モジュールのライフサイクル](../architecture/module-lifecycle.md)を参照してください。
```

<a name="追加情報" />

## 追加情報

* [OSGiサービスとしてのAPI](./apis-as-osgi-services.md)
* [OSGiサービスの使用](./using-an-osgi-service.md)
* [依存関係を構成する](./configuring-dependencies.md)
* [パッケージのインポート](./importing-packages.md)
* [パッケージのエクスポート](./exporting-packages.md)
* [モジュールのライフサイクル](../architecture/module-lifecycle.md)