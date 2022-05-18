# RESTビルダーを使用した新しいAPIの実装

RESTビルダーを使用すると、構築するAPIを定義でき、RESTビルダーはフレームワークとエンドポイントを提供します。 <!-- Add link to the REST Builder overview article once available. -->

<a name="deploy-an-example-rest-api" />

## サンプルのREST APIをデプロイする

RESTビルダーの動作を確認するために、カタログ内のIDによってダミー製品を取得するサンプルAPIをデプロイできます。 この簡単な例がどのように機能するかを理解したら、独自のアプリケーション用のAPIを作成できます。

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. [Acme Foo API](./producing-apis-with-rest-builder/liferay-r3b2.zip) を含む`.zip`アーカイブをダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/headless-delivery/producing-apis-with-rest-builder/liferay-r3b2.zip -O
    ```

    ```bash
    unzip liferay-r3b2.zip
    ```

1. サンプルをビルドしてデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
       このコマンドは、デプロイされたjarをDockerコンテナの/opt/liferay/osgi/modulesにコピーするのと同じです。
    ```

1. `api`バンドルと`impl`バンドルの両方のDockerコンテナコンソールでのデプロイを確認します

    ```
    STARTED com.acme.headless.r3b2.api_1.0.0 
    STARTED com.acme.headless.r3b2.impl_1.0.0
    ```

1. DXPインスタンスにログインし、 **グローバルメニュー**（![Global Menu icon](../../images/icon-applications-menu.png)）&rarr; ［**コントロールパネル**］ &rarr; ［**Gogo シェル**］ に移動します。

1. Gogo シェルプロンプトで、次のコマンドを入力します。

    ```
    jaxrs:check
    ```

    このページには、新しくデプロイされたAPIである`Liferay.Headless.R3B2`を含む、インストールされているすべてのJAX-RSバンドルが一覧表示されます。 これでAPIがデプロイされ、呼び出す準備が整いました。

    ![新しくデプロイされたAPI（Liferay.Headless.R3B2という名前）は、コマンドの結果として一覧表示され、使用できるようになります。](./implementing-a-new-api-with-rest-builder/images/01.png)

1. ターミナルから次のコマンドを実行し、`{fooId}`を1〜3の数字に置き換えて、APIをテストします。

    ```bash
    curl -u 'test@liferay.com:learn' "http://localhost:8080/o/headless-r3b2/v1.0/foo/{fooId}"
    ```

    クエリは、JSONオブジェクトでラップされた対応する製品のID、名前、および説明を返します。

    ```json
   {
     "description": "Universal truth must be transcendental.",
     "id": 1,
     "name": "Truth"
   }
    ```

新しいREST APIを正常にデプロイして使用しました。

RESTビルダーで生成されたAPIを確認したので、次はそれがどのように機能するかを理解します。

<a name="initial-setup" />

## 初期設定

Liferayワークスペースプロジェクトで`impl`および`api`モジュールを作成することから始めます。  `impl`モジュールの`build.gradle`ファイルは、RESTビルダーをプラグインとしてインストールして適用する必要があります。

```
buildscript {
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.rest.builder", version: "1.1.32"
    }

    repositories {
        maven {
            url "https://repository-cdn.liferay.com/nexus/content/groups/public"
        }
    }
}

apply plugin: "com.liferay.portal.tools.rest.builder"

dependencies {
    compileOnly group: "com.liferay.portal", name: "release.portal.api"
    compileOnly project(":headless-r3b2-api")
}
```

両方のモジュールの`build.gradle`ファイルも、ポータルリリースへの依存関係を宣言する必要があります。

<a name="yaml-configuration" />

## YAML構成

最初のステップは、RESTビルダー構成ファイルを作成することです。 `impl`モジュールのルートフォルダに、`rest-config.yaml`と`rest-openapi.yaml`の2つのファイルを追加します。 これらのファイルには、RESTビルダーがAPIのビルディングブロックコードを生成するために必要なすべての情報が含まれている必要があります。

### RESTビルダー構成を追加する

RESTビルダーの構成は`rest-config.yaml`ファイルに属します。  この構成では次のフィールドを定義します。

`apiDir`：Javaソースコードフォルダ

`apiPackagePath`：RESTビルダーがすべてのモジュールにわたってコードを生成する開始Javaパッケージパス

