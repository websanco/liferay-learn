# 通知テンプレートの使用

通知は、ユーザーに送信される自動メールです。 電子メール通知を送信するには、最初に **通知テンプレート** を作成します。 通知テンプレートは、電子メール通知の通知送信者、トリガー、およびコンテンツを定義します。 利用可能な通知テンプレートの完全なリストについては、 [Store Emails](./store-emails.md#notification-templates) 記事を参照してください。

```{note}
   Liferay Commerceの通知機能を使用するには、最初にLiferay Digital Experience Platform（DXP）のメール設定を構成します。 詳細は、 [メールサーバーへの接続](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.html) を参照してください。
```

<a name="creating-a-notification" />

## 通知を作成する

次の手順に従って、受信した注文の通知を作成します。

1. ［**コントロールパネル**］ → ［**コマース**］ → ［**チャネル**］ に移動します。

    ![Commerce 2.1チャネル](./using-notification-templates/images/03.png)

1. 目的のチャネルをクリックします。
1. ［**通知**］ タブをクリックします。
1. ［**通知テンプレート**］ サブタブをクリックします。
1. 追加（![Add icon](../../images/icon-add.png)）ボタンをクリックして、新しい通知テンプレートを作成します。
1. 次のように入力します：
    ***Name** ：新規注文
    ***Description** ：新規注文テンプレート
    ***Type** ： Order Placed
    ***有効** ：トグルを ［**YES**］ に切り替えます.
    ***From Address** ：ストアのメールアドレス（例： **orders@yourstore.com**）
    ***From Name** ：ストアまたは顧客サービス担当者の名前
    ***Subject** ：メールの件名（例： **ご注文を受け取りました**）
    ***本文** ：メッセージを入力します。 `[%ORDER_CREATOR%]` や `[%ORDER_ID%]` などの変数を使用して、それぞれ顧客の名前と注文IDを表すことができます。 詳細は、 [通知テンプレート変数リファレンスガイド](./notification-template-variables-reference-guide.md) を参照してください。

    ![2.1での通知テンプレートの追加](./using-notification-templates/images/02.png)

1. ［**保存**］ をクリックします。

新しい通知テンプレートが保存され、ストアは注文を受信すると、バイヤーに自動メールを送信します。

![通知キューですべての送信メールを確認してください。](./using-notification-templates/images/05.png)

<a name="example-using-placeholder-values" />

## 例：プレースホルダー値の使用

電子メール通知テンプレートを作成する場合、変数のコンマ区切りリストを、電子メールコンテンツの **電子メール設定** および **ボディ** フィールドのキー値の代わりとして使用できます。 キーの値には、顧客の名前、注文ID、配送先と請求先の住所、注文のアイテムのリストが含まれます。

**電子メール設定** および **電子メール本文** セクションでプレースホルダー変数を使用できます。 この例では、注文の受領を確認するメールテンプレート通知を作成します。

### メール設定

受信者、送信者アドレス、送信者名を定義するには、次のように入力します。

***送り先** : `[%ORDER_CREATOR%]`
***アドレス** から： orders@sahara.com
***差出人** : `[%ACCOUNT_ROLE_ORDER_MANAGER%]`

![電子メールでのプレースホルダー値の使用。](./using-notification-templates/images/06.png)

複数の受信者に通知する場合、上述したように、することができますに入力してカンマ区切りリスト **へ** フィールド： `[%ORDER_CREATOR%]`、`[%ACCOUNT_ROLE_ADMINISTRATOR%]`。 これにより、注文者とアカウント管理者にメールが送信されます。

### メール本文

通知の内容を定義するには、次のように入力します。

***件名** ：受注済み
***ボディ** ：

  ```
  Dear `[%ORDER_CREATOR%]`,

  Your Order `[%ORDER_ID%]` has been received.

  Regards,

  `[%ACCOUNT_ROLE_ORDER_MANAGER%]`
  ```

<a name="commerce-20-and-below" />

## Commerce 2.0以前

Commerce 2.0以下でメール通知を作成するには：

1. ［**サイト管理**］ → ［**コマース**］ → ［**設定**］ に移動します。

    ![Commerce 2.0サイト設定](./using-notification-templates/images/04.png)

1. ［**通知**］ タブをクリックします。
1. ［**通知テンプレート**］ サブタブをクリックします。
1. 追加（![Add icon](../../images/icon-add.png)）ボタンをクリックして、新しい通知テンプレートを作成します。
1. 次のように入力します：
    ***Name** ：新規注文
    ***Description** ：新規注文テンプレート
    ***Account Groups** ：（空白のままにするか、 **オプションの** アカウントグループを指定します）
    ***From Address** ：ストアのメールアドレス（例： **orders@yourstore.com**）
    ***From Name** ：ストアまたは顧客サービス担当者の名前
    ***Type** ： Order Placed
    ***有効** ：トグルを ［**YES**］ に切り替えます
    ***Subject** ：メールの件名（例： **ご注文を受け取りました**）
    ***本文** ： `[%ORDER_CREATOR%]` や `[%ORDER_ID%]` などの変数を使用して、それぞれ顧客の名前と注文IDを表すことができます。 詳細は、 [通知テンプレート変数リファレンスガイド](./notification-template-variables-reference-guide.md) を参照してください。

    ![通知テンプレートの追加](./using-notification-templates/images/01.png)

1. ［**保存**］ をクリックします。

新しい通知テンプレートが保存され、ストアが注文を受け取ると、ストアから購入者に自動メールが送信されます。 テンプレートが作成されると、注文が行われるたびに新しい通知が通知キューに追加されます。 注文が行われたときは、 ［**通知キュー**］ サブタブを確認してください。 デフォルトでは、Liferay Commerceは15分ごとにキューをチェックして、未送信の通知を送信します。 間隔を変更する方法については、 [コマース通知キューの構成](./configuring-the-commerce-notification-queue.md)参照してください。

<a name="additional-information" />

## 追加情報

* [通知テンプレート変数リファレンスガイド](./notification-template-variables-reference-guide.md)
* [コマース通知キューの構成](./configuring-the-commerce-notification-queue.md)
