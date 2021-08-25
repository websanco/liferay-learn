# WebSphereへのインストール

WebSphereにLiferay DXPをインストールするには、DXP WARファイルをデプロイし、DXPの依存関係をデプロイし、DXP用にWebSphereを構成する必要があります。

> IBM®WebSphere®はInternational Business Machines Corporationの商標であり、世界中の多くの国で登録されています。

``` tip::
   このインストールおよび設定プロセス全体を通して、WebSphereは[*Save*]をクリックして変更をマスター構成に適用するようにプロンプトを出します。 変更を保存するには、この操作が必要です。
```

## 前提条件

Liferay DXPが正しく機能するには、WebSphere 9（フィックスパック11が最新）がインストールされている必要があります。 このフィックスパックについて詳しくは、[IBMサポート](http://www-01.ibm.com/support/docview.wss?uid=swg24043005)にアクセスしてください。 Liferay DXPは現在、WebSphere Application Liberty Profileをサポートしていません。

``` important::
   Before installing DXP, please review the `Installing a Liferay-Tomcat Bundle <../installing-a-liferay-tomcat-bundle.md>`_ and `Configuring a Database <../configuring-a-database.md>`_ articles.
```

次のファイルは、WebSphereアプリケーションサーバーにLiferay DXPをインストールするために必要であり、[ヘルプセンター](https://customer.liferay.com/downloads)（サブスクリプション）または[Liferayコミュニティのダウンロード](https://www.liferay.com/downloads-community)から入手できます。

  - DXP WARファイル
  - 依存関係のZIPファイル
  - OSGi依存関係のZIPファイル

利用可能なLiferay DXPダウンロードの詳細については、「 [Liferay-Tomcatバンドル](../installing-a-liferay-tomcat-bundle.md) インストール」を参照してください。

[`[Liferay Home]`](../../reference/liferay-home.md) フォルダーは、Liferay DXPが機能するために必要なファイルとフォルダーを格納および管理する場所です。 WebSphereでは、`[Liferay Home]`フォルダは通常`[Install Location]/WebSphere/AppServer/profiles/[your-profile]/liferay`です。

WebSphereにDXPをインストールするための基本的な手順は次のとおりです。

1.  DXPのためのWebSphereの準備
2.  DXP依存関係のインストール
3.  データベース設定
4.  メール設定
5.  HTTPセッションのCookieを有効にする
6.  DXP WARのデプロイ
7.  JSPをコンパイルするためのJDKバージョンの設定

## DXPのためのWebSphereの準備

### WebSphereプロファイルの作成

アプリケーションサーバーのバイナリがインストールされたら、*プロファイル管理ツール*を起動して、DXPに適したプロファイルを作成します。

1.  *[Create...]* をクリックし、*[Application Server]* を選択して、*[Next]* をクリックします。

2.  *アドバンスト*プロファイルの作成オプションをクリックし、*[Next]* をクリックします。 アドバンスト プロファイルを使用して、プロファイルの場所やプロファイルの名前、ノードとホストなどの設定値を指定したり、特定のポートを割り当てたり、オプションで管理コンソールとサンプルアプリケーションをデプロイするかどうか、またWeb IBM HTTP ServerのWebサーバー定義を追加するかどうかを選択します。 これらのオプションの詳細については、WebSphereのドキュメントを参照してください。

    ![図1：アドバンストプロファイルオプションを選択して、独自の設定を指定します。](./installing-on-websphere/images/01.png)

3.  *[Deploy the administrative console]* ボックスをオンにします。 これにより、アプリケーションサーバーを操作するためのWebベースのUIが有効になります。 デフォルトのアプリケーションをスキップします。 （これらは開発マシンにのみインストールしてください。） *次へ*をクリックします。

4.  プロファイル名と場所を設定します。 環境に適したパフォーマンス調整設定を指定します。

    ``` note::
       パフォーマンス調整設定の詳細については、WebSphereのドキュメントを参照してください。 [*Next*]をクリックします。
    ```

5.  サーバーのノード、サーバー、およびホスト名を選択します。 これらはユーザーの環境に固有です。 *[Next]* をクリックします。

6.  WebSphereの管理セキュリティは、管理ツールへのアクセス権を持つユーザーを制限する方法です。 管理者は環境でこの方法を有効にすることで、WebSphereサーバーを管理するにはユーザー名とパスワードが必要となるようにできます。 詳細については、WebSphereのドキュメントを参照してください。 *次へ*をクリックします。

7.  各プロファイルには、ウィザードの次に表示されるセキュリティ証明書が必要です。 証明書をまだ生成していない場合は、個人証明書と署名証明書を生成するオプションを選択し、*[Next]* をクリックします。

8.  証明書が生成されたら、キーストアのパスワードを設定します。 *次へ*をクリックします。

9.  管理者は、このサーバープロファイルが使用するポートをカスタマイズできます。 マシンで開いているポートを選択してください。 ポートを選択すると、ウィザードは既存のWebSphereインストールを自動的に検出し、アクティビティが見つかると、ポートを1つ増やします。

10. マシンの起動時にこのプロファイルを開始するかどうかを選択します。 *[Next]* をクリックします。

11. WebSphereにはIBM HTTP Serverが付属しています。 このJVMがHTTPサーバーから転送された要求を受信できるように、Webサーバー定義が必要かどうかを選択します。 詳細については、WebSphereのドキュメントを参照してください。 完了したら、* [Next]* をクリックします。

12. ウィザードに選択された内容の概要が表示され、管理者は選択内容を保持したり、前の画面に戻って内容を変更したりできます。 完了したら、* [Next]* をクリックします。

WebSphereはプロファイルを作成し、プロファイルが正常に作成されたことを示すメッセージで終了します。

![図2：プロファイルを作成する前の設定例](./installing-on-websphere/images/02.png)

最後に、アプリケーションサーバーをシャットダウンします。

### WebSphereアプリケーションサーバーの構成

``` warning::
   アプリケーションサーバーの実行中は構成変更を行わないでください。
```

このバージョンのWebSphereでは、サーブレットフィルターはWebアプリケーションの起動時ではなく、最初のアクセス時に初期化されます。 これにより、特定のアプリをDXPにデプロイするときに問題が発生する可能性があります。 アプリケーションの起動時（つまり、デプロイ時）に初期化するようにサーブレットフィルターを構成するには、WebSphereアプリケーションサーバーで以下の`webcontainer`プロパティを設定します。

``` properties
com.ibm.ws.webcontainer.initFilterBeforeInitServlet = true
com.ibm.ws.webcontainer.invokeFilterInitAtStartup = true
```

WebSphereアプリケーションサーバーで`webcontainer`プロパティを設定するには、[WebSphereのドキュメント](http://www-01.ibm.com/support/docview.wss?rss=180&uid=swg21284395)の指示に従ってください。

### Liferay DXPのJVMパラメータの設定

次に、WebSphereプロファイルで、DXPのJVM要件をサポートする引数を設定します。 次のファイルを変更します。

`[Install Location]/WebSphere/AppServer/profiles/your-profile/config/cells/your-cell/nodes/your-node/servers/your-server/server.xml`

ベースラインとして、`jvmEntries`タグ内に`maximumHeapSize="2560"`を追加します。 たとえば、

    <jvmEntries xmi:id="JavaVirtualMachine_1183122130078" ... maximumHeapSize="2560">

``` note::
   ここで使用されるJVMパラメーターは、本番環境システムの初期デプロイを意図したデフォルトです。 管理者は、特定の環境に最適な値に設定を変更する必要があります。 これらは、必要に応じて調整する必要があります。
```

管理者は、`server.xml`の`<jvmEntries genericJvmArguments=.../>`属性でUTF-8プロパティを設定できます。 これは必須です。設定しないと、国際文字は正しく解析されません。 最大および最小ヒープサイズを`2560m`に設定します。 `jvmEntries`タグ内に以下を追加します。

``` xml
<jvmEntries xmi:id="JavaVirtualMachine_1183122130078" ...genericJvmArguments="-Dfile.encoding=UTF-8 -Duser.timezone=GMT -Xms2560m -Xmx2560m">
```

``` important::
   DXPが適切に機能するには、アプリケーションサーバーJVMがGMTタイムゾーンとUTF-8ファイルエンコーディングを使用する必要があります。
```

あるいは、WebSphere管理コンソールからUTF-8プロパティを設定します。 （下記参照。）

### `secureSessionCookie`タグの削除

同じプロファイルで、DXP起動エラーの原因となる可能性のある`secureSessionCookie`タグを削除します。 これは単なるデフォルト設定であることに注意してください。 DXPがインストールされたら、使用環境に基づいて適切に調整する必要があります。

`[Install Location]/WebSphere/AppServer/profiles/your-profile/config/cells/your-cell/cell.xml`で、`xmi:id="SecureSessionCookie_1"`を含む`secureSessionCookie`タグを削除します。

このタグを削除しないと、次のようなエラーが発生する可能性があります。

    WSVR0501E: Error creating component com.ibm.ws.runtime.component.CompositionUnitMgrImpl@d74fa901
    com.ibm.ws.exception.RuntimeWarning: com.ibm.ws.webcontainer.exception.WebAppNotLoadedException: Failed to load webapp: Failed to load webapp: SRVE8111E: The application, LiferayEAR, is trying to modify a cookie which matches a pattern in the restricted programmatic session cookies list [domain=*, name=JSESSIONID, path=/].

### 構成チェックポイント

この時点で、次の手順が完了している必要があります。

1.  WebSphereアプリケーションサーバーのプロファイルが作成されている。
2.  サーブレットフィルターが、`webcontainer`設定でアプリケーションの起動時に初期化するように構成されている。
3.  JVMパラメータが`server.xml`ファイルに設定されている。
4.  ファイルのエンコーディングとしてUTF-8が設定されている。
5.  サーバーのタイムゾーンがGMTに設定されている。
6.  `secureSessionCookie`タグが削除されている。

## DXP依存関係のインストール

1.  依存関係のZIPファイルを解凍し、そのコンテンツをWebSphereアプリケーションサーバーの`[Install Location]/WebSphere/AppServer/lib/ext`フォルダに配置します。 使用されているデータベースに適切なJDBCコネクタJARをこの場所にも追加します。
2.  同じアーカイブから、WebSphere 9.0.0.xの`[Install Location]/WebSphere/AppServer/javaext`に`portlet.jar`をコピーします。 WebSphereには、古いバージョンの `portlet.jar` がすでに含まれており、最高レベルのクラスローダーレベルでオーバーライドする必要があります。 新しい`portlet.jar`（バージョン3）には下位互換性があります。
3.  OSGi依存関係ZIPファイルを解凍し、その内容を`[Liferay Home]/osgi`フォルダに置きます(まだ存在しない場合は、このフォルダを作成します)。 通常、これは`[Install Location]/WebSphere/AppServer/profiles/your-profile/liferay/osgi`です。

DXPはJDBCを介してデータベースと通信します。 データベースJDBCドライバーのJARファイルをユーザードメインのlibフォルダに追加します。 次のデータベース用のJDBCドライバーのJARをダウンロードできます。

  - [MariaDB](https://downloads.mariadb.org/)
  - [MySQL](http://dev.mysql.com/downloads/connector/j)
  - [PostgreSQL](https://jdbc.postgresql.org/download/postgresql-42.0.0.jar)

HypersonicデータベースはDXPにバンドルされており、テスト目的には問題ありませんが、本番環境のDXPインスタンスには使用しないでください。

### DXP Portlet.jarが最初に読み込まれるようにする

`portlet.jar`を正しいフォルダに配置することに加えて、`config.ini`ファイルを最初にロードされるように構成します。 `/IBM/WebSphere/AppServer/configuration/config.ini`に移動します。

1.  プロパティ`com.ibm.CORBA,com.ibm`を検索します
2.  プロパティ`javax.portlet,javax.portlet.filter,javax.portlet.annotations`を`com.ibm.CORBA`の後、`com.ibm`の前に挿入します。
3.  ファイルを保存します。

### 依存関係チェックポイント

1.  DXP依存関係がインストールされている。
2.  `config.ini`ファイルが設定されている。

アプリケーションサーバープロファイルを起動します。

## データベース設定

DXPには組み込みのHypersonicデータベースが含まれています。これはデモンストレーション目的には最適ですが、本番環境では使用しないでください。 デモの目的以外に、フル機能のサポートされているRDBMSを使用することをお勧めします。 データベースのセットアップについては、[Configuring a Database](../configuring-a-database.md)を参照してください。

Liferay DXPは、DXPに組み込まれているデータソースを使用する（推奨）か、アプリケーションサーバー上に作成したデータソースを使用してデータベースに接続できます。

初めてDXPを実行するときにDXPの組み込みデータソースをデータベースで設定するには、[セットアップウィザード](../../../getting-started/using-the-setup-wizard.md)を使用します。 または、データベースの [データベーステンプレート](../../reference/database-templates.md) 基づいて、データソースを [`portal-ext.properties` ファイル](../../reference/portal-properties.md)で構成できます。

WebSphereを使用してデータベース接続を管理する場合は、以下の手順に従ってください。 それ以外の場合、DXPの組み込みデータソースを使用する場合は、このセクションをスキップしてください。

``` warning::
   Liferayは、デモ目的でデフォルトでHSQLを使用しています。 HSQLは、Liferay DXPの本番環境インスタンスでは使用*しない*でください。
```

![図3：WebSphere JDBCプロバイダー](./installing-on-websphere/images/03.png)

1.  WebSphereを起動します。

2.  管理コンソールを開き、ログインします。

3.  *[Resources] → [JDBC Providers]* をクリックします。

4.  スコープを選択し、*[New]* をクリックします。

5.  データベースの種類、プロバイダーの種類、実装の種類を選択します。 事前定義されたデータベースを選択すると、ウィザードによって名前と説明のフィールドが自動的に入力されます。 目的のデータベースがリストにない場合は、*[Database type]* フィールドから*[User-defined]* を選択して、*[Implementation Class Name]* を入力します。 たとえば、MySQLを使用する場合は、*データベースの種類*→ *ユーザー定義*を選択し、`com.mysql.jdbc.jdbc2.optionalと入力します。MysqlConnectionPoolDataSource`の*実装クラス名*。 *次へ*をクリックします。

6.  クラスパス設定のテキストをすべてクリアします。 必要なJARは、サーバーのクラスパス上の場所にすでにコピーされています。 *次へ*をクリックします。

7.  設定を確認し、*[Finish]* をクリックします。 最終的な構成は次のようになります。

    ![図4：完成したJDBCプロバイダーの構成。](./installing-on-websphere/images/04.png)

8.  新しいプロバイダー構成が表に表示されたらクリックします。

9.  *[Additional Properties]* の下の*[Data Sources]* をクリックします。

10. *[New]* をクリックします。

11. *[Data source name]* フィールドに`liferaydatabasesource`と入力し、*[JNDI name]* フィールドに`jdbc/LiferayPool`と入力します。 *次へ*をクリックします。

12. ウィザードの残りの画面で*[Next]* をクリックして、デフォルト値を受け入れます。 次にすべての変更を確認し、*[Finish]* をクリックします。

13. データソースが表に表示されたらクリックし、*[Custom Properties]* をクリックします。

14. *[Show Filter Function]* ボタンをクリックします。 このボタンは、*[New]* ボタンと*[Delete]* ボタンの下にある小さいアイコンの最後から2番目です。

15. *user*を検索語句に入力し、*[Go]* をクリックします。

    ![図5：WebSphereでのデータソースプロパティの変更](././installing-on-websphere/images/05.png)

16. *user*プロパティを選択し、ユーザー名の値をデータベースに付与します。

17. *[OK]* をクリックして、マスター構成に保存します。

18. *url*プロパティに対して別のフィルター検索を実行します。 このプロパティに、データベースを指す値を指定します。 たとえば、MySQLのURLは次のようになります。

    ``` properties
    jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    ```

    ``` tip::
       URLの例については、 `Database Templates <../../reference/database-templates.md>`_ の`jdbc.default.url`の値を参照してください。
    ```


    [*OK*]をクリックして、マスター構成に保存します。

19. *password*プロパティに対して別のフィルター検索を実行します。 このプロパティの値として、前に追加したユーザーIDのパスワードを入力します。 *[OK]* をクリックして、マスター構成に保存します。

20. パンくずリストでデータソースページをクリックして、そのページに戻ります。 *[Test Connection]* ボタンを使用して、この時点までの構成を検証します。

## メール設定

DXPの組み込みメールセッションを使用する場合は、このセクションをスキップしてください。 DXPの組み込みメールセッションの使用方法については、[Configuring Mail](../../setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md)の記事をご覧ください。

WebSphereを使用してメールセッションを管理する場合は、次の手順に従います。

### WebSphere管理のメールセッションの作成

1.  *[Resources] → [Mail] → [Mail Providers]* をクリックします。

2.  ノードとサーバーの組み込みメールプロバイダーをクリックします。

3.  *[Mail Sessions]* をクリックし、*[New]* ボタンをクリックします。

4.  メールセッションに`liferaymail`の名前と`mail/MailSession`のJNDI名を付けます。 *[Outgoing Mail Properties]* セクションと*[Incoming Mail Properties]* セクションにメールサーバーの正しい情報を入力します。 *[OK]* をクリックして、マスター構成に保存します。

5.  表に表示されたメールセッションをクリックし、*[Additional Properties]* セクションの下で*[Custom Properties]* を選択します。 プロトコル、ポート、SSLを使用するかどうかなど、メールサーバーに必要なその他のJavaMailプロパティを設定します。

6.  *[Security] → [Global Security]* をクリックし、*[Use Java 2 security to restrict application access to local resources]* が選択されている場合は選択を解除します。

    ![図6：メールセッションでのJavaセキュリティの適用](./installing-on-websphere/images/06.png)

7.  *[Apply]* をクリックします。

メールサーバーからSSL証明書を取得し、それをWebSphereのトラストストアに追加する必要がある場合があることに注意してください。 この手順については、WebSphereのドキュメントを参照してください。

### WebSphereメールプロバイダーの検証

メールセッションが正しく構成されていることを検証するには、WARがデプロイされ、サーバーが起動し、ユーザーがシステム管理者としてサインインした後で、これをテストする方法がいくつかあります。 検証する簡単な方法の1つは、有効なメールアカウントで新しいユーザーを作成することです。 新しく作成されたユーザーは、電子メール通知を受信します。 ログには、リストされている正しいポート番号でSMTPサーバーがpingされていることが表示されます。

## HTTPセッションのCookieを有効にする

WebSphereでは、デフォルトでCookieをHTTPSセッションに制限しています。 HTTPを使用している場合、ユーザーはDXPにサインインできなくなり、コンソールに次のエラーが表示されます。

    20:07:14,021 WARN  [WebContainer : 1][SecurityPortletContainerWrapper:341]
    User 0 is not allowed to access URL http://localhost:9081/web/guest/home and portlet com_liferay_login_web_portlet_LoginPortlet

これは、HTTPの使用時にDXPがHTTPS Cookieを使用できないために発生します。 その結果、ページが更新されるたびに新しいセッションが作成されます。 WebSphereでこの問題を解決するには、以下の手順に従ってください。

1.  *[Application Servers]* → *[server1]* → *[Session Management]* → *[Enable Cookies]* の順にクリックします。
2.  *[Restrict cookies to HTTPS sessions]* の選択を解除します。
3.  *[Apply]* をクリックします。
4.  *[保存]* をクリックします。

## UTF-8を有効にする

`server.xml`に`-Dfile.encoding=UTF-8`プロパティを追加してもUTF-8が有効になっていない場合、管理者は管理コンソールでも有効にすることができます。

1.  *[Application Servers]* → *[server1]* → *[Process definition]* の順にクリックします。
2.  *[Additional Properties]* の下の*[Java Virtual Machine]* をクリックします。
3.  *[Generic JVM arguments]* フィールドに`-Dfile.encoding=UTF-8`と入力します。
4.  *[Apply]* をクリックしてから、*[保存]* をクリックしてマスター構成に保存します。

変更が保存されると、ローカライズされたコンテンツがある場合、DXPは特殊文字を解析できます。

## DXP `.war`ファイルのデプロイ

1.  WebSphereの管理コンソールで、*[Applications]* → *[New Application]* → *[New Enterprise Application]* の順にクリックします。

2.  DXP `.war`ファイルを参照して選択し、*[Next]* をクリックします。

3.  *[Fast Path]* を選択したままにして、*[Next]* をクリックします。 *[Distribute Application]* がオンになっていることを確認し、もう一度*[Next]* をクリックします。

4.  DXPをデプロイするWebSphereランタイムまたはクラスター、あるいはその両方を選択します。 *次へ*をクリックします。

5.  DXPをデプロイする仮想ホストを選択し、*[Next]* をクリックします。

6.  DXPをルートコンテキスト（`/`）にマップし、*[Next]* をクリックします。

7.  目的の*metadata-complete属性*設定を選択し、*[Next]* をクリックします。

8.  設定が正しいことを確認し、*[Finish]* をクリックします。

9.  DXPがインストールされたら、*[Save to Master Configuration]* をクリックします。

    ![図7：デプロイする前に、デプロイオプションを確認します。](./installing-on-websphere/images/07.png)

DXPがインストールされました。 DXPを開始する前に、いくつかの必要な手順があります。

## JSPをコンパイルするためのJDKバージョンの設定

DXPでは、JSPをJava 8バイトコード形式にコンパイルする必要があります。 WebSphereがこれを行うようにするには、DXP `.war`ファイルをデプロイした後にWebSphereをシャットダウンします。 `WEB_INF`フォルダに移動し、次の設定を`ibm-web-ext.xml`に追加します（ほとんどの場合、`ibm-web-ext.xmi`ファイルです）。

``` xml
<jsp-attribute name="jdkSourceLevel" value="18" />
```

`ibm-web-ext.xmi`ファイルへの正確なパスは、WebSphereのインストール場所とDXPのバージョンによって異なりますが、以下に例を示します。

``` bash
/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/config/cells/localhostNode01Cell/applications/liferayXX.ear/deployments/liferayXX/liferayXX.war/WEB-INF/ibm-web-ext.xmi
```

DXP `.war`は、`ibm-web-ext.xmi`ファイルにあらかじめパッケージ化されていることに注意してください。この形式は機能的に`.xml`と同じで、WebSphereは両方の形式を認識します。 WebSphereによるJSPのコンパイル方法に関する一般的な情報については、IBMの[WebSphere Application Server 9.0.0.x](https://www.ibm.com/support/knowledgecenter/en/SSEQTP_9.0.0/com.ibm.websphere.base.doc/ae/rweb_jspengine.html)の公式ドキュメントを参照してください。

## DXPを起動する

1.  管理者がDXPの[セットアップウィザード](../running-liferay-for-the-first-time.md)を使用している場合は、次のステップにスキップしてください。 ただし、管理者がWebSphereのデータソースとメールセッションを使用している場合は、Liferay Homeフォルダに`portal-ext.properties`というファイルを作成します。 次の構成をファイルに配置します。

    ``` properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    mail.session.jndi.name=mail/MailSession
    setup.wizard.enabled=false
    ```

2.  アプリケーションサーバーを起動します。

3.  WebSphere管理コンソールで、*[Enterprise Applications]* に移動し、DXPアプリケーションを選択して、*[Start]* をクリックします。 DXPの起動中、WebSphereには回転する図が表示されます。

4.  DXPのセットアップウィザードで、データベースの種類を選択して設定します。 *[Finish]* をクリックします。 DXPは、データベースに必要なテーブルを作成します。

DXPをデプロイした後、`PhaseOptimizer`に関連する以下のような警告やログメッセージが過剰になることがあります。 これらは良性なので無視することができます。 このようなログメッセージを回避するために、必ずアプリケーションサーバーのログレベルまたはログフィルターを調整してください。

    |     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    |     WARNING: Skipping pass gatherExternProperties
    |     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    |     WARNING: Skipping pass checkControlFlow
    |     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    |     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
    |     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]

## 次のステップ

  - [Liferay-Tomcatバンドルのインストール](../installing-a-liferay-tomcat-bundle.md)
  - [Activating Liferay DXP](../../setting-up-liferay/activating-liferay-dxp.md)
  - [検索エンジンのインストール](../../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md)
  - [Securing Liferay DXP](../../securing-liferay/introduction-to-securing-liferay.md)
  - [高可用性のクラスタリング](../../setting-up-liferay/clustering-for-high-availability.md)
