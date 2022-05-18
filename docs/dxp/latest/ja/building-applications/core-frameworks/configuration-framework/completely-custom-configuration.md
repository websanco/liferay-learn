# 完全なカスタム構成

[インターフェイスを作成](./setting-and-accessing-configurations.html#creating-the-configuration-interface) すると、構成UIが自動的に生成されます。 ただし、構成に完全にカスタムのUIが必要な場合もあります。 たとえば、Liferayの構成管理を使用する代わりに、プログラムで構成を処理するよう計画している場合などです。 または、完全にカスタムのUIを作成する柔軟性が必要な場合もあります。 その方法は以下の通りです。

<a name="see-the-example-project" />

## サンプルプロジェクトを参照する

```{include} /_snippets/run-liferay-dxp.md
```

次に、以下の手順を実行します。

1. [完全なカスタム構成](./liferay-u2g5.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/configuration-framework/liferay-u2g5.zip -O
    ```

    ```bash
    unzip liferay-u2g5.zip
    ```

1. モジュールのルートから、ビルドおよびデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、デプロイされたjarをDockerコンテナの/opt/liferay/osgi/modulesにコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.u2g5.web_1.0.0 [1034]
    ```

1. サンプルのモジュールが機能していることを確認します。 ブラウザで`https://localhost:8080`を開きます。

1. ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**システム設定**］ &rarr; ［**サードパーティー**］ に移動します。 ［**U2G5 Configuration**］ をクリックします。

   ![システム設定でU2G5構成に移動します。](./completely-custom-configuration/images/01.png)

   このビューはカスタムJSPファイルによって提供されることに注意してください。

<a name="create-the-configuration-interface" />

## 構成インターフェイスを作成する

構成インターフェイスで構成可能な属性を定義します。 サンプルプロジェクトには、`fontColor`、`fontFamily`、および`fontSize`の3つの構成可能な属性があります。

```{literalinclude} ./completely-custom-configuration/resources/liferay-u2g5.zip/u2g5-web/src/main/java/com/acme/u2g5/web/internal/configuration/U2G5WebConfiguration.java
:language: java
:lines: 7-24
```

`@ExtendedObjectClassDefinition`アノテーションでは、`generateUI`が`false`に設定されていることに注意してください。 これにより、構成UIが自動生成されなくなります。

<a name="add-the-configuration-bean-declaration" />

## 構成Bean宣言を追加する

構成クラスを`ConfigurationBeanDeclaration`に登録します。 これにより、システムは構成の変更が発生したときにそれを追跡できます。

```{literalinclude} ./completely-custom-configuration/resources/liferay-u2g5.zip/u2g5-web/src/main/java/com/acme/u2g5/web/internal/settings/definition/U2G5WebConfigurationBeanDeclaration.java
:language: java
:lines: 9-18
```

<a name="implement-the-configuration-screen" />

## 設定画面を実装する

1. `@Component`アノテーションを使用して、`ConfigurationScreen`の実装としてクラスを宣言します。

    ```java
    @Component(service = ConfigurationScreen.class)
    ```

1. カテゴリーキー、設定エントリーのキー、およびそのローカライズされた名前を設定します。 サンプルプロジェクトでは、システム設定でカテゴリーキーが`third-party`に設定されています。 構成名のString値は、バンドルの`Language.properties`ファイルの言語キーによって設定されます。

    ```{literalinclude} ./completely-custom-configuration/resources/liferay-u2g5.zip/u2g5-web/src/main/java/com/acme/u2g5/web/internal/configuration/admin/display/U2G5ConfigurationScreen.java
    :dedent: 1
    :language: java
    :lines: 24-40
    ```

1. この例では、構成スコープは `system`に設定されています。 詳細については、 [スコープ設定](./scoping-configurations.md) を参照してください。

    ```{literalinclude} ./completely-custom-configuration/resources/liferay-u2g5.zip/u2g5-web/src/main/java/com/acme/u2g5/web/internal/configuration/admin/display/U2G5ConfigurationScreen.java
    :dedent: 1
    :language: java
    :lines: 42-45
    ```

1. `render()`メソッドは`ConfigurationProvider`を使用して構成を取得します。 サーブレットコンテキストは、リクエストディスパッチャへのアクセスを提供します。これにより、カスタムJSPが構成を読み取ることができます。

    ```{literalinclude} ./completely-custom-configuration/resources/liferay-u2g5.zip/u2g5-web/src/main/java/com/acme/u2g5/web/internal/configuration/admin/display/U2G5ConfigurationScreen.java
    :dedent: 1
    :language: java
    :lines: 47-67
    ```

1. 必ず`@Reference`アノテーションを使用して、モジュールのシンボリック名を定義してください。

    ```java
    @Reference(
        target = "(osgi.web.symbolicname=com.acme.u2g5.web)"
    )
    ```

<a name="add-the-web-contextpath" />

## Web-ContextPathを追加する

`bnd.bnd`ファイルでバンドルの`Web-ContextPath`を指定します。 たとえば、サンプルプロジェクトのBndファイルには`Web-ContextPath: /u2g5-web`があります。 これは、構成画面ファイルに`ServletContext`オブジェクトを登録するものです。 サーブレットコンテキストはポートレット用に自動的に作成されますが、このサンプルにはポートレットがないため、この行をBndファイルに追加する必要があることに注意してください。

<a name="create-a-custom-jsp" />

## カスタムJSPを作成する

1. 構成インターフェイスをJSPにインポートします。

    ```markup
    <%@ page import="com.acme.u2g5.web.internal.configuration.U2G5WebConfiguration" %>
    ```

1. リクエストオブジェクトから構成値にアクセスします。

    ```jsp
    <%
    U2G5WebConfiguration u2g5WebConfiguration = (U2G5WebConfiguration)request.getAttribute(U2G5WebConfiguration.class.getName());
    %>
    ```

1. 属性`fontColor()`、`fontFamily()`、`fontSize()`をJSPで使用できるようになりました。

このサンプルプロジェクトは、`ConfigurationScreen`を使用してカスタムJSPで構成値を読み取って表示する方法の基本的な例を示しています。 アプリケーションで、独自のコードを記述し、ニーズに合わせて完全にカスタムの構成UIを作成します。
