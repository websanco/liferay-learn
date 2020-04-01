# Authorize.net

この記事では、Authorize.Netを支払い方法として有効にする方法について詳しく説明します。 [ Authorize.Net ](https://www.authorize.net/about-us/)はVisaの子会社であり、加盟店顧客に代わって処理ネットワークへの数十億件の取引の送信を管理する支払いゲートウェイサービスのプロバイダーです。

## 前提条件

Authorize.Netを使用するようにストアを設定する前に、[以下を生成する](https://support.authorize.net/s/article/How-do-I-obtain-my-API-Login-ID-and-Transaction-Key) 必要があります。

  - APIログインID
  - トランザクションキー

## 支払い方法としてAuthorize.netを有効にする

1.  *[Site Administration] → [Commerce] → [Settings] → [Payment Methods]*に移動します。
2.  *[Authorize.Net]*をクリックします。
3.  *[Configuration]*をクリックします。
4.  次のように入力します：
      - **APIログインID**
      - **トランザクションキー**
5.  ライブサイトの場合は*[Production]*を、テスト環境の場合は*[Sandbox]*を選択します。
6.  以下の内容を表示するには、以下のオプションをオンにします。
      - *Show Bank Account*
      - *Show Credit Card*
      - *Show Store Name*
7.  以下の内容を表示するには、以下のオプションをオンにします。
      - *Require CAPTCHA*
      - *Require Card Code Verification* ![Authorize.Netの設定](./authorize.net/images/01.png)
8.  *[Save]* をクリックします。
9.  *[Authorize.Net]*の隣にある*3ドットアイコン*をクリックし、次に*[Activate]*をクリックします。

完了すると、Authorize.Netが有効になります。

## 追加情報

  - [Adding a New Currency](../../starting-a-store/store-administration/adding-a-new-currency.md)
  - [Mercanet](./mercanet.md)
  - [PayPal](./paypal.md)
