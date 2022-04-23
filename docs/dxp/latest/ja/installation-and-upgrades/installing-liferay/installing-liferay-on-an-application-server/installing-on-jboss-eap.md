# JBoss EAPへのインストール

JBoss EAPにインストールするには、DXP WARのインストール、依存関係のインストール、JBossの設定、およびJBossへのDXPのデプロイが必要です。 データベースとメールサーバーの接続も設定する必要があります。

## 前提条件

Liferay DXPにはJava JDK 8または11が必要です。 詳細は、[互換性マトリクス](https://help.liferay.com/hc/en-us/articles/360049238151)を参照してください。

## 前提条件

Liferay DXPにはJava JDK 8または11が必要です。 詳細は、 [互換性マトリクス](https://help.liferay.com/hc/ja/articles/360049238151) を参照してください。

これらのファイルを [ヘルプセンター](https://customer.liferay.com/downloads) （サブスクリプション）または [Liferayコミュニティダウンロード](https://www.liferay.com/downloads-community) からダウンロードします。

* DXP WARファイル
* OSGi依存関係のZIPファイル
* 依存関係のZIPファイル（DXP 7.3以前）

インストール手順では、これらの用語を使用しています。

[`[Liferay Home]`](../../reference/liferay-home.md)：JBossサーバーフォルダを含むフォルダ（ `$JBOSS_HOME`と表記されています。） DXPをインストールしてデプロイした後、`data`、`deploy`、および`logs`フォルダを生成します。

`$JBOSS_HOME`：JBossサーバーフォルダー。 通常、`jboss-eap-[version]`という名前です。

## DXP WARのインストール

1. クリーンなJBossインストールを開始していて、`$JBOSS_HOME/standalone/deployments/ROOT.war`フォルダが存在する場合は、そのすべてのサブフォルダとファイルを削除します。
1. DXP WARファイルを`$JBOSS_HOME/standalone/deployments/ROOT.war`フォルダに解凍します（このフォルダが存在しない場合は作成します）。

## 依存関係のインストール

1. OSGi Dependencies ZIPファイルを `［Liferay Home]/osgi` フォルダーに解凍します（このフォルダーが存在しない場合は作成します）。 LiferayのOSGiランタイムは、これらのモジュールに依存しています。
1. DXP 7.4+ WARファイルには、MariaDBおよびPostgreSQLのドライバーが含まれています。 以前のWARにはそれらがありません。 7.4以降のWARに、使用中のサポートされているデータベースのドライバーがない場合は、データベースベンダーのJDBC JARファイルをダウンロードして、`$JBOSS_HOME/standalone/deployments/ROOT.war/WEB-INF/shielded-container-lib`フォルダーに配置します。

    サポートされているデータベースの一覧については、 [互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360049238151) を参照してください。

```{note}
DXPには、テスト目的に役立つHypersonicデータベースが含まれています。 本番環境インスタンスにはHSQLを**使用しないでください**。
```

### 以前のバージョンの依存関係をインストールする

DXP 7.3以前の場合は、次の追加手順に従います。

1. 依存関係のZIPファイルを`$JBOSS_HOME/modules/com/liferay/portal/main`フォルダに解凍します（このフォルダが存在しない場合は作成します）。
1. `$JBOSS_HOME/modules/com/liferay/portal/main`フォルダに`module.xml`というファイルを作成します。 ファイルで、ポータルモジュールとそれに必要なすべてのリソースと依存関係を宣言します。

    ```xml
    <?xml version="1.0"?>

    <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
        <resources>
            <resource-root path="[place your database vendor's JAR file name here]" />
            <resource-root path="[place a Liferay dependencies ZIP JAR file name here]" />
            <!-- Add a resource-root element for each Liferay dependencies ZIP JAR -->
        </resources>
        <dependencies>
            <module name="javax.api" />
            <module name="javax.mail.api" />
            <module name="javax.servlet.api" />
            <module name="javax.servlet.jsp.api" />
            <module name="javax.transaction.api" />
        </dependencies>
    </module>
    ```

    `[place your database vendor's JAR file name here]`をデータベースのドライバーJARに置き換えます。

    Liferay依存関係ZIPのJARごとに、`path`属性がJAR名に設定された`resource-root`要素を追加します。 たとえば、`com.liferay.petra.concurrent.jar`ファイルに次のような`resource-root`要素を追加します。

    ```xml
    <resource-root path="com.liferay.petra.concurrent.jar" />
    ```

## スタンドアロンモードとドメインモードのJBoss EAPでのDXPの実行

JBoss EAPは、 *スタンドアロン* モードまたは *ドメイン* モードのいずれかで起動できます。 ドメインモードでは、単一のコントロールポイントから複数のアプリケーションサーバーインスタンスを管理できます。 このようなアプリケーションサーバーのコレクションは、 *ドメイン*と呼ばれます。 スタンドアロンモードとドメインモードの詳細については、 [JBoss EAP Product Documentation](https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.1/html/introduction_to_jboss_eap/overview_of_jboss_eap#operating_modes)のこのトピックに関するセクションを参照してください。

DXPは、スタンドアロンモードで実行する場合はJBoss EAPをサポートしますが、ドメインモードで実行する場合はサポートしません。 JBossはファイル（展開または非展開）をコピーして管理対象デプロイメントのコンテンツを管理するため、DXPの自動展開は管理対象デプロイメントでは機能しません。 これにより、JSPフックとExtプラグインが意図したとおりに機能しなくなります。 たとえば、DXPのJSPオーバーライドメカニズムはアプリケーションサーバーに依存しているため、JSPフックは管理対象ドメインモードで実行されているJBoss EAPでは機能しません。 ただし、JSPフックとExtプラグインは非推奨であるため、使用していない可能性があります。

ドメインモードの展開を使用する場合は、コマンドラインインターフェースを使用します。

```{note}
これにより、DXPが複数のJBossサーバー上のクラスター環境で実行されるのを防ぐことはできません。 スタンドアロンモードで実行されているJBoss EAPサーバーで実行されているDXPインスタンスのクラスターを設定できます。 詳細については、[クラスタリングの記事](../../setting-up-liferay/clustering-for-high-availability.md) を参照してください。
```

<a name="configuring-jboss" />

## JBossの構成

JBossの構成手順は次のとおりです。

* 環境変数を設定する
* プロパティと記述子を指定する
* 不要な構成を削除する

`$JBOSS_HOME/standalone/configuration/standalone.xml`に次の変更を加えます。

1. JSPにJava 8 VM互換を使用するようにサーブレットコンテナを設定します。 デフォルトサーブレットコンテナを`<servlet-container name="default">``<subsystem xmlns="urn:jboss:domain:undertow:12.0" ...`要素で探し出します。 サーブレットコンテナの `<jsp-config>` 要素に、 `development`、`source-vm`、`target-vm` 属性を以下のように設定します。

    ```xml
    <jsp-config development="true" source-vm="1.8" target-vm="1.8" />
    ```

1. `</extensions>` の終了タグを見つけます。 その終了タグの直下に、まだ存在していなければ、以下のシステムプロパティを挿入します。

    ```xml
    <system-properties>
        <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
        <property name="org.apache.catalina.connector.USE **BODY** ENCODING **FOR** QUERY_STRING" value="true" />
    </system-properties>
    ```

1. ログから `WFLYSRV0059` と `WFLYEE0007` のメッセージを除外します。 `<subsystem xmlns="urn:jboss:domain:logging:8.0">` 要素の `<console-handler>` タグの中に、`<level name="INFO"/>`タグの直下に次の`<filter-spec>`タグを追加します。

    ```xml
    <filter-spec value="not(any(match(&quot;WFLYSRV0059&quot;),match(&quot;WFLYEE0007&quot;)))" />
    ```

1. `deployment-timeout="600"` の設定を、 `<subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">` 要素内の `<deployment-scanner>` タグに追加することで、デプロイメントスキャナーのタイムアウトを追加しています。 例えば、

    ```xml
    <deployment-scanner deployment-timeout="600" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000" runtime-failure-causes-rollback="${jboss.deployment.scanner.rollback.on.failure:false}"/>
    ```

1. LiferayのJAASセキュリティドメインを `<subsystem xmlns="urn:jboss:domain:security:2.0">`の`<security-domains>` 要素に追加します。 追加するドメインコードは以下の通りです。

    ```xml
    <security-domain name="PortalRealm">
        <authentication>
            <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
        </authentication>
    </security-domain>
    ```

1. `<subsystem xmlns="urn:jboss:domain:undertow:12.0" ...>` 要素からウェルカムコンテンツ要素をコメントアウトします。 例えば、

    ```xml
    <!--<location name="/" handler="welcome-content"/>-->
    ```

    そして

    ```xml
    <handlers>
        <!--<file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>-->
    </handlers>
    ```

**チェックポイント:**

続行する前に、次のプロパティが `standalone.xml` ファイルに設定されていることを確認してください。

1. 新しい `<system-property>` が追加されていること。
1. 新しい `<filter-spec>` が追加されていること。
1. `<deployment-timeout>`が`600`に設定されていること。
1. 新しい `<security-domain>` が作成されていること。
1. ウェルカムコンテンツが無効になっていること。

次に、JVMと起動スクリプトを構成します。

`$JBOSS_HOME/ bin /` フォルダーで、スタンドアロンドメインの構成スクリプトファイル `standalone.conf` を変更します。

* ファイルのエンコーディングを `UTF-8`に設定します
* ユーザーのタイムゾーンを `GMT`に設定します
* 優先プロトコルスタックを設定します
* 利用可能なデフォルトのメモリ容量を増やします。

```{important}
DXPでは、アプリケーションサーバーのJVMが `GMT`タイムゾーンと` UTF-8`ファイルエンコーディングを使用する必要があります。
```

`standalone.conf`スクリプトを次のように編集します。

1. `if [ "x$JAVA_OPTS" = "x" ];` ステートメントの下で、 `JAVA_OPTS` の割り当てからJVMサイジングオプションを削除します。 たとえば、以下のものを

    ```bash
    JAVA_OPTS="-Xms1303m -Xmx1303m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2560m -Djava.net.preferIPv4Stack=true"
    ```

    以下のものと置き換えます。

    ```bash
    JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
    ```

1. 次のJavaオプションの設定をファイルの最後に追加します。

    ```bash
    JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
    ```

Javaオプションとメモリ引数について以下に説明します。

**JVMオプションの説明**

| オプション                                     | 説明                                                                                                   |
|:----------------------------------------- |:---------------------------------------------------------------------------------------------------- |
| `-Dfile.encoding=UTF-8`                   | DXPにはUTF-8ファイルエンコーディングが必要です。                                                                         |
| `-Djava.locale.providers=JRE,COMPAT,CLDR` | これは、JDK 11で4桁の日付を表示するために必要です。                                                                        |
| `-Djava.net.preferIPv4Stack=true`         | IPv6よりもIPv4スタックを優先します。                                                                               |
| `-Dlog4j2.formatMsgNoLookups=true`        | リモートコード実行（RCE）の脆弱性を解決します。 詳細は、 [LPS-143663](https://issues.liferay.com/browse/LPS-143663) を参照してください。 |
| `-Duser.timezone=GMT`                     | DXPでは、アプリケーションサーバーのJVMがGMTタイムゾーンを使用する必要があります。                                                        |

**メモリ引数の説明**

| メモリ引数                  | 説明                                                                     |
|:---------------------- |:---------------------------------------------------------------------- |
| `-Xms`                 | ヒープの初期スペース。                                                            |
| `-Xmx`                 | ヒープの最大スペース。                                                            |
| `-XX:NewSize`          | 最初の新しいスペース。 通常、新しいサイズをヒープ全体の半分に設定すると、より小さな新しいサイズを使用するよりもパフォーマンスが向上します。 |
| `-XX:MaxNewSize`       | 最大の新しいスペース。                                                            |
| `-XX:MetaspaceSize`    | 静的コンテンツ用の初期スペース。                                                       |
| `-XX:MaxMetaspaceSize` | 静的コンテンツ用の最大スペース。                                                       |
| `-XX:SurvivorRatio`    | 新しいスペースとSurvivor領域の比率。 Survivor領域は、古い世代の領域に昇格する前に、若い世代のオブジェクトを保持します。   |

```{note}
DXPのインストール後、これらの構成（これらのJVMオプションを含む）をさらに調整して、パフォーマンスを向上させることができます。 詳細については、[Tuning Liferay](../../setting-up-liferay/tuning-liferay.md)および[Tuning Your JVM](../../setting-up-liferay/tuning-your-jvm.md)を参照してください。
```

### IBM JDKの使用

JBossサーバーでIBM JDKを使用する場合は、以下の追加手順を実行します。

1. `$JBOSS_HOME/modules/com/liferay/portal/main/module.xml` ファイルに移動し、この依存関係を `<dependencies>` 要素の中に挿入します。

    `<module name="ibm.jdk" />`

1. `$JBOSS_HOME/modules/system/layers/base/sun/jdk/main/module.xml` ファイルに移動し、以下のパスを `&lt;paths&gt;... の中に挿入します。
<pre><code class="xml">    <path name="com/sun/crypto" />
    <path name="com/sun/crypto/provider" />
    <path name="com/sun/image/codec/jpeg" />
    <path name="com/sun/org/apache/xml/internal/resolver" />
    <path name="com/sun/org/apache/xml/internal/resolver/tools" />
`</pre>

追加されたパスは、ポータルのデプロイメントの例外と画像のアップロードの問題を解決します。

## データベースに接続する

データベース構成を処理する最も簡単な方法は、DXPにデータソースを管理させることです。 [セットアップウィザード](../running-liferay-for-the-first-time.md)を使用して、DXPの組み込みデータソースを構成できます。 組み込みのデータソースを使用する場合は、このセクションをスキップしてください。

JBossを使用してデータソースを管理する場合は、次の手順に従います。

1. DXP WAR（7.4以降）またはデータベースベンダーからJDBC JARを取得し、`$JBOSS_HOME/modules/com/liferay/portal/main`フォルダにコピーします。

1. `$JBOSS_HOME/standalone/configuration/standalone.xml` ファイルの `<datasources>` 要素の中にデータソースを追加します。

    ```xml
    <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
        <connection-url>[place the URL to your database here]</connection-url>
        <driver>[place the driver name here]</driver>
        <security>
            <user-name>[place your user name here]</user-name>
            <password>[place your password here]</password>
        </security>
    </datasource>
    ```

    データベースのURL、ユーザー名、パスワードを適切な値に置き換えてください。

    ```{note}
    データソース`jndi-name`を変更する必要がある場合は、 `<default-bindings>` タグ内の`datasource`要素を編集してください。
    ```

1. `<datasources>` 要素内にもある `standalone.xml` ファイルの `<drivers>` 要素にドライバーを追加します。

    ```xml
    <drivers>
        <driver name="[name of driver must match name above]" module="com.liferay.portal">
            <driver-class>[place your JDBC driver class here]</driver-class>
        </driver>
    </drivers>
    ```

    MySQLを使用する最終的なデータソースサブシステムは次のようになります。

    ```xml
    <subsystem xmlns="urn:jboss:domain:datasources:5.0">
        <datasources>
            <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
                <connection-url>jdbc:mysql://localhost/lportal</connection-url>
                <driver>mysql</driver>
                <security>
                    <user-name>root</user-name>
                    <password>root</password>
                </security>
            </datasource>
            <driver name="mysql" module="com.liferay.portal">
                <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
            </driver>
        </datasources>
    </subsystem>
    ```

1. Liferay Homeの[`portal-ext.properties`](../../reference/portal-properties.md)ファイルで、データソースを指定します。 例:

    ```properties
    jdbc.default.jndi.name=java:jboss/datasources/ExampleDS
    ```

これで、データソースが構成され、データベースに接続する準備ができました。

<a name="connect-to-a-mail-server" />

## メールサーバーに接続する

データベース構成と同様に、構成するのが最も簡単なメールセッションはDXPです。 DXPの組み込みのメールセッションを使用する場合は、このセクションをスキップして、コントロールパネルの[メールサーバーに接続](../../setting-up-liferay/configuring-mail.md)してください。

JBossでメールセッションを設定する場合は、以下の手順に従ってください。

1. 次のように、`$JBOSS_HOME/standalone/configuration/standalone.xml`ファイルでメールサブシステムを指定します。

    ```xml
    <subsystem xmlns="urn:jboss:domain:mail:3.0">
        <mail-session jndi-name="java:jboss/mail/MailSession" >
            <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp">
                <login username="[place user name here]" password="[place password here]"/>
            </smtp-server>
       </mail-session>
    </subsystem>
    ...
    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    ...
    <outbound-socket-binding name="mail-smtp">
            <remote-destination host="[place SMTP mail host here]" port="[place mail port here]"/>
        </outbound-socket-binding>
    </socket-binding-group>
    ```

1. Liferay Homeの [`portal-ext.properties`](../../reference/portal-properties.md)ファイルで、メールセッションを参照します。 例:

    ```properties
    mail.session.jndi.name=java:jboss/mail/MailSession
    ```

<a name="deploying-dxp" />

## DXPのデプロイ

1. `ROOT.war`デプロイメントをトリガーするには、 `$JBOSS_HOME/ standalone/deployments /` フォルダーに `ROOT.war.dodeploy` という名前の空のファイルを作成します。
1. `$JBOSS_HOME/bin` に移動し、 `standalone.sh`を実行して JBoss アプリケーションサーバーを起動します。 JBoss は `ROOT.war.dodeploy` ファイルを検出し、ファイルの接頭辞に一致する Web アプリケーションをデプロイします (つまり、 `ROOT.war`)。

DXPのデプロイ後に、 `PhaseOptimizer`を含む以下のような過剰な警告やログメッセージが表示される場合があります。 これらは良性なので無視することができます。 これらのメッセージは、アプリサーバーのログレベルまたはログフィルターを調整することでオフにできます。

```
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
WARNING: Skipping pass gatherExternProperties
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
WARNING: Skipping pass checkControlFlow
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
```

Liferay DXP Enterpriseサブスクリプションをお持ちの場合、DXPはアクティベーションキーを要求します。 詳細は、[Activating Liferay DXP](../../setting-up-liferay/activating-liferay-dxp.md)を参照してください。

　 DXPはJBoss EAPで実行されています。

## 次のステップ

[管理者ユーザーとしてサインイン](../../../getting-started/introduction-to-the-admin-account.md)して、\[DXPでのソリューションの構築\](../../../building_solutions_on_dxp.html)を開始できます。 または、[Liferay DXPのその他のセットアップ](../../setting-up-liferay.md)トピックを参照できます。

* [マーケットプレイスプラグインのインストール](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
* [試用期間中のプラグインへのアクセス](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
* [検索エンジンのインストール](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md)
* [Liferay DXPの保護](../../securing-liferay.md)
* [高可用性のためのクラスタリング](../../setting-up-liferay/clustering-for-high-availability.md)