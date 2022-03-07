# DXP Cloudサービスへのログイン

デフォルトでは、DXPおよびインフラストラクチャサービスのWebインターフェイスに直接アクセスするにはログインが必要です。 これらのサービスのログイン資格情報は通常、初期のオンボーディングメールで提供されますが、必要に応じてDXP Cloud Consoleに配置される場合があります。

<a name="locating-login-credentials" />

## ログイン資格情報の検索

1. **infra** 環境に移動します。
1. 左側のメニューで［**Services**］をクリックします。
1. ［**ci**］ サービスを選択します。
1. ［**Environment Variables**］ タブをクリックします。
1. `JENKINS_CUSTOMER_PASSWWORD` 横の ［**show**］ アイコンを表示して、パスワードを取得します。
1. `JENKINS_CUSTOMER_USER_NAME` 横にある ［**show**］ アイコンをクリックして、ユーザー名を取得します。

![ウェブサーバー](./logging-into-your-dxp-cloud-services/images/01.png)

<a name="logging-in" />

## ログイン

1. 選択した環境（**dev** 、 **prd** 、 など）に移動します。

1. ページの上部にある ［**Visit Site**］ ドロップダウンメニューをクリックします。

    ![［Visit Site］ドロップダウンには、環境内のLiferayインスタンスの利用可能なエンドポイントがすべて表示されます。](./logging-into-your-dxp-cloud-services/images/02.png)

    ドロップダウンメニューは、アクセスすることのできる全ての利用可能な`liferay`サービスを表示します。これには、デフォルトの`webserver`サービスエンドポイントと環境に追加したカスタムドメインが含まれています。

1. 目的のエンドポイントをクリックして、対応するURLでLiferayインスタンスにアクセスできます。

1. プロンプトされたら、DXP Cloudの [Jenkinsユーザーネームと パスワード](#locating-login-credentials) を入力します。

    ![認証](./logging-into-your-dxp-cloud-services/images/03.png)

1. これにより、ユーザーがLiferay DXP 7.xインスタンス（この例ではLiferay DXP 7.2 GA1）にリダイレクトされます。

    ![DXP 7.2 GA1サインイン](./logging-into-your-dxp-cloud-services/images/04.png)

<a name="finding-default-service-urls" />

### デフォルトのサービスURLの検索

デフォルトの`webserver`エンドポイントのURLを見つけるには、サービスのページに移動し 、ページの１番上にある`webserver` ロゴ をクリックします（そうするとURLに展開されます）：

![サービス名のロゴをクリックすると、そのサービスに該当するURLが表示されます。](./logging-into-your-dxp-cloud-services/images/05.png)

この方法でLiferayにアクセスする際には、 [Jenkinsログイン資格情報](#locating-login-credentials) が必要です。

同様の手順で、Jenkins CIのWebインターフェイスにもアクセスできます。 Jenkins URLを見つけるには、`infra`環境にある`ci`サービスに移動します：

![また、CIサービスでは、Jenkins CIのWebインターフェイスで利用できるURLが用意されています。](./logging-into-your-dxp-cloud-services/images/06.png)

<a name="additional-information" />

## 追加情報

* [DXPサービスの概要](../using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.md)
* [継続的インテグレーション](../platform-services/continuous-integration.md)
* [Webサーバーサービス](../platform-services/web-server-service.md)
