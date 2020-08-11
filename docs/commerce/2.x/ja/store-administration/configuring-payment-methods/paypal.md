# PayPal

この記事では、PayPalを支払い方法として有効にする方法について詳しく説明します。

PayPalを使用するようにストアを設定する前に、PayPalクライアントIDとクライアントシークレット番号を生成する必要があります。 詳細については、[PayPal開発者ダッシュボード](https://developer.paypal.com/developer/applications/create)にアクセスしてください。

1.  *[Site Administration] → [Commerce] → [Settings] → [Payment Methods]*に移動します。
2.  *[PayPal]* をクリックします。
3.  *[Configuration]*をクリックします。
4.  次のように入力します：
      - **クライアントID**
      - **クライアントシークレット**
5.  ライブサイトの場合は*[Live]*を、テスト環境の場合は*[Sandbox]*を選択します。
6.  *[Payment Attempts Max Count]*フィールドに、サブスクリプションをキャンセルする前にサブスクリプションの支払いを試行する回数を入力します。
      - 詳細については、PayPalの記事[「Reattempt failed recurring payments with Subscribe buttons」](https://developer.paypal.com/docs/paypal-payments-standard/integration-guide/reattempt-failed-payment/)を参照してください。 ![PayPalの設定](./paypal/images/01.png)
7.  *[Save]*をクリックします。
8.  *[PayPal]*の隣にある*3ドットアイコン*をクリックし、次に*[Activate]*をクリックします。

PayPalがストアで有効になりました。

## 追加情報

他の支払い方法の追加に関する詳細は次のとおりです。

  - [Adding a New Currency](../currencies/adding-a-new-currency.md)
  - [Authorize.net](./authorize.net.md)
  - [Mercanet](./mercanet.md)
  - [Reattempt failed recurring payments with Subscribe buttons](https://developer.paypal.com/docs/paypal-payments-standard/integration-guide/reattempt-failed-payment/)
