# 以前のバージョンでのモジュール言語キーのオーバーライドをする

```{important}
Liferay DXP/Portal 7.4以降を使用している場合は、[Overriding Global Language Keys](./overriding-global-language-keys.md)の手順に従ってください。
```

以前のバージョンでLiferayアプリケーション固有の言語キーをオーバーライドすることは、以前のバージョンでのグローバル言語キーのオーバーライドと似ていますが、追加の手順があります。

<a name="モジュール言語キーを調べる" />

## モジュール言語キーを調べる

モジュールの言語キーを上書きするには、最初に[Gogoシェル](../fundamentals/using-the-gogo-shell.md)でモジュールに関する情報を収集する必要があります。 たとえば、ブログモジュールの言語キーをオーバーライドする場合は、`grep`でキーワード「blogs」を検索します。 Gogoコマンドと出力は次のようになります。

 ```
 g! lb | grep Blogs
 Output
 418|Active     |   10|Liferay Collaboration - Liferay Blogs - API (1.0.0)|1.0.0
 419|Active     |   10|Liferay Blogs API (6.4.5)|6.4.5
 420|Active     |   10|Liferay Blogs Item Selector API (4.0.5)|4.0.5
 421|Active     |   10|Liferay Blogs Recent Bloggers API (4.0.5)|4.0.5
 570|Active     |   10|Liferay Adaptive Media Blogs Editor Configuration (4.0.5)|4.0.5
 571|Active     |   10|Liferay Adaptive Media Blogs Item Selector Web (4.0.5)|4.0.5
 572|Active     |   10|Liferay Adaptive Media Blogs Web (4.0.9)|4.0.9
 573|Resolved   |   10|Liferay Adaptive Media Blogs Web Fragment (4.0.6)|4.0.6
 671|Active     |   15|Liferay Sharing Blogs (2.0.6)|2.0.6
 1126|Active     |   10|Liferay Collaboration - Liferay Blogs - Impl (1.0.0)|1.0.0
 1127|Active     |   10|Liferay Blogs Editor Configuration (4.0.8)|4.0.8
 1128|Active     |   15|Liferay Blogs Item Selector Web (5.0.9)|5.0.9
 1129|Active     |   10|Liferay Blogs Layout Prototype (5.0.8)|5.0.8
 1130|Active     |   10|Liferay Blogs Reading Time (3.0.11)|3.0.11
 1131|Active     |   15|Liferay Blogs Recent Bloggers Web (5.0.11)|5.0.11
 1132|Active     |   10|Liferay Blogs Service (4.0.24)|4.0.24
 1133|Active     |   10|Liferay Blogs UAD (5.0.6)|5.0.6
 1134|Active     |   15|Liferay Blogs Web (5.0.36)|5.0.36
 true
 ```

モジュールのID番号をメモします。 バンドルのヘッダーのリストを取得するには、`headers`コマンドを使用します。 この場合は、Liferay Blogs Webモジュールの1134です。

 ```
 g! headers 1134
 Output
 Bundle headers:
 Bnd-LastModified = 1601503219290
 Bundle-ManifestVersion = 2
 Bundle-Name = Liferay Blogs Web
 Bundle-SymbolicName = com.liferay.blogs.web
 Bundle-Vendor = Liferay, Inc.
 Bundle-Version = 5.0.36
 ...
 Web-ContextPath = /blogs-web
 ```

`Bundle-SymbolicName`、`Bundle-Version`、および`Web-ContextPath`に注目してください。 `/`に続く`Web-ContextPath`値は、モジュールのコンテキスト名です。

バンドルのシンボリック名またはコンテキスト名を使用して、モジュールに固有の言語キーを検索します。 モジュールのJARファイルを見つけて、その言語キーを調べます。 Liferayは、このモジュールのJARファイルの命名規則に従います。

```
[bundle symbolic name]-[version].jar
```

たとえば、Blogs Webバージョン5.0.36モジュールは`com.liferay.blogs.web-5.0.36.jar`にあります。

モジュールの場所は次のとおりです。

* Liferayの [Nexusリポジトリ](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/)
* `[Liferay Home]/osgi/modules`
* [`liferay- [dxp|portal]/modules/apps`](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps) にあるソースコード

言語プロパティファイルは、モジュールの`src/main/resources/content`フォルダにあります `Language[xx_XX].properties`ファイルでオーバーライドする言語キーを特定します。

