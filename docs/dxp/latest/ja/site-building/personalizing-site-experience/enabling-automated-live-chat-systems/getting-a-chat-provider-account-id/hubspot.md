# Hubspot

[Hubspot](https://www.hubspot.com/) は、ライブチャット機能を含むCRMプラットフォームです。 Hubspotとの統合を有効にするには、ライブチャットを作成し、Hubspotアカウントから取得できる2つの値（HubspotユーザーアカウントIDとHubspot APIトークン）の組み合わせを作成する必要があります。

ライブチャットを作成し設定するには、 [Hubspotのナレッジベース](https://knowledge.hubspot.com/chatflows/create-a-live-chat) の手順に従ってください。

<a name="locating-your-hubspot-user-account-id" />

## HubspotユーザーアカウントIDを見つける

1. [Hubspotアカウント](https://app.hubspot.com/login) にログインします。

1. 右上隅にあるプロフィールに移動します。

    使用するチャネルに対応するアカウント番号をコピーします。 この番号は、Liferay Portalで［クリックでチャット］を有効にするときに使用されるアカウントIDに対応しています。

    ![右上隅にあるプロフィールに移動し、使用するチャンネルへのアカウント番号をコピーします。](./hubspot/images/01.png)

<a name="getting-the-hubspot-api-token" />

## Hubspot APIトークンを取得する

Hubspotアカウントにログインしている状態で、

1. **Settings** ボタンをクリックします。

    ![［Settings］ボタンをクリックします。](./hubspot/images/02.png)

1. ページの左側にある</em>［Integrations］</em> &rarr; ［**API Key**］ をクリックします。</p>

    ![［Integrations］をクリックして、APIキーにアクセスします。](./hubspot/images/03.png)</li>

1

［**Show**］ をクリックして、APIトークンキーを表示します。

    ![［Show］をクリックして、APIトークンキーを表示します。](./hubspot/images/04.png)</ol>

<a name="conclusion" />

## まとめ

ユーザーアカウントIDとAPIトークンを取得したら、それらを組み合わせて、Liferayインスタンスとの[ライブチャットを有効にする](../enabling-automated-live-chat-systems.md)ときにチャットプロバイダーアカウントIDとして使用します。 チャットプロバイダーアカウントIDは、`[Hubspot Account ID]/[Hubspot API Token]`の形式に従います。