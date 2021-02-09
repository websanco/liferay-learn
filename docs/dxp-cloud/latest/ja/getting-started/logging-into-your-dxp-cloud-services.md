# DXP Cloudサービスへのログイン

デフォルトでは、DXPおよびインフラストラクチャサービスのWebインターフェイスに直接アクセスするにはログインが必要です。 これらのサービスのログイン資格情報は通常、初期のオンボーディングメールで提供されますが、必要に応じてDXP Cloud管理コンソールに配置される場合があります。

## ログイン資格情報の検索

1.  *infra* 環境に移動します。
2.  左側のメニューで[ *Services* ]をクリックします。
3.  *ci* サービスを選択します。
4.  「 *環境変数*」 タブをクリックします。
5.  `JENKINS_CUSTOMER_PASSWWORD` 横の *show* アイコンを表示して、パスワードを取得します。
6.  `JENKINS_CUSTOMER_USER_NAME` 横にある *show* アイコンをクリックして、ユーザー名を取得します。

![ウェブサーバー](./logging-into-your-dxp-cloud-services/images/01.png)

## ログイン

1.  *webserver* サービスが配置されている環境に移動します。

2.  ページの上部にある *webserver* ロゴをクリックします。

    ![ウェブサーバー](./logging-into-your-dxp-cloud-services/images/02.png)

3.  プロンプトが表示されたら、DXP Cloud Jenkinsのユーザー名とパスワードを入力します。

    ![認証](./logging-into-your-dxp-cloud-services/images/03.png)

4.  これにより、ユーザーがLiferay DXP 7.xインスタンス（この例ではLiferay DXP 7.2 GA1）にリダイレクトされます。

    ![DXP 7.2 GA1サインイン](./logging-into-your-dxp-cloud-services/images/04.png)

Jenkins CI Webインターフェースにアクセスするには、同様の手順を実行できます。

## 追加情報

  - [DXPサービスの概要](../using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.md)
  - [継続的インテグレーション](../platform-services/continuous-integration.md)
  - [Webサーバーサービス](../platform-services/web-server-service.md)
