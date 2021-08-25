# JBoss EAPへのインストール

JBoss EAPにインストールするには、依存関係のデプロイ、スクリプトの変更、config `xml` ファイルの変更、およびDXP WARファイルのデプロイが必要です。 データベースとメールサーバーの接続も設定する必要があります。

Liferay DXPにはJava JDK 8または11が必要です。 参照 [互換性マトリックス](https://www.liferay.com/documents/10182/246659966/Liferay+DXP+7.2+Compatibility+Matrix.pdf/ed234765-db47-c4ad-7c82-2acb4c73b0f9) 詳細については。

これらのファイルを [ヘルプセンター](https://customer.liferay.com/downloads) （サブスクリプション）または [Liferayコミュニティダウンロード](https://www.liferay.com/downloads-community)からダウンロードします。

  - DXP WARファイル
  - 依存関係のZIPファイル
  - OSGi依存関係のZIPファイル

[*Liferay Home*](../../reference/liferay-home.md) は、JBossサーバーフォルダーを含むフォルダーです。 DXPをインストールして展開した後、Liferayのホームフォルダは、JBossサーバのフォルダと同様に含ま `データ`、 `配布`、 `ログ`、及び `のOSGi` フォルダを。 `$JBOSS_HOME` はJBossサーバーフォルダーを参照します。 このフォルダーの名前は通常 `jboss-eap-[version]`です。

JBoss EAPにDXPをインストールするには、次の手順を実行します。

1.  [アプリケーションサーバーへの依存関係のインストール](#installing-dependencies)
2.  [DXP用のアプリケーションサーバーの設定](#configuring-jboss)
3.  [データベースに接続する](#connect-to-a-database)
4.  [メールサーバーに接続する](#connect-to-a-mail-server)
5.  [DXP WARファイルをアプリケーションサーバーにデプロイする](#deploying-dxp)

## 依存関係をインストールする

1.  $JBOSS_HOMEフォルダー`$JBOSS_HOME/modules/com/liferay/portal/main` が存在しない場合は作成し、依存関係ZIPからJARをこのフォルダーに抽出します。

2.  データベースベンダーのJDBC .jarファイルを同じ場所にコピーします。 サポートされるデータベースのリストについては、 [互換性マトリックス](https://www.liferay.com/documents/10182/246659966/Liferay+DXP+7.2+Compatibility+Matrix.pdf/ed234765-db47-c4ad-7c82-2acb4c73b0f9) を参照してください。

3.  `$JBOSS_HOME/modules/com/liferay/portal/main`  フォルダーにファイル `module.xml` 作成し、すべての依存関係を宣言します。

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
            <resource-root path="[place your database vendor's jar here]" />
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

    `[データベースベンダーのjarをここに配置]` をデータベースのドライバーJARに置き換えます。

4.  [Liferay Home](../../reference/liferay-home.md) フォルダーに `osgi` フォルダーを作成します。 ダウンロードしたOSGi Dependencies ZIPファイルを `[Liferay Home]/osgi` フォルダーに解凍します。

    `osgi` フォルダーは、DXPのOSGiランタイムに必要なモジュールを提供します。

**チェックポイント:**

1.  依存関係ファイルは`$JBOSS_HOME/modules/com/liferay/portal/main` フォルダーとデータベースjarに解凍されています。
2.  `module.xml` は、 `<resource-root-path>` 要素内のすべてのJARが含まれています。
3.  `osgi` 依存関係が `osgi` フォルダーに解凍されました。

### スタンドアロンモードとドメインモードのJBoss EAPでのDXPの実行

JBoss EAPは、 *スタンドアロン* モードまたは *ドメイン* モードのいずれかで起動できます。 ドメインモードでは、単一のコントロールポイントから複数のアプリケーションサーバーインスタンスを管理できます。 このようなアプリケーションサーバーのコレクションは、 *ドメイン*と呼ばれます。 ドメインモード対スタンドアロンモードの詳細については、このトピックに関するセクションを参照してください [のJBoss EAP製品ドキュメント](https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.1/html/introduction_to_jboss_eap/overview_of_jboss_eap#operating_modes)。

DXPは、スタンドアロンモードで実行する場合はJBoss EAPをサポートしますが、ドメインモードで実行する場合はサポートしません。 JBossはファイル（展開または非展開）をコピーして管理対象デプロイメントのコンテンツを管理するため、DXPの自動展開は管理対象デプロイメントでは機能しません。 これにより、JSPフックとExtプラグインが意図したとおりに機能しなくなります。 たとえば、DXPのJSPオーバーライドメカニズムはアプリケーションサーバーに依存しているため、JSPフックは管理対象ドメインモードで実行されているJBoss EAPでは機能しません。 ただし、JSPフックとExtプラグインは非推奨であるため、使用していない可能性があります。

コマンドラインインターフェイスは、ドメインモードの展開に推奨されます。

``` note::
   This does not prevent DXP from running in a clustered environment on multiple JBoss servers. You can set up a cluster of DXP instances running on JBoss EAP servers running in standalone mode. Please refer to the `DXP clustering articles <../../setting-up-liferay/clustering-for-high-availability.md>`_ for more information.
```

## JBossの構成

DXPを実行するためのJBossの設定には、次のものが含まれます。

  - 環境変数を設定する
  - プロパティと記述子の設定
  - 不要な構成を削除する

`$JBOSS_HOME/standalone/configuration/standalone.xml`次の変更を加えます。

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

3.  以下の抜粋に示すように、 `deployment-timeout = "600"` を設定して、デプロイメントスキャナーのタイムアウトを追加します。

    ``` xml
    <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
        <deployment-scanner deployment-timeout="600" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
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
3.  `<deployment-timeout>` は `360`に設定されます。
4.  新しい `<security-domain>` が作成されます。
5.  ウェルカムコンテンツが削除されます。

次に、JVMと起動スクリプトを設定します。

`$JBOSS_HOME/ bin /` フォルダーで、スタンドアロンドメインの構成スクリプトファイル `standalone.conf` （Windowsでは`standalone.conf.bat` します。

  - ファイルのエンコーディングを `設定UTF-8`
  - ユーザーのタイムゾーンを `GMT`設定します
  - 優先プロトコルスタックを設定する
  - 利用可能なデフォルトのメモリ容量を増やします。

<!-- end list -->

``` important::
   DXPが適切に機能するには、アプリケーションサーバーJVMがGMTタイムゾーンとUTF-8ファイルエンコーディングを使用する必要があります。
```

それぞれのオペレーティングシステムに応じて、次の編集を行います。

**Windows:**

1.  次の行に示すように、最初の `JAVA_OPTS` 割り当てをコメント化します。

    ``` bash
    rem set "JAVA_OPTS=-Xms1G -Xmx1G -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2560m"
    ```

2.  ファイルの最後にある `：JAVA_OPTS_SET` 行の1行上に次の `JAVA_OPTS` 割り当てを追加します。

    ``` bash
    set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=768m"
    ```

**Unix:**

1.  `下[[x$JAVA_OPTS"=" x "];` ステートメント、この `JAVA_OPTS` ステートメントを置き換えます：

    ``` bash
    JAVA_OPTS="-Xms1303m -Xmx1303m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2560m -Djava.net.preferIPv4Stack=true"
    ```

    これとともに：

    ``` bash
    JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
    ```

2.  ファイルの最後に次のステートメントを追加します。

    ``` bash
    JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=512m"
    ```

    JDK 11では、このJVM引数を追加して4桁の年を表示します。

    ``` bash
    -Djava.locale.providers=JRE,COMPAT,CLDR
    ```

    ``` note::
       JBossサーバーでIBM JDKを使用する場合は、以下の追加手順を実行します。
    ```

3.  `$JBOSS_HOME/modules/com/liferay/portal/main/module.xml` ファイルに移動し、 `<dependencies>` 要素内に次の依存関係を挿入します。

    `<module name="ibm.jdk" />`

4.  `$JBOSS_HOME/modules/system/layers/base/sun/jdk/main/module.xml` ファイルに移動し、次のパス名を `<paths>...</paths>` 要素内に挿入します。

<!-- end list -->

``` xml
    <path name="com/sun/crypto" />
    <path name="com/sun/crypto/provider" />
    <path name="com/sun/image/codec/jpeg" />
    <path name="com/sun/org/apache/xml/internal/resolver" />
    <path name="com/sun/org/apache/xml/internal/resolver/tools" />
```

追加されたパスは、ポータルのデプロイメントの例外と画像のアップロードの問題を解決します。

**チェックポイント:**

1.  ファイルのエンコーディング、ユーザーのタイムゾーン、優先プロトコルスタックは、 `standalone.conf.bat` ファイルの `JAVA_OPTS` に設定されています。

2.  利用可能なメモリのデフォルト量が増加しました。

これで、JBossにDXPをインストールするための規定のスクリプト変更が完了しました。

## データベースに接続する

データベース構成を処理する最も簡単な方法は、DXPにデータソースを管理させることです。 管理者は [基本構成](../../../getting-started/using-the-setup-wizard.md) を使用して、DXPの組み込みデータソースを構成できます。 組み込みのデータソースを使用する場合は、このセクションをスキップしてください。

JBossを使用してデータソースを管理する場合は、次の手順に従います。

1.  `$JBOSS_HOME/standalone/configuration/standalone.xml` ファイルの `<datasources>` 要素内にデータソースを追加します。

    ``` xml
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

    ``` note::
       If the data source ``jndi-name`` must be changed, edit the ``datasource`` element in the ``<default-bindings>`` tag.
    ```

2.  `<datasources>` 要素内にもある `standalone.xml` ファイルの `<drivers>` 要素にドライバーを追加します。

    ``` xml
    <drivers>
        <driver name="[name of driver must match name above]" module="com.liferay.portal">
            <driver-class>[place your JDBC driver class here]</driver-class>
        </driver>
    </drivers>
    ```

    MySQLを使用する最終的なデータソースサブシステムは次のようになります。

    ``` xml
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

3.  Liferayホームの [`portal-ext.properties`](../../reference/portal-properties.md) ファイルで、データソースを指定します。

    ``` properties
    jdbc.default.jndi.name=java:jboss/datasources/ExampleDS
    ```

これで、データソースが構成され、データベースに接続する準備ができました。

## メールサーバーに接続する

データベース構成と同様に、メールを構成する最も簡単な方法は、DXPにメールセッションを処理させることです。 DXPの内蔵メールセッションを使用したい場合は、このセクションと飛ばしコントロールパネルにある [DXPをメールサーバーに接続](../../setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md) に行きます。

JBossとのメールセッションを管理する場合は、次の手順に従います。

1.  次のように、 `$JBOSS_HOME/standalone/configuration/standalone.xml` ファイルでメールサブシステムを指定します。

    ``` xml
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

2.  Liferayホームの `portal-ext.properties` ファイルで、メールセッションを参照します。

    ``` properties
    mail.session.jndi.name=java:jboss/mail/MailSession
    ```

## DXPのデプロイ

1.  フォルダー `$JBOSS_HOME/standalone/deployments/ROOT.war` がJBossインストールにすでに存在する場合は、そのサブフォルダーとファイルをすべて削除します。 それ以外の場合は、 `$JBOSS_HOME/standalone/deployments/ROOT.war`という名前の新しいフォルダーを作成します。
2.  DXP `.war` ファイルを `ROOT.war` フォルダーに解凍します。
3.  `ROOT.war`デプロイメントをトリガーするには、 `$JBOSS_HOME/ standalone/deployments /` フォルダーに `ROOT.war.dodeploy` という名前の空のファイルを作成します。 起動時に、JBossはこのファイルを検出し、Webアプリケーションとしてデプロイします。
4.  `$JBOSS_HOME/ bin` 、 `standalone.bat` または `standalone.sh`を実行して、JBossアプリケーションサーバーを起動します。

DXPの導入後、 `PhaseOptimizer`含む以下のような警告やログメッセージが過剰に表示される場合があります。 これらは良性なので無視することができます。 このようなログメッセージを回避するために、必ずアプリケーションサーバーのログレベルまたはログフィルターを調整してください。

    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass gatherExternProperties
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass checkControlFlow
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
    current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
