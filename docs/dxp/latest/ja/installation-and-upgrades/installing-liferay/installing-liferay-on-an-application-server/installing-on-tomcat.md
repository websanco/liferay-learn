# Tomcatへのインストール

``` important::
   `Using a Tomcat bundle <../installing-a-liferay-tomcat-bundle.md>`_ or `Docker image <../../../getting-started/starting-with-a-docker-image.md>`_ is the fastest way to get started using Liferay DXP. この記事は、Tomcatアプリケーションサーバーの設定を完全に制御したいユーザーを対象としています。

   Review the `Installing a Liferay-Tomcat Bundle <../installing-a-liferay-tomcat-bundle.md>`_ and `Configuring a Database <../configuring-a-database.md>`_ articles before continuing.
```

TomcatにLiferay DXPをインストールするには、DXP WARファイルをデプロイし、DXPの依存関係をデプロイし、DXP用にTomcatを構成する必要があります。

これを実現する最も単純で簡単な方法は、Liferayによって作成された[Liferay DXP Tomcatバンドルをダウンロード](../installing-a-liferay-tomcat-bundle.md)し、そこで提供されている依存関係、スクリプト、および`ROOT.xml`をコピーすることです。 依存関係をダウンロードし、Tomcatスクリプトと構成を手動で作成/変更することもできます。

