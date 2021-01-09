# 通知テンプレートの使用

通知は、ユーザーに送信される自動メールです。 電子メール通知を送信するには、最初に*通知テンプレート*を作成します。 通知テンプレートは、電子メール通知の通知送信者、トリガー、およびコンテンツを定義します。 利用可能な通知テンプレートの完全なリストについては、 [Store Emails](./store-emails.md#notification-templates) 記事を参照してください。

``` note::
   Liferay Commerceの通知機能を使用するには、最初にLiferay Digital Experience Platform（DXP）のメール設定を構成します。 詳細については、 `メールサーバー への接続 <https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.html>`_ を参照してください。
```

## 通知を作成する

次の手順に従って、受信した注文の通知を作成します。

1.  *[Control Panel]* → *[Commerce]* → *[Channels]*に移動します。

    ![Commerce 2.1チャネル](./using-notification-templates/images/03.png)

2.  目的のチャネルをクリックします。

3.  *[Notifications]*タブをクリックします。

4.  *[Notification Templates]*サブタブをクリックします。

5.  追加（![Add icon](../../images/icon-add.png)）ボタンをクリックして、新しい通知テンプレートを作成します。

6.  次のように入力します：

      - **Name**：新規注文
      - **Description**：新規注文テンプレート
      - **Type**： Order Placed
      - **Enabled**：トグルを*[YES]*に切り替えます.
      - **From Address**：ストアのメールアドレス（例： *<orders@yourstore.com>*）
      - **From Name**：ストアまたは顧客サービス担当者の名前
      - **Subject**：メールの件名（例：*ご注文を受け取りました * ）
      - **本文**：メッセージを入力します。 `[%ORDER_CREATOR%]` や `[%ORDER_ID%]` などの変数を使用して、それぞれ顧客の名前と注文IDを表すことができます。 詳細については、 [通知テンプレート変数リファレンスガイド](./notification-template-variables-reference-guide.md) を参照してください。

    ![2.1での通知テンプレートの追加](./using-notification-templates/images/02.png)

7.  *[Save]*をクリックします。

新しい通知テンプレートが保存され、ストアは注文を受信すると、バイヤーに自動メールを送信します。

![通知キューですべての送信メールを確認してください。](./using-notification-templates/images/05.png)

## 例：プレースホルダー値の使用

電子メール通知テンプレートを作成する場合、変数のコンマ区切りリストを、電子メールコンテンツの *電子メール設定* および *ボディ* フィールドのキー値の代わりとして使用できます。 キーの値には、顧客の名前、注文ID、配送先と請求先の住所、注文のアイテムのリストが含まれます。

*電子メール設定* および *電子メール本文* セクションでプレースホルダー変数を使用できます。 この例では、注文の受領を確認するメールテンプレート通知を作成します。

### メール設定

受信者、送信者アドレス、送信者名を定義するには、次のように入力します。

  - **から**： `[%ORDER_CREATOR%]`
  - **アドレス**から： <orders@sahara.com>
  - **名前から**： `[%ACCOUNT_ROLE_ORDER_MANAGER%]`

![電子メールでのプレースホルダー値の使用。](./using-notification-templates/images/06.png)

複数の受信者に通知する場合、上述したように、することができますに入力してカンマ区切りリスト *へ* フィールド： `[%ORDER_CREATOR%]`、`[%ACCOUNT_ROLE_ADMINISTRATOR%]`。 これにより、注文者とアカウント管理者にメールが送信されます。

### メール本文

通知の内容を定義するには、次のように入力します。

  - **件名**：受注済み

  - **ボディ**：
    
        `[%ORDER_CREATOR%]`様
        
        ご注文`[%ORDER_ID%]`を承りました。
        
        ありがとうございます。
        
        `[%ACCOUNT_ROLE_ORDER_MANAGER%]`

## Commerce 2.0以前

Commerce 2.0以下でメール通知を作成するには：

1.  *[Site Administration]* → *[Commerce]* → *[Settings]*に移動します。

    ![Commerce 2.0サイト設定](./using-notification-templates/images/04.png)

2.  *[Notifications]*タブをクリックします。

3.  *[Notification Templates]*サブタブをクリックします。

4.  追加（![Add icon](../../images/icon-add.png)）ボタンをクリックして、新しい通知テンプレートを作成します。

5.  次のように入力します：

      - **Name**：新規注文
      - **Description**：新規注文テンプレート
      - **Account Groups**：（空白のままにするか、**オプションの**アカウントグループを指定します）
      - **From Address**：ストアのメールアドレス（例： *<orders@yourstore.com>*）
      - **From Name**：ストアまたは顧客サービス担当者の名前
      - **Type**： Order Placed
      - **Enabled**：トグルを*[YES]*に切り替えます
      - **Subject**：メールの件名（例：*ご注文を受け取りました * ）
      - **本文**： `[%ORDER_CREATOR%]` や `[%ORDER_ID%]` などの変数を使用して、それぞれ顧客の名前と注文IDを表すことができます。 詳細については、 [通知テンプレート変数リファレンスガイド](./notification-template-variables-reference-guide.md) を参照してください。

    ![通知テンプレートの追加](./using-notification-templates/images/01.png)

6.  *[Save]*をクリックします。

新しい通知テンプレートが保存され、ストアが注文を受け取ると、ストアから購入者に自動メールが送信されます。 テンプレートが作成されると、注文が行われるたびに新しい通知が通知キューに追加されます。 注文が行われたときは、*[Notifications Queue]*サブタブを確認してください。 デフォルトでは、Liferay Commerceは15分ごとにキューをチェックして、未送信の通知を送信します。 間隔を変更する方法については、 [コマース通知キューの構成](./configuring-the-commerce-notification-queue.md)参照してください。

## 追加情報

  - [通知テンプレート変数リファレンスガイド](./notification-template-variables-reference-guide.md)
  - [コマース通知キューの構成](./configuring-the-commerce-notification-queue.md)