`baseURI`：このプロジェクトのすべてのAPIのコンテキストURL

`className`：ルートリソースクラスのJavaクラス名（JAX-RSで使用）

`name`：APIのJAX-RS名

次の構造を使用して、これらのフィールドを定義します。

```yaml
apiDir: "../headless-r3b2-api/src/main/java"
apiPackagePath: "com.acme.headless.r3b2"
application:
    baseURI: "/headless-r3b2"
    className: "HeadlessR3B2Application"
    name: "Liferay.Headless.R3B2"
author: "Jonah the son of Amittai"
clientDir: "../headless-r3b2-client/src/main/java"
testDir: "../headless-r3b2-test/src/testIntegration/java"
```

### OpenAPI構成に情報ブロックを追加する

次に、`rest-openapi.yaml`ファイルを開いて、APIの構成を開始します。

追加する最初のセクションは、情報ブロックです。

```yaml
info:
    description:
        "API to return a Foo."
    license:
        name: "Apache 2.0"
        url: "http://www.apache.org/licenses/LICENSE-2.0.html"
    title: "Headless R3B2"
    version: v1.0
openapi: 3.0.1
```

```important::
   ここで定義された `` version``フィールドは、APIパスがLiferayインスタンス内で公開されるときにURLの一部になります。
```

### 必要なスキーマを定義する

次に、`components`ブロックで、エンティティのスキーマを定義します。 RESTビルダーは、ここで定義したものを使用して、これらのエンティティを表す対応するJava Beanを作成します。 <!-- Add reference to overview article elaborating a bit more on how REST Builder represents Java Objects with schemas. -->

表現するエンティティごとに`schema`ブロックを定義します。

```yaml
components:
    schemas:
        Foo:
            properties:
                description:
                    type: string
                id:
                    format: int64
                    type: integer
                name:
                    type: string
            type: object
        Goo:
            properties:
                description:
                    type: string
                fooId:
                    format: int64
                    type: integer
                id:
                    format: int64
                    type: integer
                name:
                    type: string
```

