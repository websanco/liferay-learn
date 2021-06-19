# 多要素認証

すべてのAnalytics Cloudユーザーには多要素認証（MFA）が必要です。 この追加のセキュリティ層は、フィッシングやMITM（man-in-the-middle）攻撃などのサイバー攻撃からユーザーを保護するのに役立ちます。

## 多要素認証の設定

1.  [analytics.liferay.com](https://analytics.liferay.com) に、 [liferay.com](https://www.liferay.com) アカウントのメールアドレスでログインします。

    ![自分のユーザーアカウントでLiferayにログインします。](./multi-factor-authentication/images/01.png)

2.  5つのMFAオプションの中から1つを選択します。 例えば、 *SMS認証* を選択すると、テキストメッセージでコードを受け取ることができます。

    ![MFAオプションを選択してください。](./multi-factor-authentication/images/02.png)

    なお、セットアップ後に別の認証タイプに変更したい場合は、 [サポートに](#help-with-mfa)ご連絡ください。

3.  *Send Code* ボタンをクリックして、コードが届くのを待ちます。 なお、 *電子メール認証* オプションを使用する場合は、スパムフォルダを確認する必要があります。

    ![認証オプションを選択し、［コードを送信］をクリックします。](./multi-factor-authentication/images/03.png)

4.  認証コードを入力し、［ *Verify* ］ボタンをクリックします。 認証コードを受け取れなかった場合は、 *Re-send code* ボタンをクリックしてください。

    ![受信したコードを入力して確認します。](./multi-factor-authentication/images/04.png)

5.  次の30日間はMFAの有効期限が切れないようにする場合は、チェックを入れます。

    ![MFAを30日間期限切れにしないようにする場合は、チェックを入れます。](./multi-factor-authentication/images/06.png)

6.  *終了* をクリックして、認証処理を完了します。

    ![完了ボタンをクリックして処理を終了します。](./multi-factor-authentication/images/05.png)

7.  認証が完了すると、Analytics Cloudのスタートページが表示されます。 ここから、 [ワークスペース](./accessing-your-workspace.md)にアクセスすることができます。

## MFAのヘルプ

安全なログインは、ユーザーと顧客データを保護するための重要な機能です。 MFAで問題が発生した場合は、私たちがサポートします。

サポートへのお問い合わせは、 [help.liferay.com](https://help.liferay.com/) または、 [<analytics-cloud@liferay.com>](mailto:analytics-cloud%40liferay.com)までメールでご連絡ください。