さまざまな言語とロケールの言語キーは、ファイル名の末尾で識別できます。 たとえば、`Language_ja.properties`は日本語用です。

この例では、デフォルトの`Add Blog Entry`言語キーをカスタムキーに変更します。 今度はそれをデプロイします。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. `liferay-e6u7.zip`をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/liferay-internals/extending-liferay/liferay-e6u7.zip -O
    ```

    ```bash
    unzip liferay-e6u7.zip
    ```

1. モジュールのルートから、ビルドおよびデプロイします。

    ```bash
    cd liferay-e6u7
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、デプロイされたjarをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.e6u7.impl_1.0.0 [1650]
    ```

1. ［**Content & Data**］ &rarr; ［**Blogs**］ に移動します。 カーソルを追加アイコン（![Add](../../images/icon-add.png)）に合わせます。 メッセージにカスタム言語キーが表示されます。

    ![カスタム言語キーが使用されるようになりました。](./overriding-module-language-keys/images/01.png)

1. チュートリアルコードには、他のロケールの例も含まれています。 たとえば、言語セレクターを使用してブラジルポルトガル語または日本語を選択すると、カスタム言語キーが表示されます。 モジュールは、モジュールに含める各ロケールの言語キーをオーバーライドします。

    ![カスタム言語キーは、ポルトガル語と日本語にも使用されます。](./overriding-module-language-keys/images/02.png)

次に、コードがどのように機能するかを確認します。

<a name="言語プロパティファイルを作成する" />

## 言語プロパティファイルを作成する

まず、オーバーライドするキーを選択します。 たとえば、このチュートリアルコードは、`Add Blog Entry`言語キーをオーバーライドします。

オーバーライドするキーを決定したら、モジュールの`src/main/resources/content`フォルダに言語プロパティファイルを作成します。  ファイルでキーを定義します。 ファイル名がオーバーライドするロケールと一致していることを確認してください。 たとえば、日本語の場合は、`Language_ja.properties`を使用します。

```{literalinclude} ./overriding-module-language-keys/resources/liferay-e6u7.zip/e6u7-impl/src/main/resources/content/Language_ja.properties
:language: properties
```

<a name="言語リソースバンドルを作成する" />

## 言語リソースバンドルを作成する

モジュールで、オーバーライドするロケールの`java.util.ResourceBundle`を拡張するクラスを作成します。 `en_US`ロケールのリソースバンドルクラスの例を次に示します。

```{literalinclude} ./overriding-module-language-keys/resources/liferay-e6u7.zip/e6u7-impl/src/main/java/com/acme/e6u7/internal/language/E6U7EnglishResourceBundle.java
:language: java
:lines: 10-26
```

クラスの`_resourceBundle`フィールドには`ResourceBundle`が割り当てられます。 `ResourceBundle.getBundle`の呼び出しには、2つのパラメーターが必要です。  `content.Language_en_US`パラメーターは、モジュールの`src/main/resources/content`フォルダに対する言語ファイルの修飾名です。 2番目のパラメーターは、リソースバンドルの言語構文を設定する`control`です。 Liferayの構文と同じ言語構文を使用するには、Liferayの`com.liferay.portal.kernel.language.UTF8Control`クラスをインポートし、2番目のパラメーターを`UTF8Control.INSTANCE`に設定します。

クラスの`@Component`アノテーションは、それをOSGi `ResourceBundle` サービスコンポーネントとして宣言します。 その`language.id`プロパティは、`en_US`ロケール用にそれを指定します。

```{literalinclude} ./overriding-module-language-keys/resources/liferay-e6u7.zip/e6u7-impl/src/main/java/com/acme/e6u7/internal/language/E6U7EnglishResourceBundle.java
:language: java
:lines: 10
```

クラスは次のメソッドをオーバーライドします。

**`handleGetObject`：** モジュールのリソースバンドル（モジュールの言語プロパティファイルに基づく）でキーを検索し、キーの値を`Object`として返します。

**`getKeys`：** リソースバンドルのキーの`Enumeration`を返します。

リソースバンドルサービスコンポーネントは、デフォルトの言語キーをモジュールの言語キーオーバーライドにリダイレクトします。

**注：** 複数のロケールのモジュール言語キーをオーバーライドするには、ロケールごとに個別のリソースバンドルクラスが必要です。 たとえば、このチュートリアルコードには、英語、日本語、ポルトガル語用のクラスがあります。 各リソースバンドルは、`language.id`コンポーネントのプロパティ定義と言語ファイルの修飾名パラメーターでロケールを指定する必要があります。  たとえば、日本語ロケールでは次のようになります。

コンポーネント定義：

```{literalinclude} ./overriding-module-language-keys/resources/liferay-e6u7.zip/e6u7-impl/src/main/java/com/acme/e6u7/internal/language/E6U7JapaneseResourceBundle.java
:language: java
:lines: 10
```

リソースバンドルの割り当て：

```{literalinclude} ./overriding-module-language-keys/resources/liferay-e6u7.zip/e6u7-impl/src/main/java/com/acme/e6u7/internal/language/E6U7JapaneseResourceBundle.java
:dedent: 1
:language: java
:lines: 23-24
```

<a name="モジュールのリソースバンドルに優先順位を付ける" />

## モジュールのリソースバンドルに優先順位を付ける

ターゲットモジュールがカスタム言語キーを使用するには、OSGIマニフェストヘッダーでリソースバンドルを指定する必要があります。 目的のモジュールを最初に一覧表示することで、ターゲットモジュールのリソースバンドルよりもそのリソースバンドルを優先させます。 これにより、2つのリソースが集約されます。 チュートリアルモジュール`com.acme.e6u7.impl`の例を次に示します。この例では、ターゲットモジュール`com.liferay.blogs.web`のリソースバンドルよりもリソースバンドルを優先しています。

```{literalinclude} ./overriding-module-language-keys/resources/liferay-e6u7.zip/e6u7-impl/bnd.bnd
:language: properties
:lines: 4-12
```

サンプルの`Provide-Capability`ヘッダーには、次の2つの部分があります。

1.  `liferay.resource.bundle;resource.bundle.base.name="content.Language"`は、モジュールがベース名`content.Language`のリソースバンドルを提供することを宣言します。

1. `liferay.resource.bundle;resource.bundle.aggregate:String=...`ディレクティブは、集約するリソースバンドルを含むバンドルのリスト、ターゲットバンドル、ターゲットバンドルのリソースバンドル名、およびこのサービスのランキングを指定します。

    * `"(bundle.symbolic.name=com.acme.e6u7.impl)、(bundle.symbolic.name=com.liferay.blogs.web)"`：このサービスは、バンドル`com.acme.e6u7.impl`および`com.liferay.blogs.web`からリソースバンドルを集約します。 必要な数のバンドルを集約します。 リストされたバンドルは降順で優先されます。
    * `bundle.symbolic.name=com.liferay.blogs.web;resource.bundle.base.name="content.Language"`：`content.Language`という名前の`com.liferay.blogs.web`バンドルのリソースバンドルをオーバーライドします。
    * `service.ranking:Long="2"`：リソースバンドルのサービスランキングは`2`です。 OSGiフレームワークは、このサービスが`com.liferay.blogs.web`の`content.Language`リソースバンドルを対象とする他のすべてのリソースバンドルサービスを上回る場合、このサービスを適用します。
    * `servlet.context.name=blogs-web`：ターゲットリソースバンドルはサーブレットコンテキスト`blogs-web`にあります。

```{note}
オーバーライドが表示されない場合は、[Gogoシェル](../fundamentals/using-the-gogo-shell.md)を使用して、競合するリソースバンドルサービスを確認してください。 別のサービスのランクのほうが高い可能性があります。 たとえば、`com.liferay.blogs.web`のリソースバンドルが集約されている競合するリソースバンドルサービスを確認するには、以下のGogoシェルコマンドを実行します。

`services "(bundle.symbolic.name=com.liferay.blogs.web)"`
```

```{note}
言語キー名が同じ場合は、DXP7.4以降で言語キーオーバーライドを引き続き使用できます---  [`/modules/apps/portal-language/portal-language-lang/src/main/resources/content/Language [_xx_XX].properties`](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-language/portal-language-lang/src/main/resources/content) ファイルを確認してください。 オプションとして、`ResourceBundle`クラスを削除し、`bnd.bnd`ファイルの`Provide-Capability`ヘッダーを [Overriding Global Language Keys](./overriding-global-language-keys.md#declare-the-oOverride-in-the-bnd-file) で示されているヘッダーに置き換えることで、モジュールを簡素化することができます。
```

結果を検索して、ランキングが高いリソースバンドル集約サービスを探します。

<a name="関連情報" />

## 関連情報

* [グローバル言語キーのオーバーライド](./overriding-global-language-keys.md)