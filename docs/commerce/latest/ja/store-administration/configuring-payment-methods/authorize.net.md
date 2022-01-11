# Authorize.net

この記事では、Authorize.Netを支払い方法として有効にする方法について詳しく説明します。 [ Authorize.Net ](https://www.authorize.net/about-us/) はVisaの子会社であり、加盟店顧客に代わって処理ネットワークへの数十億件の取引の送信を管理する支払いゲートウェイサービスのプロバイダーです。

## 前提条件

Authorize.Netを使用するようにストアを設定する前に、 [以下を生成する](https://support.authorize.net/s/article/How-do-I-obtain-my-API-Login-ID-and-Transaction-Key) 必要があります。

* APIログインID
* トランザクションキー

## 支払い方法としてAuthorize.netを有効にする

Authorize.netを支払い方法としてアクティブ化するには：

1. _コントロールパネル_ →Commerce→ _チャネル_ へ移動します。

    ![Commerce チャネル](./authorize.net/images/03.png)

1. 目的のチャネルをクリックします。
1. 下にスクロールして、Authorize.netの横にある _編集_ をクリックします。

    ![［編集］をクリックして、Authorize.netの構成を開始します。](./authorize.net/images/04.png)

1. _アクティブ_ トグルを _はい_ に切り替えます。
1. _設定_ タブをクリックします。
1. 次のように入力します：
    * **APIログインID**
    * **トランザクションキー**
1. _環境_ ドロップダウンメニューで目的の環境設定を選択します。
1. 以下の内容を表示するには、以下のオプションをオンにします。
    * _銀行口座を表示_
    * _クレジットカードを表示_
    * _店舗名を表示_
1. 以下の内容を表示するには、以下のオプションをオンにします。
    * _CAPTCHAが必要_
    * _カードコードの検証が必要_

     ![Authorize.Netの設定](./authorize.net/images/01.png)

1. ［_保存_］ をクリックします。
1. _Authorize.Net_ の隣にある _3ドットアイコン_ をクリックし、次に ［_有効にする_］ をクリックします。

完了すると、Authorize.Netが有効になります。

### Commerce 2.0以前

1. ［_サイト管理］ → ［コマース］ → ［設定］ → ［支払い方法_］ に移動します。

    ![Authorize.Netの設定](./authorize.net/images/05.png)

1. 追加（+）ボタンをクリックし、次に _Authorize.Net_ をクリックします。
1. ［_設定_］ をクリックします。
1. 次のように入力します：
    * **APIログインID**
    * **トランザクションキー**
1. ライブサイトの場合は ［_Production_］ を、テスト環境の場合は ［_Sandbox_］ を選択します。
1. 以下の内容を表示するには、以下のオプションをオンにします。
    * _銀行口座を表示_
    * _クレジットカードを表示_
    * _店舗名を表示_
1. 以下の内容を表示するには、以下のオプションをオンにします。
    * _Require CAPTCHA_
    * _カードコードの検証が必要_

       ![Authorize.Netの設定](./authorize.net/images/02.png)

1. ［_保存_］ をクリックします。
1. ［_Authorize.Net_］ の隣にある _3ドットアイコン_ をクリックし、次に ［_有効にする_］ をクリックします。

## 追加情報

* [新しい通貨の追加](../currencies/adding-a-new-currency.md)
* [Mercanet](./mercanet.md)
* [PayPal](./paypal.md)
