# メールサーバーに接続する

Liferay DXPは、メールサーバーを使用して、ユーザー登録とパスワード管理、サイトメンバーシップ通知、コンテンツの更新などのさまざまな目的で電子メール通知を送信するように構成できます。 この記事では、デモ目的として、Gmailをメールサーバーとして使用するように組み込みのDXPメールセッションを構成する手順を説明します。

```{warning}
カテゴリのメーリングリストに [IMAP](https://support.google.com/mail/answer/7126229?hl=en) プロトコルを使用している場合は、[メーリングリストのユーザーにメッセージを送信するメールクライアントによってメッセージがプルされたときにメッセージが削除されるようにIMAP受信トレイを設定](https://support.google.com/mail/answer/78892?hl=en) してください。 そうしないと、サーバーに保持されている各電子メールメッセージは、カテゴリに新しい投稿や更新があるたびにメーリングリストに送信されます。
```

## DXP組み込みメールセッションの構成

コントロールパネルからメールセッションを構成するには、次の手順に従います。

1.  管理ユーザー（[[Basic Configuration]ページ](../../../getting-started/using-the-setup-wizard.md)で指定されたユーザー）としてサインインします。

2.  *[Control Panel] → [Configuration] → [Server Administration] → [Mail]* に移動します。

3.  [次のフィールド](#mail-configuration-reference)に値を入力します。

      - **Incoming POP Server：**pop.gmail.com

      - **Incoming Port：** 465

      - **Use a Secure Network Connection：**Flagged

      - **User Name：**joe.bloggs

      - **Password：**\*\*\*\*\*

      - **Outgoing SMTP Server：**smtp.gmail.com

      - **Outgoing Port：**110

      - **Use a Secure Network Connection：**Flagged

      - **User Name：**joe.bloggs

      - **Password：**\*\*\*\*\*

      - **Manually specify additional JavaMail properties to override the above configuration：**指定する必要のある追加のプロパティがある場合は、ここで指定します。

        ![メールサーバーの構成](./connecting-to-a-mail-server/images/01.png)

4.  *[保存]* をクリックします。

DXPがすぐにメールセッションに接続します。


<!-- 
## Validating Mail Configuration

To validate that you configured the mail session correctly, do the following:

1. 
1. 
1. 
-->

## メール構成リファレンス

| フィールド                                                                               | 説明                                                                                            |
| ----------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| Incoming POP Server                                                                 | Post Office Protocolを実行しているサーバーのホスト名。 DXPはこのメールボックスをチェックして、メッセージボードの返信などの着信メッセージを確認します。       |
| Incoming Port                                                                       | POPサーバーがリスンしているポート。                                                                           |
| Use a Secure Network Connection                                                     | POPサーバーに接続するときに暗号化された接続の使用を有効にするチェックボックス。                                                     |
| User Name                                                                           | DXPがPOPサーバーへのログインに使用するユーザーID。                                                                 |
| Password                                                                            | DXPがPOPサーバーへのログインに使用するパスワード。                                                                  |
| Outgoing SMTP Server                                                                | Simple Mail Transfer Protocolを実行しているサーバーのホスト名。 DXPはこのサーバーを使用して、パスワード変更メールやその他の通知などのメールを送信します。 |
| Outgoing Port                                                                       | SMTPサーバーがリスンしているポート。                                                                          |
| Use a Secure Network Connection                                                     | SMTPサーバーに接続するときに暗号化された接続を使用します。                                                               |
| User Name                                                                           | DXPがSMTPサーバーへのログインに使用するユーザーID。                                                                |
| Password                                                                            | DXPがSMTPサーバーへのログインに使用するパスワード。                                                                 |
| Manually specify additional JavaMail properties to override the above configuration | このフィールドは、追加のJavaMail設定用です。                                                                    |

## 追加情報

  - [Configuring Default Email Senders](./configuring-default-email-senders.md)
  - [Alternative Email Configuration Methods](./alternative-email-configuration-methods.md)
