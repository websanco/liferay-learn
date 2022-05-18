# OSGiサービスとしてのAPI

[モジュール](./module-projects.md)とは何か、モジュールをデプロイする方法を学習したら、モジュールを使用してAPIを定義し、それらを実装できます。 Liferay APIは [OSGiサービス](https://enroute.osgi.org/) であり、Javaインターフェイスによって定義され、具体的なJavaクラスによって実装されます。

Liferayは、API、実装、およびクライアントをコンポーネントとして公開します。 [OSGi Declarative Service](https://enroute.osgi.org/FAQ/300-declarative-services.html) （DS）アノテーションは、コンポーネントとそれらの関係を定義します。

* [`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) は、コンポーネントが提供（実装）または消費できるインターフェイスを定義します。
* [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) は、クラスをコンポーネントとして宣言し、特定の機能を提供します。
* [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) は、別のコンポーネントをクラスメンバー（通常はフィールド）に関連付けます。

APIと実装に関する懸念事項を異なるモジュールに分離することができます。

***API** モジュールは、Javaインターフェイスを使用して機能を **定義** します。 モジュールはインターフェイスパッケージをエクスポートします。
***実装** モジュールは、具体的なJavaクラスを使用して機能を **提供** します。

ここでは、単純なgreeter OSGiサービスを作成するAPIと実装モジュールをデプロイします。 また、実装モジュールとそのJARを調べて、実装することでgreeterサービス機能がどのように提供されるかを学習します。 次のチュートリアルでは、クライアント--- UIで呼び出すことができる部分を作成します。

<a name="シンプルなapiと実装をデプロイする" />

## シンプルなAPIと実装をデプロイする

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. `liferay-p9g2.zip`をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/liferay-internals/fundamentals/liferay-p9g2.zip -O
    ```

    ```bash
    unzip liferay-p9g2.zip
    ```

1. プロジェクトのルートフォルダから、モジュールをデプロイします。

    ```bash
    cd liferay-p9g2
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Dockerコンテナコンソールでモジュールの起動を確認します。

    ```
    STARTED com.acme.p9g2.api_1.0.0
    STARTED com.acme.p9g2.impl_1.0.0
    ```

1. `http://localhost:8080`に移動して、サインインします。

1. [Gogo シェル](./using-the-gogo-shell.md)に移動します。

1. `lb` Gogo シェルコマンドを使用してモジュールIDを取得します。

    ```bash
    g! lb | grep -i "Acme P9G2"
    ```

    出力:

    ```
    1150|Active     |   15|Acme P9G2 API (1.0.0)|1.0.0
    1151|Active     |   15|Acme P9G2 Implementation (1.0.0)|1.0.0
    ```

1. 次のコマンドを実行し、番号をモジュールのIDに置き換えて、実装モジュールのサービス機能を一覧表示します。

    ```bash
    g! inspect capability service 1195
    ```

    出力:

    ```
    com.acme.p9g2.impl_1.0.0 [1151] provides:
    -----------------------------------------
    service; com.acme.p9g2.Greeter with properties:
       service.id = 22933
       service.bundleid = 1151
       service.scope = bundle
       component.name = com.acme.p9g2.internal.P9G2Greeter
       component.id = 8462
    ```

Acme P9G2実装モジュールは、`com.acme.p9g2.Greeter`という1つのサービスを提供します。 `component.name`プロパティは、モジュールの`com.acme.p9g2.internal.P9G2Greeter`コンポーネントがサービスを実装していることを示しています。

`P9G2Greeter`コンポーネントが`Greeter`サービスを提供していることを確認しました。

次に、APIモジュールがどのようにgreeter機能を定義し、実装モジュールがどのようにgreeter機能をOSGiサービスとして提供するかを学習します。 まずはAPIの作成から始めます。

<a name="apiを作成する" />

## APIを作成する

APIは、次の2つのステップで作成します。

* [機能を定義する](#define-the-capability)
* [インタフェースパッケージのエクスポート](#export-the-interface-package)

### 機能を定義する

サンプルのAPIモジュールの`Greeter`クラスはJavaインターフェイスです。

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-api/src/main/java/com/acme/p9g2/Greeter.java
:language: java
:lines: 5-6
```

[`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) アノテーションは、`Greeter`をコンポーネントが実装または消費できるタイプとして登録します。

`greet`メソッドは、入力として`String`という名前を取ります。

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-api/src/main/java/com/acme/p9g2/Greeter.java
:dedent: 1
:language: java
:lines: 8
```

`Greeter`機能が定義されています。

### インターフェイスパッケージをエクスポートする

APIモジュールの`bnd.bnd`ファイルは、モジュールを記述し、`com.acme.p9g2`インターフェイスパッケージをエクスポートします。
```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-api/bnd.bnd
```

The [package export](./exporting-packages.md) shares the `Greeter` interface with other modules.

The `Greeter` service type is available to implement and use.

<a name="create-the-implementation" />

## Create the Implementation

The example implementation module contains a concrete Java class that provides the `Greeter` capability. Here are the implementation steps.

* [Add the Component Annotion Class](#add-the-component-annotation)
* [Implement the Interface](#implement-the-interface)
* [Add a Dependency on the API](#add-a-dependency-on-the-api)
* [Examine the Module JAR](#examine-the-module-jar)

### Add the Component Annotation

The `P9G2Greeter` class implements the `Greeter` interface:

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-impl/src/main/java/com/acme/p9g2/internal/P9G2Greeter.java
:language: java
:lines: 7-8
```

[`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) アノテーションとその`service = Greeter.class`属性により、`P9G2Greeter`クラスは`Greeter`サービス・プロバイダーになります。

### インターフェイスを実装する

`Greeter`インターフェイスは、`void`の戻り値を持つメソッド`greet(String)`を定義します。

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-impl/src/main/java/com/acme/p9g2/internal/P9G2Greeter.java
:dedent: 1
:language: java
:lines: 10-13
```

`greet`メソッドの例では、指定された名前を使用して熱烈な挨拶文を出力します。

### APIへの依存関係を追加する

実装モジュールの`build.gradle`ファイルは以下のとおりです。

```{literalinclude} ./apis-as-osgi-services/resources/liferay-p9g2.zip/p9g2-impl/build.gradle
:language: groovy
```

このファイルには、モジュールの`Greeter`クラスが必要なため、`p9g2-api`モジュールプロジェクトへのコンパイル時の依存関係が含まれています。

### モジュールのJARを調べる

`p9g2-impl/build/libs/com.acme.p9g2.impl-1.0.0.jar`実装モジュールJARをビルドしたとき、 [Bnd](http://bnd.bndtools.org/) はJARの`/META-INF/MANIFEST.MF`ファイルを生成しました。

Bndがマニフェストで生成する主要なサービス関連のヘッダは次のとおりです。

```properties
Import-Package: com.acme.p9g2;version="[1.0,2)"
```

[`Import-Package`](./importing-packages.md)ヘッダは、`Greeter`サービス定義を含むAPIモジュールのパブリックパッケージをインポートします。

```properties
Provide-Capability: osgi.service;objectClass:List<String>="com.acme.p9
 g2.Greeter";uses:="com.acme.p9g2"
```

`Provide-Capability`ヘッダは、`P9G2Greeter`コンポーネントサービスを構成します。

```properties
Service-Component: OSGI-INF/com.acme.p9g2.internal.P9G2Greeter.xml
```

`Service-Component`ヘッダには、モジュールの各サービスコンポーネントの構成ファイル（`.xml`）が一覧表示されます。

モジュールをデプロイしたとき、サービスコンポーネントランタイムは`P9G2Greeter`サービスコンポーネントを`Greeter`サービスを提供するものとして登録しました。

<a name="まとめ" />

## まとめ

`Greeter`というサービス機能を **定義** し、`P9G2Greeter`というサービスコンポーネントで提供しました。 `Greeter`サービスが配置されました。 クライアントはどのようにサービスにアクセスして使用するのでしょうか。 これについては、 [OSGiサービスの使用](./using-an-osgi-service.md) で説明しています。

<a name="追加情報" />

## 追加情報

* [Gogo シェルコマンド](./using-the-gogo-shell/gogo-shell-commands.md)
* [パッケージのエクスポート](./exporting-packages.md)
* [パッケージのインポート](./importing-packages.md)
* [依存関係の構成](./configuring-dependencies.md)
* [OSGiEnRouteでOSGiを使い始める](https://enroute.osgi.org/)
* [宣言型サービス](https://enroute.osgi.org/FAQ/300-declarative-services.html)
* [OSGiアライアンス](https://www.osgi.org/)