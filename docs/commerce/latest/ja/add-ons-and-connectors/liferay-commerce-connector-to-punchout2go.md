# Liferay Commerce Connector to PunchOut2Go

> 変更通知を受け取り（購読）が必要

Liferayは、ユーザーの既存のPunchOut2GoインフラとCommerceを統合できるPunchOut2Goコネクターを備えています。 このコネクターは、LPKGファイルとして [Liferayヘルプセンタ](http://customer.liferay.com/downloads)からダウンロードできます。 <!--!\[Punch Out Flow Diagram\](./liferay-commerce-connector-to-punchout2go/images/01.png) -->

```{important}
PunchOut2Goコネクターを使用するには、Liferay Commerce 2.1.2およびLiferay DXP 7.1 FP18、または7.2 FP6が必要です。
```

## PunchOut2Goコネクターのデプロイ

競合を避けるため、PunchOut2Goコネクターをデプロイする前にLiferayサーバーにCommerceがデプロイ済みであることを確認してください。 次に、以下の手順でPunchOut2Go LPKGコネクターをデプロイします。

1.  *Commerce Connector to PunchOut2Go*を[Liferayヘルプセンター](http://customer.liferay.com/downloads)からダウンロードします。

2.  Copy the *PunchOut2Goコネクター* `LPKG`を[`${liferay.home}/deploy`](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/liferay-home.html)フォルダーにコピーします。

3.  アプリケーションサーバーコンソールに次のメッセージが表示されることを確認します。
   
        2020-07-24 22:10:01.924 INFO  [fileinstall-/../../liferay-portal-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:209] The portal instance needs to be restarted to complete the installation of file:/../../liferay-portal-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20Connector%20to%20PunchOut2Go%20-%20API.lpkg
        2020-07-24 22:10:01.926 INFO  [fileinstall-../../liferay-portal-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:209] The portal instance needs to be restarted to complete the installation of file:/../../liferay-portal-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20Connector%20to%20PunchOut2Go%20-%20Impl.lpkg`

4.  アプリケーションサーバーを再起動します。 <!-- 1. Verify that the following message displays in the application server console:

    ```
     [Success message]
    ```
--> サーバーの再起動が完了すると、コネクターがアクティブになり、設定できるようになります。

## コネクターの設定

PunchOut2Goコネクターは、[チャネル](../starting-a-store/channels/introduction-to-channels.md)単位で設定されます。

ユーザーはストアのカタログページのURLを所有していなければなりません。この例では、以下になります。 `http://localhost:8080/web/everest.com/catalog`

1.  *[コントロールパネル]* → *[Commerce]* → *[Channels]* に移動します。
2.  目的のチャネルをクリックします。
3.  *[パンチアウト]* タブをクリックします。
4.  トグルを *[Enabled]* に切り替えます。
5.  パンチアウトの開始URLを入力します。
6.  完了したら、*[保存]* をクリックします。

### 自動ログインのパンチアウトアクセストークンを有効にします。

ユーザーは、購入者の調達システムのパンチアウトアクセストークンを有効にし、Liferay Commerceインスタンスにアクセスできるようにします。 トークンを有効にするには、

1.  *[コントロールパネル]* → *[Configuration]* → *[System Settings]* に移動します。

2.  *[セキュリティ]* で、*API認証*をクリックします。

    ![認証](./liferay-commerce-connector-to-punchout2go/images/02.png)

3.  左メニューの*[自動ログインのパンチアウトアクセストークン]* をクリックします。

4.  *[Enabled]* のチェックボックスをオンにします。

    ![自動ログインアクセストークンを有効にします。](./liferay-commerce-connector-to-punchout2go/images/03.jpg)

5.  *[アップデート]* ボタンをクリックします。

自動ログインパンチアウトトークンが有効になり、購入者の調達システムがログインできるようになります。

### パンチアウトアクセストークンプロバイダーの設定

パンチアウトアクセストークンを有効にすると、ユーザーはパンチアウトアクセストークンプロバイダーを設定できます。 具体的には、トークンの持続時間やサイズを設定できます。 以下の手順に従ってください。

1.  *[コントロールパネル]* → *[Configuration]* → *[System Settings]* に移動します。

2.  *[セキュリティ]* で、*OAuth2*をクリックします。

3.  次のように入力します：

      - **アクセストークンの有効期間**：
      - **アクセストークンのサイズ**：

    ![自動ログインアクセストークンを有効にします。](./liferay-commerce-connector-to-punchout2go/images/04.jpg)

4.  完了したら、*[保存]* をクリックします。

自動ログイン*パンチアウト*トークンが設定されます。

## パンチアウト購入者ロールの作成

ベストプラクティスとして、ユーザーはパンチアウトを使用するベンダー向けにロールを作成する必要があります。 Liferay Commerceロールの詳細については、[「アカウントロール」](../account-management/account-roles.md)および[カスタムアカウントロールの作成」](../account-management/creating-a-custom-account-role.md)を参照してください。 Liferay DXPのロールと権限の詳細は、「ロールと権限について」を参照してください。

1.  *コントロールパネル* → *ユーザー* → *ロール*へ移動します。

2.  *Site Roles* タブをクリックします。

3.  *[サイトロールを追加]* ボタンをクリックします。

4.  次のように入力します：

      - **Name**：パンチアウトベンダー

5.  *[保存]* をクリックします。

6.  *権限の定義*をクリックします。

7.  左メニューの*[サイト管理]* を展開します。

8.  *[Applications]* → *[カートを開く]* に移動します。

9.  （少なくとも）以下の項目にチェックを入れてください。：

      - **未処理注文をチェックアウト**
      - **未処理注文を表示**

    ![ロール権限のパンチアウト](./liferay-commerce-connector-to-punchout2go/images/05.png)

10. 完了したら、*[保存]* をクリックします。

必要最低限の権限でロールが作成されています。 パンチアウトを使用している購入者にこのロールを割り当てます。

## 購入者の調達システムへのリダイレクトを確認する

PunchOut2Goへのコネクターが有効な状態でのチェックアウトプロセスも同様に行われます。 ユーザーが*送信*をクリックすると、適切な調達システムにリダイレクトされます。

![注文が送信された後のリダイレクトをパンチアウトする。](./liferay-commerce-connector-to-punchout2go/images/06.jpg)

## 追加情報

  - [Liferay Commerceを既存のLiferayインストールにデプロイする](../installation-and-upgrades/installing-commerce-2.1-and-below/deploying-liferay-commerce-to-an-existing-liferay-installation.md)
  - [Liferay Commerce Enterpriseのアクティベーション](../installation-and-upgrades/activating-liferay-commerce-enterprise.md)
  - [Liferay Commerce Connector to PunchOut2Goリファレンスガイド](./liferay-commerce-connector-to-punchout2go-reference-guide.md)
