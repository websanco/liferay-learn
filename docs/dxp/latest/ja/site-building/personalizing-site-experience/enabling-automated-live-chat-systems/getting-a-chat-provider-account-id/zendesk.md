# Zendesk

[Zendesk](https://www.zendesk.com/) は顧客サービスプラットフォームです。 この記事では、Liferayインスタンスとの[統合を可能にする](../enabling-automated-live-chat-systems.md)ためにZendeskアカウントIDを見つける方法について説明します。

<a name="locating-your-zendesk-account-id" />

## ZendeskアカウントIDを見つける

1. [Zendeskアカウント](https://www.zendesk.com/login/#login) にログインします。

1. ［ダッシュボード］ホームページで、 ［**Manage widget**］ リンクをクリックします。

    ![Zendeskダッシュボードページが表示されます。](./zendesk/images/01.png)

1. （オプション）［**Settings**］ に移動し、次の手順の指示に従います。

    ![Zendeskの［Settings］セクションが表示されます。](./zendesk/images/02.png)

1. Zendeskより、Webウィジェットを埋め込むためのコードスニペットが提供されます。 アカウントIDは、スニペットの`...snippet.js?key=`の後の部分です。 スニペットのその部分を選択してコピーし、チャットプロバイダーアカウントIDとして使用して、Liferayインスタンスとの[自動ライブチャット統合を有効にします](../enabling-automated-live-chat-systems.md)。

   ![スニペットの一部をコピーして、チャットプロバイダーアカウントIDとして使用します。](./zendesk/images/03.png)