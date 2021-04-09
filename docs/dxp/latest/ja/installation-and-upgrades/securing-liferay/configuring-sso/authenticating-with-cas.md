# CAS（中央認証サービス）による認証

CASは広く使用されているオープンソースのシングルサインオンソリューションであり、Liferay DXPでサポートされる最初のSSO製品でした。 Liferay DXPのCASモジュールにはCASクライアントが含まれているため、個別にインストールする必要はありません。

``` note::
   Liferay DXPはCAS 3.3.xをサポートしています。新しいバージョンのCASを使用する場合は、OpenID ConnectやSAMLなどの規格に対するCASのサポートを使用してLiferay DXPと連携させることをお勧めします。
```

CASでLiferayを実行するには、3つのステップがあります。

1.  SSL証明書を生成する
2.  CASをインストールする
3.  CASを使用するようにLiferayを構成する

## SSL証明書の生成

CASサーバーアプリケーションでは、サーバーに適切に構成されたSSL（Secure Socket Layer）証明書が必要です。 自分で生成するには、JDKに付属の`keytool`ユーティリティを使用します。 最初にキーを生成し、次にキーをファイルにエクスポートします。 最後に、キーをローカルJavaキーストアにインポートします。 パブリックなインターネットベースの本番環境の場合は、認識済みの認証局またはLet's Encryptから署名済みのキーを購入するか、または認識済みの認証局によって署名されたキーが必要です。 イントラネットの場合は、証明書に関する警告メッセージをユーザーが受け取らないように、ユーザーのブラウザが証明書を受け入れるようIT部門に事前設定してもらう必要があります。

キーを生成するには、次のコマンドを使用します。

``` bash
keytool -genkey -alias tomcat -keypass changeit -keyalg RSA
```

例のパスワード（`changeit` ）の代わりに、覚えやすいパスワードを使用してください。 Tomcatを使用していない場合は、別のエイリアスを使用することもできます。 姓名には、`localhost`またはサーバーのホスト名を入力します。 IPアドレスにすることはできません。

キーをファイルにエクスポートするには、次のコマンドを使用します。

``` bash
keytool -export -alias tomcat -keypass changeit -file server.cert
```

最後に、キーをJavaキーストアにインポートするには、次のコマンドを使用します。

``` bash
keytool -import -alias tomcat -file server.cert -keypass changeit -keystore $JAVA_HOME/jre/lib/security/cacerts
```

Windowsシステムを使用している場合は、上記の`$JAVA_HOME`を`%JAVA_HOME%`に置き換えます。 もちろん、これらはすべてCASが実行されているシステムで実行する必要があります。

## CASを使用するようにLiferay DXPを構成する

CASサーバーが起動して実行されたら、それを使用するようにLiferay DXPを設定します。 CASの構成は、システムスコープまたはポータルインスタンスのスコープのいずれかに適用できます。 システムまたはインスタンスのスコープでCAS SSOモジュールを構成するには、コントロールパネルに移動し、*[Configuration]* → *[System Settings]*（または*[Instance Settings]*）→ *[Security]* → *[SSO]* をクリックします。 システム設定で構成された値は、すべてのポータルインスタンスのデフォルト値を提供します。 CAS認証を有効にしてから、CASサーバーを指すようにURLプロパティを変更します。

**Enabled：**CASシングルサインオンを有効にするには、このボックスをオンにします。

**Import from LDAP：**CASから認証されたユーザーがLiferay DXPにまだ存在していない場合があります。 ユーザーがLiferay DXPに存在しない場合にLDAPからユーザーを自動的にインポートするには、これを選択します。 これを機能させるには、LDAPを有効にする必要があります。

残りの設定は、デフォルトが含まれているさまざまなURLです。 CASサーバーを指すようにデフォルト値の*localhost*を変更します。 完了したら、* [保存]* をクリックします。 この後、ユーザーが*[Sign In]* リンクをクリックすると、Liferay DXPにサインインするようにCASサーバーに誘導されます。

状況によっては、ディスク上のファイルを介してシステム構成を指定する方が便利な場合があります。 これを行うには、次のファイルを作成します。

``` bash
[Liferay Home]/osgi/configs/com.liferay.portal.security.sso.cas.configuration.CASConfiguration.cfg
```

このファイルの形式は、他のプロパティファイルと同じです。 設定可能な各プロパティに使用するキーを以下に示します。 リテラル値でJavaプリミティブ型を初期化するときと同じ形式で値を入力します。

| プロパティラベル                         | プロパティキー                     | 説明                                                                                                                                                                                                  | タイプ       |
| -------------------------------- | --------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------- |
| **Enabled**                      | `enabled`                   | CAS SSO認証を有効にするには、このボックスをオンにします。                                                                                                                                                                    | `boolean` |
| **Import from LDAP**             | `importFromLDAP`            | Liferay DXPに存在しないCASから認証されたユーザーがLDAPからインポートされます。 LDAPは個別に有効にする必要があります。                                                                                                                              | `boolean` |
| **Login URL**                    | `loginURL`                  | CASサーバーのログインURLを設定します。                                                                                                                                                                              | `String`  |
| **Logout on session expiration** | `logoutOnSessionExpiration` | オンにすると、セッションが期限切れのブラウザがCASログアウトURLにリダイレクトされます。                                                                                                                                                      | `boolean` |
| **Logout URL**                   | `logoutURL`                 | CASサーバーのログアウトURL。 Liferay DXPのログアウト機能でCASログアウトをトリガーする場合は、これを設定します                                                                                                                                   | `String`  |
| **Server Name**                  | `serverName`                | Liferay DXPインスタンスの名前（例：`liferay.com` ）。 指定された名前にプロトコル（`https://`など）が含まれている場合、これはパス`/c/portal/login`とともに使用され、CASサーバーがチケットを提供するURLを構築します。 スキームが提供されない場合は、Liferay DXPログインページへのアクセスに通常使用されるスキームが使用されます。 | `String`  |
| **Server URL**                   | `serviceURL`                | 指定した場合、これはCASサーバーがチケットを提供するURLとして使用されます。 これにより、上記の[Server Name]に基づいて構築されたURLが上書きされます。                                                                                                               | `String`  |
| **No Such User Redirect URL**    | `noSuchUserRedirectURL`     | ユーザーがCASで認証できるがLiferay DXPで見つからない場合にユーザーをリダイレクトするURLを設定します。 LDAPからのインポートが有効になっている場合、ユーザーが見つからなかったり、LDAPからインポートできなかったりすると、ユーザーはリダイレクトされます。                                                           | `String`  |

特定のポータルインスタンスのシステムデフォルトを上書きするには、コントロールパネルに移動して、*[Configuration]* → *[Instance Settings]* をクリックし、右側の*[Authentication]* をクリックしてから、上部の*[CAS]* をクリックします。
