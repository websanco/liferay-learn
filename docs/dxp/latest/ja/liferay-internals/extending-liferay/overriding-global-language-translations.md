# グローバル言語キーのオーバーライド

Liferay DXP/Portalでは、言語キーを使用して、デフォルトロケールと他の多くのロケールの見出し、ラベル、およびメッセージを実装します。 モジュール内の新しい言語キー値を使用して、任意のロケールのこれらのキーをオーバーライドできます。

<a name="グローバル言語キーを調べる" />

## グローバル言語キーを調べる

グローバル言語キーは、ソースコードと[DXP/Portalバンドル](../../installation-and-upgrades/installing-liferay/hosting-liferay.md)に含まれています。

ソースの場合：

* [`liferay-[dxp|portal]/portal-impl/src/content/Language [_xx_XX].properties`](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-impl/src/content)
* [`liferay-[dxp|portal]/modules/apps/portal-language/portal-language-lang/src/main/resources/content/Language [_xx_XX].properties`](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/portal-language/portal-language-lang/src/main/resources/content)

バンドルの場合：

* `portal-impl.jar#content/Language[_xx_XX].properties`
* `Liferay Foundation - Liferay Portal Language - Impl.lpkg` &rarr; `com.liferay.portal.language.lang-[version].jar#content/Language[_xx_XX].properties`

さまざまな言語とロケールの言語キーは、ファイル名の末尾で識別できます。 たとえば、`Language_ja.properties`は日本語用です。

これらの言語キーファイルには、言語設定プロパティなど、オーバーライドできるプロパティが含まれています。

```properties
...
lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
lang.user.name.required.field.names=last-name
lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr
...
```

メッセージやラベル用にオーバーライドできる単純なキーもたくさんあります。

```properties
category.admin=Admin
category.alfresco=Alfresco
category.christianity=Christianity
category.cms=Content Management
...
```

Liferay DXP/Portal 7.4以降では、メタデータを使用してオーバーライドを宣言できます。 以前のバージョンでは、Javaクラスがオーバーライドを宣言します。

