# Liferay Commerce Connector to PunchOut2Go

> サブスクリプションが必要です

Liferayは、ユーザーの既存のPunchOut2GoインフラとCommerceを統合できるPunchOut2Goコネクターを備えています。 このコネクターは、LPKGファイルとして [Liferayヘルプセンタ](http://customer.liferay.com/downloads) からダウンロードできます。

<!--!\[Punch Out Flow Diagram\](./liferay-commerce-connector-to-punchout2go/images/01.png) -->

```important::
   PunchOut2Goコネクターを使用するには、Liferay Commerce 2.1.2およびLiferay DXP 7.1 FP18、または7.2 FP6が必要です。
```

<a name="deploy-the-punchout2go-connector" />

## PunchOut2Goコネクターのデプロイ

競合を避けるため、PunchOut2Goコネクターをデプロイする前にLiferayサーバーにCommerceがデプロイ済みであることを確認してください。 次に、以下の手順でPunchOut2Go LPKGコネクターをデプロイします。

1. **Commerce Connector to PunchOut2Go** を [Liferayヘルプセンター](http://customer.liferay.com/downloads) からダウンロードします。

1. **PunchOut2Goコネクター** `LPKG`を [`${liferay.home}/deploy`](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/reference/liferay-home.html) フォルダーにコピーします。

1. アプリケーションサーバーコンソールに次のメッセージが表示されることを確認します。

    ```
    2020-07-24 22:10:01.924 INFO  [fileinstall-/../../liferay-portal-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:209] The portal instance needs to be restarted to complete the installation of file:/../../liferay-portal-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20Connector%20to%20PunchOut2Go%20-%20API.lpkg
    2020-07-24 22:10:01.926 INFO  [fileinstall-../../liferay-portal-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:209] The portal instance needs to be restarted to complete the installation of file:/../../liferay-portal-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20Connector%20to%20PunchOut2Go%20-%20Impl.lpkg`
    ```

1. アプリケーションサーバーを再起動します。

<!-- 1. Verify that the following message displays in the application server console:

    ```
     [Success message]
    ```
-->

サーバーの再起動が完了すると、コネクターがアクティブになり、設定できるようになります。

<a name="configure-the-connector" />

## コネクターの設定

PunchOut2Goコネクターは、[チャネル](../starting-a-store/channels/introduction-to-channels.md)単位で設定されます。

ユーザーはストアのカタログページのURLを所有していなければなりません。この例では、以下になります。 `http://localhost:8080/web/everest.com/catalog`

1. ［**コントロールパネル**］ → ［**Commerce**］ → ［**チャネル**］に移動します。
1. 目的のチャネルをクリックします。
1. ［**パンチアウト**］タブをクリックします。
1. トグルを ［**Enabled**］に切り替えます。
1. パンチアウトの開始URLを入力します。
1. 完了したら、［**保存**］をクリックします。

### 自動ログインのパンチアウトアクセストークンを有効にします。

ユーザーは、購入者の調達システムのパンチアウトアクセストークンを有効にし、Liferay Commerceインスタンスにアクセスできるようにします。 トークンを有効にするには、

1. ［**コントロールパネル**］ → ［**設定**］ → ［**システム設定**］に移動します。
1. ［**セキュリティ**］の下にある［**API 認証**］をクリックします。

    ![認証](./liferay-commerce-connector-to-punchout2go/images/02.png)

1. 左メニューの ［**Auto Login Punch Out Access Token**］ をクリックします。
1. ［**Enabled**］のチェックボックスをオンにします。

    ![自動ログインアクセストークンを有効にします。](./liferay-commerce-connector-to-punchout2go/images/03.png)

1. ［**Update**］ボタンをクリックします。

自動ログイン **Punch Out** トークンが有効になり、購入者の調達システムがログインできるようになります。

### パンチアウトアクセストークンプロバイダーの設定

パンチアウトアクセストークンを有効にすると、ユーザーはパンチアウトアクセストークンプロバイダーを設定できます。 具体的には、トークンの持続時間やサイズを設定できます。 以下の手順に従ってください。

1. ［**コントロールパネル**］ → ［**設定**］ → ［**システム設定**］に移動します。
1. ［**OAuth2**］の下にある［**セキュリティ**］をクリックします。
1. 次のように入力します：

    ***アクセストークンの有効期間** ：
    ***アクセストークンのサイズ** ：

    ![自動ログインアクセストークンを有効にします。](./liferay-commerce-connector-to-punchout2go/images/04.png)

1. 完了したら、［**保存**］をクリックします。

自動ログイン **パンチアウト** トークンが設定されます。

<a name="create-a-punch-out-buyer-role" />

## パンチアウト購入者ロールの作成

ベストプラクティスとして、ユーザーはパンチアウトを使用するベンダー向けにロールを作成する必要があります。 コマースロールの詳細は、 [Commerce Roles](../users-and-accounts/roles-and-permissions/commerce-roles-reference.md)を参照してください。 Liferay DXPのロールと権限の詳細は、 [Understanding Roles and Permissions](https://learn.liferay.com/dxp/latest/ja/users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.html) を参照してください。

1. ［**コントロールパネル**］ → ［**ユーザー**］ → ［**ロール**］へ移動します。
1. ［**Site Roles**］ タブをクリックします。
1. ［**Add Site Role**］ボタンをクリックします。
1. 次のように入力します：
    ***Name** ：パンチアウトベンダー
1. ［**保存**］ をクリックします。
1. ［**Define Permissions**］をクリックします。
1. 左メニューの［**サイト管理**］を展開します。
1. ［**Applications**］ &rarr; ［**Open Carts**］に移動します。
1. （少なくとも）以下の項目にチェックを入れてください。：

    ***未処理注文をチェックアウト**
    ***未処理注文を表示**

    ![ロール権限のパンチアウト](./liferay-commerce-connector-to-punchout2go/images/05.png)

1. 完了したら、［**保存**］をクリックします。

必要最低限の権限でロールが作成されています。 パンチアウトを使用している購入者にこのロールを割り当てます。

<a name="verify-redirect-to-buyers-procurement-system" />

## 購入者の調達システムへのリダイレクトを確認する

PunchOut2Goへのコネクターが有効な状態でのチェックアウトプロセスも同様に行われます。 ユーザーが **Submit** をクリックすると、適切な調達システムにリダイレクトされます。

![注文が送信された後のリダイレクトをパンチアウトする。](./liferay-commerce-connector-to-punchout2go/images/06.png)

<a name="additional-information" />

## 追加情報

  - [Liferay Commerceを既存のLiferayインストールにデプロイする](../../installation-and-upgrades/installing-commerce-2.1-and-below/deploying-liferay-commerce-to-an-existing-liferay-installation.md)
  - [Liferay Commerce Enterpriseのアクティベーション](../../installation-and-upgrades/activating-liferay-commerce-enterprise.md)
  - [Liferay Commerce Connector to PunchOut2Goリファレンスガイド](./liferay-commerce-connector-to-punchout2go-reference-guide.md)
