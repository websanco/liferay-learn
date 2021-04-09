# OpenAMの使用

OpenAMは、SunのSystem Access Manager製品のコードベースから提供されるオープンソースのシングルサインオンソリューションです。 OpenAMを使用して、IDのさまざまなリポジトリに対するいくつかの異なる認証スキームを含むインフラストラクチャにLiferay DXPを統合できます。

## インストールに関する注記

ドキュメントの指示に従ってOpenAMをインストールしてください。 OpenAMはアプリケーション間のCookie共有に依存していることに注意してください。 したがって、OpenAMが機能するためには、**SSOを必要とするすべてのアプリケーションが同じWebドメインにある必要があります**。 一部のWebコンテナ（Apache Tomcat™など）が特殊文字を含むCookieを解析するためにHTTPOnly Cookieを有効にしている場合は、次のプロパティも追加する必要があります。

``` properties
com.iplanet.am.cookie.encode=true
```

OpenAMは、Liferay DXPと同じサーバーまたは別のサーバーにインストールできます。 OpenAMサーバーのコンテキストパスとサーバーホスト名を必ず確認してください。

OpenAMをLiferay DXPと同じサーバーにインストールする場合は、[ここ](https://backstage.forgerock.com/downloads/browse/am/archive/productId:openam)からダウンロード可能なOpenAM `.war`をデプロイする必要があります。 それ以外の場合は、[OpenAM 13サイト](https://backstage.forgerock.com/docs/openam/13/install-guide/)の指示に従ってOpenAMをインストールします。

``` note::
   OpenAM 12以下はLiferay DXPで動作しますが、サポートは終了しています。 このため、本番環境での使用にはOpenAM 13のみをお勧めします。
```

## OpenAMの構成

インストールしたら、次の2つの手順を行います。

1.  OpenAMでLiferay DXP管理ユーザーを作成する
2.  認証用にOpenAMを有効にする

ユーザーは画面名によって前後にマッピングされるため、OpenAMのユーザーIDをLiferay管理ユーザーの画面名と必ず一致させてください。 たとえば、Liferay DXP管理ユーザーの画面名が*admin*の場合は、*admin*のIDと[`admin.email.from.address`](http://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#Admin%20Portlet)[ポータルプロパティ](../../../reference/portal-properties.md)で指定された電子メールアドレスを使用して、ユーザーをOpenAMに登録します。 ユーザーを設定したら、このユーザーを使用してOpenAMにログインします。

1.  同じブラウザウィンドウで、管理ユーザーとして（以前の管理者の電子メールアドレスを使用して）Liferay DXPにログインします。

2.  コントロールパネルに移動し、*[Configuration]* → *[Instance Settings]* → *[Security]* → *[SSO]* の順にクリックします。 次に、左側のリストで*[OpenSSO]* を選択します。

    ![統合を機能させるには、LiferayのOpenSSO設定を有効にする必要があります。](./using-openam/images/01.png)

3.  OpenAMサーバーを指すように3つのURLフィールド（[Login URL]、[Logout URL]、および[Service URL]）を変更し（つまり、URLのホスト名部分のみを変更）、*[Enabled]* チェックボックスをオンにして、*[保存]* をクリックします。

Liferay DXPは、ユーザーが`/c/portal/login` URLをリクエストすると（たとえば、*[Sign In]* リンクをクリックしたとき）、ユーザーをOpenAMにリダイレクトします。

## 別のLiferayスコープでOpenAMを構成する

Liferay DXPのOpenAM構成は、システムスコープでもインスタンススコープでも適用できます。

システムスコープでOpenAM SSOモジュールを構成するには：

1.  コントロールパネルに移動します。

2.  *[Configuration]* → *[System Settings]* → *[Security]* → *[SSO]* → *[OpenSSO]* の順にクリックします。 以下の設定が表示されます。 ここで設定されている値は、すべてのポータルインスタンスのデフォルト値です。 リテラル値を使用してJavaプリミティブ型を初期化する場合と同じ形式で入力します。

| プロパティラベル                    | プロパティキー            | 説明                                                                                                         | タイプ       |
| --------------------------- | ------------------ | ---------------------------------------------------------------------------------------------------------- | --------- |
| **Version**                 | `version`          | 使用するOpenAMバージョン（12以下または13）                                                                                 | `String`  |
| **Enabled**                 | `enabled`          | OpenAM認証を有効にするには、このボックスをオンにします。 OpenAMは、LDAP認証も有効になっていて、Liferay DXPの認証タイプが画面名に設定されている場合にのみ機能することに注意してください。 | `boolean` |
| **Import from LDAP**        | `importFromLDAP`   | これをオンにすると、Liferay DXPに存在しないOpenAMから認証されたユーザがLDAPからインポートされます。 LDAPを有効にする必要があります。                            | `boolean` |
| **Login URL**               | `loginURL`         | OpenAMサーバーのログインページへのURL                                                                                    | `String`  |
| **Logout URL**              | `logoutURL`        | OpenAMサーバーのログアウトページへのURL                                                                                   | `String`  |
| **Service URL**             | `serviceURL`       | 認証されたWebサービスを使用するためにOpenAMにアクセスできるURL。 OpenAM Express 8以降を使用している場合は、サーバーでJava 6を実行する必要があります。               | `String`  |
| **Screen Name Attribute**   | `screenNameAttr`   | ユーザーの画面名を表すOpenAM上の属性の名前                                                                                   | `String`  |
| **Email Address Attribute** | `emailAddressAttr` | ユーザーの電子メールアドレスを表すOpenAM上の属性の名前                                                                             | `String`  |
| **First Name Attribute**    | `firstNameAttr`    | ユーザーの名を表すOpenAM上の属性の名前                                                                                     | `String`  |
| **Last Name Attribute**     | `lastNameAttr`     | ユーザーの姓を表すOpenAM上の属性の名前                                                                                     | `String`  |

特定のポータルインスタンスのこれらのデフォルト設定を上書きするには、コントロールパネルに移動し、*[Configuration]* → *[Instance Settings]* → *[Security]* → *[SSO]* をクリックします。 次に、左側のリストで*[OpenSSO]* を選択します。
