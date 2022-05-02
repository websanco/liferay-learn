# デフォルトのメール送信者の設定

デフォルトでは、Liferay DXPから送信される電子メールは、名前と電子メールアドレスをデフォルトの管理ユーザーとして使用します。 この動作は、さまざまな電子メールタイプごとに設定できます。

<a name="changing-the-default-email-sender" />

## デフォルトのメール送信者の変更

デフォルトのメール送信者を変更するには、次の手順に従います。

1. **製品メニュー** を開き、 [**Control Panel**] → [**Configuration**] → [**Instance Settings**] に移動します。

    ![ [Instance Settings]メニューでメール設定オプションを見つける。](./configuring-default-email-senders/images/01.png)

2. [**Email**] をクリックします。

3. [**Email Sender**] をクリックします。

1. 次のフィールドに値を入力します。

    * 名前
    * Address

    ![デフォルトのメール送信者名とメールアドレスを変更する。](./configuring-default-email-senders/images/02.png)

5. [**保存**] をクリックします。

### ポータルプロパティを使用したデフォルトの電子メール送信者の設定

デフォルトのメール送信者は、[`portal-ext.properties`ファイル](../../reference/portal-properties.md)を使用して設定することもできます。 次のプロパティ値は、独自の値に置き換えることができます。

```properties
admin.email.from.name=Joe Bloggs
admin.email.from.address=test@domain.invalid
```

<a name="changing-the-email-sender-for-announcements" />

## アナウンスのためのメール送信者の変更

お知らせアプリケーションは、独自の送信者を使用します。 お知らせメールの送信者名とアドレスを設定するには、`portal-ext.properties`ファイルを使用する必要があります。 次のプロパティ値を独自の値に置き換えます。

```properties
announcements.email.to.name=
announcements.email.to.address=noreply@domain.invalid
```

<a name="blacklisted-emails" />

## ブラックリストに登録されたメール

次のメールはデフォルトでブラックリストに登録されており、DXPのインストールでは使用できません。

* `noreply@liferay.com`
* `test@liferay.com`
* `noreply@domain.invalid`
* `test@domain.invalid`

上記のメールを使用しようとすると、`WARN`トレースが記録されます。

```bash
Email xxx will be ignored because it is included in mail.send.blacklist
```

<a name="additional-information" />

## 追加情報

* [Connecting to a Mail Server](./connecting-to-a-mail-server.md)
* [代替の電子メールの設定方法](./alternative-email-configuration-methods.md)
* [ポータルプロパティ](../../reference/portal-properties.md)