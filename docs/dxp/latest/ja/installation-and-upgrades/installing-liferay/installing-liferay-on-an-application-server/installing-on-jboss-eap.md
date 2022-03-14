# JBoss EAPへのインストール

JBoss EAPにインストールするには、DXP WARのインストール、依存関係のインストール、JBossの設定、およびDXPのデプロイが必要です。 データベースとメールサーバーの接続も設定する必要があります。

<a name="prerequisites" />

## 前提条件

Liferay DXPにはJava JDK 8または11が必要です。 詳細は、 [互換性マトリクス](https://help.liferay.com/hc/ja/articles/360049238151) を参照してください。

これらのファイルを [ヘルプセンター](https://customer.liferay.com/downloads) （サブスクリプション）または [Liferayコミュニティダウンロード](https://www.liferay.com/downloads-community) からダウンロードします。

* DXP WARファイル
* OSGi依存関係のZIPファイル
* 依存関係のZIPファイル（DXP 7.3以前）

[**Liferay Home**](../../reference/liferay-home.md) は、JBossサーバーフォルダーを含むフォルダーです。 DXPをインストールしてデプロイした後、`data`、`deploy`、および`logs`フォルダを生成します。 `$JBOSS_HOME` はJBossサーバーフォルダーを参照します。 このフォルダーの名前は通常 `jboss-eap-［version］`です。

<a name="installing-the-dxp-war" />

## DXP WARのインストール

1. フォルダー `$JBOSS_HOME/standalone/deployments/ROOT.war` がJBossインストールにすでに存在する場合は、そのサブフォルダーとファイルをすべて削除します。 それ以外の場合は、 `$JBOSS_HOME/standalone/deployments/ROOT.war`という名前の新しいフォルダーを作成します。
1. DXP `.war` ファイルを `ROOT.war` フォルダーに解凍します。

<a name="installing-dependencies" />

## 依存関係をインストールする

1. OSGi Dependencies ZIPファイルを `［Liferay Home］/osgi` フォルダーに解凍します（このフォルダーが存在しない場合は作成します）。 LiferayのOSGiランタイムは、これらのモジュールに依存しています。
1. DXP 7.4+ WARファイルには、MariaDB、MySQL、およびPostgreSQLのドライバーが含まれています。 以前のWARにはそれらがありません。 WARに必要なドライバーがない場合は、データベースベンダーのJDBC JARファイルを`$JBOSS_HOME/modules/com/liferay/portal/main`フォルダにダウンロードします（このフォルダーが存在しない場合は作成します）。 サポートされるデータベースのリストについては、 [互換性マトリックス](https://help.liferay.com/hc/ja/articles/360049238151) を参照してください。

```{note}
DXPには、テスト目的に役立つHypersonicデータベースが含まれています。 本番環境インスタンスにはHSQLを**使用しないでください**。
```

### 以前のバージョンの依存関係をインストールする

DXP 7.3以前の場合は、次の追加手順に従います。

1. 依存関係のZIPファイルを`$JBOSS_HOME/modules/com/liferay/portal/main`フォルダに解凍します（このフォルダが存在しない場合は作成します）。
1. `$JBOSS_HOME/modules/com/liferay/portal/main`フォルダにファイル`module.xml`を作成します。 ファイルで、ポータルモジュールとそれに必要なすべてのリソースと依存関係を宣言します。

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

<a name="running-dxp-on-jboss-eap-in-standalone-mode-vs-domain-mode" />

## スタンドアロンモードとドメインモードのJBoss EAPでのDXPの実行

JBoss EAPは、 **スタンドアロン** モードまたは **ドメイン** モードのいずれかで起動できます。 ドメインモードでは、単一のコントロールポイントから複数のアプリケーションサーバーインスタンスを管理できます。 このようなアプリケーションサーバーのコレクションは、 **ドメイン** と呼ばれます。 ドメインモード対スタンドアロンモードの詳細は、このトピックに関するセクションを参照してください [のJBoss EAP製品ドキュメント](https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.1/html/introduction_to_jboss_eap/overview_of_jboss_eap#operating_modes) 。

DXPは、スタンドアロンモードで実行する場合はJBoss EAPをサポートしますが、ドメインモードで実行する場合はサポートしません。 JBossはファイル（展開または非展開）をコピーして管理対象デプロイメントのコンテンツを管理するため、DXPの自動展開は管理対象デプロイメントでは機能しません。 これにより、JSPフックとExtプラグインが意図したとおりに機能しなくなります。 たとえば、DXPのJSPオーバーライドメカニズムはアプリケーションサーバーに依存しているため、JSPフックは管理対象ドメインモードで実行されているJBoss EAPでは機能しません。 ただし、JSPフックとExtプラグインは非推奨であるため、使用していない可能性があります。

コマンドラインインターフェイスは、ドメインモードの展開に推奨されます。

```{note}
これにより、DXPが複数のJBossサーバー上のクラスター環境で実行されるのを防ぐことはできません。 スタンドアロンモードで実行されているJBoss EAPサーバーで実行されているインスタンスのクラスターを設定できます。 詳細については、[クラスタリングの記事](../../setting-up-liferay/clustering-for-high-availability.md) を参照してください。
```

<a name="configuring-jboss" />

## JBossの構成

JBossの構成手順は次のとおりです。

* 環境変数を設定する
* プロパティと記述子を指定する
* 不要な構成を削除する

`$JBOSS_HOME/standalone/configuration/standalone.xml`次の変更を加えます。

1. `<jsp-config>`タグで、Liferay JavaソースファイルとクラスファイルのJava VMの互換性を設定します。  これらはデフォルトでJava 8と互換性があります。

    ```xml
    <jsp-config development="true" source-vm="1.8" target-vm="1.8" />
    ```

1. `</extensions>` の終了タグを見つけます。 そのタグのすぐ下に、次のシステムプロパティを挿入します。

    ```xml
    <system-properties>
        <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
        <property name="org.apache.catalina.connector.USE **BODY** ENCODING **FOR** QUERY_STRING" value="true" />
    </system-properties>
    ```

1. `<level name="INFO"/>`タグのすぐ下にある`<console-handler>`タグ内に次の`<filter-spec>`タグを追加します。

    ```xml
    <filter-spec value="not(any(match(&quot;WFLYSRV0059&quot;),match(&quot;WFLYEE0007&quot;)))" />
    ```

1. 次に示すように、`deployment-timeout="600"`を設定して、デプロイメントスキャナーのタイムアウトを追加します。

    ```xml
    <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
        <deployment-scanner deployment-timeout="600" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
    </subsystem>
    ```

1. 次のJAASセキュリティドメインを要素`<subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">`で定義されたセキュリティサブシステム`<security-domains>`に追加します。

    ```xml
    <security-domain name="PortalRealm">
        <authentication>
            <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
        </authentication>
    </security-domain>
    ```

1. ウェルカムコンテンツのコードスニペットを削除します。

    ```xml
    <location name="/" handler="welcome-content"/>
    ```

    そして

    ```xml
    <handlers>
        <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
    </handlers>
    ```

**チェックポイント:**

続行する前に、次のプロパティが `standalone.xml` ファイルに設定されていることを確認してください。

1. 新しい `<system-property>` が追加されます。
1. 新しい `<filter-spec>` が追加されます。
1. `<deployment-timeout>` は `360`に設定されます。
1. 新しい `<security-domain>` が作成されます。
1. ウェルカムコンテンツが削除されます。

次に、構成スクリプトを変更します。

### 構成スクリプトの変更

`$JBOSS_HOME/ bin /` フォルダーで、スタンドアロンドメインの構成スクリプトファイル `standalone.conf` （Windowsでは`standalone.conf.bat` します。

* ファイルのエンコーディングを `設定UTF-8`
* ユーザーのタイムゾーンを `GMT`設定します
* 優先プロトコルスタックを設定する
* 利用可能なデフォルトのメモリ容量を増やします。

```{important}
DXPでは、アプリケーションサーバーのJVMが `GMT`タイムゾーンと` UTF-8`ファイルエンコーディングを使用する必要があります。
```

`standalone.conf`スクリプトを次のように編集します。

1. `if [ "x$JAVA_OPTS" = "x" ];`文の下で、`JAVA_OPTS`文を置き換えます。 たとえば、次のように置き換えます。

    ```bash
    JAVA_OPTS="-Xms1303m -Xmx1303m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2560m -Djava.net.preferIPv4Stack=true"
    ```

    これとともに：

    ```bash
    JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
    ```

1. ファイルの最後に次のステートメントを追加します。

    ```bash
    JAVA **OPTS="$JAVA** OPTS -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Djboss.as.management.blocking.timeout=480 -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
    ```

Javaオプションとメモリ引数について以下に説明します。

**JVMオプションの説明**

| オプション                                     | 説明                                            |
|:----------------------------------------- |:--------------------------------------------- |
| `-Dfile.encoding=UTF-8`                   | DXPにはUTF-8ファイルエンコーディングが必要です。                  |
| `-Djava.locale.providers=JRE,COMPAT,CLDR` | これは、JDK 11で4桁の日付を表示するために必要です。                 |
| `-Djava.net.preferIPv4Stack=true`         | IPv6よりもIPv4スタックを優先します。                        |
| `-Duser.timezone=GMT`                     | DXPでは、アプリケーションサーバーのJVMがGMTタイムゾーンを使用する必要があります。 |

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
DXPのインストール後、これらの構成（これらのJVMオプションを含む）をさらに調整して、パフォーマンスを向上させることができます。 詳細については、 [Liferayの調整](../../setting-up-liferay/tuning-liferay.md) および [JVMの調整](../../setting-up-liferay/tuning-your-jvm.md) を参照してください。
```

### IBM JDKの使用

JBossサーバーでIBM JDKを使用する場合は、以下の追加手順を実行します。

1. `$JBOSS_HOME/modules/com/liferay/portal/main/module.xml` ファイルに移動し、 `<dependencies>` 要素内に次の依存関係を挿入します。

    `<module name="ibm.jdk" />`

1. `$JBOSS_HOME/modules/system/layers/base/sun/jdk/main/module.xml` ファイルに移動し、次のパス名を `<paths>...</paths>` 要素内に挿入します。

    ```xml
    <path name="com/sun/crypto" />
    <path name="com/sun/crypto/provider" />
    <path name="com/sun/image/codec/jpeg" />
    <path name="com/sun/org/apache/xml/internal/resolver" />
    <path name="com/sun/org/apache/xml/internal/resolver/tools" />
    ```

追加されたパスは、ポータルのデプロイメントの例外と画像のアップロードの問題を解決します。

スクリプトの変更が完了しました。

<a name="connect-to-a-database" />

## データベースに接続する

データベース構成を処理する最も簡単な方法は、DXPにデータソースを管理させることです。 [セットアップウィザード](../running-liferay-for-the-first-time.md)を使用して、DXPの組み込みデータソースを構成できます。 組み込みのデータソースを使用する場合は、このセクションをスキップしてください。

JBossを使用してデータソースを管理する場合は、次の手順に従います。

1. DXP WAR（7.4以降）またはデータベースベンダーからJDBC JARを取得し、`$JBOSS_HOME/modules/com/liferay/portal/main`フォルダにコピーします。

1. `$JBOSS_HOME/standalone/configuration/standalone.xml` ファイルの `<datasources>` 要素内にデータソースを追加します。

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
    データソース`jndi-name`を変更する必要がある場合は、`<default-bindings>`タグ内の`datasource`要素を編集してください。
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
            <drivers>
                <driver name="mysql" module="com.liferay.portal"/>
            </drivers>
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

データベース構成と同様に、構成するのが最も簡単なメールセッションはDXPです。 DXPの組み込みのメールセッションを使用する場合は、このセクションをスキップして、コントロールパネルの[メールサーバーに接続](../../setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md)してください。

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

1. `ROOT.war`ファイルのデプロイをトリガーするには、`$JBOSS_HOME/standalone/deployments/`フォルダに`ROOT.war.dodeploy`という名前の空のファイルを作成します。 起動時に、JBossはこのファイルを検出し、Webアプリケーションとしてデプロイします。
1. `$JBOSS_HOME/ bin` 、 `standalone.sh` または `standalone.bat`を実行して、JBossアプリケーションサーバーを起動します。

DXPの導入後、 `PhaseOptimizer`含む以下のような警告やログメッセージが過剰に表示される場合があります。 これらは良性なので無視することができます。 これらのメッセージは、アプリサーバーのログレベルまたはログフィルターを調整することでオフにできます。

```
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
WARNING: Skipping pass gatherExternProperties
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
WARNING: Skipping pass checkControlFlow
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
```

Liferay DXP Enterpriseサブスクリプションをお持ちの場合、DXPはアクティベーションキーを要求します。 詳細は、 [Liferay DXPのアクティブ化](../../setting-up-liferay/activating-liferay-dxp.md) を参照してください。

　 DXPはJBoss EAPで実行されています。

<a name="next-steps" />

## 次のステップ

[管理者ユーザーとしてサインイン](../../../getting-started/introduction-to-the-admin-account.md)して、\ [DXPでのソリューションの構築\](../../../building_solutions_on_dxp.html) を開始できます。 または、[Liferay DXPのその他のセットアップ](../../setting-up-liferay.md)トピックを参照できます。

* [Installing the Marketplace Plugin](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
* [試用期間中のプラグインへのアクセス](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
* [検索エンジンのインストール](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md)
* [Securing Liferay DXP](../../securing-liferay.md)
* [高可用性のクラスタリング](../../setting-up-liferay/clustering-for-high-availability.md)