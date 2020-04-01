# 通知テンプレートを使用したストアメールの自動化

通知は、ユーザーに送信される自動メールです。 電子メール通知を送信するには、最初に*通知テンプレート*を作成します。 通知テンプレートは、電子メール通知の通知送信者、トリガー、およびコンテンツを定義します。

> **注：**Liferay Commerceの通知機能を使用するには、システム管理者が*最初*にLiferay Digital Experience Platform（DXP）のメール設定を構成する必要があります。 詳細については、[User Subscriptions and Mailing Lists](https://help.liferay.com/hc/en-us/articles/360017896652-Installing-Liferay-DXP#configuring-mail)を参照してください。

メール設定を構成したら、次の手順に従って、受信した注文に対する通知を作成します。

1.  *[Site Administration]* → *[Commerce]* → *[Settings]*に移動します。

2.  *[Notifications]*タブをクリックします。

3.  *[Notification Templates]*サブタブをクリックします。

4.  （+）ボタンをクリックして、新しい通知テンプレートを作成します。

5.  次のように入力します：

      - **Name**：新規注文
      - **Description**：新規注文テンプレート
      - **Account Groups**：（空白のままにするか、**オプションの**アカウントグループを指定します）
      - **From Address**：ストアのメールアドレス（例： *<orders@yourstore.com>*）
      - **From Name**：ストアまたは顧客サービス担当者の名前
      - **Type**： Order Placed
      - **Enabled**：トグルを*[YES]*に切り替えます
      - **Subject**：メールの件名（例：*ご注文を受け取りました * ）
      - **Body**：メール本文\*

    ![通知テンプレート](./automating-store-emails-by-using-notification-templates/images/01.png)

6.  *[Save]*をクリックします。

新しい通知テンプレートが保存され、ストアが注文を受け取ると、ストアから購入者に自動メールが送信されます。 テンプレートが作成されると、注文が行われるたびに新しい通知が通知キューに追加されます。 注文が行われたときは、*[Notifications Queue]*サブタブを確認してください。 Liferay Commerceは、指定された間隔でキューをチェックして、未送信の通知を送信します。

## 追加の通知

**注1 **：\* Liferay Commerceでは、注文作成者の名前と注文IDをプログラムで挿入するための2つのスニペットを提供しています。

  - \[%ORDER\_CREATOR%\]は、注文を作成したユーザーを挿入します
  - \[%ORDER\_ID%\]は注文IDを挿入します

**注2 **：拡張ポイントを使用すると、出荷済みの注文、補充された商品、運送業者の例外、その他のイベントなど、他の通知を実装できます。

**注3 **：Liferay Commerceが未送信の通知をチェックする間隔を変更するには、*[Control Panel]* → *[Configuration]* → *[System Settings]*に移動します。 *[Orders]*をクリックしてから、*[Commerce Notification Queue]*をクリックします。 デフォルト値は分単位でリストされています。 必要に応じて、[Check Interval]と[Delete Interval]の値を変更します。

![間隔を変更する](./automating-store-emails-by-using-notification-templates/images/02.png)