依存関係、スクリプト、および構成をLiferay Tomcatバンドルファイルからコピーする（または手動でダウンロードして構成する）ことに加えて、[ヘルプセンター](https://customer.liferay.com/downloads)（サブスクリプション）またはLiferayLiferayコミュニティダウンロード</a>から次のファイルをダウンロードする必要があります。

  - DXP WARファイル
  - 依存関係のZIPファイル
  - OSGi依存関係のZIPファイル

Liferay DXPにはJava JDK 8または11が必要です。

``` note::
   `互換性マトリックスを参照してください <https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?>サポートのJDK、データベース、および環境に関する情報については、` _。
```

以下はTomcatにDXPをインストールするための基本的な手順です。

1.  [依存ファイルをインストールする](#installing-dependencies)
2.  [Tomcatを構成する](#configuring-tomcat)
3.  [データベース設定](#database-configuration)
4.  [メール設定](#mail-configuration)
5.  [DXP WARをデプロイする](#deploying-DXP)

Tomcatサーバーの親フォルダは[*Liferay Home*](../../reference/liferay-home.md)です。 `$TOMCAT_HOME`はTomcatサーバーフォルダを参照します。 通常は、`tomcat-[version]`または`apache-tomcat-[version]`という名前です。

## 依存関係をインストールする

DXPは、DXP Tomcatバンドルに含まれている多くのJARに依存しています。 バンドル内のJARの中には必須ではないファイルもありますが、有用なJARもあります。 Tomcatバンドルを使用していない場合は、以下で説明するように、ダウンロードした*依存関係*アーカイブおよび*OSGi依存関係*アーカイブ内のLiferay JAR、およびサードパーティのJARを使用してください。

1.  依存関係ZIPファイルのコンテンツを`$TOMCAT_HOME/lib/ext`フォルダに解凍します（このフォルダが存在しない場合は作成します）。
2.  OSGi Dependencies ZIPファイルの内容を `[Liferay Home]/osgi` フォルダーに解凍します（このフォルダーが存在しない場合は作成します）。

DXPはJDBCを介してデータベースと通信します。 データベースJDBCドライバーのJARファイルをユーザードメインの`lib`フォルダに追加します。 次のデータベース用のJDBCドライバーのJARをダウンロードできます。

  - [MariaDB](https://downloads.mariadb.org/)
  - [MySQL](http://dev.mysql.com/downloads/connector/j)
  - [PostgreSQL](https://jdbc.postgresql.org/download/postgresql-42.0.0.jar)

HypersonicデータベースはDXPにバンドルされており、テスト目的で役立ちます。 本番環境用のDXPインスタンスにはHSQLを使用**しない**でください。

## Tomcatを構成する

DXPを実行するためのTomcatの構成には、次のものが含まれます。

  - 環境変数を設定する
  - DXPのWebアプリケーションコンテキストを指定する
  - プロパティと記述子を設定する

手順は次のとおりです。

1.  `setenv.bat`、`setenv.sh`、`startup.bat`、`startup.sh`、`shutdown.bat`、および`shutdown.sh`ファイルをDXPバンドルから`$CATALINA_BASE/bin`フォルダにコピーします。 それ以外の場合は、`setenv.bat`および`setenv.sh`スクリプトを作成します。

    これらのスクリプトはCatalina用のJVMオプションを設定し、Tomcatのサーブレットコンテナになります。 これらのオプションの中にはJavaランタイム環境の場所が含まれます。 この環境がサーバーでグローバルに使用できない場合は、これらのファイルに場所を設定して、Tomcatを実行できるようにします。 `JAVA_HOME` 環境変数がDXPがサポートするJREを指すようにして、これを行います。

    ``` bash
    export JAVA_HOME=/usr/lib/jvm/java-8-jdk
    export PATH=$JAVA_HOME/bin:$PATH
    ```

    次に、DXPをサポートするようにCatalinaのJVMオプションを設定します。

    ``` bash
    CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=512m"
    ```

    次のことが行われます。

    1.  ファイルのエンコーディングをUTF-8に設定します。
    2.  IPv6よりもIPv4スタックを優先します。
    3.  Tomcatが静的フィールドまたは最終フィールドに関連するガベージコレクションのバグを回避できないようにします（これらのバグはDXPには存在せず、それらを回避するとロギングシステムで問題が発生します）。
    4.  タイムゾーンをGMTに設定します
    5.  JVMに2GBのRAMを割り当てます
    6.  メタスペースを512MBに制限します。
    
    <!-- end list -->
    
    ``` important::
       DXPでは、アプリケーションサーバーのJVMがGMTタイムゾーンとUTF-8ファイルエンコーディングを使用する必要があります。
    ```

    ``` Note::
       JDK 11では、次のJVM引数を追加して4桁の年を表示することをお勧めします：``-Djava.locale.providers=JRE,COMPAT,CLDR``
    ```


    インストール後、これらの構成（これらのJVMオプションを含む）をさらに調整して、パフォーマンスを向上させることができます。

2.  DXP Tomcatバンドルがある場合は、その`$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`ファイルをアプリケーションサーバーの対応する場所にコピーします。 ファイルパス（存在しない場合）と`ROOT.xml`ファイルを作成します。

    `ROOT.xml`ファイルは、DXPのWebアプリケーションコンテキストを次のように指定します。

    ``` xml
    <Context crossContext="true" path="">
    
        <!-- JAAS -->
    
        <!--<Realm
            className="org.apache.catalina.realm.JAASRealm"
            appName="PortalRealm"
            userClassNames="com.liferay.portal.kernel.security.jaas.PortalPrincipal"
            roleClassNames="com.liferay.portal.kernel.security.jaas.PortalRole"
        />-->
    
        <!--
        Uncomment the following to disable persistent sessions across reboots.
        -->
    
        <!--<Manager pathname="" />-->
    
        <!--
        Uncomment the following to not use sessions. See the property
        "session.disabled" in portal.properties.
        -->
    
        <!--<Manager className="com.liferay.support.tomcat.session.SessionLessManagerBase" />-->
    
        <Resources>
            <PreResources
                base="${catalina.base}/lib/ext/portal"
                className="com.liferay.support.tomcat.webresources.ExtResourceSet"
                webAppMount="/WEB-INF/lib"
            />
        </Resources>
    </Context>
    ```

    `crossContext="true"`を設定すると、複数のWebアプリケーションで同じクラスローダーを使用できます。 この設定には、JAAS領域の設定、永続するセッションの無効化、セッションの完全な無効化のためのコメント付きの説明とタグが含まれています。

3.  `$CATALINA_BASE/conf/catalina.properties`ファイルを開いて、次の値を`common.loader`プロパティに追加することで、`$CATALINA_BASE/lib/ext`内のJARへのCatalinaによるアクセスを提供します。
   
        ,"${catalina.home}/lib/ext/global","${catalina.home}/lib/ext/global/*.jar","${catalina.home}/lib/ext","${catalina.home}/lib/ext/*.jar"

4.  必ずUTF-8 URIエンコードを使用してください。 `$CATALINA_BASE/conf/server.xml`ファイルをTomcatバンドルからサーバーにコピーします。 それ以外の場合は、`$CATALINA_BASE/conf/server.xml`ファイルを開き、属性`URIEncoding="UTF-8"`をHTTPおよび`redirectPort=8443`を使用するAJPコネクタに追加します。 以下は例です:

    旧:

    ``` xml
    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />
    ```

    新

    ``` xml
    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />
    ```

    旧:

    ``` xml
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
    ```

    新

    ``` xml
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" URIEncoding="UTF-8" />
    ```

5.  `$CATALINA_BASE/conf/server.xml`内のアクセスログの`Valve`要素をコメントアウトして、アクセスログの書き込みを控えます（オプション）。 以下ではコメントアウトされています。

    ``` xml
    <!-- <Valve className="org.apache.catalina.valves.AccessLogValve"
           directory="logs"
           prefix="localhost_access_log" suffix=".txt"
           pattern="%h %l %u %t &quot;%r&quot; %s %b" /> -->
    ```

6.  必要に応じて、`$CATALINA_HOME/conf/logging.properties`ファイルで次のログレベルを設定します。

    ``` properties
    org.apache.catalina.startup.Catalina.level=INFO
    org.apache.catalina.startup.ClassLoaderFactory.level=SEVERE
    org.apache.catalina.startup.VersionLoggerListener.level=WARNING
    org.apache.level=WARNING
    ```

7.  `$CATALINA_HOME/conf/web.xml`で、JSPコンパイラをJava 8に設定し、DXPの`TagHandlerPool`クラスを設定して、JSPタグプールを管理します。 `jsp`サーブレット要素の`<load-on-startup>`要素の上に次の要素を追加します。

    ``` xml
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

8.  `$CATALINA_HOME/conf/web.xml`で、アプリケーションサーバーがアプリケーションのJARやクラスの注釈などの追加のメタデータを検索するかどうかを指定します。 `web-app`要素の属性`metadata-complete="true"`を設定すると、追加のメタデータはないことがアプリケーションサーバーに通知されます。 この設定により、アプリケーションサーバーの起動パフォーマンスが向上します。 デフォルトでは、追加のメタデータをチェックします。

9.  Unix、Linux、またはMac OSを使用している場合は、各フォルダーで次のコマンドを実行して、`$CATALINA_HOME/bin`および`$CATALINA_BASE/bin`フォルダのシェルスクリプトを実行可能にします。

    ``` bash
    chmod a+x *.sh
    ```

**チェックポイント:**

1.  ファイルのエンコーディング、ユーザーのタイムゾーン、および優先プロトコルスタックが`setenv.sh`に設定されている。
2.  デフォルトの使用可能メモリとメタスペース制限が設定されている。
3.  `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`はWebアプリケーションコンテキストを宣言している。
4.  `$CATALINA_BASE/conf/catalina.properties`の`common.loader`プロパティは、Catalinaに`$CATALINA_BASE/lib/ext`内のJARへのアクセスを許可している。
5.  `$CATALINA_BASE/conf/server.xml`は、UTF-8エンコーディングを設定している。
6.  `$CATALINA_BASE/conf/server.xml`は、ホストアクセスログを書き込むためのバルブを宣言していない。 *（オプション）*
7.  `$CATALINA_HOME/conf/logging.properties`は、必要なログレベルを設定している。
8.  `$CATALINA_HOME/conf/web.xml`は、タグハンドラープールを設定し、Java 8をJSPコンパイラとして設定している。
9.  `$CATALINA_HOME/conf/web.xml`は、アプリケーションサーバーが追加のメタデータを検索しないように指定している。 *（オプション）*
10. Tomcatの`bin`フォルダ内のスクリプトは実行可能である。

アプリケーションサーバーは、DXPを実行するように設定されています。

## データベース設定

DXPには組み込みのHypersonicデータベースが含まれています。これはデモンストレーション目的には最適ですが、**本番環境では使用しないでください**。 本番環境では、フル機能のサポートされているRDBMSを使用してください。 データベースのセットアップについては、[Configure a Database](../configuring-a-database.md)を参照してください。

Liferay DXPは、DXPに組み込まれているデータソースを使用する（推奨）か、アプリケーションサーバー上に作成したデータソースを使用してデータベースに接続できます。

初めてDXPを実行するときにDXPの組み込みデータソースをデータベースで設定するには、[セットアップウィザード](../../../getting-started/using-the-setup-wizard.md)を使用できます。 または、データベースの [データベーステンプレート](../../reference/database-templates.md) 基づいて、データソースを [`portal-ext.properties` ファイル](../../reference/portal-properties.md) で構成できます。

それ以外の場合は、Tomcatでデータソースを設定できます。

### Tomcatデータソースの設定

1.  データベースサーバーがインストールされ、動作していることを確かめます。 別のマシンにインストールされている場合は、DXPマシンがアクセスできることを確認してください。

2.  `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`を開き、Webアプリケーション`Context`で`Resource`としてデータソースを追加します。

    ``` xml
    <Context...>
        ...
        <Resource
            name="jdbc/LiferayPool"
            auth="Container"
            type="javax.sql.DataSource "
            driverClassName =" [ドライバー名をここに配置] "
            url =" [データベースへのURLをここに配置] "
            username =" [ユーザー名をここに配置] "
            password =" [パスワードをここに配置] "
            maxTotal =" 100"
            maxIdle = "30"
            maxWaitMillis = "10000"
        />
    </Context>
    ```

    データベースのURL、ユーザー名、パスワードを適切な値に置き換えてください。

3.  **\[Liferay\_Home\]**の`portal-ext.properties`ファイルで、データソースを指定します。

    ``` properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    ```

データソースが設定されました。

## メール設定

メールを設定する最も簡単な方法は、DXPに[組み込みのメールセッション](../../setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md)を使用することです。 組み込みのメールセッションを使用する場合は、このセクションをスキップできます。

Tomcatを使用してメールセッションを管理する場合は、次の手順に従います。

1.  `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`を開き、メールセッションをWebアプリケーション`Context`の`Resource`として開きます。 サンプルのメールセッション値を自分のものに置き換えてください。

    ``` xml
    <Context...>
        ...
        <Resource
            name="mail/MailSession"
            auth="Container"
            type="javax.mail.セッション "
            mail.pop3.host =" [POPメールホストをここに配置] "
            mail.pop3.port =" 110 "
            mail.smtp.host =" [SMTPメールホストをここに配置] "
            mail.smtp.port = "465"
            mail.smtp.user = "[ここにユーザー名を配置]"
            mail.smtp.password = "[ここにパスワードを配置]"
            mail.smtp.auth = "true"
            mail.smtp.starttls。 enable = "true"
            mail.smtp.socketFactory.class = "javax.net.ssl.SSLSocketFactory"
            mail.imap.host = "[IMAPメールホストをここに配置]"
            mail.imap.port = "993"
            mail.transport.protocol = "SMTP"
            mail.store.protocol = "IMAP"
        />
    </Context>
    ```

2.  Liferay Homeの`portal-ext.properties`ファイルに、メールセッションの値を入力します。

    ``` properties
    mail.session.jndi.name=mail/MailSession
    ```

Tomcatのメールセッションが設定されました。

## DXPのデプロイ

1.  クリーンなTomcatサーバーに手動インストールする場合は、`$CATALINA_BASE/webapps/ROOT`フォルダのコンテンツを削除します。 これにより、デフォルトのTomcatホームページが削除されます。
2.  DXP `.war`ファイルのコンテンツを`$CATALINA_BASE/webapps/ROOT`に展開します。
3.  `$CATALINA_HOME/bin`に移動して`./startup.sh`を実行し、Tomcatを起動します。 または、`./catalina.sh run`を実行して、DXPのログファイルを追跡します。 ログは起動アクティビティを監査し、デプロイのデバッグに役立ちます。

DXPはTomcatで実行されています。

## 次のステップ

[管理者ユーザーとしてサインイン](../../../getting-started/introduction-to-the-admin-account.md)して、[DXPでソリューションの構築](../../../building-solutions-on-dxp/README.md)を開始できます。 または、[Liferay DXPのその他のセットアップ](../../setting-up-liferay.md)トピックを参照できます。

  - [Installing the Marketplace Plugin](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
  - [試用期間中のプラグインへのアクセス](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
  - [検索エンジンのインストール](../../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md)
  - [Securing Liferay DXP](../../securing-liferay/introduction-to-securing-liferay.md)
  - [高可用性のクラスタリング](../../setting-up-liferay/clustering-for-high-availability.md)
