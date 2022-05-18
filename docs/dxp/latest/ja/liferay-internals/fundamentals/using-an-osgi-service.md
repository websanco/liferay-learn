# OSGiサービスの使用

Liferay APIは、OSGiサービスとしてすぐに利用できます。 次のように、そのサービスタイプのフィールドを作成し、そのフィールドに [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) アノテーションを付けることで、サービスにアクセスできます。

```java
@Reference
BlogsEntryService _blogsEntryService;
```

上記の`_blogsEntryService`フィールドは、 [`BlogsEntryService`](https://docs.liferay.com/ce/apps/blogs/latest/javadocs/com/liferay/blogs/service/BlogsEntryService.html) OSGiサービスにアクセスします。

すべてのDeclarative Servicesコンポーネント（ [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) でアノテーションが付けられたクラス）は、この方法でOSGiサービスにアクセスできます。  ランタイムフレームワークは、コンポーネントの`@Reference`のアノテーションが付いたフィールドにサービスタイプを挿入します。

次の例は、`Greeter`と呼ばれるOSGiサービスの使用を示しています。 3つのモジュールは、OSGiサービスで使用される **API-Provider-Consumer** パターンを示しています。

***API** モジュールは、`Greeter`サービスタイプを定義します。
* 実装モジュールは`Greeter`サービスを **提供** します。
* サンプルモジュールは`Greeter`サービスを **消費** します。

サンプルモジュールクラスは、`Greeter`サービスを使用してパーソナライズされた挨拶を返すGogo シェルコマンドを作成します。 この例をOSGiサービスの「Hello World」と考えてください。

![サンプルモジュールは、Gogo シェル用のgreeterコマンドを提供します。](./using-an-osgi-service/images/01.png)

OSGiサービスは任意のJavaクラスで使用できます。

LiferayサービスのJavadocは [こちら](https://learn.liferay.com/reference/latest/en/dxp.html) から入手できます。

```{note}
OSGiサービスの作成方法については、 [OSGiサービスとしてのAPI](./apis-as-osgi-services.md) を参照してください。
```

<a name="gogo-シェルコマンドの例をデプロイする" />

## Gogo シェルコマンドの例をデプロイする

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. `liferay-j1h1.zip`をダウンロードして解凍します。

    ```bash
    curl hhttps://learn.liferay.com/dxp/latest/ja/liferay-internals/fundamentals/liferay-j1h1.zip -O
    ```

    ```bash
    unzip liferay-j1h1.zip
    ```

1. サンプルモジュールをデプロイします。

    ```bash
    cd liferay-j1h1.zip
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```
    STARTED com.acme.j1h1.api_1.0.0
    STARTED com.acme.j1h1.impl_1.0.0
    STARTED com.acme.j1h1.osgi.commands_1.0.0
    ```

1. [Gogo シェル](./using-the-gogo-shell.md)を開きます。

1. Gogo シェルコマンドフィールドに、`j1h1:greet`コマンドを入力して、挨拶を生成します。

    ```groovy
    j1h1:greet "Captain Kirk"
    ```

1. 出力を確認します。

    ```
    Hello Captain Kirk!
    ```

この例のモジュールは、APIおよび実装モジュールを利用して、`j1h1:greet` Gogo シェルコマンドから返されるコンテンツを生成します。

<a name="osgiサービスの使用方法" />

## OSGiサービスの使用方法

* [ビジネスロジックの作成](#write-your-business-logic)
* [外部サービスリファレンスの注釈](#annotate-external-service-references)
* [クラスをコンポーネントにする](#make-your-class-a-component)

### ビジネスロジックの作成

クラスでは、必要なOSGiサービスを使用してビジネスロジックを実装します。

1. サービスをインポートします。

    ```java
    import com.acme.j1h1.Greeter;
    ```

1. サービスを使用します。

    ```java
    public void greet(String name) {
        _greeter.greet(name);
    }

    private Greeter _greeter;
    ```

上記のメソッドは、`Greeter`の`greet`メソッドを呼び出します。 `com.acme.j1h1.Greeter`は、実装モジュールが登録するOSGiサービスタイプです。 クラスは、OSGiサービスレジストリから`Greeter`インスタンスを取得する必要があります。

### 外部サービスリファレンスの注釈

レジストリからOSGiサービスを取得するには、そのサービスタイプのフィールドに [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) アノテーションを追加する必要があります。 `@Reference`をサービスフィールドに追加します。

```{literalinclude} ./using-an-osgi-service/resources/liferay-j1h1.zip/j1h1-osgi-commands/src/main/java/com/acme/j1h1/internal/osgi/commands/J1H1OSGiCommands.java
:dedent: 1
:language: java
:lines: 18-19
```

`J1H1OSGiCommands`クラスには、`_greeter`と呼ばれる上記のプライベート`Greeter`フィールドがあります。 `@Reference`アノテーションは、レジストリからの`Greeter`サービスをフィールドに挿入するようにOSGiランタイムに指示します。 `J1H1Greeter`がレジストリ内で最も一致する`Greeter`サービスコンポーネントである場合（この例ではこれが唯一の一致です）、ランタイムは`_greeter`に`J1H1Greeter`を挿入します。

### クラスをコンポーネントにする

`@Reference`アノテーションを使用できるのは、Declarative Servicesコンポーネントのみです。  `@Component`アノテーションをクラスに追加し、`service`属性を使用して、コンポーネントが特定のサービスを実装していることを宣言します。

```{literalinclude} ./using-an-osgi-service/resources/liferay-j1h1.zip/j1h1-osgi-commands/src/main/java/com/acme/j1h1/internal/osgi/commands/J1H1OSGiCommands.java
:language: java
:lines: 8-12
```

`J1H1OSGiCommands`クラスは、独自のタイプのOSGiサービスを提供します。 2つのプロパティは、`j1h1`というスコープで`greet` というコマンド関数を使用してGogoシェルコマンドを定義します。 デプロイされた`J1H1OSGiCommands`コンポーネントは、`String`を入力として受け取るGogo シェルコマンド`j1h1:greet`を提供します。

### APIへの依存関係を追加する

コンシューマモジュールはAPIに依存します。 `build.gradle`ファイルで、依存関係にAPIを追加します。 `j1h1-osgi-commands`モジュールの`build.gradle`ファイルは次のとおりです。

```{literalinclude} ./using-an-osgi-service/resources/liferay-j1h1.zip/j1h1-osgi-commands/build.gradle
:language: groovy
```

`release.portal.api`アーティファクトは、モジュールが現在のLiferay製品リリースから必要とするLiferay、Bnd、およびOSGiサービスを提供します。 `[project root]/gradle.properties`ファイルの`liferay.workspace.product`は、リリースを指定します。

ローカルプロジェクト`j1h1-api`は`Greeter`サービスを提供するため、`j1h1-osgi-commands`はアーティファクトではなくプロジェクトとしてそのサービスに依存できます。 外部アーティファクトに[依存関係を指定する](./configuring-dependencies/specifying-dependencies.md)ことも簡単です。

<a name="まとめ" />

## まとめ

APIモジュールとImplモジュールは、それぞれ`Greeter`サービスを定義および提供しました。 例の`j1h1-osgi-commands`モジュールは、サービスを使用して単純なGogo シェルコマンドを作成します。 API-Provider-Consumer契約によって疎結合が促進され、ソフトウェアの管理、拡張、およびサポートが容易になります。

隣接するプロジェクトからOSGiサービスを使用することに慣れてきたので、外部アーティファクトからOSGiサービスを使用することを検討してみましょう。 [依存関係の構成](./configuring-dependencies.md) では、モジュールを見つけて依存関係として構成する方法を示しています。

<a name="追加情報" />

## 追加情報

* [パッケージのインポート](./importing-packages.md)
* [パッケージのエクスポート](./exporting-packages.md)
* [セマンティックバージョニング](./semantic-versioning.md)
* [依存関係の構成](./configuring-dependencies.md)