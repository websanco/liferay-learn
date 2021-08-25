# WildFlyへのインストール

WildFlyにインストールするには、依存関係のデプロイ、スクリプトの変更、設定`xml`ファイルの変更、およびDXP WARファイルのデプロイが必要です。 さらに、オプションのデータベースとメールサーバーの構成を作成して、DXPインスタンスを最適化します。

Liferay DXPにはJava JDK 8または11が必要です。 参照 [互換性マトリックス](https://www.liferay.com/documents/10182/246659966/Liferay+DXP+7.2+Compatibility+Matrix.pdf/ed234765-db47-c4ad-7c82-2acb4c73b0f9) 詳細については。

[ヘルプセンター](https://customer.liferay.com/downloads)(サブスクリプション)または[Liferayコミュニティのダウンロード](https://www.liferay.com/downloads-community)から、これらのファイルをダウンロードしてください。 管理者は以下をダウンロードする必要があります。

  - DXP WARファイル
  - 依存関係のZIPファイル
  - OSGi依存関係のZIPファイル

次に進む前に、[*Liferay Home*](../../reference/liferay-home.md)と`$WILDFLY_HOME`の違いを理解する必要があります。

  - `Liferay.home` は、WildFlyサーバーフォルダーを含むフォルダーです。 DXPのインストールとデプロイ後、Liferay HomeフォルダにはWildFlyサーバーフォルダと`データ`、`デプロイ`、`ログ`フォルダ、`osgi`フォルダが含まれます。

  - `$WILDFLY_HOME` は、WildFlyサーバーフォルダーを指します。 通常、`wildfly-[version]`という名前です。

WildFlyにLiferay DXPをインストールするには、次の手順が必要です。

1.  [アプリケーションサーバーへの依存関係のインストール](#installing-dependencies)
2.  [DXP用のアプリケーションサーバーの設定](#configuring-wildfly)
3.  [データベースに接続する](#connect-to-a-database)
4.  [メールサーバーに接続する](#connect-to-a-mail-server)
5.  [DXP WARファイルをアプリケーションサーバーにデプロイする](#deploying-dxp)

## 依存関係をインストールする

1.  フォルダ`$WILDFLY_HOME/modules/com/liferay/portal/main`が存在しない場合はフォルダ<0>を作成し、ここに依存関係ZIP JARを抽出します。

2.  データベースドライバー `.jar` ファイルをダウンロードし、同じフォルダーにコピーします。 サポートされているデータベースのリストについては、Liferayの [サポートマトリックス](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?)参照してください。

3.  ファイル`module.xml`を`$WILDFLY_HOME/modules/com/liferay/portal/main`フォルダに作成し、すべての依存関係を宣言します：

    ``` xml
    <?xml version="1.0"?>

    <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
        <resources>
            <resource-root path="com.liferay.petra.concurrent.jar" />
            <resource-root path="com.liferay.petra.executor.jar" />
            <resource-root path="com.liferay.petra.function.jar" />
            <resource-root path="com.liferay.petra.io.jar" />
            <resource-root path="com.liferay.petra.lang.jar" />
            <resource-root path="com.liferay.petra.memory.jar" />
            <resource-root path="com.liferay.petra.nio.jar" />
            <resource-root path="com.liferay.petra.process.jar" />
            <resource-root path="com.liferay.petra.reflect.jar" />
            <resource-root path="com.liferay.petra.string.jar" />
            <resource-root path="com.liferay.registry.api.jar" />
            <resource-root path="hsql.jar" />
            <resource-root path="[place your database driver here]" />
            <resource-root path="portal-kernel.jar" />
            <resource-root path="portlet.jar" />
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

    示された `resource-root` 要素をデータベースのドライバーJARに置き換えます。

4.  [Liferay Home](../../reference/liferay-home.md) フォルダーに `osgi` フォルダーを作成します。 `[Liferay Home]/osgi`フォルダにダウンロードしたOSGi依存関係ZIPファイルを抽出します。

    `osgi`フォルダは、DXPのOSGiランタイムに必要なモジュールを提供します。

**チェックポイント:**

1.  依存関係のzipの内容は、`$WILDFLY_HOME/modules/com/liferay/portal/main`フォルダに配置されています:
2.  データベースベンダーのJDBCドライバが`$WILDFLY_HOME/modules/com/liferay/portal/main`フォルダに配置され、依存関係としてリストされています。
3.  `module.xml` は `<resource-root>` 要素内のすべての JAR をリストしています。
4.  OSGiの依存関係は、 `${Liferay.home}` フォルダー内にある `osgi` フォルダーに解凍されています。

### WildFlyでのスタンドアロンモードとドメインモードのDXPの実行

WildFlyは、 *スタンドアロン* モードまたは *ドメイン* モードのいずれかで起動できます。 ドメインモードでは、単一のコントロールポイントから複数のアプリケーションサーバーインスタンスを管理できます。 このようなアプリケーションサーバーのコレクションは、 *ドメイン*と呼ばれます。 スタンドアロンモードとドメインモードの詳細については、 [WildFly管理ガイド](https://docs.jboss.org/author/display/WFLY/Admin+Guide#AdminGuide-Operatingmodes)このトピックに関するセクションを参照してください。 DXPは、スタンドアロンモードではWildFlyを完全にサポートしますが、ドメインモードではサポートしません。

管理者はドメインモードのWildFlyでDXPを実行できますが、この方法は完全にはサポートされていません。 特に、WildFlyがファイル（展開または非展開）をコピーすることによって管理された展開のコンテンツを管理するため、DXPの自動展開は管理された展開では機能しません。 これにより、JSPフックとExtプラグインが意図したとおりに機能しなくなります。 たとえば、DXPのJSPオーバーライドメカニズムはアプリケーションサーバーに依存しているため、JSPフックは管理対象ドメインモードで実行されているWildFlyでは機能しません。 ただし、JSPフックとExtプラグインは非推奨であるため、使用していない可能性があります。

コマンドラインインターフェイスは、ドメインモードの展開に推奨されます。

``` note::
   This does not prevent DXP from running in a clustered environment on multiple WildFly servers. Administrators can set up a cluster of DXP instances running on WildFly servers running in standalone mode. Please refer to the `DXP clustering articles <../../setting-up-liferay/clustering-for-high-availability.md>`_ for more information.
```

## WildFlyの構成

WildFlyがDXPを実行するように構成するには、次のものが含まれます。

  - 環境変数を設定する
  - プロパティと記述子の設定
  - 不要な構成を削除する

`$WILDFLY_HOME/standalone/configuration/standalone.xml`に以下の変更を加えます。

1.  `</extensions>` の終了タグを見つけます。 そのタグのすぐ下に、次のシステムプロパティを挿入します。

    ``` xml
    <system-properties>
        <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
        <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
    </system-properties>
    ```

2.  `<level name="INFO"/>` タグのすぐ下にある `<console-handler>` タグ内に次の `<filter-spec>` タグを追加します`
<pre><code class="xml">    <filter-spec value="not(any(match(&quot;WFLYSRV0059&quot;),match(&quot;WFLYEE0007&quot;)))" />
`</pre>

3.  以下の抜粋に示すように、`deployment-timeout="600"`を設定することで、デプロイメントスキャナのタイムアウトを追加します。

    ``` xml
    <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
        <deployment-scanner deployment-timeout="600" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000" runtime-failure-causes-rollback="${jboss.deployment.scanner.rollback.on.failure:false}"/>
    </subsystem>
    ```

4.  要素 `<subsystem xmlns="urn:jboss:domain:security:2.0">`定義されたセキュリティサブシステム `<security-domains>` 次のJAASセキュリティドメインを追加します。

    ``` xml
    <security-domain name="PortalRealm">
        <authentication>
            <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
        </authentication>
    </security-domain>
    ```

5.  ウェルカムコンテンツのコードスニペットを削除します。

    ``` xml
    <location name="/" handler="welcome-content"/>
    ```

    そして

    ``` xml
    <handlers>
        <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
    </handlers>
    ```

**チェックポイント:**

続行する前に、次のプロパティが `standalone.xml` ファイルに設定されていることを確認してください。

1.  新しい `<system-property>` が追加されます。
2.  新しい `<filter-spec>` が追加されます。
3.  `<deployment-timeout>` は `600`設定されます。
4.  新しい `<security-domain>` が作成されます。
5.  ウェルカムコンテンツが削除されます。

次に、JVMと起動スクリプトを構成します。

`$WILDFLY_HOME/bin/`フォルダで、スタンドアロンドメインの設定スクリプトファイル`standalone.conf`(`standalone.conf.bat`Windowsの場合):

  - ファイルエンコーディングを `UTF-8` に設定します。
  - ユーザーのタイムゾーンを`GMT`に設定します
  - 優先プロトコルスタックを設定
  - 利用可能なデフォルトのメモリ容量を増やします。

<!-- end list -->

``` important::
   DXPが適切に機能するには、アプリケーションサーバーJVMがGMTタイムゾーンとUTF-8ファイルエンコーディングを使用する必要があります。
```

それぞれのオペレーティングシステムに応じて、次の編集を行います。

**Windows:**

1.  最初の `JAVA_OPTS` 割り当てをコメント化します。

    ``` bash
    rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2560m"
    ```

2.  ファイルの最後に見つかった`:JAVA_OPTS`の上に1行目の`JAVA_OPTS_SET`を追加します。

    ``` bash
    set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=200m"
    ```

**Unix:**

1.  `if [ "x$JAVA_OPTS" = "x" ];` 文の下に、この `JAVA_OPTS` 文を置き換えます。

    ``` bash
    JAVA_OPTS="-Xms64m -Xmx512m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m -Djava.net.preferIPv4Stack=true"
    ```

    これとともに：

    ``` bash
    JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
    ```

2.  ファイルの最後に次のステートメントを追加します。

    ``` bash
    JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=200m"
    ```

これによりファイルエンコーディングを UTF-8 に設定し、IPv6 よりも IPv4 スタックを優先します。 タイムゾーンをGMTに設定し、JVM 2GBのRAMを与え、Metaspaceを512MBに制限します。

JDK 11では、このJVM引数を追加して4桁の年を表示します。

``` bash
-Djava.locale.providers=JRE,COMPAT,CLDR
```

インストール後、パフォーマンスのためにシステム(これらのJVMオプションを含む)を調整します。

IBM JDK と WildFly サーバーを使用する場合は、以下の追加手順を完了してください。

1.  `$WILDFLY_HOME/modules/com/liferay/portal/main/module.xml` ファイルに移動し、 `<dependencies>` 要素内に次の依存関係を挿入します。

    `<module name="ibm.jdk" />`

2.  `$WILDFLY_HOME/modules/system/layers/base/sun/jdk/main/module.xml` ファイルに移動し、`<paths>...</paths>` 要素内に次のパス名を挿入します。

<!-- end list -->

``` xml
     <path name="com/sun/crypto" />
     <path name="com/sun/crypto/provider" />
     <path name="com/sun/org/apache/xml/internal/resolver" />
     <path name="com/sun/org/apache/xml/internal/resolver/tools" />
```

追加されたパスは、デプロイメントの例外およびイメージのアップロードの問題に関する問題を解決します。

**チェックポイント:**

1.  ファイルエンコーディング、ユーザーのタイムゾーン、優先プロトコルスタックは、`スタンドアロン.conf.bat`ファイルの`JAVA_OPTS`で設定されています。
2.  利用可能なメモリのデフォルト量が増加しました。

これで、WildFlyにDXPをインストールするための規定のスクリプト変更が完了しました。

## データベースに接続する

データベース構成を処理する最も簡単な方法は、DXPにデータソースを管理させることです。 [基本構成](../../../getting-started/using-the-setup-wizard.md) を使用して、DXPの組み込みデータソースを構成します。 組み込みのデータソースを使用する場合は、このセクションをスキップしてください。

WildFlyを使用してデータソースを管理する場合は、次の手順に従います。

1.  `$WILDFLY_HOME/standalone/configuration/standalone.xml` ファイルの `<datasources>` 要素内にデータソースを追加します。

    ``` xml
    <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
        <connection-url>[place the URL to your database here]</connection-url>
        <driver>[place your driver name here]</driver>
        <security>
            <user-name>[place your user name here]</user-name>
            <password>[place your password here]</password>
        </security>
    </datasource>
    ```

    データベースのURL、ユーザー名、パスワードを適切な値に置き換えてください。

    ``` note::
       If the data source ``jndi-name`` must be changed, edit the ``datasource`` element in the ``<default-bindings>`` tag.
    ```

2.  `<datasources>` 要素内にもある `standalone.xml` ファイルの `<drivers>` 要素にドライバーを追加します。

    ``` xml
    <drivers>
        <driver name="[name of database driver]" module="com.liferay.portal">
            <driver-class>[JDBC driver class]</driver-class>
        </driver>
    </drivers>
    ```

    MySQLを使用する最終的なデータソースサブシステムは次のようになります。

    ``` xml
    <subsystem xmlns="urn:jboss:domain:datasources:1.0">
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
                <driver name="mysql" module="com.liferay.portal">
                    <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
                </driver>
            </drivers>
        </datasources>
    </subsystem>
    ```

3.  Liferay Homeフォルダーの [`portal-ext.properties`](../../reference/portal-properties.md) ファイルで、データソースを指定します。

    ``` properties
    jdbc.default.jndi.name=java:jboss/datasources/ExampleDS
    ```

これでデータソースが構成され、準備が整いました。

## メールサーバーに接続する

データベース構成と同様に、メールを構成する最も簡単な方法は、DXPにメールセッションを処理させることです。 あなたはDXPの内蔵メールセッションを使用したい場合は、このセクションと飛ばし [configureがメールセッション](../../setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md) コントロールパネルの。

WildFlyでメールセッションを管理する場合は、次の手順に従います。

1.  次のように `$WILDFLY_HOME/standalone/configuration/standalone.xml` ファイルでメールサブシステムを指定します。

    ``` xml
    <subsystem xmlns="urn:jboss:domain:mail:3.0">
        <mail-session jndi-name="java:jboss/mail/MailSession" name="mail-smtp">
            <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp" username="USERNAME" password="PASSWORD"/>
       </mail-session>
    </subsystem>
    ...
    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    ...
    <outbound-socket-binding name="mail-smtp">
            <remote-destination host="[place SMTP host here]" port="[place SMTP port here]"/>
        </outbound-socket-binding>
    </socket-binding-group>
    ```

2.  Liferay Homeの [`portal-ext.properties`](../../reference/portal-properties.md) ファイルで、メールセッションを参照します。

    ``` properties
    mail.session.jndi.name=java:jboss/mail/MailSession
    ```

## DXPのデプロイ

1.  フォルダー `$WILDFLY_HOME/standalone/deployments/ROOT.war` がWildFlyインストールにすでに存在する場合は、そのサブフォルダーとファイルをすべて削除します。 それ以外の場合は、 `$WILDFLY_HOME/standalone/deployments/ROOT.war`という名前の新しいフォルダーを作成します。
2.  DXP `.war` ファイルを `ROOT.war` フォルダーに解凍します。
3.  `ROOT.war`デプロイメントをトリガーするには、 `$WILDFLY_HOME/ standalone/deployments /` フォルダーに `ROOT.war.dodeploy` という名前の空のファイルを作成します。 起動時に、WildFlyはこのファイルを検出して、Webアプリケーションとして展開します。
4.  `$WILDFLY_HOME/ bin` 、 `standalone.bat` または `standalone.sh`を実行して、WildFlyアプリケーションサーバーを起動します。

<!-- end list -->

``` note::
   After deploying DXP, you may see excessive warnings and log messages, such as the ones below, involving ``PhaseOptimizer``. これらは良性なので無視することができます。 Make sure to adjust your app server's logging level or log filters to avoid excessive benign log messages.
```


    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass gatherExternProperties
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass checkControlFlow
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
    current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest

## 次のステップ

[管理者ユーザーとしてサインイン](../../../getting-started/introduction-to-the-admin-account.md)して、[DXPでソリューションの構築](../../../building-solutions-on-dxp/README.md)を開始できます。 または、[Liferay DXPのその他のセットアップ](../../setting-up-liferay/setting-up-liferay.md)トピックを参照できます。

  - [マーケットプレイスプラグインのインストール](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
  - [試用期間中のEEプラグインへのアクセス](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
  - [検索エンジンのインストール](../../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md)
  - [Securing Liferay DXP](../../securing-liferay/introduction-to-securing-liferay.md)
  - [高可用性のクラスタリング](../../setting-up-liferay/clustering-for-high-availability.md)
