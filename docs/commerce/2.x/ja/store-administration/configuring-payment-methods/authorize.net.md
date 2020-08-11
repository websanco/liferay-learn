# Authorize.net

この記事では、Authorize.Netを支払い方法として有効にする方法について詳しく説明します。 [ Authorize.Net ](https://www.authorize.net/about-us/)はVisaの子会社であり、加盟店顧客に代わって処理ネットワークへの数十億件の取引の送信を管理する支払いゲートウェイサービスのプロバイダーです。

## 前提条件

Authorize.Netを使用するようにストアを設定する前に、[以下を生成する](https://support.authorize.net/s/article/How-do-I-obtain-my-API-Login-ID-and-Transaction-Key) 必要があります。

  - APIログインID
  - トランザクションキー

## 支払い方法としてAuthorize.netを有効にする

Authorize.netを支払い方法としてアクティブ化するには：

1.  *コントロールパネル* →Commerce→ *チャネル*へ行きます。

    ![Commerce チャネル](./authorize.net/images/03.png)

2.  目的のチャネルをクリックします。

3.  下にスクロールして、Authorize.netの横にある *編集* をクリックします。

    ![[編集]をクリックして、Authorize.netの構成を開始します。](./authorize.net/images/04.png)

4.  *アクティブ* トグルを *はい*に切り替えます。

5.  *設定* タブをクリックします。

6.  次のように入力します：

      - **APIログインID**
      - **トランザクションキー**

7.  *環境* ドロップダウンメニューで目的の環境設定を選択します。

8.  以下の内容を表示するには、以下のオプションをオンにします。

      - *銀行口座を表示*
      - *クレジットカードを表示*
      - *店舗名を表示*

9.  以下の内容を表示するには、以下のオプションをオンにします。

      - *CAPTCHAが必要*
      - *カードコードの検証が必要*

    ![Authorize.Netの設定](./authorize.net/images/01.png)

10. *[Save]*をクリックします。

11. *[Authorize.Net]*の隣にある*3ドットアイコン*をクリックし、次に*[Activate]*をクリックします。

完了すると、Authorize.Netが有効になります。

### Commerce 2.0以前

1.  *[Site Administration] → [Commerce] → [Settings] → [Payment Methods]*に移動します。

    ![Authorize.Netの設定](./authorize.net/images/05.png)

2.  追加（+）ボタンをクリックし、次に *Authorize.Net*をクリックします。

3.  *[Configuration]*をクリックします。

4.  次のように入力します：

      - **APIログインID**
      - **トランザクションキー**

5.  ライブサイトの場合は*[Production]*を、テスト環境の場合は*[Sandbox]*を選択します。

6.  以下の内容を表示するには、以下のオプションをオンにします。

      - *銀行口座を表示*
      - *クレジットカードを表示*
      - *店舗名を表示*

7.  以下の内容を表示するには、以下のオプションをオンにします。

      - *CAPTCHAが必要*

      - *カードコードの検証が必要*

        ![Authorize.Netの設定](./authorize.net/images/02.png)

8.  *[Save]*をクリックします。

9.  *[Authorize.Net]*の隣にある*3ドットアイコン*をクリックし、次に*[Activate]*をクリックします。

## 追加情報

  - [Adding a New Currency](../currencies/adding-a-new-currency.md)
  - [Mercanet](./mercanet.md)
  - [PayPal](./paypal.md)