お使いのバージョンが7.4より前の場合は、 [以前のバージョンでのオーバーライド](#overriding-in-earlier-versions) に進んでください。  それ以外の場合は、読み進めてください。

<a name="サンプルをデプロイする74以降の場合" />

## サンプルをデプロイする（7.4以降の場合）

この例では、`home`言語キー設定を次のように変更します。

```{literalinclude} ./overriding-global-language-keys/resources/liferay-i2f4.zip/i2f4-impl/src/main/resources/content/Language_en_US.properties
:language: properties
```

サンプルをデプロイする方法は次のとおりです。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [最新のサンプル](./liferay-i2f4.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/liferay-internals/extending-liferay/liferay-i2f4.zip -O
    ```

    ```bash
    unzip liferay-i2f4.zip
    ```

1. プロジェクトモジュールをビルドしてデプロイします。

    ```bash
    cd liferay-i2f4
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、デプロイされたjarをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.i2f4.impl_1.0.0 [3209]
    ```

1. サンプルモジュールのカスタマイゼーションを確認します。 ブラウザで`https://localhost:8080`を開きます。

1. Click the menu icon (![Menu](../../images/icon-menu.png)). ホームアイコンラベルには、カスタム言語のキー値が使用されています。

    ![ホームアイコンにカスタム言語のキー値が使用されるようになりました。](./overriding-global-language-keys/images/01.png)

1. この例には、複数のロケールのカスタム言語キー値が含まれています。 たとえば、言語セレクターでブラジルポルトガル語または日本語を選択すると、そのロケールでのカスタマイゼーションが表示されます。 モジュールは、これらのロケールの言語キーもオーバーライドします。

    ![カスタム言語キーは、ブラジルポルトガル語と日本語にも使用されます。](./overriding-global-language-keys/images/02.png)

例を見たところで、次にこれがどのように機能するかを確認していきます。

<a name="言語プロパティファイルを作成する" />

## 言語プロパティファイルを作成する

オーバーライドするキーを選択します。 サンプルモジュールは、`home`言語キーをオーバーライドします。

```{literalinclude} ./overriding-global-language-keys/resources/liferay-i2f4.zip/i2f4-impl/src/main/resources/content/Language_en_US.properties
:language: properties
```

```{important}
宣言する言語キーの値は、それらの既存のキーの値をオーバーライドします。 他のすべての既存の言語キー設定は保持されます。
```

オーバーライドするキーを決定したら、モジュールの`src/main/resources/content`フォルダに言語プロパティファイルを作成します。  ファイル名`Language.properties`を使用して、デフォルトのロケールの言語キーをオーバーライドします。 特定のロケールのキーをオーバーライドするには、言語プロパティのファイル命名規則を使用します。

```
Language[_xx_XX].properties
```

たとえば、日本語をオーバーライドする場合は、`Language_ja.properties`を使用します。

<a name="bndファイルでオーバーライドを宣言する" />

## Bndファイルでオーバーライドを宣言する

モジュールの`bnd.bnd`ファイルで、言語リソースプロバイダーの機能を指定します。 サンプルの`Provide-Capability`ヘッダーは次のとおりです。

```{literalinclude} ./overriding-global-language-keys/resources/liferay-i2f4.zip/i2f4-impl/bnd.bnd
:lines: 4-6
```

```{note}
この例では、サービスのランキングを省略しており、OSGiのデフォルトのランキング `0`を使用しています。これは、デフォルトのグローバルリソースバンドルサービスのランキング` -1`よりも高くなっています。
```

グローバル言語キーのオーバーライドは、同じモジュール内にあるほうが管理が簡単です。

複数のモジュールを使用してグローバル言語キーをオーバーライドすることはお勧めしませんが、複数のモジュールで同じキーをオーバーライドすると、サービスランキングが最も高い言語リソースプロバイダーが優先されます。

たとえば、モジュールの言語キーがサービスランキング`1`のプロバイダーのキーよりも優先されるようにする場合は、ランキングを`2`以上に設定します。

```properties
Provide-Capability:\
    liferay.language.resources;\
        resource.bundle.base.name="content.Language";\
        service.ranking:Long="2"
```

モジュールをデプロイして、新しい言語キー値を確認します。

<a name="以前のバージョンでのオーバーライド" />

## 以前のバージョンでのオーバーライド

7.4より前のLiferay DXP/Portalバージョンでは、グローバル言語キーをオーバーライドするには、カスタマイズする翻訳ごとに [言語プロパティファイル](#create-a-language-properties-file) と`java.util.ResourceBundle`が必要です。 次の例をデプロイしてそのコードを調べることにより、詳細を確認してください。

```{note}
言語キーの多くはグローバル言語キーファイルにありますが、特定のアプリケーションモジュールにある場合もあります。 [以前のバージョンでモジュール言語キーをオーバーライドする](./overriding-module-language-keys.md)プロセスは、グローバルキーをオーバーライドするプロセスとは異なります。
```

### 以前のバージョンの例をデプロイする

この例では、`publish`言語キー設定を次のように変更します。

```{literalinclude} ./overriding-global-language-keys/resources/liferay-x8f3.zip/x8f3-impl/src/main/resources/content/Language_en_US.properties
:language: properties
```

サンプルをデプロイする方法は次のとおりです。

1. [グローバル言語キーのオーバーライド](./liferay-x8f3.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/liferay-internals/extending-liferay/liferay-x8f3.zip -O
    ```

    ```bash
    unzip liferay-x8f3.zip
    ```

1. プロジェクトモジュールをビルドしてデプロイします。

    ```bash
    cd liferay-x8f3
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.x8f3.impl_1.0.0 [3209]
    ```

1. Navigate to a Site page and click the edit icon (![Edit](../../images/icon-edit.png)). 公開ボタンには、カスタム言語キーが表示されます。

    ![公開ボタンにカスタム言語キーが使用されるようになりました。](./overriding-global-language-keys/images/03.png)

1. 言語セレクターを使用してブラジルポルトガル語または日本語を選択すると、カスタム言語キーが表示されます。 モジュールは、モジュールに含める各ロケールの言語キーをオーバーライドします。

    ![カスタム言語キーは、ブラジルポルトガル語と日本語にも使用されます。](./overriding-global-language-keys/images/04.png)

7.4以降の例と同様に、このモジュールは言語キーファイルのカスタム値を指定します。 ただし、メタデータ（`bnd.bnd`ファイルヘッダー）を使用してオーバーライドを宣言する代わりに、モジュールは`ResourceBundle`クラスを使用します。

### リソースバンドルクラスを作成する

オーバーライドする各ロケールには、`java.util.ResourceBundle`を拡張するクラスが必要です。 `en_US`ロケールのリソースバンドルクラスの例を次に示します。

```{literalinclude} ./overriding-global-language-keys/resources/liferay-x8f3.zip/x8f3-impl/src/main/java/com/acme/x8f3/internal/language/X8F3EnglishResourceBundle.java
:language: java
:lines: 10-26
```

クラスの`_resourceBundle`フィールドには`ResourceBundle`が割り当てられます。 `ResourceBundle.getBundle`の呼び出しには、2つのパラメーターが必要です。  `content.Language_en_US`パラメーターは、モジュールの`src/main/resources/content`フォルダに対する言語ファイルの修飾名です。 2番目のパラメーターは、リソースバンドルの言語構文を設定する`control`です。 Liferayの構文と同じ言語構文を使用するには、Liferayの`com.liferay.portal.kernel.language.UTF8Control`クラスをインポートし、2番目のパラメーターを`UTF8Control.INSTANCE`に設定します。

クラスの`@Component`アノテーションは、それをOSGi `ResourceBundle` サービスコンポーネントとして宣言します。 その`language.id`プロパティは、`en_US`ロケール用にそれを指定します。

```{literalinclude} ./overriding-global-language-keys/resources/liferay-x8f3.zip/x8f3-impl/src/main/java/com/acme/x8f3/internal/language/X8F3EnglishResourceBundle.java
:language: java
:lines: 10
```

クラスは次のメソッドをオーバーライドします。

**`handleGetObject`：** モジュールのリソースバンドル（モジュールの言語プロパティファイルに基づく）でキーを検索し、キーの値を`Object`として返します。

**`getKeys`：** リソースバンドルのキーの`Enumeration`を返します。

リソースバンドルサービスコンポーネントは、デフォルトの言語キーをモジュールの言語キーオーバーライドにリダイレクトします。

複数のロケールのグローバル言語キーをオーバーライドするには、ロケールごとに個別のリソースバンドルクラスが必要です。 たとえば、チュートリアルコードには、ブラジルポルトガル語、英語、および日本語用のクラスがあります。 各リソースバンドルは、`language.id`コンポーネントのプロパティ定義と言語ファイルの修飾名パラメーターでロケールを指定する必要があります。  たとえば、日本語ロケールでは次のようになります。

コンポーネント定義：

```{literalinclude} ./overriding-global-language-keys/resources/liferay-x8f3.zip/x8f3-impl/src/main/java/com/acme/x8f3/internal/language/X8F3JapaneseResourceBundle.java
:language: java
:lines: 10
```

リソースバンドルの割り当て：

```{literalinclude} ./overriding-global-language-keys/resources/liferay-x8f3.zip/x8f3-impl/src/main/java/com/acme/x8f3/internal/language/X8F3JapaneseResourceBundle.java
:dedent: 1
:language: java
:lines: 23-24
```

モジュールをデプロイして、新しい言語キー値を確認します。

```{note}
DXP 7.4以降にアップグレードする準備ができたら、言語キーオーバーライドモジュールを引き続き使用できます。 オプションとして、 [above](#declare-the-override-in-the-bnd-file) で示すように、`ResourceBundle` クラスを削除し、`Provide-Capability` ヘッダーを `bnd.bnd` ファイルで指定することで、モジュールを簡素化することができます。
```

<a name="関連情報" />

## 関連情報

* [以前のバージョンでのモジュール言語キーのオーバーライドをする](./overriding-module-language-keys.md)