# Tomcatへのインストール

```{important}
[Liferay-Tomcatバンドルの使用](../installing-a-liferay-tomcat-bundle.md) または[Dockerイメージ](../../../getting-started/starting-with-a-docker-image.md)は、Liferay DXPの使用を開始するための最速の方法です。 この記事は、Tomcatアプリケーションサーバーの設定を完全に制御したいユーザーを対象としています。

続行する前に、 [Liferay-Tomcatバンドルのインストール](../installing-a-liferay-tomcat-bundle.md) と [データベースの構成](../configuring-a-database.md) の記事を確認してください。
```

Tomcatにインストールするには、DXP WARのインストール、依存関係のインストール、Tomcatの設定、およびDXPのデプロイが必要です。 データベースとメールサーバーの接続も設定する必要があります。

これを実現する最も単純で簡単な方法は、[Liferay Liferay-Tomcatバンドルをダウンロード](../installing-a-liferay-tomcat-bundle.md)し、依存関係、スクリプト、および`ROOT.xml`をそこから以下に説明する場所にコピーすることです。 それ以外の方法としては、依存関係をダウンロードして、Tomcatを手動で構成できます。

<a name="prerequisites" />

## 前提条件

Tomcatの構成方法に関係なく、 [ヘルプセンター](https://customer.liferay.com/downloads) （サブスクリプション）または [Liferayコミュニティのダウンロード](https://www.liferay.com/downloads-community) からこれらのファイルをダウンロードしてインストールする必要があります。

* DXP WARファイル
* OSGi依存関係のZIPファイル
* 依存関係のZIPファイル（DXP 7.3以前）

Java JDK 8または11が必要です。

```{note}
サポートされているJDK、データベース、および環境については、 [互換性マトリックス](https://help.liferay.com/hc/ja/articles/360049238151) を参照してください。  推奨されるJVM設定については、 [JVM設定](../../reference/jvm-configuration.md) を参照してください。
```

Tomcatサーバーの親フォルダは[**Liferay Home**](../../reference/liferay-home.md)です。 `$TOMCAT_HOME`はTomcatサーバーフォルダを参照します。 通常は、`tomcat-［version］`または`apache-tomcat-［version］`という名前です。

<a name="installing-the-dxp-war" />

## DXP WARのインストール

1. クリーンなTomcatのインストールを開始する場合は、`$CATALINA_BASE/webapps/ROOT`フォルダのコンテンツを削除します。 これにより、デフォルトのTomcatホームページが削除されます。
1. DXP `.war`ファイルのコンテンツを`$CATALINA_BASE/webapps/ROOT`に展開します。

<a name="installing-dependencies" />

## 依存関係のインストール

DXPは、Liferay-Tomcatバンドルに含まれている多くのJARに依存しています。 バンドル内のJARの中には必須ではないファイルもありますが、有用なJARもあります。 Tomcatバンドルを使用していない場合は、ダウンロードした **OSGi依存関係** アーカイブと、以下で説明するサードパーティのJAR依存関係を使用します。

1. OSGi Dependencies ZIPファイルの内容を `［Liferay Home］/osgi` フォルダーに解凍します（このフォルダーが存在しない場合は作成します）。 LiferayのOSGiランタイムは、これらのモジュールに依存しています。
1. DXP 7.4+ WARファイルには、MariaDBおよびPostgreSQLのドライバーが含まれています。 以前のWARにはそれらがありません。 7.4以降のWARに、使用中のサポートされているデータベースのドライバーがない場合は、データベースベンダーのJDBC JARファイルをダウンロードして、`$CATALINA_BASE/standalone/deployments/ROOT.war/WEB-INF/shielded-container-lib`フォルダーに配置します。

    サポートされているデータベースの一覧については、 [互換性マトリックス](https://help.liferay.com/hc/ja/articles/360049238151) を参照してください。

```{note}
HypersonicデータベースはDXPにバンドルされており、テスト目的で役立ちます。 本番環境インスタンスにはHSQLを**使用しないでください**。
```

```{note}
DXP 7.3以前の場合は、依存関係ZIPファイルのコンテンツを$TOMCAT_HOME/lib/extフォルダに解凍します（このフォルダが存在しない場合は作成します）。 データベースベンダーのJDBC JARファイルもこのフォルダに入れてください。
```

<a name="configuring-tomcat" />

## Tomcatの設定

DXPを実行するためのTomcatの構成には、次のタスクが含まれます。

* JVMオプションの設定
* DXPのWebアプリケーションコンテキストを指定する
* プロパティと記述子の設定

1. `setenv.bat`、`setenv.sh`、`startup.bat`、`startup.sh`、`shutdown.bat`、および`shutdown.sh`ファイルをDXPバンドルから`$CATALINA_BASE/bin`フォルダにコピーします。 それ以外の場合は、`setenv.bat`および`setenv.sh`スクリプトを作成します。

1. `setenv.sh`スクリプトはCatalina用のJVMオプションを設定し、Tomcatのサーブレットコンテナになります。 これらのオプションの中にはJavaランタイム環境の場所が含まれます。 この環境がサーバーでグローバルに使用できない場合は、`setenv.sh`スクリプトにその場所を設定して、Tomcatを実行できるようにします。 `JAVA_HOME` 環境変数がDXPがサポートするJREを指すようにして、これを行います。

    ```bash
    export JAVA_HOME=/usr/lib/jvm/java-8-jdk
    export PATH=$JAVA_HOME/bin:$PATH
    ```

1. 次に、DXPをサポートするようにCatalinaのJVMオプションを設定します。

    ```bash
    CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
    ```

**JVMオプションの説明**

| オプション                                     | 説明                                                                                                   |
|:----------------------------------------- |:---------------------------------------------------------------------------------------------------- |
| `-Dfile.encoding=UTF-8`                   | DXPにはUTF-8ファイルエンコーディングが必要です。                                                                         |
| `-Djava.locale.providers=JRE,COMPAT,CLDR` | これは、JDK 11で4桁の日付を表示するために必要です。                                                                        |
| `-Djava.net.preferIPv4Stack=true`         | IPv6よりもIPv4スタックを優先します。                                                                               |
| `-Dlog4j2.formatMsgNoLookups=true`        | リモートコード実行（RCE）の脆弱性を解決します。 詳細は、 [LPS-143663](https://issues.liferay.com/browse/LPS-143663) を参照してください。 |
| `-Duser.timezone=GMT`                     | DXPでは、アプリケーションサーバーのJVMがGMTタイムゾーンを使用する必要があります。                                                        |

**メモリ引数の説明**

| メモリ引数               | 説明                                                                     |
|:------------------- |:---------------------------------------------------------------------- |
| `-Xms`              | ヒープの初期スペース。                                                            |
| `-Xmx`              | ヒープの最大スペース。                                                            |
| `-XX:NewSize`       | 最初の新しいスペース。 通常、新しいサイズをヒープ全体の半分に設定すると、より小さな新しいサイズを使用するよりもパフォーマンスが向上します。 |
| `-XX:MaxNewSize`    | 最大の新しいスペース。                                                            |
| `-XX:SurvivorRatio` | 新しいスペースとSurvivor領域の比率。 Survivor領域は、古い世代の領域に昇格する前に、若い世代のオブジェクトを保持します。   |

```{note}
DXPのインストール後、これらの構成（これらのJVMオプションを含む）をさらに調整して、パフォーマンスを向上させることができます。 詳細については、 [Liferayの調整](../../setting-up-liferay/tuning-liferay.md) および [JVMの調整](../../setting-up-liferay/tuning-your-jvm.md) を参照してください。
```

引き続き、Tomcatの構成を行います。

1. Liferay-Tomcatバンドルがある場合は、その`$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`ファイルをアプリケーションサーバーの対応する場所にコピーします。 ファイルパス（存在しない場合）と`ROOT.xml`ファイルを作成します。

    `ROOT.xml` ファイルは、DXPのWebアプリケーションコンテキストを指定します。 例:

    ```xml
    <Context crossContext="true">
        <JarScanner className="com.liferay.support.tomcat.util.scan.NOPJarScanner" />

        <!-- JAAS -->

        <!--<Realm
            className="org.apache.catalina.realm.JAASRealm"
            appName="PortalRealm"
            userClassNames="com.liferay.portal.kernel.security.jaas.PortalPrincipal"
            roleClassNames="com.liferay.portal.kernel.security.jaas.PortalRole"
        />-->
    </Context>
    ```

     `crossContext="true"`を設定すると、複数のWebアプリケーションで同じクラスローダーを使用できます。 この構成には、JAAS領域を構成するためのコメント付きの指示とタグが含まれています。

1. 必ずUTF-8 URIエンコードを使用してください。 `$CATALINA_BASE/conf/server.xml`ファイルをTomcatバンドルからサーバーにコピーします。 それ以外の場合は、`$CATALINA_BASE/conf/server.xml`ファイルを開き、属性`URIEncoding="UTF-8"`をHTTPおよび`redirectPort=8443`を使用するAJPコネクタに追加します。 以下は例です:

    旧:

    ```xml
    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />
    ```

    新:

    ```xml
    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />
    ```

    旧:

    ```xml
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
    ```

    新:

    ```xml
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" URIEncoding="UTF-8" />
    ```

1. `$CATALINA_BASE/conf/server.xml`内のアクセスログの`Valve`要素をコメントアウトして、アクセスログの書き込みを控えます（オプション）。 以下ではコメントアウトされています。

    ```xml
    <!-- <Valve className="org.apache.catalina.valves.AccessLogValve"
           directory="logs"
           prefix="localhost **access** log" suffix=".txt"
           pattern="%h %l %u %t &quot;%r&quot; %s %b" /> -->
    ```

1. 必要に応じて、`$CATALINA_HOME/conf/logging.properties`ファイルで次のログレベルを設定します。

    ```properties
    org.apache.catalina.startup.Catalina.level=INFO
    org.apache.catalina.startup.ClassLoaderFactory.level=SEVERE
    org.apache.catalina.startup.VersionLoggerListener.level=WARNING
    org.apache.level=WARNING
    ```

1. DXP 7.3以前の場合、`$CATALINA_HOME/conf/web.xml`ファイルを開いてJSPコンパイラをJava 8に設定し、DXPの`TagHandlerPool`クラスを設定して、`jsp`サーブレット要素の`<load-on-startup>`要素の上に次の要素を追加してJSPタグプールを管理します。

    ```xml
    <init-param>
        <param-name>compilerSourceVM</param-name>
        <param-value>1.8</param-value>
    </init-param>
    <init-param>
        <param-name>compilerTargetVM</param-name>
        <param-value>1.8</param-value>
    </init-param>
    <init-param>
        <param-name>tagpoolClassName</param-name>
        <param-value>com.liferay.support.tomcat.jasper.runtime.TagHandlerPool</param-value>
    </init-param>
    ```

1. `$CATALINA_HOME/conf/web.xml`で、アプリケーションサーバーがアプリケーションのJARやクラスの注釈などの追加のメタデータを検索するかどうかを指定します。 `web-app`要素の属性`metadata-complete="true"`を設定すると、追加のメタデータはないことがアプリケーションサーバーに通知されます。 この設定を使用すると、アプリケーションサーバーの起動が速くなります。 デフォルトでは、追加のメタデータをチェックします。

1. Unix、Linux、またはMac OSを使用している場合は、各フォルダで次のコマンドを実行して、`$CATALINA_HOME/bin`および`$CATALINA_BASE/bin`フォルダのシェルスクリプトを実行可能にします。

    ```bash
    chmod a+x *.sh
    ```

LiferayのTomcatサポートJARは、DXP Webアプリケーションの一部です。 DXPはJARのファイルスキャナーを使用します。 DXPがJARを使用するためには、JARが共通のクラスローダーに入っている必要があります。 `$CATALINA_BASE/conf/catalina.properties`ファイルを開き、次の値を`common.loader`プロパティのコンマ区切り値リストの先頭に追加して、ファイルへのCatalinaアクセスを提供します。

```properties
"${catalina.home}/webapps/ROOT/WEB-INF/lib/support-tomcat.jar",
```

DXP 7.3以前の場合、`common.loader`プロパティの値リストの先頭に次の値を追加して、`$CATALINA_BASE/lib/ext`のJARへのCatalinaアクセスを提供します。

```
"${catalina.home}/lib/ext","${catalina.home}/lib/ext/*.jar",
```

**チェックポイント:**

1. ファイルのエンコーディング、ユーザーのタイムゾーン、および優先プロトコルスタックが`setenv.sh`スクリプトに設定されている。
1. デフォルトの使用可能メモリとメタスペース制限が設定されています。
1. `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`はWebアプリケーションコンテキストを宣言している。
1. `$CATALINA_BASE/conf/server.xml`は、UTF-8エンコーディングを設定している。
1. `$CATALINA_BASE/conf/server.xml`は、ホストアクセスログを書き込むための値を宣言していない。 (**オプション**)
1. `$CATALINA_HOME/conf/logging.properties`は、必要なログレベルを設定している。
1. `$CATALINA_HOME/conf/web.xml`は、タグハンドラープールを設定し、Java 8をJSPコンパイラとして設定している。
1. `$CATALINA_HOME/conf/web.xml`は、アプリケーションサーバーが追加のメタデータを検索しないように指定している。 (**オプション**)
1. Tomcatの`bin`フォルダ内のスクリプトは実行可能である。
1. `$CATALINA_BASE/conf/catalina.properties`の`common.loader`プロパティは、Catalinaに必要なJARへのアクセスを許可している。

アプリケーションサーバーは、DXPを実行するように設定されています。

<a name="database-configuration" />

## データベース設定

DXPには組み込みのHypersonicデータベースが含まれています。これはデモンストレーション目的には最適ですが、 **本番環境では使用しないでください** 。 本番環境では、フル機能のサポートされているRDBMSを使用してください。 データベースのセットアップについては、[Configure a Database](../configuring-a-database.md)を参照してください。

Liferay DXPは、DXPに組み込まれているデータソースを使用する（推奨）か、アプリケーションサーバー上に作成したデータソースを使用してデータベースに接続できます。

[セットアップウィザード](../running-liferay-for-the-first-time.md)を使用して、DXPを初めて実行するときに、データベースを使用してDXPの組み込みデータソースを構成できます。 または、データベースの [データベーステンプレート](../../reference/database-templates.md)に 基づいて、データソースを [`portal-ext.properties` ファイル](../../reference/portal-properties.md)で構成できます。

それ以外の場合は、Tomcatでデータソースを設定できます。

<a name="configuring-the-tomcat-data-source" />

### Tomcatデータソースの設定

1. データベースサーバーがインストールされ、動作していることを確かめます。 別のマシンにインストールされている場合は、DXPマシンがアクセスできることを確認してください。

1. DXP WAR（7.4以降）またはデータベースベンダーからJDBC JARを取得し、`$TOMCAT_HOME/lib/ext`フォルダにコピーします。

1. `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`を開き、Webアプリケーション`コンテキスト`で`リソース`としてデータソースを追加します。

    ```xml
    <Context...>
        ...
        <Resource
            name="jdbc/LiferayPool"
            auth="Container"
            type="javax.sql.DataSource"
            driverClassName="[place the driver name here]"
            url="[place the URL to your database here]"
            username="[place your user name here]"
            password="[place your password here]"
            maxTotal="100"
            maxIdle="30"
            maxWaitMillis="10000"
        />
    </Context>
    ```

    データベースのURL、ユーザー名、パスワードを適切な値に置き換えてください。

1. [**Liferay_Home**] の`portal-ext.properties`ファイルで、データソースを指定します。 例:

    ```properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    ```

データソースが設定されました。

<a name="mail-configuration" />

## メール設定

メールを設定する最も簡単な方法は、DXPの[組み込みのメールセッション](../../setting-up-liferay/configuring-mail.md)を使用することです。 組み込みのメールセッションを使用する場合は、このセクションをスキップしてください。

Tomcatを使用してメールセッションを管理する場合は、次の手順に従います。

1. `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`を開き、メールセッションをWebアプリケーション`Context`の`Resource`として定義します。 サンプルのメールセッション値を自分のものに置き換えてください。

    ```xml
    <Context...>
        ...
        <Resource
            name="mail/MailSession"
            auth="Container"
            type="javax.mail.Session"
            mail.pop3.host="[place POP mail host here]"
            mail.pop3.port="110"
            mail.smtp.host="[place SMTP mail host here]"
            mail.smtp.port="465"
            mail.smtp.user="[place user name here]"
            mail.smtp.password="[place password here]"
            mail.smtp.auth="true"
            mail.smtp.starttls.enable="true"
            mail.smtp.socketFactory.class="javax.net.ssl.SSLSocketFactory"
            mail.imap.host="[place IMAP mail host here]"
            mail.imap.port="993"
            mail.transport.protocol="smtp"
            mail.store.protocol="imap"
        />
    </Context>
    ```

1. Liferay Homeの`portal-ext.properties`ファイルで、メールセッションを指定します。 例:

    ```properties
    mail.session.jndi.name=mail/MailSession
    ```

メールセッションはTomcatで設定されます。

<a name="deploying-dxp" />

## DXPのデプロイ

`$CATALINA_HOME/bin`に移動して`./startup.sh`を実行し、Tomcatを起動します。 または、`./catalina.sh run`を実行して、DXPのログファイルを追跡します。 ログは起動アクティビティを監査し、デプロイのデバッグに役立ちます。

Liferay DXP Enterpriseサブスクリプションをお持ちの場合、DXPはアクティベーションキーを要求します。 詳細は、 [Liferay DXPのアクティブ化](../../setting-up-liferay/activating-liferay-dxp.md) を参照してください。

　 DXPはTomcatで実行されています。

<a name="next-steps" />

## 次のステップ

[管理者ユーザーとしてサインインして](../../../getting-started/introduction-to-the-admin-account.md)、DXPでソリューションの構築を開始できます。 または、[Liferay DXPのその他のセットアップ](../../setting-up-liferay.md)トピックを参照できます。

* [マーケットプレイスプラグインのインストール](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
* [試用期間中のプラグインへのアクセス](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
* [検索エンジンのインストール](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md)
* [Liferay DXPの保護](../../securing-liferay.md)
* [高可用性のためのクラスタリング](../../setting-up-liferay/clustering-for-high-availability.md)