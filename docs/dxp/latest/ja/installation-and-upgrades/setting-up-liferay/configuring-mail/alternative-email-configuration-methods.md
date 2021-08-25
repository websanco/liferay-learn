# 代替の電子メールの設定方法

メールサーバーに接続するようにLiferay DXPを構成するには、いくつかの方法があります。 開始する最も簡単な方法は、[サーバー管理UIを使用してメールを設定する](./connecting-to-a-mail-server.md)ことです。 この記事では、ポータルプロパティまたはアプリケーションサーバーのメールセッションを使用した、メールを構成する代替方法について説明します。

## ポータルプロパティを使用した組み込みメールセッションの構成

オフラインで、またはDXPをデプロイする前にメールセッションを構成するには：

1.  [`portal-ext.properties`ファイル](../../reference/portal-properties.md)を作成します（ファイルがまだ存在しない場合）。

2.  これらのデフォルトのプロパティ設定を`portal-ext.properties`ファイルにコピーします。

    ``` properties
    mail.session.mail=false
    mail.session.mail.pop3.host=pop.gmail.com
    mail.session.mail.pop3.password=*******
    mail.session.mail.pop3.port=465
    mail.session.mail.pop3.user=joe.bloggs
    mail.session.mail.smtp.auth=true
    mail.session.mail.smtp.host=smtp.gmail.com
    mail.session.mail.smtp.password=*******
    mail.session.mail.smtp.port=110
    mail.session.mail.smtp.user=joe.bloggs
    mail.session.mail.store.protocol=pop3
    mail.session.mail.transport.protocol=smtp
    ```

3.  デフォルトのメールセッション値を独自の値に置き換えます。

4.  `portal-ext.properties`ファイルを[Liferay Home](../../reference/liferay-home.md)に配置します。

DXPは次回の起動時にメールセッションに接続します。

## アプリケーションサーバー上のメールセッションへの接続

ユーザーは、アプリケーションサーバーを使用してDXPのメールセッションを構成できます。

1.  アプリケーションサーバーでメールセッションを作成します。アプリケーションサーバーのドキュメントを参照してください。

2.  *コントロールパネル*を使用している場合は、*[Control Panel] → [Configuration] → [Server Administration] → [Mail]* に移動します。

3.  *[JavaMail Properties]* フィールドに値を入力します。

    ![JavaMail](./alternative-email-configuration-methods/images/01.png)

4.  *[保存]* をクリックします。 DXPがアプリケーションサーバー上のメールセッションを指すようになります。

メールサーバーがDXPに接続され、通知を送信する準備ができました。

```{note}
[portal-ext.properties](../../reference/portal-properties.md)で以下のプロパティを設定し、サーバーを再起動すると、コントロールパネルの値を設定するのと同じ効果があります： `mail.session.jndi.name=mail/MailSession`
```

## 追加情報

  - [Configuring a Tomcat-Managed Mail Session](../../installing-liferay/installing-liferay-on-an-application-server/installing-on-tomcat.md#mail-configuration)
  - [Configuring a WebSphere-Managed Mail Session](../../installing-liferay/installing-liferay-on-an-application-server/installing-on-websphere.md#mail-configuration)
  - [Apache Tomcat 9 JavaMail Sessions](https://tomcat.apache.org/tomcat-9.0-doc/jndi-resources-howto.html#JavaMail_Sessions)
