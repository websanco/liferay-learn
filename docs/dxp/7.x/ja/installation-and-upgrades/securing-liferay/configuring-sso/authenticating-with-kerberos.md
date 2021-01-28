# Kerberosによる認証

Kerberosを使用して、Liferay DXPでMicrosoft Windows™アカウントを認証できます。 これは、Liferay DXPのLDAPサポートとKerberosプロトコルをサポートしているWebサーバーの組み合わせを使用して行われます。

セキュリティの脆弱性が存続するため、この構成は[NTLM](https://portal.liferay.dev/docs/7-1/deploy/-/knowledge_base/d/ntlm-single-sign-on-authentication)よりも優先されます。

## 前提条件

Windows™サーバーでKerberosとActive Directoryをセットアップする方法についてはこの記事では説明しませんが、Liferay認証を設定するための最低限の前提条件について説明します。

1.  ADサーバーとLiferay DXPがネットワーク上で相互に解決できるようにActive DirectoryとDNSがセットアップされたWindows™サーバー。 つまり、*名前で*相互にpingできる必要があります。

2.  AD Liferay DXPの管理ユーザーがADへのバインドに使用できる。

3.  Liferay DXPサーバーがADにバインドする必要がある暗号情報を含む、`ktpass`コマンドを介してエクスポートされたKerberos keytabファイル。

4.  Apache、NGNIX、IISなどのKerberosをサポートするLiferay DXPの手前にあるWebサーバー。 Webサーバーは、Liferay DXP構成でトークンとして使用されるヘッダーの挿入もサポートしている必要があります（以下を参照）。

5.  他のサーバーも名前で解決できるLiferay DXPのインストール。 Active Directoryサーバーと同じサーバー上で実行しないでください。

これらの前提条件がすべて整ったら、Kerberos認証を構成する準備が整います。

## Kerberos認証のしくみ

前提条件から、SSOがKerberosとどのように連携するかについて、いくつかの重要な部分があると推測できます。

![Kerberos認証では、Liferay DXPサーバーの手前にWebサーバーが必要です。](./authenticating-with-kerberos/images/01.png)

まず、適切に構成されたWebブラウザーが、暗号化されたWindowsユーザーデータを使用してネゴシエート要求を送信します。 これを構成するには、ブラウザーがサイトを信頼済みサイトとして認識する必要があります（以下で説明します）。 WebサーバーのKerberosモジュールは、keytabファイルを使用して、Kerberosプロトコルを介してADにバインドし、ユーザー情報を確認します。 これが機能する場合、ADサーバーは有効な応答で接続を確認します。

選択するWebサーバーは、Kerberosプロトコルと、Liferay DXPが後で読み取ることができる要求へのカスタムヘッダーの挿入の両方をサポートしている必要があります。 Webサーバーが要求をLiferay DXPに転送すると、Liferay DXPはヘッダーを読み取ってユーザーデータを取得し、ユーザーを認証します。

次に、これらすべてを機能させる方法を学習します。

## Kerberos認証の構成

構成するコンポーネントは4つあります。

1.  Active Directoryからのユーザーkeytab
2.  アプリケーションサーバーの手前にあるWebサーバー
3.  Liferay DXP
4.  Windows™クライアント。

### ステップ1：ユーザーKeytabの作成

1.  Liferay DXPがActive Directoryにバインドできるようにユーザーを作成します。

2.  `ktpass`を使用してKerberos keytabファイルを生成します。

    ``` bash
    ktpass -princ HTTP/[web server host name]@[domain] -mapuser [user name]@[domain] -crypto ALL -ptype KRB5_NT_PRINCIPAL -pass [password] -out c:\kerberos.keytab
    ```

    例：

    ``` bash
    ktpass -princ HTTP/mywebserver.intdomain.local@INTDOMAIN.LOCAL -mapuser Marta@INTDOMAIN.LOCAL -crypto ALL -ptype KRB5_NT_PRINCIPAL -pass password-for-Marta -out c:\kerberos.keytab
    ```

3.  ADドメインコントローラーとWebサーバーがDNS構成または`hosts`ファイルを介してネットワーク上で相互に認識できることを確認します。

### ステップ2：Webサーバーの構成

1.  Kerberos認証を構成します。 Linuxでは、`krb5`をインストールして、Active Directory用にすでに構成されているレルムと一致するようにそれを設定します。 上記のステップ2で構成されたユーザーのドメインの例は、次のようになります。

    ``` ini
    [libdefaults]
        default_realm = INTDOMAIN.LOCAL

    [domain_realm]
        mywebserver.intdomain.local = INTDOMAIN.LOCAL intdomain.local = INTDOMAIN.LOCAL .intdomain.local = INTDOMAIN.LOCAL

    [realms]
    INTDOMAIN.LOCAL = { admin_server = winserver.intdomain.local kdc = winserver.intdomain.local
    }
    ```

2.  ADサーバーで生成したkeytabファイルをWebサーバーにコピーします。

3.  Webサーバーを構成し、正しいサーバー名、Kerberosサービス名、Kerberos認証レルム、およびkeytabファイルへのパスを設定していることを確認します。 たとえば、Apache HTTPサーバーを使用している場合、構成は次のようになります。

    ``` apache
    LoadModule headers_module /usr/lib/apache2/modules/mod_headers.so
    LoadModule rewrite_module /usr/lib/apache2/modules/mod_rewrite.so
    LoadModule proxy_module /usr/lib/apache2/modules/mod_proxy.so
    LoadModule proxy_http_module /usr/lib/apache2/modules/mod_proxy_http.so
    LoadModule proxy_ajp_module /usr/lib/apache2/modules/mod_proxy_ajp.so
    LoadModule auth_kerb_module /usr/lib/apache2/modules/mod_auth_kerb.so

    <VirtualHost *:10080>
        <Proxy *>
            Order deny,allow
            Allow from all
        </Proxy>
        ProxyRequests     Off
        ProxyPreserveHost On
        ProxyPass/ajp://localhost:8009/
        ProxyPassReverse/ajp://localhost:8009/
        ServerName mywebserver.intdomain.local
        <Location />
                    Order allow,deny
                    Allow from all
                    AuthType Kerberos
                    KrbServiceName HTTP/mywebserver.intdomain.local@INTDOMAIN.LOCAL
                    AuthName "Domain login"
                    KrbAuthRealms INTDOMAIN.LOCAL
                    Krb5KeyTab /etc/apache2/kerberos.keytab
                    require valid-user
                    KrbMethodNegotiate  On
                    KrbMethodK5Passwd   Off
                    #KrbLocalUserMapping On

                    # Below directives put logon name of authenticated user into http header X-User-Global-ID
                    RequestHeader unset X-User-Global-ID
                    RewriteEngine On
                    RewriteCond   %{LA-U:REMOTE_USER} (.+)
                    RewriteRule   /.* - [E=RU:%1,L,NS]
                    RequestHeader set X-User-Global-ID %{RU}e

                    # Remove domain suffix to get the simple logon name
                    # RequestHeader edit X-User-Global-ID "@INTDOMAIN.LOCAL$" ""

        </Location>
    </VirtualHost>
    Listen 10080
    ```

最後の行は、ユーザー設定に基づいてコメントアウトされています。 Liferay DXPに保存するときにユーザー名からドメインを削除する場合は、コメントを解除してください。 それ以外の場合は、コメントアウトしたままにして、ドメインとユーザー名を保存します。

### ステップ3：LDAPを介してLiferay DXPをActive Directoryに接続する

1.  最後に、LDAPプロトコルを介してActive DirectoryにアクセスするようにLiferay DXPを構成します。 [Configuration] → [Instance Settings] → [Authentication] → [General]で、画面名による認証に変更します。

2.  [Configuration] → [Instance Settings] → [Authentication] → [LDAP]に移動し、LDAPサーバーを追加して、Liferay DXPをAD over LDAPに接続します。 インストールに適切な情報を提供します。

    | 設定                               | 説明                                                                                  |
    | -------------------------------- | ----------------------------------------------------------------------------------- |
    | **Base Provider URL**            | 適切なポート上のADサーバー。                                                                     |
    | **Base DN**                      | ドメイン構成。 上記の例では `DC=INTDOMAIN.DC=LOCAL`。                                             |
    | **Principal/Credentials**        | keytabファイルにエクスポートされたユーザーの資格情報を入力します。                                                |
    | **Authentication Search Filter** | ユーザーオブジェクトを返す適切な検索フィルターを指定します。 例：`(&(objectCategory=person)(sAMAccountName=*))` |
    | **UUID**                         | `sAMAccountName`など、ユーザーを一意に識別するものを指定します。                                            |
    | **Screen Name**                  | `sAMAccountName`など、Liferay DXPの画面名フィールドにマップする必要があるフィールドを指定します。                      |
    | **Password**                     | `userPassword`など、ユーザーのパスワードを含むフィールドを指定します。                                          |


3.  接続をテストし、構成を保存して有効にします。

4.  最後に、[Configuration] → [System Settings] → [Security] → [SSO] → [Token Based SSO]で、シングルサインオン用のトークンを設定します。 ユーザートークン名が、Webサーバーで設定したトークンと*正確に*一致していることを確認します。 *[Enabled]* および*[Import from LDAP]* ボックスをクリックし、*[保存]* をクリックします。

    ![[Instance Settings]メニューでSSOを有効にする。](authenticating-with-kerberos/images/02.png)

サーバーを構成しました。 あとはクライアントを設定するだけです。

### ステップ4：クライアントの設定

コンピュータをドメインにログインさせ、Liferay DXPサーバーを信頼できるインターネットサイトとして構成します。

1.  コンピューターをドメインに参加させます。 上記の例に従って、コンピューターを`INTDOMAIN.LOCAL`ドメインのメンバーにします。

2.  そのドメインのユーザーとしてログインします。

3.  Internet Explorer、Edge、およびChromeは、信頼済みサイトにWindows™設定を使用しています。 これらのブラウザを使用している場合は、[Internet Options] → [Security] → [Local Intranet Sites]に移動し、Liferay DXPサーバーのURLを追加します。 たとえば、`http://mywebserver.intdomain.local:10080`を追加します。

4.  Firefoxは、アドレスバーに`about:config`と入力して設定できます。 以下の2つの設定を検索し、Liferay DXPサーバーのURLを両方の値として追加します。

      - `network.negotiate-auth.delegation-uris`
      - `network.negotiate-auth.trusted-uris`

これらを設定した後、WebサーバーのURLからLiferay DXPにアクセスして設定をテストします。 すでにクライアントマシンにログインしているため、ユーザ/パスワードのプロンプトが表示されることなく自動的にLiferay DXPにログインします。

Liferay DXPでKerberosを構成できました。