この例では、`Foo`というスキーマが、このAPIを使用するための重要なデータを表しています。 `Goo`エンティティは、`fooId`を使用して`Foo`にリンクされています。  スキーマでサポートされているデータタイプのリストについては、 [OpenAPIの仕様](https://swagger.io/docs/specification/data-models/data-types/) を参照してください。

スキーマ定義によって、RESTビルダーが生成するクラスの名前が決まります。これには、リソースファイル内のビルディングブロックとテンプレートが含まれます。 上記のスキーマは`Foo`および`Bar`と呼ばれるため、実装ロジックは`FooResourceImpl`クラスと`GooResourceImpl`クラスに属します。

### APIを定義する

最後に、`paths`ブロックを追加します。 これには、RESTビルダーで実装する予定のすべてのAPIが含まれている必要があります。 パスブロックの小さなスニペットを次に示します。

```yaml

paths:
    "/foo":
        get:
        # operations for get and post go here. See the project for full source code.
        # ...

    "/foo/{fooId}":
        get:
            operationId: getFoo
            # ...

            responses:
                200:
                    content:
                        application/json:
                            schema:
                                $ref: "#/components/schemas/Foo"
                        application/xml:
                            schema:
                                $ref: "#/components/schemas/Foo"
        # Place other operations, such as get, patch, and put here. See the project for full source code.

    "/foo/{fooId}/goos":
        get:
            operationId: getFooGoosPage
            # This is the relationship between Foos and Goos. 
            # Place your get and post operations here. 
            # ...

    "/goo/{gooId}":
        delete:
            operationId: deleteGoo

            # Place operations on other entities as needed.
```

```tip::
   `` get``、 `` post``、 `` put``、 `` patch``、 `` delete``など、さまざまな種類のリクエストのパスを追加できます。
```

このパス（`foo/{fooId}`）は、URLの末尾にパス文字列を追加することでこのAPI（`getFoo`）に到達できることを指定します（これには、`rest-config.yaml`ファイルの`baseURI`と`version`の値も含まれます）。 たとえば、このサンプルAPIには、完全なURL：`localhost:8080/o/headless-r3b2/v1.0/foo/{fooId}`を介してアクセスします。

`fooId`の代わりに使用する値は、一致する名前のパラメーターとして使用されます。

各パスには、`parameters`ブロックの下（および`get`ブロック内）に`responses`ブロックがあり、少なくとも成功した呼び出しの応答（`200`応答で示される）を定義します。

この`responses`ブロックは、呼び出しの成功時に`Product`を返すことを指定します。 文字列`#/components/schemas/Foo`は、同じファイルで以前に定義されたスキーマを参照し、RESTビルダーがこのAPIの戻り値のタイプとして`Foo`スキーマを使用できるようにします。

最後に、`responses`ブロックの下に、このパスの`tags`定義を追加します。

```yaml
tags: ["Foo"]
```

このタグは、RESTビルダーがビルディングブロックコードにアノテーションを付けるときに生成されたドキュメンテーションに追加される情報を指定します。 タグ名は、スキーマの名前を反映している必要があります。

完全なリファレンスについては、以前にダウンロードした`rest-openapi.yaml`ファイルを参照してください。

関係をどのように行うかを示す`Goo`オブジェクトもあります。Gooは、`fooId`に関連付けられているという意味でFooに関連付けられています。

<a name="run-rest-builder" />

## RESTビルダーを実行する

これで、RESTビルダーがほとんどの作業を実行するために必要なすべての構成が追加されたので、`impl`モジュール内から次のコマンドを実行して`buildREST` Gradleタスクを実行します。

```bash
../gradlew buildREST
```

RESTビルダーはこの構成を使用して、`api`クラスと`impl`クラスの両方にビルディングブロックコードと、実装ロジックを追加できるJavaクラスを取り込みます。

<a name="add-your-implementation-logic" />

## 実装ロジックを追加する

最後のステップは、定義した各APIのロジックを定義することです。 `impl`モジュール内で、`rest-openapi.yaml`で定義したスキーマ名（この例では、`FooResourceImpl.java`と`GooResourceImpl.java`）に基づいて、実装が配置されるJavaリソースクラスを見つけます。

```tip::
   実装のクラスの場所は、`` rest-config.yaml``ファイルで `` apiPackagePath``に定義した値によって異なります。 そのパスをたどり、その中の``internal/resource/<version>/``に移動します。 この例と同じパスを使用した場合、ファイルは``src/main/java/com/acme/headless/r3b2/internal/resource/v1_0/``内にあります。
```

実装クラス（`[SchemaName]ResourceImpl`）は、基本クラス（`Base[SchemaName]ResourceImpl`）の横にあります。 実装クラスを開きます。 これは単なる例であるため、この実装では事前に入力された`HashTable`を使用し、`getFoo`メソッドは一致する`fooId`を持つ`HashTable`から製品を返します。 完全な実装については、プロジェクト内の`FooResourceImpl.java`を参照してください。

```java
    @Override
    public Foo getFoo(Integer fooId) {
        return _foos.get(fooId);
    }
```

このメソッドは、特別なJAX-RSアノテーションを使用して定義された基本クラス（`Base[SchemaName]ResourceImpl`）で定義された基本メソッドをオーバーライドします。

リクエストを完了するために、任意のビジネスロジックを追加できます。 RESTビルダーは、スキーマで定義したオブジェクトのデフォルトコンストラクターのみを作成します。 このサンプルのビジネスロジックは、オブジェクトを作成し、それに値を追加します（`rest-openapi.yaml`で`parameters`を定義した方法に基づく）。

```java
   Foo foo1 = new Foo() {
      {
         description = "Universal truth must be transcendental.";
         id = 1L;
         name = "Truth";
      }
   };
```

`Goo`ロジックは似ていますが、この場合、`Foo`オブジェクトに複数の`Goo`を含めることができるため、複数の`Goo`が返される点が異なります。 オブジェクトのコレクションを返すときは、`Page`と呼ばれるページ付けに適したオブジェクトを使用する必要があります。

```java
    @Override
    public Page<Goo> getFooGoosPage(Long fooId) {
        List<Goo> goos = new ArrayList<>();

        for (Goo goo : _goos.values()) {
            if (Objects.equals(fooId, goo.getFooId())) {
                goos.add(goo);
            }
        }

        return Page.of(goos);
    }
```

<a name="conclusion" />

## まとめ

　 これで、RESTビルダーを使用して新しいAPIを実装するための基本を理解し、DXPに新しいAPIを追加しました。

<!-- Add links to the overview and other articles as additional information when available. -->
