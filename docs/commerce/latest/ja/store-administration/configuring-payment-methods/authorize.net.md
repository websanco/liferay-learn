# Authorize.net

この記事では、Authorize.Netを支払い方法として有効にする方法について詳しく説明します。 [ Authorize.Net ](https://www.authorize.net/about-us/) はVisaの子会社であり、加盟店顧客に代わって処理ネットワークへの数十億件の取引の送信を管理する支払いゲートウェイサービスのプロバイダーです。

## 前提条件

Authorize.Netを使用するようにストアを設定する前に、 [以下を生成する](https://support.authorize.net/s/article/How-do-I-obtain-my-API-Login-ID-and-Transaction-Key) 必要があります。

* APIログインID
* トランザクションキー

## 支払い方法としてAuthorize.netを有効にする

Authorize.netを支払い方法としてアクティブ化するには：

1. **コントロールパネル** →Commerce→ **チャネル** へ移動します。

    ![Commerce チャネル](./authorize.net/images/03.png)

1. 目的のチャネルをクリックします。
1. 下にスクロールして、Authorize.netの横にある **編集** をクリックします。

    ![［編集］をクリックして、Authorize.netの構成を開始します。](./authorize.net/images/04.png)

1. **アクティブ** トグルを **はい** に切り替えます。
1. **設定** タブをクリックします。
1. 次のように入力します：
    ***APIログインID**
    ***トランザクションキー**
1. **環境** ドロップダウンメニューで目的の環境設定を選択します。
1. 以下の内容を表示するには、以下のオプションをオンにします。
    ***銀行口座を表示**
    ***クレジットカードを表示**
    ***店舗名を表示**
1. 以下の内容を表示するには、以下のオプションをオンにします。
    ***CAPTCHAが必要**
    ***カードコードの検証が必要**

     ![Authorize.Netの設定](./authorize.net/images/01.png)

1. ［**保存**］ をクリックします。
1. **Authorize.Net** の隣にある **3ドットアイコン** をクリックし、次に ［**有効にする**］ をクリックします。

完了すると、Authorize.Netが有効になります。

### Commerce 2.0以前

1. ［**サイト管理］ → ［コマース］ → ［設定］ → ［支払い方法**］ に移動します。

    ![Authorize.Netの設定](./authorize.net/images/05.png)

1. 追加（+）ボタンをクリックし、次に **Authorize.Net** をクリックします。
1. ［**設定**］ をクリックします。
1. 次のように入力します：
    ***APIログインID**
    ***トランザクションキー**
1. ライブサイトの場合は ［**Production**］ を、テスト環境の場合は ［**Sandbox**］ を選択します。
1. 以下の内容を表示するには、以下のオプションをオンにします。
    ***銀行口座を表示**
    ***クレジットカードを表示**
    ***店舗名を表示**
1. 以下の内容を表示するには、以下のオプションをオンにします。
    ***Require CAPTCHA**
    ***カードコードの検証が必要**

       ![Authorize.Netの設定](./authorize.net/images/02.png)

1. ［**保存**］ をクリックします。
1. ［**Authorize.Net**］ の隣にある **3ドットアイコン** をクリックし、次に ［**有効にする**］ をクリックします。

## 追加情報

* [新しい通貨の追加](../currencies/adding-a-new-currency.md)
* [Mercanet](./mercanet.md)
* [PayPal](./paypal.md)
