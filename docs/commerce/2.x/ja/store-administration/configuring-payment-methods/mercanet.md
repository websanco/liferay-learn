# Mercanet

この記事では、Mercanetを支払い方法として有効にする方法について詳しく説明します。 Mercanetは**EURのみ**対応しているため、ストアの主要通貨がEURでなければなりません。

## Mercanetガイド

1.  [前提条件を確認します。](#prerequisites)
2.  [EURをストアの主要通貨として設定します。](#set-eur-as-the-primary-store-currency)
3.  [支払い方法としてMercanetを有効にします。](#activate-mercanet-as-a-payment-method)

## 前提条件

[ Mercanet](https://documentation.mercanet.bnpparibas.net/index.php?title=Obtenir_sa_cl%C3%A9_secr%C3%A8te#) を使用するようにストアを設定する前に、以下を生成する必要があります。

1.  Mercanetの販売者ID
2.  秘密鍵
3.  鍵バージョン

## EURをストアの主要通貨として設定する

Mercanetを使用する場合、ストアではユーロを主要通貨として使用する必要があります。 主要通貨を変更するには：

1.  *[Control Panel] → [Commerce] → [Settings]*に移動します。
2.  *[Currencies]*をクリックします。
3.  [Euro]の隣の*3ドットアイコン*をクリックします。
4.  *[Set as Primary]* をクリックします。

*ユーロ*が主要通貨として設定されました。

![主要通貨を設定](./mercanet/images/01.png)

## 支払い方法としてMercanetを有効にする

1.  *[Site Administration] → [Commerce] → [Settings] → [Payment Methods]*に移動します。
2.  *[Payment Methods]*をクリックします。
3.  *[Mercanet]*をクリックします。
4.  *[Configuration]*をクリックします。
5.  次のように入力します：
      - *販売者ID*
      - *秘密鍵*
      - *鍵バージョン*
6.  ライブサイトの場合は*[Production]*を、テスト環境の場合は*[Test]*または*[Simulation]*を選択します。 ![Mercanetの設定](./mercanet/images/02.png)
7.  *[Save]*をクリックします。
8.  *[Mercanet]*の隣にある*3ドットアイコン*をクリックし、次に*[Activate]*をクリックします。

Mercanetがストアで有効になりました。

## 追加情報

  - [新しい通貨の追加](../currencies/adding-a-new-currency.md)
  - [Authorize.Net](./authorize.net.md)
  - [PayPal](./paypal.md)
